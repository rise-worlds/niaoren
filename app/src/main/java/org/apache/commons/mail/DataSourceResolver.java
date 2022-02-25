package org.apache.commons.mail;

import java.io.IOException;
import javax.activation.DataSource;

/* loaded from: classes2.dex */
public interface DataSourceResolver {
    DataSource resolve(String str) throws IOException;

    DataSource resolve(String str, boolean z) throws IOException;
}
