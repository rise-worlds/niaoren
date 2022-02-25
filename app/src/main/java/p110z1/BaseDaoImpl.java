package p110z1;

import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import p110z1.Dao;
import p110z1.StatementBuilder;

/* renamed from: z1.rp */
/* loaded from: classes3.dex */
public abstract class BaseDaoImpl<T, ID> implements Dao<T, ID> {

    /* renamed from: j */
    private static final ThreadLocal<C5550a> f23194j = new ThreadLocal<C5550a>() { // from class: z1.rp.1
        /* JADX INFO: Access modifiers changed from: protected */
        /* renamed from: a */
        public C5550a initialValue() {
            return new C5550a();
        }
    };

    /* renamed from: k */
    private static ReferenceObjectCache f23195k;

    /* renamed from: a */
    protected StatementExecutor<T, ID> f23196a;

    /* renamed from: b */
    protected DatabaseType f23197b;

    /* renamed from: c */
    protected final Class<T> f23198c;

    /* renamed from: d */
    protected DatabaseTableConfig<T> f23199d;

    /* renamed from: e */
    protected TableInfo<T, ID> f23200e;

    /* renamed from: f */
    protected ConnectionSource f23201f;

    /* renamed from: g */
    protected CloseableIterator<T> f23202g;

    /* renamed from: h */
    protected ObjectFactory<T> f23203h;

    /* renamed from: i */
    private boolean f23204i;

    /* renamed from: l */
    private ObjectCache f23205l;

    protected BaseDaoImpl(Class<T> cls) throws SQLException {
        this(null, cls, null);
    }

    protected BaseDaoImpl(ConnectionSource wmVar, Class<T> cls) throws SQLException {
        this(wmVar, cls, null);
    }

    protected BaseDaoImpl(ConnectionSource wmVar, DatabaseTableConfig<T> wrVar) throws SQLException {
        this(wmVar, wrVar.m188b(), wrVar);
    }

    private BaseDaoImpl(ConnectionSource wmVar, Class<T> cls, DatabaseTableConfig<T> wrVar) throws SQLException {
        this.f23198c = cls;
        this.f23199d = wrVar;
        if (wmVar != null) {
            this.f23201f = wmVar;
            m1129a();
        }
    }

    /* renamed from: a */
    public void m1129a() throws SQLException {
        if (!this.f23204i) {
            ConnectionSource wmVar = this.f23201f;
            if (wmVar != null) {
                this.f23197b = wmVar.mo249e();
                if (this.f23197b != null) {
                    DatabaseTableConfig<T> wrVar = this.f23199d;
                    if (wrVar == null) {
                        this.f23200e = new TableInfo<>(this.f23201f, this, this.f23198c);
                    } else {
                        wrVar.m192a(this.f23201f);
                        this.f23200e = new TableInfo<>(this.f23197b, this, this.f23199d);
                    }
                    this.f23196a = new StatementExecutor<>(this.f23197b, this.f23200e, this);
                    C5550a aVar = f23194j.get();
                    if (aVar.m1111a() > 0) {
                        aVar.m1109a((BaseDaoImpl<?, ?>) this);
                        return;
                    }
                    aVar.m1109a((BaseDaoImpl<?, ?>) this);
                    for (int i = 0; i < aVar.m1111a(); i++) {
                        try {
                            BaseDaoImpl<?, ?> a = aVar.m1110a(i);
                            DaoManager.m1026a(this.f23201f, a);
                            try {
                                for (FieldType ssVar : a.m1114v().m169c()) {
                                    ssVar.m724a(this.f23201f, a.mo1054i());
                                }
                                a.f23204i = true;
                            } catch (SQLException e) {
                                DaoManager.m1021b(this.f23201f, a);
                                throw e;
                            }
                        } finally {
                            aVar.m1108b();
                        }
                    }
                    return;
                }
                throw new IllegalStateException("connectionSource is getting a null DatabaseType in " + getClass().getSimpleName());
            }
            throw new IllegalStateException("connectionSource was never set on " + getClass().getSimpleName());
        }
    }

    @Override // p110z1.Dao
    /* renamed from: a */
    public T mo1104a(ID id) throws SQLException {
        m1113x();
        DatabaseConnection a = this.f23201f.mo256a();
        try {
            return this.f23196a.m409a(a, (DatabaseConnection) id, this.f23205l);
        } finally {
            this.f23201f.mo255a(a);
        }
    }

    @Override // p110z1.Dao
    /* renamed from: a */
    public T mo1091a(PreparedQuery<T> uwVar) throws SQLException {
        m1113x();
        DatabaseConnection a = this.f23201f.mo256a();
        try {
            return this.f23196a.m403a(a, (PreparedStmt) uwVar, this.f23205l);
        } finally {
            this.f23201f.mo255a(a);
        }
    }

    @Override // p110z1.Dao
    /* renamed from: b */
    public List<T> mo1083b() throws SQLException {
        m1113x();
        return this.f23196a.m414a(this.f23201f, this.f23205l);
    }

    @Override // p110z1.Dao
    /* renamed from: a */
    public List<T> mo1100a(String str, Object obj) throws SQLException {
        return mo1073c().m427o().m367a(str, obj).m336e();
    }

    @Override // p110z1.Dao
    /* renamed from: c */
    public QueryBuilder<T, ID> mo1073c() {
        m1113x();
        return new QueryBuilder<>(this.f23197b, this.f23200e, this);
    }

    @Override // p110z1.Dao
    /* renamed from: d */
    public UpdateBuilder<T, ID> mo1068d() {
        m1113x();
        return new UpdateBuilder<>(this.f23197b, this.f23200e, this);
    }

    @Override // p110z1.Dao
    /* renamed from: e */
    public DeleteBuilder<T, ID> mo1063e() {
        m1113x();
        return new DeleteBuilder<>(this.f23197b, this.f23200e, this);
    }

    @Override // p110z1.Dao
    /* renamed from: b */
    public List<T> mo1076b(PreparedQuery<T> uwVar) throws SQLException {
        m1113x();
        return this.f23196a.m413a(this.f23201f, uwVar, this.f23205l);
    }

    @Override // p110z1.Dao
    /* renamed from: b */
    public List<T> mo1082b(T t) throws SQLException {
        return m1128a((BaseDaoImpl<T, ID>) t, false);
    }

    @Override // p110z1.Dao
    /* renamed from: c */
    public List<T> mo1072c(T t) throws SQLException {
        return m1128a((BaseDaoImpl<T, ID>) t, true);
    }

    @Override // p110z1.Dao
    /* renamed from: a */
    public List<T> mo1095a(Map<String, Object> map) throws SQLException {
        return m1127a(map, false);
    }

    @Override // p110z1.Dao
    /* renamed from: b */
    public List<T> mo1077b(Map<String, Object> map) throws SQLException {
        return m1127a(map, true);
    }

    @Override // p110z1.Dao
    /* renamed from: d */
    public T mo1067d(T t) throws SQLException {
        ID m;
        m1113x();
        if (t == null || (m = mo1045m(t)) == null) {
            return null;
        }
        return mo1104a((BaseDaoImpl<T, ID>) m);
    }

    @Override // p110z1.Dao
    /* renamed from: e */
    public int mo1062e(T t) throws SQLException {
        m1113x();
        if (t == null) {
            return 0;
        }
        if (t instanceof BaseDaoEnabled) {
            ((BaseDaoEnabled) t).m540a((Dao) this);
        }
        DatabaseConnection b = this.f23201f.mo254b();
        try {
            return this.f23196a.m399b(b, (DatabaseConnection) t, this.f23205l);
        } finally {
            this.f23201f.mo255a(b);
        }
    }

    @Override // p110z1.Dao
    /* renamed from: f */
    public T mo1059f(T t) throws SQLException {
        if (t == null) {
            return null;
        }
        T d = mo1067d((BaseDaoImpl<T, ID>) t);
        if (d != null) {
            return d;
        }
        mo1062e((BaseDaoImpl<T, ID>) t);
        return t;
    }

    @Override // p110z1.Dao
    /* renamed from: g */
    public Dao.C5551a mo1057g(T t) throws SQLException {
        if (t == null) {
            return new Dao.C5551a(false, false, 0);
        }
        ID m = mo1045m(t);
        if (m == null || !mo1043n(m)) {
            return new Dao.C5551a(true, false, mo1062e((BaseDaoImpl<T, ID>) t));
        }
        return new Dao.C5551a(false, true, mo1055h(t));
    }

    @Override // p110z1.Dao
    /* renamed from: h */
    public int mo1055h(T t) throws SQLException {
        m1113x();
        if (t == null) {
            return 0;
        }
        DatabaseConnection b = this.f23201f.mo254b();
        try {
            return this.f23196a.m394c(b, (DatabaseConnection) t, this.f23205l);
        } finally {
            this.f23201f.mo255a(b);
        }
    }

    @Override // p110z1.Dao
    /* renamed from: a */
    public int mo1103a(T t, ID id) throws SQLException {
        m1113x();
        if (t == null) {
            return 0;
        }
        DatabaseConnection b = this.f23201f.mo254b();
        try {
            return this.f23196a.m410a(b, (DatabaseConnection) t, (T) id, this.f23205l);
        } finally {
            this.f23201f.mo255a(b);
        }
    }

    @Override // p110z1.Dao
    /* renamed from: a */
    public int mo1089a(PreparedUpdate<T> uyVar) throws SQLException {
        m1113x();
        DatabaseConnection b = this.f23201f.mo254b();
        try {
            return this.f23196a.m402a(b, (PreparedUpdate) uyVar);
        } finally {
            this.f23201f.mo255a(b);
        }
    }

    @Override // p110z1.Dao
    /* renamed from: i */
    public int mo1053i(T t) throws SQLException {
        m1113x();
        if (t == null) {
            return 0;
        }
        if (t instanceof BaseDaoEnabled) {
            ((BaseDaoEnabled) t).m540a((Dao) this);
        }
        DatabaseConnection a = this.f23201f.mo256a();
        try {
            return this.f23196a.m392d(a, t, this.f23205l);
        } finally {
            this.f23201f.mo255a(a);
        }
    }

    @Override // p110z1.Dao
    /* renamed from: j */
    public int mo1051j(T t) throws SQLException {
        m1113x();
        if (t == null) {
            return 0;
        }
        DatabaseConnection b = this.f23201f.mo254b();
        try {
            return this.f23196a.m391e(b, t, this.f23205l);
        } finally {
            this.f23201f.mo255a(b);
        }
    }

    @Override // p110z1.Dao
    /* renamed from: k */
    public int mo1049k(ID id) throws SQLException {
        m1113x();
        if (id == null) {
            return 0;
        }
        DatabaseConnection b = this.f23201f.mo254b();
        try {
            return this.f23196a.m390f(b, id, this.f23205l);
        } finally {
            this.f23201f.mo255a(b);
        }
    }

    @Override // p110z1.Dao
    /* renamed from: a */
    public int mo1096a(Collection<T> collection) throws SQLException {
        m1113x();
        if (collection == null || collection.isEmpty()) {
            return 0;
        }
        DatabaseConnection b = this.f23201f.mo254b();
        try {
            return this.f23196a.m406a(b, (Collection) collection, this.f23205l);
        } finally {
            this.f23201f.mo255a(b);
        }
    }

    @Override // p110z1.Dao
    /* renamed from: b */
    public int mo1078b(Collection<ID> collection) throws SQLException {
        m1113x();
        if (collection == null || collection.isEmpty()) {
            return 0;
        }
        DatabaseConnection b = this.f23201f.mo254b();
        try {
            return this.f23196a.m397b(b, (Collection) collection, this.f23205l);
        } finally {
            this.f23201f.mo255a(b);
        }
    }

    @Override // p110z1.Dao
    /* renamed from: a */
    public int mo1092a(PreparedDelete<T> uvVar) throws SQLException {
        m1113x();
        DatabaseConnection b = this.f23201f.mo254b();
        try {
            return this.f23196a.m405a(b, (PreparedDelete) uvVar);
        } finally {
            this.f23201f.mo255a(b);
        }
    }

    @Override // p110z1.Dao
    /* renamed from: f */
    public CloseableIterator<T> iterator() {
        return mo1106a(-1);
    }

    @Override // p110z1.CloseableIterable
    public CloseableIterator<T> closeableIterator() {
        return mo1106a(-1);
    }

    @Override // p110z1.Dao
    /* renamed from: a */
    public CloseableIterator<T> mo1106a(int i) {
        m1113x();
        this.f23202g = m1120b(i);
        return this.f23202g;
    }

    @Override // p110z1.Dao
    /* renamed from: g */
    public CloseableWrappedIterable<T> mo1058g() {
        m1113x();
        return new CloseableWrappedIterableImpl(new CloseableIterable<T>() { // from class: z1.rp.2
            @Override // java.lang.Iterable
            public Iterator<T> iterator() {
                return closeableIterator();
            }

            @Override // p110z1.CloseableIterable
            public CloseableIterator<T> closeableIterator() {
                try {
                    return BaseDaoImpl.this.m1120b(-1);
                } catch (Exception e) {
                    throw new IllegalStateException("Could not build iterator for " + BaseDaoImpl.this.f23198c, e);
                }
            }
        });
    }

    @Override // p110z1.Dao
    /* renamed from: c */
    public CloseableWrappedIterable<T> mo1070c(final PreparedQuery<T> uwVar) {
        m1113x();
        return new CloseableWrappedIterableImpl(new CloseableIterable<T>() { // from class: z1.rp.3
            @Override // java.lang.Iterable
            public Iterator<T> iterator() {
                return closeableIterator();
            }

            @Override // p110z1.CloseableIterable
            public CloseableIterator<T> closeableIterator() {
                try {
                    return BaseDaoImpl.this.m1118b(uwVar, -1);
                } catch (Exception e) {
                    throw new IllegalStateException("Could not build prepared-query iterator for " + BaseDaoImpl.this.f23198c, e);
                }
            }
        });
    }

    @Override // p110z1.Dao
    /* renamed from: h */
    public void mo1056h() throws SQLException {
        CloseableIterator<T> rsVar = this.f23202g;
        if (rsVar != null) {
            rsVar.mo447a();
            this.f23202g = null;
        }
    }

    @Override // p110z1.Dao
    /* renamed from: d */
    public CloseableIterator<T> mo1065d(PreparedQuery<T> uwVar) throws SQLException {
        return mo1090a(uwVar, -1);
    }

    @Override // p110z1.Dao
    /* renamed from: a */
    public CloseableIterator<T> mo1090a(PreparedQuery<T> uwVar, int i) throws SQLException {
        m1113x();
        this.f23202g = m1118b(uwVar, i);
        return this.f23202g;
    }

    @Override // p110z1.Dao
    /* renamed from: a */
    public GenericRawResults<String[]> mo1098a(String str, String... strArr) throws SQLException {
        m1113x();
        try {
            return this.f23196a.m416a(this.f23201f, str, strArr, this.f23205l);
        } catch (SQLException e) {
            throw SqlExceptionUtil.m529a("Could not perform raw query for " + str, e);
        }
    }

    @Override // p110z1.Dao
    /* renamed from: a */
    public <GR> GenericRawResults<GR> mo1099a(String str, RawRowMapper<GR> sdVar, String... strArr) throws SQLException {
        m1113x();
        try {
            return (GenericRawResults<GR>) this.f23196a.m417a(this.f23201f, str, sdVar, strArr, this.f23205l);
        } catch (SQLException e) {
            throw SqlExceptionUtil.m529a("Could not perform raw query for " + str, e);
        }
    }

    @Override // p110z1.Dao
    /* renamed from: a */
    public GenericRawResults<Object[]> mo1097a(String str, DataType[] snVarArr, String... strArr) throws SQLException {
        m1113x();
        try {
            return this.f23196a.m415a(this.f23201f, str, snVarArr, strArr, this.f23205l);
        } catch (SQLException e) {
            throw SqlExceptionUtil.m529a("Could not perform raw query for " + str, e);
        }
    }

    @Override // p110z1.Dao
    /* renamed from: b */
    public long mo1079b(String str, String... strArr) throws SQLException {
        m1113x();
        DatabaseConnection a = this.f23201f.mo256a();
        try {
            try {
                return this.f23196a.m407a(a, str, strArr);
            } catch (SQLException e) {
                throw SqlExceptionUtil.m529a("Could not perform raw value query for " + str, e);
            }
        } finally {
            this.f23201f.mo255a(a);
        }
    }

    @Override // p110z1.Dao
    /* renamed from: c */
    public int mo1071c(String str, String... strArr) throws SQLException {
        m1113x();
        DatabaseConnection b = this.f23201f.mo254b();
        try {
            try {
                return this.f23196a.m393c(b, str, strArr);
            } catch (SQLException e) {
                throw SqlExceptionUtil.m529a("Could not run raw execute statement " + str, e);
            }
        } finally {
            this.f23201f.mo255a(b);
        }
    }

    @Override // p110z1.Dao
    /* renamed from: a */
    public int mo1101a(String str) throws SQLException {
        m1113x();
        DatabaseConnection b = this.f23201f.mo254b();
        try {
            try {
                return this.f23196a.m408a(b, str);
            } catch (SQLException e) {
                throw SqlExceptionUtil.m529a("Could not run raw execute statement " + str, e);
            }
        } finally {
            this.f23201f.mo255a(b);
        }
    }

    @Override // p110z1.Dao
    /* renamed from: d */
    public int mo1066d(String str, String... strArr) throws SQLException {
        m1113x();
        DatabaseConnection b = this.f23201f.mo254b();
        try {
            try {
                return this.f23196a.m398b(b, str, strArr);
            } catch (SQLException e) {
                throw SqlExceptionUtil.m529a("Could not run raw update statement " + str, e);
            }
        } finally {
            this.f23201f.mo255a(b);
        }
    }

    @Override // p110z1.Dao
    /* renamed from: a */
    public <CT> CT mo1094a(Callable<CT> callable) throws SQLException {
        m1113x();
        DatabaseConnection b = this.f23201f.mo254b();
        try {
            return (CT) this.f23196a.m401a(b, this.f23201f.mo253b(b), callable);
        } finally {
            this.f23201f.mo251c(b);
            this.f23201f.mo255a(b);
        }
    }

    @Override // p110z1.Dao
    /* renamed from: l */
    public String mo1047l(T t) {
        m1113x();
        return this.f23200e.m174a((TableInfo<T, ID>) t);
    }

    @Override // p110z1.Dao
    /* renamed from: b */
    public boolean mo1081b(T t, T t2) throws SQLException {
        FieldType[] c;
        m1113x();
        for (FieldType ssVar : this.f23200e.m169c()) {
            if (!ssVar.m713f().dataIsEqual(ssVar.m720b(t), ssVar.m720b(t2))) {
                return false;
            }
        }
        return true;
    }

    @Override // p110z1.Dao
    /* renamed from: m */
    public ID mo1045m(T t) throws SQLException {
        m1113x();
        FieldType d = this.f23200e.m168d();
        if (d != null) {
            return (ID) d.m720b(t);
        }
        throw new SQLException("Class " + this.f23198c + " does not have an id field");
    }

    @Override // p110z1.Dao
    /* renamed from: i */
    public Class<T> mo1054i() {
        return this.f23198c;
    }

    @Override // p110z1.Dao
    /* renamed from: a */
    public FieldType mo1105a(Class<?> cls) {
        FieldType[] c;
        m1113x();
        for (FieldType ssVar : this.f23200e.m169c()) {
            if (ssVar.m717d() == cls) {
                return ssVar;
            }
        }
        return null;
    }

    @Override // p110z1.Dao
    /* renamed from: j */
    public boolean mo1052j() {
        return this.f23200e.m165g();
    }

    @Override // p110z1.Dao
    /* renamed from: k */
    public boolean mo1050k() throws SQLException {
        m1113x();
        DatabaseConnection a = this.f23201f.mo256a();
        try {
            return a.mo232c(this.f23200e.m171b());
        } finally {
            this.f23201f.mo255a(a);
        }
    }

    @Override // p110z1.Dao
    /* renamed from: l */
    public long mo1048l() throws SQLException {
        m1113x();
        DatabaseConnection a = this.f23201f.mo256a();
        try {
            return this.f23196a.m412a(a);
        } finally {
            this.f23201f.mo255a(a);
        }
    }

    @Override // p110z1.Dao
    /* renamed from: e */
    public long mo1061e(PreparedQuery<T> uwVar) throws SQLException {
        m1113x();
        if (uwVar.mo295b() == StatementBuilder.EnumC5579b.SELECT_LONG) {
            DatabaseConnection a = this.f23201f.mo256a();
            try {
                return this.f23196a.m404a(a, (PreparedStmt) uwVar);
            } finally {
                this.f23201f.mo255a(a);
            }
        } else {
            throw new IllegalArgumentException("Prepared query is not of type " + StatementBuilder.EnumC5579b.SELECT_LONG + ", did you call QueryBuilder.setCountOf(true)?");
        }
    }

    @Override // p110z1.Dao
    /* renamed from: a */
    public void mo1102a(T t, String str) throws SQLException {
        m1119b((BaseDaoImpl<T, ID>) t, str);
    }

    @Override // p110z1.Dao
    /* renamed from: b */
    public <FT> ForeignCollection<FT> mo1080b(String str) throws SQLException {
        return m1119b((BaseDaoImpl<T, ID>) null, str);
    }

    @Override // p110z1.Dao
    /* renamed from: a */
    public void mo1084a(boolean z) throws SQLException {
        if (!z) {
            ObjectCache scVar = this.f23205l;
            if (scVar != null) {
                scVar.mo1008b(this.f23198c);
                this.f23205l = null;
            }
        } else if (this.f23205l != null) {
        } else {
            if (this.f23200e.m168d() != null) {
                synchronized (getClass()) {
                    if (f23195k == null) {
                        f23195k = ReferenceObjectCache.m1005c();
                    }
                    this.f23205l = f23195k;
                }
                this.f23205l.mo1013a(this.f23198c);
                return;
            }
            throw new SQLException("Class " + this.f23198c + " must have an id field to enable the object cache");
        }
    }

    @Override // p110z1.Dao
    /* renamed from: a */
    public void mo1093a(ObjectCache scVar) throws SQLException {
        if (scVar == null) {
            ObjectCache scVar2 = this.f23205l;
            if (scVar2 != null) {
                scVar2.mo1008b(this.f23198c);
                this.f23205l = null;
                return;
            }
            return;
        }
        ObjectCache scVar3 = this.f23205l;
        if (!(scVar3 == null || scVar3 == scVar)) {
            scVar3.mo1008b(this.f23198c);
        }
        if (this.f23200e.m168d() != null) {
            this.f23205l = scVar;
            this.f23205l.mo1013a(this.f23198c);
            return;
        }
        throw new SQLException("Class " + this.f23198c + " must have an id field to enable the object cache");
    }

    @Override // p110z1.Dao
    /* renamed from: m */
    public ObjectCache mo1046m() {
        return this.f23205l;
    }

    @Override // p110z1.Dao
    /* renamed from: n */
    public void mo1044n() {
        ObjectCache scVar = this.f23205l;
        if (scVar != null) {
            scVar.mo1008b(this.f23198c);
        }
    }

    /* renamed from: o */
    public static synchronized void m1117o() {
        synchronized (BaseDaoImpl.class) {
            if (f23195k != null) {
                f23195k.mo1014a();
                f23195k = null;
            }
        }
    }

    @Override // p110z1.Dao
    /* renamed from: a */
    public T mo1086a(DatabaseResults woVar) throws SQLException {
        return this.f23196a.m421a().mo322a(woVar);
    }

    @Override // p110z1.Dao
    /* renamed from: p */
    public GenericRowMapper<T> mo1042p() throws SQLException {
        return this.f23196a.m421a();
    }

    @Override // p110z1.Dao
    /* renamed from: q */
    public RawRowMapper<T> mo1041q() {
        return this.f23196a.m400b();
    }

    @Override // p110z1.Dao
    /* renamed from: n */
    public boolean mo1043n(ID id) throws SQLException {
        DatabaseConnection a = this.f23201f.mo256a();
        try {
            return this.f23196a.m411a(a, (DatabaseConnection) id);
        } finally {
            this.f23201f.mo255a(a);
        }
    }

    @Override // p110z1.Dao
    /* renamed from: r */
    public DatabaseConnection mo1040r() throws SQLException {
        DatabaseConnection b = this.f23201f.mo254b();
        this.f23201f.mo253b(b);
        return b;
    }

    @Override // p110z1.Dao
    /* renamed from: a */
    public void mo1088a(DatabaseConnection wnVar) throws SQLException {
        this.f23201f.mo251c(wnVar);
        this.f23201f.mo255a(wnVar);
    }

    @Override // p110z1.Dao
    /* renamed from: b */
    public void mo1074b(boolean z) throws SQLException {
        DatabaseConnection b = this.f23201f.mo254b();
        try {
            mo1087a(b, z);
        } finally {
            this.f23201f.mo255a(b);
        }
    }

    @Override // p110z1.Dao
    /* renamed from: a */
    public void mo1087a(DatabaseConnection wnVar, boolean z) throws SQLException {
        wnVar.mo237a(z);
    }

    @Override // p110z1.Dao
    /* renamed from: s */
    public boolean mo1039s() throws SQLException {
        DatabaseConnection b = this.f23201f.mo254b();
        try {
            return mo1075b(b);
        } finally {
            this.f23201f.mo255a(b);
        }
    }

    @Override // p110z1.Dao
    /* renamed from: b */
    public boolean mo1075b(DatabaseConnection wnVar) throws SQLException {
        return wnVar.mo236b();
    }

    @Override // p110z1.Dao
    /* renamed from: c */
    public void mo1069c(DatabaseConnection wnVar) throws SQLException {
        wnVar.mo238a((Savepoint) null);
    }

    @Override // p110z1.Dao
    /* renamed from: d */
    public void mo1064d(DatabaseConnection wnVar) throws SQLException {
        wnVar.mo233b((Savepoint) null);
    }

    /* renamed from: t */
    public ObjectFactory<T> m1116t() {
        return this.f23203h;
    }

    @Override // p110z1.Dao
    /* renamed from: a */
    public void mo1085a(ObjectFactory<T> wtVar) {
        m1113x();
        this.f23203h = wtVar;
    }

    /* renamed from: u */
    public DatabaseTableConfig<T> m1115u() {
        return this.f23199d;
    }

    /* renamed from: v */
    public TableInfo<T, ID> m1114v() {
        return this.f23200e;
    }

    @Override // p110z1.Dao
    /* renamed from: w */
    public ConnectionSource mo1038w() {
        return this.f23201f;
    }

    /* renamed from: a */
    public void m1124a(ConnectionSource wmVar) {
        this.f23201f = wmVar;
    }

    /* renamed from: a */
    public void m1121a(DatabaseTableConfig<T> wrVar) {
        this.f23199d = wrVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static <T, ID> Dao<T, ID> m1123a(ConnectionSource wmVar, Class<T> cls) throws SQLException {
        return new BaseDaoImpl<T, ID>(wmVar, cls) { // from class: z1.rp.4
            @Override // p110z1.BaseDaoImpl, java.lang.Iterable
            public /* synthetic */ Iterator iterator() {
                return BaseDaoImpl.super.iterator();
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static <T, ID> Dao<T, ID> m1122a(ConnectionSource wmVar, DatabaseTableConfig<T> wrVar) throws SQLException {
        return new BaseDaoImpl<T, ID>(wmVar, wrVar) { // from class: z1.rp.5
            @Override // p110z1.BaseDaoImpl, java.lang.Iterable
            public /* synthetic */ Iterator iterator() {
                return BaseDaoImpl.super.iterator();
            }
        };
    }

    /* renamed from: x */
    protected void m1113x() {
        if (!this.f23204i) {
            throw new IllegalStateException("you must call initialize() before you can use the dao");
        }
    }

    /* renamed from: b */
    private <FT> ForeignCollection<FT> m1119b(T t, String str) throws SQLException {
        FieldType[] c;
        m1113x();
        ID m = t == null ? null : mo1045m(t);
        for (FieldType ssVar : this.f23200e.m169c()) {
            if (ssVar.m715e().equals(str)) {
                BaseForeignCollection a = ssVar.m728a((Object) t, (T) m);
                if (t != null) {
                    ssVar.m727a((Object) t, (Object) a, true, (ObjectCache) null);
                }
                return a;
            }
        }
        throw new IllegalArgumentException("Could not find a field named " + str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public CloseableIterator<T> m1120b(int i) {
        try {
            return this.f23196a.m420a(this, this.f23201f, i, this.f23205l);
        } catch (Exception e) {
            throw new IllegalStateException("Could not build iterator for " + this.f23198c, e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public CloseableIterator<T> m1118b(PreparedQuery<T> uwVar, int i) throws SQLException {
        try {
            return this.f23196a.m419a(this, this.f23201f, uwVar, this.f23205l, i);
        } catch (SQLException e) {
            throw SqlExceptionUtil.m529a("Could not build prepared-query iterator for " + this.f23198c, e);
        }
    }

    /* renamed from: a */
    private List<T> m1128a(T t, boolean z) throws SQLException {
        FieldType[] c;
        m1113x();
        QueryBuilder<T, ID> c2 = mo1073c();
        Where<T, ID> o = c2.m427o();
        int i = 0;
        for (FieldType ssVar : this.f23200e.m169c()) {
            Object f = ssVar.m712f(t);
            if (f != null) {
                if (z) {
                    f = new SelectArg(f);
                }
                o.m367a(ssVar.m715e(), f);
                i++;
            }
        }
        if (i == 0) {
            return Collections.emptyList();
        }
        o.m371a(i);
        return c2.m466g();
    }

    /* renamed from: a */
    private List<T> m1127a(Map<String, Object> map, boolean z) throws SQLException {
        m1113x();
        QueryBuilder<T, ID> c = mo1073c();
        Where<T, ID> o = c.m427o();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            Object value = entry.getValue();
            if (z) {
                value = new SelectArg(value);
            }
            o.m367a(entry.getKey(), value);
        }
        if (map.size() == 0) {
            return Collections.emptyList();
        }
        o.m371a(map.size());
        return c.m466g();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: BaseDaoImpl.java */
    /* renamed from: z1.rp$a */
    /* loaded from: classes3.dex */
    public static class C5550a {

        /* renamed from: a */
        private BaseDaoImpl<?, ?>[] f23209a;

        /* renamed from: b */
        private int f23210b;

        private C5550a() {
            this.f23209a = new BaseDaoImpl[10];
            this.f23210b = 0;
        }

        /* renamed from: a */
        public void m1109a(BaseDaoImpl<?, ?> rpVar) {
            int i = this.f23210b;
            BaseDaoImpl<?, ?>[] rpVarArr = this.f23209a;
            if (i == rpVarArr.length) {
                BaseDaoImpl<?, ?>[] rpVarArr2 = new BaseDaoImpl[rpVarArr.length * 2];
                int i2 = 0;
                while (true) {
                    BaseDaoImpl<?, ?>[] rpVarArr3 = this.f23209a;
                    if (i2 >= rpVarArr3.length) {
                        break;
                    }
                    rpVarArr2[i2] = rpVarArr3[i2];
                    rpVarArr3[i2] = null;
                    i2++;
                }
                this.f23209a = rpVarArr2;
            }
            BaseDaoImpl<?, ?>[] rpVarArr4 = this.f23209a;
            int i3 = this.f23210b;
            this.f23210b = i3 + 1;
            rpVarArr4[i3] = rpVar;
        }

        /* renamed from: a */
        public int m1111a() {
            return this.f23210b;
        }

        /* renamed from: a */
        public BaseDaoImpl<?, ?> m1110a(int i) {
            return this.f23209a[i];
        }

        /* renamed from: b */
        public void m1108b() {
            for (int i = 0; i < this.f23210b; i++) {
                this.f23209a[i] = null;
            }
            this.f23210b = 0;
        }
    }
}
