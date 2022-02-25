package p110z1;

import com.stripe.android.CustomerSession;
import org.apache.tools.ant.types.selectors.SizeSelector;

@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u0003\n\u0002\b\u0003\b&\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0003B\u001f\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0010\u0010\u0006\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0018\u00010\u0003¢\u0006\u0002\u0010\u0007J$\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\u00032\b\u0010\u0014\u001a\u0004\u0018\u00010\u00022\n\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u0003H\u0016J\u001a\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\u00032\n\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u0003H\u0016J\u001e\u0010\u0015\u001a\u0004\u0018\u00010\u00022\b\u0010\u0016\u001a\u0004\u0018\u00010\u00022\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H$J\u0012\u0010\u0019\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0002H\u0016J\u0010\u0010\u001a\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\u0018H\u0016R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\n\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0006\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0018\u00010\u00038\u0004@\u0004X\u0085\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\u00020\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0019\u0010\u000e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0012\u0010\u0011\u001a\u00020\u00058\u0004@\u0004X\u0085\u000e¢\u0006\u0002\n\u0000¨\u0006\u001b"}, m8860e = {"Lkotlin/coroutines/experimental/jvm/internal/CoroutineImpl;", "Lkotlin/jvm/internal/Lambda;", "", "Lkotlin/coroutines/experimental/Continuation;", "arity", "", "completion", "(ILkotlin/coroutines/experimental/Continuation;)V", "_context", "Lkotlin/coroutines/experimental/CoroutineContext;", "_facade", "context", "getContext", "()Lkotlin/coroutines/experimental/CoroutineContext;", "facade", "getFacade", "()Lkotlin/coroutines/experimental/Continuation;", "label", "create", "", SizeSelector.SIZE_KEY, "doResume", "data", CustomerSession.f11798b, "", "resume", "resumeWithException", "kotlin-stdlib-coroutines"})
/* renamed from: z1.cdm */
/* loaded from: classes3.dex */
public abstract class CoroutineImpl extends Lambda<Object> implements Coroutines<Object> {
    private final cda _context;
    private Coroutines<Object> _facade;
    @JvmPlatformAnnotations
    @dbs
    protected Coroutines<Object> completion;
    @JvmPlatformAnnotations
    protected int label;

    @dbs
    protected abstract Object doResume(@dbs Object obj, @dbs Throwable th);

    public CoroutineImpl(int i, @dbs Coroutines<Object> ccyVar) {
        super(i);
        this.completion = ccyVar;
        this.label = this.completion != null ? 0 : -1;
        Coroutines<Object> ccyVar2 = this.completion;
        this._context = ccyVar2 != null ? ccyVar2.getContext() : null;
    }

    @Override // p110z1.Coroutines
    @NotNull
    public cda getContext() {
        cda cdaVar = this._context;
        if (cdaVar == null) {
            cji.m5196a();
        }
        return cdaVar;
    }

    @NotNull
    public final Coroutines<Object> getFacade() {
        if (this._facade == null) {
            cda cdaVar = this._context;
            if (cdaVar == null) {
                cji.m5196a();
            }
            this._facade = CoroutineIntrinsics.m5569a(cdaVar, this);
        }
        Coroutines<Object> ccyVar = this._facade;
        if (ccyVar == null) {
            cji.m5196a();
        }
        return ccyVar;
    }

    @Override // p110z1.Coroutines
    public void resume(@dbs Object obj) {
        Coroutines<Object> ccyVar = this.completion;
        if (ccyVar == null) {
            cji.m5196a();
        }
        try {
            Object doResume = doResume(obj, null);
            if (doResume == cdj.m5579b()) {
                return;
            }
            if (ccyVar != null) {
                ccyVar.resume(doResume);
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
        Coroutines<Object> ccyVar = this.completion;
        if (ccyVar == null) {
            cji.m5196a();
        }
        try {
            Object doResume = doResume(null, th);
            if (doResume == cdj.m5579b()) {
                return;
            }
            if (ccyVar != null) {
                ccyVar.resume(doResume);
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.coroutines.experimental.Continuation<kotlin.Any?>");
        } catch (Throwable th2) {
            ccyVar.resumeWithException(th2);
        }
    }

    @NotNull
    public Coroutines<Unit> create(@NotNull Coroutines<?> ccyVar) {
        cji.m5162f(ccyVar, "completion");
        throw new IllegalStateException("create(Continuation) has not been overridden");
    }

    @NotNull
    public Coroutines<Unit> create(@dbs Object obj, @NotNull Coroutines<?> ccyVar) {
        cji.m5162f(ccyVar, "completion");
        throw new IllegalStateException("create(Any?;Continuation) has not been overridden");
    }
}
