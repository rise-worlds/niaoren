package p110z1;

/* renamed from: z1.bhf */
/* loaded from: classes3.dex */
public enum MaybeToPublisher implements Function<MaybeSource<Object>, Publisher<Object>> {
    INSTANCE;

    public static <T> Function<MaybeSource<T>, Publisher<T>> instance() {
        return INSTANCE;
    }

    public Publisher<Object> apply(MaybeSource<Object> asiVar) throws Exception {
        return new MaybeToFlowable(asiVar);
    }
}
