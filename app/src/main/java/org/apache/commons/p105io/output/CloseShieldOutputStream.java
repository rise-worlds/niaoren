package org.apache.commons.p105io.output;

import java.io.OutputStream;

/* renamed from: org.apache.commons.io.output.CloseShieldOutputStream */
/* loaded from: classes2.dex */
public class CloseShieldOutputStream extends ProxyOutputStream {
    public CloseShieldOutputStream(OutputStream outputStream) {
        super(outputStream);
    }

    @Override // org.apache.commons.p105io.output.ProxyOutputStream, java.io.FilterOutputStream, java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.out = new ClosedOutputStream();
    }
}
