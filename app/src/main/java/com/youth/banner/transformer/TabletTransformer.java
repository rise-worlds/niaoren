package com.youth.banner.transformer;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.View;

/* loaded from: classes2.dex */
public class TabletTransformer extends ABaseTransformer {

    /* renamed from: a */
    private static final Matrix f14601a = new Matrix();

    /* renamed from: b */
    private static final Camera f14602b = new Camera();

    /* renamed from: c */
    private static final float[] f14603c = new float[2];

    @Override // com.youth.banner.transformer.ABaseTransformer
    /* renamed from: a */
    protected void mo14895a(View view, float f) {
        float abs = (f < 0.0f ? 30.0f : -30.0f) * Math.abs(f);
        view.setTranslationX(m14896a(abs, view.getWidth(), view.getHeight()));
        view.setPivotX(view.getWidth() * 0.5f);
        view.setPivotY(0.0f);
        view.setRotationY(abs);
    }

    /* renamed from: a */
    protected static final float m14896a(float f, int i, int i2) {
        f14601a.reset();
        f14602b.save();
        f14602b.rotateY(Math.abs(f));
        f14602b.getMatrix(f14601a);
        f14602b.restore();
        f14601a.preTranslate((-i) * 0.5f, (-i2) * 0.5f);
        float f2 = i;
        float f3 = i2;
        f14601a.postTranslate(f2 * 0.5f, 0.5f * f3);
        float[] fArr = f14603c;
        fArr[0] = f2;
        fArr[1] = f3;
        f14601a.mapPoints(fArr);
        return (f2 - f14603c[0]) * (f > 0.0f ? 1.0f : -1.0f);
    }
}
