package org.apache.commons.mail.p106a;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.activation.DataSource;
import javax.activation.URLDataSource;
import p110z1.C4745bt;

/* renamed from: org.apache.commons.mail.a.e */
/* loaded from: classes2.dex */
public final class DataSourceUrlResolver extends DataSourceBaseResolver {

    /* renamed from: b */
    private final URL f14715b;

    private DataSourceUrlResolver(URL url) {
        this.f14715b = url;
    }

    private DataSourceUrlResolver(URL url, boolean z) {
        super(z);
        this.f14715b = url;
    }

    /* renamed from: a */
    private URL m14855a() {
        return this.f14715b;
    }

    /* renamed from: b */
    private URL m14854b(String str) throws MalformedURLException {
        if (this.f14715b == null) {
            return new URL(str);
        }
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("No resource defined");
        } else if (str.startsWith("file:/") || m14862a(str)) {
            return new URL(str);
        } else {
            return new URL(this.f14715b, str.replaceAll("&amp;", C4745bt.f20071b));
        }
    }

    @Override // org.apache.commons.mail.DataSourceResolver
    public final DataSource resolve(String str) throws IOException {
        return resolve(str, this.f14711a);
    }

    @Override // org.apache.commons.mail.DataSourceResolver
    public final DataSource resolve(String str, boolean z) throws IOException {
        URL url;
        try {
            if (str.startsWith("cid:")) {
                return null;
            }
            if (this.f14715b == null) {
                url = new URL(str);
            } else if (str == null || str.length() == 0) {
                throw new IllegalArgumentException("No resource defined");
            } else {
                if (!str.startsWith("file:/") && !m14862a(str)) {
                    url = new URL(this.f14715b, str.replaceAll("&amp;", C4745bt.f20071b));
                }
                url = new URL(str);
            }
            URLDataSource uRLDataSource = new URLDataSource(url);
            uRLDataSource.getInputStream();
            return uRLDataSource;
        } catch (IOException e) {
            if (z) {
                return null;
            }
            throw e;
        }
    }
}
