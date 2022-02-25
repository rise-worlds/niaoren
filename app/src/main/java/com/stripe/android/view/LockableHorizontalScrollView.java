package com.stripe.android.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.HorizontalScrollView;

/* loaded from: classes2.dex */
public class LockableHorizontalScrollView extends HorizontalScrollView {

    /* renamed from: a */
    private AbstractC2456a f12505a;

    /* renamed from: b */
    private boolean f12506b;

    /* renamed from: com.stripe.android.view.LockableHorizontalScrollView$a */
    /* loaded from: classes2.dex */
    interface AbstractC2456a {
        /* renamed from: a */
        void m17354a(int i, int i2);
    }

    public LockableHorizontalScrollView(Context context) {
        super(context);
    }

    public LockableHorizontalScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public LockableHorizontalScrollView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* renamed from: a */
    public boolean m17356a() {
        return this.f12506b;
    }

    public void setScrollable(boolean z) {
        this.f12506b = z;
        setSmoothScrollingEnabled(z);
    }

    @Override // android.widget.HorizontalScrollView, android.view.View
    public void scrollTo(int i, int i2) {
        if (this.f12506b) {
            super.scrollTo(i, i2);
        }
    }

    @Override // android.widget.HorizontalScrollView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() != 0) {
            return super.onTouchEvent(motionEvent);
        }
        return this.f12506b && super.onTouchEvent(motionEvent);
    }

    @Override // android.widget.HorizontalScrollView, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return this.f12506b && super.onInterceptTouchEvent(motionEvent);
    }

    void setScrollChangedListener(AbstractC2456a aVar) {
        this.f12505a = aVar;
    }

    /* renamed from: a */
    void m17355a(int i, int i2) {
        if (this.f12506b) {
            smoothScrollBy(i, i2);
            AbstractC2456a aVar = this.f12505a;
            if (aVar != null) {
                aVar.m17354a(i, i2);
            }
        }
    }
}
