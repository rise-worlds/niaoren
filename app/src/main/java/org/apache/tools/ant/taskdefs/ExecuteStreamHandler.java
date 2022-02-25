package org.apache.tools.ant.taskdefs;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* loaded from: classes2.dex */
public interface ExecuteStreamHandler {
    void setProcessErrorStream(InputStream inputStream) throws IOException;

    void setProcessInputStream(OutputStream outputStream) throws IOException;

    void setProcessOutputStream(InputStream inputStream) throws IOException;

    void start() throws IOException;

    void stop();
}
