package com.cyjh.mobileanjian.ipc;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.p003v4.content.LocalBroadcastManager;
import com.cyjh.ddy.media.p035a.ResultTypeConstant;
import com.cyjh.mobileanjian.ipc.stuff.IntentActions;
import com.cyjh.mobileanjian.ipc.utils.RootUtil;
import com.cyjh.mobileanjian.ipc.view.ExToast;
import com.cyjh.mqsdk.C1375R;
import com.cyjh.p045mq.p046a.MyApplication;
import com.cyjh.p045mq.sdk.MqRunner;
import com.goldcoast.sdk.domain.AnalyseResult;
import com.goldcoast.sdk.domain.EntryPoint;
import java.io.File;
import p110z1.C4985cm;

/* renamed from: com.cyjh.mobileanjian.ipc.c */
/* loaded from: classes.dex */
public final class RootManager {

    /* renamed from: a */
    public static final int f8217a = 1;

    /* renamed from: b */
    public static final int f8218b = 2;

    /* renamed from: h */
    private static RootManager f8219h = null;

    /* renamed from: j */
    private static final String f8220j = "3rd.need.or.not.m";

    /* renamed from: g */
    public int f8225g;

    /* renamed from: d */
    boolean f8222d = false;

    /* renamed from: i */
    private boolean f8226i = false;

    /* renamed from: e */
    boolean f8223e = false;

    /* renamed from: f */
    boolean f8224f = false;

    /* renamed from: c */
    Handler f8221c = new Handler(Looper.getMainLooper()) { // from class: com.cyjh.mobileanjian.ipc.c.1
        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            if (MyApplication.f8790i != null) {
                MyApplication.f8790i.onRootProgress((String) message.obj, message.what);
            }
        }
    };

    private RootManager() {
    }

    /* renamed from: a */
    public static synchronized RootManager m21044a() {
        RootManager cVar;
        synchronized (RootManager.class) {
            if (f8219h == null) {
                f8219h = new RootManager();
            }
            cVar = f8219h;
        }
        return cVar;
    }

    /* renamed from: d */
    private void m21034d() {
        this.f8225g = 2;
    }

    /* renamed from: e */
    private boolean m21032e() {
        return this.f8224f && this.f8223e;
    }

    /* renamed from: b */
    public final void m21039b() {
        if (!this.f8226i) {
            this.f8226i = true;
            if (RootUtil.isRoot()) {
                RootShell.m21029a().m21024a(MqRunner.getInstance());
            } else {
                new StringBuilder("check EntryPoint ").append(EntryPoint.instance());
                if (!(EntryPoint.instance() == null || MyApplication.f8790i == null)) {
                    if (this.f8222d) {
                        Handler handler = this.f8221c;
                        handler.obtainMessage(1, "3rd root is going on now." + EntryPoint.class.getSimpleName()).sendToTarget();
                    } else {
                        File file = new File(MyApplication.f8788g.getFilesDir(), f8220j);
                        if (!file.exists()) {
                            NetworkInfo activeNetworkInfo = ((ConnectivityManager) MyApplication.f8788g.getSystemService("connectivity")).getActiveNetworkInfo();
                            if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
                                if (!this.f8224f) {
                                    this.f8222d = true;
                                    Handler handler2 = this.f8221c;
                                    handler2.obtainMessage(1, "Now start 3rd root using " + EntryPoint.class.getSimpleName()).sendToTarget();
                                    new C12623("third_root_request", file).start();
                                } else if (this.f8223e) {
                                    this.f8221c.post(new RunnableC12612());
                                } else {
                                    this.f8221c.obtainMessage(5, C4985cm.f20833c).sendToTarget();
                                }
                            }
                        }
                        this.f8221c.obtainMessage(5, "failed because mark file").sendToTarget();
                    }
                }
            }
            this.f8226i = false;
        }
    }

    /* renamed from: f */
    private void m21030f() {
        if (this.f8222d) {
            Handler handler = this.f8221c;
            handler.obtainMessage(1, "3rd root is going on now." + EntryPoint.class.getSimpleName()).sendToTarget();
            return;
        }
        File file = new File(MyApplication.f8788g.getFilesDir(), f8220j);
        if (!file.exists()) {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) MyApplication.f8788g.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
                if (!this.f8224f) {
                    this.f8222d = true;
                    Handler handler2 = this.f8221c;
                    handler2.obtainMessage(1, "Now start 3rd root using " + EntryPoint.class.getSimpleName()).sendToTarget();
                    new C12623("third_root_request", file).start();
                    return;
                } else if (this.f8223e) {
                    this.f8221c.post(new RunnableC12612());
                    return;
                } else {
                    this.f8221c.obtainMessage(5, C4985cm.f20833c).sendToTarget();
                    return;
                }
            }
        }
        this.f8221c.obtainMessage(5, "failed because mark file").sendToTarget();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: RootManager.java */
    /* renamed from: com.cyjh.mobileanjian.ipc.c$2 */
    /* loaded from: classes.dex */
    public final class RunnableC12612 implements Runnable {
        RunnableC12612() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            ExToast.makeText(MyApplication.f8788g.getApplicationContext(), MyApplication.f8788g.getApplicationContext().getString(C1375R.string.toast_script_engine_failed_start), 3500).show();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: RootManager.java */
    /* renamed from: com.cyjh.mobileanjian.ipc.c$3 */
    /* loaded from: classes.dex */
    public final class C12623 extends Thread {

        /* renamed from: a */
        final /* synthetic */ File f8229a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C12623(String str, File file) {
            super(str);
            this.f8229a = file;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public final void run() {
            try {
                if (!this.f8229a.exists()) {
                    this.f8229a.createNewFile();
                }
                EntryPoint.instance().analyse(new AnalyseResult() { // from class: com.cyjh.mobileanjian.ipc.c.3.1
                    @Override // com.goldcoast.sdk.domain.AnalyseResult
                    public final void onProgress(String str) {
                    }

                    @Override // com.goldcoast.sdk.domain.AnalyseResult
                    public final void onSuccess(String str) {
                        if (C12623.this.f8229a.exists()) {
                            C12623.this.f8229a.delete();
                        }
                        RootManager.this.f8222d = false;
                        RootManager.this.f8224f = true;
                        RootManager.this.f8223e = true;
                        EntryPoint.instance().exec(new String[]{"chmod 777 /dev/input/*", MyApplication.m20565a().getAbsolutePath()});
                        RootManager.this.f8221c.sendMessageDelayed(RootManager.this.f8221c.obtainMessage(3, str), 10000L);
                    }

                    @Override // com.goldcoast.sdk.domain.AnalyseResult
                    public final void onFailed(String str) {
                        RootManager.this.f8222d = false;
                        if (!RootManager.this.f8224f) {
                            RootManager.this.f8221c.obtainMessage(3, str).sendToTarget();
                        }
                        RootManager.this.f8224f = true;
                        RootManager.m21036c();
                    }

                    @Override // com.goldcoast.sdk.domain.AnalyseResult
                    public final void onException(String str) {
                        RootManager.this.f8222d = false;
                        if (!RootManager.this.f8224f) {
                            RootManager.this.f8221c.obtainMessage(3, str).sendToTarget();
                        }
                        RootManager.this.f8224f = true;
                        RootManager.m21036c();
                    }
                });
            } catch (Exception unused) {
                RootManager cVar = RootManager.this;
                cVar.f8222d = false;
                cVar.f8224f = true;
                cVar.f8221c.obtainMessage(3, "Exception occurs when startThirdRoot()").sendToTarget();
            }
        }
    }

    /* renamed from: a */
    private static boolean m21043a(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    /* renamed from: a */
    private void m21041a(String str) {
        this.f8221c.obtainMessage(2, str).sendToTarget();
        this.f8221c.removeMessages(3);
    }

    /* renamed from: b */
    private void m21037b(String str) {
        this.f8221c.obtainMessage(3, str).sendToTarget();
    }

    /* renamed from: a */
    private static void m21040a(boolean z) {
        Intent intent = new Intent(IntentActions.TRY_ACTIVE_RUNNER);
        intent.putExtra(IntentActions.EXTRA_ACTIVITE_TYPE, "2");
        intent.putExtra(IntentActions.EXTRA_ACTIVE_RESULT, z ? "1" : ResultTypeConstant.f7213z);
        LocalBroadcastManager.getInstance(MyApplication.f8788g).sendBroadcast(intent);
    }

    /* renamed from: c */
    static /* synthetic */ void m21036c() {
        Intent intent = new Intent(IntentActions.TRY_ACTIVE_RUNNER);
        intent.putExtra(IntentActions.EXTRA_ACTIVITE_TYPE, "2");
        intent.putExtra(IntentActions.EXTRA_ACTIVE_RESULT, ResultTypeConstant.f7213z);
        LocalBroadcastManager.getInstance(MyApplication.f8788g).sendBroadcast(intent);
    }
}
