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
import com.blankj.utilcode.util.LogUtils;
import com.nrzs.data.DataApp;
import com.nrzs.game.C2029R;
import com.nrzs.game.model.TentcentDownModel;

/* renamed from: z1.aou */
/* loaded from: classes3.dex */
public class Wx32TipsDialog extends Dialog {

    /* renamed from: a */
    private RelativeLayout f17082a;

    /* renamed from: b */
    private TextView f17083b;

    /* renamed from: c */
    private ImageView f17084c;

    /* renamed from: d */
    private TextView f17085d;

    /* renamed from: e */
    private TentcentDownModel f17086e;

    /* renamed from: f */
    private AbstractC3822a f17087f;

    /* renamed from: g */
    private ProgressBar f17088g;

    /* renamed from: h */
    private TextView f17089h;

    /* renamed from: i */
    private TextView f17090i;

    /* compiled from: Wx32TipsDialog.java */
    /* renamed from: z1.aou$a */
    /* loaded from: classes3.dex */
    public interface AbstractC3822a {
        /* renamed from: a */
        void mo11917a();

        /* renamed from: b */
        void mo11916b();
    }

    /* renamed from: c */
    private void m11933c() {
    }

    @Override // android.app.Dialog
    protected void onStop() {
    }

    public Wx32TipsDialog(Activity activity, AbstractC3822a aVar) {
        super(activity, C2029R.style.NRZS_Game_my_dialog);
        this.f17087f = aVar;
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C2029R.layout.nrzs_game_va_32_install);
        m11925g();
        m11927f();
        m11933c();
    }

    /* renamed from: b */
    private void m11935b() {
        this.f17086e = TentcentDownModel.m18803a();
        this.f17086e.m18797a(new TentcentDownModel.AbstractC2073b() { // from class: z1.aou.1
            @Override // com.nrzs.game.model.TentcentDownModel.AbstractC2073b
            /* renamed from: a */
            public void mo11919a(String str) {
                LogUtils.m23734c("check_32_64", "onDownloadComplete");
            }

            @Override // com.nrzs.game.model.TentcentDownModel.AbstractC2073b
            /* renamed from: a */
            public void mo11922a() {
                LogUtils.m23734c("check_32_64", "onDownloadFail");
            }

            @Override // com.nrzs.game.model.TentcentDownModel.AbstractC2073b
            /* renamed from: a */
            public void mo11921a(int i) {
                LogUtils.m23734c("check_32_64", "updateFileSize");
            }

            @Override // com.nrzs.game.model.TentcentDownModel.AbstractC2073b
            /* renamed from: a */
            public void mo11920a(long j, long j2, String str) {
                int i = (int) ((j * 100) / j2);
                Wx32TipsDialog.this.f17088g.setProgress(i);
                TextView textView = Wx32TipsDialog.this.f17083b;
                textView.setText("下载中：" + i + "%");
            }

            @Override // com.nrzs.game.model.TentcentDownModel.AbstractC2073b
            /* renamed from: b */
            public void mo11918b(int i) {
                Wx32TipsDialog.this.m11931d();
            }
        });
        m11931d();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: d */
    public void m11931d() {
        int i = this.f17086e.m18774i();
        if (i == -1) {
            m11929e();
            this.f17085d.setBackgroundResource(C2029R.C2030drawable.bird_btn_tool_no_data);
        } else if (i != 6) {
            switch (i) {
                case 1:
                    this.f17085d.setText("下载成功");
                    this.f17085d.setBackgroundResource(C2029R.C2030drawable.bird_btn_tool_no_data);
                    this.f17085d.setVisibility(0);
                    this.f17082a.setVisibility(8);
                    return;
                case 2:
                    this.f17085d.setText("重试");
                    this.f17085d.setBackgroundResource(C2029R.C2030drawable.bird_btn_re_install);
                    this.f17085d.setVisibility(0);
                    this.f17082a.setVisibility(8);
                    return;
                case 3:
                    this.f17085d.setText("安装中..请稍等");
                    this.f17085d.setBackgroundResource(C2029R.C2030drawable.bird_btn_tool_no_data);
                    return;
                case 4:
                    this.f17085d.setText("安装成功");
                    this.f17085d.setBackgroundResource(C2029R.C2030drawable.bird_btn_tool_no_data);
                    LogUtils.m23734c("check_32_64", "安装成功");
                    AbstractC3822a aVar = this.f17087f;
                    if (aVar != null) {
                        aVar.mo11917a();
                    }
                    dismiss();
                    return;
                default:
                    return;
            }
        } else {
            this.f17085d.setVisibility(8);
            this.f17082a.setVisibility(0);
        }
    }

    /* renamed from: e */
    private void m11929e() {
        if (NRZSLocalConfig.m12513c()) {
            this.f17085d.setText("安装微信32位");
            this.f17089h.setText("启动64位应用异常");
            this.f17090i.setText("系统检测到当前安装的是64位微信，需【安装32位微信】后才可正常使用微信登录游戏！");
            return;
        }
        this.f17085d.setText("安装微信64位");
        this.f17089h.setText("启动32位应用异常");
        this.f17090i.setText("系统检测到当前安装的是32位微信，需【安装64位微信】后才可正常使用微信登录游戏！");
    }

    /* renamed from: f */
    private void m11927f() {
        this.f17082a.setOnClickListener(new View.OnClickListener() { // from class: z1.aou.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
            }
        });
        this.f17084c.setOnClickListener(new View.OnClickListener() { // from class: z1.aou.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (Wx32TipsDialog.this.f17087f != null) {
                    Wx32TipsDialog.this.f17087f.mo11916b();
                }
                Wx32TipsDialog.this.dismiss();
            }
        });
        this.f17083b.setOnClickListener(new View.OnClickListener() { // from class: z1.aou.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Wx32TipsDialog.this.f17082a.setVisibility(0);
            }
        });
    }

    /* renamed from: g */
    private void m11925g() {
        this.f17084c = (ImageView) findViewById(C2029R.C2031id.nrzs_game_iv_install_64_close);
        this.f17082a = (RelativeLayout) findViewById(C2029R.C2031id.rl_progressbar);
        this.f17083b = (TextView) findViewById(C2029R.C2031id.tv_progressbar);
        this.f17088g = (ProgressBar) findViewById(C2029R.C2031id.progress);
        this.f17085d = (TextView) findViewById(C2029R.C2031id.nrzs_game_tv_install_64);
        this.f17089h = (TextView) findViewById(C2029R.C2031id.tv_warn_title);
        this.f17090i = (TextView) findViewById(C2029R.C2031id.tv_warn_content);
        m11929e();
        this.f17085d.setOnClickListener(new View.OnClickListener() { // from class: z1.aou.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if ("重试".equals(Wx32TipsDialog.this.f17085d.getText().toString()) || Wx32TipsDialog.this.f17085d.getText().toString().contains("安装微信")) {
                    Wx32TipsDialog.this.f17086e.m18770m();
                    Wx32TipsDialog.this.f17085d.setText("连接中");
                    Wx32TipsDialog.this.f17085d.setBackgroundResource(C2029R.C2030drawable.bird_btn_tool_no_data);
                }
            }
        });
        ((CheckBox) findViewById(C2029R.C2031id.nrzs_game_cb_install_64)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: z1.aou.6
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                apf.m11841a(DataApp.m18939d().m18947a(), ShareVal.f16591a, ShareVal.f16572C, z);
            }
        });
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        m11923h();
    }

    /* renamed from: h */
    private void m11923h() {
        this.f17086e.m18771l();
        this.f17086e = null;
        this.f17087f = null;
    }

    /* renamed from: a */
    public void m11937a() {
        m11935b();
    }
}
