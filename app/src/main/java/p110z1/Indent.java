package p110z1;

import java.util.ArrayList;
import java.util.List;

@Metadata(m8864a = 5, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u000b\u001a!\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0002H\u0002¢\u0006\u0002\b\u0004\u001a\u0011\u0010\u0005\u001a\u00020\u0006*\u00020\u0002H\u0002¢\u0006\u0002\b\u0007\u001a\u0014\u0010\b\u001a\u00020\u0002*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u0002\u001aJ\u0010\t\u001a\u00020\u0002*\b\u0012\u0004\u0012\u00020\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00062\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u00012\u0014\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001H\u0082\b¢\u0006\u0002\b\u000e\u001a\u0014\u0010\u000f\u001a\u00020\u0002*\u00020\u00022\b\b\u0002\u0010\u0010\u001a\u00020\u0002\u001a\u001e\u0010\u0011\u001a\u00020\u0002*\u00020\u00022\b\b\u0002\u0010\u0010\u001a\u00020\u00022\b\b\u0002\u0010\u0012\u001a\u00020\u0002\u001a\n\u0010\u0013\u001a\u00020\u0002*\u00020\u0002\u001a\u0014\u0010\u0014\u001a\u00020\u0002*\u00020\u00022\b\b\u0002\u0010\u0012\u001a\u00020\u0002¨\u0006\u0015"}, m8860e = {"getIndentFunction", "Lkotlin/Function1;", "", "indent", "getIndentFunction$StringsKt__IndentKt", "indentWidth", "", "indentWidth$StringsKt__IndentKt", "prependIndent", "reindent", "", "resultSizeEstimate", "indentAddFunction", "indentCutFunction", "reindent$StringsKt__IndentKt", "replaceIndent", "newIndent", "replaceIndentByMargin", "marginPrefix", "trimIndent", "trimMargin", "kotlin-stdlib"}, m8859f = "kotlin/text/StringsKt", m8857h = 1)
/* renamed from: z1.cpm */
/* loaded from: classes3.dex */
class Indent {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: Indent.kt */
    @Metadata(m8864a = 3, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\n\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0003"}, m8860e = {"<anonymous>", "", "line", "invoke"})
    /* renamed from: z1.cpm$a */
    /* loaded from: classes3.dex */
    public static final class C5081a extends Lambda implements chd<String, String> {
        public static final C5081a INSTANCE = new C5081a();

        C5081a() {
            super(1);
        }

        @NotNull
        public final String invoke(@NotNull String str) {
            cji.m5162f(str, "line");
            return str;
        }
    }

    /* renamed from: a */
    public static /* synthetic */ String m4149a(String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str2 = "|";
        }
        return cpl.m4150a(str, str2);
    }

    @NotNull
    /* renamed from: a */
    public static final String m4150a(@NotNull String str, @NotNull String str2) {
        cji.m5162f(str, "$this$trimMargin");
        cji.m5162f(str2, "marginPrefix");
        return cpl.m4148a(str, "", str2);
    }

    /* renamed from: a */
    public static /* synthetic */ String m4147a(String str, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str2 = "";
        }
        if ((i & 2) != 0) {
            str3 = "|";
        }
        return cpl.m4148a(str, str2, str3);
    }

    @NotNull
    /* renamed from: a */
    public static final String m4148a(@NotNull String str, @NotNull String str2, @NotNull String str3) {
        Appendable a;
        String str4;
        int i;
        String invoke;
        cji.m5162f(str, "$this$replaceIndentByMargin");
        cji.m5162f(str2, "newIndent");
        cji.m5162f(str3, "marginPrefix");
        if (!cpl.m4074a((CharSequence) str3)) {
            List<String> i2 = cpl.m3848i((CharSequence) str);
            int length = str.length() + (str2.length() * i2.size());
            chd<String, String> c = m4142c(str2);
            int a2 = bzk.m6810a((List) i2);
            ArrayList arrayList = new ArrayList();
            int i3 = 0;
            for (Object obj : i2) {
                i3++;
                if (i3 < 0) {
                    bzk.m6800b();
                }
                String str5 = (String) obj;
                String str6 = null;
                if ((i3 == 0 || i3 == a2) && cpl.m4074a((CharSequence) str5)) {
                    str5 = null;
                } else {
                    int length2 = str5.length();
                    int i4 = 0;
                    while (true) {
                        if (i4 >= length2) {
                            i = -1;
                            break;
                        } else if (!cov.m4248a(str4.charAt(i4))) {
                            i = i4;
                            break;
                        } else {
                            i4++;
                        }
                    }
                    if (i != -1 && cpl.m4055a(str5, str3, i, false, 4, (Object) null)) {
                        int length3 = i + str3.length();
                        if (str5 != null) {
                            str6 = str5.substring(length3);
                            cji.m5175b(str6, "(this as java.lang.String).substring(startIndex)");
                        } else {
                            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                        }
                    }
                    if (!(str6 == null || (invoke = c.invoke(str6)) == null)) {
                        str5 = invoke;
                    }
                }
                if (str5 != null) {
                    arrayList.add(str5);
                }
            }
            a = bzk.m6682a(arrayList, new StringBuilder(length), (r14 & 2) != 0 ? ", " : "\n", (r14 & 4) != 0 ? "" : null, (r14 & 8) != 0 ? "" : null, (r14 & 16) != 0 ? -1 : 0, (r14 & 32) != 0 ? "..." : null, (r14 & 64) != 0 ? null : null);
            String sb = ((StringBuilder) a).toString();
            cji.m5175b(sb, "mapIndexedNotNull { inde…\"\\n\")\n        .toString()");
            return sb;
        }
        throw new IllegalArgumentException("marginPrefix must be non-blank string.".toString());
    }

    @NotNull
    /* renamed from: a */
    public static final String m4151a(@NotNull String str) {
        cji.m5162f(str, "$this$trimIndent");
        return cpl.m4144b(str, "");
    }

    /* renamed from: b */
    public static /* synthetic */ String m4143b(String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str2 = "";
        }
        return cpl.m4144b(str, str2);
    }

    @NotNull
    /* renamed from: b */
    public static final String m4144b(@NotNull String str, @NotNull String str2) {
        Appendable a;
        String invoke;
        cji.m5162f(str, "$this$replaceIndent");
        cji.m5162f(str2, "newIndent");
        List<String> i = cpl.m3848i((CharSequence) str);
        List<String> list = i;
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            if (!cpl.m4074a((CharSequence) ((String) obj))) {
                arrayList.add(obj);
            }
        }
        ArrayList<String> arrayList2 = arrayList;
        ArrayList arrayList3 = new ArrayList(bzk.m6783a((Iterable) arrayList2, 10));
        for (String str3 : arrayList2) {
            arrayList3.add(Integer.valueOf(m4145b(str3)));
        }
        Integer num = (Integer) bzk.m6712F(arrayList3);
        int i2 = 0;
        int intValue = num != null ? num.intValue() : 0;
        int length = str.length() + (str2.length() * i.size());
        chd<String, String> c = m4142c(str2);
        int a2 = bzk.m6810a((List) i);
        ArrayList arrayList4 = new ArrayList();
        for (Object obj2 : list) {
            i2++;
            if (i2 < 0) {
                bzk.m6800b();
            }
            String str4 = (String) obj2;
            if ((i2 == 0 || i2 == a2) && cpl.m4074a((CharSequence) str4)) {
                str4 = null;
            } else {
                String f = cpl.m3758f(str4, intValue);
                if (!(f == null || (invoke = c.invoke(f)) == null)) {
                    str4 = invoke;
                }
            }
            if (str4 != null) {
                arrayList4.add(str4);
            }
        }
        a = bzk.m6682a(arrayList4, new StringBuilder(length), (r14 & 2) != 0 ? ", " : "\n", (r14 & 4) != 0 ? "" : null, (r14 & 8) != 0 ? "" : null, (r14 & 16) != 0 ? -1 : 0, (r14 & 32) != 0 ? "..." : null, (r14 & 64) != 0 ? null : null);
        String sb = ((StringBuilder) a).toString();
        cji.m5175b(sb, "mapIndexedNotNull { inde…\"\\n\")\n        .toString()");
        return sb;
    }

    /* renamed from: c */
    public static /* synthetic */ String m4140c(String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str2 = "    ";
        }
        return cpl.m4141c(str, str2);
    }

    @NotNull
    /* renamed from: c */
    public static final String m4141c(@NotNull String str, @NotNull String str2) {
        cji.m5162f(str, "$this$prependIndent");
        cji.m5162f(str2, "indent");
        return coe.m4390a(coe.m4302u(cpl.m3849h((CharSequence) str), new C5083c(str2)), "\n", null, null, 0, null, null, 62, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: Indent.kt */
    @Metadata(m8864a = 3, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\n\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0003"}, m8860e = {"<anonymous>", "", "it", "invoke"})
    /* renamed from: z1.cpm$c */
    /* loaded from: classes3.dex */
    public static final class C5083c extends Lambda implements chd<String, String> {
        final /* synthetic */ String $indent;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C5083c(String str) {
            super(1);
            this.$indent = str;
        }

        @NotNull
        public final String invoke(@NotNull String str) {
            cji.m5162f(str, "it");
            if (cpl.m4074a((CharSequence) str)) {
                return str.length() < this.$indent.length() ? this.$indent : str;
            }
            return this.$indent + str;
        }
    }

    /* renamed from: b */
    private static final int m4145b(@NotNull String str) {
        String str2 = str;
        int length = str2.length();
        int i = 0;
        while (true) {
            if (i >= length) {
                i = -1;
                break;
            } else if (!cov.m4248a(str2.charAt(i))) {
                break;
            } else {
                i++;
            }
        }
        return i == -1 ? str.length() : i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: Indent.kt */
    @Metadata(m8864a = 3, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\n\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0003"}, m8860e = {"<anonymous>", "", "line", "invoke"})
    /* renamed from: z1.cpm$b */
    /* loaded from: classes3.dex */
    public static final class C5082b extends Lambda implements chd<String, String> {
        final /* synthetic */ String $indent;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C5082b(String str) {
            super(1);
            this.$indent = str;
        }

        @NotNull
        public final String invoke(@NotNull String str) {
            cji.m5162f(str, "line");
            return this.$indent + str;
        }
    }

    /* renamed from: c */
    private static final chd<String, String> m4142c(String str) {
        return str.length() == 0 ? C5081a.INSTANCE : new C5082b(str);
    }

    /* renamed from: a */
    private static final String m4146a(@NotNull List<String> list, int i, chd<? super String, String> chdVar, chd<? super String, String> chdVar2) {
        Appendable a;
        String invoke;
        int a2 = bzk.m6810a((List) list);
        ArrayList arrayList = new ArrayList();
        int i2 = 0;
        for (Object obj : list) {
            i2++;
            if (i2 < 0) {
                if (cfe.m5471a(1, 3, 0)) {
                    bzk.m6800b();
                } else {
                    throw new ArithmeticException("Index overflow has happened.");
                }
            }
            String str = (String) obj;
            if ((i2 == 0 || i2 == a2) && cpl.m4074a((CharSequence) str)) {
                str = null;
            } else {
                String invoke2 = chdVar2.invoke(str);
                if (!(invoke2 == null || (invoke = chdVar.invoke(invoke2)) == null)) {
                    str = invoke;
                }
            }
            if (str != null) {
                arrayList.add(str);
            }
        }
        a = bzk.m6682a(arrayList, new StringBuilder(i), (r14 & 2) != 0 ? ", " : "\n", (r14 & 4) != 0 ? "" : null, (r14 & 8) != 0 ? "" : null, (r14 & 16) != 0 ? -1 : 0, (r14 & 32) != 0 ? "..." : null, (r14 & 64) != 0 ? null : null);
        String sb = ((StringBuilder) a).toString();
        cji.m5175b(sb, "mapIndexedNotNull { inde…\"\\n\")\n        .toString()");
        return sb;
    }
}
