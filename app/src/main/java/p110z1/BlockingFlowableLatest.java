package p110z1;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.ays */
/* loaded from: classes3.dex */
public final class BlockingFlowableLatest<T> implements Iterable<T> {

    /* renamed from: a */
    final Publisher<? extends T> f17815a;

    public BlockingFlowableLatest(Publisher<? extends T> dbwVar) {
        this.f17815a = dbwVar;
    }

    @Override // java.lang.Iterable
    public Iterator<T> iterator() {
        C3979a aVar = new C3979a();
        Flowable.m10964d((Publisher) this.f17815a).m10813x().m11187a((FlowableSubscriber<? super Notification<T>>) aVar);
        return aVar;
    }

    /* compiled from: BlockingFlowableLatest.java */
    /* renamed from: z1.ays$a */
    /* loaded from: classes3.dex */
    static final class C3979a<T> extends DisposableSubscriber<Notification<T>> implements Iterator<T> {

        /* renamed from: a */
        final Semaphore f17816a = new Semaphore(0);

        /* renamed from: b */
        final AtomicReference<Notification<T>> f17817b = new AtomicReference<>();

        /* renamed from: c */
        Notification<T> f17818c;

        @Override // p110z1.Subscriber
        public void onComplete() {
        }

        C3979a() {
        }

        /* renamed from: a */
        public void onNext(Notification<T> askVar) {
            if (this.f17817b.getAndSet(askVar) == null) {
                this.f17816a.release();
            }
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            RxJavaPlugins.m9212a(th);
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            Notification<T> askVar = this.f17818c;
            if (askVar == null || !askVar.m10641b()) {
                Notification<T> askVar2 = this.f17818c;
                if ((askVar2 == null || askVar2.m10640c()) && this.f17818c == null) {
                    try {
                        BlockingHelper.m9444a();
                        this.f17816a.acquire();
                        Notification<T> andSet = this.f17817b.getAndSet(null);
                        this.f17818c = andSet;
                        if (andSet.m10641b()) {
                            throw ExceptionHelper.m9432a(andSet.m10638e());
                        }
                    } catch (InterruptedException e) {
                        dispose();
                        this.f17818c = Notification.m10642a((Throwable) e);
                        throw ExceptionHelper.m9432a(e);
                    }
                }
                return this.f17818c.m10640c();
            }
            throw ExceptionHelper.m9432a(this.f17818c.m10638e());
        }

        @Override // java.util.Iterator
        public T next() {
            if (!hasNext() || !this.f17818c.m10640c()) {
                throw new NoSuchElementException();
            }
            T d = this.f17818c.m10639d();
            this.f17818c = null;
            return d;
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Read-only iterator.");
        }
    }
}
