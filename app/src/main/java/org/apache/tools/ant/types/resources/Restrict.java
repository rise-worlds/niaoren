package org.apache.tools.ant.types.resources;

import java.util.Iterator;
import java.util.Stack;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.types.Resource;
import org.apache.tools.ant.types.ResourceCollection;
import org.apache.tools.ant.types.resources.selectors.ResourceSelector;
import org.apache.tools.ant.types.resources.selectors.ResourceSelectorContainer;

/* loaded from: classes2.dex */
public class Restrict extends ResourceSelectorContainer implements ResourceCollection {

    /* renamed from: w */
    private LazyResourceCollectionWrapper f14772w = new LazyResourceCollectionWrapper() { // from class: org.apache.tools.ant.types.resources.Restrict.1
        @Override // org.apache.tools.ant.types.resources.LazyResourceCollectionWrapper
        protected boolean filterResource(Resource resource) {
            Iterator<ResourceSelector> selectors = Restrict.this.getSelectors();
            while (selectors.hasNext()) {
                if (!selectors.next().isSelected(resource)) {
                    return true;
                }
            }
            return false;
        }
    };

    public synchronized void add(ResourceCollection resourceCollection) {
        if (isReference()) {
            throw noChildrenAllowed();
        } else if (resourceCollection != null) {
            this.f14772w.add(resourceCollection);
            setChecked(false);
        }
    }

    public synchronized void setCache(boolean z) {
        this.f14772w.setCache(z);
    }

    public synchronized boolean isCache() {
        return this.f14772w.isCache();
    }

    @Override // org.apache.tools.ant.types.resources.selectors.ResourceSelectorContainer
    public synchronized void add(ResourceSelector resourceSelector) {
        if (resourceSelector != null) {
            super.add(resourceSelector);
            FailFast.invalidate(this);
        }
    }

    @Override // org.apache.tools.ant.types.ResourceCollection, java.lang.Iterable
    public final synchronized Iterator<Resource> iterator() {
        if (isReference()) {
            return ((Restrict) getCheckedRef()).iterator();
        }
        dieOnCircularReference();
        return this.f14772w.iterator();
    }

    @Override // org.apache.tools.ant.types.ResourceCollection
    public synchronized int size() {
        if (isReference()) {
            return ((Restrict) getCheckedRef()).size();
        }
        dieOnCircularReference();
        return this.f14772w.size();
    }

    @Override // org.apache.tools.ant.types.ResourceCollection
    public synchronized boolean isFilesystemOnly() {
        if (isReference()) {
            return ((Restrict) getCheckedRef()).isFilesystemOnly();
        }
        dieOnCircularReference();
        return this.f14772w.isFilesystemOnly();
    }

    @Override // org.apache.tools.ant.types.DataType
    public synchronized String toString() {
        if (isReference()) {
            return getCheckedRef().toString();
        }
        dieOnCircularReference();
        return this.f14772w.toString();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.tools.ant.types.resources.selectors.ResourceSelectorContainer, org.apache.tools.ant.types.DataType
    public synchronized void dieOnCircularReference(Stack<Object> stack, Project project) {
        if (!isChecked()) {
            super.dieOnCircularReference(stack, project);
            if (!isReference()) {
                pushAndInvokeCircularReferenceCheck(this.f14772w, stack, project);
                setChecked(true);
            }
        }
    }
}
