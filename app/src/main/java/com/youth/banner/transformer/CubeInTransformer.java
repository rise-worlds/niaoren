package com.youth.banner.transformer;

import android.view.View;

/* loaded from: classes2.dex */
public class CubeInTransformer extends ABaseTransformer {
    @Override // com.youth.banner.transformer.ABaseTransformer
    /* renamed from: b */
    public boolean mo14897b() {
        return true;
    }

    @Override // com.youth.banner.transformer.ABaseTransformer
    /* renamed from: a */
    protected void mo14895a(View view, float f) {
        view.setPivotX(f > 0.0f ? 0.0f : view.getWidth());
        view.setPivotY(0.0f);
        view.setRotationY(f * (-90.0f));
    }
}
