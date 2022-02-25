package p110z1;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/* renamed from: z1.dbg */
/* loaded from: classes3.dex */
public class FutureCallable<V> implements Callable<V> {

    /* renamed from: a */
    private final Future<V> f21255a;

    public FutureCallable(Future<V> future) {
        this.f21255a = future;
    }

    @Override // java.util.concurrent.Callable
    public V call() throws Exception {
        try {
            return this.f21255a.get();
        } catch (InterruptedException e) {
            throw e;
        } catch (ExecutionException e2) {
            if (e2.getCause() instanceof Exception) {
                throw ((Exception) e2.getCause());
            }
            throw e2;
        }
    }
}
