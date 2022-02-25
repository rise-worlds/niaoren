package org.apache.tools.ant.types.resources;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.types.Resource;
import org.apache.tools.ant.types.ResourceCollection;

/* loaded from: classes2.dex */
public class Difference extends BaseResourceCollectionContainer {
    @Override // org.apache.tools.ant.types.resources.BaseResourceCollectionContainer
    protected Collection<Resource> getCollection() {
        List<ResourceCollection> resourceCollections = getResourceCollections();
        int size = resourceCollections.size();
        if (size < 2) {
            StringBuilder sb = new StringBuilder();
            sb.append("The difference of ");
            sb.append(size);
            sb.append(" resource collection");
            sb.append(size == 1 ? "" : "s");
            sb.append(" is undefined.");
            throw new BuildException(sb.toString());
        }
        HashSet hashSet = new HashSet();
        ArrayList arrayList = new ArrayList();
        for (ResourceCollection resourceCollection : resourceCollections) {
            for (Resource resource : resourceCollection) {
                if (hashSet.add(resource)) {
                    arrayList.add(resource);
                } else {
                    arrayList.remove(resource);
                }
            }
        }
        return arrayList;
    }
}
