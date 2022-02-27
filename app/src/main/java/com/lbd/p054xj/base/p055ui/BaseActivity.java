package com.lbd.p054xj.base.p055ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/* renamed from: com.lbd.xj.base.ui.BaseActivity */
/* loaded from: classes.dex */
public abstract class BaseActivity extends AppCompatActivity {
    /* renamed from: a */
    protected abstract int mo19493a();

    /* renamed from: a */
    public void mo19555a(Bundle bundle) {
    }

    /* renamed from: b */
    public abstract void mo19492b();

    /* renamed from: b */
    public abstract void mo19491b(Bundle bundle);

    /* renamed from: c */
    public abstract void mo19490c();

    /* renamed from: c */
    public abstract void mo19489c(Bundle bundle);

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.p006v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        mo19555a(bundle);
        super.onCreate(bundle);
        mo19492b();
        if (mo19493a() != 0) {
            super.setContentView(mo19493a());
        }
        mo19491b(bundle);
        mo19489c(bundle);
        mo19490c();
    }
}
