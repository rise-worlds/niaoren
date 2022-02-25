package p110z1;

import android.app.Dialog;
import android.content.Context;
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
import com.nrzs.base.C1946R;
import com.nrzs.data.C1959R;
import com.nrzs.data.DataApp;
import p110z1.OrcDownModel;
import p110z1.amu;

/* renamed from: z1.ajt */
/* loaded from: classes3.dex */
public class OrcDownlDialog extends Dialog {

    /* renamed from: a */
    private RelativeLayout f16106a;

    /* renamed from: b */
    private TextView f16107b;

    /* renamed from: c */
    private ImageView f16108c;

    /* renamed from: d */
    private OrcDownModel f16109d;

    /* renamed from: e */
    private ProgressBar f16110e;

    /* renamed from: f */
    private LinearLayout f16111f;

    /* renamed from: g */
    private TextView f16112g;

    /* renamed from: h */
    private TextView f16113h;

    /* renamed from: i */
    private TextView f16114i;

    /* renamed from: j */
    private String f16115j = "";

    /* renamed from: k */
    private AbstractC3549a f16116k;

    /* compiled from: OrcDownlDialog.java */
    /* renamed from: z1.ajt$a */
    /* loaded from: classes3.dex */
    public interface AbstractC3549a {
        /* renamed from: a */
        void mo12817a();

        /* renamed from: b */
        void mo12816b();
    }

    /* renamed from: d */
    private void m12832d() {
    }

    @Override // android.app.Dialog
    protected void onStop() {
    }

    public OrcDownlDialog(Context context, AbstractC3549a aVar) {
        super(context);
        this.f16116k = aVar;
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C1959R.layout.nrzs_orc_down);
        m12836b();
        m12826g();
        m12834c();
        m12828f();
        m12832d();
    }

    /* renamed from: c */
    private void m12834c() {
        if (TextUtils.isEmpty(this.f16115j)) {
            this.f16115j = "http://res2.mobileanjian.com/Resource/ocr/Android.zip";
        }
        String b = apf.m11837b(DataApp.m18939d().m18947a(), ShareVal.f16591a, ShareVal.f16582M, "");
        TextView textView = this.f16114i;
        textView.setText("需下载配套组件才可运行\n大小：" + b);
        this.f16109d = OrcDownModel.m12861a(getContext());
        this.f16109d.m12856a(new OrcDownModel.AbstractC3541b() { // from class: z1.ajt.1
            @Override // p110z1.OrcDownModel.AbstractC3541b
            /* renamed from: a */
            public void mo12821a(int i) {
            }

            @Override // p110z1.OrcDownModel.AbstractC3541b
            /* renamed from: a */
            public void mo12819a(String str) {
                OrcDownlDialog.this.f16107b.setText("安装中..请稍等");
                OrcDownlDialog.this.m12839a(str);
            }

            @Override // p110z1.OrcDownModel.AbstractC3541b
            /* renamed from: a */
            public void mo12822a() {
                OrcDownlDialog.this.f16116k.mo12816b();
            }

            @Override // p110z1.OrcDownModel.AbstractC3541b
            /* renamed from: a */
            public void mo12820a(long j, long j2) {
                int i = (int) ((j * 100) / j2);
                OrcDownlDialog.this.f16110e.setProgress(i);
                TextView textView2 = OrcDownlDialog.this.f16107b;
                textView2.setText("下载中：" + i + "%");
                if (i == 100) {
                    OrcDownlDialog.this.f16107b.setText("安装中..请稍等");
                }
            }

            @Override // p110z1.OrcDownModel.AbstractC3541b
            /* renamed from: b */
            public void mo12818b(int i) {
                OrcDownlDialog.this.m12830e();
            }
        });
        m12830e();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m12839a(String str) {
        amu.m12500a();
        amu.m12497a(str, NRZSFileConfig.f16565w, new amu.AbstractC3677a() { // from class: z1.ajt.2
            @Override // p110z1.amu.AbstractC3677a
            /* renamed from: a */
            public void mo12436a(int i) {
                Log.e("解压", i + "");
            }

            @Override // p110z1.amu.AbstractC3677a
            /* renamed from: a */
            public void mo12435a(String str2) {
                OrcDownlDialog.this.f16116k.mo12816b();
                OrcDownlDialog.this.dismiss();
                Log.e("解压", "解压失败");
                apf.m11842a(DataApp.m18939d().m18947a(), ShareVal.f16591a, ShareVal.f16580K, "");
            }

            @Override // p110z1.amu.AbstractC3677a
            /* renamed from: a */
            public void mo12437a() {
                apf.m11842a(DataApp.m18939d().m18947a(), ShareVal.f16591a, ShareVal.f16580K, apf.m11837b(DataApp.m18939d().m18947a(), ShareVal.f16591a, ShareVal.f16581L, ""));
                Log.e("解压", "解压成功");
                OrcDownlDialog.this.f16116k.mo12817a();
                OrcDownlDialog.this.f16107b.setText("解压成功");
                OrcDownlDialog.this.dismiss();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: e */
    public void m12830e() {
        int c = this.f16109d.m12846c();
        if (c == -1) {
            this.f16107b.setText("连接中....");
        } else if (c != 6) {
            switch (c) {
                case 1:
                    this.f16107b.setText("安装中..请稍等");
                    return;
                case 2:
                    this.f16107b.setText("重试");
                    return;
                case 3:
                    this.f16107b.setText("安装中..请稍等");
                    return;
                case 4:
                    this.f16107b.setText("安装成功");
                    dismiss();
                    return;
                default:
                    return;
            }
        } else {
            this.f16106a.setVisibility(0);
        }
    }

    /* renamed from: f */
    private void m12828f() {
        this.f16106a.setOnClickListener(new View.OnClickListener() { // from class: z1.ajt.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
            }
        });
        this.f16108c.setOnClickListener(new View.OnClickListener() { // from class: z1.ajt.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                OrcDownlDialog.this.dismiss();
            }
        });
        this.f16107b.setOnClickListener(new View.OnClickListener() { // from class: z1.ajt.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                OrcDownlDialog.this.f16106a.setVisibility(0);
            }
        });
        this.f16113h.setOnClickListener(new View.OnClickListener() { // from class: z1.ajt.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                OrcDownlDialog.this.dismiss();
            }
        });
        this.f16112g.setOnClickListener(new View.OnClickListener() { // from class: z1.ajt.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                OrcDownlDialog.this.f16111f.setVisibility(8);
                OrcDownlDialog.this.f16106a.setVisibility(0);
                OrcDownlDialog.this.f16109d.m12859a(OrcDownlDialog.this.f16115j);
            }
        });
    }

    /* renamed from: g */
    private void m12826g() {
        this.f16108c = (ImageView) findViewById(C1959R.C1961id.nrzs_orc_close);
        this.f16106a = (RelativeLayout) findViewById(C1959R.C1961id.rl_progressbar);
        this.f16107b = (TextView) findViewById(C1959R.C1961id.tv_progressbar);
        this.f16110e = (ProgressBar) findViewById(C1959R.C1961id.progress);
        this.f16111f = (LinearLayout) findViewById(C1959R.C1961id.start_lay);
        this.f16112g = (TextView) findViewById(C1959R.C1961id.down_and_run);
        this.f16113h = (TextView) findViewById(C1959R.C1961id.no_run_btn);
        this.f16114i = (TextView) findViewById(C1946R.C1948id.size_tv);
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        m12824h();
    }

    /* renamed from: h */
    private void m12824h() {
        this.f16109d.m12844d();
        this.f16109d = null;
    }

    /* renamed from: a */
    public void m12840a() {
        m12834c();
    }

    /* renamed from: b */
    public void m12836b() {
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        getWindow().setGravity(17);
        getWindow().getDecorView().setPadding(0, 0, 0, 0);
        getWindow().getDecorView().setBackgroundColor(getContext().getResources().getColor(C1959R.color.transparent));
        getWindow().setAttributes(attributes);
    }
}
