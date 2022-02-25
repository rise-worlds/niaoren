package p110z1;

/* renamed from: z1.blh */
/* loaded from: classes3.dex */
public final class ObservableNever extends Observable<Object> {

    /* renamed from: a */
    public static final Observable<Object> f19239a = new ObservableNever();

    private ObservableNever() {
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    protected void mo34a(Observer<? super Object> assVar) {
        assVar.onSubscribe(EmptyDisposable.NEVER);
    }
}
