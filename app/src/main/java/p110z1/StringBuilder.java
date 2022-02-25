package p110z1;

import com.googlecode.tesseract.android.TessBaseAPI;
import org.apache.tools.ant.types.selectors.SizeSelector;

@Metadata(m8864a = 5, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000B\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\r\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0007\u001a.\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u001b\u0010\u0004\u001a\u0017\u0012\b\u0012\u00060\u0006j\u0002`\u0007\u0012\u0004\u0012\u00020\b0\u0005¢\u0006\u0002\b\tH\u0087\b\u001a&\u0010\u0000\u001a\u00020\u00012\u001b\u0010\u0004\u001a\u0017\u0012\b\u0012\u00060\u0006j\u0002`\u0007\u0012\u0004\u0012\u00020\b0\u0005¢\u0006\u0002\b\tH\u0087\b\u001a5\u0010\n\u001a\u0002H\u000b\"\f\b\u0000\u0010\u000b*\u00060\fj\u0002`\r*\u0002H\u000b2\u0016\u0010\u000e\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00100\u000f\"\u0004\u0018\u00010\u0010¢\u0006\u0002\u0010\u0011\u001a/\u0010\n\u001a\u00060\u0006j\u0002`\u0007*\u00060\u0006j\u0002`\u00072\u0016\u0010\u000e\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00120\u000f\"\u0004\u0018\u00010\u0012¢\u0006\u0002\u0010\u0013\u001a/\u0010\n\u001a\u00060\u0006j\u0002`\u0007*\u00060\u0006j\u0002`\u00072\u0016\u0010\u000e\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00010\u000f\"\u0004\u0018\u00010\u0001¢\u0006\u0002\u0010\u0014\u001a9\u0010\u0015\u001a\u00020\b\"\u0004\b\u0000\u0010\u000b*\u00060\fj\u0002`\r2\u0006\u0010\u0016\u001a\u0002H\u000b2\u0014\u0010\u0017\u001a\u0010\u0012\u0004\u0012\u0002H\u000b\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u0005H\u0000¢\u0006\u0002\u0010\u0018¨\u0006\u0019"}, m8860e = {"buildString", "", "capacity", "", "builderAction", "Lkotlin/Function1;", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "", "Lkotlin/ExtensionFunctionType;", "append", TessBaseAPI.f9204e, "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", SizeSelector.SIZE_KEY, "", "", "(Ljava/lang/Appendable;[Ljava/lang/CharSequence;)Ljava/lang/Appendable;", "", "(Ljava/lang/StringBuilder;[Ljava/lang/Object;)Ljava/lang/StringBuilder;", "(Ljava/lang/StringBuilder;[Ljava/lang/String;)Ljava/lang/StringBuilder;", "appendElement", "element", "transform", "(Ljava/lang/Appendable;Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)V", "kotlin-stdlib"}, m8859f = "kotlin/text/StringsKt", m8857h = 1)
/* renamed from: z1.cpq */
/* loaded from: classes3.dex */
class StringBuilder extends StringBuilderJVM {
    @cey
    /* renamed from: a */
    private static final String m4110a(chd<? super StringBuilder, Unit> chdVar) {
        StringBuilder sb = new StringBuilder();
        chdVar.invoke(sb);
        String sb2 = sb.toString();
        cji.m5175b(sb2, "StringBuilder().apply(builderAction).toString()");
        return sb2;
    }

    @bwy(m8750a = "1.1")
    @cey
    /* renamed from: a */
    private static final String m4115a(int i, chd<? super StringBuilder, Unit> chdVar) {
        StringBuilder sb = new StringBuilder(i);
        chdVar.invoke(sb);
        String sb2 = sb.toString();
        cji.m5175b(sb2, "StringBuilder(capacity).…builderAction).toString()");
        return sb2;
    }

    @NotNull
    /* renamed from: a */
    public static final <T extends Appendable> T m4113a(@NotNull T t, @NotNull CharSequence... charSequenceArr) {
        cji.m5162f(t, "$this$append");
        cji.m5162f(charSequenceArr, SizeSelector.SIZE_KEY);
        for (CharSequence charSequence : charSequenceArr) {
            t.append(charSequence);
        }
        return t;
    }

    @NotNull
    /* renamed from: a */
    public static final StringBuilder m4111a(@NotNull StringBuilder sb, @NotNull String... strArr) {
        cji.m5162f(sb, "$this$append");
        cji.m5162f(strArr, SizeSelector.SIZE_KEY);
        for (String str : strArr) {
            sb.append(str);
        }
        return sb;
    }

    @NotNull
    /* renamed from: a */
    public static final StringBuilder m4112a(@NotNull StringBuilder sb, @NotNull Object... objArr) {
        cji.m5162f(sb, "$this$append");
        cji.m5162f(objArr, SizeSelector.SIZE_KEY);
        for (Object obj : objArr) {
            sb.append(obj);
        }
        return sb;
    }

    /* renamed from: a */
    public static final <T> void m4114a(@NotNull Appendable appendable, T t, @dbs chd<? super T, ? extends CharSequence> chdVar) {
        cji.m5162f(appendable, "$this$appendElement");
        if (chdVar != null) {
            appendable.append((CharSequence) chdVar.invoke(t));
            return;
        }
        if (t != null ? t instanceof CharSequence : true) {
            appendable.append((CharSequence) t);
        } else if (t instanceof Character) {
            appendable.append(((Character) t).charValue());
        } else {
            appendable.append(String.valueOf(t));
        }
    }
}
