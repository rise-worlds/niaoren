package com.youth.banner.transformer;

import android.view.View;

/* loaded from: classes2.dex */
public class ZoomOutSlideTransformer extends ABaseTransformer {

    /* renamed from: a */
    private static final float f14604a = 0.85f;

    /* renamed from: b */
    private static final float f14605b = 0.5f;

    @Override // com.youth.banner.transformer.ABaseTransformer
    /* renamed from: a */
    protected void mo14895a(View view, float f) {
        if (f >= -1.0f || f <= 1.0f) {
            float height = view.getHeight();
            float width = view.getWidth();
            float max = Math.max((float) f14604a, 1.0f - Math.abs(f));
            float f2 = 1.0f - max;
            float f3 = (height * f2) / 2.0f;
            float f4 = (f2 * width) / 2.0f;
            view.setPivotY(height * f14605b);
            view.setPivotX(width * f14605b);
            if (f < 0.0f) {
                view.setTranslationX(f4 - (f3 / 2.0f));
            } else {
                view.setTranslationX((-f4) + (f3 / 2.0f));
            }
            view.setScaleX(max);
            view.setScaleY(max);
            view.setAlpha((((max - f14604a) / 0.14999998f) * f14605b) + f14605b);
        }
    }
}
