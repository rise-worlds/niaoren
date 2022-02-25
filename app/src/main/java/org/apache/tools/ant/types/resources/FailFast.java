package org.apache.tools.ant.types.resources;

import java.util.ConcurrentModificationException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.WeakHashMap;
import org.apache.tools.ant.types.Resource;

/* loaded from: classes2.dex */
class FailFast implements Iterator<Resource> {
    private static final WeakHashMap<Object, Set<FailFast>> MAP = new WeakHashMap<>();
    private final Object parent;
    private Iterator<Resource> wrapped;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static synchronized void invalidate(Object obj) {
        synchronized (FailFast.class) {
            Set<FailFast> set = MAP.get(obj);
            if (set != null) {
                set.clear();
            }
        }
    }

    private static synchronized void add(FailFast failFast) {
        synchronized (FailFast.class) {
            Set<FailFast> set = MAP.get(failFast.parent);
            if (set == null) {
                set = new HashSet<>();
                MAP.put(failFast.parent, set);
            }
            set.add(failFast);
        }
    }

    private static synchronized void remove(FailFast failFast) {
        synchronized (FailFast.class) {
            Set<FailFast> set = MAP.get(failFast.parent);
            if (set != null) {
                set.remove(failFast);
            }
        }
    }

    private static synchronized void failFast(FailFast failFast) {
        synchronized (FailFast.class) {
            if (!MAP.get(failFast.parent).contains(failFast)) {
                throw new ConcurrentModificationException();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public FailFast(Object obj, Iterator<Resource> it) {
        if (obj == null) {
            throw new IllegalArgumentException("parent object is null");
        } else if (it != null) {
            this.parent = obj;
            if (it.hasNext()) {
                this.wrapped = it;
                add(this);
            }
        } else {
            throw new IllegalArgumentException("cannot wrap null iterator");
        }
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        if (this.wrapped == null) {
            return false;
        }
        failFast(this);
        return this.wrapped.hasNext();
    }

    @Override // java.util.Iterator
    public Resource next() {
        Iterator<Resource> it = this.wrapped;
        if (it == null || !it.hasNext()) {
            throw new NoSuchElementException();
        }
        failFast(this);
        try {
            return this.wrapped.next();
        } finally {
            if (!this.wrapped.hasNext()) {
                this.wrapped = null;
                remove(this);
            }
        }
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
