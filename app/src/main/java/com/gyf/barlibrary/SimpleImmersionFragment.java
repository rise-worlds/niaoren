package com.gyf.barlibrary;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

/* loaded from: classes.dex */
public abstract class SimpleImmersionFragment extends Fragment implements SimpleImmersionOwner {

    /* renamed from: a */
    private SimpleImmersionProxy f9245a = new SimpleImmersionProxy(this);

    @Override // com.gyf.barlibrary.SimpleImmersionOwner
    /* renamed from: a */
    public boolean mo19894a() {
        return true;
    }

    @Override // android.support.v4.app.Fragment
    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        this.f9245a.m19889a(z);
    }

    @Override // android.support.v4.app.Fragment
    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        this.f9245a.m19890a(bundle);
    }

    @Override // android.support.v4.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        this.f9245a.m19892a();
    }

    @Override // android.support.v4.app.Fragment
    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        this.f9245a.m19887b(z);
    }

    @Override // android.support.v4.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.f9245a.m19891a(configuration);
    }
}
