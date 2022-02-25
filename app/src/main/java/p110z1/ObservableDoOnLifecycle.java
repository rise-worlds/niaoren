package p110z1;

/* renamed from: z1.bju */
/* loaded from: classes3.dex */
public final class ObservableDoOnLifecycle<T> extends AbstractObservableWithUpstream<T, T> {

    /* renamed from: b */
    private final Consumer<? super Disposable> f19056b;

    /* renamed from: c */
    private final Action f19057c;

    public ObservableDoOnLifecycle(Observable<T> aslVar, Consumer<? super Disposable> aumVar, Action augVar) {
        super(aslVar);
        this.f19056b = aumVar;
        this.f19057c = augVar;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    protected void mo34a(Observer<? super T> assVar) {
        this.f18807a.subscribe(new DisposableLambdaObserver(assVar, this.f19056b, this.f19057c));
    }
}
