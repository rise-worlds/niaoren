package p110z1;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import p110z1.DeferredManager;

/* renamed from: z1.dba */
/* loaded from: classes3.dex */
public abstract class AbstractDeferredManager implements DeferredManager {

    /* renamed from: a */
    protected final Logger f21230a = LoggerFactory.getLogger(AbstractDeferredManager.class);

    @Override // p110z1.DeferredManager
    /* renamed from: a */
    public <D, F, P> Promise<D, F, P> mo3327a(Promise<D, F, P> dazVar) {
        return dazVar;
    }

    /* renamed from: a */
    public abstract boolean mo3310a();

    /* renamed from: b */
    protected abstract void mo3306b(Runnable runnable);

    /* renamed from: b */
    protected abstract void mo3305b(Callable callable);

    @Override // p110z1.DeferredManager
    /* renamed from: a */
    public Promise<MultipleResults, OneReject, MasterProgress> mo3325a(Runnable... runnableArr) {
        m3326a((Object[]) runnableArr);
        Promise[] dazVarArr = new Promise[runnableArr.length];
        for (int i = 0; i < runnableArr.length; i++) {
            if (runnableArr[i] instanceof DeferredRunnable) {
                dazVarArr[i] = mo3328a((DeferredRunnable) runnableArr[i]);
            } else {
                dazVarArr[i] = mo3333a(runnableArr[i]);
            }
        }
        return mo3319a(dazVarArr);
    }

    @Override // p110z1.DeferredManager
    /* renamed from: a */
    public Promise<MultipleResults, OneReject, MasterProgress> mo3324a(Callable<?>... callableArr) {
        m3326a((Object[]) callableArr);
        Promise[] dazVarArr = new Promise[callableArr.length];
        for (int i = 0; i < callableArr.length; i++) {
            if (callableArr[i] instanceof DeferredCallable) {
                dazVarArr[i] = mo3330a((DeferredCallable) callableArr[i]);
            } else {
                dazVarArr[i] = mo3332a(callableArr[i]);
            }
        }
        return mo3319a(dazVarArr);
    }

    @Override // p110z1.DeferredManager
    /* renamed from: a */
    public Promise<MultipleResults, OneReject, MasterProgress> mo3320a(DeferredRunnable<?>... dapVarArr) {
        m3326a((Object[]) dapVarArr);
        Promise[] dazVarArr = new Promise[dapVarArr.length];
        for (int i = 0; i < dapVarArr.length; i++) {
            dazVarArr[i] = mo3328a((DeferredRunnable) dapVarArr[i]);
        }
        return mo3319a(dazVarArr);
    }

    @Override // p110z1.DeferredManager
    /* renamed from: a */
    public Promise<MultipleResults, OneReject, MasterProgress> mo3322a(DeferredCallable<?, ?>... damVarArr) {
        m3326a((Object[]) damVarArr);
        Promise[] dazVarArr = new Promise[damVarArr.length];
        for (int i = 0; i < damVarArr.length; i++) {
            dazVarArr[i] = mo3330a((DeferredCallable) damVarArr[i]);
        }
        return mo3319a(dazVarArr);
    }

    @Override // p110z1.DeferredManager
    /* renamed from: a */
    public Promise<MultipleResults, OneReject, MasterProgress> mo3321a(DeferredFutureTask<?, ?>... danVarArr) {
        m3326a((Object[]) danVarArr);
        Promise[] dazVarArr = new Promise[danVarArr.length];
        for (int i = 0; i < danVarArr.length; i++) {
            dazVarArr[i] = mo3329a((DeferredFutureTask) danVarArr[i]);
        }
        return mo3319a(dazVarArr);
    }

    @Override // p110z1.DeferredManager
    /* renamed from: a */
    public Promise<MultipleResults, OneReject, MasterProgress> mo3323a(Future<?>... futureArr) {
        m3326a((Object[]) futureArr);
        Promise[] dazVarArr = new Promise[futureArr.length];
        for (int i = 0; i < futureArr.length; i++) {
            dazVarArr[i] = mo3331a(futureArr[i]);
        }
        return mo3319a(dazVarArr);
    }

    @Override // p110z1.DeferredManager
    /* renamed from: a */
    public Promise<MultipleResults, OneReject, MasterProgress> mo3319a(Promise... dazVarArr) {
        m3326a((Object[]) dazVarArr);
        return new MasterDeferredObject(dazVarArr).mo3300a();
    }

    @Override // p110z1.DeferredManager
    /* renamed from: a */
    public <P> Promise<Void, Throwable, P> mo3328a(DeferredRunnable<P> dapVar) {
        return mo3329a(new DeferredFutureTask((DeferredRunnable) dapVar));
    }

    @Override // p110z1.DeferredManager
    /* renamed from: a */
    public <D, P> Promise<D, Throwable, P> mo3330a(DeferredCallable<D, P> damVar) {
        return mo3329a((DeferredFutureTask) new DeferredFutureTask<>((DeferredCallable) damVar));
    }

    @Override // p110z1.DeferredManager
    /* renamed from: a */
    public Promise<Void, Throwable, Void> mo3333a(Runnable runnable) {
        return mo3329a(new DeferredFutureTask(runnable));
    }

    @Override // p110z1.DeferredManager
    /* renamed from: a */
    public <D> Promise<D, Throwable, Void> mo3332a(Callable<D> callable) {
        return mo3329a(new DeferredFutureTask(callable));
    }

    @Override // p110z1.DeferredManager
    /* renamed from: a */
    public <D, P> Promise<D, Throwable, P> mo3329a(DeferredFutureTask<D, P> danVar) {
        if (danVar.m3342b() == DeferredManager.EnumC5238a.AUTO || (danVar.m3342b() == DeferredManager.EnumC5238a.DEFAULT && mo3310a())) {
            mo3306b(danVar);
        }
        return danVar.m3343a();
    }

    @Override // p110z1.DeferredManager
    /* renamed from: a */
    public <D> Promise<D, Throwable, Void> mo3331a(final Future<D> future) {
        return mo3330a((DeferredCallable) new DeferredCallable<D, Void>(DeferredManager.EnumC5238a.AUTO) { // from class: z1.dba.1
            /* JADX WARN: Type inference failed for: r0v5, types: [D, java.lang.Object] */
            @Override // java.util.concurrent.Callable
            public D call() throws Exception {
                try {
                    return future.get();
                } catch (InterruptedException e) {
                    throw e;
                } catch (ExecutionException e2) {
                    if (e2.getCause() instanceof Exception) {
                        throw ((Exception) e2.getCause());
                    }
                    throw e2;
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public void m3326a(Object[] objArr) {
        if (objArr == null || objArr.length == 0) {
            throw new IllegalArgumentException("Arguments is null or its length is empty");
        }
    }
}
