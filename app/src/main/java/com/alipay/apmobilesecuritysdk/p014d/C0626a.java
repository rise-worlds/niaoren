package com.alipay.apmobilesecuritysdk.p014d;

import android.content.Context;
import java.util.HashMap;
import java.util.Map;
import p110z1.C5097cq;
import p110z1.C5151cv;

/* renamed from: com.alipay.apmobilesecuritysdk.d.a */
/* loaded from: classes.dex */
public final class C0626a {
    /* renamed from: a */
    public static synchronized Map<String, String> m25424a(Context context, Map<String, String> map) {
        HashMap hashMap;
        synchronized (C0626a.class) {
            String a = C5097cq.m3696a(map, "appchannel", "");
            hashMap = new HashMap();
            hashMap.put("AA1", context.getPackageName());
            C5151cv.m3514a();
            hashMap.put("AA2", C5151cv.m3513a(context));
            hashMap.put("AA3", "APPSecuritySDK-ALIPAYSDK");
            hashMap.put("AA4", "3.4.0.201910161639");
            hashMap.put("AA6", a);
        }
        return hashMap;
    }
}
