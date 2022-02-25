package p110z1;

import p110z1.CoroutineContext;

/* compiled from: ContinuationImpl.kt */
@bwy(m8750a = "1.3")
@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\b!\u0018\u00002\u00020\u0001B\u0019\b\u0016\u0012\u0010\u0010\u0002\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005B!\u0012\u0010\u0010\u0002\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\u000e\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003J\b\u0010\r\u001a\u00020\u000eH\u0014R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0018\u0010\f\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000f"}, m8860e = {"Lkotlin/coroutines/jvm/internal/ContinuationImpl;", "Lkotlin/coroutines/jvm/internal/BaseContinuationImpl;", "completion", "Lkotlin/coroutines/Continuation;", "", "(Lkotlin/coroutines/Continuation;)V", "_context", "Lkotlin/coroutines/CoroutineContext;", "(Lkotlin/coroutines/Continuation;Lkotlin/coroutines/CoroutineContext;)V", "context", "getContext", "()Lkotlin/coroutines/CoroutineContext;", "intercepted", "releaseIntercepted", "", "kotlin-stdlib"})
/* renamed from: z1.cef */
/* loaded from: classes3.dex */
public abstract class cef extends ContinuationImpl {
    private final CoroutineContext _context;
    private transient Continuation<Object> intercepted;

    public cef(@dbs Continuation<Object> ccpVar, @dbs CoroutineContext ccsVar) {
        super(ccpVar);
        this._context = ccsVar;
    }

    public cef(@dbs Continuation<Object> ccpVar) {
        this(ccpVar, ccpVar != null ? ccpVar.getContext() : null);
    }

    @Override // p110z1.Continuation
    @NotNull
    public CoroutineContext getContext() {
        CoroutineContext ccsVar = this._context;
        if (ccsVar == null) {
            cji.m5196a();
        }
        return ccsVar;
    }

    @NotNull
    public final Continuation<Object> intercepted() {
        cef cefVar = this.intercepted;
        if (cefVar == null) {
            ContinuationInterceptor ccqVar = (ContinuationInterceptor) getContext().get(ContinuationInterceptor.f20550a);
            if (ccqVar == null || (cefVar = ccqVar.mo5566a(this)) == null) {
                cefVar = this;
            }
            this.intercepted = cefVar;
        }
        return cefVar;
    }

    @Override // p110z1.ContinuationImpl
    protected void releaseIntercepted() {
        Continuation<?> ccpVar = this.intercepted;
        if (!(ccpVar == null || ccpVar == this)) {
            CoroutineContext.AbstractC4914b bVar = getContext().get(ContinuationInterceptor.f20550a);
            if (bVar == null) {
                cji.m5196a();
            }
            ((ContinuationInterceptor) bVar).mo5564b(ccpVar);
        }
        this.intercepted = cee.f20624a;
    }
}
