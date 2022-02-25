package com.nrzs.user.p071ui.activity;

import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.gyf.barlibrary.ImmersionBar;
import com.nrzs.base.router.RouterConstants;
import com.nrzs.user.C2222R;
import com.nrzs.user.p071ui.base.UserBaseActivity;
import com.nrzs.user.p071ui.fragment.AlterpassWordFragment1;

@Route(path = RouterConstants.ModuleUser.ALTER_PASSWORD)
/* renamed from: com.nrzs.user.ui.activity.AlterPasswordActivity */
/* loaded from: classes2.dex */
public class AlterPasswordActivity extends UserBaseActivity {

    /* renamed from: a */
    private ImageView f11352a;

    /* renamed from: a */
    public static void m18379a(Context context) {
        context.startActivity(new Intent(context, AlterPasswordActivity.class));
    }

    @Override // com.nrzs.libcommon.BaseActivity
    /* renamed from: a */
    protected int mo18307a() {
        return C2222R.layout.activity_alter_layout;
    }

    @Override // com.nrzs.libcommon.BaseActivity
    /* renamed from: b */
    public void mo18303b() {
        ImmersionBar.m20080a(this).m20087a(C2222R.color.colorPrimary).m19974h(true).m19994f();
    }

    @Override // com.nrzs.libcommon.BaseActivity
    /* renamed from: a */
    public void mo18305a(Bundle bundle) {
        this.f11352a = (ImageView) findViewById(C2222R.C2224id.nrzs_user_tv_regist_back);
    }

    @Override // com.nrzs.libcommon.BaseActivity
    /* renamed from: b */
    public void mo18302b(Bundle bundle) {
        if (bundle == null) {
            getSupportFragmentManager().beginTransaction().add(C2222R.C2224id.nrzs_user_fl_root, AlterpassWordFragment1.m18271c(), AlterpassWordFragment1.class.getName()).commit();
        }
    }

    @Override // com.nrzs.libcommon.BaseActivity
    /* renamed from: d */
    public void mo18300d() {
        this.f11352a.setOnClickListener(new View.OnClickListener() { // from class: com.nrzs.user.ui.activity.AlterPasswordActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AlterPasswordActivity.this.m18378c();
            }
        });
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.nrzs.user.ui.activity.AlterPasswordActivity$2] */
    /* renamed from: c */
    public void m18378c() {
        new Thread() { // from class: com.nrzs.user.ui.activity.AlterPasswordActivity.2
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                try {
                    new Instrumentation().sendKeyDownUpSync(4);
                } catch (Exception unused) {
                }
            }
        }.start();
    }
}
