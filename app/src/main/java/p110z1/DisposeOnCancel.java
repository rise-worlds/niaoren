package p110z1;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* renamed from: z1.bqo */
/* loaded from: classes3.dex */
final class DisposeOnCancel implements Future<Object> {

    /* renamed from: a */
    final Disposable f19880a;

    @Override // java.util.concurrent.Future
    public Object get() throws InterruptedException, ExecutionException {
        return null;
    }

    @Override // java.util.concurrent.Future
    public Object get(long j, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
        return null;
    }

    @Override // java.util.concurrent.Future
    public boolean isCancelled() {
        return false;
    }

    @Override // java.util.concurrent.Future
    public boolean isDone() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DisposeOnCancel(Disposable atrVar) {
        this.f19880a = atrVar;
    }

    @Override // java.util.concurrent.Future
    public boolean cancel(boolean z) {
        this.f19880a.dispose();
        return false;
    }
}
