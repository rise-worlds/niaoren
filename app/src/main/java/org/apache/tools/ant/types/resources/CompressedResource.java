package org.apache.tools.ant.types.resources;

import org.apache.tools.ant.types.ResourceCollection;

/* loaded from: classes2.dex */
public abstract class CompressedResource extends ContentTransformingResource {
    protected abstract String getCompressionName();

    /* JADX INFO: Access modifiers changed from: protected */
    public CompressedResource() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public CompressedResource(ResourceCollection resourceCollection) {
        addConfigured(resourceCollection);
    }

    @Override // org.apache.tools.ant.types.Resource, org.apache.tools.ant.types.DataType
    public String toString() {
        return getCompressionName() + " compressed " + super.toString();
    }
}
