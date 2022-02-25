package p110z1;

import com.googlecode.tesseract.android.TessBaseAPI;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import p110z1.Result;

@bwy(m8750a = "1.3")
@bwt
@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000<\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0001\u0018\u0000 \u001a*\u0006\b\u0000\u0010\u0001 \u00002\b\u0012\u0004\u0012\u0002H\u00010\u00022\u00020\u0003:\u0001\u001aB\u0015\b\u0011\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002¢\u0006\u0002\u0010\u0005B\u001f\b\u0000\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\n\u0010\u0011\u001a\u0004\u0018\u00010\u0007H\u0001J\n\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J\u001e\u0010\u0014\u001a\u00020\u00152\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028\u00000\u0016H\u0016ø\u0001\u0000¢\u0006\u0002\u0010\u0017J\b\u0010\u0018\u001a\u00020\u0019H\u0016R\u0016\u0010\t\u001a\u0004\u0018\u00010\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001b"}, m8860e = {"Lkotlin/coroutines/SafeContinuation;", TessBaseAPI.f9204e, "Lkotlin/coroutines/Continuation;", "Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "delegate", "(Lkotlin/coroutines/Continuation;)V", "initialResult", "", "(Lkotlin/coroutines/Continuation;Ljava/lang/Object;)V", "callerFrame", "getCallerFrame", "()Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "context", "Lkotlin/coroutines/CoroutineContext;", "getContext", "()Lkotlin/coroutines/CoroutineContext;", C4985cm.f20833c, "getOrThrow", "getStackTraceElement", "Ljava/lang/StackTraceElement;", "resumeWith", "", "Lkotlin/Result;", "(Ljava/lang/Object;)V", "toString", "", "Companion", "kotlin-stdlib"})
/* renamed from: z1.ccv */
/* loaded from: classes3.dex */
public final class SafeContinuationJvm<T> implements Continuation<T>, CoroutineStackFrame {
    @Deprecated

    /* renamed from: a */
    public static final C4917a f20554a = new C4917a(null);

    /* renamed from: d */
    private static final AtomicReferenceFieldUpdater<SafeContinuationJvm<?>, Object> f20555d = AtomicReferenceFieldUpdater.newUpdater(SafeContinuationJvm.class, Object.class, "b");

    /* renamed from: b */
    private volatile Object f20556b;

    /* renamed from: c */
    private final Continuation<T> f20557c;

    @Override // p110z1.CoroutineStackFrame
    @dbs
    public StackTraceElement getStackTraceElement() {
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public SafeContinuationJvm(@NotNull Continuation<? super T> ccpVar, @dbs Object obj) {
        cji.m5162f(ccpVar, "delegate");
        this.f20557c = ccpVar;
        this.f20556b = obj;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @bwt
    public SafeContinuationJvm(@NotNull Continuation<? super T> ccpVar) {
        this(ccpVar, cdy.UNDECIDED);
        cji.m5162f(ccpVar, "delegate");
    }

    @Override // p110z1.Continuation
    @NotNull
    public CoroutineContext getContext() {
        return this.f20557c.getContext();
    }

    /* compiled from: SafeContinuationJvm.kt */
    @Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002RZ\u0010\u0003\u001aF\u0012\u0014\u0012\u0012\u0012\u0002\b\u0003 \u0006*\b\u0012\u0002\b\u0003\u0018\u00010\u00050\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u0001 \u0006*\"\u0012\u0014\u0012\u0012\u0012\u0002\b\u0003 \u0006*\b\u0012\u0002\b\u0003\u0018\u00010\u00050\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\u00040\u00048\u0002X\u0083\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0007\u0010\u0002¨\u0006\b"}, m8860e = {"Lkotlin/coroutines/SafeContinuation$Companion;", "", "()V", "RESULT", "Ljava/util/concurrent/atomic/AtomicReferenceFieldUpdater;", "Lkotlin/coroutines/SafeContinuation;", "kotlin.jvm.PlatformType", "RESULT$annotations", "kotlin-stdlib"})
    /* renamed from: z1.ccv$a */
    /* loaded from: classes3.dex */
    private static final class C4917a {
        @cgr
        /* renamed from: a */
        private static /* synthetic */ void m5631a() {
        }

        private C4917a() {
        }

        public /* synthetic */ C4917a(DefaultConstructorMarker civVar) {
            this();
        }
    }

    @Override // p110z1.Continuation
    public void resumeWith(@NotNull Object obj) {
        while (true) {
            Object obj2 = this.f20556b;
            if (obj2 == cdy.UNDECIDED) {
                if (f20555d.compareAndSet(this, cdy.UNDECIDED, obj)) {
                    return;
                }
            } else if (obj2 != cdz.m5528b()) {
                throw new IllegalStateException("Already resumed");
            } else if (f20555d.compareAndSet(this, cdz.m5528b(), cdy.RESUMED)) {
                this.f20557c.resumeWith(obj);
                return;
            }
        }
    }

    @dbs
    @bwt
    /* renamed from: a */
    public final Object m5632a() {
        Object obj = this.f20556b;
        if (obj == cdy.UNDECIDED) {
            if (f20555d.compareAndSet(this, cdy.UNDECIDED, cdz.m5528b())) {
                return cdz.m5528b();
            }
            obj = this.f20556b;
        }
        if (obj == cdy.RESUMED) {
            return cdz.m5528b();
        }
        if (!(obj instanceof Result.C4803b)) {
            return obj;
        }
        throw ((Result.C4803b) obj).exception;
    }

    @Override // p110z1.CoroutineStackFrame
    @dbs
    public CoroutineStackFrame getCallerFrame() {
        Continuation<T> ccpVar = this.f20557c;
        if (!(ccpVar instanceof CoroutineStackFrame)) {
            ccpVar = null;
        }
        return (CoroutineStackFrame) ccpVar;
    }

    @NotNull
    public String toString() {
        return "SafeContinuation for " + this.f20557c;
    }
}
