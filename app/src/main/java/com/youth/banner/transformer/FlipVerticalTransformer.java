package com.youth.banner.transformer;

import android.view.View;

/* loaded from: classes2.dex */
public class FlipVerticalTransformer extends ABaseTransformer {
    @Override // com.youth.banner.transformer.ABaseTransformer
    /* renamed from: a */
    protected void mo14895a(View view, float f) {
        float f2 = f * (-180.0f);
        view.setAlpha((f2 > 90.0f || f2 < -90.0f) ? 0.0f : 1.0f);
        view.setPivotX(view.getWidth() * 0.5f);
        view.setPivotY(view.getHeight() * 0.5f);
        view.setRotationX(f2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.youth.banner.transformer.ABaseTransformer
    /* renamed from: c */
    public void mo14898c(View view, float f) {
        super.mo14898c(view, f);
        if (f <= -0.5f || f >= 0.5f) {
            view.setVisibility(4);
        } else {
            view.setVisibility(0);
        }
    }
}
