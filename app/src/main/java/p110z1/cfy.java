package p110z1;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/* compiled from: FilePathComponents.kt */
@Metadata(m8864a = 5, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000$\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\u001a\u0011\u0010\u000b\u001a\u00020\f*\u00020\bH\u0002¢\u0006\u0002\b\r\u001a\u001c\u0010\u000e\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\fH\u0000\u001a\f\u0010\u0011\u001a\u00020\u0012*\u00020\u0002H\u0000\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0000\u0010\u0003\"\u0018\u0010\u0004\u001a\u00020\u0002*\u00020\u00028@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006\"\u0018\u0010\u0007\u001a\u00020\b*\u00020\u00028@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\n¨\u0006\u0013"}, m8860e = {"isRooted", "", "Ljava/io/File;", "(Ljava/io/File;)Z", "root", "getRoot", "(Ljava/io/File;)Ljava/io/File;", "rootName", "", "getRootName", "(Ljava/io/File;)Ljava/lang/String;", "getRootLength", "", "getRootLength$FilesKt__FilePathComponentsKt", "subPath", "beginIndex", "endIndex", "toComponents", "Lkotlin/io/FilePathComponents;", "kotlin-stdlib"}, m8859f = "kotlin/io/FilesKt", m8857h = 1)
/* renamed from: z1.cfy */
/* loaded from: classes3.dex */
class cfy {
    /* renamed from: a */
    private static final int m5366a(@NotNull String str) {
        int a;
        String str2 = str;
        int a2 = cpl.m3994a((CharSequence) str2, File.separatorChar, 0, false, 4, (Object) null);
        if (a2 == 0) {
            if (str.length() <= 1 || str.charAt(1) != File.separatorChar || (a = cpl.m3994a((CharSequence) str2, File.separatorChar, 2, false, 4, (Object) null)) < 0) {
                return 1;
            }
            int a3 = cpl.m3994a((CharSequence) str2, File.separatorChar, a + 1, false, 4, (Object) null);
            return a3 >= 0 ? a3 + 1 : str.length();
        } else if (a2 > 0 && str.charAt(a2 - 1) == ':') {
            return a2 + 1;
        } else {
            if (a2 != -1 || !cpl.m3933b((CharSequence) str2, ':', false, 2, (Object) null)) {
                return 0;
            }
            return str.length();
        }
    }

    @NotNull
    /* renamed from: a */
    public static final String m5368a(@NotNull File file) {
        cji.m5162f(file, "$this$rootName");
        String path = file.getPath();
        cji.m5175b(path, "path");
        String path2 = file.getPath();
        cji.m5175b(path2, "path");
        int a = m5366a(path2);
        if (path != null) {
            String substring = path.substring(0, a);
            cji.m5175b(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            return substring;
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }

    @NotNull
    /* renamed from: b */
    public static final File m5365b(@NotNull File file) {
        cji.m5162f(file, "$this$root");
        return new File(cfx.m5368a(file));
    }

    /* renamed from: c */
    public static final boolean m5364c(@NotNull File file) {
        cji.m5162f(file, "$this$isRooted");
        String path = file.getPath();
        cji.m5175b(path, "path");
        return m5366a(path) > 0;
    }

    @NotNull
    /* renamed from: d */
    public static final FilePathComponents m5363d(@NotNull File file) {
        ArrayList arrayList;
        cji.m5162f(file, "$this$toComponents");
        String path = file.getPath();
        cji.m5175b(path, "path");
        int a = m5366a(path);
        String substring = path.substring(0, a);
        cji.m5175b(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        String substring2 = path.substring(a);
        cji.m5175b(substring2, "(this as java.lang.String).substring(startIndex)");
        String str = substring2;
        if (str.length() == 0) {
            arrayList = bzk.m6816a();
        } else {
            List<String> b = cpl.m3910b((CharSequence) str, new char[]{File.separatorChar}, false, 0, 6, (Object) null);
            ArrayList arrayList2 = new ArrayList(bzk.m6783a((Iterable) b, 10));
            for (String str2 : b) {
                arrayList2.add(new File(str2));
            }
            arrayList = arrayList2;
        }
        return new FilePathComponents(new File(substring), arrayList);
    }

    @NotNull
    /* renamed from: a */
    public static final File m5367a(@NotNull File file, int i, int i2) {
        cji.m5162f(file, "$this$subPath");
        return cfx.m5363d(file).m5391a(i, i2);
    }
}
