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
import com.nrzs.data.DataApp;
import com.nrzs.data.p066ft.bean.MultVersion;
import com.nrzs.http.UICallback;
import com.nrzs.p072va.AppInstallOptions;
import com.nrzs.p072va.AppInstallResult;
import com.nrzs.p072va.VirtualCoreProxy;
import com.tencent.smtt.sdk.TbsConfig;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;
import p110z1.AppRepository;
import p110z1.DoneCallback;
import p110z1.GameApp;
import p110z1.MultVersionRepository;
import p110z1.NRZSFileConfig;
import p110z1.NRZSLocalConfig;
import p110z1.Promise;
import p110z1.ShareVal;
import p110z1.VUiKit;
import p110z1.apb;
import p110z1.apf;

/* renamed from: com.nrzs.game.model.a */
/* loaded from: classes2.dex */
public class TentcentDownModel {

    /* renamed from: b */
    private static final HashMap<String, Boolean> f10890b = new HashMap<>();

    /* renamed from: c */
    private static TentcentDownModel f10891c;

    /* renamed from: a */
    AppRepository f10892a;

    /* renamed from: e */
    private BaseDownloadInfo f10894e;

    /* renamed from: f */
    private BroadcastReceiver f10895f;

    /* renamed from: g */
    private AbstractC2073b f10896g;

    /* renamed from: d */
    private int f10893d = -1;

    /* renamed from: h */
    private List<MultVersion> f10897h = new ArrayList();

    /* renamed from: i */
    private HashMap<String, String> f10898i = new HashMap<>();

    /* renamed from: j */
    private String[] f10899j = {TbsConfig.APP_WX, TbsConfig.APP_QQ};

    /* compiled from: TentcentDownModel.java */
    /* renamed from: com.nrzs.game.model.a$a */
    /* loaded from: classes2.dex */
    public interface AbstractC2072a {
        void callback(boolean z, String str);
    }

    /* compiled from: TentcentDownModel.java */
    /* renamed from: com.nrzs.game.model.a$b */
    /* loaded from: classes2.dex */
    public interface AbstractC2073b {
        /* renamed from: a */
        void mo11922a();

        /* renamed from: a */
        void mo11921a(int i);

        /* renamed from: a */
        void mo11920a(long j, long j2, String str);

        /* renamed from: a */
        void mo11919a(String str);

        /* renamed from: b */
        void mo11918b(int i);
    }

    private TentcentDownModel() {
        if (!NRZSLocalConfig.m12512d()) {
            this.f10892a = new AppRepository(GameApp.getInstance().m13006b());
            m18800a(GameApp.getInstance().m13006b());
            m18776g();
            m18782c();
            f10890b.clear();
            for (String str : this.f10899j) {
                f10890b.put(str, false);
            }
        }
    }

    /* renamed from: a */
    public static TentcentDownModel m18803a() {
        if (f10891c == null) {
            synchronized (TentcentDownModel.class) {
                if (f10891c == null) {
                    f10891c = new TentcentDownModel();
                }
            }
        }
        return f10891c;
    }

    /* renamed from: a */
    public void m18797a(AbstractC2073b bVar) {
        this.f10896g = bVar;
    }

    /* renamed from: b */
    public void m18788b() {
        m18775h();
    }

    /* renamed from: c */
    public void m18782c() {
        if (!VirtualCore.get().isOutsideInstalled(TbsConfig.APP_WX)) {
            this.f10892a.mo12951a(TbsConfig.APP_WX).mo3282b(new DoneCallback<AppData>() { // from class: com.nrzs.game.model.a.1
                /* renamed from: a */
                public void onDone(AppData aVar) {
                    if (aVar == null) {
                        return;
                    }
                    if (TentcentDownModel.this.f10892a.mo12950a(TbsConfig.APP_WX, 0)) {
                        Log.i("LBS_PXKJ", "isUnistall start 1");
                    } else {
                        Log.i("LBS_PXKJ", "isUnistall start 2");
                    }
                }
            });
        }
    }

    /* renamed from: d */
    public void m18779d() {
        new MultVersionRepository().m12773a(new UICallback<List<MultVersion>>() { // from class: com.nrzs.game.model.a.2
            @Override // com.nrzs.http.UICallback
            /* renamed from: a */
            public void mo3021a(Throwable th) {
                th.printStackTrace();
            }

            /* renamed from: a  reason: avoid collision after fix types in other method */
            public void mo3022a(List<MultVersion> list) {
                if (list != null && list.size() > 0) {
                    TentcentDownModel.this.f10897h = list;
                }
            }
        });
    }

    /* renamed from: e */
    public boolean m18778e() {
        boolean b = apf.m11836b(DataApp.m18939d().m18947a(), ShareVal.f16591a, ShareVal.f16573D, false);
        boolean b2 = apf.m11836b(DataApp.m18939d().m18947a(), ShareVal.f16591a, ShareVal.f16600j, true);
        StringBuilder sb = new StringBuilder();
        sb.append("isShowInstallQQDialog: ");
        sb.append(b2 && !b);
        Log.i("LBS_PXKJ", sb.toString());
        return b2 && !b;
    }

    /* renamed from: f */
    public boolean m18777f() {
        boolean b = apf.m11836b(DataApp.m18939d().m18947a(), ShareVal.f16591a, ShareVal.f16572C, false);
        boolean b2 = apf.m11836b(DataApp.m18939d().m18947a(), ShareVal.f16591a, ShareVal.f16599i, true);
        StringBuilder sb = new StringBuilder();
        sb.append("isShowInstallQQDialog: ");
        sb.append(b2 && !b);
        Log.i("LBS_PXKJ", sb.toString());
        return b2 && !b;
    }

    /* renamed from: g */
    public void m18776g() {
        if (!VirtualCore.get().isOutsideInstalled(TbsConfig.APP_QQ)) {
            this.f10892a.mo12951a(TbsConfig.APP_QQ).mo3282b(new DoneCallback<AppData>() { // from class: com.nrzs.game.model.a.3
                /* renamed from: a */
                public void onDone(AppData aVar) {
                    if (aVar == null) {
                        return;
                    }
                    if (TentcentDownModel.this.f10892a.mo12950a(TbsConfig.APP_QQ, 0)) {
                        Log.i("LBS_PXKJ", "isUnistall start 1");
                    } else {
                        Log.i("LBS_PXKJ", "isUnistall start 2");
                    }
                }
            });
        }
    }

    /* renamed from: a */
    public void m18800a(Context context) {
        IntentFilter intentFilter = new IntentFilter(BaseDownloadWorker.NOTIFY_VIEW_ACTION);
        if (this.f10895f == null) {
            this.f10895f = new BroadcastReceiver() { // from class: com.nrzs.game.model.a.4
                @Override // com.kaopu.receiver.BroadcastReceiver, android.content.BroadcastReceiver
                public void onReceive(Context context2, Intent intent) {
                    BaseDownloadInfo baseDownloadInfo = (BaseDownloadInfo) intent.getParcelableExtra(BaseDownloadWorker.NOTIFY_VIEW_ACTION_EXTRA_INFO_KEY);
                    if (baseDownloadInfo != null && TentcentDownModel.this.f10894e != null) {
                        if (TextUtils.isEmpty(baseDownloadInfo.getIdentification()) || !baseDownloadInfo.getIdentification().equals(TentcentDownModel.this.f10894e.getIdentification())) {
                            LogUtils.m23742b("过滤其他下载广播", TentcentDownModel.this.f10894e.getIdentification());
                            return;
                        }
                        switch (C20717.f10914a[baseDownloadInfo.getState().getState().ordinal()]) {
                            case 1:
                                LogUtils.m23742b("saveDir:" + baseDownloadInfo.getSaveDir());
                                LogUtils.m23742b("saveName:" + baseDownloadInfo.getSaveName());
                                TentcentDownModel.this.m18768o();
                                return;
                            case 2:
                                TentcentDownModel.this.m18792a(baseDownloadInfo.getIdentification(), baseDownloadInfo.getdSize(), baseDownloadInfo.getfSize(), baseDownloadInfo.getSpeed());
                                return;
                            case 3:
                                TentcentDownModel.this.m18767p();
                                return;
                            default:
                                return;
                        }
                    }
                }
            };
        }
        this.f10895f.registerReceiver(context, intentFilter);
    }

    /* compiled from: TentcentDownModel.java */
    /* renamed from: com.nrzs.game.model.a$7 */
    /* loaded from: classes2.dex */
    static /* synthetic */ class C20717 {

        /* renamed from: a */
        static final /* synthetic */ int[] f10914a = new int[BaseDownloadStateFactory.State.values().length];

        static {
            try {
                f10914a[BaseDownloadStateFactory.State.DOWNLOADED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f10914a[BaseDownloadStateFactory.State.DOWNLOADING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f10914a[BaseDownloadStateFactory.State.DOWNLOAD_FAILED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m18792a(String str, long j, long j2, long j3) {
        AbstractC2073b bVar = this.f10896g;
        if (bVar != null) {
            bVar.mo11920a(j, j2, this.f10898i.get(str));
        }
    }

    /* renamed from: h */
    public void m18775h() {
        BroadcastReceiver broadcastReceiver = this.f10895f;
        if (broadcastReceiver != null) {
            broadcastReceiver.unregisterReceiver();
        }
        this.f10896g = null;
    }

    /* renamed from: a */
    private boolean m18799a(BaseDownloadInfo baseDownloadInfo) {
        StringBuilder sb = new StringBuilder();
        sb.append(baseDownloadInfo.getSaveDir());
        sb.append(baseDownloadInfo.getSaveName());
        return !FileUtils.m22229b(sb.toString());
    }

    /* renamed from: a */
    public void m18791a(String str, String str2) {
        LogUtils.m23742b("startDownload packageName：" + str + "  url:" + str2);
        this.f10898i.put(str2, str);
        m18784b(str2, str);
    }

    /* renamed from: b */
    private void m18784b(String str, String str2) {
        LogUtils.m23742b("TAG", "startDownloadWx");
        BaseDownloadInfo a = m18790a(NRZSFileConfig.f16563u, str, str2);
        this.f10894e = a;
        if (a != null && a.isDownLoaded()) {
            m18768o();
        } else if (a == null) {
            m18767p();
        } else {
            m18802a(6);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: o */
    public void m18768o() {
        m18802a(1);
        BaseDownloadInfo baseDownloadInfo = this.f10894e;
        if (baseDownloadInfo != null) {
            String str = this.f10898i.get(baseDownloadInfo.getUrl());
            if (!TextUtils.isEmpty(str) && Arrays.asList(this.f10899j).contains(str)) {
                m18780c(this.f10894e.getSavePath(), str);
            }
            this.f10898i.remove(this.f10894e.getUrl());
        }
        AbstractC2073b bVar = this.f10896g;
        if (bVar != null) {
            bVar.mo11919a(this.f10894e.getSavePath());
        }
    }

    /* renamed from: i */
    public int m18774i() {
        return this.f10893d;
    }

    /* renamed from: a */
    public void m18802a(int i) {
        this.f10893d = i;
        AbstractC2073b bVar = this.f10896g;
        if (bVar != null) {
            bVar.mo11918b(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: TentcentDownModel.java */
    /* renamed from: com.nrzs.game.model.a$5 */
    /* loaded from: classes2.dex */
    public class C20655 implements DoneCallback<AppInfo> {

        /* renamed from: a */
        final /* synthetic */ String f10904a;

        C20655(String str) {
            this.f10904a = str;
        }

        /* renamed from: a */
        public void onDone(AppInfo bVar) {
            final AppInfoLite appInfoLite = new AppInfoLite(bVar);
            TentcentDownModel.this.m18802a(3);
            Promise a = VUiKit.m11713a().mo3332a(new Callable() { // from class: com.nrzs.game.model.-$$Lambda$a$5$skW_YweNuIR97XT6guZvyNlw-qg
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    AppInstallResult a2;
                    a2 = TentcentDownModel.C20655.this.m18763a(appInfoLite);
                    return a2;
                }
            });
            final String str = this.f10904a;
            a.mo3282b(new DoneCallback() { // from class: com.nrzs.game.model.-$$Lambda$a$5$Q9l6dKF4dGCvuHkkU-SR9v-BY9Y
                @Override // p110z1.DoneCallback
                public final void onDone(Object obj) {
                    TentcentDownModel.C20655.this.m18761a(str, (AppInstallResult) obj);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: a */
        public /* synthetic */ AppInstallResult m18763a(AppInfoLite appInfoLite) throws Exception {
            return TentcentDownModel.this.f10892a.mo12952a(appInfoLite);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: a */
        public /* synthetic */ void m18761a(String str, AppInstallResult appInstallResult) {
            if (appInstallResult.isSuccess) {
                TentcentDownModel.this.m18802a(4);
                TentcentDownModel.f10890b.put(str, true);
                return;
            }
            TentcentDownModel.this.m18802a(5);
        }
    }

    /* renamed from: c */
    private void m18780c(String str, String str2) {
        if (!f10890b.get(str2).booleanValue()) {
            this.f10892a.mo12946b(GameApp.getInstance().m13006b(), str).mo3282b(new C20655(str2));
        }
    }

    /* renamed from: j */
    public boolean m18773j() {
        return f10890b.get(TbsConfig.APP_WX).booleanValue();
    }

    /* renamed from: k */
    public boolean m18772k() {
        return m18793a(TbsConfig.APP_QQ);
    }

    /* renamed from: a */
    private boolean m18793a(String str) {
        Boolean bool = f10890b.get(str);
        if (bool == null) {
            return true;
        }
        return bool.booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: p */
    public void m18767p() {
        m18802a(2);
        AbstractC2073b bVar = this.f10896g;
        if (bVar != null) {
            bVar.mo11922a();
        }
    }

    /* renamed from: b */
    private void m18787b(int i) {
        AbstractC2073b bVar = this.f10896g;
        if (bVar != null) {
            bVar.mo11921a(i);
        }
    }

    /* renamed from: a */
    private void m18798a(AbstractC2072a aVar) {
        int i = NRZSLocalConfig.m12513c() ? 0 : 2;
        Log.i("LBS_PXKJ", "findTentcentInVa bit: " + i);
        m18801a(i, aVar);
    }

    /* renamed from: a */
    private void m18801a(final int i, final AbstractC2072a aVar) {
        String[] strArr;
        for (final String str : this.f10899j) {
            if ((!str.equals(TbsConfig.APP_WX) || apf.m11836b(DataApp.m18939d().m18947a(), ShareVal.f16591a, ShareVal.f16599i, true)) && (!str.equals(TbsConfig.APP_QQ) || apf.m11836b(DataApp.m18939d().m18947a(), ShareVal.f16591a, ShareVal.f16600j, true))) {
                Log.i("LBS_PXKJ", "findTentcentInVa start 1  " + str);
                this.f10892a.mo12951a(str).mo3282b(new DoneCallback<AppData>() { // from class: com.nrzs.game.model.a.6
                    /* renamed from: a */
                    public void onDone(AppData aVar2) {
                        Log.i("LBS_PXKJ", "findTentcentInVa start 2   " + str);
                        if (aVar2 != null) {
                            Log.i("LBS_PXKJ", "findTentcentInVa start 3     " + str + "flag:" + aVar2.mo18948o() + "  bit:" + i);
                            if (aVar2.mo18948o() != i) {
                                Log.i("LBS_PXKJ", "findTentcentInVa start 4   " + str);
                                VUiKit.m11713a().mo3332a(new Callable<Boolean>() { // from class: com.nrzs.game.model.a.6.2
                                    /* renamed from: a */
                                    public Boolean call() throws Exception {
                                        Log.i("LBS_PXKJ", "findTentcentInVa start 5   " + str);
                                        boolean a = TentcentDownModel.this.f10892a.mo12950a(TbsConfig.APP_WX, 0);
                                        if (a) {
                                            Log.i("LBS_PXKJ", "findTentcentInVa start 6    " + str);
                                        } else {
                                            Log.i("LBS_PXKJ", "findTentcentInVa start 7    " + str);
                                        }
                                        return Boolean.valueOf(a);
                                    }
                                }).mo3282b(new DoneCallback<Boolean>() { // from class: com.nrzs.game.model.a.6.1
                                    /* renamed from: a */
                                    public void onDone(Boolean bool) {
                                        Log.i("LBS_PXKJ", "findTentcentInVa start 8    " + str);
                                        TentcentDownModel.f10890b.put(str, false);
                                        aVar.callback(false, str);
                                    }
                                });
                            } else {
                                Log.i("LBS_PXKJ", "findTentcentInVa start 9    " + str);
                                TentcentDownModel.f10890b.put(str, true);
                                aVar.callback(true, str);
                            }
                        } else {
                            Log.i("LBS_PXKJ", "findTentcentInVa start 10    " + str);
                            if (VirtualCore.get().isOutsideInstalled(str)) {
                                Log.i("LBS_PXKJ", "findTentcentInVa start 11    " + str);
                                if (!VirtualCoreProxy.isRunProcess(str)) {
                                    Log.i("LBS_PXKJ", "findTentcentInVa start 12    " + str);
                                    VUiKit.m11713a().mo3332a(new Callable<Boolean>() { // from class: com.nrzs.game.model.a.6.4
                                        /* renamed from: a */
                                        public Boolean call() throws Exception {
                                            Log.i("LBS_PXKJ", "findTentcentInVa start 13    " + str);
                                            try {
                                                Log.i("LBS_PXKJ", "findTentcentInVa start 14    " + str);
                                                ApplicationInfo applicationInfo = VirtualCoreProxy.getUnHookPackageManager().getApplicationInfo(str, 0);
                                                return Boolean.valueOf(VirtualCoreProxy.installPackageSync(applicationInfo.sourceDir, AppInstallOptions.makeOptions(true, false)).isSuccess);
                                            } catch (Exception unused) {
                                                Log.i("LBS_PXKJ", "findTentcentInVa start 15    " + str);
                                                Log.i("LBS_PXKJ", "findTentcentInVa start 16    " + str);
                                                return false;
                                            }
                                        }
                                    }).mo3282b(new DoneCallback<Boolean>() { // from class: com.nrzs.game.model.a.6.3
                                        /* renamed from: a */
                                        public void onDone(Boolean bool) {
                                            Log.i("LBS_PXKJ", "findTentcentInVa start 17    " + str);
                                            TentcentDownModel.f10890b.put(str, bool);
                                            Log.i("LBS_PXKJ", "result start 19    " + str + "  isInstall64:" + TentcentDownModel.f10890b.get(str));
                                            aVar.callback(bool.booleanValue(), str);
                                        }
                                    });
                                } else {
                                    Log.i("LBS_PXKJ", "findTentcentInVa start 20    " + str);
                                    TentcentDownModel.f10890b.put(str, false);
                                    aVar.callback(false, str);
                                }
                            } else {
                                Log.i("LBS_PXKJ", "findTentcentInVa start 21");
                                TentcentDownModel.f10890b.put(str, true);
                                aVar.callback(true, str);
                            }
                        }
                        Log.i("LBS_PXKJ", "findTentcentInVa start 22  " + str);
                    }
                });
            }
        }
    }

    /* renamed from: l */
    public void m18771l() {
        this.f10896g = null;
    }

    /* renamed from: a */
    private BaseDownloadInfo m18790a(String str, String str2, String str3) {
        BaseDownloadInfo downloadInfo = BaseDownloadOperate.getDownloadInfo(GameApp.getInstance().m13006b(), str2);
        if (downloadInfo == null) {
            downloadInfo = m18783b(str, str2, str3);
            if (m18799a(downloadInfo)) {
                m18786b(downloadInfo);
            }
        }
        return downloadInfo;
    }

    /* renamed from: b */
    private BaseDownloadInfo m18783b(String str, String str2, String str3) {
        BaseDownloadInfo baseDownloadInfo = new BaseDownloadInfo();
        baseDownloadInfo.setIdentification(str2);
        baseDownloadInfo.setSaveDir(str);
        baseDownloadInfo.setSaveName(apb.m11874a(str2) + ".apk");
        baseDownloadInfo.setUrl(str2);
        return baseDownloadInfo;
    }

    /* renamed from: b */
    private void m18786b(BaseDownloadInfo baseDownloadInfo) {
        BaseDownloadOperate.addNewDownloadTask(GameApp.getInstance().m13006b(), baseDownloadInfo);
    }

    /* renamed from: m */
    public void m18770m() {
        if (!NRZSLocalConfig.m12512d()) {
            m18798a(new AbstractC2072a() { // from class: com.nrzs.game.model.-$$Lambda$a$Cs6BDctCcZGGpUPpExdAXvXxi1s
                @Override // com.nrzs.game.model.TentcentDownModel.AbstractC2072a
                public final void callback(boolean z, String str) {
                    TentcentDownModel.this.m18789a(z, str);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public /* synthetic */ void m18789a(boolean z, String str) {
        if (!z) {
            for (MultVersion multVersion : this.f10897h) {
                if (str.equals(multVersion.getPackage())) {
                    m18791a(str, multVersion.getUrl());
                }
            }
        }
    }
}
