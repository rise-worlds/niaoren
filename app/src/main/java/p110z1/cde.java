package p110z1;

import com.googlecode.tesseract.android.TessBaseAPI;
import com.stripe.android.CustomerSession;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import org.apache.tools.ant.types.selectors.SizeSelector;

/* compiled from: SafeContinuationJvm.kt */
@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000.\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u0003\n\u0002\b\u0003\b\u0001\u0018\u0000 \u0015*\u0006\b\u0000\u0010\u0001 \u00002\b\u0012\u0004\u0012\u0002H\u00010\u0002:\u0002\u0015\u0016B\u0015\b\u0011\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002¢\u0006\u0002\u0010\u0004B\u001f\b\u0000\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007J\n\u0010\r\u001a\u0004\u0018\u00010\u0006H\u0001J\u0015\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0011J\u0010\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u0014H\u0016R\u0014\u0010\b\u001a\u00020\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0017"}, m8860e = {"Lkotlin/coroutines/experimental/SafeContinuation;", TessBaseAPI.f9204e, "Lkotlin/coroutines/experimental/Continuation;", "delegate", "(Lkotlin/coroutines/experimental/Continuation;)V", "initialResult", "", "(Lkotlin/coroutines/experimental/Continuation;Ljava/lang/Object;)V", "context", "Lkotlin/coroutines/experimental/CoroutineContext;", "getContext", "()Lkotlin/coroutines/experimental/CoroutineContext;", C4985cm.f20833c, "getResult", "resume", "", SizeSelector.SIZE_KEY, "(Ljava/lang/Object;)V", "resumeWithException", CustomerSession.f11798b, "", "Companion", "Fail", "kotlin-stdlib-coroutines"})
@bwt
/* renamed from: z1.cde */
/* loaded from: classes3.dex */
public final class cde<T> implements Coroutines<T> {

    /* renamed from: a */
    public static final C4927a f20566a = new C4927a(null);

    /* renamed from: d */
    private static final Object f20567d = new Object();

    /* renamed from: e */
    private static final Object f20568e = new Object();

    /* renamed from: f */
    private static final AtomicReferenceFieldUpdater<cde<?>, Object> f20569f = AtomicReferenceFieldUpdater.newUpdater(cde.class, Object.class, "b");

    /* renamed from: b */
    private volatile Object f20570b;

    /* renamed from: c */
    private final Coroutines<T> f20571c;

    /* JADX WARN: Multi-variable type inference failed */
    public cde(@NotNull Coroutines<? super T> ccyVar, @dbs Object obj) {
        cji.m5162f(ccyVar, "delegate");
        this.f20571c = ccyVar;
        this.f20570b = obj;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @bwt
    public cde(@NotNull Coroutines<? super T> ccyVar) {
        this(ccyVar, f20567d);
        cji.m5162f(ccyVar, "delegate");
    }

    @Override // p110z1.Coroutines
    @NotNull
    public cda getContext() {
        return this.f20571c.getContext();
    }

    /* compiled from: SafeContinuationJvm.kt */
    @Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002RZ\u0010\u0003\u001aF\u0012\u0014\u0012\u0012\u0012\u0002\b\u0003 \u0006*\b\u0012\u0002\b\u0003\u0018\u00010\u00050\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u0001 \u0006*\"\u0012\u0014\u0012\u0012\u0012\u0002\b\u0003 \u0006*\b\u0012\u0002\b\u0003\u0018\u00010\u00050\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\u00040\u00048\u0002X\u0083\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0007\u0010\u0002R\u0010\u0010\b\u001a\u0004\u0018\u00010\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, m8860e = {"Lkotlin/coroutines/experimental/SafeContinuation$Companion;", "", "()V", "RESULT", "Ljava/util/concurrent/atomic/AtomicReferenceFieldUpdater;", "Lkotlin/coroutines/experimental/SafeContinuation;", "kotlin.jvm.PlatformType", "RESULT$annotations", "RESUMED", "UNDECIDED", "kotlin-stdlib-coroutines"})
    /* renamed from: z1.cde$a */
    /* loaded from: classes3.dex */
    public static final class C4927a {
        @cgr
        /* renamed from: a */
        private static /* synthetic */ void m5596a() {
        }

        private C4927a() {
        }

        public /* synthetic */ C4927a(DefaultConstructorMarker civVar) {
            this();
        }
    }

    /* compiled from: SafeContinuationJvm.kt */
    @Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0004\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, m8860e = {"Lkotlin/coroutines/experimental/SafeContinuation$Fail;", "", CustomerSession.f11798b, "", "(Ljava/lang/Throwable;)V", "getException", "()Ljava/lang/Throwable;", "kotlin-stdlib-coroutines"})
    /* renamed from: z1.cde$b */
    /* loaded from: classes3.dex */
    private static final class C4928b {
        @NotNull

        /* renamed from: a */
        private final Throwable f20572a;

        public C4928b(@NotNull Throwable th) {
            cji.m5162f(th, CustomerSession.f11798b);
            this.f20572a = th;
        }

        @NotNull
        /* renamed from: a */
        public final Throwable m5595a() {
            return this.f20572a;
        }
    }

    @Override // p110z1.Coroutines
    public void resume(T t) {
        while (true) {
            Object obj = this.f20570b;
            Object obj2 = f20567d;
            if (obj == obj2) {
                if (f20569f.compareAndSet(this, obj2, t)) {
                    return;
                }
            } else if (obj != cdj.m5579b()) {
                throw new IllegalStateException("Already resumed");
            } else if (f20569f.compareAndSet(this, cdj.m5579b(), f20568e)) {
                this.f20571c.resume(t);
                return;
            }
        }
    }

    @Override // p110z1.Coroutines
    public void resumeWithException(@NotNull Throwable th) {
        cji.m5162f(th, CustomerSession.f11798b);
        while (true) {
            Object obj = this.f20570b;
            Object obj2 = f20567d;
            if (obj == obj2) {
                if (f20569f.compareAndSet(this, obj2, new C4928b(th))) {
                    return;
                }
            } else if (obj != cdj.m5579b()) {
                throw new IllegalStateException("Already resumed");
            } else if (f20569f.compareAndSet(this, cdj.m5579b(), f20568e)) {
                this.f20571c.resumeWithException(th);
                return;
            }
        }
    }

    @dbs
    @bwt
    /* renamed from: a */
    public final Object m5597a() {
        Object obj = this.f20570b;
        Object obj2 = f20567d;
        if (obj == obj2) {
            if (f20569f.compareAndSet(this, obj2, cdj.m5579b())) {
                return cdj.m5579b();
            }
            obj = this.f20570b;
        }
        if (obj == f20568e) {
            return cdj.m5579b();
        }
        if (!(obj instanceof C4928b)) {
            return obj;
        }
        throw ((C4928b) obj).m5595a();
    }
}
