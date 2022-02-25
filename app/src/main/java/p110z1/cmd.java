package p110z1;

import java.util.NoSuchElementException;

/* compiled from: ProgressionIterators.kt */
@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\b\u001a\u00020\tH\u0096\u0002J\b\u0010\r\u001a\u00020\u0003H\u0016R\u000e\u0010\u0007\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u000e"}, m8860e = {"Lkotlin/ranges/IntProgressionIterator;", "Lkotlin/collections/IntIterator;", "first", "", "last", "step", "(III)V", "finalElement", "hasNext", "", "next", "getStep", "()I", "nextInt", "kotlin-stdlib"})
/* renamed from: z1.cmd */
/* loaded from: classes3.dex */
public final class cmd extends cai {

    /* renamed from: a */
    private final int f20840a;

    /* renamed from: b */
    private boolean f20841b;

    /* renamed from: c */
    private int f20842c;

    /* renamed from: d */
    private final int f20843d;

    public cmd(int i, int i2, int i3) {
        this.f20843d = i3;
        this.f20840a = i2;
        boolean z = true;
        if (this.f20843d <= 0 ? i < i2 : i > i2) {
            z = false;
        }
        this.f20841b = z;
        this.f20842c = !this.f20841b ? this.f20840a : i;
    }

    /* renamed from: c */
    public final int m4827c() {
        return this.f20843d;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.f20841b;
    }

    @Override // p110z1.cai
    /* renamed from: b */
    public int mo4828b() {
        int i = this.f20842c;
        if (i != this.f20840a) {
            this.f20842c = this.f20843d + i;
        } else if (this.f20841b) {
            this.f20841b = false;
        } else {
            throw new NoSuchElementException();
        }
        return i;
    }
}
