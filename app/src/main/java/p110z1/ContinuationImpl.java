package p110z1;

import java.io.Serializable;
import org.apache.tools.ant.types.selectors.SizeSelector;
import p110z1.Result;

@bwy(m8750a = "1.3")
@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\b!\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00012\u00020\u00032\u00020\u0004B\u0017\u0012\u0010\u0010\u0005\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0018\u00010\u0001¢\u0006\u0002\u0010\u0006J$\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\u00012\b\u0010\u000e\u001a\u0004\u0018\u00010\u00022\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0001H\u0016J\u001a\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\u00012\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0001H\u0016J\n\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0016J\"\u0010\u0011\u001a\u0004\u0018\u00010\u00022\u000e\u0010\u0012\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0013H$ø\u0001\u0000¢\u0006\u0002\u0010\u0014J\b\u0010\u0015\u001a\u00020\rH\u0014J\u001e\u0010\u0016\u001a\u00020\r2\u000e\u0010\u0012\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0013ø\u0001\u0000¢\u0006\u0002\u0010\u0017J\b\u0010\u0018\u001a\u00020\u0019H\u0016R\u0016\u0010\u0007\u001a\u0004\u0018\u00010\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u001b\u0010\u0005\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0018\u00010\u0001¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001a"}, m8860e = {"Lkotlin/coroutines/jvm/internal/BaseContinuationImpl;", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "Ljava/io/Serializable;", "completion", "(Lkotlin/coroutines/Continuation;)V", "callerFrame", "getCallerFrame", "()Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "getCompletion", "()Lkotlin/coroutines/Continuation;", "create", "", SizeSelector.SIZE_KEY, "getStackTraceElement", "Ljava/lang/StackTraceElement;", "invokeSuspend", C4985cm.f20833c, "Lkotlin/Result;", "(Ljava/lang/Object;)Ljava/lang/Object;", "releaseIntercepted", "resumeWith", "(Ljava/lang/Object;)V", "toString", "", "kotlin-stdlib"})
/* renamed from: z1.cec */
/* loaded from: classes3.dex */
public abstract class ContinuationImpl implements Serializable, Continuation<Object>, CoroutineStackFrame {
    @dbs
    private final Continuation<Object> completion;

    @dbs
    protected abstract Object invokeSuspend(@NotNull Object obj);

    protected void releaseIntercepted() {
    }

    public ContinuationImpl(@dbs Continuation<Object> ccpVar) {
        this.completion = ccpVar;
    }

    @dbs
    public final Continuation<Object> getCompletion() {
        return this.completion;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // p110z1.Continuation
    public final void resumeWith(@NotNull Object obj) {
        Object invokeSuspend;
        Continuation ccpVar = this;
        while (true) {
            ContinuationImpl cecVar = (ContinuationImpl) ccpVar;
            DebugProbes.m5504b(cecVar);
            Continuation ccpVar2 = cecVar.completion;
            if (ccpVar2 == null) {
                cji.m5196a();
            }
            try {
                invokeSuspend = cecVar.invokeSuspend(obj);
            } catch (Throwable th) {
                Result.C4802a aVar = Result.Companion;
                obj = Result.m25723constructorimpl(bww.m8760a(th));
            }
            if (invokeSuspend != cdz.m5528b()) {
                Result.C4802a aVar2 = Result.Companion;
                obj = Result.m25723constructorimpl(invokeSuspend);
                cecVar.releaseIntercepted();
                if (ccpVar2 instanceof ContinuationImpl) {
                    ccpVar = ccpVar2;
                } else {
                    ccpVar2.resumeWith(obj);
                    return;
                }
            } else {
                return;
            }
        }
    }

    @NotNull
    public Continuation<Unit> create(@NotNull Continuation<?> ccpVar) {
        cji.m5162f(ccpVar, "completion");
        throw new UnsupportedOperationException("create(Continuation) has not been overridden");
    }

    @NotNull
    public Continuation<Unit> create(@dbs Object obj, @NotNull Continuation<?> ccpVar) {
        cji.m5162f(ccpVar, "completion");
        throw new UnsupportedOperationException("create(Any?;Continuation) has not been overridden");
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Continuation at ");
        Serializable stackTraceElement = getStackTraceElement();
        if (stackTraceElement == null) {
            stackTraceElement = getClass().getName();
        }
        sb.append(stackTraceElement);
        return sb.toString();
    }

    @Override // p110z1.CoroutineStackFrame
    @dbs
    public CoroutineStackFrame getCallerFrame() {
        Continuation<Object> ccpVar = this.completion;
        if (!(ccpVar instanceof CoroutineStackFrame)) {
            ccpVar = null;
        }
        return (CoroutineStackFrame) ccpVar;
    }

    @Override // p110z1.CoroutineStackFrame
    @dbs
    public StackTraceElement getStackTraceElement() {
        return cei.m5509a(this);
    }
}
