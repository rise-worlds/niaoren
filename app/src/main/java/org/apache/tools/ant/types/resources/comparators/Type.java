package org.apache.tools.ant.types.resources.comparators;

import org.apache.tools.ant.types.Resource;

/* loaded from: classes2.dex */
public class Type extends ResourceComparator {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.tools.ant.types.resources.comparators.ResourceComparator
    public int resourceCompare(Resource resource, Resource resource2) {
        boolean isDirectory = resource.isDirectory();
        if (isDirectory == resource2.isDirectory()) {
            return 0;
        }
        return isDirectory ? 1 : -1;
    }
}
