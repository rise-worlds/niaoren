package p110z1;

/* renamed from: z1.axi */
/* loaded from: classes3.dex */
public final class CompletableDisposeOn extends Completable {

    /* renamed from: a */
    final CompletableSource f17707a;

    /* renamed from: b */
    final Scheduler f17708b;

    public CompletableDisposeOn(CompletableSource arsVar, Scheduler astVar) {
        this.f17707a = arsVar;
        this.f17708b = astVar;
    }

    @Override // p110z1.Completable
    /* renamed from: b */
    protected void mo9001b(CompletableObserver arpVar) {
        this.f17707a.mo11309a(new RunnableC3951a(arpVar, this.f17708b));
    }

    /* compiled from: CompletableDisposeOn.java */
    /* renamed from: z1.axi$a */
    /* loaded from: classes3.dex */
    static final class RunnableC3951a implements Runnable, CompletableObserver, Disposable {

        /* renamed from: a */
        final CompletableObserver f17709a;

        /* renamed from: b */
        final Scheduler f17710b;

        /* renamed from: c */
        Disposable f17711c;

        /* renamed from: d */
        volatile boolean f17712d;

        RunnableC3951a(CompletableObserver arpVar, Scheduler astVar) {
            this.f17709a = arpVar;
            this.f17710b = astVar;
        }

        @Override // p110z1.CompletableObserver, p110z1.MaybeObserver
        public void onComplete() {
            if (!this.f17712d) {
                this.f17709a.onComplete();
            }
        }

        @Override // p110z1.CompletableObserver, p110z1.MaybeObserver, p110z1.SingleObserver
        public void onError(Throwable th) {
            if (this.f17712d) {
                RxJavaPlugins.m9212a(th);
            } else {
                this.f17709a.onError(th);
            }
        }

        @Override // p110z1.CompletableObserver, p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.f17711c, atrVar)) {
                this.f17711c = atrVar;
                this.f17709a.onSubscribe(this);
            }
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.f17712d = true;
            this.f17710b.mo9481a(this);
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f17712d;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f17711c.dispose();
            this.f17711c = DisposableHelper.DISPOSED;
        }
    }
}
