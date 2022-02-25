package com.cyjh.mobileanjian.ipc;

import android.app.Application;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.WindowManager;
import android.widget.Toast;
import com.cyjh.event.Injector;
import com.cyjh.event.UiTransHelper;
import com.cyjh.mobileanjian.ipc.interfaces.OnScriptListener;
import com.cyjh.mobileanjian.ipc.log.NativeLog;
import com.cyjh.mobileanjian.ipc.p043b.IRunner;
import com.cyjh.mqm.MQLanguageStub;
import com.cyjh.mqsdk.C1375R;
import com.cyjh.p045mq.p046a.MyApplication;
import com.cyjh.p045mq.sdk.entity.Script4Run;
import java.io.File;
import java.util.concurrent.ArrayBlockingQueue;

/* renamed from: com.cyjh.mobileanjian.ipc.e */
/* loaded from: classes.dex */
public final class ScriptRunnerLite implements IRunner {

    /* renamed from: i */
    private static final int f8245i = 1;

    /* renamed from: j */
    private static final int f8246j = 2;

    /* renamed from: k */
    private static final int f8247k = 3;

    /* renamed from: l */
    private static final int f8248l = 25;

    /* renamed from: a */
    public Script4Run f8249a;

    /* renamed from: b */
    public OnScriptListener f8250b;

    /* renamed from: g */
    public C1270c f8255g;

    /* renamed from: m */
    private MQLanguageStub f8257m;

    /* renamed from: c */
    public volatile boolean f8251c = false;

    /* renamed from: d */
    boolean f8252d = false;

    /* renamed from: e */
    public boolean f8253e = false;

    /* renamed from: f */
    public boolean f8254f = true;

    /* renamed from: h */
    public ArrayBlockingQueue<C1268a> f8256h = new ArrayBlockingQueue<>(4);

    /* renamed from: n */
    private Handler f8258n = new Handler(Looper.getMainLooper()) { // from class: com.cyjh.mobileanjian.ipc.e.1
        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    if (ScriptRunnerLite.this.f8250b != null) {
                        ScriptRunnerLite.this.f8250b.onStartScript();
                        return;
                    }
                    return;
                case 2:
                    if (ScriptRunnerLite.this.f8250b != null) {
                        ScriptRunnerLite.this.f8250b.onStopScript(message.arg1, (String) message.obj);
                        return;
                    }
                    return;
                case 3:
                    Toast.makeText(MyApplication.f8788g.getApplicationContext(), (String) message.obj, 1).show();
                    return;
                default:
                    return;
            }
        }
    };

    /* renamed from: o */
    private MQLanguageStub.MQAuxiliary f8259o = null;

    /* renamed from: b */
    private void m21002b(String str) {
        this.f8258n.obtainMessage(3, str).sendToTarget();
    }

    /* renamed from: a */
    private void m21007a(Script4Run script4Run) {
        if (script4Run != null) {
            this.f8249a = script4Run;
        }
    }

    /* renamed from: a */
    private void m21008a(OnScriptListener onScriptListener) {
        if (onScriptListener != null) {
            this.f8250b = onScriptListener;
        }
    }

    /* renamed from: a */
    public final synchronized boolean m21013a() {
        return this.f8251c;
    }

    @Override // com.cyjh.mobileanjian.ipc.p043b.IRunner
    /* renamed from: b */
    public final void mo21004b() {
        if (this.f8251c) {
            OnScriptListener onScriptListener = this.f8250b;
            if (onScriptListener != null) {
                onScriptListener.onScriptIsRunning();
                return;
            }
            return;
        }
        try {
            if (this.f8249a.isFengwoScript) {
                this.f8256h.put(new C1268a(this, 0, ""));
                return;
            }
            new C1269b(this, (byte) 0).start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override // com.cyjh.mobileanjian.ipc.p043b.IRunner
    /* renamed from: c */
    public final void mo21001c() {
        m20993g().Resume();
    }

    @Override // com.cyjh.mobileanjian.ipc.p043b.IRunner
    /* renamed from: d */
    public final void mo20998d() {
        m20993g().Pause();
    }

    @Override // com.cyjh.mobileanjian.ipc.p043b.IRunner
    /* renamed from: e */
    public final void mo20996e() {
        m20993g().Stop();
    }

    /* renamed from: a */
    private void m21005a(boolean z, int i, int i2, int i3, int i4) {
        m20992h().SetImageCrop(z, i, i2, i3, i4);
    }

    /* renamed from: g */
    public final MQLanguageStub m20993g() {
        if (this.f8257m == null) {
            this.f8257m = new MQLanguageStub();
            new StringBuilder("Application: ").append(MyApplication.f8788g);
            File file = new File(Environment.getExternalStorageDirectory(), MyApplication.f8788g.getPackageName());
            if (!file.exists()) {
                file.mkdir();
            }
            this.f8257m.SetLocalDir(Environment.getExternalStorageDirectory().getAbsolutePath(), file.getAbsolutePath(), new File(MyApplication.f8788g.getApplicationContext().getFilesDir().getParent(), "lib").getAbsolutePath());
            this.f8257m.SetWriteLog2File(true);
        }
        return this.f8257m;
    }

    /* renamed from: h */
    public final MQLanguageStub.MQAuxiliary m20992h() {
        if (this.f8259o == null) {
            MQLanguageStub g = m20993g();
            g.getClass();
            this.f8259o = new MQLanguageStub.MQAuxiliary();
        }
        return this.f8259o;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: ScriptRunnerLite.java */
    /* renamed from: com.cyjh.mobileanjian.ipc.e$c */
    /* loaded from: classes.dex */
    public class C1270c extends Thread {
        public C1270c(String str) {
            super(str);
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public final void run() {
            Looper.prepare();
            ScriptRunnerLite.this.f8253e = true;
            while (MyApplication.f8788g != null) {
                try {
                    C1268a take = ScriptRunnerLite.this.f8256h.take();
                    new StringBuilder("Take Request:\n").append(take);
                    int i = take.f8268h;
                    if (i != 17) {
                        switch (i) {
                            case 0:
                                ScriptRunnerLite.m21010a(ScriptRunnerLite.this);
                                continue;
                            case 1:
                                ScriptRunnerLite.this.m20993g().InitRunner(take.f8267g, take.f8269i);
                                ScriptRunnerLite.this.f8252d = true;
                                continue;
                            case 2:
                                ScriptRunnerLite.this.m20993g().Request(take.f8269i);
                                continue;
                            default:
                                continue;
                        }
                    } else {
                        ScriptRunnerLite.this.m20993g().SetSid(Long.valueOf(take.f8269i).longValue());
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Looper.myLooper().quit();
            Looper.loop();
            ScriptRunnerLite.this.f8253e = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: ScriptRunnerLite.java */
    /* renamed from: com.cyjh.mobileanjian.ipc.e$b */
    /* loaded from: classes.dex */
    public class C1269b extends Thread {
        private C1269b() {
        }

        public /* synthetic */ C1269b(ScriptRunnerLite eVar, byte b) {
            this();
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public final void run() {
            if (ScriptRunnerLite.this.f8249a != null) {
                Looper.prepare();
                ScriptRunnerLite.m21010a(ScriptRunnerLite.this);
                Looper.myLooper().quit();
                Looper.loop();
            }
        }
    }

    /* renamed from: i */
    private void m20991i() {
        int i;
        if (this.f8249a != null) {
            this.f8251c = true;
            this.f8258n.obtainMessage(1).sendToTarget();
            m21002b(MyApplication.f8788g.getApplicationContext().getString(C1375R.string.toast_on_start_script));
            m20994f();
            if (this.f8249a.isFengwoScript) {
                i = m20993g().Run(new byte[]{0}, this.f8249a.atcPath, this.f8249a.uiCfgPath);
            } else {
                i = m20993g().Run(this.f8249a.lcPath, this.f8249a.atcPath, this.f8249a.uiCfgPath, this.f8249a.trialTime, this.f8249a.repeat, this.f8249a.getEncryptKey());
            }
            if (i == 102) {
                m21002b(String.format(MyApplication.f8788g.getApplicationContext().getString(C1375R.string.toast_run_failed), Integer.valueOf(i)));
            }
            Handler handler = this.f8258n;
            if (handler != null) {
                Message obtainMessage = handler.obtainMessage();
                obtainMessage.what = 2;
                obtainMessage.arg1 = i;
                obtainMessage.obj = NativeLog.getExtraLog();
                this.f8258n.sendMessage(obtainMessage);
            }
            m21002b(MyApplication.f8788g.getApplicationContext().getString(C1375R.string.toast_on_stop_script));
            UiTransHelper.m21090c();
            Injector.release();
            NativeLog.reset();
            this.f8251c = false;
        }
    }

    /* renamed from: a */
    private void m21011a(Application application, String str) {
        try {
            if (!this.f8253e) {
                this.f8255g = new C1270c("ScriptRunnerLite_Request");
                this.f8255g.start();
            }
            this.f8256h.put(new C1268a(application, 1, str));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public final void m21006a(String str) {
        try {
            if (this.f8252d) {
                this.f8256h.put(new C1268a(this, 2, str));
                return;
            }
            Injector.OnResponse(25, "NOT_INIT_USER");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    private void m21012a(long j) {
        try {
            m20993g().SetSid(Long.valueOf(new C1268a(this, 17, String.valueOf(j)).f8269i).longValue());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: c */
    private void m20999c(String str) {
        try {
            m20993g().SetDeviceSessionId(new C1268a(this, 18, str).f8269i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: j */
    private void m20990j() {
        this.f8254f = false;
        mo20996e();
        try {
            this.f8256h.put(new C1268a(this, 16, ""));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: ScriptRunnerLite.java */
    /* renamed from: com.cyjh.mobileanjian.ipc.e$a */
    /* loaded from: classes.dex */
    public class C1268a {

        /* renamed from: a */
        public static final int f8261a = 0;

        /* renamed from: b */
        public static final int f8262b = 1;

        /* renamed from: c */
        public static final int f8263c = 2;

        /* renamed from: d */
        public static final int f8264d = 16;

        /* renamed from: e */
        public static final int f8265e = 17;

        /* renamed from: f */
        public static final int f8266f = 18;

        /* renamed from: g */
        public Application f8267g;

        /* renamed from: h */
        public int f8268h;

        /* renamed from: i */
        public String f8269i;

        public C1268a(ScriptRunnerLite eVar, int i, String str) {
            this(null, i, str);
        }

        public C1268a(Application application, int i, String str) {
            this.f8267g = application;
            this.f8268h = i;
            this.f8269i = str;
        }

        public final String toString() {
            return String.format("What: %d\nParam: %s", Integer.valueOf(this.f8268h), this.f8269i);
        }
    }

    /* renamed from: f */
    public final void m20994f() {
        m20992h().SetScreenRotation(((WindowManager) MyApplication.f8788g.getSystemService("window")).getDefaultDisplay().getRotation());
    }

    /* renamed from: a */
    static /* synthetic */ void m21010a(ScriptRunnerLite eVar) {
        int i;
        if (eVar.f8249a != null) {
            eVar.f8251c = true;
            eVar.f8258n.obtainMessage(1).sendToTarget();
            eVar.m21002b(MyApplication.f8788g.getApplicationContext().getString(C1375R.string.toast_on_start_script));
            eVar.m20994f();
            if (eVar.f8249a.isFengwoScript) {
                i = eVar.m20993g().Run(new byte[]{0}, eVar.f8249a.atcPath, eVar.f8249a.uiCfgPath);
            } else {
                i = eVar.m20993g().Run(eVar.f8249a.lcPath, eVar.f8249a.atcPath, eVar.f8249a.uiCfgPath, eVar.f8249a.trialTime, eVar.f8249a.repeat, eVar.f8249a.getEncryptKey());
            }
            if (i == 102) {
                eVar.m21002b(String.format(MyApplication.f8788g.getApplicationContext().getString(C1375R.string.toast_run_failed), Integer.valueOf(i)));
            }
            Handler handler = eVar.f8258n;
            if (handler != null) {
                Message obtainMessage = handler.obtainMessage();
                obtainMessage.what = 2;
                obtainMessage.arg1 = i;
                obtainMessage.obj = NativeLog.getExtraLog();
                eVar.f8258n.sendMessage(obtainMessage);
            }
            eVar.m21002b(MyApplication.f8788g.getApplicationContext().getString(C1375R.string.toast_on_stop_script));
            UiTransHelper.m21090c();
            Injector.release();
            NativeLog.reset();
            eVar.f8251c = false;
        }
    }
}
