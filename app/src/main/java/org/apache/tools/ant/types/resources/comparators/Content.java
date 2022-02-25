package org.apache.tools.ant.types.resources.comparators;

import java.io.IOException;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.types.Resource;
import org.apache.tools.ant.util.ResourceUtils;

/* loaded from: classes2.dex */
public class Content extends ResourceComparator {
    private boolean binary = true;

    public void setBinary(boolean z) {
        this.binary = z;
    }

    public boolean isBinary() {
        return this.binary;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.tools.ant.types.resources.comparators.ResourceComparator
    public int resourceCompare(Resource resource, Resource resource2) {
        try {
            return ResourceUtils.compareContent(resource, resource2, !this.binary);
        } catch (IOException e) {
            throw new BuildException(e);
        }
    }
}
