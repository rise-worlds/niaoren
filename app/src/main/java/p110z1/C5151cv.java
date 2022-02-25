package p110z1;

import android.content.Context;

/* renamed from: z1.cv */
/* loaded from: classes3.dex */
public final class C5151cv {

    /* renamed from: a */
    private static C5151cv f21102a = new C5151cv();

    private C5151cv() {
    }

    /* renamed from: a */
    public static String m3513a(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 16).versionName;
        } catch (Exception unused) {
            return "0.0.0";
        }
    }

    /* renamed from: a */
    public static C5151cv m3514a() {
        return f21102a;
    }
}
