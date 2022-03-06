package com.angel.nrzs.ui.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.angel.nrzs.C0692R;
import com.angel.nrzs.ui.activity.NRZSWebviewActivity;
import com.cyjh.ddy.media.p035a.ResultTypeConstant;
import com.nrzs.base.router.RouterUtils;
import com.nrzs.base.router.provider.ProviderFactory;
import com.nrzs.data.other.bean.AdResultInfoItem;
import com.nrzs.game.ui.activity.GameAllActivity;
import com.redwas.redwars.RedbgaIndextActivity;
import com.youth.banner.Banner;
import java.util.ArrayList;
import java.util.List;
import p110z1.BannerManager;
import p110z1.BannersOnClick;
import p110z1.ChannelDataManager;
import p110z1.DoneCallback;
import p110z1.EventCollectManager;
import p110z1.EventConstants;
import p110z1.GlideImageLoader;
import p110z1.GlideImageUtils;
import p110z1.HttpVal;
import p110z1.LoginManager;
import p110z1.OnBannerListener;
import p110z1.PreSetListManager;

/* renamed from: com.angel.nrzs.ui.view.HomeHeaderBannerView */
/* loaded from: classes.dex */
public class HomeHeaderBannerView extends LinearLayout {

    /* renamed from: a */
    private Banner f5615a;

    /* renamed from: b */
    private RelativeLayout f5616b;

    /* renamed from: c */
    private List<AdResultInfoItem> f5617c = new ArrayList();

    /* renamed from: d */
    private List<String> f5618d = new ArrayList();

    /* renamed from: e */
    private TextView f5619e;

    /* renamed from: f */
    private ImageView f5620f;

    /* renamed from: g */
    private TextView f5621g;

    /* renamed from: h */
    private LinearLayout f5622h;

    /* renamed from: i */
    private TextView f5623i;

    /* renamed from: j */
    private TextView f5624j;

    /* renamed from: k */
    private LinearLayout f5625k;

    /* renamed from: l */
    private TextView f5626l;

    /* renamed from: m */
    private TextView f5627m;

    /* renamed from: n */
    private ImageView f5628n;

    public HomeHeaderBannerView(Context context) {
        super(context);
        m24874e();
        m24875d();
        m24876c();
    }

    /* renamed from: c */
    private void m24876c() {
        if (PreSetListManager.m12116a().m12103m().equals("1")) {
            this.f5625k.setVisibility(0);
            this.f5626l.setText(PreSetListManager.m12116a().m12102n());
            this.f5627m.setText(PreSetListManager.m12116a().m12098r());
            GlideImageUtils.m11880a(this.f5628n, getContext(), (int) C0692R.C0693drawable.f2282n7, PreSetListManager.m12116a().m12097s());
            return;
        }
        this.f5625k.setVisibility(8);
    }

    /* renamed from: d */
    private void m24875d() {
        this.f5624j.setOnClickListener(new View.OnClickListener() { // from class: com.angel.nrzs.ui.view.HomeHeaderBannerView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                RedbgaIndextActivity.m18176a(view.getContext());
            }
        });
        this.f5616b.setOnClickListener(new View.OnClickListener() { // from class: com.angel.nrzs.ui.view.HomeHeaderBannerView.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                GameAllActivity.m18710a(view.getContext());
            }
        });
        this.f5620f.setOnClickListener(new View.OnClickListener() { // from class: com.angel.nrzs.ui.view.HomeHeaderBannerView.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                HomeHeaderPopupView.m24862a(HomeHeaderBannerView.this.getContext(), HomeHeaderBannerView.this.f5622h);
            }
        });
        this.f5621g.setOnClickListener(new View.OnClickListener() { // from class: com.angel.nrzs.ui.view.HomeHeaderBannerView.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                EventCollectManager.m12642a().m12640a(HomeHeaderBannerView.this.getContext(), "首页充值金币", "首页充值金币", EventConstants.f16417U);
                if (ChannelDataManager.m12655a().m12650d()) {
                    ProviderFactory.createCarkey().showdialog(HomeHeaderBannerView.this.getContext());
                } else if (LoginManager.m12620d().m12606r()) {
                    AdResultInfoItem adResultInfoItem = new AdResultInfoItem();
                    adResultInfoItem.Title = "支持鸟人送金币";
                    adResultInfoItem.ExecArgs = HttpVal.f16516c;
                    NRZSWebviewActivity.m25006a(HomeHeaderBannerView.this.getContext(), 0, 1, 2, adResultInfoItem);
                } else {
                    RouterUtils.toLogin(1, 0);
                }
            }
        });
    }

    public void setVisible(int i) {
        this.f5615a.setVisibility(i);
    }

    /* renamed from: a */
    public void m24883a() {
        BannerManager.m12679a().m12672a(new DoneCallback<List<AdResultInfoItem>>() { // from class: com.angel.nrzs.ui.view.HomeHeaderBannerView.5
            /* renamed from: a */
            public void onDone(List<AdResultInfoItem> list) {
                if (list != null) {
                    HomeHeaderBannerView.this.f5617c = list;
                    HomeHeaderBannerView homeHeaderBannerView = HomeHeaderBannerView.this;
                    homeHeaderBannerView.m24880a(homeHeaderBannerView.f5617c);
                }
            }
        });
        m24879b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m24880a(final List<AdResultInfoItem> list) {
        this.f5618d.clear();
        for (AdResultInfoItem adResultInfoItem : list) {
            this.f5618d.add(adResultInfoItem.ImgUrl);
        }
        this.f5615a.m14949b(this.f5618d).m14956a(new GlideImageLoader()).m14965a(5000).m14966a();
        this.f5615a.m14957a(new OnBannerListener() { // from class: com.angel.nrzs.ui.view.HomeHeaderBannerView.6
            @Override // p110z1.OnBannerListener
            /* renamed from: a */
            public void mo11419a(int i) {
                List list2 = list;
                if (list2 != null && list2.size() > i) {
                    new BannersOnClick().m3199a(HomeHeaderBannerView.this.getContext(), (AdResultInfoItem) list.get(i), 0);
                }
            }
        });
    }

    /* renamed from: e */
    private void m24874e() {
        LayoutInflater.from(getContext()).inflate(C0692R.layout.nrzs_home_header_layout, this);
        this.f5615a = (Banner) findViewById(C0692R.C0694id.nrzs_app_home_banner);
        this.f5616b = (RelativeLayout) findViewById(C0692R.C0694id.nrzs_app_rl_likes);
        this.f5619e = (TextView) findViewById(C0692R.C0694id.nrzs_app_tv_gold);
        this.f5620f = (ImageView) findViewById(C0692R.C0694id.nrzs_app_iv_help);
        this.f5621g = (TextView) findViewById(C0692R.C0694id.nrzs_app_tv_recharge_gold);
        this.f5622h = (LinearLayout) findViewById(C0692R.C0694id.nrzs_app_home_ll_gold);
        this.f5623i = (TextView) findViewById(C0692R.C0694id.nrzs_app_tv_the_author_script);
        this.f5624j = (TextView) findViewById(C0692R.C0694id.entrance_redbag);
        this.f5625k = (LinearLayout) findViewById(C0692R.C0694id.reb_bag_layout);
        this.f5626l = (TextView) findViewById(C0692R.C0694id.red_title);
        this.f5627m = (TextView) findViewById(C0692R.C0694id.red_count);
        this.f5628n = (ImageView) findViewById(C0692R.C0694id.red_logos);
    }

    /* renamed from: b */
    public void m24879b() {
        if (LoginManager.m12620d().m12606r()) {
            TextView textView = this.f5619e;
            textView.setText("" + LoginManager.m12620d().m12607q());
            return;
        }
        this.f5619e.setText(ResultTypeConstant.f7213z);
    }

    public void setAuthorScript(String str) {
        TextView textView = this.f5623i;
        textView.setText(str + "的脚本");
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }
}
