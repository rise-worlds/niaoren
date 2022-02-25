package p110z1;

import java.util.NoSuchElementException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.awn */
/* loaded from: classes3.dex */
public final class FutureObserver<T> extends CountDownLatch implements Future<T>, Observer<T>, Disposable {

    /* renamed from: a */
    T f17626a;

    /* renamed from: b */
    Throwable f17627b;

    /* renamed from: c */
    final AtomicReference<Disposable> f17628c = new AtomicReference<>();

    @Override // p110z1.Disposable
    public void dispose() {
    }

    public FutureObserver() {
        super(1);
    }

    @Override // java.util.concurrent.Future
    public boolean cancel(boolean z) {
        Disposable atrVar;
        do {
            atrVar = this.f17628c.get();
            if (atrVar == this || atrVar == DisposableHelper.DISPOSED) {
                return false;
            }
        } while (!this.f17628c.compareAndSet(atrVar, DisposableHelper.DISPOSED));
        if (atrVar != null) {
            atrVar.dispose();
        }
        countDown();
        return true;
    }

    @Override // java.util.concurrent.Future
    public boolean isCancelled() {
        return DisposableHelper.isDisposed(this.f17628c.get());
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
            Throwable th = this.f17627b;
            if (th == null) {
                return this.f17626a;
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
            Throwable th = this.f17627b;
            if (th == null) {
                return this.f17626a;
            }
            throw new ExecutionException(th);
        }
        throw new CancellationException();
    }

    @Override // p110z1.Observer
    public void onSubscribe(Disposable atrVar) {
        DisposableHelper.setOnce(this.f17628c, atrVar);
    }

    @Override // p110z1.Observer
    public void onNext(T t) {
        if (this.f17626a != null) {
            this.f17628c.get().dispose();
            onError(new IndexOutOfBoundsException("More than one element received"));
            return;
        }
        this.f17626a = t;
    }

    @Override // p110z1.Observer
    public void onError(Throwable th) {
        Disposable atrVar;
        if (this.f17627b == null) {
            this.f17627b = th;
            do {
                atrVar = this.f17628c.get();
                if (atrVar == this || atrVar == DisposableHelper.DISPOSED) {
                    RxJavaPlugins.m9212a(th);
                    return;
                }
            } while (!this.f17628c.compareAndSet(atrVar, this));
            countDown();
            return;
        }
        RxJavaPlugins.m9212a(th);
    }

    @Override // p110z1.Observer
    public void onComplete() {
        Disposable atrVar;
        if (this.f17626a == null) {
            onError(new NoSuchElementException("The source is empty"));
            return;
        }
        do {
            atrVar = this.f17628c.get();
            if (atrVar == this || atrVar == DisposableHelper.DISPOSED) {
                return;
            }
        } while (!this.f17628c.compareAndSet(atrVar, this));
        countDown();
    }

    @Override // p110z1.Disposable
    public boolean isDisposed() {
        return isDone();
    }
}
