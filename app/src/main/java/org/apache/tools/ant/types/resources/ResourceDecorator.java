package org.apache.tools.ant.types.resources;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Stack;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.types.Reference;
import org.apache.tools.ant.types.Resource;
import org.apache.tools.ant.types.ResourceCollection;

/* loaded from: classes2.dex */
public abstract class ResourceDecorator extends Resource {
    private Resource resource;

    /* JADX INFO: Access modifiers changed from: protected */
    public ResourceDecorator() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ResourceDecorator(ResourceCollection resourceCollection) {
        addConfigured(resourceCollection);
    }

    public final void addConfigured(ResourceCollection resourceCollection) {
        checkChildrenAllowed();
        if (this.resource != null) {
            throw new BuildException("you must not specify more than one resource");
        } else if (resourceCollection.size() == 1) {
            setChecked(false);
            this.resource = resourceCollection.iterator().next();
        } else {
            throw new BuildException("only single argument resource collections are supported");
        }
    }

    @Override // org.apache.tools.ant.types.Resource
    public String getName() {
        return getResource().getName();
    }

    @Override // org.apache.tools.ant.types.Resource
    public boolean isExists() {
        return getResource().isExists();
    }

    @Override // org.apache.tools.ant.types.Resource
    public long getLastModified() {
        return getResource().getLastModified();
    }

    @Override // org.apache.tools.ant.types.Resource
    public boolean isDirectory() {
        return getResource().isDirectory();
    }

    @Override // org.apache.tools.ant.types.Resource
    public long getSize() {
        return getResource().getSize();
    }

    @Override // org.apache.tools.ant.types.Resource
    public InputStream getInputStream() throws IOException {
        return getResource().getInputStream();
    }

    @Override // org.apache.tools.ant.types.Resource
    public OutputStream getOutputStream() throws IOException {
        return getResource().getOutputStream();
    }

    @Override // org.apache.tools.ant.types.Resource, org.apache.tools.ant.types.ResourceCollection
    public boolean isFilesystemOnly() {
        return mo14823as(FileProvider.class) != null;
    }

    @Override // org.apache.tools.ant.types.Resource, org.apache.tools.ant.types.DataType
    public void setRefid(Reference reference) {
        if (this.resource == null) {
            super.setRefid(reference);
            return;
        }
        throw noChildrenAllowed();
    }

    @Override // org.apache.tools.ant.types.Resource
    /* renamed from: as */
    public <T> T mo14823as(Class<T> cls) {
        return (T) getResource().mo14823as(cls);
    }

    @Override // org.apache.tools.ant.types.Resource
    public int compareTo(Resource resource) {
        if (resource == this) {
            return 0;
        }
        if (resource instanceof ResourceDecorator) {
            return getResource().compareTo(((ResourceDecorator) resource).getResource());
        }
        return getResource().compareTo(resource);
    }

    @Override // org.apache.tools.ant.types.Resource
    public int hashCode() {
        return (getClass().hashCode() << 4) | getResource().hashCode();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final Resource getResource() {
        if (isReference()) {
            return (Resource) getCheckedRef();
        }
        if (this.resource != null) {
            dieOnCircularReference();
            return this.resource;
        }
        throw new BuildException("no resource specified");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.tools.ant.types.DataType
    public void dieOnCircularReference(Stack<Object> stack, Project project) throws BuildException {
        if (!isChecked()) {
            if (isReference()) {
                super.dieOnCircularReference(stack, project);
                return;
            }
            pushAndInvokeCircularReferenceCheck(this.resource, stack, project);
            setChecked(true);
        }
    }

    @Override // org.apache.tools.ant.types.Resource
    public void setName(String str) throws BuildException {
        throw new BuildException("you can't change the name of a " + getDataTypeName());
    }

    @Override // org.apache.tools.ant.types.Resource
    public void setExists(boolean z) {
        throw new BuildException("you can't change the exists state of a " + getDataTypeName());
    }

    @Override // org.apache.tools.ant.types.Resource
    public void setLastModified(long j) throws BuildException {
        throw new BuildException("you can't change the timestamp of a " + getDataTypeName());
    }

    @Override // org.apache.tools.ant.types.Resource
    public void setDirectory(boolean z) throws BuildException {
        throw new BuildException("you can't change the directory state of a " + getDataTypeName());
    }

    @Override // org.apache.tools.ant.types.Resource
    public void setSize(long j) throws BuildException {
        throw new BuildException("you can't change the size of a " + getDataTypeName());
    }
}
