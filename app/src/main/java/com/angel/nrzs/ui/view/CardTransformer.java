package com.angel.nrzs.ui.view;

import android.support.v4.view.ViewPager;
import android.view.View;

/* renamed from: com.angel.nrzs.ui.view.CardTransformer */
/* loaded from: classes.dex */
public class CardTransformer implements ViewPager.PageTransformer {

    /* renamed from: a */
    private static final float f5610a = 0.9f;

    /* renamed from: b */
    private static final float f5611b = 0.5f;

    @Override // android.support.v4.view.ViewPager.PageTransformer
    public void transformPage(View view, float f) {
        if (f < -1.0f || f > 1.0f) {
            view.setAlpha(f5611b);
            view.setScaleX(f5610a);
            view.setScaleY(f5610a);
        } else if (f <= 1.0f) {
            float max = Math.max((float) f5610a, 1.0f - Math.abs(f));
            if (f < 0.0f) {
                float f2 = (f * 0.1f) + 1.0f;
                view.setScaleX(f2);
                view.setScaleY(f2);
            } else {
                float f3 = 1.0f - (f * 0.1f);
                view.setScaleX(f3);
                view.setScaleY(f3);
            }
            view.setAlpha((((max - f5610a) / 0.100000024f) * f5611b) + f5611b);
        }
    }
}
