package p110z1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.p105io.FilenameUtils;
import org.apache.tools.ant.taskdefs.optional.vss.MSVSSConstants;
import org.apache.tools.ant.types.selectors.DateSelector;
import p110z1.cpi;

/* compiled from: Regex.kt */
@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 ,2\u00060\u0001j\u0002`\u0002:\u0002,-B\u000f\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005B\u0017\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bB\u001d\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00070\n¢\u0006\u0002\u0010\u000bB\u000f\b\u0001\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010\u000eJ\u000e\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017J\u001a\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010\u001a\u001a\u00020\u001bJ\u001e\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00190\u001d2\u0006\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010\u001a\u001a\u00020\u001bJ\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u0016\u001a\u00020\u0017J\u0011\u0010\u001f\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0086\u0004J\"\u0010 \u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u00172\u0012\u0010!\u001a\u000e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00170\"J\u0016\u0010 \u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010#\u001a\u00020\u0004J\u0016\u0010$\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010#\u001a\u00020\u0004J\u001e\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00040&2\u0006\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010'\u001a\u00020\u001bJ\u0006\u0010(\u001a\u00020\rJ\b\u0010)\u001a\u00020\u0004H\u0016J\b\u0010*\u001a\u00020+H\u0002R\u0016\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00070\n8F¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0003\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013¨\u0006."}, m8860e = {"Lkotlin/text/Regex;", "Ljava/io/Serializable;", "Lkotlin/io/Serializable;", DateSelector.PATTERN_KEY, "", "(Ljava/lang/String;)V", "option", "Lkotlin/text/RegexOption;", "(Ljava/lang/String;Lkotlin/text/RegexOption;)V", "options", "", "(Ljava/lang/String;Ljava/util/Set;)V", "nativePattern", "Ljava/util/regex/Pattern;", "(Ljava/util/regex/Pattern;)V", "_options", "getOptions", "()Ljava/util/Set;", "getPattern", "()Ljava/lang/String;", "containsMatchIn", "", "input", "", "find", "Lkotlin/text/MatchResult;", "startIndex", "", "findAll", "Lkotlin/sequences/Sequence;", "matchEntire", "matches", MSVSSConstants.WRITABLE_REPLACE, "transform", "Lkotlin/Function1;", "replacement", "replaceFirst", "split", "", "limit", "toPattern", "toString", "writeReplace", "", "Companion", "Serialized", "kotlin-stdlib"})
/* renamed from: z1.cph */
/* loaded from: classes3.dex */
public final class cph implements Serializable {
    public static final C5074a Companion = new C5074a(null);
    private Set<? extends cpj> _options;
    private final Pattern nativePattern;

    @bwt
    public cph(@NotNull Pattern pattern) {
        cji.m5162f(pattern, "nativePattern");
        this.nativePattern = pattern;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public cph(@p110z1.NotNull java.lang.String r2) {
        /*
            r1 = this;
            java.lang.String r0 = "pattern"
            p110z1.cji.m5162f(r2, r0)
            java.util.regex.Pattern r2 = java.util.regex.Pattern.compile(r2)
            java.lang.String r0 = "Pattern.compile(pattern)"
            p110z1.cji.m5175b(r2, r0)
            r1.<init>(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p110z1.cph.<init>(java.lang.String):void");
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public cph(@p110z1.NotNull java.lang.String r2, @p110z1.NotNull p110z1.cpj r3) {
        /*
            r1 = this;
            java.lang.String r0 = "pattern"
            p110z1.cji.m5162f(r2, r0)
            java.lang.String r0 = "option"
            p110z1.cji.m5162f(r3, r0)
            z1.cph$a r0 = p110z1.cph.Companion
            int r3 = r3.getValue()
            int r3 = p110z1.cph.C5074a.m4166a(r0, r3)
            java.util.regex.Pattern r2 = java.util.regex.Pattern.compile(r2, r3)
            java.lang.String r3 = "Pattern.compile(pattern,…nicodeCase(option.value))"
            p110z1.cji.m5175b(r2, r3)
            r1.<init>(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p110z1.cph.<init>(java.lang.String, z1.cpj):void");
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public cph(@p110z1.NotNull java.lang.String r2, @p110z1.NotNull java.util.Set<? extends p110z1.cpj> r3) {
        /*
            r1 = this;
            java.lang.String r0 = "pattern"
            p110z1.cji.m5162f(r2, r0)
            java.lang.String r0 = "options"
            p110z1.cji.m5162f(r3, r0)
            z1.cph$a r0 = p110z1.cph.Companion
            java.lang.Iterable r3 = (java.lang.Iterable) r3
            int r3 = p110z1.cpi.m4162a(r3)
            int r3 = p110z1.cph.C5074a.m4166a(r0, r3)
            java.util.regex.Pattern r2 = java.util.regex.Pattern.compile(r2, r3)
            java.lang.String r3 = "Pattern.compile(pattern,…odeCase(options.toInt()))"
            p110z1.cji.m5175b(r2, r3)
            r1.<init>(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p110z1.cph.<init>(java.lang.String, java.util.Set):void");
    }

    @NotNull
    public final String getPattern() {
        String pattern = this.nativePattern.pattern();
        cji.m5175b(pattern, "nativePattern.pattern()");
        return pattern;
    }

    @NotNull
    public final Set<cpj> getOptions() {
        Set set = this._options;
        if (set != null) {
            return set;
        }
        int flags = this.nativePattern.flags();
        EnumSet allOf = EnumSet.allOf(cpj.class);
        bzk.m6751b((Iterable) allOf, (chd) new cpi.C5079a(flags));
        Set<cpj> unmodifiableSet = Collections.unmodifiableSet(allOf);
        cji.m5175b(unmodifiableSet, "Collections.unmodifiable…mask == it.value }\n    })");
        this._options = unmodifiableSet;
        return unmodifiableSet;
    }

    public final boolean matches(@NotNull CharSequence charSequence) {
        cji.m5162f(charSequence, "input");
        return this.nativePattern.matcher(charSequence).matches();
    }

    public final boolean containsMatchIn(@NotNull CharSequence charSequence) {
        cji.m5162f(charSequence, "input");
        return this.nativePattern.matcher(charSequence).find();
    }

    public static /* synthetic */ cpf find$default(cph cphVar, CharSequence charSequence, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        return cphVar.find(charSequence, i);
    }

    @dbs
    public final cpf find(@NotNull CharSequence charSequence, int i) {
        cji.m5162f(charSequence, "input");
        Matcher matcher = this.nativePattern.matcher(charSequence);
        cji.m5175b(matcher, "nativePattern.matcher(input)");
        return cpi.m4159a(matcher, i, charSequence);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: Regex.kt */
    @Metadata(m8864a = 3, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001H\n¢\u0006\u0002\b\u0002"}, m8860e = {"<anonymous>", "Lkotlin/text/MatchResult;", "invoke"})
    /* renamed from: z1.cph$c */
    /* loaded from: classes3.dex */
    public static final class C5077c extends Lambda implements chc<cpf> {
        final /* synthetic */ CharSequence $input;
        final /* synthetic */ int $startIndex;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C5077c(CharSequence charSequence, int i) {
            super(0);
            this.$input = charSequence;
            this.$startIndex = i;
        }

        @Override // p110z1.chc
        @dbs
        public final cpf invoke() {
            return cph.this.find(this.$input, this.$startIndex);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: Regex.kt */
    @Metadata(m8864a = 3, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0002\b\u0003"}, m8860e = {"<anonymous>", "Lkotlin/text/MatchResult;", "p1", "invoke"})
    /* renamed from: z1.cph$d */
    /* loaded from: classes3.dex */
    public static final /* synthetic */ class C5078d extends FunctionReference implements chd<cpf, cpf> {
        public static final C5078d INSTANCE = new C5078d();

        C5078d() {
            super(1);
        }

        @Override // p110z1.CallableReference, p110z1.KCallable
        public final String getName() {
            return "next";
        }

        @Override // p110z1.CallableReference
        public final KDeclarationContainer getOwner() {
            return Reflection.m5109b(cpf.class);
        }

        @Override // p110z1.CallableReference
        public final String getSignature() {
            return "next()Lkotlin/text/MatchResult;";
        }

        @dbs
        public final cpf invoke(@NotNull cpf cpfVar) {
            cji.m5162f(cpfVar, "p1");
            return cpfVar.mo4178f();
        }
    }

    public static /* synthetic */ Sequence findAll$default(cph cphVar, CharSequence charSequence, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        return cphVar.findAll(charSequence, i);
    }

    @NotNull
    public final Sequence<cpf> findAll(@NotNull CharSequence charSequence, int i) {
        cji.m5162f(charSequence, "input");
        return coe.m4445a((chc) new C5077c(charSequence, i), (chd) C5078d.INSTANCE);
    }

    @dbs
    public final cpf matchEntire(@NotNull CharSequence charSequence) {
        cji.m5162f(charSequence, "input");
        Matcher matcher = this.nativePattern.matcher(charSequence);
        cji.m5175b(matcher, "nativePattern.matcher(input)");
        return cpi.m4158a(matcher, charSequence);
    }

    @NotNull
    public final String replace(@NotNull CharSequence charSequence, @NotNull String str) {
        cji.m5162f(charSequence, "input");
        cji.m5162f(str, "replacement");
        String replaceAll = this.nativePattern.matcher(charSequence).replaceAll(str);
        cji.m5175b(replaceAll, "nativePattern.matcher(in…).replaceAll(replacement)");
        return replaceAll;
    }

    @NotNull
    public final String replace(@NotNull CharSequence charSequence, @NotNull chd<? super cpf, ? extends CharSequence> chdVar) {
        cji.m5162f(charSequence, "input");
        cji.m5162f(chdVar, "transform");
        int i = 0;
        cpf find$default = find$default(this, charSequence, 0, 2, null);
        if (find$default == null) {
            return charSequence.toString();
        }
        int length = charSequence.length();
        StringBuilder sb = new StringBuilder(length);
        do {
            if (find$default == null) {
                cji.m5196a();
            }
            sb.append(charSequence, i, find$default.mo4184a().mo4665g().intValue());
            sb.append((CharSequence) chdVar.invoke(find$default));
            i = find$default.mo4184a().mo4663i().intValue() + 1;
            find$default = find$default.mo4178f();
            if (i >= length) {
                break;
            }
        } while (find$default != null);
        if (i < length) {
            sb.append(charSequence, i, length);
        }
        String sb2 = sb.toString();
        cji.m5175b(sb2, "sb.toString()");
        return sb2;
    }

    @NotNull
    public final String replaceFirst(@NotNull CharSequence charSequence, @NotNull String str) {
        cji.m5162f(charSequence, "input");
        cji.m5162f(str, "replacement");
        String replaceFirst = this.nativePattern.matcher(charSequence).replaceFirst(str);
        cji.m5175b(replaceFirst, "nativePattern.matcher(in…replaceFirst(replacement)");
        return replaceFirst;
    }

    public static /* synthetic */ List split$default(cph cphVar, CharSequence charSequence, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        return cphVar.split(charSequence, i);
    }

    @NotNull
    public final List<String> split(@NotNull CharSequence charSequence, int i) {
        cji.m5162f(charSequence, "input");
        int i2 = 0;
        if (i >= 0) {
            Matcher matcher = this.nativePattern.matcher(charSequence);
            if (!matcher.find() || i == 1) {
                return bzk.m6822a(charSequence.toString());
            }
            int i3 = 10;
            if (i > 0) {
                i3 = cmi.m4701d(i, 10);
            }
            ArrayList arrayList = new ArrayList(i3);
            int i4 = i - 1;
            do {
                arrayList.add(charSequence.subSequence(i2, matcher.start()).toString());
                i2 = matcher.end();
                if (i4 >= 0 && arrayList.size() == i4) {
                    break;
                }
            } while (matcher.find());
            arrayList.add(charSequence.subSequence(i2, charSequence.length()).toString());
            return arrayList;
        }
        throw new IllegalArgumentException(("Limit must be non-negative, but was " + i + FilenameUtils.EXTENSION_SEPARATOR).toString());
    }

    @NotNull
    public String toString() {
        String pattern = this.nativePattern.toString();
        cji.m5175b(pattern, "nativePattern.toString()");
        return pattern;
    }

    @NotNull
    public final Pattern toPattern() {
        return this.nativePattern;
    }

    private final Object writeReplace() {
        String pattern = this.nativePattern.pattern();
        cji.m5175b(pattern, "nativePattern.pattern()");
        return new C5075b(pattern, this.nativePattern.flags());
    }

    /* compiled from: Regex.kt */
    @Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0002\u0018\u0000 \u000e2\u00060\u0001j\u0002`\u0002:\u0001\u000eB\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\b\u0010\f\u001a\u00020\rH\u0002R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u000f"}, m8860e = {"Lkotlin/text/Regex$Serialized;", "Ljava/io/Serializable;", "Lkotlin/io/Serializable;", DateSelector.PATTERN_KEY, "", "flags", "", "(Ljava/lang/String;I)V", "getFlags", "()I", "getPattern", "()Ljava/lang/String;", "readResolve", "", "Companion", "kotlin-stdlib"})
    /* renamed from: z1.cph$b */
    /* loaded from: classes3.dex */
    private static final class C5075b implements Serializable {
        public static final C5076a Companion = new C5076a(null);
        private static final long serialVersionUID = 0;
        private final int flags;
        @NotNull
        private final String pattern;

        /* compiled from: Regex.kt */
        @Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, m8860e = {"Lkotlin/text/Regex$Serialized$Companion;", "", "()V", "serialVersionUID", "", "kotlin-stdlib"})
        /* renamed from: z1.cph$b$a */
        /* loaded from: classes3.dex */
        public static final class C5076a {
            private C5076a() {
            }

            public /* synthetic */ C5076a(DefaultConstructorMarker civVar) {
                this();
            }
        }

        public C5075b(@NotNull String str, int i) {
            cji.m5162f(str, DateSelector.PATTERN_KEY);
            this.pattern = str;
            this.flags = i;
        }

        public final int getFlags() {
            return this.flags;
        }

        @NotNull
        public final String getPattern() {
            return this.pattern;
        }

        private final Object readResolve() {
            Pattern compile = Pattern.compile(this.pattern, this.flags);
            cji.m5175b(compile, "Pattern.compile(pattern, flags)");
            return new cph(compile);
        }
    }

    /* compiled from: Regex.kt */
    @Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0002J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007J\u000e\u0010\t\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007J\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\u0007¨\u0006\f"}, m8860e = {"Lkotlin/text/Regex$Companion;", "", "()V", "ensureUnicodeCase", "", "flags", "escape", "", "literal", "escapeReplacement", "fromLiteral", "Lkotlin/text/Regex;", "kotlin-stdlib"})
    /* renamed from: z1.cph$a */
    /* loaded from: classes3.dex */
    public static final class C5074a {
        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: a */
        public final int m4168a(int i) {
            return (i & 2) != 0 ? i | 64 : i;
        }

        private C5074a() {
        }

        public /* synthetic */ C5074a(DefaultConstructorMarker civVar) {
            this();
        }

        @NotNull
        /* renamed from: a */
        public final cph m4167a(@NotNull String str) {
            cji.m5162f(str, "literal");
            return new cph(str, cpj.LITERAL);
        }

        @NotNull
        /* renamed from: b */
        public final String m4165b(@NotNull String str) {
            cji.m5162f(str, "literal");
            String quote = Pattern.quote(str);
            cji.m5175b(quote, "Pattern.quote(literal)");
            return quote;
        }

        @NotNull
        /* renamed from: c */
        public final String m4164c(@NotNull String str) {
            cji.m5162f(str, "literal");
            String quoteReplacement = Matcher.quoteReplacement(str);
            cji.m5175b(quoteReplacement, "Matcher.quoteReplacement(literal)");
            return quoteReplacement;
        }
    }
}
