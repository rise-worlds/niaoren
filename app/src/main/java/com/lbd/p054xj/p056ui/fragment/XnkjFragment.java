package com.lbd.p054xj.p056ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.NetworkUtils;
import com.kaopu.download.BaseDownloadStateFactory;
import com.kaopu.download.BaseDownloadWorker;
import com.kaopu.download.kernel.BaseDownloadInfo;
import com.kaopu.receiver.BroadcastReceiver;
import com.lbd.p054xj.C1467R;
import com.lbd.p054xj.app.AppConfig;
import com.lbd.p054xj.app.XJApp;
import com.lbd.p054xj.manager.XnkjPluginManager;
import com.lbd.p054xj.manager.launch.BoxLaunchManager;
import com.lbd.p054xj.manager.launch.LaunchFileSocket;
import com.lbd.p054xj.p056ui.dialog.AppRomUpdateDialog;
import com.lbd.p054xj.service.XJFloatService;
import com.lbd.p054xj.socket.C1545f;
import com.nrzs.base.router.RouterConstants;
import com.nrzs.base.router.RouterUtils;
import com.nrzs.data.other.bean.AdResultInfoItem;
import com.nrzs.data.xnkj.bean.AppUpdateInfo;
import com.nrzs.libcommon.BaseFragment;
import java.io.PrintStream;
import p110z1.EventBus;
import p110z1.EventCollectManager;
import p110z1.EventConstants;
import p110z1.FloatingPermissionCompat;
import p110z1.GetAppItemTask;
import p110z1.IntentToAssistService;
import p110z1.LoginManager;
import p110z1.ObjectCallback;
import p110z1.PreSetListManager;
import p110z1.RomDownloadEvent;
import p110z1.StartGameTask;
import p110z1.StartXNKJEvent;
import p110z1.Subscribe;
import p110z1.ThreadMode;
import p110z1.ToastUtil;
import p110z1.UnZipEvent;
import p110z1.VersionHelper;

@Route(path = RouterConstants.ModuleXNKJ.XNKJ)
/* renamed from: com.lbd.xj.ui.fragment.XnkjFragment */
/* loaded from: classes.dex */
public class XnkjFragment extends BaseFragment implements View.OnClickListener {

    /* renamed from: b */
    private TextView f9789b;

    /* renamed from: c */
    private TextView f9790c;

    /* renamed from: g */
    private TextView f9791g;

    /* renamed from: h */
    private ImageView f9792h;

    /* renamed from: i */
    private BoxLaunchManager f9793i;

    /* renamed from: k */
    private BaseDownloadInfo f9795k;

    /* renamed from: l */
    private LoadingFragment f9796l;

    /* renamed from: n */
    private BroadcastReceiver f9798n;

    /* renamed from: j */
    private volatile boolean f9794j = false;

    /* renamed from: a */
    int f9788a = -1;

    /* renamed from: m */
    private boolean f9797m = false;

    @Override // com.nrzs.libcommon.BaseFragment
    /* renamed from: b */
    protected int mo18216b() {
        return C1467R.layout.nrzs_fragment_main_xnkj_layout;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.nrzs.libcommon.BaseFragment
    /* renamed from: d */
    public void mo18212d() {
        super.mo18212d();
        EventBus.m3448a().m3446a(this);
        m19403h();
        this.f9788a = XnkjPluginManager.getInstance().checkRomState();
        PrintStream printStream = System.out;
        printStream.println("XnkjPluginManager state:" + this.f9788a);
        switch (this.f9788a) {
            case 0:
                if (!NetworkUtils.m23635j()) {
                    this.f9790c.setVisibility(0);
                } else {
                    this.f9790c.setVisibility(8);
                }
                m19399l();
                this.f9791g.setText(C1467R.string.bird_back_video_btn_download);
                this.f9789b.setVisibility(0);
                return;
            case 1:
            case 2:
                this.f9791g.setText(C1467R.string.bird_back_video_btn_run);
                this.f9790c.setVisibility(8);
                this.f9789b.setVisibility(8);
                return;
            default:
                return;
        }
    }

    /* renamed from: g */
    private void m19404g() {
        if (LoginManager.m12620d().m12606r() && this.f9788a == 1 && !this.f9797m) {
            this.f9797m = true;
            XnkjPluginManager.getInstance().updateVersionRom(new ObjectCallback() { // from class: com.lbd.xj.ui.fragment.-$$Lambda$XnkjFragment$6K_RoFvgjyv9uRl4cEm6ZAxsBJM
                @Override // p110z1.ObjectCallback
                public final void call(Object obj) {
                    XnkjFragment.this.m19411a((AppUpdateInfo) obj);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public /* synthetic */ void m19411a(AppUpdateInfo appUpdateInfo) {
        if (appUpdateInfo != null && appUpdateInfo.getPackageUpdateType() == 1) {
            if (appUpdateInfo.getVersionCode() > VersionHelper.m13827c().f15480b && getActivity() != null && !getActivity().isFinishing()) {
                new AppRomUpdateDialog(getContext(), appUpdateInfo).show();
            }
        }
    }

    /* renamed from: h */
    private void m19403h() {
        if (this.f9793i == null) {
            this.f9793i = new BoxLaunchManager();
            this.f9793i.setCallBack(new BoxLaunchManager.BoxLaunchCallback() { // from class: com.lbd.xj.ui.fragment.XnkjFragment.1
                @Override // com.lbd.p054xj.manager.launch.BoxLaunchManager.BoxLaunchCallback
                public void launchReady() {
                }

                @Override // com.lbd.p054xj.manager.launch.BoxLaunchManager.BoxLaunchCallback
                public void unZipSucceed() {
                }

                @Override // com.lbd.p054xj.manager.launch.BoxLaunchManager.BoxLaunchCallback
                public void upZipFail() {
                }

                @Override // com.lbd.p054xj.manager.launch.BoxLaunchManager.BoxLaunchCallback
                public void unZipProgress(int i) {
                    EventBus.m3448a().m3427d(new UnZipEvent(i));
                }

                @Override // com.lbd.p054xj.manager.launch.BoxLaunchManager.BoxLaunchCallback
                public void patchUnZip() {
                    EventBus.m3448a().m3427d(new UnZipEvent(-1));
                }
            });
            if (BoxLaunchManager.none == 0) {
                new LaunchFileSocket(XJApp.getInstance().getApplicationContext());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.nrzs.libcommon.BaseFragment
    /* renamed from: a */
    public void mo18221a(View view) {
        super.mo18221a(view);
        this.f9789b = (TextView) view.findViewById(C1467R.C1469id.tv_frist_tip);
        this.f9790c = (TextView) view.findViewById(C1467R.C1469id.tv_no_wifi);
        this.f9791g = (TextView) view.findViewById(C1467R.C1469id.tv_start);
        this.f9792h = (ImageView) view.findViewById(C1467R.C1469id.iv_video);
        this.f9792h.setOnClickListener(this);
        this.f9791g.setOnClickListener(this);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == C1467R.C1469id.tv_start) {
            if (!LoginManager.m12620d().m12606r()) {
                IntentToAssistService.m12813a(getContext());
                return;
            }
            this.f9788a = XnkjPluginManager.getInstance().checkRomState();
            int i = this.f9788a;
            if (i == 2) {
                EventCollectManager.m12642a().m12640a(getContext(), "后台挂机开始", "后台挂机开始", EventConstants.f16404H);
                m19402i();
                m19413a(1);
            } else if (i == 1) {
                EventCollectManager.m12642a().m12640a(getContext(), "后台挂机开始", "后台挂机开始", EventConstants.f16404H);
                m19402i();
            } else if (!AppConfig.f9435b) {
                EventCollectManager.m12642a().m12640a(getContext(), "后台挂机下载", "后台挂机下载", EventConstants.f16403G);
                m19413a(0);
            } else {
                m19402i();
                m19413a(1);
            }
        } else if (view.getId() == C1467R.C1469id.iv_video) {
            AdResultInfoItem adResultInfoItem = new AdResultInfoItem();
            adResultInfoItem.Title = "使用教程";
            adResultInfoItem.ExecArgs = PreSetListManager.m12116a().m12112d();
            RouterUtils.toMainWeb(0, 1, adResultInfoItem);
        }
    }

    /* renamed from: i */
    private void m19402i() {
        C1545f.m19586c(new GetAppItemTask());
        BoxLaunchManager boxLaunchManager = this.f9793i;
        if (boxLaunchManager != null) {
            boxLaunchManager.setDisplayParam(getActivity(), null);
        }
    }

    /* renamed from: a */
    private void m19413a(int i) {
        getChildFragmentManager().beginTransaction().replace(C1467R.C1469id.content, m19407b(i)).commit();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: j */
    public void m19401j() {
        if (this.f9796l != null) {
            getChildFragmentManager().beginTransaction().remove(this.f9796l).commit();
        }
    }

    /* renamed from: b */
    private LoadingFragment m19407b(int i) {
        if (this.f9796l == null) {
            this.f9796l = LoadingFragment.m19426a(i);
        }
        return this.f9796l;
    }

    /* renamed from: k */
    private void m19400k() {
        if (!FloatingPermissionCompat.m14338a().m14337a(getActivity())) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 26) {
            getActivity().startForegroundService(new Intent(getActivity(), XJFloatService.class));
        } else {
            getActivity().startService(new Intent(getActivity(), XJFloatService.class));
        }
    }

    @Subscribe(m3389a = ThreadMode.MAIN)
    /* renamed from: a */
    public void m19408a(UnZipEvent acuVar) {
        if (acuVar.m14342a() == 100) {
            this.f9793i.gotoFullActivity(getActivity());
        }
        if (acuVar.m14342a() == -1) {
            this.f9791g.setEnabled(false);
            ToastUtil.m13876a(getContext(), "正在解压更新包...");
        }
    }

    @Subscribe(m3389a = ThreadMode.MAIN)
    /* renamed from: a */
    public void m19410a(RomDownloadEvent acrVar) {
        if (acrVar.m14344a()) {
            m19402i();
        } else {
            m19401j();
        }
    }

    @Subscribe(m3389a = ThreadMode.MAIN)
    /* renamed from: a */
    public void m19409a(StartXNKJEvent actVar) {
        int checkRomState = XnkjPluginManager.getInstance().checkRomState();
        if (checkRomState == 0) {
            RouterUtils.toMain(1);
            return;
        }
        C1545f.m19586c(new StartGameTask(actVar.m14343a()));
        if (checkRomState == 2) {
            RouterUtils.toMain(1);
        }
        this.f9791g.callOnClick();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.nrzs.libcommon.BaseFragment
    /* renamed from: g_ */
    public void mo18544g_() {
        super.mo18544g_();
        m19404g();
    }

    /* renamed from: l */
    private void m19399l() {
        IntentFilter intentFilter = new IntentFilter(BaseDownloadWorker.NOTIFY_VIEW_ACTION);
        if (this.f9798n == null) {
            this.f9798n = new BroadcastReceiver() { // from class: com.lbd.xj.ui.fragment.XnkjFragment.2
                @Override // com.kaopu.receiver.BroadcastReceiver, android.content.BroadcastReceiver
                public void onReceive(Context context, Intent intent) {
                    BaseDownloadInfo baseDownloadInfo = (BaseDownloadInfo) intent.getParcelableExtra(BaseDownloadWorker.NOTIFY_VIEW_ACTION_EXTRA_INFO_KEY);
                    if (baseDownloadInfo != null && XnkjFragment.this.f9795k != null) {
                        if (TextUtils.isEmpty(baseDownloadInfo.getIdentification()) || !baseDownloadInfo.getIdentification().equals(XnkjFragment.this.f9795k.getIdentification())) {
                            LogUtils.m23742b("过滤其他下载广播", XnkjFragment.this.f9795k.getIdentification());
                            return;
                        }
                        switch (C16213.f9801a[baseDownloadInfo.getState().getState().ordinal()]) {
                            case 1:
                                LogUtils.m23742b("saveDir:" + baseDownloadInfo.getSaveDir());
                                LogUtils.m23742b("saveName:" + baseDownloadInfo.getSaveName());
                                XnkjPluginManager instance = XnkjPluginManager.getInstance();
                                instance.saveDownloadComplete(baseDownloadInfo.getSaveDir() + baseDownloadInfo.getSaveName());
                                XnkjFragment.this.m19405c();
                                return;
                            case 2:
                                XnkjFragment.this.m19401j();
                                return;
                            default:
                                return;
                        }
                    }
                }
            };
        }
        this.f9798n.registerReceiver(getContext(), intentFilter);
    }

    /* renamed from: com.lbd.xj.ui.fragment.XnkjFragment$3 */
    /* loaded from: classes.dex */
    static /* synthetic */ class C16213 {

        /* renamed from: a */
        static final /* synthetic */ int[] f9801a = new int[BaseDownloadStateFactory.State.values().length];

        static {
            try {
                f9801a[BaseDownloadStateFactory.State.DOWNLOADED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f9801a[BaseDownloadStateFactory.State.DOWNLOAD_FAILED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* renamed from: m */
    private void m19398m() {
        BroadcastReceiver broadcastReceiver = this.f9798n;
        if (broadcastReceiver != null) {
            broadcastReceiver.unregisterReceiver();
        }
    }

    /* renamed from: c */
    public void m19405c() {
        this.f9788a = 2;
        this.f9791g.setText(C1467R.string.bird_back_video_btn_run);
        this.f9790c.setVisibility(8);
        this.f9789b.setVisibility(8);
    }

    @Override // com.nrzs.libcommon.BaseFragment, android.support.p003v4.app.Fragment
    public void onDestroy() {
        EventBus.m3448a().m3430c(this);
        m19398m();
        super.onDestroy();
    }
}
