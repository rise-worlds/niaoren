package p110z1;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.cyjh.ddy.media.media.ActionCode;
import com.nrzs.data.DataApp;
import com.nrzs.p067ft.C1990R;
import p110z1.amu;
import p110z1.anf;

/* compiled from: OrcDownlDialog.java */
/* renamed from: z1.ang */
/* loaded from: classes3.dex */
public class ang extends Dialog {

    /* renamed from: a */
    private RelativeLayout f16696a;

    /* renamed from: b */
    private TextView f16697b;

    /* renamed from: c */
    private ImageView f16698c;

    /* renamed from: d */
    private anf f16699d;

    /* renamed from: e */
    private ProgressBar f16700e;

    /* renamed from: f */
    private LinearLayout f16701f;

    /* renamed from: g */
    private TextView f16702g;

    /* renamed from: h */
    private TextView f16703h;

    /* renamed from: i */
    private TextView f16704i;

    /* renamed from: k */
    private AbstractC3695a f16706k;

    /* renamed from: j */
    private String f16705j = "";

    /* renamed from: l */
    private ams f16707l = new ams() { // from class: z1.ang.1
        @Override // p110z1.ams
        /* renamed from: a */
        public void mo12446a() {
            ang.this.f16706k.mo12433b();
            ang.this.dismiss();
            apf.m11842a(DataApp.m18939d().m18947a(), ShareVal.f16591a, ShareVal.f16580K, "");
        }

        @Override // p110z1.ams
        /* renamed from: a */
        public void mo12445a(int i) {
            Log.e("解压", ((int) ((i * 0.65d) + 30.0d)) + "");
        }

        @Override // p110z1.ams
        /* renamed from: b */
        public void mo12444b() {
            Log.e("解压", "开始解压");
        }

        @Override // p110z1.ams
        /* renamed from: c */
        public void mo12443c() {
            apf.m11842a(DataApp.m18939d().m18947a(), ShareVal.f16591a, ShareVal.f16580K, apf.m11837b(DataApp.m18939d().m18947a(), ShareVal.f16591a, ShareVal.f16581L, ""));
            ang.this.f16706k.mo12434a();
            ang.this.f16697b.setText("解压成功");
            ang.this.dismiss();
        }
    };

    /* compiled from: OrcDownlDialog.java */
    /* renamed from: z1.ang$a */
    /* loaded from: classes3.dex */
    public interface AbstractC3695a {
        /* renamed from: a */
        void mo12434a();

        /* renamed from: b */
        void mo12433b();
    }

    /* renamed from: e */
    private void m12455e() {
    }

    @Override // android.app.Dialog
    protected void onStop() {
    }

    public ang(Context context, AbstractC3695a aVar) {
        super(context);
        this.f16706k = aVar;
        m12465a();
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C1990R.layout.nrzs_orc_down);
        m12459c();
        m12449h();
        m12457d();
        m12451g();
        m12455e();
    }

    /* renamed from: a */
    public void m12465a() {
        if (getWindow() == null) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 24) {
            getWindow().setType(2005);
        } else if (Build.VERSION.SDK_INT >= 26) {
            getWindow().setType(2038);
        } else {
            getWindow().setType(ActionCode.CtrlConnectRefuse_2002);
        }
    }

    /* renamed from: d */
    private void m12457d() {
        if (TextUtils.isEmpty(this.f16705j)) {
            this.f16705j = "http://res2.mobileanjian.com/Resource/ocr/Android.zip";
        }
        String b = apf.m11837b(DataApp.m18939d().m18947a(), ShareVal.f16591a, ShareVal.f16582M, "");
        TextView textView = this.f16704i;
        textView.setText("需下载配套组件才可运行\n大小：" + b);
        this.f16699d = anf.m12486a(getContext());
        this.f16699d.m12481a(new anf.AbstractC3686b() { // from class: z1.ang.2
            @Override // p110z1.anf.AbstractC3686b
            /* renamed from: a */
            public void mo12441a(int i) {
            }

            @Override // p110z1.anf.AbstractC3686b
            /* renamed from: a */
            public void mo12439a(String str) {
                ang.this.f16697b.setText("安装中..请稍等");
                ang.this.m12464a(str);
            }

            @Override // p110z1.anf.AbstractC3686b
            /* renamed from: a */
            public void mo12442a() {
                ang.this.f16706k.mo12433b();
            }

            @Override // p110z1.anf.AbstractC3686b
            /* renamed from: a */
            public void mo12440a(long j, long j2) {
                int i = (int) ((j * 100) / j2);
                ang.this.f16700e.setProgress(i);
                TextView textView2 = ang.this.f16697b;
                textView2.setText("下载中：" + i + "%");
                if (i == 100) {
                    ang.this.f16697b.setText("安装中..请稍等");
                }
            }

            @Override // p110z1.anf.AbstractC3686b
            /* renamed from: b */
            public void mo12438b(int i) {
                ang.this.m12453f();
            }
        });
        m12453f();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m12464a(String str) {
        String b = apf.m11837b(DataApp.m18939d().m18947a(), ShareVal.f16591a, ShareVal.f16585P, "");
        amu.m12500a();
        amu.m12497a(str, b, new amu.AbstractC3677a() { // from class: z1.ang.3
            @Override // p110z1.amu.AbstractC3677a
            /* renamed from: a */
            public void mo12436a(int i) {
                Log.e("解压", i + "");
            }

            @Override // p110z1.amu.AbstractC3677a
            /* renamed from: a */
            public void mo12435a(String str2) {
                ang.this.f16706k.mo12433b();
                ang.this.dismiss();
                apf.m11842a(DataApp.m18939d().m18947a(), ShareVal.f16591a, ShareVal.f16580K, "");
            }

            @Override // p110z1.amu.AbstractC3677a
            /* renamed from: a */
            public void mo12437a() {
                apf.m11842a(DataApp.m18939d().m18947a(), ShareVal.f16591a, ShareVal.f16580K, apf.m11837b(DataApp.m18939d().m18947a(), ShareVal.f16591a, ShareVal.f16581L, ""));
                ang.this.f16706k.mo12434a();
                ang.this.f16697b.setText("解压成功");
                ang.this.dismiss();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: f */
    public void m12453f() {
        int c = this.f16699d.m12471c();
        if (c == -1) {
            this.f16697b.setText("连接中");
        } else if (c != 6) {
            switch (c) {
                case 1:
                    this.f16697b.setText("安装中..请稍等");
                    return;
                case 2:
                    this.f16697b.setText("重试");
                    return;
                case 3:
                    this.f16697b.setText("安装中..请稍等");
                    return;
                case 4:
                    this.f16697b.setText("安装成功");
                    dismiss();
                    return;
                default:
                    return;
            }
        } else {
            this.f16696a.setVisibility(0);
        }
    }

    /* renamed from: g */
    private void m12451g() {
        this.f16696a.setOnClickListener(new View.OnClickListener() { // from class: z1.ang.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
            }
        });
        this.f16698c.setOnClickListener(new View.OnClickListener() { // from class: z1.ang.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ang.this.dismiss();
            }
        });
        this.f16697b.setOnClickListener(new View.OnClickListener() { // from class: z1.ang.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ang.this.f16696a.setVisibility(0);
            }
        });
        this.f16703h.setOnClickListener(new View.OnClickListener() { // from class: z1.ang.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ang.this.dismiss();
            }
        });
        this.f16702g.setOnClickListener(new View.OnClickListener() { // from class: z1.ang.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ang.this.f16701f.setVisibility(8);
                ang.this.f16696a.setVisibility(0);
                ang.this.f16699d.m12484a(ang.this.f16705j);
            }
        });
    }

    /* renamed from: h */
    private void m12449h() {
        this.f16698c = (ImageView) findViewById(C1990R.C1992id.nrzs_orc_close);
        this.f16696a = (RelativeLayout) findViewById(C1990R.C1992id.rl_progressbar);
        this.f16697b = (TextView) findViewById(C1990R.C1992id.tv_progressbar);
        this.f16700e = (ProgressBar) findViewById(C1990R.C1992id.progress);
        this.f16701f = (LinearLayout) findViewById(C1990R.C1992id.start_lay);
        this.f16702g = (TextView) findViewById(C1990R.C1992id.down_and_run);
        this.f16703h = (TextView) findViewById(C1990R.C1992id.no_run_btn);
        this.f16704i = (TextView) findViewById(C1990R.C1992id.size_tv);
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        m12447i();
    }

    /* renamed from: i */
    private void m12447i() {
        this.f16699d.m12469d();
        this.f16699d = null;
    }

    /* renamed from: b */
    public void m12461b() {
        m12457d();
    }

    /* renamed from: c */
    public void m12459c() {
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        getWindow().getDecorView().setBackgroundColor(getContext().getResources().getColor(C1990R.color.transparent));
        getWindow().setAttributes(attributes);
    }
}
