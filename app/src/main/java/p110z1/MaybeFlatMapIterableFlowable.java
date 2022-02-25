package p110z1;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicLong;

/* renamed from: z1.bft */
/* loaded from: classes3.dex */
public final class MaybeFlatMapIterableFlowable<T, R> extends Flowable<R> {

    /* renamed from: b */
    final MaybeSource<T> f18615b;

    /* renamed from: c */
    final Function<? super T, ? extends Iterable<? extends R>> f18616c;

    public MaybeFlatMapIterableFlowable(MaybeSource<T> asiVar, Function<? super T, ? extends Iterable<? extends R>> aunVar) {
        this.f18615b = asiVar;
        this.f18616c = aunVar;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    protected void mo9054d(Subscriber<? super R> dbxVar) {
        this.f18615b.mo10646a(new C4291a(dbxVar, this.f18616c));
    }

    /* compiled from: MaybeFlatMapIterableFlowable.java */
    /* renamed from: z1.bft$a */
    /* loaded from: classes3.dex */
    static final class C4291a<T, R> extends BasicIntQueueSubscription<R> implements MaybeObserver<T> {
        private static final long serialVersionUID = -8938804753851907758L;
        volatile boolean cancelled;
        final Subscriber<? super R> downstream;

        /* renamed from: it */
        volatile Iterator<? extends R> f18617it;
        final Function<? super T, ? extends Iterable<? extends R>> mapper;
        boolean outputFused;
        final AtomicLong requested = new AtomicLong();
        Disposable upstream;

        C4291a(Subscriber<? super R> dbxVar, Function<? super T, ? extends Iterable<? extends R>> aunVar) {
            this.downstream = dbxVar;
            this.mapper = aunVar;
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.upstream, atrVar)) {
                this.upstream = atrVar;
                this.downstream.onSubscribe(this);
            }
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSuccess(T t) {
            try {
                Iterator<? extends R> it = ((Iterable) this.mapper.apply(t)).iterator();
                if (!it.hasNext()) {
                    this.downstream.onComplete();
                    return;
                }
                this.f18617it = it;
                drain();
            } catch (Throwable th) {
                Exceptions.m9983b(th);
                this.downstream.onError(th);
            }
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onError(Throwable th) {
            this.upstream = DisposableHelper.DISPOSED;
            this.downstream.onError(th);
        }

        @Override // p110z1.MaybeObserver
        public void onComplete() {
            this.downstream.onComplete();
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
            this.cancelled = true;
            this.upstream.dispose();
            this.upstream = DisposableHelper.DISPOSED;
        }

        void fastPath(Subscriber<? super R> dbxVar, Iterator<? extends R> it) {
            while (!this.cancelled) {
                try {
                    dbxVar.onNext((Object) it.next());
                    if (!this.cancelled) {
                        try {
                            if (!it.hasNext()) {
                                dbxVar.onComplete();
                                return;
                            }
                        } catch (Throwable th) {
                            Exceptions.m9983b(th);
                            dbxVar.onError(th);
                            return;
                        }
                    } else {
                        return;
                    }
                } catch (Throwable th2) {
                    Exceptions.m9983b(th2);
                    dbxVar.onError(th2);
                    return;
                }
            }
        }

        void drain() {
            if (getAndIncrement() == 0) {
                Subscriber<? super R> dbxVar = this.downstream;
                Iterator<? extends R> it = this.f18617it;
                if (!this.outputFused || it == null) {
                    int i = 1;
                    while (true) {
                        if (it != null) {
                            long j = this.requested.get();
                            if (j == cjm.f20759b) {
                                fastPath(dbxVar, it);
                                return;
                            }
                            long j2 = 0;
                            while (j2 != j) {
                                if (!this.cancelled) {
                                    try {
                                        dbxVar.onNext((Object) ObjectHelper.m9873a(it.next(), "The iterator returned a null value"));
                                        if (!this.cancelled) {
                                            j2++;
                                            try {
                                                if (!it.hasNext()) {
                                                    dbxVar.onComplete();
                                                    return;
                                                }
                                            } catch (Throwable th) {
                                                Exceptions.m9983b(th);
                                                dbxVar.onError(th);
                                                return;
                                            }
                                        } else {
                                            return;
                                        }
                                    } catch (Throwable th2) {
                                        Exceptions.m9983b(th2);
                                        dbxVar.onError(th2);
                                        return;
                                    }
                                } else {
                                    return;
                                }
                            }
                            if (j2 != 0) {
                                BackpressureHelper.m9446c(this.requested, j2);
                            }
                        }
                        i = addAndGet(-i);
                        if (i != 0) {
                            if (it == null) {
                                it = this.f18617it;
                            }
                        } else {
                            return;
                        }
                    }
                } else {
                    dbxVar.onNext(null);
                    dbxVar.onComplete();
                }
            }
        }

        @Override // p110z1.QueueFuseable
        public int requestFusion(int i) {
            if ((i & 2) == 0) {
                return 0;
            }
            this.outputFused = true;
            return 2;
        }

        @Override // p110z1.SimpleQueue
        public void clear() {
            this.f18617it = null;
        }

        @Override // p110z1.SimpleQueue
        public boolean isEmpty() {
            return this.f18617it == null;
        }

        @Override // p110z1.SimpleQueue
        @atn
        public R poll() throws Exception {
            Iterator<? extends R> it = this.f18617it;
            if (it == null) {
                return null;
            }
            R r = (R) ObjectHelper.m9873a(it.next(), "The iterator returned a null value");
            if (!it.hasNext()) {
                this.f18617it = null;
            }
            return r;
        }
    }
}
