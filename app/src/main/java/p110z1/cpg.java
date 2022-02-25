package p110z1;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import org.apache.tools.ant.types.selectors.SizeSelector;
import p110z1.cpf;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Regex.kt */
@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\n\u0010\u001c\u001a\u0004\u0018\u00010\u0001H\u0016R\u001a\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0016\u0010\f\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\u00020\u000eX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\u00020\u00128BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0015\u001a\u00020\u00168VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R\u0014\u0010\u0019\u001a\u00020\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001b¨\u0006\u001d"}, m8860e = {"Lkotlin/text/MatcherMatchResult;", "Lkotlin/text/MatchResult;", "matcher", "Ljava/util/regex/Matcher;", "input", "", "(Ljava/util/regex/Matcher;Ljava/lang/CharSequence;)V", "groupValues", "", "", "getGroupValues", "()Ljava/util/List;", "groupValues_", "groups", "Lkotlin/text/MatchGroupCollection;", "getGroups", "()Lkotlin/text/MatchGroupCollection;", "matchResult", "Ljava/util/regex/MatchResult;", "getMatchResult", "()Ljava/util/regex/MatchResult;", "range", "Lkotlin/ranges/IntRange;", "getRange", "()Lkotlin/ranges/IntRange;", SizeSelector.SIZE_KEY, "getValue", "()Ljava/lang/String;", "next", "kotlin-stdlib"})
/* renamed from: z1.cpg */
/* loaded from: classes3.dex */
public final class cpg implements cpf {
    @NotNull

    /* renamed from: a */
    private final MatchResult f21019a = new C5072b();

    /* renamed from: b */
    private List<String> f21020b;

    /* renamed from: c */
    private final Matcher f21021c;

    /* renamed from: d */
    private final CharSequence f21022d;

    public cpg(@NotNull Matcher matcher, @NotNull CharSequence charSequence) {
        cji.m5162f(matcher, "matcher");
        cji.m5162f(charSequence, "input");
        this.f21021c = matcher;
        this.f21022d = charSequence;
    }

    @Override // p110z1.cpf
    @NotNull
    /* renamed from: e */
    public cpf.C5070b mo4179e() {
        return cpf.C5069a.m4197a(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: g */
    public final MatchResult m4177g() {
        return this.f21021c;
    }

    @Override // p110z1.cpf
    @NotNull
    /* renamed from: a */
    public cme mo4184a() {
        cme b;
        b = cpi.m4155b(m4177g());
        return b;
    }

    /* compiled from: Regex.kt */
    @Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000-\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010(\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u00012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0002J\u0013\u0010\b\u001a\u0004\u0018\u00010\u00032\u0006\u0010\t\u001a\u00020\u0005H\u0096\u0002J\u0013\u0010\b\u001a\u0004\u0018\u00010\u00032\u0006\u0010\n\u001a\u00020\u000bH\u0096\u0002J\b\u0010\f\u001a\u00020\rH\u0016J\u0011\u0010\u000e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u000fH\u0096\u0002R\u0014\u0010\u0004\u001a\u00020\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0010"}, m8860e = {"kotlin/text/MatcherMatchResult$groups$1", "Lkotlin/text/MatchNamedGroupCollection;", "Lkotlin/collections/AbstractCollection;", "Lkotlin/text/MatchGroup;", "size", "", "getSize", "()I", "get", "index", "name", "", "isEmpty", "", "iterator", "", "kotlin-stdlib"})
    /* renamed from: z1.cpg$b */
    /* loaded from: classes3.dex */
    public static final class C5072b extends AbstractCollection<cpc> implements cpe {
        @Override // p110z1.AbstractCollection, java.util.Collection
        public boolean isEmpty() {
            return false;
        }

        /* compiled from: Regex.kt */
        @Metadata(m8864a = 3, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, m8860e = {"<anonymous>", "Lkotlin/text/MatchGroup;", "it", "", "invoke"})
        /* renamed from: z1.cpg$b$a */
        /* loaded from: classes3.dex */
        static final class C5073a extends Lambda implements chd<Integer, cpc> {
            C5073a() {
                super(1);
            }

            @Override // p110z1.chd
            public /* synthetic */ cpc invoke(Integer num) {
                return invoke(num.intValue());
            }

            @dbs
            public final cpc invoke(int i) {
                return C5072b.this.mo4171a(i);
            }
        }

        C5072b() {
        }

        /* renamed from: a */
        public boolean m4169a(cpc cpcVar) {
            return super.contains(cpcVar);
        }

        @Override // p110z1.AbstractCollection, java.util.Collection
        public final boolean contains(Object obj) {
            if (obj != null ? obj instanceof cpc : true) {
                return m4169a((cpc) obj);
            }
            return false;
        }

        @Override // p110z1.AbstractCollection
        /* renamed from: a */
        public int mo4172a() {
            return cpg.this.m4177g().groupCount() + 1;
        }

        @Override // p110z1.AbstractCollection, java.util.Collection, java.lang.Iterable
        @NotNull
        public Iterator<cpc> iterator() {
            return coe.m4302u(bzk.m6704J(bzk.m6813a((Collection<?>) this)), new C5073a()).mo3707a();
        }

        @Override // p110z1.MatchResult
        @dbs
        /* renamed from: a */
        public cpc mo4171a(int i) {
            cme b;
            b = cpi.m4154b(cpg.this.m4177g(), i);
            if (b.mo4665g().intValue() < 0) {
                return null;
            }
            String group = cpg.this.m4177g().group(i);
            cji.m5175b(group, "matchResult.group(index)");
            return new cpc(group, b);
        }

        @Override // p110z1.cpe
        @dbs
        /* renamed from: a */
        public cpc mo4170a(@NotNull String str) {
            cji.m5162f(str, "name");
            return cfe.f20646a.m5473a(cpg.this.m4177g(), str);
        }
    }

    @Override // p110z1.cpf
    @NotNull
    /* renamed from: b */
    public String mo4182b() {
        String group = m4177g().group();
        cji.m5175b(group, "matchResult.group()");
        return group;
    }

    @Override // p110z1.cpf
    @NotNull
    /* renamed from: c */
    public MatchResult mo4181c() {
        return this.f21019a;
    }

    /* compiled from: Regex.kt */
    @Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0011\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0004H\u0096\u0002R\u0014\u0010\u0003\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, m8860e = {"kotlin/text/MatcherMatchResult$groupValues$1", "Lkotlin/collections/AbstractList;", "", "size", "", "getSize", "()I", "get", "index", "kotlin-stdlib"})
    /* renamed from: z1.cpg$a */
    /* loaded from: classes3.dex */
    public static final class C5071a extends AbstractList<String> {
        C5071a() {
        }

        /* renamed from: a */
        public int m4175a(String str) {
            return super.indexOf(str);
        }

        /* renamed from: b */
        public int m4174b(String str) {
            return super.lastIndexOf(str);
        }

        /* renamed from: c */
        public boolean m4173c(String str) {
            return super.contains(str);
        }

        @Override // p110z1.AbstractCollection, java.util.Collection
        public final boolean contains(Object obj) {
            if (obj instanceof String) {
                return m4173c((String) obj);
            }
            return false;
        }

        @Override // p110z1.AbstractList, java.util.List
        public final int indexOf(Object obj) {
            if (obj instanceof String) {
                return m4175a((String) obj);
            }
            return -1;
        }

        @Override // p110z1.AbstractList, java.util.List
        public final int lastIndexOf(Object obj) {
            if (obj instanceof String) {
                return m4174b((String) obj);
            }
            return -1;
        }

        @Override // p110z1.AbstractList, p110z1.AbstractCollection
        /* renamed from: a */
        public int mo4172a() {
            return cpg.this.m4177g().groupCount() + 1;
        }

        @NotNull
        /* renamed from: a */
        public String get(int i) {
            String group = cpg.this.m4177g().group(i);
            return group != null ? group : "";
        }
    }

    @Override // p110z1.cpf
    @NotNull
    /* renamed from: d */
    public List<String> mo4180d() {
        if (this.f21020b == null) {
            this.f21020b = new C5071a();
        }
        List<String> list = this.f21020b;
        if (list == null) {
            cji.m5196a();
        }
        return list;
    }

    @Override // p110z1.cpf
    @dbs
    /* renamed from: f */
    public cpf mo4178f() {
        cpf b;
        int end = m4177g().end() + (m4177g().end() == m4177g().start() ? 1 : 0);
        if (end > this.f21022d.length()) {
            return null;
        }
        Matcher matcher = this.f21021c.pattern().matcher(this.f21022d);
        cji.m5175b(matcher, "matcher.pattern().matcher(input)");
        b = cpi.m4153b(matcher, end, this.f21022d);
        return b;
    }
}
