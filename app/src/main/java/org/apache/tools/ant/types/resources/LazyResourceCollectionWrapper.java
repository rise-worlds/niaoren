package org.apache.tools.ant.types.resources;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import org.apache.tools.ant.types.Resource;

/* loaded from: classes2.dex */
public class LazyResourceCollectionWrapper extends AbstractResourceCollectionWrapper {
    private final List<Resource> cachedResources = new ArrayList();
    private FilteringIterator filteringIterator;

    protected boolean filterResource(Resource resource) {
        return false;
    }

    @Override // org.apache.tools.ant.types.resources.AbstractResourceCollectionWrapper
    protected Iterator<Resource> createIterator() {
        if (!isCache()) {
            return new FilteringIterator(getResourceCollection().iterator());
        }
        if (this.filteringIterator == null) {
            this.filteringIterator = new FilteringIterator(getResourceCollection().iterator());
        }
        return new CachedIterator(this.filteringIterator);
    }

    @Override // org.apache.tools.ant.types.resources.AbstractResourceCollectionWrapper
    protected int getSize() {
        Iterator<Resource> createIterator = createIterator();
        int i = 0;
        while (createIterator.hasNext()) {
            createIterator.next();
            i++;
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class FilteringIterator implements Iterator<Resource> {

        /* renamed from: it */
        protected final Iterator<Resource> f14769it;
        Resource next = null;
        boolean ended = false;

        public FilteringIterator(Iterator<Resource> it) {
            this.f14769it = it;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (this.ended) {
                return false;
            }
            while (this.next == null) {
                if (!this.f14769it.hasNext()) {
                    this.ended = true;
                    return false;
                }
                this.next = this.f14769it.next();
                if (LazyResourceCollectionWrapper.this.filterResource(this.next)) {
                    this.next = null;
                }
            }
            return true;
        }

        @Override // java.util.Iterator
        public Resource next() {
            if (hasNext()) {
                Resource resource = this.next;
                this.next = null;
                return resource;
            }
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class CachedIterator implements Iterator<Resource> {
        int cusrsor = 0;

        /* renamed from: it */
        private final Iterator<Resource> f14768it;

        public CachedIterator(Iterator<Resource> it) {
            this.f14768it = it;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            synchronized (LazyResourceCollectionWrapper.this.cachedResources) {
                if (LazyResourceCollectionWrapper.this.cachedResources.size() > this.cusrsor) {
                    return true;
                }
                if (!this.f14768it.hasNext()) {
                    return false;
                }
                LazyResourceCollectionWrapper.this.cachedResources.add(this.f14768it.next());
                return true;
            }
        }

        @Override // java.util.Iterator
        public Resource next() {
            Resource resource;
            if (hasNext()) {
                synchronized (LazyResourceCollectionWrapper.this.cachedResources) {
                    List list = LazyResourceCollectionWrapper.this.cachedResources;
                    int i = this.cusrsor;
                    this.cusrsor = i + 1;
                    resource = (Resource) list.get(i);
                }
                return resource;
            }
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
