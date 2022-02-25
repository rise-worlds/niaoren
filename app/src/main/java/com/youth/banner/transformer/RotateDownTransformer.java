package com.youth.banner.transformer;

import android.view.View;

/* loaded from: classes2.dex */
public class RotateDownTransformer extends ABaseTransformer {

    /* renamed from: a */
    private static final float f14599a = -15.0f;

    @Override // com.youth.banner.transformer.ABaseTransformer
    /* renamed from: b */
    protected boolean mo14897b() {
        return true;
    }

    @Override // com.youth.banner.transformer.ABaseTransformer
    /* renamed from: a */
    protected void mo14895a(View view, float f) {
        view.setPivotX(view.getWidth() * 0.5f);
        view.setPivotY(view.getHeight());
        view.setRotation(f * f14599a * (-1.25f));
    }
}
