package p110z1;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bfs */
/* loaded from: classes3.dex */
public final class MaybeFlatMapCompletable<T> extends Completable {

    /* renamed from: a */
    final MaybeSource<T> f18613a;

    /* renamed from: b */
    final Function<? super T, ? extends CompletableSource> f18614b;

    public MaybeFlatMapCompletable(MaybeSource<T> asiVar, Function<? super T, ? extends CompletableSource> aunVar) {
        this.f18613a = asiVar;
        this.f18614b = aunVar;
    }

    @Override // p110z1.Completable
    /* renamed from: b */
    protected void mo9001b(CompletableObserver arpVar) {
        C4290a aVar = new C4290a(arpVar, this.f18614b);
        arpVar.onSubscribe(aVar);
        this.f18613a.mo10646a(aVar);
    }

    /* compiled from: MaybeFlatMapCompletable.java */
    /* renamed from: z1.bfs$a */
    /* loaded from: classes3.dex */
    static final class C4290a<T> extends AtomicReference<Disposable> implements CompletableObserver, MaybeObserver<T>, Disposable {
        private static final long serialVersionUID = -2177128922851101253L;
        final CompletableObserver downstream;
        final Function<? super T, ? extends CompletableSource> mapper;

        C4290a(CompletableObserver arpVar, Function<? super T, ? extends CompletableSource> aunVar) {
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

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
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
