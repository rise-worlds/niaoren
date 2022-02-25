package p110z1;

import com.googlecode.tesseract.android.TessBaseAPI;
import java.util.Iterator;

/* compiled from: Iterators.kt */
@Metadata(m8864a = 5, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010(\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a-\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00010\u0005H\u0086\b\u001a\u001f\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0003\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u0087\n\u001a\"\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\b0\u0003\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003¨\u0006\t"}, m8860e = {"forEach", "", TessBaseAPI.f9204e, "", "operation", "Lkotlin/Function1;", "iterator", "withIndex", "Lkotlin/collections/IndexedValue;", "kotlin-stdlib"}, m8859f = "kotlin/collections/CollectionsKt", m8857h = 1)
/* renamed from: z1.bzp */
/* loaded from: classes3.dex */
class bzp extends IteratorsJVM {
    /* JADX WARN: Multi-variable type inference failed */
    @cey
    /* renamed from: b */
    private static final <T> Iterator<T> m6773b(@NotNull Iterator<? extends T> it) {
        cji.m5162f(it, "$this$iterator");
        return it;
    }

    @NotNull
    /* renamed from: a */
    public static final <T> Iterator<IndexedValue<T>> m6775a(@NotNull Iterator<? extends T> it) {
        cji.m5162f(it, "$this$withIndex");
        return new cah(it);
    }

    /* renamed from: a */
    public static final <T> void m6774a(@NotNull Iterator<? extends T> it, @NotNull chd<? super T, Unit> chdVar) {
        cji.m5162f(it, "$this$forEach");
        cji.m5162f(chdVar, "operation");
        while (it.hasNext()) {
            chdVar.invoke((Object) it.next());
        }
    }
}
