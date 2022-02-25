package p110z1;

import java.lang.reflect.Constructor;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/* renamed from: z1.wu */
/* loaded from: classes3.dex */
public class TableInfo<T, ID> {

    /* renamed from: a */
    private static final FieldType[] f23633a = new FieldType[0];

    /* renamed from: b */
    private final BaseDaoImpl<T, ID> f23634b;

    /* renamed from: c */
    private final Class<T> f23635c;

    /* renamed from: d */
    private final String f23636d;

    /* renamed from: e */
    private final FieldType[] f23637e;

    /* renamed from: f */
    private final FieldType[] f23638f;

    /* renamed from: g */
    private final FieldType f23639g;

    /* renamed from: h */
    private final Constructor<T> f23640h;

    /* renamed from: i */
    private final boolean f23641i;

    /* renamed from: j */
    private Map<String, FieldType> f23642j;

    public TableInfo(ConnectionSource wmVar, BaseDaoImpl<T, ID> rpVar, Class<T> cls) throws SQLException {
        this(wmVar.mo249e(), rpVar, DatabaseTableConfig.m191a(wmVar, cls));
    }

    public TableInfo(DatabaseType siVar, BaseDaoImpl<T, ID> rpVar, DatabaseTableConfig<T> wrVar) throws SQLException {
        FieldType[] ssVarArr;
        FieldType[] ssVarArr2;
        this.f23634b = rpVar;
        this.f23635c = wrVar.m188b();
        this.f23636d = wrVar.m186c();
        this.f23637e = wrVar.m193a(siVar);
        FieldType ssVar = null;
        boolean z = false;
        int i = 0;
        for (FieldType ssVar2 : this.f23637e) {
            if (ssVar2.m703l() || ssVar2.m702m() || ssVar2.m701n()) {
                if (ssVar == null) {
                    ssVar = ssVar2;
                } else {
                    throw new SQLException("More than 1 idField configured for class " + this.f23635c + " (" + ssVar + "," + ssVar2 + ")");
                }
            }
            z = ssVar2.m737F() ? true : z;
            if (ssVar2.m741B()) {
                i++;
            }
        }
        this.f23639g = ssVar;
        this.f23640h = wrVar.m183e();
        this.f23641i = z;
        if (i == 0) {
            this.f23638f = f23633a;
            return;
        }
        this.f23638f = new FieldType[i];
        int i2 = 0;
        for (FieldType ssVar3 : this.f23637e) {
            if (ssVar3.m741B()) {
                this.f23638f[i2] = ssVar3;
                i2++;
            }
        }
    }

    /* renamed from: a */
    public Class<T> m175a() {
        return this.f23635c;
    }

    /* renamed from: b */
    public String m171b() {
        return this.f23636d;
    }

    /* renamed from: c */
    public FieldType[] m169c() {
        return this.f23637e;
    }

    /* renamed from: a */
    public FieldType m173a(String str) {
        FieldType[] ssVarArr;
        if (this.f23642j == null) {
            HashMap hashMap = new HashMap();
            for (FieldType ssVar : this.f23637e) {
                hashMap.put(ssVar.m715e(), ssVar);
            }
            this.f23642j = hashMap;
        }
        FieldType ssVar2 = this.f23642j.get(str);
        if (ssVar2 != null) {
            return ssVar2;
        }
        for (FieldType ssVar3 : this.f23637e) {
            if (ssVar3.m719c().equals(str)) {
                throw new IllegalArgumentException("You should use columnName '" + ssVar3.m715e() + "' for table " + this.f23636d + " instead of fieldName '" + ssVar3.m719c() + "'");
            }
        }
        throw new IllegalArgumentException("Unknown column name '" + str + "' in table " + this.f23636d);
    }

    /* renamed from: d */
    public FieldType m168d() {
        return this.f23639g;
    }

    /* renamed from: e */
    public Constructor<T> m167e() {
        return this.f23640h;
    }

    /* renamed from: a */
    public String m174a(T t) {
        FieldType[] ssVarArr;
        StringBuilder sb = new StringBuilder(64);
        sb.append(t.getClass().getSimpleName());
        for (FieldType ssVar : this.f23637e) {
            sb.append(' ');
            sb.append(ssVar.m715e());
            sb.append(SimpleComparison.f23609c);
            try {
                sb.append(ssVar.m720b(t));
            } catch (Exception e) {
                throw new IllegalStateException("Could not generate toString of field " + ssVar, e);
            }
        }
        return sb.toString();
    }

    /* renamed from: f */
    public T m166f() throws SQLException {
        T t;
        ObjectFactory<T> wtVar = null;
        try {
            if (this.f23634b != null) {
                wtVar = this.f23634b.m1116t();
            }
            if (wtVar == null) {
                t = this.f23640h.newInstance(new Object[0]);
            } else {
                t = wtVar.m176a(this.f23640h, this.f23634b.mo1054i());
            }
            m172a(this.f23634b, t);
            return t;
        } catch (Exception e) {
            throw SqlExceptionUtil.m529a("Could not create object for " + this.f23640h.getDeclaringClass(), e);
        }
    }

    /* renamed from: g */
    public boolean m165g() {
        return this.f23639g != null && this.f23637e.length > 1;
    }

    /* renamed from: h */
    public boolean m164h() {
        return this.f23641i;
    }

    /* renamed from: i */
    public FieldType[] m163i() {
        return this.f23638f;
    }

    /* renamed from: b */
    public boolean m170b(String str) {
        for (FieldType ssVar : this.f23637e) {
            if (ssVar.m715e().equals(str)) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: a */
    private static <T, ID> void m172a(BaseDaoImpl<T, ID> rpVar, T t) {
        if (t instanceof BaseDaoEnabled) {
            ((BaseDaoEnabled) t).m540a((Dao) rpVar);
        }
    }
}
