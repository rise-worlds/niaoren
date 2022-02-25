package p110z1;

/* renamed from: z1.avi */
/* loaded from: classes3.dex */
public interface ConditionalSubscriber<T> extends FlowableSubscriber<T> {
    boolean tryOnNext(T t);
}
