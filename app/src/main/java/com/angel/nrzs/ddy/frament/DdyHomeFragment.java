package com.angel.nrzs.ddy.frament;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.ActivityNotFoundException;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.angel.nrzs.App;
import com.angel.nrzs.C0692R;
import com.angel.nrzs.adapter.ViewPageCardAdapter;
import com.angel.nrzs.ddy.model.DdyHomeFragmentModel;
import com.angel.nrzs.app.activity.NRZSWebviewActivity;
import com.angel.nrzs.app.base.AppBaseFragment;
import com.angel.nrzs.app.view.CardTransformer;
import com.blankj.utilcode.util.LogUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.nrzs.base.router.RouterUtils;
import com.nrzs.base.router.provider.ProviderFactory;
import com.nrzs.data.ddy.bean.YsjResultInfo;
import com.nrzs.data.ddy.bean.respond.GroupInfo;
import com.nrzs.data.ddy.bean.respond.OrderDaileInfo;
import com.nrzs.data.other.bean.AdResultInfoItem;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import p110z1.BannerManager;
import p110z1.BuysuccessEvent;
import p110z1.ChannelDataManager;
import p110z1.CommonEvent;
import p110z1.DdyGroupDialog;
import p110z1.DdyManager;
import p110z1.DoneCallback;
import p110z1.EventBus;
import p110z1.EventCollectManager;
import p110z1.EventConstants;
import p110z1.FlaushDdyEvent;
import p110z1.GroupFlushEvent;
import p110z1.GroupManager;
import p110z1.HttpVal;
import p110z1.LoginManager;
import p110z1.PreSetListManager;
import p110z1.Subscribe;
import p110z1.ThreadMode;
import p110z1.UserInfoEvent;

/* loaded from: classes.dex */
public class DdyHomeFragment extends AppBaseFragment {

    /* renamed from: g */
    private DdyHomeFragmentModel f5275g;

    /* renamed from: h */
    private ViewPager f5276h;

    /* renamed from: i */
    private ViewPageCardAdapter f5277i;

    /* renamed from: j */
    private ImageView f5278j;

    /* renamed from: k */
    private ImageView f5279k;

    /* renamed from: l */
    private RelativeLayout f5280l;

    /* renamed from: m */
    private TextView f5281m;

    /* renamed from: n */
    private TextView f5282n;

    /* renamed from: o */
    private TextView f5283o;

    /* renamed from: p */
    private ImageView f5284p;

    /* renamed from: q */
    private ImageView f5285q;

    /* renamed from: r */
    private RelativeLayout f5286r;

    /* renamed from: v */
    private LinearLayout f5290v;

    /* renamed from: w */
    private LinearLayout f5291w;

    /* renamed from: c */
    private List<OrderDaileInfo> f5274c = new ArrayList();

    /* renamed from: s */
    private Map<Long, List<OrderDaileInfo>> f5287s = new HashMap();

    /* renamed from: t */
    private List<GroupInfo> f5288t = new ArrayList();

    /* renamed from: u */
    private List<GroupInfo> f5289u = new ArrayList();

    /* renamed from: x */
    private Observer<List<OrderDaileInfo>> f5292x = new Observer<List<OrderDaileInfo>>() { // from class: com.angel.nrzs.ddy.frament.DdyHomeFragment.1
        /* renamed from: a */
        public void onChanged(@Nullable List<OrderDaileInfo> list) {
            LogUtils.m23734c("HomeFragmentChange", "onChange");
            DdyHomeFragment.this.f5274c.clear();
            if (list == null) {
                DdyHomeFragment.this.f5291w.setVisibility(8);
                DdyHomeFragment.this.f5290v.setVisibility(0);
                DdyHomeFragment.this.f5281m.setVisibility(8);
                DdyHomeFragment.this.m25100h();
                return;
            }
            DdyHomeFragment.this.f5274c = list;
            GroupManager.m12887a().m12882b(DdyHomeFragment.this.f5274c);
            DdyHomeFragment.this.m25102g();
        }
    };

    /* renamed from: y */
    private Observer<List<GroupInfo>> f5293y = new Observer<List<GroupInfo>>() { // from class: com.angel.nrzs.ddy.frament.DdyHomeFragment.2
        /* renamed from: a */
        public void onChanged(@Nullable List<GroupInfo> list) {
            LogUtils.m23734c("HomeFragmentChange", "onChange");
            if (list == null) {
                DdyHomeFragment.this.f5291w.setVisibility(8);
                DdyHomeFragment.this.f5290v.setVisibility(0);
                DdyHomeFragment.this.f5281m.setVisibility(8);
                DdyHomeFragment.this.m25100h();
                return;
            }
            DdyHomeFragment.this.f5280l.setBackgroundColor(DdyHomeFragment.this.getContext().getResources().getColor(C0692R.color.ad_broucks));
            DdyHomeFragment.this.f5289u.clear();
            DdyHomeFragment.this.f5288t = list;
            DdyHomeFragment.this.f5289u.addAll(DdyHomeFragment.this.f5288t);
            DdyHomeFragment.this.f5287s.clear();
            GroupManager.m12887a().m12885a(list);
            GroupInfo groupInfo = new GroupInfo();
            groupInfo.GroupName = "全部设备";
            groupInfo.GroupId = -1L;
            DdyHomeFragment.this.f5289u.add(0, groupInfo);
            for (int i = 0; i < list.size(); i++) {
                GroupInfo groupInfo2 = list.get(i);
                ArrayList arrayList = new ArrayList();
                for (int i2 = 0; i2 < DdyHomeFragment.this.f5274c.size(); i2++) {
                    OrderDaileInfo orderDaileInfo = (OrderDaileInfo) DdyHomeFragment.this.f5274c.get(i2);
                    if (groupInfo2.GroupId == orderDaileInfo.GroupId) {
                        arrayList.add(orderDaileInfo);
                    }
                }
                DdyHomeFragment.this.f5287s.put(Long.valueOf(groupInfo2.GroupId), arrayList);
            }
            GroupManager.m12887a().m12884a(DdyHomeFragment.this.f5287s);
            DdyHomeFragment.this.m25124a();
        }
    };

    @Subscribe(m3389a = ThreadMode.MAIN)
    /* renamed from: a */
    public void m25115a(CommonEvent.C3553a aVar) {
    }

    @Subscribe(m3389a = ThreadMode.MAIN)
    /* renamed from: a */
    public void m25111a(UserInfoEvent.C3561a aVar) {
    }

    @Override // com.nrzs.libcommon.BaseFragment
    /* renamed from: b */
    protected int mo18216b() {
        return C0692R.layout.nrzs_fragment_ysj_index_layout;
    }

    /* renamed from: a */
    private void m25119a(String str, int i, int i2) {
        TextView textView = this.f5281m;
        textView.setText(str + "(" + i2 + ")");
    }

    /* renamed from: a */
    public void m25118a(String str, int i, long j) {
        m25117a(this.f5287s.get(Long.valueOf(j)));
        m25119a(str, i, this.f5287s.get(Long.valueOf(j)).size());
    }

    /* renamed from: a */
    public void m25124a() {
        m25117a(this.f5274c);
        m25119a("全部设备", 1, this.f5274c.size());
    }

    @Override // android.support.v4.app.Fragment
    public void onResume() {
        super.onResume();
        Log.e("ddyhaome", "resume");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.nrzs.libcommon.BaseFragment
    /* renamed from: a */
    public void mo18221a(View view) {
        this.f5276h = (ViewPager) view.findViewById(C0692R.C0694id.vp_card);
        this.f5278j = (ImageView) view.findViewById(C0692R.C0694id.flash_list);
        this.f5281m = (TextView) view.findViewById(C0692R.C0694id.group_title);
        this.f5282n = (TextView) view.findViewById(C0692R.C0694id.add_btn);
        this.f5283o = (TextView) view.findViewById(C0692R.C0694id.no_login_add);
        this.f5284p = (ImageView) view.findViewById(C0692R.C0694id.ad_close);
        this.f5285q = (ImageView) view.findViewById(C0692R.C0694id.ad_img);
        this.f5286r = (RelativeLayout) view.findViewById(C0692R.C0694id.ad_lay);
        this.f5290v = (LinearLayout) view.findViewById(C0692R.C0694id.no_login);
        this.f5290v.setVisibility(8);
        this.f5291w = (LinearLayout) view.findViewById(C0692R.C0694id.toplaypout);
        this.f5279k = (ImageView) view.findViewById(C0692R.C0694id.no_loging_img);
        this.f5280l = (RelativeLayout) view.findViewById(C0692R.C0694id.button_lay);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: g */
    public void m25102g() {
        this.f5275g.m25089d().observe(this, this.f5293y);
        this.f5275g.m25091b();
    }

    /* renamed from: c */
    public void m25107c() {
        if (!DdyManager.m3122c().m3128a()) {
            DdyManager.m3122c().m3121d();
        }
        if (this.f5275g == null) {
            this.f5275g = (DdyHomeFragmentModel) ViewModelProviders.m25706of(this).get(DdyHomeFragmentModel.class);
        }
        this.f5275g.m25090c().observe(this, this.f5292x);
        this.f5275g.m25093a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: h */
    public void m25100h() {
        BannerManager.m12679a().m12668b(new DoneCallback() { // from class: com.angel.nrzs.ddy.frament.-$$Lambda$DdyHomeFragment$DDLo7HcE6WTjpMzGp_ljmvQrNM4
            @Override // p110z1.DoneCallback
            public final void onDone(Object obj) {
                DdyHomeFragment.this.m25108b((List) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public /* synthetic */ void m25108b(final List list) {
        if (list == null) {
            this.f5286r.setVisibility(8);
            if (this.f5290v.getVisibility() == 0) {
                this.f5280l.setBackgroundColor(App.m25213a().getResources().getColor(C0692R.color.f1449fa));
            } else {
                this.f5280l.setBackgroundColor(App.m25213a().getResources().getColor(C0692R.color.ad_broucks));
            }
        } else if (list.size() <= 0 || getContext() == null) {
            this.f5286r.setVisibility(8);
            if (this.f5290v.getVisibility() == 0) {
                this.f5280l.setBackgroundColor(App.m25213a().getResources().getColor(C0692R.color.f1449fa));
            } else {
                this.f5280l.setBackgroundColor(App.m25213a().getResources().getColor(C0692R.color.ad_broucks));
            }
        } else {
            this.f5286r.setVisibility(0);
            Glide.with(getContext()).load(((AdResultInfoItem) list.get(0)).ImgUrl).apply((BaseRequestOptions<?>) new RequestOptions().placeholder(C0692R.C0693drawable.f2399ep)).into(this.f5285q);
            this.f5285q.setOnClickListener(new View.OnClickListener() { // from class: com.angel.nrzs.ddy.frament.DdyHomeFragment.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    try {
                        AdResultInfoItem adResultInfoItem = (AdResultInfoItem) list.get(0);
                        if (!TextUtils.isEmpty(adResultInfoItem.ExecArgs)) {
                            NRZSWebviewActivity.m25004a(DdyHomeFragment.this.getContext(), 99, 1, adResultInfoItem);
                        }
                    } catch (ActivityNotFoundException unused) {
                        Log.d("FirstActivity.this", "找不到该活动");
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.nrzs.libcommon.BaseFragment
    /* renamed from: d */
    public void mo18212d() {
        Log.e("ddyhome", "initdata");
        if (LoginManager.m12620d().m12606r()) {
            this.f5291w.setVisibility(0);
            this.f5290v.setVisibility(8);
            this.f5281m.setVisibility(0);
            m25107c();
        } else {
            this.f5291w.setVisibility(8);
            this.f5290v.setVisibility(0);
            this.f5281m.setVisibility(8);
        }
        m25100h();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m25123a(int i) {
        if (ChannelDataManager.m12655a().m12650d()) {
            ProviderFactory.createCarkey().showdialog(getContext());
            return;
        }
        YsjResultInfo ysjResultInfo = new YsjResultInfo();
        ysjResultInfo.Title = "购买云手机";
        ysjResultInfo.ExecArgs = HttpVal.f16516c;
        NRZSWebviewActivity.m25006a(getContext(), 0, 1, i, ysjResultInfo);
    }

    /* renamed from: a */
    public void m25117a(List<OrderDaileInfo> list) {
        ViewPageCardAdapter viewPageCardAdapter = this.f5277i;
        if (viewPageCardAdapter == null) {
            this.f5277i = new ViewPageCardAdapter(getContext(), list);
        } else {
            viewPageCardAdapter.notifyDataSetChanged();
            this.f5277i.m25150a(list);
        }
        this.f5276h.setOffscreenPageLimit(2);
        this.f5276h.setPageMargin(0);
        this.f5276h.setClipChildren(false);
        this.f5276h.setPageTransformer(true, new CardTransformer());
        this.f5276h.setAdapter(this.f5277i);
    }

    @Subscribe(m3389a = ThreadMode.MAIN)
    /* renamed from: a */
    public void m25114a(FlaushDdyEvent.C3554a aVar) {
        this.f5291w.setVisibility(0);
        this.f5281m.setVisibility(0);
        this.f5290v.setVisibility(8);
        m25107c();
    }

    @Subscribe(m3389a = ThreadMode.MAIN)
    /* renamed from: a */
    public void m25113a(GroupFlushEvent.C3557a aVar) {
        this.f5291w.setVisibility(0);
        this.f5281m.setVisibility(0);
        this.f5290v.setVisibility(8);
        m25107c();
    }

    @Subscribe(m3389a = ThreadMode.MAIN)
    /* renamed from: a */
    public void m25116a(BuysuccessEvent.C3552a aVar) {
        Log.e("Exchange", "兑换成功接收去刷新列表");
        this.f5291w.setVisibility(0);
        this.f5290v.setVisibility(8);
        this.f5281m.setVisibility(0);
        m25107c();
    }

    @Subscribe(m3389a = ThreadMode.MAIN)
    /* renamed from: a */
    public void m25112a(GroupFlushEvent.C3559c cVar) {
        if (cVar.f16129b == -1) {
            m25124a();
        } else {
            m25118a(cVar.f16128a, cVar.f16130c, cVar.f16129b);
        }
    }

    @Override // com.nrzs.libcommon.BaseFragment, android.support.v4.app.Fragment
    @Nullable
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        EventBus.m3448a().m3446a(this);
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    @Override // com.nrzs.libcommon.BaseFragment, android.support.v4.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        ViewPageCardAdapter viewPageCardAdapter = this.f5277i;
        if (viewPageCardAdapter != null) {
            viewPageCardAdapter.notifyDataSetChanged();
        }
        EventBus.m3448a().m3430c(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.nrzs.libcommon.BaseFragment
    /* renamed from: e */
    public void mo18210e() {
        this.f5278j.setOnClickListener(new View.OnClickListener() { // from class: com.angel.nrzs.ddy.frament.DdyHomeFragment.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DdyHomeFragment.this.m25107c();
            }
        });
        this.f5281m.setOnClickListener(new View.OnClickListener() { // from class: com.angel.nrzs.ddy.frament.DdyHomeFragment.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DdyGroupDialog ebVar = new DdyGroupDialog(DdyHomeFragment.this.getContext());
                ebVar.m3179a(DdyHomeFragment.this.f5289u);
                ebVar.show();
            }
        });
        this.f5282n.setOnClickListener(new View.OnClickListener() { // from class: com.angel.nrzs.ddy.frament.DdyHomeFragment.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (LoginManager.m12620d().m12606r()) {
                    EventCollectManager.m12642a().m12640a(DdyHomeFragment.this.getContext(), "顶部添加设备", "顶部添加设备", EventConstants.f16427ad);
                    DdyHomeFragment.this.m25123a(4);
                    return;
                }
                RouterUtils.toLogin(1, 0);
            }
        });
        this.f5279k.setOnClickListener(new View.OnClickListener() { // from class: com.angel.nrzs.ddy.frament.DdyHomeFragment.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                String h = PreSetListManager.m12116a().m12108h();
                if (!TextUtils.isEmpty(h)) {
                    try {
                        AdResultInfoItem adResultInfoItem = new AdResultInfoItem();
                        adResultInfoItem.ExecArgs = h;
                        adResultInfoItem.Title = "使用教程";
                        NRZSWebviewActivity.m25004a(DdyHomeFragment.this.getContext(), 99, 1, adResultInfoItem);
                    } catch (ActivityNotFoundException unused) {
                        Log.d("FirstActivity.this", "找不到该活动");
                    }
                }
            }
        });
        this.f5283o.setOnClickListener(new View.OnClickListener() { // from class: com.angel.nrzs.ddy.frament.DdyHomeFragment.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (LoginManager.m12620d().m12606r()) {
                    EventCollectManager.m12642a().m12640a(DdyHomeFragment.this.getContext(), "无设备首页添加", "无设备首页添加", EventConstants.f16426ac);
                    DdyHomeFragment.this.m25123a(4);
                    return;
                }
                RouterUtils.toLogin(1, 0);
            }
        });
        this.f5284p.setOnClickListener(new View.OnClickListener() { // from class: com.angel.nrzs.ddy.frament.DdyHomeFragment.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DdyHomeFragment.this.f5286r.setVisibility(8);
                if (DdyHomeFragment.this.f5290v.getVisibility() == 0) {
                    DdyHomeFragment.this.f5280l.setBackgroundColor(DdyHomeFragment.this.getContext().getResources().getColor(C0692R.color.f1449fa));
                } else {
                    DdyHomeFragment.this.f5280l.setBackgroundColor(DdyHomeFragment.this.getContext().getResources().getColor(C0692R.color.ad_broucks));
                }
            }
        });
    }
}
