package p110z1;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import p110z1.Scheduler;

/* renamed from: z1.bku */
/* loaded from: classes3.dex */
public final class ObservableInterval extends Observable<Long> {

    /* renamed from: a */
    final Scheduler f19198a;

    /* renamed from: b */
    final long f19199b;

    /* renamed from: c */
    final long f19200c;

    /* renamed from: d */
    final TimeUnit f19201d;

    public ObservableInterval(long j, long j2, TimeUnit timeUnit, Scheduler astVar) {
        this.f19199b = j;
        this.f19200c = j2;
        this.f19201d = timeUnit;
        this.f19198a = astVar;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    public void mo34a(Observer<? super Long> assVar) {
        RunnableC4485a aVar = new RunnableC4485a(assVar);
        assVar.onSubscribe(aVar);
        Scheduler astVar = this.f19198a;
        if (astVar instanceof TrampolineScheduler) {
            Scheduler.AbstractC3881c b = astVar.mo9034b();
            aVar.setResource(b);
            b.mo9511a(aVar, this.f19199b, this.f19200c, this.f19201d);
            return;
        }
        aVar.setResource(astVar.mo9485a(aVar, this.f19199b, this.f19200c, this.f19201d));
    }

    /* compiled from: ObservableInterval.java */
    /* renamed from: z1.bku$a */
    /* loaded from: classes3.dex */
    static final class RunnableC4485a extends AtomicReference<Disposable> implements Runnable, Disposable {
        private static final long serialVersionUID = 346773832286157679L;
        long count;
        final Observer<? super Long> downstream;

        RunnableC4485a(Observer<? super Long> assVar) {
            this.downstream = assVar;
        }

        @Override // p110z1.Disposable
        public void dispose() {
            DisposableHelper.dispose(this);
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return get() == DisposableHelper.DISPOSED;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (get() != DisposableHelper.DISPOSED) {
                Observer<? super Long> assVar = this.downstream;
                long j = this.count;
                this.count = 1 + j;
                assVar.onNext(Long.valueOf(j));
            }
        }

        public void setResource(Disposable atrVar) {
            DisposableHelper.setOnce(this, atrVar);
        }
    }
}
