package p110z1;

/* renamed from: z1.azp */
/* loaded from: classes3.dex */
public final class FlowableConcatMapPublisher<T, R> extends Flowable<R> {

    /* renamed from: b */
    final Publisher<T> f17969b;

    /* renamed from: c */
    final Function<? super T, ? extends Publisher<? extends R>> f17970c;

    /* renamed from: d */
    final int f17971d;

    /* renamed from: e */
    final ErrorMode f17972e;

    public FlowableConcatMapPublisher(Publisher<T> dbwVar, Function<? super T, ? extends Publisher<? extends R>> aunVar, int i, ErrorMode bsuVar) {
        this.f17969b = dbwVar;
        this.f17970c = aunVar;
        this.f17971d = i;
        this.f17972e = bsuVar;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    protected void mo9054d(Subscriber<? super R> dbxVar) {
        if (!FlowableScalarXMap.m9735a(this.f17969b, dbxVar, this.f17970c)) {
            this.f17969b.subscribe(FlowableConcatMap.m9797a(dbxVar, this.f17970c, this.f17971d, this.f17972e));
        }
    }
}
