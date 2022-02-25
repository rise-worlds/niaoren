package p110z1;

import java.io.File;
import java.util.List;

@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\r\b\u0080\b\u0018\u00002\u00020\u0001B\u001d\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005HÆ\u0003J#\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005HÆ\u0001J\u0013\u0010\u0019\u001a\u00020\b2\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001b\u001a\u00020\u0013HÖ\u0001J\u0016\u0010\u001c\u001a\u00020\u00032\u0006\u0010\u001d\u001a\u00020\u00132\u0006\u0010\u001e\u001a\u00020\u0013J\t\u0010\u001f\u001a\u00020\rHÖ\u0001R\u0011\u0010\u0007\u001a\u00020\b8F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\f\u001a\u00020\r8F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0012\u001a\u00020\u00138F¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015¨\u0006 "}, m8860e = {"Lkotlin/io/FilePathComponents;", "", "root", "Ljava/io/File;", "segments", "", "(Ljava/io/File;Ljava/util/List;)V", "isRooted", "", "()Z", "getRoot", "()Ljava/io/File;", "rootName", "", "getRootName", "()Ljava/lang/String;", "getSegments", "()Ljava/util/List;", "size", "", "getSize", "()I", "component1", "component2", "copy", "equals", "other", "hashCode", "subPath", "beginIndex", "endIndex", "toString", "kotlin-stdlib"})
/* renamed from: z1.cfs */
/* loaded from: classes3.dex */
public final class FilePathComponents {
    @NotNull

    /* renamed from: a */
    private final File f20658a;
    @NotNull

    /* renamed from: b */
    private final List<File> f20659b;

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: a */
    public static /* synthetic */ FilePathComponents m5389a(FilePathComponents cfsVar, File file, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            file = cfsVar.f20658a;
        }
        if ((i & 2) != 0) {
            list = cfsVar.f20659b;
        }
        return cfsVar.m5390a(file, list);
    }

    @NotNull
    /* renamed from: a */
    public final FilePathComponents m5390a(@NotNull File file, @NotNull List<? extends File> list) {
        cji.m5162f(file, "root");
        cji.m5162f(list, "segments");
        return new FilePathComponents(file, list);
    }

    public boolean equals(@dbs Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FilePathComponents)) {
            return false;
        }
        FilePathComponents cfsVar = (FilePathComponents) obj;
        return cji.m5184a(this.f20658a, cfsVar.f20658a) && cji.m5184a(this.f20659b, cfsVar.f20659b);
    }

    @NotNull
    /* renamed from: f */
    public final File m5384f() {
        return this.f20658a;
    }

    @NotNull
    /* renamed from: g */
    public final List<File> m5383g() {
        return this.f20659b;
    }

    public int hashCode() {
        File file = this.f20658a;
        int i = 0;
        int hashCode = (file != null ? file.hashCode() : 0) * 31;
        List<File> list = this.f20659b;
        if (list != null) {
            i = list.hashCode();
        }
        return hashCode + i;
    }

    @NotNull
    public String toString() {
        return "FilePathComponents(root=" + this.f20658a + ", segments=" + this.f20659b + ")";
    }

    /* JADX WARN: Multi-variable type inference failed */
    public FilePathComponents(@NotNull File file, @NotNull List<? extends File> list) {
        cji.m5162f(file, "root");
        cji.m5162f(list, "segments");
        this.f20658a = file;
        this.f20659b = list;
    }

    @NotNull
    /* renamed from: d */
    public final File m5386d() {
        return this.f20658a;
    }

    @NotNull
    /* renamed from: e */
    public final List<File> m5385e() {
        return this.f20659b;
    }

    @NotNull
    /* renamed from: a */
    public final String m5392a() {
        String path = this.f20658a.getPath();
        cji.m5175b(path, "root.path");
        return path;
    }

    /* renamed from: b */
    public final boolean m5388b() {
        String path = this.f20658a.getPath();
        cji.m5175b(path, "root.path");
        return path.length() > 0;
    }

    /* renamed from: c */
    public final int m5387c() {
        return this.f20659b.size();
    }

    @NotNull
    /* renamed from: a */
    public final File m5391a(int i, int i2) {
        if (i < 0 || i > i2 || i2 > m5387c()) {
            throw new IllegalArgumentException();
        }
        List<File> subList = this.f20659b.subList(i, i2);
        String str = File.separator;
        cji.m5175b(str, "File.separator");
        return new File(bzk.m6679a(subList, str, null, null, 0, null, null, 62, null));
    }
}
