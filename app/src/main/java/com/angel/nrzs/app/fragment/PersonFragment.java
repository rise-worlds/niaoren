package com.angel.nrzs.app.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import com.angel.nrzs.app.activity.NRZSWebviewActivity;
import com.angel.nrzs.app.activity.PurchasedAssistActivyt;
import com.angel.nrzs.app.base.AppBaseFragment;
import com.angel.nrzs.app.view.HomeHeaderPopupView;
import com.blankj.utilcode.util.TimeUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.cyjh.ddy.media.p035a.ResultTypeConstant;
import com.lody.virtual.client.ipc.ServiceManagerNative;
import com.nrzs.base.router.RouterUtils;
import com.nrzs.base.router.provider.ProviderFactory;
import com.nrzs.data.DataApp;
import com.nrzs.data.base.BaseResponse;
import com.nrzs.data.other.bean.AdResultInfoItem;
import com.nrzs.data.other.bean.request.UplaodAppRequestInfo;
import com.nrzs.data.user.bean.UserInfo;
import com.nrzs.data.user.bean.request.LoginOutReg;
import com.nrzs.http.ThreadCallback;
import com.nrzs.http.UICallback;
import com.nrzs.libcommon.NRZSViewClickListener;
import com.nrzs.user.ui.activity.RegisterActivity;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.apache.tools.ant.util.DateUtils;
import p110z1.BirMoneyEvent;
import p110z1.ChannelDataManager;
import p110z1.DialogC5331eu;
import p110z1.DoneCallback;
import p110z1.EventBus;
import p110z1.EventCollectManager;
import p110z1.EventConstants;
import p110z1.FloatWindowManager;
import p110z1.FtCheckFloatingDialog;
import p110z1.GlideImageUtils;
import p110z1.GoldExpireDialog;
import p110z1.HttpVal;
import p110z1.LoginEvent;
import p110z1.LoginManager;
import p110z1.NRZSCommonManager;
import p110z1.NRZSFileConfig;
import p110z1.NRZSLocalConfig;
import p110z1.PreSetListManager;
import p110z1.ScriptModelSwapDialog;
import p110z1.ShareVal;
import p110z1.Subscribe;
import p110z1.ThreadMode;
import p110z1.TipsPopWindow;
import p110z1.TypeToken;
import p110z1.UserInfoEvent;
import p110z1.UserInfoManager;
import p110z1.UserRepository;
import p110z1.VersionEvent;
import p110z1.VersionManager;
import p110z1.acf;
import p110z1.aof;
import p110z1.aoy;
import p110z1.apa;
import p110z1.apf;
import p110z1.aqg;

/* renamed from: com.angel.nrzs.ui.fragment.PersonFragment */
/* loaded from: classes.dex */
public class PersonFragment extends AppBaseFragment implements View.OnClickListener {

    /* renamed from: A */
    private TextView f5559A;

    /* renamed from: B */
    private TextView f5560B;

    /* renamed from: C */
    private TextView f5561C;

    /* renamed from: D */
    private TextView f5562D;

    /* renamed from: E */
    private RelativeLayout f5563E;

    /* renamed from: F */
    private TextView f5564F;

    /* renamed from: G */
    private ImageView f5565G;

    /* renamed from: H */
    private ImageView f5566H;

    /* renamed from: I */
    private LinearLayout f5567I;

    /* renamed from: J */
    private TextView f5568J;

    /* renamed from: K */
    private LinearLayout f5569K;

    /* renamed from: L */
    private LinearLayout f5570L;

    /* renamed from: M */
    private UserInfo f5571M;

    /* renamed from: g */
    private TextView f5576g;

    /* renamed from: h */
    private TextView f5577h;

    /* renamed from: i */
    private TextView f5578i;

    /* renamed from: j */
    private TextView f5579j;

    /* renamed from: k */
    private TextView f5580k;

    /* renamed from: l */
    private TextView f5581l;

    /* renamed from: m */
    private TextView f5582m;

    /* renamed from: n */
    private ImageView f5583n;

    /* renamed from: o */
    private ImageView f5584o;

    /* renamed from: p */
    private ImageView f5585p;

    /* renamed from: r */
    private LinearLayout f5587r;

    /* renamed from: s */
    private LinearLayout f5588s;

    /* renamed from: t */
    private LinearLayout f5589t;

    /* renamed from: u */
    private LinearLayout f5590u;

    /* renamed from: v */
    private LinearLayout f5591v;

    /* renamed from: w */
    private LinearLayout f5592w;

    /* renamed from: x */
    private LinearLayout f5593x;

    /* renamed from: y */
    private LinearLayout f5594y;

    /* renamed from: z */
    private TextView f5595z;

    /* renamed from: c */
    BroadcastReceiver f5575c = new BroadcastReceiver() { // from class: com.angel.nrzs.ui.fragment.PersonFragment.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String stringExtra = intent.getStringExtra("SGB");
            String stringExtra2 = intent.getStringExtra("VIP");
            if (intent.getAction().equals("fresh_vip_sgb")) {
                PersonFragment.this.f5581l.setText(stringExtra);
                PersonFragment.this.f5582m.setText(stringExtra2);
            }
        }
    };

    /* renamed from: q */
    private UserRepository f5586q = null;

    /* renamed from: N */
    private Handler f5572N = new Handler() { // from class: com.angel.nrzs.ui.fragment.PersonFragment.5
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what == 1) {
                PersonFragment personFragment = PersonFragment.this;
                personFragment.m24908a(personFragment.f5571M);
            }
        }
    };

    /* renamed from: O */
    private ThreadCallback<BaseResponse, String> f5573O = new ThreadCallback<BaseResponse, String>() { // from class: com.angel.nrzs.ui.fragment.PersonFragment.6
        /* renamed from: a */
        public BaseResponse onResponse(String str) {
            BaseResponse baseResponse = (BaseResponse) apa.m11877a(str, new TypeToken<BaseResponse<Object>>() { // from class: com.angel.nrzs.ui.fragment.PersonFragment.6.1
            });
            if (baseResponse.code == 0) {
                aqg.m11586a(PersonFragment.this.getContext(), baseResponse.msg);
            }
            return baseResponse;
        }
    };

    /* renamed from: P */
    private UICallback<BaseResponse> f5574P = new UICallback<BaseResponse>() { // from class: com.angel.nrzs.ui.fragment.PersonFragment.7
        @Override // com.nrzs.http.UICallback
        /* renamed from: a */
        public void mo3021a(Throwable th) {
            NRZSCommonManager.m11698b().m11700a();
            aqg.m11586a(PersonFragment.this.getContext(), "退出登录失败");
        }

        /* renamed from: a  reason: avoid collision after fix types in other method */
        public void mo3022a(BaseResponse baseResponse) {
            if (baseResponse.code != 0) {
                LoginManager.m12620d().m12617g();
            }
            NRZSCommonManager.m11698b().m11700a();
        }
    };

    @Override // com.nrzs.libcommon.BaseFragment
    /* renamed from: b */
    protected int mo18216b() {
        return C0692R.layout.nrzs_fragment_main_person_layout;
    }

    @Override // com.nrzs.libcommon.BaseFragment, android.support.v4.app.Fragment
    @Nullable
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        EventBus.m3448a().m3446a(this);
        EventCollectManager.m12642a().m12640a(App.getInstance(), "我的页面展示", "我的页面展示", EventConstants.f16436d);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("fresh_vip_sgb");
        getContext().registerReceiver(this.f5575c, intentFilter);
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    @Override // com.nrzs.libcommon.BaseFragment, android.support.v4.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        EventBus.m3448a().m3430c(this);
        getContext().unregisterReceiver(this.f5575c);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.nrzs.libcommon.BaseFragment
    /* renamed from: a */
    public void mo18221a(View view) {
        this.f5576g = (TextView) view.findViewById(C0692R.C0694id.nrzs_app_tv_resite);
        this.f5577h = (TextView) view.findViewById(C0692R.C0694id.nrzs_app_tv_login);
        this.f5578i = (TextView) view.findViewById(C0692R.C0694id.nrzs_app_tv_name);
        this.f5583n = (ImageView) view.findViewById(C0692R.C0694id.nrzs_app_tv_heardimg);
        this.f5587r = (LinearLayout) view.findViewById(C0692R.C0694id.nrzs_app_lay_pay);
        this.f5588s = (LinearLayout) view.findViewById(C0692R.C0694id.nrzs_app_lay_fz);
        this.f5589t = (LinearLayout) view.findViewById(C0692R.C0694id.nrzs_app_lay_faq);
        this.f5590u = (LinearLayout) view.findViewById(C0692R.C0694id.nrzs_app_lay_rules);
        this.f5591v = (LinearLayout) view.findViewById(C0692R.C0694id.nrzs_app_lay_model);
        this.f5592w = (LinearLayout) view.findViewById(C0692R.C0694id.nrzs_app_lay_code);
        this.f5593x = (LinearLayout) view.findViewById(C0692R.C0694id.nrzs_app_lay_openmore);
        this.f5594y = (LinearLayout) view.findViewById(C0692R.C0694id.nrzs_app_lay_question);
        this.f5579j = (TextView) view.findViewById(C0692R.C0694id.nrzs_app_tv_login_out);
        this.f5580k = (TextView) view.findViewById(C0692R.C0694id.tv_version_name);
        this.f5563E = (RelativeLayout) view.findViewById(C0692R.C0694id.rl_check_update);
        this.f5581l = (TextView) view.findViewById(C0692R.C0694id.tv_bird_money);
        this.f5582m = (TextView) view.findViewById(C0692R.C0694id.tv_vip_state);
        this.f5584o = (ImageView) view.findViewById(C0692R.C0694id.iv_bird_nb_tips);
        this.f5585p = (ImageView) view.findViewById(C0692R.C0694id.iv_red_position);
        this.f5564F = (TextView) view.findViewById(C0692R.C0694id.nrzs_app_tv_gold);
        this.f5567I = (LinearLayout) view.findViewById(C0692R.C0694id.nrzs_app_home_ll_gold);
        this.f5565G = (ImageView) view.findViewById(C0692R.C0694id.nrzs_app_iv_help);
        this.f5566H = (ImageView) view.findViewById(C0692R.C0694id.nrzs_app_iv_vip_help);
        this.f5568J = (TextView) view.findViewById(C0692R.C0694id.nrzs_app_tv_recharge_gold);
        this.f5569K = (LinearLayout) view.findViewById(C0692R.C0694id.ll_my_vip_open);
        this.f5570L = (LinearLayout) view.findViewById(C0692R.C0694id.ll_my_vip_unopen);
        this.f5559A = (TextView) view.findViewById(C0692R.C0694id.tv_my_vip_open_sum);
        this.f5560B = (TextView) view.findViewById(C0692R.C0694id.tv_my_vip_date);
        this.f5561C = (TextView) view.findViewById(C0692R.C0694id.tv_my_vip_renew);
        this.f5595z = (TextView) view.findViewById(C0692R.C0694id.tv_vip_open);
        this.f5562D = (TextView) view.findViewById(C0692R.C0694id.tv_my_vip_add_open);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.nrzs.libcommon.BaseFragment
    /* renamed from: e */
    public void mo18210e() {
        this.f5576g.setOnClickListener(this);
        this.f5577h.setOnClickListener(new NRZSViewClickListener() { // from class: com.angel.nrzs.ui.fragment.PersonFragment.8
            @Override // com.nrzs.libcommon.NRZSViewClickListener
            /* renamed from: b */
            protected void mo18274b(View view) {
            }

            @Override // com.nrzs.libcommon.NRZSViewClickListener
            /* renamed from: a */
            protected void mo18275a(View view) {
                RouterUtils.toLogin(1, 0);
            }
        });
        this.f5589t.setOnClickListener(this);
        this.f5587r.setOnClickListener(this);
        this.f5588s.setOnClickListener(this);
        this.f5590u.setOnClickListener(this);
        this.f5579j.setOnClickListener(this);
        this.f5591v.setOnClickListener(this);
        this.f5563E.setOnClickListener(this);
        this.f5582m.setOnClickListener(this);
        this.f5584o.setOnClickListener(this);
        this.f5592w.setOnClickListener(this);
        this.f5561C.setOnClickListener(this);
        this.f5595z.setOnClickListener(this);
        this.f5562D.setOnClickListener(this);
        this.f5593x.setOnClickListener(this);
        this.f5594y.setOnClickListener(this);
        this.f5565G.setOnClickListener(new View.OnClickListener() { // from class: com.angel.nrzs.ui.fragment.PersonFragment.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                HomeHeaderPopupView.m24862a(PersonFragment.this.getContext(), PersonFragment.this.f5567I);
            }
        });
        this.f5566H.setOnClickListener(new View.OnClickListener() { // from class: com.angel.nrzs.ui.fragment.PersonFragment.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                HomeHeaderPopupView.m24861b(PersonFragment.this.getContext(), PersonFragment.this.f5566H);
            }
        });
        this.f5568J.setOnClickListener(new View.OnClickListener() { // from class: com.angel.nrzs.ui.fragment.PersonFragment.11
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                EventCollectManager.m12642a().m12640a(PersonFragment.this.getContext(), "我的充值金币", "我的充值金币", EventConstants.f16418V);
                if (LoginManager.m12620d().m12606r()) {
                    PersonFragment.this.m24913a(2);
                } else {
                    RouterUtils.toLogin(1, 0);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.nrzs.libcommon.BaseFragment
    /* renamed from: d */
    public void mo18212d() {
        int i = 0;
        if (LoginManager.m12620d().m12606r()) {
            this.f5579j.setVisibility(0);
            this.f5576g.setVisibility(8);
            this.f5577h.setVisibility(8);
            this.f5578i.setText(LoginManager.m12620d().m12630a().UserName);
            this.f5571M = LoginManager.m12620d().m12630a();
            if (this.f5571M != null) {
                this.f5572N.sendEmptyMessage(1);
            }
            GlideImageUtils.m11880a(this.f5583n, getContext(), (int) C0692R.C0693drawable.f2238m0, LoginManager.m12620d().m12630a().HeadImgPath);
        } else {
            this.f5569K.setVisibility(8);
            this.f5570L.setVisibility(0);
            this.f5578i.setText("未登录");
            this.f5579j.setVisibility(8);
            this.f5576g.setVisibility(0);
            this.f5577h.setVisibility(0);
            this.f5581l.setText(ResultTypeConstant.f7213z);
            this.f5582m.setText("未开通");
            this.f5564F.setText(ResultTypeConstant.f7213z);
        }
        ImageView imageView = this.f5585p;
        if (!VersionManager.m3095a(getActivity()).m3096a()) {
            i = 8;
        }
        imageView.setVisibility(i);
        this.f5580k.setText("1.3.5");
    }

    @Subscribe(m3389a = ThreadMode.MAIN)
    /* renamed from: a */
    public void m24905a(LoginEvent.C3560a aVar) {
        if (aVar.f16136f == 1) {
            mo18212d();
        } else if (aVar.f16136f == 3) {
            mo18212d();
        }
    }

    @Override // android.support.v4.app.Fragment
    public void onResume() {
        super.onResume();
        m24893h();
    }

    @Subscribe(m3389a = ThreadMode.MAIN)
    /* renamed from: a */
    public void m24906a(BirMoneyEvent.C3551a aVar) {
        TextView textView = this.f5581l;
        textView.setText(aVar.f16124a + "");
    }

    @Subscribe(m3389a = ThreadMode.MAIN)
    /* renamed from: a */
    public void m24904a(UserInfoEvent.C3561a aVar) {
        m24893h();
    }

    @Subscribe(m3389a = ThreadMode.MAIN)
    /* renamed from: a */
    public void m24903a(VersionEvent.C3562a aVar) {
        if (aVar.m12815a()) {
            this.f5585p.setVisibility(0);
        } else {
            this.f5585p.setVisibility(8);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(final View view) {
        int id = view.getId();
        if (id == this.f5576g.getId()) {
            RegisterActivity.m18306a(getContext());
        } else if (id == this.f5589t.getId()) {
            String e = PreSetListManager.m12116a().m12111e();
            AdResultInfoItem adResultInfoItem = new AdResultInfoItem();
            adResultInfoItem.Title = "FAQ";
            adResultInfoItem.ExecArgs = e;
            NRZSWebviewActivity.m25004a(getContext(), 99, 1, adResultInfoItem);
        } else if (id == this.f5587r.getId() || id == this.f5582m.getId() || id == this.f5595z.getId()) {
            boolean f = PreSetListManager.m12116a().m12110f();
            if (!LoginManager.m12620d().m12606r()) {
                RouterUtils.toLogin(1, 0);
            } else if (f) {
                new DialogC5331eu(getContext()).show();
            } else {
                EventCollectManager.m12642a().m12640a(getContext(), "我的充值续费", "我的充值续费", EventConstants.f16447o);
                m24913a(1);
            }
        } else if (id == this.f5588s.getId()) {
            if (LoginManager.m12620d().m12606r()) {
                PurchasedAssistActivyt.m24992a(getContext());
            } else {
                RouterUtils.toLogin(1, 0);
            }
        } else if (id == this.f5590u.getId()) {
            FloatWindowManager.m12303a(view.getContext(), true, new FloatWindowManager.AbstractC3717a() { // from class: com.angel.nrzs.ui.fragment.PersonFragment.12
                @Override // p110z1.FloatWindowManager.AbstractC3717a
                /* renamed from: a */
                public void mo12295a(int i) {
                    if (i == -1) {
                        ToastUtils.m23030a("权限出错");
                    } else if (i == 1) {
                        new FtCheckFloatingDialog(view.getContext(), 1).show();
                    } else if (i == 2) {
                        new FtCheckFloatingDialog(view.getContext(), 2).show();
                    } else if (i == 3) {
                        new FtCheckFloatingDialog(view.getContext(), 3).show();
                    } else if (i == 4) {
                        new FtCheckFloatingDialog(view.getContext(), 4).show();
                    } else {
                        aqg.m11586a(view.getContext().getApplicationContext(), "您已经设置过权限");
                    }
                }
            });
        } else if (id == this.f5591v.getId()) {
            if (NRZSLocalConfig.m12512d()) {
                ToastUtils.m23021b("该版本仅支持root模式，无需设置");
            } else if (LoginManager.m12620d().m12606r()) {
                ScriptModelSwapDialog.m2997a(getContext());
            } else {
                RouterUtils.toLogin(1, 0);
            }
        } else if (id == this.f5592w.getId()) {
            if (LoginManager.m12620d().m12606r()) {
                new DialogC5331eu(getContext()).show();
            } else {
                RouterUtils.toLogin(1, 0);
            }
        } else if (id == this.f5579j.getId()) {
            try {
                NRZSCommonManager.m11698b().m11699a(getActivity(), "退出，请稍等");
                if (this.f5586q == null) {
                    this.f5586q = new UserRepository();
                }
                LoginOutReg loginOutReg = new LoginOutReg();
                loginOutReg.UserId = LoginManager.m12620d().m12630a().UserID;
                loginOutReg.SessionId = LoginManager.m12620d().m12630a().UserSessionId;
                this.f5586q.m12543a(loginOutReg, this.f5574P, this.f5573O);
            } catch (Exception e2) {
                e2.printStackTrace();
                NRZSCommonManager.m11698b().m11700a();
            }
        } else if (id == this.f5563E.getId()) {
            VersionManager.m3095a(getActivity()).m3086a(true, false);
        } else if (id == this.f5584o.getId()) {
            TipsPopWindow.m2980a(getContext(), view);
        } else if (id == this.f5562D.getId()) {
            if (LoginManager.m12620d().m12606r()) {
                EventCollectManager.m12642a().m12640a(getContext(), "我的充值续费", "我的充值续费", EventConstants.f16447o);
                m24913a(3);
                return;
            }
            RouterUtils.toLogin(1, 0);
        } else if (id == this.f5561C.getId()) {
            if (!LoginManager.m12620d().m12606r()) {
                RouterUtils.toLogin(1, 0);
            } else {
                m24913a(1);
            }
        } else if (id == this.f5593x.getId()) {
            if (!LoginManager.m12620d().m12606r()) {
                RouterUtils.toLogin(1, 0);
            } else {
                m24895g();
            }
        } else if (id != this.f5594y.getId()) {
        } else {
            if (!LoginManager.m12620d().m12606r()) {
                RouterUtils.toLogin(1, 0);
            } else {
                m24900c();
            }
        }
    }

    /* renamed from: c */
    private void m24900c() {
        RouterUtils.toQuesttion();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m24913a(int i) {
        if (ChannelDataManager.m12655a().m12650d()) {
            ProviderFactory.createCarkey().showdialog(getContext());
            return;
        }
        AdResultInfoItem adResultInfoItem = new AdResultInfoItem();
        if (i == 2) {
            adResultInfoItem.Title = "支持鸟人送金币";
        } else {
            adResultInfoItem.Title = "购买续费";
        }
        adResultInfoItem.ExecArgs = HttpVal.f16516c;
        NRZSWebviewActivity.m25006a(getContext(), 0, 1, i, adResultInfoItem);
    }

    /* renamed from: g */
    private void m24895g() {
        RouterUtils.toKickOut(LoginManager.m12620d().m12614j(), false);
    }

    /* renamed from: h */
    private void m24893h() {
        try {
            UserInfoManager.m12594a().m12588c(new DoneCallback<UserInfo>() { // from class: com.angel.nrzs.ui.fragment.PersonFragment.2
                /* renamed from: a */
                public void onDone(UserInfo userInfo) {
                    if (userInfo != null) {
                        if (LoginManager.m12620d().m12630a() != null) {
                            LoginManager.m12620d().m12630a().IsVip = userInfo.IsVip;
                            LoginManager.m12620d().m12630a().VIPExpireTime = userInfo.VIPExpireTime;
                            LoginManager.m12620d().m12630a().SGB = userInfo.SGB;
                            LoginManager.m12620d().m12630a().GoldCoinNum = userInfo.GoldCoinNum;
                            LoginManager.m12620d().m12630a().OpenNum = userInfo.OpenNum;
                            LoginManager.m12620d().m12630a().GoldExpireTime = userInfo.GoldExpireTime;
                            LoginManager.m12620d().m12629a(userInfo.AscriptionAuthorId);
                        }
                        PersonFragment.this.f5571M = userInfo;
                        PersonFragment.this.f5572N.sendEmptyMessage(1);
                        EventBus.m3448a().m3427d(new LoginEvent.C3560a(1));
                        PersonFragment.this.m24891i();
                    }
                    if (PersonFragment.this.getContext() != null) {
                        GlideImageUtils.m11880a(PersonFragment.this.f5583n, PersonFragment.this.getContext(), (int) C0692R.C0693drawable.f2238m0, LoginManager.m12620d().m12630a().HeadImgPath);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m24908a(UserInfo userInfo) {
        if (userInfo.IsVip != 0) {
            this.f5570L.setVisibility(8);
            this.f5569K.setVisibility(0);
            this.f5560B.setText(aof.m12120b(userInfo.VIPExpireTime));
            TextView textView = this.f5559A;
            textView.setText(userInfo.OpenNum + "开");
        } else {
            this.f5570L.setVisibility(0);
            this.f5569K.setVisibility(8);
            this.f5582m.setText("未开通");
        }
        this.f5581l.setText(userInfo.getformatSGB());
        TextView textView2 = this.f5564F;
        textView2.setText("" + userInfo.GoldCoinNum);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: i */
    public void m24891i() {
        if (LoginManager.m12620d().m12611m()) {
            String a = TimeUtils.m23111a(new SimpleDateFormat(DateUtils.ISO8601_DATE_PATTERN));
            if (!a.equals(apf.m11837b(DataApp.m18939d().m18947a(), ShareVal.f16591a, ShareVal.f16575F, ""))) {
                new GoldExpireDialog(getContext()).show();
                apf.m11842a(DataApp.m18939d().m18947a(), ShareVal.f16591a, ShareVal.f16575F, a);
            }
        }
    }

    /* renamed from: a */
    public void m24914a() {
        Log.e("datalal", "调用");
        new Thread(new Runnable() { // from class: com.angel.nrzs.ui.fragment.PersonFragment.3
            @Override // java.lang.Runnable
            public void run() {
                Log.e("datalal", "走run");
                if (LoginManager.m12620d().m12606r()) {
                    Log.e("datalal", "开始上传");
                    try {
                        UplaodAppRequestInfo uplaodAppRequestInfo = new UplaodAppRequestInfo();
                        uplaodAppRequestInfo.UserId = LoginManager.m12620d().m12614j();
                        uplaodAppRequestInfo.UploadStatue = 2;
                        uplaodAppRequestInfo.LocalAppFileMD5 = aoy.m11887a(new File(NRZSFileConfig.f16545c + "hh.apk"));
                        PersonFragment personFragment = PersonFragment.this;
                        String str = HttpVal.f16509Y;
                        personFragment.m24907a(str, NRZSFileConfig.f16545c + "hh.apk", uplaodAppRequestInfo);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m24907a(String str, String str2, UplaodAppRequestInfo uplaodAppRequestInfo) throws Exception {
        new HashMap();
        List<String> sigin = uplaodAppRequestInfo.getSigin(str, uplaodAppRequestInfo.getNoencodeMapProperty());
        if (sigin != null && sigin.size() >= 2) {
            File file = new File(str2);
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            MultipartBody.Builder addFormDataPart = new MultipartBody.Builder().setType(MultipartBody.FORM).addFormDataPart("UserId", String.valueOf(LoginManager.m12620d().m12614j()));
            MultipartBody.Builder addFormDataPart2 = addFormDataPart.addFormDataPart("UploadStatue", uplaodAppRequestInfo.UploadStatue + "").addFormDataPart("LocalAppFileMD5", uplaodAppRequestInfo.LocalAppFileMD5).addFormDataPart("a", uplaodAppRequestInfo.f10610a).addFormDataPart("b", uplaodAppRequestInfo.f10614b).addFormDataPart("ab", uplaodAppRequestInfo.f10612ab).addFormDataPart("bc", uplaodAppRequestInfo.f10615bc);
            MultipartBody.Builder addFormDataPart3 = addFormDataPart2.addFormDataPart("d", uplaodAppRequestInfo.f10616d + "");
            MultipartBody.Builder addFormDataPart4 = addFormDataPart3.addFormDataPart("de", uplaodAppRequestInfo.f10617de + "").addFormDataPart(ServiceManagerNative.f10497VS, uplaodAppRequestInfo.f10621vs);
            MultipartBody.Builder addFormDataPart5 = addFormDataPart4.addFormDataPart("vc", uplaodAppRequestInfo.f10620vc + "");
            MultipartBody.Builder addFormDataPart6 = addFormDataPart5.addFormDataPart("pg", uplaodAppRequestInfo.f10618pg + "").addFormDataPart("pv", uplaodAppRequestInfo.f10619pv).addFormDataPart("ad", uplaodAppRequestInfo.f10613ad);
            MultipartBody.Builder addFormDataPart7 = addFormDataPart6.addFormDataPart("aa", uplaodAppRequestInfo.f10611aa + "");
            MultipartBody build = addFormDataPart7.addFormDataPart("isVa", uplaodAppRequestInfo.isVa + "").addFormDataPart("R", sigin.get(0)).addFormDataPart("Sign", sigin.get(1)).addFormDataPart("file", file.getName(), RequestBody.create(MediaType.parse("multipart/form-data"), file)).build();
            Request.Builder builder2 = new Request.Builder();
            builder.connectTimeout(acf.f15175N, TimeUnit.SECONDS).writeTimeout(acf.f15175N, TimeUnit.SECONDS).build().newCall(builder2.header("Authorization", "Client-ID " + UUID.randomUUID()).url(str).post(build).build()).enqueue(new Callback() { // from class: com.angel.nrzs.ui.fragment.PersonFragment.4
                @Override // okhttp3.Callback
                public void onFailure(Call call, IOException iOException) {
                    Log.e("上传", "失败");
                }

                @Override // okhttp3.Callback
                public void onResponse(Call call, Response response) throws IOException {
                    Log.e("上传", "成功");
                }
            });
        }
    }
}
