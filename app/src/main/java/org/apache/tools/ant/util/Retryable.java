package org.apache.tools.ant.util;

import java.io.IOException;

/* loaded from: classes2.dex */
public interface Retryable {
    public static final int RETRY_FOREVER = -1;

    void execute() throws IOException;
}
