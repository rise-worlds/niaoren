package p110z1;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bfc */
/* loaded from: classes3.dex */
public final class MaybeDelay<T> extends AbstractMaybeWithUpstream<T, T> {

    /* renamed from: b */
    final long f18564b;

    /* renamed from: c */
    final TimeUnit f18565c;

    /* renamed from: d */
    final Scheduler f18566d;

    public MaybeDelay(MaybeSource<T> asiVar, long j, TimeUnit timeUnit, Scheduler astVar) {
        super(asiVar);
        this.f18564b = j;
        this.f18565c = timeUnit;
        this.f18566d = astVar;
    }

    @Override // p110z1.Maybe
    /* renamed from: b */
    protected void mo8992b(MaybeObserver<? super T> asfVar) {
        this.f18529a.mo10646a(new RunnableC4272a(asfVar, this.f18564b, this.f18565c, this.f18566d));
    }

    /* compiled from: MaybeDelay.java */
    /* renamed from: z1.bfc$a */
    /* loaded from: classes3.dex */
    static final class RunnableC4272a<T> extends AtomicReference<Disposable> implements Runnable, MaybeObserver<T>, Disposable {
        private static final long serialVersionUID = 5566860102500855068L;
        final long delay;
        final MaybeObserver<? super T> downstream;
        Throwable error;
        final Scheduler scheduler;
        final TimeUnit unit;
        T value;

        RunnableC4272a(MaybeObserver<? super T> asfVar, long j, TimeUnit timeUnit, Scheduler astVar) {
            this.downstream = asfVar;
            this.delay = j;
            this.unit = timeUnit;
            this.scheduler = astVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            Throwable th = this.error;
            if (th != null) {
                this.downstream.onError(th);
                return;
            }
            T t = this.value;
            if (t != null) {
                this.downstream.onSuccess(t);
            } else {
                this.downstream.onComplete();
            }
        }

        @Override // p110z1.Disposable
        public void dispose() {
            DisposableHelper.dispose(this);
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return DisposableHelper.isDisposed(get());
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.setOnce(this, atrVar)) {
                this.downstream.onSubscribe(this);
            }
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSuccess(T t) {
            this.value = t;
            schedule();
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onError(Throwable th) {
            this.error = th;
            schedule();
        }

        @Override // p110z1.MaybeObserver
        public void onComplete() {
            schedule();
        }

        void schedule() {
            DisposableHelper.replace(this, this.scheduler.mo9480a(this, this.delay, this.unit));
        }
    }
}
