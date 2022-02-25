package p110z1;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReferenceArray;

/* renamed from: z1.bqk */
/* loaded from: classes3.dex */
public final class SpscArrayQueue<E> extends AtomicReferenceArray<E> implements SimplePlainQueue<E> {
    private static final Integer MAX_LOOK_AHEAD_STEP = Integer.getInteger("jctools.spsc.max.lookahead.step", 4096);
    private static final long serialVersionUID = -1296597691183856449L;
    final int lookAheadStep;
    long producerLookAhead;
    final int mask = length() - 1;
    final AtomicLong producerIndex = new AtomicLong();
    final AtomicLong consumerIndex = new AtomicLong();

    int calcElementOffset(long j, int i) {
        return ((int) j) & i;
    }

    public SpscArrayQueue(int i) {
        super(Pow2.m9387a(i));
        this.lookAheadStep = Math.min(i / 4, MAX_LOOK_AHEAD_STEP.intValue());
    }

    @Override // p110z1.SimpleQueue
    public boolean offer(E e) {
        if (e != null) {
            int i = this.mask;
            long j = this.producerIndex.get();
            int calcElementOffset = calcElementOffset(j, i);
            if (j >= this.producerLookAhead) {
                long j2 = this.lookAheadStep + j;
                if (lvElement(calcElementOffset(j2, i)) == null) {
                    this.producerLookAhead = j2;
                } else if (lvElement(calcElementOffset) != null) {
                    return false;
                }
            }
            soElement(calcElementOffset, e);
            soProducerIndex(j + 1);
            return true;
        }
        throw new NullPointerException("Null is not a valid element");
    }

    @Override // p110z1.SimpleQueue
    public boolean offer(E e, E e2) {
        return offer(e) && offer(e2);
    }

    @Override // p110z1.SimplePlainQueue, p110z1.SimpleQueue
    @atn
    public E poll() {
        long j = this.consumerIndex.get();
        int calcElementOffset = calcElementOffset(j);
        E lvElement = lvElement(calcElementOffset);
        if (lvElement == null) {
            return null;
        }
        soConsumerIndex(j + 1);
        soElement(calcElementOffset, null);
        return lvElement;
    }

    @Override // p110z1.SimpleQueue
    public boolean isEmpty() {
        return this.producerIndex.get() == this.consumerIndex.get();
    }

    void soProducerIndex(long j) {
        this.producerIndex.lazySet(j);
    }

    void soConsumerIndex(long j) {
        this.consumerIndex.lazySet(j);
    }

    @Override // p110z1.SimpleQueue
    public void clear() {
        while (true) {
            if (poll() == null && isEmpty()) {
                return;
            }
        }
    }

    int calcElementOffset(long j) {
        return ((int) j) & this.mask;
    }

    void soElement(int i, E e) {
        lazySet(i, e);
    }

    E lvElement(int i) {
        return get(i);
    }
}
