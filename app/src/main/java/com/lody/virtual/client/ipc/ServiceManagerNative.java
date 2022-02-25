package com.lody.virtual.client.ipc;

import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import com.lody.virtual.client.core.VirtualCore;
import com.lody.virtual.client.ipc.ProviderCall;
import com.lody.virtual.helper.compat.BundleCompat;
import com.lody.virtual.helper.utils.VLog;
import com.lody.virtual.server.ServiceCache;
import com.lody.virtual.server.interfaces.IServiceFetcher;

/* loaded from: classes.dex */
public class ServiceManagerNative {
    public static final String ACCOUNT = "account";
    public static final String ACTIVITY = "activity";
    public static final String APP = "app";
    public static final String CONTENT = "content";
    public static final String DEVICE = "device";
    public static final String JOB = "job";
    public static final String NOTIFICATION = "notification";
    public static final String PACKAGE = "package";
    private static final String TAG = "ServiceManagerNative";
    public static final String USER = "user";
    public static final String VIRTUAL_LOC = "virtual-loc";

    /* renamed from: VS */
    public static final String f10497VS = "vs";
    private static IServiceFetcher sFetcher;

    private static String getAuthority() {
        return VirtualCore.getConfig().getBinderProviderAuthority();
    }

    private static IServiceFetcher getServiceFetcher() {
        IServiceFetcher iServiceFetcher = sFetcher;
        if (iServiceFetcher == null || !iServiceFetcher.asBinder().isBinderAlive()) {
            synchronized (ServiceManagerNative.class) {
                Bundle callSafely = new ProviderCall.Builder(VirtualCore.get().getContext(), getAuthority()).methodName("@").callSafely();
                if (callSafely != null) {
                    IBinder binder = BundleCompat.getBinder(callSafely, "_VA_|_binder_");
                    linkBinderDied(binder);
                    sFetcher = IServiceFetcher.Stub.asInterface(binder);
                }
            }
        }
        return sFetcher;
    }

    public static void ensureServerStarted() {
        new ProviderCall.Builder(VirtualCore.get().getContext(), getAuthority()).methodName("ensure_created").callSafely();
    }

    public static void clearServerFetcher() {
        sFetcher = null;
    }

    private static void linkBinderDied(final IBinder iBinder) {
        try {
            iBinder.linkToDeath(new IBinder.DeathRecipient() { // from class: com.lody.virtual.client.ipc.ServiceManagerNative.1
                @Override // android.os.IBinder.DeathRecipient
                public void binderDied() {
                    iBinder.unlinkToDeath(this, 0);
                }
            }, 0);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public static IBinder getService(String str) {
        if (VirtualCore.get().isServerProcess()) {
            return ServiceCache.getService(str);
        }
        IServiceFetcher serviceFetcher = getServiceFetcher();
        if (serviceFetcher != null) {
            try {
                return serviceFetcher.getService(str);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        VLog.m18991e(TAG, "GetService(%s) return null.", str);
        return null;
    }

    public static void addService(String str, IBinder iBinder) {
        IServiceFetcher serviceFetcher = getServiceFetcher();
        if (serviceFetcher != null) {
            try {
                serviceFetcher.addService(str, iBinder);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public static void removeService(String str) {
        IServiceFetcher serviceFetcher = getServiceFetcher();
        if (serviceFetcher != null) {
            try {
                serviceFetcher.removeService(str);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
}
