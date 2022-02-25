package p110z1;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bno */
/* loaded from: classes3.dex */
public final class ObservableWithLatestFrom<T, U, R> extends AbstractObservableWithUpstream<T, R> {

    /* renamed from: b */
    final BiFunction<? super T, ? super U, ? extends R> f19554b;

    /* renamed from: c */
    final ObservableSource<? extends U> f19555c;

    public ObservableWithLatestFrom(ObservableSource<T> asqVar, BiFunction<? super T, ? super U, ? extends R> auiVar, ObservableSource<? extends U> asqVar2) {
        super(asqVar);
        this.f19554b = auiVar;
        this.f19555c = asqVar2;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    public void mo34a(Observer<? super R> assVar) {
        SerializedObserver btyVar = new SerializedObserver(assVar);
        C4610a aVar = new C4610a(btyVar, this.f19554b);
        btyVar.onSubscribe(aVar);
        this.f19555c.subscribe(new C4611b(aVar));
        this.f18807a.subscribe(aVar);
    }

    /* compiled from: ObservableWithLatestFrom.java */
    /* renamed from: z1.bno$a */
    /* loaded from: classes3.dex */
    static final class C4610a<T, U, R> extends AtomicReference<U> implements Observer<T>, Disposable {
        private static final long serialVersionUID = -312246233408980075L;
        final BiFunction<? super T, ? super U, ? extends R> combiner;
        final Observer<? super R> downstream;
        final AtomicReference<Disposable> upstream = new AtomicReference<>();
        final AtomicReference<Disposable> other = new AtomicReference<>();

        C4610a(Observer<? super R> assVar, BiFunction<? super T, ? super U, ? extends R> auiVar) {
            this.downstream = assVar;
            this.combiner = auiVar;
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            DisposableHelper.setOnce(this.upstream, atrVar);
        }

        @Override // p110z1.Observer
        public void onNext(T t) {
            U u = get();
            if (u != null) {
                try {
                    this.downstream.onNext(ObjectHelper.m9873a(this.combiner.apply(t, u), "The combiner returned a null value"));
                } catch (Throwable th) {
                    Exceptions.m9983b(th);
                    dispose();
                    this.downstream.onError(th);
                }
            }
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            DisposableHelper.dispose(this.other);
            this.downstream.onError(th);
        }

        @Override // p110z1.Observer
        public void onComplete() {
            DisposableHelper.dispose(this.other);
            this.downstream.onComplete();
        }

        @Override // p110z1.Disposable
        public void dispose() {
            DisposableHelper.dispose(this.upstream);
            DisposableHelper.dispose(this.other);
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return DisposableHelper.isDisposed(this.upstream.get());
        }

        public boolean setOther(Disposable atrVar) {
            return DisposableHelper.setOnce(this.other, atrVar);
        }

        public void otherError(Throwable th) {
            DisposableHelper.dispose(this.upstream);
            this.downstream.onError(th);
        }
    }

    /* compiled from: ObservableWithLatestFrom.java */
    /* renamed from: z1.bno$b */
    /* loaded from: classes3.dex */
    final class C4611b implements Observer<U> {

        /* renamed from: b */
        private final C4610a<T, U, R> f19557b;

        @Override // p110z1.Observer
        public void onComplete() {
        }

        C4611b(C4610a<T, U, R> aVar) {
            this.f19557b = aVar;
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            this.f19557b.setOther(atrVar);
        }

        @Override // p110z1.Observer
        public void onNext(U u) {
            this.f19557b.lazySet(u);
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            this.f19557b.otherError(th);
        }
    }
}
