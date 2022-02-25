package p110z1;

import java.util.Queue;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.brk */
/* loaded from: classes3.dex */
public final class BlockingSubscriber<T> extends AtomicReference<dby> implements FlowableSubscriber<T>, dby {
    public static final Object TERMINATED = new Object();
    private static final long serialVersionUID = -4875965440900746268L;
    final Queue<Object> queue;

    public BlockingSubscriber(Queue<Object> queue) {
        this.queue = queue;
    }

    @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
    public void onSubscribe(dby dbyVar) {
        if (SubscriptionHelper.setOnce(this, dbyVar)) {
            this.queue.offer(NotificationLite.subscription(this));
        }
    }

    @Override // p110z1.Subscriber
    public void onNext(T t) {
        this.queue.offer(NotificationLite.next(t));
    }

    @Override // p110z1.Subscriber
    public void onError(Throwable th) {
        this.queue.offer(NotificationLite.error(th));
    }

    @Override // p110z1.Subscriber
    public void onComplete() {
        this.queue.offer(NotificationLite.complete());
    }

    @Override // p110z1.dby
    public void request(long j) {
        get().request(j);
    }

    @Override // p110z1.dby
    public void cancel() {
        if (SubscriptionHelper.cancel(this)) {
            this.queue.offer(TERMINATED);
        }
    }

    public boolean isCancelled() {
        return get() == SubscriptionHelper.CANCELLED;
    }
}
