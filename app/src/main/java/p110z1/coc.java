package p110z1;

import com.googlecode.tesseract.android.TessBaseAPI;
import com.stripe.android.view.ShippingInfoWidget;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.apache.tools.ant.types.selectors.SizeSelector;
import p110z1.Result;

/* compiled from: SequenceBuilder.kt */
@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000B\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010(\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u00032\b\u0012\u0004\u0012\u00020\u00050\u0004B\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0016\u001a\u00020\u0017H\u0002J\t\u0010\u0018\u001a\u00020\u0019H\u0096\u0002J\u000e\u0010\u001a\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010\u001bJ\r\u0010\u001c\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010\u001bJ\u001e\u0010\u001d\u001a\u00020\u00052\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00050\u001fH\u0016ø\u0001\u0000¢\u0006\u0002\u0010 J\u0019\u0010!\u001a\u00020\u00052\u0006\u0010\"\u001a\u00028\u0000H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010#J\u001f\u0010$\u001a\u00020\u00052\f\u0010%\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010&R\u0014\u0010\u0007\u001a\u00020\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0016\u0010\u000b\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u0012\u0010\u0011\u001a\u0004\u0018\u00018\u0000X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0012R\u0012\u0010\u0013\u001a\u00060\u0014j\u0002`\u0015X\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006'"}, m8860e = {"Lkotlin/sequences/SequenceBuilderIterator;", TessBaseAPI.f9204e, "Lkotlin/sequences/SequenceScope;", "", "Lkotlin/coroutines/Continuation;", "", "()V", "context", "Lkotlin/coroutines/CoroutineContext;", "getContext", "()Lkotlin/coroutines/CoroutineContext;", "nextIterator", "nextStep", "getNextStep", "()Lkotlin/coroutines/Continuation;", "setNextStep", "(Lkotlin/coroutines/Continuation;)V", "nextValue", "Ljava/lang/Object;", ShippingInfoWidget.f12562e, "", "Lkotlin/sequences/State;", "exceptionalState", "", "hasNext", "", "next", "()Ljava/lang/Object;", "nextNotReady", "resumeWith", C4985cm.f20833c, "Lkotlin/Result;", "(Ljava/lang/Object;)V", "yield", SizeSelector.SIZE_KEY, "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "yieldAll", "iterator", "(Ljava/util/Iterator;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlin-stdlib"})
/* renamed from: z1.coc */
/* loaded from: classes3.dex */
final class coc<T> extends cod<T> implements Iterator<T>, Continuation<Unit>, KMarkers {

    /* renamed from: a */
    private int f20937a;

    /* renamed from: b */
    private T f20938b;

    /* renamed from: c */
    private Iterator<? extends T> f20939c;
    @dbs

    /* renamed from: d */
    private Continuation<? super Unit> f20940d;

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @dbs
    /* renamed from: a */
    public final Continuation<Unit> m4462a() {
        return this.f20940d;
    }

    /* renamed from: a */
    public final void m4461a(@dbs Continuation<? super Unit> ccpVar) {
        this.f20940d = ccpVar;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // java.util.Iterator
    public boolean hasNext() {
        while (true) {
            switch (this.f20937a) {
                case 0:
                    break;
                case 1:
                    Iterator<? extends T> it = this.f20939c;
                    if (it == null) {
                        cji.m5196a();
                    }
                    if (!it.hasNext()) {
                        this.f20939c = null;
                        break;
                    } else {
                        this.f20937a = 2;
                        return true;
                    }
                case 2:
                case 3:
                    return true;
                case 4:
                    return false;
                default:
                    throw m4459c();
            }
            this.f20937a = 5;
            Continuation<? super Unit> ccpVar = this.f20940d;
            if (ccpVar == null) {
                cji.m5196a();
            }
            this.f20940d = null;
            Unit bydVar = Unit.f20418a;
            Result.C4802a aVar = Result.Companion;
            ccpVar.resumeWith(Result.m25723constructorimpl(bydVar));
        }
    }

    @Override // java.util.Iterator
    public T next() {
        switch (this.f20937a) {
            case 0:
            case 1:
                return m4460b();
            case 2:
                this.f20937a = 1;
                Iterator<? extends T> it = this.f20939c;
                if (it == null) {
                    cji.m5196a();
                }
                return (T) it.next();
            case 3:
                this.f20937a = 0;
                T t = this.f20938b;
                this.f20938b = null;
                return t;
            default:
                throw m4459c();
        }
    }

    /* renamed from: b */
    private final T m4460b() {
        if (hasNext()) {
            return next();
        }
        throw new NoSuchElementException();
    }

    /* renamed from: c */
    private final Throwable m4459c() {
        switch (this.f20937a) {
            case 4:
                return new NoSuchElementException();
            case 5:
                return new IllegalStateException("Iterator has failed.");
            default:
                return new IllegalStateException("Unexpected state of the iterator: " + this.f20937a);
        }
    }

    @Override // p110z1.cod
    @dbs
    /* renamed from: a */
    public Object mo4457a(T t, @NotNull Continuation<? super Unit> ccpVar) {
        this.f20938b = t;
        this.f20937a = 3;
        m4461a(ccpVar);
        Object b = cdz.m5528b();
        if (b == cdz.m5528b()) {
            DebugProbes.m5503c(ccpVar);
        }
        return b;
    }

    @Override // p110z1.cod
    @dbs
    /* renamed from: a */
    public Object mo4456a(@NotNull Iterator<? extends T> it, @NotNull Continuation<? super Unit> ccpVar) {
        if (!it.hasNext()) {
            return Unit.f20418a;
        }
        this.f20939c = it;
        this.f20937a = 2;
        m4461a(ccpVar);
        Object b = cdz.m5528b();
        if (b == cdz.m5528b()) {
            DebugProbes.m5503c(ccpVar);
        }
        return b;
    }

    @Override // p110z1.Continuation
    public void resumeWith(@NotNull Object obj) {
        bww.m8764a(obj);
        this.f20937a = 4;
    }

    @Override // p110z1.Continuation
    @NotNull
    public CoroutineContext getContext() {
        return cct.INSTANCE;
    }
}
