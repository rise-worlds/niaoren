package com.angel.nrzs;

import android.app.Application;
import android.content.Context;
import android.os.Process;
import android.support.multidex.MultiDex;
import android.support.p003v4.app.ActivityCompat;
import android.text.TextUtils;
import android.util.Log;
import com.angel.nrzs.p017ui.activity.MainActivity;
import com.blankj.utilcode.util.CrashUtils;
import com.blankj.utilcode.util.LogUtils;
import com.cyjh.ddy.base.utils.CLog;
import com.cyjh.ddysdk.order.DdyOrderHelper;
import com.kaopu.download.kernel.DownloadServiceConnection;
import com.nrzs.data.DataApp;
import com.nrzs.libcommon.util.share.PreferenceUtil;
import com.nrzs.moudlepay.AliPayManeger;
import com.nrzs.p067ft.FloatApp;
import com.nrzs.umeng.UmengAnalyModule;
import com.nrzs.umeng.UmengModule;
import com.tencent.smtt.sdk.QbSdk;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import p110z1.ARouter;
import p110z1.Config;
import p110z1.GameApp;
import p110z1.NRZSFileConfig;

/* loaded from: classes.dex */
public class App extends Application {

    /* renamed from: a */
    public static boolean f419a = false;

    /* renamed from: b */
    public static boolean f420b = false;

    /* renamed from: c */
    public static boolean f421c = false;

    /* renamed from: d */
    protected static App f422d;

    /* renamed from: e */
    private String f423e;

    /* renamed from: f */
    private String f424f;

    /* renamed from: j */
    private void m25203j() {
    }

    /* renamed from: a */
    public static App m25213a() {
        if (f422d == null) {
            f422d = new App();
        }
        return f422d;
    }

    /* renamed from: a */
    public static String m25212a(int i) {
        Throwable th;
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new FileReader("/proc/" + i + "/cmdline"));
        } catch (Throwable th2) {
            th = th2;
            bufferedReader = null;
        }
        try {
            String readLine = bufferedReader.readLine();
            if (!TextUtils.isEmpty(readLine)) {
                readLine = readLine.trim();
            }
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return readLine;
        } catch (Throwable th3) {
            th = th3;
            try {
                th.printStackTrace();
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
                return null;
            } catch (Throwable th4) {
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                }
                throw th4;
            }
        }
    }

    @Override // android.content.ContextWrapper
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        m25211b();
        MultiDex.install(this);
        PreferenceUtil.m18517a(this);
        GameApp.m13000d().m13009a(this, getPackageName(), MainActivity.class);
    }

    /* renamed from: b */
    private void m25211b() {
        this.f423e = m25212a(Process.myPid());
        this.f424f = getPackageName();
        Log.i("TAG", "initProcess: " + this.f424f);
        String str = this.f423e;
        if ((str != null && str.contains("addon.arm64")) || this.f423e.contains("addon.arm32")) {
            f420b = true;
        }
        if (getPackageName().equals(this.f423e)) {
            f421c = true;
            f419a = true;
        }
        if ((this.f424f + ":channel64").equals(this.f423e)) {
            f421c = true;
        }
    }

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        f422d = this;
        m25210c();
    }

    /* renamed from: c */
    private void m25210c() {
        m25207f();
        if (!f420b) {
            m25208e();
        }
        if (f419a) {
            m25206g();
        }
        m25209d();
    }

    /* renamed from: d */
    private void m25209d() {
        CLog.mPrintLog = true;
        CLog.mLogPriority = 2;
        DdyOrderHelper.getInstance().initHTTPDNS(this, false);
    }

    /* renamed from: e */
    private void m25208e() {
        m25205h();
        Config.m12526a(this);
        if (ActivityCompat.checkSelfPermission(this, "android.permission.WRITE_EXTERNAL_STORAGE") == 0) {
            CrashUtils.m22433a(NRZSFileConfig.f16554l);
        }
        DataApp.m18939d().m18946a(this);
        FloatApp.m18899b().m18900a(this);
        LogUtils.m23772a().m23707a(false);
        new DownloadServiceConnection(this).bindDownloadService(null);
        AliPayManeger.m18507a().m18506a(this);
    }

    /* renamed from: f */
    private void m25207f() {
        GameApp.m13000d().m13011a();
    }

    /* renamed from: g */
    private void m25206g() {
        m25202k();
        QbSdk.initX5Environment(getApplicationContext(), new QbSdk.PreInitCallback() { // from class: com.angel.nrzs.App.1
            @Override // com.tencent.smtt.sdk.QbSdk.PreInitCallback
            public void onCoreInitFinished() {
            }

            @Override // com.tencent.smtt.sdk.QbSdk.PreInitCallback
            public void onViewInitFinished(boolean z) {
                Log.d("app", " onViewInitFinished is " + z);
            }
        });
        NRZSFileConfig amhVar = new NRZSFileConfig();
        amhVar.m12517a();
        amhVar.m12516a(this);
    }

    /* renamed from: h */
    private void m25205h() {
        ARouter.m1713a((Application) this);
    }

    /* renamed from: i */
    private void m25204i() {
        if (f421c) {
            UmengModule.m18413a(this, null);
            UmengAnalyModule.m18422a();
        }
    }

    /* renamed from: k */
    private void m25202k() {
        try {
            Class<?> cls = Class.forName("com.lbd.xj.app.XJApp");
            if (cls != null) {
                cls.getMethod("init", Context.class).invoke(cls.getMethod("getInstance", new Class[0]).invoke(new Object(), new Object[0]), this);
            }
        } catch (ClassNotFoundException unused) {
            Log.i("TAG", "initXJApp: 无需加载虚拟空间");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
