package p110z1;

/* renamed from: z1.ayf */
/* loaded from: classes3.dex */
public final class CompletableOnErrorComplete extends Completable {

    /* renamed from: a */
    final CompletableSource f17763a;

    /* renamed from: b */
    final Predicate<? super Throwable> f17764b;

    public CompletableOnErrorComplete(CompletableSource arsVar, Predicate<? super Throwable> auxVar) {
        this.f17763a = arsVar;
        this.f17764b = auxVar;
    }

    @Override // p110z1.Completable
    /* renamed from: b */
    protected void mo9001b(CompletableObserver arpVar) {
        this.f17763a.mo11309a(new C3965a(arpVar));
    }

    /* compiled from: CompletableOnErrorComplete.java */
    /* renamed from: z1.ayf$a */
    /* loaded from: classes3.dex */
    final class C3965a implements CompletableObserver {

        /* renamed from: b */
        private final CompletableObserver f17766b;

        C3965a(CompletableObserver arpVar) {
            this.f17766b = arpVar;
        }

        @Override // p110z1.CompletableObserver, p110z1.MaybeObserver
        public void onComplete() {
            this.f17766b.onComplete();
        }

        @Override // p110z1.CompletableObserver, p110z1.MaybeObserver, p110z1.SingleObserver
        public void onError(Throwable th) {
            try {
                if (CompletableOnErrorComplete.this.f17764b.test(th)) {
                    this.f17766b.onComplete();
                } else {
                    this.f17766b.onError(th);
                }
            } catch (Throwable th2) {
                Exceptions.m9983b(th2);
                this.f17766b.onError(new CompositeException(th, th2));
            }
        }

        @Override // p110z1.CompletableObserver, p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSubscribe(Disposable atrVar) {
            this.f17766b.onSubscribe(atrVar);
        }
    }
}
