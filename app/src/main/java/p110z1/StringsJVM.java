package p110z1;

import com.github.kevinsawicki.http.HttpRequest;
import com.tendcloud.tenddata.C2970ih;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CodingErrorAction;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import org.apache.commons.p105io.FilenameUtils;
import org.apache.tools.ant.taskdefs.optional.vss.MSVSSConstants;
import org.apache.tools.ant.types.selectors.FilenameSelector;

@Metadata(m8864a = 5, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000~\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0019\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\r\n\u0002\b\t\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\b\n\u0002\u0010\f\n\u0002\b\u0011\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\u001a\u0011\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\tH\u0087\b\u001a\u0011\u0010\u0007\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u000bH\u0087\b\u001a\u0011\u0010\u0007\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\rH\u0087\b\u001a\u0019\u0010\u0007\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0087\b\u001a!\u0010\u0007\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011H\u0087\b\u001a)\u0010\u0007\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u000e\u001a\u00020\u000fH\u0087\b\u001a\u0011\u0010\u0007\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u0014H\u0087\b\u001a!\u0010\u0007\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011H\u0087\b\u001a!\u0010\u0007\u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011H\u0087\b\u001a\n\u0010\u0017\u001a\u00020\u0002*\u00020\u0002\u001a\u0014\u0010\u0017\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u0019H\u0007\u001a\u0015\u0010\u001a\u001a\u00020\u0011*\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u0011H\u0087\b\u001a\u0015\u0010\u001c\u001a\u00020\u0011*\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u0011H\u0087\b\u001a\u001d\u0010\u001d\u001a\u00020\u0011*\u00020\u00022\u0006\u0010\u001e\u001a\u00020\u00112\u0006\u0010\u001f\u001a\u00020\u0011H\u0087\b\u001a\u001c\u0010 \u001a\u00020\u0011*\u00020\u00022\u0006\u0010!\u001a\u00020\u00022\b\b\u0002\u0010\"\u001a\u00020#\u001a\f\u0010$\u001a\u00020\u0002*\u00020\u0014H\u0007\u001a \u0010$\u001a\u00020\u0002*\u00020\u00142\b\b\u0002\u0010%\u001a\u00020\u00112\b\b\u0002\u0010\u001f\u001a\u00020\u0011H\u0007\u001a\u0015\u0010&\u001a\u00020#*\u00020\u00022\u0006\u0010\n\u001a\u00020\tH\u0087\b\u001a\u0015\u0010&\u001a\u00020#*\u00020\u00022\u0006\u0010'\u001a\u00020(H\u0087\b\u001a\n\u0010)\u001a\u00020\u0002*\u00020\u0002\u001a\u0014\u0010)\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u0019H\u0007\u001a\f\u0010*\u001a\u00020\u0002*\u00020\rH\u0007\u001a*\u0010*\u001a\u00020\u0002*\u00020\r2\b\b\u0002\u0010%\u001a\u00020\u00112\b\b\u0002\u0010\u001f\u001a\u00020\u00112\b\b\u0002\u0010+\u001a\u00020#H\u0007\u001a\f\u0010,\u001a\u00020\r*\u00020\u0002H\u0007\u001a*\u0010,\u001a\u00020\r*\u00020\u00022\b\b\u0002\u0010%\u001a\u00020\u00112\b\b\u0002\u0010\u001f\u001a\u00020\u00112\b\b\u0002\u0010+\u001a\u00020#H\u0007\u001a\u001c\u0010-\u001a\u00020#*\u00020\u00022\u0006\u0010.\u001a\u00020\u00022\b\b\u0002\u0010\"\u001a\u00020#\u001a \u0010/\u001a\u00020#*\u0004\u0018\u00010\u00022\b\u0010!\u001a\u0004\u0018\u00010\u00022\b\b\u0002\u0010\"\u001a\u00020#\u001a2\u00100\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u00192\u0016\u00101\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010302\"\u0004\u0018\u000103H\u0087\b¢\u0006\u0002\u00104\u001a*\u00100\u001a\u00020\u0002*\u00020\u00022\u0016\u00101\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010302\"\u0004\u0018\u000103H\u0087\b¢\u0006\u0002\u00105\u001a:\u00100\u001a\u00020\u0002*\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u00100\u001a\u00020\u00022\u0016\u00101\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010302\"\u0004\u0018\u000103H\u0087\b¢\u0006\u0002\u00106\u001a2\u00100\u001a\u00020\u0002*\u00020\u00042\u0006\u00100\u001a\u00020\u00022\u0016\u00101\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010302\"\u0004\u0018\u000103H\u0087\b¢\u0006\u0002\u00107\u001a\r\u00108\u001a\u00020\u0002*\u00020\u0002H\u0087\b\u001a\n\u00109\u001a\u00020#*\u00020(\u001a\u001d\u0010:\u001a\u00020\u0011*\u00020\u00022\u0006\u0010;\u001a\u00020<2\u0006\u0010=\u001a\u00020\u0011H\u0081\b\u001a\u001d\u0010:\u001a\u00020\u0011*\u00020\u00022\u0006\u0010>\u001a\u00020\u00022\u0006\u0010=\u001a\u00020\u0011H\u0081\b\u001a\u001d\u0010?\u001a\u00020\u0011*\u00020\u00022\u0006\u0010;\u001a\u00020<2\u0006\u0010=\u001a\u00020\u0011H\u0081\b\u001a\u001d\u0010?\u001a\u00020\u0011*\u00020\u00022\u0006\u0010>\u001a\u00020\u00022\u0006\u0010=\u001a\u00020\u0011H\u0081\b\u001a\u001d\u0010@\u001a\u00020\u0011*\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u00112\u0006\u0010A\u001a\u00020\u0011H\u0087\b\u001a4\u0010B\u001a\u00020#*\u00020(2\u0006\u0010C\u001a\u00020\u00112\u0006\u0010!\u001a\u00020(2\u0006\u0010D\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\b\b\u0002\u0010\"\u001a\u00020#\u001a4\u0010B\u001a\u00020#*\u00020\u00022\u0006\u0010C\u001a\u00020\u00112\u0006\u0010!\u001a\u00020\u00022\u0006\u0010D\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\b\b\u0002\u0010\"\u001a\u00020#\u001a\u0012\u0010E\u001a\u00020\u0002*\u00020(2\u0006\u0010F\u001a\u00020\u0011\u001a$\u0010G\u001a\u00020\u0002*\u00020\u00022\u0006\u0010H\u001a\u00020<2\u0006\u0010I\u001a\u00020<2\b\b\u0002\u0010\"\u001a\u00020#\u001a$\u0010G\u001a\u00020\u0002*\u00020\u00022\u0006\u0010J\u001a\u00020\u00022\u0006\u0010K\u001a\u00020\u00022\b\b\u0002\u0010\"\u001a\u00020#\u001a$\u0010L\u001a\u00020\u0002*\u00020\u00022\u0006\u0010H\u001a\u00020<2\u0006\u0010I\u001a\u00020<2\b\b\u0002\u0010\"\u001a\u00020#\u001a$\u0010L\u001a\u00020\u0002*\u00020\u00022\u0006\u0010J\u001a\u00020\u00022\u0006\u0010K\u001a\u00020\u00022\b\b\u0002\u0010\"\u001a\u00020#\u001a\"\u0010M\u001a\b\u0012\u0004\u0012\u00020\u00020N*\u00020(2\u0006\u0010O\u001a\u00020P2\b\b\u0002\u0010Q\u001a\u00020\u0011\u001a\u001c\u0010R\u001a\u00020#*\u00020\u00022\u0006\u0010S\u001a\u00020\u00022\b\b\u0002\u0010\"\u001a\u00020#\u001a$\u0010R\u001a\u00020#*\u00020\u00022\u0006\u0010S\u001a\u00020\u00022\u0006\u0010%\u001a\u00020\u00112\b\b\u0002\u0010\"\u001a\u00020#\u001a\u0015\u0010T\u001a\u00020\u0002*\u00020\u00022\u0006\u0010%\u001a\u00020\u0011H\u0087\b\u001a\u001d\u0010T\u001a\u00020\u0002*\u00020\u00022\u0006\u0010%\u001a\u00020\u00112\u0006\u0010\u001f\u001a\u00020\u0011H\u0087\b\u001a\u0017\u0010U\u001a\u00020\r*\u00020\u00022\b\b\u0002\u0010\u000e\u001a\u00020\u000fH\u0087\b\u001a\r\u0010V\u001a\u00020\u0014*\u00020\u0002H\u0087\b\u001a3\u0010V\u001a\u00020\u0014*\u00020\u00022\u0006\u0010W\u001a\u00020\u00142\b\b\u0002\u0010X\u001a\u00020\u00112\b\b\u0002\u0010%\u001a\u00020\u00112\b\b\u0002\u0010\u001f\u001a\u00020\u0011H\u0087\b\u001a \u0010V\u001a\u00020\u0014*\u00020\u00022\b\b\u0002\u0010%\u001a\u00020\u00112\b\b\u0002\u0010\u001f\u001a\u00020\u0011H\u0007\u001a\r\u0010Y\u001a\u00020\u0002*\u00020\u0002H\u0087\b\u001a\u0015\u0010Y\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u0019H\u0087\b\u001a\u0017\u0010Z\u001a\u00020P*\u00020\u00022\b\b\u0002\u0010[\u001a\u00020\u0011H\u0087\b\u001a\r\u0010\\\u001a\u00020\u0002*\u00020\u0002H\u0087\b\u001a\u0015\u0010\\\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u0019H\u0087\b\"%\u0010\u0000\u001a\u0012\u0012\u0004\u0012\u00020\u00020\u0001j\b\u0012\u0004\u0012\u00020\u0002`\u0003*\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006]"}, m8860e = {"CASE_INSENSITIVE_ORDER", "Ljava/util/Comparator;", "", "Lkotlin/Comparator;", "Lkotlin/String$Companion;", "getCASE_INSENSITIVE_ORDER", "(Lkotlin/jvm/internal/StringCompanionObject;)Ljava/util/Comparator;", "String", "stringBuffer", "Ljava/lang/StringBuffer;", "stringBuilder", "Ljava/lang/StringBuilder;", "bytes", "", HttpRequest.PARAM_CHARSET, "Ljava/nio/charset/Charset;", "offset", "", C2970ih.C2971a.LENGTH, "chars", "", "codePoints", "", "capitalize", "locale", "Ljava/util/Locale;", "codePointAt", "index", "codePointBefore", "codePointCount", "beginIndex", "endIndex", "compareTo", "other", "ignoreCase", "", "concatToString", "startIndex", "contentEquals", "charSequence", "", "decapitalize", "decodeToString", "throwOnInvalidSequence", "encodeToByteArray", "endsWith", "suffix", "equals", "format", "args", "", "", "(Ljava/lang/String;Ljava/util/Locale;[Ljava/lang/Object;)Ljava/lang/String;", "(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;", "(Lkotlin/jvm/internal/StringCompanionObject;Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;", "(Lkotlin/jvm/internal/StringCompanionObject;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;", "intern", "isBlank", "nativeIndexOf", "ch", "", "fromIndex", "str", "nativeLastIndexOf", "offsetByCodePoints", "codePointOffset", "regionMatches", "thisOffset", "otherOffset", "repeat", "n", MSVSSConstants.WRITABLE_REPLACE, "oldChar", "newChar", "oldValue", "newValue", "replaceFirst", "split", "", FilenameSelector.REGEX_KEY, "Ljava/util/regex/Pattern;", "limit", "startsWith", "prefix", "substring", "toByteArray", "toCharArray", "destination", "destinationOffset", "toLowerCase", "toPattern", "flags", "toUpperCase", "kotlin-stdlib"}, m8859f = "kotlin/text/StringsKt", m8857h = 1)
/* renamed from: z1.cpt */
/* loaded from: classes3.dex */
public class StringsJVM extends StringNumberConversions {
    @cey
    /* renamed from: a */
    private static final int m4066a(@NotNull String str, char c, int i) {
        if (str != null) {
            return str.indexOf(c, i);
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }

    @cey
    /* renamed from: a */
    private static final int m4057a(@NotNull String str, String str2, int i) {
        if (str != null) {
            return str.indexOf(str2, i);
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }

    @cey
    /* renamed from: b */
    private static final int m4026b(@NotNull String str, char c, int i) {
        if (str != null) {
            return str.lastIndexOf(c, i);
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }

    @cey
    /* renamed from: b */
    private static final int m4024b(@NotNull String str, String str2, int i) {
        if (str != null) {
            return str.lastIndexOf(str2, i);
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }

    /* renamed from: a */
    public static /* synthetic */ boolean m4051a(String str, String str2, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return cpl.m4052a(str, str2, z);
    }

    /* renamed from: a */
    public static final boolean m4052a(@dbs String str, @dbs String str2, boolean z) {
        if (str == null) {
            return str2 == null;
        }
        if (!z) {
            return str.equals(str2);
        }
        return str.equalsIgnoreCase(str2);
    }

    /* renamed from: a */
    public static /* synthetic */ String m4067a(String str, char c, char c2, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        return cpl.m4068a(str, c, c2, z);
    }

    @NotNull
    /* renamed from: a */
    public static final String m4068a(@NotNull String str, char c, char c2, boolean z) {
        cji.m5162f(str, "$this$replace");
        if (z) {
            return coe.m4390a(cpl.m3957a(str, new char[]{c}, z, 0, 4, (Object) null), String.valueOf(c2), null, null, 0, null, null, 62, null);
        }
        String replace = str.replace(c, c2);
        cji.m5175b(replace, "(this as java.lang.Strin…replace(oldChar, newChar)");
        return replace;
    }

    /* renamed from: a */
    public static /* synthetic */ String m4053a(String str, String str2, String str3, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        return cpl.m4054a(str, str2, str3, z);
    }

    @NotNull
    /* renamed from: a */
    public static final String m4054a(@NotNull String str, @NotNull String str2, @NotNull String str3, boolean z) {
        cji.m5162f(str, "$this$replace");
        cji.m5162f(str2, "oldValue");
        cji.m5162f(str3, "newValue");
        return coe.m4390a(cpl.m3953a(str, new String[]{str2}, z, 0, 4, (Object) null), str3, null, null, 0, null, null, 62, null);
    }

    /* renamed from: b */
    public static /* synthetic */ String m4027b(String str, char c, char c2, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        return cpl.m4028b(str, c, c2, z);
    }

    @NotNull
    /* renamed from: b */
    public static final String m4028b(@NotNull String str, char c, char c2, boolean z) {
        cji.m5162f(str, "$this$replaceFirst");
        String str2 = str;
        int a = cpl.m3994a((CharSequence) str2, c, 0, z, 2, (Object) null);
        return a < 0 ? str : cpl.m3987a((CharSequence) str2, a, a + 1, (CharSequence) String.valueOf(c2)).toString();
    }

    /* renamed from: b */
    public static /* synthetic */ String m4022b(String str, String str2, String str3, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        return cpl.m4023b(str, str2, str3, z);
    }

    @NotNull
    /* renamed from: b */
    public static final String m4023b(@NotNull String str, @NotNull String str2, @NotNull String str3, boolean z) {
        cji.m5162f(str, "$this$replaceFirst");
        cji.m5162f(str2, "oldValue");
        cji.m5162f(str3, "newValue");
        String str4 = str;
        int a = cpl.m3977a((CharSequence) str4, str2, 0, z, 2, (Object) null);
        return a < 0 ? str : cpl.m3987a((CharSequence) str4, a, str2.length() + a, (CharSequence) str3).toString();
    }

    @cey
    /* renamed from: n */
    private static final String m3999n(@NotNull String str) {
        if (str != null) {
            String upperCase = str.toUpperCase();
            cji.m5175b(upperCase, "(this as java.lang.String).toUpperCase()");
            return upperCase;
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }

    @cey
    /* renamed from: o */
    private static final String m3998o(@NotNull String str) {
        if (str != null) {
            String lowerCase = str.toLowerCase();
            cji.m5175b(lowerCase, "(this as java.lang.String).toLowerCase()");
            return lowerCase;
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }

    @bwy(m8750a = "1.3")
    @ExperimentalStdlibApi
    @NotNull
    /* renamed from: a */
    public static final String m4032a(@NotNull char[] cArr) {
        cji.m5162f(cArr, "$this$concatToString");
        return new String(cArr);
    }

    /* renamed from: a */
    public static /* synthetic */ String m4030a(char[] cArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = cArr.length;
        }
        return cpl.m4031a(cArr, i, i2);
    }

    @bwy(m8750a = "1.3")
    @ExperimentalStdlibApi
    @NotNull
    /* renamed from: a */
    public static final String m4031a(@NotNull char[] cArr, int i, int i2) {
        cji.m5162f(cArr, "$this$concatToString");
        AbstractList.f20424a.m8317b(i, i2, cArr.length);
        return new String(cArr, i, i2 - i);
    }

    /* renamed from: a */
    public static /* synthetic */ char[] m4064a(String str, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = str.length();
        }
        return cpl.m4065a(str, i, i2);
    }

    @bwy(m8750a = "1.3")
    @ExperimentalStdlibApi
    @NotNull
    /* renamed from: a */
    public static final char[] m4065a(@NotNull String str, int i, int i2) {
        cji.m5162f(str, "$this$toCharArray");
        AbstractList.f20424a.m8317b(i, i2, str.length());
        char[] cArr = new char[i2 - i];
        str.getChars(i, i2, cArr, 0);
        return cArr;
    }

    @bwy(m8750a = "1.3")
    @ExperimentalStdlibApi
    @NotNull
    /* renamed from: a */
    public static final String m4038a(@NotNull byte[] bArr) {
        cji.m5162f(bArr, "$this$decodeToString");
        return new String(bArr, Charsets.f20995a);
    }

    /* renamed from: a */
    public static /* synthetic */ String m4034a(byte[] bArr, int i, int i2, boolean z, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = bArr.length;
        }
        if ((i3 & 4) != 0) {
            z = false;
        }
        return cpl.m4035a(bArr, i, i2, z);
    }

    @bwy(m8750a = "1.3")
    @ExperimentalStdlibApi
    @NotNull
    /* renamed from: a */
    public static final String m4035a(@NotNull byte[] bArr, int i, int i2, boolean z) {
        cji.m5162f(bArr, "$this$decodeToString");
        AbstractList.f20424a.m8317b(i, i2, bArr.length);
        if (!z) {
            return new String(bArr, i, i2 - i, Charsets.f20995a);
        }
        String charBuffer = Charsets.f20995a.newDecoder().onMalformedInput(CodingErrorAction.REPORT).onUnmappableCharacter(CodingErrorAction.REPORT).decode(ByteBuffer.wrap(bArr, i, i2 - i)).toString();
        cji.m5175b(charBuffer, "decoder.decode(ByteBuffe…- startIndex)).toString()");
        return charBuffer;
    }

    @bwy(m8750a = "1.3")
    @ExperimentalStdlibApi
    @NotNull
    /* renamed from: k */
    public static final byte[] m4002k(@NotNull String str) {
        cji.m5162f(str, "$this$encodeToByteArray");
        byte[] bytes = str.getBytes(Charsets.f20995a);
        cji.m5175b(bytes, "(this as java.lang.String).getBytes(charset)");
        return bytes;
    }

    /* renamed from: a */
    public static /* synthetic */ byte[] m4061a(String str, int i, int i2, boolean z, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        if ((i3 & 2) != 0) {
            i2 = str.length();
        }
        if ((i3 & 4) != 0) {
            z = false;
        }
        return cpl.m4062a(str, i, i2, z);
    }

    @bwy(m8750a = "1.3")
    @ExperimentalStdlibApi
    @NotNull
    /* renamed from: a */
    public static final byte[] m4062a(@NotNull String str, int i, int i2, boolean z) {
        cji.m5162f(str, "$this$encodeToByteArray");
        AbstractList.f20424a.m8317b(i, i2, str.length());
        if (!z) {
            String substring = str.substring(i, i2);
            cji.m5175b(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            Charset charset = Charsets.f20995a;
            if (substring != null) {
                byte[] bytes = substring.getBytes(charset);
                cji.m5175b(bytes, "(this as java.lang.String).getBytes(charset)");
                return bytes;
            }
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        ByteBuffer encode = Charsets.f20995a.newEncoder().onMalformedInput(CodingErrorAction.REPORT).onUnmappableCharacter(CodingErrorAction.REPORT).encode(CharBuffer.wrap(str, i, i2));
        if (encode.hasArray() && encode.arrayOffset() == 0) {
            int remaining = encode.remaining();
            byte[] array = encode.array();
            if (array == null) {
                cji.m5196a();
            }
            if (remaining == array.length) {
                byte[] array2 = encode.array();
                cji.m5175b(array2, "byteBuffer.array()");
                return array2;
            }
        }
        byte[] bArr = new byte[encode.remaining()];
        encode.get(bArr);
        return bArr;
    }

    @cey
    /* renamed from: p */
    private static final char[] m3997p(@NotNull String str) {
        if (str != null) {
            char[] charArray = str.toCharArray();
            cji.m5175b(charArray, "(this as java.lang.String).toCharArray()");
            return charArray;
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }

    /* renamed from: a */
    static /* synthetic */ char[] m4044a(String str, char[] cArr, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 2) != 0) {
            i = 0;
        }
        if ((i4 & 4) != 0) {
            i2 = 0;
        }
        if ((i4 & 8) != 0) {
            i3 = str.length();
        }
        if (str != null) {
            str.getChars(i2, i3, cArr, i);
            return cArr;
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }

    @cey
    /* renamed from: a */
    private static final char[] m4045a(@NotNull String str, char[] cArr, int i, int i2, int i3) {
        if (str != null) {
            str.getChars(i2, i3, cArr, i);
            return cArr;
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }

    @cey
    /* renamed from: a */
    private static final String m4043a(@NotNull String str, Object... objArr) {
        String format = String.format(str, Arrays.copyOf(objArr, objArr.length));
        cji.m5175b(format, "java.lang.String.format(this, *args)");
        return format;
    }

    @cey
    /* renamed from: a */
    private static final String m4040a(@NotNull ckm ckmVar, String str, Object... objArr) {
        String format = String.format(str, Arrays.copyOf(objArr, objArr.length));
        cji.m5175b(format, "java.lang.String.format(format, *args)");
        return format;
    }

    @cey
    /* renamed from: a */
    private static final String m4046a(@NotNull String str, Locale locale, Object... objArr) {
        String format = String.format(locale, str, Arrays.copyOf(objArr, objArr.length));
        cji.m5175b(format, "java.lang.String.format(locale, this, *args)");
        return format;
    }

    @cey
    /* renamed from: a */
    private static final String m4039a(@NotNull ckm ckmVar, Locale locale, String str, Object... objArr) {
        String format = String.format(locale, str, Arrays.copyOf(objArr, objArr.length));
        cji.m5175b(format, "java.lang.String.format(locale, format, *args)");
        return format;
    }

    /* renamed from: a */
    public static /* synthetic */ List m4069a(CharSequence charSequence, Pattern pattern, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        return cpl.m4070a(charSequence, pattern, i);
    }

    @NotNull
    /* renamed from: a */
    public static final List<String> m4070a(@NotNull CharSequence charSequence, @NotNull Pattern pattern, int i) {
        cji.m5162f(charSequence, "$this$split");
        cji.m5162f(pattern, FilenameSelector.REGEX_KEY);
        if (i >= 0) {
            if (i == 0) {
                i = -1;
            }
            String[] split = pattern.split(charSequence, i);
            cji.m5175b(split, "regex.split(this, if (limit == 0) -1 else limit)");
            return bzb.m8093d((Object[]) split);
        }
        throw new IllegalArgumentException(("Limit must be non-negative, but was " + i + FilenameUtils.EXTENSION_SEPARATOR).toString());
    }

    @cey
    /* renamed from: f */
    private static final String m4006f(@NotNull String str, int i) {
        if (str != null) {
            String substring = str.substring(i);
            cji.m5175b(substring, "(this as java.lang.String).substring(startIndex)");
            return substring;
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }

    @cey
    /* renamed from: b */
    private static final String m4025b(@NotNull String str, int i, int i2) {
        if (str != null) {
            String substring = str.substring(i, i2);
            cji.m5175b(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            return substring;
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }

    /* renamed from: b */
    public static /* synthetic */ boolean m4020b(String str, String str2, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return cpl.m4021b(str, str2, z);
    }

    /* renamed from: b */
    public static final boolean m4021b(@NotNull String str, @NotNull String str2, boolean z) {
        cji.m5162f(str, "$this$startsWith");
        cji.m5162f(str2, "prefix");
        if (!z) {
            return str.startsWith(str2);
        }
        return cpl.m4060a(str, 0, str2, 0, str2.length(), z);
    }

    /* renamed from: a */
    public static /* synthetic */ boolean m4055a(String str, String str2, int i, boolean z, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            z = false;
        }
        return cpl.m4056a(str, str2, i, z);
    }

    /* renamed from: a */
    public static final boolean m4056a(@NotNull String str, @NotNull String str2, int i, boolean z) {
        cji.m5162f(str, "$this$startsWith");
        cji.m5162f(str2, "prefix");
        if (!z) {
            return str.startsWith(str2, i);
        }
        return cpl.m4060a(str, i, str2, 0, str2.length(), z);
    }

    /* renamed from: c */
    public static /* synthetic */ boolean m4013c(String str, String str2, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return cpl.m4014c(str, str2, z);
    }

    /* renamed from: c */
    public static final boolean m4014c(@NotNull String str, @NotNull String str2, boolean z) {
        cji.m5162f(str, "$this$endsWith");
        cji.m5162f(str2, "suffix");
        if (!z) {
            return str.endsWith(str2);
        }
        return cpl.m4060a(str, str.length() - str2.length(), str2, 0, str2.length(), true);
    }

    @cey
    /* renamed from: a */
    private static final String m4036a(byte[] bArr, int i, int i2, Charset charset) {
        return new String(bArr, i, i2, charset);
    }

    @cey
    /* renamed from: a */
    private static final String m4033a(byte[] bArr, Charset charset) {
        return new String(bArr, charset);
    }

    @cey
    /* renamed from: a */
    private static final String m4037a(byte[] bArr, int i, int i2) {
        return new String(bArr, i, i2, Charsets.f20995a);
    }

    @cey
    /* renamed from: b */
    private static final String m4018b(byte[] bArr) {
        return new String(bArr, Charsets.f20995a);
    }

    @cey
    /* renamed from: b */
    private static final String m4017b(char[] cArr) {
        return new String(cArr);
    }

    @cey
    /* renamed from: b */
    private static final String m4016b(char[] cArr, int i, int i2) {
        return new String(cArr, i, i2);
    }

    @cey
    /* renamed from: a */
    private static final String m4029a(int[] iArr, int i, int i2) {
        return new String(iArr, i, i2);
    }

    @cey
    /* renamed from: a */
    private static final String m4042a(StringBuffer stringBuffer) {
        return new String(stringBuffer);
    }

    @cey
    /* renamed from: c */
    private static final String m4011c(StringBuilder sb) {
        return new String(sb);
    }

    @cey
    /* renamed from: g */
    private static final int m4005g(@NotNull String str, int i) {
        if (str != null) {
            return str.codePointAt(i);
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }

    @cey
    /* renamed from: h */
    private static final int m4004h(@NotNull String str, int i) {
        if (str != null) {
            return str.codePointBefore(i);
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }

    @cey
    /* renamed from: c */
    private static final int m4015c(@NotNull String str, int i, int i2) {
        if (str != null) {
            return str.codePointCount(i, i2);
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }

    /* renamed from: d */
    public static /* synthetic */ int m4008d(String str, String str2, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return cpl.m4009d(str, str2, z);
    }

    /* renamed from: d */
    public static final int m4009d(@NotNull String str, @NotNull String str2, boolean z) {
        cji.m5162f(str, "$this$compareTo");
        cji.m5162f(str2, "other");
        if (z) {
            return str.compareToIgnoreCase(str2);
        }
        return str.compareTo(str2);
    }

    @cey
    /* renamed from: a */
    private static final boolean m4058a(@NotNull String str, CharSequence charSequence) {
        if (str != null) {
            return str.contentEquals(charSequence);
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }

    @cey
    /* renamed from: a */
    private static final boolean m4050a(@NotNull String str, StringBuffer stringBuffer) {
        if (str != null) {
            return str.contentEquals(stringBuffer);
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }

    @cey
    /* renamed from: q */
    private static final String m3996q(@NotNull String str) {
        if (str != null) {
            String intern = str.intern();
            cji.m5175b(intern, "(this as java.lang.String).intern()");
            return intern;
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }

    /* renamed from: a */
    public static final boolean m4074a(@NotNull CharSequence charSequence) {
        boolean z;
        cji.m5162f(charSequence, "$this$isBlank");
        if (charSequence.length() != 0) {
            cme f = cpl.m3851f(charSequence);
            if (!(f instanceof Collection) || !((Collection) f).isEmpty()) {
                Iterator<Integer> it = f.iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (!cov.m4248a(charSequence.charAt(((cai) it).mo4828b()))) {
                            z = false;
                            break;
                        }
                    } else {
                        z = true;
                        break;
                    }
                }
            } else {
                z = true;
            }
            if (!z) {
                return false;
            }
        }
        return true;
    }

    @cey
    /* renamed from: d */
    private static final int m4010d(@NotNull String str, int i, int i2) {
        if (str != null) {
            return str.offsetByCodePoints(i, i2);
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }

    /* renamed from: a */
    public static final boolean m4072a(@NotNull CharSequence charSequence, int i, @NotNull CharSequence charSequence2, int i2, int i3, boolean z) {
        cji.m5162f(charSequence, "$this$regionMatches");
        cji.m5162f(charSequence2, "other");
        if (!(charSequence instanceof String) || !(charSequence2 instanceof String)) {
            return cpl.m3928b(charSequence, i, charSequence2, i2, i3, z);
        }
        return cpl.m4060a((String) charSequence, i, (String) charSequence2, i2, i3, z);
    }

    /* renamed from: a */
    public static final boolean m4060a(@NotNull String str, int i, @NotNull String str2, int i2, int i3, boolean z) {
        cji.m5162f(str, "$this$regionMatches");
        cji.m5162f(str2, "other");
        if (!z) {
            return str.regionMatches(i, str2, i2, i3);
        }
        return str.regionMatches(z, i, str2, i2, i3);
    }

    @cey
    /* renamed from: c */
    private static final String m4012c(@NotNull String str, Locale locale) {
        if (str != null) {
            String lowerCase = str.toLowerCase(locale);
            cji.m5175b(lowerCase, "(this as java.lang.String).toLowerCase(locale)");
            return lowerCase;
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }

    @cey
    /* renamed from: d */
    private static final String m4007d(@NotNull String str, Locale locale) {
        if (str != null) {
            String upperCase = str.toUpperCase(locale);
            cji.m5175b(upperCase, "(this as java.lang.String).toUpperCase(locale)");
            return upperCase;
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }

    @cey
    /* renamed from: a */
    private static final byte[] m4049a(@NotNull String str, Charset charset) {
        if (str != null) {
            byte[] bytes = str.getBytes(charset);
            cji.m5175b(bytes, "(this as java.lang.String).getBytes(charset)");
            return bytes;
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }

    /* renamed from: a */
    static /* synthetic */ byte[] m4048a(String str, Charset charset, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = Charsets.f20995a;
        }
        if (str != null) {
            byte[] bytes = str.getBytes(charset);
            cji.m5175b(bytes, "(this as java.lang.String).getBytes(charset)");
            return bytes;
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }

    @cey
    /* renamed from: i */
    private static final Pattern m4003i(@NotNull String str, int i) {
        Pattern compile = Pattern.compile(str, i);
        cji.m5175b(compile, "java.util.regex.Pattern.compile(this, flags)");
        return compile;
    }

    @NotNull
    /* renamed from: l */
    public static final String m4001l(@NotNull String str) {
        cji.m5162f(str, "$this$capitalize");
        if (!(str.length() > 0) || !Character.isLowerCase(str.charAt(0))) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        String substring = str.substring(0, 1);
        cji.m5175b(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        if (substring != null) {
            String upperCase = substring.toUpperCase();
            cji.m5175b(upperCase, "(this as java.lang.String).toUpperCase()");
            sb.append(upperCase);
            String substring2 = str.substring(1);
            cji.m5175b(substring2, "(this as java.lang.String).substring(startIndex)");
            sb.append(substring2);
            return sb.toString();
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }

    @bwy(m8750a = "1.3")
    @ExperimentalStdlibApi
    @cez
    @NotNull
    /* renamed from: a */
    public static final String m4047a(@NotNull String str, @NotNull Locale locale) {
        cji.m5162f(str, "$this$capitalize");
        cji.m5162f(locale, "locale");
        if (str.length() > 0) {
            char charAt = str.charAt(0);
            if (Character.isLowerCase(charAt)) {
                StringBuilder sb = new StringBuilder();
                char titleCase = Character.toTitleCase(charAt);
                if (titleCase != Character.toUpperCase(charAt)) {
                    sb.append(titleCase);
                } else {
                    String substring = str.substring(0, 1);
                    cji.m5175b(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                    if (substring != null) {
                        String upperCase = substring.toUpperCase(locale);
                        cji.m5175b(upperCase, "(this as java.lang.String).toUpperCase(locale)");
                        sb.append(upperCase);
                    } else {
                        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                    }
                }
                String substring2 = str.substring(1);
                cji.m5175b(substring2, "(this as java.lang.String).substring(startIndex)");
                sb.append(substring2);
                String sb2 = sb.toString();
                cji.m5175b(sb2, "StringBuilder().apply(builderAction).toString()");
                return sb2;
            }
        }
        return str;
    }

    @NotNull
    /* renamed from: m */
    public static final String m4000m(@NotNull String str) {
        cji.m5162f(str, "$this$decapitalize");
        if (!(str.length() > 0) || !Character.isUpperCase(str.charAt(0))) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        String substring = str.substring(0, 1);
        cji.m5175b(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        if (substring != null) {
            String lowerCase = substring.toLowerCase();
            cji.m5175b(lowerCase, "(this as java.lang.String).toLowerCase()");
            sb.append(lowerCase);
            String substring2 = str.substring(1);
            cji.m5175b(substring2, "(this as java.lang.String).substring(startIndex)");
            sb.append(substring2);
            return sb.toString();
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }

    @bwy(m8750a = "1.3")
    @ExperimentalStdlibApi
    @cez
    @NotNull
    /* renamed from: b */
    public static final String m4019b(@NotNull String str, @NotNull Locale locale) {
        cji.m5162f(str, "$this$decapitalize");
        cji.m5162f(locale, "locale");
        if (!(str.length() > 0) || Character.isLowerCase(str.charAt(0))) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        String substring = str.substring(0, 1);
        cji.m5175b(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        if (substring != null) {
            String lowerCase = substring.toLowerCase(locale);
            cji.m5175b(lowerCase, "(this as java.lang.String).toLowerCase(locale)");
            sb.append(lowerCase);
            String substring2 = str.substring(1);
            cji.m5175b(substring2, "(this as java.lang.String).substring(startIndex)");
            sb.append(substring2);
            return sb.toString();
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }

    @NotNull
    /* renamed from: a */
    public static final String m4073a(@NotNull CharSequence charSequence, int i) {
        cji.m5162f(charSequence, "$this$repeat");
        int i2 = 1;
        if (i >= 0) {
            switch (i) {
                case 0:
                    return "";
                case 1:
                    return charSequence.toString();
                default:
                    switch (charSequence.length()) {
                        case 0:
                            return "";
                        case 1:
                            char charAt = charSequence.charAt(0);
                            char[] cArr = new char[i];
                            for (int i3 = 0; i3 < i; i3++) {
                                cArr[i3] = charAt;
                            }
                            return new String(cArr);
                        default:
                            StringBuilder sb = new StringBuilder(charSequence.length() * i);
                            if (1 <= i) {
                                while (true) {
                                    sb.append(charSequence);
                                    if (i2 != i) {
                                        i2++;
                                    }
                                }
                            }
                            String sb2 = sb.toString();
                            cji.m5175b(sb2, "sb.toString()");
                            return sb2;
                    }
            }
        } else {
            throw new IllegalArgumentException(("Count 'n' must be non-negative, but was " + i + FilenameUtils.EXTENSION_SEPARATOR).toString());
        }
    }

    @NotNull
    /* renamed from: a */
    public static final Comparator<String> m4041a(@NotNull ckm ckmVar) {
        cji.m5162f(ckmVar, "$this$CASE_INSENSITIVE_ORDER");
        Comparator<String> comparator = String.CASE_INSENSITIVE_ORDER;
        cji.m5175b(comparator, "java.lang.String.CASE_INSENSITIVE_ORDER");
        return comparator;
    }

    /* renamed from: a */
    static /* synthetic */ Pattern m4063a(String str, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        Pattern compile = Pattern.compile(str, i);
        cji.m5175b(compile, "java.util.regex.Pattern.compile(this, flags)");
        return compile;
    }
}
