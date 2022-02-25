package p110z1;

import java.util.concurrent.Callable;

/* renamed from: z1.bme */
/* loaded from: classes3.dex */
public final class ObservableScanSeed<T, R> extends AbstractObservableWithUpstream<T, R> {

    /* renamed from: b */
    final BiFunction<R, ? super T, R> f19335b;

    /* renamed from: c */
    final Callable<R> f19336c;

    public ObservableScanSeed(ObservableSource<T> asqVar, Callable<R> callable, BiFunction<R, ? super T, R> auiVar) {
        super(asqVar);
        this.f19335b = auiVar;
        this.f19336c = callable;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    public void mo34a(Observer<? super R> assVar) {
        try {
            this.f18807a.subscribe(new C4549a(assVar, this.f19335b, ObjectHelper.m9873a(this.f19336c.call(), "The seed supplied is null")));
        } catch (Throwable th) {
            Exceptions.m9983b(th);
            EmptyDisposable.error(th, assVar);
        }
    }

    /* compiled from: ObservableScanSeed.java */
    /* renamed from: z1.bme$a */
    /* loaded from: classes3.dex */
    static final class C4549a<T, R> implements Observer<T>, Disposable {

        /* renamed from: a */
        final Observer<? super R> f19337a;

        /* renamed from: b */
        final BiFunction<R, ? super T, R> f19338b;

        /* renamed from: c */
        R f19339c;

        /* renamed from: d */
        Disposable f19340d;

        /* renamed from: e */
        boolean f19341e;

        C4549a(Observer<? super R> assVar, BiFunction<R, ? super T, R> auiVar, R r) {
            this.f19337a = assVar;
            this.f19338b = auiVar;
            this.f19339c = r;
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.f19340d, atrVar)) {
                this.f19340d = atrVar;
                this.f19337a.onSubscribe(this);
                this.f19337a.onNext((R) this.f19339c);
            }
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.f19340d.dispose();
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f19340d.isDisposed();
        }

        @Override // p110z1.Observer
        public void onNext(T t) {
            if (!this.f19341e) {
                try {
                    R r = (R) ObjectHelper.m9873a(this.f19338b.apply(this.f19339c, t), "The accumulator returned a null value");
                    this.f19339c = r;
                    this.f19337a.onNext(r);
                } catch (Throwable th) {
                    Exceptions.m9983b(th);
                    this.f19340d.dispose();
                    onError(th);
                }
            }
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            if (this.f19341e) {
                RxJavaPlugins.m9212a(th);
                return;
            }
            this.f19341e = true;
            this.f19337a.onError(th);
        }

        @Override // p110z1.Observer
        public void onComplete() {
            if (!this.f19341e) {
                this.f19341e = true;
                this.f19337a.onComplete();
            }
        }
    }
}
