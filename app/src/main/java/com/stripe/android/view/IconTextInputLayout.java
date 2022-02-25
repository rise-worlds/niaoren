package com.stripe.android.view;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.VisibleForTesting;
import android.support.design.widget.TextInputLayout;
import android.util.AttributeSet;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* loaded from: classes2.dex */
public class IconTextInputLayout extends TextInputLayout {

    /* renamed from: d */
    private static final String f12499d = "mCollapsedBounds";

    /* renamed from: e */
    private static final String f12500e = "mCollapsingTextHelper";

    /* renamed from: f */
    private static final String f12501f = "recalculate";
    @VisibleForTesting

    /* renamed from: a */
    Rect f12502a;
    @VisibleForTesting

    /* renamed from: b */
    Object f12503b;
    @VisibleForTesting

    /* renamed from: c */
    Method f12504c;

    public IconTextInputLayout(Context context) {
        this(context, null);
    }

    public IconTextInputLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public IconTextInputLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m17358a();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.design.widget.TextInputLayout, android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        m17357b();
    }

    @VisibleForTesting
    /* renamed from: a */
    void m17358a() {
        try {
            Field declaredField = TextInputLayout.class.getDeclaredField(f12500e);
            declaredField.setAccessible(true);
            this.f12503b = declaredField.get(this);
            Field declaredField2 = this.f12503b.getClass().getDeclaredField(f12499d);
            declaredField2.setAccessible(true);
            this.f12502a = (Rect) declaredField2.get(this.f12503b);
            this.f12504c = this.f12503b.getClass().getDeclaredMethod(f12501f, new Class[0]);
        } catch (Exception e) {
            this.f12503b = null;
            this.f12502a = null;
            this.f12504c = null;
            e.printStackTrace();
        }
    }

    /* renamed from: b */
    private void m17357b() {
        if (this.f12503b != null && getEditText() != null) {
            try {
                this.f12502a.left = getEditText().getLeft() + getEditText().getPaddingLeft();
                this.f12504c.invoke(this.f12503b, new Object[0]);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }
}
