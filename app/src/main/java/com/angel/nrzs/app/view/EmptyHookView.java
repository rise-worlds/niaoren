package com.angel.nrzs.app.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.angel.nrzs.C0692R;

/* renamed from: com.angel.nrzs.ui.view.EmptyHookView */
/* loaded from: classes.dex */
public class EmptyHookView extends RelativeLayout implements View.OnClickListener {

    /* renamed from: a */
    private TextView f5613a;

    /* renamed from: b */
    private TextView f5614b;

    /* renamed from: a */
    private void m24887a() {
    }

    /* renamed from: d */
    private void m24884d() {
    }

    public EmptyHookView(Context context) {
        super(context);
        m24884d();
        m24885c();
        m24886b();
        m24887a();
    }

    /* renamed from: b */
    private void m24886b() {
        this.f5613a.setOnClickListener(this);
    }

    /* renamed from: c */
    private void m24885c() {
        LayoutInflater.from(getContext()).inflate(C0692R.layout.nrzs_emptyhook_view, this);
        this.f5613a = (TextView) findViewById(C0692R.C0694id.add_tv);
    }

    public EmptyHookView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m24884d();
        m24885c();
        m24886b();
        m24887a();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        view.getId();
    }
}
