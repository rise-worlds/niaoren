package p110z1;

/* renamed from: z1.atp */
/* loaded from: classes3.dex */
final class ActionDisposable extends ReferenceDisposable<Action> {
    private static final long serialVersionUID = -8219729196779211169L;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ActionDisposable(Action augVar) {
        super(augVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onDisposed(@AbstractC3889atm Action augVar) {
        try {
            augVar.mo9442a();
        } catch (Throwable th) {
            throw ExceptionHelper.m9432a(th);
        }
    }
}
