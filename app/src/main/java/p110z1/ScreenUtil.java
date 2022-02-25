package p110z1;

import android.content.Context;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

/* renamed from: z1.aqt */
/* loaded from: classes3.dex */
public class ScreenUtil {

    /* renamed from: a */
    public static int f17410a;

    /* renamed from: b */
    public static int f17411b;

    /* renamed from: a */
    public static void m11450a(Context context) {
        f17410a = context.getResources().getDisplayMetrics().widthPixels;
        f17411b = m11449b(context);
        SharedPreferencesUtil.m11443a("SCREEN_WIDTH", f17410a);
        SharedPreferencesUtil.m11443a("SCREEN_HEIGHT", f17411b);
    }

    /* renamed from: b */
    public static int m11449b(Context context) {
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        if (Build.VERSION.SDK_INT >= 17) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            defaultDisplay.getRealMetrics(displayMetrics);
            return displayMetrics.heightPixels;
        } else if (Build.VERSION.SDK_INT < 14) {
            return 0;
        } else {
            try {
                return ((Integer) Display.class.getMethod("getRawHeight", new Class[0]).invoke(defaultDisplay, new Object[0])).intValue();
            } catch (Exception unused) {
                DisplayMetrics displayMetrics2 = new DisplayMetrics();
                defaultDisplay.getMetrics(displayMetrics2);
                return displayMetrics2.heightPixels;
            }
        }
    }

    /* renamed from: c */
    public int m11448c(Context context) {
        int identifier = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (identifier > 0) {
            return context.getResources().getDimensionPixelSize(identifier);
        }
        return 0;
    }
}
