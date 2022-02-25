package com.alipay.mobilesecuritysdk.face;

import android.content.Context;
import com.alipay.apmobilesecuritysdk.face.APSecuritySdk;
import com.alipay.apmobilesecuritysdk.p011a.C0621a;
import com.lody.virtual.client.stub.ChooseTypeAndAccountActivity;
import java.util.HashMap;
import java.util.Map;
import p110z1.C3877as;
import p110z1.C5097cq;

/* loaded from: classes.dex */
public class SecurityClientMobile {
    public static synchronized String GetApdid(Context context, Map<String, String> map) {
        String a;
        synchronized (SecurityClientMobile.class) {
            HashMap hashMap = new HashMap();
            hashMap.put(C3877as.f17455g, C5097cq.m3696a(map, C3877as.f17455g, ""));
            hashMap.put("tid", C5097cq.m3696a(map, "tid", ""));
            hashMap.put(ChooseTypeAndAccountActivity.KEY_USER_ID, C5097cq.m3696a(map, ChooseTypeAndAccountActivity.KEY_USER_ID, ""));
            APSecuritySdk.getInstance(context).initToken(0, hashMap, null);
            a = C0621a.m25440a(context);
        }
        return a;
    }
}
