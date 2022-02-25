package p110z1;

import android.accounts.Account;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Environment;
import android.os.RemoteException;
import android.support.p006v7.app.AppCompatDelegate;
import android.text.TextUtils;
import android.util.Log;
import com.kaopu.VACallBack;
import com.kaopu.tiantian.Global.Hook_getCallingUid;
import com.kaopu.tiantian.HookMain;
import com.lody.virtual.client.NativeEngine;
import com.lody.virtual.client.core.SettingConfig;
import com.lody.virtual.client.core.VirtualCore;
import com.lody.virtual.client.ipc.VAccountManager;
import com.lody.virtual.helper.utils.Reflect;
import com.lody.virtual.helper.utils.VLog;
import com.lody.virtual.remote.InstalledAppInfo;
import com.nrzs.p072va.SettingConfigProxy;
import com.tencent.smtt.sdk.TbsConfig;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import patch.MyFixer;

/* renamed from: z1.air */
/* loaded from: classes3.dex */
public class GameApp {

    /* renamed from: a */
    private Context f15997a;

    /* renamed from: b */
    private SettingConfig f15998b;

    /* renamed from: c */
    private VACallBack f15999c;

    /* renamed from: d */
    private String f16000d;

    /* renamed from: e */
    private Class f16001e;

    /* renamed from: a */
    public void m13009a(Context context, String str, Class cls) {
        this.f15997a = context;
        this.f16000d = str;
        this.f16001e = cls;
        if (!NRZSLocalConfig.m12512d()) {
            m13010a(context);
        }
    }

    /* renamed from: a */
    private void m13010a(Context context) {
        VLog.OPEN_LOG = false;
        try {
            this.f16000d = context.getPackageName();
            VirtualCore.get().startup(context, this.f15998b);
            VirtualCore.get().setVaCallBack(this.f15999c);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* renamed from: a */
    public void m13011a() {
        if (!NRZSLocalConfig.m12512d()) {
            final VirtualCore virtualCore = VirtualCore.get();
            virtualCore.initialize(new VirtualCore.VirtualInitializer() { // from class: z1.air.3
                @Override // com.lody.virtual.client.core.VirtualCore.VirtualInitializer
                public void onServerProcess() {
                }

                @Override // com.lody.virtual.client.core.VirtualCore.VirtualInitializer
                public void onMainProcess() {
                    AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
                }

                @Override // com.lody.virtual.client.core.VirtualCore.VirtualInitializer
                public void onVirtualProcess() {
                    virtualCore.setAppCallback(new MyComponentDelegate());
                    virtualCore.setTaskDescriptionDelegate(new MyTaskDescDelegate());
                    virtualCore.setAppRequestListener(new MyAppRequestListener(GameApp.this.f15997a));
                }
            });
        }
    }

    /* renamed from: b */
    public Context m13006b() {
        return this.f15997a;
    }

    /* renamed from: c */
    public void m13003c() {
        VUiKit.m11713a().mo3333a($$Lambda$air$29jvYAAVUgwFTVa3afI5JcvOs.INSTANCE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: e */
    public static /* synthetic */ void m12999e() {
        if (!VirtualCore.get().isEngineLaunched()) {
            VirtualCore.get().waitForEngine();
        }
        TencentSupport.m12997a(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public static String m13005b(String str) {
        File[] listFiles;
        for (File file : new File("/proc").listFiles()) {
            if (file.isDirectory()) {
                try {
                    int parseInt = Integer.parseInt(file.getName());
                    try {
                        if (m13002c(String.format("/proc/%d/cmdline", Integer.valueOf(parseInt))).contains(str)) {
                            return String.valueOf(parseInt);
                        }
                        continue;
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (NumberFormatException e2) {
                        e2.printStackTrace();
                    } catch (Exception e3) {
                        e3.printStackTrace();
                    }
                } catch (NumberFormatException unused) {
                }
            }
        }
        return null;
    }

    /* renamed from: c */
    private static String m13002c(String str) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(str));
        sb.append(bufferedReader.readLine());
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine != null) {
                sb.append('\n');
                sb.append(readLine);
            } else {
                bufferedReader.close();
                return sb.toString().trim();
            }
        }
    }

    private GameApp() {
        this.f15998b = new SettingConfigProxy() { // from class: z1.air.1
            @Override // com.lody.virtual.client.core.SettingConfig
            public boolean isAllowCreateShortcut() {
                return false;
            }

            @Override // com.lody.virtual.client.core.SettingConfig
            public boolean isUseRealDataDir(String str) {
                return false;
            }

            @Override // com.nrzs.p072va.SettingConfigProxy, com.lody.virtual.client.core.SettingConfig
            public String getHostPackageName() {
                return GameApp.this.f16000d.endsWith(".addon.arm64") ? GameApp.this.f16000d.substring(0, GameApp.this.f16000d.indexOf(".addon.arm64")) : GameApp.this.f16000d;
            }

            @Override // com.nrzs.p072va.SettingConfigProxy, com.lody.virtual.client.core.SettingConfig
            public String get32bitEnginePackageName() {
                Log.i("TAG", "get32bitEnginePackageName: com.angel.nrzs.addon.arm64");
                return "com.angel.nrzs.addon.arm64";
            }

            @Override // com.nrzs.p072va.SettingConfigProxy, com.lody.virtual.client.core.SettingConfig
            public String get64bitEnginePackageName() {
                Log.i("TAG", "get64bitEnginePackageName: com.angel.nrzs.addon.arm64");
                return "com.angel.nrzs.addon.arm64";
            }

            @Override // com.lody.virtual.client.core.SettingConfig
            public Intent onHandleLauncherIntent(Intent intent) {
                Intent intent2 = new Intent(VirtualCore.get().getContext(), GameApp.this.f16001e);
                intent2.addFlags(268435456);
                return intent2;
            }

            @Override // com.nrzs.p072va.SettingConfigProxy, com.lody.virtual.client.core.SettingConfig
            public SettingConfig.AppLibConfig getAppLibConfig(String str) {
                return SettingConfig.AppLibConfig.UseRealLib;
            }

            @Override // com.lody.virtual.client.core.SettingConfig
            public boolean isDisableDrawOverlays(String str) {
                return super.isDisableDrawOverlays(str);
            }
        };
        this.f15999c = new VACallBack() { // from class: z1.air.2
            @Override // com.kaopu.VACallBack
            public void startYafaVa(Context context) {
                try {
                    int b = apf.m11838b(VirtualCore.get().getContext(), "common_shared_file", ShareVal.f16606p, 0);
                    Log.d("lbsxxxx", "type:" + b);
                    if (b == 0) {
                        String b2 = apf.m11837b(VirtualCore.get().getContext(), "common_shared_file", "PREINSTALL_PHONE_BRAND", "xiaomi");
                        String b3 = apf.m11837b(VirtualCore.get().getContext(), "common_shared_file", "PREINSTALL_PHONE_MANUFACTURER", "xiaomi");
                        String b4 = apf.m11837b(VirtualCore.get().getContext(), "common_shared_file", "PREINSTALL_PHONE", "xiaomi mix");
                        if (!TextUtils.isEmpty(b2)) {
                            Reflect.m18999on(Build.TYPE).set("MODEL", b4);
                            Reflect.m18999on(Build.TYPE).set("MANUFACTURER", b3);
                            Reflect.m18999on(Build.TYPE).set("BRAND", b2);
                        }
                        ClassLoader classLoader = context.getClassLoader();
                        ClassLoader classLoader2 = VirtualCore.get().getContext().getClassLoader();
                        HookMain.doHookDefault_va(classLoader2, classLoader, b);
                        if ("com.huawei.hwid".equals(context.getPackageName())) {
                            HookMain.doHookDefault_list(classLoader2, classLoader, new String[]{Hook_getCallingUid.class.getName()});
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (Build.BRAND.equalsIgnoreCase("ONEPLUS")) {
                    Reflect.m18999on(Build.TYPE).set("BRAND", "vivo");
                }
            }

            @Override // com.kaopu.VACallBack
            public void fixForAll(Context context, Application application, InstalledAppInfo installedAppInfo, String str) {
                MyFixer.m14548a(installedAppInfo);
                MyFixer.m14553a(context);
                MyFixer.m14552a(context, str);
                MyFixer.m14537d(context);
            }

            @Override // com.kaopu.VACallBack
            public void fixFor1(InstalledAppInfo installedAppInfo) {
                String b = GameApp.m13005b("jpush");
                if (b != null) {
                    NativeEngine.redirectDirectory("/proc/" + b + "/status", "/proc/self/status");
                    NativeEngine.redirectDirectory("/proc/" + b + "/cmdline", "/proc/self/cmdline");
                }
                if (installedAppInfo.packageName.equals("com.tencent.tmgp.rxcq") || installedAppInfo.packageName.equals(TbsConfig.APP_WX) || installedAppInfo.packageName.contains(".wzcq") || installedAppInfo.packageName.equals("com.nasanx.jiojs.twlyx") || installedAppInfo.packageName.equals("com.nhnst.SKCQCN.bili") || installedAppInfo.packageName.equals("com.tencent.tmgp.shenghewzcq")) {
                    String absolutePath = Environment.getExternalStorageDirectory().getAbsolutePath();
                    NativeEngine.redirectDirectory(absolutePath, absolutePath + "/nrzs/pxkjapp/");
                }
                if (installedAppInfo.packageName.equals("com.tencent.tmgp.rxcq") || installedAppInfo.packageName.equals(TbsConfig.APP_WX)) {
                    String absolutePath2 = Environment.getExternalStorageDirectory().getAbsolutePath();
                    NativeEngine.redirectDirectory(absolutePath2, absolutePath2 + "/nrzs/pxkjapp/");
                }
            }

            @Override // com.kaopu.VACallBack
            public void fixFor2(String str, int i) throws RemoteException {
                if ("com.huawei.hwid".equals(str)) {
                    Account[] accounts = VAccountManager.get().getAccounts(i, "com.huawei.hwid");
                    if (accounts.length > 0) {
                        VAccountManager.get().getService().removeAccountExplicitly(i, accounts[0]);
                    }
                }
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: GameApp.java */
    /* renamed from: z1.air$a */
    /* loaded from: classes3.dex */
    public static class C3511a {

        /* renamed from: a */
        private static final GameApp f16006a = new GameApp();

        private C3511a() {
        }
    }

    /* renamed from: d */
    public static GameApp m13000d() {
        return C3511a.f16006a;
    }
}
