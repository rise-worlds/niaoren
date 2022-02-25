package com.lbd.p054xj.p056ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.kaopu.download.BaseDownloadStateFactory;
import com.kaopu.download.BaseDownloadWorker;
import com.kaopu.download.kernel.BaseDownloadInfo;
import com.kaopu.receiver.BroadcastReceiver;
import com.lbd.p054xj.C1467R;
import com.lbd.p054xj.manager.XnkjPluginManager;
import p110z1.EventBus;
import p110z1.ObjectCallback;
import p110z1.RomDownloadEvent;
import p110z1.Subscribe;
import p110z1.ThreadMode;
import p110z1.ToastUtil;
import p110z1.UnZipEvent;
import p110z1.UnitUtils;

/* renamed from: com.lbd.xj.ui.fragment.LoadingFragment */
/* loaded from: classes.dex */
public class LoadingFragment extends AppBaseFragment {

    /* renamed from: f */
    private ProgressBar f9773f;

    /* renamed from: g */
    private TextView f9774g;

    /* renamed from: h */
    private Handler f9775h = new Handler();

    /* renamed from: i */
    private BroadcastReceiver f9776i;

    /* renamed from: a */
    public static LoadingFragment m19426a(int i) {
        LoadingFragment loadingFragment = new LoadingFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", i);
        loadingFragment.setArguments(bundle);
        return loadingFragment;
    }

    @Override // com.lbd.p054xj.base.p055ui.BaseFragment
    /* renamed from: e */
    protected int mo19416e() {
        return C1467R.layout.xj_fragment_loading;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.lbd.p054xj.base.p055ui.BaseFragment
    /* renamed from: b */
    public void mo19417b(View view) {
        this.f9773f = (ProgressBar) view.findViewById(C1467R.C1469id.xj_bar);
        this.f9774g = (TextView) view.findViewById(C1467R.C1469id.xj_tv_tips);
        ((TextView) view.findViewById(C1467R.C1469id.tv_app_ver)).setText("1.3.5");
        if (((Integer) getArguments().get("type")).intValue() == 0) {
            m19422g();
            this.f9774g.setText("插件下载中...");
            XnkjPluginManager.getInstance().startDownloadRom(new ObjectCallback<BaseDownloadInfo>() { // from class: com.lbd.xj.ui.fragment.LoadingFragment.1
                /* renamed from: a */
                public void call(BaseDownloadInfo baseDownloadInfo) {
                    if (baseDownloadInfo == null) {
                        EventBus.m3448a().m3427d(new RomDownloadEvent(false));
                        ToastUtil.m13876a(LoadingFragment.this.getContext(), "下载失败");
                    } else if (baseDownloadInfo.isDownLoaded()) {
                        XnkjPluginManager.getInstance().saveDownloadComplete(baseDownloadInfo.getSavePath());
                        LoadingFragment.this.m19423f();
                    }
                }
            });
            return;
        }
        this.f9774g.setText(getResources().getString(C1467R.string.bird_back_loading_first));
    }

    @Override // com.lbd.p054xj.base.p055ui.BaseFragment, android.support.p003v4.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        m19421h();
        EventBus.m3448a().m3430c(this);
    }

    @Subscribe(m3389a = ThreadMode.MAIN)
    /* renamed from: a */
    public void m19424a(UnZipEvent acuVar) {
        TextView textView = this.f9774g;
        textView.setText("插件安装中..." + acuVar.m14342a() + "%");
        this.f9773f.setProgress(acuVar.m14342a());
    }

    @Override // com.lbd.p054xj.base.p055ui.BaseFragment, android.support.p003v4.app.Fragment
    public void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        EventBus.m3448a().m3446a(this);
    }

    /* renamed from: g */
    private void m19422g() {
        IntentFilter intentFilter = new IntentFilter(BaseDownloadWorker.NOTIFY_VIEW_ACTION);
        if (this.f9776i == null) {
            this.f9776i = new BroadcastReceiver() { // from class: com.lbd.xj.ui.fragment.LoadingFragment.2
                @Override // com.kaopu.receiver.BroadcastReceiver, android.content.BroadcastReceiver
                public void onReceive(Context context, Intent intent) {
                    BaseDownloadInfo baseDownloadInfo = (BaseDownloadInfo) intent.getParcelableExtra(BaseDownloadWorker.NOTIFY_VIEW_ACTION_EXTRA_INFO_KEY);
                    if (!TextUtils.isEmpty(XnkjPluginManager.getInstance().getXnkjDownId()) && baseDownloadInfo.getIdentification().equals(XnkjPluginManager.getInstance().getXnkjDownId()) && (baseDownloadInfo instanceof BaseDownloadInfo)) {
                        switch (C16174.f9780a[baseDownloadInfo.getState().getState().ordinal()]) {
                            case 1:
                                XnkjPluginManager instance = XnkjPluginManager.getInstance();
                                instance.saveDownloadComplete(baseDownloadInfo.getSaveDir() + baseDownloadInfo.getSaveName());
                                LoadingFragment.this.m19423f();
                                return;
                            case 2:
                                LoadingFragment.this.m19425a(baseDownloadInfo.getIdentification(), baseDownloadInfo.getdSize(), baseDownloadInfo.getfSize(), baseDownloadInfo.getSpeed());
                                return;
                            case 3:
                                ToastUtil.m13876a(LoadingFragment.this.getContext(), "下载失败");
                                return;
                            default:
                                return;
                        }
                    }
                }
            };
        }
        this.f9776i.registerReceiver(getContext(), intentFilter);
    }

    /* renamed from: com.lbd.xj.ui.fragment.LoadingFragment$4 */
    /* loaded from: classes.dex */
    static /* synthetic */ class C16174 {

        /* renamed from: a */
        static final /* synthetic */ int[] f9780a = new int[BaseDownloadStateFactory.State.values().length];

        static {
            try {
                f9780a[BaseDownloadStateFactory.State.DOWNLOADED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f9780a[BaseDownloadStateFactory.State.DOWNLOADING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f9780a[BaseDownloadStateFactory.State.DOWNLOAD_FAILED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* renamed from: h */
    private void m19421h() {
        BroadcastReceiver broadcastReceiver = this.f9776i;
        if (broadcastReceiver != null) {
            broadcastReceiver.unregisterReceiver();
        }
    }

    /* renamed from: a */
    public void m19425a(String str, long j, long j2, long j3) {
        TextView textView = this.f9774g;
        textView.setText("插件下载中...（" + UnitUtils.m13875a(j) + "/" + UnitUtils.m13875a(j2) + "）");
        this.f9773f.setProgress((int) ((j * 100) / j2));
    }

    /* renamed from: f */
    public void m19423f() {
        this.f9773f.setProgress(0);
        this.f9775h.postDelayed(new Runnable() { // from class: com.lbd.xj.ui.fragment.LoadingFragment.3
            @Override // java.lang.Runnable
            public void run() {
                EventBus.m3448a().m3427d(new RomDownloadEvent(true));
            }
        }, 200L);
    }

    @Override // android.support.p003v4.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        Handler handler = this.f9775h;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }
}
