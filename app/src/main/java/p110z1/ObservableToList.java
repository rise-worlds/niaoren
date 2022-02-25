package p110z1;

import java.util.Collection;
import java.util.concurrent.Callable;

/* renamed from: z1.bnf */
/* loaded from: classes3.dex */
public final class ObservableToList<T, U extends Collection<? super T>> extends AbstractObservableWithUpstream<T, U> {

    /* renamed from: b */
    final Callable<U> f19467b;

    public ObservableToList(ObservableSource<T> asqVar, int i) {
        super(asqVar);
        this.f19467b = Functions.m9934a(i);
    }

    public ObservableToList(ObservableSource<T> asqVar, Callable<U> callable) {
        super(asqVar);
        this.f19467b = callable;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    public void mo34a(Observer<? super U> assVar) {
        try {
            this.f18807a.subscribe(new C4589a(assVar, (Collection) ObjectHelper.m9873a(this.f19467b.call(), "The collectionSupplier returned a null collection. Null values are generally not allowed in 2.x operators and sources.")));
        } catch (Throwable th) {
            Exceptions.m9983b(th);
            EmptyDisposable.error(th, assVar);
        }
    }

    /* compiled from: ObservableToList.java */
    /* renamed from: z1.bnf$a */
    /* loaded from: classes3.dex */
    static final class C4589a<T, U extends Collection<? super T>> implements Observer<T>, Disposable {

        /* renamed from: a */
        final Observer<? super U> f19468a;

        /* renamed from: b */
        Disposable f19469b;

        /* renamed from: c */
        U f19470c;

        C4589a(Observer<? super U> assVar, U u) {
            this.f19468a = assVar;
            this.f19470c = u;
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.f19469b, atrVar)) {
                this.f19469b = atrVar;
                this.f19468a.onSubscribe(this);
            }
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.f19469b.dispose();
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f19469b.isDisposed();
        }

        @Override // p110z1.Observer
        public void onNext(T t) {
            this.f19470c.add(t);
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            this.f19470c = null;
            this.f19468a.onError(th);
        }

        @Override // p110z1.Observer
        public void onComplete() {
            U u = this.f19470c;
            this.f19470c = null;
            this.f19468a.onNext(u);
            this.f19468a.onComplete();
        }
    }
}
