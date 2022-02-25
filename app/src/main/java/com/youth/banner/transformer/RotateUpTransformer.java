package com.youth.banner.transformer;

import android.view.View;

/* loaded from: classes2.dex */
public class RotateUpTransformer extends ABaseTransformer {

    /* renamed from: a */
    private static final float f14600a = -15.0f;

    @Override // com.youth.banner.transformer.ABaseTransformer
    /* renamed from: b */
    protected boolean mo14897b() {
        return true;
    }

    @Override // com.youth.banner.transformer.ABaseTransformer
    /* renamed from: a */
    protected void mo14895a(View view, float f) {
        float f2 = f * f14600a;
        view.setPivotX(view.getWidth() * 0.5f);
        view.setPivotY(0.0f);
        view.setTranslationX(0.0f);
        view.setRotation(f2);
    }
}
