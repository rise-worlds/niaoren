package p110z1;

import java.util.Iterator;

/* renamed from: z1.bpj */
/* loaded from: classes3.dex */
public final class SingleFlatMapIterableObservable<T, R> extends Observable<R> {

    /* renamed from: a */
    final SingleSource<T> f19784a;

    /* renamed from: b */
    final Function<? super T, ? extends Iterable<? extends R>> f19785b;

    public SingleFlatMapIterableObservable(SingleSource<T> ataVar, Function<? super T, ? extends Iterable<? extends R>> aunVar) {
        this.f19784a = ataVar;
        this.f19785b = aunVar;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    protected void mo34a(Observer<? super R> assVar) {
        this.f19784a.mo10018a(new C4680a(assVar, this.f19785b));
    }

    /* compiled from: SingleFlatMapIterableObservable.java */
    /* renamed from: z1.bpj$a */
    /* loaded from: classes3.dex */
    static final class C4680a<T, R> extends BasicIntQueueDisposable<R> implements SingleObserver<T> {
        private static final long serialVersionUID = -8938804753851907758L;
        volatile boolean cancelled;
        final Observer<? super R> downstream;

        /* renamed from: it */
        volatile Iterator<? extends R> f19786it;
        final Function<? super T, ? extends Iterable<? extends R>> mapper;
        boolean outputFused;
        Disposable upstream;

        C4680a(Observer<? super R> assVar, Function<? super T, ? extends Iterable<? extends R>> aunVar) {
            this.downstream = assVar;
            this.mapper = aunVar;
        }

        @Override // p110z1.SingleObserver
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.upstream, atrVar)) {
                this.upstream = atrVar;
                this.downstream.onSubscribe(this);
            }
        }

        @Override // p110z1.SingleObserver
        public void onSuccess(T t) {
            Observer<? super R> assVar = this.downstream;
            try {
                Iterator<? extends R> it = ((Iterable) this.mapper.apply(t)).iterator();
                if (!it.hasNext()) {
                    assVar.onComplete();
                } else if (this.outputFused) {
                    this.f19786it = it;
                    assVar.onNext(null);
                    assVar.onComplete();
                } else {
                    while (!this.cancelled) {
                        try {
                            assVar.onNext((Object) it.next());
                            if (!this.cancelled) {
                                try {
                                    if (!it.hasNext()) {
                                        assVar.onComplete();
                                        return;
                                    }
                                } catch (Throwable th) {
                                    Exceptions.m9983b(th);
                                    assVar.onError(th);
                                    return;
                                }
                            } else {
                                return;
                            }
                        } catch (Throwable th2) {
                            Exceptions.m9983b(th2);
                            assVar.onError(th2);
                            return;
                        }
                    }
                }
            } catch (Throwable th3) {
                Exceptions.m9983b(th3);
                this.downstream.onError(th3);
            }
        }

        @Override // p110z1.SingleObserver
        public void onError(Throwable th) {
            this.upstream = DisposableHelper.DISPOSED;
            this.downstream.onError(th);
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.cancelled = true;
            this.upstream.dispose();
            this.upstream = DisposableHelper.DISPOSED;
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.cancelled;
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
            this.f19786it = null;
        }

        @Override // p110z1.SimpleQueue
        public boolean isEmpty() {
            return this.f19786it == null;
        }

        @Override // p110z1.SimpleQueue
        @atn
        public R poll() throws Exception {
            Iterator<? extends R> it = this.f19786it;
            if (it == null) {
                return null;
            }
            R r = (R) ObjectHelper.m9873a(it.next(), "The iterator returned a null value");
            if (!it.hasNext()) {
                this.f19786it = null;
            }
            return r;
        }
    }
}
