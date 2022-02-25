package com.lbd.p054xj.p056ui.p057fw;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import com.lbd.p054xj.C1467R;
import com.lbd.p054xj.app.XJApp;

/* renamed from: com.lbd.xj.ui.fw.XJVedioLayoutView */
/* loaded from: classes.dex */
public class XJVedioLayoutView extends LinearLayout {

    /* renamed from: a */
    private LinearLayout f9945a;

    /* renamed from: b */
    private XJLiveView f9946b;

    public XJVedioLayoutView(@NonNull Context context) {
        super(context);
        LayoutInflater.from(XJApp.getInstance().getApplicationContext()).inflate(C1467R.layout.xj_float_layout_outer_float, this);
        this.f9945a = (LinearLayout) findViewById(C1467R.C1469id.ll_root);
    }

    public XJVedioLayoutView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public XJVedioLayoutView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* renamed from: a */
    public void m19301a(int i, int i2, float f) {
        int i3 = i - i2;
        this.f9946b = new XJLiveView(XJApp.getInstance().getApplicationContext(), (int) (i3 * f), i3);
    }

    /* renamed from: a */
    public void m19299a(XJLiveView xJLiveView) {
        this.f9945a.addView(this.f9946b);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(this.f9946b.getLayoutParams());
        layoutParams.setMargins(0, m19300a(getContext(), 24.0f), 0, 0);
        this.f9946b.setLayoutParams(layoutParams);
    }

    /* renamed from: a */
    private int m19300a(Context context, float f) {
        return (int) TypedValue.applyDimension(1, f, context.getResources().getDisplayMetrics());
    }
}
