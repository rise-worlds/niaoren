package p110z1;

import org.apache.tools.ant.types.selectors.SizeSelector;

@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\f\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000 \u00152\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u0002:\u0001\u0015B\u0015\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\u0011\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0003H\u0096\u0002J\u0013\u0010\r\u001a\u00020\u000b2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0096\u0002J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010\u0012\u001a\u00020\u000bH\u0016J\b\u0010\u0013\u001a\u00020\u0014H\u0016R\u0014\u0010\u0005\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\u0004\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\b¨\u0006\u0016"}, m8860e = {"Lkotlin/ranges/CharRange;", "Lkotlin/ranges/CharProgression;", "Lkotlin/ranges/ClosedRange;", "", "start", "endInclusive", "(CC)V", "getEndInclusive", "()Ljava/lang/Character;", "getStart", "contains", "", SizeSelector.SIZE_KEY, "equals", "other", "", "hashCode", "", "isEmpty", "toString", "", "Companion", "kotlin-stdlib"})
/* renamed from: z1.clw */
/* loaded from: classes3.dex */
public final class Ranges extends Progressions implements Range<Character> {

    /* renamed from: b */
    public static final C4983a f20825b = new C4983a(null);
    @NotNull

    /* renamed from: c */
    private static final Ranges f20826c = new Ranges((char) 1, (char) 0);

    public Ranges(char c, char c2) {
        super(c, c2, 1);
    }

    @Override // p110z1.Range
    /* renamed from: a */
    public /* synthetic */ boolean mo4668a(Character ch) {
        return m4855a(ch.charValue());
    }

    @NotNull
    /* renamed from: f */
    public Character mo4665g() {
        return Character.valueOf(m4861a());
    }

    @NotNull
    /* renamed from: h */
    public Character mo4663i() {
        return Character.valueOf(m4860b());
    }

    /* renamed from: a */
    public boolean m4855a(char c) {
        return m4861a() <= c && c <= m4860b();
    }

    @Override // p110z1.Progressions, p110z1.Range
    /* renamed from: e */
    public boolean mo4667e() {
        return m4861a() > m4860b();
    }

    @Override // p110z1.Progressions
    public boolean equals(@dbs Object obj) {
        if (obj instanceof Ranges) {
            if (!mo4667e() || !((Ranges) obj).mo4667e()) {
                Ranges clwVar = (Ranges) obj;
                if (!(m4861a() == clwVar.m4861a() && m4860b() == clwVar.m4860b())) {
                }
            }
            return true;
        }
        return false;
    }

    @Override // p110z1.Progressions
    public int hashCode() {
        if (mo4667e()) {
            return -1;
        }
        return (m4861a() * 31) + m4860b();
    }

    @Override // p110z1.Progressions
    @NotNull
    public String toString() {
        return m4861a() + ".." + m4860b();
    }

    /* compiled from: Ranges.kt */
    @Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, m8860e = {"Lkotlin/ranges/CharRange$Companion;", "", "()V", "EMPTY", "Lkotlin/ranges/CharRange;", "getEMPTY", "()Lkotlin/ranges/CharRange;", "kotlin-stdlib"})
    /* renamed from: z1.clw$a */
    /* loaded from: classes3.dex */
    public static final class C4983a {
        private C4983a() {
        }

        public /* synthetic */ C4983a(DefaultConstructorMarker civVar) {
            this();
        }

        @NotNull
        /* renamed from: a */
        public final Ranges m4851a() {
            return Ranges.f20826c;
        }
    }
}
