package p110z1;

import com.googlecode.tesseract.android.TessBaseAPI;
import p110z1.CoroutineContext;

@bwy(m8750a = "1.3")
@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\bg\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fJ(\u0010\u0002\u001a\u0004\u0018\u0001H\u0003\"\b\b\u0000\u0010\u0003*\u00020\u00012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00030\u0005H\u0096\u0002¢\u0006\u0002\u0010\u0006J\"\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\t0\b\"\u0004\b\u0000\u0010\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\t0\bH&J\u0014\u0010\u000b\u001a\u00020\f2\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005H\u0016J\u0014\u0010\r\u001a\u00020\u000e2\n\u0010\n\u001a\u0006\u0012\u0002\b\u00030\bH\u0016¨\u0006\u0010"}, m8860e = {"Lkotlin/coroutines/ContinuationInterceptor;", "Lkotlin/coroutines/CoroutineContext$Element;", "get", "E", "key", "Lkotlin/coroutines/CoroutineContext$Key;", "(Lkotlin/coroutines/CoroutineContext$Key;)Lkotlin/coroutines/CoroutineContext$Element;", "interceptContinuation", "Lkotlin/coroutines/Continuation;", TessBaseAPI.f9204e, "continuation", "minusKey", "Lkotlin/coroutines/CoroutineContext;", "releaseInterceptedContinuation", "", "Key", "kotlin-stdlib"})
/* renamed from: z1.ccq */
/* loaded from: classes3.dex */
public interface ContinuationInterceptor extends CoroutineContext.AbstractC4914b {

    /* renamed from: a */
    public static final C4910b f20550a = C4910b.f20551a;

    @NotNull
    /* renamed from: a */
    <T> Continuation<T> mo5566a(@NotNull Continuation<? super T> ccpVar);

    /* renamed from: b */
    void mo5564b(@NotNull Continuation<?> ccpVar);

    @Override // p110z1.CoroutineContext.AbstractC4914b, p110z1.CoroutineContext
    @dbs
    <E extends CoroutineContext.AbstractC4914b> E get(@NotNull CoroutineContext.AbstractC4916c<E> cVar);

    @Override // p110z1.CoroutineContext.AbstractC4914b, p110z1.CoroutineContext
    @NotNull
    CoroutineContext minusKey(@NotNull CoroutineContext.AbstractC4916c<?> cVar);

    /* compiled from: ContinuationInterceptor.kt */
    @Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003¨\u0006\u0004"}, m8860e = {"Lkotlin/coroutines/ContinuationInterceptor$Key;", "Lkotlin/coroutines/CoroutineContext$Key;", "Lkotlin/coroutines/ContinuationInterceptor;", "()V", "kotlin-stdlib"})
    /* renamed from: z1.ccq$b */
    /* loaded from: classes3.dex */
    public static final class C4910b implements CoroutineContext.AbstractC4916c<ContinuationInterceptor> {

        /* renamed from: a */
        static final /* synthetic */ C4910b f20551a = new C4910b();

        private C4910b() {
        }
    }

    /* compiled from: ContinuationInterceptor.kt */
    @Metadata(m8864a = 3, m8863b = {1, 1, 15}, m8862c = {1, 0, 3})
    /* renamed from: z1.ccq$a */
    /* loaded from: classes3.dex */
    public static final class C4909a {
        /* renamed from: a */
        public static <R> R m5652a(ContinuationInterceptor ccqVar, R r, @NotNull cho<? super R, ? super CoroutineContext.AbstractC4914b, ? extends R> choVar) {
            cji.m5162f(choVar, "operation");
            return (R) CoroutineContext.AbstractC4914b.C4915a.m5636a(ccqVar, r, choVar);
        }

        @NotNull
        /* renamed from: a */
        public static CoroutineContext m5649a(ContinuationInterceptor ccqVar, @NotNull CoroutineContext ccsVar) {
            cji.m5162f(ccsVar, "context");
            return CoroutineContext.AbstractC4914b.C4915a.m5634a(ccqVar, ccsVar);
        }

        /* renamed from: a */
        public static void m5651a(ContinuationInterceptor ccqVar, @NotNull Continuation<?> ccpVar) {
            cji.m5162f(ccpVar, "continuation");
        }

        @dbs
        /* renamed from: a */
        public static <E extends CoroutineContext.AbstractC4914b> E m5650a(ContinuationInterceptor ccqVar, @NotNull CoroutineContext.AbstractC4916c<E> cVar) {
            cji.m5162f(cVar, "key");
            if (cVar != ContinuationInterceptor.f20550a) {
                return null;
            }
            if (ccqVar != null) {
                return ccqVar;
            }
            throw new TypeCastException("null cannot be cast to non-null type E");
        }

        @NotNull
        /* renamed from: b */
        public static CoroutineContext m5648b(ContinuationInterceptor ccqVar, @NotNull CoroutineContext.AbstractC4916c<?> cVar) {
            cji.m5162f(cVar, "key");
            CoroutineContext ccsVar = ccqVar;
            if (cVar == ContinuationInterceptor.f20550a) {
                ccsVar = cct.INSTANCE;
            }
            return ccsVar;
        }
    }
}
