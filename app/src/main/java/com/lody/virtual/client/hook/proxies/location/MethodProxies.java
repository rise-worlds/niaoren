package com.lody.virtual.client.hook.proxies.location;

import android.location.LocationRequest;
import android.os.Build;
import com.lody.virtual.client.hook.annotations.SkipInject;
import com.lody.virtual.client.hook.base.MethodProxy;
import com.lody.virtual.client.hook.base.ReplaceLastPkgMethodProxy;
import com.lody.virtual.client.ipc.VLocationManager;
import com.lody.virtual.helper.utils.Reflect;
import com.lody.virtual.remote.vloc.VLocation;
import java.lang.reflect.Method;
import java.util.Arrays;
import p110z1.LocationRequestL;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class MethodProxies {
    MethodProxies() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void fixLocationRequest(LocationRequest locationRequest) {
        if (locationRequest != null) {
            if (LocationRequestL.mHideFromAppOps != null) {
                LocationRequestL.mHideFromAppOps.set(locationRequest, false);
            }
            if (LocationRequestL.mWorkSource != null) {
                LocationRequestL.mWorkSource.set(locationRequest, null);
            }
        }
    }

    @SkipInject
    /* loaded from: classes.dex */
    static class AddGpsStatusListener extends ReplaceLastPkgMethodProxy {
        public AddGpsStatusListener() {
            super("addGpsStatusListener");
        }

        public AddGpsStatusListener(String str) {
            super(str);
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            if (!isFakeLocationEnable()) {
                return super.call(obj, method, objArr);
            }
            VLocationManager.get().addGpsStatusListener(objArr);
            return true;
        }
    }

    @SkipInject
    /* loaded from: classes.dex */
    static class RequestLocationUpdates extends ReplaceLastPkgMethodProxy {
        public RequestLocationUpdates() {
            super("requestLocationUpdates");
        }

        public RequestLocationUpdates(String str) {
            super(str);
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            if (isFakeLocationEnable()) {
                VLocationManager.get().requestLocationUpdates(objArr);
                return 0;
            }
            if (Build.VERSION.SDK_INT > 16) {
                MethodProxies.fixLocationRequest((LocationRequest) objArr[0]);
            }
            return super.call(obj, method, objArr);
        }
    }

    @SkipInject
    /* loaded from: classes.dex */
    static class RemoveUpdates extends ReplaceLastPkgMethodProxy {
        public RemoveUpdates() {
            super("removeUpdates");
        }

        public RemoveUpdates(String str) {
            super(str);
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            if (!isFakeLocationEnable()) {
                return super.call(obj, method, objArr);
            }
            VLocationManager.get().removeUpdates(objArr);
            return 0;
        }
    }

    @SkipInject
    /* loaded from: classes.dex */
    static class GetLastLocation extends ReplaceLastPkgMethodProxy {
        public GetLastLocation() {
            super("getLastLocation");
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            if (!(objArr[0] instanceof String)) {
                MethodProxies.fixLocationRequest((LocationRequest) objArr[0]);
            }
            if (!isFakeLocationEnable()) {
                return super.call(obj, method, objArr);
            }
            VLocation location = VLocationManager.get().getLocation(getAppPkg(), getAppUserId());
            if (location != null) {
                return location.toSysLocation();
            }
            return null;
        }
    }

    @SkipInject
    /* loaded from: classes.dex */
    static class GetLastKnownLocation extends GetLastLocation {
        @Override // com.lody.virtual.client.hook.base.StaticMethodProxy, com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "getLastKnownLocation";
        }

        @Override // com.lody.virtual.client.hook.proxies.location.MethodProxies.GetLastLocation, com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            if (!isFakeLocationEnable()) {
                return super.call(obj, method, objArr);
            }
            VLocation location = VLocationManager.get().getLocation(getAppPkg(), getAppUserId());
            if (location != null) {
                return location.toSysLocation();
            }
            return null;
        }
    }

    @SkipInject
    /* loaded from: classes.dex */
    static class IsProviderEnabled extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "isProviderEnabled";
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            if (!isFakeLocationEnable() || !(objArr[0] instanceof String)) {
                return super.call(obj, method, objArr);
            }
            return Boolean.valueOf(VLocationManager.get().isProviderEnabled((String) objArr[0]));
        }
    }

    /* loaded from: classes.dex */
    private static class getAllProviders extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "getAllProviders";
        }

        private getAllProviders() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            if (isFakeLocationEnable()) {
                return Arrays.asList("gps", "network");
            }
            return super.call(obj, method, objArr);
        }
    }

    @SkipInject
    /* loaded from: classes.dex */
    static class GetBestProvider extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "getBestProvider";
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            return isFakeLocationEnable() ? "gps" : super.call(obj, method, objArr);
        }
    }

    @SkipInject
    /* loaded from: classes.dex */
    static class RemoveGpsStatusListener extends ReplaceLastPkgMethodProxy {
        public RemoveGpsStatusListener() {
            super("removeGpsStatusListener");
        }

        public RemoveGpsStatusListener(String str) {
            super(str);
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            if (!isFakeLocationEnable()) {
                return super.call(obj, method, objArr);
            }
            VLocationManager.get().removeGpsStatusListener(objArr);
            return 0;
        }
    }

    @SkipInject
    /* loaded from: classes.dex */
    static class RequestLocationUpdatesPI extends RequestLocationUpdates {
        public RequestLocationUpdatesPI() {
            super("requestLocationUpdatesPI");
        }
    }

    @SkipInject
    /* loaded from: classes.dex */
    static class RemoveUpdatesPI extends RemoveUpdates {
        public RemoveUpdatesPI() {
            super("removeUpdatesPI");
        }
    }

    @SkipInject
    /* loaded from: classes.dex */
    static class UnregisterGnssStatusCallback extends RemoveGpsStatusListener {
        public UnregisterGnssStatusCallback() {
            super("unregisterGnssStatusCallback");
        }
    }

    @SkipInject
    /* loaded from: classes.dex */
    static class RegisterGnssStatusCallback extends AddGpsStatusListener {
        public RegisterGnssStatusCallback() {
            super("registerGnssStatusCallback");
        }
    }

    /* loaded from: classes.dex */
    static class sendExtraCommand extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "sendExtraCommand";
        }

        sendExtraCommand() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            if (isFakeLocationEnable()) {
                return true;
            }
            return super.call(obj, method, objArr);
        }
    }

    /* loaded from: classes.dex */
    static class getProviderProperties extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "getProviderProperties";
        }

        getProviderProperties() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object afterCall(Object obj, Method method, Object[] objArr, Object obj2) throws Throwable {
            if (!isFakeLocationEnable()) {
                return super.afterCall(obj, method, objArr, obj2);
            }
            try {
                Reflect.m18998on(obj2).set("mRequiresNetwork", false);
                Reflect.m18998on(obj2).set("mRequiresCell", false);
            } catch (Throwable th) {
                th.printStackTrace();
            }
            return obj2;
        }
    }

    /* loaded from: classes.dex */
    static class locationCallbackFinished extends MethodProxy {
        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public String getMethodName() {
            return "locationCallbackFinished";
        }

        locationCallbackFinished() {
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            return super.call(obj, method, objArr);
        }
    }
}
