package com.youth.banner.transformer;

import android.view.View;

/* loaded from: classes2.dex */
public class StackTransformer extends ABaseTransformer {
    @Override // com.youth.banner.transformer.ABaseTransformer
    /* renamed from: a */
    protected void mo14895a(View view, float f) {
        float f2 = 0.0f;
        if (f >= 0.0f) {
            f2 = (-view.getWidth()) * f;
        }
        view.setTranslationX(f2);
    }
}
