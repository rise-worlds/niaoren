package com.youth.banner.transformer;

import android.support.v4.view.ViewPager;
import android.view.View;

/* loaded from: classes2.dex */
public abstract class ABaseTransformer implements ViewPager.PageTransformer {
    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public static final float m14900a(float f, float f2) {
        return f < f2 ? f2 : f;
    }

    /* renamed from: a */
    protected abstract void mo14895a(View view, float f);

    /* renamed from: a */
    protected boolean m14901a() {
        return true;
    }

    /* renamed from: b */
    protected boolean mo14897b() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: c */
    public void mo14898c(View view, float f) {
    }

    @Override // android.support.v4.view.ViewPager.PageTransformer
    public void transformPage(View view, float f) {
        m14899b(view, f);
        mo14895a(view, f);
        mo14898c(view, f);
    }

    /* renamed from: b */
    protected void m14899b(View view, float f) {
        float width = view.getWidth();
        float f2 = 0.0f;
        view.setRotationX(0.0f);
        view.setRotationY(0.0f);
        view.setRotation(0.0f);
        view.setScaleX(1.0f);
        view.setScaleY(1.0f);
        view.setPivotX(0.0f);
        view.setPivotY(0.0f);
        view.setTranslationY(0.0f);
        view.setTranslationX(mo14897b() ? 0.0f : (-width) * f);
        if (m14901a()) {
            if (f > -1.0f && f < 1.0f) {
                f2 = 1.0f;
            }
            view.setAlpha(f2);
            return;
        }
        view.setAlpha(1.0f);
    }
}
