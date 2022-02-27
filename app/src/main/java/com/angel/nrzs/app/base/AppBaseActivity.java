package com.angel.nrzs.app.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import com.gyf.barlibrary.ImmersionBar;
import com.nrzs.libcommon.BaseActivity;
import p110z1.IntentToAssistService;

/* renamed from: com.angel.nrzs.ui.base.AppBaseActivity */
/* loaded from: classes.dex */
public abstract class AppBaseActivity extends BaseActivity {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.nrzs.libcommon.BaseActivity, android.support.p006v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // com.nrzs.libcommon.BaseActivity
    /* renamed from: c */
    public void mo18291c(Bundle bundle) {
        super.mo18291c(bundle);
        ImmersionBar.m20080a(this).m19994f();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.p006v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        Log.e("MyComponentDelegate", "AppBaseActivity");
        IntentToAssistService.m12812a((Context) this, 4);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.p006v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        ImmersionBar.m20080a(this).m19985g();
    }
}
