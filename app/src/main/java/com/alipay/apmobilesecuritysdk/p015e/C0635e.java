package com.alipay.apmobilesecuritysdk.p015e;

import android.content.Context;
import com.alipay.apmobilesecuritysdk.p013c.C0623a;
import com.alipay.apmobilesecuritysdk.p016f.C0640a;
import org.apache.tools.ant.taskdefs.condition.C3209Os;
import org.json.JSONObject;
import p110z1.C5097cq;

/* renamed from: com.alipay.apmobilesecuritysdk.e.e */
/* loaded from: classes.dex */
public final class C0635e {
    /* renamed from: a */
    public static C0636f m25400a(Context context) {
        if (context == null) {
            return null;
        }
        String a = C0640a.m25350a(context, "device_feature_prefs_name", "device_feature_prefs_key");
        if (C5097cq.m3699a(a)) {
            a = C0640a.m25348a("device_feature_file_name", "device_feature_file_key");
        }
        if (C5097cq.m3699a(a)) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(a);
            C0636f fVar = new C0636f();
            fVar.m25398a(jSONObject.getString("imei"));
            fVar.m25396b(jSONObject.getString("imsi"));
            fVar.m25394c(jSONObject.getString(C3209Os.FAMILY_MAC));
            fVar.m25392d(jSONObject.getString("bluetoothmac"));
            fVar.m25390e(jSONObject.getString("gsi"));
            return fVar;
        } catch (Exception e) {
            C0623a.m25428a(e);
            return null;
        }
    }
}
