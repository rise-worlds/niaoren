package p110z1;

/* renamed from: z1.bif */
/* loaded from: classes3.dex */
abstract class AbstractObservableWithUpstream<T, U> extends Observable<U> implements HasUpstreamObservableSource<T> {

    /* renamed from: a */
    protected final ObservableSource<T> f18807a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AbstractObservableWithUpstream(ObservableSource<T> asqVar) {
        this.f18807a = asqVar;
    }

    @Override // p110z1.HasUpstreamObservableSource
    /* renamed from: a */
    public final ObservableSource<T> mo9587a() {
        return this.f18807a;
    }
}
