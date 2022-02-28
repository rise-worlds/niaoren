package com.lody.virtual.server.pm.installer;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.p001pm.IPackageInstallerCallback;
import android.content.p001pm.IPackageInstallerSession;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.SparseArray;
import com.lody.virtual.client.core.VirtualCore;
import com.lody.virtual.helper.compat.ObjectsCompat;
import com.lody.virtual.helper.utils.Singleton;
import com.lody.virtual.os.VBinder;
import com.lody.virtual.os.VEnvironment;
import com.lody.virtual.os.VUserHandle;
import com.lody.virtual.remote.VParceledListSlice;
import com.lody.virtual.server.IPackageInstaller;
import com.lody.virtual.server.pm.VAppManagerService;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Random;

@TargetApi(21)
/* renamed from: com.lody.virtual.server.pm.installer.VPackageInstallerService */
/* loaded from: classes.dex */
public class VPackageInstallerService extends IPackageInstaller.Stub {
    private static final long MAX_ACTIVE_SESSIONS = 1024;
    private static final String TAG = "PackageInstaller";
    private static final Singleton<VPackageInstallerService> gDefault = new Singleton<VPackageInstallerService>() { // from class: com.lody.virtual.server.pm.installer.VPackageInstallerService.1
        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.lody.virtual.helper.utils.Singleton
        public VPackageInstallerService create() {
            return new VPackageInstallerService();
        }
    };
    private final Callbacks mCallbacks;
    private Context mContext;
    private final Handler mInstallHandler;
    private final HandlerThread mInstallThread;
    private final InternalCallback mInternalCallback;
    private final Random mRandom;
    private final SparseArray<PackageInstallerSession> mSessions;

    private boolean isCallingUidOwner(PackageInstallerSession packageInstallerSession) {
        return true;
    }

    private VPackageInstallerService() {
        this.mRandom = new SecureRandom();
        this.mSessions = new SparseArray<>();
        this.mInternalCallback = new InternalCallback();
        this.mContext = VirtualCore.get().getContext();
        this.mInstallThread = new HandlerThread(TAG);
        this.mInstallThread.start();
        this.mInstallHandler = new Handler(this.mInstallThread.getLooper());
        this.mCallbacks = new Callbacks(this.mInstallThread.getLooper());
    }

    public static VPackageInstallerService get() {
        return gDefault.get();
    }

    private static int getSessionCount(SparseArray<PackageInstallerSession> sparseArray, int i) {
        int size = sparseArray.size();
        int i2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            if (sparseArray.valueAt(i3).installerUid == i) {
                i2++;
            }
        }
        return i2;
    }

    @Override // com.lody.virtual.server.IPackageInstaller
    public int createSession(SessionParams sessionParams, String str, int i) {
        try {
            return createSessionInternal(sessionParams, str, i, VBinder.getCallingUid());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private int createSessionInternal(SessionParams sessionParams, String str, int i, int i2) throws IOException {
        int allocateSessionIdLocked;
        PackageInstallerSession packageInstallerSession;
        synchronized (this.mSessions) {
            if (getSessionCount(this.mSessions, i2) < 1024) {
                allocateSessionIdLocked = allocateSessionIdLocked();
                packageInstallerSession = new PackageInstallerSession(this.mInternalCallback, this.mContext, this.mInstallHandler.getLooper(), str, allocateSessionIdLocked, i, i2, sessionParams, VEnvironment.getPackageInstallerStageDir());
            } else {
                throw new IllegalStateException("Too many active sessions for UID " + i2);
            }
        }
        synchronized (this.mSessions) {
            this.mSessions.put(allocateSessionIdLocked, packageInstallerSession);
        }
        this.mCallbacks.notifySessionCreated(packageInstallerSession.sessionId, packageInstallerSession.userId);
        return allocateSessionIdLocked;
    }

    @Override // com.lody.virtual.server.IPackageInstaller
    public void updateSessionAppIcon(int i, Bitmap bitmap) {
        synchronized (this.mSessions) {
            PackageInstallerSession packageInstallerSession = this.mSessions.get(i);
            if (packageInstallerSession == null || !isCallingUidOwner(packageInstallerSession)) {
                throw new SecurityException("Caller has no access to session " + i);
            }
            packageInstallerSession.params.appIcon = bitmap;
            packageInstallerSession.params.appIconLastModified = -1L;
            this.mInternalCallback.onSessionBadgingChanged(packageInstallerSession);
        }
    }

    @Override // com.lody.virtual.server.IPackageInstaller
    public void updateSessionAppLabel(int i, String str) {
        synchronized (this.mSessions) {
            PackageInstallerSession packageInstallerSession = this.mSessions.get(i);
            if (packageInstallerSession == null || !isCallingUidOwner(packageInstallerSession)) {
                throw new SecurityException("Caller has no access to session " + i);
            }
            packageInstallerSession.params.appLabel = str;
            this.mInternalCallback.onSessionBadgingChanged(packageInstallerSession);
        }
    }

    @Override // com.lody.virtual.server.IPackageInstaller
    public void abandonSession(int i) {
        synchronized (this.mSessions) {
            PackageInstallerSession packageInstallerSession = this.mSessions.get(i);
            if (packageInstallerSession == null || !isCallingUidOwner(packageInstallerSession)) {
                throw new SecurityException("Caller has no access to session " + i);
            }
            try {
                packageInstallerSession.abandon();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override // com.lody.virtual.server.IPackageInstaller
    public IPackageInstallerSession openSession(int i) {
        try {
            return openSessionInternal(i);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private IPackageInstallerSession openSessionInternal(int i) throws IOException {
        PackageInstallerSession packageInstallerSession;
        synchronized (this.mSessions) {
            packageInstallerSession = this.mSessions.get(i);
            if (packageInstallerSession == null || !isCallingUidOwner(packageInstallerSession)) {
                throw new SecurityException("Caller has no access to session " + i);
            }
            packageInstallerSession.open();
        }
        return packageInstallerSession;
    }

    @Override // com.lody.virtual.server.IPackageInstaller
    public SessionInfo getSessionInfo(int i) {
        SessionInfo generateInfo;
        synchronized (this.mSessions) {
            PackageInstallerSession packageInstallerSession = this.mSessions.get(i);
            generateInfo = packageInstallerSession != null ? packageInstallerSession.generateInfo() : null;
        }
        return generateInfo;
    }

    @Override // com.lody.virtual.server.IPackageInstaller
    public VParceledListSlice getAllSessions(int i) {
        ArrayList arrayList = new ArrayList();
        synchronized (this.mSessions) {
            for (int i2 = 0; i2 < this.mSessions.size(); i2++) {
                PackageInstallerSession valueAt = this.mSessions.valueAt(i2);
                if (valueAt.userId == i) {
                    arrayList.add(valueAt.generateInfo());
                }
            }
        }
        return new VParceledListSlice(arrayList);
    }

    @Override // com.lody.virtual.server.IPackageInstaller
    public VParceledListSlice getMySessions(String str, int i) {
        ArrayList arrayList = new ArrayList();
        synchronized (this.mSessions) {
            for (int i2 = 0; i2 < this.mSessions.size(); i2++) {
                PackageInstallerSession valueAt = this.mSessions.valueAt(i2);
                if (ObjectsCompat.equals(valueAt.installerPackageName, str) && valueAt.userId == i) {
                    arrayList.add(valueAt.generateInfo());
                }
            }
        }
        return new VParceledListSlice(arrayList);
    }

    @Override // com.lody.virtual.server.IPackageInstaller
    public void registerCallback(IPackageInstallerCallback iPackageInstallerCallback, int i) {
        this.mCallbacks.register(iPackageInstallerCallback, i);
    }

    @Override // com.lody.virtual.server.IPackageInstaller
    public void unregisterCallback(IPackageInstallerCallback iPackageInstallerCallback) {
        this.mCallbacks.unregister(iPackageInstallerCallback);
    }

    @Override // com.lody.virtual.server.IPackageInstaller
    public void uninstall(String str, String str2, int i, IntentSender intentSender, int i2) {
        boolean uninstallPackage = VAppManagerService.get().uninstallPackage(str);
        if (intentSender != null) {
            Intent intent = new Intent();
            intent.putExtra("android.content.pm.extra.PACKAGE_NAME", str);
            intent.putExtra("android.content.pm.extra.STATUS", !uninstallPackage);
            intent.putExtra("android.content.pm.extra.STATUS_MESSAGE", PackageHelper.deleteStatusToString(uninstallPackage));
            intent.putExtra("android.content.pm.extra.LEGACY_STATUS", uninstallPackage != 0 ? 1 : -1);
            try {
                intentSender.sendIntent(this.mContext, 0, intent, null, null);
            } catch (IntentSender.SendIntentException e) {
                e.printStackTrace();
            }
        }
    }

    @Override // com.lody.virtual.server.IPackageInstaller
    public void setPermissionsResult(int i, boolean z) {
        synchronized (this.mSessions) {
            PackageInstallerSession packageInstallerSession = this.mSessions.get(i);
            if (packageInstallerSession != null) {
                packageInstallerSession.setPermissionsResult(z);
            }
        }
    }

    private int allocateSessionIdLocked() {
        int i = 0;
        while (true) {
            int nextInt = this.mRandom.nextInt(2147483646) + 1;
            if (this.mSessions.get(nextInt) == null) {
                return nextInt;
            }
            i++;
            if (i >= 32) {
                throw new IllegalStateException("Failed to allocate session ID");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.lody.virtual.server.pm.installer.VPackageInstallerService$Callbacks */
    /* loaded from: classes.dex */
    public static class Callbacks extends Handler {
        private static final int MSG_SESSION_ACTIVE_CHANGED = 3;
        private static final int MSG_SESSION_BADGING_CHANGED = 2;
        private static final int MSG_SESSION_CREATED = 1;
        private static final int MSG_SESSION_FINISHED = 5;
        private static final int MSG_SESSION_PROGRESS_CHANGED = 4;
        private final RemoteCallbackList<IPackageInstallerCallback> mCallbacks = new RemoteCallbackList<>();

        public Callbacks(Looper looper) {
            super(looper);
        }

        public void register(IPackageInstallerCallback iPackageInstallerCallback, int i) {
            this.mCallbacks.register(iPackageInstallerCallback, new VUserHandle(i));
        }

        public void unregister(IPackageInstallerCallback iPackageInstallerCallback) {
            this.mCallbacks.unregister(iPackageInstallerCallback);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i = message.arg2;
            int beginBroadcast = this.mCallbacks.beginBroadcast();
            for (int i2 = 0; i2 < beginBroadcast; i2++) {
                IPackageInstallerCallback broadcastItem = this.mCallbacks.getBroadcastItem(i2);
                if (i == ((VUserHandle) this.mCallbacks.getBroadcastCookie(i2)).getIdentifier()) {
                    try {
                        invokeCallback(broadcastItem, message);
                    } catch (RemoteException unused) {
                    }
                }
            }
            this.mCallbacks.finishBroadcast();
        }

        private void invokeCallback(IPackageInstallerCallback iPackageInstallerCallback, Message message) throws RemoteException {
            int i = message.arg1;
            switch (message.what) {
                case 1:
                    iPackageInstallerCallback.onSessionCreated(i);
                    return;
                case 2:
                    iPackageInstallerCallback.onSessionBadgingChanged(i);
                    return;
                case 3:
                    iPackageInstallerCallback.onSessionActiveChanged(i, ((Boolean) message.obj).booleanValue());
                    return;
                case 4:
                    iPackageInstallerCallback.onSessionProgressChanged(i, ((Float) message.obj).floatValue());
                    return;
                case 5:
                    iPackageInstallerCallback.onSessionFinished(i, ((Boolean) message.obj).booleanValue());
                    return;
                default:
                    return;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void notifySessionCreated(int i, int i2) {
            obtainMessage(1, i, i2).sendToTarget();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void notifySessionBadgingChanged(int i, int i2) {
            obtainMessage(2, i, i2).sendToTarget();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void notifySessionActiveChanged(int i, int i2, boolean z) {
            obtainMessage(3, i, i2, Boolean.valueOf(z)).sendToTarget();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void notifySessionProgressChanged(int i, int i2, float f) {
            obtainMessage(4, i, i2, Float.valueOf(f)).sendToTarget();
        }

        public void notifySessionFinished(int i, int i2, boolean z) {
            obtainMessage(5, i, i2, Boolean.valueOf(z)).sendToTarget();
        }
    }

    /* renamed from: com.lody.virtual.server.pm.installer.VPackageInstallerService$PackageInstallObserverAdapter */
    /* loaded from: classes.dex */
    static class PackageInstallObserverAdapter extends PackageInstallObserver {
        private final Context mContext;
        private final int mSessionId;
        private final IntentSender mTarget;
        private final int mUserId;

        /* JADX INFO: Access modifiers changed from: package-private */
        public PackageInstallObserverAdapter(Context context, IntentSender intentSender, int i, int i2) {
            this.mContext = context;
            this.mTarget = intentSender;
            this.mSessionId = i;
            this.mUserId = i2;
        }

        @Override // com.lody.virtual.server.pm.installer.PackageInstallObserver
        public void onUserActionRequired(Intent intent) {
            Intent intent2 = new Intent();
            intent2.putExtra("android.content.pm.extra.SESSION_ID", this.mSessionId);
            intent2.putExtra("android.content.pm.extra.STATUS", -1);
            intent2.putExtra("android.intent.extra.INTENT", intent);
            try {
                this.mTarget.sendIntent(this.mContext, 0, intent2, null, null);
            } catch (IntentSender.SendIntentException unused) {
            }
        }

        @Override // com.lody.virtual.server.pm.installer.PackageInstallObserver
        public void onPackageInstalled(String str, int i, String str2, Bundle bundle) {
            Intent intent = new Intent();
            intent.putExtra("android.content.pm.extra.PACKAGE_NAME", str);
            intent.putExtra("android.content.pm.extra.SESSION_ID", this.mSessionId);
            intent.putExtra("android.content.pm.extra.STATUS", PackageHelper.installStatusToPublicStatus(i));
            intent.putExtra("android.content.pm.extra.STATUS_MESSAGE", PackageHelper.installStatusToString(i, str2));
            intent.putExtra("android.content.pm.extra.LEGACY_STATUS", i);
            if (bundle != null) {
                String string = bundle.getString("android.content.pm.extra.FAILURE_EXISTING_PACKAGE");
                if (!TextUtils.isEmpty(string)) {
                    intent.putExtra("android.content.pm.extra.OTHER_PACKAGE_NAME", string);
                }
            }
            try {
                this.mTarget.sendIntent(this.mContext, 0, intent, null, null);
            } catch (IntentSender.SendIntentException unused) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.lody.virtual.server.pm.installer.VPackageInstallerService$InternalCallback */
    /* loaded from: classes.dex */
    public class InternalCallback {
        public void onSessionPrepared(PackageInstallerSession packageInstallerSession) {
        }

        public void onSessionSealedBlocking(PackageInstallerSession packageInstallerSession) {
        }

        InternalCallback() {
        }

        public void onSessionBadgingChanged(PackageInstallerSession packageInstallerSession) {
            VPackageInstallerService.this.mCallbacks.notifySessionBadgingChanged(packageInstallerSession.sessionId, packageInstallerSession.userId);
        }

        public void onSessionActiveChanged(PackageInstallerSession packageInstallerSession, boolean z) {
            VPackageInstallerService.this.mCallbacks.notifySessionActiveChanged(packageInstallerSession.sessionId, packageInstallerSession.userId, z);
        }

        public void onSessionProgressChanged(PackageInstallerSession packageInstallerSession, float f) {
            VPackageInstallerService.this.mCallbacks.notifySessionProgressChanged(packageInstallerSession.sessionId, packageInstallerSession.userId, f);
        }

        public void onSessionFinished(final PackageInstallerSession packageInstallerSession, boolean z) {
            VPackageInstallerService.this.mCallbacks.notifySessionFinished(packageInstallerSession.sessionId, packageInstallerSession.userId, z);
            VPackageInstallerService.this.mInstallHandler.post(new Runnable() { // from class: com.lody.virtual.server.pm.installer.VPackageInstallerService.InternalCallback.1
                @Override // java.lang.Runnable
                public void run() {
                    synchronized (VPackageInstallerService.this.mSessions) {
                        VPackageInstallerService.this.mSessions.remove(packageInstallerSession.sessionId);
                    }
                }
            });
        }
    }
}
