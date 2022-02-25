package p110z1;

import com.googlecode.tesseract.android.TessBaseAPI;
import com.stripe.android.CustomerSession;
import com.stripe.android.model.C2395g;
import org.apache.tools.ant.types.selectors.SizeSelector;
import p110z1.Result;

/* compiled from: Continuation.kt */
@Metadata(m8864a = 2, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000>\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0003\n\u0002\b\u0004\u001a<\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\b0\u0007\"\u0004\b\u0000\u0010\b2\u0006\u0010\t\u001a\u00020\u00012\u001a\b\u0004\u0010\n\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\b0\f\u0012\u0004\u0012\u00020\r0\u000bH\u0087\bø\u0001\u0000\u001a3\u0010\u000e\u001a\u0002H\b\"\u0004\b\u0000\u0010\b2\u001a\b\u0004\u0010\u000f\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\b0\u0007\u0012\u0004\u0012\u00020\r0\u000bH\u0087Hø\u0001\u0000¢\u0006\u0002\u0010\u0010\u001aD\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\r0\u0007\"\u0004\b\u0000\u0010\b*\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\b0\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u000b2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\b0\u0007H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0014\u001a]\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\r0\u0007\"\u0004\b\u0000\u0010\u0015\"\u0004\b\u0001\u0010\b*#\b\u0001\u0012\u0004\u0012\u0002H\u0015\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\b0\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u0016¢\u0006\u0002\b\u00172\u0006\u0010\u0018\u001a\u0002H\u00152\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\b0\u0007H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0019\u001a&\u0010\u001a\u001a\u00020\r\"\u0004\b\u0000\u0010\b*\b\u0012\u0004\u0012\u0002H\b0\u00072\u0006\u0010\u001b\u001a\u0002H\bH\u0087\b¢\u0006\u0002\u0010\u001c\u001a!\u0010\u001d\u001a\u00020\r\"\u0004\b\u0000\u0010\b*\b\u0012\u0004\u0012\u0002H\b0\u00072\u0006\u0010\u001e\u001a\u00020\u001fH\u0087\b\u001a>\u0010 \u001a\u00020\r\"\u0004\b\u0000\u0010\b*\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\b0\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u000b2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\b0\u0007H\u0007ø\u0001\u0000¢\u0006\u0002\u0010!\u001aW\u0010 \u001a\u00020\r\"\u0004\b\u0000\u0010\u0015\"\u0004\b\u0001\u0010\b*#\b\u0001\u0012\u0004\u0012\u0002H\u0015\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\b0\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u0016¢\u0006\u0002\b\u00172\u0006\u0010\u0018\u001a\u0002H\u00152\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\b0\u0007H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\"\"\u001b\u0010\u0000\u001a\u00020\u00018Æ\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\b\u0002\u0010\u0003\u001a\u0004\b\u0004\u0010\u0005\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006#"}, m8860e = {"coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "coroutineContext$annotations", "()V", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "Continuation", "Lkotlin/coroutines/Continuation;", TessBaseAPI.f9204e, "context", "resumeWith", "Lkotlin/Function1;", "Lkotlin/Result;", "", "suspendCoroutine", "block", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "createCoroutine", "", "completion", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;", "R", "Lkotlin/Function2;", "Lkotlin/ExtensionFunctionType;", C2395g.f12127u, "(Lkotlin/jvm/functions/Function2;Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Lkotlin/coroutines/Continuation;", "resume", SizeSelector.SIZE_KEY, "(Lkotlin/coroutines/Continuation;Ljava/lang/Object;)V", "resumeWithException", CustomerSession.f11798b, "", "startCoroutine", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)V", "(Lkotlin/jvm/functions/Function2;Ljava/lang/Object;Lkotlin/coroutines/Continuation;)V", "kotlin-stdlib"})
/* renamed from: z1.ccr */
/* loaded from: classes3.dex */
public final class ccr {
    @bwy(m8750a = "1.3")
    @cey
    /* renamed from: a */
    public static /* synthetic */ void m5647a() {
    }

    @bwy(m8750a = "1.3")
    @cey
    /* renamed from: a */
    private static final <T> void m5646a(@NotNull Continuation<? super T> ccpVar, T t) {
        Result.C4802a aVar = Result.Companion;
        ccpVar.resumeWith(Result.m25723constructorimpl(t));
    }

    @bwy(m8750a = "1.3")
    @cey
    /* renamed from: a */
    private static final <T> void m5645a(@NotNull Continuation<? super T> ccpVar, Throwable th) {
        Result.C4802a aVar = Result.Companion;
        ccpVar.resumeWith(Result.m25723constructorimpl(bww.m8760a(th)));
    }

    /* compiled from: Continuation.kt */
    @Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000!\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u001e\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\tH\u0016ø\u0001\u0000¢\u0006\u0002\u0010\nR\u0014\u0010\u0002\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000b"}, m8860e = {"kotlin/coroutines/ContinuationKt$Continuation$1", "Lkotlin/coroutines/Continuation;", "context", "Lkotlin/coroutines/CoroutineContext;", "getContext", "()Lkotlin/coroutines/CoroutineContext;", "resumeWith", "", C4985cm.f20833c, "Lkotlin/Result;", "(Ljava/lang/Object;)V", "kotlin-stdlib"})
    /* renamed from: z1.ccr$a */
    /* loaded from: classes3.dex */
    public static final class C4911a implements Continuation<T> {

        /* renamed from: a */
        final /* synthetic */ CoroutineContext f20552a;

        /* renamed from: b */
        final /* synthetic */ chd f20553b;

        public C4911a(CoroutineContext ccsVar, chd chdVar) {
            this.f20552a = ccsVar;
            this.f20553b = chdVar;
        }

        @Override // p110z1.Continuation
        @NotNull
        public CoroutineContext getContext() {
            return this.f20552a;
        }

        @Override // p110z1.Continuation
        public void resumeWith(@NotNull Object obj) {
            this.f20553b.invoke(Result.m25722boximpl(obj));
        }
    }

    @bwy(m8750a = "1.3")
    @cey
    /* renamed from: a */
    private static final <T> Continuation<T> m5644a(CoroutineContext ccsVar, chd<? super Result<? extends T>, Unit> chdVar) {
        return new C4911a(ccsVar, chdVar);
    }

    @bwy(m8750a = "1.3")
    @NotNull
    /* renamed from: a */
    public static final <T> Continuation<Unit> m5643a(@NotNull chd<? super Continuation<? super T>, ? extends Object> chdVar, @NotNull Continuation<? super T> ccpVar) {
        cji.m5162f(chdVar, "$this$createCoroutine");
        cji.m5162f(ccpVar, "completion");
        return new SafeContinuationJvm(cdz.m5535a(cdz.m5533a(chdVar, ccpVar)), cdz.m5528b());
    }

    @bwy(m8750a = "1.3")
    @NotNull
    /* renamed from: a */
    public static final <R, T> Continuation<Unit> m5642a(@NotNull cho<? super R, ? super Continuation<? super T>, ? extends Object> choVar, R r, @NotNull Continuation<? super T> ccpVar) {
        cji.m5162f(choVar, "$this$createCoroutine");
        cji.m5162f(ccpVar, "completion");
        return new SafeContinuationJvm(cdz.m5535a(cdz.m5532a(choVar, r, ccpVar)), cdz.m5528b());
    }

    @bwy(m8750a = "1.3")
    /* renamed from: b */
    public static final <T> void m5640b(@NotNull chd<? super Continuation<? super T>, ? extends Object> chdVar, @NotNull Continuation<? super T> ccpVar) {
        cji.m5162f(chdVar, "$this$startCoroutine");
        cji.m5162f(ccpVar, "completion");
        Continuation a = cdz.m5535a(cdz.m5533a(chdVar, ccpVar));
        Unit bydVar = Unit.f20418a;
        Result.C4802a aVar = Result.Companion;
        a.resumeWith(Result.m25723constructorimpl(bydVar));
    }

    @bwy(m8750a = "1.3")
    /* renamed from: b */
    public static final <R, T> void m5639b(@NotNull cho<? super R, ? super Continuation<? super T>, ? extends Object> choVar, R r, @NotNull Continuation<? super T> ccpVar) {
        cji.m5162f(choVar, "$this$startCoroutine");
        cji.m5162f(ccpVar, "completion");
        Continuation a = cdz.m5535a(cdz.m5532a(choVar, r, ccpVar));
        Unit bydVar = Unit.f20418a;
        Result.C4802a aVar = Result.Companion;
        a.resumeWith(Result.m25723constructorimpl(bydVar));
    }

    @bwy(m8750a = "1.3")
    @cey
    /* renamed from: c */
    private static final <T> Object m5638c(chd<? super Continuation<? super T>, Unit> chdVar, Continuation<? super T> ccpVar) {
        InlineMarker.m5204a(0);
        SafeContinuationJvm ccvVar = new SafeContinuationJvm(cdz.m5535a(ccpVar));
        chdVar.invoke(ccvVar);
        Object a = ccvVar.m5632a();
        if (a == cdz.m5528b()) {
            DebugProbes.m5503c(ccpVar);
        }
        InlineMarker.m5204a(1);
        return a;
    }

    /* renamed from: b */
    private static final CoroutineContext m5641b() {
        throw new Standard("Implemented as intrinsic");
    }
}
