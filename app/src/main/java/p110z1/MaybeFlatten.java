package p110z1;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bfy */
/* loaded from: classes3.dex */
public final class MaybeFlatten<T, R> extends AbstractMaybeWithUpstream<T, R> {

    /* renamed from: b */
    final Function<? super T, ? extends MaybeSource<? extends R>> f18638b;

    public MaybeFlatten(MaybeSource<T> asiVar, Function<? super T, ? extends MaybeSource<? extends R>> aunVar) {
        super(asiVar);
        this.f18638b = aunVar;
    }

    @Override // p110z1.Maybe
    /* renamed from: b */
    protected void mo8992b(MaybeObserver<? super R> asfVar) {
        this.f18529a.mo10646a(new C4299a(asfVar, this.f18638b));
    }

    /* compiled from: MaybeFlatten.java */
    /* renamed from: z1.bfy$a */
    /* loaded from: classes3.dex */
    static final class C4299a<T, R> extends AtomicReference<Disposable> implements MaybeObserver<T>, Disposable {
        private static final long serialVersionUID = 4375739915521278546L;
        final MaybeObserver<? super R> downstream;
        final Function<? super T, ? extends MaybeSource<? extends R>> mapper;
        Disposable upstream;

        C4299a(MaybeObserver<? super R> asfVar, Function<? super T, ? extends MaybeSource<? extends R>> aunVar) {
            this.downstream = asfVar;
            this.mapper = aunVar;
        }

        @Override // p110z1.Disposable
        public void dispose() {
            DisposableHelper.dispose(this);
            this.upstream.dispose();
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return DisposableHelper.isDisposed(get());
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.upstream, atrVar)) {
                this.upstream = atrVar;
                this.downstream.onSubscribe(this);
            }
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSuccess(T t) {
            try {
                MaybeSource asiVar = (MaybeSource) ObjectHelper.m9873a(this.mapper.apply(t), "The mapper returned a null MaybeSource");
                if (!isDisposed()) {
                    asiVar.mo10646a(new C4300a());
                }
            } catch (Exception e) {
                Exceptions.m9983b(e);
                this.downstream.onError(e);
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

        /* compiled from: MaybeFlatten.java */
        /* renamed from: z1.bfy$a$a */
        /* loaded from: classes3.dex */
        final class C4300a implements MaybeObserver<R> {
            C4300a() {
            }

            @Override // p110z1.MaybeObserver, p110z1.SingleObserver
            public void onSubscribe(Disposable atrVar) {
                DisposableHelper.setOnce(C4299a.this, atrVar);
            }

            @Override // p110z1.MaybeObserver, p110z1.SingleObserver
            public void onSuccess(R r) {
                C4299a.this.downstream.onSuccess(r);
            }

            @Override // p110z1.MaybeObserver, p110z1.SingleObserver
            public void onError(Throwable th) {
                C4299a.this.downstream.onError(th);
            }

            @Override // p110z1.MaybeObserver
            public void onComplete() {
                C4299a.this.downstream.onComplete();
            }
        }
    }
}
