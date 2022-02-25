package org.apache.commons.mail.p106a;

import java.io.IOException;
import javax.activation.DataSource;
import org.apache.commons.mail.DataSourceResolver;

/* renamed from: org.apache.commons.mail.a.c */
/* loaded from: classes2.dex */
public final class DataSourceCompositeResolver extends DataSourceBaseResolver {

    /* renamed from: b */
    private final DataSourceResolver[] f14713b;

    private DataSourceCompositeResolver(DataSourceResolver[] dataSourceResolverArr) {
        this.f14713b = new DataSourceResolver[dataSourceResolverArr.length];
        System.arraycopy(dataSourceResolverArr, 0, this.f14713b, 0, dataSourceResolverArr.length);
    }

    private DataSourceCompositeResolver(DataSourceResolver[] dataSourceResolverArr, boolean z) {
        super(z);
        this.f14713b = new DataSourceResolver[dataSourceResolverArr.length];
        System.arraycopy(dataSourceResolverArr, 0, this.f14713b, 0, dataSourceResolverArr.length);
    }

    /* renamed from: a */
    private DataSourceResolver[] m14857a() {
        DataSourceResolver[] dataSourceResolverArr = this.f14713b;
        DataSourceResolver[] dataSourceResolverArr2 = new DataSourceResolver[dataSourceResolverArr.length];
        System.arraycopy(dataSourceResolverArr, 0, dataSourceResolverArr2, 0, dataSourceResolverArr.length);
        return dataSourceResolverArr2;
    }

    @Override // org.apache.commons.mail.DataSourceResolver
    public final DataSource resolve(String str) throws IOException {
        DataSource resolve = resolve(str, true);
        if (this.f14711a || resolve != null) {
            return resolve;
        }
        throw new IOException("The following resource was not found : " + str);
    }

    @Override // org.apache.commons.mail.DataSourceResolver
    public final DataSource resolve(String str, boolean z) throws IOException {
        for (int i = 0; i < m14857a().length; i++) {
            DataSource resolve = m14857a()[i].resolve(str, z);
            if (resolve != null) {
                return resolve;
            }
        }
        if (z) {
            return null;
        }
        throw new IOException("The following resource was not found : " + str);
    }
}
