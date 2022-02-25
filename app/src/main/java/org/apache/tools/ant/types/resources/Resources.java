package org.apache.tools.ant.types.resources;

import java.io.File;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Stack;
import java.util.Vector;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.types.DataType;
import org.apache.tools.ant.types.Resource;
import org.apache.tools.ant.types.ResourceCollection;
import org.apache.tools.ant.util.CollectionUtils;

/* loaded from: classes2.dex */
public class Resources extends DataType implements ResourceCollection {
    private boolean cache = false;
    private Collection<Resource> coll;

    /* renamed from: rc */
    private Vector<ResourceCollection> f14770rc;
    public static final ResourceCollection NONE = new ResourceCollection() { // from class: org.apache.tools.ant.types.resources.Resources.1
        @Override // org.apache.tools.ant.types.ResourceCollection
        public boolean isFilesystemOnly() {
            return true;
        }

        @Override // org.apache.tools.ant.types.ResourceCollection
        public int size() {
            return 0;
        }

        @Override // org.apache.tools.ant.types.ResourceCollection, java.lang.Iterable
        public Iterator<Resource> iterator() {
            return Resources.EMPTY_ITERATOR;
        }
    };
    public static final Iterator<Resource> EMPTY_ITERATOR = new Iterator<Resource>() { // from class: org.apache.tools.ant.types.resources.Resources.2
        @Override // java.util.Iterator
        public boolean hasNext() {
            return false;
        }

        @Override // java.util.Iterator
        public Resource next() {
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class MyCollection extends AbstractCollection<Resource> {
        private Collection<Resource> cached;

        MyCollection() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            return getCache().size();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<Resource> iterator() {
            return getCache().iterator();
        }

        private synchronized Collection<Resource> getCache() {
            Collection<Resource> collection;
            collection = this.cached;
            if (collection == null) {
                collection = CollectionUtils.asCollection(new MyIterator());
                if (Resources.this.cache) {
                    this.cached = collection;
                }
            }
            return collection;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes2.dex */
        public class MyIterator implements Iterator<Resource> {
            private Iterator<ResourceCollection> rci;

            /* renamed from: ri */
            private Iterator<Resource> f14771ri;

            private MyIterator() {
                this.rci = Resources.this.getNested().iterator();
                this.f14771ri = null;
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                Iterator<Resource> it = this.f14771ri;
                boolean z = it != null && it.hasNext();
                while (!z && this.rci.hasNext()) {
                    this.f14771ri = this.rci.next().iterator();
                    z = this.f14771ri.hasNext();
                }
                return z;
            }

            @Override // java.util.Iterator
            public Resource next() {
                if (hasNext()) {
                    return this.f14771ri.next();
                }
                throw new NoSuchElementException();
            }

            @Override // java.util.Iterator
            public void remove() {
                throw new UnsupportedOperationException();
            }
        }
    }

    public Resources() {
    }

    public Resources(Project project) {
        setProject(project);
    }

    public synchronized void setCache(boolean z) {
        this.cache = z;
    }

    public synchronized void add(ResourceCollection resourceCollection) {
        if (isReference()) {
            throw noChildrenAllowed();
        } else if (resourceCollection != null) {
            if (this.f14770rc == null) {
                this.f14770rc = new Vector<>();
            }
            this.f14770rc.add(resourceCollection);
            invalidateExistingIterators();
            this.coll = null;
            setChecked(false);
        }
    }

    @Override // org.apache.tools.ant.types.ResourceCollection, java.lang.Iterable
    public synchronized Iterator<Resource> iterator() {
        if (isReference()) {
            return getRef().iterator();
        }
        validate();
        return new FailFast(this, this.coll.iterator());
    }

    @Override // org.apache.tools.ant.types.ResourceCollection
    public synchronized int size() {
        if (isReference()) {
            return getRef().size();
        }
        validate();
        return this.coll.size();
    }

    @Override // org.apache.tools.ant.types.ResourceCollection
    public boolean isFilesystemOnly() {
        if (isReference()) {
            return getRef().isFilesystemOnly();
        }
        validate();
        for (ResourceCollection resourceCollection : getNested()) {
            if (!resourceCollection.isFilesystemOnly()) {
                return false;
            }
        }
        return true;
    }

    @Override // org.apache.tools.ant.types.DataType
    public synchronized String toString() {
        if (isReference()) {
            return getCheckedRef().toString();
        }
        validate();
        if (this.coll != null && !this.coll.isEmpty()) {
            StringBuffer stringBuffer = new StringBuffer();
            for (Resource resource : this.coll) {
                if (stringBuffer.length() > 0) {
                    stringBuffer.append(File.pathSeparatorChar);
                }
                stringBuffer.append(resource);
            }
            return stringBuffer.toString();
        }
        return "";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.tools.ant.types.DataType
    public void dieOnCircularReference(Stack<Object> stack, Project project) throws BuildException {
        if (!isChecked()) {
            if (isReference()) {
                super.dieOnCircularReference(stack, project);
                return;
            }
            for (ResourceCollection resourceCollection : getNested()) {
                if (resourceCollection instanceof DataType) {
                    pushAndInvokeCircularReferenceCheck((DataType) resourceCollection, stack, project);
                }
            }
            setChecked(true);
        }
    }

    protected void invalidateExistingIterators() {
        FailFast.invalidate(this);
    }

    private ResourceCollection getRef() {
        return (ResourceCollection) getCheckedRef(ResourceCollection.class, "ResourceCollection");
    }

    private synchronized void validate() {
        dieOnCircularReference();
        this.coll = this.coll == null ? new MyCollection() : this.coll;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized List<ResourceCollection> getNested() {
        return this.f14770rc == null ? Collections.emptyList() : this.f14770rc;
    }
}
