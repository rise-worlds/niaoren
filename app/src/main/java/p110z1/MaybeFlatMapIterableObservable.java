package p110z1;

import java.util.Iterator;

/* renamed from: z1.bfu */
/* loaded from: classes3.dex */
public final class MaybeFlatMapIterableObservable<T, R> extends Observable<R> {

    /* renamed from: a */
    final MaybeSource<T> f18618a;

    /* renamed from: b */
    final Function<? super T, ? extends Iterable<? extends R>> f18619b;

    public MaybeFlatMapIterableObservable(MaybeSource<T> asiVar, Function<? super T, ? extends Iterable<? extends R>> aunVar) {
        this.f18618a = asiVar;
        this.f18619b = aunVar;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    protected void mo34a(Observer<? super R> assVar) {
        this.f18618a.mo10646a(new C4292a(assVar, this.f18619b));
    }

    /* compiled from: MaybeFlatMapIterableObservable.java */
    /* renamed from: z1.bfu$a */
    /* loaded from: classes3.dex */
    static final class C4292a<T, R> extends BasicQueueDisposable<R> implements MaybeObserver<T> {

        /* renamed from: f */
        final Observer<? super R> f18620f;

        /* renamed from: g */
        final Function<? super T, ? extends Iterable<? extends R>> f18621g;

        /* renamed from: h */
        Disposable f18622h;

        /* renamed from: i */
        volatile Iterator<? extends R> f18623i;

        /* renamed from: j */
        volatile boolean f18624j;

        /* renamed from: k */
        boolean f18625k;

        C4292a(Observer<? super R> assVar, Function<? super T, ? extends Iterable<? extends R>> aunVar) {
            this.f18620f = assVar;
            this.f18621g = aunVar;
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.f18622h, atrVar)) {
                this.f18622h = atrVar;
                this.f18620f.onSubscribe(this);
            }
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSuccess(T t) {
            Observer<? super R> assVar = this.f18620f;
            try {
                Iterator<? extends R> it = ((Iterable) this.f18621g.apply(t)).iterator();
                if (!it.hasNext()) {
                    assVar.onComplete();
                    return;
                }
                this.f18623i = it;
                if (this.f18625k) {
                    assVar.onNext(null);
                    assVar.onComplete();
                    return;
                }
                while (!this.f18624j) {
                    try {
                        assVar.onNext((Object) it.next());
                        if (!this.f18624j) {
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
            } catch (Throwable th3) {
                Exceptions.m9983b(th3);
                assVar.onError(th3);
            }
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onError(Throwable th) {
            this.f18622h = DisposableHelper.DISPOSED;
            this.f18620f.onError(th);
        }

        @Override // p110z1.MaybeObserver
        public void onComplete() {
            this.f18620f.onComplete();
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.f18624j = true;
            this.f18622h.dispose();
            this.f18622h = DisposableHelper.DISPOSED;
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f18624j;
        }

        @Override // p110z1.QueueFuseable
        public int requestFusion(int i) {
            if ((i & 2) == 0) {
                return 0;
            }
            this.f18625k = true;
            return 2;
        }

        @Override // p110z1.SimpleQueue
        public void clear() {
            this.f18623i = null;
        }

        @Override // p110z1.SimpleQueue
        public boolean isEmpty() {
            return this.f18623i == null;
        }

        @Override // p110z1.SimpleQueue
        @atn
        public R poll() throws Exception {
            Iterator<? extends R> it = this.f18623i;
            if (it == null) {
                return null;
            }
            R r = (R) ObjectHelper.m9873a(it.next(), "The iterator returned a null value");
            if (!it.hasNext()) {
                this.f18623i = null;
            }
            return r;
        }
    }
}
