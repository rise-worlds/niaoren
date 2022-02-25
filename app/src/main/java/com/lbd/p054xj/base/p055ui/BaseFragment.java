package com.lbd.p054xj.base.p055ui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p003v4.app.Fragment;
import android.support.p003v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/* renamed from: com.lbd.xj.base.ui.BaseFragment */
/* loaded from: classes.dex */
public abstract class BaseFragment extends Fragment {

    /* renamed from: d */
    private static final String f9448d = "STATE_SAVE_IS_HIDDEN";

    /* renamed from: a */
    protected Activity f9449a;

    /* renamed from: b */
    protected View f9450b;

    /* renamed from: c */
    protected boolean f9451c = false;

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public void mo19419a() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public void mo19429a(Bundle bundle) {
    }

    /* renamed from: a */
    protected void mo19431a(View view) {
    }

    /* renamed from: b */
    protected void m19787b() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: b */
    public void mo19417b(View view) {
    }

    /* renamed from: c */
    protected void m19786c() {
    }

    /* renamed from: d */
    protected void m19785d() {
    }

    /* renamed from: e */
    protected abstract int mo19416e();

    @Override // android.support.p003v4.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        this.f9449a = (Activity) context;
    }

    @Override // android.support.p003v4.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            boolean z = bundle.getBoolean(f9448d);
            if (getFragmentManager() != null) {
                FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
                if (z) {
                    beginTransaction.hide(this);
                } else {
                    beginTransaction.show(this);
                }
                beginTransaction.commit();
            }
        }
        this.f9451c = true;
        mo19429a(bundle);
    }

    @Override // android.support.p003v4.app.Fragment
    @Nullable
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View view = this.f9450b;
        if (view == null) {
            this.f9450b = layoutInflater.inflate(mo19416e(), viewGroup, false);
        } else {
            ViewGroup viewGroup2 = (ViewGroup) view.getParent();
            if (viewGroup2 != null) {
                viewGroup2.removeView(this.f9450b);
            }
        }
        return this.f9450b;
    }

    @Override // android.support.p003v4.app.Fragment
    public void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        mo19431a(view);
        mo19417b(view);
        mo19419a();
        m19787b();
    }

    @Override // android.support.p003v4.app.Fragment
    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        if (this.f9451c) {
            if (z) {
                m19786c();
            } else {
                m19785d();
            }
        }
    }

    @Override // android.support.p003v4.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        this.f9451c = false;
    }
}
