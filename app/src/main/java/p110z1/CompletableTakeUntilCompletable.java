package p110z1;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.ayj */
/* loaded from: classes3.dex */
public final class CompletableTakeUntilCompletable extends Completable {

    /* renamed from: a */
    final Completable f17781a;

    /* renamed from: b */
    final CompletableSource f17782b;

    public CompletableTakeUntilCompletable(Completable armVar, CompletableSource arsVar) {
        this.f17781a = armVar;
        this.f17782b = arsVar;
    }

    @Override // p110z1.Completable
    /* renamed from: b */
    protected void mo9001b(CompletableObserver arpVar) {
        C3969a aVar = new C3969a(arpVar);
        arpVar.onSubscribe(aVar);
        this.f17782b.mo11309a(aVar.other);
        this.f17781a.mo11309a((CompletableObserver) aVar);
    }

    /* compiled from: CompletableTakeUntilCompletable.java */
    /* renamed from: z1.ayj$a */
    /* loaded from: classes3.dex */
    static final class C3969a extends AtomicReference<Disposable> implements CompletableObserver, Disposable {
        private static final long serialVersionUID = 3533011714830024923L;
        final CompletableObserver downstream;
        final C3970a other = new C3970a(this);
        final AtomicBoolean once = new AtomicBoolean();

        C3969a(CompletableObserver arpVar) {
            this.downstream = arpVar;
        }

        @Override // p110z1.Disposable
        public void dispose() {
            if (this.once.compareAndSet(false, true)) {
                DisposableHelper.dispose(this);
                DisposableHelper.dispose(this.other);
            }
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.once.get();
        }

        @Override // p110z1.CompletableObserver, p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSubscribe(Disposable atrVar) {
            DisposableHelper.setOnce(this, atrVar);
        }

        @Override // p110z1.CompletableObserver, p110z1.MaybeObserver
        public void onComplete() {
            if (this.once.compareAndSet(false, true)) {
                DisposableHelper.dispose(this.other);
                this.downstream.onComplete();
            }
        }

        @Override // p110z1.CompletableObserver, p110z1.MaybeObserver, p110z1.SingleObserver
        public void onError(Throwable th) {
            if (this.once.compareAndSet(false, true)) {
                DisposableHelper.dispose(this.other);
                this.downstream.onError(th);
                return;
            }
            RxJavaPlugins.m9212a(th);
        }

        void innerComplete() {
            if (this.once.compareAndSet(false, true)) {
                DisposableHelper.dispose(this);
                this.downstream.onComplete();
            }
        }

        void innerError(Throwable th) {
            if (this.once.compareAndSet(false, true)) {
                DisposableHelper.dispose(this);
                this.downstream.onError(th);
                return;
            }
            RxJavaPlugins.m9212a(th);
        }

        /* compiled from: CompletableTakeUntilCompletable.java */
        /* renamed from: z1.ayj$a$a */
        /* loaded from: classes3.dex */
        static final class C3970a extends AtomicReference<Disposable> implements CompletableObserver {
            private static final long serialVersionUID = 5176264485428790318L;
            final C3969a parent;

            C3970a(C3969a aVar) {
                this.parent = aVar;
            }

            @Override // p110z1.CompletableObserver, p110z1.MaybeObserver, p110z1.SingleObserver
            public void onSubscribe(Disposable atrVar) {
                DisposableHelper.setOnce(this, atrVar);
            }

            @Override // p110z1.CompletableObserver, p110z1.MaybeObserver
            public void onComplete() {
                this.parent.innerComplete();
            }

            @Override // p110z1.CompletableObserver, p110z1.MaybeObserver, p110z1.SingleObserver
            public void onError(Throwable th) {
                this.parent.innerError(th);
            }
        }
    }
}
