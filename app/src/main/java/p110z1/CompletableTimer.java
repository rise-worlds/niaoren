package p110z1;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.ayl */
/* loaded from: classes3.dex */
public final class CompletableTimer extends Completable {

    /* renamed from: a */
    final long f17796a;

    /* renamed from: b */
    final TimeUnit f17797b;

    /* renamed from: c */
    final Scheduler f17798c;

    public CompletableTimer(long j, TimeUnit timeUnit, Scheduler astVar) {
        this.f17796a = j;
        this.f17797b = timeUnit;
        this.f17798c = astVar;
    }

    @Override // p110z1.Completable
    /* renamed from: b */
    protected void mo9001b(CompletableObserver arpVar) {
        RunnableC3974a aVar = new RunnableC3974a(arpVar);
        arpVar.onSubscribe(aVar);
        aVar.setFuture(this.f17798c.mo9480a(aVar, this.f17796a, this.f17797b));
    }

    /* compiled from: CompletableTimer.java */
    /* renamed from: z1.ayl$a */
    /* loaded from: classes3.dex */
    static final class RunnableC3974a extends AtomicReference<Disposable> implements Runnable, Disposable {
        private static final long serialVersionUID = 3167244060586201109L;
        final CompletableObserver downstream;

        RunnableC3974a(CompletableObserver arpVar) {
            this.downstream = arpVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.downstream.onComplete();
        }

        @Override // p110z1.Disposable
        public void dispose() {
            DisposableHelper.dispose(this);
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return DisposableHelper.isDisposed(get());
        }

        void setFuture(Disposable atrVar) {
            DisposableHelper.replace(this, atrVar);
        }
    }
}
