package p110z1;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.aye */
/* loaded from: classes3.dex */
public final class CompletableObserveOn extends Completable {

    /* renamed from: a */
    final CompletableSource f17761a;

    /* renamed from: b */
    final Scheduler f17762b;

    public CompletableObserveOn(CompletableSource arsVar, Scheduler astVar) {
        this.f17761a = arsVar;
        this.f17762b = astVar;
    }

    @Override // p110z1.Completable
    /* renamed from: b */
    protected void mo9001b(CompletableObserver arpVar) {
        this.f17761a.mo11309a(new RunnableC3964a(arpVar, this.f17762b));
    }

    /* compiled from: CompletableObserveOn.java */
    /* renamed from: z1.aye$a */
    /* loaded from: classes3.dex */
    static final class RunnableC3964a extends AtomicReference<Disposable> implements Runnable, CompletableObserver, Disposable {
        private static final long serialVersionUID = 8571289934935992137L;
        final CompletableObserver downstream;
        Throwable error;
        final Scheduler scheduler;

        RunnableC3964a(CompletableObserver arpVar, Scheduler astVar) {
            this.downstream = arpVar;
            this.scheduler = astVar;
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
            if (DisposableHelper.setOnce(this, atrVar)) {
                this.downstream.onSubscribe(this);
            }
        }

        @Override // p110z1.CompletableObserver, p110z1.MaybeObserver, p110z1.SingleObserver
        public void onError(Throwable th) {
            this.error = th;
            DisposableHelper.replace(this, this.scheduler.mo9481a(this));
        }

        @Override // p110z1.CompletableObserver, p110z1.MaybeObserver
        public void onComplete() {
            DisposableHelper.replace(this, this.scheduler.mo9481a(this));
        }

        @Override // java.lang.Runnable
        public void run() {
            Throwable th = this.error;
            if (th != null) {
                this.error = null;
                this.downstream.onError(th);
                return;
            }
            this.downstream.onComplete();
        }
    }
}
