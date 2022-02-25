package com.nrzs.p067ft.p068ui.view;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.alipay.sdk.widget.C0675j;
import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.Utils;
import com.nrzs.base.router.RouterUtils;
import com.nrzs.data.p066ft.bean.AInfo;
import com.nrzs.data.p066ft.bean.AssistInfo;
import com.nrzs.p067ft.C1990R;
import com.nrzs.p067ft.p068ui.base.FtBaseView;
import p110z1.AssistInfoPresenter;
import p110z1.AssistRunTJRepository;
import p110z1.CdKeyDialog;
import p110z1.ChannelDataManager;
import p110z1.EventCollectManager;
import p110z1.EventConstants;
import p110z1.FloatDataManager;
import p110z1.FloatViewManager;
import p110z1.FtUserManager;
import p110z1.IAssistInfoView;
import p110z1.ILoadData;
import p110z1.IntentToAssistService;
import p110z1.LoadstatueViewFactory;
import p110z1.LocalLoadHelper;
import p110z1.LoginManager;
import p110z1.ShareVal;
import p110z1.apf;
import p110z1.apn;

/* renamed from: com.nrzs.ft.ui.view.AssistInfoView */
/* loaded from: classes2.dex */
public class AssistInfoView extends FtBaseView implements IAssistInfoView {

    /* renamed from: a */
    private ImageView f10739a;

    /* renamed from: b */
    private ImageView f10740b;

    /* renamed from: c */
    private LinearLayout f10741c;

    /* renamed from: d */
    private AssistInfoPresenter f10742d;

    /* renamed from: e */
    private AssistInfo f10743e;

    /* renamed from: f */
    private apn f10744f;

    /* renamed from: g */
    private TextView f10745g;

    /* renamed from: h */
    private TextView f10746h;

    /* renamed from: i */
    private TextView f10747i;

    /* renamed from: j */
    private LinearLayout f10748j;

    /* renamed from: k */
    private TextView f10749k;

    /* renamed from: l */
    private TextView f10750l;

    /* renamed from: m */
    private TextView f10751m;

    /* renamed from: n */
    private LinearLayout f10752n;

    /* renamed from: o */
    private LinearLayout f10753o;

    /* renamed from: p */
    private RelativeLayout f10754p;

    @Override // p110z1.IAssistInfoView
    public void setAssistPath(String str) {
    }

    public AssistInfoView(Context context, AssistInfo assistInfo) {
        super(context);
        this.f10743e = assistInfo;
        m18872l();
        m18871m();
    }

    @Override // com.nrzs.p067ft.p068ui.base.FtBaseView
    public int getLayoutId() {
        return C1990R.layout.nrzs_ft_layout_assist_detail;
    }

    @Override // com.nrzs.p067ft.p068ui.base.FtBaseView
    /* renamed from: a */
    public void mo18870a() {
        this.f10754p = (RelativeLayout) findViewById(C1990R.C1992id.nrzs_ft_rl_title);
        this.f10746h = (TextView) findViewById(C1990R.C1992id.nrzs_ft_tv_title);
        this.f10747i = (TextView) findViewById(C1990R.C1992id.nrzs_ft_tv_try);
        this.f10739a = (ImageView) findViewById(C1990R.C1992id.nrzs_ft_iv_close);
        this.f10740b = (ImageView) findViewById(C1990R.C1992id.nrzs_ft_iv_back);
        this.f10741c = (LinearLayout) findViewById(C1990R.C1992id.nrzs_ft_ll_script);
        this.f10745g = (TextView) findViewById(C1990R.C1992id.nrzs_ft_tv_buy);
        this.f10748j = (LinearLayout) findViewById(C1990R.C1992id.nrzs_ft_tv_renew_buy);
        this.f10752n = (LinearLayout) findViewById(C1990R.C1992id.nrzs_ft_ll_run);
        this.f10753o = (LinearLayout) findViewById(C1990R.C1992id.nrzs_ft_ll_run1);
        this.f10749k = (TextView) findViewById(C1990R.C1992id.tv_ft_reword);
        this.f10750l = (TextView) findViewById(C1990R.C1992id.tv_ft_gold);
        this.f10751m = (TextView) findViewById(C1990R.C1992id.tv_ft_surplus_gold);
    }

    /* renamed from: l */
    private void m18872l() {
        EventCollectManager.m12642a().m12640a(getContext(), "悬浮窗脚本设置页", "悬浮窗脚本设置页", EventConstants.f16444l);
        this.f10742d = new AssistInfoPresenter(this.f10743e.TopicId, this.f10743e.ScriptID, this.f10743e.OnlyID, this);
        this.f10744f = new apn(new LocalLoadHelper(getContext(), this.f10741c, null, m18873k(), m18874j(), new View.OnClickListener() { // from class: com.nrzs.ft.ui.view.AssistInfoView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AssistInfoView.this.f10744f.mo11686a();
            }
        }), new ILoadData() { // from class: com.nrzs.ft.ui.view.AssistInfoView.2
            @Override // p110z1.ILoadData
            /* renamed from: a */
            public void mo11683a(int i) {
                AssistInfoView.this.f10742d.m12256a();
            }
        });
        this.f10744f.mo11686a();
        this.f10746h.setText(FloatDataManager.m12352j().m12356f().TopicName);
        if (this.f10743e != null) {
            TextView textView = this.f10751m;
            textView.setText("剩余：" + FtUserManager.m12790g().m12792e());
            if (this.f10743e.IsVip == 1) {
                TextView textView2 = this.f10750l;
                textView2.setText(this.f10743e.Gold + "金币/天");
                this.f10750l.setVisibility(0);
                return;
            }
            this.f10750l.setVisibility(4);
        }
    }

    /* renamed from: m */
    private void m18871m() {
        this.f10740b.setOnClickListener(new View.OnClickListener() { // from class: com.nrzs.ft.ui.view.AssistInfoView.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                FloatViewManager.m12346a(view.getContext().getApplicationContext()).m12336b(view.getContext());
                FloatViewManager.m12346a(view.getContext().getApplicationContext()).m12319j();
                FloatViewManager.m12346a(view.getContext().getApplicationContext()).m12335b(AssistInfoView.class.getName());
            }
        });
        this.f10739a.setOnClickListener(new View.OnClickListener() { // from class: com.nrzs.ft.ui.view.AssistInfoView.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                FloatViewManager.m12346a(view.getContext().getApplicationContext()).m12319j();
                FloatViewManager.m12346a(view.getContext().getApplicationContext()).m12339a(AssistInfoView.class.getName());
            }
        });
        this.f10745g.setOnClickListener(new View.OnClickListener() { // from class: com.nrzs.ft.ui.view.AssistInfoView.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (ChannelDataManager.m12655a().m12650d()) {
                    new CdKeyDialog(AssistInfoView.this.getContext()).show();
                    return;
                }
                EventCollectManager.m12642a().m12640a(AssistInfoView.this.getContext(), "悬浮窗脚本设置页全平台", "悬浮窗脚本设置页全平台", EventConstants.f16456x);
                IntentToAssistService.m12810a(view.getContext(), 0L);
            }
        });
        this.f10748j.setOnClickListener(new View.OnClickListener() { // from class: com.nrzs.ft.ui.view.AssistInfoView.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (ChannelDataManager.m12655a().m12650d()) {
                    new CdKeyDialog(AssistInfoView.this.getContext()).show();
                    return;
                }
                EventCollectManager.m12642a().m12640a(AssistInfoView.this.getContext(), "悬浮窗脚本设置页充值金币", "悬浮窗脚本设置页充值金币", EventConstants.f16422Z);
                if (apf.m11836b((Context) Utils.m24103a(), ShareVal.f16591a, ShareVal.f16614x, false)) {
                    new CdKeyDialog(AssistInfoView.this.getContext()).show();
                } else {
                    IntentToAssistService.m12800b(view.getContext(), FloatDataManager.m12352j().m12354h(), 2);
                }
            }
        });
        this.f10753o.setOnClickListener(new View.OnClickListener() { // from class: com.nrzs.ft.ui.view.AssistInfoView.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AssistInfoView.this.f10742d.m12249b();
            }
        });
        this.f10749k.setOnClickListener(new View.OnClickListener() { // from class: com.nrzs.ft.ui.view.AssistInfoView.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (!LoginManager.m12620d().m12606r()) {
                    RouterUtils.toLogin(1, 0);
                } else if (AssistInfoView.this.f10743e != null) {
                    Bundle bundle = new Bundle();
                    bundle.putLong("topicId", AssistInfoView.this.f10743e.TopicId);
                    bundle.putString("script_id_key", AssistInfoView.this.f10743e.OnlyID);
                    bundle.putString(C0675j.f373k, FloatDataManager.m12352j().m12356f().TopicName);
                    RouterUtils.toReward(AssistInfoView.this.f10743e.ScriptAuthor, bundle);
                }
            }
        });
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        FloatViewManager.m12346a(getContext().getApplicationContext()).m12334b(ScreenUtils.m23292g());
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        AssistRunTJRepository.m12779c().m12781b();
        this.f10742d.m12248c();
    }

    @Override // android.view.View
    protected void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        if (i == 0) {
            this.f10742d.m12255a(i);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.f10742d.m12247d();
    }

    @Override // p110z1.IAssistInfoView
    /* renamed from: a */
    public void mo12129a(AInfo aInfo) {
        if (aInfo.EachTryTime > 0) {
            this.f10747i.setVisibility(0);
            this.f10747i.setText(getResources().getString(C1990R.string.nrzs_tv_try, Integer.valueOf(aInfo.EachTryTime / 60)));
            return;
        }
        this.f10747i.setVisibility(8);
    }

    @Override // p110z1.IAssistInfoView
    /* renamed from: e */
    public void mo12128e() {
        this.f10754p.setVisibility(0);
        this.f10752n.setVisibility(8);
        this.f10744f.mo11681l_();
    }

    @Override // p110z1.IAssistInfoView
    /* renamed from: f */
    public void mo12127f() {
        this.f10754p.setVisibility(0);
        this.f10752n.setVisibility(0);
        this.f10744f.mo11680m_();
    }

    @Override // p110z1.IAssistInfoView
    /* renamed from: g */
    public void mo12126g() {
        this.f10754p.setVisibility(0);
        this.f10752n.setVisibility(0);
        this.f10744f.mo11679n_();
    }

    @Override // p110z1.IAssistInfoView
    /* renamed from: a */
    public void mo12130a(float f) {
        if (m18882d()) {
            TextView textView = this.f10751m;
            textView.setText("剩余：" + f);
        }
    }

    @Override // p110z1.IAssistInfoView
    /* renamed from: h */
    public void mo12125h() {
        FloatViewManager.m12346a(getContext().getApplicationContext()).m12322g(getContext());
    }

    /* renamed from: j */
    public View m18874j() {
        return LoadstatueViewFactory.m11671e(getContext(), this.f10741c);
    }

    /* renamed from: k */
    public View m18873k() {
        return LoadstatueViewFactory.m11673c(getContext(), this.f10741c);
    }

    @Override // com.nrzs.p067ft.p068ui.base.FtBaseView
    /* renamed from: c */
    public void mo18876c() {
        super.mo18876c();
        AssistInfoPresenter anpVar = this.f10742d;
        if (anpVar != null) {
            anpVar.m12245f();
        }
    }
}
