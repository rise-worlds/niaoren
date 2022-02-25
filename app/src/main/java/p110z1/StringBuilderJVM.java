package p110z1;

import org.apache.tools.ant.types.selectors.SizeSelector;

@Metadata(m8864a = 5, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000T\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\f\n\u0002\u0010\r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0002\u0010\u0005\n\u0002\u0010\u0019\n\u0002\u0010\u0006\n\u0002\u0010\u0007\n\u0002\u0010\b\n\u0002\u0010\t\n\u0002\u0010\n\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u001a\u0012\u0010\u0000\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u0002\u001a\u001d\u0010\u0000\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0087\b\u001a\u001f\u0010\u0000\u001a\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u0005H\u0087\b\u001a\u0012\u0010\u0000\u001a\u00060\u0006j\u0002`\u0007*\u00060\u0006j\u0002`\u0007\u001a\u001f\u0010\u0000\u001a\u00060\u0006j\u0002`\u0007*\u00060\u0006j\u0002`\u00072\b\u0010\u0003\u001a\u0004\u0018\u00010\bH\u0087\b\u001a\u001f\u0010\u0000\u001a\u00060\u0006j\u0002`\u0007*\u00060\u0006j\u0002`\u00072\b\u0010\u0003\u001a\u0004\u0018\u00010\tH\u0087\b\u001a\u001d\u0010\u0000\u001a\u00060\u0006j\u0002`\u0007*\u00060\u0006j\u0002`\u00072\u0006\u0010\u0003\u001a\u00020\nH\u0087\b\u001a\u001d\u0010\u0000\u001a\u00060\u0006j\u0002`\u0007*\u00060\u0006j\u0002`\u00072\u0006\u0010\u0003\u001a\u00020\u000bH\u0087\b\u001a\u001d\u0010\u0000\u001a\u00060\u0006j\u0002`\u0007*\u00060\u0006j\u0002`\u00072\u0006\u0010\u0003\u001a\u00020\u0004H\u0087\b\u001a\u001d\u0010\u0000\u001a\u00060\u0006j\u0002`\u0007*\u00060\u0006j\u0002`\u00072\u0006\u0010\u0003\u001a\u00020\fH\u0087\b\u001a\u001f\u0010\u0000\u001a\u00060\u0006j\u0002`\u0007*\u00060\u0006j\u0002`\u00072\b\u0010\u0003\u001a\u0004\u0018\u00010\u0005H\u0087\b\u001a\u001d\u0010\u0000\u001a\u00060\u0006j\u0002`\u0007*\u00060\u0006j\u0002`\u00072\u0006\u0010\u0003\u001a\u00020\rH\u0087\b\u001a\u001d\u0010\u0000\u001a\u00060\u0006j\u0002`\u0007*\u00060\u0006j\u0002`\u00072\u0006\u0010\u0003\u001a\u00020\u000eH\u0087\b\u001a\u001d\u0010\u0000\u001a\u00060\u0006j\u0002`\u0007*\u00060\u0006j\u0002`\u00072\u0006\u0010\u0003\u001a\u00020\u000fH\u0087\b\u001a\u001d\u0010\u0000\u001a\u00060\u0006j\u0002`\u0007*\u00060\u0006j\u0002`\u00072\u0006\u0010\u0003\u001a\u00020\u0010H\u0087\b\u001a\u001d\u0010\u0000\u001a\u00060\u0006j\u0002`\u0007*\u00060\u0006j\u0002`\u00072\u0006\u0010\u0003\u001a\u00020\u0011H\u0087\b\u001a\u001f\u0010\u0000\u001a\u00060\u0006j\u0002`\u0007*\u00060\u0006j\u0002`\u00072\b\u0010\u0003\u001a\u0004\u0018\u00010\u0012H\u0087\b\u001a%\u0010\u0000\u001a\u00060\u0006j\u0002`\u0007*\u00060\u0006j\u0002`\u00072\u000e\u0010\u0003\u001a\n\u0018\u00010\u0006j\u0004\u0018\u0001`\u0007H\u0087\b\u001a\u0014\u0010\u0013\u001a\u00060\u0006j\u0002`\u0007*\u00060\u0006j\u0002`\u0007H\u0007\u001a!\u0010\u0014\u001a\u00020\u0015*\u00060\u0006j\u0002`\u00072\u0006\u0010\u0016\u001a\u00020\u000f2\u0006\u0010\u0003\u001a\u00020\u0004H\u0087\n¨\u0006\u0017"}, m8860e = {"appendln", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", SizeSelector.SIZE_KEY, "", "", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "Ljava/lang/StringBuffer;", "", "", "", "", "", "", "", "", "", "", "clear", "set", "", "index", "kotlin-stdlib"}, m8859f = "kotlin/text/StringsKt", m8857h = 1)
/* renamed from: z1.cpp */
/* loaded from: classes3.dex */
class StringBuilderJVM extends RegexExtensions {
    @cey
    /* renamed from: a */
    private static final void m4126a(@NotNull StringBuilder sb, int i, char c) {
        cji.m5162f(sb, "$this$set");
        sb.setCharAt(i, c);
    }

    @bwy(m8750a = "1.3")
    @NotNull
    /* renamed from: a */
    public static final StringBuilder m4132a(@NotNull StringBuilder sb) {
        cji.m5162f(sb, "$this$clear");
        sb.setLength(0);
        return sb;
    }

    @NotNull
    /* renamed from: a */
    public static final Appendable m4135a(@NotNull Appendable appendable) {
        cji.m5162f(appendable, "$this$appendln");
        Appendable append = appendable.append(cpx.f21033a);
        cji.m5175b(append, "append(SystemProperties.LINE_SEPARATOR)");
        return append;
    }

    @cey
    /* renamed from: a */
    private static final Appendable m4133a(@NotNull Appendable appendable, CharSequence charSequence) {
        Appendable append = appendable.append(charSequence);
        cji.m5175b(append, "append(value)");
        return cpl.m4135a(append);
    }

    @cey
    /* renamed from: a */
    private static final Appendable m4134a(@NotNull Appendable appendable, char c) {
        Appendable append = appendable.append(c);
        cji.m5175b(append, "append(value)");
        return cpl.m4135a(append);
    }

    @NotNull
    /* renamed from: b */
    public static final StringBuilder m4116b(@NotNull StringBuilder sb) {
        cji.m5162f(sb, "$this$appendln");
        sb.append(cpx.f21033a);
        cji.m5175b(sb, "append(SystemProperties.LINE_SEPARATOR)");
        return sb;
    }

    @cey
    /* renamed from: a */
    private static final StringBuilder m4121a(@NotNull StringBuilder sb, StringBuffer stringBuffer) {
        sb.append(stringBuffer);
        cji.m5175b(sb, "append(value)");
        return cpl.m4116b(sb);
    }

    @cey
    /* renamed from: a */
    private static final StringBuilder m4124a(@NotNull StringBuilder sb, CharSequence charSequence) {
        sb.append(charSequence);
        cji.m5175b(sb, "append(value)");
        return cpl.m4116b(sb);
    }

    @cey
    /* renamed from: a */
    private static final StringBuilder m4122a(@NotNull StringBuilder sb, String str) {
        sb.append(str);
        cji.m5175b(sb, "append(value)");
        return cpl.m4116b(sb);
    }

    @cey
    /* renamed from: a */
    private static final StringBuilder m4123a(@NotNull StringBuilder sb, Object obj) {
        sb.append(obj);
        cji.m5175b(sb, "append(value)");
        return cpl.m4116b(sb);
    }

    @cey
    /* renamed from: a */
    private static final StringBuilder m4120a(@NotNull StringBuilder sb, StringBuilder sb2) {
        sb.append((CharSequence) sb2);
        cji.m5175b(sb, "append(value)");
        return cpl.m4116b(sb);
    }

    @cey
    /* renamed from: a */
    private static final StringBuilder m4117a(@NotNull StringBuilder sb, char[] cArr) {
        sb.append(cArr);
        cji.m5175b(sb, "append(value)");
        return cpl.m4116b(sb);
    }

    @cey
    /* renamed from: a */
    private static final StringBuilder m4130a(@NotNull StringBuilder sb, char c) {
        sb.append(c);
        cji.m5175b(sb, "append(value)");
        return cpl.m4116b(sb);
    }

    @cey
    /* renamed from: a */
    private static final StringBuilder m4118a(@NotNull StringBuilder sb, boolean z) {
        sb.append(z);
        cji.m5175b(sb, "append(value)");
        return cpl.m4116b(sb);
    }

    @cey
    /* renamed from: a */
    private static final StringBuilder m4127a(@NotNull StringBuilder sb, int i) {
        sb.append(i);
        cji.m5175b(sb, "append(value)");
        return cpl.m4116b(sb);
    }

    @cey
    /* renamed from: a */
    private static final StringBuilder m4119a(@NotNull StringBuilder sb, short s) {
        sb.append((int) s);
        cji.m5175b(sb, "append(value.toInt())");
        return cpl.m4116b(sb);
    }

    @cey
    /* renamed from: a */
    private static final StringBuilder m4131a(@NotNull StringBuilder sb, byte b) {
        sb.append((int) b);
        cji.m5175b(sb, "append(value.toInt())");
        return cpl.m4116b(sb);
    }

    @cey
    /* renamed from: a */
    private static final StringBuilder m4125a(@NotNull StringBuilder sb, long j) {
        sb.append(j);
        cji.m5175b(sb, "append(value)");
        return cpl.m4116b(sb);
    }

    @cey
    /* renamed from: a */
    private static final StringBuilder m4128a(@NotNull StringBuilder sb, float f) {
        sb.append(f);
        cji.m5175b(sb, "append(value)");
        return cpl.m4116b(sb);
    }

    @cey
    /* renamed from: a */
    private static final StringBuilder m4129a(@NotNull StringBuilder sb, double d) {
        sb.append(d);
        cji.m5175b(sb, "append(value)");
        return cpl.m4116b(sb);
    }
}
