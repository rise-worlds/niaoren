package p110z1;

import java.util.SortedSet;
import java.util.TreeSet;

@Metadata(m8864a = 5, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u0018\n\u0000\n\u0002\u0010\f\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0087\b\u001a\u0010\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00010\u0006*\u00020\u0002¨\u0006\u0007"}, m8860e = {"elementAt", "", "", "index", "", "toSortedSet", "Ljava/util/SortedSet;", "kotlin-stdlib"}, m8859f = "kotlin/text/StringsKt", m8857h = 1)
/* renamed from: z1.cpv */
/* loaded from: classes3.dex */
class _StringsJvm extends cpu {
    @cey
    /* renamed from: c */
    private static final char m3837c(@NotNull CharSequence charSequence, int i) {
        return charSequence.charAt(i);
    }

    @NotNull
    /* renamed from: j */
    public static final SortedSet<Character> m3836j(@NotNull CharSequence charSequence) {
        cji.m5162f(charSequence, "$this$toSortedSet");
        return (SortedSet) cpl.m3807a(charSequence, new TreeSet());
    }
}
