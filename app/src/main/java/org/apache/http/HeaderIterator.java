package org.apache.http;

import java.util.Iterator;

@Deprecated
/* loaded from: assets/org.apache.http.legacy.boot */
public interface HeaderIterator extends Iterator {
    @Override // java.util.Iterator
    boolean hasNext();

    Header nextHeader();
}
