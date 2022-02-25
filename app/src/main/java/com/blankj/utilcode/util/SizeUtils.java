package com.blankj.utilcode.util;

import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import p110z1.MemoryConstants;

/* renamed from: com.blankj.utilcode.util.aw */
/* loaded from: classes.dex */
public final class SizeUtils {

    /* compiled from: SizeUtils.java */
    /* renamed from: com.blankj.utilcode.util.aw$a */
    /* loaded from: classes.dex */
    public interface AbstractC0987a {
        /* renamed from: a */
        void m23253a(View view);
    }

    private SizeUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /* renamed from: a */
    public static int m23262a(float f) {
        return (int) ((f * Resources.getSystem().getDisplayMetrics().density) + 0.5f);
    }

    /* renamed from: b */
    public static int m23258b(float f) {
        return (int) ((f / Resources.getSystem().getDisplayMetrics().density) + 0.5f);
    }

    /* renamed from: c */
    public static int m23256c(float f) {
        return (int) ((f * Resources.getSystem().getDisplayMetrics().scaledDensity) + 0.5f);
    }

    /* renamed from: d */
    public static int m23254d(float f) {
        return (int) ((f / Resources.getSystem().getDisplayMetrics().scaledDensity) + 0.5f);
    }

    /* renamed from: a */
    public static float m23261a(float f, int i) {
        DisplayMetrics displayMetrics = Resources.getSystem().getDisplayMetrics();
        switch (i) {
            case 0:
                return f;
            case 1:
                return f * displayMetrics.density;
            case 2:
                return f * displayMetrics.scaledDensity;
            case 3:
                return f * displayMetrics.xdpi * 0.013888889f;
            case 4:
                return f * displayMetrics.xdpi;
            case 5:
                return f * displayMetrics.xdpi * 0.03937008f;
            default:
                return 0.0f;
        }
    }

    /* renamed from: a */
    public static void m23259a(final View view, final AbstractC0987a aVar) {
        view.post(new Runnable() { // from class: com.blankj.utilcode.util.aw.1
            @Override // java.lang.Runnable
            public void run() {
                AbstractC0987a aVar2 = AbstractC0987a.this;
                if (aVar2 != null) {
                    aVar2.m23253a(view);
                }
            }
        });
    }

    /* renamed from: a */
    public static int m23260a(View view) {
        return m23255c(view)[0];
    }

    /* renamed from: b */
    public static int m23257b(View view) {
        return m23255c(view)[1];
    }

    /* renamed from: c */
    public static int[] m23255c(View view) {
        int i;
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new ViewGroup.LayoutParams(-1, -2);
        }
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(0, 0, layoutParams.width);
        int i2 = layoutParams.height;
        if (i2 > 0) {
            i = View.MeasureSpec.makeMeasureSpec(i2, MemoryConstants.f21646d);
        } else {
            i = View.MeasureSpec.makeMeasureSpec(0, 0);
        }
        view.measure(childMeasureSpec, i);
        return new int[]{view.getMeasuredWidth(), view.getMeasuredHeight()};
    }
}
