package com.angel.nrzs.app.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alipay.sdk.widget.C0675j;
import com.angel.nrzs.C0692R;
import com.angel.nrzs.adapter.RewardAdapter;
import com.angel.nrzs.app.base.AppBaseActivity;
import com.angel.nrzs.app.view.ToastView;
import com.gyf.barlibrary.ImmersionBar;
import com.nrzs.base.router.RouterConstants;
import com.nrzs.base.router.RouterUtils;
import com.nrzs.data.base.BaseResponse;
import com.nrzs.data.other.bean.SgbInfo;
import com.nrzs.data.other.bean.request.GetRewardRequestinfo;
import com.nrzs.data.other.bean.response.GiveRewardresponseinfo;
import com.nrzs.data.other.bean.response.RewarResponseinfo;
import com.nrzs.http.ThreadCallback;
import com.nrzs.http.UICallback;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import p110z1.BirMoneyEvent;
import p110z1.EventBus;
import p110z1.GlideImageUtils;
import p110z1.ILoadData;
import p110z1.LocalLoadHelper;
import p110z1.LoginManager;
import p110z1.RewardRepository;
import p110z1.RewardSuccessDialog;
import p110z1.TypeToken;
import p110z1.apa;
import p110z1.apn;

@Route(path = RouterConstants.ModuleUser.REWARD)
/* renamed from: com.angel.nrzs.ui.activity.RewardActivity */
/* loaded from: classes.dex */
public class RewardActivity extends AppBaseActivity implements View.OnClickListener {

    /* renamed from: b */
    private RecyclerView f5463b;

    /* renamed from: c */
    private RewardAdapter f5464c;

    /* renamed from: d */
    private List<SgbInfo> f5465d;

    /* renamed from: e */
    private TextView f5466e;

    /* renamed from: f */
    private ImageView f5467f;

    /* renamed from: g */
    private apn f5468g;

    /* renamed from: h */
    private LinearLayout f5469h;

    /* renamed from: i */
    private RewardRepository f5470i;

    /* renamed from: j */
    private long f5471j;

    /* renamed from: k */
    private ImageView f5472k;

    /* renamed from: l */
    private TextView f5473l;

    /* renamed from: m */
    private TextView f5474m;

    /* renamed from: o */
    private TextView f5476o;

    /* renamed from: p */
    private ToastView f5477p;

    /* renamed from: n */
    private long f5475n = 0;

    /* renamed from: q */
    private RewarResponseinfo f5478q = null;

    /* renamed from: r */
    private ThreadCallback<BaseResponse<RewarResponseinfo>, String> f5479r = new ThreadCallback<BaseResponse<RewarResponseinfo>, String>() { // from class: com.angel.nrzs.ui.activity.RewardActivity.1
        /* renamed from: a */
        public BaseResponse<RewarResponseinfo> onResponse(String str) {
            BaseResponse<RewarResponseinfo> baseResponse = (BaseResponse) apa.m11877a(str, new TypeToken<BaseResponse<RewarResponseinfo>>() { // from class: com.angel.nrzs.ui.activity.RewardActivity.1.1
            });
            if (baseResponse != null) {
                return baseResponse;
            }
            return null;
        }
    };

    /* renamed from: s */
    private UICallback<BaseResponse<RewarResponseinfo>> f5480s = new UICallback<BaseResponse<RewarResponseinfo>>() { // from class: com.angel.nrzs.ui.activity.RewardActivity.2
        @Override // com.nrzs.http.UICallback
        /* renamed from: a */
        public void mo3021a(Throwable th) {
            RewardActivity.this.f5468g.mo11681l_();
        }

        /* renamed from: a  reason: avoid collision after fix types in other method */
        public void mo3022a(BaseResponse<RewarResponseinfo> baseResponse) {
            if (baseResponse == null) {
                return;
            }
            if (baseResponse.code == 1) {
                if (baseResponse != null && baseResponse.data != null) {
                    RewardActivity.this.f5468g.mo11679n_();
                    RewardActivity.this.m24979a(baseResponse.data);
                }
            } else if (RewardActivity.this.f5477p != null) {
                RewardActivity.this.f5477p.m24867a(baseResponse.msg);
            }
        }
    };

    /* renamed from: t */
    private ThreadCallback<BaseResponse<GiveRewardresponseinfo>, String> f5481t = new ThreadCallback<BaseResponse<GiveRewardresponseinfo>, String>() { // from class: com.angel.nrzs.ui.activity.RewardActivity.3
        /* renamed from: a */
        public BaseResponse<GiveRewardresponseinfo> onResponse(String str) {
            BaseResponse<GiveRewardresponseinfo> baseResponse = (BaseResponse) apa.m11877a(str, new TypeToken<BaseResponse<GiveRewardresponseinfo>>() { // from class: com.angel.nrzs.ui.activity.RewardActivity.3.1
            });
            if (baseResponse != null) {
                return baseResponse;
            }
            return null;
        }
    };

    /* renamed from: a */
    public UICallback<BaseResponse<GiveRewardresponseinfo>> f5462a = new UICallback<BaseResponse<GiveRewardresponseinfo>>() { // from class: com.angel.nrzs.ui.activity.RewardActivity.4
        @Override // com.nrzs.http.UICallback
        /* renamed from: a */
        public void mo3021a(Throwable th) {
            RewardActivity.this.f5468g.mo11681l_();
        }

        /* renamed from: a  reason: avoid collision after fix types in other method */
        public void mo3022a(BaseResponse<GiveRewardresponseinfo> baseResponse) {
            if (baseResponse == null) {
                return;
            }
            if (baseResponse.code == 1) {
                if (baseResponse != null && baseResponse.data != null) {
                    RewardActivity rewardActivity = RewardActivity.this;
                    new RewardSuccessDialog(rewardActivity, rewardActivity.f5478q).show();
                    TextView textView = RewardActivity.this.f5474m;
                    textView.setText("我的鸟币：" + RewardActivity.this.m24982a(baseResponse.data.SGB));
                    EventBus.m3448a().m3427d(new BirMoneyEvent.C3551a(RewardActivity.this.m24982a(baseResponse.data.SGB)));
                }
            } else if (RewardActivity.this.f5477p != null) {
                RewardActivity.this.f5477p.m24867a(baseResponse.msg);
            }
        }
    };

    @Override // com.nrzs.libcommon.BaseActivity
    /* renamed from: a */
    protected int mo18307a() {
        return C0692R.layout.nrzs_activity_reward;
    }

    @Override // com.nrzs.libcommon.BaseActivity
    /* renamed from: b */
    public void mo18303b() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.angel.nrzs.app.base.AppBaseActivity, com.nrzs.libcommon.BaseActivity, android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        getWindow().setSoftInputMode(32);
        m24977c();
    }

    /* renamed from: c */
    public void m24977c() {
        ImmersionBar.m20080a(this).m20009d(findViewById(C0692R.C0694id.rl_reward_title)).m19974h(true).m19994f();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.angel.nrzs.app.base.AppBaseActivity, android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // com.nrzs.libcommon.BaseActivity
    /* renamed from: a */
    public void mo18305a(Bundle bundle) {
        this.f5471j = getIntent().getLongExtra("authorid", 0L);
        this.f5463b = (RecyclerView) findViewById(C0692R.C0694id.nrzs_app_reward_recycle);
        this.f5466e = (TextView) findViewById(C0692R.C0694id.nrzs_app_reward_reward_btn);
        this.f5469h = (LinearLayout) findViewById(C0692R.C0694id.nrzs_app_reward_load_lay);
        this.f5467f = (ImageView) findViewById(C0692R.C0694id.f2520ca);
        this.f5472k = (ImageView) findViewById(C0692R.C0694id.nrzs_app_reward_heardimg);
        this.f5473l = (TextView) findViewById(C0692R.C0694id.nrzs_app_reward_name);
        this.f5474m = (TextView) findViewById(C0692R.C0694id.nrzs_app_reward_coin);
        this.f5476o = (TextView) findViewById(C0692R.C0694id.nrzs_app_reward_authorjy);
        this.f5477p = (ToastView) findViewById(C0692R.C0694id.nrzs_app_reward_toast);
    }

    /* renamed from: a */
    public void m24979a(RewarResponseinfo rewarResponseinfo) {
        this.f5478q = rewarResponseinfo;
        this.f5465d.clear();
        this.f5465d.addAll(rewarResponseinfo.RewardSGBList);
        SgbInfo sgbInfo = new SgbInfo();
        sgbInfo.type = 1;
        this.f5465d.add(sgbInfo);
        this.f5464c.notifyDataSetChanged();
        this.f5464c.m25157b(0);
        GlideImageUtils.m11880a(this.f5472k, this, (int) C0692R.C0693drawable.f2238m0, rewarResponseinfo.AuthorHeadImgPath);
        this.f5473l.setText(TextUtils.isEmpty(rewarResponseinfo.AuthorNickName) ? "辅助大神" : rewarResponseinfo.AuthorNickName);
        TextView textView = this.f5474m;
        textView.setText("我的鸟币：" + m24982a(rewarResponseinfo.MySGB));
        if ("".equals(rewarResponseinfo.AuthorPersonalInfo)) {
            this.f5476o.setText("这个作者很懒, 什么也没留下");
        } else {
            this.f5476o.setText(rewarResponseinfo.AuthorPersonalInfo);
        }
    }

    @Override // com.nrzs.libcommon.BaseActivity
    /* renamed from: b */
    public void mo18302b(Bundle bundle) {
        this.f5465d = new ArrayList();
        this.f5470i = new RewardRepository();
        this.f5463b.setLayoutManager(new GridLayoutManager(this, 3));
        this.f5464c = new RewardAdapter(this.f5465d);
        this.f5463b.setAdapter(this.f5464c);
        this.f5468g = new apn(new LocalLoadHelper(getApplicationContext(), this.f5469h, new View.OnClickListener() { // from class: com.angel.nrzs.ui.activity.RewardActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                RewardActivity.this.m24972f();
            }
        }), new ILoadData() { // from class: com.angel.nrzs.ui.activity.RewardActivity.6
            @Override // p110z1.ILoadData
            /* renamed from: a */
            public void mo11683a(int i) {
            }
        });
        m24972f();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: f */
    public void m24972f() {
        this.f5468g.mo11682k_();
        try {
            GetRewardRequestinfo getRewardRequestinfo = new GetRewardRequestinfo();
            getRewardRequestinfo.AuthorUserID = this.f5471j;
            getRewardRequestinfo.UserID = LoginManager.m12620d().m12614j();
            this.f5470i.m12580a(getRewardRequestinfo, this.f5480s, this.f5479r);
        } catch (Exception e) {
            e.printStackTrace();
            this.f5468g.mo11681l_();
        }
    }

    /* renamed from: g */
    private void m24971g() {
        try {
            GetRewardRequestinfo getRewardRequestinfo = new GetRewardRequestinfo();
            getRewardRequestinfo.AuthorUserID = this.f5471j;
            getRewardRequestinfo.UserID = LoginManager.m12620d().m12614j();
            getRewardRequestinfo.SGB = this.f5464c.m25161a();
            this.f5470i.m12579b(getRewardRequestinfo, this.f5462a, this.f5481t);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.nrzs.libcommon.BaseActivity
    /* renamed from: d */
    public void mo18300d() {
        this.f5466e.setOnClickListener(this);
        this.f5467f.setOnClickListener(this);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == this.f5466e.getId()) {
            m24971g();
        } else if (id == this.f5467f.getId()) {
            m24974e();
        }
    }

    /* renamed from: e */
    public void m24974e() {
        Bundle bundleExtra = getIntent().getBundleExtra("bundle");
        if (bundleExtra != null) {
            RouterUtils.toScripInfo(bundleExtra.getLong("topicId"), bundleExtra.getString("script_id_key"), bundleExtra.getString(C0675j.f373k));
        }
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public String m24982a(double d) {
        return new DecimalFormat("#").format(d);
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onBackPressed() {
        m24974e();
    }
}
