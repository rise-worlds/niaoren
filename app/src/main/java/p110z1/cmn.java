package p110z1;

import java.util.NoSuchElementException;

/* compiled from: UIntRange.kt */
@bwy(m8750a = "1.3")
@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0003\u0018\u00002\u00020\u0001B \u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006ø\u0001\u0000¢\u0006\u0002\u0010\u0007J\t\u0010\n\u001a\u00020\u000bH\u0096\u0002J\u0010\u0010\r\u001a\u00020\u0003H\u0016ø\u0001\u0000¢\u0006\u0002\u0010\u000eR\u0013\u0010\b\u001a\u00020\u0003X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0013\u0010\f\u001a\u00020\u0003X\u0082\u000eø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\tR\u0013\u0010\u0005\u001a\u00020\u0003X\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\t\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000f"}, m8860e = {"Lkotlin/ranges/UIntProgressionIterator;", "Lkotlin/collections/UIntIterator;", "first", "Lkotlin/UInt;", "last", "step", "", "(IIILkotlin/jvm/internal/DefaultConstructorMarker;)V", "finalElement", "I", "hasNext", "", "next", "nextUInt", "()I", "kotlin-stdlib"})
@Unsigned
/* renamed from: z1.cmn */
/* loaded from: classes3.dex */
final class cmn extends cbl {

    /* renamed from: a */
    private final int f20860a;

    /* renamed from: b */
    private boolean f20861b;

    /* renamed from: c */
    private final int f20862c;

    /* renamed from: d */
    private int f20863d;

    private cmn(int i, int i2, int i3) {
        this.f20860a = i2;
        boolean z = true;
        if (i3 <= 0 ? UnsignedUtils.m8340a(i, i2) < 0 : UnsignedUtils.m8340a(i, i2) > 0) {
            z = false;
        }
        this.f20861b = z;
        this.f20862c = UInt.m8628b(i3);
        this.f20863d = !this.f20861b ? this.f20860a : i;
    }

    public /* synthetic */ cmn(int i, int i2, int i3, DefaultConstructorMarker civVar) {
        this(i, i2, i3);
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.f20861b;
    }

    @Override // p110z1.cbl
    /* renamed from: a */
    public int mo4681a() {
        int i = this.f20863d;
        if (i != this.f20860a) {
            this.f20863d = UInt.m8628b(this.f20862c + i);
        } else if (this.f20861b) {
            this.f20861b = false;
        } else {
            throw new NoSuchElementException();
        }
        return i;
    }
}
