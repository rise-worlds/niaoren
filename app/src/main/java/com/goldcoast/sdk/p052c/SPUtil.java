package com.goldcoast.sdk.p052c;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

/* renamed from: com.goldcoast.sdk.c.h */
/* loaded from: classes.dex */
public final class SPUtil {

    /* renamed from: a */
    private static SharedPreferences f9016a;

    /* renamed from: b */
    private static SPUtil f9017b;

    /* renamed from: c */
    private static Context f9018c;

    private SPUtil() {
    }

    /* renamed from: a */
    public static void m20316a(Context context) {
        f9018c = context;
        if (f9016a == null) {
            f9016a = f9018c.getSharedPreferences("dispatch_log", 0);
        }
    }

    /* renamed from: a */
    public static SPUtil m20317a() {
        if (f9017b == null) {
            f9017b = new SPUtil();
        }
        return f9017b;
    }

    /* renamed from: a */
    public static void m20314a(String str, String str2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            SharedPreferences.Editor edit = f9016a.edit();
            edit.putString(str, str2);
            edit.commit();
        }
    }

    /* renamed from: a */
    public static String m20315a(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        return f9016a.getString(str, "");
    }
}
