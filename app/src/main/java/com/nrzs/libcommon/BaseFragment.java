package com.nrzs.libcommon;

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

/* loaded from: classes2.dex */
public abstract class BaseFragment extends Fragment {

    /* renamed from: a */
    private static final String f11165a = "STATE_SAVE_IS_HIDDEN";

    /* renamed from: d */
    protected Activity f11166d;

    /* renamed from: e */
    protected View f11167e;

    /* renamed from: f */
    protected boolean f11168f = false;

    /* renamed from: a */
    protected void m18547a(Bundle bundle) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public void mo18221a(View view) {
    }

    /* renamed from: b */
    protected abstract int mo18216b();

    /* renamed from: b */
    protected void mo18546b(View view) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: d */
    public void mo18212d() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: e */
    public void mo18210e() {
    }

    /* renamed from: f */
    protected void m18545f() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: g_ */
    public void mo18544g_() {
    }

    @Override // android.support.p003v4.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        this.f11166d = (Activity) context;
    }

    @Override // android.support.p003v4.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            boolean z = bundle.getBoolean(f11165a);
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
        this.f11168f = true;
        m18547a(bundle);
    }

    @Override // android.support.p003v4.app.Fragment
    @Nullable
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View view = this.f11167e;
        if (view == null) {
            this.f11167e = layoutInflater.inflate(mo18216b(), viewGroup, false);
        } else {
            ViewGroup viewGroup2 = (ViewGroup) view.getParent();
            if (viewGroup2 != null) {
                viewGroup2.removeView(this.f11167e);
            }
        }
        return this.f11167e;
    }

    @Override // android.support.p003v4.app.Fragment
    public void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        mo18546b(view);
        mo18221a(view);
        mo18212d();
        mo18210e();
    }

    @Override // android.support.p003v4.app.Fragment
    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        if (this.f11168f) {
            if (z) {
                mo18544g_();
            } else {
                m18545f();
            }
        }
    }

    @Override // android.support.p003v4.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        this.f11168f = false;
    }
}
