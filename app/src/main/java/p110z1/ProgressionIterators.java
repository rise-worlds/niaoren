package p110z1;

import java.util.NoSuchElementException;

@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\f\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\t\u0010\t\u001a\u00020\nH\u0096\u0002J\b\u0010\u000e\u001a\u00020\u0003H\u0016R\u000e\u0010\b\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u000f"}, m8860e = {"Lkotlin/ranges/CharProgressionIterator;", "Lkotlin/collections/CharIterator;", "first", "", "last", "step", "", "(CCI)V", "finalElement", "hasNext", "", "next", "getStep", "()I", "nextChar", "kotlin-stdlib"})
/* renamed from: z1.clv */
/* loaded from: classes3.dex */
public final class ProgressionIterators extends bzj {

    /* renamed from: a */
    private final int f20821a;

    /* renamed from: b */
    private boolean f20822b;

    /* renamed from: c */
    private int f20823c;

    /* renamed from: d */
    private final int f20824d;

    public ProgressionIterators(char c, char c2, int i) {
        this.f20824d = i;
        this.f20821a = c2;
        boolean z = true;
        if (this.f20824d <= 0 ? c < c2 : c > c2) {
            z = false;
        }
        this.f20822b = z;
        this.f20823c = (!this.f20822b ? this.f20821a : c) == 1 ? 1 : 0;
    }

    /* renamed from: c */
    public final int m4856c() {
        return this.f20824d;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.f20822b;
    }

    @Override // p110z1.bzj
    /* renamed from: b */
    public char mo3838b() {
        int i = this.f20823c;
        if (i != this.f20821a) {
            this.f20823c = this.f20824d + i;
        } else if (this.f20822b) {
            this.f20822b = false;
        } else {
            throw new NoSuchElementException();
        }
        return (char) i;
    }
}
