package org.apache.commons.mail.p106a;

import java.io.File;
import java.io.IOException;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import p110z1.Consts;

/* renamed from: org.apache.commons.mail.a.d */
/* loaded from: classes2.dex */
public final class DataSourceFileResolver extends DataSourceBaseResolver {

    /* renamed from: b */
    private final File f14714b;

    public DataSourceFileResolver() {
        this.f14714b = new File(Consts.f23430h);
    }

    private DataSourceFileResolver(File file) {
        this.f14714b = file;
    }

    private DataSourceFileResolver(File file, boolean z) {
        super(z);
        this.f14714b = file;
    }

    /* renamed from: a */
    private File m14856a() {
        return this.f14714b;
    }

    @Override // org.apache.commons.mail.DataSourceResolver
    public final DataSource resolve(String str) throws IOException {
        return resolve(str, this.f14711a);
    }

    @Override // org.apache.commons.mail.DataSourceResolver
    public final DataSource resolve(String str, boolean z) throws IOException {
        if (!str.startsWith("cid:")) {
            File file = new File(str);
            if (!file.isAbsolute()) {
                File file2 = this.f14714b;
                file = file2 != null ? new File(file2, str) : new File(str);
            }
            if (file.exists()) {
                return new FileDataSource(file);
            }
            if (!z) {
                throw new IOException("Cant resolve the following file resource :" + file.getAbsolutePath());
            }
        }
        return null;
    }
}
