package p110z1;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: z1.bnj */
/* loaded from: classes3.dex */
public final class ObservableWindow<T> extends AbstractObservableWithUpstream<T, Observable<T>> {

    /* renamed from: b */
    final long f19482b;

    /* renamed from: c */
    final long f19483c;

    /* renamed from: d */
    final int f19484d;

    public ObservableWindow(ObservableSource<T> asqVar, long j, long j2, int i) {
        super(asqVar);
        this.f19482b = j;
        this.f19483c = j2;
        this.f19484d = i;
    }

    @Override // p110z1.Observable
    /* renamed from: a */
    public void mo34a(Observer<? super Observable<T>> assVar) {
        if (this.f19482b == this.f19483c) {
            this.f18807a.subscribe(new RunnableC4594a(assVar, this.f19482b, this.f19484d));
        } else {
            this.f18807a.subscribe(new RunnableC4595b(assVar, this.f19482b, this.f19483c, this.f19484d));
        }
    }

    /* compiled from: ObservableWindow.java */
    /* renamed from: z1.bnj$a */
    /* loaded from: classes3.dex */
    static final class RunnableC4594a<T> extends AtomicInteger implements Runnable, Observer<T>, Disposable {
        private static final long serialVersionUID = -7481782523886138128L;
        volatile boolean cancelled;
        final int capacityHint;
        final long count;
        final Observer<? super Observable<T>> downstream;
        long size;
        Disposable upstream;
        UnicastSubject<T> window;

        RunnableC4594a(Observer<? super Observable<T>> assVar, long j, int i) {
            this.downstream = assVar;
            this.count = j;
            this.capacityHint = i;
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
            UnicastSubject<T> bvaVar = this.window;
            if (bvaVar == null && !this.cancelled) {
                bvaVar = UnicastSubject.m8942a(this.capacityHint, (Runnable) this);
                this.window = bvaVar;
                this.downstream.onNext(bvaVar);
            }
            if (bvaVar != null) {
                bvaVar.onNext(t);
                long j = this.size + 1;
                this.size = j;
                if (j >= this.count) {
                    this.size = 0L;
                    this.window = null;
                    bvaVar.onComplete();
                    if (this.cancelled) {
                        this.upstream.dispose();
                    }
                }
            }
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            UnicastSubject<T> bvaVar = this.window;
            if (bvaVar != null) {
                this.window = null;
                bvaVar.onError(th);
            }
            this.downstream.onError(th);
        }

        @Override // p110z1.Observer
        public void onComplete() {
            UnicastSubject<T> bvaVar = this.window;
            if (bvaVar != null) {
                this.window = null;
                bvaVar.onComplete();
            }
            this.downstream.onComplete();
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.cancelled = true;
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.cancelled;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.cancelled) {
                this.upstream.dispose();
            }
        }
    }

    /* compiled from: ObservableWindow.java */
    /* renamed from: z1.bnj$b */
    /* loaded from: classes3.dex */
    static final class RunnableC4595b<T> extends AtomicBoolean implements Runnable, Observer<T>, Disposable {
        private static final long serialVersionUID = 3366976432059579510L;
        volatile boolean cancelled;
        final int capacityHint;
        final long count;
        final Observer<? super Observable<T>> downstream;
        long firstEmission;
        long index;
        final long skip;
        Disposable upstream;
        final AtomicInteger wip = new AtomicInteger();
        final ArrayDeque<UnicastSubject<T>> windows = new ArrayDeque<>();

        RunnableC4595b(Observer<? super Observable<T>> assVar, long j, long j2, int i) {
            this.downstream = assVar;
            this.count = j;
            this.skip = j2;
            this.capacityHint = i;
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
            ArrayDeque<UnicastSubject<T>> arrayDeque = this.windows;
            long j = this.index;
            long j2 = this.skip;
            if (j % j2 == 0 && !this.cancelled) {
                this.wip.getAndIncrement();
                UnicastSubject<T> a = UnicastSubject.m8942a(this.capacityHint, (Runnable) this);
                arrayDeque.offer(a);
                this.downstream.onNext(a);
            }
            long j3 = this.firstEmission + 1;
            Iterator<UnicastSubject<T>> it = arrayDeque.iterator();
            while (it.hasNext()) {
                it.next().onNext(t);
            }
            if (j3 >= this.count) {
                arrayDeque.poll().onComplete();
                if (!arrayDeque.isEmpty() || !this.cancelled) {
                    this.firstEmission = j3 - j2;
                } else {
                    this.upstream.dispose();
                    return;
                }
            } else {
                this.firstEmission = j3;
            }
            this.index = j + 1;
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            ArrayDeque<UnicastSubject<T>> arrayDeque = this.windows;
            while (!arrayDeque.isEmpty()) {
                arrayDeque.poll().onError(th);
            }
            this.downstream.onError(th);
        }

        @Override // p110z1.Observer
        public void onComplete() {
            ArrayDeque<UnicastSubject<T>> arrayDeque = this.windows;
            while (!arrayDeque.isEmpty()) {
                arrayDeque.poll().onComplete();
            }
            this.downstream.onComplete();
        }

        @Override // p110z1.Disposable
        public void dispose() {
            this.cancelled = true;
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return this.cancelled;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.wip.decrementAndGet() == 0 && this.cancelled) {
                this.upstream.dispose();
            }
        }
    }
}
