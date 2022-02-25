package p110z1;

import java.util.NoSuchElementException;

/* compiled from: ArrayIterators.kt */
@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0019\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\f\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\bH\u0096\u0002J\b\u0010\t\u001a\u00020\nH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000b"}, m8860e = {"Lkotlin/jvm/internal/ArrayCharIterator;", "Lkotlin/collections/CharIterator;", "array", "", "([C)V", "index", "", "hasNext", "", "nextChar", "", "kotlin-stdlib"})
/* renamed from: z1.cic */
/* loaded from: classes3.dex */
final class cic extends bzj {

    /* renamed from: a */
    private int f20693a;

    /* renamed from: b */
    private final char[] f20694b;

    public cic(@NotNull char[] cArr) {
        cji.m5162f(cArr, "array");
        this.f20694b = cArr;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.f20693a < this.f20694b.length;
    }

    @Override // p110z1.bzj
    /* renamed from: b */
    public char mo3838b() {
        try {
            char[] cArr = this.f20694b;
            int i = this.f20693a;
            this.f20693a = i + 1;
            return cArr[i];
        } catch (ArrayIndexOutOfBoundsException e) {
            this.f20693a--;
            throw new NoSuchElementException(e.getMessage());
        }
    }
}
