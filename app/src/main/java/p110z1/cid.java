package p110z1;

import java.util.NoSuchElementException;

/* compiled from: ArrayIterators.kt */
@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0013\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0006\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\bH\u0096\u0002J\b\u0010\t\u001a\u00020\nH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000b"}, m8860e = {"Lkotlin/jvm/internal/ArrayDoubleIterator;", "Lkotlin/collections/DoubleIterator;", "array", "", "([D)V", "index", "", "hasNext", "", "nextDouble", "", "kotlin-stdlib"})
/* renamed from: z1.cid */
/* loaded from: classes3.dex */
final class cid extends bzv {

    /* renamed from: a */
    private int f20695a;

    /* renamed from: b */
    private final double[] f20696b;

    public cid(@NotNull double[] dArr) {
        cji.m5162f(dArr, "array");
        this.f20696b = dArr;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.f20695a < this.f20696b.length;
    }

    @Override // p110z1.bzv
    /* renamed from: b */
    public double mo5261b() {
        try {
            double[] dArr = this.f20696b;
            int i = this.f20695a;
            this.f20695a = i + 1;
            return dArr[i];
        } catch (ArrayIndexOutOfBoundsException e) {
            this.f20695a--;
            throw new NoSuchElementException(e.getMessage());
        }
    }
}
