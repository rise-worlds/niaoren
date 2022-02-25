package p110z1;

@Metadata(m8864a = 5, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000&\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\f\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0011\u001a\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0001\u001a\u0018\u0010\f\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\nH\u0000\u001a\r\u0010\u000e\u001a\u00020\u000f*\u00020\u0002H\u0087\b\u001a\r\u0010\u0010\u001a\u00020\u000f*\u00020\u0002H\u0087\b\u001a\r\u0010\u0011\u001a\u00020\u000f*\u00020\u0002H\u0087\b\u001a\r\u0010\u0012\u001a\u00020\u000f*\u00020\u0002H\u0087\b\u001a\r\u0010\u0013\u001a\u00020\u000f*\u00020\u0002H\u0087\b\u001a\r\u0010\u0014\u001a\u00020\u000f*\u00020\u0002H\u0087\b\u001a\r\u0010\u0015\u001a\u00020\u000f*\u00020\u0002H\u0087\b\u001a\r\u0010\u0016\u001a\u00020\u000f*\u00020\u0002H\u0087\b\u001a\r\u0010\u0017\u001a\u00020\u000f*\u00020\u0002H\u0087\b\u001a\r\u0010\u0018\u001a\u00020\u000f*\u00020\u0002H\u0087\b\u001a\r\u0010\u0019\u001a\u00020\u000f*\u00020\u0002H\u0087\b\u001a\r\u0010\u001a\u001a\u00020\u000f*\u00020\u0002H\u0087\b\u001a\r\u0010\u001b\u001a\u00020\u000f*\u00020\u0002H\u0087\b\u001a\n\u0010\u001c\u001a\u00020\u000f*\u00020\u0002\u001a\r\u0010\u001d\u001a\u00020\u0002*\u00020\u0002H\u0087\b\u001a\r\u0010\u001e\u001a\u00020\u0002*\u00020\u0002H\u0087\b\u001a\r\u0010\u001f\u001a\u00020\u0002*\u00020\u0002H\u0087\b\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u0015\u0010\u0005\u001a\u00020\u0006*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006 "}, m8860e = {"category", "Lkotlin/text/CharCategory;", "", "getCategory", "(C)Lkotlin/text/CharCategory;", "directionality", "Lkotlin/text/CharDirectionality;", "getDirectionality", "(C)Lkotlin/text/CharDirectionality;", "checkRadix", "", "radix", "digitOf", "char", "isDefined", "", "isDigit", "isHighSurrogate", "isISOControl", "isIdentifierIgnorable", "isJavaIdentifierPart", "isJavaIdentifierStart", "isLetter", "isLetterOrDigit", "isLowSurrogate", "isLowerCase", "isTitleCase", "isUpperCase", "isWhitespace", "toLowerCase", "toTitleCase", "toUpperCase", "kotlin-stdlib"}, m8859f = "kotlin/text/CharsKt", m8857h = 1)
/* renamed from: z1.cow */
/* loaded from: classes3.dex */
class CharJVM {
    @cey
    /* renamed from: d */
    private static final boolean m4243d(char c) {
        return Character.isDefined(c);
    }

    @cey
    /* renamed from: e */
    private static final boolean m4242e(char c) {
        return Character.isLetter(c);
    }

    @cey
    /* renamed from: f */
    private static final boolean m4241f(char c) {
        return Character.isLetterOrDigit(c);
    }

    @cey
    /* renamed from: g */
    private static final boolean m4240g(char c) {
        return Character.isDigit(c);
    }

    @cey
    /* renamed from: h */
    private static final boolean m4239h(char c) {
        return Character.isIdentifierIgnorable(c);
    }

    @cey
    /* renamed from: i */
    private static final boolean m4238i(char c) {
        return Character.isISOControl(c);
    }

    @cey
    /* renamed from: j */
    private static final boolean m4237j(char c) {
        return Character.isJavaIdentifierPart(c);
    }

    @cey
    /* renamed from: k */
    private static final boolean m4236k(char c) {
        return Character.isJavaIdentifierStart(c);
    }

    /* renamed from: a */
    public static final boolean m4248a(char c) {
        return Character.isWhitespace(c) || Character.isSpaceChar(c);
    }

    @cey
    /* renamed from: l */
    private static final boolean m4235l(char c) {
        return Character.isUpperCase(c);
    }

    @cey
    /* renamed from: m */
    private static final boolean m4234m(char c) {
        return Character.isLowerCase(c);
    }

    @cey
    /* renamed from: n */
    private static final char m4233n(char c) {
        return Character.toUpperCase(c);
    }

    @cey
    /* renamed from: o */
    private static final char m4232o(char c) {
        return Character.toLowerCase(c);
    }

    @cey
    /* renamed from: p */
    private static final boolean m4231p(char c) {
        return Character.isTitleCase(c);
    }

    @cey
    /* renamed from: q */
    private static final char m4230q(char c) {
        return Character.toTitleCase(c);
    }

    @NotNull
    /* renamed from: b */
    public static final CharCategory m4245b(char c) {
        return CharCategory.Companion.m4251a(Character.getType(c));
    }

    @NotNull
    /* renamed from: c */
    public static final CharDirectionality m4244c(char c) {
        return CharDirectionality.Companion.m4249a(Character.getDirectionality(c));
    }

    @cey
    /* renamed from: r */
    private static final boolean m4229r(char c) {
        return Character.isHighSurrogate(c);
    }

    @cey
    /* renamed from: s */
    private static final boolean m4228s(char c) {
        return Character.isLowSurrogate(c);
    }

    /* renamed from: a */
    public static final int m4247a(char c, int i) {
        return Character.digit((int) c, i);
    }

    @bwt
    /* renamed from: a */
    public static final int m4246a(int i) {
        if (2 <= i && 36 >= i) {
            return i;
        }
        throw new IllegalArgumentException("radix " + i + " was not in valid range " + new cme(2, 36));
    }
}
