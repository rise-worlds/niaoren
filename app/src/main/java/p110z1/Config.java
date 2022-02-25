package p110z1;

import android.app.Application;
import android.support.annotation.RequiresPermission;
import android.text.TextUtils;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.Utils;
import com.nrzs.data.DataApp;

/* renamed from: z1.amd */
/* loaded from: classes3.dex */
public class Config {

    /* renamed from: a */
    private static String f16472a = null;

    /* renamed from: b */
    private static String f16473b = null;

    /* renamed from: c */
    private static int f16474c = -1;

    /* renamed from: d */
    private static String f16475d = null;

    /* renamed from: e */
    private static String f16476e = null;

    /* renamed from: f */
    private static String f16477f = "福建福州";

    /* renamed from: a */
    public static void m12526a(Application application) {
        Utils.m24102a(application);
    }

    @RequiresPermission("android.permission.READ_PHONE_STATE")
    /* renamed from: a */
    public static String m12527a() {
        if (TextUtils.isEmpty(f16472a)) {
            f16472a = DataApp.m18939d().m18940c(DataApp.m18939d().m18947a());
        }
        return f16472a;
    }

    /* renamed from: b */
    public static String m12524b() {
        if (TextUtils.isEmpty(f16473b)) {
            f16473b = AppUtils.m22914l();
        }
        return f16473b;
    }

    /* renamed from: c */
    public static int m12523c() {
        if (f16474c == -1) {
            f16474c = AppUtils.m22912m();
        }
        return f16474c;
    }

    /* renamed from: d */
    public static String m12522d() {
        if (TextUtils.isEmpty(f16475d)) {
            f16475d = "1231233123";
        }
        return f16475d;
    }

    /* renamed from: e */
    public static String m12521e() {
        if (TextUtils.isEmpty(f16476e)) {
            f16476e = AppUtils.m22920i();
        }
        return f16476e;
    }

    /* renamed from: f */
    public static String m12520f() {
        if (TextUtils.isEmpty(f16477f)) {
            f16477f = "";
        }
        return f16477f;
    }

    /* renamed from: a */
    public static void m12525a(String str) {
        f16477f = str;
    }
}
