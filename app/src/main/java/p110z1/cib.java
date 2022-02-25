package p110z1;

import java.util.NoSuchElementException;

/* compiled from: ArrayIterators.kt */
@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0005\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\bH\u0096\u0002J\b\u0010\t\u001a\u00020\nH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000b"}, m8860e = {"Lkotlin/jvm/internal/ArrayByteIterator;", "Lkotlin/collections/ByteIterator;", "array", "", "([B)V", "index", "", "hasNext", "", "nextByte", "", "kotlin-stdlib"})
/* renamed from: z1.cib */
/* loaded from: classes3.dex */
final class cib extends bzi {

    /* renamed from: a */
    private int f20691a;

    /* renamed from: b */
    private final byte[] f20692b;

    public cib(@NotNull byte[] bArr) {
        cji.m5162f(bArr, "array");
        this.f20692b = bArr;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.f20691a < this.f20692b.length;
    }

    @Override // p110z1.bzi
    /* renamed from: b */
    public byte mo5262b() {
        try {
            byte[] bArr = this.f20692b;
            int i = this.f20691a;
            this.f20691a = i + 1;
            return bArr[i];
        } catch (ArrayIndexOutOfBoundsException e) {
            this.f20691a--;
            throw new NoSuchElementException(e.getMessage());
        }
    }
}
