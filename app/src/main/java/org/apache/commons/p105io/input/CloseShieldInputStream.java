package org.apache.commons.p105io.input;

import java.io.InputStream;

/* renamed from: org.apache.commons.io.input.CloseShieldInputStream */
/* loaded from: classes2.dex */
public class CloseShieldInputStream extends ProxyInputStream {
    public CloseShieldInputStream(InputStream inputStream) {
        super(inputStream);
    }

    @Override // org.apache.commons.p105io.input.ProxyInputStream, java.io.FilterInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.in = new ClosedInputStream();
    }
}
