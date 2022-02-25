package org.apache.tools.ant.types.resources.selectors;

import java.util.Iterator;
import org.apache.tools.ant.types.Resource;

/* renamed from: org.apache.tools.ant.types.resources.selectors.Or */
/* loaded from: classes2.dex */
public class C3240Or extends ResourceSelectorContainer implements ResourceSelector {
    public C3240Or() {
    }

    public C3240Or(ResourceSelector[] resourceSelectorArr) {
        super(resourceSelectorArr);
    }

    @Override // org.apache.tools.ant.types.resources.selectors.ResourceSelector
    public boolean isSelected(Resource resource) {
        Iterator<ResourceSelector> selectors = getSelectors();
        while (selectors.hasNext()) {
            if (selectors.next().isSelected(resource)) {
                return true;
            }
        }
        return false;
    }
}
