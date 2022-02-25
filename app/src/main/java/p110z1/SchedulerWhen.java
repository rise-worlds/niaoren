package p110z1;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import p110z1.Scheduler;

/* renamed from: z1.brc */
/* loaded from: classes3.dex */
public class SchedulerWhen extends Scheduler implements Disposable {

    /* renamed from: b */
    static final Disposable f19950b = new C4736g();

    /* renamed from: c */
    static final Disposable f19951c = Disposables.m9989b();

    /* renamed from: d */
    private final Scheduler f19952d;

    /* renamed from: e */
    private final FlowableProcessor<Flowable<Completable>> f19953e = UnicastProcessor.m9065T().m9111ac();

    /* renamed from: f */
    private Disposable f19954f;

    public SchedulerWhen(Function<Flowable<Flowable<Completable>>, Completable> aunVar, Scheduler astVar) {
        this.f19952d = astVar;
        try {
            this.f19954f = aunVar.apply(this.f19953e).m11317l();
        } catch (Throwable th) {
            throw ExceptionHelper.m9432a(th);
        }
    }

    @Override // p110z1.Disposable
    public void dispose() {
        this.f19954f.dispose();
    }

    @Override // p110z1.Disposable
    public boolean isDisposed() {
        return this.f19954f.isDisposed();
    }

    @Override // p110z1.Scheduler
    @AbstractC3889atm
    /* renamed from: b */
    public Scheduler.AbstractC3881c mo9034b() {
        Scheduler.AbstractC3881c b = this.f19952d.mo9034b();
        FlowableProcessor<T> ac = UnicastProcessor.m9065T().m9111ac();
        Flowable<Completable> v = ac.m10817v(new C4729a(b));
        C4734e eVar = new C4734e(ac, b);
        this.f19953e.onNext(v);
        return eVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: SchedulerWhen.java */
    /* renamed from: z1.brc$f */
    /* loaded from: classes3.dex */
    public static abstract class AbstractC4735f extends AtomicReference<Disposable> implements Disposable {
        protected abstract Disposable callActual(Scheduler.AbstractC3881c cVar, CompletableObserver arpVar);

        AbstractC4735f() {
            super(SchedulerWhen.f19950b);
        }

        void call(Scheduler.AbstractC3881c cVar, CompletableObserver arpVar) {
            Disposable atrVar = get();
            if (atrVar != SchedulerWhen.f19951c && atrVar == SchedulerWhen.f19950b) {
                Disposable callActual = callActual(cVar, arpVar);
                if (!compareAndSet(SchedulerWhen.f19950b, callActual)) {
                    callActual.dispose();
                }
            }
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return get().isDisposed();
        }

        @Override // p110z1.Disposable
        public void dispose() {
            Disposable atrVar;
            Disposable atrVar2 = SchedulerWhen.f19951c;
            do {
                atrVar = get();
                if (atrVar == SchedulerWhen.f19951c) {
                    return;
                }
            } while (!compareAndSet(atrVar, atrVar2));
            if (atrVar != SchedulerWhen.f19950b) {
                atrVar.dispose();
            }
        }
    }

    /* compiled from: SchedulerWhen.java */
    /* renamed from: z1.brc$c */
    /* loaded from: classes3.dex */
    static class C4732c extends AbstractC4735f {
        private final Runnable action;

        C4732c(Runnable runnable) {
            this.action = runnable;
        }

        @Override // p110z1.SchedulerWhen.AbstractC4735f
        protected Disposable callActual(Scheduler.AbstractC3881c cVar, CompletableObserver arpVar) {
            return cVar.mo9031a(new RunnableC4733d(this.action, arpVar));
        }
    }

    /* compiled from: SchedulerWhen.java */
    /* renamed from: z1.brc$b */
    /* loaded from: classes3.dex */
    static class C4731b extends AbstractC4735f {
        private final Runnable action;
        private final long delayTime;
        private final TimeUnit unit;

        C4731b(Runnable runnable, long j, TimeUnit timeUnit) {
            this.action = runnable;
            this.delayTime = j;
            this.unit = timeUnit;
        }

        @Override // p110z1.SchedulerWhen.AbstractC4735f
        protected Disposable callActual(Scheduler.AbstractC3881c cVar, CompletableObserver arpVar) {
            return cVar.mo9030a(new RunnableC4733d(this.action, arpVar), this.delayTime, this.unit);
        }
    }

    /* compiled from: SchedulerWhen.java */
    /* renamed from: z1.brc$d */
    /* loaded from: classes3.dex */
    static class RunnableC4733d implements Runnable {

        /* renamed from: a */
        final CompletableObserver f19958a;

        /* renamed from: b */
        final Runnable f19959b;

        RunnableC4733d(Runnable runnable, CompletableObserver arpVar) {
            this.f19959b = runnable;
            this.f19958a = arpVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                this.f19959b.run();
            } finally {
                this.f19958a.onComplete();
            }
        }
    }

    /* compiled from: SchedulerWhen.java */
    /* renamed from: z1.brc$a */
    /* loaded from: classes3.dex */
    static final class C4729a implements Function<AbstractC4735f, Completable> {

        /* renamed from: a */
        final Scheduler.AbstractC3881c f19955a;

        C4729a(Scheduler.AbstractC3881c cVar) {
            this.f19955a = cVar;
        }

        /* renamed from: a */
        public Completable apply(AbstractC4735f fVar) {
            return new C4730a(fVar);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: SchedulerWhen.java */
        /* renamed from: z1.brc$a$a */
        /* loaded from: classes3.dex */
        public final class C4730a extends Completable {

            /* renamed from: a */
            final AbstractC4735f f19956a;

            C4730a(AbstractC4735f fVar) {
                this.f19956a = fVar;
            }

            @Override // p110z1.Completable
            /* renamed from: b */
            protected void mo9001b(CompletableObserver arpVar) {
                arpVar.onSubscribe(this.f19956a);
                this.f19956a.call(C4729a.this.f19955a, arpVar);
            }
        }
    }

    /* compiled from: SchedulerWhen.java */
    /* renamed from: z1.brc$e */
    /* loaded from: classes3.dex */
    static final class C4734e extends Scheduler.AbstractC3881c {

        /* renamed from: a */
        private final AtomicBoolean f19960a = new AtomicBoolean();

        /* renamed from: b */
        private final FlowableProcessor<AbstractC4735f> f19961b;

        /* renamed from: c */
        private final Scheduler.AbstractC3881c f19962c;

        C4734e(FlowableProcessor<AbstractC4735f> buhVar, Scheduler.AbstractC3881c cVar) {
            this.f19961b = buhVar;
            this.f19962c = cVar;
        }

        @Override // p110z1.Disposable
        public void dispose() {
            if (this.f19960a.compareAndSet(false, true)) {
                this.f19961b.onComplete();
                this.f19962c.dispose();
            }
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.f19960a.get();
        }

        @Override // p110z1.Scheduler.AbstractC3881c
        @AbstractC3889atm
        /* renamed from: a */
        public Disposable mo9030a(@AbstractC3889atm Runnable runnable, long j, @AbstractC3889atm TimeUnit timeUnit) {
            C4731b bVar = new C4731b(runnable, j, timeUnit);
            this.f19961b.onNext(bVar);
            return bVar;
        }

        @Override // p110z1.Scheduler.AbstractC3881c
        @AbstractC3889atm
        /* renamed from: a */
        public Disposable mo9031a(@AbstractC3889atm Runnable runnable) {
            C4732c cVar = new C4732c(runnable);
            this.f19961b.onNext(cVar);
            return cVar;
        }
    }

    /* compiled from: SchedulerWhen.java */
    /* renamed from: z1.brc$g */
    /* loaded from: classes3.dex */
    static final class C4736g implements Disposable {
        @Override // p110z1.Disposable
        public void dispose() {
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return false;
        }

        C4736g() {
        }
    }
}
