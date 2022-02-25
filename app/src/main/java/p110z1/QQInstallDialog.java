package p110z1;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.nrzs.data.DataApp;
import com.nrzs.game.C2029R;
import com.nrzs.game.model.TentcentDownModel;

/* renamed from: z1.aot */
/* loaded from: classes3.dex */
public class QQInstallDialog extends Dialog {

    /* renamed from: a */
    private RelativeLayout f17067a;

    /* renamed from: b */
    private TextView f17068b;

    /* renamed from: c */
    private ImageView f17069c;

    /* renamed from: d */
    private TextView f17070d;

    /* renamed from: e */
    private TextView f17071e;

    /* renamed from: f */
    private TextView f17072f;

    /* renamed from: g */
    private TentcentDownModel f17073g;

    /* renamed from: h */
    private AbstractC3815a f17074h;

    /* renamed from: i */
    private ProgressBar f17075i;

    /* compiled from: QQInstallDialog.java */
    /* renamed from: z1.aot$a */
    /* loaded from: classes3.dex */
    public interface AbstractC3815a {
        /* renamed from: a */
        void mo11939a();

        /* renamed from: b */
        void mo11938b();
    }

    /* renamed from: d */
    private void m11948d() {
    }

    @Override // android.app.Dialog
    protected void onStop() {
    }

    public QQInstallDialog(Activity activity, AbstractC3815a aVar) {
        super(activity, C2029R.style.NRZS_Game_my_dialog);
        this.f17074h = aVar;
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C2029R.layout.nrzs_game_va_qq_install);
        m11942g();
        m11952b();
        m11944f();
        m11948d();
    }

    /* renamed from: b */
    private void m11952b() {
        this.f17073g = TentcentDownModel.m18803a();
        this.f17073g.m18797a(new TentcentDownModel.AbstractC2073b() { // from class: z1.aot.1
            @Override // com.nrzs.game.model.TentcentDownModel.AbstractC2073b
            /* renamed from: a */
            public void mo11922a() {
            }

            @Override // com.nrzs.game.model.TentcentDownModel.AbstractC2073b
            /* renamed from: a */
            public void mo11921a(int i) {
            }

            @Override // com.nrzs.game.model.TentcentDownModel.AbstractC2073b
            /* renamed from: a */
            public void mo11919a(String str) {
            }

            @Override // com.nrzs.game.model.TentcentDownModel.AbstractC2073b
            /* renamed from: a */
            public void mo11920a(long j, long j2, String str) {
                int i = (int) ((j * 100) / j2);
                QQInstallDialog.this.f17075i.setProgress(i);
                TextView textView = QQInstallDialog.this.f17068b;
                textView.setText("下载中：" + i + "%");
            }

            @Override // com.nrzs.game.model.TentcentDownModel.AbstractC2073b
            /* renamed from: b */
            public void mo11918b(int i) {
                QQInstallDialog.this.m11946e();
            }
        });
        m11946e();
    }

    /* renamed from: c */
    private void m11950c() {
        if (NRZSLocalConfig.m12513c()) {
            this.f17071e.setText("启动64位应用异常");
            this.f17072f.setText("系统检测到当前安装的是64位QQ，需【安装32位QQ】后才可正常使用QQ登录游戏！");
            return;
        }
        this.f17071e.setText("启动32位应用异常");
        this.f17072f.setText("系统检测到当前安装的是32位QQ，需【安装64位QQ】后才可正常使用QQ登录游戏！");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: e */
    public void m11946e() {
        int i = this.f17073g.m18774i();
        if (i == -1) {
            this.f17070d.setText("安装");
            this.f17070d.setBackgroundResource(C2029R.C2030drawable.bird_btn_tool_no_data);
        } else if (i != 6) {
            switch (i) {
                case 1:
                    this.f17070d.setText("下载成功");
                    this.f17070d.setBackgroundResource(C2029R.C2030drawable.bird_btn_tool_no_data);
                    this.f17070d.setVisibility(0);
                    this.f17067a.setVisibility(8);
                    return;
                case 2:
                    this.f17070d.setText("重试");
                    this.f17070d.setBackgroundResource(C2029R.C2030drawable.bird_btn_re_install);
                    this.f17070d.setVisibility(0);
                    this.f17067a.setVisibility(8);
                    return;
                case 3:
                    this.f17070d.setText("安装中..请稍等");
                    this.f17070d.setBackgroundResource(C2029R.C2030drawable.bird_btn_tool_no_data);
                    return;
                case 4:
                    this.f17070d.setText("安装成功");
                    this.f17070d.setBackgroundResource(C2029R.C2030drawable.bird_btn_tool_no_data);
                    dismiss();
                    AbstractC3815a aVar = this.f17074h;
                    if (aVar != null) {
                        aVar.mo11939a();
                        return;
                    }
                    return;
                default:
                    return;
            }
        } else {
            this.f17070d.setVisibility(8);
            this.f17067a.setVisibility(0);
        }
    }

    /* renamed from: f */
    private void m11944f() {
        this.f17067a.setOnClickListener(new View.OnClickListener() { // from class: z1.aot.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
            }
        });
        this.f17069c.setOnClickListener(new View.OnClickListener() { // from class: z1.aot.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (QQInstallDialog.this.f17074h != null) {
                    QQInstallDialog.this.f17074h.mo11938b();
                }
                QQInstallDialog.this.dismiss();
            }
        });
        this.f17068b.setOnClickListener(new View.OnClickListener() { // from class: z1.aot.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                QQInstallDialog.this.f17067a.setVisibility(0);
            }
        });
    }

    /* renamed from: g */
    private void m11942g() {
        this.f17069c = (ImageView) findViewById(C2029R.C2031id.nrzs_game_iv_install_64_close);
        this.f17067a = (RelativeLayout) findViewById(C2029R.C2031id.rl_progressbar);
        this.f17068b = (TextView) findViewById(C2029R.C2031id.tv_progressbar);
        this.f17075i = (ProgressBar) findViewById(C2029R.C2031id.progress);
        this.f17070d = (TextView) findViewById(C2029R.C2031id.nrzs_game_tv_install_64);
        this.f17071e = (TextView) findViewById(C2029R.C2031id.tv_warn_title);
        this.f17072f = (TextView) findViewById(C2029R.C2031id.tv_warn_content);
        m11950c();
        this.f17070d.setOnClickListener(new View.OnClickListener() { // from class: z1.aot.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if ("重试".equals(QQInstallDialog.this.f17070d.getText().toString()) || "安装QQ64位".equals(QQInstallDialog.this.f17070d.getText().toString())) {
                    QQInstallDialog.this.f17073g.m18770m();
                    QQInstallDialog.this.f17070d.setText("连接中");
                    QQInstallDialog.this.f17070d.setBackgroundResource(C2029R.C2030drawable.bird_btn_tool_no_data);
                }
            }
        });
        ((CheckBox) findViewById(C2029R.C2031id.nrzs_game_cb_install_64)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: z1.aot.6
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                apf.m11841a(DataApp.m18939d().m18947a(), ShareVal.f16591a, ShareVal.f16573D, z);
            }
        });
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        m11940h();
    }

    /* renamed from: h */
    private void m11940h() {
        this.f17073g.m18771l();
        this.f17073g = null;
        this.f17074h = null;
    }

    /* renamed from: a */
    public void m11954a() {
        m11952b();
    }
}
