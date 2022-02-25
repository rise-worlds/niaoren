package p110z1;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import p110z1.StatementBuilder;

/* renamed from: z1.vf */
/* loaded from: classes3.dex */
public class StatementExecutor<T, ID> implements GenericRowMapper<String[]> {

    /* renamed from: a */
    private static C5570ui f23532a = LoggerFactory.m545a(StatementExecutor.class);

    /* renamed from: b */
    private static final FieldType[] f23533b = new FieldType[0];

    /* renamed from: c */
    private final DatabaseType f23534c;

    /* renamed from: d */
    private final TableInfo<T, ID> f23535d;

    /* renamed from: e */
    private final Dao<T, ID> f23536e;

    /* renamed from: f */
    private MappedQueryForId<T, ID> f23537f;

    /* renamed from: g */
    private PreparedQuery<T> f23538g;

    /* renamed from: h */
    private MappedCreate<T, ID> f23539h;

    /* renamed from: i */
    private MappedUpdate<T, ID> f23540i;

    /* renamed from: j */
    private MappedUpdateId<T, ID> f23541j;

    /* renamed from: k */
    private MappedDelete<T, ID> f23542k;

    /* renamed from: l */
    private MappedRefresh<T, ID> f23543l;

    /* renamed from: m */
    private String f23544m;

    /* renamed from: n */
    private String f23545n;

    /* renamed from: o */
    private FieldType[] f23546o;

    /* renamed from: p */
    private RawRowMapper<T> f23547p;

    public StatementExecutor(DatabaseType siVar, TableInfo<T, ID> wuVar, Dao<T, ID> rvVar) {
        this.f23534c = siVar;
        this.f23535d = wuVar;
        this.f23536e = rvVar;
    }

    /* renamed from: a */
    public T m409a(DatabaseConnection wnVar, ID id, ObjectCache scVar) throws SQLException {
        if (this.f23537f == null) {
            this.f23537f = MappedQueryForId.m294a(this.f23534c, this.f23535d, (FieldType) null);
        }
        return this.f23537f.m293a(wnVar, (DatabaseConnection) id, scVar);
    }

    /* renamed from: a */
    public T m403a(DatabaseConnection wnVar, PreparedStmt<T> uxVar, ObjectCache scVar) throws SQLException {
        DatabaseResults woVar;
        Throwable th;
        CompiledStatement a = uxVar.mo297a(wnVar, StatementBuilder.EnumC5579b.SELECT);
        try {
            woVar = a.mo262a(scVar);
            try {
                if (woVar.mo222c()) {
                    f23532a.m595b("query-for-first of '{}' returned at least 1 result", uxVar.mo300a());
                    T a2 = uxVar.mo322a(woVar);
                    if (woVar != null) {
                        woVar.mo210j();
                    }
                    a.mo258d();
                    return a2;
                }
                f23532a.m595b("query-for-first of '{}' returned at 0 results", uxVar.mo300a());
                if (woVar != null) {
                    woVar.mo210j();
                }
                a.mo258d();
                return null;
            } catch (Throwable th2) {
                th = th2;
                if (woVar != null) {
                    woVar.mo210j();
                }
                a.mo258d();
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            woVar = null;
        }
    }

    /* renamed from: a */
    public List<T> m414a(ConnectionSource wmVar, ObjectCache scVar) throws SQLException {
        m395c();
        return m413a(wmVar, this.f23538g, scVar);
    }

    /* renamed from: a */
    public long m412a(DatabaseConnection wnVar) throws SQLException {
        if (this.f23544m == null) {
            StringBuilder sb = new StringBuilder(64);
            sb.append("SELECT COUNT(*) FROM ");
            this.f23534c.mo899b(sb, this.f23535d.m171b());
            this.f23544m = sb.toString();
        }
        long b = wnVar.mo235b(this.f23544m);
        f23532a.m594b("query of '{}' returned {}", this.f23544m, Long.valueOf(b));
        return b;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [z1.wo, z1.sc] */
    /* renamed from: a */
    public long m404a(DatabaseConnection wnVar, PreparedStmt<T> uxVar) throws SQLException {
        CompiledStatement a = uxVar.mo297a(wnVar, StatementBuilder.EnumC5579b.SELECT_LONG);
        DatabaseResults woVar = 0;
        try {
            woVar = a.mo262a((ObjectCache) woVar);
            if (woVar.mo222c()) {
                return woVar.mo209j(0);
            }
            throw new SQLException("No result found in queryForLong: " + uxVar.mo300a());
        } finally {
            if (woVar != 0) {
                woVar.mo210j();
            }
            a.mo258d();
        }
    }

    /* renamed from: a */
    public long m407a(DatabaseConnection wnVar, String str, String[] strArr) throws SQLException {
        Throwable th;
        CompiledStatement wlVar;
        f23532a.m595b("executing raw query for long: {}", str);
        if (strArr.length > 0) {
            f23532a.m619a("query arguments: {}", (Object) strArr);
        }
        DatabaseResults woVar = null;
        try {
            wlVar = wnVar.mo243a(str, StatementBuilder.EnumC5579b.SELECT, f23533b);
        } catch (Throwable th2) {
            th = th2;
            wlVar = null;
        }
        try {
            m418a(wlVar, strArr);
            DatabaseResults a = wlVar.mo262a((ObjectCache) null);
            if (a.mo222c()) {
                long j = a.mo209j(0);
                if (a != null) {
                    a.mo210j();
                }
                if (wlVar != null) {
                    wlVar.mo258d();
                }
                return j;
            }
            throw new SQLException("No result found in queryForLong: " + str);
        } catch (Throwable th3) {
            th = th3;
            if (0 != 0) {
                woVar.mo210j();
            }
            if (wlVar != null) {
                wlVar.mo258d();
            }
            throw th;
        }
    }

    /* renamed from: a */
    public List<T> m413a(ConnectionSource wmVar, PreparedStmt<T> uxVar, ObjectCache scVar) throws SQLException {
        SelectIterator<T, ID> a = m419a((BaseDaoImpl) null, wmVar, uxVar, scVar, -1);
        try {
            ArrayList arrayList = new ArrayList();
            while (a.m438i()) {
                arrayList.add(a.mo439h());
            }
            f23532a.m594b("query of '{}' returned {} results", uxVar.mo300a(), Integer.valueOf(arrayList.size()));
            return arrayList;
        } finally {
            a.mo447a();
        }
    }

    /* renamed from: a */
    public SelectIterator<T, ID> m420a(BaseDaoImpl<T, ID> rpVar, ConnectionSource wmVar, int i, ObjectCache scVar) throws SQLException {
        m395c();
        return m419a(rpVar, wmVar, this.f23538g, scVar, i);
    }

    /* renamed from: a */
    public GenericRowMapper<T> m421a() throws SQLException {
        m395c();
        return this.f23538g;
    }

    /* renamed from: b */
    public RawRowMapper<T> m400b() {
        if (this.f23547p == null) {
            this.f23547p = new RawRowMapperImpl(this.f23535d);
        }
        return this.f23547p;
    }

    /* renamed from: a */
    public SelectIterator<T, ID> m419a(BaseDaoImpl<T, ID> rpVar, ConnectionSource wmVar, PreparedStmt<T> uxVar, ObjectCache scVar, int i) throws SQLException {
        Throwable th;
        DatabaseConnection a = wmVar.mo256a();
        CompiledStatement wlVar = null;
        try {
            wlVar = uxVar.mo296a(a, StatementBuilder.EnumC5579b.SELECT, i);
            try {
                try {
                    return new SelectIterator<>(this.f23535d.m175a(), rpVar, uxVar, wmVar, a, wlVar, uxVar.mo300a(), scVar);
                } catch (Throwable th2) {
                    th = th2;
                    if (wlVar != null) {
                        wlVar.mo258d();
                    }
                    if (a != null) {
                        wmVar.mo255a(a);
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
            }
        } catch (Throwable th4) {
            th = th4;
        }
    }

    /* renamed from: a */
    public GenericRawResults<String[]> m416a(ConnectionSource wmVar, String str, String[] strArr, ObjectCache scVar) throws SQLException {
        f23532a.m595b("executing raw query for: {}", str);
        if (strArr.length > 0) {
            f23532a.m619a("query arguments: {}", (Object) strArr);
        }
        DatabaseConnection a = wmVar.mo256a();
        CompiledStatement wlVar = null;
        try {
            wlVar = a.mo243a(str, StatementBuilder.EnumC5579b.SELECT, f23533b);
            m418a(wlVar, strArr);
            return new RawResultsImpl(wmVar, a, str, String[].class, wlVar, this, scVar);
        } catch (Throwable th) {
            if (wlVar != null) {
                wlVar.mo258d();
            }
            if (a != null) {
                wmVar.mo255a(a);
            }
            throw th;
        }
    }

    /* renamed from: a */
    public <UO> GenericRawResults<UO> m417a(ConnectionSource wmVar, String str, RawRowMapper<UO> sdVar, String[] strArr, ObjectCache scVar) throws SQLException {
        f23532a.m595b("executing raw query for: {}", str);
        if (strArr.length > 0) {
            f23532a.m619a("query arguments: {}", (Object) strArr);
        }
        DatabaseConnection a = wmVar.mo256a();
        CompiledStatement wlVar = null;
        try {
            wlVar = a.mo243a(str, StatementBuilder.EnumC5579b.SELECT, f23533b);
            m418a(wlVar, strArr);
            return new RawResultsImpl(wmVar, a, str, String[].class, wlVar, new C5581b(sdVar, this), scVar);
        } catch (Throwable th) {
            if (wlVar != null) {
                wlVar.mo258d();
            }
            if (a != null) {
                wmVar.mo255a(a);
            }
            throw th;
        }
    }

    /* renamed from: a */
    public GenericRawResults<Object[]> m415a(ConnectionSource wmVar, String str, DataType[] snVarArr, String[] strArr, ObjectCache scVar) throws SQLException {
        f23532a.m595b("executing raw query for: {}", str);
        if (strArr.length > 0) {
            f23532a.m619a("query arguments: {}", (Object) strArr);
        }
        DatabaseConnection a = wmVar.mo256a();
        CompiledStatement wlVar = null;
        try {
            wlVar = a.mo243a(str, StatementBuilder.EnumC5579b.SELECT, f23533b);
            m418a(wlVar, strArr);
            return new RawResultsImpl(wmVar, a, str, Object[].class, wlVar, new C5580a(snVarArr), scVar);
        } catch (Throwable th) {
            if (wlVar != null) {
                wlVar.mo258d();
            }
            if (a != null) {
                wmVar.mo255a(a);
            }
            throw th;
        }
    }

    /* renamed from: b */
    public int m398b(DatabaseConnection wnVar, String str, String[] strArr) throws SQLException {
        f23532a.m595b("running raw update statement: {}", str);
        if (strArr.length > 0) {
            f23532a.m619a("update arguments: {}", (Object) strArr);
        }
        CompiledStatement a = wnVar.mo243a(str, StatementBuilder.EnumC5579b.UPDATE, f23533b);
        try {
            m418a(a, strArr);
            return a.mo261b();
        } finally {
            a.mo258d();
        }
    }

    /* renamed from: a */
    public int m408a(DatabaseConnection wnVar, String str) throws SQLException {
        f23532a.m595b("running raw execute statement: {}", str);
        return wnVar.mo244a(str, -1);
    }

    /* renamed from: c */
    public int m393c(DatabaseConnection wnVar, String str, String[] strArr) throws SQLException {
        f23532a.m595b("running raw execute statement: {}", str);
        if (strArr.length > 0) {
            f23532a.m619a("execute arguments: {}", (Object) strArr);
        }
        CompiledStatement a = wnVar.mo243a(str, StatementBuilder.EnumC5579b.EXECUTE, f23533b);
        try {
            m418a(a, strArr);
            return a.mo259c();
        } finally {
            a.mo258d();
        }
    }

    /* renamed from: b */
    public int m399b(DatabaseConnection wnVar, T t, ObjectCache scVar) throws SQLException {
        if (this.f23539h == null) {
            this.f23539h = MappedCreate.m313a(this.f23534c, this.f23535d);
        }
        return this.f23539h.m314a(this.f23534c, wnVar, (DatabaseConnection) t, scVar);
    }

    /* renamed from: c */
    public int m394c(DatabaseConnection wnVar, T t, ObjectCache scVar) throws SQLException {
        if (this.f23540i == null) {
            this.f23540i = MappedUpdate.m288a(this.f23534c, this.f23535d);
        }
        return this.f23540i.m286a(wnVar, t, scVar);
    }

    /* renamed from: a */
    public int m410a(DatabaseConnection wnVar, T t, ID id, ObjectCache scVar) throws SQLException {
        if (this.f23541j == null) {
            this.f23541j = MappedUpdateId.m285a(this.f23534c, this.f23535d);
        }
        return this.f23541j.m284a(wnVar, (DatabaseConnection) t, (T) id, scVar);
    }

    /* renamed from: a */
    public int m402a(DatabaseConnection wnVar, PreparedUpdate<T> uyVar) throws SQLException {
        CompiledStatement a = uyVar.mo297a(wnVar, StatementBuilder.EnumC5579b.UPDATE);
        try {
            return a.mo261b();
        } finally {
            a.mo258d();
        }
    }

    /* renamed from: d */
    public int m392d(DatabaseConnection wnVar, T t, ObjectCache scVar) throws SQLException {
        if (this.f23543l == null) {
            this.f23543l = MappedRefresh.m290a(this.f23534c, (TableInfo) this.f23535d);
        }
        return this.f23543l.m289b(wnVar, (DatabaseConnection) t, scVar);
    }

    /* renamed from: e */
    public int m391e(DatabaseConnection wnVar, T t, ObjectCache scVar) throws SQLException {
        if (this.f23542k == null) {
            this.f23542k = MappedDelete.m308a(this.f23534c, this.f23535d);
        }
        return this.f23542k.m307a(wnVar, t, scVar);
    }

    /* renamed from: f */
    public int m390f(DatabaseConnection wnVar, ID id, ObjectCache scVar) throws SQLException {
        if (this.f23542k == null) {
            this.f23542k = MappedDelete.m308a(this.f23534c, this.f23535d);
        }
        return this.f23542k.m306b(wnVar, id, scVar);
    }

    /* renamed from: a */
    public int m406a(DatabaseConnection wnVar, Collection<T> collection, ObjectCache scVar) throws SQLException {
        return MappedDeleteCollection.m303a(this.f23534c, this.f23535d, wnVar, collection, scVar);
    }

    /* renamed from: b */
    public int m397b(DatabaseConnection wnVar, Collection<ID> collection, ObjectCache scVar) throws SQLException {
        return MappedDeleteCollection.m301b(this.f23534c, this.f23535d, wnVar, collection, scVar);
    }

    /* renamed from: a */
    public int m405a(DatabaseConnection wnVar, PreparedDelete<T> uvVar) throws SQLException {
        CompiledStatement a = uvVar.mo297a(wnVar, StatementBuilder.EnumC5579b.DELETE);
        try {
            return a.mo261b();
        } finally {
            a.mo258d();
        }
    }

    /* renamed from: a */
    public <CT> CT m401a(DatabaseConnection wnVar, boolean z, Callable<CT> callable) throws SQLException {
        Throwable th;
        if (this.f23534c.mo872p()) {
            return (CT) TransactionManager.m522a(wnVar, z, this.f23534c, callable);
        }
        boolean z2 = false;
        try {
            if (wnVar.mo246a()) {
                boolean b = wnVar.mo236b();
                if (b) {
                    try {
                        wnVar.mo237a(false);
                        f23532a.m595b("disabled auto-commit on table {} before batch tasks", this.f23535d.m171b());
                    } catch (Throwable th2) {
                        th = th2;
                        z2 = b;
                        if (z2) {
                            wnVar.mo237a(true);
                            f23532a.m595b("re-enabled auto-commit on table {} after batch tasks", this.f23535d.m171b());
                        }
                        throw th;
                    }
                }
                z2 = b;
            }
            try {
                try {
                    CT call = callable.call();
                    if (z2) {
                        wnVar.mo237a(true);
                        f23532a.m595b("re-enabled auto-commit on table {} after batch tasks", this.f23535d.m171b());
                    }
                    return call;
                } catch (SQLException e) {
                    throw e;
                }
            } catch (Exception e2) {
                throw SqlExceptionUtil.m529a("Batch tasks callable threw non-SQL exception", e2);
            }
        } catch (Throwable th3) {
            th = th3;
        }
    }

    /* renamed from: b */
    public String[] mo322a(DatabaseResults woVar) throws SQLException {
        int a = woVar.mo227a();
        String[] strArr = new String[a];
        for (int i = 0; i < a; i++) {
            strArr[i] = woVar.mo221c(i);
        }
        return strArr;
    }

    /* renamed from: a */
    public boolean m411a(DatabaseConnection wnVar, ID id) throws SQLException {
        if (this.f23545n == null) {
            QueryBuilder uzVar = new QueryBuilder(this.f23534c, this.f23535d, this.f23536e);
            uzVar.m478b("COUNT(*)");
            uzVar.m427o().m367a(this.f23535d.m168d().m715e(), new SelectArg());
            this.f23545n = uzVar.m426p();
            this.f23546o = new FieldType[]{this.f23535d.m168d()};
        }
        long c = wnVar.mo231c(this.f23545n, new Object[]{id}, this.f23546o);
        f23532a.m594b("query of '{}' returned {}", this.f23545n, Long.valueOf(c));
        return c != 0;
    }

    /* renamed from: a */
    private void m418a(CompiledStatement wlVar, String[] strArr) throws SQLException {
        for (int i = 0; i < strArr.length; i++) {
            wlVar.mo264a(i, strArr[i], SqlType.STRING);
        }
    }

    /* renamed from: c */
    private void m395c() throws SQLException {
        if (this.f23538g == null) {
            this.f23538g = new QueryBuilder(this.f23534c, this.f23535d, this.f23536e).m471e();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: StatementExecutor.java */
    /* renamed from: z1.vf$b */
    /* loaded from: classes3.dex */
    public static class C5581b<UO> implements GenericRowMapper<UO> {

        /* renamed from: a */
        private final RawRowMapper<UO> f23549a;

        /* renamed from: b */
        private final GenericRowMapper<String[]> f23550b;

        /* renamed from: c */
        private String[] f23551c;

        public C5581b(RawRowMapper<UO> sdVar, GenericRowMapper<String[]> utVar) {
            this.f23549a = sdVar;
            this.f23550b = utVar;
        }

        @Override // p110z1.GenericRowMapper
        /* renamed from: a */
        public UO mo322a(DatabaseResults woVar) throws SQLException {
            return this.f23549a.mo448a(m388b(woVar), this.f23550b.mo322a(woVar));
        }

        /* renamed from: b */
        private String[] m388b(DatabaseResults woVar) throws SQLException {
            String[] strArr = this.f23551c;
            if (strArr != null) {
                return strArr;
            }
            this.f23551c = woVar.mo224b();
            return this.f23551c;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: StatementExecutor.java */
    /* renamed from: z1.vf$a */
    /* loaded from: classes3.dex */
    public static class C5580a implements GenericRowMapper<Object[]> {

        /* renamed from: a */
        private final DataType[] f23548a;

        public C5580a(DataType[] snVarArr) {
            this.f23548a = snVarArr;
        }

        /* renamed from: b */
        public Object[] mo322a(DatabaseResults woVar) throws SQLException {
            DataType snVar;
            int a = woVar.mo227a();
            Object[] objArr = new Object[a];
            for (int i = 0; i < a; i++) {
                DataType[] snVarArr = this.f23548a;
                if (i >= snVarArr.length) {
                    snVar = DataType.STRING;
                } else {
                    snVar = snVarArr[i];
                }
                objArr[i] = snVar.getDataPersister().resultToJava(null, woVar, i);
            }
            return objArr;
        }
    }
}
