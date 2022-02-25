package p110z1;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.text.TextUtils;

/* compiled from: PackageUtils.java */
/* renamed from: z1.x */
/* loaded from: classes3.dex */
public class C5589x {

    /* renamed from: a */
    private static String f23652a;

    /* renamed from: b */
    private static int f23653b;

    /* renamed from: a */
    public static boolean m134a(Context context) {
        PackageInfo c = m132c(context);
        if (c == null) {
            return true;
        }
        String str = c.versionName;
        int i = c.versionCode;
        SharedPreferences sharedPreferences = context.getSharedPreferences(Consts.f23432j, 0);
        if (str.equals(sharedPreferences.getString(Consts.f23434l, null)) && i == sharedPreferences.getInt(Consts.f23435m, -1)) {
            return false;
        }
        f23652a = str;
        f23653b = i;
        return true;
    }

    /* renamed from: b */
    public static void m133b(Context context) {
        if (!TextUtils.isEmpty(f23652a) && f23653b != 0) {
            context.getSharedPreferences(Consts.f23432j, 0).edit().putString(Consts.f23434l, f23652a).putInt(Consts.f23435m, f23653b).apply();
        }
    }

    /* renamed from: c */
    private static PackageInfo m132c(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 16384);
        } catch (Exception unused) {
            ARouter.f22668c.error("ARouter::", "Get package info error.");
            return null;
        }
    }
}
