package p110z1;

/* renamed from: z1.axw */
/* loaded from: classes3.dex */
public final class CompletableLift extends Completable {

    /* renamed from: a */
    final CompletableSource f17736a;

    /* renamed from: b */
    final CompletableOperator f17737b;

    public CompletableLift(CompletableSource arsVar, CompletableOperator arrVar) {
        this.f17736a = arsVar;
        this.f17737b = arrVar;
    }

    @Override // p110z1.Completable
    /* renamed from: b */
    protected void mo9001b(CompletableObserver arpVar) {
        try {
            this.f17736a.mo11309a(this.f17737b.m11310a(arpVar));
        } catch (NullPointerException e) {
            throw e;
        } catch (Throwable th) {
            Exceptions.m9983b(th);
            RxJavaPlugins.m9212a(th);
        }
    }
}
