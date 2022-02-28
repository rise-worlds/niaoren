package com.lbd.p054xj.p056ui.p057fw;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.Utils;
import com.lbd.p054xj.app.XJApp;
import com.nrzs.base.router.RouterUtils;
import com.nrzs.data.p066ft.bean.AInfo;
import com.nrzs.data.p066ft.bean.AssistInfo;
import com.nrzs.data.p066ft.bean.ScriptPathInfo;
import com.nrzs.ft.C1990R;
import java.io.File;
import p110z1.AssistRunTJRepository;
import p110z1.CdKeyDialog;
import p110z1.ChannelDataManager;
import p110z1.EventCollectManager;
import p110z1.EventConstants;
import p110z1.FloatAssistManager;
import p110z1.FloatDataManager;
import p110z1.FloatViewManager;
import p110z1.FwManager;
import p110z1.IAssistInfoView;
import p110z1.ILoadData;
import p110z1.IntentToAssistService;
import p110z1.LoadstatueViewFactory;
import p110z1.LocalLoadHelper;
import p110z1.LoginManager;
import p110z1.ShareVal;
import p110z1.XJAssistInfoPresenter;
import p110z1.aef;
import p110z1.aeo;
import p110z1.apf;
import p110z1.apn;

/* renamed from: com.lbd.xj.ui.fw.XJAssistInfoView */
/* loaded from: classes.dex */
public class XJAssistInfoView extends FtBaseView implements IAssistInfoView {

    /* renamed from: c */
    private ImageView f9804c;

    /* renamed from: d */
    private ImageView f9805d;

    /* renamed from: e */
    private LinearLayout f9806e;

    /* renamed from: f */
    private XJAssistInfoPresenter f9807f;

    /* renamed from: g */
    private AssistInfo f9808g;

    /* renamed from: h */
    private apn f9809h;

    /* renamed from: i */
    private TextView f9810i;

    /* renamed from: j */
    private TextView f9811j;

    /* renamed from: k */
    private TextView f9812k;

    /* renamed from: l */
    private LinearLayout f9813l;

    /* renamed from: m */
    private TextView f9814m;

    /* renamed from: n */
    private TextView f9815n;

    /* renamed from: o */
    private TextView f9816o;

    /* renamed from: p */
    private LinearLayout f9817p;

    /* renamed from: q */
    private LinearLayout f9818q;

    /* renamed from: r */
    private RelativeLayout f9819r;

    public XJAssistInfoView(Context context, AssistInfo assistInfo, WindowManager windowManager) {
        super(context, windowManager);
        this.f9808g = assistInfo;
        m19391l();
        m19390m();
    }

    @Override // com.lbd.p054xj.p056ui.p057fw.FtBaseView
    public int getLayoutId() {
        return C1990R.layout.nrzs_ft_layout_assist_detail;
    }

    @Override // com.lbd.p054xj.p056ui.p057fw.FtBaseView
    /* renamed from: a */
    public void mo19389a() {
        this.f9819r = (RelativeLayout) findViewById(C1990R.C1992id.nrzs_ft_rl_title);
        this.f9811j = (TextView) findViewById(C1990R.C1992id.nrzs_ft_tv_title);
        this.f9812k = (TextView) findViewById(C1990R.C1992id.nrzs_ft_tv_try);
        this.f9804c = (ImageView) findViewById(C1990R.C1992id.nrzs_ft_iv_close);
        this.f9805d = (ImageView) findViewById(C1990R.C1992id.nrzs_ft_iv_back);
        this.f9806e = (LinearLayout) findViewById(C1990R.C1992id.nrzs_ft_ll_script);
        this.f9810i = (TextView) findViewById(C1990R.C1992id.nrzs_ft_tv_buy);
        this.f9813l = (LinearLayout) findViewById(C1990R.C1992id.nrzs_ft_tv_renew_buy);
        this.f9817p = (LinearLayout) findViewById(C1990R.C1992id.nrzs_ft_ll_run);
        this.f9818q = (LinearLayout) findViewById(C1990R.C1992id.nrzs_ft_ll_run1);
        this.f9814m = (TextView) findViewById(C1990R.C1992id.tv_ft_reword);
        this.f9815n = (TextView) findViewById(C1990R.C1992id.tv_ft_gold);
        this.f9816o = (TextView) findViewById(C1990R.C1992id.tv_ft_surplus_gold);
    }

    @Override // com.lbd.p054xj.p056ui.p057fw.FtBaseView
    /* renamed from: b */
    public void mo19387b() {
        WindowManager.LayoutParams params = getParams();
        params.x = 0;
        params.y = 0;
        params.width = aeo.m13910e();
        params.height = aeo.m13912d();
        int systemUiVisibility = getSystemUiVisibility();
        if (Build.VERSION.SDK_INT >= 19) {
            setSystemUiVisibility(systemUiVisibility | 2 | 512 | 4 | 1024 | 2048);
        } else {
            setSystemUiVisibility(systemUiVisibility | 2 | 512 | 4 | 1024);
        }
        try {
            this.f9803b.addView(this, params);
            m19392k();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: k */
    private void m19392k() {
        XJAssistInfoPresenter advVar = this.f9807f;
        if (advVar != null) {
            advVar.m14245d();
        }
    }

    @Override // com.lbd.p054xj.p056ui.p057fw.FtBaseView
    /* renamed from: c */
    public void mo19385c() {
        if (getParent() != null) {
            this.f9803b.removeView(this);
            FloatViewManager.m12346a(getContext().getApplicationContext()).m12319j();
        }
    }

    @Override // com.lbd.p054xj.p056ui.p057fw.FtBaseView
    /* renamed from: d */
    public void mo19384d() {
        this.f9805d.callOnClick();
    }

    /* renamed from: l */
    private void m19391l() {
        LogUtils.m23734c("1  -   initData");
        EventCollectManager.m12642a().m12640a(getContext(), "悬浮窗脚本设置页", "悬浮窗脚本设置页", EventConstants.f16444l);
        this.f9807f = new XJAssistInfoPresenter(this.f9808g, this);
        this.f9809h = new apn(new LocalLoadHelper(getContext(), this.f9806e, null, m19393j(), m19394i(), new View.OnClickListener() { // from class: com.lbd.xj.ui.fw.XJAssistInfoView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                XJAssistInfoView.this.f9809h.mo11686a();
            }
        }), new ILoadData() { // from class: com.lbd.xj.ui.fw.XJAssistInfoView.2
            @Override // p110z1.ILoadData
            /* renamed from: a */
            public void mo11683a(int i) {
                XJAssistInfoView.this.f9807f.m14257a();
            }
        });
        this.f9809h.mo11686a();
        this.f9811j.setText(FloatDataManager.m12352j().m12356f().TopicName);
        if (this.f9808g != null) {
            TextView textView = this.f9816o;
            textView.setText("剩余：" + LoginManager.m12620d().m12607q());
            if (this.f9808g.IsVip == 1) {
                TextView textView2 = this.f9815n;
                textView2.setText(this.f9808g.Gold + "金币/天");
                this.f9815n.setVisibility(0);
                return;
            }
            this.f9815n.setVisibility(4);
        }
    }

    /* renamed from: m */
    private void m19390m() {
        this.f9805d.setOnClickListener(new View.OnClickListener() { // from class: com.lbd.xj.ui.fw.XJAssistInfoView.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                FloatViewManager.m12346a(view.getContext().getApplicationContext()).m12319j();
                FwManager.getInstance().removeXJAssisInfoView();
                FwManager.getInstance().initXJAssistListView();
            }
        });
        this.f9804c.setOnClickListener(new View.OnClickListener() { // from class: com.lbd.xj.ui.fw.XJAssistInfoView.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                FloatViewManager.m12346a(view.getContext().getApplicationContext()).m12319j();
                FwManager.getInstance().removeXJAssisInfoView();
                FwManager.getInstance().initXJFloatView(true);
            }
        });
        this.f9810i.setOnClickListener(new View.OnClickListener() { // from class: com.lbd.xj.ui.fw.XJAssistInfoView.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (ChannelDataManager.m12655a().m12650d()) {
                    new CdKeyDialog(XJAssistInfoView.this.getContext()).show();
                    return;
                }
                EventCollectManager.m12642a().m12640a(XJAssistInfoView.this.getContext(), "悬浮窗脚本设置页全平台", "悬浮窗脚本设置页全平台", EventConstants.f16456x);
                IntentToAssistService.m12810a(view.getContext(), 0L);
            }
        });
        this.f9813l.setOnClickListener(new View.OnClickListener() { // from class: com.lbd.xj.ui.fw.XJAssistInfoView.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                FloatViewManager.m12346a(view.getContext().getApplicationContext()).m12319j();
                FwManager.getInstance().removeXJAssisInfoView();
                EventCollectManager.m12642a().m12640a(XJAssistInfoView.this.getContext(), "悬浮窗脚本设置页充值金币", "悬浮窗脚本设置页充值金币", EventConstants.f16422Z);
                if (ChannelDataManager.m12655a().m12650d()) {
                    new CdKeyDialog(XJAssistInfoView.this.getContext()).show();
                } else if (apf.m11836b((Context) Utils.m24103a(), ShareVal.f16591a, ShareVal.f16614x, false)) {
                    new CdKeyDialog(XJAssistInfoView.this.getContext()).show();
                } else {
                    IntentToAssistService.m12800b(view.getContext(), FloatDataManager.m12352j().m12354h(), 2);
                }
            }
        });
        this.f9818q.setOnClickListener(new View.OnClickListener() { // from class: com.lbd.xj.ui.fw.XJAssistInfoView.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                EventCollectManager.m12642a().m12640a(XJAssistInfoView.this.getContext(), "后台挂机启动脚本", "后台挂机启动脚本", EventConstants.f16407K);
                XJAssistInfoView.this.f9807f.m14243e();
            }
        });
        this.f9814m.setOnClickListener(new View.OnClickListener() { // from class: com.lbd.xj.ui.fw.XJAssistInfoView.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                FloatViewManager.m12346a(view.getContext().getApplicationContext()).m12319j();
                FwManager.getInstance().removeXJAssisInfoView();
                if (!LoginManager.m12620d().m12606r()) {
                    RouterUtils.toLogin(1, 0);
                } else if (XJAssistInfoView.this.f9808g != null) {
                    RouterUtils.toReward(XJAssistInfoView.this.f9808g.ScriptAuthor);
                }
            }
        });
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        AssistRunTJRepository.m12779c().m12781b();
        this.f9807f.m14249b();
    }

    @Override // android.view.View
    protected void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        if (i == 0) {
            this.f9807f.m14256a(i);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.f9807f.m14247c();
    }

    @Override // p110z1.IAssistInfoView
    /* renamed from: a */
    public void mo12129a(AInfo aInfo) {
        if (aInfo.EachTryTime > 0) {
            this.f9812k.setVisibility(0);
            this.f9812k.setText(getResources().getString(C1990R.string.nrzs_tv_try, Integer.valueOf(aInfo.EachTryTime / 60)));
            return;
        }
        this.f9812k.setVisibility(8);
    }

    @Override // p110z1.IAssistInfoView
    /* renamed from: e */
    public void mo12128e() {
        this.f9819r.setVisibility(0);
        this.f9817p.setVisibility(8);
        this.f9809h.mo11681l_();
    }

    @Override // p110z1.IAssistInfoView
    /* renamed from: f */
    public void mo12127f() {
        this.f9819r.setVisibility(0);
        this.f9817p.setVisibility(0);
        this.f9809h.mo11680m_();
    }

    @Override // p110z1.IAssistInfoView
    /* renamed from: g */
    public void mo12126g() {
        this.f9819r.setVisibility(0);
        this.f9817p.setVisibility(0);
        this.f9809h.mo11679n_();
    }

    @Override // p110z1.IAssistInfoView
    public void setAssistPath(String str) {
        File file = new File(XJApp.getInstance().getApplicationInfo().dataDir, "osimg");
        StringBuilder sb = new StringBuilder();
        sb.append(file);
        sb.append("/r/ot01/storage/sdcard/xjscript/");
        str.substring(str.lastIndexOf("/"));
        LogUtils.m23734c("newEngin", "path:" + str + ",filePath.toString():" + sb.toString());
        ScriptPathInfo g = FloatAssistManager.m12397i().m12399g();
        String str2 = g.lc_path;
        aef.m14123e(str2, sb.toString() + "1.lc");
        String str3 = g.atc_Path;
        aef.m14123e(str3, sb.toString() + "1.atc");
        String str4 = g.uiCfgPath;
        aef.m14123e(str4, sb.toString() + "1.uicfg");
    }

    @Override // p110z1.IAssistInfoView
    /* renamed from: a */
    public void mo12130a(float f) {
        if (getParent() != null) {
            TextView textView = this.f9816o;
            textView.setText("剩余：" + f);
        }
    }

    @Override // p110z1.IAssistInfoView
    /* renamed from: h */
    public void mo12125h() {
        FwManager.getInstance().showFtNeedGoldDialog(getContext());
    }

    /* renamed from: i */
    public View m19394i() {
        return LoadstatueViewFactory.m11671e(getContext(), this.f9806e);
    }

    /* renamed from: j */
    public View m19393j() {
        return LoadstatueViewFactory.m11673c(getContext(), this.f9806e);
    }
}
