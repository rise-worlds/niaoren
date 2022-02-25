package com.youth.banner.transformer;

import android.view.View;

/* loaded from: classes2.dex */
public class AccordionTransformer extends ABaseTransformer {
    @Override // com.youth.banner.transformer.ABaseTransformer
    /* renamed from: a */
    protected void mo14895a(View view, float f) {
        float f2 = 0.0f;
        int i = (f > 0.0f ? 1 : (f == 0.0f ? 0 : -1));
        if (i >= 0) {
            f2 = view.getWidth();
        }
        view.setPivotX(f2);
        view.setScaleX(i < 0 ? f + 1.0f : 1.0f - f);
    }
}
