package p110z1;

/* renamed from: z1.axl */
/* loaded from: classes3.dex */
public final class CompletableEmpty extends Completable {

    /* renamed from: a */
    public static final Completable f17719a = new CompletableEmpty();

    private CompletableEmpty() {
    }

    @Override // p110z1.Completable
    /* renamed from: b */
    public void mo9001b(CompletableObserver arpVar) {
        EmptyDisposable.complete(arpVar);
    }
}
