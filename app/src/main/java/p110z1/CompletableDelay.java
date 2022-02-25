package p110z1;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.axg */
/* loaded from: classes3.dex */
public final class CompletableDelay extends Completable {

    /* renamed from: a */
    final CompletableSource f17699a;

    /* renamed from: b */
    final long f17700b;

    /* renamed from: c */
    final TimeUnit f17701c;

    /* renamed from: d */
    final Scheduler f17702d;

    /* renamed from: e */
    final boolean f17703e;

    public CompletableDelay(CompletableSource arsVar, long j, TimeUnit timeUnit, Scheduler astVar, boolean z) {
        this.f17699a = arsVar;
        this.f17700b = j;
        this.f17701c = timeUnit;
        this.f17702d = astVar;
        this.f17703e = z;
    }

    @Override // p110z1.Completable
    /* renamed from: b */
    protected void mo9001b(CompletableObserver arpVar) {
        this.f17699a.mo11309a(new RunnableC3949a(arpVar, this.f17700b, this.f17701c, this.f17702d, this.f17703e));
    }

    /* compiled from: CompletableDelay.java */
    /* renamed from: z1.axg$a */
    /* loaded from: classes3.dex */
    static final class RunnableC3949a extends AtomicReference<Disposable> implements Runnable, CompletableObserver, Disposable {
        private static final long serialVersionUID = 465972761105851022L;
        final long delay;
        final boolean delayError;
        final CompletableObserver downstream;
        Throwable error;
        final Scheduler scheduler;
        final TimeUnit unit;

        RunnableC3949a(CompletableObserver arpVar, long j, TimeUnit timeUnit, Scheduler astVar, boolean z) {
            this.downstream = arpVar;
            this.delay = j;
            this.unit = timeUnit;
            this.scheduler = astVar;
            this.delayError = z;
        }

        @Override // p110z1.CompletableObserver, p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.setOnce(this, atrVar)) {
                this.downstream.onSubscribe(this);
            }
        }

        @Override // p110z1.CompletableObserver, p110z1.MaybeObserver
        public void onComplete() {
            DisposableHelper.replace(this, this.scheduler.mo9480a(this, this.delay, this.unit));
        }

        @Override // p110z1.CompletableObserver, p110z1.MaybeObserver, p110z1.SingleObserver
        public void onError(Throwable th) {
            this.error = th;
            DisposableHelper.replace(this, this.scheduler.mo9480a(this, this.delayError ? this.delay : 0L, this.unit));
        }

        @Override // p110z1.Disposable
        public void dispose() {
            DisposableHelper.dispose(this);
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return DisposableHelper.isDisposed(get());
        }

        @Override // java.lang.Runnable
        public void run() {
            Throwable th = this.error;
            this.error = null;
            if (th != null) {
                this.downstream.onError(th);
            } else {
                this.downstream.onComplete();
            }
        }
    }
}
