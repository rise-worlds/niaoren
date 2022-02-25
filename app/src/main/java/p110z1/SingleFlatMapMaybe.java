package p110z1;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bpk */
/* loaded from: classes3.dex */
public final class SingleFlatMapMaybe<T, R> extends Maybe<R> {

    /* renamed from: a */
    final SingleSource<? extends T> f19787a;

    /* renamed from: b */
    final Function<? super T, ? extends MaybeSource<? extends R>> f19788b;

    public SingleFlatMapMaybe(SingleSource<? extends T> ataVar, Function<? super T, ? extends MaybeSource<? extends R>> aunVar) {
        this.f19788b = aunVar;
        this.f19787a = ataVar;
    }

    @Override // p110z1.Maybe
    /* renamed from: b */
    protected void mo8992b(MaybeObserver<? super R> asfVar) {
        this.f19787a.mo10018a(new C4682b(asfVar, this.f19788b));
    }

    /* compiled from: SingleFlatMapMaybe.java */
    /* renamed from: z1.bpk$b */
    /* loaded from: classes3.dex */
    static final class C4682b<T, R> extends AtomicReference<Disposable> implements SingleObserver<T>, Disposable {
        private static final long serialVersionUID = -5843758257109742742L;
        final MaybeObserver<? super R> downstream;
        final Function<? super T, ? extends MaybeSource<? extends R>> mapper;

        C4682b(MaybeObserver<? super R> asfVar, Function<? super T, ? extends MaybeSource<? extends R>> aunVar) {
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

        @Override // p110z1.SingleObserver
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.setOnce(this, atrVar)) {
                this.downstream.onSubscribe(this);
            }
        }

        @Override // p110z1.SingleObserver
        public void onSuccess(T t) {
            try {
                MaybeSource asiVar = (MaybeSource) ObjectHelper.m9873a(this.mapper.apply(t), "The mapper returned a null MaybeSource");
                if (!isDisposed()) {
                    asiVar.mo10646a(new C4681a(this, this.downstream));
                }
            } catch (Throwable th) {
                Exceptions.m9983b(th);
                onError(th);
            }
        }

        @Override // p110z1.SingleObserver
        public void onError(Throwable th) {
            this.downstream.onError(th);
        }
    }

    /* compiled from: SingleFlatMapMaybe.java */
    /* renamed from: z1.bpk$a */
    /* loaded from: classes3.dex */
    static final class C4681a<R> implements MaybeObserver<R> {

        /* renamed from: a */
        final AtomicReference<Disposable> f19789a;

        /* renamed from: b */
        final MaybeObserver<? super R> f19790b;

        C4681a(AtomicReference<Disposable> atomicReference, MaybeObserver<? super R> asfVar) {
            this.f19789a = atomicReference;
            this.f19790b = asfVar;
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSubscribe(Disposable atrVar) {
            DisposableHelper.replace(this.f19789a, atrVar);
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSuccess(R r) {
            this.f19790b.onSuccess(r);
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onError(Throwable th) {
            this.f19790b.onError(th);
        }

        @Override // p110z1.MaybeObserver
        public void onComplete() {
            this.f19790b.onComplete();
        }
    }
}
