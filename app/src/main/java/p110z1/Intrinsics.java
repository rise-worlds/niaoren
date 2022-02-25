package p110z1;

import com.googlecode.tesseract.android.TessBaseAPI;

@Metadata(m8864a = 5, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u0014\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u001a5\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u00012\u001c\b\u0004\u0010\u0002\u001a\u0016\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0003H\u0087Hø\u0001\u0000¢\u0006\u0002\u0010\u0006\u001a5\u0010\u0007\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u00012\u001c\b\u0004\u0010\u0002\u001a\u0016\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0003H\u0087Hø\u0001\u0000¢\u0006\u0002\u0010\u0006\u001a\u001f\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0004\"\u0004\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u0004H\u0087\b\u0082\u0002\u0004\n\u0002\b\t¨\u0006\t"}, m8860e = {"suspendCoroutineOrReturn", TessBaseAPI.f9204e, "block", "Lkotlin/Function1;", "Lkotlin/coroutines/experimental/Continuation;", "", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/experimental/Continuation;)Ljava/lang/Object;", "suspendCoroutineUninterceptedOrReturn", "intercepted", "kotlin-stdlib-coroutines"}, m8859f = "kotlin/coroutines/experimental/intrinsics/IntrinsicsKt", m8857h = 1)
/* renamed from: z1.cdl */
/* loaded from: classes3.dex */
class Intrinsics extends IntrinsicsJvm {
    @bwy(m8750a = "1.1")
    @cey
    /* renamed from: b */
    private static final <T> Object m5572b(chd<? super Coroutines<? super T>, ? extends Object> chdVar, Coroutines<? super T> ccyVar) {
        InlineMarker.m5204a(0);
        Object invoke = chdVar.invoke(CoroutineIntrinsics.m5570a(ccyVar));
        InlineMarker.m5204a(1);
        return invoke;
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: c */
    private static final <T> Object m5571c(chd<? super Coroutines<? super T>, ? extends Object> chdVar, Coroutines<? super T> ccyVar) {
        throw new Standard("Implementation of suspendCoroutineUninterceptedOrReturn is intrinsic");
    }

    @bwy(m8750a = "1.2")
    @cey
    /* renamed from: a */
    private static final <T> Coroutines<T> m5573a(@NotNull Coroutines<? super T> ccyVar) {
        throw new Standard("Implementation of intercepted is intrinsic");
    }
}
