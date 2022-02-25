package org.apache.tools.ant.types.resources;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.types.DataType;
import org.apache.tools.ant.types.Resource;
import org.apache.tools.ant.types.ResourceCollection;

/* loaded from: classes2.dex */
public abstract class BaseResourceCollectionContainer extends DataType implements Cloneable, ResourceCollection {

    /* renamed from: rc */
    private List<ResourceCollection> f14766rc = new ArrayList();
    private Collection<Resource> coll = null;
    private boolean cache = true;

    protected abstract Collection<Resource> getCollection();

    public BaseResourceCollectionContainer() {
    }

    public BaseResourceCollectionContainer(Project project) {
        setProject(project);
    }

    public synchronized void setCache(boolean z) {
        this.cache = z;
    }

    public synchronized boolean isCache() {
        return this.cache;
    }

    public synchronized void clear() throws BuildException {
        if (!isReference()) {
            this.f14766rc.clear();
            FailFast.invalidate(this);
            this.coll = null;
            setChecked(false);
        } else {
            throw noChildrenAllowed();
        }
    }

    public synchronized void add(ResourceCollection resourceCollection) throws BuildException {
        Project project;
        if (isReference()) {
            throw noChildrenAllowed();
        } else if (resourceCollection != null) {
            if (Project.getProject(resourceCollection) == null && (project = getProject()) != null) {
                project.setProjectReference(resourceCollection);
            }
            this.f14766rc.add(resourceCollection);
            FailFast.invalidate(this);
            this.coll = null;
            setChecked(false);
        }
    }

    public synchronized void addAll(Collection<? extends ResourceCollection> collection) throws BuildException {
        if (!isReference()) {
            try {
                for (ResourceCollection resourceCollection : collection) {
                    add(resourceCollection);
                }
            } catch (ClassCastException e) {
                throw new BuildException(e);
            }
        } else {
            throw noChildrenAllowed();
        }
    }

    @Override // org.apache.tools.ant.types.ResourceCollection, java.lang.Iterable
    public final synchronized Iterator<Resource> iterator() {
        if (isReference()) {
            return ((BaseResourceCollectionContainer) getCheckedRef()).iterator();
        }
        dieOnCircularReference();
        return new FailFast(this, cacheCollection().iterator());
    }

    @Override // org.apache.tools.ant.types.ResourceCollection
    public synchronized int size() {
        if (isReference()) {
            return ((BaseResourceCollectionContainer) getCheckedRef(BaseResourceCollectionContainer.class, getDataTypeName())).size();
        }
        dieOnCircularReference();
        return cacheCollection().size();
    }

    @Override // org.apache.tools.ant.types.ResourceCollection
    public synchronized boolean isFilesystemOnly() {
        if (isReference()) {
            return ((BaseResourceCollectionContainer) getCheckedRef()).isFilesystemOnly();
        }
        dieOnCircularReference();
        Iterator<ResourceCollection> it = this.f14766rc.iterator();
        boolean z = true;
        while (z && it.hasNext()) {
            z = it.next().isFilesystemOnly();
        }
        if (z) {
            return true;
        }
        for (Resource resource : cacheCollection()) {
            if (resource.mo14823as(FileProvider.class) == null) {
                return false;
            }
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
                for (ResourceCollection resourceCollection : this.f14766rc) {
                    if (resourceCollection instanceof DataType) {
                        pushAndInvokeCircularReferenceCheck((DataType) resourceCollection, stack, project);
                    }
                }
                setChecked(true);
            }
        }
    }

    public final synchronized List<ResourceCollection> getResourceCollections() {
        dieOnCircularReference();
        return Collections.unmodifiableList(this.f14766rc);
    }

    @Override // org.apache.tools.ant.types.DataType, org.apache.tools.ant.ProjectComponent
    public Object clone() {
        try {
            BaseResourceCollectionContainer baseResourceCollectionContainer = (BaseResourceCollectionContainer) super.clone();
            baseResourceCollectionContainer.f14766rc = new ArrayList(this.f14766rc);
            baseResourceCollectionContainer.coll = null;
            return baseResourceCollectionContainer;
        } catch (CloneNotSupportedException e) {
            throw new BuildException(e);
        }
    }

    @Override // org.apache.tools.ant.types.DataType
    public synchronized String toString() {
        if (isReference()) {
            return getCheckedRef().toString();
        } else if (cacheCollection().size() == 0) {
            return "";
        } else {
            StringBuilder sb = new StringBuilder();
            for (Resource resource : this.coll) {
                if (sb.length() > 0) {
                    sb.append(File.pathSeparatorChar);
                }
                sb.append(resource);
            }
            return sb.toString();
        }
    }

    private synchronized Collection<Resource> cacheCollection() {
        if (this.coll == null || !isCache()) {
            this.coll = getCollection();
        }
        return this.coll;
    }
}
