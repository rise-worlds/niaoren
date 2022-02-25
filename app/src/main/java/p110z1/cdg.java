package p110z1;

import com.googlecode.tesseract.android.TessBaseAPI;
import com.stripe.android.CustomerSession;
import com.stripe.android.view.ShippingInfoWidget;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.apache.tools.ant.types.selectors.SizeSelector;

/* compiled from: SequenceBuilder.kt */
@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000:\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010(\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000e\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u00032\b\u0012\u0004\u0012\u00020\u00050\u0004B\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0016\u001a\u00020\u0017H\u0002J\t\u0010\u0018\u001a\u00020\u0019H\u0096\u0002J\u000e\u0010\u001a\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010\u001bJ\r\u0010\u001c\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010\u001bJ\u0015\u0010\u001d\u001a\u00020\u00052\u0006\u0010\u001e\u001a\u00020\u0005H\u0016¢\u0006\u0002\u0010\u001fJ\u0010\u0010 \u001a\u00020\u00052\u0006\u0010!\u001a\u00020\u0017H\u0016J\u0019\u0010\"\u001a\u00020\u00052\u0006\u0010\u001e\u001a\u00028\u0000H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010#J\u001f\u0010$\u001a\u00020\u00052\f\u0010%\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010&R\u0014\u0010\u0007\u001a\u00020\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0016\u0010\u000b\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u0012\u0010\u0011\u001a\u0004\u0018\u00018\u0000X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0012R\u0012\u0010\u0013\u001a\u00060\u0014j\u0002`\u0015X\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\t¨\u0006'"}, m8860e = {"Lkotlin/coroutines/experimental/SequenceBuilderIterator;", TessBaseAPI.f9204e, "Lkotlin/coroutines/experimental/SequenceBuilder;", "", "Lkotlin/coroutines/experimental/Continuation;", "", "()V", "context", "Lkotlin/coroutines/experimental/CoroutineContext;", "getContext", "()Lkotlin/coroutines/experimental/CoroutineContext;", "nextIterator", "nextStep", "getNextStep", "()Lkotlin/coroutines/experimental/Continuation;", "setNextStep", "(Lkotlin/coroutines/experimental/Continuation;)V", "nextValue", "Ljava/lang/Object;", ShippingInfoWidget.f12562e, "", "Lkotlin/coroutines/experimental/State;", "exceptionalState", "", "hasNext", "", "next", "()Ljava/lang/Object;", "nextNotReady", "resume", SizeSelector.SIZE_KEY, "(Lkotlin/Unit;)V", "resumeWithException", CustomerSession.f11798b, "yield", "(Ljava/lang/Object;Lkotlin/coroutines/experimental/Continuation;)Ljava/lang/Object;", "yieldAll", "iterator", "(Ljava/util/Iterator;Lkotlin/coroutines/experimental/Continuation;)Ljava/lang/Object;", "kotlin-stdlib-coroutines"})
/* renamed from: z1.cdg */
/* loaded from: classes3.dex */
final class cdg<T> extends SequenceBuilder<T> implements Iterator<T>, Coroutines<Unit>, KMarkers {

    /* renamed from: a */
    private int f20573a;

    /* renamed from: b */
    private T f20574b;

    /* renamed from: c */
    private Iterator<? extends T> f20575c;
    @dbs

    /* renamed from: d */
    private Coroutines<? super Unit> f20576d;

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @dbs
    /* renamed from: a */
    public final Coroutines<Unit> m5592a() {
        return this.f20576d;
    }

    /* renamed from: a */
    public final void m5588a(@dbs Coroutines<? super Unit> ccyVar) {
        this.f20576d = ccyVar;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // java.util.Iterator
    public boolean hasNext() {
        while (true) {
            switch (this.f20573a) {
                case 0:
                    break;
                case 1:
                    Iterator<? extends T> it = this.f20575c;
                    if (it == null) {
                        cji.m5196a();
                    }
                    if (!it.hasNext()) {
                        this.f20575c = null;
                        break;
                    } else {
                        this.f20573a = 2;
                        return true;
                    }
                case 2:
                case 3:
                    return true;
                case 4:
                    return false;
                default:
                    throw m5586c();
            }
            this.f20573a = 5;
            Coroutines<? super Unit> ccyVar = this.f20576d;
            if (ccyVar == null) {
                cji.m5196a();
            }
            this.f20576d = null;
            ccyVar.resume(Unit.f20418a);
        }
    }

    @Override // java.util.Iterator
    public T next() {
        switch (this.f20573a) {
            case 0:
            case 1:
                return m5587b();
            case 2:
                this.f20573a = 1;
                Iterator<? extends T> it = this.f20575c;
                if (it == null) {
                    cji.m5196a();
                }
                return (T) it.next();
            case 3:
                this.f20573a = 0;
                T t = this.f20574b;
                this.f20574b = null;
                return t;
            default:
                throw m5586c();
        }
    }

    /* renamed from: b */
    private final T m5587b() {
        if (hasNext()) {
            return next();
        }
        throw new NoSuchElementException();
    }

    /* renamed from: c */
    private final Throwable m5586c() {
        switch (this.f20573a) {
            case 4:
                return new NoSuchElementException();
            case 5:
                return new IllegalStateException("Iterator has failed.");
            default:
                return new IllegalStateException("Unexpected state of the iterator: " + this.f20573a);
        }
    }

    @Override // p110z1.SequenceBuilder
    @dbs
    /* renamed from: a */
    public Object mo5591a(T t, @NotNull Coroutines<? super Unit> ccyVar) {
        this.f20574b = t;
        this.f20573a = 3;
        m5588a(CoroutineIntrinsics.m5570a(ccyVar));
        return cdj.m5579b();
    }

    @Override // p110z1.SequenceBuilder
    @dbs
    /* renamed from: a */
    public Object mo5590a(@NotNull Iterator<? extends T> it, @NotNull Coroutines<? super Unit> ccyVar) {
        if (!it.hasNext()) {
            return Unit.f20418a;
        }
        this.f20575c = it;
        this.f20573a = 2;
        m5588a(CoroutineIntrinsics.m5570a(ccyVar));
        return cdj.m5579b();
    }

    /* renamed from: a */
    public void resume(@NotNull Unit bydVar) {
        cji.m5162f(bydVar, SizeSelector.SIZE_KEY);
        this.f20573a = 4;
    }

    @Override // p110z1.Coroutines
    public void resumeWithException(@NotNull Throwable th) {
        cji.m5162f(th, CustomerSession.f11798b);
        throw th;
    }

    @Override // p110z1.Coroutines
    @NotNull
    public cda getContext() {
        return cdc.f20565a;
    }
}
