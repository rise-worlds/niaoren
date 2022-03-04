package com.nrzs.user.ui.fragment;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.nrzs.data.base.BaseResponse;
import com.nrzs.data.user.bean.request.LoginRInfo;
import com.nrzs.data.user.bean.request.RegSmsinfo;
import com.nrzs.data.user.bean.request.Reginfo;
import com.nrzs.data.user.bean.respond.LoginResultV1Info;
import com.nrzs.http.ThreadCallback;
import com.nrzs.http.UICallback;
import com.nrzs.user.C2222R;
import com.nrzs.user.ui.activity.RegisterActivity;
import com.nrzs.user.ui.base.UserBaseFragment;
import com.stripe.android.view.ShippingInfoWidget;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import p110z1.ActionVal;
import p110z1.Gson;
import p110z1.LoginManager;
import p110z1.NRZSCommonManager;
import p110z1.RegexUtil;
import p110z1.TypeToken;
import p110z1.UserRepository;
import p110z1.apa;
import p110z1.aqg;

/* renamed from: com.nrzs.user.ui.fragment.PhoneRegisterFragment2 */
/* loaded from: classes2.dex */
public class PhoneRegisterFragment2 extends UserBaseFragment {

    /* renamed from: b */
    private String f11603b;

    /* renamed from: c */
    private TextView f11604c;

    /* renamed from: g */
    private EditText f11605g;

    /* renamed from: h */
    private EditText f11606h;

    /* renamed from: i */
    private EditText f11607i;

    /* renamed from: j */
    private TextView f11608j;

    /* renamed from: k */
    private TextView f11609k;

    /* renamed from: l */
    private TextView f11610l;

    /* renamed from: m */
    private TextView f11611m;

    /* renamed from: n */
    private UserRepository f11612n;

    /* renamed from: o */
    private LinearLayout f11613o;

    /* renamed from: p */
    private int f11614p = 60;

    /* renamed from: q */
    private Gson f11615q = new Gson();

    /* renamed from: r */
    private Timer f11616r = new Timer();

    /* renamed from: a */
    Handler f11602a = new Handler() { // from class: com.nrzs.user.ui.fragment.PhoneRegisterFragment2.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            PhoneRegisterFragment2.m18220a(PhoneRegisterFragment2.this);
            if (message.what == 1) {
                TextView textView = PhoneRegisterFragment2.this.f11609k;
                textView.setText("重新发送（" + PhoneRegisterFragment2.this.f11614p + "）");
                if (PhoneRegisterFragment2.this.f11614p == 0) {
                    PhoneRegisterFragment2.this.f11609k.setVisibility(8);
                    PhoneRegisterFragment2.this.f11608j.setVisibility(0);
                    PhoneRegisterFragment2.this.f11616r.cancel();
                    PhoneRegisterFragment2.this.f11616r = new Timer();
                }
            }
            super.handleMessage(message);
        }
    };

    /* renamed from: s */
    private ThreadCallback f11617s = new ThreadCallback<BaseResponse<Object>, String>() { // from class: com.nrzs.user.ui.fragment.PhoneRegisterFragment2.3
        /* renamed from: a */
        public BaseResponse<Object> onResponse(String str) {
            BaseResponse<Object> baseResponse = (BaseResponse) apa.m11877a(str, new TypeToken<BaseResponse<Object>>() { // from class: com.nrzs.user.ui.fragment.PhoneRegisterFragment2.3.1
            });
            if (baseResponse != null) {
                return baseResponse;
            }
            return null;
        }
    };

    /* renamed from: t */
    private UICallback f11618t = new UICallback<BaseResponse<Object>>() { // from class: com.nrzs.user.ui.fragment.PhoneRegisterFragment2.4
        @Override // com.nrzs.http.UICallback
        /* renamed from: a */
        public void mo3021a(Throwable th) {
            aqg.m11586a(PhoneRegisterFragment2.this.getContext(), "发送验证码失败");
        }

        /* renamed from: a  reason: avoid collision after fix types in other method */
        public void mo3022a(BaseResponse<Object> baseResponse) {
            if (baseResponse != null && baseResponse.code == 0) {
                aqg.m11586a(PhoneRegisterFragment2.this.getContext(), baseResponse.msg);
            }
        }
    };

    /* renamed from: u */
    private ThreadCallback f11619u = new ThreadCallback<BaseResponse<LoginResultV1Info>, String>() { // from class: com.nrzs.user.ui.fragment.PhoneRegisterFragment2.5
        /* renamed from: a */
        public BaseResponse<LoginResultV1Info> onResponse(String str) {
            BaseResponse<LoginResultV1Info> baseResponse = (BaseResponse) apa.m11877a(str, new TypeToken<BaseResponse<LoginResultV1Info>>() { // from class: com.nrzs.user.ui.fragment.PhoneRegisterFragment2.5.1
            });
            if (baseResponse != null) {
                return baseResponse;
            }
            return null;
        }
    };

    /* renamed from: v */
    private UICallback f11620v = new UICallback<BaseResponse<LoginResultV1Info>>() { // from class: com.nrzs.user.ui.fragment.PhoneRegisterFragment2.6
        @Override // com.nrzs.http.UICallback
        /* renamed from: a */
        public void mo3021a(Throwable th) {
            NRZSCommonManager.m11698b().m11700a();
            aqg.m11586a(PhoneRegisterFragment2.this.getContext(), "注册失败");
        }

        /* renamed from: a  reason: avoid collision after fix types in other method */
        public void mo3022a(BaseResponse<LoginResultV1Info> baseResponse) {
            if (baseResponse == null) {
                NRZSCommonManager.m11698b().m11700a();
            } else if (baseResponse.code == 0) {
                NRZSCommonManager.m11698b().m11700a();
                aqg.m11586a(PhoneRegisterFragment2.this.getContext(), baseResponse.msg);
            } else {
                PhoneRegisterFragment2.this.m18207g();
            }
        }
    };

    /* renamed from: w */
    private UICallback<BaseResponse<LoginResultV1Info>> f11621w = new UICallback<BaseResponse<LoginResultV1Info>>() { // from class: com.nrzs.user.ui.fragment.PhoneRegisterFragment2.7
        @Override // com.nrzs.http.UICallback
        /* renamed from: a */
        public void mo3021a(Throwable th) {
            NRZSCommonManager.m11698b().m11700a();
            aqg.m11586a(PhoneRegisterFragment2.this.getContext(), "登录失败");
        }

        /* renamed from: a  reason: avoid collision after fix types in other method */
        public void mo3022a(BaseResponse<LoginResultV1Info> baseResponse) {
            if (baseResponse != null) {
                if (baseResponse.code == 0) {
                    aqg.m11586a(PhoneRegisterFragment2.this.getContext(), baseResponse.msg);
                } else if (baseResponse.code != -1) {
                    LoginManager.m12620d().m12625a(baseResponse.data.AutoLoginToken, baseResponse.data.UserInfo, baseResponse.data.AscriptionAuthorId, baseResponse.data.UploadLocalAppPackage);
                    try {
                        PhoneRegisterFragment2.this.startActivity(new Intent(ActionVal.f16464a));
                        ((RegisterActivity) PhoneRegisterFragment2.this.getContext()).finish();
                    } catch (ActivityNotFoundException unused) {
                        Log.d("FirstActivity.this", "找不到该活动");
                    }
                } else if (baseResponse.data != null) {
                    LoginManager.m12620d().m12626a(baseResponse.data.AutoLoginToken, baseResponse.data.UserId);
                }
            }
            NRZSCommonManager.m11698b().m11700a();
        }
    };

    /* renamed from: x */
    private ThreadCallback<BaseResponse<LoginResultV1Info>, String> f11622x = new ThreadCallback<BaseResponse<LoginResultV1Info>, String>() { // from class: com.nrzs.user.ui.fragment.PhoneRegisterFragment2.8
        /* renamed from: a */
        public BaseResponse<LoginResultV1Info> onResponse(String str) {
            BaseResponse<LoginResultV1Info> baseResponse = (BaseResponse) apa.m11877a(str, new TypeToken<BaseResponse<LoginResultV1Info>>() { // from class: com.nrzs.user.ui.fragment.PhoneRegisterFragment2.8.1
            });
            if (baseResponse != null) {
                return baseResponse;
            }
            return null;
        }
    };

    /* renamed from: a */
    static /* synthetic */ int m18220a(PhoneRegisterFragment2 phoneRegisterFragment2) {
        int i = phoneRegisterFragment2.f11614p;
        phoneRegisterFragment2.f11614p = i - 1;
        return i;
    }

    /* renamed from: a */
    public static PhoneRegisterFragment2 m18217a(String str) {
        PhoneRegisterFragment2 phoneRegisterFragment2 = new PhoneRegisterFragment2();
        Bundle bundle = new Bundle();
        bundle.putString(ShippingInfoWidget.f12563f, str);
        phoneRegisterFragment2.setArguments(bundle);
        return phoneRegisterFragment2;
    }

    /* renamed from: com.nrzs.user.ui.fragment.PhoneRegisterFragment2$a */
    /* loaded from: classes2.dex */
    class C2331a extends TimerTask {
        C2331a() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            Message message = new Message();
            message.what = 1;
            PhoneRegisterFragment2.this.f11602a.sendMessage(message);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: g */
    public void m18207g() {
        try {
            LoginRInfo loginRInfo = new LoginRInfo();
            loginRInfo.UserName = this.f11603b;
            loginRInfo.setPassWord(this.f11605g.getText().toString().trim());
            loginRInfo.uuid = UUID.randomUUID().toString();
            this.f11612n.m12542a(loginRInfo, this.f11621w, this.f11622x);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.nrzs.libcommon.BaseFragment
    /* renamed from: b */
    protected int mo18216b() {
        return C2222R.layout.fragment_phone_register_layout2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.nrzs.libcommon.BaseFragment
    /* renamed from: a */
    public void mo18221a(View view) {
        super.mo18221a(view);
        this.f11604c = (TextView) view.findViewById(C2222R.C2224id.nrzs_user_tv_regist_phonenum);
        this.f11605g = (EditText) view.findViewById(C2222R.C2224id.nrzs_user_tv_regist_passwort);
        this.f11606h = (EditText) view.findViewById(C2222R.C2224id.nrzs_user_tv_regist_passwortagain);
        this.f11607i = (EditText) view.findViewById(C2222R.C2224id.nrzs_user_tv_regist_verconde);
        this.f11608j = (TextView) view.findViewById(C2222R.C2224id.nrzs_user_tv_regist_subit);
        this.f11609k = (TextView) view.findViewById(C2222R.C2224id.nrzs_user_tv_regist_cxsubit);
        this.f11610l = (TextView) view.findViewById(C2222R.C2224id.nrzs_user_tv_regist_veritecode);
        this.f11611m = (TextView) view.findViewById(C2222R.C2224id.nrzs_user_tv_regist_success);
        this.f11613o = (LinearLayout) view.findViewById(C2222R.C2224id.nrzs_user_alter_layout);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.nrzs.libcommon.BaseFragment
    /* renamed from: d */
    public void mo18212d() {
        super.mo18212d();
        char[] charArray = this.f11603b.toCharArray();
        charArray[4] = '*';
        charArray[5] = '*';
        charArray[6] = '*';
        charArray[3] = '*';
        this.f11604c.setText(String.valueOf(charArray));
        this.f11616r.schedule(new C2331a(), 1000L, 1000L);
        this.f11612n = new UserRepository();
        m18214c();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.nrzs.libcommon.BaseFragment
    /* renamed from: e */
    public void mo18210e() {
        super.mo18210e();
        this.f11608j.setOnClickListener(new View.OnClickListener() { // from class: com.nrzs.user.ui.fragment.PhoneRegisterFragment2.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PhoneRegisterFragment2.this.f11614p = 60;
                PhoneRegisterFragment2.this.f11608j.setVisibility(8);
                PhoneRegisterFragment2.this.f11609k.setVisibility(0);
                TextView textView = PhoneRegisterFragment2.this.f11609k;
                textView.setText("重新发送（" + PhoneRegisterFragment2.this.f11614p + "）");
                PhoneRegisterFragment2.this.f11616r.schedule(new C2331a(), 1000L, 1000L);
                PhoneRegisterFragment2.this.m18214c();
            }
        });
        this.f11613o.setOnClickListener(new View.OnClickListener() { // from class: com.nrzs.user.ui.fragment.PhoneRegisterFragment2.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
            }
        });
        this.f11611m.setOnClickListener(new View.OnClickListener() { // from class: com.nrzs.user.ui.fragment.PhoneRegisterFragment2.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (TextUtils.isEmpty(PhoneRegisterFragment2.this.f11607i.getText().toString().trim())) {
                    aqg.m11586a(PhoneRegisterFragment2.this.getContext(), "请输入正确的验证码");
                } else if (TextUtils.isEmpty(PhoneRegisterFragment2.this.f11605g.getText().toString().trim())) {
                    aqg.m11586a(PhoneRegisterFragment2.this.getContext(), "请输入6-16密码，字符、数字或字符");
                } else if (!PhoneRegisterFragment2.this.f11605g.getText().toString().trim().equals(PhoneRegisterFragment2.this.f11606h.getText().toString().trim())) {
                    aqg.m11586a(PhoneRegisterFragment2.this.getContext(), "两次密码不同，请重新输入");
                } else if (PhoneRegisterFragment2.this.f11605g.getText().toString().trim().length() < 6 || PhoneRegisterFragment2.this.f11605g.getText().toString().trim().length() > 16) {
                    aqg.m11586a(PhoneRegisterFragment2.this.getContext(), "请输入6-16密码，字符、数字或字符");
                } else {
                    if (!RegexUtil.m11592b(RegexUtil.f17300a, PhoneRegisterFragment2.this.f11605g.getText().toString().trim().toString())) {
                        aqg.m11586a(PhoneRegisterFragment2.this.getContext(), "请输入6-16密码，字符、数字或字符");
                    }
                    if (!RegexUtil.m11592b(RegexUtil.f17300a, PhoneRegisterFragment2.this.f11606h.getText().toString().trim().toString())) {
                        aqg.m11586a(PhoneRegisterFragment2.this.getContext(), "请输入6-16密码，字符、数字或字符");
                    }
                    try {
                        NRZSCommonManager.m11698b().m11699a(PhoneRegisterFragment2.this.getActivity(), "注册账号，请稍等");
                        Reginfo reginfo = new Reginfo();
                        reginfo.Tel = PhoneRegisterFragment2.this.f11603b;
                        reginfo.CheckCode = PhoneRegisterFragment2.this.f11607i.getText().toString().trim();
                        reginfo.setPassWord(PhoneRegisterFragment2.this.f11605g.getText().toString().trim());
                        PhoneRegisterFragment2.this.f11612n.m12540a(reginfo, PhoneRegisterFragment2.this.f11620v, PhoneRegisterFragment2.this.f11619u);
                    } catch (Exception e) {
                        NRZSCommonManager.m11698b().m11700a();
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    /* renamed from: c */
    public void m18214c() {
        try {
            RegSmsinfo regSmsinfo = new RegSmsinfo();
            regSmsinfo.Tel = this.f11603b;
            this.f11612n.m12541a(regSmsinfo, this.f11618t, this.f11617s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.nrzs.libcommon.BaseFragment, android.support.v4.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            this.f11603b = getArguments().getString(ShippingInfoWidget.f12563f);
        }
    }
}
