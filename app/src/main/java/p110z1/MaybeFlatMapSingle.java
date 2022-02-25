package p110z1;

import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bfw */
/* loaded from: classes3.dex */
public final class MaybeFlatMapSingle<T, R> extends Single<R> {

    /* renamed from: a */
    final MaybeSource<T> f18630a;

    /* renamed from: b */
    final Function<? super T, ? extends SingleSource<? extends R>> f18631b;

    public MaybeFlatMapSingle(MaybeSource<T> asiVar, Function<? super T, ? extends SingleSource<? extends R>> aunVar) {
        this.f18630a = asiVar;
        this.f18631b = aunVar;
    }

    @Override // p110z1.Single
    /* renamed from: b */
    protected void mo8961b(SingleObserver<? super R> asxVar) {
        this.f18630a.mo10646a(new C4295a(asxVar, this.f18631b));
    }

    /* compiled from: MaybeFlatMapSingle.java */
    /* renamed from: z1.bfw$a */
    /* loaded from: classes3.dex */
    static final class C4295a<T, R> extends AtomicReference<Disposable> implements MaybeObserver<T>, Disposable {
        private static final long serialVersionUID = 4827726964688405508L;
        final SingleObserver<? super R> downstream;
        final Function<? super T, ? extends SingleSource<? extends R>> mapper;

        C4295a(SingleObserver<? super R> asxVar, Function<? super T, ? extends SingleSource<? extends R>> aunVar) {
            this.downstream = asxVar;
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
                SingleSource ataVar = (SingleSource) ObjectHelper.m9873a(this.mapper.apply(t), "The mapper returned a null SingleSource");
                if (!isDisposed()) {
                    ataVar.mo10018a(new C4296b(this, this.downstream));
                }
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
            this.downstream.onError(new NoSuchElementException());
        }
    }

    /* compiled from: MaybeFlatMapSingle.java */
    /* renamed from: z1.bfw$b */
    /* loaded from: classes3.dex */
    static final class C4296b<R> implements SingleObserver<R> {

        /* renamed from: a */
        final AtomicReference<Disposable> f18632a;

        /* renamed from: b */
        final SingleObserver<? super R> f18633b;

        C4296b(AtomicReference<Disposable> atomicReference, SingleObserver<? super R> asxVar) {
            this.f18632a = atomicReference;
            this.f18633b = asxVar;
        }

        @Override // p110z1.SingleObserver
        public void onSubscribe(Disposable atrVar) {
            DisposableHelper.replace(this.f18632a, atrVar);
        }

        @Override // p110z1.SingleObserver
        public void onSuccess(R r) {
            this.f18633b.onSuccess(r);
        }

        @Override // p110z1.SingleObserver
        public void onError(Throwable th) {
            this.f18633b.onError(th);
        }
    }
}
