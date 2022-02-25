package p110z1;

import com.stripe.android.CustomerSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.p105io.FilenameUtils;
import org.apache.commons.p105io.IOUtils;
import org.apache.tools.ant.taskdefs.optional.ejb.EjbJar;

/* compiled from: Utils.kt */
@Metadata(m8864a = 5, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000<\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\u001a(\u0010\t\u001a\u00020\u00022\b\b\u0002\u0010\n\u001a\u00020\u00012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0002\u001a(\u0010\r\u001a\u00020\u00022\b\b\u0002\u0010\n\u001a\u00020\u00012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0002\u001a8\u0010\u000e\u001a\u00020\u000f*\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u00022\b\b\u0002\u0010\u0011\u001a\u00020\u000f2\u001a\b\u0002\u0010\u0012\u001a\u0014\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00150\u0013\u001a&\u0010\u0016\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u00022\b\b\u0002\u0010\u0011\u001a\u00020\u000f2\b\b\u0002\u0010\u0017\u001a\u00020\u0018\u001a\n\u0010\u0019\u001a\u00020\u000f*\u00020\u0002\u001a\u0012\u0010\u001a\u001a\u00020\u000f*\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u0002\u001a\u0012\u0010\u001a\u001a\u00020\u000f*\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u0001\u001a\n\u0010\u001c\u001a\u00020\u0002*\u00020\u0002\u001a\u001d\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00020\u001d*\b\u0012\u0004\u0012\u00020\u00020\u001dH\u0002¢\u0006\u0002\b\u001e\u001a\u0011\u0010\u001c\u001a\u00020\u001f*\u00020\u001fH\u0002¢\u0006\u0002\b\u001e\u001a\u0012\u0010 \u001a\u00020\u0002*\u00020\u00022\u0006\u0010!\u001a\u00020\u0002\u001a\u0014\u0010\"\u001a\u0004\u0018\u00010\u0002*\u00020\u00022\u0006\u0010!\u001a\u00020\u0002\u001a\u0012\u0010#\u001a\u00020\u0002*\u00020\u00022\u0006\u0010!\u001a\u00020\u0002\u001a\u0012\u0010$\u001a\u00020\u0002*\u00020\u00022\u0006\u0010%\u001a\u00020\u0002\u001a\u0012\u0010$\u001a\u00020\u0002*\u00020\u00022\u0006\u0010%\u001a\u00020\u0001\u001a\u0012\u0010&\u001a\u00020\u0002*\u00020\u00022\u0006\u0010%\u001a\u00020\u0002\u001a\u0012\u0010&\u001a\u00020\u0002*\u00020\u00022\u0006\u0010%\u001a\u00020\u0001\u001a\u0012\u0010'\u001a\u00020\u000f*\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u0002\u001a\u0012\u0010'\u001a\u00020\u000f*\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u0001\u001a\u0012\u0010(\u001a\u00020\u0001*\u00020\u00022\u0006\u0010!\u001a\u00020\u0002\u001a\u001b\u0010)\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010!\u001a\u00020\u0002H\u0002¢\u0006\u0002\b*\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u0015\u0010\u0005\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0004\"\u0015\u0010\u0007\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\b\u0010\u0004¨\u0006+"}, m8860e = {"extension", "", "Ljava/io/File;", "getExtension", "(Ljava/io/File;)Ljava/lang/String;", "invariantSeparatorsPath", "getInvariantSeparatorsPath", "nameWithoutExtension", "getNameWithoutExtension", "createTempDir", "prefix", "suffix", EjbJar.NamingScheme.DIRECTORY, "createTempFile", "copyRecursively", "", "target", "overwrite", "onError", "Lkotlin/Function2;", "Ljava/io/IOException;", "Lkotlin/io/OnErrorAction;", "copyTo", "bufferSize", "", "deleteRecursively", "endsWith", "other", "normalize", "", "normalize$FilesKt__UtilsKt", "Lkotlin/io/FilePathComponents;", "relativeTo", "base", "relativeToOrNull", "relativeToOrSelf", "resolve", "relative", "resolveSibling", "startsWith", "toRelativeString", "toRelativeStringOrNull", "toRelativeStringOrNull$FilesKt__UtilsKt", "kotlin-stdlib"}, m8859f = "kotlin/io/FilesKt", m8857h = 1)
/* renamed from: z1.cgb */
/* loaded from: classes3.dex */
class cgb extends cga {
    /* renamed from: a */
    public static /* synthetic */ File m5322a(String str, String str2, File file, int i, Object obj) {
        if ((i & 1) != 0) {
            str = "tmp";
        }
        if ((i & 2) != 0) {
            str2 = null;
        }
        if ((i & 4) != 0) {
            file = null;
        }
        return cfx.m5323a(str, str2, file);
    }

    @NotNull
    /* renamed from: a */
    public static final File m5323a(@NotNull String str, @dbs String str2, @dbs File file) {
        cji.m5162f(str, "prefix");
        File createTempFile = File.createTempFile(str, str2, file);
        createTempFile.delete();
        if (createTempFile.mkdir()) {
            cji.m5175b(createTempFile, "dir");
            return createTempFile;
        }
        throw new IOException("Unable to create temporary directory " + createTempFile + FilenameUtils.EXTENSION_SEPARATOR);
    }

    /* renamed from: b */
    public static /* synthetic */ File m5316b(String str, String str2, File file, int i, Object obj) {
        if ((i & 1) != 0) {
            str = "tmp";
        }
        if ((i & 2) != 0) {
            str2 = null;
        }
        if ((i & 4) != 0) {
            file = null;
        }
        return cfx.m5317b(str, str2, file);
    }

    @NotNull
    /* renamed from: b */
    public static final File m5317b(@NotNull String str, @dbs String str2, @dbs File file) {
        cji.m5162f(str, "prefix");
        File createTempFile = File.createTempFile(str, str2, file);
        cji.m5175b(createTempFile, "File.createTempFile(prefix, suffix, directory)");
        return createTempFile;
    }

    @NotNull
    /* renamed from: h */
    public static final String m5308h(@NotNull File file) {
        cji.m5162f(file, "$this$extension");
        String name = file.getName();
        cji.m5175b(name, "name");
        return cpl.m3864d(name, (char) FilenameUtils.EXTENSION_SEPARATOR, "");
    }

    @NotNull
    /* renamed from: i */
    public static final String m5306i(@NotNull File file) {
        cji.m5162f(file, "$this$invariantSeparatorsPath");
        if (File.separatorChar != '/') {
            String path = file.getPath();
            cji.m5175b(path, "path");
            return cpl.m4067a(path, File.separatorChar, (char) IOUtils.DIR_SEPARATOR_UNIX, false, 4, (Object) null);
        }
        String path2 = file.getPath();
        cji.m5175b(path2, "path");
        return path2;
    }

    @NotNull
    /* renamed from: j */
    public static final String m5304j(@NotNull File file) {
        cji.m5162f(file, "$this$nameWithoutExtension");
        String name = file.getName();
        cji.m5175b(name, "name");
        return cpl.m3859d(name, Consts.f23430h, (String) null, 2, (Object) null);
    }

    @NotNull
    /* renamed from: a */
    public static final String m5329a(@NotNull File file, @NotNull File file2) {
        cji.m5162f(file, "$this$toRelativeString");
        cji.m5162f(file2, "base");
        String i = m5305i(file, file2);
        if (i != null) {
            return i;
        }
        throw new IllegalArgumentException("this and base files have different roots: " + file + " and " + file2 + FilenameUtils.EXTENSION_SEPARATOR);
    }

    @NotNull
    /* renamed from: b */
    public static final File m5319b(@NotNull File file, @NotNull File file2) {
        cji.m5162f(file, "$this$relativeTo");
        cji.m5162f(file2, "base");
        return new File(cfx.m5329a(file, file2));
    }

    @NotNull
    /* renamed from: c */
    public static final File m5315c(@NotNull File file, @NotNull File file2) {
        cji.m5162f(file, "$this$relativeToOrSelf");
        cji.m5162f(file2, "base");
        String i = m5305i(file, file2);
        return i != null ? new File(i) : file;
    }

    @dbs
    /* renamed from: d */
    public static final File m5313d(@NotNull File file, @NotNull File file2) {
        cji.m5162f(file, "$this$relativeToOrNull");
        cji.m5162f(file2, "base");
        String i = m5305i(file, file2);
        if (i != null) {
            return new File(i);
        }
        return null;
    }

    /* renamed from: i */
    private static final String m5305i(@NotNull File file, File file2) {
        FilePathComponents a = m5320a(cfx.m5363d(file));
        FilePathComponents a2 = m5320a(cfx.m5363d(file2));
        if (!cji.m5184a(a.m5386d(), a2.m5386d())) {
            return null;
        }
        int c = a2.m5387c();
        int c2 = a.m5387c();
        int i = 0;
        int min = Math.min(c2, c);
        while (i < min && cji.m5184a(a.m5385e().get(i), a2.m5385e().get(i))) {
            i++;
        }
        StringBuilder sb = new StringBuilder();
        int i2 = c - 1;
        if (i2 >= i) {
            while (!cji.m5184a((Object) a2.m5385e().get(i2).getName(), (Object) "..")) {
                sb.append("..");
                if (i2 != i) {
                    sb.append(File.separatorChar);
                }
                if (i2 != i) {
                    i2--;
                }
            }
            return null;
        }
        if (i < c2) {
            if (i < c) {
                sb.append(File.separatorChar);
            }
            String str = File.separator;
            cji.m5175b(str, "File.separator");
            bzk.m6682a(bzk.m6621d((Iterable) a.m5385e(), i), sb, (r14 & 2) != 0 ? ", " : str, (r14 & 4) != 0 ? "" : null, (r14 & 8) != 0 ? "" : null, (r14 & 16) != 0 ? -1 : 0, (r14 & 32) != 0 ? "..." : null, (r14 & 64) != 0 ? null : null);
        }
        return sb.toString();
    }

    /* renamed from: a */
    public static /* synthetic */ File m5327a(File file, File file2, boolean z, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z = false;
        }
        if ((i2 & 4) != 0) {
            i = 8192;
        }
        return cfx.m5328a(file, file2, z, i);
    }

    @NotNull
    /* renamed from: a */
    public static final File m5328a(@NotNull File file, @NotNull File file2, boolean z, int i) {
        cji.m5162f(file, "$this$copyTo");
        cji.m5162f(file2, "target");
        if (file.exists()) {
            if (file2.exists()) {
                boolean z2 = true;
                if (z && file2.delete()) {
                    z2 = false;
                }
                if (z2) {
                    throw new cfr(file, file2, "The destination file already exists.");
                }
            }
            if (!file.isDirectory()) {
                File parentFile = file2.getParentFile();
                if (parentFile != null) {
                    parentFile.mkdirs();
                }
                th = null;
                try {
                    FileInputStream fileInputStream = new FileInputStream(file);
                    FileOutputStream fileOutputStream = new FileOutputStream(file2);
                    Throwable th = null;
                    IOStreams.m5449a(fileInputStream, fileOutputStream, i);
                    Closeable.m5424a(fileOutputStream, th);
                } finally {
                    try {
                    } finally {
                    }
                }
            } else if (!file2.mkdirs()) {
                throw new cft(file, file2, "Failed to create target directory.");
            }
            return file2;
        }
        throw new cgd(file, null, "The source file doesn't exist.", 2, null);
    }

    /* compiled from: Utils.kt */
    @Metadata(m8864a = 3, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, m8860e = {"<anonymous>", "", "<anonymous parameter 0>", "Ljava/io/File;", CustomerSession.f11798b, "Ljava/io/IOException;", "invoke"})
    /* renamed from: z1.cgb$a */
    /* loaded from: classes3.dex */
    static final class C4956a extends Lambda implements cho {
        public static final C4956a INSTANCE = new C4956a();

        C4956a() {
            super(2);
        }

        @NotNull
        public final Void invoke(@NotNull File file, @NotNull IOException iOException) {
            cji.m5162f(file, "<anonymous parameter 0>");
            cji.m5162f(iOException, CustomerSession.f11798b);
            throw iOException;
        }
    }

    /* renamed from: a */
    public static /* synthetic */ boolean m5325a(File file, File file2, boolean z, cho choVar, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        if ((i & 4) != 0) {
            choVar = C4956a.INSTANCE;
        }
        return cfx.m5326a(file, file2, z, choVar);
    }

    /* renamed from: a */
    public static final boolean m5326a(@NotNull File file, @NotNull File file2, boolean z, @NotNull cho<? super File, ? super IOException, ? extends cge> choVar) {
        boolean z2;
        cji.m5162f(file, "$this$copyRecursively");
        cji.m5162f(file2, "target");
        cji.m5162f(choVar, "onError");
        if (!file.exists()) {
            return ((cge) choVar.invoke(file, new cgd(file, null, "The source file doesn't exist.", 2, null))) != cge.TERMINATE;
        }
        try {
            Iterator<File> a = cfx.m5331f(file).m5379a(new C4957b(choVar)).mo3707a();
            while (a.hasNext()) {
                File next = a.next();
                if (next.exists()) {
                    File file3 = new File(file2, cfx.m5329a(next, file));
                    if (file3.exists() && (!next.isDirectory() || !file3.isDirectory())) {
                        if (!z) {
                            z2 = true;
                        } else if (file3.isDirectory()) {
                            z2 = !cfx.m5303k(file3);
                        } else {
                            z2 = !file3.delete();
                        }
                        if (z2) {
                            if (((cge) choVar.invoke(file3, new cfr(next, file3, "The destination file already exists."))) == cge.TERMINATE) {
                                return false;
                            }
                        }
                    }
                    if (next.isDirectory()) {
                        file3.mkdirs();
                    } else if (cfx.m5327a(next, file3, z, 0, 4, (Object) null).length() != next.length() && ((cge) choVar.invoke(next, new IOException("Source file wasn't copied completely, length of destination file differs."))) == cge.TERMINATE) {
                        return false;
                    }
                } else if (((cge) choVar.invoke(next, new cgd(next, null, "The source file doesn't exist.", 2, null))) == cge.TERMINATE) {
                    return false;
                }
            }
            return true;
        } catch (cgg unused) {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: Utils.kt */
    @Metadata(m8864a = 3, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, m8860e = {"<anonymous>", "", "f", "Ljava/io/File;", "e", "Ljava/io/IOException;", "invoke"})
    /* renamed from: z1.cgb$b */
    /* loaded from: classes3.dex */
    public static final class C4957b extends Lambda implements cho<File, IOException, Unit> {
        final /* synthetic */ cho $onError;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C4957b(cho choVar) {
            super(2);
            this.$onError = choVar;
        }

        @Override // p110z1.cho
        public /* bridge */ /* synthetic */ Unit invoke(File file, IOException iOException) {
            invoke2(file, iOException);
            return Unit.f20418a;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(@NotNull File file, @NotNull IOException iOException) {
            cji.m5162f(file, "f");
            cji.m5162f(iOException, "e");
            if (((cge) this.$onError.invoke(file, iOException)) == cge.TERMINATE) {
                throw new cgg(file);
            }
        }
    }

    /* renamed from: k */
    public static final boolean m5303k(@NotNull File file) {
        cji.m5162f(file, "$this$deleteRecursively");
        Iterator<File> a = cfx.m5330g(file).mo3707a();
        boolean z = true;
        while (a.hasNext()) {
            File next = a.next();
            z = (next.delete() || !next.exists()) && z;
        }
        return z;
    }

    /* renamed from: e */
    public static final boolean m5311e(@NotNull File file, @NotNull File file2) {
        cji.m5162f(file, "$this$startsWith");
        cji.m5162f(file2, "other");
        FilePathComponents d = cfx.m5363d(file);
        FilePathComponents d2 = cfx.m5363d(file2);
        if (!(!cji.m5184a(d.m5386d(), d2.m5386d())) && d.m5387c() >= d2.m5387c()) {
            return d.m5385e().subList(0, d2.m5387c()).equals(d2.m5385e());
        }
        return false;
    }

    /* renamed from: a */
    public static final boolean m5324a(@NotNull File file, @NotNull String str) {
        cji.m5162f(file, "$this$startsWith");
        cji.m5162f(str, "other");
        return cfx.m5311e(file, new File(str));
    }

    /* renamed from: f */
    public static final boolean m5310f(@NotNull File file, @NotNull File file2) {
        cji.m5162f(file, "$this$endsWith");
        cji.m5162f(file2, "other");
        FilePathComponents d = cfx.m5363d(file);
        FilePathComponents d2 = cfx.m5363d(file2);
        if (d2.m5388b()) {
            return cji.m5184a(file, file2);
        }
        int c = d.m5387c() - d2.m5387c();
        if (c < 0) {
            return false;
        }
        return d.m5385e().subList(c, d.m5387c()).equals(d2.m5385e());
    }

    /* renamed from: b */
    public static final boolean m5318b(@NotNull File file, @NotNull String str) {
        cji.m5162f(file, "$this$endsWith");
        cji.m5162f(str, "other");
        return cfx.m5310f(file, new File(str));
    }

    @NotNull
    /* renamed from: l */
    public static final File m5302l(@NotNull File file) {
        cji.m5162f(file, "$this$normalize");
        FilePathComponents d = cfx.m5363d(file);
        File d2 = d.m5386d();
        List<File> a = m5321a(d.m5385e());
        String str = File.separator;
        cji.m5175b(str, "File.separator");
        return cfx.m5314c(d2, bzk.m6679a(a, str, null, null, 0, null, null, 62, null));
    }

    /* renamed from: a */
    private static final FilePathComponents m5320a(@NotNull FilePathComponents cfsVar) {
        return new FilePathComponents(cfsVar.m5386d(), m5321a(cfsVar.m5385e()));
    }

    /* renamed from: a */
    private static final List<File> m5321a(@NotNull List<? extends File> list) {
        ArrayList arrayList = new ArrayList(list.size());
        for (File file : list) {
            String name = file.getName();
            if (name != null) {
                int hashCode = name.hashCode();
                if (hashCode != 46) {
                    if (hashCode == 1472 && name.equals("..")) {
                        if (arrayList.isEmpty() || !(!cji.m5184a((Object) ((File) bzk.m6574i((List<? extends Object>) arrayList)).getName(), (Object) ".."))) {
                            arrayList.add(file);
                        } else {
                            arrayList.remove(arrayList.size() - 1);
                        }
                    }
                } else if (name.equals(Consts.f23430h)) {
                }
            }
            arrayList.add(file);
        }
        return arrayList;
    }

    @NotNull
    /* renamed from: g */
    public static final File m5309g(@NotNull File file, @NotNull File file2) {
        cji.m5162f(file, "$this$resolve");
        cji.m5162f(file2, "relative");
        if (cfx.m5364c(file2)) {
            return file2;
        }
        String file3 = file.toString();
        cji.m5175b(file3, "this.toString()");
        String str = file3;
        if ((str.length() == 0) || cpl.m3933b((CharSequence) str, File.separatorChar, false, 2, (Object) null)) {
            return new File(file3 + file2);
        }
        return new File(file3 + File.separatorChar + file2);
    }

    @NotNull
    /* renamed from: c */
    public static final File m5314c(@NotNull File file, @NotNull String str) {
        cji.m5162f(file, "$this$resolve");
        cji.m5162f(str, "relative");
        return cfx.m5309g(file, new File(str));
    }

    @NotNull
    /* renamed from: h */
    public static final File m5307h(@NotNull File file, @NotNull File file2) {
        cji.m5162f(file, "$this$resolveSibling");
        cji.m5162f(file2, "relative");
        FilePathComponents d = cfx.m5363d(file);
        return cfx.m5309g(cfx.m5309g(d.m5386d(), d.m5387c() == 0 ? new File("..") : d.m5391a(0, d.m5387c() - 1)), file2);
    }

    @NotNull
    /* renamed from: d */
    public static final File m5312d(@NotNull File file, @NotNull String str) {
        cji.m5162f(file, "$this$resolveSibling");
        cji.m5162f(str, "relative");
        return cfx.m5307h(file, new File(str));
    }
}
