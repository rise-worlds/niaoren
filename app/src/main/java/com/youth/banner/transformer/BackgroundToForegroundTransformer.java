package com.youth.banner.transformer;

import android.view.View;

/* loaded from: classes2.dex */
public class BackgroundToForegroundTransformer extends ABaseTransformer {
    @Override // com.youth.banner.transformer.ABaseTransformer
    /* renamed from: a */
    protected void mo14895a(View view, float f) {
        float height = view.getHeight();
        float width = view.getWidth();
        float f2 = 1.0f;
        int i = (f > 0.0f ? 1 : (f == 0.0f ? 0 : -1));
        if (i >= 0) {
            f2 = Math.abs(1.0f - f);
        }
        float a = m14900a(f2, 0.5f);
        view.setScaleX(a);
        view.setScaleY(a);
        view.setPivotX(width * 0.5f);
        view.setPivotY(height * 0.5f);
        view.setTranslationX(i < 0 ? width * f : (-width) * f * 0.25f);
    }
}
