package p110z1;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bph */
/* loaded from: classes3.dex */
public final class SingleFlatMapCompletable<T> extends Completable {

    /* renamed from: a */
    final SingleSource<T> f19779a;

    /* renamed from: b */
    final Function<? super T, ? extends CompletableSource> f19780b;

    public SingleFlatMapCompletable(SingleSource<T> ataVar, Function<? super T, ? extends CompletableSource> aunVar) {
        this.f19779a = ataVar;
        this.f19780b = aunVar;
    }

    @Override // p110z1.Completable
    /* renamed from: b */
    protected void mo9001b(CompletableObserver arpVar) {
        C4678a aVar = new C4678a(arpVar, this.f19780b);
        arpVar.onSubscribe(aVar);
        this.f19779a.mo10018a(aVar);
    }

    /* compiled from: SingleFlatMapCompletable.java */
    /* renamed from: z1.bph$a */
    /* loaded from: classes3.dex */
    static final class C4678a<T> extends AtomicReference<Disposable> implements CompletableObserver, SingleObserver<T>, Disposable {
        private static final long serialVersionUID = -2177128922851101253L;
        final CompletableObserver downstream;
        final Function<? super T, ? extends CompletableSource> mapper;

        C4678a(CompletableObserver arpVar, Function<? super T, ? extends CompletableSource> aunVar) {
            this.downstream = arpVar;
            this.mapper = aunVar;
        }

        @Override // p110z1.Disposable
        public void dispose() {
            DisposableHelper.dispose(this);
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return DisposableHelper.isDisposed(get());
        }

        @Override // p110z1.CompletableObserver, p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSubscribe(Disposable atrVar) {
            DisposableHelper.replace(this, atrVar);
        }

        @Override // p110z1.SingleObserver
        public void onSuccess(T t) {
            try {
                CompletableSource arsVar = (CompletableSource) ObjectHelper.m9873a(this.mapper.apply(t), "The mapper returned a null CompletableSource");
                if (!isDisposed()) {
                    arsVar.mo11309a(this);
                }
            } catch (Throwable th) {
                Exceptions.m9983b(th);
                onError(th);
            }
        }

        @Override // p110z1.CompletableObserver, p110z1.MaybeObserver, p110z1.SingleObserver
        public void onError(Throwable th) {
            this.downstream.onError(th);
        }

        @Override // p110z1.CompletableObserver, p110z1.MaybeObserver
        public void onComplete() {
            this.downstream.onComplete();
        }
    }
}
