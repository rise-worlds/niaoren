package com.nrzs.user.p071ui.fragment;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.nrzs.data.base.BaseResponse;
import com.nrzs.data.user.bean.request.AlterPwinfmZh;
import com.nrzs.data.user.bean.request.LoginRInfo;
import com.nrzs.data.user.bean.respond.LoginResultV1Info;
import com.nrzs.http.ThreadCallback;
import com.nrzs.http.UICallback;
import com.nrzs.user.C2222R;
import com.nrzs.user.p071ui.activity.AlterPasswordActivity;
import com.nrzs.user.p071ui.base.UserBaseFragment;
import com.stripe.android.view.ShippingInfoWidget;
import java.util.UUID;
import p110z1.ActionVal;
import p110z1.LoginManager;
import p110z1.NRZSCommonManager;
import p110z1.RegexUtil;
import p110z1.TypeToken;
import p110z1.UserRepository;
import p110z1.apa;
import p110z1.aqg;

/* renamed from: com.nrzs.user.ui.fragment.AlterpassWordZhFragmnet2 */
/* loaded from: classes2.dex */
public class AlterpassWordZhFragmnet2 extends UserBaseFragment {

    /* renamed from: a */
    private TextView f11570a;

    /* renamed from: b */
    private EditText f11571b;

    /* renamed from: c */
    private EditText f11572c;

    /* renamed from: g */
    private LinearLayout f11573g;

    /* renamed from: h */
    private TextView f11574h;

    /* renamed from: j */
    private String f11576j;

    /* renamed from: i */
    private UserRepository f11575i = null;

    /* renamed from: k */
    private ThreadCallback f11577k = new ThreadCallback<BaseResponse<Object>, String>() { // from class: com.nrzs.user.ui.fragment.AlterpassWordZhFragmnet2.1
        /* renamed from: a */
        public BaseResponse<Object> onResponse(String str) {
            BaseResponse<Object> baseResponse = (BaseResponse) apa.m11877a(str, new TypeToken<BaseResponse<Object>>() { // from class: com.nrzs.user.ui.fragment.AlterpassWordZhFragmnet2.1.1
            });
            if (baseResponse != null) {
                return baseResponse;
            }
            return null;
        }
    };

    /* renamed from: l */
    private UICallback f11578l = new UICallback<BaseResponse<Object>>() { // from class: com.nrzs.user.ui.fragment.AlterpassWordZhFragmnet2.2
        @Override // com.nrzs.http.UICallback
        /* renamed from: a */
        public void mo3021a(Throwable th) {
            NRZSCommonManager.m11698b().m11700a();
            Log.d("NetEngin", "test2 onError:" + th.getMessage() + "," + Thread.currentThread().getName());
            aqg.m11586a(AlterpassWordZhFragmnet2.this.getContext(), "修改密码失败");
        }

        /* renamed from: a  reason: avoid collision after fix types in other method */
        public void mo3022a(BaseResponse<Object> baseResponse) {
            if (baseResponse == null) {
                NRZSCommonManager.m11698b().m11700a();
            } else if (baseResponse.code == 0) {
                NRZSCommonManager.m11698b().m11700a();
                aqg.m11586a(AlterpassWordZhFragmnet2.this.getContext(), baseResponse.msg);
            } else {
                aqg.m11586a(AlterpassWordZhFragmnet2.this.getContext(), "修改密码成功");
                AlterpassWordZhFragmnet2.this.m18241c();
            }
        }
    };

    /* renamed from: m */
    private UICallback<BaseResponse<LoginResultV1Info>> f11579m = new UICallback<BaseResponse<LoginResultV1Info>>() { // from class: com.nrzs.user.ui.fragment.AlterpassWordZhFragmnet2.3
        @Override // com.nrzs.http.UICallback
        /* renamed from: a */
        public void mo3021a(Throwable th) {
            NRZSCommonManager.m11698b().m11700a();
            aqg.m11586a(AlterpassWordZhFragmnet2.this.getContext(), "登录失败");
        }

        /* renamed from: a  reason: avoid collision after fix types in other method */
        public void mo3022a(BaseResponse<LoginResultV1Info> baseResponse) {
            if (baseResponse != null) {
                if (baseResponse.code == 0) {
                    aqg.m11586a(AlterpassWordZhFragmnet2.this.getContext(), baseResponse.msg);
                } else if (baseResponse.code != -1) {
                    LoginManager.m12620d().m12625a(baseResponse.data.AutoLoginToken, baseResponse.data.UserInfo, baseResponse.data.AscriptionAuthorId, baseResponse.data.UploadLocalAppPackage);
                    try {
                        AlterpassWordZhFragmnet2.this.startActivity(new Intent(ActionVal.f16464a));
                        ((AlterPasswordActivity) AlterpassWordZhFragmnet2.this.getContext()).finish();
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

    /* renamed from: n */
    private ThreadCallback<BaseResponse<LoginResultV1Info>, String> f11580n = new ThreadCallback<BaseResponse<LoginResultV1Info>, String>() { // from class: com.nrzs.user.ui.fragment.AlterpassWordZhFragmnet2.4
        /* renamed from: a */
        public BaseResponse<LoginResultV1Info> onResponse(String str) {
            BaseResponse<LoginResultV1Info> baseResponse = (BaseResponse) apa.m11877a(str, new TypeToken<BaseResponse<LoginResultV1Info>>() { // from class: com.nrzs.user.ui.fragment.AlterpassWordZhFragmnet2.4.1
            });
            if (baseResponse != null) {
                return baseResponse;
            }
            return null;
        }
    };

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.nrzs.libcommon.BaseFragment
    /* renamed from: d */
    public void mo18212d() {
    }

    /* renamed from: a */
    public static AlterpassWordZhFragmnet2 m18243a(String str) {
        AlterpassWordZhFragmnet2 alterpassWordZhFragmnet2 = new AlterpassWordZhFragmnet2();
        Bundle bundle = new Bundle();
        bundle.putString(ShippingInfoWidget.f12563f, str);
        alterpassWordZhFragmnet2.setArguments(bundle);
        return alterpassWordZhFragmnet2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public void m18241c() {
        try {
            LoginRInfo loginRInfo = new LoginRInfo();
            loginRInfo.UserName = this.f11576j;
            loginRInfo.setPassWord(this.f11571b.getText().toString().trim());
            loginRInfo.uuid = UUID.randomUUID().toString();
            this.f11575i.m12542a(loginRInfo, this.f11579m, this.f11580n);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.nrzs.libcommon.BaseFragment
    /* renamed from: b */
    protected int mo18216b() {
        return C2222R.layout.fragment_phone_alterpw_layout3;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.nrzs.libcommon.BaseFragment
    /* renamed from: a */
    public void mo18221a(View view) {
        super.mo18221a(view);
        this.f11570a = (TextView) view.findViewById(C2222R.C2224id.nrzs_user_tv_alterpw_old_passwort);
        this.f11571b = (EditText) view.findViewById(C2222R.C2224id.nrzs_user_tv_alterpw_passwort);
        this.f11572c = (EditText) view.findViewById(C2222R.C2224id.nrzs_user_tv_alterpw_passwortagain);
        this.f11574h = (TextView) view.findViewById(C2222R.C2224id.nrzs_user_tv_alterpw_success);
        this.f11573g = (LinearLayout) view.findViewById(C2222R.C2224id.nrzs_user_alter_layout);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.nrzs.libcommon.BaseFragment
    /* renamed from: e */
    public void mo18210e() {
        this.f11573g.setOnClickListener(new View.OnClickListener() { // from class: com.nrzs.user.ui.fragment.AlterpassWordZhFragmnet2.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
            }
        });
        this.f11574h.setOnClickListener(new View.OnClickListener() { // from class: com.nrzs.user.ui.fragment.AlterpassWordZhFragmnet2.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (TextUtils.isEmpty(AlterpassWordZhFragmnet2.this.f11570a.getText().toString().trim())) {
                    aqg.m11586a(AlterpassWordZhFragmnet2.this.getContext(), "旧密码错误");
                } else if (TextUtils.isEmpty(AlterpassWordZhFragmnet2.this.f11571b.getText().toString().trim())) {
                    aqg.m11586a(AlterpassWordZhFragmnet2.this.getContext(), "请输入6-16密码，字符、数字或字符");
                } else if (!AlterpassWordZhFragmnet2.this.f11571b.getText().toString().trim().equals(AlterpassWordZhFragmnet2.this.f11572c.getText().toString().trim())) {
                    aqg.m11586a(AlterpassWordZhFragmnet2.this.getContext(), "两次密码不同，请重新输入");
                } else if (AlterpassWordZhFragmnet2.this.f11571b.getText().toString().trim().length() < 6 || AlterpassWordZhFragmnet2.this.f11571b.getText().toString().trim().length() > 16) {
                    aqg.m11586a(AlterpassWordZhFragmnet2.this.getContext(), "请输入6-16密码，字符、数字或字符");
                } else if (!RegexUtil.m11592b(RegexUtil.f17300a, AlterpassWordZhFragmnet2.this.f11571b.getText().toString().trim().toString())) {
                    aqg.m11586a(AlterpassWordZhFragmnet2.this.getContext(), "请输入6-16密码，字符、数字或字符");
                } else if (!RegexUtil.m11592b(RegexUtil.f17300a, AlterpassWordZhFragmnet2.this.f11572c.getText().toString().trim().toString())) {
                    aqg.m11586a(AlterpassWordZhFragmnet2.this.getContext(), "请输入6-16密码，字符、数字或字符");
                } else {
                    try {
                        if (AlterpassWordZhFragmnet2.this.f11575i == null) {
                            AlterpassWordZhFragmnet2.this.f11575i = new UserRepository();
                        }
                        NRZSCommonManager.m11698b().m11699a(AlterpassWordZhFragmnet2.this.getActivity(), "修改密码，请稍等");
                        AlterPwinfmZh alterPwinfmZh = new AlterPwinfmZh();
                        alterPwinfmZh.UserName = AlterpassWordZhFragmnet2.this.f11576j;
                        alterPwinfmZh.setOldPassWord(AlterpassWordZhFragmnet2.this.f11570a.getText().toString().trim());
                        alterPwinfmZh.setPassWord(AlterpassWordZhFragmnet2.this.f11571b.getText().toString().trim());
                        AlterpassWordZhFragmnet2.this.f11575i.m12545a(alterPwinfmZh, AlterpassWordZhFragmnet2.this.f11578l, AlterpassWordZhFragmnet2.this.f11577k);
                    } catch (Exception e) {
                        NRZSCommonManager.m11698b().m11700a();
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @Override // com.nrzs.libcommon.BaseFragment, android.support.p003v4.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            this.f11576j = getArguments().getString(ShippingInfoWidget.f12563f);
        }
    }
}
