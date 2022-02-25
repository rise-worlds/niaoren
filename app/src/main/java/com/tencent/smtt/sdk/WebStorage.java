package com.tencent.smtt.sdk;

import java.util.Map;

/* loaded from: classes2.dex */
public class WebStorage {

    /* renamed from: a */
    private static WebStorage f13013a;

    @Deprecated
    /* loaded from: classes2.dex */
    public interface QuotaUpdater {
        void updateQuota(long j);
    }

    public void getOrigins(ValueCallback<Map> valueCallback) {
        X5CoreEngine a = X5CoreEngine.m16621a();
        if (a == null || !a.m16618b()) {
            android.webkit.WebStorage.getInstance().getOrigins(valueCallback);
        } else {
            a.m16616c().m16608a(valueCallback);
        }
    }

    public void getUsageForOrigin(String str, ValueCallback<Long> valueCallback) {
        X5CoreEngine a = X5CoreEngine.m16621a();
        if (a == null || !a.m16618b()) {
            android.webkit.WebStorage.getInstance().getUsageForOrigin(str, valueCallback);
        } else {
            a.m16616c().m16605a(str, valueCallback);
        }
    }

    public void getQuotaForOrigin(String str, ValueCallback<Long> valueCallback) {
        X5CoreEngine a = X5CoreEngine.m16621a();
        if (a == null || !a.m16618b()) {
            android.webkit.WebStorage.getInstance().getQuotaForOrigin(str, valueCallback);
        } else {
            a.m16616c().m16594b(str, valueCallback);
        }
    }

    @Deprecated
    public void setQuotaForOrigin(String str, long j) {
        X5CoreEngine a = X5CoreEngine.m16621a();
        if (a == null || !a.m16618b()) {
            android.webkit.WebStorage.getInstance().setQuotaForOrigin(str, j);
        } else {
            a.m16616c().m16606a(str, j);
        }
    }

    public void deleteOrigin(String str) {
        X5CoreEngine a = X5CoreEngine.m16621a();
        if (a == null || !a.m16618b()) {
            android.webkit.WebStorage.getInstance().deleteOrigin(str);
        } else {
            a.m16616c().m16583e(str);
        }
    }

    public void deleteAllData() {
        X5CoreEngine a = X5CoreEngine.m16621a();
        if (a == null || !a.m16618b()) {
            android.webkit.WebStorage.getInstance().deleteAllData();
        } else {
            a.m16616c().m16562n();
        }
    }

    public static WebStorage getInstance() {
        return m16936a();
    }

    /* renamed from: a */
    private static synchronized WebStorage m16936a() {
        WebStorage webStorage;
        synchronized (WebStorage.class) {
            if (f13013a == null) {
                f13013a = new WebStorage();
            }
            webStorage = f13013a;
        }
        return webStorage;
    }
}
