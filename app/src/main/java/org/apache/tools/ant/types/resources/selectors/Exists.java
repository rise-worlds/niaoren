package org.apache.tools.ant.types.resources.selectors;

import org.apache.tools.ant.types.Resource;

/* loaded from: classes2.dex */
public class Exists implements ResourceSelector {
    @Override // org.apache.tools.ant.types.resources.selectors.ResourceSelector
    public boolean isSelected(Resource resource) {
        return resource.isExists();
    }
}
