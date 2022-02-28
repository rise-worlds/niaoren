package com.nrzs.libcommon;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/* loaded from: classes2.dex */
public abstract class BaseActivity extends AppCompatActivity {
    /* renamed from: a */
    protected abstract int mo18307a();

    /* renamed from: a */
    public abstract void mo18305a(Bundle bundle);

    /* renamed from: b */
    public abstract void mo18303b();

    /* renamed from: b */
    public abstract void mo18302b(Bundle bundle);

    /* renamed from: c */
    public void mo18291c(Bundle bundle) {
    }

    /* renamed from: d */
    public abstract void mo18300d();

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        mo18291c(bundle);
        super.onCreate(bundle);
        mo18303b();
        if (mo18307a() != 0) {
            super.setContentView(mo18307a());
        }
        mo18305a(bundle);
        mo18302b(bundle);
        mo18300d();
    }
}
