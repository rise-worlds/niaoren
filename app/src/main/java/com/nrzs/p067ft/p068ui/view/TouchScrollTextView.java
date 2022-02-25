package com.nrzs.p067ft.p068ui.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.p006v7.widget.AppCompatTextView;
import android.text.method.ScrollingMovementMethod;
import android.util.AttributeSet;
import android.view.MotionEvent;
import com.lidroid.xutils.util.LogUtils;

/* renamed from: com.nrzs.ft.ui.view.TouchScrollTextView */
/* loaded from: classes2.dex */
public class TouchScrollTextView extends AppCompatTextView {

    /* renamed from: e */
    private static final int f10795e = 5;

    /* renamed from: a */
    private boolean f10796a = false;

    /* renamed from: b */
    private int f10797b = 0;

    /* renamed from: c */
    private float f10798c;

    /* renamed from: d */
    private float f10799d;

    public TouchScrollTextView(Context context) {
        super(context);
        m18837a();
    }

    public TouchScrollTextView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        m18837a();
    }

    public TouchScrollTextView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m18837a();
    }

    /* renamed from: a */
    private void m18837a() {
        post(new Runnable() { // from class: com.nrzs.ft.ui.view.TouchScrollTextView.1
            @Override // java.lang.Runnable
            public void run() {
                int measuredHeight = TouchScrollTextView.this.getMeasuredHeight();
                int lineHeight = TouchScrollTextView.this.getLineHeight() * TouchScrollTextView.this.getLineCount();
                TouchScrollTextView.this.f10797b = lineHeight;
                if (lineHeight > measuredHeight) {
                    LogUtils.m19222d("height:" + measuredHeight + "   contentHeight:" + TouchScrollTextView.this.f10797b);
                    TouchScrollTextView.this.f10796a = true;
                }
            }
        });
        setMovementMethod(ScrollingMovementMethod.getInstance());
    }

    /* renamed from: b */
    private boolean m18833b() {
        int scrollY = (this.f10797b - getScrollY()) - getMeasuredHeight();
        if (this.f10799d <= 0.0f || scrollY <= 5) {
            return this.f10799d < 0.0f && getScrollY() > 5;
        }
        return true;
    }

    @Override // android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            this.f10798c = motionEvent.getY();
        } else if (action == 2) {
            this.f10799d = this.f10798c - motionEvent.getY();
        }
        m18832c();
        return super.dispatchTouchEvent(motionEvent);
    }

    /* renamed from: c */
    private void m18832c() {
        if (this.f10796a && getParent() != null && m18833b()) {
            getParent().requestDisallowInterceptTouchEvent(true);
        }
    }
}
