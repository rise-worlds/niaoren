package org.jdeferred.android;

import android.annotation.SuppressLint;
import android.os.Build;
import java.util.concurrent.ExecutorService;
import p110z1.DefaultDeferredManager;
import p110z1.DeferredFutureTask;
import p110z1.DeferredManager;
import p110z1.MasterProgress;
import p110z1.MultipleResults;
import p110z1.OneReject;
import p110z1.Promise;

/* renamed from: org.jdeferred.android.b */
/* loaded from: classes2.dex */
public class AndroidDeferredManager extends DefaultDeferredManager {

    /* renamed from: c */
    private static Void[] f14831c = new Void[0];

    public AndroidDeferredManager() {
    }

    public AndroidDeferredManager(ExecutorService executorService) {
        super(executorService);
    }

    @SuppressLint({"NewApi"})
    /* renamed from: a */
    public <Progress, Result> Promise<Result, Throwable, Progress> m14771a(DeferredAsyncTask<Void, Progress, Result> iVar) {
        if (iVar.m14762b() == DeferredManager.EnumC5238a.AUTO || (iVar.m14762b() == DeferredManager.EnumC5238a.DEFAULT && mo3310a())) {
            if (Build.VERSION.SDK_INT >= 11) {
                iVar.executeOnExecutor(m3307b(), f14831c);
            } else {
                iVar.execute(f14831c);
            }
        }
        return iVar.m14765a();
    }

    /* renamed from: a */
    public Promise<MultipleResults, OneReject, MasterProgress> m14769a(DeferredAsyncTask<Void, ?, ?>... iVarArr) {
        m3326a((Object[]) iVarArr);
        Promise[] dazVarArr = new Promise[iVarArr.length];
        for (int i = 0; i < iVarArr.length; i++) {
            dazVarArr[i] = m14771a(iVarArr[i]);
        }
        return mo3319a(dazVarArr);
    }

    /* renamed from: a */
    public Promise<MultipleResults, OneReject, MasterProgress> m14773a(AndroidExecutionScope eVar, DeferredAsyncTask<Void, ?, ?>... iVarArr) {
        m3326a((Object[]) iVarArr);
        Promise[] dazVarArr = new Promise[iVarArr.length];
        for (int i = 0; i < iVarArr.length; i++) {
            dazVarArr[i] = m14771a(iVarArr[i]);
        }
        return m14772a(eVar, dazVarArr);
    }

    @Override // p110z1.AbstractDeferredManager, p110z1.DeferredManager
    /* renamed from: a */
    public <D, P> Promise<D, Throwable, P> mo3329a(DeferredFutureTask<D, P> danVar) {
        return (Promise<D, Throwable, P>) new AndroidDeferredObject(super.mo3329a((DeferredFutureTask) danVar)).mo3300a();
    }

    @Override // p110z1.AbstractDeferredManager, p110z1.DeferredManager
    /* renamed from: a */
    public <D, F, P> Promise<D, F, P> mo3327a(Promise<D, F, P> dazVar) {
        return dazVar instanceof AndroidDeferredObject ? dazVar : new AndroidDeferredObject(dazVar).mo3300a();
    }

    /* renamed from: a */
    public <D, F, P> Promise<D, F, P> m14770a(Promise<D, F, P> dazVar, AndroidExecutionScope eVar) {
        return dazVar instanceof AndroidDeferredObject ? dazVar : new AndroidDeferredObject(dazVar, eVar).mo3300a();
    }

    @Override // p110z1.AbstractDeferredManager, p110z1.DeferredManager
    /* renamed from: a */
    public Promise<MultipleResults, OneReject, MasterProgress> mo3319a(Promise... dazVarArr) {
        return new AndroidDeferredObject(super.mo3319a(dazVarArr)).mo3300a();
    }

    /* renamed from: a */
    public Promise<MultipleResults, OneReject, MasterProgress> m14772a(AndroidExecutionScope eVar, Promise... dazVarArr) {
        return new AndroidDeferredObject(super.mo3319a(dazVarArr), eVar).mo3300a();
    }
}
