package p110z1;

import com.googlecode.tesseract.android.TessBaseAPI;

@Metadata(m8864a = 2, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\u001a\"\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001H\u0001\u001a\u0014\u0010\u0004\u001a\u00020\u00052\n\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u0001H\u0001\u001a\u0014\u0010\u0007\u001a\u00020\u00052\n\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u0001H\u0001¨\u0006\b"}, m8860e = {"probeCoroutineCreated", "Lkotlin/coroutines/Continuation;", TessBaseAPI.f9204e, "completion", "probeCoroutineResumed", "", "frame", "probeCoroutineSuspended", "kotlin-stdlib"})
/* renamed from: z1.cej */
/* loaded from: classes3.dex */
public final class DebugProbes {
    /* JADX WARN: Multi-variable type inference failed */
    @bwy(m8750a = "1.3")
    @NotNull
    /* renamed from: a */
    public static final <T> Continuation<T> m5505a(@NotNull Continuation<? super T> ccpVar) {
        cji.m5162f(ccpVar, "completion");
        return ccpVar;
    }

    @bwy(m8750a = "1.3")
    /* renamed from: b */
    public static final void m5504b(@NotNull Continuation<?> ccpVar) {
        cji.m5162f(ccpVar, "frame");
    }

    @bwy(m8750a = "1.3")
    /* renamed from: c */
    public static final void m5503c(@NotNull Continuation<?> ccpVar) {
        cji.m5162f(ccpVar, "frame");
    }
}
