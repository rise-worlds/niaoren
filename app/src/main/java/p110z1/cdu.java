package p110z1;

import com.googlecode.tesseract.android.TessBaseAPI;
import com.stripe.android.CustomerSession;
import org.apache.tools.ant.types.selectors.SizeSelector;
import p110z1.Result;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: CoroutinesMigration.kt */
@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u0003\n\u0000\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0013\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\u0002\u0010\u0005J\u0015\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u000fJ\u0010\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016R\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0013"}, m8860e = {"Lkotlin/coroutines/experimental/migration/ExperimentalContinuationMigration;", TessBaseAPI.f9204e, "Lkotlin/coroutines/experimental/Continuation;", "continuation", "Lkotlin/coroutines/Continuation;", "(Lkotlin/coroutines/Continuation;)V", "context", "Lkotlin/coroutines/experimental/CoroutineContext;", "getContext", "()Lkotlin/coroutines/experimental/CoroutineContext;", "getContinuation", "()Lkotlin/coroutines/Continuation;", "resume", "", SizeSelector.SIZE_KEY, "(Ljava/lang/Object;)V", "resumeWithException", CustomerSession.f11798b, "", "kotlin-stdlib-coroutines"})
/* renamed from: z1.cdu */
/* loaded from: classes3.dex */
public final class cdu<T> implements Coroutines<T> {
    @NotNull

    /* renamed from: a */
    private final cda f20601a;
    @NotNull

    /* renamed from: b */
    private final Continuation<T> f20602b;

    /* JADX WARN: Multi-variable type inference failed */
    public cdu(@NotNull Continuation<? super T> ccpVar) {
        cji.m5162f(ccpVar, "continuation");
        this.f20602b = ccpVar;
        this.f20601a = cdr.m5560a(this.f20602b.getContext());
    }

    @NotNull
    /* renamed from: a */
    public final Continuation<T> m5545a() {
        return this.f20602b;
    }

    @Override // p110z1.Coroutines
    @NotNull
    public cda getContext() {
        return this.f20601a;
    }

    @Override // p110z1.Coroutines
    public void resume(T t) {
        Continuation<T> ccpVar = this.f20602b;
        Result.C4802a aVar = Result.Companion;
        ccpVar.resumeWith(Result.m25723constructorimpl(t));
    }

    @Override // p110z1.Coroutines
    public void resumeWithException(@NotNull Throwable th) {
        cji.m5162f(th, CustomerSession.f11798b);
        Continuation<T> ccpVar = this.f20602b;
        Result.C4802a aVar = Result.Companion;
        ccpVar.resumeWith(Result.m25723constructorimpl(bww.m8760a(th)));
    }
}
