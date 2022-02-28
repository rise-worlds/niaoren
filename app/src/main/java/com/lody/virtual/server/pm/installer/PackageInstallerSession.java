package com.lody.virtual.server.pm.installer;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.IntentSender;
import android.content.p001pm.IPackageInstallObserver2;
import android.content.p001pm.IPackageInstallerSession;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import android.text.TextUtils;
import com.lody.virtual.helper.utils.FileUtils;
import com.lody.virtual.helper.utils.VLog;
import com.lody.virtual.remote.InstallOptions;
import com.lody.virtual.server.pm.VAppManagerService;
import com.lody.virtual.server.pm.installer.VPackageInstallerService;
import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@TargetApi(21)
/* renamed from: com.lody.virtual.server.pm.installer.PackageInstallerSession */
/* loaded from: classes.dex */
public class PackageInstallerSession extends IPackageInstallerSession.Stub {
    public static final int INSTALL_FAILED_ABORTED = -115;
    public static final int INSTALL_FAILED_INTERNAL_ERROR = -110;
    public static final int INSTALL_FAILED_INVALID_APK = -2;
    public static final int INSTALL_SUCCEEDED = 1;
    private static final int MSG_COMMIT = 0;
    private static final String REMOVE_SPLIT_MARKER_EXTENSION = ".removed";
    private static final String TAG = "PackageInstaller";
    final String installerPackageName;
    final int installerUid;
    private final VPackageInstallerService.InternalCallback mCallback;
    private final Context mContext;
    private String mFinalMessage;
    private int mFinalStatus;
    private final Handler mHandler;
    private String mPackageName;
    private boolean mPermissionsAccepted;
    private IPackageInstallObserver2 mRemoteObserver;
    private File mResolvedBaseFile;
    private File mResolvedStageDir;
    final SessionParams params;
    final int sessionId;
    final File stageDir;
    final int userId;
    private final AtomicInteger mActiveCount = new AtomicInteger();
    private final Object mLock = new Object();
    private float mClientProgress = 0.0f;
    private float mInternalProgress = 0.0f;
    private float mProgress = 0.0f;
    private float mReportedProgress = -1.0f;
    private boolean mPrepared = false;
    private boolean mSealed = false;
    private boolean mDestroyed = false;
    private ArrayList<FileBridge> mBridges = new ArrayList<>();
    private final List<File> mResolvedStagedFiles = new ArrayList();
    private final Handler.Callback mHandlerCallback = new Handler.Callback() { // from class: com.lody.virtual.server.pm.installer.PackageInstallerSession.1
        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            synchronized (PackageInstallerSession.this.mLock) {
                if (message.obj != null) {
                    PackageInstallerSession.this.mRemoteObserver = (IPackageInstallObserver2) message.obj;
                }
                try {
                    PackageInstallerSession.this.commitLocked();
                } catch (PackageManagerException e) {
                    String completeMessage = PackageInstallerSession.getCompleteMessage(e);
                    VLog.m18992e(PackageInstallerSession.TAG, "Commit of session " + PackageInstallerSession.this.sessionId + " failed: " + completeMessage);
                    PackageInstallerSession.this.destroyInternal();
                    PackageInstallerSession.this.dispatchSessionFinished(e.error, completeMessage, null);
                }
            }
            return true;
        }
    };

    private static float constrain(float f, float f2, float f3) {
        return f < f2 ? f2 : f > f3 ? f3 : f;
    }

    public PackageInstallerSession(VPackageInstallerService.InternalCallback internalCallback, Context context, Looper looper, String str, int i, int i2, int i3, SessionParams sessionParams, File file) {
        this.mCallback = internalCallback;
        this.mContext = context;
        this.mHandler = new Handler(looper, this.mHandlerCallback);
        this.installerPackageName = str;
        this.sessionId = i;
        this.userId = i2;
        this.installerUid = i3;
        this.mPackageName = sessionParams.appPackageName;
        this.params = sessionParams;
        this.stageDir = file;
    }

    public SessionInfo generateInfo() {
        SessionInfo sessionInfo = new SessionInfo();
        synchronized (this.mLock) {
            sessionInfo.sessionId = this.sessionId;
            sessionInfo.installerPackageName = this.installerPackageName;
            sessionInfo.resolvedBaseCodePath = this.mResolvedBaseFile != null ? this.mResolvedBaseFile.getAbsolutePath() : null;
            sessionInfo.progress = this.mProgress;
            sessionInfo.sealed = this.mSealed;
            sessionInfo.active = this.mActiveCount.get() > 0;
            sessionInfo.mode = this.params.mode;
            sessionInfo.sizeBytes = this.params.sizeBytes;
            sessionInfo.appPackageName = this.params.appPackageName;
            sessionInfo.appIcon = this.params.appIcon;
            sessionInfo.appLabel = this.params.appLabel;
        }
        return sessionInfo;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void commitLocked() throws PackageManagerException {
        File[] listFiles;
        if (this.mDestroyed) {
            throw new PackageManagerException(-110, "Session destroyed");
        } else if (this.mSealed) {
            try {
                resolveStageDir();
            } catch (IOException e) {
                e.printStackTrace();
            }
            validateInstallLocked();
            this.mInternalProgress = 0.5f;
            int i = 1;
            computeProgressLocked(true);
            boolean z = false;
            for (File file : this.stageDir.listFiles()) {
                VLog.m18992e(TAG, "found apk in stage dir: " + file.getPath());
                if (VAppManagerService.get().installPackage(file.getPath(), InstallOptions.makeOptions(false)).isSuccess) {
                    z = true;
                }
            }
            destroyInternal();
            if (!z) {
                i = -115;
            }
            dispatchSessionFinished(i, null, null);
        } else {
            throw new PackageManagerException(-110, "Session not sealed");
        }
    }

    private void validateInstallLocked() throws PackageManagerException {
        this.mResolvedBaseFile = null;
        this.mResolvedStagedFiles.clear();
        File[] listFiles = this.mResolvedStageDir.listFiles();
        if (listFiles == null || listFiles.length == 0) {
            throw new PackageManagerException(-2, "No packages staged");
        }
        for (File file : listFiles) {
            if (!file.isDirectory()) {
                File file2 = new File(this.mResolvedStageDir, "base.apk");
                if (!file.equals(file2)) {
                    file.renameTo(file2);
                }
                this.mResolvedBaseFile = file2;
                this.mResolvedStagedFiles.add(file2);
            }
        }
        if (this.mResolvedBaseFile == null) {
            throw new PackageManagerException(-2, "Full install must include a base package");
        }
    }

    @Override // android.content.p001pm.IPackageInstallerSession
    public void setClientProgress(float f) throws RemoteException {
        synchronized (this.mLock) {
            boolean z = this.mClientProgress == 0.0f;
            this.mClientProgress = f;
            computeProgressLocked(z);
        }
    }

    private void computeProgressLocked(boolean z) {
        this.mProgress = constrain(this.mClientProgress * 0.8f, 0.0f, 0.8f) + constrain(this.mInternalProgress * 0.2f, 0.0f, 0.2f);
        if (z || Math.abs(this.mProgress - this.mReportedProgress) >= 0.01d) {
            float f = this.mProgress;
            this.mReportedProgress = f;
            this.mCallback.onSessionProgressChanged(this, f);
        }
    }

    @Override // android.content.p001pm.IPackageInstallerSession
    public void addClientProgress(float f) throws RemoteException {
        synchronized (this.mLock) {
            setClientProgress(this.mClientProgress + f);
        }
    }

    @Override // android.content.p001pm.IPackageInstallerSession
    public String[] getNames() throws RemoteException {
        assertPreparedAndNotSealed("getNames");
        try {
            return resolveStageDir().list();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private File resolveStageDir() throws IOException {
        File file;
        synchronized (this.mLock) {
            if (this.mResolvedStageDir == null && this.stageDir != null) {
                this.mResolvedStageDir = this.stageDir;
                if (!this.stageDir.exists()) {
                    this.stageDir.mkdirs();
                }
            }
            file = this.mResolvedStageDir;
        }
        return file;
    }

    @Override // android.content.p001pm.IPackageInstallerSession
    public ParcelFileDescriptor openWrite(String str, long j, long j2) throws RemoteException {
        try {
            return openWriteInternal(str, j, j2);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private void assertPreparedAndNotSealed(String str) {
        synchronized (this.mLock) {
            if (!this.mPrepared) {
                throw new IllegalStateException(str + " before prepared");
            } else if (this.mSealed) {
                throw new SecurityException(str + " not allowed after commit");
            }
        }
    }

    private ParcelFileDescriptor openWriteInternal(String str, long j, long j2) throws IOException {
        FileBridge fileBridge;
        synchronized (this.mLock) {
            assertPreparedAndNotSealed("openWrite");
            fileBridge = new FileBridge();
            this.mBridges.add(fileBridge);
        }
        try {
            FileDescriptor open = Os.open(new File(resolveStageDir(), str).getAbsolutePath(), OsConstants.O_CREAT | OsConstants.O_WRONLY, 420);
            if (j2 > 0) {
                Os.posix_fallocate(open, 0L, j2);
            }
            if (j > 0) {
                Os.lseek(open, j, OsConstants.SEEK_SET);
            }
            fileBridge.setTargetFile(open);
            fileBridge.start();
            return ParcelFileDescriptor.dup(fileBridge.getClientSocket());
        } catch (ErrnoException e) {
            throw new IOException(e);
        }
    }

    @Override // android.content.p001pm.IPackageInstallerSession
    public ParcelFileDescriptor openRead(String str) throws RemoteException {
        try {
            return openReadInternal(str);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private ParcelFileDescriptor openReadInternal(String str) throws IOException {
        assertPreparedAndNotSealed("openRead");
        try {
            if (FileUtils.isValidExtFilename(str)) {
                return ParcelFileDescriptor.dup(Os.open(new File(resolveStageDir(), str).getAbsolutePath(), OsConstants.O_RDONLY, 0));
            }
            throw new IllegalArgumentException("Invalid name: " + str);
        } catch (ErrnoException e) {
            throw new IOException(e);
        }
    }

    @Override // android.content.p001pm.IPackageInstallerSession
    public void removeSplit(String str) throws RemoteException {
        if (!TextUtils.isEmpty(this.params.appPackageName)) {
            try {
                createRemoveSplitMarker(str);
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        } else {
            throw new IllegalStateException("Must specify package name to remove a split");
        }
    }

    private void createRemoveSplitMarker(String str) throws IOException {
        try {
            String str2 = str + REMOVE_SPLIT_MARKER_EXTENSION;
            if (FileUtils.isValidExtFilename(str2)) {
                File file = new File(resolveStageDir(), str2);
                file.createNewFile();
                Os.chmod(file.getAbsolutePath(), 0);
                return;
            }
            throw new IllegalArgumentException("Invalid marker: " + str2);
        } catch (ErrnoException e) {
            throw new IOException(e);
        }
    }

    @Override // android.content.p001pm.IPackageInstallerSession
    public void close() throws RemoteException {
        if (this.mActiveCount.decrementAndGet() == 0) {
            this.mCallback.onSessionActiveChanged(this, false);
        }
    }

    @TargetApi(26)
    public void commit(IntentSender intentSender, boolean z) throws RemoteException {
        commit(intentSender);
    }

    @Override // android.content.p001pm.IPackageInstallerSession
    public void commit(IntentSender intentSender) throws RemoteException {
        boolean z;
        synchronized (this.mLock) {
            z = this.mSealed;
            if (!this.mSealed) {
                Iterator<FileBridge> it = this.mBridges.iterator();
                while (it.hasNext()) {
                    if (!it.next().isClosed()) {
                        throw new SecurityException("Files still open");
                    }
                }
                this.mSealed = true;
            }
            this.mClientProgress = 1.0f;
            computeProgressLocked(true);
        }
        if (!z) {
            this.mCallback.onSessionSealedBlocking(this);
        }
        this.mActiveCount.incrementAndGet();
        this.mHandler.obtainMessage(0, new VPackageInstallerService.PackageInstallObserverAdapter(this.mContext, intentSender, this.sessionId, this.userId).getBinder()).sendToTarget();
    }

    @Override // android.content.p001pm.IPackageInstallerSession
    public void abandon() throws RemoteException {
        destroyInternal();
        dispatchSessionFinished(-115, "Session was abandoned", null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void destroyInternal() {
        synchronized (this.mLock) {
            this.mSealed = true;
            this.mDestroyed = true;
            Iterator<FileBridge> it = this.mBridges.iterator();
            while (it.hasNext()) {
                it.next().forceClose();
            }
        }
        File file = this.stageDir;
        if (file != null) {
            FileUtils.deleteDir(file.getAbsolutePath());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchSessionFinished(int i, String str, Bundle bundle) {
        this.mFinalStatus = i;
        this.mFinalMessage = str;
        IPackageInstallObserver2 iPackageInstallObserver2 = this.mRemoteObserver;
        if (iPackageInstallObserver2 != null) {
            try {
                iPackageInstallObserver2.onPackageInstalled(this.mPackageName, i, str, bundle);
            } catch (RemoteException unused) {
            }
        }
        boolean z = true;
        if (i != 1) {
            z = false;
        }
        this.mCallback.onSessionFinished(this, z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setPermissionsResult(boolean z) {
        if (!this.mSealed) {
            throw new SecurityException("Must be sealed to accept permissions");
        } else if (z) {
            synchronized (this.mLock) {
                this.mPermissionsAccepted = true;
            }
            this.mHandler.obtainMessage(0).sendToTarget();
        } else {
            destroyInternal();
            dispatchSessionFinished(-115, "User rejected permissions", null);
        }
    }

    public void open() throws IOException {
        if (this.mActiveCount.getAndIncrement() == 0) {
            this.mCallback.onSessionActiveChanged(this, true);
        }
        synchronized (this.mLock) {
            if (!this.mPrepared) {
                if (this.stageDir != null) {
                    this.mPrepared = true;
                    this.mCallback.onSessionPrepared(this);
                } else {
                    throw new IllegalArgumentException("Exactly one of stageDir or stageCid stage must be set");
                }
            }
        }
    }

    public static String getCompleteMessage(Throwable th) {
        StringBuilder sb = new StringBuilder();
        sb.append(th.getMessage());
        while (true) {
            th = th.getCause();
            if (th == null) {
                return sb.toString();
            }
            sb.append(": ");
            sb.append(th.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.lody.virtual.server.pm.installer.PackageInstallerSession$PackageManagerException */
    /* loaded from: classes.dex */
    public class PackageManagerException extends Exception {
        public final int error;

        PackageManagerException(int i, String str) {
            super(str);
            this.error = i;
        }
    }
}
