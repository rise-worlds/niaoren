package p110z1;

import com.googlecode.tesseract.android.TessBaseAPI;
import com.stripe.android.CustomerSession;
import com.stripe.android.model.C2395g;
import org.apache.tools.ant.types.selectors.SizeSelector;

/* JADX INFO: Access modifiers changed from: package-private */
@Metadata(m8864a = 5, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u00002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a:\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\"\u0004\b\u0000\u0010\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\t0\u00072\u0010\b\u0004\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\fH\u0082\b¢\u0006\u0002\b\r\u001aD\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\"\u0004\b\u0000\u0010\t*\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\t0\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u000f2\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\t0\u0007H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0010\u001a]\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\"\u0004\b\u0000\u0010\u0011\"\u0004\b\u0001\u0010\t*#\b\u0001\u0012\u0004\u0012\u0002H\u0011\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\t0\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0012¢\u0006\u0002\b\u00132\u0006\u0010\u0014\u001a\u0002H\u00112\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\t0\u0007H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0015\u001aA\u0010\u0016\u001a\u0004\u0018\u00010\u0001\"\u0004\b\u0000\u0010\t*\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\t0\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u000f2\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\t0\u0007H\u0087\bø\u0001\u0000¢\u0006\u0002\u0010\u0017\u001aZ\u0010\u0016\u001a\u0004\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u0011\"\u0004\b\u0001\u0010\t*#\b\u0001\u0012\u0004\u0012\u0002H\u0011\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\t0\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0012¢\u0006\u0002\b\u00132\u0006\u0010\u0014\u001a\u0002H\u00112\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\t0\u0007H\u0087\bø\u0001\u0000¢\u0006\u0002\u0010\u0018\"\u001a\u0010\u0000\u001a\u00020\u00018FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0002\u0010\u0003\u001a\u0004\b\u0004\u0010\u0005\u0082\u0002\u0004\n\u0002\b\t¨\u0006\u0019"}, m8860e = {"COROUTINE_SUSPENDED", "", "COROUTINE_SUSPENDED$annotations", "()V", "getCOROUTINE_SUSPENDED", "()Ljava/lang/Object;", "buildContinuationByInvokeCall", "Lkotlin/coroutines/experimental/Continuation;", "", TessBaseAPI.f9204e, "completion", "block", "Lkotlin/Function0;", "buildContinuationByInvokeCall$IntrinsicsKt__IntrinsicsJvmKt", "createCoroutineUnchecked", "Lkotlin/Function1;", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/experimental/Continuation;)Lkotlin/coroutines/experimental/Continuation;", "R", "Lkotlin/Function2;", "Lkotlin/ExtensionFunctionType;", C2395g.f12127u, "(Lkotlin/jvm/functions/Function2;Ljava/lang/Object;Lkotlin/coroutines/experimental/Continuation;)Lkotlin/coroutines/experimental/Continuation;", "startCoroutineUninterceptedOrReturn", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/experimental/Continuation;)Ljava/lang/Object;", "(Lkotlin/jvm/functions/Function2;Ljava/lang/Object;Lkotlin/coroutines/experimental/Continuation;)Ljava/lang/Object;", "kotlin-stdlib-coroutines"}, m8859f = "kotlin/coroutines/experimental/intrinsics/IntrinsicsKt", m8857h = 1)
/* renamed from: z1.cdk */
/* loaded from: classes3.dex */
public class IntrinsicsJvm {
    @bwy(m8750a = "1.1")
    /* renamed from: a */
    public static /* synthetic */ void m5583a() {
    }

    @bwy(m8750a = "1.1")
    @cey
    /* renamed from: b */
    private static final <T> Object m5578b(@NotNull chd<? super Coroutines<? super T>, ? extends Object> chdVar, Coroutines<? super T> ccyVar) {
        if (chdVar != null) {
            return ((chd) TypeIntrinsics.m5070b(chdVar, 1)).invoke(ccyVar);
        }
        throw new TypeCastException("null cannot be cast to non-null type (kotlin.coroutines.experimental.Continuation<T>) -> kotlin.Any?");
    }

    @bwy(m8750a = "1.1")
    @cey
    /* renamed from: b */
    private static final <R, T> Object m5577b(@NotNull cho<? super R, ? super Coroutines<? super T>, ? extends Object> choVar, R r, Coroutines<? super T> ccyVar) {
        if (choVar != null) {
            return ((cho) TypeIntrinsics.m5070b(choVar, 2)).invoke(r, ccyVar);
        }
        throw new TypeCastException("null cannot be cast to non-null type (R, kotlin.coroutines.experimental.Continuation<T>) -> kotlin.Any?");
    }

    @bwy(m8750a = "1.1")
    @NotNull
    /* renamed from: a */
    public static final <T> Coroutines<Unit> m5581a(@NotNull chd<? super Coroutines<? super T>, ? extends Object> chdVar, @NotNull Coroutines<? super T> ccyVar) {
        cji.m5162f(chdVar, "$this$createCoroutineUnchecked");
        cji.m5162f(ccyVar, "completion");
        if (!(chdVar instanceof CoroutineImpl)) {
            return CoroutineIntrinsics.m5569a(ccyVar.getContext(), new C4931b(ccyVar, chdVar, ccyVar));
        }
        Coroutines<Unit> create = ((CoroutineImpl) chdVar).create(ccyVar);
        if (create != null) {
            return ((CoroutineImpl) create).getFacade();
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.coroutines.experimental.jvm.internal.CoroutineImpl");
    }

    @bwy(m8750a = "1.1")
    @NotNull
    /* renamed from: a */
    public static final <R, T> Coroutines<Unit> m5580a(@NotNull cho<? super R, ? super Coroutines<? super T>, ? extends Object> choVar, R r, @NotNull Coroutines<? super T> ccyVar) {
        cji.m5162f(choVar, "$this$createCoroutineUnchecked");
        cji.m5162f(ccyVar, "completion");
        if (!(choVar instanceof CoroutineImpl)) {
            return CoroutineIntrinsics.m5569a(ccyVar.getContext(), new C4932c(ccyVar, choVar, r, ccyVar));
        }
        Coroutines<Unit> create = ((CoroutineImpl) choVar).create(r, ccyVar);
        if (create != null) {
            return ((CoroutineImpl) create).getFacade();
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.coroutines.experimental.jvm.internal.CoroutineImpl");
    }

    /* compiled from: IntrinsicsJvm.kt */
    @Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0003\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0015\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0002H\u0016¢\u0006\u0002\u0010\tJ\u0010\u0010\n\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\fH\u0016R\u0014\u0010\u0003\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\r"}, m8860e = {"kotlin/coroutines/experimental/intrinsics/IntrinsicsKt__IntrinsicsJvmKt$buildContinuationByInvokeCall$continuation$1", "Lkotlin/coroutines/experimental/Continuation;", "", "context", "Lkotlin/coroutines/experimental/CoroutineContext;", "getContext", "()Lkotlin/coroutines/experimental/CoroutineContext;", "resume", SizeSelector.SIZE_KEY, "(Lkotlin/Unit;)V", "resumeWithException", CustomerSession.f11798b, "", "kotlin-stdlib-coroutines"})
    /* renamed from: z1.cdk$a */
    /* loaded from: classes3.dex */
    public static final class C4930a implements Coroutines<Unit> {

        /* renamed from: a */
        final /* synthetic */ Coroutines f20584a;

        /* renamed from: b */
        final /* synthetic */ chc f20585b;

        public C4930a(Coroutines ccyVar, chc chcVar) {
            this.f20584a = ccyVar;
            this.f20585b = chcVar;
        }

        @Override // p110z1.Coroutines
        @NotNull
        public cda getContext() {
            return this.f20584a.getContext();
        }

        /* renamed from: a */
        public void resume(@NotNull Unit bydVar) {
            cji.m5162f(bydVar, SizeSelector.SIZE_KEY);
            Coroutines ccyVar = this.f20584a;
            try {
                Object invoke = this.f20585b.invoke();
                if (invoke == cdj.m5579b()) {
                    return;
                }
                if (ccyVar != null) {
                    ccyVar.resume(invoke);
                    return;
                }
                throw new TypeCastException("null cannot be cast to non-null type kotlin.coroutines.experimental.Continuation<kotlin.Any?>");
            } catch (Throwable th) {
                ccyVar.resumeWithException(th);
            }
        }

        @Override // p110z1.Coroutines
        public void resumeWithException(@NotNull Throwable th) {
            cji.m5162f(th, CustomerSession.f11798b);
            this.f20584a.resumeWithException(th);
        }
    }

    /* compiled from: IntrinsicsJvm.kt */
    @Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u00005\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\b\u0007\n\u0002\b\u0007\n\u0002\b\u0007\n\u0002\b\u0007\n\u0002\b\u0007\n\u0002\b\u0007\n\u0002\u0010\u0003\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0015\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0002H\u0016¢\u0006\u0002\u0010\tJ\u0010\u0010\n\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\fH\u0016R\u0014\u0010\u0003\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\r¸\u0006\u0000"}, m8860e = {"kotlin/coroutines/experimental/intrinsics/IntrinsicsKt__IntrinsicsJvmKt$buildContinuationByInvokeCall$continuation$1", "Lkotlin/coroutines/experimental/Continuation;", "", "context", "Lkotlin/coroutines/experimental/CoroutineContext;", "getContext", "()Lkotlin/coroutines/experimental/CoroutineContext;", "resume", SizeSelector.SIZE_KEY, "(Lkotlin/Unit;)V", "resumeWithException", CustomerSession.f11798b, "", "kotlin-stdlib-coroutines"})
    /* renamed from: z1.cdk$b */
    /* loaded from: classes3.dex */
    public static final class C4931b implements Coroutines<Unit> {

        /* renamed from: a */
        final /* synthetic */ Coroutines f20586a;

        /* renamed from: b */
        final /* synthetic */ chd f20587b;

        /* renamed from: c */
        final /* synthetic */ Coroutines f20588c;

        public C4931b(Coroutines ccyVar, chd chdVar, Coroutines ccyVar2) {
            this.f20586a = ccyVar;
            this.f20587b = chdVar;
            this.f20588c = ccyVar2;
        }

        @Override // p110z1.Coroutines
        @NotNull
        public cda getContext() {
            return this.f20586a.getContext();
        }

        /* renamed from: a */
        public void resume(@NotNull Unit bydVar) {
            cji.m5162f(bydVar, SizeSelector.SIZE_KEY);
            Coroutines ccyVar = this.f20586a;
            try {
                chd chdVar = this.f20587b;
                if (chdVar != null) {
                    Object invoke = ((chd) TypeIntrinsics.m5070b(chdVar, 1)).invoke(this.f20588c);
                    if (invoke == cdj.m5579b()) {
                        return;
                    }
                    if (ccyVar != null) {
                        ccyVar.resume(invoke);
                        return;
                    }
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.coroutines.experimental.Continuation<kotlin.Any?>");
                }
                throw new TypeCastException("null cannot be cast to non-null type (kotlin.coroutines.experimental.Continuation<T>) -> kotlin.Any?");
            } catch (Throwable th) {
                ccyVar.resumeWithException(th);
            }
        }

        @Override // p110z1.Coroutines
        public void resumeWithException(@NotNull Throwable th) {
            cji.m5162f(th, CustomerSession.f11798b);
            this.f20586a.resumeWithException(th);
        }
    }

    /* compiled from: IntrinsicsJvm.kt */
    @Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u00005\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\b\u0007\n\u0002\b\u0007\n\u0002\b\u0007\n\u0002\b\u0007\n\u0002\b\u0007\n\u0002\b\u0007\n\u0002\u0010\u0003\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0015\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0002H\u0016¢\u0006\u0002\u0010\tJ\u0010\u0010\n\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\fH\u0016R\u0014\u0010\u0003\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\r¸\u0006\u0000"}, m8860e = {"kotlin/coroutines/experimental/intrinsics/IntrinsicsKt__IntrinsicsJvmKt$buildContinuationByInvokeCall$continuation$1", "Lkotlin/coroutines/experimental/Continuation;", "", "context", "Lkotlin/coroutines/experimental/CoroutineContext;", "getContext", "()Lkotlin/coroutines/experimental/CoroutineContext;", "resume", SizeSelector.SIZE_KEY, "(Lkotlin/Unit;)V", "resumeWithException", CustomerSession.f11798b, "", "kotlin-stdlib-coroutines"})
    /* renamed from: z1.cdk$c */
    /* loaded from: classes3.dex */
    public static final class C4932c implements Coroutines<Unit> {

        /* renamed from: a */
        final /* synthetic */ Coroutines f20589a;

        /* renamed from: b */
        final /* synthetic */ cho f20590b;

        /* renamed from: c */
        final /* synthetic */ Object f20591c;

        /* renamed from: d */
        final /* synthetic */ Coroutines f20592d;

        public C4932c(Coroutines ccyVar, cho choVar, Object obj, Coroutines ccyVar2) {
            this.f20589a = ccyVar;
            this.f20590b = choVar;
            this.f20591c = obj;
            this.f20592d = ccyVar2;
        }

        @Override // p110z1.Coroutines
        @NotNull
        public cda getContext() {
            return this.f20589a.getContext();
        }

        /* renamed from: a */
        public void resume(@NotNull Unit bydVar) {
            cji.m5162f(bydVar, SizeSelector.SIZE_KEY);
            Coroutines ccyVar = this.f20589a;
            try {
                cho choVar = this.f20590b;
                if (choVar != null) {
                    Object invoke = ((cho) TypeIntrinsics.m5070b(choVar, 2)).invoke(this.f20591c, this.f20592d);
                    if (invoke == cdj.m5579b()) {
                        return;
                    }
                    if (ccyVar != null) {
                        ccyVar.resume(invoke);
                        return;
                    }
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.coroutines.experimental.Continuation<kotlin.Any?>");
                }
                throw new TypeCastException("null cannot be cast to non-null type (R, kotlin.coroutines.experimental.Continuation<T>) -> kotlin.Any?");
            } catch (Throwable th) {
                ccyVar.resumeWithException(th);
            }
        }

        @Override // p110z1.Coroutines
        public void resumeWithException(@NotNull Throwable th) {
            cji.m5162f(th, CustomerSession.f11798b);
            this.f20589a.resumeWithException(th);
        }
    }

    /* renamed from: a */
    private static final <T> Coroutines<Unit> m5582a(Coroutines<? super T> ccyVar, chc<? extends Object> chcVar) {
        return CoroutineIntrinsics.m5569a(ccyVar.getContext(), new C4930a(ccyVar, chcVar));
    }

    @NotNull
    /* renamed from: b */
    public static final Object m5579b() {
        return cdz.m5528b();
    }
}
