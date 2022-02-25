package org.apache.tools.ant.types.resources;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import org.apache.tools.ant.types.Resource;

/* loaded from: classes2.dex */
public class AllButFirst extends SizeLimitCollection {
    @Override // org.apache.tools.ant.types.resources.BaseResourceCollectionWrapper
    protected Collection<Resource> getCollection() {
        int validCount = getValidCount();
        Iterator<Resource> it = getResourceCollection().iterator();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < validCount && it.hasNext(); i++) {
            it.next();
        }
        while (it.hasNext()) {
            arrayList.add(it.next());
        }
        return arrayList;
    }

    @Override // org.apache.tools.ant.types.resources.SizeLimitCollection, org.apache.tools.ant.types.resources.AbstractResourceCollectionWrapper, org.apache.tools.ant.types.ResourceCollection
    public synchronized int size() {
        int size;
        int validCount;
        size = getResourceCollection().size();
        validCount = getValidCount();
        return size > validCount ? size - validCount : 0;
    }
}
