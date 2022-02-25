package com.angel.nrzs.p017ui.fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p006v7.widget.LinearLayoutManager;
import android.support.p006v7.widget.RecyclerView;
import android.support.p006v7.widget.SimpleItemAnimator;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.angel.nrzs.C0692R;
import com.angel.nrzs.adapter.ChannelHomeAdapter;
import com.angel.nrzs.model.ChannelFragmentModel;
import com.angel.nrzs.p017ui.base.AppBaseFragment;
import com.angel.nrzs.p017ui.view.HomeHeaderBannerView;
import com.ctetin.expandabletextviewlibrary.ExpandableTextView;
import com.nrzs.base.router.RouterUtils;
import com.nrzs.data.DataApp;
import com.nrzs.data.game.bean.AuthorInfo;
import com.nrzs.data.game.bean.AuthorScriptBean;
import com.nrzs.data.other.bean.AdResultInfoItem;
import com.nrzs.game.p069ui.activity.GameAllActivity;
import com.nrzs.game.p069ui.activity.GameSearchActivity;
import com.nrzs.moudleui.p070ui.state.LoadingView;
import java.util.ArrayList;
import java.util.List;
import p110z1.ADStatueEvent;
import p110z1.AuthorInfromDialog;
import p110z1.BannerManager;
import p110z1.BannersOnClick;
import p110z1.DoneCallback;
import p110z1.EventBus;
import p110z1.GlideImageUtils;
import p110z1.LoginEvent;
import p110z1.LoginManager;
import p110z1.StatusType;
import p110z1.Subscribe;
import p110z1.ThreadMode;

/* renamed from: com.angel.nrzs.ui.fragment.ChannelFragment */
/* loaded from: classes.dex */
public class ChannelFragment extends AppBaseFragment implements View.OnClickListener {

    /* renamed from: A */
    private TextView f5519A;

    /* renamed from: c */
    private ExpandableTextView f5522c;

    /* renamed from: g */
    private RecyclerView f5523g;

    /* renamed from: h */
    private HomeHeaderBannerView f5524h;

    /* renamed from: i */
    private ChannelHomeAdapter f5525i;

    /* renamed from: j */
    private LoadingView f5526j;

    /* renamed from: k */
    private ChannelFragmentModel f5527k;

    /* renamed from: m */
    private AdResultInfoItem f5529m;

    /* renamed from: n */
    private List<AuthorScriptBean> f5530n;

    /* renamed from: p */
    private long f5532p;

    /* renamed from: q */
    private TextView f5533q;

    /* renamed from: r */
    private TextView f5534r;

    /* renamed from: s */
    private TextView f5535s;

    /* renamed from: t */
    private TextView f5536t;

    /* renamed from: u */
    private LinearLayout f5537u;

    /* renamed from: v */
    private RelativeLayout f5538v;

    /* renamed from: w */
    private ImageView f5539w;

    /* renamed from: x */
    private ImageView f5540x;

    /* renamed from: y */
    private ImageView f5541y;

    /* renamed from: z */
    private ImageView f5542z;

    /* renamed from: l */
    private List<AdResultInfoItem> f5528l = new ArrayList();

    /* renamed from: o */
    private String f5531o = "";

    /* renamed from: B */
    private final Observer<List<AuthorInfo>> f5520B = new Observer<List<AuthorInfo>>() { // from class: com.angel.nrzs.ui.fragment.ChannelFragment.1
        /* renamed from: a */
        public void onChanged(@Nullable List<AuthorInfo> list) {
            if (list != null) {
                for (AuthorInfo authorInfo : list) {
                    ChannelFragment.this.f5525i.m25201a(authorInfo.getAuthorId());
                    ChannelFragment.this.f5527k.m25050b(authorInfo.getAuthorId());
                    ChannelFragment.this.f5531o = authorInfo.getAuthorNotice();
                    ChannelFragment.this.f5532p = authorInfo.getAuthorId();
                    if (TextUtils.isEmpty(authorInfo.getAuthorNotice())) {
                        ChannelFragment.this.f5522c.setContent(ChannelFragment.this.getResources().getString(C0692R.string.f3987lw));
                    } else {
                        ChannelFragment.this.f5522c.setContent(authorInfo.getAuthorNotice());
                    }
                    ChannelFragment.this.f5534r.setText(authorInfo.getNickName());
                    ChannelFragment.this.f5524h.setAuthorScript(authorInfo.getNickName());
                    TextView textView = ChannelFragment.this.f5535s;
                    textView.setText("辅助：" + authorInfo.getScriptNum());
                    TextView textView2 = ChannelFragment.this.f5536t;
                    textView2.setText("打赏：" + authorInfo.getRewardSGBTotalNum());
                    if (!TextUtils.isEmpty(authorInfo.getAuthorHeadImg())) {
                        GlideImageUtils.m11880a(ChannelFragment.this.f5541y, ChannelFragment.this.getContext(), (int) C0692R.C0693drawable.f1994b5, authorInfo.getAuthorHeadImg());
                    }
                }
            }
        }
    };

    /* renamed from: C */
    private final Observer<List<AuthorScriptBean>> f5521C = new Observer<List<AuthorScriptBean>>() { // from class: com.angel.nrzs.ui.fragment.ChannelFragment.2
        /* renamed from: a */
        public void onChanged(@Nullable List<AuthorScriptBean> list) {
            if (list == null || list.size() <= 0) {
                ChannelFragment.this.f5525i.m18494a(ChannelFragment.this.f5526j);
                ChannelFragment.this.f5537u.setVisibility(0);
                return;
            }
            ChannelFragment.this.f5530n.clear();
            ChannelFragment.this.f5530n.addAll(list);
            ChannelFragment.this.f5525i.m18494a(ChannelFragment.this.f5526j);
            ChannelFragment.this.f5537u.setVisibility(8);
            ChannelFragment.this.f5525i.notifyDataSetChanged();
        }
    };

    @Override // com.nrzs.libcommon.BaseFragment
    /* renamed from: b */
    protected int mo18216b() {
        return C0692R.layout.nrzs_fragment_channel;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.nrzs.libcommon.BaseFragment
    /* renamed from: a */
    public void mo18221a(View view) {
        super.mo18221a(view);
        this.f5522c = (ExpandableTextView) view.findViewById(C0692R.C0694id.nrzs_app_tv_redmore);
        this.f5523g = (RecyclerView) view.findViewById(C0692R.C0694id.nrzs_app_channel_recyclerView);
        this.f5533q = (TextView) view.findViewById(C0692R.C0694id.nrzs_app_tv_rewaerd);
        this.f5534r = (TextView) view.findViewById(C0692R.C0694id.f2481b9);
        this.f5541y = (ImageView) view.findViewById(C0692R.C0694id.f2482b_);
        this.f5535s = (TextView) view.findViewById(C0692R.C0694id.f2480b8);
        this.f5536t = (TextView) view.findViewById(C0692R.C0694id.f2483ba);
        this.f5537u = (LinearLayout) view.findViewById(C0692R.C0694id.nrzs_app_ll_nodata);
        this.f5538v = (RelativeLayout) view.findViewById(C0692R.C0694id.nezs_channel_bottom_banner);
        this.f5539w = (ImageView) view.findViewById(C0692R.C0694id.nezs_channel_bottom_banner_image);
        this.f5540x = (ImageView) view.findViewById(C0692R.C0694id.nezs_channel_bottom_banner_close);
        this.f5542z = (ImageView) view.findViewById(C0692R.C0694id.nrzs_app_iv_search);
        this.f5519A = (TextView) view.findViewById(C0692R.C0694id.nrzs_app_tv_allgame);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.nrzs.libcommon.BaseFragment
    /* renamed from: d */
    public void mo18212d() {
        super.mo18212d();
        this.f5530n = new ArrayList();
        if (this.f5527k == null) {
            this.f5527k = (ChannelFragmentModel) ViewModelProviders.m25706of(this).get(ChannelFragmentModel.class);
        }
        this.f5527k.m25053a().observe(this, this.f5520B);
        this.f5527k.m25051b().observe(this, this.f5521C);
        if (LoginManager.m12620d().m12623b() == 0 || DataApp.m18939d().f10604g != 0) {
            this.f5527k.m25052a(DataApp.m18939d().f10605h);
        } else {
            this.f5527k.m25052a(LoginManager.m12620d().m12623b());
        }
        this.f5523g.setLayoutManager(new LinearLayoutManager(getContext()));
        ((SimpleItemAnimator) this.f5523g.getItemAnimator()).setSupportsChangeAnimations(false);
        this.f5524h = new HomeHeaderBannerView(getActivity());
        this.f5525i = new ChannelHomeAdapter(this.f5530n);
        this.f5525i.m18491a((View) this.f5524h, 0, true);
        this.f5526j = new LoadingView(getActivity());
        List<AdResultInfoItem> list = this.f5528l;
        if (list == null || list.size() <= 0) {
            this.f5524h.setVisible(8);
            this.f5525i.m18491a((View) this.f5526j, 1, true);
        } else {
            this.f5524h.setVisible(0);
            this.f5524h.m24883a();
            this.f5525i.m18491a((View) this.f5526j, 1, true);
        }
        BannerManager.m12679a().m12672a(new DoneCallback() { // from class: com.angel.nrzs.ui.fragment.-$$Lambda$ChannelFragment$pqZV6Y7u8ntI3yB_oN11Dk6OEbw
            @Override // p110z1.DoneCallback
            public final void onDone(Object obj) {
                ChannelFragment.this.m24937b((List) obj);
            }
        });
        BannerManager.m12679a().m12660f(new DoneCallback() { // from class: com.angel.nrzs.ui.fragment.-$$Lambda$ChannelFragment$DidK96igc9MrFfqKWVU9mf9oyzM
            @Override // p110z1.DoneCallback
            public final void onDone(Object obj) {
                ChannelFragment.this.m24938b((AdResultInfoItem) obj);
            }
        });
        this.f5523g.setAdapter(this.f5525i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public /* synthetic */ void m24937b(List list) {
        if (list != null) {
            this.f5528l = list;
            if (this.f5528l.size() > 0) {
                this.f5524h.setVisible(0);
                this.f5524h.m24883a();
                return;
            }
            this.f5524h.setVisible(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public /* synthetic */ void m24938b(AdResultInfoItem adResultInfoItem) {
        if (adResultInfoItem != null) {
            this.f5529m = adResultInfoItem;
            this.f5538v.setVisibility(0);
            if (!TextUtils.isEmpty(this.f5529m.ImgUrl)) {
                GlideImageUtils.m11880a(this.f5539w, getContext(), (int) C0692R.C0693drawable.f1994b5, this.f5529m.ImgUrl);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.nrzs.libcommon.BaseFragment
    /* renamed from: e */
    public void mo18210e() {
        super.mo18210e();
        this.f5533q.setOnClickListener(this);
        this.f5540x.setOnClickListener(this);
        this.f5539w.setOnClickListener(this);
        this.f5542z.setOnClickListener(this);
        this.f5519A.setOnClickListener(this);
        this.f5522c.m22025a(new ExpandableTextView.AbstractC1107b() { // from class: com.angel.nrzs.ui.fragment.ChannelFragment.3
            @Override // com.ctetin.expandabletextviewlibrary.ExpandableTextView.AbstractC1107b
            /* renamed from: a */
            public void mo21985a(StatusType fqVar) {
                if (fqVar == StatusType.STATUS_EXPAND) {
                    AuthorInfromDialog.m3044a(ChannelFragment.this.getContext(), ChannelFragment.this.f5531o);
                }
            }
        }, false);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == this.f5533q.getId()) {
            if (LoginManager.m12620d().m12606r()) {
                RouterUtils.toReward(this.f5532p);
            } else {
                RouterUtils.toLogin(1, 0);
            }
        } else if (id == this.f5540x.getId()) {
            this.f5538v.setVisibility(8);
        } else if (id == this.f5539w.getId()) {
            new BannersOnClick().m3199a(getContext(), this.f5529m, 1);
        } else if (id == this.f5542z.getId()) {
            GameSearchActivity.m18673a(getContext());
        } else if (id == this.f5519A.getId()) {
            GameAllActivity.m18710a(getContext());
        }
    }

    @Override // com.nrzs.libcommon.BaseFragment, android.support.p003v4.app.Fragment
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        EventBus.m3448a().m3446a(this);
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    @Override // com.nrzs.libcommon.BaseFragment, android.support.p003v4.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        EventBus.m3448a().m3430c(this);
    }

    @Subscribe(m3389a = ThreadMode.MAIN)
    /* renamed from: a */
    public void m24940a(LoginEvent.C3560a aVar) {
        HomeHeaderBannerView homeHeaderBannerView;
        if (aVar.f16136f == 1) {
            HomeHeaderBannerView homeHeaderBannerView2 = this.f5524h;
            if (homeHeaderBannerView2 != null) {
                homeHeaderBannerView2.m24879b();
            }
        } else if (aVar.f16136f == 3 && (homeHeaderBannerView = this.f5524h) != null) {
            homeHeaderBannerView.m24879b();
        }
    }

    @Subscribe(m3389a = ThreadMode.MAIN)
    /* renamed from: a */
    public void m24941a(ADStatueEvent.C3550a aVar) {
        this.f5538v.setVisibility(8);
        BannerManager.m12679a().m12672a(new DoneCallback() { // from class: com.angel.nrzs.ui.fragment.-$$Lambda$ChannelFragment$UfJ_pYPOTI3hR2T2O0lZkVLMdEI
            @Override // p110z1.DoneCallback
            public final void onDone(Object obj) {
                ChannelFragment.this.m24942a((List) obj);
            }
        });
        BannerManager.m12679a().m12660f(new DoneCallback() { // from class: com.angel.nrzs.ui.fragment.-$$Lambda$ChannelFragment$Z_ca7V8SEUIsvmN1pTGaXS8YwM0
            @Override // p110z1.DoneCallback
            public final void onDone(Object obj) {
                ChannelFragment.this.m24943a((AdResultInfoItem) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public /* synthetic */ void m24942a(List list) {
        List<AdResultInfoItem> list2 = this.f5528l;
        if (list2 == null || list2.size() == 0) {
            this.f5524h.setVisible(8);
            return;
        }
        this.f5524h.setVisible(0);
        this.f5524h.m24883a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public /* synthetic */ void m24943a(AdResultInfoItem adResultInfoItem) {
        if (adResultInfoItem != null) {
            this.f5529m = adResultInfoItem;
            this.f5538v.setVisibility(0);
            if (!TextUtils.isEmpty(this.f5529m.ImgUrl)) {
                GlideImageUtils.m11880a(this.f5539w, getContext(), (int) C0692R.C0693drawable.f1994b5, this.f5529m.ImgUrl);
            }
        }
    }
}
