package com.lbd.xj.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.http.Headers;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SizeUtils;
import com.lbd.xj.C1467R;
import com.lbd.xj.app.AppConfig;
import com.lbd.xj.app.XJApp;
import com.lbd.xj.device.MobileInfoManagerUtil;
import com.lbd.xj.device.location.LocationController;
import com.lbd.xj.device.location.LocationManagerUtil;
import com.lbd.xj.device.sersor.SensorController;
import com.lbd.xj.device.wifi.WifiManagerUtil;
import com.lbd.xj.manager.XJDataManager;
import com.lbd.xj.manager.XJRenderActivityManager;
import com.lbd.xj.ui.dialog.ResolutionDialog;
import com.lbd.xj.ui.p057fw.XJLiveView;
import com.lbd.xj.receiver.KeyCodeReceiver;
import com.lbd.xj.service.XJFloatService;
import com.stripe.android.view.ShippingInfoWidget;
import p110z1.EventBus;
import p110z1.EventBusMessage;
import p110z1.FloatingPermissionCompat;
import p110z1.FwManager;
import p110z1.KeyCodeEvent;
import p110z1.ResolutionEvent;
import p110z1.RomSizeUtil;
import p110z1.Subscribe;
import p110z1.ThreadMode;
import p110z1.XJFtGlodNotEnoughDialog;
import p110z1.XJFtLoginKickDialog;
import p110z1.XJRenderActivityContract;
import p110z1.XJRenderActivityPresenter;
import p110z1.XNKJEvent;
import p110z1.aeo;

/* renamed from: com.lbd.xj.ui.activity.XJRenderActivity */
/* loaded from: classes.dex */
public class XJRenderActivity extends XJBaseANativityctivity implements View.OnTouchListener, XJRenderActivityContract.AbstractC3332b {

    /* renamed from: h */
    private static final int f9628h = 4;

    /* renamed from: i */
    private static boolean f9629i = false;

    /* renamed from: A */
    private float f9630A;

    /* renamed from: B */
    private XJRenderActivityContract.AbstractC3331a f9631B;

    /* renamed from: C */
    private KeyCodeReceiver f9632C;

    /* renamed from: f */
    boolean f9634f;

    /* renamed from: g */
    float[] f9635g;

    /* renamed from: k */
    private LinearLayout f9637k;

    /* renamed from: l */
    private LinearLayout f9638l;

    /* renamed from: m */
    private boolean f9639m;

    /* renamed from: o */
    private FrameLayout.LayoutParams f9641o;

    /* renamed from: q */
    private SurfaceHolder.Callback f9643q;

    /* renamed from: s */
    private ResolutionDialog f9645s;

    /* renamed from: t */
    private LinearLayout f9646t;

    /* renamed from: u */
    private ImageView f9647u;

    /* renamed from: v */
    private ImageView f9648v;

    /* renamed from: w */
    private ImageView f9649w;

    /* renamed from: y */
    private float f9651y;

    /* renamed from: z */
    private float f9652z;

    /* renamed from: e */
    public String f9633e = "newFullActivity";

    /* renamed from: j */
    private boolean f9636j = false;

    /* renamed from: n */
    private boolean f9640n = true;

    /* renamed from: p */
    private boolean f9642p = false;

    /* renamed from: r */
    private SurfaceView f9644r = null;

    /* renamed from: x */
    private int f9650x = 0;

    /* renamed from: A */
    private void m19535A() {
    }

    /* renamed from: g */
    public static void m19517g() {
    }

    /* renamed from: l */
    public static void m19512l() {
    }

    public static native void vmtools_input_event(int i, float f, Object obj, float f2);

    /* renamed from: y */
    private void m19499y() {
    }

    /* renamed from: z */
    private void m19498z() {
    }

    @Override // p110z1.IXJBaseView
    /* renamed from: a */
    public Context mo14358a() {
        return this;
    }

    /* renamed from: d */
    public void m19521d() {
    }

    /* renamed from: f */
    public void m19518f() {
    }

    public native int start_pipe(String str);

    public native boolean vmtools_init(Object obj, int i, int i2, int i3);

    public native void vmtools_input_keyevent(int i);

    public native boolean vmtools_removeSubWindow();

    public native boolean vmtools_resetup_window(Object obj, int i, int i2, int i3, int i4, float f);

    static {
        System.loadLibrary("vmtools");
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    /* renamed from: a  reason: avoid collision after fix types in other method */
    public void mo14357a(XJRenderActivityPresenter ackVar) {
        this.f9631B = ackVar;
    }

    @Override // p110z1.XJRenderActivityContract.AbstractC3332b
    /* renamed from: a */
    public void mo14356a(int i) {
        this.f9646t.setVisibility(i);
    }

    /* renamed from: com.lbd.xj.ui.activity.XJRenderActivity$a */
    /* loaded from: classes.dex */
    public class SurfaceHolder$CallbackC1588a implements SurfaceHolder.Callback {
        public SurfaceHolder$CallbackC1588a() {
        }

        @Override // android.view.SurfaceHolder.Callback
        public void surfaceCreated(SurfaceHolder surfaceHolder) {
            Log.d("lbswww", "surfaceCreated surface= " + surfaceHolder.getSurface() + "w=" + XJRenderActivity.this.f9644r.getWidth() + " h=" + XJRenderActivity.this.f9644r.getHeight());
            XJRenderActivity.this.vmtools_init(surfaceHolder.getSurface(), XJRenderActivity.this.f9644r.getWidth(), XJRenderActivity.this.f9644r.getHeight(), 22501);
        }

        @Override // android.view.SurfaceHolder.Callback
        public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
            Log.d("lbswww", "surfaceChanged surface " + surfaceHolder.getSurface() + "w=" + XJRenderActivity.this.f9644r.getWidth() + " h=" + XJRenderActivity.this.f9644r.getHeight());
            XJRenderActivity.this.vmtools_resetup_window(surfaceHolder.getSurface(), 0, 0, XJRenderActivity.this.f9644r.getWidth(), XJRenderActivity.this.f9644r.getHeight(), 0.0f);
        }

        @Override // android.view.SurfaceHolder.Callback
        public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
            Log.d("lbswww", "surfaceDestroyed surface=" + surfaceHolder.getSurface());
            XJRenderActivity.this.vmtools_removeSubWindow();
        }
    }

    @Override // com.lbd.xj.ui.activity.XJBaseANativityctivity
    /* renamed from: b */
    public void mo19525b() {
        super.mo19525b();
    }

    @Override // com.lbd.xj.ui.activity.XJBaseANativityctivity
    /* renamed from: b */
    public void mo19524b(Message message) {
        super.mo19524b(message);
        int i = message.what;
        if (i == 1) {
            new Thread(new Runnable() { // from class: com.lbd.xj.ui.activity.XJRenderActivity.1
                @Override // java.lang.Runnable
                public void run() {
                    Log.d("sunya", "for test close start_pipe");
                }
            }).start();
        } else if (i != 2) {
            if (i == 3) {
                MobileInfoManagerUtil.getInstance().startPhoneStateListener();
            }
        } else if (message.obj != null) {
            LocationManagerUtil.getInstance().mHandlerMsg(String.valueOf(message.obj));
        }
    }

    /* renamed from: a */
    public static void m19533a(Activity activity) {
        activity.startActivity(new Intent(activity, XJRenderActivity.class));
    }

    @Override // com.lbd.xj.ui.activity.XJBaseANativityctivity, android.support.v4.app.FragmentActivity, android.support.v4.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Log.d("sunya", "newFullActivity oncreate");
        f9629i = true;
        setContentView(C1467R.layout.activity_xj_render_layout);
        if (XJDataManager.INSTANCE.nstatic == 1) {
            m19521d();
        }
        XJRenderActivityManager.INSTANCE.initDisplayParam(this);
        m19510n();
        m19509o();
        m19508p();
    }

    /* renamed from: n */
    private void m19510n() {
        this.f9637k = (LinearLayout) findViewById(C1467R.C1469id.fl_content);
        this.f9644r = (SurfaceView) findViewById(C1467R.C1469id.surfaceView);
        this.f9638l = (LinearLayout) findViewById(C1467R.C1469id.fl_p);
        this.f9646t = (LinearLayout) findViewById(C1467R.C1469id.nrzs_xnkj_ll_opera);
        this.f9647u = (ImageView) findViewById(C1467R.C1469id.iv_back);
        this.f9648v = (ImageView) findViewById(C1467R.C1469id.iv_home);
        this.f9649w = (ImageView) findViewById(C1467R.C1469id.iv_menu);
        if (!XJDataManager.INSTANCE.isFullActivityIsStart()) {
            this.f9609d.sendEmptyMessage(1);
        }
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.f9638l.getLayoutParams();
        layoutParams.width = m19515i();
        layoutParams.height = m19516h();
        this.f9638l.setLayoutParams(layoutParams);
        this.f9635g = m19500x();
        LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) this.f9644r.getLayoutParams();
        float[] fArr = this.f9635g;
        layoutParams2.width = m19513k();
        int j = m19514j();
        if (j > layoutParams.height) {
            this.f9650x = SizeUtils.m23262a(48.0f);
        } else if (j == layoutParams.height) {
            this.f9650x = SizeUtils.m23262a(48.0f);
        }
        this.f9652z = m19514j();
        layoutParams2.height = m19514j() - this.f9650x;
        this.f9651y = layoutParams2.height;
        this.f9644r.setLayoutParams(layoutParams2);
        this.f9643q = new SurfaceHolder$CallbackC1588a();
        m19504t();
    }

    /* renamed from: o */
    private void m19509o() {
        if (!EventBus.m3448a().m3434b(this)) {
            EventBus.m3448a().m3446a(this);
        }
        m19501w();
        XJDataManager.INSTANCE.setFullActivityIsStart(true);
        SensorController.getInstance().initializeSensorManager(getSystemService("sensor"));
        LocationController.getInstance().initializeSensorManager(getSystemService(Headers.LOCATION), this);
        WifiManagerUtil.getInstance().initializeWifiManager(getApplicationContext().getSystemService("wifi"));
        MobileInfoManagerUtil.getInstance().initializeMobileInfoManager(getApplicationContext().getSystemService(ShippingInfoWidget.f12563f), this);
        m19538c();
        if (XJRenderActivityManager.INSTANCE.showAnimaDialog(this)) {
            XJRenderActivityManager.INSTANCE.getXjLoadingDialog().m19455a((Object) null);
        }
        m19499y();
        m19498z();
        m19535A();
        new XJRenderActivityPresenter(this);
        this.f9631B.mo14355a();
        this.f9630A = this.f9652z / this.f9651y;
    }

    /* renamed from: p */
    private void m19508p() {
        this.f9644r.setOnTouchListener(this);
        this.f9646t.setOnClickListener(new View.OnClickListener() { // from class: com.lbd.xj.ui.activity.XJRenderActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
            }
        });
        this.f9647u.setOnClickListener(new View.OnClickListener() { // from class: com.lbd.xj.ui.activity.XJRenderActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                XJRenderActivity.this.m19505s();
            }
        });
        this.f9648v.setOnClickListener(new View.OnClickListener() { // from class: com.lbd.xj.ui.activity.XJRenderActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                XJRenderActivity.this.m19506r();
            }
        });
        this.f9649w.setOnClickListener(new View.OnClickListener() { // from class: com.lbd.xj.ui.activity.XJRenderActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                XJRenderActivity.this.m19507q();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: q */
    public void m19507q() {
        FwManager.getInstance().sendInnerTask();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: r */
    public void m19506r() {
        FwManager.getInstance().sendInnerHome();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: s */
    public void m19505s() {
        FwManager.getInstance().sendInnerBack();
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        vmtools_input_event((int) (motionEvent.getX() / this.f9635g[2]), motionEvent.getY() * this.f9630A, motionEvent, this.f9635g[2]);
        return true;
    }

    /* renamed from: t */
    private void m19504t() {
        this.f9644r.getHolder().addCallback(this.f9643q);
    }

    /* renamed from: u */
    private void m19503u() {
        this.f9644r.getHolder().removeCallback(this.f9643q);
    }

    @Subscribe(m3389a = ThreadMode.MAIN)
    /* renamed from: a */
    public void m19527a(XNKJEvent.C3576f fVar) {
        LogUtils.m23734c("newEngin", "onEventMainThread - EventRunPermission" + fVar.f16172a);
        int i = fVar.f16172a;
        if (i == 4) {
            new XJFtLoginKickDialog(this, "互踢检测").show();
        } else if (i == 19) {
            new XJFtGlodNotEnoughDialog(this).show();
        } else if (i == 22) {
            FwManager.getInstance().showXJUserKickDialog(XJApp.getInstance().getApplicationContext(), true);
        }
    }

    /* renamed from: e */
    public String m19519e() {
        return getApplicationInfo().nativeLibraryDir;
    }

    @Override // com.lbd.xj.ui.activity.XJBaseANativityctivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        FwManager.getInstance().isFront = true;
        XJRenderActivityManager.INSTANCE.sendBroadcast(this, "onPause");
        this.f9636j = false;
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
    }

    @Override // com.lbd.xj.ui.activity.XJBaseANativityctivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        if (!this.f9639m) {
            this.f9639m = true;
            this.f9609d.sendEmptyMessageDelayed(3, 5000L);
        }
        m19512l();
        FwManager.getInstance().isFront = false;
        XJRenderActivityManager.INSTANCE.sendBroadcast(this, "onResume");
        if (XJLiveView.f9864c == 2) {
            XJLiveView.m19351c();
        }
        m19517g();
    }

    /* renamed from: h */
    public int m19516h() {
        try {
            return Integer.valueOf(XJRenderActivityManager.INSTANCE.getH()).intValue();
        } catch (Exception unused) {
            return 0;
        }
    }

    /* renamed from: i */
    public int m19515i() {
        try {
            return Integer.valueOf(XJRenderActivityManager.INSTANCE.getW()).intValue();
        } catch (Exception unused) {
            return 0;
        }
    }

    /* renamed from: j */
    public int m19514j() {
        try {
            return aeo.m13904h();
        } catch (Exception unused) {
            return 0;
        }
    }

    /* renamed from: k */
    public int m19513k() {
        try {
            return aeo.m13906g();
        } catch (Exception unused) {
            return 0;
        }
    }

    /* renamed from: v */
    private void m19502v() {
        if (!FloatingPermissionCompat.m14338a().m14337a(this)) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 26) {
            startForegroundService(new Intent(this, XJFloatService.class));
        } else {
            startService(new Intent(this, XJFloatService.class));
        }
    }

    /* renamed from: w */
    private void m19501w() {
        this.f9632C = new KeyCodeReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("XJKEYACTION");
        registerReceiver(this.f9632C, intentFilter);
    }

    /* renamed from: x */
    private float[] m19500x() {
        int i = m19515i();
        int h = m19516h();
        int[] a = RomSizeUtil.m13926a();
        float f = i;
        float f2 = f / a[0];
        float f3 = h;
        float f4 = f3 / a[1];
        float[] fArr = new float[3];
        if (f2 < f4) {
            fArr[0] = f;
            fArr[1] = a[1] * f2;
            fArr[2] = f2;
            Log.d(AppConfig.f9434a, "widthScale < heightScale screenWidth:" + i);
            Log.d(AppConfig.f9434a, "widthScale < heightScale       sizeP[1]:" + fArr[1]);
            Log.d(AppConfig.f9434a, "widthScale < heightScale widthScale:" + f2);
        } else {
            fArr[0] = a[0] * f4;
            fArr[1] = f3;
            fArr[2] = f4;
            Log.d(AppConfig.f9434a, "widthScale > heightScale sizeP[0]:" + fArr[0]);
            Log.d(AppConfig.f9434a, "widthScale > heightScale screenHeight:" + h);
            Log.d(AppConfig.f9434a, "widthScale > heightScale heightScale:" + f4);
        }
        return fArr;
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onBackPressed() {
        if (XJApp.click_return_phone_in_floatview) {
            moveTaskToBack(true);
            XJApp.click_return_phone_in_floatview = false;
            return;
        }
        this.f9637k.postDelayed(new Runnable() { // from class: com.lbd.xj.ui.activity.XJRenderActivity.6
            @Override // java.lang.Runnable
            public void run() {
                com.common.utils.log.LogUtils.m22036e("topActivity", "是桌面。。。。。。。。。。。。。");
                XJRenderActivity.this.moveTaskToBack(true);
            }
        }, 150L);
    }

    @Override // com.lbd.xj.ui.activity.XJBaseANativityctivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        com.common.utils.log.LogUtils.m22037e("Full onDestroy222222222222222222222222222222222222222222");
        KeyCodeReceiver keyCodeReceiver = this.f9632C;
        if (keyCodeReceiver != null) {
            unregisterReceiver(keyCodeReceiver);
        }
        ResolutionDialog dVar = this.f9645s;
        if (dVar != null) {
            dVar.dismiss();
        }
        if (EventBus.m3448a().m3434b(this)) {
            EventBus.m3448a().m3430c(this);
        }
        XJRenderActivityContract.AbstractC3331a aVar = this.f9631B;
        if (aVar != null) {
            aVar.mo14353b();
            this.f9631B = null;
        }
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(final int i, KeyEvent keyEvent) {
        if (i != 24 && i != 25) {
            return super.onKeyDown(i, keyEvent);
        }
        com.common.utils.log.LogUtils.m22038d("VMOS_INPUT", "event:" + keyEvent);
        this.f9607b.post(new Runnable() { // from class: com.lbd.xj.ui.activity.XJRenderActivity.7
            @Override // java.lang.Runnable
            public void run() {
                XJRenderActivity.this.vmtools_input_keyevent(i);
            }
        });
        return true;
    }

    @Subscribe(m3389a = ThreadMode.MAIN, m3388b = true)
    /* renamed from: a */
    public void m19530a(EventBusMessage acmVar) {
        String a = acmVar.m14349a();
        Log.d("sunya", "newFullActivity event=" + acmVar.m14349a());
        if (((a.hashCode() == 553527590 && a.equals("start_success")) ? (char) 0 : (char) 65535) == 0) {
            if (this.f9640n) {
                this.f9640n = false;
            }
            XJRenderActivityManager.INSTANCE.cancelMyFullDialog();
            Log.e("sunya", "启动成功 666666");
            XJDataManager.INSTANCE.saveHasdecompression(true);
            m19502v();
        }
    }

    @Subscribe(m3389a = ThreadMode.MAIN)
    /* renamed from: a */
    public void m19526a(XNKJEvent.C3577g gVar) {
        FwManager.getInstance().setRunScript(false);
    }

    @Subscribe(m3389a = ThreadMode.MAIN)
    /* renamed from: a */
    public void m19529a(KeyCodeEvent acpVar) {
        if (KeyCodeReceiver.f9484a.equals(acpVar.m14345a())) {
            onBackPressed();
        } else if (KeyCodeReceiver.f9485b.equals(acpVar.m14345a())) {
            Intent intent = new Intent("android.intent.action.MAIN");
            intent.setFlags(268435456);
            intent.addCategory("android.intent.category.HOME");
            startActivity(intent);
        }
    }

    @Subscribe(m3389a = ThreadMode.MAIN)
    /* renamed from: a */
    public void m19528a(ResolutionEvent acqVar) {
        m19534B();
    }

    /* renamed from: B */
    private void m19534B() {
        if (this.f9645s == null) {
            this.f9645s = new ResolutionDialog(this);
            this.f9645s.m19447a(new ResolutionDialog.AbstractC1606b() { // from class: com.lbd.xj.ui.activity.XJRenderActivity.8
                @Override // com.lbd.xj.ui.dialog.ResolutionDialog.AbstractC1606b
                /* renamed from: a */
                public void mo19443a(int i, int i2, int i3) {
                    Log.e("resolution_call", "sendata" + i + "--" + i2 + "--" + i3);
                    FwManager.getInstance().socketSetScreenXJ(i, i2, i3);
                }
            });
        }
        if (!this.f9645s.isShowing()) {
            this.f9645s.show();
        }
    }

    /* renamed from: m */
    public static boolean m19511m() {
        return f9629i;
    }
}
