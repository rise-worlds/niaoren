package com.angel.nrzs.p017ui.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.angel.nrzs.C0692R;

/* renamed from: com.angel.nrzs.ui.view.ToastView */
/* loaded from: classes.dex */
public class ToastView extends RelativeLayout {

    /* renamed from: a */
    private static final int f5636a = 0;

    /* renamed from: b */
    private static final int f5637b = 2;

    /* renamed from: c */
    private TextView f5638c;

    /* renamed from: d */
    private volatile int f5639d = 0;

    /* renamed from: e */
    private Handler f5640e = new Handler() { // from class: com.angel.nrzs.ui.view.ToastView.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what == 0) {
                ToastView.m24869a(ToastView.this);
                if (ToastView.this.f5639d >= 2) {
                    ToastView.this.setVisibility(8);
                    ToastView.this.f5639d = 0;
                    return;
                }
                ToastView.this.f5640e.sendEmptyMessageDelayed(0, 1000L);
            }
        }
    };

    /* renamed from: a */
    static /* synthetic */ int m24869a(ToastView toastView) {
        int i = toastView.f5639d;
        toastView.f5639d = i + 1;
        return i;
    }

    public ToastView(Context context) {
        super(context);
        m24870a(context);
    }

    public ToastView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m24870a(context);
    }

    public ToastView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m24870a(context);
    }

    /* renamed from: a */
    private void m24870a(Context context) {
        LayoutInflater.from(context).inflate(C0692R.layout.fial_toast, this);
        this.f5638c = (TextView) findViewById(C0692R.C0694id.nrzs_toast_text);
    }

    /* renamed from: a */
    public void m24867a(String str) {
        TextView textView = this.f5638c;
        if (textView != null) {
            textView.setText(str);
        }
        if (getVisibility() == 8) {
            m24872a();
            setVisibility(0);
            return;
        }
        m24866b();
    }

    /* renamed from: a */
    public void m24871a(int i, String str) {
        Drawable drawable = getResources().getDrawable(i);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        this.f5638c.setCompoundDrawables(drawable, null, null, null);
        m24867a(str);
    }

    /* renamed from: a */
    private void m24872a() {
        this.f5640e.removeCallbacksAndMessages(null);
        this.f5640e.sendEmptyMessage(0);
    }

    /* renamed from: b */
    private void m24866b() {
        this.f5639d = 0;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.f5640e.removeCallbacksAndMessages(null);
    }
}
