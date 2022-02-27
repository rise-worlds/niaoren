package com.nrzs.game.model;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.text.TextUtils;
import android.util.Log;
import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.LogUtils;
import com.kaopu.download.BaseDownloadOperate;
import com.kaopu.download.BaseDownloadStateFactory;
import com.kaopu.download.BaseDownloadWorker;
import com.kaopu.download.kernel.BaseDownloadInfo;
import com.kaopu.receiver.BroadcastReceiver;
import com.lody.virtual.client.core.VirtualCore;
import com.nrzs.core.models.AppData;
import com.nrzs.core.models.AppInfo;
import com.nrzs.core.models.AppInfoLite;
import com.nrzs.data.p066ft.bean.MultVersion;
import com.nrzs.http.UICallback;
import com.nrzs.p072va.AppInstallOptions;
import com.nrzs.p072va.AppInstallResult;
import com.nrzs.p072va.VirtualCoreProxy;
import com.tencent.smtt.sdk.TbsConfig;
import java.util.concurrent.Callable;
import p110z1.AppRepository;
import p110z1.DoneCallback;
import p110z1.GameApp;
import p110z1.MultVersionRepository;
import p110z1.NRZSFileConfig;
import p110z1.VUiKit;
import p110z1.ajc;

/* renamed from: com.nrzs.game.model.b */
/* loaded from: classes2.dex */
public class Wx32DownModel {

    /* renamed from: b */
    private static Wx32DownModel f10915b;

    /* renamed from: d */
    private BaseDownloadInfo f10918d;

    /* renamed from: e */
    private BroadcastReceiver f10919e;

    /* renamed from: f */
    private AbstractC2084a f10920f;

    /* renamed from: c */
    private int f10917c = -1;

    /* renamed from: g */
    private boolean f10921g = false;

    /* renamed from: a */
    AppRepository f10916a = new AppRepository(GameApp.getInstance().m13006b());

    /* compiled from: Wx32DownModel.java */
    /* renamed from: com.nrzs.game.model.b$a */
    /* loaded from: classes2.dex */
    public interface AbstractC2084a {
        /* renamed from: a */
        void m18715a();

        /* renamed from: a */
        void m18714a(int i);

        /* renamed from: a */
        void m18713a(long j, long j2);

        /* renamed from: b */
        void m18712b();

        /* renamed from: b */
        void m18711b(int i);
    }

    private Wx32DownModel() {
        m18753a(GameApp.getInstance().m13006b());
        m18736c();
    }

    /* renamed from: a */
    public static Wx32DownModel m18755a() {
        if (f10915b == null) {
            synchronized (Wx32DownModel.class) {
                if (f10915b == null) {
                    f10915b = new Wx32DownModel();
                }
            }
        }
        return f10915b;
    }

    /* renamed from: a */
    public void m18751a(AbstractC2084a aVar) {
        this.f10920f = aVar;
    }

    /* renamed from: b */
    public void m18742b() {
        m18733d();
    }

    /* renamed from: c */
    public void m18736c() {
        if (!VirtualCore.get().isOutsideInstalled(TbsConfig.APP_WX)) {
            this.f10916a.mo12951a(TbsConfig.APP_WX).mo3282b(new DoneCallback<AppData>() { // from class: com.nrzs.game.model.b.1
                /* renamed from: a */
                public void onDone(AppData aVar) {
                    if (aVar == null) {
                        return;
                    }
                    if (Wx32DownModel.this.f10916a.mo12950a(TbsConfig.APP_WX, 0)) {
                        Log.i("LBS_PXKJ", "isUnistall start 1");
                    } else {
                        Log.i("LBS_PXKJ", "isUnistall start 2");
                    }
                }
            });
        }
    }

    /* renamed from: a */
    public void m18753a(Context context) {
        IntentFilter intentFilter = new IntentFilter(BaseDownloadWorker.NOTIFY_VIEW_ACTION);
        if (this.f10919e == null) {
            this.f10919e = new BroadcastReceiver() { // from class: com.nrzs.game.model.b.2
                @Override // com.kaopu.receiver.BroadcastReceiver, android.content.BroadcastReceiver
                public void onReceive(Context context2, Intent intent) {
                    BaseDownloadInfo baseDownloadInfo = (BaseDownloadInfo) intent.getParcelableExtra(BaseDownloadWorker.NOTIFY_VIEW_ACTION_EXTRA_INFO_KEY);
                    if (baseDownloadInfo != null && Wx32DownModel.this.f10918d != null) {
                        if (TextUtils.isEmpty(baseDownloadInfo.getIdentification()) || !baseDownloadInfo.getIdentification().equals(Wx32DownModel.this.f10918d.getIdentification())) {
                            LogUtils.m23742b("过滤其他下载广播", Wx32DownModel.this.f10918d.getIdentification());
                            return;
                        }
                        switch (C20836.f10932a[baseDownloadInfo.getState().getState().ordinal()]) {
                            case 1:
                                LogUtils.m23742b("saveDir:" + baseDownloadInfo.getSaveDir());
                                LogUtils.m23742b("saveName:" + baseDownloadInfo.getSaveName());
                                Wx32DownModel.this.m18727i();
                                return;
                            case 2:
                                Wx32DownModel.this.m18745a(baseDownloadInfo.getIdentification(), baseDownloadInfo.getdSize(), baseDownloadInfo.getfSize(), baseDownloadInfo.getSpeed());
                                return;
                            case 3:
                                Wx32DownModel.this.m18726j();
                                return;
                            default:
                                return;
                        }
                    }
                }
            };
        }
        this.f10919e.registerReceiver(context, intentFilter);
    }

    /* compiled from: Wx32DownModel.java */
    /* renamed from: com.nrzs.game.model.b$6 */
    /* loaded from: classes2.dex */
    static /* synthetic */ class C20836 {

        /* renamed from: a */
        static final /* synthetic */ int[] f10932a = new int[BaseDownloadStateFactory.State.values().length];

        static {
            try {
                f10932a[BaseDownloadStateFactory.State.DOWNLOADED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f10932a[BaseDownloadStateFactory.State.DOWNLOADING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f10932a[BaseDownloadStateFactory.State.DOWNLOAD_FAILED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m18745a(String str, long j, long j2, long j3) {
        AbstractC2084a aVar = this.f10920f;
        if (aVar != null) {
            aVar.m18713a(j, j2);
        }
    }

    /* renamed from: d */
    public void m18733d() {
        BroadcastReceiver broadcastReceiver = this.f10919e;
        if (broadcastReceiver != null) {
            broadcastReceiver.unregisterReceiver();
        }
        this.f10920f = null;
    }

    /* renamed from: a */
    private boolean m18752a(BaseDownloadInfo baseDownloadInfo) {
        return !FileUtils.m22229b(baseDownloadInfo.getSaveDir() + baseDownloadInfo.getSaveName());
    }

    /* renamed from: a */
    public void m18746a(String str) {
        m18738b(str);
    }

    /* renamed from: b */
    private void m18738b(String str) {
        LogUtils.m23742b("TAG", "startDownloadWx");
        BaseDownloadInfo a = m18744a(NRZSFileConfig.f16563u, str);
        this.f10918d = a;
        if (a != null && a.isDownLoaded()) {
            m18727i();
        } else if (a == null) {
            m18726j();
        } else {
            m18754a(6);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: i */
    public void m18727i() {
        m18754a(1);
        BaseDownloadInfo baseDownloadInfo = this.f10918d;
        if (baseDownloadInfo != null) {
            m18734c(baseDownloadInfo.getSavePath());
        }
        AbstractC2084a aVar = this.f10920f;
        if (aVar != null) {
            aVar.m18715a();
        }
    }

    /* renamed from: e */
    public int m18731e() {
        return this.f10917c;
    }

    /* renamed from: a */
    public void m18754a(int i) {
        this.f10917c = i;
        AbstractC2084a aVar = this.f10920f;
        if (aVar != null) {
            aVar.m18711b(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: Wx32DownModel.java */
    /* renamed from: com.nrzs.game.model.b$3 */
    /* loaded from: classes2.dex */
    public class C20763 implements DoneCallback<AppInfo> {
        C20763() {
        }

        /* renamed from: a */
        public void onDone(AppInfo bVar) {
            final AppInfoLite appInfoLite = new AppInfoLite(bVar);
            Wx32DownModel.this.m18754a(3);
            VUiKit.m11713a().mo3332a(new Callable() { // from class: com.nrzs.game.model.-$$Lambda$b$3$3-yquj5gIaVorsaMEm-xchZySP4
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    AppInstallResult a;
                    a = Wx32DownModel.C20763.this.m18724a(appInfoLite);
                    return a;
                }
            }).mo3282b(new DoneCallback() { // from class: com.nrzs.game.model.-$$Lambda$b$3$keH-A4IThVMch2HozKiW1tvPRrc
                @Override // p110z1.DoneCallback
                public final void onDone(Object obj) {
                    Wx32DownModel.C20763.this.m18722a((AppInstallResult) obj);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: a */
        public /* synthetic */ AppInstallResult m18724a(AppInfoLite appInfoLite) throws Exception {
            return Wx32DownModel.this.f10916a.mo12952a(appInfoLite);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: a */
        public /* synthetic */ void m18722a(AppInstallResult appInstallResult) {
            if (appInstallResult.isSuccess) {
                Wx32DownModel.this.m18754a(4);
                Wx32DownModel.this.f10921g = true;
                return;
            }
            Wx32DownModel.this.m18754a(5);
        }
    }

    /* renamed from: c */
    private void m18734c(String str) {
        if (!this.f10921g) {
            this.f10916a.mo12946b(GameApp.getInstance().m13006b(), str).mo3282b(new C20763());
        }
    }

    /* renamed from: f */
    public boolean m18730f() {
        return this.f10921g;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: j */
    public void m18726j() {
        m18754a(2);
        AbstractC2084a aVar = this.f10920f;
        if (aVar != null) {
            aVar.m18712b();
        }
    }

    /* renamed from: b */
    private void m18741b(int i) {
        AbstractC2084a aVar = this.f10920f;
        if (aVar != null) {
            aVar.m18714a(i);
        }
    }

    /* renamed from: a */
    private void m18743a(final ajc<Boolean> ajcVar) {
        Log.i("LBS_PXKJ", "findWx32InVa start 1");
        this.f10916a.mo12951a(TbsConfig.APP_WX).mo3282b(new DoneCallback<AppData>() { // from class: com.nrzs.game.model.b.4
            /* renamed from: a */
            public void onDone(AppData aVar) {
                Log.i("LBS_PXKJ", "findWx32InVa start 2");
                if (aVar != null) {
                    Log.i("LBS_PXKJ", "findWx32InVa start 3");
                    if (aVar.mo18948o() == 2) {
                        Log.i("LBS_PXKJ", "findWx32InVa start 4");
                        VUiKit.m11713a().mo3332a(new Callable<Boolean>() { // from class: com.nrzs.game.model.b.4.2
                            /* renamed from: a */
                            public Boolean call() throws Exception {
                                Log.i("LBS_PXKJ", "findWx32InVa start 5");
                                boolean a = Wx32DownModel.this.f10916a.mo12950a(TbsConfig.APP_WX, 0);
                                if (a) {
                                    Log.i("LBS_PXKJ", "findWx32InVa start 6");
                                } else {
                                    Log.i("LBS_PXKJ", "findWx32InVa start 7");
                                }
                                return Boolean.valueOf(a);
                            }
                        }).mo3282b(new DoneCallback<Boolean>() { // from class: com.nrzs.game.model.b.4.1
                            /* renamed from: a */
                            public void onDone(Boolean bool) {
                                Log.i("LBS_PXKJ", "findWx32InVa start 8");
                                Wx32DownModel.this.f10921g = false;
                                ajcVar.callback(Boolean.valueOf(Wx32DownModel.this.f10921g));
                            }
                        });
                    } else {
                        Log.i("LBS_PXKJ", "findWx32InVa start 9");
                        Wx32DownModel.this.f10921g = true;
                        ajcVar.callback(Boolean.valueOf(Wx32DownModel.this.f10921g));
                    }
                } else {
                    Log.i("LBS_PXKJ", "findWx32InVa start 10");
                    if (VirtualCore.get().isOutsideInstalled(TbsConfig.APP_WX)) {
                        Log.i("LBS_PXKJ", "findWx32InVa start 11");
                        if (!VirtualCoreProxy.isRunProcess(TbsConfig.APP_WX)) {
                            Log.i("LBS_PXKJ", "findWx32InVa start 12");
                            VUiKit.m11713a().mo3332a(new Callable<Boolean>() { // from class: com.nrzs.game.model.b.4.4
                                /* renamed from: a */
                                public Boolean call() throws Exception {
                                    Log.i("LBS_PXKJ", "findWx32InVa start 13");
                                    try {
                                        Log.i("LBS_PXKJ", "findWx32InVa start 14");
                                        ApplicationInfo applicationInfo = VirtualCoreProxy.getUnHookPackageManager().getApplicationInfo(TbsConfig.APP_WX, 0);
                                        return Boolean.valueOf(VirtualCoreProxy.installPackageSync(applicationInfo.sourceDir, AppInstallOptions.makeOptions(true, false)).isSuccess);
                                    } catch (Exception unused) {
                                        Log.i("LBS_PXKJ", "findWx32InVa start 15");
                                        Log.i("LBS_PXKJ", "findWx32InVa start 16");
                                        return false;
                                    }
                                }
                            }).mo3282b(new DoneCallback<Boolean>() { // from class: com.nrzs.game.model.b.4.3
                                /* renamed from: a */
                                public void onDone(Boolean bool) {
                                    Log.i("LBS_PXKJ", "findWx32InVa start 17");
                                    if (bool.booleanValue()) {
                                        Log.i("LBS_PXKJ", "findWx32InVa start 18");
                                        Wx32DownModel.this.f10921g = true;
                                    } else {
                                        Wx32DownModel.this.f10921g = false;
                                    }
                                    Log.i("LBS_PXKJ", "findWx32InVa start 19");
                                    ajcVar.callback(Boolean.valueOf(Wx32DownModel.this.f10921g));
                                }
                            });
                        } else {
                            Log.i("LBS_PXKJ", "findWx32InVa start 20");
                            Wx32DownModel.this.f10921g = false;
                            ajcVar.callback(Boolean.valueOf(Wx32DownModel.this.f10921g));
                        }
                    } else {
                        Log.i("LBS_PXKJ", "findWx32InVa start 21");
                        Wx32DownModel.this.f10921g = true;
                        ajcVar.callback(Boolean.valueOf(Wx32DownModel.this.f10921g));
                    }
                }
                Log.i("LBS_PXKJ", "findWx32InVa start 22");
            }
        });
    }

    /* renamed from: g */
    public void m18729g() {
        this.f10920f = null;
    }

    /* renamed from: a */
    private BaseDownloadInfo m18744a(String str, String str2) {
        BaseDownloadInfo downloadInfo = BaseDownloadOperate.getDownloadInfo(GameApp.getInstance().m13006b(), str2);
        if (downloadInfo == null) {
            downloadInfo = m18737b(str, str2);
            if (m18752a(downloadInfo)) {
                m18740b(downloadInfo);
            }
        }
        return downloadInfo;
    }

    /* renamed from: b */
    private BaseDownloadInfo m18737b(String str, String str2) {
        BaseDownloadInfo baseDownloadInfo = new BaseDownloadInfo();
        baseDownloadInfo.setIdentification(str2);
        baseDownloadInfo.setSaveDir(str);
        String substring = str2.substring(str2.lastIndexOf("/") + 1);
        if (TextUtils.isEmpty(substring)) {
            substring = "wx.apk";
        }
        baseDownloadInfo.setSaveName(substring);
        baseDownloadInfo.setUrl(str2);
        return baseDownloadInfo;
    }

    /* renamed from: b */
    private void m18740b(BaseDownloadInfo baseDownloadInfo) {
        BaseDownloadOperate.addNewDownloadTask(GameApp.getInstance().m13006b(), baseDownloadInfo);
    }

    /* renamed from: h */
    public void m18728h() {
        m18743a(new ajc() { // from class: com.nrzs.game.model.-$$Lambda$b$5ef8p6Cgu5ckA28cAiDikh2QtqY
            @Override // p110z1.ajc
            public final void callback(Object obj) {
                Wx32DownModel.this.m18747a((Boolean) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public /* synthetic */ void m18747a(Boolean bool) {
        if (!bool.booleanValue()) {
            new MultVersionRepository().m12772a("", new UICallback<MultVersion>() { // from class: com.nrzs.game.model.b.5
                @Override // com.nrzs.http.UICallback
                /* renamed from: a */
                public void mo3021a(Throwable th) {
                }

                /* renamed from: a  reason: avoid collision after fix types in other method */
                public void mo3022a(MultVersion multVersion) {
                    if (multVersion != null) {
                        Wx32DownModel.this.m18746a(multVersion.getUrl());
                    }
                }
            });
        }
    }
}
