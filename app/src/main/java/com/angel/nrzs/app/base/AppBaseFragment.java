package com.angel.nrzs.app.base;

import android.content.res.Configuration;
import android.support.v7.widget.Toolbar;
import android.view.View;
import com.angel.nrzs.C0692R;
import com.gyf.barlibrary.ImmersionBar;
import com.nrzs.libcommon.BaseFragment;

/* renamed from: com.angel.nrzs.ui.base.AppBaseFragment */
/* loaded from: classes.dex */
public abstract class AppBaseFragment extends BaseFragment {

    /* renamed from: a */
    protected Toolbar f5517a;

    /* renamed from: b */
    protected View f5518b;

    @Override // com.nrzs.libcommon.BaseFragment
    /* renamed from: b */
    protected void mo18546b(View view) {
        this.f5517a = (Toolbar) view.findViewById(C0692R.C0694id.nrzs_app_toolbar);
        m24947c();
    }

    @Override // android.support.v4.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        m24947c();
    }

    /* renamed from: c */
    private void m24947c() {
        if (this.f5518b != null) {
            ImmersionBar.m20024c(getActivity(), this.f5518b);
        } else {
            ImmersionBar.m20076a(getActivity(), this.f5517a);
        }
    }
}
