package p110z1;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/* renamed from: z1.rx */
/* loaded from: classes3.dex */
public class EagerForeignCollection<T, ID> extends BaseForeignCollection<T, ID> implements Serializable, CloseableWrappedIterable<T>, ForeignCollection<T> {
    private static final long serialVersionUID = -2523335606983317721L;
    private List<T> results;

    @Override // p110z1.CloseableWrappedIterable
    public void close() {
    }

    @Override // p110z1.ForeignCollection
    public void closeLastIterator() {
    }

    @Override // p110z1.ForeignCollection
    public CloseableWrappedIterable<T> getWrappedIterable() {
        return this;
    }

    @Override // p110z1.ForeignCollection
    public boolean isEager() {
        return true;
    }

    public EagerForeignCollection(Dao<T, ID> rvVar, Object obj, Object obj2, FieldType ssVar, String str, boolean z) throws SQLException {
        super(rvVar, obj, obj2, ssVar, str, z);
        if (obj2 == null) {
            this.results = new ArrayList();
        } else {
            this.results = rvVar.mo1076b((PreparedQuery) getPreparedQuery());
        }
    }

    @Override // java.util.Collection, java.lang.Iterable
    public CloseableIterator<T> iterator() {
        return iteratorThrow();
    }

    @Override // p110z1.CloseableIterable
    public CloseableIterator<T> closeableIterator() {
        return iteratorThrow();
    }

    @Override // p110z1.ForeignCollection
    public CloseableIterator<T> iteratorThrow() {
        return new CloseableIterator<T>() { // from class: z1.rx.1

            /* renamed from: b */
            private int f23225b = -1;

            @Override // p110z1.CloseableIterator
            /* renamed from: a */
            public void mo447a() {
            }

            @Override // p110z1.CloseableIterator
            /* renamed from: b */
            public void mo445b() {
            }

            @Override // p110z1.CloseableIterator
            /* renamed from: c */
            public DatabaseResults mo444c() {
                return null;
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.f23225b + 1 < EagerForeignCollection.this.results.size();
            }

            @Override // p110z1.CloseableIterator
            /* renamed from: e */
            public T mo442e() {
                this.f23225b = 0;
                if (this.f23225b >= EagerForeignCollection.this.results.size()) {
                    return null;
                }
                return (T) EagerForeignCollection.this.results.get(0);
            }

            @Override // java.util.Iterator
            public T next() {
                this.f23225b++;
                return (T) EagerForeignCollection.this.results.get(this.f23225b);
            }

            @Override // p110z1.CloseableIterator
            /* renamed from: h */
            public T mo439h() {
                this.f23225b++;
                if (this.f23225b >= EagerForeignCollection.this.results.size()) {
                    return null;
                }
                return (T) EagerForeignCollection.this.results.get(this.f23225b);
            }

            @Override // p110z1.CloseableIterator
            /* renamed from: g */
            public T mo440g() {
                if (this.f23225b < 0) {
                    this.f23225b = 0;
                }
                if (this.f23225b >= EagerForeignCollection.this.results.size()) {
                    return null;
                }
                return (T) EagerForeignCollection.this.results.get(this.f23225b);
            }

            @Override // p110z1.CloseableIterator
            /* renamed from: f */
            public T mo441f() {
                this.f23225b--;
                int i = this.f23225b;
                if (i < 0 || i >= EagerForeignCollection.this.results.size()) {
                    return null;
                }
                return (T) EagerForeignCollection.this.results.get(this.f23225b);
            }

            @Override // p110z1.CloseableIterator
            /* renamed from: a */
            public T mo446a(int i) {
                this.f23225b += i;
                int i2 = this.f23225b;
                if (i2 < 0 || i2 >= EagerForeignCollection.this.results.size()) {
                    return null;
                }
                return (T) EagerForeignCollection.this.results.get(this.f23225b);
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Iterator
            public void remove() {
                int i = this.f23225b;
                if (i < 0) {
                    throw new IllegalStateException("next() must be called before remove()");
                } else if (i < EagerForeignCollection.this.results.size()) {
                    Object remove = EagerForeignCollection.this.results.remove(this.f23225b);
                    this.f23225b--;
                    if (EagerForeignCollection.this.dao != null) {
                        try {
                            EagerForeignCollection.this.dao.mo1051j(remove);
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                    }
                } else {
                    throw new IllegalStateException("current results position (" + this.f23225b + ") is out of bounds");
                }
            }

            @Override // p110z1.CloseableIterator
            /* renamed from: d */
            public void mo443d() {
                this.f23225b++;
            }
        };
    }

    @Override // java.util.Collection
    public int size() {
        return this.results.size();
    }

    @Override // java.util.Collection
    public boolean isEmpty() {
        return this.results.isEmpty();
    }

    @Override // java.util.Collection
    public boolean contains(Object obj) {
        return this.results.contains(obj);
    }

    @Override // java.util.Collection
    public boolean containsAll(Collection<?> collection) {
        return this.results.containsAll(collection);
    }

    @Override // java.util.Collection
    public Object[] toArray() {
        return this.results.toArray();
    }

    @Override // java.util.Collection
    public <E> E[] toArray(E[] eArr) {
        return (E[]) this.results.toArray(eArr);
    }

    @Override // p110z1.BaseForeignCollection, p110z1.ForeignCollection, java.util.Collection
    public boolean add(T t) {
        if (this.results.add(t)) {
            return super.add(t);
        }
        return false;
    }

    @Override // p110z1.BaseForeignCollection, java.util.Collection
    public boolean addAll(Collection<? extends T> collection) {
        if (this.results.addAll(collection)) {
            return super.addAll(collection);
        }
        return false;
    }

    @Override // p110z1.BaseForeignCollection, java.util.Collection
    public boolean remove(Object obj) {
        if (!this.results.remove(obj) || this.dao == null) {
            return false;
        }
        try {
            return this.dao.mo1051j(obj) == 1;
        } catch (SQLException e) {
            throw new IllegalStateException("Could not delete data element from dao", e);
        }
    }

    @Override // p110z1.BaseForeignCollection, java.util.Collection
    public boolean removeAll(Collection<?> collection) {
        Iterator<?> it = collection.iterator();
        boolean z = false;
        while (it.hasNext()) {
            if (remove(it.next())) {
                z = true;
            }
        }
        return z;
    }

    @Override // p110z1.BaseForeignCollection, java.util.Collection
    public boolean retainAll(Collection<?> collection) {
        return super.retainAll(collection);
    }

    @Override // p110z1.ForeignCollection
    public int updateAll() throws SQLException {
        int i = 0;
        for (T t : this.results) {
            i += this.dao.mo1055h(t);
        }
        return i;
    }

    @Override // p110z1.ForeignCollection
    public int refreshAll() throws SQLException {
        int i = 0;
        for (T t : this.results) {
            i += this.dao.mo1053i(t);
        }
        return i;
    }

    @Override // p110z1.ForeignCollection
    public int refreshCollection() throws SQLException {
        this.results = this.dao.mo1076b((PreparedQuery) getPreparedQuery());
        return this.results.size();
    }

    @Override // java.util.Collection
    public boolean equals(Object obj) {
        if (!(obj instanceof EagerForeignCollection)) {
            return false;
        }
        return this.results.equals(((EagerForeignCollection) obj).results);
    }

    @Override // java.util.Collection
    public int hashCode() {
        return this.results.hashCode();
    }
}
