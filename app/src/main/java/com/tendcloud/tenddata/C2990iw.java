package com.tendcloud.tenddata;

import android.os.Build;
import java.util.Locale;
import java.util.TimeZone;
import org.json.JSONObject;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.iw */
/* loaded from: classes2.dex */
public class C2990iw extends AbstractC2984iq {
    public C2990iw() {
        m15410a("os", "android");
        m15410a("osVersionName", C2821dv.m15946a());
        m15410a("osVersionCode", String.valueOf(C2821dv.m15923i()));
        m15410a("timezone", TimeZone.getDefault().getID());
        m15410a("locale", m15394e());
        m15410a("timezoneV", m15392g());
        m15410a("language", C2821dv.m15919k());
        m15410a("romVersion", Build.FINGERPRINT);
        m15410a("basebandVersion", m15395d());
        m15410a("osBuild", C2821dv.m15940b());
        m15410a("jailBroken", Boolean.valueOf(C2845ej.m15833a(C2664ab.f13513g)));
    }

    /* renamed from: b */
    public String m15397b() {
        return ((JSONObject) mo15408a_()).optString("timezoneV");
    }

    /* renamed from: c */
    public String m15396c() {
        return ((JSONObject) mo15408a_()).optString("locale");
    }

    /* renamed from: d */
    public static String m15395d() {
        try {
            if (C2855es.m15807a(14)) {
                return Build.getRadioVersion();
            }
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }

    /* renamed from: e */
    public static String m15394e() {
        try {
            return Locale.getDefault().toString();
        } catch (Throwable unused) {
            return null;
        }
    }

    /* renamed from: g */
    private static String m15392g() {
        try {
            return String.valueOf(m15393f());
        } catch (Throwable unused) {
            return null;
        }
    }

    /* renamed from: f */
    public static float m15393f() {
        try {
            return TimeZone.getDefault().getRawOffset() / 3600000.0f;
        } catch (Throwable unused) {
            return -1.0f;
        }
    }
}
