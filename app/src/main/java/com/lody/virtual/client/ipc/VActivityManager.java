package com.lody.virtual.client.ipc;

import android.app.Activity;
import android.app.IServiceConnection;
import android.app.Notification;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ActivityInfo;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Process;
import android.os.RemoteException;
import android.util.Log;
import com.lody.virtual.client.VClient;
import com.lody.virtual.client.core.VirtualCore;
import com.lody.virtual.client.env.VirtualRuntime;
import com.lody.virtual.client.hook.secondary.ServiceConnectionDelegate;
import com.lody.virtual.client.stub.WindowPreviewActivity;
import com.lody.virtual.helper.compat.ActivityManagerCompat;
import com.lody.virtual.helper.utils.ComponentUtils;
import com.lody.virtual.helper.utils.IInterfaceUtils;
import com.lody.virtual.helper.utils.VLog;
import com.lody.virtual.p061os.VUserHandle;
import com.lody.virtual.remote.AppTaskInfo;
import com.lody.virtual.remote.BadgerInfo;
import com.lody.virtual.remote.ClientConfig;
import com.lody.virtual.remote.IntentSenderData;
import com.lody.virtual.remote.VParceledListSlice;
import com.lody.virtual.server.bit64.V64BitHelper;
import com.lody.virtual.server.interfaces.IActivityManager;
import java.util.List;
import p110z1.ActivityThread;
import p110z1.ContentProviderNative;

/* loaded from: classes.dex */
public class VActivityManager {
    private static final VActivityManager sAM = new VActivityManager();
    private IActivityManager mService;

    public IActivityManager getService() {
        if (!IInterfaceUtils.isAlive(this.mService)) {
            synchronized (VActivityManager.class) {
                this.mService = (IActivityManager) LocalProxyUtils.genProxy(IActivityManager.class, getRemoteInterface());
            }
        }
        return this.mService;
    }

    private Object getRemoteInterface() {
        return IActivityManager.Stub.asInterface(ServiceManagerNative.getService(ServiceManagerNative.ACTIVITY));
    }

    public static VActivityManager get() {
        return sAM;
    }

    public int startActivity(Intent intent, ActivityInfo activityInfo, IBinder iBinder, Bundle bundle, String str, int i, int i2) {
        ActivityInfo activityInfo2;
        Log.i("efdf2", "finalIntent ---- 71");
        if (activityInfo == null) {
            Log.i("efdf2", "finalIntent ---- 72");
            ActivityInfo resolveActivityInfo = VirtualCore.get().resolveActivityInfo(intent, i2);
            Log.i("efdf2", "finalIntent ---- 73");
            if (resolveActivityInfo == null) {
                Log.i("efdf2", "finalIntent ---- 74");
                return ActivityManagerCompat.START_INTENT_NOT_RESOLVED;
            }
            activityInfo2 = resolveActivityInfo;
        } else {
            activityInfo2 = activityInfo;
        }
        try {
            Log.i("efdf2", "finalIntent ---- 5");
            return getService().startActivity(intent, activityInfo2, iBinder, bundle, str, i, i2);
        } catch (RemoteException e) {
            return ((Integer) VirtualRuntime.crash(e)).intValue();
        }
    }

    public int startActivities(Intent[] intentArr, String[] strArr, IBinder iBinder, Bundle bundle, int i) {
        try {
            return getService().startActivities(intentArr, strArr, iBinder, bundle, i);
        } catch (RemoteException e) {
            return ((Integer) VirtualRuntime.crash(e)).intValue();
        }
    }

    public int startActivity(Intent intent, int i) {
        if (i < 0) {
            return ActivityManagerCompat.START_NOT_CURRENT_USER_ACTIVITY;
        }
        ActivityInfo resolveActivityInfo = VirtualCore.get().resolveActivityInfo(intent, i);
        if (resolveActivityInfo == null) {
            return ActivityManagerCompat.START_INTENT_NOT_RESOLVED;
        }
        return startActivity(intent, resolveActivityInfo, null, null, null, 0, i);
    }

    public void appDoneExecuting(String str) {
        try {
            getService().appDoneExecuting(str, VUserHandle.myUserId());
        } catch (RemoteException e) {
            VirtualRuntime.crash(e);
        }
    }

    public void onActivityCreate(IBinder iBinder, IBinder iBinder2, int i) {
        try {
            getService().onActivityCreated(iBinder, iBinder2, i);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void onActivityResumed(IBinder iBinder) {
        try {
            getService().onActivityResumed(VUserHandle.myUserId(), iBinder);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public boolean onActivityDestroy(IBinder iBinder) {
        try {
            return getService().onActivityDestroyed(VUserHandle.myUserId(), iBinder);
        } catch (RemoteException e) {
            return ((Boolean) VirtualRuntime.crash(e)).booleanValue();
        }
    }

    public AppTaskInfo getTaskInfo(int i) {
        try {
            return getService().getTaskInfo(i);
        } catch (RemoteException e) {
            return (AppTaskInfo) VirtualRuntime.crash(e);
        }
    }

    public ComponentName getCallingActivity(IBinder iBinder) {
        try {
            return getService().getCallingActivity(VUserHandle.myUserId(), iBinder);
        } catch (RemoteException e) {
            return (ComponentName) VirtualRuntime.crash(e);
        }
    }

    public String getCallingPackage(IBinder iBinder) {
        try {
            return getService().getCallingPackage(VUserHandle.myUserId(), iBinder);
        } catch (RemoteException e) {
            return (String) VirtualRuntime.crash(e);
        }
    }

    public String getPackageForToken(IBinder iBinder) {
        try {
            return getService().getPackageForToken(VUserHandle.myUserId(), iBinder);
        } catch (RemoteException e) {
            return (String) VirtualRuntime.crash(e);
        }
    }

    public ComponentName getActivityForToken(IBinder iBinder) {
        try {
            return getService().getActivityClassForToken(VUserHandle.myUserId(), iBinder);
        } catch (RemoteException e) {
            return (ComponentName) VirtualRuntime.crash(e);
        }
    }

    public void processRestarted(String str, String str2, int i) {
        try {
            getService().processRestarted(str, str2, i);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public String getAppProcessName(int i) {
        try {
            return getService().getAppProcessName(i);
        } catch (RemoteException e) {
            return (String) VirtualRuntime.crash(e);
        }
    }

    public String getInitialPackage(int i) {
        try {
            return getService().getInitialPackage(i);
        } catch (RemoteException e) {
            return (String) VirtualRuntime.crash(e);
        }
    }

    public boolean isAppProcess(String str) {
        try {
            return getService().isAppProcess(str);
        } catch (RemoteException e) {
            return ((Boolean) VirtualRuntime.crash(e)).booleanValue();
        }
    }

    public void finishAllActivities() {
        try {
            getService().finishAllActivities();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void finishAllActivitiesByPkg(String str, int i) {
        try {
            getService().finishAllActivitiesByPkg(str, i);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void killAllApps() {
        try {
            getService().killAllApps();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void killApplicationProcess(String str, int i) {
        try {
            getService().killApplicationProcess(str, i);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void killAppByPkg(String str, int i) {
        try {
            getService().killAppByPkg(str, i);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public List<String> getProcessPkgList(int i) {
        try {
            return getService().getProcessPkgList(i);
        } catch (RemoteException e) {
            return (List) VirtualRuntime.crash(e);
        }
    }

    public boolean isAppPid(int i) {
        try {
            return getService().isAppPid(i);
        } catch (RemoteException e) {
            return ((Boolean) VirtualRuntime.crash(e)).booleanValue();
        }
    }

    public int getUidByPid(int i) {
        try {
            return getService().getUidByPid(i);
        } catch (RemoteException e) {
            return ((Integer) VirtualRuntime.crash(e)).intValue();
        }
    }

    public int getSystemPid() {
        try {
            return getService().getSystemPid();
        } catch (RemoteException e) {
            return ((Integer) VirtualRuntime.crash(e)).intValue();
        }
    }

    public int getSystemUid() {
        try {
            return getService().getSystemUid();
        } catch (RemoteException e) {
            return ((Integer) VirtualRuntime.crash(e)).intValue();
        }
    }

    public void sendCancelActivityResult(IBinder iBinder, String str, int i) {
        sendActivityResult(iBinder, str, i, null, 0);
    }

    public void sendActivityResult(IBinder iBinder, String str, int i, Intent intent, int i2) {
        Activity findActivityByToken = findActivityByToken(iBinder);
        Log.i("efdf2", "sendActivityResult 111 ");
        if (findActivityByToken != null) {
            Log.i("efdf2", "sendActivityResult 22 ");
            ActivityThread.sendActivityResult.call(VirtualCore.mainThread(), iBinder, str, Integer.valueOf(i), intent, Integer.valueOf(i2));
            return;
        }
        Log.i("efdf2", "sendActivityResult 33 ");
    }

    public IInterface acquireProviderClient(int i, ProviderInfo providerInfo) throws RemoteException {
        IBinder acquireProviderClient = getService().acquireProviderClient(i, providerInfo);
        if (acquireProviderClient != null) {
            return ContentProviderNative.asInterface.call(acquireProviderClient);
        }
        return null;
    }

    public void addOrUpdateIntentSender(IntentSenderData intentSenderData) throws RemoteException {
        getService().addOrUpdateIntentSender(intentSenderData, VUserHandle.myUserId());
    }

    public void removeIntentSender(IBinder iBinder) throws RemoteException {
        getService().removeIntentSender(iBinder);
    }

    public IntentSenderData getIntentSender(IBinder iBinder) {
        try {
            return getService().getIntentSender(iBinder);
        } catch (RemoteException e) {
            return (IntentSenderData) VirtualRuntime.crash(e);
        }
    }

    public Activity findActivityByToken(IBinder iBinder) {
        Object obj = ActivityThread.mActivities.get(VirtualCore.mainThread()).get(iBinder);
        if (obj != null) {
            return ActivityThread.C5105a.activity.get(obj);
        }
        return null;
    }

    public void finishActivity(IBinder iBinder) {
        Activity findActivityByToken = findActivityByToken(iBinder);
        if (findActivityByToken == null) {
            VLog.m18992e("VActivityManager", "finishActivity fail : activity = null");
            return;
        }
        while (true) {
            Activity activity = p110z1.Activity.mParent.get(findActivityByToken);
            if (activity == null) {
                ActivityManagerCompat.finishActivity(iBinder, p110z1.Activity.mResultCode.get(findActivityByToken), p110z1.Activity.mResultData.get(findActivityByToken));
                p110z1.Activity.mFinished.set(findActivityByToken, true);
                return;
            }
            findActivityByToken = activity;
        }
    }

    public boolean isAppRunning(String str, int i, boolean z) {
        try {
            return getService().isAppRunning(str, i, z);
        } catch (RemoteException e) {
            return ((Boolean) VirtualRuntime.crash(e)).booleanValue();
        }
    }

    public int getUid() {
        return VClient.get().getVUid();
    }

    public ClientConfig initProcess(String str, String str2, int i) {
        try {
            return getService().initProcess(str, str2, i);
        } catch (RemoteException e) {
            return (ClientConfig) VirtualRuntime.crash(e);
        }
    }

    public void sendBroadcast(Intent intent, int i) {
        Intent redirectBroadcastIntent = ComponentUtils.redirectBroadcastIntent(intent, i);
        if (redirectBroadcastIntent != null) {
            VirtualCore.get().getContext().sendBroadcast(redirectBroadcastIntent);
        }
    }

    public void notifyBadgerChange(BadgerInfo badgerInfo) {
        try {
            getService().notifyBadgerChange(badgerInfo);
        } catch (RemoteException e) {
            VirtualRuntime.crash(e);
        }
    }

    public int getCallingUid() {
        try {
            int callingUidByPid = getService().getCallingUidByPid(Process.myPid());
            return callingUidByPid <= 0 ? VClient.get().getVUid() : callingUidByPid;
        } catch (RemoteException e) {
            VirtualRuntime.crash(e);
            return VClient.get().getVUid();
        }
    }

    public void setAppInactive(String str, boolean z, int i) {
        try {
            getService().setAppInactive(str, z, i);
        } catch (RemoteException e) {
            VirtualRuntime.crash(e);
        }
    }

    public boolean isAppInactive(String str, int i) {
        try {
            return getService().isAppInactive(str, i);
        } catch (RemoteException e) {
            return ((Boolean) VirtualRuntime.crash(e)).booleanValue();
        }
    }

    public boolean launchApp(int i, String str) {
        return launchApp(i, str, true);
    }

    public boolean launchApp(final int i, String str, boolean z) {
        if (VirtualCore.get().isRun64BitProcess(str) && !V64BitHelper.has64BitEngineStartPermission()) {
            return false;
        }
        Context context = VirtualCore.get().getContext();
        VPackageManager vPackageManager = VPackageManager.get();
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.INFO");
        intent.setPackage(str);
        List<ResolveInfo> queryIntentActivities = vPackageManager.queryIntentActivities(intent, intent.resolveType(context), 0, i);
        if (queryIntentActivities == null || queryIntentActivities.size() <= 0) {
            intent.removeCategory("android.intent.category.INFO");
            intent.addCategory("android.intent.category.LAUNCHER");
            intent.setPackage(str);
            queryIntentActivities = vPackageManager.queryIntentActivities(intent, intent.resolveType(context), 0, i);
        }
        if (queryIntentActivities == null || queryIntentActivities.size() <= 0) {
            return false;
        }
        ActivityInfo activityInfo = queryIntentActivities.get(0).activityInfo;
        final Intent intent2 = new Intent(intent);
        intent2.setFlags(268435456);
        intent2.setClassName(activityInfo.packageName, activityInfo.name);
        if (!z || get().isAppRunning(activityInfo.packageName, i, true)) {
            get().startActivity(intent2, i);
        } else {
            intent2.putExtra("_VA_|no_animation", true);
            WindowPreviewActivity.previewActivity(i, activityInfo);
            VirtualRuntime.getUIHandler().postDelayed(new Runnable() { // from class: com.lody.virtual.client.ipc.VActivityManager.1
                @Override // java.lang.Runnable
                public void run() {
                    VActivityManager.get().startActivity(intent2, i);
                }
            }, 400L);
        }
        return true;
    }

    public void onFinishActivity(IBinder iBinder) {
        try {
            getService().onActivityFinish(VUserHandle.myUserId(), iBinder);
        } catch (RemoteException e) {
            VirtualRuntime.crash(e);
        }
    }

    public int checkPermission(String str, int i, int i2) {
        try {
            return getService().checkPermission(VirtualCore.get().is64BitEngine(), str, i, i2);
        } catch (RemoteException e) {
            return ((Integer) VirtualRuntime.crash(e)).intValue();
        }
    }

    public void handleDownloadCompleteIntent(Intent intent) {
        try {
            getService().handleDownloadCompleteIntent(intent);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public boolean finishActivityAffinity(int i, IBinder iBinder) {
        try {
            return getService().finishActivityAffinity(i, iBinder);
        } catch (RemoteException e) {
            return ((Boolean) VirtualRuntime.crash(e)).booleanValue();
        }
    }

    public ComponentName startService(Intent intent, String str, int i) {
        try {
            return getService().startService(intent, str, i);
        } catch (RemoteException e) {
            return (ComponentName) VirtualRuntime.crash(e);
        }
    }

    public int stopService(IInterface iInterface, Intent intent, String str) {
        try {
            return getService().stopService(iInterface != null ? iInterface.asBinder() : null, intent, str, VUserHandle.myUserId());
        } catch (RemoteException e) {
            return ((Integer) VirtualRuntime.crash(e)).intValue();
        }
    }

    public boolean stopServiceToken(ComponentName componentName, IBinder iBinder, int i) {
        try {
            return getService().stopServiceToken(componentName, iBinder, i, VUserHandle.myUserId());
        } catch (RemoteException e) {
            return ((Boolean) VirtualRuntime.crash(e)).booleanValue();
        }
    }

    public boolean isVAServiceToken(IBinder iBinder) {
        try {
            return getService().isVAServiceToken(iBinder);
        } catch (RemoteException e) {
            return ((Boolean) VirtualRuntime.crash(e)).booleanValue();
        }
    }

    public void setServiceForeground(ComponentName componentName, IBinder iBinder, int i, Notification notification, boolean z) {
        try {
            getService().setServiceForeground(componentName, iBinder, i, notification, z, VUserHandle.myUserId());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public boolean bindService(Context context, Intent intent, ServiceConnection serviceConnection, int i) {
        try {
            return getService().bindService(null, null, intent, null, ServiceConnectionDelegate.getDelegate(context, serviceConnection, i), i, 0) > 0;
        } catch (RemoteException e) {
            return ((Boolean) VirtualRuntime.crash(e)).booleanValue();
        }
    }

    public boolean unbindService(Context context, ServiceConnection serviceConnection) {
        try {
            return getService().unbindService(ServiceConnectionDelegate.removeDelegate(context, serviceConnection), VUserHandle.myUserId());
        } catch (RemoteException e) {
            return ((Boolean) VirtualRuntime.crash(e)).booleanValue();
        }
    }

    public int bindService(IBinder iBinder, IBinder iBinder2, Intent intent, String str, IServiceConnection iServiceConnection, int i, int i2) {
        try {
            return getService().bindService(iBinder, iBinder2, intent, str, iServiceConnection, i, i2);
        } catch (RemoteException e) {
            return ((Integer) VirtualRuntime.crash(e)).intValue();
        }
    }

    public boolean unbindService(IServiceConnection iServiceConnection) {
        try {
            return getService().unbindService(iServiceConnection, VUserHandle.myUserId());
        } catch (RemoteException e) {
            return ((Boolean) VirtualRuntime.crash(e)).booleanValue();
        }
    }

    public void unbindFinished(IBinder iBinder, Intent intent, boolean z) {
        try {
            getService().unbindFinished(iBinder, intent, z, VUserHandle.myUserId());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void serviceDoneExecuting(IBinder iBinder, int i, int i2, int i3) {
        try {
            getService().serviceDoneExecuting(iBinder, i, i2, i3, VUserHandle.myUserId());
        } catch (RemoteException unused) {
        }
    }

    public IBinder peekService(Intent intent, String str) {
        try {
            return getService().peekService(intent, str, VUserHandle.myUserId());
        } catch (RemoteException e) {
            return (IBinder) VirtualRuntime.crash(e);
        }
    }

    public void publishService(IBinder iBinder, Intent intent, IBinder iBinder2) {
        try {
            getService().publishService(iBinder, intent, iBinder2, VUserHandle.myUserId());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public VParceledListSlice getServices(int i, int i2) {
        try {
            return getService().getServices(i, i2, VUserHandle.myUserId());
        } catch (RemoteException e) {
            return (VParceledListSlice) VirtualRuntime.crash(e);
        }
    }

    public boolean broadcastFinish(IBinder iBinder) {
        try {
            return getService().broadcastFinish(iBinder);
        } catch (RemoteException e) {
            return ((Boolean) VirtualRuntime.crash(e)).booleanValue();
        }
    }
}
