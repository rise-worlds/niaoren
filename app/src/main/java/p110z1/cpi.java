package p110z1;

import com.googlecode.tesseract.android.TessBaseAPI;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import org.apache.tools.ant.types.selectors.SizeSelector;

/* compiled from: Regex.kt */
@Metadata(m8864a = 2, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000>\n\u0000\n\u0002\u0010\"\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\r\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001c\n\u0000\u001a-\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0014\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0082\b\u001a\u001e\u0010\u0007\u001a\u0004\u0018\u00010\b*\u00020\t2\u0006\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\fH\u0002\u001a\u0016\u0010\r\u001a\u0004\u0018\u00010\b*\u00020\t2\u0006\u0010\u000b\u001a\u00020\fH\u0002\u001a\f\u0010\u000e\u001a\u00020\u000f*\u00020\u0010H\u0002\u001a\u0014\u0010\u000e\u001a\u00020\u000f*\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0006H\u0002\u001a\u0012\u0010\u0012\u001a\u00020\u0006*\b\u0012\u0004\u0012\u00020\u00030\u0013H\u0002¨\u0006\u0014"}, m8860e = {"fromInt", "", TessBaseAPI.f9204e, "Lkotlin/text/FlagEnum;", "", SizeSelector.SIZE_KEY, "", "findNext", "Lkotlin/text/MatchResult;", "Ljava/util/regex/Matcher;", "from", "input", "", "matchEntire", "range", "Lkotlin/ranges/IntRange;", "Ljava/util/regex/MatchResult;", "groupIndex", "toInt", "", "kotlin-stdlib"})
/* renamed from: z1.cpi */
/* loaded from: classes3.dex */
public final class cpi {

    /* compiled from: Regex.kt */
    @Metadata(m8864a = 3, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000 \n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0005\u0010\u0000\u001a\u00020\u0001\"\u0014\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u00042\u000e\u0010\u0005\u001a\n \u0006*\u0004\u0018\u0001H\u0002H\u0002H\n¢\u0006\u0004\b\u0007\u0010\b¨\u0006\t"}, m8860e = {"<anonymous>", "", TessBaseAPI.f9204e, "Lkotlin/text/FlagEnum;", "", "it", "kotlin.jvm.PlatformType", "invoke", "(Ljava/lang/Enum;)Z", "kotlin/text/RegexKt$fromInt$1$1"})
    /* renamed from: z1.cpi$a */
    /* loaded from: classes3.dex */
    public static final class C5079a extends Lambda implements chd<T, Boolean> {
        final /* synthetic */ int $value$inlined;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C5079a(int i) {
            super(1);
            this.$value$inlined = i;
        }

        @Override // p110z1.chd
        public /* synthetic */ Boolean invoke(Object obj) {
            return Boolean.valueOf(invoke((Enum) obj));
        }

        public final boolean invoke(Enum r3) {
            Regex cpbVar = (Regex) r3;
            return (this.$value$inlined & cpbVar.getMask()) == cpbVar.getValue();
        }
    }

    /* compiled from: Regex.kt */
    @Metadata(m8864a = 3, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000 \n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0005\u0010\u0000\u001a\u00020\u0001\"\u0014\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u00042\u000e\u0010\u0005\u001a\n \u0006*\u0004\u0018\u0001H\u0002H\u0002H\n¢\u0006\u0004\b\u0007\u0010\b¨\u0006\t"}, m8860e = {"<anonymous>", "", TessBaseAPI.f9204e, "Lkotlin/text/FlagEnum;", "", "it", "kotlin.jvm.PlatformType", "invoke", "(Ljava/lang/Enum;)Z", "kotlin/text/RegexKt$fromInt$1$1"})
    /* renamed from: z1.cpi$b */
    /* loaded from: classes3.dex */
    public static final class C5080b extends Lambda implements chd<T, Boolean> {
        final /* synthetic */ int $value$inlined;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C5080b(int i) {
            super(1);
            this.$value$inlined = i;
        }

        @Override // p110z1.chd
        public /* synthetic */ Boolean invoke(Object obj) {
            return Boolean.valueOf(invoke((Enum) obj));
        }

        public final boolean invoke(Enum r3) {
            Regex cpbVar = (Regex) r3;
            return (this.$value$inlined & cpbVar.getMask()) == cpbVar.getValue();
        }
    }

    /* renamed from: b */
    public static final /* synthetic */ <T extends Enum<T> & Regex> Set<T> m4157b(int i) {
        cji.m5192a(4, TessBaseAPI.f9204e);
        EnumSet allOf = EnumSet.allOf(Enum.class);
        bzk.m6751b((Iterable) allOf, (chd) new C5080b(i));
        Set<T> unmodifiableSet = Collections.unmodifiableSet(allOf);
        cji.m5175b(unmodifiableSet, "Collections.unmodifiable…mask == it.value }\n    })");
        return unmodifiableSet;
    }

    /* renamed from: b */
    public static final cpf m4153b(@NotNull Matcher matcher, int i, CharSequence charSequence) {
        if (!matcher.find(i)) {
            return null;
        }
        return new cpg(matcher, charSequence);
    }

    /* renamed from: b */
    public static final cpf m4152b(@NotNull Matcher matcher, CharSequence charSequence) {
        if (!matcher.matches()) {
            return null;
        }
        return new cpg(matcher, charSequence);
    }

    /* renamed from: b */
    public static final cme m4155b(@NotNull MatchResult matchResult) {
        return cmi.m4739b(matchResult.start(), matchResult.end());
    }

    /* renamed from: b */
    public static final cme m4154b(@NotNull MatchResult matchResult, int i) {
        return cmi.m4739b(matchResult.start(i), matchResult.end(i));
    }

    /* renamed from: b */
    public static final int m4156b(@NotNull Iterable<? extends Regex> iterable) {
        int i = 0;
        for (Regex cpbVar : iterable) {
            i |= cpbVar.getValue();
        }
        return i;
    }
}
