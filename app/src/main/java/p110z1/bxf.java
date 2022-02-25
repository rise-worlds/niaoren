package p110z1;

import com.googlecode.tesseract.android.TessBaseAPI;
import java.io.Serializable;
import org.apache.tools.ant.taskdefs.optional.clearcase.ClearCase;
import org.apache.tools.ant.types.selectors.SizeSelector;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: LazyJVM.kt */
@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u00000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0002\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\u00060\u0003j\u0002`\u0004B\u001f\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\tJ\b\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010\u0012\u001a\u00020\bH\u0002R\u0010\u0010\n\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0006X\u0088\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\u00028\u00008VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\r¨\u0006\u0013"}, m8860e = {"Lkotlin/SynchronizedLazyImpl;", TessBaseAPI.f9204e, "Lkotlin/Lazy;", "Ljava/io/Serializable;", "Lkotlin/io/Serializable;", "initializer", "Lkotlin/Function0;", ClearCase.COMMAND_LOCK, "", "(Lkotlin/jvm/functions/Function0;Ljava/lang/Object;)V", "_value", SizeSelector.SIZE_KEY, "getValue", "()Ljava/lang/Object;", "isInitialized", "", "toString", "", "writeReplace", "kotlin-stdlib"})
/* renamed from: z1.bxf */
/* loaded from: classes3.dex */
public final class bxf<T> implements Serializable, bvz<T> {
    private volatile Object _value;
    private chc<? extends T> initializer;
    private final Object lock;

    public bxf(@NotNull chc<? extends T> chcVar, @dbs Object obj) {
        cji.m5162f(chcVar, "initializer");
        this.initializer = chcVar;
        this._value = bxw.f20392a;
        this.lock = obj == null ? this : obj;
    }

    public /* synthetic */ bxf(chc chcVar, Object obj, int i, DefaultConstructorMarker civVar) {
        this(chcVar, (i & 2) != 0 ? null : obj);
    }

    @Override // p110z1.bvz
    public T getValue() {
        T t;
        T t2 = (T) this._value;
        if (t2 != bxw.f20392a) {
            return t2;
        }
        synchronized (this.lock) {
            t = (T) this._value;
            if (t == bxw.f20392a) {
                chc<? extends T> chcVar = this.initializer;
                if (chcVar == null) {
                    cji.m5196a();
                }
                t = (T) chcVar.invoke();
                this._value = t;
                this.initializer = null;
            }
        }
        return t;
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
}
