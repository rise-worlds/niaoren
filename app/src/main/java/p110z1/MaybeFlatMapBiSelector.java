package p110z1;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bfr */
/* loaded from: classes3.dex */
public final class MaybeFlatMapBiSelector<T, U, R> extends AbstractMaybeWithUpstream<T, R> {

    /* renamed from: b */
    final Function<? super T, ? extends MaybeSource<? extends U>> f18609b;

    /* renamed from: c */
    final BiFunction<? super T, ? super U, ? extends R> f18610c;

    public MaybeFlatMapBiSelector(MaybeSource<T> asiVar, Function<? super T, ? extends MaybeSource<? extends U>> aunVar, BiFunction<? super T, ? super U, ? extends R> auiVar) {
        super(asiVar);
        this.f18609b = aunVar;
        this.f18610c = auiVar;
    }

    @Override // p110z1.Maybe
    /* renamed from: b */
    protected void mo8992b(MaybeObserver<? super R> asfVar) {
        this.f18529a.mo10646a(new C4288a(asfVar, this.f18609b, this.f18610c));
    }

    /* compiled from: MaybeFlatMapBiSelector.java */
    /* renamed from: z1.bfr$a */
    /* loaded from: classes3.dex */
    static final class C4288a<T, U, R> implements MaybeObserver<T>, Disposable {

        /* renamed from: a */
        final Function<? super T, ? extends MaybeSource<? extends U>> f18611a;

        /* renamed from: b */
        final C4289a<T, U, R> f18612b;

        C4288a(MaybeObserver<? super R> asfVar, Function<? super T, ? extends MaybeSource<? extends U>> aunVar, BiFunction<? super T, ? super U, ? extends R> auiVar) {
            this.f18612b = new C4289a<>(asfVar, auiVar);
            this.f18611a = aunVar;
        }

        @Override // p110z1.Disposable
        public void dispose() {
            DisposableHelper.dispose(this.f18612b);
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return DisposableHelper.isDisposed(this.f18612b.get());
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.setOnce(this.f18612b, atrVar)) {
                this.f18612b.downstream.onSubscribe(this);
            }
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSuccess(T t) {
            try {
                MaybeSource asiVar = (MaybeSource) ObjectHelper.m9873a(this.f18611a.apply(t), "The mapper returned a null MaybeSource");
                if (DisposableHelper.replace(this.f18612b, null)) {
                    C4289a<T, U, R> aVar = this.f18612b;
                    aVar.value = t;
                    asiVar.mo10646a(aVar);
                }
            } catch (Throwable th) {
                Exceptions.m9983b(th);
                this.f18612b.downstream.onError(th);
            }
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onError(Throwable th) {
            this.f18612b.downstream.onError(th);
        }

        @Override // p110z1.MaybeObserver
        public void onComplete() {
            this.f18612b.downstream.onComplete();
        }

        /* compiled from: MaybeFlatMapBiSelector.java */
        /* renamed from: z1.bfr$a$a */
        /* loaded from: classes3.dex */
        static final class C4289a<T, U, R> extends AtomicReference<Disposable> implements MaybeObserver<U> {
            private static final long serialVersionUID = -2897979525538174559L;
            final MaybeObserver<? super R> downstream;
            final BiFunction<? super T, ? super U, ? extends R> resultSelector;
            T value;

            C4289a(MaybeObserver<? super R> asfVar, BiFunction<? super T, ? super U, ? extends R> auiVar) {
                this.downstream = asfVar;
                this.resultSelector = auiVar;
            }

            @Override // p110z1.MaybeObserver, p110z1.SingleObserver
            public void onSubscribe(Disposable atrVar) {
                DisposableHelper.setOnce(this, atrVar);
            }

            @Override // p110z1.MaybeObserver, p110z1.SingleObserver
            public void onSuccess(U u) {
                T t = this.value;
                this.value = null;
                try {
                    this.downstream.onSuccess(ObjectHelper.m9873a(this.resultSelector.apply(t, u), "The resultSelector returned a null value"));
                } catch (Throwable th) {
                    Exceptions.m9983b(th);
                    this.downstream.onError(th);
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
    }
}
