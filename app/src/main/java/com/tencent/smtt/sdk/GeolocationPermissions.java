package com.tencent.smtt.sdk;

import java.util.Set;

/* loaded from: classes2.dex */
public class GeolocationPermissions {

    /* renamed from: a */
    private static GeolocationPermissions f12762a;

    public static GeolocationPermissions getInstance() {
        return m17073a();
    }

    /* renamed from: a */
    private static synchronized GeolocationPermissions m17073a() {
        GeolocationPermissions geolocationPermissions;
        synchronized (GeolocationPermissions.class) {
            if (f12762a == null) {
                f12762a = new GeolocationPermissions();
            }
            geolocationPermissions = f12762a;
        }
        return geolocationPermissions;
    }

    public void getOrigins(ValueCallback<Set<String>> valueCallback) {
        X5CoreEngine a = X5CoreEngine.m16621a();
        if (a == null || !a.m16618b()) {
            android.webkit.GeolocationPermissions.getInstance().getOrigins(valueCallback);
        } else {
            a.m16616c().m16596b(valueCallback);
        }
    }

    public void getAllowed(String str, ValueCallback<Boolean> valueCallback) {
        X5CoreEngine a = X5CoreEngine.m16621a();
        if (a == null || !a.m16618b()) {
            android.webkit.GeolocationPermissions.getInstance().getAllowed(str, valueCallback);
        } else {
            a.m16616c().m16589c(str, valueCallback);
        }
    }

    public void clear(String str) {
        X5CoreEngine a = X5CoreEngine.m16621a();
        if (a == null || !a.m16618b()) {
            android.webkit.GeolocationPermissions.getInstance().clear(str);
        } else {
            a.m16616c().m16580f(str);
        }
    }

    public void allow(String str) {
        X5CoreEngine a = X5CoreEngine.m16621a();
        if (a == null || !a.m16618b()) {
            android.webkit.GeolocationPermissions.getInstance().allow(str);
        } else {
            a.m16616c().m16577g(str);
        }
    }

    public void clearAll() {
        X5CoreEngine a = X5CoreEngine.m16621a();
        if (a == null || !a.m16618b()) {
            android.webkit.GeolocationPermissions.getInstance().clearAll();
        } else {
            a.m16616c().m16560o();
        }
    }
}
