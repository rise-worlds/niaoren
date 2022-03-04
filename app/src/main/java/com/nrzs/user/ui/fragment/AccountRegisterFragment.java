package com.nrzs.user.ui.fragment;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.nrzs.data.base.BaseResponse;
import com.nrzs.data.user.bean.request.AccoutnReginfo;
import com.nrzs.data.user.bean.request.LoginRInfo;
import com.nrzs.data.user.bean.respond.LoginResultV1Info;
import com.nrzs.http.ThreadCallback;
import com.nrzs.http.UICallback;
import com.nrzs.libcommon.NRZSViewClickListener;
import com.nrzs.user.C2222R;
import com.nrzs.user.ui.activity.RegisterActivity;
import com.nrzs.user.ui.base.UserBaseFragment;
import com.nrzs.user.ui.view.AuthCodeView;
import java.util.UUID;
import p110z1.ActionVal;
import p110z1.Config;
import p110z1.DevUtil;
import p110z1.Gson;
import p110z1.LoginManager;
import p110z1.NRZSCommonManager;
import p110z1.RegexUtil;
import p110z1.TypeToken;
import p110z1.UserRepository;
import p110z1.apa;
import p110z1.aqg;

/* renamed from: com.nrzs.user.ui.fragment.AccountRegisterFragment */
/* loaded from: classes2.dex */
public class AccountRegisterFragment extends UserBaseFragment {

    /* renamed from: a */
    private EditText f11508a;

    /* renamed from: b */
    private EditText f11509b;

    /* renamed from: c */
    private EditText f11510c;

    /* renamed from: g */
    private EditText f11511g;

    /* renamed from: h */
    private UserRepository f11512h;

    /* renamed from: i */
    private TextView f11513i;

    /* renamed from: j */
    private TextView f11514j;

    /* renamed from: k */
    private AuthCodeView f11515k;

    /* renamed from: l */
    private UserRepository f11516l;

    /* renamed from: m */
    private Gson f11517m = new Gson();

    /* renamed from: n */
    private ThreadCallback f11518n = new ThreadCallback<BaseResponse<LoginResultV1Info>, String>() { // from class: com.nrzs.user.ui.fragment.AccountRegisterFragment.1
        /* renamed from: a */
        public BaseResponse<LoginResultV1Info> onResponse(String str) {
            BaseResponse<LoginResultV1Info> baseResponse = (BaseResponse) apa.m11877a(str, new TypeToken<BaseResponse<LoginResultV1Info>>() { // from class: com.nrzs.user.ui.fragment.AccountRegisterFragment.1.1
            });
            if (baseResponse != null) {
                return baseResponse;
            }
            return null;
        }
    };

    /* renamed from: o */
    private UICallback f11519o = new UICallback<BaseResponse<LoginResultV1Info>>() { // from class: com.nrzs.user.ui.fragment.AccountRegisterFragment.2
        @Override // com.nrzs.http.UICallback
        /* renamed from: a */
        public void mo3021a(Throwable th) {
            AccountRegisterFragment.this.f11514j.setEnabled(true);
            NRZSCommonManager.m11698b().m11700a();
            aqg.m11586a(AccountRegisterFragment.this.getContext(), "注册失败");
        }

        /* renamed from: a  reason: avoid collision after fix types in other method */
        public void mo3022a(BaseResponse<LoginResultV1Info> baseResponse) {
            if (baseResponse == null) {
                AccountRegisterFragment.this.f11514j.setEnabled(true);
                NRZSCommonManager.m11698b().m11700a();
            } else if (baseResponse.code == 0) {
                AccountRegisterFragment.this.f11514j.setEnabled(true);
                NRZSCommonManager.m11698b().m11700a();
                aqg.m11586a(AccountRegisterFragment.this.getContext(), baseResponse.msg);
            } else {
                AccountRegisterFragment.this.m18283g();
            }
        }
    };

    /* renamed from: p */
    private UICallback<BaseResponse<LoginResultV1Info>> f11520p = new UICallback<BaseResponse<LoginResultV1Info>>() { // from class: com.nrzs.user.ui.fragment.AccountRegisterFragment.3
        @Override // com.nrzs.http.UICallback
        /* renamed from: a */
        public void mo3021a(Throwable th) {
            AccountRegisterFragment.this.f11514j.setEnabled(true);
            NRZSCommonManager.m11698b().m11700a();
            aqg.m11586a(AccountRegisterFragment.this.getContext(), "登录失败");
        }

        /* renamed from: a  reason: avoid collision after fix types in other method */
        public void mo3022a(BaseResponse<LoginResultV1Info> baseResponse) {
            if (baseResponse != null) {
                if (baseResponse.code == 0) {
                    aqg.m11586a(AccountRegisterFragment.this.getContext(), baseResponse.msg);
                } else if (baseResponse.code != -1) {
                    LoginManager.m12620d().m12625a(baseResponse.data.AutoLoginToken, baseResponse.data.UserInfo, baseResponse.data.AscriptionAuthorId, baseResponse.data.UploadLocalAppPackage);
                    try {
                        AccountRegisterFragment.this.startActivity(new Intent(ActionVal.f16464a));
                        ((RegisterActivity) AccountRegisterFragment.this.getContext()).finish();
                    } catch (ActivityNotFoundException unused) {
                    }
                } else if (baseResponse.data != null) {
                    LoginManager.m12620d().m12626a(baseResponse.data.AutoLoginToken, baseResponse.data.UserId);
                }
            }
            AccountRegisterFragment.this.f11514j.setEnabled(true);
            NRZSCommonManager.m11698b().m11700a();
        }
    };

    /* renamed from: q */
    private ThreadCallback<BaseResponse<LoginResultV1Info>, String> f11521q = new ThreadCallback<BaseResponse<LoginResultV1Info>, String>() { // from class: com.nrzs.user.ui.fragment.AccountRegisterFragment.4
        /* renamed from: a */
        public BaseResponse<LoginResultV1Info> onResponse(String str) {
            BaseResponse<LoginResultV1Info> baseResponse = (BaseResponse) apa.m11877a(str, new TypeToken<BaseResponse<LoginResultV1Info>>() { // from class: com.nrzs.user.ui.fragment.AccountRegisterFragment.4.1
            });
            if (baseResponse != null) {
                return baseResponse;
            }
            return null;
        }
    };

    /* renamed from: c */
    public static AccountRegisterFragment m18288c() {
        AccountRegisterFragment accountRegisterFragment = new AccountRegisterFragment();
        accountRegisterFragment.setArguments(new Bundle());
        return accountRegisterFragment;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: g */
    public void m18283g() {
        try {
            this.f11512h = new UserRepository();
            LoginRInfo loginRInfo = new LoginRInfo();
            loginRInfo.UserName = this.f11508a.getText().toString().trim();
            loginRInfo.setPassWord(this.f11509b.getText().toString().trim());
            loginRInfo.uuid = UUID.randomUUID().toString();
            this.f11512h.m12542a(loginRInfo, this.f11520p, this.f11521q);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.nrzs.libcommon.BaseFragment
    /* renamed from: b */
    protected int mo18216b() {
        return C2222R.layout.fragment_account_register_layout;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.nrzs.libcommon.BaseFragment
    /* renamed from: a */
    public void mo18221a(View view) {
        super.mo18221a(view);
        this.f11508a = (EditText) view.findViewById(C2222R.C2224id.nrzs_user_et_account);
        this.f11509b = (EditText) view.findViewById(C2222R.C2224id.nrzs_user_et_pwd);
        this.f11510c = (EditText) view.findViewById(C2222R.C2224id.nrzs_user_et_confirmpwd);
        this.f11511g = (EditText) view.findViewById(C2222R.C2224id.nrzs_user_et_account_regist_code);
        this.f11513i = (TextView) view.findViewById(C2222R.C2224id.nrzs_user_tv_account_regist_code_err);
        this.f11514j = (TextView) view.findViewById(C2222R.C2224id.nrzs_user_tv_account_regist_btn);
        this.f11515k = (AuthCodeView) view.findViewById(C2222R.C2224id.verifyCodeTv);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.nrzs.libcommon.BaseFragment
    /* renamed from: d */
    public void mo18212d() {
        super.mo18212d();
        this.f11516l = new UserRepository();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.nrzs.libcommon.BaseFragment
    /* renamed from: e */
    public void mo18210e() {
        super.mo18210e();
        this.f11514j.setOnClickListener(new NRZSViewClickListener() { // from class: com.nrzs.user.ui.fragment.AccountRegisterFragment.5
            @Override // com.nrzs.libcommon.NRZSViewClickListener
            /* renamed from: b */
            protected void mo18274b(View view) {
            }

            @Override // com.nrzs.libcommon.NRZSViewClickListener
            /* renamed from: a */
            protected void mo18275a(View view) {
                AccountRegisterFragment.this.f11514j.setEnabled(false);
                AccountRegisterFragment.this.f11511g.getText().toString();
                if (TextUtils.isEmpty(AccountRegisterFragment.this.f11508a.getText().toString().trim())) {
                    AccountRegisterFragment.this.f11514j.setEnabled(true);
                    aqg.m11586a(AccountRegisterFragment.this.getContext(), "请输入4-20账号，数字或字符");
                } else if (TextUtils.isEmpty(AccountRegisterFragment.this.f11509b.getText().toString().trim())) {
                    AccountRegisterFragment.this.f11514j.setEnabled(true);
                    aqg.m11586a(AccountRegisterFragment.this.getContext(), "请输入6-16密码，字符、数字或字符");
                } else if (AccountRegisterFragment.this.f11509b.getText().toString().trim().length() < 6 || AccountRegisterFragment.this.f11509b.getText().toString().trim().length() > 16) {
                    AccountRegisterFragment.this.f11514j.setEnabled(true);
                    aqg.m11586a(AccountRegisterFragment.this.getContext(), "请输入6-16密码，字符、数字或字符");
                } else if (!AccountRegisterFragment.this.f11509b.getText().toString().trim().equals(AccountRegisterFragment.this.f11510c.getText().toString().trim())) {
                    AccountRegisterFragment.this.f11514j.setEnabled(true);
                    aqg.m11586a(AccountRegisterFragment.this.getContext(), "两次密码不同，请重新输入");
                } else if (AccountRegisterFragment.this.f11508a.getText().toString().trim().length() < 4 || AccountRegisterFragment.this.f11508a.getText().toString().trim().length() > 20) {
                    AccountRegisterFragment.this.f11514j.setEnabled(true);
                    aqg.m11586a(AccountRegisterFragment.this.getContext(), "请输入4-20账号，数字或字符");
                } else {
                    if (!RegexUtil.m11592b(RegexUtil.f17300a, AccountRegisterFragment.this.f11508a.getText().toString().trim().toString())) {
                        AccountRegisterFragment.this.f11514j.setEnabled(true);
                        aqg.m11586a(AccountRegisterFragment.this.getContext(), "请输入4-20账号，数字或字符");
                    }
                    if (!RegexUtil.m11592b(RegexUtil.f17300a, AccountRegisterFragment.this.f11509b.getText().toString().trim().toString())) {
                        AccountRegisterFragment.this.f11514j.setEnabled(true);
                        aqg.m11586a(AccountRegisterFragment.this.getContext(), "请输入6-16密码，字符、数字或字符");
                    }
                    if (!RegexUtil.m11592b(RegexUtil.f17300a, AccountRegisterFragment.this.f11510c.getText().toString().trim().toString())) {
                        AccountRegisterFragment.this.f11514j.setEnabled(true);
                        aqg.m11586a(AccountRegisterFragment.this.getContext(), "请输入6-16密码，字符、数字或字符");
                    }
                    NRZSCommonManager.m11698b().m11699a(AccountRegisterFragment.this.getActivity(), "注册中，请稍等");
                    try {
                        AccoutnReginfo accoutnReginfo = new AccoutnReginfo();
                        accoutnReginfo.username = AccountRegisterFragment.this.f11508a.getText().toString().trim();
                        accoutnReginfo.setPassWord(AccountRegisterFragment.this.f11509b.getText().toString().trim());
                        accoutnReginfo.deviceCode = Config.m12527a();
                        accoutnReginfo.deviceModel = DevUtil.m12534a();
                        AccountRegisterFragment.this.f11516l.m12547a(accoutnReginfo, AccountRegisterFragment.this.f11519o, AccountRegisterFragment.this.f11518n);
                    } catch (Exception e) {
                        e.printStackTrace();
                        NRZSCommonManager.m11698b().m11700a();
                    }
                }
            }
        });
    }
}
