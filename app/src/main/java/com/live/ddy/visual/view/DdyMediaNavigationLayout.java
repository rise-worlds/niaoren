package com.live.ddy.visual.view;

import android.content.Context;
import android.content.res.Configuration;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.live.ddy.C1701R;
import p110z1.DdyScreenUtil;
import p110z1.IDdyClickInterface;

/* loaded from: classes.dex */
public class DdyMediaNavigationLayout extends LinearLayout {

    /* renamed from: a */
    private View f10479a;

    /* renamed from: b */
    private int f10480b = 0;

    /* renamed from: c */
    private int f10481c = 0;

    /* renamed from: d */
    private int f10482d = 0;

    /* renamed from: e */
    private LinearLayout f10483e;

    /* renamed from: f */
    private LinearLayout f10484f;

    /* renamed from: g */
    private LinearLayout f10485g;

    /* renamed from: h */
    private IDdyClickInterface f10486h;

    public DdyMediaNavigationLayout(Context context, View view) {
        super(context);
        this.f10479a = view;
        m19004c();
    }

    public DdyMediaNavigationLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m19004c();
    }

    public void setiDdyClickInterface(IDdyClickInterface aieVar) {
        this.f10486h = aieVar;
    }

    /* renamed from: c */
    private void m19004c() {
        LayoutInflater.from(getContext()).inflate(C1701R.layout.ddy_layout_media_navigation, (ViewGroup) this, true);
        this.f10483e = (LinearLayout) findViewById(C1701R.C1703id.nrzs_ddy_ll_devback);
        this.f10484f = (LinearLayout) findViewById(C1701R.C1703id.nrzs_ddy_ll_devhome);
        this.f10485g = (LinearLayout) findViewById(C1701R.C1703id.nrzs_ddy_ll_devmenu);
        m19007a();
        setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        m19005b();
        m19003d();
    }

    /* renamed from: d */
    private void m19003d() {
        this.f10483e.setOnClickListener(new View.OnClickListener() { // from class: com.live.ddy.visual.view.DdyMediaNavigationLayout.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (DdyMediaNavigationLayout.this.f10486h != null) {
                    DdyMediaNavigationLayout.this.f10486h.mo13081a(view, 0, 2, "");
                }
            }
        });
        this.f10484f.setOnClickListener(new View.OnClickListener() { // from class: com.live.ddy.visual.view.DdyMediaNavigationLayout.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (DdyMediaNavigationLayout.this.f10486h != null) {
                    DdyMediaNavigationLayout.this.f10486h.mo13081a(view, 1, 2, "");
                }
            }
        });
        this.f10485g.setOnClickListener(new View.OnClickListener() { // from class: com.live.ddy.visual.view.DdyMediaNavigationLayout.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (DdyMediaNavigationLayout.this.f10486h != null) {
                    DdyMediaNavigationLayout.this.f10486h.mo13081a(view, 2, 2, "");
                }
            }
        });
    }

    /* renamed from: a */
    public void m19007a() {
        this.f10480b = Math.max(this.f10479a.getWidth(), this.f10479a.getHeight());
        this.f10481c = Math.min(this.f10479a.getWidth(), this.f10479a.getHeight());
        this.f10482d = DdyScreenUtil.m13144a(getContext(), 44.0f);
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        m19005b();
    }

    /* renamed from: b */
    public void m19005b() {
        boolean c = DdyScreenUtil.m13142c(getContext());
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        int i = this.f10481c;
        layoutParams.width = i;
        if (c) {
            setX(this.f10480b - ((i + this.f10482d) / 2));
            setY((this.f10481c - this.f10482d) / 2);
            setRotation(-90.0f);
            return;
        }
        setX(0.0f);
        setY(this.f10480b - this.f10482d);
        setRotation(0.0f);
    }
}
