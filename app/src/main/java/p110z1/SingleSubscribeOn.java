package p110z1;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bpz */
/* loaded from: classes3.dex */
public final class SingleSubscribeOn<T> extends Single<T> {

    /* renamed from: a */
    final SingleSource<? extends T> f19824a;

    /* renamed from: b */
    final Scheduler f19825b;

    public SingleSubscribeOn(SingleSource<? extends T> ataVar, Scheduler astVar) {
        this.f19824a = ataVar;
        this.f19825b = astVar;
    }

    @Override // p110z1.Single
    /* renamed from: b */
    protected void mo8961b(SingleObserver<? super T> asxVar) {
        RunnableC4695a aVar = new RunnableC4695a(asxVar, this.f19824a);
        asxVar.onSubscribe(aVar);
        aVar.task.replace(this.f19825b.mo9481a(aVar));
    }

    /* compiled from: SingleSubscribeOn.java */
    /* renamed from: z1.bpz$a */
    /* loaded from: classes3.dex */
    static final class RunnableC4695a<T> extends AtomicReference<Disposable> implements Runnable, SingleObserver<T>, Disposable {
        private static final long serialVersionUID = 7000911171163930287L;
        final SingleObserver<? super T> downstream;
        final SingleSource<? extends T> source;
        final SequentialDisposable task = new SequentialDisposable();

        RunnableC4695a(SingleObserver<? super T> asxVar, SingleSource<? extends T> ataVar) {
            this.downstream = asxVar;
            this.source = ataVar;
        }

        @Override // p110z1.SingleObserver
        public void onSubscribe(Disposable atrVar) {
            DisposableHelper.setOnce(this, atrVar);
        }

        @Override // p110z1.SingleObserver
        public void onSuccess(T t) {
            this.downstream.onSuccess(t);
        }

        @Override // p110z1.SingleObserver
        public void onError(Throwable th) {
            this.downstream.onError(th);
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

        @Override // java.lang.Runnable
        public void run() {
            this.source.mo10018a(this);
        }
    }
}
