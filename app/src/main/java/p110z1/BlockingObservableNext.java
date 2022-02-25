package p110z1;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: z1.bij */
/* loaded from: classes3.dex */
public final class BlockingObservableNext<T> implements Iterable<T> {

    /* renamed from: a */
    final ObservableSource<T> f18819a;

    public BlockingObservableNext(ObservableSource<T> asqVar) {
        this.f18819a = asqVar;
    }

    @Override // java.lang.Iterable
    public Iterator<T> iterator() {
        return new C4381a(this.f18819a, new C4382b());
    }

    /* compiled from: BlockingObservableNext.java */
    /* renamed from: z1.bij$a */
    /* loaded from: classes3.dex */
    static final class C4381a<T> implements Iterator<T> {

        /* renamed from: a */
        private final C4382b<T> f18820a;

        /* renamed from: b */
        private final ObservableSource<T> f18821b;

        /* renamed from: c */
        private T f18822c;

        /* renamed from: d */
        private boolean f18823d = true;

        /* renamed from: e */
        private boolean f18824e = true;

        /* renamed from: f */
        private Throwable f18825f;

        /* renamed from: g */
        private boolean f18826g;

        C4381a(ObservableSource<T> asqVar, C4382b<T> bVar) {
            this.f18821b = asqVar;
            this.f18820a = bVar;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            Throwable th = this.f18825f;
            if (th != null) {
                throw ExceptionHelper.m9432a(th);
            } else if (!this.f18823d) {
                return false;
            } else {
                return !this.f18824e || m9667a();
            }
        }

        /* renamed from: a */
        private boolean m9667a() {
            if (!this.f18826g) {
                this.f18826g = true;
                this.f18820a.m9664b();
                new ObservableMaterialize(this.f18821b).subscribe(this.f18820a);
            }
            try {
                Notification<T> a = this.f18820a.m9666a();
                if (a.m10640c()) {
                    this.f18824e = false;
                    this.f18822c = a.m10639d();
                    return true;
                }
                this.f18823d = false;
                if (a.m10644a()) {
                    return false;
                }
                this.f18825f = a.m10638e();
                throw ExceptionHelper.m9432a(this.f18825f);
            } catch (InterruptedException e) {
                this.f18820a.dispose();
                this.f18825f = e;
                throw ExceptionHelper.m9432a(e);
            }
        }

        @Override // java.util.Iterator
        public T next() {
            Throwable th = this.f18825f;
            if (th != null) {
                throw ExceptionHelper.m9432a(th);
            } else if (hasNext()) {
                this.f18824e = true;
                return this.f18822c;
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
    /* compiled from: BlockingObservableNext.java */
    /* renamed from: z1.bij$b */
    /* loaded from: classes3.dex */
    public static final class C4382b<T> extends DisposableObserver<Notification<T>> {

        /* renamed from: b */
        private final BlockingQueue<Notification<T>> f18828b = new ArrayBlockingQueue(1);

        /* renamed from: a */
        final AtomicInteger f18827a = new AtomicInteger();

        @Override // p110z1.Observer
        public void onComplete() {
        }

        C4382b() {
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            RxJavaPlugins.m9212a(th);
        }

        /* renamed from: a */
        public void onNext(Notification<T> askVar) {
            if (this.f18827a.getAndSet(0) == 1 || !askVar.m10640c()) {
                while (!this.f18828b.offer(askVar)) {
                    Notification<T> poll = this.f18828b.poll();
                    if (poll != null && !poll.m10640c()) {
                        askVar = poll;
                    }
                }
            }
        }

        /* renamed from: a */
        public Notification<T> m9666a() throws InterruptedException {
            m9664b();
            BlockingHelper.m9444a();
            return this.f18828b.take();
        }

        /* renamed from: b */
        void m9664b() {
            this.f18827a.set(1);
        }
    }
}
