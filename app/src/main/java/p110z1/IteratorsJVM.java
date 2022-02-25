package p110z1;

import com.googlecode.tesseract.android.TessBaseAPI;
import java.util.Enumeration;
import java.util.Iterator;

@Metadata(m8864a = 5, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u000e\n\u0000\n\u0002\u0010(\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u001f\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u0086\u0002¨\u0006\u0004"}, m8860e = {"iterator", "", TessBaseAPI.f9204e, "Ljava/util/Enumeration;", "kotlin-stdlib"}, m8859f = "kotlin/collections/CollectionsKt", m8857h = 1)
/* renamed from: z1.bzo */
/* loaded from: classes3.dex */
class IteratorsJVM extends Iterables {

    /* compiled from: IteratorsJVM.kt */
    @Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u0013\n\u0000\n\u0002\u0010(\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\t\u0010\u0002\u001a\u00020\u0003H\u0096\u0002J\u000e\u0010\u0004\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010\u0005¨\u0006\u0006"}, m8860e = {"kotlin/collections/CollectionsKt__IteratorsJVMKt$iterator$1", "", "hasNext", "", "next", "()Ljava/lang/Object;", "kotlin-stdlib"})
    /* renamed from: z1.bzo$a */
    /* loaded from: classes3.dex */
    public static final class C4867a implements Iterator<T>, KMarkers {

        /* renamed from: a */
        final /* synthetic */ Enumeration f20472a;

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        C4867a(Enumeration<T> enumeration) {
            this.f20472a = enumeration;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.f20472a.hasMoreElements();
        }

        /* JADX WARN: Type inference failed for: r0v1, types: [T, java.lang.Object] */
        @Override // java.util.Iterator
        public T next() {
            return this.f20472a.nextElement();
        }
    }

    @NotNull
    /* renamed from: a */
    public static final <T> Iterator<T> m6776a(@NotNull Enumeration<T> enumeration) {
        cji.m5162f(enumeration, "$this$iterator");
        return new C4867a(enumeration);
    }
}
