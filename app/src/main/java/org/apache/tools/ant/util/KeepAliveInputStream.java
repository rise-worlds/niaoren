package org.apache.tools.ant.util;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes2.dex */
public class KeepAliveInputStream extends FilterInputStream {
    @Override // java.io.FilterInputStream, java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
    }

    public KeepAliveInputStream(InputStream inputStream) {
        super(inputStream);
    }

    public static InputStream wrapSystemIn() {
        return new KeepAliveInputStream(System.in);
    }
}
