package p110z1;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

/* renamed from: z1.rv */
/* loaded from: classes3.dex */
public interface Dao<T, ID> extends CloseableIterable<T> {
    /* renamed from: a */
    int mo1103a(T t, ID id) throws SQLException;

    /* renamed from: a */
    int mo1101a(String str) throws SQLException;

    /* renamed from: a */
    int mo1096a(Collection<T> collection) throws SQLException;

    /* renamed from: a */
    int mo1092a(PreparedDelete<T> uvVar) throws SQLException;

    /* renamed from: a */
    int mo1089a(PreparedUpdate<T> uyVar) throws SQLException;

    /* renamed from: a */
    T mo1104a(ID id) throws SQLException;

    /* renamed from: a */
    <CT> CT mo1094a(Callable<CT> callable) throws Exception;

    /* renamed from: a */
    T mo1091a(PreparedQuery<T> uwVar) throws SQLException;

    /* renamed from: a */
    T mo1086a(DatabaseResults woVar) throws SQLException;

    /* renamed from: a */
    List<T> mo1100a(String str, Object obj) throws SQLException;

    /* renamed from: a */
    List<T> mo1095a(Map<String, Object> map) throws SQLException;

    /* renamed from: a */
    CloseableIterator<T> mo1106a(int i);

    /* renamed from: a */
    CloseableIterator<T> mo1090a(PreparedQuery<T> uwVar, int i) throws SQLException;

    /* renamed from: a */
    <UO> GenericRawResults<UO> mo1099a(String str, RawRowMapper<UO> sdVar, String... strArr) throws SQLException;

    /* renamed from: a */
    GenericRawResults<String[]> mo1098a(String str, String... strArr) throws SQLException;

    /* renamed from: a */
    GenericRawResults<Object[]> mo1097a(String str, DataType[] snVarArr, String... strArr) throws SQLException;

    /* renamed from: a */
    FieldType mo1105a(Class<?> cls);

    /* renamed from: a */
    void mo1102a(T t, String str) throws SQLException;

    /* renamed from: a */
    void mo1093a(ObjectCache scVar) throws SQLException;

    /* renamed from: a */
    void mo1088a(DatabaseConnection wnVar) throws SQLException;

    /* renamed from: a */
    void mo1087a(DatabaseConnection wnVar, boolean z) throws SQLException;

    /* renamed from: a */
    void mo1085a(ObjectFactory<T> wtVar);

    /* renamed from: a */
    void mo1084a(boolean z) throws SQLException;

    /* renamed from: b */
    int mo1078b(Collection<ID> collection) throws SQLException;

    /* renamed from: b */
    long mo1079b(String str, String... strArr) throws SQLException;

    /* renamed from: b */
    List<T> mo1083b() throws SQLException;

    /* renamed from: b */
    List<T> mo1082b(T t) throws SQLException;

    /* renamed from: b */
    List<T> mo1077b(Map<String, Object> map) throws SQLException;

    /* renamed from: b */
    List<T> mo1076b(PreparedQuery<T> uwVar) throws SQLException;

    /* renamed from: b */
    <FT> ForeignCollection<FT> mo1080b(String str) throws SQLException;

    @Deprecated
    /* renamed from: b */
    void mo1074b(boolean z) throws SQLException;

    /* renamed from: b */
    boolean mo1081b(T t, T t2) throws SQLException;

    /* renamed from: b */
    boolean mo1075b(DatabaseConnection wnVar) throws SQLException;

    /* renamed from: c */
    int mo1071c(String str, String... strArr) throws SQLException;

    /* renamed from: c */
    List<T> mo1072c(T t) throws SQLException;

    /* renamed from: c */
    CloseableWrappedIterable<T> mo1070c(PreparedQuery<T> uwVar);

    /* renamed from: c */
    QueryBuilder<T, ID> mo1073c();

    /* renamed from: c */
    void mo1069c(DatabaseConnection wnVar) throws SQLException;

    /* renamed from: d */
    int mo1066d(String str, String... strArr) throws SQLException;

    /* renamed from: d */
    T mo1067d(T t) throws SQLException;

    /* renamed from: d */
    CloseableIterator<T> mo1065d(PreparedQuery<T> uwVar) throws SQLException;

    /* renamed from: d */
    UpdateBuilder<T, ID> mo1068d();

    /* renamed from: d */
    void mo1064d(DatabaseConnection wnVar) throws SQLException;

    /* renamed from: e */
    int mo1062e(T t) throws SQLException;

    /* renamed from: e */
    long mo1061e(PreparedQuery<T> uwVar) throws SQLException;

    /* renamed from: e */
    DeleteBuilder<T, ID> mo1063e();

    /* renamed from: f */
    T mo1059f(T t) throws SQLException;

    /* renamed from: f */
    CloseableIterator<T> mo1060f();

    /* renamed from: g */
    CloseableWrappedIterable<T> mo1058g();

    /* renamed from: g */
    C5551a mo1057g(T t) throws SQLException;

    /* renamed from: h */
    int mo1055h(T t) throws SQLException;

    /* renamed from: h */
    void mo1056h() throws SQLException;

    /* renamed from: i */
    int mo1053i(T t) throws SQLException;

    /* renamed from: i */
    Class<T> mo1054i();

    /* renamed from: j */
    int mo1051j(T t) throws SQLException;

    /* renamed from: j */
    boolean mo1052j();

    /* renamed from: k */
    int mo1049k(ID id) throws SQLException;

    /* renamed from: k */
    boolean mo1050k() throws SQLException;

    /* renamed from: l */
    long mo1048l() throws SQLException;

    /* renamed from: l */
    String mo1047l(T t);

    /* renamed from: m */
    ID mo1045m(T t) throws SQLException;

    /* renamed from: m */
    ObjectCache mo1046m();

    /* renamed from: n */
    void mo1044n();

    /* renamed from: n */
    boolean mo1043n(ID id) throws SQLException;

    /* renamed from: p */
    GenericRowMapper<T> mo1042p() throws SQLException;

    /* renamed from: q */
    RawRowMapper<T> mo1041q();

    /* renamed from: r */
    DatabaseConnection mo1040r() throws SQLException;

    @Deprecated
    /* renamed from: s */
    boolean mo1039s() throws SQLException;

    /* renamed from: w */
    ConnectionSource mo1038w();

    /* compiled from: Dao.java */
    /* renamed from: z1.rv$a */
    /* loaded from: classes3.dex */
    public static class C5551a {

        /* renamed from: a */
        private boolean f23213a;

        /* renamed from: b */
        private boolean f23214b;

        /* renamed from: c */
        private int f23215c;

        public C5551a(boolean z, boolean z2, int i) {
            this.f23213a = z;
            this.f23214b = z2;
            this.f23215c = i;
        }

        /* renamed from: a */
        public boolean m1037a() {
            return this.f23213a;
        }

        /* renamed from: b */
        public boolean m1036b() {
            return this.f23214b;
        }

        /* renamed from: c */
        public int m1035c() {
            return this.f23215c;
        }
    }
}
