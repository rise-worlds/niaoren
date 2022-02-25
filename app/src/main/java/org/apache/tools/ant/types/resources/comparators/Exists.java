package org.apache.tools.ant.types.resources.comparators;

import org.apache.tools.ant.types.Resource;

/* loaded from: classes2.dex */
public class Exists extends ResourceComparator {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.tools.ant.types.resources.comparators.ResourceComparator
    public int resourceCompare(Resource resource, Resource resource2) {
        boolean isExists = resource.isExists();
        if (isExists == resource2.isExists()) {
            return 0;
        }
        return isExists ? 1 : -1;
    }
}
