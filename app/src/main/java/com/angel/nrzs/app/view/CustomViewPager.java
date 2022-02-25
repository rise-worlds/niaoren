package com.angel.nrzs.app.view;

import android.content.Context;
import android.support.p003v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/* renamed from: com.angel.nrzs.ui.view.CustomViewPager */
/* loaded from: classes.dex */
public class CustomViewPager extends ViewPager {

    /* renamed from: a */
    private boolean f5612a = true;

    public CustomViewPager(Context context) {
        super(context);
    }

    public CustomViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setScroll(boolean z) {
        this.f5612a = z;
    }

    @Override // android.support.p003v4.view.ViewPager, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        return this.f5612a && super.onTouchEvent(motionEvent);
    }

    @Override // android.support.p003v4.view.ViewPager, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return this.f5612a && super.onInterceptTouchEvent(motionEvent);
    }

    @Override // android.support.p003v4.view.ViewPager
    public void setCurrentItem(int i) {
        setCurrentItem(i, false);
    }
}
