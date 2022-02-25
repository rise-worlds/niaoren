package p110z1;

/* renamed from: z1.daz */
/* loaded from: classes3.dex */
public interface Promise<D, F, P> {

    /* compiled from: Promise.java */
    /* renamed from: z1.daz$a */
    /* loaded from: classes3.dex */
    public enum EnumC5239a {
        PENDING,
        REJECTED,
        RESOLVED
    }

    /* renamed from: a */
    Promise<D, F, P> mo3295a(AlwaysCallback<D, F> dakVar);

    /* renamed from: a */
    Promise<D, F, P> mo3294a(DoneCallback<D> daqVar);

    /* renamed from: a */
    Promise<D, F, P> mo3293a(DoneCallback<D> daqVar, FailCallback<F> datVar);

    /* renamed from: a */
    Promise<D, F, P> mo3292a(DoneCallback<D> daqVar, FailCallback<F> datVar, ProgressCallback<P> dawVar);

    /* renamed from: a */
    <D_OUT, F_OUT, P_OUT> Promise<D_OUT, F_OUT, P_OUT> mo3291a(DoneFilter<D, D_OUT> darVar);

    /* renamed from: a */
    <D_OUT, F_OUT, P_OUT> Promise<D_OUT, F_OUT, P_OUT> mo3290a(DoneFilter<D, D_OUT> darVar, FailFilter<F, F_OUT> dauVar);

    /* renamed from: a */
    <D_OUT, F_OUT, P_OUT> Promise<D_OUT, F_OUT, P_OUT> mo3289a(DoneFilter<D, D_OUT> darVar, FailFilter<F, F_OUT> dauVar, ProgressFilter<P, P_OUT> daxVar);

    /* renamed from: a */
    <D_OUT, F_OUT, P_OUT> Promise<D_OUT, F_OUT, P_OUT> mo3288a(DonePipe<D, D_OUT, F_OUT, P_OUT> dasVar);

    /* renamed from: a */
    <D_OUT, F_OUT, P_OUT> Promise<D_OUT, F_OUT, P_OUT> mo3287a(DonePipe<D, D_OUT, F_OUT, P_OUT> dasVar, FailPipe<F, D_OUT, F_OUT, P_OUT> davVar);

    /* renamed from: a */
    <D_OUT, F_OUT, P_OUT> Promise<D_OUT, F_OUT, P_OUT> mo3286a(DonePipe<D, D_OUT, F_OUT, P_OUT> dasVar, FailPipe<F, D_OUT, F_OUT, P_OUT> davVar, ProgressPipe<P, D_OUT, F_OUT, P_OUT> dayVar);

    /* renamed from: a */
    Promise<D, F, P> mo3285a(FailCallback<F> datVar);

    /* renamed from: a */
    Promise<D, F, P> mo3284a(ProgressCallback<P> dawVar);

    /* renamed from: a */
    void mo3296a(long j) throws InterruptedException;

    /* renamed from: b */
    EnumC5239a mo3283b();

    /* renamed from: b */
    Promise<D, F, P> mo3282b(DoneCallback<D> daqVar);

    /* renamed from: c */
    boolean mo3281c();

    /* renamed from: d */
    boolean mo3280d();

    /* renamed from: e */
    boolean mo3279e();

    /* renamed from: f */
    void mo3278f() throws InterruptedException;
}
