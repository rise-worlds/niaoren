package p110z1;

import java.io.File;
import java.io.IOException;

/* compiled from: Exceptions.kt */
@Metadata(m8864a = 1, m8863b = {1, 1, 15}, m8862c = {1, 0, 3}, m8861d = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0016\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\r"}, m8860e = {"Lkotlin/io/FileSystemException;", "Ljava/io/IOException;", "file", "Ljava/io/File;", "other", "reason", "", "(Ljava/io/File;Ljava/io/File;Ljava/lang/String;)V", "getFile", "()Ljava/io/File;", "getOther", "getReason", "()Ljava/lang/String;", "kotlin-stdlib"})
/* renamed from: z1.cft */
/* loaded from: classes3.dex */
public class cft extends IOException {
    @NotNull
    private final File file;
    @dbs
    private final File other;
    @dbs
    private final String reason;

    @NotNull
    public final File getFile() {
        return this.file;
    }

    public /* synthetic */ cft(File file, File file2, String str, int i, DefaultConstructorMarker civVar) {
        this(file, (i & 2) != 0 ? null : file2, (i & 4) != 0 ? null : str);
    }

    @dbs
    public final File getOther() {
        return this.other;
    }

    @dbs
    public final String getReason() {
        return this.reason;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public cft(@p110z1.NotNull java.io.File r2, @p110z1.dbs java.io.File r3, @p110z1.dbs java.lang.String r4) {
        /*
            r1 = this;
            java.lang.String r0 = "file"
            p110z1.cji.m5162f(r2, r0)
            java.lang.String r0 = p110z1.cfq.m5394a(r2, r3, r4)
            r1.<init>(r0)
            r1.file = r2
            r1.other = r3
            r1.reason = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p110z1.cft.<init>(java.io.File, java.io.File, java.lang.String):void");
    }
}
