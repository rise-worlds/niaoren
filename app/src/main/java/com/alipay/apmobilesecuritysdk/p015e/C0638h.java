package com.alipay.apmobilesecuritysdk.p015e;

import android.content.Context;
import android.content.SharedPreferences;
import com.cyjh.ddy.media.p035a.ResultTypeConstant;
import java.util.UUID;
import org.apache.tools.ant.taskdefs.WaitFor;
import p110z1.C5097cq;
import p110z1.C5116cs;
import p110z1.C5210cz;
import p110z1.C5258dd;

/* renamed from: com.alipay.apmobilesecuritysdk.e.h */
/* loaded from: classes.dex */
public class C0638h {

    /* renamed from: a */
    private static String f235a = "";

    /* renamed from: a */
    public static long m25385a(Context context) {
        String a = C5210cz.m3450a(context, "vkeyid_settings", "update_time_interval");
        if (!C5097cq.m3695b(a)) {
            return WaitFor.ONE_DAY;
        }
        try {
            return Long.parseLong(a);
        } catch (Exception unused) {
            return WaitFor.ONE_DAY;
        }
    }

    /* renamed from: a */
    public static void m25384a(Context context, String str) {
        m25382a(context, "update_time_interval", str);
    }

    /* renamed from: a */
    public static void m25383a(Context context, String str, long j) {
        C5210cz.m3449a(context, "vkeyid_settings", "vkey_valid" + str, String.valueOf(j));
    }

    /* renamed from: a */
    private static void m25382a(Context context, String str, String str2) {
        C5210cz.m3449a(context, "vkeyid_settings", str, str2);
    }

    /* renamed from: a */
    public static void m25381a(Context context, boolean z) {
        m25382a(context, "log_switch", z ? "1" : ResultTypeConstant.f7213z);
    }

    /* renamed from: b */
    public static String m25380b(Context context) {
        return C5210cz.m3450a(context, "vkeyid_settings", "last_apdid_env");
    }

    /* renamed from: b */
    public static void m25379b(Context context, String str) {
        m25382a(context, "last_machine_boot_time", str);
    }

    /* renamed from: c */
    public static void m25377c(Context context, String str) {
        m25382a(context, "last_apdid_env", str);
    }

    /* renamed from: c */
    public static boolean m25378c(Context context) {
        String a = C5210cz.m3450a(context, "vkeyid_settings", "log_switch");
        return a != null && "1".equals(a);
    }

    /* renamed from: d */
    public static String m25376d(Context context) {
        return C5210cz.m3450a(context, "vkeyid_settings", "dynamic_key");
    }

    /* renamed from: d */
    public static void m25375d(Context context, String str) {
        m25382a(context, "agent_switch", str);
    }

    /* renamed from: e */
    public static String m25374e(Context context) {
        return C5210cz.m3450a(context, "vkeyid_settings", "apse_degrade");
    }

    /* renamed from: e */
    public static void m25373e(Context context, String str) {
        m25382a(context, "dynamic_key", str);
    }

    /* renamed from: f */
    public static String m25372f(Context context) {
        String str;
        SharedPreferences.Editor edit;
        synchronized (C0638h.class) {
            if (C5097cq.m3699a(f235a)) {
                String a = C5258dd.m3228a(context, "alipay_vkey_random", "random", "");
                f235a = a;
                if (C5097cq.m3699a(a)) {
                    f235a = C5116cs.m3522a(UUID.randomUUID().toString());
                    String str2 = f235a;
                    if (!(str2 == null || (edit = context.getSharedPreferences("alipay_vkey_random", 0).edit()) == null)) {
                        edit.putString("random", str2);
                        edit.commit();
                    }
                }
            }
            str = f235a;
        }
        return str;
    }

    /* renamed from: f */
    public static void m25371f(Context context, String str) {
        m25382a(context, "webrtc_url", str);
    }

    /* renamed from: g */
    public static void m25370g(Context context, String str) {
        m25382a(context, "apse_degrade", str);
    }

    /* renamed from: h */
    public static long m25369h(Context context, String str) {
        try {
            String a = C5210cz.m3450a(context, "vkeyid_settings", "vkey_valid" + str);
            if (C5097cq.m3699a(a)) {
                return 0L;
            }
            return Long.parseLong(a);
        } catch (Throwable unused) {
            return 0L;
        }
    }
}
