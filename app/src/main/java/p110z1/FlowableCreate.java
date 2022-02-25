package p110z1;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.azv */
/* loaded from: classes3.dex */
public final class FlowableCreate<T> extends Flowable<T> {

    /* renamed from: b */
    final FlowableOnSubscribe<T> f17980b;

    /* renamed from: c */
    final BackpressureStrategy f17981c;

    public FlowableCreate(FlowableOnSubscribe<T> aryVar, BackpressureStrategy arlVar) {
        this.f17980b = aryVar;
        this.f17981c = arlVar;
    }

    @Override // p110z1.Flowable
    /* renamed from: d */
    public void mo9054d(Subscriber<? super T> dbxVar) {
        AbstractC4027a aVar;
        switch (this.f17981c) {
            case MISSING:
                aVar = new C4032f(dbxVar);
                break;
            case ERROR:
                aVar = new C4030d(dbxVar);
                break;
            case DROP:
                aVar = new C4029c(dbxVar);
                break;
            case LATEST:
                aVar = new C4031e(dbxVar);
                break;
            default:
                aVar = new C4028b(dbxVar, m11274a());
                break;
        }
        dbxVar.onSubscribe(aVar);
        try {
            this.f17980b.subscribe(aVar);
        } catch (Throwable th) {
            Exceptions.m9983b(th);
            aVar.onError(th);
        }
    }

    /* compiled from: FlowableCreate.java */
    /* renamed from: z1.azv$h */
    /* loaded from: classes3.dex */
    static final class C4034h<T> extends AtomicInteger implements FlowableEmitter<T> {
        private static final long serialVersionUID = 4883307006032401862L;
        volatile boolean done;
        final AbstractC4027a<T> emitter;
        final AtomicThrowable error = new AtomicThrowable();
        final SimplePlainQueue<T> queue = new SpscLinkedArrayQueue(16);

        @Override // p110z1.FlowableEmitter
        public FlowableEmitter<T> serialize() {
            return this;
        }

        C4034h(AbstractC4027a<T> aVar) {
            this.emitter = aVar;
        }

        @Override // p110z1.Emitter
        public void onNext(T t) {
            if (!this.emitter.isCancelled() && !this.done) {
                if (t == null) {
                    onError(new NullPointerException("onNext called with null. Null values are generally not allowed in 2.x operators and sources."));
                    return;
                }
                if (get() != 0 || !compareAndSet(0, 1)) {
                    SimplePlainQueue<T> avvVar = this.queue;
                    synchronized (avvVar) {
                        avvVar.offer(t);
                    }
                    if (getAndIncrement() != 0) {
                        return;
                    }
                } else {
                    this.emitter.onNext(t);
                    if (decrementAndGet() == 0) {
                        return;
                    }
                }
                drainLoop();
            }
        }

        @Override // p110z1.Emitter
        public void onError(Throwable th) {
            if (!tryOnError(th)) {
                RxJavaPlugins.m9212a(th);
            }
        }

        @Override // p110z1.FlowableEmitter
        public boolean tryOnError(Throwable th) {
            if (this.emitter.isCancelled() || this.done) {
                return false;
            }
            if (th == null) {
                th = new NullPointerException("onError called with null. Null values are generally not allowed in 2.x operators and sources.");
            }
            if (!this.error.addThrowable(th)) {
                return false;
            }
            this.done = true;
            drain();
            return true;
        }

        @Override // p110z1.Emitter
        public void onComplete() {
            if (!this.emitter.isCancelled() && !this.done) {
                this.done = true;
                drain();
            }
        }

        void drain() {
            if (getAndIncrement() == 0) {
                drainLoop();
            }
        }

        void drainLoop() {
            AbstractC4027a<T> aVar = this.emitter;
            SimplePlainQueue<T> avvVar = this.queue;
            AtomicThrowable bsnVar = this.error;
            int i = 1;
            while (!aVar.isCancelled()) {
                if (bsnVar.get() != null) {
                    avvVar.clear();
                    aVar.onError(bsnVar.terminate());
                    return;
                }
                boolean z = this.done;
                T poll = avvVar.poll();
                boolean z2 = poll == null;
                if (z && z2) {
                    aVar.onComplete();
                    return;
                } else if (z2) {
                    i = addAndGet(-i);
                    if (i == 0) {
                        return;
                    }
                } else {
                    aVar.onNext(poll);
                }
            }
            avvVar.clear();
        }

        @Override // p110z1.FlowableEmitter
        public void setDisposable(Disposable atrVar) {
            this.emitter.setDisposable(atrVar);
        }

        @Override // p110z1.FlowableEmitter
        public void setCancellable(Cancellable aulVar) {
            this.emitter.setCancellable(aulVar);
        }

        @Override // p110z1.FlowableEmitter
        public long requested() {
            return this.emitter.requested();
        }

        @Override // p110z1.FlowableEmitter
        public boolean isCancelled() {
            return this.emitter.isCancelled();
        }

        @Override // java.util.concurrent.atomic.AtomicInteger
        public String toString() {
            return this.emitter.toString();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: FlowableCreate.java */
    /* renamed from: z1.azv$a */
    /* loaded from: classes3.dex */
    public static abstract class AbstractC4027a<T> extends AtomicLong implements FlowableEmitter<T>, dby {
        private static final long serialVersionUID = 7326289992464377023L;
        final Subscriber<? super T> downstream;
        final SequentialDisposable serial = new SequentialDisposable();

        void onRequested() {
        }

        void onUnsubscribed() {
        }

        AbstractC4027a(Subscriber<? super T> dbxVar) {
            this.downstream = dbxVar;
        }

        @Override // p110z1.Emitter
        public void onComplete() {
            complete();
        }

        protected void complete() {
            if (!isCancelled()) {
                try {
                    this.downstream.onComplete();
                } finally {
                    this.serial.dispose();
                }
            }
        }

        @Override // p110z1.Emitter
        public final void onError(Throwable th) {
            if (!tryOnError(th)) {
                RxJavaPlugins.m9212a(th);
            }
        }

        @Override // p110z1.FlowableEmitter
        public boolean tryOnError(Throwable th) {
            return error(th);
        }

        /* JADX WARN: Finally extract failed */
        protected boolean error(Throwable th) {
            if (th == null) {
                th = new NullPointerException("onError called with null. Null values are generally not allowed in 2.x operators and sources.");
            }
            if (isCancelled()) {
                return false;
            }
            try {
                this.downstream.onError(th);
                this.serial.dispose();
                return true;
            } catch (Throwable th2) {
                this.serial.dispose();
                throw th2;
            }
        }

        @Override // p110z1.dby
        public final void cancel() {
            this.serial.dispose();
            onUnsubscribed();
        }

        @Override // p110z1.FlowableEmitter
        public final boolean isCancelled() {
            return this.serial.isDisposed();
        }

        @Override // p110z1.dby
        public final void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                BackpressureHelper.m9449a(this, j);
                onRequested();
            }
        }

        @Override // p110z1.FlowableEmitter
        public final void setDisposable(Disposable atrVar) {
            this.serial.update(atrVar);
        }

        @Override // p110z1.FlowableEmitter
        public final void setCancellable(Cancellable aulVar) {
            setDisposable(new CancellableDisposable(aulVar));
        }

        @Override // p110z1.FlowableEmitter
        public final long requested() {
            return get();
        }

        @Override // p110z1.FlowableEmitter
        public final FlowableEmitter<T> serialize() {
            return new C4034h(this);
        }

        @Override // java.util.concurrent.atomic.AtomicLong
        public String toString() {
            return String.format("%s{%s}", getClass().getSimpleName(), super.toString());
        }
    }

    /* compiled from: FlowableCreate.java */
    /* renamed from: z1.azv$f */
    /* loaded from: classes3.dex */
    static final class C4032f<T> extends AbstractC4027a<T> {
        private static final long serialVersionUID = 3776720187248809713L;

        C4032f(Subscriber<? super T> dbxVar) {
            super(dbxVar);
        }

        @Override // p110z1.Emitter
        public void onNext(T t) {
            long j;
            if (!isCancelled()) {
                if (t != null) {
                    this.downstream.onNext(t);
                    do {
                        j = get();
                        if (j == 0) {
                            return;
                        }
                    } while (!compareAndSet(j, j - 1));
                    return;
                }
                onError(new NullPointerException("onNext called with null. Null values are generally not allowed in 2.x operators and sources."));
            }
        }
    }

    /* compiled from: FlowableCreate.java */
    /* renamed from: z1.azv$g */
    /* loaded from: classes3.dex */
    static abstract class AbstractC4033g<T> extends AbstractC4027a<T> {
        private static final long serialVersionUID = 4127754106204442833L;

        abstract void onOverflow();

        AbstractC4033g(Subscriber<? super T> dbxVar) {
            super(dbxVar);
        }

        @Override // p110z1.Emitter
        public final void onNext(T t) {
            if (!isCancelled()) {
                if (t == null) {
                    onError(new NullPointerException("onNext called with null. Null values are generally not allowed in 2.x operators and sources."));
                } else if (get() != 0) {
                    this.downstream.onNext(t);
                    BackpressureHelper.m9446c(this, 1L);
                } else {
                    onOverflow();
                }
            }
        }
    }

    /* compiled from: FlowableCreate.java */
    /* renamed from: z1.azv$c */
    /* loaded from: classes3.dex */
    static final class C4029c<T> extends AbstractC4033g<T> {
        private static final long serialVersionUID = 8360058422307496563L;

        @Override // p110z1.FlowableCreate.AbstractC4033g
        void onOverflow() {
        }

        C4029c(Subscriber<? super T> dbxVar) {
            super(dbxVar);
        }
    }

    /* compiled from: FlowableCreate.java */
    /* renamed from: z1.azv$d */
    /* loaded from: classes3.dex */
    static final class C4030d<T> extends AbstractC4033g<T> {
        private static final long serialVersionUID = 338953216916120960L;

        C4030d(Subscriber<? super T> dbxVar) {
            super(dbxVar);
        }

        @Override // p110z1.FlowableCreate.AbstractC4033g
        void onOverflow() {
            onError(new MissingBackpressureException("create: could not emit value due to lack of requests"));
        }
    }

    /* compiled from: FlowableCreate.java */
    /* renamed from: z1.azv$b */
    /* loaded from: classes3.dex */
    static final class C4028b<T> extends AbstractC4027a<T> {
        private static final long serialVersionUID = 2427151001689639875L;
        volatile boolean done;
        Throwable error;
        final SpscLinkedArrayQueue<T> queue;
        final AtomicInteger wip = new AtomicInteger();

        C4028b(Subscriber<? super T> dbxVar, int i) {
            super(dbxVar);
            this.queue = new SpscLinkedArrayQueue<>(i);
        }

        @Override // p110z1.Emitter
        public void onNext(T t) {
            if (!this.done && !isCancelled()) {
                if (t == null) {
                    onError(new NullPointerException("onNext called with null. Null values are generally not allowed in 2.x operators and sources."));
                    return;
                }
                this.queue.offer(t);
                drain();
            }
        }

        @Override // p110z1.FlowableCreate.AbstractC4027a, p110z1.FlowableEmitter
        public boolean tryOnError(Throwable th) {
            if (this.done || isCancelled()) {
                return false;
            }
            if (th == null) {
                th = new NullPointerException("onError called with null. Null values are generally not allowed in 2.x operators and sources.");
            }
            this.error = th;
            this.done = true;
            drain();
            return true;
        }

        @Override // p110z1.FlowableCreate.AbstractC4027a, p110z1.Emitter
        public void onComplete() {
            this.done = true;
            drain();
        }

        @Override // p110z1.FlowableCreate.AbstractC4027a
        void onRequested() {
            drain();
        }

        @Override // p110z1.FlowableCreate.AbstractC4027a
        void onUnsubscribed() {
            if (this.wip.getAndIncrement() == 0) {
                this.queue.clear();
            }
        }

        void drain() {
            int i;
            if (this.wip.getAndIncrement() == 0) {
                Subscriber<? super T> dbxVar = this.downstream;
                SpscLinkedArrayQueue<T> bqlVar = this.queue;
                int i2 = 1;
                do {
                    long j = get();
                    long j2 = 0;
                    while (true) {
                        i = (j2 > j ? 1 : (j2 == j ? 0 : -1));
                        if (i == 0) {
                            break;
                        } else if (isCancelled()) {
                            bqlVar.clear();
                            return;
                        } else {
                            boolean z = this.done;
                            Object obj = (T) bqlVar.poll();
                            boolean z2 = obj == null;
                            if (z && z2) {
                                Throwable th = this.error;
                                if (th != null) {
                                    error(th);
                                    return;
                                } else {
                                    complete();
                                    return;
                                }
                            } else if (z2) {
                                break;
                            } else {
                                dbxVar.onNext(obj);
                                j2++;
                            }
                        }
                    }
                    if (i == 0) {
                        if (isCancelled()) {
                            bqlVar.clear();
                            return;
                        }
                        boolean z3 = this.done;
                        boolean isEmpty = bqlVar.isEmpty();
                        if (z3 && isEmpty) {
                            Throwable th2 = this.error;
                            if (th2 != null) {
                                error(th2);
                                return;
                            } else {
                                complete();
                                return;
                            }
                        }
                    }
                    if (j2 != 0) {
                        BackpressureHelper.m9446c(this, j2);
                    }
                    i2 = this.wip.addAndGet(-i2);
                } while (i2 != 0);
            }
        }
    }

    /* compiled from: FlowableCreate.java */
    /* renamed from: z1.azv$e */
    /* loaded from: classes3.dex */
    static final class C4031e<T> extends AbstractC4027a<T> {
        private static final long serialVersionUID = 4023437720691792495L;
        volatile boolean done;
        Throwable error;
        final AtomicReference<T> queue = new AtomicReference<>();
        final AtomicInteger wip = new AtomicInteger();

        C4031e(Subscriber<? super T> dbxVar) {
            super(dbxVar);
        }

        @Override // p110z1.Emitter
        public void onNext(T t) {
            if (!this.done && !isCancelled()) {
                if (t == null) {
                    onError(new NullPointerException("onNext called with null. Null values are generally not allowed in 2.x operators and sources."));
                    return;
                }
                this.queue.set(t);
                drain();
            }
        }

        @Override // p110z1.FlowableCreate.AbstractC4027a, p110z1.FlowableEmitter
        public boolean tryOnError(Throwable th) {
            if (this.done || isCancelled()) {
                return false;
            }
            if (th == null) {
                onError(new NullPointerException("onError called with null. Null values are generally not allowed in 2.x operators and sources."));
            }
            this.error = th;
            this.done = true;
            drain();
            return true;
        }

        @Override // p110z1.FlowableCreate.AbstractC4027a, p110z1.Emitter
        public void onComplete() {
            this.done = true;
            drain();
        }

        @Override // p110z1.FlowableCreate.AbstractC4027a
        void onRequested() {
            drain();
        }

        @Override // p110z1.FlowableCreate.AbstractC4027a
        void onUnsubscribed() {
            if (this.wip.getAndIncrement() == 0) {
                this.queue.lazySet(null);
            }
        }

        void drain() {
            boolean z;
            int i;
            if (this.wip.getAndIncrement() == 0) {
                Subscriber<? super T> dbxVar = this.downstream;
                AtomicReference<T> atomicReference = this.queue;
                int i2 = 1;
                do {
                    long j = get();
                    long j2 = 0;
                    while (true) {
                        z = false;
                        i = (j2 > j ? 1 : (j2 == j ? 0 : -1));
                        if (i == 0) {
                            break;
                        } else if (isCancelled()) {
                            atomicReference.lazySet(null);
                            return;
                        } else {
                            boolean z2 = this.done;
                            Object obj = (T) atomicReference.getAndSet(null);
                            boolean z3 = obj == null;
                            if (z2 && z3) {
                                Throwable th = this.error;
                                if (th != null) {
                                    error(th);
                                    return;
                                } else {
                                    complete();
                                    return;
                                }
                            } else if (z3) {
                                break;
                            } else {
                                dbxVar.onNext(obj);
                                j2++;
                            }
                        }
                    }
                    if (i == 0) {
                        if (isCancelled()) {
                            atomicReference.lazySet(null);
                            return;
                        }
                        boolean z4 = this.done;
                        if (atomicReference.get() == null) {
                            z = true;
                        }
                        if (z4 && z) {
                            Throwable th2 = this.error;
                            if (th2 != null) {
                                error(th2);
                                return;
                            } else {
                                complete();
                                return;
                            }
                        }
                    }
                    if (j2 != 0) {
                        BackpressureHelper.m9446c(this, j2);
                    }
                    i2 = this.wip.addAndGet(-i2);
                } while (i2 != 0);
            }
        }
    }
}
