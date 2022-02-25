package p110z1;

/* renamed from: z1.ayd */
/* loaded from: classes3.dex */
public final class CompletableNever extends Completable {

    /* renamed from: a */
    public static final Completable f17760a = new CompletableNever();

    private CompletableNever() {
    }

    @Override // p110z1.Completable
    /* renamed from: b */
    protected void mo9001b(CompletableObserver arpVar) {
        arpVar.onSubscribe(EmptyDisposable.NEVER);
    }
}
