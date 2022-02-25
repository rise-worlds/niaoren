package org.apache.tools.ant.types.resources.comparators;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;
import java.util.Vector;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.types.DataType;
import org.apache.tools.ant.types.Resource;

/* loaded from: classes2.dex */
public class DelegatedResourceComparator extends ResourceComparator {
    private List<ResourceComparator> resourceComparators = null;

    public synchronized void add(ResourceComparator resourceComparator) {
        if (isReference()) {
            throw noChildrenAllowed();
        } else if (resourceComparator != null) {
            this.resourceComparators = this.resourceComparators == null ? new Vector<>() : this.resourceComparators;
            this.resourceComparators.add(resourceComparator);
            setChecked(false);
        }
    }

    @Override // org.apache.tools.ant.types.resources.comparators.ResourceComparator, java.util.Comparator
    public synchronized boolean equals(Object obj) {
        boolean z = true;
        if (obj == this) {
            return true;
        }
        if (isReference()) {
            return getCheckedRef().equals(obj);
        } else if (!(obj instanceof DelegatedResourceComparator)) {
            return false;
        } else {
            List<ResourceComparator> list = ((DelegatedResourceComparator) obj).resourceComparators;
            if (this.resourceComparators != null) {
                z = this.resourceComparators.equals(list);
            } else if (list != null) {
                z = false;
            }
            return z;
        }
    }

    @Override // org.apache.tools.ant.types.resources.comparators.ResourceComparator
    public synchronized int hashCode() {
        if (isReference()) {
            return getCheckedRef().hashCode();
        }
        return this.resourceComparators == null ? 0 : this.resourceComparators.hashCode();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.tools.ant.types.resources.comparators.ResourceComparator
    public synchronized int resourceCompare(Resource resource, Resource resource2) {
        if (this.resourceComparators != null && !this.resourceComparators.isEmpty()) {
            int i = 0;
            Iterator<ResourceComparator> it = this.resourceComparators.iterator();
            while (i == 0 && it.hasNext()) {
                i = it.next().resourceCompare(resource, resource2);
            }
            return i;
        }
        return resource.compareTo(resource2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.tools.ant.types.DataType
    public void dieOnCircularReference(Stack<Object> stack, Project project) throws BuildException {
        if (!isChecked()) {
            if (isReference()) {
                super.dieOnCircularReference(stack, project);
                return;
            }
            List<ResourceComparator> list = this.resourceComparators;
            if (list != null && !list.isEmpty()) {
                for (ResourceComparator resourceComparator : this.resourceComparators) {
                    if (resourceComparator instanceof DataType) {
                        pushAndInvokeCircularReferenceCheck(resourceComparator, stack, project);
                    }
                }
            }
            setChecked(true);
        }
    }
}
