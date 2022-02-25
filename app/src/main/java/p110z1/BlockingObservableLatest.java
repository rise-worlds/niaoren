package p110z1;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.bih */
/* loaded from: classes3.dex */
public final class BlockingObservableLatest<T> implements Iterable<T> {

    /* renamed from: a */
    final ObservableSource<T> f18810a;

    public BlockingObservableLatest(ObservableSource<T> asqVar) {
        this.f18810a = asqVar;
    }

    @Override // java.lang.Iterable
    public Iterator<T> iterator() {
        C4378a aVar = new C4378a();
        Observable.m10239i((ObservableSource) this.f18810a).m10636A().subscribe(aVar);
        return aVar;
    }

    /* compiled from: BlockingObservableLatest.java */
    /* renamed from: z1.bih$a */
    /* loaded from: classes3.dex */
    static final class C4378a<T> extends DisposableObserver<Notification<T>> implements Iterator<T> {

        /* renamed from: a */
        Notification<T> f18811a;

        /* renamed from: b */
        final Semaphore f18812b = new Semaphore(0);

        /* renamed from: c */
        final AtomicReference<Notification<T>> f18813c = new AtomicReference<>();

        @Override // p110z1.Observer
        public void onComplete() {
        }

        C4378a() {
        }

        /* renamed from: a */
        public void onNext(Notification<T> askVar) {
            if (this.f18813c.getAndSet(askVar) == null) {
                this.f18812b.release();
            }
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            RxJavaPlugins.m9212a(th);
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            Notification<T> askVar = this.f18811a;
            if (askVar == null || !askVar.m10641b()) {
                if (this.f18811a == null) {
                    try {
                        BlockingHelper.m9444a();
                        this.f18812b.acquire();
                        Notification<T> andSet = this.f18813c.getAndSet(null);
                        this.f18811a = andSet;
                        if (andSet.m10641b()) {
                            throw ExceptionHelper.m9432a(andSet.m10638e());
                        }
                    } catch (InterruptedException e) {
                        dispose();
                        this.f18811a = Notification.m10642a((Throwable) e);
                        throw ExceptionHelper.m9432a(e);
                    }
                }
                return this.f18811a.m10640c();
            }
            throw ExceptionHelper.m9432a(this.f18811a.m10638e());
        }

        @Override // java.util.Iterator
        public T next() {
            if (hasNext()) {
                T d = this.f18811a.m10639d();
                this.f18811a = null;
                return d;
            }
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Read-only iterator.");
        }
    }
}
