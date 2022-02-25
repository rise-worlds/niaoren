package p110z1;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import p110z1.Scheduler;

/* renamed from: z1.bkv */
/* loaded from: classes3.dex */
public final class ObservableIntervalRange extends Observable<Long> {

    /* renamed from: a */
    final Scheduler f19202a;

    /* renamed from: b */
    final long f19203b;

    /* renamed from: c */
    final long f19204c;

    /* renamed from: d */
    final long f19205d;

    /* renamed from: e */
    final long f19206e;

    /* renamed from: f */
    final TimeUnit f19207f;

    public ObservableIntervalRange(long j, long j2, long j3, long j4, TimeUnit timeUnit, Scheduler astVar) {
        this.f19205d = j3;
        this.f19206e = j4;
        this.f19207f = timeUnit;
        this.f19202a = astVar;
        this.f19203b = j;
        this.f19204c = j2;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    public void mo34a(Observer<? super Long> assVar) {
        RunnableC4486a aVar = new RunnableC4486a(assVar, this.f19203b, this.f19204c);
        assVar.onSubscribe(aVar);
        Scheduler astVar = this.f19202a;
        if (astVar instanceof TrampolineScheduler) {
            Scheduler.AbstractC3881c b = astVar.mo9034b();
            aVar.setResource(b);
            b.mo9511a(aVar, this.f19205d, this.f19206e, this.f19207f);
            return;
        }
        aVar.setResource(astVar.mo9485a(aVar, this.f19205d, this.f19206e, this.f19207f));
    }

    /* compiled from: ObservableIntervalRange.java */
    /* renamed from: z1.bkv$a */
    /* loaded from: classes3.dex */
    static final class RunnableC4486a extends AtomicReference<Disposable> implements Runnable, Disposable {
        private static final long serialVersionUID = 1891866368734007884L;
        long count;
        final Observer<? super Long> downstream;
        final long end;

        RunnableC4486a(Observer<? super Long> assVar, long j, long j2) {
            this.downstream = assVar;
            this.count = j;
            this.end = j2;
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
            if (!isDisposed()) {
                long j = this.count;
                this.downstream.onNext(Long.valueOf(j));
                if (j == this.end) {
                    DisposableHelper.dispose(this);
                    this.downstream.onComplete();
                    return;
                }
                this.count = j + 1;
            }
        }

        public void setResource(Disposable atrVar) {
            DisposableHelper.setOnce(this, atrVar);
        }
    }
}
