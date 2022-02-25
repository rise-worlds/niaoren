package p110z1;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: z1.bmm */
/* loaded from: classes3.dex */
public final class ObservableSkipLastTimed<T> extends AbstractObservableWithUpstream<T, T> {

    /* renamed from: b */
    final long f19381b;

    /* renamed from: c */
    final TimeUnit f19382c;

    /* renamed from: d */
    final Scheduler f19383d;

    /* renamed from: e */
    final int f19384e;

    /* renamed from: f */
    final boolean f19385f;

    public ObservableSkipLastTimed(ObservableSource<T> asqVar, long j, TimeUnit timeUnit, Scheduler astVar, int i, boolean z) {
        super(asqVar);
        this.f19381b = j;
        this.f19382c = timeUnit;
        this.f19383d = astVar;
        this.f19384e = i;
        this.f19385f = z;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    public void mo34a(Observer<? super T> assVar) {
        this.f18807a.subscribe(new C4558a(assVar, this.f19381b, this.f19382c, this.f19383d, this.f19384e, this.f19385f));
    }

    /* compiled from: ObservableSkipLastTimed.java */
    /* renamed from: z1.bmm$a */
    /* loaded from: classes3.dex */
    static final class C4558a<T> extends AtomicInteger implements Observer<T>, Disposable {
        private static final long serialVersionUID = -5677354903406201275L;
        volatile boolean cancelled;
        final boolean delayError;
        volatile boolean done;
        final Observer<? super T> downstream;
        Throwable error;
        final SpscLinkedArrayQueue<Object> queue;
        final Scheduler scheduler;
        final long time;
        final TimeUnit unit;
        Disposable upstream;

        C4558a(Observer<? super T> assVar, long j, TimeUnit timeUnit, Scheduler astVar, int i, boolean z) {
            this.downstream = assVar;
            this.time = j;
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
            this.queue.offer(Long.valueOf(this.scheduler.mo9035a(this.unit)), t);
            drain();
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            this.error = th;
            this.done = true;
            drain();
        }

        @Override // p110z1.Observer
        public void onComplete() {
            this.done = true;
            drain();
        }

        @Override // p110z1.Disposable
        public void dispose() {
            if (!this.cancelled) {
                this.cancelled = true;
                this.upstream.dispose();
                if (getAndIncrement() == 0) {
                    this.queue.clear();
                }
            }
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.cancelled;
        }

        void drain() {
            if (getAndIncrement() == 0) {
                Observer<? super T> assVar = this.downstream;
                SpscLinkedArrayQueue<Object> bqlVar = this.queue;
                boolean z = this.delayError;
                TimeUnit timeUnit = this.unit;
                Scheduler astVar = this.scheduler;
                long j = this.time;
                int i = 1;
                while (!this.cancelled) {
                    boolean z2 = this.done;
                    Long l = (Long) bqlVar.m9533a();
                    boolean z3 = l == null;
                    long a = astVar.mo9035a(timeUnit);
                    if (!z3 && l.longValue() > a - j) {
                        z3 = true;
                    }
                    if (z2) {
                        if (!z) {
                            Throwable th = this.error;
                            if (th != null) {
                                this.queue.clear();
                                assVar.onError(th);
                                return;
                            } else if (z3) {
                                assVar.onComplete();
                                return;
                            }
                        } else if (z3) {
                            Throwable th2 = this.error;
                            if (th2 != null) {
                                assVar.onError(th2);
                                return;
                            } else {
                                assVar.onComplete();
                                return;
                            }
                        }
                    }
                    if (z3) {
                        i = addAndGet(-i);
                        if (i == 0) {
                            return;
                        }
                    } else {
                        bqlVar.poll();
                        assVar.onNext(bqlVar.poll());
                    }
                }
                this.queue.clear();
            }
        }
    }
}
