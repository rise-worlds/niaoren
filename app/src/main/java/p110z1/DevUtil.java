package p110z1;

import android.os.Build;

/* renamed from: z1.alw */
/* loaded from: classes3.dex */
public class DevUtil {
    /* renamed from: a */
    public static String m12534a() {
        return m12533b();
    }

    /* renamed from: b */
    public static String m12533b() {
        if (Build.CPU_ABI.equals("x86")) {
            return "模拟器";
        }
        String str = Build.MODEL;
        return StringUtil.m11795c((CharSequence) str) ? "模拟器" : str;
    }
}
