package p110z1;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;

/* renamed from: z1.rq */
/* loaded from: classes3.dex */
public abstract class BaseForeignCollection<T, ID> implements Serializable, ForeignCollection<T> {
    private static final long serialVersionUID = -5158840898186237589L;
    protected final transient Dao<T, ID> dao;
    private final transient FieldType foreignFieldType;
    private final transient boolean orderAscending;
    private final transient String orderColumn;
    private final transient Object parent;
    private final transient Object parentId;
    private transient PreparedQuery<T> preparedQuery;

    @Override // java.util.Collection
    public abstract boolean remove(Object obj);

    @Override // java.util.Collection
    public abstract boolean removeAll(Collection<?> collection);

    /* JADX INFO: Access modifiers changed from: protected */
    public BaseForeignCollection(Dao<T, ID> rvVar, Object obj, Object obj2, FieldType ssVar, String str, boolean z) {
        this.dao = rvVar;
        this.foreignFieldType = ssVar;
        this.parentId = obj2;
        this.orderColumn = str;
        this.orderAscending = z;
        this.parent = obj;
    }

    @Override // p110z1.ForeignCollection, java.util.Collection
    public boolean add(T t) {
        try {
            if (this.parent != null && this.foreignFieldType.m712f(t) == null) {
                this.foreignFieldType.m727a((Object) t, this.parent, true, (ObjectCache) null);
            }
            if (this.dao == null) {
                return false;
            }
            this.dao.mo1062e((Dao<T, ID>) t);
            return true;
        } catch (SQLException e) {
            throw new IllegalStateException("Could not create data element in dao", e);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Collection
    public boolean addAll(Collection<? extends T> collection) {
        boolean z = false;
        if (this.dao == null) {
            return false;
        }
        Iterator<? extends T> it = collection.iterator();
        while (it.hasNext()) {
            try {
                this.dao.mo1062e((Dao<T, ID>) it.next());
                z = true;
            } catch (SQLException e) {
                throw new IllegalStateException("Could not create data elements in dao", e);
            }
        }
        return z;
    }

    @Override // java.util.Collection
    public boolean retainAll(Collection<?> collection) {
        boolean z = false;
        if (this.dao == null) {
            return false;
        }
        CloseableIterator<T> closeableIterator = closeableIterator();
        while (closeableIterator.hasNext()) {
            try {
                if (!collection.contains(closeableIterator.next())) {
                    closeableIterator.remove();
                    z = true;
                }
            } finally {
                try {
                    closeableIterator.mo447a();
                } catch (SQLException unused) {
                }
            }
        }
        return z;
    }

    @Override // java.util.Collection
    public void clear() {
        if (this.dao != null) {
            CloseableIterator<T> closeableIterator = closeableIterator();
            while (closeableIterator.hasNext()) {
                try {
                    closeableIterator.next();
                    closeableIterator.remove();
                } finally {
                    try {
                        closeableIterator.mo447a();
                    } catch (SQLException unused) {
                    }
                }
            }
        }
    }

    @Override // p110z1.ForeignCollection
    public int update(T t) throws SQLException {
        Dao<T, ID> rvVar = this.dao;
        if (rvVar == null) {
            return 0;
        }
        return rvVar.mo1055h(t);
    }

    @Override // p110z1.ForeignCollection
    public int refresh(T t) throws SQLException {
        Dao<T, ID> rvVar = this.dao;
        if (rvVar == null) {
            return 0;
        }
        return rvVar.mo1053i(t);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public PreparedQuery<T> getPreparedQuery() throws SQLException {
        if (this.dao == null) {
            return null;
        }
        if (this.preparedQuery == null) {
            SelectArg vcVar = new SelectArg();
            vcVar.mo387a(this.parentId);
            QueryBuilder<T, ID> c = this.dao.mo1073c();
            String str = this.orderColumn;
            if (str != null) {
                c.m496a(str, this.orderAscending);
            }
            this.preparedQuery = c.m427o().m367a(this.foreignFieldType.m715e(), vcVar).m339d();
            PreparedQuery<T> uwVar = this.preparedQuery;
            if (uwVar instanceof MappedPreparedStmt) {
                ((MappedPreparedStmt) uwVar).m323a(this.parent, this.parentId);
            }
        }
        return this.preparedQuery;
    }
}
