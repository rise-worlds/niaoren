package com.gyf.barlibrary;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.gyf.barlibrary.a */
/* loaded from: classes.dex */
public class BarConfig {

    /* renamed from: a */
    private static final String f9246a = "status_bar_height";

    /* renamed from: b */
    private static final String f9247b = "navigation_bar_height";

    /* renamed from: c */
    private static final String f9248c = "navigation_bar_height_landscape";

    /* renamed from: d */
    private static final String f9249d = "navigation_bar_width";

    /* renamed from: e */
    private static final String f9250e = "force_fsg_nav_bar";

    /* renamed from: f */
    private final int f9251f;

    /* renamed from: g */
    private final int f9252g;

    /* renamed from: h */
    private final boolean f9253h;

    /* renamed from: i */
    private final int f9254i;

    /* renamed from: j */
    private final int f9255j;

    /* renamed from: k */
    private final boolean f9256k;

    /* renamed from: l */
    private final float f9257l;

    /* renamed from: m */
    private final boolean f9258m;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BarConfig(Activity activity) {
        boolean z = false;
        this.f9256k = activity.getResources().getConfiguration().orientation == 1;
        this.f9257l = m20114b(activity);
        this.f9251f = m20116a(activity, f9246a);
        this.f9252g = m20117a((Context) activity);
        this.f9254i = m20113b((Context) activity);
        this.f9255j = m20111c(activity);
        this.f9253h = this.f9254i > 0 ? true : z;
        this.f9258m = NotchUtils.m19919a(activity);
    }

    @TargetApi(14)
    /* renamed from: a */
    private int m20117a(Context context) {
        if (Build.VERSION.SDK_INT < 14) {
            return 0;
        }
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(16843499, typedValue, true);
        return TypedValue.complexToDimensionPixelSize(typedValue.data, context.getResources().getDisplayMetrics());
    }

    @TargetApi(14)
    /* renamed from: b */
    private int m20113b(Context context) {
        if (Build.VERSION.SDK_INT < 14 || !m20118a((Activity) context)) {
            return 0;
        }
        return m20116a(context, this.f9256k ? f9247b : f9248c);
    }

    @TargetApi(14)
    /* renamed from: c */
    private int m20111c(Context context) {
        if (Build.VERSION.SDK_INT < 14 || !m20118a((Activity) context)) {
            return 0;
        }
        return m20116a(context, f9249d);
    }

    @TargetApi(14)
    /* renamed from: a */
    private boolean m20118a(Activity activity) {
        if (Build.VERSION.SDK_INT >= 17 && Settings.Global.getInt(activity.getContentResolver(), f9250e, 0) != 0) {
            return false;
        }
        Display defaultDisplay = activity.getWindowManager().getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (Build.VERSION.SDK_INT >= 17) {
            defaultDisplay.getRealMetrics(displayMetrics);
        }
        int i = displayMetrics.heightPixels;
        int i2 = displayMetrics.widthPixels;
        DisplayMetrics displayMetrics2 = new DisplayMetrics();
        defaultDisplay.getMetrics(displayMetrics2);
        return i2 - displayMetrics2.widthPixels > 0 || i - displayMetrics2.heightPixels > 0;
    }

    /* renamed from: a */
    private int m20116a(Context context, String str) {
        try {
            int identifier = Resources.getSystem().getIdentifier(str, "dimen", "android");
            if (identifier <= 0) {
                return 0;
            }
            int dimensionPixelSize = context.getResources().getDimensionPixelSize(identifier);
            int dimensionPixelSize2 = Resources.getSystem().getDimensionPixelSize(identifier);
            if (dimensionPixelSize2 >= dimensionPixelSize) {
                return dimensionPixelSize2;
            }
            return Math.round((dimensionPixelSize * Resources.getSystem().getDisplayMetrics().density) / context.getResources().getDisplayMetrics().density);
        } catch (Resources.NotFoundException unused) {
            return 0;
        }
    }

    @SuppressLint({"NewApi"})
    /* renamed from: b */
    private float m20114b(Activity activity) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (Build.VERSION.SDK_INT >= 16) {
            activity.getWindowManager().getDefaultDisplay().getRealMetrics(displayMetrics);
        } else {
            activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        }
        return Math.min(displayMetrics.widthPixels / displayMetrics.density, displayMetrics.heightPixels / displayMetrics.density);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public boolean m20119a() {
        return this.f9257l >= 600.0f || this.f9256k;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public int m20115b() {
        return this.f9251f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c */
    public int m20112c() {
        return this.f9252g;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: d */
    public boolean m20110d() {
        return this.f9253h;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: e */
    public int m20109e() {
        return this.f9254i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: f */
    public int m20108f() {
        return this.f9255j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: g */
    public boolean m20107g() {
        return this.f9258m;
    }
}
