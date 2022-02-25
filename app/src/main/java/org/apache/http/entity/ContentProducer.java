package org.apache.http.entity;

import java.io.IOException;
import java.io.OutputStream;

@Deprecated
/* loaded from: assets/org.apache.http.legacy.boot */
public interface ContentProducer {
    void writeTo(OutputStream outputStream) throws IOException;
}
