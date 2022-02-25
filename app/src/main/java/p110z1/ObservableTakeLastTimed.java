package p110z1;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: z1.bmv */
/* loaded from: classes3.dex */
public final class ObservableTakeLastTimed<T> extends AbstractObservableWithUpstream<T, T> {

    /* renamed from: b */
    final long f19422b;

    /* renamed from: c */
    final long f19423c;

    /* renamed from: d */
    final TimeUnit f19424d;

    /* renamed from: e */
    final Scheduler f19425e;

    /* renamed from: f */
    final int f19426f;

    /* renamed from: g */
    final boolean f19427g;

    public ObservableTakeLastTimed(ObservableSource<T> asqVar, long j, long j2, TimeUnit timeUnit, Scheduler astVar, int i, boolean z) {
        super(asqVar);
        this.f19422b = j;
        this.f19423c = j2;
        this.f19424d = timeUnit;
        this.f19425e = astVar;
        this.f19426f = i;
        this.f19427g = z;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    public void mo34a(Observer<? super T> assVar) {
        this.f18807a.subscribe(new C4570a(assVar, this.f19422b, this.f19423c, this.f19424d, this.f19425e, this.f19426f, this.f19427g));
    }

    /* compiled from: ObservableTakeLastTimed.java */
    /* renamed from: z1.bmv$a */
    /* loaded from: classes3.dex */
    static final class C4570a<T> extends AtomicBoolean implements Observer<T>, Disposable {
        private static final long serialVersionUID = -5677354903406201275L;
        volatile boolean cancelled;
        final long count;
        final boolean delayError;
        final Observer<? super T> downstream;
        Throwable error;
        final SpscLinkedArrayQueue<Object> queue;
        final Scheduler scheduler;
        final long time;
        final TimeUnit unit;
        Disposable upstream;

        C4570a(Observer<? super T> assVar, long j, long j2, TimeUnit timeUnit, Scheduler astVar, int i, boolean z) {
            this.downstream = assVar;
            this.count = j;
            this.time = j2;
            this.unit = timeUnit;
            this.scheduler = astVar;
            this.queue = new SpscLinkedArrayQueue<>(i);
            this.delayError = z;
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            if (DisposableHelper.validate(this.upstream, atrVar)) {
                this.upstream = atrVar;
                this.downstream.onSubscribe(this);
            }
        }

        @Override // p110z1.Observer
        public void onNext(T t) {
            SpscLinkedArrayQueue<Object> bqlVar = this.queue;
            long a = this.scheduler.mo9035a(this.unit);
            long j = this.time;
            long j2 = this.count;
            boolean z = j2 == cjm.f20759b;
            bqlVar.offer(Long.valueOf(a), t);
            while (!bqlVar.isEmpty()) {
                if (((Long) bqlVar.m9533a()).longValue() <= a - j || (!z && (bqlVar.m9523b() >> 1) > j2)) {
                    bqlVar.poll();
                    bqlVar.poll();
                } else {
                    return;
                }
            }
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            this.error = th;
            drain();
        }

        @Override // p110z1.Observer
        public void onComplete() {
            drain();
        }

        @Override // p110z1.Disposable
        public void dispose() {
            if (!this.cancelled) {
                this.cancelled = true;
                this.upstream.dispose();
                if (compareAndSet(false, true)) {
                    this.queue.clear();
                }
            }
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.cancelled;
        }

        void drain() {
            Throwable th;
            if (compareAndSet(false, true)) {
                Observer<? super T> assVar = this.downstream;
                SpscLinkedArrayQueue<Object> bqlVar = this.queue;
                boolean z = this.delayError;
                while (!this.cancelled) {
                    if (z || (th = this.error) == null) {
                        Object poll = bqlVar.poll();
                        if (poll == null) {
                            Throwable th2 = this.error;
                            if (th2 != null) {
                                assVar.onError(th2);
                                return;
                            } else {
                                assVar.onComplete();
                                return;
                            }
                        } else {
                            Object poll2 = bqlVar.poll();
                            if (((Long) poll).longValue() >= this.scheduler.mo9035a(this.unit) - this.time) {
                                assVar.onNext(poll2);
                            }
                        }
                    } else {
                        bqlVar.clear();
                        assVar.onError(th);
                        return;
                    }
                }
                bqlVar.clear();
            }
        }
    }
}
