package p110z1;

/* renamed from: z1.bjy */
/* loaded from: classes3.dex */
public final class ObservableEmpty extends Observable<Object> implements ScalarCallable<Object> {

    /* renamed from: a */
    public static final Observable<Object> f19084a = new ObservableEmpty();

    @Override // p110z1.ScalarCallable, java.util.concurrent.Callable
    public Object call() {
        return null;
    }

    private ObservableEmpty() {
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    protected void mo34a(Observer<? super Object> assVar) {
        EmptyDisposable.complete(assVar);
    }
}
