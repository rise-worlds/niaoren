package p110z1;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: z1.ayu */
/* loaded from: classes3.dex */
public final class BlockingFlowableNext<T> implements Iterable<T> {

    /* renamed from: a */
    final Publisher<? extends T> f17824a;

    public BlockingFlowableNext(Publisher<? extends T> dbwVar) {
        this.f17824a = dbwVar;
    }

    @Override // java.lang.Iterable
    public Iterator<T> iterator() {
        return new C3982a(this.f17824a, new C3983b());
    }

    /* compiled from: BlockingFlowableNext.java */
    /* renamed from: z1.ayu$a */
    /* loaded from: classes3.dex */
    static final class C3982a<T> implements Iterator<T> {

        /* renamed from: a */
        private final C3983b<T> f17825a;

        /* renamed from: b */
        private final Publisher<? extends T> f17826b;

        /* renamed from: c */
        private T f17827c;

        /* renamed from: d */
        private boolean f17828d = true;

        /* renamed from: e */
        private boolean f17829e = true;

        /* renamed from: f */
        private Throwable f17830f;

        /* renamed from: g */
        private boolean f17831g;

        C3982a(Publisher<? extends T> dbwVar, C3983b<T> bVar) {
            this.f17826b = dbwVar;
            this.f17825a = bVar;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            Throwable th = this.f17830f;
            if (th != null) {
                throw ExceptionHelper.m9432a(th);
            } else if (!this.f17828d) {
                return false;
            } else {
                return !this.f17829e || m9826a();
            }
        }

        /* renamed from: a */
        private boolean m9826a() {
            try {
                if (!this.f17831g) {
                    this.f17831g = true;
                    this.f17825a.m9823b();
                    Flowable.m10964d((Publisher) this.f17826b).m10813x().m11187a((FlowableSubscriber<? super Notification<T>>) this.f17825a);
                }
                Notification<T> a = this.f17825a.m9825a();
                if (a.m10640c()) {
                    this.f17829e = false;
                    this.f17827c = a.m10639d();
                    return true;
                }
                this.f17828d = false;
                if (a.m10644a()) {
                    return false;
                }
                if (a.m10641b()) {
                    this.f17830f = a.m10638e();
                    throw ExceptionHelper.m9432a(this.f17830f);
                }
                throw new IllegalStateException("Should not reach here");
            } catch (InterruptedException e) {
                this.f17825a.dispose();
                this.f17830f = e;
                throw ExceptionHelper.m9432a(e);
            }
        }

        @Override // java.util.Iterator
        public T next() {
            Throwable th = this.f17830f;
            if (th != null) {
                throw ExceptionHelper.m9432a(th);
            } else if (hasNext()) {
                this.f17829e = true;
                return this.f17827c;
            } else {
                throw new NoSuchElementException("No more elements");
            }
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Read only iterator");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: BlockingFlowableNext.java */
    /* renamed from: z1.ayu$b */
    /* loaded from: classes3.dex */
    public static final class C3983b<T> extends DisposableSubscriber<Notification<T>> {

        /* renamed from: b */
        private final BlockingQueue<Notification<T>> f17833b = new ArrayBlockingQueue(1);

        /* renamed from: a */
        final AtomicInteger f17832a = new AtomicInteger();

        @Override // p110z1.Subscriber
        public void onComplete() {
        }

        C3983b() {
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            RxJavaPlugins.m9212a(th);
        }

        /* renamed from: a */
        public void onNext(Notification<T> askVar) {
            if (this.f17832a.getAndSet(0) == 1 || !askVar.m10640c()) {
                while (!this.f17833b.offer(askVar)) {
                    Notification<T> poll = this.f17833b.poll();
                    if (poll != null && !poll.m10640c()) {
                        askVar = poll;
                    }
                }
            }
        }

        /* renamed from: a */
        public Notification<T> m9825a() throws InterruptedException {
            m9823b();
            BlockingHelper.m9444a();
            return this.f17833b.take();
        }

        /* renamed from: b */
        void m9823b() {
            this.f17832a.set(1);
        }
    }
}
