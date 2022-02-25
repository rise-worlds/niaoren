package com.lbd.p054xj.p056ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.kaopu.download.BaseDownloadStateFactory;
import com.kaopu.download.BaseDownloadWorker;
import com.kaopu.download.kernel.BaseDownloadInfo;
import com.kaopu.receiver.BroadcastReceiver;
import com.lbd.p054xj.C1467R;
import com.lbd.p054xj.manager.XnkjPluginManager;
import com.nrzs.data.xnkj.bean.AppUpdateInfo;
import java.io.File;
import p110z1.ObjectCallback;
import p110z1.PreferencesUtil;
import p110z1.ToastUtil;
import p110z1.acf;

/* renamed from: com.lbd.xj.ui.dialog.a */
/* loaded from: classes.dex */
public class AppRomUpdateDialog extends Dialog implements View.OnClickListener {

    /* renamed from: a */
    private static int f9682a = 801;

    /* renamed from: b */
    private static int f9683b = 802;

    /* renamed from: c */
    private static int f9684c = 803;

    /* renamed from: d */
    private static int f9685d = 804;

    /* renamed from: e */
    private TextView f9686e;

    /* renamed from: f */
    private TextView f9687f;

    /* renamed from: g */
    private TextView f9688g;

    /* renamed from: h */
    private TextView f9689h;

    /* renamed from: i */
    private ProgressBar f9690i;

    /* renamed from: j */
    private ImageView f9691j;

    /* renamed from: k */
    private AppUpdateInfo f9692k;

    /* renamed from: l */
    private BaseDownloadInfo f9693l;

    /* renamed from: m */
    private BroadcastReceiver f9694m;

    /* renamed from: n */
    private Handler f9695n = new Handler() { // from class: com.lbd.xj.ui.dialog.a.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what == AppRomUpdateDialog.f9682a) {
                AppRomUpdateDialog.this.f9693l = (BaseDownloadInfo) message.obj;
                Toast.makeText(AppRomUpdateDialog.this.getContext(), "下载完成", 1).show();
                File file = new File(AppRomUpdateDialog.this.f9693l.getSaveDir(), AppRomUpdateDialog.this.f9693l.getSaveName());
                if (file.exists()) {
                    PreferencesUtil.m13937a().m13931a(acf.f15181f, Integer.valueOf(AppRomUpdateDialog.this.f9692k.getVersionCode()));
                    PreferencesUtil.m13937a().m13931a(acf.f15180e, (Object) file.getAbsolutePath());
                }
                AppRomUpdateDialog.this.dismiss();
            }
            if (message.what == AppRomUpdateDialog.f9683b) {
                AppRomUpdateDialog.this.f9693l = (BaseDownloadInfo) message.obj;
                Toast.makeText(AppRomUpdateDialog.this.getContext(), "更新失败，请稍后重试", 1).show();
                AppRomUpdateDialog.this.f9690i.setVisibility(0);
                AppRomUpdateDialog.this.f9690i.setMax(100);
                AppRomUpdateDialog.this.f9690i.setProgress(100);
                AppRomUpdateDialog.this.f9686e.setText("重试");
                AppRomUpdateDialog.this.f9686e.setVisibility(0);
            }
            if (message.what == AppRomUpdateDialog.f9684c) {
                AppRomUpdateDialog.this.f9693l = (BaseDownloadInfo) message.obj;
                AppRomUpdateDialog.this.f9690i.setProgress(message.arg2);
                AppRomUpdateDialog.this.f9693l = (BaseDownloadInfo) message.obj;
            }
        }
    };

    public AppRomUpdateDialog(@NonNull Context context, AppUpdateInfo appUpdateInfo) {
        super(context, C1467R.style.MyDialog);
        this.f9692k = appUpdateInfo;
        m19481d();
    }

    public AppRomUpdateDialog(@NonNull Context context, int i) {
        super(context, i);
        m19481d();
    }

    /* renamed from: d */
    private void m19481d() {
        setContentView(C1467R.layout.dialog_update);
        this.f9691j = (ImageView) findViewById(C1467R.C1469id.iv_update_close);
        this.f9691j.setOnClickListener(this);
        this.f9686e = (TextView) findViewById(C1467R.C1469id.tv_update_sure);
        this.f9686e.setOnClickListener(this);
        this.f9687f = (TextView) findViewById(C1467R.C1469id.tv_update_size);
        this.f9688g = (TextView) findViewById(C1467R.C1469id.tv_update_versionname);
        this.f9689h = (TextView) findViewById(C1467R.C1469id.tv_update_content);
        this.f9689h.setMovementMethod(ScrollingMovementMethod.getInstance());
        this.f9690i = (ProgressBar) findViewById(C1467R.C1469id.rlProgressBar);
        m19479e();
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        m19476g();
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        m19475h();
    }

    /* renamed from: e */
    private void m19479e() {
        AppUpdateInfo appUpdateInfo = this.f9692k;
        if (appUpdateInfo != null) {
            this.f9689h.setText(appUpdateInfo.getUpdateContent());
            TextView textView = this.f9688g;
            textView.setText("版本号:" + this.f9692k.getVersionNum());
            TextView textView2 = this.f9687f;
            textView2.setText("大小:" + this.f9692k.getFileSzie() + "M");
            this.f9686e.setText(C1467R.string.bird_pop_update_btn);
            if (this.f9692k.getUpdateType() == 2) {
                this.f9691j.setVisibility(8);
                setCancelable(false);
            }
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == C1467R.C1469id.iv_update_close) {
            dismiss();
        } else if (id != C1467R.C1469id.tv_update_sure) {
        } else {
            if (!TextUtils.isEmpty(this.f9692k.getInstallUrl())) {
                m19477f();
            } else {
                dismiss();
            }
        }
    }

    /* renamed from: f */
    private void m19477f() {
        XnkjPluginManager.getInstance().startPatchloadRom(this.f9692k, new ObjectCallback<BaseDownloadInfo>() { // from class: com.lbd.xj.ui.dialog.a.2
            /* renamed from: a */
            public void call(BaseDownloadInfo baseDownloadInfo) {
                if (baseDownloadInfo != null && baseDownloadInfo.isDownLoaded()) {
                    AppRomUpdateDialog.this.dismiss();
                    ToastUtil.m13876a(AppRomUpdateDialog.this.getContext(), "文件下载成功");
                    File file = new File(baseDownloadInfo.getSaveDir(), baseDownloadInfo.getSaveName());
                    if (file.exists()) {
                        PreferencesUtil.m13937a().m13931a(acf.f15181f, Integer.valueOf(AppRomUpdateDialog.this.f9692k.getVersionCode()));
                        PreferencesUtil.m13937a().m13931a(acf.f15180e, (Object) file.getAbsolutePath());
                    }
                }
            }
        });
    }

    /* renamed from: g */
    private void m19476g() {
        IntentFilter intentFilter = new IntentFilter(BaseDownloadWorker.NOTIFY_VIEW_ACTION);
        if (this.f9694m == null) {
            this.f9694m = new BroadcastReceiver() { // from class: com.lbd.xj.ui.dialog.a.3
                @Override // com.kaopu.receiver.BroadcastReceiver, android.content.BroadcastReceiver
                public void onReceive(Context context, Intent intent) {
                    BaseDownloadInfo baseDownloadInfo = (BaseDownloadInfo) intent.getParcelableExtra(BaseDownloadWorker.NOTIFY_VIEW_ACTION_EXTRA_INFO_KEY);
                    if ((baseDownloadInfo instanceof BaseDownloadInfo) && baseDownloadInfo.getIdentification().equals(AppRomUpdateDialog.this.f9692k.getInstallUrl())) {
                        Message obtainMessage = AppRomUpdateDialog.this.f9695n.obtainMessage();
                        switch (C15974.f9699a[baseDownloadInfo.getState().getState().ordinal()]) {
                            case 1:
                                obtainMessage.what = AppRomUpdateDialog.f9682a;
                                obtainMessage.obj = baseDownloadInfo;
                                AppRomUpdateDialog.this.f9695n.sendMessage(obtainMessage);
                                return;
                            case 2:
                                obtainMessage.what = AppRomUpdateDialog.f9683b;
                                obtainMessage.obj = baseDownloadInfo;
                                AppRomUpdateDialog.this.f9695n.sendMessage(obtainMessage);
                                return;
                            case 3:
                                obtainMessage.what = AppRomUpdateDialog.f9684c;
                                obtainMessage.obj = baseDownloadInfo;
                                obtainMessage.arg1 = (int) baseDownloadInfo.getfSize();
                                obtainMessage.arg2 = (int) ((baseDownloadInfo.getdSize() * 100) / baseDownloadInfo.getfSize());
                                AppRomUpdateDialog.this.f9695n.sendMessage(obtainMessage);
                                return;
                            default:
                                return;
                        }
                    }
                }
            };
        }
        this.f9694m.registerReceiver(getContext(), intentFilter);
    }

    /* compiled from: AppRomUpdateDialog.java */
    /* renamed from: com.lbd.xj.ui.dialog.a$4 */
    /* loaded from: classes.dex */
    static /* synthetic */ class C15974 {

        /* renamed from: a */
        static final /* synthetic */ int[] f9699a = new int[BaseDownloadStateFactory.State.values().length];

        static {
            try {
                f9699a[BaseDownloadStateFactory.State.DOWNLOADED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f9699a[BaseDownloadStateFactory.State.DOWNLOAD_FAILED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f9699a[BaseDownloadStateFactory.State.DOWNLOADING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* renamed from: h */
    private void m19475h() {
        BroadcastReceiver broadcastReceiver = this.f9694m;
        if (broadcastReceiver != null) {
            broadcastReceiver.unregisterReceiver();
        }
    }
}
