package com.lody.virtual.server.interfaces;

import android.app.IServiceConnection;
import android.app.Notification;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ProviderInfo;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.lody.virtual.remote.AppTaskInfo;
import com.lody.virtual.remote.BadgerInfo;
import com.lody.virtual.remote.ClientConfig;
import com.lody.virtual.remote.IntentSenderData;
import com.lody.virtual.remote.VParceledListSlice;
import java.util.List;

/* loaded from: classes.dex */
public interface IActivityManager extends IInterface {
    IBinder acquireProviderClient(int i, ProviderInfo providerInfo) throws RemoteException;

    void addOrUpdateIntentSender(IntentSenderData intentSenderData, int i) throws RemoteException;

    void appDoneExecuting(String str, int i) throws RemoteException;

    int bindService(IBinder iBinder, IBinder iBinder2, Intent intent, String str, IServiceConnection iServiceConnection, int i, int i2) throws RemoteException;

    boolean broadcastFinish(IBinder iBinder) throws RemoteException;

    int checkPermission(boolean z, String str, int i, int i2) throws RemoteException;

    void dump() throws RemoteException;

    boolean finishActivityAffinity(int i, IBinder iBinder) throws RemoteException;

    void finishAllActivities() throws RemoteException;

    void finishAllActivitiesByPkg(String str, int i) throws RemoteException;

    ComponentName getActivityClassForToken(int i, IBinder iBinder) throws RemoteException;

    String getAppProcessName(int i) throws RemoteException;

    ComponentName getCallingActivity(int i, IBinder iBinder) throws RemoteException;

    String getCallingPackage(int i, IBinder iBinder) throws RemoteException;

    int getCallingUidByPid(int i) throws RemoteException;

    int getFreeStubCount() throws RemoteException;

    String getInitialPackage(int i) throws RemoteException;

    IntentSenderData getIntentSender(IBinder iBinder) throws RemoteException;

    String getPackageForToken(int i, IBinder iBinder) throws RemoteException;

    List<String> getProcessPkgList(int i) throws RemoteException;

    VParceledListSlice getServices(int i, int i2, int i3) throws RemoteException;

    int getSystemPid() throws RemoteException;

    int getSystemUid() throws RemoteException;

    AppTaskInfo getTaskInfo(int i) throws RemoteException;

    int getUidByPid(int i) throws RemoteException;

    void handleDownloadCompleteIntent(Intent intent) throws RemoteException;

    ClientConfig initProcess(String str, String str2, int i) throws RemoteException;

    boolean isAppInactive(String str, int i) throws RemoteException;

    boolean isAppPid(int i) throws RemoteException;

    boolean isAppProcess(String str) throws RemoteException;

    boolean isAppRunning(String str, int i, boolean z) throws RemoteException;

    boolean isVAServiceToken(IBinder iBinder) throws RemoteException;

    void killAllApps() throws RemoteException;

    void killAppByPkg(String str, int i) throws RemoteException;

    void killApplicationProcess(String str, int i) throws RemoteException;

    void notifyBadgerChange(BadgerInfo badgerInfo) throws RemoteException;

    void onActivityCreated(IBinder iBinder, IBinder iBinder2, int i) throws RemoteException;

    boolean onActivityDestroyed(int i, IBinder iBinder) throws RemoteException;

    void onActivityFinish(int i, IBinder iBinder) throws RemoteException;

    void onActivityResumed(int i, IBinder iBinder) throws RemoteException;

    IBinder peekService(Intent intent, String str, int i) throws RemoteException;

    void processRestarted(String str, String str2, int i) throws RemoteException;

    void publishService(IBinder iBinder, Intent intent, IBinder iBinder2, int i) throws RemoteException;

    void removeIntentSender(IBinder iBinder) throws RemoteException;

    void serviceDoneExecuting(IBinder iBinder, int i, int i2, int i3, int i4) throws RemoteException;

    void setAppInactive(String str, boolean z, int i) throws RemoteException;

    void setServiceForeground(ComponentName componentName, IBinder iBinder, int i, Notification notification, boolean z, int i2) throws RemoteException;

    int startActivities(Intent[] intentArr, String[] strArr, IBinder iBinder, Bundle bundle, int i) throws RemoteException;

    int startActivity(Intent intent, ActivityInfo activityInfo, IBinder iBinder, Bundle bundle, String str, int i, int i2) throws RemoteException;

    ComponentName startService(Intent intent, String str, int i) throws RemoteException;

    int stopService(IBinder iBinder, Intent intent, String str, int i) throws RemoteException;

    boolean stopServiceToken(ComponentName componentName, IBinder iBinder, int i, int i2) throws RemoteException;

    void unbindFinished(IBinder iBinder, Intent intent, boolean z, int i) throws RemoteException;

    boolean unbindService(IServiceConnection iServiceConnection, int i) throws RemoteException;

    /* loaded from: classes.dex */
    public static abstract class Stub extends Binder implements IActivityManager {
        private static final String DESCRIPTOR = "com.lody.virtual.server.interfaces.IActivityManager";
        static final int TRANSACTION_acquireProviderClient = 33;
        static final int TRANSACTION_addOrUpdateIntentSender = 35;
        static final int TRANSACTION_appDoneExecuting = 2;
        static final int TRANSACTION_bindService = 47;
        static final int TRANSACTION_broadcastFinish = 34;
        static final int TRANSACTION_checkPermission = 4;
        static final int TRANSACTION_dump = 18;
        static final int TRANSACTION_finishActivityAffinity = 22;
        static final int TRANSACTION_finishAllActivities = 15;
        static final int TRANSACTION_finishAllActivitiesByPkg = 16;
        static final int TRANSACTION_getActivityClassForToken = 27;
        static final int TRANSACTION_getAppProcessName = 11;
        static final int TRANSACTION_getCallingActivity = 30;
        static final int TRANSACTION_getCallingPackage = 28;
        static final int TRANSACTION_getCallingUidByPid = 29;
        static final int TRANSACTION_getFreeStubCount = 3;
        static final int TRANSACTION_getInitialPackage = 19;
        static final int TRANSACTION_getIntentSender = 37;
        static final int TRANSACTION_getPackageForToken = 32;
        static final int TRANSACTION_getProcessPkgList = 12;
        static final int TRANSACTION_getServices = 53;
        static final int TRANSACTION_getSystemPid = 5;
        static final int TRANSACTION_getSystemUid = 6;
        static final int TRANSACTION_getTaskInfo = 31;
        static final int TRANSACTION_getUidByPid = 7;
        static final int TRANSACTION_handleDownloadCompleteIntent = 54;
        static final int TRANSACTION_initProcess = 1;
        static final int TRANSACTION_isAppInactive = 41;
        static final int TRANSACTION_isAppPid = 10;
        static final int TRANSACTION_isAppProcess = 8;
        static final int TRANSACTION_isAppRunning = 9;
        static final int TRANSACTION_isVAServiceToken = 42;
        static final int TRANSACTION_killAllApps = 13;
        static final int TRANSACTION_killAppByPkg = 14;
        static final int TRANSACTION_killApplicationProcess = 17;
        static final int TRANSACTION_notifyBadgerChange = 39;
        static final int TRANSACTION_onActivityCreated = 23;
        static final int TRANSACTION_onActivityDestroyed = 25;
        static final int TRANSACTION_onActivityFinish = 26;
        static final int TRANSACTION_onActivityResumed = 24;
        static final int TRANSACTION_peekService = 51;
        static final int TRANSACTION_processRestarted = 38;
        static final int TRANSACTION_publishService = 52;
        static final int TRANSACTION_removeIntentSender = 36;
        static final int TRANSACTION_serviceDoneExecuting = 50;
        static final int TRANSACTION_setAppInactive = 40;
        static final int TRANSACTION_setServiceForeground = 46;
        static final int TRANSACTION_startActivities = 20;
        static final int TRANSACTION_startActivity = 21;
        static final int TRANSACTION_startService = 43;
        static final int TRANSACTION_stopService = 44;
        static final int TRANSACTION_stopServiceToken = 45;
        static final int TRANSACTION_unbindFinished = 49;
        static final int TRANSACTION_unbindService = 48;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IActivityManager asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IActivityManager)) {
                return new Proxy(iBinder);
            }
            return (IActivityManager) queryLocalInterface;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1598968902) {
                boolean z = false;
                Bundle bundle = null;
                Intent intent = null;
                Intent intent2 = null;
                Intent intent3 = null;
                Intent intent4 = null;
                Intent intent5 = null;
                Notification notification = null;
                ComponentName componentName = null;
                Intent intent6 = null;
                Intent intent7 = null;
                BadgerInfo badgerInfo = null;
                IntentSenderData intentSenderData = null;
                ProviderInfo providerInfo = null;
                Bundle bundle2 = null;
                switch (i) {
                    case 1:
                        parcel.enforceInterface(DESCRIPTOR);
                        ClientConfig initProcess = initProcess(parcel.readString(), parcel.readString(), parcel.readInt());
                        parcel2.writeNoException();
                        if (initProcess != null) {
                            parcel2.writeInt(1);
                            initProcess.writeToParcel(parcel2, 1);
                        } else {
                            parcel2.writeInt(0);
                        }
                        return true;
                    case 2:
                        parcel.enforceInterface(DESCRIPTOR);
                        appDoneExecuting(parcel.readString(), parcel.readInt());
                        parcel2.writeNoException();
                        return true;
                    case 3:
                        parcel.enforceInterface(DESCRIPTOR);
                        int freeStubCount = getFreeStubCount();
                        parcel2.writeNoException();
                        parcel2.writeInt(freeStubCount);
                        return true;
                    case 4:
                        parcel.enforceInterface(DESCRIPTOR);
                        if (parcel.readInt() != 0) {
                            z = true;
                        }
                        int checkPermission = checkPermission(z, parcel.readString(), parcel.readInt(), parcel.readInt());
                        parcel2.writeNoException();
                        parcel2.writeInt(checkPermission);
                        return true;
                    case 5:
                        parcel.enforceInterface(DESCRIPTOR);
                        int systemPid = getSystemPid();
                        parcel2.writeNoException();
                        parcel2.writeInt(systemPid);
                        return true;
                    case 6:
                        parcel.enforceInterface(DESCRIPTOR);
                        int systemUid = getSystemUid();
                        parcel2.writeNoException();
                        parcel2.writeInt(systemUid);
                        return true;
                    case 7:
                        parcel.enforceInterface(DESCRIPTOR);
                        int uidByPid = getUidByPid(parcel.readInt());
                        parcel2.writeNoException();
                        parcel2.writeInt(uidByPid);
                        return true;
                    case 8:
                        parcel.enforceInterface(DESCRIPTOR);
                        boolean isAppProcess = isAppProcess(parcel.readString());
                        parcel2.writeNoException();
                        parcel2.writeInt(isAppProcess ? 1 : 0);
                        return true;
                    case 9:
                        parcel.enforceInterface(DESCRIPTOR);
                        String readString = parcel.readString();
                        int readInt = parcel.readInt();
                        if (parcel.readInt() != 0) {
                            z = true;
                        }
                        boolean isAppRunning = isAppRunning(readString, readInt, z);
                        parcel2.writeNoException();
                        parcel2.writeInt(isAppRunning ? 1 : 0);
                        return true;
                    case 10:
                        parcel.enforceInterface(DESCRIPTOR);
                        boolean isAppPid = isAppPid(parcel.readInt());
                        parcel2.writeNoException();
                        parcel2.writeInt(isAppPid ? 1 : 0);
                        return true;
                    case 11:
                        parcel.enforceInterface(DESCRIPTOR);
                        String appProcessName = getAppProcessName(parcel.readInt());
                        parcel2.writeNoException();
                        parcel2.writeString(appProcessName);
                        return true;
                    case 12:
                        parcel.enforceInterface(DESCRIPTOR);
                        List<String> processPkgList = getProcessPkgList(parcel.readInt());
                        parcel2.writeNoException();
                        parcel2.writeStringList(processPkgList);
                        return true;
                    case 13:
                        parcel.enforceInterface(DESCRIPTOR);
                        killAllApps();
                        parcel2.writeNoException();
                        return true;
                    case 14:
                        parcel.enforceInterface(DESCRIPTOR);
                        killAppByPkg(parcel.readString(), parcel.readInt());
                        parcel2.writeNoException();
                        return true;
                    case 15:
                        parcel.enforceInterface(DESCRIPTOR);
                        finishAllActivities();
                        parcel2.writeNoException();
                        return true;
                    case 16:
                        parcel.enforceInterface(DESCRIPTOR);
                        finishAllActivitiesByPkg(parcel.readString(), parcel.readInt());
                        parcel2.writeNoException();
                        return true;
                    case 17:
                        parcel.enforceInterface(DESCRIPTOR);
                        killApplicationProcess(parcel.readString(), parcel.readInt());
                        parcel2.writeNoException();
                        return true;
                    case 18:
                        parcel.enforceInterface(DESCRIPTOR);
                        dump();
                        parcel2.writeNoException();
                        return true;
                    case 19:
                        parcel.enforceInterface(DESCRIPTOR);
                        String initialPackage = getInitialPackage(parcel.readInt());
                        parcel2.writeNoException();
                        parcel2.writeString(initialPackage);
                        return true;
                    case 20:
                        parcel.enforceInterface(DESCRIPTOR);
                        Intent[] intentArr = (Intent[]) parcel.createTypedArray(Intent.CREATOR);
                        String[] createStringArray = parcel.createStringArray();
                        IBinder readStrongBinder = parcel.readStrongBinder();
                        if (parcel.readInt() != 0) {
                            bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                        }
                        int startActivities = startActivities(intentArr, createStringArray, readStrongBinder, bundle, parcel.readInt());
                        parcel2.writeNoException();
                        parcel2.writeInt(startActivities);
                        return true;
                    case 21:
                        parcel.enforceInterface(DESCRIPTOR);
                        Intent intent8 = parcel.readInt() != 0 ? (Intent) Intent.CREATOR.createFromParcel(parcel) : null;
                        ActivityInfo activityInfo = parcel.readInt() != 0 ? (ActivityInfo) ActivityInfo.CREATOR.createFromParcel(parcel) : null;
                        IBinder readStrongBinder2 = parcel.readStrongBinder();
                        if (parcel.readInt() != 0) {
                            bundle2 = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                        }
                        int startActivity = startActivity(intent8, activityInfo, readStrongBinder2, bundle2, parcel.readString(), parcel.readInt(), parcel.readInt());
                        parcel2.writeNoException();
                        parcel2.writeInt(startActivity);
                        return true;
                    case 22:
                        parcel.enforceInterface(DESCRIPTOR);
                        boolean finishActivityAffinity = finishActivityAffinity(parcel.readInt(), parcel.readStrongBinder());
                        parcel2.writeNoException();
                        parcel2.writeInt(finishActivityAffinity ? 1 : 0);
                        return true;
                    case 23:
                        parcel.enforceInterface(DESCRIPTOR);
                        onActivityCreated(parcel.readStrongBinder(), parcel.readStrongBinder(), parcel.readInt());
                        parcel2.writeNoException();
                        return true;
                    case 24:
                        parcel.enforceInterface(DESCRIPTOR);
                        onActivityResumed(parcel.readInt(), parcel.readStrongBinder());
                        parcel2.writeNoException();
                        return true;
                    case 25:
                        parcel.enforceInterface(DESCRIPTOR);
                        boolean onActivityDestroyed = onActivityDestroyed(parcel.readInt(), parcel.readStrongBinder());
                        parcel2.writeNoException();
                        parcel2.writeInt(onActivityDestroyed ? 1 : 0);
                        return true;
                    case 26:
                        parcel.enforceInterface(DESCRIPTOR);
                        onActivityFinish(parcel.readInt(), parcel.readStrongBinder());
                        parcel2.writeNoException();
                        return true;
                    case 27:
                        parcel.enforceInterface(DESCRIPTOR);
                        ComponentName activityClassForToken = getActivityClassForToken(parcel.readInt(), parcel.readStrongBinder());
                        parcel2.writeNoException();
                        if (activityClassForToken != null) {
                            parcel2.writeInt(1);
                            activityClassForToken.writeToParcel(parcel2, 1);
                        } else {
                            parcel2.writeInt(0);
                        }
                        return true;
                    case 28:
                        parcel.enforceInterface(DESCRIPTOR);
                        String callingPackage = getCallingPackage(parcel.readInt(), parcel.readStrongBinder());
                        parcel2.writeNoException();
                        parcel2.writeString(callingPackage);
                        return true;
                    case 29:
                        parcel.enforceInterface(DESCRIPTOR);
                        int callingUidByPid = getCallingUidByPid(parcel.readInt());
                        parcel2.writeNoException();
                        parcel2.writeInt(callingUidByPid);
                        return true;
                    case 30:
                        parcel.enforceInterface(DESCRIPTOR);
                        ComponentName callingActivity = getCallingActivity(parcel.readInt(), parcel.readStrongBinder());
                        parcel2.writeNoException();
                        if (callingActivity != null) {
                            parcel2.writeInt(1);
                            callingActivity.writeToParcel(parcel2, 1);
                        } else {
                            parcel2.writeInt(0);
                        }
                        return true;
                    case 31:
                        parcel.enforceInterface(DESCRIPTOR);
                        AppTaskInfo taskInfo = getTaskInfo(parcel.readInt());
                        parcel2.writeNoException();
                        if (taskInfo != null) {
                            parcel2.writeInt(1);
                            taskInfo.writeToParcel(parcel2, 1);
                        } else {
                            parcel2.writeInt(0);
                        }
                        return true;
                    case 32:
                        parcel.enforceInterface(DESCRIPTOR);
                        String packageForToken = getPackageForToken(parcel.readInt(), parcel.readStrongBinder());
                        parcel2.writeNoException();
                        parcel2.writeString(packageForToken);
                        return true;
                    case 33:
                        parcel.enforceInterface(DESCRIPTOR);
                        int readInt2 = parcel.readInt();
                        if (parcel.readInt() != 0) {
                            providerInfo = (ProviderInfo) ProviderInfo.CREATOR.createFromParcel(parcel);
                        }
                        IBinder acquireProviderClient = acquireProviderClient(readInt2, providerInfo);
                        parcel2.writeNoException();
                        parcel2.writeStrongBinder(acquireProviderClient);
                        return true;
                    case 34:
                        parcel.enforceInterface(DESCRIPTOR);
                        boolean broadcastFinish = broadcastFinish(parcel.readStrongBinder());
                        parcel2.writeNoException();
                        parcel2.writeInt(broadcastFinish ? 1 : 0);
                        return true;
                    case 35:
                        parcel.enforceInterface(DESCRIPTOR);
                        if (parcel.readInt() != 0) {
                            intentSenderData = IntentSenderData.CREATOR.createFromParcel(parcel);
                        }
                        addOrUpdateIntentSender(intentSenderData, parcel.readInt());
                        parcel2.writeNoException();
                        return true;
                    case 36:
                        parcel.enforceInterface(DESCRIPTOR);
                        removeIntentSender(parcel.readStrongBinder());
                        parcel2.writeNoException();
                        return true;
                    case 37:
                        parcel.enforceInterface(DESCRIPTOR);
                        IntentSenderData intentSender = getIntentSender(parcel.readStrongBinder());
                        parcel2.writeNoException();
                        if (intentSender != null) {
                            parcel2.writeInt(1);
                            intentSender.writeToParcel(parcel2, 1);
                        } else {
                            parcel2.writeInt(0);
                        }
                        return true;
                    case 38:
                        parcel.enforceInterface(DESCRIPTOR);
                        processRestarted(parcel.readString(), parcel.readString(), parcel.readInt());
                        parcel2.writeNoException();
                        return true;
                    case 39:
                        parcel.enforceInterface(DESCRIPTOR);
                        if (parcel.readInt() != 0) {
                            badgerInfo = BadgerInfo.CREATOR.createFromParcel(parcel);
                        }
                        notifyBadgerChange(badgerInfo);
                        parcel2.writeNoException();
                        return true;
                    case 40:
                        parcel.enforceInterface(DESCRIPTOR);
                        String readString2 = parcel.readString();
                        if (parcel.readInt() != 0) {
                            z = true;
                        }
                        setAppInactive(readString2, z, parcel.readInt());
                        parcel2.writeNoException();
                        return true;
                    case 41:
                        parcel.enforceInterface(DESCRIPTOR);
                        boolean isAppInactive = isAppInactive(parcel.readString(), parcel.readInt());
                        parcel2.writeNoException();
                        parcel2.writeInt(isAppInactive ? 1 : 0);
                        return true;
                    case 42:
                        parcel.enforceInterface(DESCRIPTOR);
                        boolean isVAServiceToken = isVAServiceToken(parcel.readStrongBinder());
                        parcel2.writeNoException();
                        parcel2.writeInt(isVAServiceToken ? 1 : 0);
                        return true;
                    case 43:
                        parcel.enforceInterface(DESCRIPTOR);
                        if (parcel.readInt() != 0) {
                            intent7 = (Intent) Intent.CREATOR.createFromParcel(parcel);
                        }
                        ComponentName startService = startService(intent7, parcel.readString(), parcel.readInt());
                        parcel2.writeNoException();
                        if (startService != null) {
                            parcel2.writeInt(1);
                            startService.writeToParcel(parcel2, 1);
                        } else {
                            parcel2.writeInt(0);
                        }
                        return true;
                    case 44:
                        parcel.enforceInterface(DESCRIPTOR);
                        IBinder readStrongBinder3 = parcel.readStrongBinder();
                        if (parcel.readInt() != 0) {
                            intent6 = (Intent) Intent.CREATOR.createFromParcel(parcel);
                        }
                        int stopService = stopService(readStrongBinder3, intent6, parcel.readString(), parcel.readInt());
                        parcel2.writeNoException();
                        parcel2.writeInt(stopService);
                        return true;
                    case 45:
                        parcel.enforceInterface(DESCRIPTOR);
                        if (parcel.readInt() != 0) {
                            componentName = (ComponentName) ComponentName.CREATOR.createFromParcel(parcel);
                        }
                        boolean stopServiceToken = stopServiceToken(componentName, parcel.readStrongBinder(), parcel.readInt(), parcel.readInt());
                        parcel2.writeNoException();
                        parcel2.writeInt(stopServiceToken ? 1 : 0);
                        return true;
                    case 46:
                        parcel.enforceInterface(DESCRIPTOR);
                        ComponentName componentName2 = parcel.readInt() != 0 ? (ComponentName) ComponentName.CREATOR.createFromParcel(parcel) : null;
                        IBinder readStrongBinder4 = parcel.readStrongBinder();
                        int readInt3 = parcel.readInt();
                        if (parcel.readInt() != 0) {
                            notification = (Notification) Notification.CREATOR.createFromParcel(parcel);
                        }
                        setServiceForeground(componentName2, readStrongBinder4, readInt3, notification, parcel.readInt() != 0, parcel.readInt());
                        parcel2.writeNoException();
                        return true;
                    case 47:
                        parcel.enforceInterface(DESCRIPTOR);
                        IBinder readStrongBinder5 = parcel.readStrongBinder();
                        IBinder readStrongBinder6 = parcel.readStrongBinder();
                        if (parcel.readInt() != 0) {
                            intent5 = (Intent) Intent.CREATOR.createFromParcel(parcel);
                        }
                        int bindService = bindService(readStrongBinder5, readStrongBinder6, intent5, parcel.readString(), IServiceConnection.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt());
                        parcel2.writeNoException();
                        parcel2.writeInt(bindService);
                        return true;
                    case 48:
                        parcel.enforceInterface(DESCRIPTOR);
                        boolean unbindService = unbindService(IServiceConnection.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                        parcel2.writeNoException();
                        parcel2.writeInt(unbindService ? 1 : 0);
                        return true;
                    case 49:
                        parcel.enforceInterface(DESCRIPTOR);
                        IBinder readStrongBinder7 = parcel.readStrongBinder();
                        if (parcel.readInt() != 0) {
                            intent4 = (Intent) Intent.CREATOR.createFromParcel(parcel);
                        }
                        if (parcel.readInt() != 0) {
                            z = true;
                        }
                        unbindFinished(readStrongBinder7, intent4, z, parcel.readInt());
                        parcel2.writeNoException();
                        return true;
                    case 50:
                        parcel.enforceInterface(DESCRIPTOR);
                        serviceDoneExecuting(parcel.readStrongBinder(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                        parcel2.writeNoException();
                        return true;
                    case 51:
                        parcel.enforceInterface(DESCRIPTOR);
                        if (parcel.readInt() != 0) {
                            intent3 = (Intent) Intent.CREATOR.createFromParcel(parcel);
                        }
                        IBinder peekService = peekService(intent3, parcel.readString(), parcel.readInt());
                        parcel2.writeNoException();
                        parcel2.writeStrongBinder(peekService);
                        return true;
                    case 52:
                        parcel.enforceInterface(DESCRIPTOR);
                        IBinder readStrongBinder8 = parcel.readStrongBinder();
                        if (parcel.readInt() != 0) {
                            intent2 = (Intent) Intent.CREATOR.createFromParcel(parcel);
                        }
                        publishService(readStrongBinder8, intent2, parcel.readStrongBinder(), parcel.readInt());
                        parcel2.writeNoException();
                        return true;
                    case 53:
                        parcel.enforceInterface(DESCRIPTOR);
                        VParceledListSlice services = getServices(parcel.readInt(), parcel.readInt(), parcel.readInt());
                        parcel2.writeNoException();
                        if (services != null) {
                            parcel2.writeInt(1);
                            services.writeToParcel(parcel2, 1);
                        } else {
                            parcel2.writeInt(0);
                        }
                        return true;
                    case 54:
                        parcel.enforceInterface(DESCRIPTOR);
                        if (parcel.readInt() != 0) {
                            intent = (Intent) Intent.CREATOR.createFromParcel(parcel);
                        }
                        handleDownloadCompleteIntent(intent);
                        parcel2.writeNoException();
                        return true;
                    default:
                        return super.onTransact(i, parcel, parcel2, i2);
                }
            } else {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Proxy implements IActivityManager {
            private IBinder mRemote;

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.lody.virtual.server.interfaces.IActivityManager
            public ClientConfig initProcess(String str, String str2, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeInt(i);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? ClientConfig.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.server.interfaces.IActivityManager
            public void appDoneExecuting(String str, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.server.interfaces.IActivityManager
            public int getFreeStubCount() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.server.interfaces.IActivityManager
            public int checkPermission(boolean z, String str, int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    this.mRemote.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.server.interfaces.IActivityManager
            public int getSystemPid() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.server.interfaces.IActivityManager
            public int getSystemUid() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.server.interfaces.IActivityManager
            public int getUidByPid(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    this.mRemote.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.server.interfaces.IActivityManager
            public boolean isAppProcess(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    boolean z = false;
                    this.mRemote.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.server.interfaces.IActivityManager
            public boolean isAppRunning(String str, int i, boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    boolean z2 = true;
                    obtain.writeInt(z ? 1 : 0);
                    this.mRemote.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() == 0) {
                        z2 = false;
                    }
                    return z2;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.server.interfaces.IActivityManager
            public boolean isAppPid(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    boolean z = false;
                    this.mRemote.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.server.interfaces.IActivityManager
            public String getAppProcessName(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    this.mRemote.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.server.interfaces.IActivityManager
            public List<String> getProcessPkgList(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    this.mRemote.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.createStringArrayList();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.server.interfaces.IActivityManager
            public void killAllApps() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(13, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.server.interfaces.IActivityManager
            public void killAppByPkg(String str, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    this.mRemote.transact(14, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.server.interfaces.IActivityManager
            public void finishAllActivities() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(15, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.server.interfaces.IActivityManager
            public void finishAllActivitiesByPkg(String str, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    this.mRemote.transact(16, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.server.interfaces.IActivityManager
            public void killApplicationProcess(String str, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    this.mRemote.transact(17, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.server.interfaces.IActivityManager
            public void dump() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(18, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.server.interfaces.IActivityManager
            public String getInitialPackage(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    this.mRemote.transact(19, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.server.interfaces.IActivityManager
            public int startActivities(Intent[] intentArr, String[] strArr, IBinder iBinder, Bundle bundle, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeTypedArray(intentArr, 0);
                    obtain.writeStringArray(strArr);
                    obtain.writeStrongBinder(iBinder);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeInt(i);
                    this.mRemote.transact(20, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.server.interfaces.IActivityManager
            public int startActivity(Intent intent, ActivityInfo activityInfo, IBinder iBinder, Bundle bundle, String str, int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (intent != null) {
                        obtain.writeInt(1);
                        intent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (activityInfo != null) {
                        obtain.writeInt(1);
                        activityInfo.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    this.mRemote.transact(21, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.server.interfaces.IActivityManager
            public boolean finishActivityAffinity(int i, IBinder iBinder) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeStrongBinder(iBinder);
                    boolean z = false;
                    this.mRemote.transact(22, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.server.interfaces.IActivityManager
            public void onActivityCreated(IBinder iBinder, IBinder iBinder2, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iBinder);
                    obtain.writeStrongBinder(iBinder2);
                    obtain.writeInt(i);
                    this.mRemote.transact(23, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.server.interfaces.IActivityManager
            public void onActivityResumed(int i, IBinder iBinder) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeStrongBinder(iBinder);
                    this.mRemote.transact(24, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.server.interfaces.IActivityManager
            public boolean onActivityDestroyed(int i, IBinder iBinder) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeStrongBinder(iBinder);
                    boolean z = false;
                    this.mRemote.transact(25, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.server.interfaces.IActivityManager
            public void onActivityFinish(int i, IBinder iBinder) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeStrongBinder(iBinder);
                    this.mRemote.transact(26, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.server.interfaces.IActivityManager
            public ComponentName getActivityClassForToken(int i, IBinder iBinder) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeStrongBinder(iBinder);
                    this.mRemote.transact(27, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (ComponentName) ComponentName.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.server.interfaces.IActivityManager
            public String getCallingPackage(int i, IBinder iBinder) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeStrongBinder(iBinder);
                    this.mRemote.transact(28, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.server.interfaces.IActivityManager
            public int getCallingUidByPid(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    this.mRemote.transact(29, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.server.interfaces.IActivityManager
            public ComponentName getCallingActivity(int i, IBinder iBinder) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeStrongBinder(iBinder);
                    this.mRemote.transact(30, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (ComponentName) ComponentName.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.server.interfaces.IActivityManager
            public AppTaskInfo getTaskInfo(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    this.mRemote.transact(31, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? AppTaskInfo.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.server.interfaces.IActivityManager
            public String getPackageForToken(int i, IBinder iBinder) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeStrongBinder(iBinder);
                    this.mRemote.transact(32, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.server.interfaces.IActivityManager
            public IBinder acquireProviderClient(int i, ProviderInfo providerInfo) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (providerInfo != null) {
                        obtain.writeInt(1);
                        providerInfo.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(33, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readStrongBinder();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.server.interfaces.IActivityManager
            public boolean broadcastFinish(IBinder iBinder) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iBinder);
                    boolean z = false;
                    this.mRemote.transact(34, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.server.interfaces.IActivityManager
            public void addOrUpdateIntentSender(IntentSenderData intentSenderData, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (intentSenderData != null) {
                        obtain.writeInt(1);
                        intentSenderData.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeInt(i);
                    this.mRemote.transact(35, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.server.interfaces.IActivityManager
            public void removeIntentSender(IBinder iBinder) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iBinder);
                    this.mRemote.transact(36, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.server.interfaces.IActivityManager
            public IntentSenderData getIntentSender(IBinder iBinder) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iBinder);
                    this.mRemote.transact(37, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? IntentSenderData.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.server.interfaces.IActivityManager
            public void processRestarted(String str, String str2, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeInt(i);
                    this.mRemote.transact(38, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.server.interfaces.IActivityManager
            public void notifyBadgerChange(BadgerInfo badgerInfo) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (badgerInfo != null) {
                        obtain.writeInt(1);
                        badgerInfo.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(39, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.server.interfaces.IActivityManager
            public void setAppInactive(String str, boolean z, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(z ? 1 : 0);
                    obtain.writeInt(i);
                    this.mRemote.transact(40, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.server.interfaces.IActivityManager
            public boolean isAppInactive(String str, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    boolean z = false;
                    this.mRemote.transact(41, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.server.interfaces.IActivityManager
            public boolean isVAServiceToken(IBinder iBinder) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iBinder);
                    boolean z = false;
                    this.mRemote.transact(42, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.server.interfaces.IActivityManager
            public ComponentName startService(Intent intent, String str, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (intent != null) {
                        obtain.writeInt(1);
                        intent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    this.mRemote.transact(43, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (ComponentName) ComponentName.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.server.interfaces.IActivityManager
            public int stopService(IBinder iBinder, Intent intent, String str, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iBinder);
                    if (intent != null) {
                        obtain.writeInt(1);
                        intent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    this.mRemote.transact(44, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.server.interfaces.IActivityManager
            public boolean stopServiceToken(ComponentName componentName, IBinder iBinder, int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean z = true;
                    if (componentName != null) {
                        obtain.writeInt(1);
                        componentName.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iBinder);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    this.mRemote.transact(45, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() == 0) {
                        z = false;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.server.interfaces.IActivityManager
            public void setServiceForeground(ComponentName componentName, IBinder iBinder, int i, Notification notification, boolean z, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    int i3 = 1;
                    if (componentName != null) {
                        obtain.writeInt(1);
                        componentName.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iBinder);
                    obtain.writeInt(i);
                    if (notification != null) {
                        obtain.writeInt(1);
                        notification.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!z) {
                        i3 = 0;
                    }
                    obtain.writeInt(i3);
                    obtain.writeInt(i2);
                    this.mRemote.transact(46, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.server.interfaces.IActivityManager
            public int bindService(IBinder iBinder, IBinder iBinder2, Intent intent, String str, IServiceConnection iServiceConnection, int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iBinder);
                    obtain.writeStrongBinder(iBinder2);
                    if (intent != null) {
                        obtain.writeInt(1);
                        intent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(str);
                    obtain.writeStrongBinder(iServiceConnection != null ? iServiceConnection.asBinder() : null);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    this.mRemote.transact(47, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.server.interfaces.IActivityManager
            public boolean unbindService(IServiceConnection iServiceConnection, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iServiceConnection != null ? iServiceConnection.asBinder() : null);
                    obtain.writeInt(i);
                    boolean z = false;
                    this.mRemote.transact(48, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.server.interfaces.IActivityManager
            public void unbindFinished(IBinder iBinder, Intent intent, boolean z, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iBinder);
                    int i2 = 1;
                    if (intent != null) {
                        obtain.writeInt(1);
                        intent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!z) {
                        i2 = 0;
                    }
                    obtain.writeInt(i2);
                    obtain.writeInt(i);
                    this.mRemote.transact(49, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.server.interfaces.IActivityManager
            public void serviceDoneExecuting(IBinder iBinder, int i, int i2, int i3, int i4) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iBinder);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeInt(i3);
                    obtain.writeInt(i4);
                    this.mRemote.transact(50, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.server.interfaces.IActivityManager
            public IBinder peekService(Intent intent, String str, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (intent != null) {
                        obtain.writeInt(1);
                        intent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    this.mRemote.transact(51, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readStrongBinder();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.server.interfaces.IActivityManager
            public void publishService(IBinder iBinder, Intent intent, IBinder iBinder2, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iBinder);
                    if (intent != null) {
                        obtain.writeInt(1);
                        intent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(iBinder2);
                    obtain.writeInt(i);
                    this.mRemote.transact(52, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.server.interfaces.IActivityManager
            public VParceledListSlice getServices(int i, int i2, int i3) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeInt(i3);
                    this.mRemote.transact(53, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? VParceledListSlice.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.lody.virtual.server.interfaces.IActivityManager
            public void handleDownloadCompleteIntent(Intent intent) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (intent != null) {
                        obtain.writeInt(1);
                        intent.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(54, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
