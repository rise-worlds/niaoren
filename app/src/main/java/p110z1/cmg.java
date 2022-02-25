package p110z1;

import java.util.NoSuchElementException;

/* compiled from: ProgressionIterators.kt */
@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\b\u001a\u00020\tH\u0096\u0002J\b\u0010\r\u001a\u00020\u0003H\u0016R\u000e\u0010\u0007\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u000e"}, m8860e = {"Lkotlin/ranges/LongProgressionIterator;", "Lkotlin/collections/LongIterator;", "first", "", "last", "step", "(JJJ)V", "finalElement", "hasNext", "", "next", "getStep", "()J", "nextLong", "kotlin-stdlib"})
/* renamed from: z1.cmg */
/* loaded from: classes3.dex */
public final class cmg extends caj {

    /* renamed from: a */
    private final long f20850a;

    /* renamed from: b */
    private boolean f20851b;

    /* renamed from: c */
    private long f20852c;

    /* renamed from: d */
    private final long f20853d;

    public cmg(long j, long j2, long j3) {
        this.f20853d = j3;
        this.f20850a = j2;
        boolean z = true;
        if (this.f20853d <= 0 ? j < j2 : j > j2) {
            z = false;
        }
        this.f20851b = z;
        this.f20852c = !this.f20851b ? this.f20850a : j;
    }

    /* renamed from: c */
    public final long m4815c() {
        return this.f20853d;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.f20851b;
    }

    @Override // p110z1.caj
    /* renamed from: b */
    public long mo4816b() {
        long j = this.f20852c;
        if (j != this.f20850a) {
            this.f20852c = this.f20853d + j;
        } else if (this.f20851b) {
            this.f20851b = false;
        } else {
            throw new NoSuchElementException();
        }
        return j;
    }
}
