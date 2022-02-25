package com.lody.virtual.helper.compat;

import android.content.Intent;
import android.content.pm.ServiceInfo;
import android.os.Build;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import java.util.ArrayList;
import p110z1.CompatibilityInfo;
import p110z1.IApplicationThread;
import p110z1.IApplicationThreadICSMR1;
import p110z1.IApplicationThreadKitkat;
import p110z1.IApplicationThreadOreo;
import p110z1.ServiceStartArgs;

/* loaded from: classes.dex */
public class IApplicationThreadCompat {
    public static void scheduleCreateService(IInterface iInterface, IBinder iBinder, ServiceInfo serviceInfo) throws RemoteException {
        if (Build.VERSION.SDK_INT >= 19) {
            IApplicationThreadKitkat.scheduleCreateService.call(iInterface, iBinder, serviceInfo, CompatibilityInfo.DEFAULT_COMPATIBILITY_INFO.get(), 0);
        } else if (Build.VERSION.SDK_INT >= 15) {
            IApplicationThreadICSMR1.scheduleCreateService.call(iInterface, iBinder, serviceInfo, CompatibilityInfo.DEFAULT_COMPATIBILITY_INFO.get());
        } else {
            IApplicationThread.scheduleCreateService.call(iInterface, iBinder, serviceInfo);
        }
    }

    public static void scheduleBindService(IInterface iInterface, IBinder iBinder, Intent intent, boolean z) throws RemoteException {
        if (Build.VERSION.SDK_INT >= 19) {
            IApplicationThreadKitkat.scheduleBindService.call(iInterface, iBinder, intent, Boolean.valueOf(z), 0);
        } else {
            IApplicationThread.scheduleBindService.call(iInterface, iBinder, intent, Boolean.valueOf(z));
        }
    }

    public static void scheduleUnbindService(IInterface iInterface, IBinder iBinder, Intent intent) throws RemoteException {
        IApplicationThread.scheduleUnbindService.call(iInterface, iBinder, intent);
    }

    public static void scheduleServiceArgs(IInterface iInterface, IBinder iBinder, int i, Intent intent) throws RemoteException {
        if (BuildCompat.isOreo()) {
            ArrayList arrayList = new ArrayList(1);
            arrayList.add(ServiceStartArgs.ctor.newInstance(false, Integer.valueOf(i), 0, intent));
            IApplicationThreadOreo.scheduleServiceArgs.call(iInterface, iBinder, ParceledListSliceCompat.create(arrayList));
        } else if (Build.VERSION.SDK_INT >= 15) {
            IApplicationThreadICSMR1.scheduleServiceArgs.call(iInterface, iBinder, false, Integer.valueOf(i), 0, intent);
        } else {
            IApplicationThread.scheduleServiceArgs.call(iInterface, iBinder, Integer.valueOf(i), 0, intent);
        }
    }

    public static void scheduleStopService(IInterface iInterface, IBinder iBinder) throws RemoteException {
        IApplicationThread.scheduleStopService.call(iInterface, iBinder);
    }
}
