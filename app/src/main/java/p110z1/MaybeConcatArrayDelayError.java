package p110z1;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bew */
/* loaded from: classes3.dex */
public final class MaybeConcatArrayDelayError<T> extends Flowable<T> {

    /* renamed from: b */
    final MaybeSource<? extends T>[] f18543b;

    public MaybeConcatArrayDelayError(MaybeSource<? extends T>[] asiVarArr) {
        this.f18543b = asiVarArr;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    protected void mo9054d(Subscriber<? super T> dbxVar) {
        C4264a aVar = new C4264a(dbxVar, this.f18543b);
        dbxVar.onSubscribe(aVar);
        aVar.drain();
    }

    /* compiled from: MaybeConcatArrayDelayError.java */
    /* renamed from: z1.bew$a */
    /* loaded from: classes3.dex */
    static final class C4264a<T> extends AtomicInteger implements MaybeObserver<T>, dby {
        private static final long serialVersionUID = 3520831347801429610L;
        final Subscriber<? super T> downstream;
        int index;
        long produced;
        final MaybeSource<? extends T>[] sources;
        final AtomicLong requested = new AtomicLong();
        final SequentialDisposable disposables = new SequentialDisposable();
        final AtomicReference<Object> current = new AtomicReference<>(NotificationLite.COMPLETE);
        final AtomicThrowable errors = new AtomicThrowable();

        C4264a(Subscriber<? super T> dbxVar, MaybeSource<? extends T>[] asiVarArr) {
            this.downstream = dbxVar;
            this.sources = asiVarArr;
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
            this.current.lazySet(NotificationLite.COMPLETE);
            if (this.errors.addThrowable(th)) {
                drain();
            } else {
                RxJavaPlugins.m9212a(th);
            }
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
                            int i = this.index;
                            MaybeSource<? extends T>[] asiVarArr = this.sources;
                            if (i != asiVarArr.length) {
                                this.index = i + 1;
                                asiVarArr[i].mo10646a(this);
                            } else if (this.errors.get() != null) {
                                dbxVar.onError(this.errors.terminate());
                                return;
                            } else {
                                dbxVar.onComplete();
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
