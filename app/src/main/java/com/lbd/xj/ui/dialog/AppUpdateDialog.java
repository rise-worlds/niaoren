package com.lbd.xj.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.graphics.drawable.PathInterpolatorCompat;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.blankj.utilcode.util.AppUtils;
import com.lbd.xj.C1467R;
import com.nrzs.data.xnkj.bean.AppUpdateInfo;
import java.io.File;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import p110z1.DownLoadTask;
import p110z1.OnDownLoaderListener;
import p110z1.acf;
import p110z1.amx;

/* renamed from: com.lbd.xj.ui.dialog.b */
/* loaded from: classes.dex */
public class AppUpdateDialog extends Dialog implements View.OnClickListener {

    /* renamed from: a */
    private static int f9700a = 801;

    /* renamed from: b */
    private static int f9701b = 802;

    /* renamed from: c */
    private static int f9702c = 803;

    /* renamed from: d */
    private static int f9703d = 804;

    /* renamed from: e */
    private TextView f9704e;

    /* renamed from: f */
    private TextView f9705f;

    /* renamed from: g */
    private TextView f9706g;

    /* renamed from: h */
    private TextView f9707h;

    /* renamed from: i */
    private AppUpdateInfo f9708i;

    /* renamed from: j */
    private ThreadPoolExecutor f9709j;

    /* renamed from: k */
    private DownLoadTask f9710k;

    /* renamed from: l */
    private ProgressBar f9711l;

    /* renamed from: m */
    private ImageView f9712m;

    /* renamed from: n */
    private amx f9713n;

    /* renamed from: o */
    private Handler f9714o = new Handler() { // from class: com.lbd.xj.ui.dialog.b.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what == AppUpdateDialog.f9700a) {
                AppUpdateDialog.this.f9713n = (amx) message.obj;
                Toast.makeText(AppUpdateDialog.this.getContext(), "下载完成", 1).show();
                File file = new File(AppUpdateDialog.this.f9713n.getSaveDir(), AppUpdateDialog.this.f9713n.getFileName());
                if (file.exists()) {
                    AppUtils.m22956a(file);
                }
                AppUpdateDialog.this.dismiss();
            }
            if (message.what == AppUpdateDialog.f9701b) {
                AppUpdateDialog.this.f9713n = (amx) message.obj;
                Toast.makeText(AppUpdateDialog.this.getContext(), "更新失败，请稍后重试", 1).show();
                AppUpdateDialog.this.f9711l.setVisibility(0);
                AppUpdateDialog.this.f9711l.setMax(100);
                AppUpdateDialog.this.f9711l.setProgress(100);
                AppUpdateDialog.this.f9704e.setText("重试");
                AppUpdateDialog.this.f9704e.setVisibility(0);
            }
            if (message.what == AppUpdateDialog.f9702c) {
                AppUpdateDialog.this.f9713n = (amx) message.obj;
                if (!AppUpdateDialog.this.f9713n.isStop()) {
                    AppUpdateDialog.this.f9704e.setText("更新中");
                }
                AppUpdateDialog.this.f9711l.setMax(message.arg1);
                AppUpdateDialog.this.f9711l.setProgress(message.arg2);
                AppUpdateDialog.this.f9713n = (amx) message.obj;
            }
        }
    };

    public AppUpdateDialog(@NonNull Context context, AppUpdateInfo appUpdateInfo) {
        super(context, C1467R.style.MyDialog);
        this.f9708i = appUpdateInfo;
        m19464e();
    }

    public AppUpdateDialog(@NonNull Context context, int i) {
        super(context, i);
        m19464e();
    }

    /* renamed from: e */
    private void m19464e() {
        setContentView(C1467R.layout.dialog_update);
        this.f9712m = (ImageView) findViewById(C1467R.C1469id.iv_update_close);
        this.f9712m.setOnClickListener(this);
        this.f9704e = (TextView) findViewById(C1467R.C1469id.tv_update_sure);
        this.f9704e.setOnClickListener(this);
        this.f9705f = (TextView) findViewById(C1467R.C1469id.tv_update_size);
        this.f9706g = (TextView) findViewById(C1467R.C1469id.tv_update_versionname);
        this.f9707h = (TextView) findViewById(C1467R.C1469id.tv_update_content);
        this.f9707h.setMovementMethod(ScrollingMovementMethod.getInstance());
        this.f9711l = (ProgressBar) findViewById(C1467R.C1469id.rlProgressBar);
        m19463f();
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f9709j = new ThreadPoolExecutor(50, 50, 50L, TimeUnit.SECONDS, new ArrayBlockingQueue(PathInterpolatorCompat.MAX_NUM_POINTS));
    }

    /* renamed from: f */
    private void m19463f() {
        AppUpdateInfo appUpdateInfo = this.f9708i;
        if (appUpdateInfo != null) {
            this.f9707h.setText(appUpdateInfo.getUpdateContent());
            TextView textView = this.f9706g;
            textView.setText("版本号:" + this.f9708i.getVersionNum());
            TextView textView2 = this.f9705f;
            textView2.setText("大小:" + this.f9708i.getFileSzie() + "M");
            this.f9704e.setText(C1467R.string.bird_pop_update_btn);
            if (this.f9708i.getUpdateType() == 2) {
                this.f9712m.setVisibility(8);
                setCancelable(false);
            }
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == C1467R.C1469id.iv_update_close) {
            amx amxVar = this.f9713n;
            if (amxVar != null) {
                amxVar.setStop(true);
            }
            dismiss();
        } else if (id != C1467R.C1469id.tv_update_sure) {
        } else {
            if (!TextUtils.isEmpty(this.f9708i.getInstallUrl())) {
                if (this.f9713n == null) {
                    this.f9713n = new amx();
                    this.f9713n.setSaveDir(acf.f15177b);
                    amx amxVar2 = this.f9713n;
                    amxVar2.setFileName(getContext().getPackageName() + "_" + this.f9708i.getVersionNum() + "_" + this.f9708i.getVersionCode() + ".apk");
                    this.f9713n.setUrl(this.f9708i.getInstallUrl());
                    this.f9713n.setStop(false);
                }
                File file = new File(this.f9713n.getSaveDir(), this.f9713n.getFileName());
                if (file.exists()) {
                    AppUtils.m22956a(file);
                } else {
                    m19462g();
                }
            } else {
                dismiss();
            }
        }
    }

    /* renamed from: g */
    private void m19462g() {
        if (this.f9710k == null) {
            this.f9710k = new DownLoadTask(getContext(), this.f9713n, new OnDownLoaderListener() { // from class: com.lbd.xj.ui.dialog.b.2
                @Override // p110z1.OnDownLoaderListener
                /* renamed from: a */
                public void mo3049a(int i, int i2, double d, amx amxVar) {
                    Message message = new Message();
                    message.what = AppUpdateDialog.f9702c;
                    message.arg1 = i;
                    message.arg2 = i2;
                    message.obj = amxVar;
                    AppUpdateDialog.this.f9714o.sendMessage(message);
                }

                @Override // p110z1.OnDownLoaderListener
                /* renamed from: a */
                public void mo3048a(amx amxVar) {
                    Message message = new Message();
                    message.what = AppUpdateDialog.f9703d;
                    message.obj = amxVar;
                    AppUpdateDialog.this.f9714o.sendMessage(message);
                }

                @Override // p110z1.OnDownLoaderListener
                /* renamed from: a */
                public void mo3047a(amx amxVar, String str) {
                    Message message = new Message();
                    message.what = AppUpdateDialog.f9701b;
                    message.obj = amxVar;
                    AppUpdateDialog.this.f9714o.sendMessage(message);
                }

                @Override // p110z1.OnDownLoaderListener
                /* renamed from: b */
                public void mo3046b(amx amxVar) {
                    Message message = new Message();
                    message.what = AppUpdateDialog.f9700a;
                    message.obj = amxVar;
                    AppUpdateDialog.this.f9714o.sendMessage(message);
                }
            });
            this.f9709j.execute(this.f9710k);
        }
    }
}
