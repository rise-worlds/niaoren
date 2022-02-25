package p110z1;

import com.googlecode.tesseract.android.TessBaseAPI;

@Metadata(m8864a = 2, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a*\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001H\u0000\u001a \u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001¨\u0006\u0007"}, m8860e = {"interceptContinuationIfNeeded", "Lkotlin/coroutines/experimental/Continuation;", TessBaseAPI.f9204e, "context", "Lkotlin/coroutines/experimental/CoroutineContext;", "continuation", "normalizeContinuation", "kotlin-stdlib-coroutines"})
@cgo(m5270a = "CoroutineIntrinsics")
/* renamed from: z1.cdn */
/* loaded from: classes3.dex */
public final class CoroutineIntrinsics {
    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    /* renamed from: a */
    public static final <T> Coroutines<T> m5570a(@NotNull Coroutines<? super T> ccyVar) {
        Coroutines<T> ccyVar2;
        cji.m5162f(ccyVar, "continuation");
        CoroutineImpl cdmVar = !(ccyVar instanceof CoroutineImpl) ? null : ccyVar;
        return (cdmVar == null || (ccyVar2 = (Coroutines<T>) cdmVar.getFacade()) == null) ? ccyVar : ccyVar2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    /* renamed from: a */
    public static final <T> Coroutines<T> m5569a(@NotNull cda cdaVar, @NotNull Coroutines<? super T> ccyVar) {
        Coroutines<T> a;
        cji.m5162f(cdaVar, "context");
        cji.m5162f(ccyVar, "continuation");
        ccz cczVar = (ccz) cdaVar.mo5549a(ccz.f20561a);
        return (cczVar == null || (a = cczVar.mo5550a(ccyVar)) == null) ? ccyVar : a;
    }
}
