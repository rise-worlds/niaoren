package com.angel.nrzs.p017ui.fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p003v4.widget.SwipeRefreshLayout;
import android.support.p006v7.widget.GridLayoutManager;
import android.support.p006v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.angel.nrzs.App;
import com.angel.nrzs.C0692R;
import com.angel.nrzs.adapter.HomeAdapter;
import com.angel.nrzs.model.HomeFragmentModel;
import com.angel.nrzs.p017ui.base.AppBaseFragment;
import com.angel.nrzs.p017ui.view.HomeHeaderBannerView;
import com.blankj.utilcode.util.LogUtils;
import com.nrzs.base.router.RouterUtils;
import com.nrzs.data.game.bean.TopicInfo;
import com.nrzs.data.other.bean.AdResultInfoItem;
import com.nrzs.moudleui.p070ui.state.LoadingView;
import java.util.ArrayList;
import java.util.List;
import p110z1.ADStatueEvent;
import p110z1.BannerManager;
import p110z1.CommonEvent;
import p110z1.DoneCallback;
import p110z1.EventBus;
import p110z1.EventCollectManager;
import p110z1.EventConstants;
import p110z1.LoginEvent;
import p110z1.PreSetListManager;
import p110z1.Subscribe;
import p110z1.ThreadMode;
import p110z1.UserInfoEvent;

/* renamed from: com.angel.nrzs.ui.fragment.HomeFragment */
/* loaded from: classes.dex */
public class HomeFragment extends AppBaseFragment {

    /* renamed from: c */
    private List<TopicInfo> f5546c;

    /* renamed from: g */
    private HomeHeaderBannerView f5547g;

    /* renamed from: h */
    private RecyclerView f5548h;

    /* renamed from: i */
    private SwipeRefreshLayout f5549i;

    /* renamed from: j */
    private HomeAdapter f5550j;

    /* renamed from: k */
    private HomeFragmentModel f5551k;

    /* renamed from: l */
    private LoadingView f5552l;

    /* renamed from: m */
    private LinearLayout f5553m;

    /* renamed from: n */
    private TextView f5554n;

    /* renamed from: o */
    private List<AdResultInfoItem> f5555o = new ArrayList();

    /* renamed from: p */
    private Observer<List<TopicInfo>> f5556p = new Observer<List<TopicInfo>>() { // from class: com.angel.nrzs.ui.fragment.HomeFragment.1
        /* renamed from: a */
        public void onChanged(@Nullable List<TopicInfo> list) {
            LogUtils.m23734c("HomeFragmentChange", "onChange");
            HomeFragment.this.f5550j.m18494a(HomeFragment.this.f5552l);
            if (list != null) {
                HomeFragment.this.f5546c.clear();
                HomeFragment.this.f5546c.addAll(list);
                HomeFragment.this.f5550j.notifyDataSetChanged();
            }
        }
    };

    @Subscribe(m3389a = ThreadMode.MAIN)
    /* renamed from: a */
    public void m24919a(UserInfoEvent.C3561a aVar) {
    }

    @Override // com.nrzs.libcommon.BaseFragment
    /* renamed from: b */
    protected int mo18216b() {
        return C0692R.layout.nrzs_fragment_main_index_layout;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.nrzs.libcommon.BaseFragment
    /* renamed from: a */
    public void mo18221a(View view) {
        this.f5548h = (RecyclerView) view.findViewById(C0692R.C0694id.nrzs_app_home_recyclerView);
        this.f5549i = (SwipeRefreshLayout) view.findViewById(C0692R.C0694id.nrzs_app_home_swiprefresh);
        this.f5549i.setRefreshing(false);
        this.f5549i.setEnabled(false);
        this.f5553m = (LinearLayout) view.findViewById(C0692R.C0694id.nrzs_app_ll_search);
        this.f5554n = (TextView) view.findViewById(C0692R.C0694id.nrzs_app_tv_search);
        if (PreSetListManager.m12116a().m12113c() != null && !PreSetListManager.m12116a().m12113c().equals("")) {
            this.f5554n.setHint(PreSetListManager.m12116a().m12113c());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.nrzs.libcommon.BaseFragment
    /* renamed from: d */
    public void mo18212d() {
        this.f5546c = new ArrayList();
        if (this.f5551k == null) {
            this.f5551k = (HomeFragmentModel) ViewModelProviders.m25706of(this).get(HomeFragmentModel.class);
        }
        this.f5551k.m25046b().observe(this, this.f5556p);
        this.f5548h.setLayoutManager(new GridLayoutManager(getContext(), 3));
        this.f5552l = new LoadingView(getActivity());
        this.f5547g = new HomeHeaderBannerView(getActivity());
        this.f5550j = new HomeAdapter(this.f5546c);
        LogUtils.m23734c("HomeAdapter", "添加头布局前");
        this.f5550j.m18491a((View) this.f5547g, 0, true);
        LogUtils.m23734c("HomeAdapter", "添加头布局后");
        List<AdResultInfoItem> list = this.f5555o;
        if (list == null || list.size() <= 0) {
            this.f5547g.setVisible(8);
            this.f5550j.m18491a((View) this.f5552l, 1, true);
        } else {
            this.f5547g.setVisible(0);
            this.f5547g.m24883a();
            this.f5550j.m18491a((View) this.f5552l, 1, true);
        }
        LogUtils.m23734c("HomeAdapter", "添加头布局后2");
        BannerManager.m12679a().m12672a(new DoneCallback() { // from class: com.angel.nrzs.ui.fragment.-$$Lambda$HomeFragment$3RVgPQW5732V8p9pZdoQXZSQXMA
            @Override // p110z1.DoneCallback
            public final void onDone(Object obj) {
                HomeFragment.this.m24917b((List) obj);
            }
        });
        this.f5548h.setAdapter(this.f5550j);
        LogUtils.m23734c("HomeAdapter", "添加头布局后3");
        this.f5551k.m25047a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public /* synthetic */ void m24917b(List list) {
        if (list != null) {
            this.f5555o = list;
            if (this.f5555o.size() > 0) {
                this.f5547g.setVisible(0);
                this.f5547g.m24883a();
                return;
            }
            this.f5547g.setVisible(8);
        }
    }

    @Subscribe(m3389a = ThreadMode.MAIN)
    /* renamed from: a */
    public void m24922a(ADStatueEvent.C3550a aVar) {
        this.f5550j.m18494a(this.f5547g);
        this.f5550j.m18491a((View) this.f5547g, 0, true);
        BannerManager.m12679a().m12672a(new DoneCallback() { // from class: com.angel.nrzs.ui.fragment.-$$Lambda$HomeFragment$lGJiPL8ZbL0GN-zc9cH_z27OCVk
            @Override // p110z1.DoneCallback
            public final void onDone(Object obj) {
                HomeFragment.this.m24923a((List) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public /* synthetic */ void m24923a(List list) {
        List<AdResultInfoItem> list2 = this.f5555o;
        if (list2 == null || list2.size() == 0) {
            this.f5547g.setVisible(8);
            return;
        }
        this.f5547g.setVisible(0);
        this.f5547g.m24883a();
    }

    @Subscribe(m3389a = ThreadMode.MAIN)
    /* renamed from: a */
    public void m24920a(LoginEvent.C3560a aVar) {
        HomeHeaderBannerView homeHeaderBannerView;
        if (aVar.f16136f == 1) {
            HomeHeaderBannerView homeHeaderBannerView2 = this.f5547g;
            if (homeHeaderBannerView2 != null) {
                homeHeaderBannerView2.m24879b();
            }
        } else if (aVar.f16136f == 3 && (homeHeaderBannerView = this.f5547g) != null) {
            homeHeaderBannerView.m24879b();
        }
    }

    @Subscribe(m3389a = ThreadMode.MAIN)
    /* renamed from: a */
    public void m24921a(CommonEvent.C3553a aVar) {
        this.f5546c.clear();
        this.f5550j.m18491a((View) this.f5552l, 1, true);
        this.f5551k.m25047a();
    }

    @Override // com.nrzs.libcommon.BaseFragment, android.support.p003v4.app.Fragment
    @Nullable
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        EventBus.m3448a().m3446a(this);
        EventCollectManager.m12642a().m12640a(App.m25213a(), "首页展示", "首页展示", EventConstants.f16435c);
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    @Override // com.nrzs.libcommon.BaseFragment, android.support.p003v4.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        EventBus.m3448a().m3430c(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.nrzs.libcommon.BaseFragment
    /* renamed from: e */
    public void mo18210e() {
        this.f5553m.setOnClickListener(new View.OnClickListener() { // from class: com.angel.nrzs.ui.fragment.HomeFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                EventCollectManager.m12642a().m12640a(App.m25213a(), "搜索栏点击", "搜索栏点击", EventConstants.f16437e);
                RouterUtils.toSearch();
            }
        });
    }
}
