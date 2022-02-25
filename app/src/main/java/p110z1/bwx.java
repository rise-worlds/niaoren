package p110z1;

import com.googlecode.tesseract.android.TessBaseAPI;
import java.io.Serializable;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import org.apache.tools.ant.types.selectors.SizeSelector;

/* compiled from: LazyJVM.kt */
@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u00002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0002\u0018\u0000 \u0013*\u0006\b\u0000\u0010\u0001 \u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\u00060\u0003j\u0002`\u0004:\u0001\u0013B\u0013\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006¢\u0006\u0002\u0010\u0007J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010\u0012\u001a\u00020\tH\u0002R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0006X\u0088\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\u00028\u00008VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\r¨\u0006\u0014"}, m8860e = {"Lkotlin/SafePublicationLazyImpl;", TessBaseAPI.f9204e, "Lkotlin/Lazy;", "Ljava/io/Serializable;", "Lkotlin/io/Serializable;", "initializer", "Lkotlin/Function0;", "(Lkotlin/jvm/functions/Function0;)V", "_value", "", "final", SizeSelector.SIZE_KEY, "getValue", "()Ljava/lang/Object;", "isInitialized", "", "toString", "", "writeReplace", "Companion", "kotlin-stdlib"})
/* renamed from: z1.bwx */
/* loaded from: classes3.dex */
final class bwx<T> implements Serializable, bvz<T> {
    public static final C4804a Companion = new C4804a(null);
    private static final AtomicReferenceFieldUpdater<bwx<?>, Object> valueUpdater = AtomicReferenceFieldUpdater.newUpdater(bwx.class, Object.class, "_value");
    private volatile Object _value = bxw.f20392a;

    /* renamed from: final  reason: not valid java name */
    private final Object f23838final = bxw.f20392a;
    private volatile chc<? extends T> initializer;

    public bwx(@NotNull chc<? extends T> chcVar) {
        cji.m5162f(chcVar, "initializer");
        this.initializer = chcVar;
    }

    @Override // p110z1.bvz
    public T getValue() {
        T t = (T) this._value;
        if (t != bxw.f20392a) {
            return t;
        }
        chc<? extends T> chcVar = this.initializer;
        if (chcVar != null) {
            T t2 = (T) chcVar.invoke();
            if (valueUpdater.compareAndSet(this, bxw.f20392a, t2)) {
                this.initializer = null;
                return t2;
            }
        }
        return (T) this._value;
    }

    @Override // p110z1.bvz
    public boolean isInitialized() {
        return this._value != bxw.f20392a;
    }

    @NotNull
    public String toString() {
        return isInitialized() ? String.valueOf(getValue()) : "Lazy value not initialized yet.";
    }

    private final Object writeReplace() {
        return new Lazy(getValue());
    }

    /* compiled from: LazyJVM.kt */
    @Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R^\u0010\u0003\u001aR\u0012\u0014\u0012\u0012\u0012\u0002\b\u0003 \u0006*\b\u0012\u0002\b\u0003\u0018\u00010\u00050\u0005\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u00010\u0001 \u0006*(\u0012\u0014\u0012\u0012\u0012\u0002\b\u0003 \u0006*\b\u0012\u0002\b\u0003\u0018\u00010\u00050\u0005\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u00010\u0001\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0007"}, m8860e = {"Lkotlin/SafePublicationLazyImpl$Companion;", "", "()V", "valueUpdater", "Ljava/util/concurrent/atomic/AtomicReferenceFieldUpdater;", "Lkotlin/SafePublicationLazyImpl;", "kotlin.jvm.PlatformType", "kotlin-stdlib"})
    /* renamed from: z1.bwx$a */
    /* loaded from: classes3.dex */
    public static final class C4804a {
        private C4804a() {
        }

        public /* synthetic */ C4804a(DefaultConstructorMarker civVar) {
            this();
        }
    }
}
