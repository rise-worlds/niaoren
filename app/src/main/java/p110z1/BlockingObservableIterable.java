package p110z1;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* renamed from: z1.big */
/* loaded from: classes3.dex */
public final class BlockingObservableIterable<T> implements Iterable<T> {

    /* renamed from: a */
    final ObservableSource<? extends T> f18808a;

    /* renamed from: b */
    final int f18809b;

    public BlockingObservableIterable(ObservableSource<? extends T> asqVar, int i) {
        this.f18808a = asqVar;
        this.f18809b = i;
    }

    @Override // java.lang.Iterable
    public Iterator<T> iterator() {
        C4377a aVar = new C4377a(this.f18809b);
        this.f18808a.subscribe(aVar);
        return aVar;
    }

    /* compiled from: BlockingObservableIterable.java */
    /* renamed from: z1.big$a */
    /* loaded from: classes3.dex */
    static final class C4377a<T> extends AtomicReference<Disposable> implements Iterator<T>, Observer<T>, Disposable {
        private static final long serialVersionUID = 6695226475494099826L;
        volatile boolean done;
        Throwable error;
        final SpscLinkedArrayQueue<T> queue;
        final Lock lock = new ReentrantLock();
        final Condition condition = this.lock.newCondition();

        C4377a(int i) {
            this.queue = new SpscLinkedArrayQueue<>(i);
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            while (true) {
                boolean z = this.done;
                boolean isEmpty = this.queue.isEmpty();
                if (z) {
                    Throwable th = this.error;
                    if (th != null) {
                        throw ExceptionHelper.m9432a(th);
                    } else if (isEmpty) {
                        return false;
                    }
                }
                if (!isEmpty) {
                    return true;
                }
                try {
                    BlockingHelper.m9444a();
                    this.lock.lock();
                    while (!this.done && this.queue.isEmpty()) {
                        this.condition.await();
                    }
                    this.lock.unlock();
                } catch (InterruptedException e) {
                    DisposableHelper.dispose(this);
                    signalConsumer();
                    throw ExceptionHelper.m9432a(e);
                }
            }
        }

        @Override // java.util.Iterator
        public T next() {
            if (hasNext()) {
                return this.queue.poll();
            }
            throw new NoSuchElementException();
        }

        @Override // p110z1.Observer
        public void onSubscribe(Disposable atrVar) {
            DisposableHelper.setOnce(this, atrVar);
        }

        @Override // p110z1.Observer
        public void onNext(T t) {
            this.queue.offer(t);
            signalConsumer();
        }

        @Override // p110z1.Observer
        public void onError(Throwable th) {
            this.error = th;
            this.done = true;
            signalConsumer();
        }

        @Override // p110z1.Observer
        public void onComplete() {
            this.done = true;
            signalConsumer();
        }

        void signalConsumer() {
            this.lock.lock();
            try {
                this.condition.signalAll();
            } finally {
                this.lock.unlock();
            }
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("remove");
        }

        @Override // p110z1.Disposable
        public void dispose() {
            DisposableHelper.dispose(this);
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return DisposableHelper.isDisposed(get());
        }
    }
}
