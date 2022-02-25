package com.live.ddy.visual.view;

import android.content.Context;
import android.content.res.Configuration;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.live.ddy.C1701R;
import p110z1.DdyScreenUtil;
import p110z1.IDdyClickInterface;
import p110z1.ITouchHelp;
import p110z1.VisualTouchHelp;

/* loaded from: classes.dex */
public class DdyMediaFloatView extends RelativeLayout implements ITouchHelp {

    /* renamed from: a */
    private ImageView f10472a;

    /* renamed from: b */
    private RelativeLayout.LayoutParams f10473b;

    /* renamed from: c */
    private int f10474c;

    /* renamed from: d */
    private int f10475d;

    /* renamed from: e */
    private int f10476e;

    /* renamed from: f */
    private VisualTouchHelp f10477f;

    /* renamed from: g */
    private IDdyClickInterface f10478g;

    /* renamed from: d */
    private void m19010d() {
    }

    public DdyMediaFloatView(Context context) {
        this(context, null);
    }

    public DdyMediaFloatView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f10474c = 0;
        this.f10475d = 0;
        this.f10476e = 0;
        m19011c();
        m19010d();
        m19009e();
    }

    /* renamed from: c */
    private void m19011c() {
        LayoutInflater.from(getContext()).inflate(C1701R.layout.ddy_layout_media_flaot, (ViewGroup) this, true);
        this.f10472a = (ImageView) findViewById(C1701R.C1703id.nrzs_ddy_id_iv_float);
        mo13090a();
        this.f10473b = new RelativeLayout.LayoutParams(-2, -2);
        setLayoutParams(this.f10473b);
    }

    /* renamed from: e */
    private void m19009e() {
        this.f10477f = new VisualTouchHelp(this, this, this.f10473b);
        this.f10477f.m13117a(this.f10472a);
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        m19008f();
    }

    /* renamed from: f */
    private void m19008f() {
        mo13090a();
        this.f10473b = new RelativeLayout.LayoutParams(-2, -2);
        RelativeLayout.LayoutParams layoutParams = this.f10473b;
        layoutParams.leftMargin = 0;
        layoutParams.topMargin = (this.f10475d / 2) - (getHeight() / 2);
        setLayoutParams(this.f10473b);
    }

    @Override // p110z1.ITouchHelp
    /* renamed from: a */
    public void mo13090a() {
        this.f10474c = getResources().getDisplayMetrics().widthPixels;
        this.f10475d = getResources().getDisplayMetrics().heightPixels;
        VisualTouchHelp aicVar = this.f10477f;
        if (aicVar != null) {
            aicVar.m13118a(this.f10474c, this.f10475d);
        }
    }

    @Override // p110z1.ITouchHelp
    public void setLayoutParams(RelativeLayout.LayoutParams layoutParams) {
        super.setLayoutParams((ViewGroup.LayoutParams) layoutParams);
        VisualTouchHelp aicVar = this.f10477f;
        if (aicVar != null) {
            aicVar.m13116a(layoutParams);
            this.f10473b = layoutParams;
        }
    }

    @Override // p110z1.ITouchHelp
    /* renamed from: b */
    public void mo13089b() {
        IDdyClickInterface aieVar = this.f10478g;
        if (aieVar != null) {
            aieVar.mo13081a(this, 0, 0, "");
        }
    }

    public void setiDdyClickInterface(IDdyClickInterface aieVar) {
        this.f10478g = aieVar;
    }

    /* renamed from: a */
    private void m19012a(int i) {
        int i2 = this.f10473b.leftMargin;
        int i3 = this.f10473b.topMargin;
        int a = DdyScreenUtil.m13145a(getContext());
        int a2 = DdyScreenUtil.m13144a(getContext(), 355.0f);
        int i4 = (a - i2) - a2;
        if (i4 < 0) {
            i2 += i4;
        }
        if (i == 0) {
            this.f10473b = new RelativeLayout.LayoutParams(a2, -2);
        } else {
            this.f10473b = new RelativeLayout.LayoutParams(-2, -2);
        }
        RelativeLayout.LayoutParams layoutParams = this.f10473b;
        layoutParams.leftMargin = i2;
        layoutParams.topMargin = i3;
        setLayoutParams(layoutParams);
    }
}
