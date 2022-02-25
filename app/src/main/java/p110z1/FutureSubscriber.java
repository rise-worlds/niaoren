package p110z1;

import java.util.NoSuchElementException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bro */
/* loaded from: classes3.dex */
public final class FutureSubscriber<T> extends CountDownLatch implements Future<T>, FlowableSubscriber<T>, dby {

    /* renamed from: a */
    T f20000a;

    /* renamed from: b */
    Throwable f20001b;

    /* renamed from: c */
    final AtomicReference<dby> f20002c = new AtomicReference<>();

    @Override // p110z1.dby
    public void cancel() {
    }

    @Override // p110z1.dby
    public void request(long j) {
    }

    public FutureSubscriber() {
        super(1);
    }

    @Override // java.util.concurrent.Future
    public boolean cancel(boolean z) {
        dby dbyVar;
        do {
            dbyVar = this.f20002c.get();
            if (dbyVar == this || dbyVar == SubscriptionHelper.CANCELLED) {
                return false;
            }
        } while (!this.f20002c.compareAndSet(dbyVar, SubscriptionHelper.CANCELLED));
        if (dbyVar != null) {
            dbyVar.cancel();
        }
        countDown();
        return true;
    }

    @Override // java.util.concurrent.Future
    public boolean isCancelled() {
        return this.f20002c.get() == SubscriptionHelper.CANCELLED;
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
            Throwable th = this.f20001b;
            if (th == null) {
                return this.f20000a;
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
            Throwable th = this.f20001b;
            if (th == null) {
                return this.f20000a;
            }
            throw new ExecutionException(th);
        }
        throw new CancellationException();
    }

    @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
    public void onSubscribe(dby dbyVar) {
        SubscriptionHelper.setOnce(this.f20002c, dbyVar, cjm.f20759b);
    }

    @Override // p110z1.Subscriber
    public void onNext(T t) {
        if (this.f20000a != null) {
            this.f20002c.get().cancel();
            onError(new IndexOutOfBoundsException("More than one element received"));
            return;
        }
        this.f20000a = t;
    }

    @Override // p110z1.Subscriber
    public void onError(Throwable th) {
        dby dbyVar;
        do {
            dbyVar = this.f20002c.get();
            if (dbyVar == this || dbyVar == SubscriptionHelper.CANCELLED) {
                RxJavaPlugins.m9212a(th);
                return;
            }
            this.f20001b = th;
        } while (!this.f20002c.compareAndSet(dbyVar, this));
        countDown();
    }

    @Override // p110z1.Subscriber
    public void onComplete() {
        dby dbyVar;
        if (this.f20000a == null) {
            onError(new NoSuchElementException("The source is empty"));
            return;
        }
        do {
            dbyVar = this.f20002c.get();
            if (dbyVar == this || dbyVar == SubscriptionHelper.CANCELLED) {
                return;
            }
        } while (!this.f20002c.compareAndSet(dbyVar, this));
        countDown();
    }
}
