package p110z1;

import java.util.NoSuchElementException;

/* compiled from: ArrayIterators.kt */
@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0016\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\bH\u0096\u0002J\b\u0010\t\u001a\u00020\nH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000b"}, m8860e = {"Lkotlin/jvm/internal/ArrayLongIterator;", "Lkotlin/collections/LongIterator;", "array", "", "([J)V", "index", "", "hasNext", "", "nextLong", "", "kotlin-stdlib"})
/* renamed from: z1.cij */
/* loaded from: classes3.dex */
final class cij extends caj {

    /* renamed from: a */
    private int f20703a;

    /* renamed from: b */
    private final long[] f20704b;

    public cij(@NotNull long[] jArr) {
        cji.m5162f(jArr, "array");
        this.f20704b = jArr;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.f20703a < this.f20704b.length;
    }

    @Override // p110z1.caj
    /* renamed from: b */
    public long mo4816b() {
        try {
            long[] jArr = this.f20704b;
            int i = this.f20703a;
            this.f20703a = i + 1;
            return jArr[i];
        } catch (ArrayIndexOutOfBoundsException e) {
            this.f20703a--;
            throw new NoSuchElementException(e.getMessage());
        }
    }
}
