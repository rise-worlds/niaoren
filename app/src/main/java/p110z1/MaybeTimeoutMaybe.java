package p110z1;

import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bha */
/* loaded from: classes3.dex */
public final class MaybeTimeoutMaybe<T, U> extends AbstractMaybeWithUpstream<T, T> {

    /* renamed from: b */
    final MaybeSource<U> f18709b;

    /* renamed from: c */
    final MaybeSource<? extends T> f18710c;

    public MaybeTimeoutMaybe(MaybeSource<T> asiVar, MaybeSource<U> asiVar2, MaybeSource<? extends T> asiVar3) {
        super(asiVar);
        this.f18709b = asiVar2;
        this.f18710c = asiVar3;
    }

    @Override // p110z1.Maybe
    /* renamed from: b */
    protected void mo8992b(MaybeObserver<? super T> asfVar) {
        C4332b bVar = new C4332b(asfVar, this.f18710c);
        asfVar.onSubscribe(bVar);
        this.f18709b.mo10646a(bVar.other);
        this.f18529a.mo10646a(bVar);
    }

    /* compiled from: MaybeTimeoutMaybe.java */
    /* renamed from: z1.bha$b */
    /* loaded from: classes3.dex */
    static final class C4332b<T, U> extends AtomicReference<Disposable> implements MaybeObserver<T>, Disposable {
        private static final long serialVersionUID = -5955289211445418871L;
        final MaybeObserver<? super T> downstream;
        final MaybeSource<? extends T> fallback;
        final C4333c<T, U> other = new C4333c<>(this);
        final C4331a<T> otherObserver;

        C4332b(MaybeObserver<? super T> asfVar, MaybeSource<? extends T> asiVar) {
            this.downstream = asfVar;
            this.fallback = asiVar;
            this.otherObserver = asiVar != null ? new C4331a<>(asfVar) : null;
        }

        @Override // p110z1.Disposable
        public void dispose() {
            DisposableHelper.dispose(this);
            DisposableHelper.dispose(this.other);
            C4331a<T> aVar = this.otherObserver;
            if (aVar != null) {
                DisposableHelper.dispose(aVar);
            }
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return DisposableHelper.isDisposed(get());
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSubscribe(Disposable atrVar) {
            DisposableHelper.setOnce(this, atrVar);
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSuccess(T t) {
            DisposableHelper.dispose(this.other);
            if (getAndSet(DisposableHelper.DISPOSED) != DisposableHelper.DISPOSED) {
                this.downstream.onSuccess(t);
            }
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onError(Throwable th) {
            DisposableHelper.dispose(this.other);
            if (getAndSet(DisposableHelper.DISPOSED) != DisposableHelper.DISPOSED) {
                this.downstream.onError(th);
            } else {
                RxJavaPlugins.m9212a(th);
            }
        }

        @Override // p110z1.MaybeObserver
        public void onComplete() {
            DisposableHelper.dispose(this.other);
            if (getAndSet(DisposableHelper.DISPOSED) != DisposableHelper.DISPOSED) {
                this.downstream.onComplete();
            }
        }

        public void otherError(Throwable th) {
            if (DisposableHelper.dispose(this)) {
                this.downstream.onError(th);
            } else {
                RxJavaPlugins.m9212a(th);
            }
        }

        public void otherComplete() {
            if (DisposableHelper.dispose(this)) {
                MaybeSource<? extends T> asiVar = this.fallback;
                if (asiVar == null) {
                    this.downstream.onError(new TimeoutException());
                } else {
                    asiVar.mo10646a(this.otherObserver);
                }
            }
        }
    }

    /* compiled from: MaybeTimeoutMaybe.java */
    /* renamed from: z1.bha$c */
    /* loaded from: classes3.dex */
    static final class C4333c<T, U> extends AtomicReference<Disposable> implements MaybeObserver<Object> {
        private static final long serialVersionUID = 8663801314800248617L;
        final C4332b<T, U> parent;

        C4333c(C4332b<T, U> bVar) {
            this.parent = bVar;
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSubscribe(Disposable atrVar) {
            DisposableHelper.setOnce(this, atrVar);
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSuccess(Object obj) {
            this.parent.otherComplete();
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onError(Throwable th) {
            this.parent.otherError(th);
        }

        @Override // p110z1.MaybeObserver
        public void onComplete() {
            this.parent.otherComplete();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: MaybeTimeoutMaybe.java */
    /* renamed from: z1.bha$a */
    /* loaded from: classes3.dex */
    public static final class C4331a<T> extends AtomicReference<Disposable> implements MaybeObserver<T> {
        private static final long serialVersionUID = 8663801314800248617L;
        final MaybeObserver<? super T> downstream;

        C4331a(MaybeObserver<? super T> asfVar) {
            this.downstream = asfVar;
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSubscribe(Disposable atrVar) {
            DisposableHelper.setOnce(this, atrVar);
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSuccess(T t) {
            this.downstream.onSuccess(t);
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
