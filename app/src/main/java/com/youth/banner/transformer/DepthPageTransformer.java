package com.youth.banner.transformer;

import android.view.View;

/* loaded from: classes2.dex */
public class DepthPageTransformer extends ABaseTransformer {

    /* renamed from: a */
    private static final float f14598a = 0.75f;

    @Override // com.youth.banner.transformer.ABaseTransformer
    /* renamed from: b */
    protected boolean mo14897b() {
        return true;
    }

    @Override // com.youth.banner.transformer.ABaseTransformer
    /* renamed from: a */
    protected void mo14895a(View view, float f) {
        if (f <= 0.0f) {
            view.setTranslationX(0.0f);
            view.setScaleX(1.0f);
            view.setScaleY(1.0f);
        } else if (f <= 1.0f) {
            float abs = ((1.0f - Math.abs(f)) * 0.25f) + f14598a;
            view.setAlpha(1.0f - f);
            view.setPivotY(view.getHeight() * 0.5f);
            view.setTranslationX(view.getWidth() * (-f));
            view.setScaleX(abs);
            view.setScaleY(abs);
        }
    }
}
