package com.lbd.p054xj.p056ui.p057fw;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.p003v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.blankj.utilcode.util.LogUtils;
import com.lbd.p054xj.C1467R;
import com.lbd.p054xj.app.AppConfig;
import com.lbd.p054xj.app.XJApp;
import com.lbd.p054xj.p056ui.activity.XJFullActivity;
import com.lbd.p054xj.p056ui.activity.XJRenderActivity;
import com.lbd.p054xj.service.XJFloatService;
import p110z1.FwManager;
import p110z1.PreferencesUtil;
import p110z1.ValReceiver;
import p110z1.aeo;

/* renamed from: com.lbd.xj.ui.fw.XJOutFloatView */
/* loaded from: classes.dex */
public class XJOutFloatView extends LinearLayout implements View.OnClickListener {

    /* renamed from: H */
    private static final int f9889H = 5;

    /* renamed from: A */
    private FrameLayout f9890A;

    /* renamed from: B */
    private LinearLayout f9891B;

    /* renamed from: C */
    private LinearLayout f9892C;

    /* renamed from: D */
    private LinearLayout f9893D;

    /* renamed from: E */
    private LinearLayout f9894E;

    /* renamed from: F */
    private RelativeLayout f9895F;

    /* renamed from: I */
    private int f9897I;

    /* renamed from: J */
    private int f9898J;

    /* renamed from: O */
    private int f9903O;

    /* renamed from: P */
    private int f9904P;

    /* renamed from: Q */
    private int f9905Q;

    /* renamed from: R */
    private int f9906R;

    /* renamed from: S */
    private long f9907S;

    /* renamed from: a */
    long f9908a;

    /* renamed from: b */
    private WindowManager f9909b;

    /* renamed from: c */
    private LinearLayout f9910c;

    /* renamed from: d */
    private WindowManager.LayoutParams f9911d;

    /* renamed from: e */
    private LinearLayout f9912e;

    /* renamed from: f */
    private Context f9913f;

    /* renamed from: g */
    private ImageView f9914g;

    /* renamed from: h */
    private ImageView f9915h;

    /* renamed from: i */
    private ImageView f9916i;

    /* renamed from: j */
    private ImageView f9917j;

    /* renamed from: k */
    private ImageView f9918k;

    /* renamed from: l */
    private ImageView f9919l;

    /* renamed from: m */
    private ImageView f9920m;

    /* renamed from: n */
    private ImageView f9921n;

    /* renamed from: o */
    private LinearLayout f9922o;

    /* renamed from: p */
    private ImageView f9923p;

    /* renamed from: q */
    private ImageView f9924q;

    /* renamed from: r */
    private ImageView f9925r;

    /* renamed from: s */
    private ImageView f9926s;

    /* renamed from: t */
    private ImageView f9927t;

    /* renamed from: u */
    private ImageView f9928u;

    /* renamed from: v */
    private TextView f9929v;

    /* renamed from: w */
    private TextView f9930w;

    /* renamed from: x */
    private TextView f9931x;

    /* renamed from: y */
    private XJLiveView f9932y;

    /* renamed from: z */
    private FrameLayout f9933z;

    /* renamed from: G */
    private int f9896G = 0;

    /* renamed from: K */
    private boolean f9899K = true;

    /* renamed from: L */
    private boolean f9900L = true;

    /* renamed from: M */
    private float f9901M = 1.0f;

    /* renamed from: N */
    private Handler f9902N = new Handler() { // from class: com.lbd.xj.ui.fw.XJOutFloatView.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what == 0) {
                XJOutFloatView.this.f9902N.sendEmptyMessageDelayed(0, 1000L);
                Log.w("handleMessage", " isVisable:" + XJOutFloatView.this.f9899K + "   goneCount" + XJOutFloatView.this.f9896G);
                if (!XJOutFloatView.this.f9899K) {
                    XJOutFloatView.this.f9896G = 0;
                } else if (XJOutFloatView.this.f9896G > 5) {
                    XJOutFloatView.this.f9896G = 0;
                    XJOutFloatView.this.f9902N.sendEmptyMessage(1);
                } else {
                    XJOutFloatView.m19333d(XJOutFloatView.this);
                }
            } else if (message.what == 1) {
                XJOutFloatView.this.m19320k();
            }
        }
    };

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: com.lbd.xj.ui.fw.XJOutFloatView$a */
    /* loaded from: classes.dex */
    public interface AbstractC1652a {
        /* renamed from: a */
        void mo19302a();
    }

    /* renamed from: d */
    static /* synthetic */ int m19333d(XJOutFloatView xJOutFloatView) {
        int i = xJOutFloatView.f9896G;
        xJOutFloatView.f9896G = i + 1;
        return i;
    }

    public XJOutFloatView(Context context, WindowManager windowManager, WindowManager.LayoutParams layoutParams) {
        super(context);
        this.f9913f = context;
        this.f9909b = windowManager;
        this.f9911d = layoutParams;
        m19334d();
        m19332e();
        m19330f();
        setOrientation(1);
    }

    /* renamed from: d */
    private void m19334d() {
        this.f9912e = (LinearLayout) LayoutInflater.from(getContext()).inflate(C1467R.layout.xj_float_layout_outer_float, (ViewGroup) this, true);
        this.f9914g = (ImageView) this.f9912e.findViewById(C1467R.C1469id.outfloat_tuding);
        this.f9915h = (ImageView) this.f9912e.findViewById(C1467R.C1469id.outfloat_scale);
        this.f9916i = (ImageView) this.f9912e.findViewById(C1467R.C1469id.outfloat_zoom);
        this.f9917j = (ImageView) this.f9912e.findViewById(C1467R.C1469id.outfloat_hide);
        this.f9918k = (ImageView) this.f9912e.findViewById(C1467R.C1469id.outfloat_voice);
        this.f9919l = (ImageView) this.f9912e.findViewById(C1467R.C1469id.outfloat_back);
        this.f9920m = (ImageView) this.f9912e.findViewById(C1467R.C1469id.outfloat_home);
        this.f9921n = (ImageView) this.f9912e.findViewById(C1467R.C1469id.outfloat_task);
        this.f9922o = (LinearLayout) this.f9912e.findViewById(C1467R.C1469id.layout_outfloat_task);
        this.f9929v = (TextView) this.f9912e.findViewById(C1467R.C1469id.tv_outfloat_tuding);
        this.f9930w = (TextView) this.f9912e.findViewById(C1467R.C1469id.tv_outfloat_scale);
        this.f9931x = (TextView) this.f9912e.findViewById(C1467R.C1469id.tv_outfloat_voice);
        this.f9923p = (ImageView) this.f9912e.findViewById(C1467R.C1469id.outfloat_small_tuding);
        this.f9924q = (ImageView) this.f9912e.findViewById(C1467R.C1469id.outfloat_small_scale);
        this.f9925r = (ImageView) this.f9912e.findViewById(C1467R.C1469id.outfloat_small_zoom);
        this.f9926s = (ImageView) this.f9912e.findViewById(C1467R.C1469id.outfloat_small_hide);
        this.f9927t = (ImageView) this.f9912e.findViewById(C1467R.C1469id.outfloat_small_voice);
        this.f9928u = (ImageView) this.f9912e.findViewById(C1467R.C1469id.outfloat_small_back);
        this.f9933z = (FrameLayout) findViewById(C1467R.C1469id.layout_top);
        this.f9890A = (FrameLayout) findViewById(C1467R.C1469id.layout_left);
        this.f9891B = (LinearLayout) findViewById(C1467R.C1469id.layout_big_top);
        this.f9893D = (LinearLayout) findViewById(C1467R.C1469id.layout_big_left);
        this.f9892C = (LinearLayout) findViewById(C1467R.C1469id.layout_small_top);
        this.f9894E = (LinearLayout) findViewById(C1467R.C1469id.layout_small_left);
        this.f9895F = (RelativeLayout) findViewById(C1467R.C1469id.ll_root);
        this.f9910c = (LinearLayout) findViewById(C1467R.C1469id.ll_content);
        this.f9932y = new XJLiveView(XJApp.getInstance().getApplicationContext(), (int) ((FwManager.getInstance().height_vertical - FwManager.getInstance().surfaceMagin) * FwManager.getInstance().screen_w_h), FwManager.getInstance().height_vertical - FwManager.getInstance().surfaceMagin);
        m19344a();
        this.f9932y.setOnTouchCallBack(new AbstractC1652a() { // from class: com.lbd.xj.ui.fw.XJOutFloatView.4
            @Override // com.lbd.p054xj.p056ui.p057fw.XJOutFloatView.AbstractC1652a
            /* renamed from: a */
            public void mo19302a() {
                XJOutFloatView.this.m19316m();
                XJOutFloatView.this.m19318l();
            }
        });
    }

    /* renamed from: e */
    private void m19332e() {
        this.f9914g.setImageResource(this.f9932y.f9869h ? C1467R.C1468drawable.bird_ic_back_window_topping_on : C1467R.C1468drawable.bird_ic_back_window_topping);
    }

    /* renamed from: f */
    private void m19330f() {
        findViewById(C1467R.C1469id.layout_tuding).setOnClickListener(new View.OnClickListener() { // from class: com.lbd.xj.ui.fw.XJOutFloatView.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtils.m23742b("layout_tuding");
                XJOutFloatView.this.m19303v();
            }
        });
        findViewById(C1467R.C1469id.layout_scale).setOnClickListener(new View.OnClickListener() { // from class: com.lbd.xj.ui.fw.XJOutFloatView.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtils.m23742b("layout_scale");
                XJOutFloatView.this.m19312o();
            }
        });
        findViewById(C1467R.C1469id.layout_outfloat_zoom).setOnClickListener(new View.OnClickListener() { // from class: com.lbd.xj.ui.fw.XJOutFloatView.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                XJOutFloatView.this.m19304u();
            }
        });
        findViewById(C1467R.C1469id.layout_outfloat_hide).setOnClickListener(new View.OnClickListener() { // from class: com.lbd.xj.ui.fw.XJOutFloatView.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                XJOutFloatView.this.m19305t();
            }
        });
        findViewById(C1467R.C1469id.layout_outfloat_voice).setOnClickListener(new View.OnClickListener() { // from class: com.lbd.xj.ui.fw.XJOutFloatView.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                XJOutFloatView.this.m19306s();
            }
        });
        findViewById(C1467R.C1469id.layout_outfloat_back).setOnClickListener(new View.OnClickListener() { // from class: com.lbd.xj.ui.fw.XJOutFloatView.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                XJOutFloatView.this.m19307r();
            }
        });
        findViewById(C1467R.C1469id.layout_outfloat_home).setOnClickListener(new View.OnClickListener() { // from class: com.lbd.xj.ui.fw.XJOutFloatView.11
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                XJOutFloatView.this.m19308q();
            }
        });
        findViewById(C1467R.C1469id.layout_outfloat_task).setOnClickListener(new View.OnClickListener() { // from class: com.lbd.xj.ui.fw.XJOutFloatView.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                XJOutFloatView.this.m19310p();
            }
        });
        this.f9923p.setOnClickListener(this);
        this.f9925r.setOnClickListener(this);
        this.f9926s.setOnClickListener(this);
        this.f9924q.setOnClickListener(this);
        this.f9927t.setOnClickListener(this);
        this.f9928u.setOnClickListener(this);
    }

    /* renamed from: g */
    private void m19328g() {
        float f = this.f9901M;
        if (f == 1.0f) {
            this.f9901M = 0.75f;
            m19324i();
            this.f9915h.setImageResource(C1467R.C1468drawable.bird_ic_back_pop_size_50);
            this.f9930w.setText("极小");
            this.f9922o.setVisibility(8);
        } else if (f == 0.75f) {
            this.f9901M = 0.5f;
            this.f9915h.setImageResource(C1467R.C1468drawable.bird_ic_back_pop_size_25);
            m19322j();
        } else if (f == 0.5f) {
            this.f9901M = 1.0f;
            this.f9930w.setText("缩小");
            this.f9922o.setVisibility(0);
            m19324i();
            this.f9915h.setImageResource(C1467R.C1468drawable.bird_ic_back_pop_size_100);
        }
        m19343a(this.f9901M);
    }

    /* renamed from: a */
    private void m19343a(float f) {
        WindowManager.LayoutParams layoutParams = this.f9911d;
        layoutParams.width = (int) (this.f9897I * f);
        layoutParams.height = (int) (this.f9898J * f);
        if (f != 0.5f) {
            layoutParams.width = (int) (layoutParams.width + aeo.m13924a(44.0f));
            WindowManager.LayoutParams layoutParams2 = this.f9911d;
            layoutParams2.height = (int) (layoutParams2.height + aeo.m13924a(44.0f));
            return;
        }
        layoutParams.width = (int) (layoutParams.width + aeo.m13924a(28.0f));
        WindowManager.LayoutParams layoutParams3 = this.f9911d;
        layoutParams3.height = (int) (layoutParams3.height + aeo.m13924a(28.0f));
    }

    /* renamed from: h */
    private void m19326h() {
        PreferencesUtil.m13937a().m13931a("isRotation", Boolean.valueOf(!((Boolean) PreferencesUtil.m13937a().m13927b("isRotation", Boolean.TRUE)).booleanValue()));
        if (((Boolean) PreferencesUtil.m13937a().m13927b("isRotation", Boolean.TRUE)).booleanValue()) {
            m19338b(0.0f);
        } else {
            m19338b(90.0f);
        }
        if (!((Boolean) PreferencesUtil.m13937a().m13927b("isRotation", Boolean.TRUE)).booleanValue()) {
            if (this.f9911d.height >= FwManager.getInstance().getwidth_change()) {
                this.f9911d.height = FwManager.getInstance().getwidth_change();
                WindowManager.LayoutParams layoutParams = this.f9911d;
                layoutParams.width = ((int) (layoutParams.height * FwManager.getInstance().screen_w_h)) + FwManager.getInstance().dip2px(this.f9913f, 24.0f);
            }
            int i = this.f9911d.width;
            WindowManager.LayoutParams layoutParams2 = this.f9911d;
            layoutParams2.width = layoutParams2.height;
            WindowManager.LayoutParams layoutParams3 = this.f9911d;
            layoutParams3.height = i;
            this.f9909b.updateViewLayout(this, layoutParams3);
            return;
        }
        int i2 = this.f9911d.width;
        WindowManager.LayoutParams layoutParams4 = this.f9911d;
        layoutParams4.width = layoutParams4.height;
        WindowManager.LayoutParams layoutParams5 = this.f9911d;
        layoutParams5.height = i2;
        if (layoutParams5.height >= FwManager.getInstance().getwidth_change()) {
            this.f9911d.height = FwManager.getInstance().getwidth_change();
            WindowManager.LayoutParams layoutParams6 = this.f9911d;
            layoutParams6.width = (int) (layoutParams6.height * FwManager.getInstance().getscreen_w_h());
            LogUtils.m23742b("getwidth_change:" + FwManager.getInstance().getwidth_change());
        }
        LogUtils.m23742b("mParams.width:" + this.f9911d.width);
        LogUtils.m23742b("mParams.width:" + this.f9911d.height);
        this.f9909b.updateViewLayout(this, this.f9911d);
    }

    /* renamed from: a */
    public void m19342a(WindowManager.LayoutParams layoutParams) {
        long j;
        this.f9897I = layoutParams.width;
        this.f9898J = layoutParams.height;
        this.f9911d = layoutParams;
        LogUtils.m23742b("mParams.width:" + layoutParams.width + "   mParams.height:" + layoutParams.height);
        m19343a(this.f9901M);
        this.f9909b.addView(this, this.f9911d);
        if (FwManager.getInstance().isFristOuter) {
            j = 500;
            FwManager.getInstance().isFristOuter = false;
        } else {
            j = 100;
        }
        postDelayed(new Runnable() { // from class: com.lbd.xj.ui.fw.XJOutFloatView.3
            @Override // java.lang.Runnable
            public void run() {
                XJOutFloatView.this.f9932y.m19355a();
            }
        }, j);
        this.f9902N.sendEmptyMessage(0);
    }

    /* renamed from: i */
    private void m19324i() {
        this.f9894E.setVisibility(8);
        this.f9892C.setVisibility(8);
        this.f9893D.setVisibility(0);
        this.f9891B.setVisibility(0);
        this.f9895F.setBackgroundResource(C1467R.C1468drawable.bird_back_window_big);
    }

    /* renamed from: j */
    private void m19322j() {
        this.f9894E.setVisibility(0);
        this.f9892C.setVisibility(0);
        this.f9893D.setVisibility(8);
        this.f9891B.setVisibility(8);
        this.f9895F.setBackgroundColor(Color.parseColor("#ad000000"));
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        LogUtils.m23720e("onDetachedFromWindow");
        this.f9902N.removeCallbacksAndMessages(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: k */
    public void m19320k() {
        if (this.f9899K) {
            this.f9899K = false;
            this.f9890A.setVisibility(8);
            this.f9933z.setVisibility(8);
            this.f9895F.setBackground(null);
            if (this.f9901M != 0.5f) {
                WindowManager.LayoutParams layoutParams = this.f9911d;
                layoutParams.height = (int) (layoutParams.height - aeo.m13924a(44.0f));
                WindowManager.LayoutParams layoutParams2 = this.f9911d;
                layoutParams2.width = (int) (layoutParams2.width - aeo.m13924a(44.0f));
            } else {
                WindowManager.LayoutParams layoutParams3 = this.f9911d;
                layoutParams3.height = (int) (layoutParams3.height - aeo.m13924a(28.0f));
                WindowManager.LayoutParams layoutParams4 = this.f9911d;
                layoutParams4.width = (int) (layoutParams4.width - aeo.m13924a(28.0f));
            }
            this.f9909b.updateViewLayout(this, this.f9911d);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: l */
    public void m19318l() {
        if (!this.f9899K) {
            this.f9899K = true;
            this.f9890A.setVisibility(0);
            this.f9933z.setVisibility(0);
            if (this.f9901M == 0.5f) {
                m19322j();
            } else {
                m19324i();
            }
            m19316m();
            if (this.f9901M != 0.5f) {
                WindowManager.LayoutParams layoutParams = this.f9911d;
                layoutParams.height = (int) (layoutParams.height + aeo.m13924a(44.0f));
                WindowManager.LayoutParams layoutParams2 = this.f9911d;
                layoutParams2.width = (int) (layoutParams2.width + aeo.m13924a(44.0f));
            } else {
                WindowManager.LayoutParams layoutParams3 = this.f9911d;
                layoutParams3.height = (int) (layoutParams3.height + aeo.m13924a(28.0f));
                WindowManager.LayoutParams layoutParams4 = this.f9911d;
                layoutParams4.width = (int) (layoutParams4.width + aeo.m13924a(28.0f));
            }
            this.f9909b.updateViewLayout(this, this.f9911d);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: m */
    public void m19316m() {
        this.f9896G = 0;
    }

    /* renamed from: a */
    public void m19344a() {
        this.f9910c.addView(this.f9932y);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(this.f9932y.getLayoutParams());
        layoutParams.setMargins(0, 0, 0, 0);
        LogUtils.m23720e("surfaceChanged", "layoutParams1.width:" + layoutParams.width + ",layoutParams1.height:" + layoutParams.height);
        this.f9932y.setLayoutParams(layoutParams);
    }

    /* renamed from: b */
    public void m19339b() {
        LogUtils.m23734c("1  -   this.wManager.removeView(XJOutFloatView)");
        if (getParent() != null) {
            LogUtils.m23734c("2   -   this.wManager.removeView(XJOutFloatView)");
            this.f9909b.removeView(this);
        }
    }

    public void setTuing(boolean z) {
        XJLiveView xJLiveView = this.f9932y;
        if (xJLiveView != null) {
            xJLiveView.setTudingStatus(z);
            this.f9914g.setImageResource(z ? C1467R.C1468drawable.bird_ic_back_pop_lock : C1467R.C1468drawable.bird_ic_back_pop_unlock);
            this.f9923p.setImageResource(z ? C1467R.C1468drawable.bird_ic_back_pop_lock : C1467R.C1468drawable.bird_ic_back_pop_unlock);
            int i = z ? C1467R.color.gray : C1467R.color.white;
            this.f9914g.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(getContext(), i)));
            this.f9923p.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(getContext(), i)));
            this.f9929v.setTextColor(getResources().getColor(i));
            this.f9929v.setText(z ? "锁定" : "解锁");
        }
    }

    /* renamed from: c */
    public void m19336c() {
        ImageView imageView = this.f9914g;
        if (imageView != null) {
            imageView.callOnClick();
        }
    }

    /* renamed from: n */
    private void m19314n() {
        Intent intent = new Intent();
        intent.putExtra("key", "onPause");
        intent.setAction(ValReceiver.f15490a);
        this.f9913f.sendBroadcast(intent);
    }

    /* renamed from: b */
    private void m19338b(float f) {
        Intent intent = new Intent(getContext(), XJFloatService.class);
        intent.putExtra("Rotation", f);
        intent.putExtra("key", 1);
        getContext().startService(intent);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        m19318l();
        int action = motionEvent.getAction();
        if (action == 0) {
            this.f9908a = System.currentTimeMillis();
            this.f9905Q = (int) motionEvent.getRawX();
            this.f9906R = (int) motionEvent.getRawY();
        } else if (action == 2) {
            this.f9911d.x += ((int) motionEvent.getRawX()) - this.f9905Q;
            this.f9911d.y += ((int) motionEvent.getRawY()) - this.f9906R;
            this.f9905Q = (int) motionEvent.getRawX();
            this.f9906R = (int) motionEvent.getRawY();
            LogUtils.m23734c("onTouchEvent", "我被拖动了：x = " + this.f9911d.x + "，y = " + this.f9911d.y);
            this.f9909b.updateViewLayout(this, this.f9911d);
            return false;
        }
        return false;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == C1467R.C1469id.outfloat_tuding || id == C1467R.C1469id.outfloat_small_tuding) {
            m19303v();
        } else if (id == C1467R.C1469id.outfloat_small_scale) {
            m19312o();
        } else if (id == C1467R.C1469id.outfloat_zoom || id == C1467R.C1469id.outfloat_small_zoom) {
            m19304u();
        } else if (id == C1467R.C1469id.outfloat_hide || id == C1467R.C1469id.outfloat_small_hide) {
            m19305t();
        } else if (id == C1467R.C1469id.outfloat_voice || id == C1467R.C1469id.outfloat_small_voice) {
            m19306s();
        } else if (id == C1467R.C1469id.outfloat_back || id == C1467R.C1469id.outfloat_small_back) {
            m19307r();
        } else if (id == C1467R.C1469id.outfloat_home) {
            m19308q();
        } else if (id == C1467R.C1469id.outfloat_task) {
            m19310p();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: o */
    public void m19312o() {
        m19316m();
        m19328g();
        this.f9909b.updateViewLayout(this, this.f9911d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: p */
    public void m19310p() {
        m19316m();
        FwManager.getInstance().sendInnerTask();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: q */
    public void m19308q() {
        m19316m();
        FwManager.getInstance().sendInnerHome();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: r */
    public void m19307r() {
        m19316m();
        FwManager.getInstance().sendInnerBack();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: s */
    public void m19306s() {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.f9907S >= 2000) {
            m19316m();
            if (this.f9900L) {
                this.f9900L = false;
                this.f9931x.setText("静音");
                this.f9918k.setImageResource(C1467R.C1468drawable.icon_mute_voice);
                this.f9927t.setImageResource(C1467R.C1468drawable.icon_mute_voice);
            } else {
                this.f9900L = true;
                this.f9931x.setText("声音");
                this.f9918k.setImageResource(C1467R.C1468drawable.bird_ic_back_pop_voice);
                this.f9927t.setImageResource(C1467R.C1468drawable.bird_ic_back_pop_voice);
            }
            FwManager.getInstance().sendInnerVoice();
            this.f9907S = currentTimeMillis;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: t */
    public void m19305t() {
        m19316m();
        FwManager.getInstance().initXJFloatView(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: u */
    public void m19304u() {
        m19316m();
        if (AppConfig.f9445l) {
            FwManager.getInstance().startActivity(getContext(), XJRenderActivity.class);
        } else {
            FwManager.getInstance().startActivity(getContext(), XJFullActivity.class);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: v */
    public void m19303v() {
        m19316m();
        setTuing(!this.f9932y.f9869h);
    }
}
