package p110z1;

import p110z1.Promise;

/* renamed from: z1.dbe */
/* loaded from: classes3.dex */
public class DeferredPromise<D, F, P> implements Promise<D, F, P> {

    /* renamed from: a */
    protected final Deferred<D, F, P> f21244a;

    /* renamed from: b */
    private final Promise<D, F, P> f21245b;

    public DeferredPromise(Deferred<D, F, P> dalVar) {
        this.f21244a = dalVar;
        this.f21245b = dalVar.mo3300a();
    }

    @Override // p110z1.Promise
    /* renamed from: b */
    public Promise.EnumC5239a mo3283b() {
        return this.f21245b.mo3283b();
    }

    @Override // p110z1.Promise
    /* renamed from: c */
    public boolean mo3281c() {
        return this.f21245b.mo3281c();
    }

    @Override // p110z1.Promise
    /* renamed from: d */
    public boolean mo3280d() {
        return this.f21245b.mo3280d();
    }

    @Override // p110z1.Promise
    /* renamed from: e */
    public boolean mo3279e() {
        return this.f21245b.mo3279e();
    }

    @Override // p110z1.Promise
    /* renamed from: a */
    public Promise<D, F, P> mo3294a(DoneCallback<D> daqVar) {
        return this.f21245b.mo3294a(daqVar);
    }

    @Override // p110z1.Promise
    /* renamed from: a */
    public Promise<D, F, P> mo3293a(DoneCallback<D> daqVar, FailCallback<F> datVar) {
        return this.f21245b.mo3293a(daqVar, datVar);
    }

    @Override // p110z1.Promise
    /* renamed from: a */
    public Promise<D, F, P> mo3292a(DoneCallback<D> daqVar, FailCallback<F> datVar, ProgressCallback<P> dawVar) {
        return this.f21245b.mo3292a(daqVar, datVar, dawVar);
    }

    @Override // p110z1.Promise
    /* renamed from: a */
    public <D_OUT, F_OUT, P_OUT> Promise<D_OUT, F_OUT, P_OUT> mo3291a(DoneFilter<D, D_OUT> darVar) {
        return this.f21245b.mo3291a(darVar);
    }

    @Override // p110z1.Promise
    /* renamed from: a */
    public <D_OUT, F_OUT, P_OUT> Promise<D_OUT, F_OUT, P_OUT> mo3290a(DoneFilter<D, D_OUT> darVar, FailFilter<F, F_OUT> dauVar) {
        return this.f21245b.mo3290a(darVar, dauVar);
    }

    @Override // p110z1.Promise
    /* renamed from: a */
    public <D_OUT, F_OUT, P_OUT> Promise<D_OUT, F_OUT, P_OUT> mo3289a(DoneFilter<D, D_OUT> darVar, FailFilter<F, F_OUT> dauVar, ProgressFilter<P, P_OUT> daxVar) {
        return this.f21245b.mo3289a(darVar, dauVar, daxVar);
    }

    @Override // p110z1.Promise
    /* renamed from: b */
    public Promise<D, F, P> mo3282b(DoneCallback<D> daqVar) {
        return this.f21245b.mo3282b(daqVar);
    }

    @Override // p110z1.Promise
    /* renamed from: a */
    public Promise<D, F, P> mo3285a(FailCallback<F> datVar) {
        return this.f21245b.mo3285a(datVar);
    }

    @Override // p110z1.Promise
    /* renamed from: a */
    public Promise<D, F, P> mo3295a(AlwaysCallback<D, F> dakVar) {
        return this.f21245b.mo3295a(dakVar);
    }

    @Override // p110z1.Promise
    /* renamed from: a */
    public Promise<D, F, P> mo3284a(ProgressCallback<P> dawVar) {
        return this.f21245b.mo3284a(dawVar);
    }

    @Override // p110z1.Promise
    /* renamed from: f */
    public void mo3278f() throws InterruptedException {
        this.f21245b.mo3278f();
    }

    @Override // p110z1.Promise
    /* renamed from: a */
    public void mo3296a(long j) throws InterruptedException {
        this.f21245b.mo3296a(j);
    }

    @Override // p110z1.Promise
    /* renamed from: a */
    public <D_OUT, F_OUT, P_OUT> Promise<D_OUT, F_OUT, P_OUT> mo3288a(DonePipe<D, D_OUT, F_OUT, P_OUT> dasVar) {
        return this.f21245b.mo3288a(dasVar);
    }

    @Override // p110z1.Promise
    /* renamed from: a */
    public <D_OUT, F_OUT, P_OUT> Promise<D_OUT, F_OUT, P_OUT> mo3287a(DonePipe<D, D_OUT, F_OUT, P_OUT> dasVar, FailPipe<F, D_OUT, F_OUT, P_OUT> davVar) {
        return this.f21245b.mo3287a(dasVar, davVar);
    }

    @Override // p110z1.Promise
    /* renamed from: a */
    public <D_OUT, F_OUT, P_OUT> Promise<D_OUT, F_OUT, P_OUT> mo3286a(DonePipe<D, D_OUT, F_OUT, P_OUT> dasVar, FailPipe<F, D_OUT, F_OUT, P_OUT> davVar, ProgressPipe<P, D_OUT, F_OUT, P_OUT> dayVar) {
        return this.f21245b.mo3286a(dasVar, davVar, dayVar);
    }
}
