package com.lbd.p054xj.p056ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.gyf.barlibrary.ImmersionBar;
import com.lbd.p054xj.base.p055ui.BaseActivity;

/* renamed from: com.lbd.xj.ui.activity.AppBaseActivity */
/* loaded from: classes.dex */
public abstract class AppBaseActivity extends BaseActivity {
    @Override // com.lbd.p054xj.base.p055ui.BaseActivity, android.support.p006v7.app.AppCompatActivity, android.support.p003v4.app.FragmentActivity, android.support.p003v4.app.ComponentActivity, android.app.Activity
    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // com.lbd.p054xj.base.p055ui.BaseActivity
    /* renamed from: a */
    public void mo19555a(Bundle bundle) {
        super.mo19555a(bundle);
        ImmersionBar.m20080a(this).m19994f();
    }

    @Override // android.support.p003v4.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        super.onPause();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.p003v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.p006v7.app.AppCompatActivity, android.support.p003v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        ImmersionBar.m20080a(this).m19985g();
    }
}
