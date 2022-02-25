package p110z1;

import java.util.NoSuchElementException;

/* compiled from: ULongRange.kt */
@bwy(m8750a = "1.3")
@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0003\u0018\u00002\u00020\u0001B \u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006ø\u0001\u0000¢\u0006\u0002\u0010\u0007J\t\u0010\n\u001a\u00020\u000bH\u0096\u0002J\u0010\u0010\r\u001a\u00020\u0003H\u0016ø\u0001\u0000¢\u0006\u0002\u0010\u000eR\u0013\u0010\b\u001a\u00020\u0003X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0013\u0010\f\u001a\u00020\u0003X\u0082\u000eø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\tR\u0013\u0010\u0005\u001a\u00020\u0003X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\t\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000f"}, m8860e = {"Lkotlin/ranges/ULongProgressionIterator;", "Lkotlin/collections/ULongIterator;", "first", "Lkotlin/ULong;", "last", "step", "", "(JJJLkotlin/jvm/internal/DefaultConstructorMarker;)V", "finalElement", "J", "hasNext", "", "next", "nextULong", "()J", "kotlin-stdlib"})
@Unsigned
/* renamed from: z1.cmq */
/* loaded from: classes3.dex */
final class cmq extends cbm {

    /* renamed from: a */
    private final long f20870a;

    /* renamed from: b */
    private boolean f20871b;

    /* renamed from: c */
    private final long f20872c;

    /* renamed from: d */
    private long f20873d;

    private cmq(long j, long j2, long j3) {
        this.f20870a = j2;
        boolean z = true;
        if (j3 <= 0 ? UnsignedUtils.m8337a(j, j2) < 0 : UnsignedUtils.m8337a(j, j2) > 0) {
            z = false;
        }
        this.f20871b = z;
        this.f20872c = ULong.m8548b(j3);
        this.f20873d = !this.f20871b ? this.f20870a : j;
    }

    public /* synthetic */ cmq(long j, long j2, long j3, DefaultConstructorMarker civVar) {
        this(j, j2, j3);
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.f20871b;
    }

    @Override // p110z1.cbm
    /* renamed from: a */
    public long mo4670a() {
        long j = this.f20873d;
        if (j != this.f20870a) {
            this.f20873d = ULong.m8548b(this.f20872c + j);
        } else if (this.f20871b) {
            this.f20871b = false;
        } else {
            throw new NoSuchElementException();
        }
        return j;
    }
}
