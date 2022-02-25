package p110z1;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/* renamed from: z1.dao */
/* loaded from: classes3.dex */
public interface DeferredManager {

    /* compiled from: DeferredManager.java */
    /* renamed from: z1.dao$a */
    /* loaded from: classes3.dex */
    public enum EnumC5238a {
        DEFAULT,
        AUTO,
        MANAUL
    }

    /* renamed from: a */
    Promise<Void, Throwable, Void> mo3333a(Runnable runnable);

    /* renamed from: a */
    <D> Promise<D, Throwable, Void> mo3332a(Callable<D> callable);

    /* renamed from: a */
    <D> Promise<D, Throwable, Void> mo3331a(Future<D> future);

    /* renamed from: a */
    <D, P> Promise<D, Throwable, P> mo3330a(DeferredCallable<D, P> damVar);

    /* renamed from: a */
    <D, P> Promise<D, Throwable, P> mo3329a(DeferredFutureTask<D, P> danVar);

    /* renamed from: a */
    <P> Promise<Void, Throwable, P> mo3328a(DeferredRunnable<P> dapVar);

    /* renamed from: a */
    <D, F, P> Promise<D, F, P> mo3327a(Promise<D, F, P> dazVar);

    /* renamed from: a */
    Promise<MultipleResults, OneReject, MasterProgress> mo3325a(Runnable... runnableArr);

    /* renamed from: a */
    Promise<MultipleResults, OneReject, MasterProgress> mo3324a(Callable<?>... callableArr);

    /* renamed from: a */
    Promise<MultipleResults, OneReject, MasterProgress> mo3323a(Future<?>... futureArr);

    /* renamed from: a */
    Promise<MultipleResults, OneReject, MasterProgress> mo3322a(DeferredCallable<?, ?>... damVarArr);

    /* renamed from: a */
    Promise<MultipleResults, OneReject, MasterProgress> mo3321a(DeferredFutureTask<?, ?>... danVarArr);

    /* renamed from: a */
    Promise<MultipleResults, OneReject, MasterProgress> mo3320a(DeferredRunnable<?>... dapVarArr);

    /* renamed from: a */
    Promise<MultipleResults, OneReject, MasterProgress> mo3319a(Promise... dazVarArr);
}
