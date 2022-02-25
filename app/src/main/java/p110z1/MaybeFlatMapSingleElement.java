package p110z1;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bfx */
/* loaded from: classes3.dex */
public final class MaybeFlatMapSingleElement<T, R> extends Maybe<R> {

    /* renamed from: a */
    final MaybeSource<T> f18634a;

    /* renamed from: b */
    final Function<? super T, ? extends SingleSource<? extends R>> f18635b;

    public MaybeFlatMapSingleElement(MaybeSource<T> asiVar, Function<? super T, ? extends SingleSource<? extends R>> aunVar) {
        this.f18634a = asiVar;
        this.f18635b = aunVar;
    }

    @Override // p110z1.Maybe
    /* renamed from: b */
    protected void mo8992b(MaybeObserver<? super R> asfVar) {
        this.f18634a.mo10646a(new C4297a(asfVar, this.f18635b));
    }

    /* compiled from: MaybeFlatMapSingleElement.java */
    /* renamed from: z1.bfx$a */
    /* loaded from: classes3.dex */
    static final class C4297a<T, R> extends AtomicReference<Disposable> implements MaybeObserver<T>, Disposable {
        private static final long serialVersionUID = 4827726964688405508L;
        final MaybeObserver<? super R> downstream;
        final Function<? super T, ? extends SingleSource<? extends R>> mapper;

        C4297a(MaybeObserver<? super R> asfVar, Function<? super T, ? extends SingleSource<? extends R>> aunVar) {
            this.downstream = asfVar;
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

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.setOnce(this, atrVar)) {
                this.downstream.onSubscribe(this);
            }
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSuccess(T t) {
            try {
                ((SingleSource) ObjectHelper.m9873a(this.mapper.apply(t), "The mapper returned a null SingleSource")).mo10018a(new C4298b(this, this.downstream));
            } catch (Throwable th) {
                Exceptions.m9983b(th);
                onError(th);
            }
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onError(Throwable th) {
            this.downstream.onError(th);
        }

        @Override // p110z1.MaybeObserver
        public void onComplete() {
            this.downstream.onComplete();
        }
    }

    /* compiled from: MaybeFlatMapSingleElement.java */
    /* renamed from: z1.bfx$b */
    /* loaded from: classes3.dex */
    static final class C4298b<R> implements SingleObserver<R> {

        /* renamed from: a */
        final AtomicReference<Disposable> f18636a;

        /* renamed from: b */
        final MaybeObserver<? super R> f18637b;

        C4298b(AtomicReference<Disposable> atomicReference, MaybeObserver<? super R> asfVar) {
            this.f18636a = atomicReference;
            this.f18637b = asfVar;
        }

        @Override // p110z1.SingleObserver
        public void onSubscribe(Disposable atrVar) {
            DisposableHelper.replace(this.f18636a, atrVar);
        }

        @Override // p110z1.SingleObserver
        public void onSuccess(R r) {
            this.f18637b.onSuccess(r);
        }

        @Override // p110z1.SingleObserver
        public void onError(Throwable th) {
            this.f18637b.onError(th);
        }
    }
}
