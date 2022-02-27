package com.gyf.barlibrary;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

/* loaded from: classes.dex */
public abstract class ImmersionFragment extends Fragment implements ImmersionOwner {

    /* renamed from: a */
    private ImmersionProxy f9244a = new ImmersionProxy(this);

    @Override // com.gyf.barlibrary.ImmersionOwner
    /* renamed from: a */
    public void mo19934a() {
    }

    @Override // com.gyf.barlibrary.ImmersionOwner
    /* renamed from: b */
    public void mo19933b() {
    }

    @Override // com.gyf.barlibrary.ImmersionOwner
    /* renamed from: c */
    public void mo19932c() {
    }

    @Override // com.gyf.barlibrary.ImmersionOwner
    /* renamed from: d */
    public void mo19931d() {
    }

    @Override // com.gyf.barlibrary.ImmersionOwner
    /* renamed from: e */
    public boolean mo19930e() {
        return true;
    }

    @Override // android.support.v4.app.Fragment
    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        this.f9244a.m19925a(z);
    }

    @Override // android.support.v4.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        this.f9244a.m19926a(bundle);
    }

    @Override // android.support.v4.app.Fragment
    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        this.f9244a.m19923b(bundle);
    }

    @Override // android.support.v4.app.Fragment
    public void onResume() {
        super.onResume();
        this.f9244a.m19928a();
    }

    @Override // android.support.v4.app.Fragment
    public void onPause() {
        super.onPause();
        this.f9244a.m19924b();
    }

    @Override // android.support.v4.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        this.f9244a.m19921c();
    }

    @Override // android.support.v4.app.Fragment
    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        this.f9244a.m19922b(z);
    }

    @Override // android.support.v4.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.f9244a.m19927a(configuration);
    }
}
