package p110z1;

import com.googlecode.tesseract.android.TessBaseAPI;
import org.apache.tools.ant.types.selectors.SizeSelector;

@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\u00020\u0002B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00028\u0000¢\u0006\u0002\u0010\u0006J\t\u0010\f\u001a\u00020\u0004HÆ\u0003J\u000e\u0010\r\u001a\u00028\u0000HÆ\u0003¢\u0006\u0002\u0010\nJ(\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00028\u0000HÆ\u0001¢\u0006\u0002\u0010\u000fJ\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0002HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0004HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0005\u001a\u00028\u0000¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\n¨\u0006\u0016"}, m8860e = {"Lkotlin/collections/IndexedValue;", TessBaseAPI.f9204e, "", "index", "", SizeSelector.SIZE_KEY, "(ILjava/lang/Object;)V", "getIndex", "()I", "getValue", "()Ljava/lang/Object;", "Ljava/lang/Object;", "component1", "component2", "copy", "(ILjava/lang/Object;)Lkotlin/collections/IndexedValue;", "equals", "", "other", "hashCode", "toString", "", "kotlin-stdlib"})
/* renamed from: z1.caf */
/* loaded from: classes3.dex */
public final class IndexedValue<T> {

    /* renamed from: a */
    private final int f20486a;

    /* renamed from: b */
    private final T f20487b;

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: a */
    public static /* synthetic */ IndexedValue m6496a(IndexedValue cafVar, int i, Object obj, int i2, Object obj2) {
        if ((i2 & 1) != 0) {
            i = cafVar.f20486a;
        }
        if ((i2 & 2) != 0) {
            obj = cafVar.f20487b;
        }
        return cafVar.m6497a(i, obj);
    }

    @NotNull
    /* renamed from: a */
    public final IndexedValue<T> m6497a(int i, T t) {
        return new IndexedValue<>(i, t);
    }

    /* renamed from: c */
    public final int m6494c() {
        return this.f20486a;
    }

    /* renamed from: d */
    public final T m6493d() {
        return this.f20487b;
    }

    public boolean equals(@dbs Object obj) {
        if (this != obj) {
            if (obj instanceof IndexedValue) {
                IndexedValue cafVar = (IndexedValue) obj;
                if (!(this.f20486a == cafVar.f20486a) || !cji.m5184a(this.f20487b, cafVar.f20487b)) {
                }
            }
            return false;
        }
        return true;
    }

    public int hashCode() {
        int i = this.f20486a * 31;
        T t = this.f20487b;
        return i + (t != null ? t.hashCode() : 0);
    }

    @NotNull
    public String toString() {
        return "IndexedValue(index=" + this.f20486a + ", value=" + this.f20487b + ")";
    }

    public IndexedValue(int i, T t) {
        this.f20486a = i;
        this.f20487b = t;
    }

    /* renamed from: a */
    public final int m6498a() {
        return this.f20486a;
    }

    /* renamed from: b */
    public final T m6495b() {
        return this.f20487b;
    }
}
