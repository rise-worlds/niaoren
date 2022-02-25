package p110z1;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bgv */
/* loaded from: classes3.dex */
public final class MaybeSubscribeOn<T> extends AbstractMaybeWithUpstream<T, T> {

    /* renamed from: b */
    final Scheduler f18695b;

    public MaybeSubscribeOn(MaybeSource<T> asiVar, Scheduler astVar) {
        super(asiVar);
        this.f18695b = astVar;
    }

    @Override // p110z1.Maybe
    /* renamed from: b */
    protected void mo8992b(MaybeObserver<? super T> asfVar) {
        C4320a aVar = new C4320a(asfVar);
        asfVar.onSubscribe(aVar);
        aVar.task.replace(this.f18695b.mo9481a(new RunnableC4321b(aVar, this.f18529a)));
    }

    /* compiled from: MaybeSubscribeOn.java */
    /* renamed from: z1.bgv$b */
    /* loaded from: classes3.dex */
    static final class RunnableC4321b<T> implements Runnable {

        /* renamed from: a */
        final MaybeObserver<? super T> f18696a;

        /* renamed from: b */
        final MaybeSource<T> f18697b;

        RunnableC4321b(MaybeObserver<? super T> asfVar, MaybeSource<T> asiVar) {
            this.f18696a = asfVar;
            this.f18697b = asiVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f18697b.mo10646a(this.f18696a);
        }
    }

    /* compiled from: MaybeSubscribeOn.java */
    /* renamed from: z1.bgv$a */
    /* loaded from: classes3.dex */
    static final class C4320a<T> extends AtomicReference<Disposable> implements MaybeObserver<T>, Disposable {
        private static final long serialVersionUID = 8571289934935992137L;
        final MaybeObserver<? super T> downstream;
        final SequentialDisposable task = new SequentialDisposable();

        C4320a(MaybeObserver<? super T> asfVar) {
            this.downstream = asfVar;
        }

        @Override // p110z1.Disposable
        public void dispose() {
            DisposableHelper.dispose(this);
            this.task.dispose();
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return DisposableHelper.isDisposed(get());
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSubscribe(Disposable atrVar) {
            DisposableHelper.setOnce(this, atrVar);
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onSuccess(T t) {
            this.downstream.onSuccess(t);
        }

        @Override // p110z1.MaybeObserver, p110z1.SingleObserver
        public void onError(Throwable th) {
            this.downstream.onError(th);
        }

        @Override // p110z1.MaybeObserver
        public void onComplete() {
            this.downstream.onComplete();
        }
    }
}
