package com.gyf.barlibrary;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.p003v4.app.Fragment;

/* renamed from: com.gyf.barlibrary.n */
/* loaded from: classes.dex */
public class SimpleImmersionProxy {

    /* renamed from: a */
    private Fragment f9371a;

    /* renamed from: b */
    private SimpleImmersionOwner f9372b;

    /* renamed from: c */
    private boolean f9373c;

    public SimpleImmersionProxy(Fragment fragment) {
        this.f9371a = fragment;
        if (fragment instanceof SimpleImmersionOwner) {
            this.f9372b = (SimpleImmersionOwner) fragment;
            return;
        }
        throw new IllegalArgumentException("Fragment请实现SimpleImmersionOwner接口");
    }

    /* renamed from: a */
    public void m19889a(boolean z) {
        m19886c();
    }

    /* renamed from: a */
    public void m19890a(@Nullable Bundle bundle) {
        this.f9373c = true;
        m19886c();
    }

    /* renamed from: a */
    public void m19892a() {
        Fragment fragment = this.f9371a;
        if (!(fragment == null || fragment.getActivity() == null || !this.f9372b.mo19894a())) {
            ImmersionBar.m20073a(this.f9371a).m19985g();
        }
        this.f9371a = null;
        this.f9372b = null;
    }

    /* renamed from: a */
    public void m19891a(Configuration configuration) {
        m19886c();
    }

    /* renamed from: b */
    public void m19887b(boolean z) {
        Fragment fragment = this.f9371a;
        if (fragment != null) {
            fragment.setUserVisibleHint(!z);
        }
    }

    /* renamed from: b */
    public boolean m19888b() {
        Fragment fragment = this.f9371a;
        if (fragment != null) {
            return fragment.getUserVisibleHint();
        }
        return false;
    }

    /* renamed from: c */
    private void m19886c() {
        Fragment fragment = this.f9371a;
        if (fragment != null && this.f9373c && fragment.getUserVisibleHint() && this.f9372b.mo19894a()) {
            this.f9372b.m19893b();
        }
    }
}
