package p110z1;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bfv */
/* loaded from: classes3.dex */
public final class MaybeFlatMapNotification<T, R> extends AbstractMaybeWithUpstream<T, R> {

    /* renamed from: b */
    final Function<? super T, ? extends MaybeSource<? extends R>> f18626b;

    /* renamed from: c */
    final Function<? super Throwable, ? extends MaybeSource<? extends R>> f18627c;

    /* renamed from: d */
    final Callable<? extends MaybeSource<? extends R>> f18628d;

    public MaybeFlatMapNotification(MaybeSource<T> asiVar, Function<? super T, ? extends MaybeSource<? extends R>> aunVar, Function<? super Throwable, ? extends MaybeSource<? extends R>> aunVar2, Callable<? extends MaybeSource<? extends R>> callable) {
        super(asiVar);
        this.f18626b = aunVar;
        this.f18627c = aunVar2;
        this.f18628d = callable;
    }

    @Override // p110z1.Maybe
    /* renamed from: b */
    protected void mo8992b(MaybeObserver<? super R> asfVar) {
        this.f18529a.mo10646a(new C4293a(asfVar, this.f18626b, this.f18627c, this.f18628d));
    }

    /* compiled from: MaybeFlatMapNotification.java */
    /* renamed from: z1.bfv$a */
    /* loaded from: classes3.dex */
    static final class C4293a<T, R> extends AtomicReference<Disposable> implements MaybeObserver<T>, Disposable {
        private static final long serialVersionUID = 4375739915521278546L;
        final MaybeObserver<? super R> downstream;
        final Callable<? extends MaybeSource<? extends R>> onCompleteSupplier;
        final Function<? super Throwable, ? extends MaybeSource<? extends R>> onErrorMapper;
        final Function<? super T, ? extends MaybeSource<? extends R>> onSuccessMapper;
        Disposable upstream;

        C4293a(MaybeObserver<? super R> asfVar, Function<? super T, ? extends MaybeSource<? extends R>> aunVar, Function<? super Throwable, ? extends MaybeSource<? extends R>> aunVar2, Callable<? extends MaybeSource<? extends R>> callable) {
            this.downstream = asfVar;
            this.onSuccessMapper = aunVar;
            this.onErrorMapper = aunVar2;
            this.onCompleteSupplier = callable;
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
                ((MaybeSource) ObjectHelper.m9873a(this.onSuccessMapper.apply(t), "The onSuccessMapper returned a null MaybeSource")).mo10646a(new C4294a());
            } catch (Exception e) {
                Exceptions.m9983b(e);
                this.downstream.onError(e);
            }
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onError(Throwable th) {
            try {
                ((MaybeSource) ObjectHelper.m9873a(this.onErrorMapper.apply(th), "The onErrorMapper returned a null MaybeSource")).mo10646a(new C4294a());
            } catch (Exception e) {
                Exceptions.m9983b(e);
                this.downstream.onError(new CompositeException(th, e));
            }
        }

        @Override // p110z1.MaybeObserver
        public void onComplete() {
            try {
                ((MaybeSource) ObjectHelper.m9873a(this.onCompleteSupplier.call(), "The onCompleteSupplier returned a null MaybeSource")).mo10646a(new C4294a());
            } catch (Exception e) {
                Exceptions.m9983b(e);
                this.downstream.onError(e);
            }
        }

        /* compiled from: MaybeFlatMapNotification.java */
        /* renamed from: z1.bfv$a$a */
        /* loaded from: classes3.dex */
        final class C4294a implements MaybeObserver<R> {
            C4294a() {
            }

            @Override // p110z1.MaybeObserver, p110z1.SingleObserver
            public void onSubscribe(Disposable atrVar) {
                DisposableHelper.setOnce(C4293a.this, atrVar);
            }

            @Override // p110z1.MaybeObserver, p110z1.SingleObserver
            public void onSuccess(R r) {
                C4293a.this.downstream.onSuccess(r);
            }

            @Override // p110z1.MaybeObserver, p110z1.SingleObserver
            public void onError(Throwable th) {
                C4293a.this.downstream.onError(th);
            }

            @Override // p110z1.MaybeObserver
            public void onComplete() {
                C4293a.this.downstream.onComplete();
            }
        }
    }
}
