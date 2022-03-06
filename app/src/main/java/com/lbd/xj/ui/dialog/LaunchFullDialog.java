package com.lbd.xj.ui.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.net.LocalServerSocket;
import android.net.LocalSocket;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.common.utils.SharedPreferencesUtil;
import com.common.utils.log.LogUtils;
import com.lbd.xj.C1467R;
import com.lbd.xj.app.AppConfig;
import java.io.DataInputStream;
import p110z1.EventBus;
import p110z1.EventBusMessage;
import p110z1.LogCarshUtil;
import p110z1.XJRomStateRepository;
import p110z1.acf;

/* renamed from: com.lbd.xj.ui.dialog.c */
/* loaded from: classes.dex */
public class LaunchFullDialog extends AlertDialog {

    /* renamed from: a */
    public boolean f9717a;

    /* renamed from: e */
    public LocalServerSocket f9721e;

    /* renamed from: f */
    private ProgressBar f9722f;

    /* renamed from: g */
    private TextView f9723g;

    /* renamed from: i */
    private int f9725i;

    /* renamed from: j */
    private Context f9726j;

    /* renamed from: h */
    private int f9724h = 0;

    /* renamed from: b */
    public boolean f9718b = true;

    /* renamed from: c */
    public boolean f9719c = true;

    /* renamed from: d */
    public boolean f9720d = false;

    /* renamed from: b */
    static /* synthetic */ int m19453b(LaunchFullDialog cVar) {
        int i = cVar.f9724h + 1;
        cVar.f9724h = i;
        return i;
    }

    /* renamed from: d */
    static /* synthetic */ int m19451d(LaunchFullDialog cVar) {
        int i = cVar.f9725i + 1;
        cVar.f9725i = i;
        return i;
    }

    public LaunchFullDialog(Context context) {
        super(context, C1467R.style.MyDialog);
        this.f9726j = context;
        setCancelable(false);
    }

    /* renamed from: a */
    private void m19461a() {
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.gravity = 17;
        attributes.width = -1;
        attributes.height = -1;
        getWindow().getDecorView().setPadding(0, 0, 0, 0);
        getWindow().setAttributes(attributes);
        setContentView(LayoutInflater.from(getContext()).inflate(C1467R.layout.xj_fragment_loading, (ViewGroup) null));
        this.f9722f = (ProgressBar) findViewById(C1467R.C1469id.xj_bar);
        this.f9723g = (TextView) findViewById(C1467R.C1469id.xj_tv_tips);
    }

    /* renamed from: a */
    public void m19459a(int i, String str) {
        TextView textView = this.f9723g;
        if (textView != null) {
            textView.setText(str);
        }
        m19460a(i);
    }

    /* renamed from: a */
    public void m19460a(final int i) {
        getWindow().getDecorView().post(new Runnable() { // from class: com.lbd.xj.ui.dialog.c.1
            @Override // java.lang.Runnable
            public void run() {
                if (LaunchFullDialog.this.f9722f != null) {
                    LaunchFullDialog.this.f9722f.setProgress(i);
                }
            }
        });
    }

    /* renamed from: a */
    public void m19458a(long j) {
        final int i = (int) (j / 100);
        getWindow().getDecorView().post(new Runnable() { // from class: com.lbd.xj.ui.dialog.c.2
            @Override // java.lang.Runnable
            public void run() {
                LaunchFullDialog.m19453b(LaunchFullDialog.this);
                if (LaunchFullDialog.this.f9724h >= 99) {
                    LaunchFullDialog.this.f9724h = 99;
                    LaunchFullDialog.m19451d(LaunchFullDialog.this);
                    if (LaunchFullDialog.this.f9725i >= 200) {
                        LogUtils.m22037e("启动超时2");
                        XJRomStateRepository.m12503a(0, "window overtime");
                        LaunchFullDialog.this.cancel();
                    }
                }
                LaunchFullDialog cVar = LaunchFullDialog.this;
                cVar.m19459a(cVar.f9724h, "后台挂机正在启动");
                if (LaunchFullDialog.this.isShowing()) {
                    LaunchFullDialog.this.getWindow().getDecorView().postDelayed(this, i);
                }
            }
        });
    }

    @Override // android.app.Dialog
    public void show() {
        super.show();
        Activity activity = (Activity) this.f9726j;
        if (activity != null && !activity.isFinishing()) {
            m19461a();
        }
    }

    /* renamed from: a */
    public void m19455a(Object obj) {
        m19454b();
    }

    /* JADX WARN: Type inference failed for: r0v6, types: [com.lbd.xj.ui.dialog.c$3] */
    /* renamed from: b */
    private void m19454b() {
        this.f9717a = SharedPreferencesUtil.getInstance().getBoolean(acf.f15186k).booleanValue();
        if (this.f9717a) {
            long longValue = SharedPreferencesUtil.getInstance().getLong(acf.f15187l).longValue();
            if (longValue == 0) {
                this.f9717a = false;
            }
            Log.d("sunya", "假的进度条开始");
            m19458a(longValue);
        }
        if (!this.f9720d) {
            this.f9720d = true;
            new Thread() { // from class: com.lbd.xj.ui.dialog.c.3
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    try {
                        LaunchFullDialog.this.f9721e = new LocalServerSocket(AppConfig.f9438e);
                        Log.d("sunya", "runstatus host进度： 开始监听 " + AppConfig.f9438e + " socket=" + LaunchFullDialog.this.f9721e);
                        while (LaunchFullDialog.this.f9718b) {
                            Log.d("sunya", "runstatus new MyDealProgressThread isInitBoot=" + LaunchFullDialog.this.f9717a + " wait connect");
                            LocalSocket accept = LaunchFullDialog.this.f9721e.accept();
                            Log.d("sunya", "runstatus new MyDealProgressThread isInitBoot=" + LaunchFullDialog.this.f9717a + " got new connect");
                            new C1603a(accept, LaunchFullDialog.this.f9717a).start();
                            Log.d("sunya", "runstatus new MyDealProgressThread end goto start");
                        }
                        Log.d("sunya", "host进度： 监听runstatus 结束");
                    } catch (Exception e) {
                        Log.d("sunya-DEBUG", "initCompliteThread e=" + e.toString());
                        EventBus.m3448a().m3423f(new EventBusMessage("start_overtime"));
                        e.printStackTrace();
                    }
                    LaunchFullDialog.this.f9720d = false;
                }
            }.start();
        }
    }

    /* compiled from: LaunchFullDialog.java */
    /* renamed from: com.lbd.xj.ui.dialog.c$a */
    /* loaded from: classes.dex */
    public class C1603a extends Thread {

        /* renamed from: a */
        boolean f9732a;

        /* renamed from: b */
        LocalSocket f9733b;

        C1603a(LocalSocket localSocket, boolean z) {
            this.f9733b = localSocket;
            this.f9732a = z;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            Log.d("sunya", "runstatus 等待读取进度 runstart");
            long currentTimeMillis = System.currentTimeMillis();
            try {
                DataInputStream dataInputStream = new DataInputStream(this.f9733b.getInputStream());
                while (true) {
                    Log.d("sunya", "runstatus myfulldialog runstatus 等待读取进度 while");
                    final int readInt = dataInputStream.readInt();
                    Log.d("sunya", "runstatus myfulldialog runstatus 读取到当前进度是 " + readInt);
                    if (readInt == 666666) {
                        LogCarshUtil.m13951d().m13952c();
                        EventBus.m3448a().m3423f(new EventBusMessage("start_success"));
                        LaunchFullDialog.this.f9718b = false;
                        SharedPreferencesUtil.getInstance().putBooleanValue(acf.f15186k, true);
                        SharedPreferencesUtil.getInstance().putLongValue(acf.f15187l, Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
                        Log.d("sunya", "runstatus runstatus 读取到66666返回");
                        return;
                    } else if (readInt == -1) {
                        XJRomStateRepository.m12503a(0, "start_overtime");
                        EventBus.m3448a().m3423f(new EventBusMessage("start_overtime"));
                        SharedPreferencesUtil.getInstance().putLongValue(acf.f15187l, Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
                        LogCarshUtil.m13951d().m13952c();
                        return;
                    } else if (!this.f9732a) {
                        LaunchFullDialog.this.getWindow().getDecorView().post(new Runnable() { // from class: com.lbd.xj.ui.dialog.c.a.1
                            @Override // java.lang.Runnable
                            public void run() {
                                LaunchFullDialog.this.m19460a(readInt);
                            }
                        });
                    }
                }
            } catch (Exception e) {
                Log.d("sunya", "runstatus err=" + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
