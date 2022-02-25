package p110z1;

/* renamed from: z1.buh */
/* loaded from: classes3.dex */
public abstract class FlowableProcessor<T> extends Flowable<T> implements FlowableSubscriber<T>, Processor<T, T> {
    /* renamed from: U */
    public abstract boolean mo9064U();

    /* renamed from: V */
    public abstract boolean mo9063V();

    /* renamed from: W */
    public abstract boolean mo9062W();

    @atn
    /* renamed from: X */
    public abstract Throwable mo9061X();

    @CheckReturnValue
    @AbstractC3889atm
    /* renamed from: ac */
    public final FlowableProcessor<T> m9111ac() {
        return this instanceof SerializedProcessor ? this : new SerializedProcessor(this);
    }
}
