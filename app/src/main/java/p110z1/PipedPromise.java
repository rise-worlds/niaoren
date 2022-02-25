package p110z1;

/* renamed from: z1.dbh */
/* loaded from: classes3.dex */
public class PipedPromise<D, F, P, D_OUT, F_OUT, P_OUT> extends DeferredObject<D_OUT, F_OUT, P_OUT> implements Promise<D_OUT, F_OUT, P_OUT> {
    public PipedPromise(Promise<D, F, P> dazVar, final DonePipe<D, D_OUT, F_OUT, P_OUT> dasVar, final FailPipe<F, D_OUT, F_OUT, P_OUT> davVar, final ProgressPipe<P, D_OUT, F_OUT, P_OUT> dayVar) {
        dazVar.mo3282b(new DoneCallback<D>() { // from class: z1.dbh.3
            @Override // p110z1.DoneCallback
            public void onDone(D d) {
                DonePipe dasVar2 = dasVar;
                if (dasVar2 != null) {
                    PipedPromise.this.m3271a((Promise) dasVar2.m3338a(d));
                } else {
                    PipedPromise.this.mo3299a((PipedPromise) d);
                }
            }
        }).mo3285a(new FailCallback<F>() { // from class: z1.dbh.2
            @Override // p110z1.FailCallback
            public void onFail(F f) {
                FailPipe davVar2 = davVar;
                if (davVar2 != null) {
                    PipedPromise.this.m3271a((Promise) davVar2.m3337a(f));
                } else {
                    PipedPromise.this.mo3298b((PipedPromise) f);
                }
            }
        }).mo3284a(new ProgressCallback<P>() { // from class: z1.dbh.1
            @Override // p110z1.ProgressCallback
            /* renamed from: a */
            public void mo3266a(P p) {
                ProgressPipe dayVar2 = dayVar;
                if (dayVar2 != null) {
                    PipedPromise.this.m3271a((Promise) dayVar2.m3336a(p));
                } else {
                    PipedPromise.this.mo3297c(p);
                }
            }
        });
    }

    /* renamed from: a */
    protected Promise<D_OUT, F_OUT, P_OUT> m3271a(Promise<D_OUT, F_OUT, P_OUT> dazVar) {
        dazVar.mo3282b(new DoneCallback<D_OUT>() { // from class: z1.dbh.6
            @Override // p110z1.DoneCallback
            public void onDone(D_OUT d_out) {
                PipedPromise.this.mo3299a((PipedPromise) d_out);
            }
        }).mo3285a(new FailCallback<F_OUT>() { // from class: z1.dbh.5
            @Override // p110z1.FailCallback
            public void onFail(F_OUT f_out) {
                PipedPromise.this.mo3298b((PipedPromise) f_out);
            }
        }).mo3284a(new ProgressCallback<P_OUT>() { // from class: z1.dbh.4
            @Override // p110z1.ProgressCallback
            /* renamed from: a */
            public void mo3266a(P_OUT p_out) {
                PipedPromise.this.mo3297c(p_out);
            }
        });
        return dazVar;
    }
}
