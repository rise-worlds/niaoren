package org.greenrobot.eventbus.util;

import android.annotation.TargetApi;
import android.os.Bundle;
import android.support.v4.app.Fragment;

/* renamed from: org.greenrobot.eventbus.util.c */
/* loaded from: classes2.dex */
public abstract class ErrorDialogFragmentFactory<T> {

    /* renamed from: a */
    protected final ErrorDialogConfig f14826a;

    /* renamed from: a */
    protected abstract T mo14782a(ThrowableFailureEvent fVar, Bundle bundle);

    protected ErrorDialogFragmentFactory(ErrorDialogConfig bVar) {
        this.f14826a = bVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public T m14786a(ThrowableFailureEvent fVar, boolean z, Bundle bundle) {
        Bundle bundle2;
        if (fVar.m14774c()) {
            return null;
        }
        if (bundle != null) {
            bundle2 = (Bundle) bundle.clone();
        } else {
            bundle2 = new Bundle();
        }
        if (!bundle2.containsKey(ErrorDialogManager.f14794d)) {
            bundle2.putString(ErrorDialogManager.f14794d, m14785b(fVar, bundle2));
        }
        if (!bundle2.containsKey(ErrorDialogManager.f14795e)) {
            bundle2.putString(ErrorDialogManager.f14795e, m14784c(fVar, bundle2));
        }
        if (!bundle2.containsKey(ErrorDialogManager.f14796f)) {
            bundle2.putBoolean(ErrorDialogManager.f14796f, z);
        }
        if (!bundle2.containsKey(ErrorDialogManager.f14798h) && this.f14826a.f14825i != null) {
            bundle2.putSerializable(ErrorDialogManager.f14798h, this.f14826a.f14825i);
        }
        if (!bundle2.containsKey(ErrorDialogManager.f14797g) && this.f14826a.f14824h != 0) {
            bundle2.putInt(ErrorDialogManager.f14797g, this.f14826a.f14824h);
        }
        return mo14782a(fVar, bundle2);
    }

    /* renamed from: b */
    protected String m14785b(ThrowableFailureEvent fVar, Bundle bundle) {
        return this.f14826a.f14817a.getString(this.f14826a.f14818b);
    }

    /* renamed from: c */
    protected String m14784c(ThrowableFailureEvent fVar, Bundle bundle) {
        return this.f14826a.f14817a.getString(this.f14826a.m14789a(fVar.f14828a));
    }

    /* compiled from: ErrorDialogFragmentFactory.java */
    /* renamed from: org.greenrobot.eventbus.util.c$b */
    /* loaded from: classes2.dex */
    public static class C3258b extends ErrorDialogFragmentFactory<Fragment> {
        public C3258b(ErrorDialogConfig bVar) {
            super(bVar);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* renamed from: d */
        public Fragment mo14782a(ThrowableFailureEvent fVar, Bundle bundle) {
            ErrorDialogFragments.Support support = new ErrorDialogFragments.Support();
            support.setArguments(bundle);
            return support;
        }
    }

    /* compiled from: ErrorDialogFragmentFactory.java */
    @TargetApi(11)
    /* renamed from: org.greenrobot.eventbus.util.c$a */
    /* loaded from: classes2.dex */
    public static class C3257a extends ErrorDialogFragmentFactory<android.app.Fragment> {
        public C3257a(ErrorDialogConfig bVar) {
            super(bVar);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* renamed from: d */
        public android.app.Fragment mo14782a(ThrowableFailureEvent fVar, Bundle bundle) {
            ErrorDialogFragments.Honeycomb honeycomb = new ErrorDialogFragments.Honeycomb();
            honeycomb.setArguments(bundle);
            return honeycomb;
        }
    }
}
