package p110z1;

import java.util.concurrent.TimeUnit;
import p110z1.Scheduler;

/* renamed from: z1.bqq */
/* loaded from: classes3.dex */
public final class ImmediateThinScheduler extends Scheduler {

    /* renamed from: b */
    public static final Scheduler f19895b = new ImmediateThinScheduler();

    /* renamed from: c */
    static final Scheduler.AbstractC3881c f19896c = new C4720a();

    /* renamed from: d */
    static final Disposable f19897d = Disposables.m9995a();

    static {
        f19897d.dispose();
    }

    private ImmediateThinScheduler() {
    }

    @Override // p110z1.Scheduler
    @AbstractC3889atm
    /* renamed from: a */
    public Disposable mo9481a(@AbstractC3889atm Runnable runnable) {
        runnable.run();
        return f19897d;
    }

    @Override // p110z1.Scheduler
    @AbstractC3889atm
    /* renamed from: a */
    public Disposable mo9480a(@AbstractC3889atm Runnable runnable, long j, TimeUnit timeUnit) {
        throw new UnsupportedOperationException("This scheduler doesn't support delayed execution");
    }

    @Override // p110z1.Scheduler
    @AbstractC3889atm
    /* renamed from: a */
    public Disposable mo9485a(@AbstractC3889atm Runnable runnable, long j, long j2, TimeUnit timeUnit) {
        throw new UnsupportedOperationException("This scheduler doesn't support periodic execution");
    }

    @Override // p110z1.Scheduler
    @AbstractC3889atm
    /* renamed from: b */
    public Scheduler.AbstractC3881c mo9034b() {
        return f19896c;
    }

    /* compiled from: ImmediateThinScheduler.java */
    /* renamed from: z1.bqq$a */
    /* loaded from: classes3.dex */
    static final class C4720a extends Scheduler.AbstractC3881c {
        @Override // p110z1.Disposable
        public void dispose() {
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return false;
        }

        C4720a() {
        }

        @Override // p110z1.Scheduler.AbstractC3881c
        @AbstractC3889atm
        /* renamed from: a */
        public Disposable mo9031a(@AbstractC3889atm Runnable runnable) {
            runnable.run();
            return ImmediateThinScheduler.f19897d;
        }

        @Override // p110z1.Scheduler.AbstractC3881c
        @AbstractC3889atm
        /* renamed from: a */
        public Disposable mo9030a(@AbstractC3889atm Runnable runnable, long j, @AbstractC3889atm TimeUnit timeUnit) {
            throw new UnsupportedOperationException("This scheduler doesn't support delayed execution");
        }

        @Override // p110z1.Scheduler.AbstractC3881c
        @AbstractC3889atm
        /* renamed from: a */
        public Disposable mo9511a(@AbstractC3889atm Runnable runnable, long j, long j2, TimeUnit timeUnit) {
            throw new UnsupportedOperationException("This scheduler doesn't support periodic execution");
        }
    }
}
