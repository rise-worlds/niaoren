package p110z1;

import java.util.List;
import org.apache.tools.ant.types.selectors.SizeSelector;

/* compiled from: MatchResult.kt */
@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\bf\u0018\u00002\u00020\u0001:\u0001\u0017J\n\u0010\u0016\u001a\u0004\u0018\u00010\u0000H&R\u0014\u0010\u0002\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0018\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0012\u0010\u000b\u001a\u00020\fX¦\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0012\u0010\u000f\u001a\u00020\u0010X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0012\u0010\u0013\u001a\u00020\bX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015¨\u0006\u0018"}, m8860e = {"Lkotlin/text/MatchResult;", "", "destructured", "Lkotlin/text/MatchResult$Destructured;", "getDestructured", "()Lkotlin/text/MatchResult$Destructured;", "groupValues", "", "", "getGroupValues", "()Ljava/util/List;", "groups", "Lkotlin/text/MatchGroupCollection;", "getGroups", "()Lkotlin/text/MatchGroupCollection;", "range", "Lkotlin/ranges/IntRange;", "getRange", "()Lkotlin/ranges/IntRange;", SizeSelector.SIZE_KEY, "getValue", "()Ljava/lang/String;", "next", "Destructured", "kotlin-stdlib"})
/* renamed from: z1.cpf */
/* loaded from: classes3.dex */
public interface cpf {
    @NotNull
    /* renamed from: a */
    cme mo4184a();

    @NotNull
    /* renamed from: b */
    String mo4182b();

    @NotNull
    /* renamed from: c */
    MatchResult mo4181c();

    @NotNull
    /* renamed from: d */
    List<String> mo4180d();

    @NotNull
    /* renamed from: e */
    C5070b mo4179e();

    @dbs
    /* renamed from: f */
    cpf mo4178f();

    /* compiled from: MatchResult.kt */
    @Metadata(m8864a = 3, m8863b = {1, 1, 15}, m8862c = {1, 0, 3})
    /* renamed from: z1.cpf$a */
    /* loaded from: classes3.dex */
    public static final class C5069a {
        @NotNull
        /* renamed from: a */
        public static C5070b m4197a(cpf cpfVar) {
            return new C5070b(cpfVar);
        }
    }

    /* compiled from: MatchResult.kt */
    @Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010 \n\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\bH\u0087\nJ\t\u0010\t\u001a\u00020\bH\u0087\nJ\t\u0010\n\u001a\u00020\bH\u0087\nJ\t\u0010\u000b\u001a\u00020\bH\u0087\nJ\t\u0010\f\u001a\u00020\bH\u0087\nJ\t\u0010\r\u001a\u00020\bH\u0087\nJ\t\u0010\u000e\u001a\u00020\bH\u0087\nJ\t\u0010\u000f\u001a\u00020\bH\u0087\nJ\t\u0010\u0010\u001a\u00020\bH\u0087\nJ\t\u0010\u0011\u001a\u00020\bH\u0087\nJ\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\b0\u0013R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0014"}, m8860e = {"Lkotlin/text/MatchResult$Destructured;", "", "match", "Lkotlin/text/MatchResult;", "(Lkotlin/text/MatchResult;)V", "getMatch", "()Lkotlin/text/MatchResult;", "component1", "", "component10", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "toList", "", "kotlin-stdlib"})
    /* renamed from: z1.cpf$b */
    /* loaded from: classes3.dex */
    public static final class C5070b {
        @NotNull

        /* renamed from: a */
        private final cpf f21018a;

        public C5070b(@NotNull cpf cpfVar) {
            cji.m5162f(cpfVar, "match");
            this.f21018a = cpfVar;
        }

        @NotNull
        /* renamed from: b */
        public final cpf m4195b() {
            return this.f21018a;
        }

        @cey
        /* renamed from: c */
        private final String m4194c() {
            return m4195b().mo4180d().get(1);
        }

        @cey
        /* renamed from: d */
        private final String m4193d() {
            return m4195b().mo4180d().get(2);
        }

        @cey
        /* renamed from: e */
        private final String m4192e() {
            return m4195b().mo4180d().get(3);
        }

        @cey
        /* renamed from: f */
        private final String m4191f() {
            return m4195b().mo4180d().get(4);
        }

        @cey
        /* renamed from: g */
        private final String m4190g() {
            return m4195b().mo4180d().get(5);
        }

        @cey
        /* renamed from: h */
        private final String m4189h() {
            return m4195b().mo4180d().get(6);
        }

        @cey
        /* renamed from: i */
        private final String m4188i() {
            return m4195b().mo4180d().get(7);
        }

        @cey
        /* renamed from: j */
        private final String m4187j() {
            return m4195b().mo4180d().get(8);
        }

        @cey
        /* renamed from: k */
        private final String m4186k() {
            return m4195b().mo4180d().get(9);
        }

        @cey
        /* renamed from: l */
        private final String m4185l() {
            return m4195b().mo4180d().get(10);
        }

        @NotNull
        /* renamed from: a */
        public final List<String> m4196a() {
            return this.f21018a.mo4180d().subList(1, this.f21018a.mo4180d().size());
        }
    }
}
