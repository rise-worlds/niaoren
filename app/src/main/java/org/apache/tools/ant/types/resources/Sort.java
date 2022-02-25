package org.apache.tools.ant.types.resources;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.types.DataType;
import org.apache.tools.ant.types.Resource;
import org.apache.tools.ant.types.resources.comparators.DelegatedResourceComparator;
import org.apache.tools.ant.types.resources.comparators.ResourceComparator;
import org.apache.tools.ant.util.CollectionUtils;

/* loaded from: classes2.dex */
public class Sort extends BaseResourceCollectionWrapper {
    private DelegatedResourceComparator comp = new DelegatedResourceComparator();

    @Override // org.apache.tools.ant.types.resources.BaseResourceCollectionWrapper
    protected synchronized Collection<Resource> getCollection() {
        Iterator<Resource> it = getResourceCollection().iterator();
        if (!it.hasNext()) {
            return Collections.emptySet();
        }
        List list = (List) CollectionUtils.asCollection(it);
        Collections.sort(list, this.comp);
        return list;
    }

    public synchronized void add(ResourceComparator resourceComparator) {
        if (!isReference()) {
            this.comp.add(resourceComparator);
            FailFast.invalidate(this);
            setChecked(false);
        } else {
            throw noChildrenAllowed();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.tools.ant.types.resources.AbstractResourceCollectionWrapper, org.apache.tools.ant.types.DataType
    public synchronized void dieOnCircularReference(Stack<Object> stack, Project project) throws BuildException {
        if (!isChecked()) {
            super.dieOnCircularReference(stack, project);
            if (!isReference()) {
                DataType.pushAndInvokeCircularReferenceCheck(this.comp, stack, project);
                setChecked(true);
            }
        }
    }
}
