package p110z1;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bex */
/* loaded from: classes3.dex */
public final class MaybeConcatIterable<T> extends Flowable<T> {

    /* renamed from: b */
    final Iterable<? extends MaybeSource<? extends T>> f18544b;

    public MaybeConcatIterable(Iterable<? extends MaybeSource<? extends T>> iterable) {
        this.f18544b = iterable;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    protected void mo9054d(Subscriber<? super T> dbxVar) {
        try {
            C4265a aVar = new C4265a(dbxVar, (Iterator) ObjectHelper.m9873a(this.f18544b.iterator(), "The sources Iterable returned a null Iterator"));
            dbxVar.onSubscribe(aVar);
            aVar.drain();
        } catch (Throwable th) {
            Exceptions.m9983b(th);
            EmptySubscription.error(th, dbxVar);
        }
    }

    /* compiled from: MaybeConcatIterable.java */
    /* renamed from: z1.bex$a */
    /* loaded from: classes3.dex */
    static final class C4265a<T> extends AtomicInteger implements MaybeObserver<T>, dby {
        private static final long serialVersionUID = 3520831347801429610L;
        final Subscriber<? super T> downstream;
        long produced;
        final Iterator<? extends MaybeSource<? extends T>> sources;
        final AtomicLong requested = new AtomicLong();
        final SequentialDisposable disposables = new SequentialDisposable();
        final AtomicReference<Object> current = new AtomicReference<>(NotificationLite.COMPLETE);

        C4265a(Subscriber<? super T> dbxVar, Iterator<? extends MaybeSource<? extends T>> it) {
            this.downstream = dbxVar;
            this.sources = it;
        }

        @Override // p110z1.dby
        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                BackpressureHelper.m9449a(this.requested, j);
                drain();
            }
        }

        @Override // p110z1.dby
        public void cancel() {
            this.disposables.dispose();
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSubscribe(Disposable atrVar) {
            this.disposables.replace(atrVar);
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSuccess(T t) {
            this.current.lazySet(t);
            drain();
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onError(Throwable th) {
            this.downstream.onError(th);
        }

        @Override // p110z1.MaybeObserver
        public void onComplete() {
            this.current.lazySet(NotificationLite.COMPLETE);
            drain();
        }

        void drain() {
            if (getAndIncrement() == 0) {
                AtomicReference<Object> atomicReference = this.current;
                Subscriber<? super T> dbxVar = this.downstream;
                SequentialDisposable avfVar = this.disposables;
                while (!avfVar.isDisposed()) {
                    Object obj = atomicReference.get();
                    if (obj != null) {
                        boolean z = true;
                        if (obj != NotificationLite.COMPLETE) {
                            long j = this.produced;
                            if (j != this.requested.get()) {
                                this.produced = j + 1;
                                atomicReference.lazySet(null);
                                dbxVar.onNext(obj);
                            } else {
                                z = false;
                            }
                        } else {
                            atomicReference.lazySet(null);
                        }
                        if (z && !avfVar.isDisposed()) {
                            try {
                                if (this.sources.hasNext()) {
                                    try {
                                        ((MaybeSource) ObjectHelper.m9873a(this.sources.next(), "The source Iterator returned a null MaybeSource")).mo10646a(this);
                                    } catch (Throwable th) {
                                        Exceptions.m9983b(th);
                                        dbxVar.onError(th);
                                        return;
                                    }
                                } else {
                                    dbxVar.onComplete();
                                }
                            } catch (Throwable th2) {
                                Exceptions.m9983b(th2);
                                dbxVar.onError(th2);
                                return;
                            }
                        }
                    }
                    if (decrementAndGet() == 0) {
                        return;
                    }
                }
                atomicReference.lazySet(null);
            }
        }
    }
}
