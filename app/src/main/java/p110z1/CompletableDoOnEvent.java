package p110z1;

/* renamed from: z1.axk */
/* loaded from: classes3.dex */
public final class CompletableDoOnEvent extends Completable {

    /* renamed from: a */
    final CompletableSource f17715a;

    /* renamed from: b */
    final Consumer<? super Throwable> f17716b;

    public CompletableDoOnEvent(CompletableSource arsVar, Consumer<? super Throwable> aumVar) {
        this.f17715a = arsVar;
        this.f17716b = aumVar;
    }

    @Override // p110z1.Completable
    /* renamed from: b */
    protected void mo9001b(CompletableObserver arpVar) {
        this.f17715a.mo11309a(new C3953a(arpVar));
    }

    /* compiled from: CompletableDoOnEvent.java */
    /* renamed from: z1.axk$a */
    /* loaded from: classes3.dex */
    final class C3953a implements CompletableObserver {

        /* renamed from: b */
        private final CompletableObserver f17718b;

        C3953a(CompletableObserver arpVar) {
            this.f17718b = arpVar;
        }

        @Override // p110z1.CompletableObserver, p110z1.MaybeObserver
        public void onComplete() {
            try {
                CompletableDoOnEvent.this.f17716b.accept(null);
                this.f17718b.onComplete();
            } catch (Throwable th) {
                Exceptions.m9983b(th);
                this.f17718b.onError(th);
            }
        }

        @Override // p110z1.CompletableObserver, p110z1.MaybeObserver, p110z1.SingleObserver
        public void onError(Throwable th) {
            try {
                CompletableDoOnEvent.this.f17716b.accept(th);
            } catch (Throwable th2) {
                Exceptions.m9983b(th2);
                th = new CompositeException(th, th2);
            }
            this.f17718b.onError(th);
        }

        @Override // p110z1.CompletableObserver, p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSubscribe(Disposable atrVar) {
            this.f17718b.onSubscribe(atrVar);
        }
    }
}
