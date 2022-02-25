package p110z1;

/* renamed from: z1.bat */
/* loaded from: classes3.dex */
public final class FlowableFlatMapPublisher<T, U> extends Flowable<U> {

    /* renamed from: b */
    final Publisher<T> f18096b;

    /* renamed from: c */
    final Function<? super T, ? extends Publisher<? extends U>> f18097c;

    /* renamed from: d */
    final boolean f18098d;

    /* renamed from: e */
    final int f18099e;

    /* renamed from: f */
    final int f18100f;

    public FlowableFlatMapPublisher(Publisher<T> dbwVar, Function<? super T, ? extends Publisher<? extends U>> aunVar, boolean z, int i, int i2) {
        this.f18096b = dbwVar;
        this.f18097c = aunVar;
        this.f18098d = z;
        this.f18099e = i;
        this.f18100f = i2;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    protected void mo9054d(Subscriber<? super U> dbxVar) {
        if (!FlowableScalarXMap.m9735a(this.f18096b, dbxVar, this.f18097c)) {
            this.f18096b.subscribe(FlowableFlatMap.m9793a(dbxVar, this.f18097c, this.f18098d, this.f18099e, this.f18100f));
        }
    }
}
