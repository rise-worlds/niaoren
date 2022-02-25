package p110z1;

import java.util.Iterator;

/* compiled from: Iterators.kt */
@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010(\n\u0002\u0010\u0005\n\u0002\b\u0005\b&\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u0002H\u0086\u0002¢\u0006\u0002\u0010\u0005J\b\u0010\u0006\u001a\u00020\u0002H&¨\u0006\u0007"}, m8860e = {"Lkotlin/collections/ByteIterator;", "", "", "()V", "next", "()Ljava/lang/Byte;", "nextByte", "kotlin-stdlib"})
/* renamed from: z1.bzi */
/* loaded from: classes3.dex */
public abstract class bzi implements Iterator<Byte>, KMarkers {
    /* renamed from: b */
    public abstract byte mo5262b();

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @NotNull
    /* renamed from: a */
    public final Byte next() {
        return Byte.valueOf(mo5262b());
    }
}
