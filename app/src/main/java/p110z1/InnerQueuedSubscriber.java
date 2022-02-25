package p110z1;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: z1.brp */
/* loaded from: classes3.dex */
public final class InnerQueuedSubscriber<T> extends AtomicReference<dby> implements FlowableSubscriber<T>, dby {
    private static final long serialVersionUID = 22876611072430776L;
    volatile boolean done;
    int fusionMode;
    final int limit;
    final InnerQueuedSubscriberSupport<T> parent;
    final int prefetch;
    long produced;
    volatile SimpleQueue<T> queue;

    public InnerQueuedSubscriber(InnerQueuedSubscriberSupport<T> brqVar, int i) {
        this.parent = brqVar;
        this.prefetch = i;
        this.limit = i - (i >> 2);
    }

    @Override // p110z1.FlowableSubscriber, p110z1.Subscriber
    public void onSubscribe(dby dbyVar) {
        if (SubscriptionHelper.setOnce(this, dbyVar)) {
            if (dbyVar instanceof QueueSubscription) {
                QueueSubscription avtVar = (QueueSubscription) dbyVar;
                int requestFusion = avtVar.requestFusion(3);
                if (requestFusion == 1) {
                    this.fusionMode = requestFusion;
                    this.queue = avtVar;
                    this.done = true;
                    this.parent.innerComplete(this);
                    return;
                } else if (requestFusion == 2) {
                    this.fusionMode = requestFusion;
                    this.queue = avtVar;
                    QueueDrainHelper.m9371a(dbyVar, this.prefetch);
                    return;
                }
            }
            this.queue = QueueDrainHelper.m9377a(this.prefetch);
            QueueDrainHelper.m9371a(dbyVar, this.prefetch);
        }
    }

    @Override // p110z1.Subscriber
    public void onNext(T t) {
        if (this.fusionMode == 0) {
            this.parent.innerNext(this, t);
        } else {
            this.parent.drain();
        }
    }

    @Override // p110z1.Subscriber
    public void onError(Throwable th) {
        this.parent.innerError(this, th);
    }

    @Override // p110z1.Subscriber
    public void onComplete() {
        this.parent.innerComplete(this);
    }

    @Override // p110z1.dby
    public void request(long j) {
        if (this.fusionMode != 1) {
            long j2 = this.produced + j;
            if (j2 >= this.limit) {
                this.produced = 0L;
                get().request(j2);
                return;
            }
            this.produced = j2;
        }
    }

    public void requestOne() {
        if (this.fusionMode != 1) {
            long j = this.produced + 1;
            if (j == this.limit) {
                this.produced = 0L;
                get().request(j);
                return;
            }
            this.produced = j;
        }
    }

    @Override // p110z1.dby
    public void cancel() {
        SubscriptionHelper.cancel(this);
    }

    public boolean isDone() {
        return this.done;
    }

    public void setDone() {
        this.done = true;
    }

    public SimpleQueue<T> queue() {
        return this.queue;
    }
}
