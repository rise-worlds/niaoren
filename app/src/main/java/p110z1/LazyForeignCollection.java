package p110z1;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

/* renamed from: z1.sa */
/* loaded from: classes3.dex */
public class LazyForeignCollection<T, ID> extends BaseForeignCollection<T, ID> implements Serializable, ForeignCollection<T> {
    private static final long serialVersionUID = -5460708106909626233L;
    private transient CloseableIterator<T> lastIterator;

    @Override // p110z1.ForeignCollection
    public boolean isEager() {
        return false;
    }

    @Override // p110z1.ForeignCollection
    public int refreshCollection() {
        return 0;
    }

    public LazyForeignCollection(Dao<T, ID> rvVar, Object obj, Object obj2, FieldType ssVar, String str, boolean z) {
        super(rvVar, obj, obj2, ssVar, str, z);
    }

    @Override // java.util.Collection, java.lang.Iterable
    public CloseableIterator<T> iterator() {
        return closeableIterator();
    }

    @Override // p110z1.CloseableIterable
    public CloseableIterator<T> closeableIterator() {
        try {
            return iteratorThrow();
        } catch (SQLException e) {
            throw new IllegalStateException("Could not build lazy iterator for " + this.dao.mo1054i(), e);
        }
    }

    @Override // p110z1.ForeignCollection
    public CloseableIterator<T> iteratorThrow() throws SQLException {
        this.lastIterator = seperateIteratorThrow();
        return this.lastIterator;
    }

    public CloseableIterator<T> seperateIteratorThrow() throws SQLException {
        if (this.dao != null) {
            return this.dao.mo1065d((PreparedQuery) getPreparedQuery());
        }
        throw new IllegalStateException("Internal DAO object is null.  Lazy collections cannot be used if they have been deserialized.");
    }

    @Override // p110z1.ForeignCollection
    public CloseableWrappedIterable<T> getWrappedIterable() {
        return new CloseableWrappedIterableImpl(new CloseableIterable<T>() { // from class: z1.sa.1
            /* renamed from: a */
            public CloseableIterator<T> iterator() {
                return closeableIterator();
            }

            @Override // p110z1.CloseableIterable
            public CloseableIterator<T> closeableIterator() {
                try {
                    return LazyForeignCollection.this.seperateIteratorThrow();
                } catch (Exception e) {
                    throw new IllegalStateException("Could not build lazy iterator for " + LazyForeignCollection.this.dao.mo1054i(), e);
                }
            }
        });
    }

    @Override // p110z1.ForeignCollection
    public void closeLastIterator() throws SQLException {
        CloseableIterator<T> rsVar = this.lastIterator;
        if (rsVar != null) {
            rsVar.mo447a();
            this.lastIterator = null;
        }
    }

    @Override // java.util.Collection
    public int size() {
        CloseableIterator<T> it = iterator();
        int i = 0;
        while (it.hasNext()) {
            try {
                it.mo443d();
                i++;
            } finally {
                try {
                    it.mo447a();
                } catch (SQLException unused) {
                }
            }
        }
        return i;
    }

    @Override // java.util.Collection
    public boolean isEmpty() {
        CloseableIterator<T> it = iterator();
        try {
            return !it.hasNext();
        } finally {
            try {
                it.mo447a();
            } catch (SQLException unused) {
            }
        }
    }

    @Override // java.util.Collection
    public boolean contains(Object obj) {
        CloseableIterator<T> it = iterator();
        do {
            try {
                if (!it.hasNext()) {
                    try {
                        it.mo447a();
                    } catch (SQLException unused) {
                    }
                    return false;
                }
            } finally {
                try {
                    it.mo447a();
                } catch (SQLException unused2) {
                }
            }
        } while (!it.next().equals(obj));
        return true;
    }

    @Override // java.util.Collection
    public boolean containsAll(Collection<?> collection) {
        HashSet hashSet = new HashSet(collection);
        CloseableIterator<T> it = iterator();
        while (it.hasNext()) {
            try {
                hashSet.remove(it.next());
            } finally {
                try {
                    it.mo447a();
                } catch (SQLException unused) {
                }
            }
        }
        return hashSet.isEmpty();
    }

    @Override // p110z1.BaseForeignCollection, java.util.Collection
    public boolean remove(Object obj) {
        CloseableIterator<T> it = iterator();
        do {
            try {
                if (!it.hasNext()) {
                    try {
                        it.mo447a();
                    } catch (SQLException unused) {
                    }
                    return false;
                }
            } finally {
                try {
                    it.mo447a();
                } catch (SQLException unused2) {
                }
            }
        } while (!it.next().equals(obj));
        it.remove();
        return true;
    }

    @Override // p110z1.BaseForeignCollection, java.util.Collection
    public boolean removeAll(Collection<?> collection) {
        CloseableIterator<T> it = iterator();
        boolean z = false;
        while (it.hasNext()) {
            try {
                if (collection.contains(it.next())) {
                    it.remove();
                    z = true;
                }
            } finally {
                try {
                    it.mo447a();
                } catch (SQLException unused) {
                }
            }
        }
        return z;
    }

    @Override // java.util.Collection
    public Object[] toArray() {
        ArrayList arrayList = new ArrayList();
        CloseableIterator<T> it = iterator();
        while (it.hasNext()) {
            try {
                arrayList.add(it.next());
            } finally {
                try {
                    it.mo447a();
                } catch (SQLException unused) {
                }
            }
        }
        return arrayList.toArray();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v0, types: [E[], java.lang.Object[]] */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r4v3, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r4v4, types: [java.util.List, java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r4v5 */
    /* JADX WARN: Type inference failed for: r8v0, types: [java.lang.Object] */
    /* JADX WARN: Unknown variable types count: 1 */
    @Override // java.util.Collection
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public <E> E[] toArray(E[] r10) {
        /*
            r9 = this;
            z1.rs r0 = r9.iterator()
            r1 = 0
            r2 = 0
            r4 = r2
            r3 = 0
        L_0x0008:
            boolean r5 = r0.hasNext()     // Catch: all -> 0x0043
            if (r5 == 0) goto L_0x0031
            java.lang.Object r5 = r0.next()     // Catch: all -> 0x0043
            int r6 = r10.length     // Catch: all -> 0x0043
            if (r3 < r6) goto L_0x002c
            if (r4 != 0) goto L_0x0028
            java.util.ArrayList r4 = new java.util.ArrayList     // Catch: all -> 0x0043
            r4.<init>()     // Catch: all -> 0x0043
            int r6 = r10.length     // Catch: all -> 0x0043
            r7 = 0
        L_0x001e:
            if (r7 >= r6) goto L_0x0028
            r8 = r10[r7]     // Catch: all -> 0x0043
            r4.add(r8)     // Catch: all -> 0x0043
            int r7 = r7 + 1
            goto L_0x001e
        L_0x0028:
            r4.add(r5)     // Catch: all -> 0x0043
            goto L_0x002e
        L_0x002c:
            r10[r3] = r5     // Catch: all -> 0x0043
        L_0x002e:
            int r3 = r3 + 1
            goto L_0x0008
        L_0x0031:
            r0.mo447a()     // Catch: SQLException -> 0x0034
        L_0x0034:
            if (r4 != 0) goto L_0x003e
            int r0 = r10.length
            int r0 = r0 + (-1)
            if (r3 >= r0) goto L_0x003d
            r10[r3] = r2
        L_0x003d:
            return r10
        L_0x003e:
            java.lang.Object[] r10 = r4.toArray(r10)
            return r10
        L_0x0043:
            r10 = move-exception
            r0.mo447a()     // Catch: SQLException -> 0x0047
        L_0x0047:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: p110z1.LazyForeignCollection.toArray(java.lang.Object[]):java.lang.Object[]");
    }

    @Override // p110z1.ForeignCollection
    public int updateAll() {
        throw new UnsupportedOperationException("Cannot call updateAll() on a lazy collection.");
    }

    @Override // p110z1.ForeignCollection
    public int refreshAll() {
        throw new UnsupportedOperationException("Cannot call updateAll() on a lazy collection.");
    }

    @Override // java.util.Collection
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override // java.util.Collection
    public int hashCode() {
        return super.hashCode();
    }
}
