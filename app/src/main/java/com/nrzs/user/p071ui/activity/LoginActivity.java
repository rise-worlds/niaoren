package com.nrzs.user.p071ui.activity;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.ToastUtils;
import com.gyf.barlibrary.ImmersionBar;
import com.nrzs.base.router.RouterConstants;
import com.nrzs.data.base.BaseResponse;
import com.nrzs.data.user.bean.request.LoginRInfo;
import com.nrzs.data.user.bean.respond.LoginResultV1Info;
import com.nrzs.http.ThreadCallback;
import com.nrzs.http.UICallback;
import com.nrzs.libcommon.NRZSViewClickListener;
import com.nrzs.user.C2222R;
import com.nrzs.user.p071ui.base.UserBaseActivity;
import java.util.UUID;
import p110z1.ActionVal;
import p110z1.LoginManager;
import p110z1.NRZSCommonManager;
import p110z1.PermissionUtil;
import p110z1.TypeToken;
import p110z1.UserRepository;
import p110z1.apa;
import p110z1.aqg;

@Route(path = RouterConstants.ModuleUser.LOGIN)
/* renamed from: com.nrzs.user.ui.activity.LoginActivity */
/* loaded from: classes2.dex */
public class LoginActivity extends UserBaseActivity {

    /* renamed from: a */
    private EditText f11425a;

    /* renamed from: b */
    private EditText f11426b;

    /* renamed from: c */
    private TextView f11427c;

    /* renamed from: d */
    private TextView f11428d;

    /* renamed from: e */
    private TextView f11429e;

    /* renamed from: f */
    private UserRepository f11430f;

    /* renamed from: g */
    private int f11431g;

    /* renamed from: h */
    private int f11432h;

    /* renamed from: i */
    private ImageView f11433i;

    /* renamed from: j */
    private String f11434j;

    /* renamed from: k */
    private String f11435k;

    /* renamed from: l */
    private ThreadCallback<BaseResponse<LoginResultV1Info>, String> f11436l = new ThreadCallback<BaseResponse<LoginResultV1Info>, String>() { // from class: com.nrzs.user.ui.activity.LoginActivity.1
        /* renamed from: a */
        public BaseResponse<LoginResultV1Info> onResponse(String str) {
            BaseResponse<LoginResultV1Info> baseResponse = (BaseResponse) apa.m11877a(str, new TypeToken<BaseResponse<LoginResultV1Info>>() { // from class: com.nrzs.user.ui.activity.LoginActivity.1.1
            });
            if (baseResponse != null) {
                return baseResponse;
            }
            return null;
        }
    };

    /* renamed from: m */
    private UICallback<BaseResponse<LoginResultV1Info>> f11437m = new UICallback<BaseResponse<LoginResultV1Info>>() { // from class: com.nrzs.user.ui.activity.LoginActivity.2
        @Override // com.nrzs.http.UICallback
        /* renamed from: a */
        public void mo3021a(Throwable th) {
            NRZSCommonManager.m11698b().m11700a();
            aqg.m11586a(LoginActivity.this, "登录失败");
        }

        /* renamed from: a  reason: avoid collision after fix types in other method */
        public void mo3022a(BaseResponse<LoginResultV1Info> baseResponse) {
            if (baseResponse != null) {
                if (baseResponse.code == 0) {
                    aqg.m11586a(LoginActivity.this, baseResponse.msg);
                } else if (baseResponse.code == -1) {
                    if (baseResponse.data != null) {
                        LoginManager.m12620d().m12626a(baseResponse.data.AutoLoginToken, baseResponse.data.UserId);
                    }
                    LoginActivity.this.finish();
                } else {
                    LoginManager.m12620d().m12625a(baseResponse.data.AutoLoginToken, baseResponse.data.UserInfo, baseResponse.data.AscriptionAuthorId, baseResponse.data.UploadLocalAppPackage);
                    if (LoginActivity.this.f11432h == 0) {
                        LoginActivity.this.finish();
                    } else {
                        try {
                            LoginActivity.this.startActivity(new Intent(ActionVal.f16464a));
                            LoginActivity.this.finish();
                        } catch (ActivityNotFoundException unused) {
                        }
                    }
                }
            }
            NRZSCommonManager.m11698b().m11700a();
        }
    };

    /* renamed from: a */
    public static void m18339a(Context context, int i, String str, String str2, int i2) {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.putExtra("type", i);
        intent.putExtra("username", str);
        intent.putExtra("pw", str2);
        intent.putExtra("closetype", i2);
        context.startActivity(intent);
    }

    @Override // com.nrzs.user.p071ui.base.UserBaseActivity, com.nrzs.libcommon.BaseActivity, android.support.p006v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.ComponentActivity, android.app.Activity
    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // com.nrzs.libcommon.BaseActivity
    /* renamed from: a */
    protected int mo18307a() {
        return C2222R.layout.activity_login_layout;
    }

    @Override // com.nrzs.libcommon.BaseActivity
    /* renamed from: b */
    public void mo18303b() {
        ImmersionBar.m20080a(this).m20087a(C2222R.color.colorPrimary).m19974h(true).m19994f();
        this.f11431g = getIntent().getIntExtra("type", 1);
        this.f11434j = getIntent().getStringExtra("username");
        this.f11435k = getIntent().getStringExtra("pw");
        this.f11432h = getIntent().getIntExtra("closetype", 0);
        m18332c();
    }

    /* renamed from: c */
    public void m18332c() {
        int i = C22677.f11445a[PermissionUtil.m11847a(this, "android.permission.READ_PHONE_STATE").ordinal()];
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity, android.support.v4.app.ActivityCompat.OnRequestPermissionsResultCallback
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        switch (PermissionUtil.m11847a(this, "android.permission.READ_PHONE_STATE")) {
            case GRANTED:
            default:
                return;
            case DENIED:
                finish();
                return;
        }
    }

    @Override // com.nrzs.libcommon.BaseActivity
    /* renamed from: a */
    public void mo18305a(Bundle bundle) {
        this.f11425a = (EditText) findViewById(C2222R.C2224id.nrzs_user_et_username);
        this.f11426b = (EditText) findViewById(C2222R.C2224id.nrzs_user_et_userpwd);
        this.f11427c = (TextView) findViewById(C2222R.C2224id.nrzs_user_tv_login);
        this.f11428d = (TextView) findViewById(C2222R.C2224id.nrzs_user_tv_register);
        this.f11429e = (TextView) findViewById(C2222R.C2224id.nrzs_user_tv_updatepwd);
        this.f11433i = (ImageView) findViewById(C2222R.C2224id.nrzs_user_login_img_back);
    }

    @Override // com.nrzs.libcommon.BaseActivity
    /* renamed from: b */
    public void mo18302b(Bundle bundle) {
        LoginManager.m12620d().m12605s();
        this.f11430f = new UserRepository();
        if (this.f11431g == 2) {
            this.f11425a.setText(this.f11434j);
            this.f11426b.setText(this.f11435k);
            m18331e();
        }
    }

    @Override // com.nrzs.libcommon.BaseActivity
    /* renamed from: d */
    public void mo18300d() {
        this.f11427c.setOnClickListener(new NRZSViewClickListener() { // from class: com.nrzs.user.ui.activity.LoginActivity.3
            @Override // com.nrzs.libcommon.NRZSViewClickListener
            /* renamed from: b */
            protected void mo18274b(View view) {
            }

            @Override // com.nrzs.libcommon.NRZSViewClickListener
            /* renamed from: a */
            protected void mo18275a(View view) {
                LoginActivity.this.m18331e();
            }
        });
        this.f11428d.setOnClickListener(new View.OnClickListener() { // from class: com.nrzs.user.ui.activity.LoginActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LoginActivity.this.m18338a(view);
            }
        });
        this.f11429e.setOnClickListener(new View.OnClickListener() { // from class: com.nrzs.user.ui.activity.LoginActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LoginActivity.this.m18335b(view);
            }
        });
        this.f11433i.setOnClickListener(new View.OnClickListener() { // from class: com.nrzs.user.ui.activity.LoginActivity.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LoginActivity.this.finish();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m18338a(View view) {
        RegisterActivity.m18306a(view.getContext());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public void m18335b(View view) {
        AlterPasswordActivity.m18379a(view.getContext());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: e */
    public void m18331e() {
        String trim = this.f11425a.getText().toString().trim();
        if (TextUtils.isEmpty(trim)) {
            ToastUtils.m23030a("请输入用户名");
            return;
        }
        String trim2 = this.f11426b.getText().toString().trim();
        if (TextUtils.isEmpty(trim2)) {
            ToastUtils.m23030a("请输入密码");
            return;
        }
        NRZSCommonManager.m11698b().m11699a(this, "登录中，请稍等");
        try {
            LoginRInfo loginRInfo = new LoginRInfo();
            loginRInfo.UserName = trim;
            loginRInfo.setPassWord(trim2);
            loginRInfo.uuid = UUID.randomUUID().toString();
            this.f11430f.m12542a(loginRInfo, this.f11437m, this.f11436l);
        } catch (Exception e) {
            e.printStackTrace();
            NRZSCommonManager.m11698b().m11700a();
        }
    }

    @Override // com.nrzs.user.p071ui.base.UserBaseActivity, android.support.p006v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        InputMethodManager inputMethodManager = (InputMethodManager) getBaseContext().getSystemService("input_method");
        if (inputMethodManager != null) {
            if (this.f11425a.isFocusable()) {
                inputMethodManager.hideSoftInputFromWindow(this.f11425a.getWindowToken(), 2);
            }
            if (this.f11426b.isFocusable()) {
                inputMethodManager.hideSoftInputFromWindow(this.f11426b.getWindowToken(), 2);
            }
        }
    }
}
