package p110z1;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bgs */
/* loaded from: classes3.dex */
public final class MaybeOnErrorNext<T> extends AbstractMaybeWithUpstream<T, T> {

    /* renamed from: b */
    final Function<? super Throwable, ? extends MaybeSource<? extends T>> f18678b;

    /* renamed from: c */
    final boolean f18679c;

    public MaybeOnErrorNext(MaybeSource<T> asiVar, Function<? super Throwable, ? extends MaybeSource<? extends T>> aunVar, boolean z) {
        super(asiVar);
        this.f18678b = aunVar;
        this.f18679c = z;
    }

    @Override // p110z1.Maybe
    /* renamed from: b */
    protected void mo8992b(MaybeObserver<? super T> asfVar) {
        this.f18529a.mo10646a(new C4316a(asfVar, this.f18678b, this.f18679c));
    }

    /* compiled from: MaybeOnErrorNext.java */
    /* renamed from: z1.bgs$a */
    /* loaded from: classes3.dex */
    static final class C4316a<T> extends AtomicReference<Disposable> implements MaybeObserver<T>, Disposable {
        private static final long serialVersionUID = 2026620218879969836L;
        final boolean allowFatal;
        final MaybeObserver<? super T> downstream;
        final Function<? super Throwable, ? extends MaybeSource<? extends T>> resumeFunction;

        C4316a(MaybeObserver<? super T> asfVar, Function<? super Throwable, ? extends MaybeSource<? extends T>> aunVar, boolean z) {
            this.downstream = asfVar;
            this.resumeFunction = aunVar;
            this.allowFatal = z;
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
            this.downstream.onSuccess(t);
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onError(Throwable th) {
            if (this.allowFatal || (th instanceof Exception)) {
                try {
                    MaybeSource asiVar = (MaybeSource) ObjectHelper.m9873a(this.resumeFunction.apply(th), "The resumeFunction returned a null MaybeSource");
                    DisposableHelper.replace(this, null);
                    asiVar.mo10646a(new C4317a(this.downstream, this));
                } catch (Throwable th2) {
                    Exceptions.m9983b(th2);
                    this.downstream.onError(new CompositeException(th, th2));
                }
            } else {
                this.downstream.onError(th);
            }
        }

        @Override // p110z1.MaybeObserver
        public void onComplete() {
            this.downstream.onComplete();
        }

        /* compiled from: MaybeOnErrorNext.java */
        /* renamed from: z1.bgs$a$a */
        /* loaded from: classes3.dex */
        static final class C4317a<T> implements MaybeObserver<T> {

            /* renamed from: a */
            final MaybeObserver<? super T> f18680a;

            /* renamed from: b */
            final AtomicReference<Disposable> f18681b;

            C4317a(MaybeObserver<? super T> asfVar, AtomicReference<Disposable> atomicReference) {
                this.f18680a = asfVar;
                this.f18681b = atomicReference;
            }

            @Override // p110z1.MaybeObserver, p110z1.SingleObserver
            public void onSubscribe(Disposable atrVar) {
                DisposableHelper.setOnce(this.f18681b, atrVar);
            }

            @Override // p110z1.MaybeObserver, p110z1.SingleObserver
            public void onSuccess(T t) {
                this.f18680a.onSuccess(t);
            }

            @Override // p110z1.MaybeObserver, p110z1.SingleObserver
            public void onError(Throwable th) {
                this.f18680a.onError(th);
            }

            @Override // p110z1.MaybeObserver
            public void onComplete() {
                this.f18680a.onComplete();
            }
        }
    }
}
