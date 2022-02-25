package p110z1;

import android.content.Context;
import android.content.SharedPreferences;

/* renamed from: z1.aqu */
/* loaded from: classes3.dex */
public class SharedPreferencesUtil {

    /* renamed from: a */
    private static String f17412a = "skinpeeler_config";

    /* renamed from: b */
    private static Context f17413b;

    /* renamed from: a */
    public static void m11446a(Context context) {
        f17413b = context;
    }

    /* renamed from: a */
    public static SharedPreferences m11447a() {
        return f17413b.getSharedPreferences(f17412a, 0);
    }

    /* renamed from: a */
    public static void m11437a(String str, String str2) {
        SharedPreferences.Editor edit = m11447a().edit();
        edit.putString(str, str2);
        edit.commit();
    }

    /* renamed from: a */
    public static void m11436a(String str, boolean z) {
        SharedPreferences.Editor edit = m11447a().edit();
        edit.putBoolean(str, z);
        edit.commit();
    }

    /* renamed from: a */
    public static void m11444a(String str, float f) {
        SharedPreferences.Editor edit = m11447a().edit();
        edit.putFloat(str, f);
        edit.commit();
    }

    /* renamed from: a */
    public static void m11443a(String str, int i) {
        SharedPreferences.Editor edit = m11447a().edit();
        edit.putInt(str, i);
        edit.commit();
    }

    /* renamed from: a */
    public static void m11442a(String str, long j) {
        SharedPreferences.Editor edit = m11447a().edit();
        edit.putLong(str, j);
        edit.commit();
    }

    /* renamed from: a */
    public static boolean m11441a(String str, Boolean bool) {
        return m11447a().getBoolean(str, bool.booleanValue());
    }

    /* renamed from: a */
    public static float m11440a(String str, Float f) {
        return m11447a().getFloat(str, f.floatValue());
    }

    /* renamed from: a */
    public static int m11439a(String str, Integer num) {
        return m11447a().getInt(str, num.intValue());
    }

    /* renamed from: a */
    public static long m11438a(String str, Long l) {
        return m11447a().getLong(str, l.longValue());
    }

    /* renamed from: b */
    public static String m11434b(String str, String str2) {
        return m11447a().getString(str, str2);
    }

    /* renamed from: a */
    public static void m11445a(String str) {
        SharedPreferences.Editor edit = m11447a().edit();
        edit.remove(str);
        edit.commit();
    }

    /* renamed from: b */
    public static void m11435b() {
        m11447a().edit().clear().commit();
    }
}
