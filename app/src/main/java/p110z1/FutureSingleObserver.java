package p110z1;

import java.util.concurrent.CancellationException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.awo */
/* loaded from: classes3.dex */
public final class FutureSingleObserver<T> extends CountDownLatch implements Future<T>, SingleObserver<T>, Disposable {

    /* renamed from: a */
    T f17629a;

    /* renamed from: b */
    Throwable f17630b;

    /* renamed from: c */
    final AtomicReference<Disposable> f17631c = new AtomicReference<>();

    @Override // p110z1.Disposable
    public void dispose() {
    }

    public FutureSingleObserver() {
        super(1);
    }

    @Override // java.util.concurrent.Future
    public boolean cancel(boolean z) {
        Disposable atrVar;
        do {
            atrVar = this.f17631c.get();
            if (atrVar == this || atrVar == DisposableHelper.DISPOSED) {
                return false;
            }
        } while (!this.f17631c.compareAndSet(atrVar, DisposableHelper.DISPOSED));
        if (atrVar != null) {
            atrVar.dispose();
        }
        countDown();
        return true;
    }

    @Override // java.util.concurrent.Future
    public boolean isCancelled() {
        return DisposableHelper.isDisposed(this.f17631c.get());
    }

    @Override // java.util.concurrent.Future
    public boolean isDone() {
        return getCount() == 0;
    }

    @Override // java.util.concurrent.Future
    public T get() throws InterruptedException, ExecutionException {
        if (getCount() != 0) {
            BlockingHelper.m9444a();
            await();
        }
        if (!isCancelled()) {
            Throwable th = this.f17630b;
            if (th == null) {
                return this.f17629a;
            }
            throw new ExecutionException(th);
        }
        throw new CancellationException();
    }

    @Override // java.util.concurrent.Future
    public T get(long j, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
        if (getCount() != 0) {
            BlockingHelper.m9444a();
            if (!await(j, timeUnit)) {
                throw new TimeoutException(ExceptionHelper.m9433a(j, timeUnit));
            }
        }
        if (!isCancelled()) {
            Throwable th = this.f17630b;
            if (th == null) {
                return this.f17629a;
            }
            throw new ExecutionException(th);
        }
        throw new CancellationException();
    }

    @Override // p110z1.SingleObserver
    public void onSubscribe(Disposable atrVar) {
        DisposableHelper.setOnce(this.f17631c, atrVar);
    }

    @Override // p110z1.SingleObserver
    public void onSuccess(T t) {
        Disposable atrVar = this.f17631c.get();
        if (atrVar != DisposableHelper.DISPOSED) {
            this.f17629a = t;
            this.f17631c.compareAndSet(atrVar, this);
            countDown();
        }
    }

    @Override // p110z1.SingleObserver
    public void onError(Throwable th) {
        Disposable atrVar;
        do {
            atrVar = this.f17631c.get();
            if (atrVar == DisposableHelper.DISPOSED) {
                RxJavaPlugins.m9212a(th);
                return;
            }
            this.f17630b = th;
        } while (!this.f17631c.compareAndSet(atrVar, this));
        countDown();
    }

    @Override // p110z1.Disposable
    public boolean isDisposed() {
        return isDone();
    }
}
