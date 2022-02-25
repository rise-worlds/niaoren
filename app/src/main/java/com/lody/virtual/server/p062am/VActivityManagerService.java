package com.lody.virtual.server.p062am;

import android.app.ActivityManager;
import android.app.IServiceConnection;
import android.app.IStopUserCallback;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.ProviderInfo;
import android.content.pm.ServiceInfo;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.ConditionVariable;
import android.os.Handler;
import android.os.IBinder;
import android.os.Process;
import android.os.RemoteException;
import android.os.SystemClock;
import com.lody.virtual.client.IVClient;
import com.lody.virtual.client.core.VirtualCore;
import com.lody.virtual.client.env.Constants;
import com.lody.virtual.client.env.SpecialComponentList;
import com.lody.virtual.client.ipc.ProviderCall;
import com.lody.virtual.client.ipc.ServiceManagerNative;
import com.lody.virtual.client.ipc.VNotificationManager;
import com.lody.virtual.client.stub.ChooseTypeAndAccountActivity;
import com.lody.virtual.client.stub.StubManifest;
import com.lody.virtual.helper.collection.ArrayMap;
import com.lody.virtual.helper.collection.SparseArray;
import com.lody.virtual.helper.compat.ActivityManagerCompat;
import com.lody.virtual.helper.compat.ApplicationThreadCompat;
import com.lody.virtual.helper.compat.BuildCompat;
import com.lody.virtual.helper.compat.BundleCompat;
import com.lody.virtual.helper.compat.PermissionCompat;
import com.lody.virtual.helper.utils.ComponentUtils;
import com.lody.virtual.helper.utils.Singleton;
import com.lody.virtual.helper.utils.VLog;
import com.lody.virtual.p061os.VBinder;
import com.lody.virtual.p061os.VUserHandle;
import com.lody.virtual.remote.AppTaskInfo;
import com.lody.virtual.remote.BadgerInfo;
import com.lody.virtual.remote.ClientConfig;
import com.lody.virtual.remote.IntentSenderData;
import com.lody.virtual.remote.VParceledListSlice;
import com.lody.virtual.server.bit64.V64BitHelper;
import com.lody.virtual.server.interfaces.IActivityManager;
import com.lody.virtual.server.p062am.ServiceRecord;
import com.lody.virtual.server.p063pm.PackageCacheManager;
import com.lody.virtual.server.p063pm.PackageSetting;
import com.lody.virtual.server.p063pm.VAppManagerService;
import com.lody.virtual.server.p063pm.VPackageManagerService;
import com.lody.virtual.server.secondary.BinderDelegateService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import p110z1.IServiceConnectionO;

/* renamed from: com.lody.virtual.server.am.VActivityManagerService */
/* loaded from: classes.dex */
public class VActivityManagerService extends IActivityManager.Stub {
    private boolean mResult;
    private static final Singleton<VActivityManagerService> sService = new Singleton<VActivityManagerService>() { // from class: com.lody.virtual.server.am.VActivityManagerService.1
        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.lody.virtual.helper.utils.Singleton
        public VActivityManagerService create() {
            return new VActivityManagerService();
        }
    };
    private static final String TAG = VActivityManagerService.class.getSimpleName();
    private final Object mProcessLock = new Object();
    private final List<ProcessRecord> mPidsSelfLocked = new ArrayList();
    private final ActivityStack mActivityStack = new ActivityStack(this);
    private final ProcessMap<ProcessRecord> mProcessNames = new ProcessMap<>();
    private final Map<IBinder, IntentSenderData> mIntentSenderMap = new HashMap();

    /* renamed from: nm */
    private NotificationManager f10502nm = (NotificationManager) VirtualCore.get().getContext().getSystemService(ServiceManagerNative.NOTIFICATION);
    private final Map<String, Boolean> sIdeMap = new HashMap();
    private final Set<ServiceRecord> mHistory = new HashSet();

    public void beforeProcessKilled(ProcessRecord processRecord) {
    }

    @Override // com.lody.virtual.server.interfaces.IActivityManager
    public void dump() {
    }

    public static VActivityManagerService get() {
        return sService.get();
    }

    @Override // com.lody.virtual.server.interfaces.IActivityManager
    public int startActivity(Intent intent, ActivityInfo activityInfo, IBinder iBinder, Bundle bundle, String str, int i, int i2) {
        int startActivityLocked;
        synchronized (this) {
            startActivityLocked = this.mActivityStack.startActivityLocked(i2, intent, activityInfo, iBinder, bundle, str, i, VBinder.getCallingUid());
        }
        return startActivityLocked;
    }

    @Override // com.lody.virtual.server.interfaces.IActivityManager
    public boolean finishActivityAffinity(int i, IBinder iBinder) {
        boolean finishActivityAffinity;
        synchronized (this) {
            finishActivityAffinity = this.mActivityStack.finishActivityAffinity(i, iBinder);
        }
        return finishActivityAffinity;
    }

    @Override // com.lody.virtual.server.interfaces.IActivityManager
    public int startActivities(Intent[] intentArr, String[] strArr, IBinder iBinder, Bundle bundle, int i) {
        synchronized (this) {
            ActivityInfo[] activityInfoArr = new ActivityInfo[intentArr.length];
            for (int i2 = 0; i2 < intentArr.length; i2++) {
                ActivityInfo resolveActivityInfo = VirtualCore.get().resolveActivityInfo(intentArr[i2], i);
                if (resolveActivityInfo == null) {
                    return ActivityManagerCompat.START_INTENT_NOT_RESOLVED;
                }
                activityInfoArr[i2] = resolveActivityInfo;
            }
            return this.mActivityStack.startActivitiesLocked(i, intentArr, activityInfoArr, strArr, iBinder, bundle, VBinder.getCallingUid());
        }
    }

    @Override // com.lody.virtual.server.interfaces.IActivityManager
    public int getSystemPid() {
        return Process.myPid();
    }

    @Override // com.lody.virtual.server.interfaces.IActivityManager
    public int getSystemUid() {
        return Process.myUid();
    }

    @Override // com.lody.virtual.server.interfaces.IActivityManager
    public void onActivityCreated(IBinder iBinder, IBinder iBinder2, int i) {
        ProcessRecord findProcessLocked;
        int callingPid = Binder.getCallingPid();
        synchronized (this.mProcessLock) {
            findProcessLocked = findProcessLocked(callingPid);
        }
        if (findProcessLocked != null) {
            this.mActivityStack.onActivityCreated(findProcessLocked, iBinder2, i, (ActivityRecord) iBinder);
        }
    }

    @Override // com.lody.virtual.server.interfaces.IActivityManager
    public void onActivityResumed(int i, IBinder iBinder) {
        this.mActivityStack.onActivityResumed(i, iBinder);
    }

    @Override // com.lody.virtual.server.interfaces.IActivityManager
    public boolean onActivityDestroyed(int i, IBinder iBinder) {
        return this.mActivityStack.onActivityDestroyed(i, iBinder) != null;
    }

    @Override // com.lody.virtual.server.interfaces.IActivityManager
    public void onActivityFinish(int i, IBinder iBinder) {
        this.mActivityStack.onActivityFinish(i, iBinder);
    }

    @Override // com.lody.virtual.server.interfaces.IActivityManager
    public AppTaskInfo getTaskInfo(int i) {
        return this.mActivityStack.getTaskInfo(i);
    }

    @Override // com.lody.virtual.server.interfaces.IActivityManager
    public String getPackageForToken(int i, IBinder iBinder) {
        return this.mActivityStack.getPackageForToken(i, iBinder);
    }

    @Override // com.lody.virtual.server.interfaces.IActivityManager
    public ComponentName getActivityClassForToken(int i, IBinder iBinder) {
        return this.mActivityStack.getActivityClassForToken(i, iBinder);
    }

    private void processDied(ProcessRecord processRecord) {
        synchronized (this.mHistory) {
            Iterator<ServiceRecord> it = this.mHistory.iterator();
            while (it.hasNext()) {
                ServiceRecord next = it.next();
                if (next.process != null && next.process.pid == processRecord.pid) {
                    it.remove();
                }
            }
        }
        this.mActivityStack.processDied(processRecord);
    }

    @Override // com.lody.virtual.server.interfaces.IActivityManager
    public IBinder acquireProviderClient(int i, ProviderInfo providerInfo) {
        ProcessRecord startProcessIfNeedLocked;
        String str = providerInfo.processName;
        synchronized (this) {
            startProcessIfNeedLocked = startProcessIfNeedLocked(str, i, providerInfo.packageName, -1, VBinder.getCallingUid());
        }
        if (startProcessIfNeedLocked == null) {
            return null;
        }
        try {
            return startProcessIfNeedLocked.client.acquireProviderClient(providerInfo);
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override // com.lody.virtual.server.interfaces.IActivityManager
    public boolean broadcastFinish(IBinder iBinder) throws RemoteException {
        synchronized (this.mPidsSelfLocked) {
            for (ProcessRecord processRecord : this.mPidsSelfLocked) {
                if (processRecord.client != null && processRecord.client.finishReceiver(iBinder)) {
                    return true;
                }
            }
            return false;
        }
    }

    @Override // com.lody.virtual.server.interfaces.IActivityManager
    public void addOrUpdateIntentSender(IntentSenderData intentSenderData, int i) {
        if (intentSenderData != null && intentSenderData.token != null) {
            synchronized (this.mIntentSenderMap) {
                IntentSenderData intentSenderData2 = this.mIntentSenderMap.get(intentSenderData.token);
                if (intentSenderData2 == null) {
                    this.mIntentSenderMap.put(intentSenderData.token, intentSenderData);
                } else {
                    intentSenderData2.replace(intentSenderData);
                }
            }
        }
    }

    @Override // com.lody.virtual.server.interfaces.IActivityManager
    public void removeIntentSender(IBinder iBinder) {
        if (iBinder != null) {
            synchronized (this.mIntentSenderMap) {
                this.mIntentSenderMap.remove(iBinder);
            }
        }
    }

    @Override // com.lody.virtual.server.interfaces.IActivityManager
    public IntentSenderData getIntentSender(IBinder iBinder) {
        IntentSenderData intentSenderData;
        if (iBinder == null) {
            return null;
        }
        synchronized (this.mIntentSenderMap) {
            intentSenderData = this.mIntentSenderMap.get(iBinder);
        }
        return intentSenderData;
    }

    @Override // com.lody.virtual.server.interfaces.IActivityManager
    public ComponentName getCallingActivity(int i, IBinder iBinder) {
        return this.mActivityStack.getCallingActivity(i, iBinder);
    }

    @Override // com.lody.virtual.server.interfaces.IActivityManager
    public String getCallingPackage(int i, IBinder iBinder) {
        return this.mActivityStack.getCallingPackage(i, iBinder);
    }

    private void addRecord(ServiceRecord serviceRecord) {
        synchronized (this.mHistory) {
            this.mHistory.add(serviceRecord);
        }
    }

    private ServiceRecord findRecordLocked(int i, ServiceInfo serviceInfo) {
        for (ServiceRecord serviceRecord : this.mHistory) {
            if (serviceRecord.process == null || serviceRecord.process.userId == i) {
                if (ComponentUtils.isSameComponent(serviceInfo, serviceRecord.serviceInfo)) {
                    return serviceRecord;
                }
            }
        }
        return null;
    }

    private ServiceRecord findRecordLocked(IServiceConnection iServiceConnection) {
        for (ServiceRecord serviceRecord : this.mHistory) {
            if (serviceRecord.containConnection(iServiceConnection)) {
                return serviceRecord;
            }
        }
        return null;
    }

    @Override // com.lody.virtual.server.interfaces.IActivityManager
    public ComponentName startService(Intent intent, String str, int i) {
        ComponentName startServiceCommonLocked;
        synchronized (this) {
            startServiceCommonLocked = startServiceCommonLocked(intent, true, i);
        }
        return startServiceCommonLocked;
    }

    private ComponentName startServiceCommonLocked(Intent intent, boolean z, int i) {
        ServiceRecord findRecordLocked;
        ServiceInfo resolveServiceInfo = VirtualCore.get().resolveServiceInfo(intent, i);
        if (resolveServiceInfo == null) {
            return null;
        }
        ProcessRecord startProcessIfNeedLocked = startProcessIfNeedLocked(ComponentUtils.getProcessName(resolveServiceInfo), i, resolveServiceInfo.packageName, -1, VBinder.getCallingUid());
        if (startProcessIfNeedLocked == null) {
            VLog.m18992e(TAG, "Unable to start new process (" + ComponentUtils.toComponentName(resolveServiceInfo) + ").");
            return null;
        }
        synchronized (this.mHistory) {
            findRecordLocked = findRecordLocked(i, resolveServiceInfo);
        }
        if (findRecordLocked == null) {
            findRecordLocked = new ServiceRecord();
            findRecordLocked.startId = 0;
            findRecordLocked.activeSince = SystemClock.elapsedRealtime();
            findRecordLocked.process = startProcessIfNeedLocked;
            findRecordLocked.serviceInfo = resolveServiceInfo;
            try {
                startProcessIfNeedLocked.client.scheduleCreateService(findRecordLocked, findRecordLocked.serviceInfo);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            startShadowService(startProcessIfNeedLocked);
            addRecord(findRecordLocked);
        }
        findRecordLocked.lastActivityTime = SystemClock.uptimeMillis();
        if (z) {
            findRecordLocked.startId++;
            try {
                startProcessIfNeedLocked.client.scheduleServiceArgs(findRecordLocked, findRecordLocked.startId, intent);
            } catch (RemoteException e2) {
                e2.printStackTrace();
            }
        }
        return ComponentUtils.toComponentName(resolveServiceInfo);
    }

    private void startShadowService(ProcessRecord processRecord) {
        String stubServiceName = StubManifest.getStubServiceName(processRecord.vpid);
        Intent intent = new Intent();
        intent.setClassName(StubManifest.getStubPackageName(processRecord.is64bit), stubServiceName);
        try {
            VirtualCore.get().getContext().bindService(intent, processRecord.conn, 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.lody.virtual.server.interfaces.IActivityManager
    public int stopService(IBinder iBinder, Intent intent, String str, int i) {
        ServiceRecord findRecordLocked;
        synchronized (this) {
            ServiceInfo resolveServiceInfo = VirtualCore.get().resolveServiceInfo(intent, i);
            if (resolveServiceInfo == null) {
                return 0;
            }
            synchronized (this.mHistory) {
                findRecordLocked = findRecordLocked(i, resolveServiceInfo);
            }
            if (findRecordLocked == null) {
                return 0;
            }
            stopServiceCommon(findRecordLocked, ComponentUtils.toComponentName(resolveServiceInfo));
            return 1;
        }
    }

    @Override // com.lody.virtual.server.interfaces.IActivityManager
    public boolean stopServiceToken(ComponentName componentName, IBinder iBinder, int i, int i2) {
        synchronized (this) {
            ServiceRecord serviceRecord = (ServiceRecord) iBinder;
            if (serviceRecord == null || (serviceRecord.startId != i && i != -1)) {
                return false;
            }
            stopServiceCommon(serviceRecord, componentName);
            return true;
        }
    }

    private void stopServiceCommon(ServiceRecord serviceRecord, ComponentName componentName) {
        synchronized (serviceRecord.bindings) {
            for (ServiceRecord.IntentBindRecord intentBindRecord : serviceRecord.bindings) {
                synchronized (intentBindRecord.connections) {
                    for (IServiceConnection iServiceConnection : intentBindRecord.connections) {
                        try {
                            if (BuildCompat.isOreo()) {
                                IServiceConnectionO.connected.call(iServiceConnection, componentName, null, true);
                            } else {
                                iServiceConnection.connected(componentName, null);
                            }
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                    try {
                        serviceRecord.process.client.scheduleUnbindService(serviceRecord, intentBindRecord.intent);
                    } catch (RemoteException e2) {
                        e2.printStackTrace();
                    }
                }
            }
        }
        try {
            serviceRecord.process.client.scheduleStopService(serviceRecord);
        } catch (RemoteException e3) {
            e3.printStackTrace();
        }
        synchronized (this.mHistory) {
            this.mHistory.remove(serviceRecord);
        }
    }

    @Override // com.lody.virtual.server.interfaces.IActivityManager
    public int bindService(IBinder iBinder, IBinder iBinder2, Intent intent, String str, IServiceConnection iServiceConnection, int i, int i2) {
        ServiceRecord findRecordLocked;
        synchronized (this) {
            ServiceInfo resolveServiceInfo = VirtualCore.get().resolveServiceInfo(intent, i2);
            if (resolveServiceInfo == null) {
                return 0;
            }
            synchronized (this.mHistory) {
                findRecordLocked = findRecordLocked(i2, resolveServiceInfo);
            }
            if ((findRecordLocked == null) && (i & 1) != 0) {
                startServiceCommonLocked(intent, false, i2);
                synchronized (this.mHistory) {
                    findRecordLocked = findRecordLocked(i2, resolveServiceInfo);
                }
            }
            if (findRecordLocked == null) {
                return 0;
            }
            synchronized (findRecordLocked.bindings) {
                ServiceRecord.IntentBindRecord peekBindingLocked = findRecordLocked.peekBindingLocked(intent);
                if (peekBindingLocked == null || peekBindingLocked.binder == null || !peekBindingLocked.binder.isBinderAlive()) {
                    try {
                        findRecordLocked.process.client.scheduleBindService(findRecordLocked, intent, false);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                } else {
                    if (peekBindingLocked.doRebind) {
                        try {
                            findRecordLocked.process.client.scheduleBindService(findRecordLocked, intent, true);
                        } catch (RemoteException e2) {
                            e2.printStackTrace();
                        }
                    }
                    connectServiceLocked(iServiceConnection, new ComponentName(findRecordLocked.serviceInfo.packageName, findRecordLocked.serviceInfo.name), peekBindingLocked, false);
                }
                findRecordLocked.lastActivityTime = SystemClock.uptimeMillis();
                findRecordLocked.addToBoundIntentLocked(intent, iServiceConnection);
            }
            return 1;
        }
    }

    @Override // com.lody.virtual.server.interfaces.IActivityManager
    public boolean unbindService(IServiceConnection iServiceConnection, int i) {
        ServiceRecord findRecordLocked;
        synchronized (this) {
            synchronized (this.mHistory) {
                findRecordLocked = findRecordLocked(iServiceConnection);
            }
            if (findRecordLocked == null) {
                return false;
            }
            synchronized (findRecordLocked.bindings) {
                for (ServiceRecord.IntentBindRecord intentBindRecord : findRecordLocked.bindings) {
                    if (intentBindRecord.containConnectionLocked(iServiceConnection)) {
                        intentBindRecord.removeConnectionLocked(iServiceConnection);
                        try {
                            findRecordLocked.process.client.scheduleUnbindService(findRecordLocked, intentBindRecord.intent);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            if (findRecordLocked.startId <= 0 && findRecordLocked.getConnectionCountLocked() <= 0) {
                try {
                    findRecordLocked.process.client.scheduleStopService(findRecordLocked);
                } catch (RemoteException e2) {
                    e2.printStackTrace();
                }
                if (Build.VERSION.SDK_INT < 21) {
                    synchronized (this.mHistory) {
                        this.mHistory.remove(findRecordLocked);
                    }
                }
            }
            return true;
        }
    }

    @Override // com.lody.virtual.server.interfaces.IActivityManager
    public void unbindFinished(IBinder iBinder, Intent intent, boolean z, int i) {
        synchronized (this) {
            ServiceRecord serviceRecord = (ServiceRecord) iBinder;
            if (serviceRecord != null) {
                synchronized (serviceRecord.bindings) {
                    ServiceRecord.IntentBindRecord peekBindingLocked = serviceRecord.peekBindingLocked(intent);
                    if (peekBindingLocked != null) {
                        peekBindingLocked.doRebind = z;
                    }
                }
            }
        }
    }

    @Override // com.lody.virtual.server.interfaces.IActivityManager
    public boolean isVAServiceToken(IBinder iBinder) {
        return iBinder instanceof ServiceRecord;
    }

    @Override // com.lody.virtual.server.interfaces.IActivityManager
    public void serviceDoneExecuting(IBinder iBinder, int i, int i2, int i3, int i4) {
        synchronized (this) {
            ServiceRecord serviceRecord = (ServiceRecord) iBinder;
            if (serviceRecord != null) {
                if (2 == i) {
                    synchronized (this.mHistory) {
                        this.mHistory.remove(serviceRecord);
                    }
                }
            }
        }
    }

    @Override // com.lody.virtual.server.interfaces.IActivityManager
    public IBinder peekService(Intent intent, String str, int i) {
        ServiceRecord findRecordLocked;
        synchronized (this) {
            ServiceInfo resolveServiceInfo = VirtualCore.get().resolveServiceInfo(intent, i);
            if (resolveServiceInfo == null) {
                return null;
            }
            synchronized (this.mHistory) {
                findRecordLocked = findRecordLocked(i, resolveServiceInfo);
            }
            if (findRecordLocked != null) {
                synchronized (findRecordLocked.bindings) {
                    ServiceRecord.IntentBindRecord peekBindingLocked = findRecordLocked.peekBindingLocked(intent);
                    if (peekBindingLocked != null) {
                        return peekBindingLocked.binder;
                    }
                }
            }
            return null;
        }
    }

    @Override // com.lody.virtual.server.interfaces.IActivityManager
    public void publishService(IBinder iBinder, Intent intent, IBinder iBinder2, int i) {
        synchronized (this) {
            ServiceRecord serviceRecord = (ServiceRecord) iBinder;
            if (serviceRecord != null) {
                synchronized (serviceRecord.bindings) {
                    ServiceRecord.IntentBindRecord peekBindingLocked = serviceRecord.peekBindingLocked(intent);
                    if (peekBindingLocked != null) {
                        peekBindingLocked.binder = iBinder2;
                        synchronized (peekBindingLocked.connections) {
                            for (IServiceConnection iServiceConnection : peekBindingLocked.connections) {
                                connectServiceLocked(iServiceConnection, ComponentUtils.toComponentName(serviceRecord.serviceInfo), peekBindingLocked, false);
                            }
                        }
                    }
                }
            }
        }
    }

    private void connectServiceLocked(IServiceConnection iServiceConnection, ComponentName componentName, ServiceRecord.IntentBindRecord intentBindRecord, boolean z) {
        try {
            BinderDelegateService binderDelegateService = new BinderDelegateService(componentName, intentBindRecord.binder);
            if (BuildCompat.isOreo()) {
                IServiceConnectionO.connected.call(iServiceConnection, componentName, binderDelegateService, Boolean.valueOf(z));
            } else {
                iServiceConnection.connected(componentName, binderDelegateService);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override // com.lody.virtual.server.interfaces.IActivityManager
    public VParceledListSlice<ActivityManager.RunningServiceInfo> getServices(int i, int i2, int i3) {
        VParceledListSlice<ActivityManager.RunningServiceInfo> vParceledListSlice;
        ProcessRecord findProcessLocked;
        synchronized (this.mHistory) {
            ArrayList arrayList = new ArrayList(this.mHistory.size());
            for (ServiceRecord serviceRecord : this.mHistory) {
                if (serviceRecord.process.userId == i3) {
                    ActivityManager.RunningServiceInfo runningServiceInfo = new ActivityManager.RunningServiceInfo();
                    runningServiceInfo.uid = serviceRecord.process.vuid;
                    runningServiceInfo.pid = serviceRecord.process.pid;
                    synchronized (this.mPidsSelfLocked) {
                        findProcessLocked = findProcessLocked(serviceRecord.process.pid);
                    }
                    if (findProcessLocked != null) {
                        runningServiceInfo.process = findProcessLocked.processName;
                        runningServiceInfo.clientPackage = findProcessLocked.info.packageName;
                    }
                    runningServiceInfo.activeSince = serviceRecord.activeSince;
                    runningServiceInfo.lastActivityTime = serviceRecord.lastActivityTime;
                    runningServiceInfo.clientCount = serviceRecord.getClientCount();
                    runningServiceInfo.service = ComponentUtils.toComponentName(serviceRecord.serviceInfo);
                    runningServiceInfo.started = serviceRecord.startId > 0;
                    arrayList.add(runningServiceInfo);
                }
            }
            vParceledListSlice = new VParceledListSlice<>(arrayList);
        }
        return vParceledListSlice;
    }

    @Override // com.lody.virtual.server.interfaces.IActivityManager
    public void setServiceForeground(ComponentName componentName, IBinder iBinder, int i, Notification notification, boolean z, int i2) {
        ServiceRecord serviceRecord = (ServiceRecord) iBinder;
        if (serviceRecord == null) {
            return;
        }
        if (i != 0) {
            if (notification != null) {
                if (serviceRecord.foregroundId != i) {
                    if (serviceRecord.foregroundId != 0) {
                        cancelNotification(i2, serviceRecord.foregroundId, serviceRecord.serviceInfo.packageName);
                    }
                    serviceRecord.foregroundId = i;
                }
                serviceRecord.foregroundNoti = notification;
                postNotification(i2, i, serviceRecord.serviceInfo.packageName, notification);
                return;
            }
            throw new IllegalArgumentException("null notification");
        } else if (z) {
            cancelNotification(i2, serviceRecord.foregroundId, serviceRecord.serviceInfo.packageName);
            serviceRecord.foregroundId = 0;
            serviceRecord.foregroundNoti = null;
        }
    }

    private void cancelNotification(int i, int i2, String str) {
        int dealNotificationId = VNotificationManager.get().dealNotificationId(i2, str, null, i);
        this.f10502nm.cancel(VNotificationManager.get().dealNotificationTag(dealNotificationId, str, null, i), dealNotificationId);
    }

    private void postNotification(int i, int i2, String str, Notification notification) {
        int dealNotificationId = VNotificationManager.get().dealNotificationId(i2, str, null, i);
        String dealNotificationTag = VNotificationManager.get().dealNotificationTag(dealNotificationId, str, null, i);
        VNotificationManager.get().addNotification(dealNotificationId, dealNotificationTag, str, i);
        try {
            this.f10502nm.notify(dealNotificationTag, dealNotificationId, notification);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // com.lody.virtual.server.interfaces.IActivityManager
    public void processRestarted(String str, String str2, int i) {
        ProcessRecord findProcessLocked;
        int callingUid = VBinder.getCallingUid();
        int callingPid = VBinder.getCallingPid();
        synchronized (this) {
            synchronized (this.mProcessLock) {
                findProcessLocked = findProcessLocked(callingPid);
            }
            if (findProcessLocked == null) {
                String processName = getProcessName(callingPid);
                if (processName != null) {
                    int parseVPid = parseVPid(processName);
                    if (parseVPid != -1) {
                        startProcessIfNeedLocked(str2, i, str, parseVPid, callingUid);
                    }
                }
            }
        }
    }

    private int parseVPid(String str) {
        String str2;
        if (str == null) {
            return -1;
        }
        if (str.startsWith(StubManifest.PACKAGE_NAME_64BIT)) {
            str2 = StubManifest.PACKAGE_NAME_64BIT + ":p";
        } else if (!str.startsWith(StubManifest.PACKAGE_NAME)) {
            return -1;
        } else {
            str2 = VirtualCore.get().getHostPkg() + ":p";
        }
        if (str.startsWith(str2)) {
            try {
                return Integer.parseInt(str.substring(str2.length()));
            } catch (NumberFormatException unused) {
            }
        }
        return -1;
    }

    private String getProcessName(int i) {
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : VirtualCore.get().getRunningAppProcessesEx()) {
            if (runningAppProcessInfo.pid == i) {
                return runningAppProcessInfo.processName;
            }
        }
        return null;
    }

    private boolean attachClient(final ProcessRecord processRecord, final IBinder iBinder) {
        IVClient asInterface = IVClient.Stub.asInterface(iBinder);
        if (asInterface == null) {
            processRecord.kill();
            return false;
        }
        try {
            iBinder.linkToDeath(new IBinder.DeathRecipient() { // from class: com.lody.virtual.server.am.VActivityManagerService.2
                @Override // android.os.IBinder.DeathRecipient
                public void binderDied() {
                    iBinder.unlinkToDeath(this, 0);
                    VActivityManagerService.this.onProcessDied(processRecord);
                }
            }, 0);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        processRecord.client = asInterface;
        try {
            processRecord.appThread = ApplicationThreadCompat.asInterface(asInterface.getAppThread());
            return true;
        } catch (RemoteException e2) {
            e2.printStackTrace();
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onProcessDied(ProcessRecord processRecord) {
        synchronized (this.mProcessLock) {
            this.mProcessNames.remove(processRecord.processName, processRecord.vuid);
            this.mPidsSelfLocked.remove(processRecord);
        }
        processDied(processRecord);
    }

    @Override // com.lody.virtual.server.interfaces.IActivityManager
    public int getFreeStubCount() {
        return StubManifest.STUB_COUNT - this.mPidsSelfLocked.size();
    }

    @Override // com.lody.virtual.server.interfaces.IActivityManager
    public int checkPermission(boolean z, String str, int i, int i2) {
        if (str == null) {
            return -1;
        }
        if ("android.permission.ACCOUNT_MANAGER".equals(str)) {
            return 0;
        }
        if ("android.permission.INTERACT_ACROSS_USERS".equals(str) || "android.permission.INTERACT_ACROSS_USERS_FULL".equals(str)) {
            return -1;
        }
        if (i2 == 0) {
            return 0;
        }
        return VPackageManagerService.get().checkUidPermission(z, str, i2);
    }

    @Override // com.lody.virtual.server.interfaces.IActivityManager
    public ClientConfig initProcess(String str, String str2, int i) {
        synchronized (this) {
            ProcessRecord startProcessIfNeedLocked = startProcessIfNeedLocked(str2, i, str, -1, VBinder.getCallingUid());
            if (startProcessIfNeedLocked == null) {
                return null;
            }
            return startProcessIfNeedLocked.getClientConfig();
        }
    }

    @Override // com.lody.virtual.server.interfaces.IActivityManager
    public void appDoneExecuting(String str, int i) {
        ProcessRecord findProcessLocked = findProcessLocked(VBinder.getCallingPid());
        if (findProcessLocked != null) {
            findProcessLocked.pkgList.add(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ProcessRecord startProcessIfNeedLocked(String str, int i, String str2, int i2, int i3) {
        ProcessRecord processRecord;
        int queryFreeStubProcess;
        runProcessGC();
        PackageSetting setting = PackageCacheManager.getSetting(str2);
        ApplicationInfo applicationInfo = VPackageManagerService.get().getApplicationInfo(str2, 0, i);
        if (setting == null || applicationInfo == null) {
            return null;
        }
        if (!setting.isLaunched(i)) {
            sendFirstLaunchBroadcast(setting, i);
            setting.setLaunched(i, true);
            VAppManagerService.get().savePersistenceData();
        }
        int uid = VUserHandle.getUid(i, setting.appId);
        boolean isRunOn64BitProcess = setting.isRunOn64BitProcess();
        synchronized (this.mProcessLock) {
            if (i2 == -1) {
                try {
                    processRecord = this.mProcessNames.get(str, uid);
                    if (processRecord != null) {
                        if (processRecord.initLock != null) {
                            processRecord.initLock.block();
                        }
                        if (processRecord.client != null) {
                            return processRecord;
                        }
                    }
                    VLog.m18986w(TAG, "start new process : " + str, new Object[0]);
                    queryFreeStubProcess = queryFreeStubProcess(isRunOn64BitProcess);
                } catch (Throwable th) {
                    throw th;
                }
            } else {
                queryFreeStubProcess = i2;
                processRecord = null;
            }
            if (queryFreeStubProcess == -1) {
                VLog.m18992e(TAG, "Unable to query free stub for : " + str);
                return null;
            }
            if (processRecord != null) {
                VLog.m18986w(TAG, "remove invalid process record: " + processRecord.processName, new Object[0]);
                this.mProcessNames.remove(processRecord.processName, processRecord.vuid);
                this.mPidsSelfLocked.remove(processRecord);
            }
            ProcessRecord processRecord2 = new ProcessRecord(applicationInfo, str, uid, queryFreeStubProcess, i3, isRunOn64BitProcess);
            this.mProcessNames.put(processRecord2.processName, processRecord2.vuid, processRecord2);
            this.mPidsSelfLocked.add(processRecord2);
            if (initProcess(processRecord2)) {
                return processRecord2;
            }
            return null;
        }
    }

    private void runProcessGC() {
        if (get().getFreeStubCount() < 3) {
            killAllApps();
        }
    }

    private void sendFirstLaunchBroadcast(PackageSetting packageSetting, int i) {
        Intent intent = new Intent("android.intent.action.PACKAGE_FIRST_LAUNCH", Uri.fromParts(ServiceManagerNative.PACKAGE, packageSetting.packageName, null));
        intent.setPackage(packageSetting.packageName);
        intent.putExtra("android.intent.extra.UID", VUserHandle.getUid(packageSetting.appId, i));
        intent.putExtra(Constants.EXTRA_USER_HANDLE, i);
        sendBroadcastAsUser(intent, new VUserHandle(i));
    }

    @Override // com.lody.virtual.server.interfaces.IActivityManager
    public int getUidByPid(int i) {
        if (i == Process.myPid()) {
            return Constants.OUTSIDE_APP_UID;
        }
        boolean z = false;
        if (i == 0) {
            i = VBinder.getCallingPid();
            z = true;
        }
        synchronized (this.mProcessLock) {
            ProcessRecord findProcessLocked = findProcessLocked(i);
            if (findProcessLocked == null) {
                return i == Process.myPid() ? Constants.OUTSIDE_APP_UID : Constants.OUTSIDE_APP_UID;
            }
            if (z) {
                return findProcessLocked.callingVUid;
            }
            return findProcessLocked.vuid;
        }
    }

    private void startRequestPermissions(boolean z, String[] strArr, final ConditionVariable conditionVariable) {
        PermissionCompat.startRequestPermissions(VirtualCore.get().getContext(), z, strArr, new PermissionCompat.CallBack() { // from class: com.lody.virtual.server.am.VActivityManagerService.3
            /* JADX WARN: Finally extract failed */
            @Override // com.lody.virtual.helper.compat.PermissionCompat.CallBack
            public boolean onResult(int i, String[] strArr2, int[] iArr) {
                try {
                    VActivityManagerService.this.mResult = PermissionCompat.isRequestGranted(iArr);
                    conditionVariable.open();
                    return VActivityManagerService.this.mResult;
                } catch (Throwable th) {
                    conditionVariable.open();
                    throw th;
                }
            }
        });
    }

    private boolean initProcess(ProcessRecord processRecord) {
        try {
            requestPermissionIfNeed(processRecord);
            Bundle bundle = new Bundle();
            bundle.putParcelable("_VA_|_client_config_", processRecord.getClientConfig());
            Bundle callSafely = ProviderCall.callSafely(processRecord.getProviderAuthority(), "_VA_|_init_process_", null, bundle);
            if (callSafely == null) {
                return false;
            }
            processRecord.pid = callSafely.getInt("_VA_|_pid_");
            return attachClient(processRecord, BundleCompat.getBinder(callSafely, "_VA_|_client_"));
        } finally {
            processRecord.initLock.open();
            processRecord.initLock = null;
        }
    }

    private void requestPermissionIfNeed(ProcessRecord processRecord) {
        if (PermissionCompat.isCheckPermissionRequired(processRecord.info)) {
            String[] dangrousPermissions = VPackageManagerService.get().getDangrousPermissions(processRecord.info.packageName);
            if (!PermissionCompat.checkPermissions(dangrousPermissions, processRecord.is64bit)) {
                ConditionVariable conditionVariable = new ConditionVariable();
                startRequestPermissions(processRecord.is64bit, dangrousPermissions, conditionVariable);
                conditionVariable.block();
            }
        }
    }

    public int queryFreeStubProcess(boolean z) {
        boolean z2;
        synchronized (this.mProcessLock) {
            for (int i = 0; i < StubManifest.STUB_COUNT; i++) {
                int size = this.mPidsSelfLocked.size();
                while (true) {
                    int i2 = size - 1;
                    if (size <= 0) {
                        z2 = false;
                        break;
                    }
                    ProcessRecord processRecord = this.mPidsSelfLocked.get(i2);
                    if (processRecord.vpid == i && processRecord.is64bit == z) {
                        z2 = true;
                        break;
                    }
                    size = i2;
                }
                if (!z2) {
                    return i;
                }
            }
            return -1;
        }
    }

    @Override // com.lody.virtual.server.interfaces.IActivityManager
    public boolean isAppProcess(String str) {
        return parseVPid(str) != -1;
    }

    @Override // com.lody.virtual.server.interfaces.IActivityManager
    public boolean isAppPid(int i) {
        boolean z;
        synchronized (this.mProcessLock) {
            z = findProcessLocked(i) != null;
        }
        return z;
    }

    @Override // com.lody.virtual.server.interfaces.IActivityManager
    public String getAppProcessName(int i) {
        synchronized (this.mProcessLock) {
            ProcessRecord findProcessLocked = findProcessLocked(i);
            if (findProcessLocked == null) {
                return null;
            }
            return findProcessLocked.processName;
        }
    }

    @Override // com.lody.virtual.server.interfaces.IActivityManager
    public List<String> getProcessPkgList(int i) {
        synchronized (this.mProcessLock) {
            ProcessRecord findProcessLocked = findProcessLocked(i);
            if (findProcessLocked == null) {
                return Collections.emptyList();
            }
            return new ArrayList(findProcessLocked.pkgList);
        }
    }

    @Override // com.lody.virtual.server.interfaces.IActivityManager
    public void killAllApps() {
        synchronized (this.mProcessLock) {
            for (int i = 0; i < this.mPidsSelfLocked.size(); i++) {
                this.mPidsSelfLocked.get(i).kill();
            }
        }
    }

    @Override // com.lody.virtual.server.interfaces.IActivityManager
    public void killAppByPkg(String str, int i) {
        synchronized (this.mProcessLock) {
            ArrayMap<String, SparseArray<ProcessRecord>> map = this.mProcessNames.getMap();
            int size = map.size();
            while (true) {
                int i2 = size - 1;
                if (size > 0) {
                    SparseArray<ProcessRecord> valueAt = map.valueAt(i2);
                    if (valueAt != null) {
                        for (int i3 = 0; i3 < valueAt.size(); i3++) {
                            ProcessRecord valueAt2 = valueAt.valueAt(i3);
                            if ((i == -1 || valueAt2.userId == i) && valueAt2.pkgList.contains(str)) {
                                valueAt2.kill();
                            }
                        }
                    }
                    size = i2;
                }
            }
        }
    }

    @Override // com.lody.virtual.server.interfaces.IActivityManager
    public void finishAllActivities() {
        synchronized (this.mProcessLock) {
            for (int i = 0; i < this.mPidsSelfLocked.size(); i++) {
                ProcessRecord processRecord = this.mPidsSelfLocked.get(i);
                this.mActivityStack.finishAllActivities(processRecord);
                processRecord.kill();
            }
        }
    }

    @Override // com.lody.virtual.server.interfaces.IActivityManager
    public void finishAllActivitiesByPkg(String str, int i) {
        synchronized (this.mProcessLock) {
            ArrayMap<String, SparseArray<ProcessRecord>> map = this.mProcessNames.getMap();
            int size = map.size();
            while (true) {
                int i2 = size - 1;
                if (size > 0) {
                    SparseArray<ProcessRecord> valueAt = map.valueAt(i2);
                    if (valueAt != null) {
                        for (int i3 = 0; i3 < valueAt.size(); i3++) {
                            ProcessRecord valueAt2 = valueAt.valueAt(i3);
                            if ((i == -1 || valueAt2.userId == i) && valueAt2.pkgList.contains(str)) {
                                this.mActivityStack.finishAllActivities(valueAt2);
                            }
                        }
                    }
                    size = i2;
                }
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0033, code lost:
        r5 = r1.client.isAppRunning();
     */
    @Override // com.lody.virtual.server.interfaces.IActivityManager
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean isAppRunning(java.lang.String r5, int r6, boolean r7) {
        /*
            r4 = this;
            java.lang.Object r0 = r4.mProcessLock
            monitor-enter(r0)
            java.util.List<com.lody.virtual.server.am.ProcessRecord> r1 = r4.mPidsSelfLocked     // Catch: all -> 0x0041
            int r1 = r1.size()     // Catch: all -> 0x0041
        L_0x0009:
            int r2 = r1 + (-1)
            if (r1 <= 0) goto L_0x003e
            java.util.List<com.lody.virtual.server.am.ProcessRecord> r1 = r4.mPidsSelfLocked     // Catch: all -> 0x0041
            java.lang.Object r1 = r1.get(r2)     // Catch: all -> 0x0041
            com.lody.virtual.server.am.ProcessRecord r1 = (com.lody.virtual.server.p062am.ProcessRecord) r1     // Catch: all -> 0x0041
            int r3 = r1.userId     // Catch: all -> 0x0041
            if (r3 == r6) goto L_0x001a
            goto L_0x0031
        L_0x001a:
            android.content.pm.ApplicationInfo r3 = r1.info     // Catch: all -> 0x0041
            java.lang.String r3 = r3.packageName     // Catch: all -> 0x0041
            boolean r3 = r3.equals(r5)     // Catch: all -> 0x0041
            if (r3 != 0) goto L_0x0025
            goto L_0x0031
        L_0x0025:
            if (r7 == 0) goto L_0x0033
            android.content.pm.ApplicationInfo r3 = r1.info     // Catch: all -> 0x0041
            java.lang.String r3 = r3.processName     // Catch: all -> 0x0041
            boolean r3 = r3.equals(r5)     // Catch: all -> 0x0041
            if (r3 != 0) goto L_0x0033
        L_0x0031:
            r1 = r2
            goto L_0x0009
        L_0x0033:
            com.lody.virtual.client.IVClient r5 = r1.client     // Catch: Exception -> 0x003a, all -> 0x0041
            boolean r5 = r5.isAppRunning()     // Catch: Exception -> 0x003a, all -> 0x0041
            goto L_0x003f
        L_0x003a:
            r5 = move-exception
            r5.printStackTrace()     // Catch: all -> 0x0041
        L_0x003e:
            r5 = 0
        L_0x003f:
            monitor-exit(r0)     // Catch: all -> 0x0041
            return r5
        L_0x0041:
            r5 = move-exception
            monitor-exit(r0)     // Catch: all -> 0x0041
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lody.virtual.server.p062am.VActivityManagerService.isAppRunning(java.lang.String, int, boolean):boolean");
    }

    @Override // com.lody.virtual.server.interfaces.IActivityManager
    public void killApplicationProcess(String str, int i) {
        synchronized (this.mProcessLock) {
            ProcessRecord processRecord = this.mProcessNames.get(str, i);
            if (processRecord != null) {
                if (processRecord.is64bit) {
                    V64BitHelper.forceStop64(processRecord.pid);
                } else {
                    processRecord.kill();
                }
            }
        }
    }

    @Override // com.lody.virtual.server.interfaces.IActivityManager
    public String getInitialPackage(int i) {
        synchronized (this.mProcessLock) {
            ProcessRecord findProcessLocked = findProcessLocked(i);
            if (findProcessLocked == null) {
                return null;
            }
            return findProcessLocked.info.packageName;
        }
    }

    public ProcessRecord findProcessLocked(int i) {
        for (ProcessRecord processRecord : this.mPidsSelfLocked) {
            if (processRecord.pid == i) {
                return processRecord;
            }
        }
        return null;
    }

    public ProcessRecord findProcessLocked(String str, int i) {
        return this.mProcessNames.get(str, i);
    }

    public int stopUser(int i, IStopUserCallback.Stub stub) {
        synchronized (this.mProcessLock) {
            int size = this.mPidsSelfLocked.size();
            while (true) {
                int i2 = size - 1;
                if (size > 0) {
                    ProcessRecord processRecord = this.mPidsSelfLocked.get(i2);
                    if (processRecord.userId == i) {
                        processRecord.kill();
                    }
                    size = i2;
                }
            }
        }
        try {
            stub.userStopped(i);
            return 0;
        } catch (RemoteException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public void sendOrderedBroadcastAsUser(Intent intent, VUserHandle vUserHandle, String str, BroadcastReceiver broadcastReceiver, Handler handler, int i, String str2, Bundle bundle) {
        Context context = VirtualCore.get().getContext();
        if (vUserHandle != null) {
            intent.putExtra("_VA_|_user_id_", vUserHandle.getIdentifier());
        }
        context.sendOrderedBroadcast(intent, null, broadcastReceiver, handler, i, str2, bundle);
    }

    public void sendBroadcastAsUser(Intent intent, VUserHandle vUserHandle) {
        SpecialComponentList.protectIntent(intent);
        Context context = VirtualCore.get().getContext();
        if (vUserHandle != null) {
            intent.putExtra("_VA_|_user_id_", vUserHandle.getIdentifier());
        }
        context.sendBroadcast(intent);
    }

    public boolean bindServiceAsUser(Intent intent, ServiceConnection serviceConnection, int i, VUserHandle vUserHandle) {
        Intent intent2 = new Intent(intent);
        if (vUserHandle != null) {
            intent2.putExtra("_VA_|_user_id_", vUserHandle.getIdentifier());
        }
        return VirtualCore.get().getContext().bindService(intent2, serviceConnection, i);
    }

    public void sendBroadcastAsUser(Intent intent, VUserHandle vUserHandle, String str) {
        SpecialComponentList.protectIntent(intent);
        Context context = VirtualCore.get().getContext();
        if (vUserHandle != null) {
            intent.putExtra("_VA_|_user_id_", vUserHandle.getIdentifier());
        }
        context.sendBroadcast(intent);
    }

    @Override // com.lody.virtual.server.interfaces.IActivityManager
    public void notifyBadgerChange(BadgerInfo badgerInfo) {
        Intent intent = new Intent(Constants.ACTION_BADGER_CHANGE);
        intent.putExtra(ChooseTypeAndAccountActivity.KEY_USER_ID, badgerInfo.userId);
        intent.putExtra("packageName", badgerInfo.packageName);
        intent.putExtra("badgerCount", badgerInfo.badgerCount);
        VirtualCore.get().getContext().sendBroadcast(intent);
    }

    @Override // com.lody.virtual.server.interfaces.IActivityManager
    public int getCallingUidByPid(int i) {
        synchronized (this.mProcessLock) {
            ProcessRecord findProcessLocked = findProcessLocked(i);
            if (findProcessLocked == null) {
                return -1;
            }
            return findProcessLocked.getCallingVUid();
        }
    }

    @Override // com.lody.virtual.server.interfaces.IActivityManager
    public void setAppInactive(String str, boolean z, int i) {
        synchronized (this.sIdeMap) {
            Map<String, Boolean> map = this.sIdeMap;
            map.put(str + "@" + i, Boolean.valueOf(z));
        }
    }

    @Override // com.lody.virtual.server.interfaces.IActivityManager
    public boolean isAppInactive(String str, int i) {
        boolean z;
        synchronized (this.sIdeMap) {
            Map<String, Boolean> map = this.sIdeMap;
            Boolean bool = map.get(str + "@" + i);
            z = bool != null && !bool.booleanValue();
        }
        return z;
    }

    @Override // com.lody.virtual.server.interfaces.IActivityManager
    public void handleDownloadCompleteIntent(Intent intent) {
        intent.setPackage(null);
        intent.setComponent(null);
        VirtualCore.get().getContext().sendBroadcast(ComponentUtils.redirectBroadcastIntent(intent, -1));
    }
}
