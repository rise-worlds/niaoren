package com.nrzs.user.ui.activity;

import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.gyf.barlibrary.ImmersionBar;
import com.nrzs.base.router.RouterConstants;
import com.nrzs.user.C2222R;
import com.nrzs.user.ui.base.UserBaseActivity;
import com.nrzs.user.ui.fragment.AccountRegisterFragment;
import com.nrzs.user.ui.fragment.PhoneRegisterFragment1;
import p110z1.ARouter;

@Route(path = RouterConstants.ModuleUser.REGISTER)
/* renamed from: com.nrzs.user.ui.activity.RegisterActivity */
/* loaded from: classes2.dex */
public class RegisterActivity extends UserBaseActivity {

    /* renamed from: a */
    private ImageView f11482a;

    /* renamed from: b */
    private TextView f11483b;

    /* renamed from: a */
    public static void m18306a(Context context) {
        context.startActivity(new Intent(context, RegisterActivity.class));
    }

    @Override // com.nrzs.libcommon.BaseActivity
    /* renamed from: a */
    protected int mo18307a() {
        return C2222R.layout.activity_register_layout;
    }

    @Override // com.nrzs.libcommon.BaseActivity
    /* renamed from: b */
    public void mo18303b() {
        ImmersionBar.m20080a(this).m20087a(C2222R.color.colorPrimary).m19974h(true).m19994f();
    }

    @Override // com.nrzs.libcommon.BaseActivity
    /* renamed from: a */
    public void mo18305a(Bundle bundle) {
        this.f11482a = (ImageView) findViewById(C2222R.C2224id.nrzs_user_tv_regist_back);
        this.f11483b = (TextView) findViewById(C2222R.C2224id.nrzs_user_tv_login);
    }

    @Override // com.nrzs.libcommon.BaseActivity
    /* renamed from: b */
    public void mo18302b(Bundle bundle) {
        if (bundle == null) {
            getSupportFragmentManager().beginTransaction().add(C2222R.C2224id.nrzs_user_fl_root, AccountRegisterFragment.m18288c(), PhoneRegisterFragment1.class.getName()).commit();
        }
    }

    @Override // com.nrzs.libcommon.BaseActivity
    /* renamed from: d */
    public void mo18300d() {
        this.f11482a.setOnClickListener(new View.OnClickListener() { // from class: com.nrzs.user.ui.activity.RegisterActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                RegisterActivity.this.m18301c();
            }
        });
        this.f11483b.setOnClickListener(new View.OnClickListener() { // from class: com.nrzs.user.ui.activity.RegisterActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                RegisterActivity.this.m18299e();
                RegisterActivity.this.finish();
            }
        });
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.nrzs.user.ui.activity.RegisterActivity$3] */
    /* renamed from: c */
    public void m18301c() {
        new Thread() { // from class: com.nrzs.user.ui.activity.RegisterActivity.3
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                try {
                    new Instrumentation().sendKeyDownUpSync(4);
                } catch (Exception unused) {
                }
            }
        }.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: e */
    public void m18299e() {
        ARouter.m1714a().m1707a(RouterConstants.ModuleUser.LOGIN).withInt("type", 1).withInt("closetype", 1).navigation();
    }
}
