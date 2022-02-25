package p110z1;

import java.util.NoSuchElementException;

/* compiled from: ArrayIterators.kt */
@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0017\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\n\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\bH\u0096\u0002J\b\u0010\t\u001a\u00020\nH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000b"}, m8860e = {"Lkotlin/jvm/internal/ArrayShortIterator;", "Lkotlin/collections/ShortIterator;", "array", "", "([S)V", "index", "", "hasNext", "", "nextShort", "", "kotlin-stdlib"})
/* renamed from: z1.cik */
/* loaded from: classes3.dex */
final class cik extends cbc {

    /* renamed from: a */
    private int f20705a;

    /* renamed from: b */
    private final short[] f20706b;

    public cik(@NotNull short[] sArr) {
        cji.m5162f(sArr, "array");
        this.f20706b = sArr;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.f20705a < this.f20706b.length;
    }

    @Override // p110z1.cbc
    /* renamed from: b */
    public short mo5249b() {
        try {
            short[] sArr = this.f20706b;
            int i = this.f20705a;
            this.f20705a = i + 1;
            return sArr[i];
        } catch (ArrayIndexOutOfBoundsException e) {
            this.f20705a--;
            throw new NoSuchElementException(e.getMessage());
        }
    }
}
