package org.apache.tools.ant.types.resources.selectors;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.types.DataType;

/* loaded from: classes2.dex */
public class ResourceSelectorContainer extends DataType {
    private final List<ResourceSelector> resourceSelectors = new ArrayList();

    public ResourceSelectorContainer() {
    }

    public ResourceSelectorContainer(ResourceSelector[] resourceSelectorArr) {
        for (ResourceSelector resourceSelector : resourceSelectorArr) {
            add(resourceSelector);
        }
    }

    public void add(ResourceSelector resourceSelector) {
        if (isReference()) {
            throw noChildrenAllowed();
        } else if (resourceSelector != null) {
            this.resourceSelectors.add(resourceSelector);
            setChecked(false);
        }
    }

    public boolean hasSelectors() {
        if (isReference()) {
            return ((ResourceSelectorContainer) getCheckedRef()).hasSelectors();
        }
        dieOnCircularReference();
        return !this.resourceSelectors.isEmpty();
    }

    public int selectorCount() {
        if (isReference()) {
            return ((ResourceSelectorContainer) getCheckedRef()).selectorCount();
        }
        dieOnCircularReference();
        return this.resourceSelectors.size();
    }

    public Iterator<ResourceSelector> getSelectors() {
        if (isReference()) {
            return ((ResourceSelectorContainer) getCheckedRef()).getSelectors();
        }
        dieOnCircularReference();
        return Collections.unmodifiableList(this.resourceSelectors).iterator();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.tools.ant.types.DataType
    public void dieOnCircularReference(Stack<Object> stack, Project project) throws BuildException {
        if (!isChecked()) {
            if (isReference()) {
                super.dieOnCircularReference(stack, project);
                return;
            }
            for (ResourceSelector resourceSelector : this.resourceSelectors) {
                if (resourceSelector instanceof DataType) {
                    pushAndInvokeCircularReferenceCheck((DataType) resourceSelector, stack, project);
                }
            }
            setChecked(true);
        }
    }
}
