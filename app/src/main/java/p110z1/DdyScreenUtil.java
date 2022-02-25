package p110z1;

import android.content.Context;
import android.util.DisplayMetrics;

/* renamed from: z1.ahy */
/* loaded from: classes3.dex */
public class DdyScreenUtil {
    /* renamed from: a */
    public static int m13145a(Context context) {
        return m13143b(context).widthPixels;
    }

    /* renamed from: b */
    public static DisplayMetrics m13143b(Context context) {
        return context.getResources().getDisplayMetrics();
    }

    /* renamed from: a */
    public static int m13144a(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    /* renamed from: c */
    public static boolean m13142c(Context context) {
        return context.getResources().getConfiguration().orientation == 2;
    }
}
