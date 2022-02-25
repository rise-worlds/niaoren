package p110z1;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import p110z1.Dao;
import p110z1.Log;

/* renamed from: z1.sf */
/* loaded from: classes3.dex */
public class RuntimeExceptionDao<T, ID> implements CloseableIterable<T> {

    /* renamed from: a */
    private static final Log.EnumC5569a f23236a = Log.EnumC5569a.DEBUG;

    /* renamed from: c */
    private static final C5570ui f23237c = LoggerFactory.m545a(RuntimeExceptionDao.class);

    /* renamed from: b */
    private Dao<T, ID> f23238b;

    public RuntimeExceptionDao(Dao<T, ID> rvVar) {
        this.f23238b = rvVar;
    }

    /* renamed from: a */
    public static <T, ID> RuntimeExceptionDao<T, ID> m979a(ConnectionSource wmVar, Class<T> cls) throws SQLException {
        return new RuntimeExceptionDao<>(DaoManager.m1027a(wmVar, cls));
    }

    /* renamed from: a */
    public static <T, ID> RuntimeExceptionDao<T, ID> m978a(ConnectionSource wmVar, DatabaseTableConfig<T> wrVar) throws SQLException {
        return new RuntimeExceptionDao<>(DaoManager.m1025a(wmVar, wrVar));
    }

    /* renamed from: a */
    public T m995a(ID id) {
        try {
            return this.f23238b.mo1104a((Dao<T, ID>) id);
        } catch (SQLException e) {
            m996a((Exception) e, "queryForId threw exception on: " + id);
            throw new RuntimeException(e);
        }
    }

    /* renamed from: a */
    public T m982a(PreparedQuery<T> uwVar) {
        try {
            return this.f23238b.mo1091a((PreparedQuery) uwVar);
        } catch (SQLException e) {
            m996a((Exception) e, "queryForFirst threw exception on: " + uwVar);
            throw new RuntimeException(e);
        }
    }

    /* renamed from: a */
    public List<T> m999a() {
        try {
            return this.f23238b.mo1083b();
        } catch (SQLException e) {
            m996a((Exception) e, "queryForAll threw exception");
            throw new RuntimeException(e);
        }
    }

    /* renamed from: a */
    public List<T> m991a(String str, Object obj) {
        try {
            return this.f23238b.mo1100a(str, obj);
        } catch (SQLException e) {
            m996a((Exception) e, "queryForEq threw exception on: " + str);
            throw new RuntimeException(e);
        }
    }

    /* renamed from: b */
    public List<T> m971b(T t) {
        try {
            return this.f23238b.mo1082b((Dao<T, ID>) t);
        } catch (SQLException e) {
            m996a((Exception) e, "queryForMatching threw exception on: " + t);
            throw new RuntimeException(e);
        }
    }

    /* renamed from: c */
    public List<T> m961c(T t) {
        try {
            return this.f23238b.mo1072c((Dao<T, ID>) t);
        } catch (SQLException e) {
            m996a((Exception) e, "queryForMatchingArgs threw exception on: " + t);
            throw new RuntimeException(e);
        }
    }

    /* renamed from: a */
    public List<T> m986a(Map<String, Object> map) {
        try {
            return this.f23238b.mo1095a(map);
        } catch (SQLException e) {
            m996a((Exception) e, "queryForFieldValues threw exception");
            throw new RuntimeException(e);
        }
    }

    /* renamed from: b */
    public List<T> m966b(Map<String, Object> map) {
        try {
            return this.f23238b.mo1077b(map);
        } catch (SQLException e) {
            m996a((Exception) e, "queryForFieldValuesArgs threw exception");
            throw new RuntimeException(e);
        }
    }

    /* renamed from: d */
    public T m956d(T t) {
        try {
            return this.f23238b.mo1067d((Dao<T, ID>) t);
        } catch (SQLException e) {
            m996a((Exception) e, "queryForSameId threw exception on: " + t);
            throw new RuntimeException(e);
        }
    }

    /* renamed from: b */
    public QueryBuilder<T, ID> m972b() {
        return this.f23238b.mo1073c();
    }

    /* renamed from: c */
    public UpdateBuilder<T, ID> m962c() {
        return this.f23238b.mo1068d();
    }

    /* renamed from: d */
    public DeleteBuilder<T, ID> m957d() {
        return this.f23238b.mo1063e();
    }

    /* renamed from: b */
    public List<T> m965b(PreparedQuery<T> uwVar) {
        try {
            return this.f23238b.mo1076b((PreparedQuery) uwVar);
        } catch (SQLException e) {
            m996a((Exception) e, "query threw exception on: " + uwVar);
            throw new RuntimeException(e);
        }
    }

    /* renamed from: e */
    public int m951e(T t) {
        try {
            return this.f23238b.mo1062e((Dao<T, ID>) t);
        } catch (SQLException e) {
            m996a((Exception) e, "create threw exception on: " + t);
            throw new RuntimeException(e);
        }
    }

    /* renamed from: f */
    public T m948f(T t) {
        try {
            return this.f23238b.mo1059f(t);
        } catch (SQLException e) {
            m996a((Exception) e, "createIfNotExists threw exception on: " + t);
            throw new RuntimeException(e);
        }
    }

    /* renamed from: g */
    public Dao.C5551a m946g(T t) {
        try {
            return this.f23238b.mo1057g(t);
        } catch (SQLException e) {
            m996a((Exception) e, "createOrUpdate threw exception on: " + t);
            throw new RuntimeException(e);
        }
    }

    /* renamed from: h */
    public int m944h(T t) {
        try {
            return this.f23238b.mo1055h(t);
        } catch (SQLException e) {
            m996a((Exception) e, "update threw exception on: " + t);
            throw new RuntimeException(e);
        }
    }

    /* renamed from: a */
    public int m994a(T t, ID id) {
        try {
            return this.f23238b.mo1103a((Dao<T, ID>) t, (T) id);
        } catch (SQLException e) {
            m996a((Exception) e, "updateId threw exception on: " + t);
            throw new RuntimeException(e);
        }
    }

    /* renamed from: a */
    public int m980a(PreparedUpdate<T> uyVar) {
        try {
            return this.f23238b.mo1089a((PreparedUpdate) uyVar);
        } catch (SQLException e) {
            m996a((Exception) e, "update threw exception on: " + uyVar);
            throw new RuntimeException(e);
        }
    }

    /* renamed from: i */
    public int m942i(T t) {
        try {
            return this.f23238b.mo1053i(t);
        } catch (SQLException e) {
            m996a((Exception) e, "refresh threw exception on: " + t);
            throw new RuntimeException(e);
        }
    }

    /* renamed from: j */
    public int m940j(T t) {
        try {
            return this.f23238b.mo1051j(t);
        } catch (SQLException e) {
            m996a((Exception) e, "delete threw exception on: " + t);
            throw new RuntimeException(e);
        }
    }

    /* renamed from: k */
    public int m938k(ID id) {
        try {
            return this.f23238b.mo1049k(id);
        } catch (SQLException e) {
            m996a((Exception) e, "deleteById threw exception on: " + id);
            throw new RuntimeException(e);
        }
    }

    /* renamed from: a */
    public int m987a(Collection<T> collection) {
        try {
            return this.f23238b.mo1096a((Collection) collection);
        } catch (SQLException e) {
            m996a((Exception) e, "delete threw exception on: " + collection);
            throw new RuntimeException(e);
        }
    }

    /* renamed from: b */
    public int m967b(Collection<ID> collection) {
        try {
            return this.f23238b.mo1078b((Collection) collection);
        } catch (SQLException e) {
            m996a((Exception) e, "deleteIds threw exception on: " + collection);
            throw new RuntimeException(e);
        }
    }

    /* renamed from: a */
    public int m983a(PreparedDelete<T> uvVar) {
        try {
            return this.f23238b.mo1092a((PreparedDelete) uvVar);
        } catch (SQLException e) {
            m996a((Exception) e, "delete threw exception on: " + uvVar);
            throw new RuntimeException(e);
        }
    }

    /* renamed from: e */
    public CloseableIterator<T> iterator() {
        return this.f23238b.mo1060f();
    }

    @Override // p110z1.CloseableIterable
    public CloseableIterator<T> closeableIterator() {
        return this.f23238b.closeableIterator();
    }

    /* renamed from: a */
    public CloseableIterator<T> m998a(int i) {
        return this.f23238b.mo1106a(i);
    }

    /* renamed from: f */
    public CloseableWrappedIterable<T> m949f() {
        return this.f23238b.mo1058g();
    }

    /* renamed from: c */
    public CloseableWrappedIterable<T> m959c(PreparedQuery<T> uwVar) {
        return this.f23238b.mo1070c((PreparedQuery) uwVar);
    }

    /* renamed from: g */
    public void m947g() {
        try {
            this.f23238b.mo1056h();
        } catch (SQLException e) {
            m996a((Exception) e, "closeLastIterator threw exception");
            throw new RuntimeException(e);
        }
    }

    /* renamed from: d */
    public CloseableIterator<T> m954d(PreparedQuery<T> uwVar) {
        try {
            return this.f23238b.mo1065d((PreparedQuery) uwVar);
        } catch (SQLException e) {
            m996a((Exception) e, "iterator threw exception on: " + uwVar);
            throw new RuntimeException(e);
        }
    }

    /* renamed from: a */
    public CloseableIterator<T> m981a(PreparedQuery<T> uwVar, int i) {
        try {
            return this.f23238b.mo1090a(uwVar, i);
        } catch (SQLException e) {
            m996a((Exception) e, "iterator threw exception on: " + uwVar);
            throw new RuntimeException(e);
        }
    }

    /* renamed from: a */
    public GenericRawResults<String[]> m989a(String str, String... strArr) {
        try {
            return this.f23238b.mo1098a(str, strArr);
        } catch (SQLException e) {
            m996a((Exception) e, "queryRaw threw exception on: " + str);
            throw new RuntimeException(e);
        }
    }

    /* renamed from: b */
    public long m968b(String str, String... strArr) {
        try {
            return this.f23238b.mo1079b(str, strArr);
        } catch (SQLException e) {
            m996a((Exception) e, "queryRawValue threw exception on: " + str);
            throw new RuntimeException(e);
        }
    }

    /* renamed from: a */
    public <UO> GenericRawResults<UO> m990a(String str, RawRowMapper<UO> sdVar, String... strArr) {
        try {
            return this.f23238b.mo1099a(str, sdVar, strArr);
        } catch (SQLException e) {
            m996a((Exception) e, "queryRaw threw exception on: " + str);
            throw new RuntimeException(e);
        }
    }

    /* renamed from: a */
    public GenericRawResults<Object[]> m988a(String str, DataType[] snVarArr, String... strArr) {
        try {
            return this.f23238b.mo1097a(str, snVarArr, strArr);
        } catch (SQLException e) {
            m996a((Exception) e, "queryRaw threw exception on: " + str);
            throw new RuntimeException(e);
        }
    }

    /* renamed from: c */
    public int m960c(String str, String... strArr) {
        try {
            return this.f23238b.mo1071c(str, strArr);
        } catch (SQLException e) {
            m996a((Exception) e, "executeRaw threw exception on: " + str);
            throw new RuntimeException(e);
        }
    }

    /* renamed from: a */
    public int m992a(String str) {
        try {
            return this.f23238b.mo1101a(str);
        } catch (SQLException e) {
            m996a((Exception) e, "executeRawNoArgs threw exception on: " + str);
            throw new RuntimeException(e);
        }
    }

    /* renamed from: d */
    public int m955d(String str, String... strArr) {
        try {
            return this.f23238b.mo1066d(str, strArr);
        } catch (SQLException e) {
            m996a((Exception) e, "updateRaw threw exception on: " + str);
            throw new RuntimeException(e);
        }
    }

    /* renamed from: a */
    public <CT> CT m985a(Callable<CT> callable) {
        try {
            return (CT) this.f23238b.mo1094a((Callable<Object>) callable);
        } catch (Exception e) {
            m996a(e, "callBatchTasks threw exception on: " + callable);
            throw new RuntimeException(e);
        }
    }

    /* renamed from: l */
    public String m936l(T t) {
        return this.f23238b.mo1047l(t);
    }

    /* renamed from: b */
    public boolean m970b(T t, T t2) {
        try {
            return this.f23238b.mo1081b(t, t2);
        } catch (SQLException e) {
            m996a((Exception) e, "objectsEqual threw exception on: " + t + " and " + t2);
            throw new RuntimeException(e);
        }
    }

    /* renamed from: m */
    public ID m934m(T t) {
        try {
            return this.f23238b.mo1045m(t);
        } catch (SQLException e) {
            m996a((Exception) e, "extractId threw exception on: " + t);
            throw new RuntimeException(e);
        }
    }

    /* renamed from: h */
    public Class<T> m945h() {
        return this.f23238b.mo1054i();
    }

    /* renamed from: a */
    public FieldType m997a(Class<?> cls) {
        return this.f23238b.mo1105a(cls);
    }

    /* renamed from: i */
    public boolean m943i() {
        return this.f23238b.mo1052j();
    }

    /* renamed from: j */
    public boolean m941j() {
        try {
            return this.f23238b.mo1050k();
        } catch (SQLException e) {
            m996a((Exception) e, "isTableExists threw exception");
            throw new RuntimeException(e);
        }
    }

    /* renamed from: k */
    public long m939k() {
        try {
            return this.f23238b.mo1048l();
        } catch (SQLException e) {
            m996a((Exception) e, "countOf threw exception");
            throw new RuntimeException(e);
        }
    }

    /* renamed from: e */
    public long m950e(PreparedQuery<T> uwVar) {
        try {
            return this.f23238b.mo1061e((PreparedQuery) uwVar);
        } catch (SQLException e) {
            m996a((Exception) e, "countOf threw exception on " + uwVar);
            throw new RuntimeException(e);
        }
    }

    /* renamed from: a */
    public void m993a(T t, String str) {
        try {
            this.f23238b.mo1102a((Dao<T, ID>) t, str);
        } catch (SQLException e) {
            m996a((Exception) e, "assignEmptyForeignCollection threw exception on " + str);
            throw new RuntimeException(e);
        }
    }

    /* renamed from: b */
    public <FT> ForeignCollection<FT> m969b(String str) {
        try {
            return this.f23238b.mo1080b(str);
        } catch (SQLException e) {
            m996a((Exception) e, "getEmptyForeignCollection threw exception on " + str);
            throw new RuntimeException(e);
        }
    }

    /* renamed from: a */
    public void m973a(boolean z) {
        try {
            this.f23238b.mo1084a(z);
        } catch (SQLException e) {
            m996a((Exception) e, "setObjectCache(" + z + ") threw exception");
            throw new RuntimeException(e);
        }
    }

    /* renamed from: l */
    public ObjectCache m937l() {
        return this.f23238b.mo1046m();
    }

    /* renamed from: a */
    public void m984a(ObjectCache scVar) {
        try {
            this.f23238b.mo1093a(scVar);
        } catch (SQLException e) {
            m996a((Exception) e, "setObjectCache threw exception on " + scVar);
            throw new RuntimeException(e);
        }
    }

    /* renamed from: m */
    public void m935m() {
        this.f23238b.mo1044n();
    }

    /* renamed from: a */
    public T m975a(DatabaseResults woVar) {
        try {
            return this.f23238b.mo1086a(woVar);
        } catch (SQLException e) {
            m996a((Exception) e, "mapSelectStarRow threw exception on results");
            throw new RuntimeException(e);
        }
    }

    /* renamed from: n */
    public GenericRowMapper<T> m933n() {
        try {
            return this.f23238b.mo1042p();
        } catch (SQLException e) {
            m996a((Exception) e, "getSelectStarRowMapper threw exception");
            throw new RuntimeException(e);
        }
    }

    /* renamed from: n */
    public boolean m932n(ID id) {
        try {
            return this.f23238b.mo1043n(id);
        } catch (SQLException e) {
            m996a((Exception) e, "idExists threw exception on " + id);
            throw new RuntimeException(e);
        }
    }

    /* renamed from: o */
    public DatabaseConnection m931o() {
        try {
            return this.f23238b.mo1040r();
        } catch (SQLException e) {
            m996a((Exception) e, "startThreadConnection() threw exception");
            throw new RuntimeException(e);
        }
    }

    /* renamed from: a */
    public void m977a(DatabaseConnection wnVar) {
        try {
            this.f23238b.mo1088a(wnVar);
        } catch (SQLException e) {
            m996a((Exception) e, "endThreadConnection(" + wnVar + ") threw exception");
            throw new RuntimeException(e);
        }
    }

    @Deprecated
    /* renamed from: b */
    public void m963b(boolean z) {
        try {
            this.f23238b.mo1074b(z);
        } catch (SQLException e) {
            m996a((Exception) e, "setAutoCommit(" + z + ") threw exception");
            throw new RuntimeException(e);
        }
    }

    /* renamed from: a */
    public void m976a(DatabaseConnection wnVar, boolean z) {
        try {
            this.f23238b.mo1087a(wnVar, z);
        } catch (SQLException e) {
            m996a((Exception) e, "setAutoCommit(" + wnVar + "," + z + ") threw exception");
            throw new RuntimeException(e);
        }
    }

    @Deprecated
    /* renamed from: p */
    public boolean m930p() {
        try {
            return this.f23238b.mo1039s();
        } catch (SQLException e) {
            m996a((Exception) e, "isAutoCommit() threw exception");
            throw new RuntimeException(e);
        }
    }

    /* renamed from: b */
    public boolean m964b(DatabaseConnection wnVar) {
        try {
            return this.f23238b.mo1075b(wnVar);
        } catch (SQLException e) {
            m996a((Exception) e, "isAutoCommit(" + wnVar + ") threw exception");
            throw new RuntimeException(e);
        }
    }

    /* renamed from: c */
    public void m958c(DatabaseConnection wnVar) {
        try {
            this.f23238b.mo1069c(wnVar);
        } catch (SQLException e) {
            m996a((Exception) e, "commit(" + wnVar + ") threw exception");
            throw new RuntimeException(e);
        }
    }

    /* renamed from: d */
    public void m953d(DatabaseConnection wnVar) {
        try {
            this.f23238b.mo1064d(wnVar);
        } catch (SQLException e) {
            m996a((Exception) e, "rollBack(" + wnVar + ") threw exception");
            throw new RuntimeException(e);
        }
    }

    /* renamed from: a */
    public void m974a(ObjectFactory<T> wtVar) {
        this.f23238b.mo1085a((ObjectFactory) wtVar);
    }

    /* renamed from: q */
    public RawRowMapper<T> m929q() {
        return this.f23238b.mo1041q();
    }

    /* renamed from: r */
    public ConnectionSource m928r() {
        return this.f23238b.mo1038w();
    }

    /* renamed from: a */
    private void m996a(Exception exc, String str) {
        f23237c.m602a(f23236a, exc, str);
    }
}
