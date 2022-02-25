package p110z1;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* renamed from: z1.ayr */
/* loaded from: classes3.dex */
public final class BlockingFlowableIterable<T> implements Iterable<T> {

    /* renamed from: a */
    final Flowable<T> f17813a;

    /* renamed from: b */
    final int f17814b;

    public BlockingFlowableIterable(Flowable<T> arvVar, int i) {
        this.f17813a = arvVar;
        this.f17814b = i;
    }

    @Override // java.lang.Iterable
    public Iterator<T> iterator() {
        RunnableC3978a aVar = new RunnableC3978a(this.f17814b);
        this.f17813a.m11187a((FlowableSubscriber) aVar);
        return aVar;
    }

    /* compiled from: BlockingFlowableIterable.java */
    /* renamed from: z1.ayr$a */
    /* loaded from: classes3.dex */
    static final class RunnableC3978a<T> extends AtomicReference<dby> implements Runnable, Iterator<T>, FlowableSubscriber<T>, Disposable {
        private static final long serialVersionUID = 6695226475494099826L;
        final long batchSize;
        volatile boolean done;
        Throwable error;
        final long limit;
        long produced;
        final SpscArrayQueue<T> queue;
        final Lock lock = new ReentrantLock();
        final Condition condition = this.lock.newCondition();

        RunnableC3978a(int i) {
            this.queue = new SpscArrayQueue<>(i);
            this.batchSize = i;
            this.limit = i - (i >> 2);
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
                BlockingHelper.m9444a();
                this.lock.lock();
                while (!this.done && this.queue.isEmpty()) {
                    try {
                        try {
                            this.condition.await();
                        } catch (InterruptedException e) {
                            run();
                            throw ExceptionHelper.m9432a(e);
                        }
                    } finally {
                        this.lock.unlock();
                    }
                }
            }
        }

        @Override // java.util.Iterator
        public T next() {
            if (hasNext()) {
                T poll = this.queue.poll();
                long j = this.produced + 1;
                if (j == this.limit) {
                    this.produced = 0L;
                    get().request(j);
                } else {
                    this.produced = j;
                }
                return poll;
            }
            throw new NoSuchElementException();
        }

        @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
        public void onSubscribe(dby dbyVar) {
            SubscriptionHelper.setOnce(this, dbyVar, this.batchSize);
        }

        @Override // p110z1.Subscriber
        public void onNext(T t) {
            if (!this.queue.offer(t)) {
                SubscriptionHelper.cancel(this);
                onError(new MissingBackpressureException("Queue full?!"));
                return;
            }
            signalConsumer();
        }

        @Override // p110z1.Subscriber
        public void onError(Throwable th) {
            this.error = th;
            this.done = true;
            signalConsumer();
        }

        @Override // p110z1.Subscriber
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

        @Override // java.lang.Runnable
        public void run() {
            SubscriptionHelper.cancel(this);
            signalConsumer();
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("remove");
        }

        @Override // p110z1.Disposable
        public void dispose() {
            SubscriptionHelper.cancel(this);
        }

        @Override // p110z1.Disposable
        public boolean isDisposed() {
            return get() == SubscriptionHelper.CANCELLED;
        }
    }
}
