package com.gyf.barlibrary;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

/* renamed from: com.gyf.barlibrary.i */
/* loaded from: classes.dex */
public class ImmersionProxy {

    /* renamed from: a */
    private Fragment f9358a;

    /* renamed from: b */
    private ImmersionOwner f9359b;

    /* renamed from: c */
    private boolean f9360c;

    /* renamed from: d */
    private boolean f9361d;

    /* renamed from: e */
    private boolean f9362e;

    public ImmersionProxy(Fragment fragment) {
        this.f9358a = fragment;
        if (fragment instanceof ImmersionOwner) {
            this.f9359b = (ImmersionOwner) fragment;
            return;
        }
        throw new IllegalArgumentException("Fragment请实现ImmersionOwner接口");
    }

    /* renamed from: a */
    public void m19925a(boolean z) {
        Fragment fragment = this.f9358a;
        if (fragment == null) {
            return;
        }
        if (fragment.getUserVisibleHint()) {
            if (!this.f9362e) {
                this.f9359b.mo19934a();
                this.f9362e = true;
            }
            if (this.f9360c && this.f9358a.getUserVisibleHint()) {
                if (this.f9359b.mo19930e()) {
                    this.f9359b.m19929f();
                }
                if (!this.f9361d) {
                    this.f9359b.mo19933b();
                    this.f9361d = true;
                }
                this.f9359b.mo19932c();
            }
        } else if (this.f9360c) {
            this.f9359b.mo19931d();
        }
    }

    /* renamed from: a */
    public void m19926a(@Nullable Bundle bundle) {
        Fragment fragment = this.f9358a;
        if (fragment != null && fragment.getUserVisibleHint() && !this.f9362e) {
            this.f9359b.mo19934a();
            this.f9362e = true;
        }
    }

    /* renamed from: b */
    public void m19923b(@Nullable Bundle bundle) {
        this.f9360c = true;
        Fragment fragment = this.f9358a;
        if (fragment != null && fragment.getUserVisibleHint()) {
            if (this.f9359b.mo19930e()) {
                this.f9359b.m19929f();
            }
            if (!this.f9361d) {
                this.f9359b.mo19933b();
                this.f9361d = true;
            }
        }
    }

    /* renamed from: a */
    public void m19928a() {
        Fragment fragment = this.f9358a;
        if (fragment != null && fragment.getUserVisibleHint()) {
            this.f9359b.mo19932c();
        }
    }

    /* renamed from: b */
    public void m19924b() {
        if (this.f9358a != null) {
            this.f9359b.mo19931d();
        }
    }

    /* renamed from: c */
    public void m19921c() {
        Fragment fragment = this.f9358a;
        if (!(fragment == null || fragment.getActivity() == null || !this.f9359b.mo19930e())) {
            ImmersionBar.m20073a(this.f9358a).m19985g();
        }
        this.f9358a = null;
        this.f9359b = null;
    }

    /* renamed from: a */
    public void m19927a(Configuration configuration) {
        Fragment fragment = this.f9358a;
        if (fragment != null && fragment.getUserVisibleHint()) {
            if (this.f9359b.mo19930e()) {
                this.f9359b.m19929f();
            }
            this.f9359b.mo19932c();
        }
    }

    /* renamed from: b */
    public void m19922b(boolean z) {
        Fragment fragment = this.f9358a;
        if (fragment != null) {
            fragment.setUserVisibleHint(!z);
        }
    }

    /* renamed from: d */
    public boolean m19920d() {
        Fragment fragment = this.f9358a;
        if (fragment != null) {
            return fragment.getUserVisibleHint();
        }
        return false;
    }
}
