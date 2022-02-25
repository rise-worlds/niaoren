package org.apache.tools.ant.types.resources.selectors;

import java.util.Iterator;
import org.apache.tools.ant.types.Resource;

/* loaded from: classes2.dex */
public class Majority extends ResourceSelectorContainer implements ResourceSelector {
    private boolean tie = true;

    public Majority() {
    }

    public Majority(ResourceSelector[] resourceSelectorArr) {
        super(resourceSelectorArr);
    }

    public synchronized void setAllowtie(boolean z) {
        this.tie = z;
    }

    @Override // org.apache.tools.ant.types.resources.selectors.ResourceSelector
    public synchronized boolean isSelected(Resource resource) {
        int selectorCount = selectorCount();
        boolean z = selectorCount % 2 == 0;
        int i = selectorCount / 2;
        Iterator<ResourceSelector> selectors = getSelectors();
        int i2 = 0;
        int i3 = 0;
        while (selectors.hasNext()) {
            if (selectors.next().isSelected(resource)) {
                i3++;
                if (i3 > i || (z && this.tie && i3 == i)) {
                    return true;
                }
            } else {
                i2++;
                if (i2 > i || (z && !this.tie && i2 == i)) {
                    return false;
                }
            }
        }
        return false;
    }
}
