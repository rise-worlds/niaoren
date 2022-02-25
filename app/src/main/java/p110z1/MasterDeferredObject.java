package p110z1;

import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: z1.dbi */
/* loaded from: classes3.dex */
public class MasterDeferredObject extends DeferredObject<MultipleResults, OneReject, MasterProgress> implements Promise<MultipleResults, OneReject, MasterProgress> {

    /* renamed from: a */
    private final int f21265a;

    /* renamed from: j */
    private final AtomicInteger f21266j = new AtomicInteger();

    /* renamed from: k */
    private final AtomicInteger f21267k = new AtomicInteger();

    /* renamed from: l */
    private final MultipleResults f21268l;

    public MasterDeferredObject(Promise... dazVarArr) {
        if (dazVarArr == null || dazVarArr.length == 0) {
            throw new IllegalArgumentException("Promises is null or empty");
        }
        this.f21265a = dazVarArr.length;
        this.f21268l = new MultipleResults(this.f21265a);
        final int i = 0;
        for (final Promise dazVar : dazVarArr) {
            i++;
            dazVar.mo3285a(new FailCallback<Object>() { // from class: z1.dbi.3
                @Override // p110z1.FailCallback
                public void onFail(Object obj) {
                    synchronized (MasterDeferredObject.this) {
                        if (MasterDeferredObject.this.mo3281c()) {
                            MasterDeferredObject.this.mo3297c((MasterDeferredObject) new MasterProgress(MasterDeferredObject.this.f21266j.get(), MasterDeferredObject.this.f21267k.incrementAndGet(), MasterDeferredObject.this.f21265a));
                            MasterDeferredObject.this.mo3298b((MasterDeferredObject) new OneReject(i, dazVar, obj));
                        }
                    }
                }
            }).mo3284a(new ProgressCallback() { // from class: z1.dbi.2
                @Override // p110z1.ProgressCallback
                /* renamed from: a */
                public void mo3266a(Object obj) {
                    synchronized (MasterDeferredObject.this) {
                        if (MasterDeferredObject.this.mo3281c()) {
                            MasterDeferredObject.this.mo3297c((MasterDeferredObject) new OneProgress(MasterDeferredObject.this.f21266j.get(), MasterDeferredObject.this.f21267k.get(), MasterDeferredObject.this.f21265a, i, dazVar, obj));
                        }
                    }
                }
            }).mo3282b(new DoneCallback() { // from class: z1.dbi.1
                @Override // p110z1.DoneCallback
                public void onDone(Object obj) {
                    synchronized (MasterDeferredObject.this) {
                        if (MasterDeferredObject.this.mo3281c()) {
                            MasterDeferredObject.this.f21268l.m3260a(i, new OneResult(i, dazVar, obj));
                            int incrementAndGet = MasterDeferredObject.this.f21266j.incrementAndGet();
                            MasterDeferredObject.this.mo3297c((MasterDeferredObject) new MasterProgress(incrementAndGet, MasterDeferredObject.this.f21267k.get(), MasterDeferredObject.this.f21265a));
                            if (incrementAndGet == MasterDeferredObject.this.f21265a) {
                                MasterDeferredObject.this.mo3299a((MasterDeferredObject) MasterDeferredObject.this.f21268l);
                            }
                        }
                    }
                }
            });
        }
    }
}
