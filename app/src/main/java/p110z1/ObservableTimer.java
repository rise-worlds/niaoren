package p110z1;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bne */
/* loaded from: classes3.dex */
public final class ObservableTimer extends Observable<Long> {

    /* renamed from: a */
    final Scheduler f19464a;

    /* renamed from: b */
    final long f19465b;

    /* renamed from: c */
    final TimeUnit f19466c;

    public ObservableTimer(long j, TimeUnit timeUnit, Scheduler astVar) {
        this.f19465b = j;
        this.f19466c = timeUnit;
        this.f19464a = astVar;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    public void mo34a(Observer<? super Long> assVar) {
        RunnableC4588a aVar = new RunnableC4588a(assVar);
        assVar.onSubscribe(aVar);
        aVar.setResource(this.f19464a.mo9480a(aVar, this.f19465b, this.f19466c));
    }

    /* compiled from: ObservableTimer.java */
    /* renamed from: z1.bne$a */
    /* loaded from: classes3.dex */
    static final class RunnableC4588a extends AtomicReference<Disposable> implements Runnable, Disposable {
        private static final long serialVersionUID = -2809475196591179431L;
        final Observer<? super Long> downstream;

        RunnableC4588a(Observer<? super Long> assVar) {
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
            if (!isDisposed()) {
                this.downstream.onNext(0L);
                lazySet(EmptyDisposable.INSTANCE);
                this.downstream.onComplete();
            }
        }

        public void setResource(Disposable atrVar) {
            DisposableHelper.trySet(this, atrVar);
        }
    }
}
