package com.nrzs.user.p071ui.fragment;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.nrzs.data.base.BaseResponse;
import com.nrzs.data.user.bean.request.AlterPwinfm;
import com.nrzs.data.user.bean.request.LoginRInfo;
import com.nrzs.data.user.bean.request.RegSmsinfo;
import com.nrzs.data.user.bean.respond.LoginResultV1Info;
import com.nrzs.http.ThreadCallback;
import com.nrzs.http.UICallback;
import com.nrzs.user.C2222R;
import com.nrzs.user.p071ui.activity.AlterPasswordActivity;
import com.nrzs.user.p071ui.base.UserBaseFragment;
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

/* renamed from: com.nrzs.user.ui.fragment.AlterpassWordFragmnet2 */
/* loaded from: classes2.dex */
public class AlterpassWordFragmnet2 extends UserBaseFragment {

    /* renamed from: b */
    private String f11535b;

    /* renamed from: c */
    private TextView f11536c;

    /* renamed from: g */
    private EditText f11537g;

    /* renamed from: h */
    private EditText f11538h;

    /* renamed from: i */
    private EditText f11539i;

    /* renamed from: j */
    private TextView f11540j;

    /* renamed from: k */
    private TextView f11541k;

    /* renamed from: l */
    private TextView f11542l;

    /* renamed from: m */
    private TextView f11543m;

    /* renamed from: n */
    private UserRepository f11544n;

    /* renamed from: o */
    private LinearLayout f11545o;

    /* renamed from: p */
    private int f11546p = 60;

    /* renamed from: q */
    private Gson f11547q = new Gson();

    /* renamed from: r */
    private Timer f11548r = new Timer();

    /* renamed from: a */
    Handler f11534a = new Handler() { // from class: com.nrzs.user.ui.fragment.AlterpassWordFragmnet2.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            AlterpassWordFragmnet2.m18269a(AlterpassWordFragmnet2.this);
            if (message.what == 1) {
                TextView textView = AlterpassWordFragmnet2.this.f11541k;
                textView.setText("重新发送（" + AlterpassWordFragmnet2.this.f11546p + "）");
                if (AlterpassWordFragmnet2.this.f11546p == 0) {
                    AlterpassWordFragmnet2.this.f11541k.setVisibility(8);
                    AlterpassWordFragmnet2.this.f11540j.setVisibility(0);
                    AlterpassWordFragmnet2.this.f11548r.cancel();
                    AlterpassWordFragmnet2.this.f11548r = new Timer();
                }
            }
            super.handleMessage(message);
        }
    };

    /* renamed from: s */
    private ThreadCallback f11549s = new ThreadCallback<BaseResponse<Object>, String>() { // from class: com.nrzs.user.ui.fragment.AlterpassWordFragmnet2.4
        /* renamed from: a */
        public BaseResponse<Object> onResponse(String str) {
            BaseResponse<Object> baseResponse = (BaseResponse) apa.m11877a(str, new TypeToken<BaseResponse<Object>>() { // from class: com.nrzs.user.ui.fragment.AlterpassWordFragmnet2.4.1
            });
            if (baseResponse != null) {
                return baseResponse;
            }
            return null;
        }
    };

    /* renamed from: t */
    private UICallback f11550t = new UICallback<BaseResponse<Object>>() { // from class: com.nrzs.user.ui.fragment.AlterpassWordFragmnet2.5
        @Override // com.nrzs.http.UICallback
        /* renamed from: a */
        public void mo3021a(Throwable th) {
            aqg.m11586a(AlterpassWordFragmnet2.this.getContext(), "发送验证码失败");
        }

        /* renamed from: a  reason: avoid collision after fix types in other method */
        public void mo3022a(BaseResponse<Object> baseResponse) {
            if (baseResponse != null && baseResponse.code == 0) {
                aqg.m11586a(AlterpassWordFragmnet2.this.getContext(), baseResponse.msg);
            }
        }
    };

    /* renamed from: u */
    private ThreadCallback f11551u = new ThreadCallback<BaseResponse<Object>, String>() { // from class: com.nrzs.user.ui.fragment.AlterpassWordFragmnet2.6
        /* renamed from: a */
        public BaseResponse<Object> onResponse(String str) {
            BaseResponse<Object> baseResponse = (BaseResponse) apa.m11877a(str, new TypeToken<BaseResponse<Object>>() { // from class: com.nrzs.user.ui.fragment.AlterpassWordFragmnet2.6.1
            });
            if (baseResponse != null) {
                return baseResponse;
            }
            return null;
        }
    };

    /* renamed from: v */
    private UICallback f11552v = new UICallback<BaseResponse<Object>>() { // from class: com.nrzs.user.ui.fragment.AlterpassWordFragmnet2.7
        @Override // com.nrzs.http.UICallback
        /* renamed from: a */
        public void mo3021a(Throwable th) {
            NRZSCommonManager.m11698b().m11700a();
            aqg.m11586a(AlterpassWordFragmnet2.this.getContext(), "修改密码失败");
        }

        /* renamed from: a  reason: avoid collision after fix types in other method */
        public void mo3022a(BaseResponse<Object> baseResponse) {
            if (baseResponse == null) {
                NRZSCommonManager.m11698b().m11700a();
            } else if (baseResponse.code == 0) {
                NRZSCommonManager.m11698b().m11700a();
                aqg.m11586a(AlterpassWordFragmnet2.this.getContext(), baseResponse.msg);
            } else {
                aqg.m11586a(AlterpassWordFragmnet2.this.getContext(), "修改密码成功");
                AlterpassWordFragmnet2.this.m18259g();
            }
        }
    };

    /* renamed from: w */
    private UICallback<BaseResponse<LoginResultV1Info>> f11553w = new UICallback<BaseResponse<LoginResultV1Info>>() { // from class: com.nrzs.user.ui.fragment.AlterpassWordFragmnet2.8
        @Override // com.nrzs.http.UICallback
        /* renamed from: a */
        public void mo3021a(Throwable th) {
            NRZSCommonManager.m11698b().m11700a();
            aqg.m11586a(AlterpassWordFragmnet2.this.getContext(), "登录失败");
        }

        /* renamed from: a  reason: avoid collision after fix types in other method */
        public void mo3022a(BaseResponse<LoginResultV1Info> baseResponse) {
            if (baseResponse != null) {
                if (baseResponse.code == 0) {
                    aqg.m11586a(AlterpassWordFragmnet2.this.getContext(), baseResponse.msg);
                } else if (baseResponse.code != -1) {
                    LoginManager.m12620d().m12625a(baseResponse.data.AutoLoginToken, baseResponse.data.UserInfo, baseResponse.data.AscriptionAuthorId, baseResponse.data.UploadLocalAppPackage);
                    try {
                        AlterpassWordFragmnet2.this.startActivity(new Intent(ActionVal.f16464a));
                        ((AlterPasswordActivity) AlterpassWordFragmnet2.this.getContext()).finish();
                    } catch (ActivityNotFoundException unused) {
                    }
                } else if (baseResponse.data != null) {
                    LoginManager.m12620d().m12626a(baseResponse.data.AutoLoginToken, baseResponse.data.UserId);
                }
            }
            NRZSCommonManager.m11698b().m11700a();
        }
    };

    /* renamed from: x */
    private ThreadCallback<BaseResponse<LoginResultV1Info>, String> f11554x = new ThreadCallback<BaseResponse<LoginResultV1Info>, String>() { // from class: com.nrzs.user.ui.fragment.AlterpassWordFragmnet2.9
        /* renamed from: a */
        public BaseResponse<LoginResultV1Info> onResponse(String str) {
            BaseResponse<LoginResultV1Info> baseResponse = (BaseResponse) apa.m11877a(str, new TypeToken<BaseResponse<LoginResultV1Info>>() { // from class: com.nrzs.user.ui.fragment.AlterpassWordFragmnet2.9.1
            });
            if (baseResponse != null) {
                return baseResponse;
            }
            return null;
        }
    };

    /* renamed from: a */
    static /* synthetic */ int m18269a(AlterpassWordFragmnet2 alterpassWordFragmnet2) {
        int i = alterpassWordFragmnet2.f11546p;
        alterpassWordFragmnet2.f11546p = i - 1;
        return i;
    }

    /* renamed from: a */
    public static AlterpassWordFragmnet2 m18266a(String str) {
        AlterpassWordFragmnet2 alterpassWordFragmnet2 = new AlterpassWordFragmnet2();
        Bundle bundle = new Bundle();
        bundle.putString(ShippingInfoWidget.f12563f, str);
        alterpassWordFragmnet2.setArguments(bundle);
        return alterpassWordFragmnet2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: g */
    public void m18259g() {
        try {
            LoginRInfo loginRInfo = new LoginRInfo();
            loginRInfo.UserName = this.f11535b;
            loginRInfo.setPassWord(this.f11537g.getText().toString().trim());
            loginRInfo.uuid = UUID.randomUUID().toString();
            this.f11544n.m12542a(loginRInfo, this.f11553w, this.f11554x);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.nrzs.libcommon.BaseFragment
    /* renamed from: b */
    protected int mo18216b() {
        return C2222R.layout.fragment_phone_alterpw_layout2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.nrzs.libcommon.BaseFragment
    /* renamed from: a */
    public void mo18221a(View view) {
        super.mo18221a(view);
        this.f11536c = (TextView) view.findViewById(C2222R.C2224id.nrzs_user_tv_alterpw_phonenums);
        this.f11537g = (EditText) view.findViewById(C2222R.C2224id.nrzs_user_tv_alterpw_passwort);
        this.f11538h = (EditText) view.findViewById(C2222R.C2224id.nrzs_user_tv_alterpw_passwortagain);
        this.f11539i = (EditText) view.findViewById(C2222R.C2224id.nrzs_user_tv_alterpw_verconde);
        this.f11540j = (TextView) view.findViewById(C2222R.C2224id.nrzs_user_tv_alter_subit);
        this.f11541k = (TextView) view.findViewById(C2222R.C2224id.nrzs_user_tv_alter_cxsubit);
        this.f11542l = (TextView) view.findViewById(C2222R.C2224id.nrzs_user_tv_regist_veritecode);
        this.f11543m = (TextView) view.findViewById(C2222R.C2224id.nrzs_user_tv_alterpw_success);
        this.f11545o = (LinearLayout) view.findViewById(C2222R.C2224id.nrzs_user_alter_layout);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.nrzs.libcommon.BaseFragment
    /* renamed from: d */
    public void mo18212d() {
        super.mo18212d();
        char[] charArray = this.f11535b.toCharArray();
        charArray[4] = '*';
        charArray[5] = '*';
        charArray[6] = '*';
        charArray[3] = '*';
        this.f11536c.setText(String.valueOf(charArray));
        this.f11548r.schedule(new TimerTask() { // from class: com.nrzs.user.ui.fragment.AlterpassWordFragmnet2.10
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                Message message = new Message();
                message.what = 1;
                AlterpassWordFragmnet2.this.f11534a.sendMessage(message);
            }
        }, 1000L, 1000L);
        this.f11544n = new UserRepository();
        m18264c();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.nrzs.libcommon.BaseFragment
    /* renamed from: e */
    public void mo18210e() {
        super.mo18210e();
        this.f11545o.setOnClickListener(new View.OnClickListener() { // from class: com.nrzs.user.ui.fragment.AlterpassWordFragmnet2.11
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
            }
        });
        this.f11540j.setOnClickListener(new View.OnClickListener() { // from class: com.nrzs.user.ui.fragment.AlterpassWordFragmnet2.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AlterpassWordFragmnet2.this.f11546p = 60;
                AlterpassWordFragmnet2.this.f11540j.setVisibility(8);
                AlterpassWordFragmnet2.this.f11541k.setVisibility(0);
                TextView textView = AlterpassWordFragmnet2.this.f11541k;
                textView.setText("重新发送（" + AlterpassWordFragmnet2.this.f11546p + "）");
                AlterpassWordFragmnet2.this.f11548r.schedule(new TimerTask() { // from class: com.nrzs.user.ui.fragment.AlterpassWordFragmnet2.2.1
                    @Override // java.util.TimerTask, java.lang.Runnable
                    public void run() {
                        Message message = new Message();
                        message.what = 1;
                        AlterpassWordFragmnet2.this.f11534a.sendMessage(message);
                    }
                }, 1000L, 1000L);
                AlterpassWordFragmnet2.this.m18264c();
            }
        });
        this.f11543m.setOnClickListener(new View.OnClickListener() { // from class: com.nrzs.user.ui.fragment.AlterpassWordFragmnet2.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (TextUtils.isEmpty(AlterpassWordFragmnet2.this.f11539i.getText().toString().trim())) {
                    aqg.m11586a(AlterpassWordFragmnet2.this.getContext(), "请输入正确的验证码");
                } else if (TextUtils.isEmpty(AlterpassWordFragmnet2.this.f11537g.getText().toString().trim())) {
                    aqg.m11586a(AlterpassWordFragmnet2.this.getContext(), "请输入6-16密码，字符、数字或字符");
                } else if (!AlterpassWordFragmnet2.this.f11537g.getText().toString().trim().equals(AlterpassWordFragmnet2.this.f11538h.getText().toString().trim())) {
                    aqg.m11586a(AlterpassWordFragmnet2.this.getContext(), "两次密码不同，请重新输入");
                } else if (AlterpassWordFragmnet2.this.f11537g.getText().toString().trim().length() < 6 || AlterpassWordFragmnet2.this.f11537g.getText().toString().trim().length() > 16) {
                    aqg.m11586a(AlterpassWordFragmnet2.this.getContext(), "请输入6-16密码，字符、数字或字符");
                } else {
                    if (!RegexUtil.m11592b(RegexUtil.f17300a, AlterpassWordFragmnet2.this.f11537g.getText().toString().trim().toString())) {
                        aqg.m11586a(AlterpassWordFragmnet2.this.getContext(), "请输入6-16密码，字符、数字或字符");
                    }
                    if (!RegexUtil.m11592b(RegexUtil.f17300a, AlterpassWordFragmnet2.this.f11538h.getText().toString().trim().toString())) {
                        aqg.m11586a(AlterpassWordFragmnet2.this.getContext(), "请输入6-16密码，字符、数字或字符");
                    }
                    try {
                        NRZSCommonManager.m11698b().m11699a(AlterpassWordFragmnet2.this.getActivity(), "修改密码，请稍等");
                        AlterPwinfm alterPwinfm = new AlterPwinfm();
                        alterPwinfm.Tel = AlterpassWordFragmnet2.this.f11535b;
                        alterPwinfm.CheckCode = AlterpassWordFragmnet2.this.f11539i.getText().toString().trim();
                        alterPwinfm.setPassWord(AlterpassWordFragmnet2.this.f11537g.getText().toString().trim());
                        AlterpassWordFragmnet2.this.f11544n.m12546a(alterPwinfm, AlterpassWordFragmnet2.this.f11552v, AlterpassWordFragmnet2.this.f11551u);
                    } catch (Exception e) {
                        NRZSCommonManager.m11698b().m11700a();
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    /* renamed from: c */
    public void m18264c() {
        try {
            RegSmsinfo regSmsinfo = new RegSmsinfo();
            regSmsinfo.Tel = this.f11535b;
            this.f11544n.m12538b(regSmsinfo, this.f11550t, this.f11549s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.nrzs.libcommon.BaseFragment, android.support.p003v4.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            this.f11535b = getArguments().getString(ShippingInfoWidget.f12563f);
        }
    }
}
