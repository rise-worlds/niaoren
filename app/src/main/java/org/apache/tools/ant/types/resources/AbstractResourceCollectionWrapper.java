package org.apache.tools.ant.types.resources;

import java.io.File;
import java.util.Iterator;
import java.util.Stack;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.types.DataType;
import org.apache.tools.ant.types.Resource;
import org.apache.tools.ant.types.ResourceCollection;

/* loaded from: classes2.dex */
public abstract class AbstractResourceCollectionWrapper extends DataType implements Cloneable, ResourceCollection {
    private static final String ONE_NESTED_MESSAGE = " expects exactly one nested resource collection.";
    private boolean cache = true;

    /* renamed from: rc */
    private ResourceCollection f14765rc;

    protected abstract Iterator<Resource> createIterator();

    protected abstract int getSize();

    public synchronized void setCache(boolean z) {
        this.cache = z;
    }

    public synchronized boolean isCache() {
        return this.cache;
    }

    public synchronized void add(ResourceCollection resourceCollection) throws BuildException {
        Project project;
        if (isReference()) {
            throw noChildrenAllowed();
        } else if (resourceCollection != null) {
            if (this.f14765rc == null) {
                this.f14765rc = resourceCollection;
                if (Project.getProject(this.f14765rc) == null && (project = getProject()) != null) {
                    project.setProjectReference(this.f14765rc);
                }
                setChecked(false);
                return;
            }
            throw oneNested();
        }
    }

    @Override // org.apache.tools.ant.types.ResourceCollection, java.lang.Iterable
    public final synchronized Iterator<Resource> iterator() {
        if (isReference()) {
            return ((AbstractResourceCollectionWrapper) getCheckedRef()).iterator();
        }
        dieOnCircularReference();
        return new FailFast(this, createIterator());
    }

    @Override // org.apache.tools.ant.types.ResourceCollection
    public synchronized int size() {
        if (isReference()) {
            return ((AbstractResourceCollectionWrapper) getCheckedRef()).size();
        }
        dieOnCircularReference();
        return getSize();
    }

    @Override // org.apache.tools.ant.types.ResourceCollection
    public synchronized boolean isFilesystemOnly() {
        if (isReference()) {
            return ((BaseResourceCollectionContainer) getCheckedRef()).isFilesystemOnly();
        }
        dieOnCircularReference();
        if (this.f14765rc != null && !this.f14765rc.isFilesystemOnly()) {
            Iterator<Resource> it = iterator();
            while (it.hasNext()) {
                if (it.next().mo14823as(FileProvider.class) == null) {
                    return false;
                }
            }
            return true;
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.tools.ant.types.DataType
    public synchronized void dieOnCircularReference(Stack<Object> stack, Project project) throws BuildException {
        if (!isChecked()) {
            if (isReference()) {
                super.dieOnCircularReference(stack, project);
            } else {
                if (this.f14765rc instanceof DataType) {
                    pushAndInvokeCircularReferenceCheck((DataType) this.f14765rc, stack, project);
                }
                setChecked(true);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final synchronized ResourceCollection getResourceCollection() {
        dieOnCircularReference();
        if (this.f14765rc != null) {
        } else {
            throw oneNested();
        }
        return this.f14765rc;
    }

    @Override // org.apache.tools.ant.types.DataType
    public synchronized String toString() {
        if (isReference()) {
            return getCheckedRef().toString();
        } else if (getSize() == 0) {
            return "";
        } else {
            StringBuilder sb = new StringBuilder();
            Iterator<Resource> it = iterator();
            while (it.hasNext()) {
                Resource next = it.next();
                if (sb.length() > 0) {
                    sb.append(File.pathSeparatorChar);
                }
                sb.append(next);
            }
            return sb.toString();
        }
    }

    private BuildException oneNested() {
        return new BuildException(super.toString() + ONE_NESTED_MESSAGE);
    }
}
