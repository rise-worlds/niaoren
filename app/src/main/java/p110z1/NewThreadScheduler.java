package p110z1;

import java.util.concurrent.ThreadFactory;
import p110z1.Scheduler;

/* renamed from: z1.bqt */
/* loaded from: classes3.dex */
public final class NewThreadScheduler extends Scheduler {

    /* renamed from: b */
    final ThreadFactory f19931b;

    /* renamed from: c */
    private static final String f19928c = "RxNewThreadScheduler";

    /* renamed from: e */
    private static final String f19930e = "rx2.newthread-priority";

    /* renamed from: d */
    private static final RxThreadFactory f19929d = new RxThreadFactory(f19928c, Math.max(1, Math.min(10, Integer.getInteger(f19930e, 5).intValue())));

    public NewThreadScheduler() {
        this(f19929d);
    }

    public NewThreadScheduler(ThreadFactory threadFactory) {
        this.f19931b = threadFactory;
    }

    @Override // p110z1.Scheduler
    @AbstractC3889atm
    /* renamed from: b */
    public Scheduler.AbstractC3881c mo9034b() {
        return new NewThreadWorker(this.f19931b);
    }
}
