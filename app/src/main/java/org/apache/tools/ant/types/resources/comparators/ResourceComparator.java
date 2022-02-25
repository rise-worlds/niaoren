package org.apache.tools.ant.types.resources.comparators;

import java.util.Comparator;
import org.apache.tools.ant.types.DataType;
import org.apache.tools.ant.types.Resource;

/* loaded from: classes2.dex */
public abstract class ResourceComparator extends DataType implements Comparator<Resource> {
    /* JADX INFO: Access modifiers changed from: protected */
    public abstract int resourceCompare(Resource resource, Resource resource2);

    public final int compare(Resource resource, Resource resource2) {
        dieOnCircularReference();
        return (isReference() ? (ResourceComparator) getCheckedRef() : this).resourceCompare(resource, resource2);
    }

    public boolean equals(Object obj) {
        if (isReference()) {
            return getCheckedRef().equals(obj);
        }
        if (obj == null) {
            return false;
        }
        return obj == this || obj.getClass().equals(getClass());
    }

    public synchronized int hashCode() {
        if (isReference()) {
            return getCheckedRef().hashCode();
        }
        return getClass().hashCode();
    }
}
