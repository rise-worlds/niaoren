package com.lbd.p054xj.p056ui.fragment;

import android.content.res.Configuration;
import android.support.p006v7.widget.Toolbar;
import android.view.View;
import com.gyf.barlibrary.ImmersionBar;
import com.lbd.p054xj.C1467R;
import com.lbd.p054xj.base.p055ui.BaseFragment;

/* renamed from: com.lbd.xj.ui.fragment.AppBaseFragment */
/* loaded from: classes.dex */
public abstract class AppBaseFragment extends BaseFragment {

    /* renamed from: d */
    protected Toolbar f9768d;

    /* renamed from: e */
    protected View f9769e;

    @Override // com.lbd.p054xj.base.p055ui.BaseFragment
    /* renamed from: a */
    protected void mo19431a(View view) {
        this.f9768d = (Toolbar) view.findViewById(C1467R.C1469id.xj_app_toolbar);
        m19430f();
    }

    @Override // android.support.p003v4.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        m19430f();
    }

    /* renamed from: f */
    private void m19430f() {
        if (this.f9769e != null) {
            ImmersionBar.m20024c(getActivity(), this.f9769e);
        } else {
            ImmersionBar.m20076a(getActivity(), this.f9768d);
        }
    }
}
