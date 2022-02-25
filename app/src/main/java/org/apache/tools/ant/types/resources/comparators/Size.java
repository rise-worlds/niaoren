package org.apache.tools.ant.types.resources.comparators;

import org.apache.tools.ant.types.Resource;

/* loaded from: classes2.dex */
public class Size extends ResourceComparator {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.tools.ant.types.resources.comparators.ResourceComparator
    public int resourceCompare(Resource resource, Resource resource2) {
        int i = ((resource.getSize() - resource2.getSize()) > 0L ? 1 : ((resource.getSize() - resource2.getSize()) == 0L ? 0 : -1));
        if (i > 0) {
            return 1;
        }
        return i == 0 ? 0 : -1;
    }
}
