package org.apache.tools.ant.types.resources;

import java.util.Collection;
import java.util.List;
import org.apache.tools.ant.types.Resource;
import org.apache.tools.ant.util.CollectionUtils;

/* loaded from: classes2.dex */
public class AllButLast extends SizeLimitCollection {
    @Override // org.apache.tools.ant.types.resources.BaseResourceCollectionWrapper
    protected Collection<Resource> getCollection() {
        int validCount = getValidCount();
        List list = (List) CollectionUtils.asCollection(getResourceCollection().iterator());
        return list.subList(0, list.size() - validCount);
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
