package com.lody.virtual.client.hook.proxies.location;

import android.location.LocationManager;
import android.net.http.Headers;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.os.IInterface;
import com.lody.virtual.client.hook.annotations.Inject;
import com.lody.virtual.client.hook.base.BinderInvocationStub;
import com.lody.virtual.client.hook.base.MethodInvocationProxy;
import com.lody.virtual.client.hook.base.ReplaceLastPkgMethodProxy;
import com.lody.virtual.client.hook.base.ReplaceLastUserIdMethodProxy;
import com.lody.virtual.client.hook.base.StaticMethodProxy;
import com.lody.virtual.client.hook.proxies.location.MethodProxies;
import com.lody.virtual.helper.compat.BuildCompat;
import com.lody.virtual.helper.utils.Reflect;
import com.lody.virtual.helper.utils.ReflectException;
import java.lang.reflect.Method;
import p110z1.ILocationManager;
import p110z1.ServiceManager;

@Inject(MethodProxies.class)
/* loaded from: classes.dex */
public class LocationManagerStub extends MethodInvocationProxy<BinderInvocationStub> {
    @Override // com.lody.virtual.client.interfaces.IInjector
    public boolean isEnvBad() {
        return false;
    }

    public LocationManagerStub() {
        super(new BinderInvocationStub(getInterface()));
    }

    private static IInterface getInterface() {
        IBinder call = ServiceManager.getService.call(Headers.LOCATION);
        if (call instanceof Binder) {
            try {
                return (IInterface) Reflect.m18998on(call).get("mILocationManager");
            } catch (ReflectException e) {
                e.printStackTrace();
            }
        }
        return ILocationManager.C5155a.asInterface.call(call);
    }

    @Override // com.lody.virtual.client.hook.base.MethodInvocationProxy, com.lody.virtual.client.interfaces.IInjector
    public void inject() {
        LocationManager locationManager = (LocationManager) getContext().getSystemService(Headers.LOCATION);
        IInterface iInterface = p110z1.LocationManager.mService.get(locationManager);
        if (iInterface instanceof Binder) {
            Reflect.m18998on(iInterface).set("mILocationManager", getInvocationStub().getProxyInterface());
        }
        p110z1.LocationManager.mService.set(locationManager, getInvocationStub().getProxyInterface());
        getInvocationStub().replaceService(Headers.LOCATION);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.lody.virtual.client.hook.base.MethodInvocationProxy
    public void onBindMethods() {
        super.onBindMethods();
        if (Build.VERSION.SDK_INT >= 23) {
            addMethodProxy(new ReplaceLastPkgMethodProxy("addTestProvider"));
            addMethodProxy(new ReplaceLastPkgMethodProxy("removeTestProvider"));
            addMethodProxy(new ReplaceLastPkgMethodProxy("setTestProviderLocation"));
            addMethodProxy(new ReplaceLastPkgMethodProxy("clearTestProviderLocation"));
            addMethodProxy(new ReplaceLastPkgMethodProxy("setTestProviderEnabled"));
            addMethodProxy(new ReplaceLastPkgMethodProxy("clearTestProviderEnabled"));
            addMethodProxy(new ReplaceLastPkgMethodProxy("setTestProviderStatus"));
            addMethodProxy(new ReplaceLastPkgMethodProxy("clearTestProviderStatus"));
        }
        if (Build.VERSION.SDK_INT >= 21) {
            addMethodProxy(new FakeReplaceLastPkgMethodProxy("addGpsMeasurementListener", true));
            addMethodProxy(new FakeReplaceLastPkgMethodProxy("addGpsNavigationMessageListener", true));
            addMethodProxy(new FakeReplaceLastPkgMethodProxy("removeGpsMeasurementListener", 0));
            addMethodProxy(new FakeReplaceLastPkgMethodProxy("removeGpsNavigationMessageListener", 0));
        }
        if (Build.VERSION.SDK_INT >= 17) {
            addMethodProxy(new FakeReplaceLastPkgMethodProxy("requestGeofence", 0));
            addMethodProxy(new FakeReplaceLastPkgMethodProxy("removeGeofence", 0));
        }
        if (Build.VERSION.SDK_INT <= 16) {
            addMethodProxy(new MethodProxies.GetLastKnownLocation());
            addMethodProxy(new FakeReplaceLastPkgMethodProxy("addProximityAlert", 0));
        }
        if (Build.VERSION.SDK_INT <= 16) {
            addMethodProxy(new MethodProxies.RequestLocationUpdatesPI());
            addMethodProxy(new MethodProxies.RemoveUpdatesPI());
        }
        if (Build.VERSION.SDK_INT >= 16) {
            addMethodProxy(new MethodProxies.RequestLocationUpdates());
            addMethodProxy(new MethodProxies.RemoveUpdates());
        }
        addMethodProxy(new MethodProxies.IsProviderEnabled());
        addMethodProxy(new MethodProxies.GetBestProvider());
        if (Build.VERSION.SDK_INT >= 17) {
            addMethodProxy(new MethodProxies.GetLastLocation());
            addMethodProxy(new MethodProxies.AddGpsStatusListener());
            addMethodProxy(new MethodProxies.RemoveGpsStatusListener());
            addMethodProxy(new FakeReplaceLastPkgMethodProxy("addNmeaListener", 0));
            addMethodProxy(new FakeReplaceLastPkgMethodProxy("removeNmeaListener", 0));
        }
        if (Build.VERSION.SDK_INT >= 24) {
            addMethodProxy(new MethodProxies.RegisterGnssStatusCallback());
            addMethodProxy(new MethodProxies.UnregisterGnssStatusCallback());
        }
        addMethodProxy(new ReplaceLastUserIdMethodProxy("isProviderEnabledForUser"));
        addMethodProxy(new ReplaceLastUserIdMethodProxy("isLocationEnabledForUser"));
        if (BuildCompat.isQ()) {
            addMethodProxy(new StaticMethodProxy("setLocationControllerExtraPackageEnabled") { // from class: com.lody.virtual.client.hook.proxies.location.LocationManagerStub.1
                @Override // com.lody.virtual.client.hook.base.MethodProxy
                public Object call(Object obj, Method method, Object... objArr) throws Throwable {
                    return null;
                }
            });
            addMethodProxy(new StaticMethodProxy("setExtraLocationControllerPackageEnabled") { // from class: com.lody.virtual.client.hook.proxies.location.LocationManagerStub.2
                @Override // com.lody.virtual.client.hook.base.MethodProxy
                public Object call(Object obj, Method method, Object... objArr) throws Throwable {
                    return null;
                }
            });
        }
    }

    /* loaded from: classes.dex */
    private static class FakeReplaceLastPkgMethodProxy extends ReplaceLastPkgMethodProxy {
        private Object mDefValue;

        private FakeReplaceLastPkgMethodProxy(String str, Object obj) {
            super(str);
            this.mDefValue = obj;
        }

        @Override // com.lody.virtual.client.hook.base.MethodProxy
        public Object call(Object obj, Method method, Object... objArr) throws Throwable {
            if (isFakeLocationEnable()) {
                return this.mDefValue;
            }
            return super.call(obj, method, objArr);
        }
    }
}
