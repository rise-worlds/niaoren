package p110z1;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.ayi */
/* loaded from: classes3.dex */
public final class CompletableSubscribeOn extends Completable {

    /* renamed from: a */
    final CompletableSource f17779a;

    /* renamed from: b */
    final Scheduler f17780b;

    public CompletableSubscribeOn(CompletableSource arsVar, Scheduler astVar) {
        this.f17779a = arsVar;
        this.f17780b = astVar;
    }

    @Override // p110z1.Completable
    /* renamed from: b */
    protected void mo9001b(CompletableObserver arpVar) {
        RunnableC3968a aVar = new RunnableC3968a(arpVar, this.f17779a);
        arpVar.onSubscribe(aVar);
        aVar.task.replace(this.f17780b.mo9481a(aVar));
    }

    /* compiled from: CompletableSubscribeOn.java */
    /* renamed from: z1.ayi$a */
    /* loaded from: classes3.dex */
    static final class RunnableC3968a extends AtomicReference<Disposable> implements Runnable, CompletableObserver, Disposable {
        private static final long serialVersionUID = 7000911171163930287L;
        final CompletableObserver downstream;
        final CompletableSource source;
        final SequentialDisposable task = new SequentialDisposable();

        RunnableC3968a(CompletableObserver arpVar, CompletableSource arsVar) {
            this.downstream = arpVar;
            this.source = arsVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.source.mo11309a(this);
        }

        @Override // p110z1.CompletableObserver, p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSubscribe(Disposable atrVar) {
            DisposableHelper.setOnce(this, atrVar);
        }

        @Override // p110z1.CompletableObserver, p110z1.MaybeObserver, p110z1.SingleObserver
        public void onError(Throwable th) {
            this.downstream.onError(th);
        }

        @Override // p110z1.CompletableObserver, p110z1.MaybeObserver
        public void onComplete() {
            this.downstream.onComplete();
        }

        @Override // p110z1.Disposable
        public void dispose() {
            DisposableHelper.dispose(this);
            this.task.dispose();
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return DisposableHelper.isDisposed(get());
        }
    }
}
