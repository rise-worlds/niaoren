package org.apache.http.impl.conn.tsccm;

import java.lang.ref.Reference;

@Deprecated
/* loaded from: assets/org.apache.http.legacy.boot */
public interface RefQueueHandler {
    void handleReference(Reference<?> reference);
}
