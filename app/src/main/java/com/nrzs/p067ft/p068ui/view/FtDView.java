package com.nrzs.p067ft.p068ui.view;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.SizeUtils;
import com.cyjh.ddy.media.media.ActionCode;
import com.nrzs.p067ft.C1990R;
import p110z1.DragViewPresenter;
import p110z1.FloatDataManager;
import p110z1.IDragViewRun;
import p110z1.NRZSScreenUtil;

/* renamed from: com.nrzs.ft.ui.view.FtDView */
/* loaded from: classes2.dex */
public class FtDView extends LinearLayout implements IDragViewRun {

    /* renamed from: a */
    protected WindowManager.LayoutParams f10778a;

    /* renamed from: b */
    protected WindowManager f10779b;

    /* renamed from: c */
    protected boolean f10780c = false;

    /* renamed from: d */
    private ImageView f10781d;

    /* renamed from: e */
    private DragViewPresenter f10782e;

    /* renamed from: f */
    private int f10783f;

    /* renamed from: g */
    private int f10784g;

    /* renamed from: h */
    private float f10785h;

    /* renamed from: i */
    private float f10786i;

    /* renamed from: j */
    private float f10787j;

    /* renamed from: k */
    private float f10788k;

    /* renamed from: l */
    private float f10789l;

    /* renamed from: m */
    private float f10790m;

    /* renamed from: n */
    private long f10791n;

    /* renamed from: o */
    private float f10792o;

    public FtDView(Context context) {
        super(context);
        m18865a();
        m18858b();
        m18848e();
    }

    /* renamed from: a */
    public void m18865a() {
        LayoutInflater.from(getContext()).inflate(C1990R.layout.nrzs_ft_layout_drag_run, this);
        this.f10781d = (ImageView) findViewById(C1990R.C1992id.nrzs_ft_iv_run);
    }

    /* renamed from: b */
    public void m18858b() {
        this.f10779b = (WindowManager) getContext().getSystemService("window");
        this.f10778a = new WindowManager.LayoutParams();
        this.f10782e = new DragViewPresenter(this.f10778a, this);
        this.f10782e.m12238a();
    }

    /* renamed from: c */
    public void m18854c() {
        if (!this.f10780c) {
            if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 24) {
                this.f10778a.type = 2005;
            } else if (Build.VERSION.SDK_INT >= 26) {
                this.f10778a.type = 2038;
            } else {
                this.f10778a.type = ActionCode.CtrlConnectRefuse_2002;
            }
            WindowManager.LayoutParams layoutParams = this.f10778a;
            layoutParams.flags = 1320;
            layoutParams.systemUiVisibility = 2;
            layoutParams.format = 1;
            layoutParams.gravity = 51;
            layoutParams.width = -2;
            layoutParams.height = -2;
            layoutParams.flags |= 256;
            int a = SizeUtils.m23262a(50.0f);
            int[] d = FloatDataManager.m12352j().m12358d();
            if (d[0] == -1 && d[1] == -1) {
                this.f10778a.x = ScreenUtils.m23306a() - a;
                this.f10778a.y = (ScreenUtils.m23302b() / 4) - (a / 2);
            } else {
                WindowManager.LayoutParams layoutParams2 = this.f10778a;
                layoutParams2.x = d[0];
                layoutParams2.y = d[1];
            }
            this.f10779b.addView(this, this.f10778a);
            this.f10780c = true;
        }
    }

    /* renamed from: d */
    public void m18851d() {
        WindowManager windowManager = this.f10779b;
        if (windowManager != null) {
            windowManager.removeView(this);
            this.f10779b = null;
        }
    }

    /* renamed from: e */
    public void m18848e() {
        this.f10781d.setOnTouchListener(new View.OnTouchListener() { // from class: com.nrzs.ft.ui.view.FtDView.1
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                FtDView.this.f10787j = motionEvent.getRawX();
                FtDView.this.f10788k = motionEvent.getRawY();
                switch (action) {
                    case 0:
                        FtDView.this.f10785h = motionEvent.getRawX();
                        FtDView.this.f10786i = motionEvent.getRawY();
                        FtDView.this.f10789l = motionEvent.getRawX();
                        FtDView.this.f10790m = motionEvent.getRawY();
                        FtDView.this.f10791n = System.currentTimeMillis();
                        if (FtDView.this.f10782e == null) {
                            return true;
                        }
                        FtDView.this.f10782e.m12227c();
                        return true;
                    case 1:
                        FtDView ftDView = FtDView.this;
                        ftDView.f10783f = (int) (ftDView.f10787j - FtDView.this.f10785h);
                        FtDView ftDView2 = FtDView.this;
                        ftDView2.f10784g = (int) (ftDView2.f10788k - FtDView.this.f10786i);
                        FtDView ftDView3 = FtDView.this;
                        ftDView3.m18864a(ftDView3.f10783f, FtDView.this.f10784g);
                        if (FtDView.this.f10782e == null) {
                            return true;
                        }
                        FtDView.this.f10782e.m12223e();
                        return true;
                    case 2:
                        FtDView ftDView4 = FtDView.this;
                        ftDView4.f10783f = (int) (ftDView4.f10787j - FtDView.this.f10785h);
                        FtDView ftDView5 = FtDView.this;
                        ftDView5.f10784g = (int) (ftDView5.f10788k - FtDView.this.f10786i);
                        FtDView ftDView6 = FtDView.this;
                        ftDView6.f10785h = ftDView6.f10787j;
                        FtDView ftDView7 = FtDView.this;
                        ftDView7.f10786i = ftDView7.f10788k;
                        FtDView ftDView8 = FtDView.this;
                        ftDView8.m18864a(ftDView8.f10783f, FtDView.this.f10784g);
                        if ((FtDView.this.f10787j - FtDView.this.f10789l < 30.0f && FtDView.this.f10786i - FtDView.this.f10790m < 30.0f && System.currentTimeMillis() - FtDView.this.f10791n < 300) || FtDView.this.f10782e == null) {
                            return true;
                        }
                        FtDView.this.f10782e.m12225d();
                        return true;
                    default:
                        return true;
                }
            }
        });
    }

    /* renamed from: a */
    public void m18864a(int i, int i2) {
        this.f10778a.x += i;
        this.f10778a.y += i2;
        mo12123f();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        DragViewPresenter anrVar = this.f10782e;
        if (anrVar != null) {
            anrVar.m12222f();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        DragViewPresenter anrVar = this.f10782e;
        if (anrVar != null) {
            anrVar.m12221g();
        }
    }

    @Override // p110z1.IDragViewRun
    public void setFloatViewPosition(float f) {
        try {
            this.f10778a.y = (int) (m18863a(getContext(), ScreenUtils.m23302b()) * f);
            this.f10778a.x = ScreenUtils.m23306a();
            this.f10782e.m12229b(this.f10778a);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    protected float m18863a(Context context, float f) {
        if (context.getResources().getConfiguration().orientation == 2) {
            return f;
        }
        if (this.f10792o == 0.0f) {
            this.f10792o = NRZSScreenUtil.m11858j(context);
        }
        return f - this.f10792o;
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        int a = SizeUtils.m23262a(50.0f);
        this.f10778a.x = ScreenUtils.m23306a() - a;
        this.f10778a.y = (ScreenUtils.m23302b() / 4) - (a / 2);
        DragViewPresenter anrVar = this.f10782e;
        if (anrVar != null) {
            anrVar.m12236a(this.f10778a);
        }
        mo12123f();
        DragViewPresenter anrVar2 = this.f10782e;
        if (anrVar2 != null) {
            anrVar2.m12237a(2);
        }
    }

    @Override // p110z1.IDragViewRun
    /* renamed from: a */
    public void mo12124a(int i) {
        this.f10781d.setImageResource(i);
    }

    @Override // p110z1.IDragViewRun
    /* renamed from: f */
    public void mo12123f() {
        WindowManager windowManager = this.f10779b;
        if (windowManager != null && this.f10780c) {
            windowManager.updateViewLayout(this, this.f10778a);
            FloatDataManager.m12352j().m12363a(new int[]{this.f10778a.x, this.f10778a.y});
        }
    }

    /* renamed from: g */
    public void m18843g() {
        this.f10782e.m12221g();
        this.f10782e = null;
        this.f10779b = null;
    }
}
