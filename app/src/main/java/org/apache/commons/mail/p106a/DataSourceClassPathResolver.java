package org.apache.commons.mail.p106a;

import java.io.IOException;
import java.io.InputStream;
import javax.activation.DataSource;
import javax.activation.FileTypeMap;
import javax.mail.util.ByteArrayDataSource;

/* renamed from: org.apache.commons.mail.a.b */
/* loaded from: classes2.dex */
public class DataSourceClassPathResolver extends DataSourceBaseResolver {

    /* renamed from: b */
    private final String f14712b;

    public DataSourceClassPathResolver() {
        this.f14712b = "/";
    }

    private DataSourceClassPathResolver(String str) {
        if (!str.endsWith("/")) {
            str = str + "/";
        }
        this.f14712b = str;
    }

    private DataSourceClassPathResolver(String str, boolean z) {
        super(z);
        if (!str.endsWith("/")) {
            str = str + "/";
        }
        this.f14712b = str;
    }

    /* renamed from: a */
    private String m14859a() {
        return this.f14712b;
    }

    /* renamed from: b */
    private String m14858b(String str) {
        return (this.f14712b + str).replaceAll("//", "/");
    }

    @Override // org.apache.commons.mail.DataSourceResolver
    public DataSource resolve(String str) throws IOException {
        return resolve(str, this.f14711a);
    }

    @Override // org.apache.commons.mail.DataSourceResolver
    public DataSource resolve(String str, boolean z) throws IOException {
        try {
            if (str.startsWith("cid:") || m14862a(str)) {
                return null;
            }
            String contentType = FileTypeMap.getDefaultFileTypeMap().getContentType(str);
            String replaceAll = (this.f14712b + str).replaceAll("//", "/");
            InputStream resourceAsStream = DataSourceClassPathResolver.class.getResourceAsStream(replaceAll);
            if (resourceAsStream != null) {
                ByteArrayDataSource byteArrayDataSource = new ByteArrayDataSource(resourceAsStream, contentType);
                byteArrayDataSource.setName(DataSourceClassPathResolver.class.getResource(replaceAll).toString());
                return byteArrayDataSource;
            } else if (z) {
                return null;
            } else {
                throw new IOException("The following class path resource was not found : " + str);
            }
        } catch (IOException e) {
            if (z) {
                return null;
            }
            throw e;
        }
    }
}
