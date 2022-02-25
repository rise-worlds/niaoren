package p110z1;

/* renamed from: z1.bmh */
/* loaded from: classes3.dex */
public final class ObservableSerialized<T> extends AbstractObservableWithUpstream<T, T> {
    public ObservableSerialized(Observable<T> aslVar) {
        super(aslVar);
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    protected void mo34a(Observer<? super T> assVar) {
        this.f18807a.subscribe(new SerializedObserver(assVar));
    }
}
