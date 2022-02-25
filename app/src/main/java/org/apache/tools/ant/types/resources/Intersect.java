package org.apache.tools.ant.types.resources;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.types.Resource;
import org.apache.tools.ant.types.ResourceCollection;

/* loaded from: classes2.dex */
public class Intersect extends BaseResourceCollectionContainer {
    @Override // org.apache.tools.ant.types.resources.BaseResourceCollectionContainer
    protected Collection<Resource> getCollection() {
        List<ResourceCollection> resourceCollections = getResourceCollections();
        int size = resourceCollections.size();
        if (size < 2) {
            StringBuilder sb = new StringBuilder();
            sb.append("The intersection of ");
            sb.append(size);
            sb.append(" resource collection");
            sb.append(size == 1 ? "" : "s");
            sb.append(" is undefined.");
            throw new BuildException(sb.toString());
        }
        Iterator<ResourceCollection> it = resourceCollections.iterator();
        LinkedHashSet linkedHashSet = new LinkedHashSet(collect(it.next()));
        while (it.hasNext()) {
            linkedHashSet.retainAll(collect(it.next()));
        }
        return linkedHashSet;
    }

    private Set<Resource> collect(ResourceCollection resourceCollection) {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Iterator<Resource> it = resourceCollection.iterator();
        while (it.hasNext()) {
            linkedHashSet.add(it.next());
        }
        return linkedHashSet;
    }
}
