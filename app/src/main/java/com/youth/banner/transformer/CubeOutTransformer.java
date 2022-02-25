package com.youth.banner.transformer;

import android.view.View;

/* loaded from: classes2.dex */
public class CubeOutTransformer extends ABaseTransformer {
    @Override // com.youth.banner.transformer.ABaseTransformer
    /* renamed from: b */
    public boolean mo14897b() {
        return true;
    }

    @Override // com.youth.banner.transformer.ABaseTransformer
    /* renamed from: a */
    protected void mo14895a(View view, float f) {
        float f2 = 0.0f;
        if (f < 0.0f) {
            f2 = view.getWidth();
        }
        view.setPivotX(f2);
        view.setPivotY(view.getHeight() * 0.5f);
        view.setRotationY(f * 90.0f);
    }
}
