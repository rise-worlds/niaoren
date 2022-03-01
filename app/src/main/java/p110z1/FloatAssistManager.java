package p110z1;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.widget.LinearLayout;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.cyjh.mobileanjian.ipc.stuff.ScriptSuffix;
import com.cyjh.mobileanjian.ipc.utils.Util;
import com.lody.virtual.client.core.VirtualCore;
import com.lody.virtual.client.ipc.VActivityManager;
import com.lody.virtual.remote.InstalledAppInfo;
import com.nrzs.base.router.provider.InvokeProvider;
import com.nrzs.base.router.provider.ProviderFactory;
import com.nrzs.data.base.BaseResponse;
import com.nrzs.data.game.bean.HeartInfo;
import com.nrzs.data.p066ft.bean.AssistInfo;
import com.nrzs.data.p066ft.bean.EnginInteraRequestInfo;
import com.nrzs.data.p066ft.bean.ScriptPathInfo;
import com.nrzs.ft.FloatApp;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.Callable;
import org.apache.commons.p105io.FileUtils;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Expand;
import org.apache.tools.ant.taskdefs.optional.vss.MSVSSConstants;

/* renamed from: z1.ani */
/* loaded from: classes3.dex */
public class FloatAssistManager {

    /* renamed from: h */
    private static final Object f16720h = new Object();

    /* renamed from: i */
    private static FloatAssistManager f16721i;

    /* renamed from: b */
    private ScriptPathInfo f16723b;

    /* renamed from: c */
    private IScriptUiModel f16724c;

    /* renamed from: d */
    private EnginInteraRequestInfo f16725d;

    /* renamed from: f */
    private Handler f16727f;

    /* renamed from: a */
    public volatile boolean f16722a = false;

    /* renamed from: e */
    private int f16726e = 0;

    /* renamed from: g */
    private EnginSDKCallback f16728g = new EnginSDKCallback() { // from class: z1.ani.1
        @Override // p110z1.EnginSDKCallback
        /* renamed from: b */
        public void mo12386b() {
        }

        @Override // p110z1.EnginSDKCallback
        /* renamed from: c */
        public void mo12383c() {
        }

        @Override // p110z1.EnginSDKCallback
        /* renamed from: d */
        public void mo12380d() {
        }

        @Override // p110z1.EnginSDKCallback
        /* renamed from: a */
        public void mo12389a(int i, String str, int i2) {
            FloatAssistManager.this.m12425a(i, str, i2);
        }

        @Override // p110z1.EnginSDKCallback
        /* renamed from: a */
        public void mo12391a(int i) {
            if (i == 115) {
                FloatAssistManager.this.m12396j();
            }
        }

        @Override // p110z1.EnginSDKCallback
        /* renamed from: a */
        public void mo12387a(String str, int i) {
            LogUtils.m23734c("newengin", "onRootProgress:s=" + str + ",i=" + i);
            switch (i) {
                case 1:
                case 2:
                case 3:
                default:
                    return;
                case 4:
                    ToastUtils.m23030a("无ROOT权限，请到【我的】页面-【更换模式】为免ROOT模式");
                    return;
            }
        }

        @Override // p110z1.EnginSDKCallback
        /* renamed from: b */
        public void mo12385b(int i) {
            LogUtils.m23734c("newEngin", "onEngineStart:" + i);
            if (LoginManager.m12620d().m12606r()) {
                LogUtils.m23734c("newEngin", "onEngineStart 2:" + i);
                EnginInteraRequestInfo enginInteraRequestInfo = new EnginInteraRequestInfo();
                enginInteraRequestInfo.AppSign = Util.getAppSinature(FloatApp.m18899b().m18901a());
                enginInteraRequestInfo.Command = 1;
                enginInteraRequestInfo.SessionId = FtUserManager.m12790g().m12794c();
                enginInteraRequestInfo.UserId = FtUserManager.m12790g().m12797a();
                enginInteraRequestInfo.DesKey = FtUserManager.m12790g().m12793d();
                enginInteraRequestInfo.dycIp = LocalHttp.f16542c;
                enginInteraRequestInfo.ScriptCacheRPath = ShareVal.f16591a + File.separatorChar + MSVSSConstants.SS_EXE;
                FloatAssistManager.this.m12420a(enginInteraRequestInfo, "钥匙激活成功");
            }
        }

        @Override // p110z1.EnginSDKCallback
        /* renamed from: a */
        public void mo12392a(float f, int i) {
            EventBus.m3448a().m3427d(new FtEvent.C3563a(f, i));
        }

        @Override // p110z1.EnginSDKCallback
        /* renamed from: a */
        public void mo12393a() {
            AssistRunTJRepository.m12779c().m12784a(FloatDataManager.m12352j().m12357e(), FloatDataManager.m12352j().f16748d);
            int i = FloatDataManager.m12352j().f16748d;
            EventBus.m3448a().m3427d(new FtEvent.C3564b(1));
        }

        @Override // p110z1.EnginSDKCallback
        /* renamed from: a */
        public void mo12390a(int i, String str) {
            Log.d("setCurrentStopType", "1 currentStopType errorCode:" + i + ",,errorMsg:" + str);
            AssistRunTJRepository.m12779c().m12785a();
            if (i == 102) {
                ToastUtils.m23030a("脚本初始化异常，请尝试重启");
            }
            Log.d("setCurrentStopType", "2 currentStopType errorCode:" + i + ",,errorMsg:" + str);
            EventBus.m3448a().m3427d(new FtEvent.C3564b(2));
            Log.d("setCurrentStopType", "3 currentStopType errorCode:" + i + ",,errorMsg:" + str);
        }

        @Override // p110z1.EnginSDKCallback
        /* renamed from: a */
        public void mo12388a(String str) {
            Message obtainMessage = FloatAssistManager.this.f16727f.obtainMessage();
            obtainMessage.what = 256;
            obtainMessage.obj = str;
            FloatAssistManager.this.f16727f.sendMessage(obtainMessage);
        }

        @Override // p110z1.EnginSDKCallback
        /* renamed from: b */
        public void mo12384b(String str) {
            VirtualCore.get().killApp(str, FloatDataManager.m12352j().m12359c());
        }

        @Override // p110z1.EnginSDKCallback
        /* renamed from: e */
        public String mo12379e() {
            return FloatDataManager.m12352j().m12362b();
        }

        @Override // p110z1.EnginSDKCallback
        /* renamed from: f */
        public String mo12378f() {
            List<InstalledAppInfo> installedApps = VirtualCore.get().getInstalledApps(0);
            if (installedApps == null || installedApps.isEmpty()) {
                return null;
            }
            StringBuilder sb = new StringBuilder();
            int i = 0;
            for (InstalledAppInfo installedAppInfo : installedApps) {
                int[] installedUsers = installedAppInfo.getInstalledUsers();
                if (installedUsers != null && installedUsers.length > 0) {
                    int i2 = i;
                    for (int i3 : installedUsers) {
                        if (VActivityManager.get().isAppRunning(installedAppInfo.packageName, i3, true)) {
                            if (i2 > 0) {
                                sb.append("#");
                            }
                            sb.append(installedAppInfo.packageName);
                            i2++;
                        }
                    }
                    i = i2;
                } else if (VActivityManager.get().isAppRunning(installedAppInfo.packageName, 0, true)) {
                    if (i > 0) {
                        sb.append("#");
                    }
                    sb.append(installedAppInfo.packageName);
                    i++;
                }
            }
            return sb.toString();
        }

        @Override // p110z1.EnginSDKCallback
        /* renamed from: c */
        public void mo12382c(int i) {
            Intent intent = new Intent("_VA_" + FloatDataManager.m12352j().m12362b());
            intent.putExtra(BroadVal.f16467a, 2);
            intent.putExtra(BroadVal.f16470d, i);
            FloatApp.m18899b().m18901a().sendBroadcast(intent);
        }

        @Override // p110z1.EnginSDKCallback
        /* renamed from: c */
        public void mo12381c(String str) {
            Intent intent = new Intent("_VA_" + FloatDataManager.m12352j().m12362b());
            intent.putExtra(BroadVal.f16467a, 1);
            intent.putExtra(BroadVal.f16471e, str);
            FloatApp.m18899b().m18901a().sendBroadcast(intent);
        }
    };

    /* renamed from: a */
    public void m12424a(Context context) {
        EnginSdk.f15153a = false;
        FloatApp.m18899b().m18898b(context);
        EnginSdk.getInstance().m14389a(FloatApp.m18899b().m18901a(), this.f16728g, ShareVal.f16591a);
        EnginSdk.getInstance().m14394a();
        if (this.f16727f == null) {
            this.f16727f = new Handler(Looper.getMainLooper()) { // from class: z1.ani.2
                @Override // android.os.Handler
                public void handleMessage(Message message) {
                    if (message.what == 256) {
                        String str = (String) message.obj;
                        InvokeProvider createInvoke = ProviderFactory.createInvoke();
                        if (createInvoke != null) {
                            createInvoke.invoke(str, Integer.valueOf(FloatDataManager.m12352j().m12359c()));
                        }
                    }
                }
            };
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: j */
    public void m12396j() {
        if (!m12427a()) {
            m12401f();
        } else {
            m12414b();
        }
    }

    /* renamed from: a */
    public boolean m12427a() {
        return EnginSdk.getInstance().m14378e();
    }

    /* renamed from: b */
    public void m12414b() {
        Log.d("setCurrentStopType", "准备停止-stopScript:" + FloatDataManager.m12352j().m12371a());
        if (m12427a()) {
            Log.d("setCurrentStopType", "stopScript-stopScript:" + FloatDataManager.m12352j().m12371a(), new Exception());
            EnginSdk.getInstance().m14383b();
        }
    }

    /* renamed from: a */
    public void m12418a(String str, String str2) throws RuntimeException, IOException {
        String[] list;
        File file = new File(str);
        if (file.exists()) {
            this.f16723b = new ScriptPathInfo();
            for (String str3 : file.list()) {
                if (str3.endsWith(".lc")) {
                    this.f16723b.lc_path = str + str3;
                } else if (str3.endsWith(ScriptSuffix.f8425c)) { // .atc
                    this.f16723b.atc_Path = str + str3;
                } else if (str3.endsWith(ScriptSuffix.f8426d)) { // .ui
                    this.f16723b.uiPath = str + str3;
                } else if (str3.endsWith(NRZSFileConfig.f16543a)) { // .uicfg
                    this.f16723b.uiCfgPath = str + str3;
                } else if (str3.endsWith(ScriptSuffix.f8427e)) { // .rtd
                    this.f16723b.rtd_path = str + str3;
                } else if (str3.endsWith(".uip")) {
                    this.f16723b.uipPath = str + str3;
                }
            }
            if (this.f16723b.uiCfgPath == null || this.f16723b.uiCfgPath.equals("")) {
                this.f16723b.uiCfgPath = str + NRZSFileConfig.f16544b; // a.uicfg
            }
            if (!StringUtil.m11795c((CharSequence) str2)) {
                this.f16723b.uiCfgPath = m12404d(str, str2);
            }
            this.f16723b.compiledContent = FileUtils.readFileToByteArray(new File(NRZSFileConfig.f16553k + "script.lc"));
        }
    }

    /* renamed from: b */
    public void m12410b(String str, String str2) throws RuntimeException, IOException {
        String[] list;
        File file = new File(str);
        if (file.exists()) {
            this.f16723b = new ScriptPathInfo();
            for (String str3 : file.list()) {
                if (str3.endsWith(".lc")) {
                    this.f16723b.lc_path = str + str3;
                } else if (str3.endsWith(ScriptSuffix.f8425c)) { // .atc
                    this.f16723b.atc_Path = str + str3;
                } else if (str3.endsWith(ScriptSuffix.f8426d)) { // .ui
                    this.f16723b.uiPath = str + str3;
                } else if (str3.endsWith(NRZSFileConfig.f16543a)) { // .uicfg
                    this.f16723b.uiCfgPath = str + str3;
                } else if (str3.endsWith(ScriptSuffix.f8427e)) { // .rtd
                    this.f16723b.rtd_path = str + str3;
                } else if (str3.endsWith(".uip")) {
                    this.f16723b.uipPath = str + str3;
                }
            }
            if (this.f16723b.uiCfgPath == null || this.f16723b.uiCfgPath.equals("")) {
                this.f16723b.uiCfgPath = str + NRZSFileConfig.f16544b;
            }
            if (!StringUtil.m11795c((CharSequence) str2)) {
                this.f16723b.uiCfgPath = m12407c(str, str2);
            }
            this.f16723b.compiledContent = FileUtils.readFileToByteArray(new File(str + "script.lc"));
        }
    }

    /* renamed from: c */
    private String m12407c(String str, String str2) {
        String str3 = str.replace("/ss/script/", "/script/") + "";
        String str4 = str3 + "uiconfig" + File.separatorChar;
        String str5 = str4 + apb.m11874a(str2) + NRZSFileConfig.f16543a;
        String str6 = str3 + NRZSFileConfig.f16544b;
        if (!com.blankj.utilcode.util.FileUtils.m22229b(str5) && com.blankj.utilcode.util.FileUtils.m22229b(str6)) {
            com.blankj.utilcode.util.FileUtils.m22212e(str4);
            com.blankj.utilcode.util.FileUtils.m22221c(str6, str5);
        }
        return str5;
    }

    /* renamed from: d */
    private String m12404d(String str, String str2) {
        String str3 = NRZSFileConfig.f16559q + apb.m11874a(str2) + NRZSFileConfig.f16543a;
        String str4 = str + NRZSFileConfig.f16544b;
        if (!com.blankj.utilcode.util.FileUtils.m22229b(str3) && com.blankj.utilcode.util.FileUtils.m22229b(str4)) {
            com.blankj.utilcode.util.FileUtils.m22212e(NRZSFileConfig.f16559q);
            com.blankj.utilcode.util.FileUtils.m22221c(str4, str3);
        }
        return str3;
    }

    /* renamed from: b */
    public LinearLayout m12412b(Context context) throws Exception {
        this.f16724c = EnginSdk.getInstance().m14390a(context, this.f16723b.uiPath, this.f16723b.uipPath, this.f16723b.uiCfgPath);
        IScriptUiModel acbVar = this.f16724c;
        this.f16722a = acbVar instanceof ScriptUipModel;
        LinearLayout a = acbVar.mo14399a();
        this.f16724c.mo14397b();
        this.f16724c.mo14396c();
        if (a.getChildCount() > 0) {
            return a;
        }
        return null;
    }

    /* renamed from: a */
    public LinearLayout m12423a(Context context, String str, String str2) throws Exception {
        m12410b(str + File.separatorChar, str2);
        this.f16724c = EnginSdk.getInstance().m14390a(context, this.f16723b.uiPath, this.f16723b.uipPath, this.f16723b.uiCfgPath);
        IScriptUiModel acbVar = this.f16724c;
        this.f16722a = acbVar instanceof ScriptUipModel;
        LinearLayout a = acbVar.mo14399a();
        this.f16724c.mo14397b();
        this.f16724c.mo14396c();
        if (a.getChildCount() > 0) {
            return a;
        }
        return null;
    }

    /* renamed from: c */
    public String m12408c() {
        IScriptUiModel acbVar = this.f16724c;
        if (acbVar == null) {
            return null;
        }
        try {
            return acbVar.mo14396c();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: d */
    public boolean m12405d() {
        ScriptPathInfo scriptPathInfo = this.f16723b;
        return scriptPathInfo != null && new File(scriptPathInfo.lc_path).exists();
    }

    /* renamed from: e */
    public boolean m12403e() {
        return new File(this.f16723b.uipPath).exists();
    }

    /* renamed from: a */
    public boolean m12422a(AssistInfo assistInfo) {
        StringBuilder sb = new StringBuilder();
        sb.append(NRZSFileConfig.f16558p);
        sb.append(apb.m11874a(assistInfo.ScriptPath));
        sb.append(File.separatorChar);
        return new File(sb.toString()).exists();
    }

    /* renamed from: f */
    public void m12401f() {
        ScriptPathInfo scriptPathInfo = this.f16723b;
        if (scriptPathInfo != null && scriptPathInfo.compiledContent != null) {
            m12408c();
            FloatViewManager.m12346a(FloatApp.m18899b().m18901a()).m12319j();
            FloatViewManager.m12346a(FloatApp.m18899b().m18901a()).m12325f();
            m12395k();
            this.f16727f.postDelayed(new Runnable() { // from class: z1.ani.3
                @Override // java.lang.Runnable
                public void run() {
                    EnginSdk.getInstance().m14384a(FloatAssistManager.this.f16723b.compiledContent, FloatAssistManager.this.f16723b.lc_path, FloatAssistManager.this.f16723b.atc_Path, FloatAssistManager.this.f16723b.uiCfgPath);
                }
            }, 500L);
        }
    }

    /* renamed from: k */
    private void m12395k() {
        EnginSdk.getInstance().m14381c();
    }

    /* renamed from: a */
    public void m12426a(int i) {
        boolean a = m12427a();
        LogUtils.m23734c("updateEnginDis", "isRun=" + a);
        if (a) {
            return;
        }
        if (apf.m11838b(VirtualCore.get().getContext(), "common_shared_file", ShareVal.f16606p, 0) == 0) {
            int b = apf.m11838b(FloatApp.m18899b().m18901a(), ShareVal.f16608r, ShareVal.f16610t, 0);
            int b2 = apf.m11838b(FloatApp.m18899b().m18901a(), ShareVal.f16608r, ShareVal.f16609s, 0);
            int b3 = apf.m11838b(FloatApp.m18899b().m18901a(), ShareVal.f16608r, ShareVal.f16611u, 0);
            int b4 = apf.m11838b(FloatApp.m18899b().m18901a(), ShareVal.f16608r, ShareVal.f16612v, 0);
            LogUtils.m23734c("updateEnginDis", "updateEnginDis:disY=" + b + ",disX=" + b2 + ",m1=" + b3 + ",m2=" + b4);
            EnginSdk.getInstance().m14385a(true, b4, b3, b, b2);
            return;
        }
        EnginSdk.getInstance().m14385a(false, 0, 0, 0, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m12425a(int i, String str, int i2) {
        int i3;
        LogUtils.m23734c("newEngin", "i:" + i + "s:" + str + ",from:" + i2 + ",threadName:" + Thread.currentThread().getName());
        LogUtils.m23757a("newEngin", (Object) ("i:" + i + "s:" + str + ",from:" + i2 + ",threadName:" + Thread.currentThread().getName()));
        if (!TextUtils.isEmpty(str)) {
            str = str.trim();
        }
        if (i == 1) {
            this.f16726e = 0;
        } else if (i == 2) {
            m12402e(str, FloatDataManager.m12352j().m12357e().OnlyID);
        } else if (i != 3 && i != 4) {
            if (i == 5) {
                FloatDataManager.m12352j().m12360b(str);
            } else if (i == 6) {
                m12419a(str);
            } else if (i != 10 && i != 11 && i != 12 && i != 13) {
                if (i == 20) {
                    EnginInteraRequestInfo enginInteraRequestInfo = this.f16725d;
                    if (enginInteraRequestInfo != null && (i3 = this.f16726e) < 3) {
                        this.f16726e = i3 + 1;
                        m12420a(enginInteraRequestInfo, "引擎返回失败");
                    }
                } else if (i == 21) {
                    FloatDataManager.m12352j().m12360b(str);
                } else if (i == 22) {
                    m12419a(str);
                } else if (i == 23) {
                    m12414b();
                    FloatDataManager.m12352j().m12360b(str);
                } else if (i == 24) {
                    m12414b();
                    FloatViewManager.m12346a(FloatApp.m18899b().m18901a()).m12327e();
                    FloatViewManager.m12346a(FloatApp.m18899b().m18901a()).m12345a(FloatApp.m18899b().m18901a(), 1);
                } else if (i == 25) {
                    m12414b();
                    if (LoginManager.m12620d().m12606r()) {
                        EnginInteraRequestInfo enginInteraRequestInfo2 = new EnginInteraRequestInfo();
                        enginInteraRequestInfo2.AppSign = Util.getAppSinature(FloatApp.m18899b().m18901a());
                        enginInteraRequestInfo2.Command = 1;
                        enginInteraRequestInfo2.SessionId = LoginManager.m12620d().m12616h();
                        enginInteraRequestInfo2.UserId = LoginManager.m12620d().m12614j();
                        enginInteraRequestInfo2.DesKey = LoginManager.m12620d().m12612l();
                        enginInteraRequestInfo2.ScriptCacheRPath = ShareVal.f16591a + File.separatorChar + MSVSSConstants.SS_EXE;
                        enginInteraRequestInfo2.dycIp = LocalHttp.f16542c;
                        m12420a(enginInteraRequestInfo2, "钥匙激活成功");
                    }
                    LogUtils.m23734c("newEngin", "用户未初始化，去通知界面更新");
                    EventBus.m3448a().m3427d(new FtEvent.C3568f(i));
                }
            }
        }
    }

    /* renamed from: a */
    private void m12419a(final String str) {
        VUiKit.m11713a().mo3332a(new Callable<HeartInfo>() { // from class: z1.ani.6
            /* renamed from: a */
            public HeartInfo call() throws Exception {
                BaseResponse baseResponse;
                HeartInfo heartInfo;
                if (!TextUtils.isEmpty(str) && (baseResponse = (BaseResponse) apa.m11877a(str, new TypeToken<BaseResponse<String>>() { // from class: z1.ani.6.1
                })) != null) {
                    String str2 = (String) baseResponse.data;
                    if (!TextUtils.isEmpty(str2) && (heartInfo = (HeartInfo) apa.m11876b(str2, HeartInfo.class)) != null) {
                        return heartInfo;
                    }
                }
                throw new RuntimeException("心跳信息为空");
            }
        }).mo3285a(new FailCallback<Throwable>() { // from class: z1.ani.5
            /* renamed from: a */
            public void onFail(Throwable th) {
                FloatAssistManager.this.m12414b();
                ToastUtils.m23030a("脚本运行出现异常，请稍后尝试");
            }
        }).mo3282b(new DoneCallback<HeartInfo>() { // from class: z1.ani.4
            /* renamed from: a */
            public void onDone(HeartInfo heartInfo) {
                if (heartInfo.Status == 2 || heartInfo.Status == 6 || heartInfo.Status == 8) {
                    FloatAssistManager.this.m12414b();
                    FloatViewManager.m12346a(FloatApp.m18899b().m18901a()).m12324f(FloatApp.m18899b().m18901a());
                } else if (heartInfo.Status == 3) {
                    FloatAssistManager.this.m12414b();
                    FloatViewManager.m12346a(FloatApp.m18899b().m18901a()).m12345a(FloatApp.m18899b().m18901a(), 3);
                }
            }
        });
    }

    /* renamed from: a */
    public void m12420a(EnginInteraRequestInfo enginInteraRequestInfo, String str) {
        this.f16725d = enginInteraRequestInfo;
        LogUtils.m23734c("newEngin", "user json:" + apa.m11879a(enginInteraRequestInfo) + ",threadName:" + Thread.currentThread().getName());
        EnginSdk.getInstance().m14388a(apa.m11879a(enginInteraRequestInfo));
    }

    /* renamed from: a */
    public void m12421a(EnginInteraRequestInfo enginInteraRequestInfo) {
        EnginSdk.getInstance().m14382b(apa.m11879a(enginInteraRequestInfo));
    }

    /* renamed from: b */
    public void m12411b(EnginInteraRequestInfo enginInteraRequestInfo) {
        EnginSdk.getInstance().m14380c(apa.m11879a(enginInteraRequestInfo));
    }

    /* renamed from: e */
    private void m12402e(final String str, final String str2) {
        VUiKit.m11713a().mo3332a(new Callable<String>() { // from class: z1.ani.9
            /* renamed from: a */
            public String call() throws Exception {
                try {
                    String substring = str.substring(0, str.lastIndexOf(Consts.f23430h));
                    File file = new File(substring);
                    if (file.exists()) {
                        FileTools.m12119a(file);
                    }
                    FloatAssistManager aniVar = FloatAssistManager.this;
                    String str3 = str;
                    aniVar.m12400f(str3, substring + File.separatorChar);
                    FloatAssistManager aniVar2 = FloatAssistManager.this;
                    aniVar2.m12418a(substring + File.separatorChar, str2);
                    return substring;
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new RuntimeException();
                }
            }
        }).mo3285a(new FailCallback<Throwable>() { // from class: z1.ani.8
            /* renamed from: a */
            public void onFail(Throwable th) {
                EventBus.m3448a().m3427d(new FtEvent.C3569g(null));
            }
        }).mo3282b(new DoneCallback<String>() { // from class: z1.ani.7
            /* renamed from: a */
            public void onDone(String str3) {
                EventBus.m3448a().m3427d(new FtEvent.C3565c(str));
            }
        });
    }

    /* renamed from: l */
    private void m12394l() {
        if (3 == FloatDataManager.m12352j().f16748d) {
            File file = new File(FloatApp.m18899b().m18901a().getApplicationInfo().dataDir, "osimg");
            StringBuilder sb = new StringBuilder();
            sb.append(file);
            sb.append("/r/ot01/storage/sdcard/xjscript/");
            ScriptPathInfo g = m12397i().m12399g();
            String str = g.lc_path;
            com.blankj.utilcode.util.FileUtils.m22221c(str, sb.toString() + "1.lc");
            String str2 = g.atc_Path;
            com.blankj.utilcode.util.FileUtils.m22221c(str2, sb.toString() + "1.atc");
            String str3 = g.uiCfgPath;
            com.blankj.utilcode.util.FileUtils.m22221c(str3, sb.toString() + "1.uicfg");
            LogUtils.m23734c("newEngin", "scriptPathInfo.lc_path:" + g.lc_path);
            LogUtils.m23734c("newEngin", "scriptPathInfo.atc_Path:" + g.atc_Path);
            LogUtils.m23734c("newEngin", "scriptPathInfo.uiCfgPath:" + g.uiCfgPath);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: f */
    public void m12400f(String str, String str2) throws Exception {
        try {
            Project project = new Project();
            Expand expand = new Expand();
            expand.setProject(project);
            expand.setSrc(new File(str));
            expand.setOverwrite(false);
            expand.setDest(new File(str2));
            expand.setEncoding("gbk");
            expand.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: g */
    public ScriptPathInfo m12399g() {
        return this.f16723b;
    }

    /* renamed from: h */
    public void m12398h() {
        m12414b();
        this.f16723b = null;
        this.f16722a = false;
        this.f16724c = null;
    }

    /* renamed from: b */
    public void m12413b(int i) {
        EnginSdk.getInstance().m14392a(i);
    }

    /* renamed from: i */
    public static FloatAssistManager m12397i() {
        FloatAssistManager aniVar;
        synchronized (f16720h) {
            if (f16721i == null) {
                f16721i = new FloatAssistManager();
            }
            aniVar = f16721i;
        }
        return aniVar;
    }
}
