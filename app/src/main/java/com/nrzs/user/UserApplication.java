package com.nrzs.user;

import android.app.Application;
import p110z1.Config;

/* loaded from: classes2.dex */
public class UserApplication extends Application {

    /* renamed from: a */
    public static String f11321a;

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        m18403a();
    }

    /* renamed from: a */
    private void m18403a() {
        Config.m12526a(this);
    }
}
