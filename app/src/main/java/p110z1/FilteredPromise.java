package p110z1;

/* renamed from: z1.dbf */
/* loaded from: classes3.dex */
public class FilteredPromise<D, F, P, D_OUT, F_OUT, P_OUT> extends DeferredObject<D_OUT, F_OUT, P_OUT> implements Promise<D_OUT, F_OUT, P_OUT> {

    /* renamed from: a */
    protected static final C5245a f21246a = new C5245a();

    /* renamed from: j */
    protected static final C5246b f21247j = new C5246b();

    /* renamed from: k */
    protected static final C5247c f21248k = new C5247c();

    /* renamed from: l */
    private final DoneFilter<D, D_OUT> f21249l;

    /* renamed from: m */
    private final FailFilter<F, F_OUT> f21250m;

    /* renamed from: n */
    private final ProgressFilter<P, P_OUT> f21251n;

    /* compiled from: FilteredPromise.java */
    /* renamed from: z1.dbf$a */
    /* loaded from: classes3.dex */
    public static final class C5245a<D> implements DoneFilter<D, D> {
        @Override // p110z1.DoneFilter
        /* renamed from: a */
        public D mo3274a(D d) {
            return d;
        }
    }

    /* compiled from: FilteredPromise.java */
    /* renamed from: z1.dbf$b */
    /* loaded from: classes3.dex */
    public static final class C5246b<F> implements FailFilter<F, F> {
        @Override // p110z1.FailFilter
        /* renamed from: a */
        public F mo3273a(F f) {
            return f;
        }
    }

    /* compiled from: FilteredPromise.java */
    /* renamed from: z1.dbf$c */
    /* loaded from: classes3.dex */
    public static final class C5247c<P> implements ProgressFilter<P, P> {
        @Override // p110z1.ProgressFilter
        /* renamed from: a */
        public P mo3272a(P p) {
            return p;
        }
    }

    public FilteredPromise(Promise<D, F, P> dazVar, DoneFilter<D, D_OUT> darVar, FailFilter<F, F_OUT> dauVar, ProgressFilter<P, P_OUT> daxVar) {
        this.f21249l = darVar == null ? f21246a : darVar;
        this.f21250m = dauVar == null ? f21247j : dauVar;
        this.f21251n = daxVar == null ? f21248k : daxVar;
        dazVar.mo3282b(new DoneCallback<D>() { // from class: z1.dbf.3
            /* JADX WARN: Multi-variable type inference failed */
            @Override // p110z1.DoneCallback
            public void onDone(D d) {
                FilteredPromise dbfVar = FilteredPromise.this;
                dbfVar.mo3299a((FilteredPromise) dbfVar.f21249l.mo3274a(d));
            }
        }).mo3285a(new FailCallback<F>() { // from class: z1.dbf.2
            /* JADX WARN: Multi-variable type inference failed */
            @Override // p110z1.FailCallback
            public void onFail(F f) {
                FilteredPromise dbfVar = FilteredPromise.this;
                dbfVar.mo3298b((FilteredPromise) dbfVar.f21250m.mo3273a(f));
            }
        }).mo3284a(new ProgressCallback<P>() { // from class: z1.dbf.1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // p110z1.ProgressCallback
            /* renamed from: a */
            public void mo3266a(P p) {
                FilteredPromise dbfVar = FilteredPromise.this;
                dbfVar.mo3297c((FilteredPromise) dbfVar.f21251n.mo3272a(p));
            }
        });
    }
}
