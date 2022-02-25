package p110z1;

import com.googlecode.tesseract.android.TessBaseAPI;
import java.util.Iterator;

/* compiled from: Iterables.kt */
@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010(\n\u0002\b\u0003\b\u0000\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u00030\u0002B\u0019\u0012\u0012\u0010\u0004\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00060\u0005¢\u0006\u0002\u0010\u0007J\u0015\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00030\u0006H\u0096\u0002R\u001a\u0010\u0004\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, m8860e = {"Lkotlin/collections/IndexingIterable;", TessBaseAPI.f9204e, "", "Lkotlin/collections/IndexedValue;", "iteratorFactory", "Lkotlin/Function0;", "", "(Lkotlin/jvm/functions/Function0;)V", "iterator", "kotlin-stdlib"})
/* renamed from: z1.cag */
/* loaded from: classes3.dex */
public final class cag<T> implements Iterable<IndexedValue<? extends T>>, KMarkers {

    /* renamed from: a */
    private final chc<Iterator<T>> f20488a;

    /* JADX WARN: Multi-variable type inference failed */
    public cag(@NotNull chc<? extends Iterator<? extends T>> chcVar) {
        cji.m5162f(chcVar, "iteratorFactory");
        this.f20488a = chcVar;
    }

    @Override // java.lang.Iterable
    @NotNull
    public Iterator<IndexedValue<T>> iterator() {
        return new cah(this.f20488a.invoke());
    }
}
