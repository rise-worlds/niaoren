package p110z1;

import java.util.NoSuchElementException;

/* compiled from: ArrayIterators.kt */
@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\bH\u0096\u0002J\b\u0010\t\u001a\u00020\u0006H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\n"}, m8860e = {"Lkotlin/jvm/internal/ArrayIntIterator;", "Lkotlin/collections/IntIterator;", "array", "", "([I)V", "index", "", "hasNext", "", "nextInt", "kotlin-stdlib"})
/* renamed from: z1.cif */
/* loaded from: classes3.dex */
final class cif extends cai {

    /* renamed from: a */
    private int f20699a;

    /* renamed from: b */
    private final int[] f20700b;

    public cif(@NotNull int[] iArr) {
        cji.m5162f(iArr, "array");
        this.f20700b = iArr;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.f20699a < this.f20700b.length;
    }

    @Override // p110z1.cai
    /* renamed from: b */
    public int mo4828b() {
        try {
            int[] iArr = this.f20700b;
            int i = this.f20699a;
            this.f20699a = i + 1;
            return iArr[i];
        } catch (ArrayIndexOutOfBoundsException e) {
            this.f20699a--;
            throw new NoSuchElementException(e.getMessage());
        }
    }
}
