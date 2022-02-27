package p110z1;

import android.content.Context;
import android.content.pm.PackageManager;
import android.support.annotation.Nullable;
import android.util.Log;
import com.blankj.utilcode.util.LogUtils;
import com.cyjh.ddy.media.p035a.ResultTypeConstant;
import com.lody.virtual.client.core.VirtualCore;
import com.nrzs.core.models.AppData;
import com.nrzs.core.models.AppInfo;
import com.nrzs.core.models.AppInfoLite;
import com.nrzs.core.models.GameInfo;
import com.nrzs.data.game.bean.TopicInfo;
import com.nrzs.data.p066ft.bean.AssistInfo;
import com.nrzs.game.ui.activity.GameVALoadingActivity;
import com.nrzs.p072va.AppInstallOptions;
import com.nrzs.p072va.AppInstallResult;
import com.nrzs.p072va.VirtualCoreProxy;
import java.util.concurrent.Callable;
import p110z1.GameDialogVARunScriptCheck;

/* renamed from: z1.aoj */
/* loaded from: classes3.dex */
public class VAManager {

    /* renamed from: j */
    private static final Object f16958j = new Object();

    /* renamed from: k */
    private static VAManager f16959k;

    /* renamed from: a */
    private Context f16960a;

    /* renamed from: b */
    private TopicInfo f16961b;

    /* renamed from: c */
    private boolean f16962c;

    /* renamed from: d */
    private Class f16963d;

    /* renamed from: e */
    private AssistInfo f16964e;

    /* renamed from: f */
    private int f16965f = 1;

    /* renamed from: g */
    private GameDialogVARunScriptCheck f16966g;

    /* renamed from: h */
    private GameDialogVACheck f16967h;

    /* renamed from: i */
    private GameDialogVARepaire f16968i;

    /* renamed from: a */
    public void m12092a(Context context) {
        this.f16960a = null;
        this.f16960a = context;
    }

    /* renamed from: a */
    public void m12086a(TopicInfo topicInfo) {
        Log.e("选择游戏", topicInfo.TopicID + "设置了info");
        this.f16961b = null;
        this.f16961b = topicInfo;
    }

    /* renamed from: a */
    public void m12093a(int i) {
        this.f16965f = i;
    }

    /* renamed from: a */
    public void m12082a(boolean z) {
        this.f16962c = z;
    }

    /* renamed from: a */
    public TopicInfo m12094a() {
        return this.f16961b;
    }

    /* renamed from: a */
    public void m12085a(Class cls) {
        this.f16963d = cls;
    }

    /* renamed from: b */
    public Class m12081b() {
        return this.f16963d;
    }

    /* renamed from: c */
    public int m12077c() {
        return this.f16965f;
    }

    /* renamed from: d */
    public AssistInfo m12075d() {
        return this.f16964e;
    }

    /* renamed from: a */
    public void m12087a(AssistInfo assistInfo) {
        this.f16964e = assistInfo;
    }

    /* renamed from: e */
    public void m12074e() {
        try {
            FloatWindowManager.m12301b(this.f16960a, new Observer<Integer>() { // from class: z1.aoj.1
                @Override // p110z1.Observer
                public void onComplete() {
                }

                @Override // p110z1.Observer
                public void onError(@AbstractC3889atm Throwable th) {
                }

                @Override // p110z1.Observer
                public void onSubscribe(@AbstractC3889atm Disposable atrVar) {
                }

                /* renamed from: a */
                public void onNext(@AbstractC3889atm Integer num) {
                    num.intValue();
                    if (VAManager.this.f16960a != null && VAManager.this.f16961b != null) {
                        VUiKit.m11713a().mo3333a(new Runnable() { // from class: z1.aoj.1.2
                            @Override // java.lang.Runnable
                            public void run() {
                                try {
                                    Thread.sleep(100L);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }).mo3282b(new DoneCallback<Void>() { // from class: z1.aoj.1.1
                            /* renamed from: a */
                            public void onDone(Void r3) {
                                GameVALoadingActivity.m18632a(VAManager.this.f16960a, VAManager.this.f16961b, VAManager.this.f16965f);
                            }
                        });
                    }
                }
            });
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* renamed from: a */
    public void m12084a(final VAppStatueCallback ajdVar, final Context context, final GameInfo gameInfo, @Nullable final AppInfoLite appInfoLite) {
        final AppRepository ajfVar = new AppRepository(context);
        VUiKit.m11713a().mo3332a(new Callable() { // from class: z1.-$$Lambda$aoj$WN0q_f5QrIy3Kr4-yFz2u4ct5dA
            @Override // java.util.concurrent.Callable
            public final Object call() {
                Integer a;
                a = VAManager.m12088a(AppInfoLite.this, ajfVar, context, gameInfo);
                return a;
            }
        }).mo3285a(new FailCallback<Throwable>() { // from class: z1.aoj.3
            /* renamed from: a */
            public void onFail(Throwable th) {
                LogUtils.m23734c("checkVAApp", "failCallback -- 1");
                ajdVar.mo12962b(1);
            }
        }).mo3282b(new DoneCallback<Integer>() { // from class: z1.aoj.2
            /* renamed from: a */
            public void onDone(Integer num) {
                if (num.intValue() == 0) {
                    LogUtils.m23734c("checkVAApp", "failCallback -- 0");
                    ajdVar.mo12962b(num.intValue());
                } else if (num.intValue() == 1) {
                    LogUtils.m23734c("checkVAApp", "failCallback -- 1");
                    ajdVar.mo12962b(num.intValue());
                } else if (num.intValue() == -1) {
                    LogUtils.m23734c("checkVAApp", "failCallback -- 1");
                    ajdVar.mo12962b(num.intValue());
                } else if (num.intValue() == 2 || num.intValue() == 5) {
                    LogUtils.m23734c("checkVAApp", "Callback -- " + num);
                    ajdVar.mo12964a(0);
                    ajfVar.mo12951a(gameInfo.f10544h).mo3282b(new DoneCallback<AppData>() { // from class: z1.aoj.2.2
                        /* JADX WARN: Removed duplicated region for block: B:13:0x0054  */
                        /* JADX WARN: Removed duplicated region for block: B:16:? A[RETURN, SYNTHETIC] */
                        /* renamed from: a */
                        /*
                            Code decompiled incorrectly, please refer to instructions dump.
                            To view partially-correct code enable 'Show inconsistent code' option in preferences
                        */
                        public void onDone(com.nrzs.core.models.AppData r6) {
                            /*
                                r5 = this;
                                z1.ajg r0 = p110z1.PackageAppDataStorage.m12941a()
                                java.lang.String r1 = r6.mo18958e()
                                com.nrzs.core.models.d r0 = r0.m12937c(r1)
                                r1 = 0
                                r0.f10574e = r1
                                z1.aoj$2 r2 = p110z1.VAManager.C37702.this
                                com.nrzs.core.models.GameInfo r2 = r4
                                r0.mo18965a(r2)
                                java.lang.String r6 = r6.mo18958e()
                                if (r6 == 0) goto L_0x005c
                                int r2 = android.os.Build.VERSION.SDK_INT
                                r3 = 23
                                r4 = 1
                                if (r2 < r3) goto L_0x0051
                                com.lody.virtual.client.core.VirtualCore r2 = com.lody.virtual.client.core.VirtualCore.get()
                                com.lody.virtual.remote.InstalledAppInfo r6 = r2.getInstalledAppInfo(r6, r1)
                                android.content.pm.ApplicationInfo r2 = r6.getApplicationInfo(r1)
                                java.lang.String r3 = r6.packageName
                                boolean r3 = com.nrzs.p072va.VirtualCoreProxy.isRunProcess(r3)
                                boolean r2 = com.lody.virtual.helper.compat.PermissionCompat.isCheckPermissionRequired(r2)
                                if (r2 == 0) goto L_0x0051
                                java.lang.String r6 = r6.packageName
                                java.lang.String[] r6 = com.nrzs.p072va.VirtualCoreProxy.getDangrousPermissions(r6)
                                boolean r2 = com.lody.virtual.helper.compat.PermissionCompat.checkPermissions(r6, r3)
                                if (r2 != 0) goto L_0x0051
                                z1.aoj$2 r2 = p110z1.VAManager.C37702.this
                                z1.ajd r2 = r2
                                r3 = 2
                                r2.mo12963a(r1, r6, r0, r3)
                                r6 = 0
                                goto L_0x0052
                            L_0x0051:
                                r6 = 1
                            L_0x0052:
                                if (r6 == 0) goto L_0x005c
                                z1.aoj$2 r6 = p110z1.VAManager.C37702.this
                                z1.ajd r6 = r2
                                r2 = 0
                                r6.mo12963a(r1, r2, r0, r4)
                            L_0x005c:
                                return
                            */
                            throw new UnsupportedOperationException("Method not decompiled: p110z1.VAManager.C37702.C37722.onDone(com.nrzs.core.models.a):void");
                        }
                    }).mo3285a(new FailCallback<Throwable>() { // from class: z1.aoj.2.1
                        /* renamed from: a */
                        public void onFail(Throwable th) {
                            LogUtils.m23734c("checkVAApp", "failCallback -- 3:" + th.getMessage());
                            ajdVar.mo12962b(3);
                        }
                    });
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public static /* synthetic */ Integer m12088a(AppInfoLite appInfoLite, AppRepository ajfVar, Context context, GameInfo gameInfo) throws Exception {
        if (appInfoLite != null) {
            AppInstallResult a = ajfVar.mo12952a(appInfoLite);
            if (a.isSuccess) {
                LogUtils.m23734c("checkVAApp", "安装成功，运行APP");
                return 2;
            }
            LogUtils.m23734c("checkVAApp", "安装成功，安装失败");
            if ("64bit".equals(a.error)) {
                return -1;
            }
            return 1;
        }
        AppInfo a2 = ajfVar.mo12955a(context, gameInfo.f10544h, true);
        if (a2 == null) {
            LogUtils.m23734c("checkVAApp", "本地没有安装，提示用户安装");
            return 0;
        } else if (!VirtualCore.get().isAppInstalled(gameInfo.f10544h)) {
            AppInstallResult a3 = ajfVar.mo12952a(new AppInfoLite(a2.f10550a, a2.f10551b, a2.f10552c, a2.f10556g));
            if (a3.isSuccess) {
                LogUtils.m23734c("checkVAApp", "安装成功，运行APP -- 3");
                return 2;
            }
            LogUtils.m23734c("checkVAApp", "安装失败 -- 3");
            if ("64bit".equals(a3.error)) {
                return -1;
            }
            return 1;
        } else if (VirtualCore.get().isOutsideInstalled(gameInfo.f10544h)) {
            LogUtils.m23734c("checkVAApp", "APP可用，直接启动游戏");
            return 5;
        } else {
            aoy.m11885a(NRZSFileConfig.f16552j, gameInfo.f10544h);
            AppInstallResult installPackageSync = VirtualCoreProxy.installPackageSync(VirtualCoreProxy.getUnHookPackageManager().getApplicationInfo(gameInfo.f10544h, 0).sourceDir, AppInstallOptions.makeOptions(true, false));
            if (installPackageSync.isSuccess) {
                LogUtils.m23734c("checkVAApp", "安装成功，运行APP -- 2");
                return 2;
            }
            LogUtils.m23734c("checkVAApp", "安装失败 -- 2");
            if ("64bit".equals(installPackageSync.error)) {
                return -1;
            }
            return 1;
        }
    }

    /* renamed from: b */
    public static GameInfo m12079b(TopicInfo topicInfo) {
        GameInfo gameInfo = new GameInfo();
        gameInfo.f10537a = topicInfo.TopicID;
        gameInfo.f10541e = topicInfo.ImgPath;
        gameInfo.f10543g = topicInfo.localAppName;
        gameInfo.f10538b = topicInfo.TopicName;
        gameInfo.f10540d = topicInfo.Package;
        gameInfo.f10539c = topicInfo.PackageNames;
        gameInfo.f10542f = ResultTypeConstant.f7213z;
        gameInfo.f10544h = topicInfo.localAppPackage;
        gameInfo.f10546j = topicInfo.localVersionName;
        gameInfo.f10547k = topicInfo.ShowType;
        return gameInfo;
    }

    /* renamed from: a */
    public void m12089a(Context context, GameDialogVARunScriptCheck.AbstractC3792a aVar, long j) {
        GameDialogVARunScriptCheck aopVar = this.f16966g;
        if (aopVar != null) {
            aopVar.dismiss();
            this.f16966g = null;
        }
        this.f16966g = new GameDialogVARunScriptCheck(context, aVar, j);
        this.f16966g.show();
    }

    /* renamed from: f */
    public void m12073f() {
        GameDialogVARunScriptCheck aopVar = this.f16966g;
        if (aopVar != null) {
            aopVar.dismiss();
            this.f16966g = null;
        }
    }

    /* renamed from: b */
    public void m12080b(Context context) {
        GameDialogVACheck aomVar = this.f16967h;
        if (aomVar != null) {
            aomVar.dismiss();
            this.f16967h = null;
        }
        this.f16967h = new GameDialogVACheck(context);
        this.f16967h.show();
    }

    /* renamed from: g */
    public void m12072g() {
        GameDialogVACheck aomVar = this.f16967h;
        if (aomVar != null) {
            aomVar.dismiss();
            this.f16967h = null;
        }
    }

    /* renamed from: a */
    public void m12091a(Context context, String str) {
        GameDialogVARepaire aooVar = this.f16968i;
        if (aooVar != null) {
            aooVar.dismiss();
            this.f16968i = null;
        }
        this.f16968i = new GameDialogVARepaire(context, str);
        this.f16968i.show();
    }

    /* renamed from: h */
    public void m12071h() {
        GameDialogVARepaire aooVar = this.f16968i;
        if (aooVar != null) {
            aooVar.dismiss();
            this.f16968i = null;
        }
    }

    /* renamed from: a */
    public void m12090a(Context context, String str, String str2) {
        if (!VirtualCore.get().isAppInstalled(str)) {
            try {
                context.getPackageManager().getPackageInfo(str, 16384);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    /* renamed from: i */
    public static VAManager m12070i() {
        VAManager aojVar;
        synchronized (f16958j) {
            if (f16959k == null) {
                f16959k = new VAManager();
            }
            aojVar = f16959k;
        }
        return aojVar;
    }
}
