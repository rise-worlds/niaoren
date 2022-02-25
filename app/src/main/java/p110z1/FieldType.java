package p110z1;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Map;

/* renamed from: z1.ss */
/* loaded from: classes3.dex */
public class FieldType {

    /* renamed from: C */
    private static final ThreadLocal<C5563a> f23338C = new ThreadLocal<C5563a>() { // from class: z1.ss.1
        /* JADX INFO: Access modifiers changed from: protected */
        /* renamed from: a */
        public C5563a initialValue() {
            return new C5563a();
        }
    };

    /* renamed from: a */
    public static final String f23339a = "_id";

    /* renamed from: b */
    private static boolean f23340b;

    /* renamed from: c */
    private static byte f23341c;

    /* renamed from: d */
    private static char f23342d;

    /* renamed from: e */
    private static short f23343e;

    /* renamed from: f */
    private static int f23344f;

    /* renamed from: g */
    private static long f23345g;

    /* renamed from: h */
    private static float f23346h;

    /* renamed from: i */
    private static double f23347i;

    /* renamed from: A */
    private BaseDaoImpl<?, ?> f23348A;

    /* renamed from: B */
    private MappedQueryForId<Object, Object> f23349B;

    /* renamed from: j */
    private final ConnectionSource f23350j;

    /* renamed from: k */
    private final String f23351k;

    /* renamed from: l */
    private final Field f23352l;

    /* renamed from: m */
    private final String f23353m;

    /* renamed from: n */
    private final DatabaseFieldConfig f23354n;

    /* renamed from: o */
    private final boolean f23355o;

    /* renamed from: p */
    private final boolean f23356p;

    /* renamed from: q */
    private final String f23357q;

    /* renamed from: r */
    private final Method f23358r;

    /* renamed from: s */
    private final Method f23359s;

    /* renamed from: t */
    private DataPersister f23360t;

    /* renamed from: u */
    private Object f23361u;

    /* renamed from: v */
    private Object f23362v;

    /* renamed from: w */
    private FieldConverter f23363w;

    /* renamed from: x */
    private FieldType f23364x;

    /* renamed from: y */
    private TableInfo<?, ?> f23365y;

    /* renamed from: z */
    private FieldType f23366z;

    public FieldType(ConnectionSource wmVar, String str, Field field, DatabaseFieldConfig spVar, Class<?> cls) throws SQLException {
        DataPersister slVar;
        this.f23350j = wmVar;
        this.f23351k = str;
        DatabaseType e = wmVar.mo249e();
        this.f23352l = field;
        spVar.m827L();
        Class<?> type = field.getType();
        if (spVar.m802d() == null) {
            Class<? extends DataPersister> E = spVar.m834E();
            if (E == null || E == VoidType.class) {
                slVar = DataPersisterManager.m869a(field);
            } else {
                try {
                    try {
                        Object invoke = E.getDeclaredMethod("getSingleton", new Class[0]).invoke(null, new Object[0]);
                        if (invoke != null) {
                            try {
                                slVar = (DataPersister) invoke;
                            } catch (Exception e2) {
                                throw SqlExceptionUtil.m529a("Could not cast result of static getSingleton method to DataPersister from class " + E, e2);
                            }
                        } else {
                            throw new SQLException("Static getSingleton method should not return null on class " + E);
                        }
                    } catch (InvocationTargetException e3) {
                        throw SqlExceptionUtil.m529a("Could not run getSingleton method on class " + E, e3.getTargetException());
                    } catch (Exception e4) {
                        throw SqlExceptionUtil.m529a("Could not run getSingleton method on class " + E, e4);
                    }
                } catch (Exception e5) {
                    throw SqlExceptionUtil.m529a("Could not find getSingleton static method on class " + E, e5);
                }
            }
        } else {
            slVar = spVar.m802d();
            if (!slVar.isValidForField(field)) {
                StringBuilder sb = new StringBuilder();
                sb.append("Field class ");
                sb.append(type.getName());
                sb.append(" for field ");
                sb.append(this);
                sb.append(" is not valid for type ");
                sb.append(slVar);
                Class<?> primaryClass = slVar.getPrimaryClass();
                if (primaryClass != null) {
                    sb.append(", maybe should be " + primaryClass);
                }
                throw new IllegalArgumentException(sb.toString());
            }
        }
        String J = spVar.m829J();
        String name = field.getName();
        if (spVar.m779k() || spVar.m751v() || J != null) {
            if (slVar == null || !slVar.isPrimitive()) {
                if (J == null) {
                    name = name + "_id";
                } else {
                    name = name + "_" + J;
                }
                if (ForeignCollection.class.isAssignableFrom(type)) {
                    throw new SQLException("Field '" + field.getName() + "' in class " + type + "' should use the @" + ForeignCollectionField.class.getSimpleName() + " annotation not foreign=true");
                }
            } else {
                throw new IllegalArgumentException("Field " + this + " is a primitive class " + type + " but marked as foreign");
            }
        } else if (spVar.m749x()) {
            if (type == Collection.class || ForeignCollection.class.isAssignableFrom(type)) {
                Type genericType = field.getGenericType();
                if (!(genericType instanceof ParameterizedType)) {
                    throw new SQLException("Field class for '" + field.getName() + "' must be a parameterized Collection.");
                } else if (((ParameterizedType) genericType).getActualTypeArguments().length == 0) {
                    throw new SQLException("Field class for '" + field.getName() + "' must be a parameterized Collection with at least 1 type.");
                }
            } else {
                throw new SQLException("Field class for '" + field.getName() + "' must be of class " + ForeignCollection.class.getSimpleName() + " or Collection.");
            }
        } else if (slVar == null && !spVar.m749x()) {
            if (byte[].class.isAssignableFrom(type)) {
                throw new SQLException("ORMLite does not know how to store " + type + " for field '" + field.getName() + "'. byte[] fields must specify dataType=DataType.BYTE_ARRAY or SERIALIZABLE");
            } else if (Serializable.class.isAssignableFrom(type)) {
                throw new SQLException("ORMLite does not know how to store " + type + " for field '" + field.getName() + "'.  Use another class, custom persister, or to serialize it use dataType=DataType.SERIALIZABLE");
            } else {
                throw new IllegalArgumentException("ORMLite does not know how to store " + type + " for field " + field.getName() + ". Use another class or a custom persister.");
            }
        }
        if (spVar.m812b() == null) {
            this.f23353m = name;
        } else {
            this.f23353m = spVar.m812b();
        }
        this.f23354n = spVar;
        if (spVar.m788h()) {
            if (spVar.m785i() || spVar.m782j() != null) {
                throw new IllegalArgumentException("Must specify one of id, generatedId, and generatedIdSequence with " + field.getName());
            }
            this.f23355o = true;
            this.f23356p = false;
            this.f23357q = null;
        } else if (spVar.m785i()) {
            if (spVar.m782j() == null) {
                this.f23355o = true;
                this.f23356p = true;
                if (e.mo895e()) {
                    this.f23357q = e.mo907a(str, this);
                } else {
                    this.f23357q = null;
                }
            } else {
                throw new IllegalArgumentException("Must specify one of id, generatedId, and generatedIdSequence with " + field.getName());
            }
        } else if (spVar.m782j() != null) {
            this.f23355o = true;
            this.f23356p = true;
            String j = spVar.m782j();
            this.f23357q = e.mo887m() ? j.toUpperCase() : j;
        } else {
            this.f23355o = false;
            this.f23356p = false;
            this.f23357q = null;
        }
        if (!this.f23355o || (!spVar.m779k() && !spVar.m751v())) {
            if (spVar.m773m()) {
                this.f23358r = DatabaseFieldConfig.m820a(field, true);
                this.f23359s = DatabaseFieldConfig.m808b(field, true);
            } else {
                if (!field.isAccessible()) {
                    try {
                        this.f23352l.setAccessible(true);
                    } catch (SecurityException unused) {
                        throw new IllegalArgumentException("Could not open access to field " + field.getName() + ".  You may have to set useGetSet=true to fix.");
                    }
                }
                this.f23358r = null;
                this.f23359s = null;
            }
            if (spVar.m833F() && !spVar.m785i()) {
                throw new IllegalArgumentException("Field " + field.getName() + " must be a generated-id if allowGeneratedIdInsert = true");
            } else if (spVar.m751v() && !spVar.m779k()) {
                throw new IllegalArgumentException("Field " + field.getName() + " must have foreign = true if foreignAutoRefresh = true");
            } else if (spVar.m831H() && !spVar.m779k()) {
                throw new IllegalArgumentException("Field " + field.getName() + " must have foreign = true if foreignAutoCreate = true");
            } else if (spVar.m829J() != null && !spVar.m779k()) {
                throw new IllegalArgumentException("Field " + field.getName() + " must have foreign = true if foreignColumnName is set");
            } else if (spVar.m830I() && (slVar == null || !slVar.isValidForVersion())) {
                throw new IllegalArgumentException("Field " + field.getName() + " is not a valid type to be a version field");
            } else if (spVar.m750w() <= 0 || spVar.m751v()) {
                m725a(e, slVar);
            } else {
                throw new IllegalArgumentException("Field " + field.getName() + " has maxForeignAutoRefreshLevel set but not foreignAutoRefresh is false");
            }
        } else {
            throw new IllegalArgumentException("Id field " + field.getName() + " cannot also be a foreign object");
        }
    }

    /* renamed from: a */
    public void m724a(ConnectionSource wmVar, Class<?> cls) throws SQLException {
        TableInfo<?, ?> wuVar;
        FieldType ssVar;
        FieldType ssVar2;
        BaseDaoImpl<?, ?> rpVar;
        BaseDaoImpl<?, ?> rpVar2;
        BaseDaoImpl<?, ?> rpVar3;
        Class<?> type = this.f23352l.getType();
        DatabaseType e = wmVar.mo249e();
        String J = this.f23354n.m829J();
        MappedQueryForId<Object, Object> vpVar = null;
        if (this.f23354n.m751v() || J != null) {
            DatabaseTableConfig<?> l = this.f23354n.m776l();
            if (l == null) {
                rpVar = (BaseDaoImpl) DaoManager.m1027a(wmVar, type);
                wuVar = rpVar.m1114v();
            } else {
                l.m192a(wmVar);
                rpVar = (BaseDaoImpl) DaoManager.m1025a(wmVar, l);
                wuVar = rpVar.m1114v();
            }
            if (J == null) {
                ssVar2 = wuVar.m168d();
                if (ssVar2 == null) {
                    throw new IllegalArgumentException("Foreign field " + type + " does not have id field");
                }
            } else {
                FieldType a = wuVar.m173a(J);
                if (a != null) {
                    ssVar2 = a;
                } else {
                    throw new IllegalArgumentException("Foreign field " + type + " does not have field named '" + J + "'");
                }
            }
            ssVar = null;
            vpVar = MappedQueryForId.m294a(e, wuVar, ssVar2);
        } else if (this.f23354n.m779k()) {
            DataPersister slVar = this.f23360t;
            if (slVar == null || !slVar.isPrimitive()) {
                DatabaseTableConfig<?> l2 = this.f23354n.m776l();
                if (l2 != null) {
                    l2.m192a(wmVar);
                    rpVar3 = (BaseDaoImpl) DaoManager.m1025a(wmVar, l2);
                } else {
                    rpVar3 = (BaseDaoImpl) DaoManager.m1027a(wmVar, type);
                }
                wuVar = rpVar3.m1114v();
                ssVar2 = wuVar.m168d();
                if (ssVar2 == null) {
                    throw new IllegalArgumentException("Foreign field " + type + " does not have id field");
                } else if (!m737F() || ssVar2.m702m()) {
                    rpVar = rpVar3;
                    ssVar = null;
                } else {
                    throw new IllegalArgumentException("Field " + this.f23352l.getName() + ", if foreignAutoCreate = true then class " + type.getSimpleName() + " must have id field with generatedId = true");
                }
            } else {
                throw new IllegalArgumentException("Field " + this + " is a primitive class " + type + " but marked as foreign");
            }
        } else if (!this.f23354n.m749x()) {
            ssVar = null;
            wuVar = null;
            rpVar = null;
            ssVar2 = null;
        } else if (type == Collection.class || ForeignCollection.class.isAssignableFrom(type)) {
            Type genericType = this.f23352l.getGenericType();
            if (genericType instanceof ParameterizedType) {
                Type[] actualTypeArguments = ((ParameterizedType) genericType).getActualTypeArguments();
                if (actualTypeArguments.length != 0) {
                    Class<?> cls2 = (Class) actualTypeArguments[0];
                    DatabaseTableConfig<?> l3 = this.f23354n.m776l();
                    if (l3 == null) {
                        rpVar2 = (BaseDaoImpl) DaoManager.m1027a(wmVar, cls2);
                    } else {
                        rpVar2 = (BaseDaoImpl) DaoManager.m1025a(wmVar, l3);
                    }
                    FieldType a2 = m731a(cls2, cls, rpVar2);
                    rpVar = rpVar2;
                    ssVar = a2;
                    wuVar = null;
                    ssVar2 = null;
                } else {
                    throw new SQLException("Field class for '" + this.f23352l.getName() + "' must be a parameterized Collection with at least 1 type.");
                }
            } else {
                throw new SQLException("Field class for '" + this.f23352l.getName() + "' must be a parameterized Collection.");
            }
        } else {
            throw new SQLException("Field class for '" + this.f23352l.getName() + "' must be of class " + ForeignCollection.class.getSimpleName() + " or Collection.");
        }
        this.f23349B = vpVar;
        this.f23365y = wuVar;
        this.f23366z = ssVar;
        this.f23348A = rpVar;
        this.f23364x = ssVar2;
        FieldType ssVar3 = this.f23364x;
        if (ssVar3 != null) {
            m725a(e, ssVar3.m713f());
        }
    }

    /* renamed from: a */
    public Field m732a() {
        return this.f23352l;
    }

    /* renamed from: b */
    public String m721b() {
        return this.f23351k;
    }

    /* renamed from: c */
    public String m719c() {
        return this.f23352l.getName();
    }

    /* renamed from: d */
    public Class<?> m717d() {
        return this.f23352l.getType();
    }

    /* renamed from: e */
    public String m715e() {
        return this.f23353m;
    }

    /* renamed from: f */
    public DataPersister m713f() {
        return this.f23360t;
    }

    /* renamed from: g */
    public Object m711g() {
        return this.f23362v;
    }

    /* renamed from: h */
    public SqlType m709h() {
        return this.f23363w.getSqlType();
    }

    /* renamed from: i */
    public Object m707i() {
        return this.f23361u;
    }

    /* renamed from: j */
    public int m705j() {
        return this.f23354n.m794f();
    }

    /* renamed from: k */
    public boolean m704k() {
        return this.f23354n.m791g();
    }

    /* renamed from: l */
    public boolean m703l() {
        return this.f23355o;
    }

    /* renamed from: m */
    public boolean m702m() {
        return this.f23356p;
    }

    /* renamed from: n */
    public boolean m701n() {
        return this.f23357q != null;
    }

    /* renamed from: o */
    public String m700o() {
        return this.f23357q;
    }

    /* renamed from: p */
    public boolean m699p() {
        return this.f23354n.m779k();
    }

    /* renamed from: a */
    public void m727a(Object obj, Object obj2, boolean z, ObjectCache scVar) throws SQLException {
        if (!(this.f23364x == null || obj2 == null)) {
            Object b = m720b(obj);
            if (b != null && b.equals(obj2)) {
                return;
            }
            if (!z) {
                C5563a aVar = f23338C.get();
                if (aVar.f23367a == 0) {
                    aVar.f23368b = this.f23354n.m750w();
                }
                if (aVar.f23367a >= aVar.f23368b) {
                    Object f = this.f23365y.m166f();
                    this.f23364x.m727a(f, obj2, false, scVar);
                    obj2 = f;
                } else {
                    if (this.f23349B == null) {
                        this.f23349B = MappedQueryForId.m294a(this.f23350j.mo249e(), this.f23348A.m1114v(), this.f23364x);
                    }
                    aVar.f23367a++;
                    try {
                        DatabaseConnection a = this.f23350j.mo256a();
                        obj2 = this.f23349B.m293a(a, (DatabaseConnection) obj2, scVar);
                        this.f23350j.mo255a(a);
                    } finally {
                        aVar.f23367a--;
                    }
                }
            }
        }
        Method method = this.f23359s;
        if (method == null) {
            try {
                this.f23352l.set(obj, obj2);
            } catch (IllegalAccessException e) {
                throw SqlExceptionUtil.m529a("Could not assign object '" + obj2 + "' to field " + this, e);
            } catch (IllegalArgumentException e2) {
                throw SqlExceptionUtil.m529a("Could not assign object '" + obj2 + "' to field " + this, e2);
            }
        } else {
            try {
                method.invoke(obj, obj2);
            } catch (Exception e3) {
                throw SqlExceptionUtil.m529a("Could not call " + this.f23359s + " on object with '" + obj2 + "' for " + this, e3);
            }
        }
    }

    /* renamed from: a */
    public Object m729a(Object obj, Number number, ObjectCache scVar) throws SQLException {
        Object convertIdNumber = this.f23360t.convertIdNumber(number);
        if (convertIdNumber != null) {
            m727a(obj, convertIdNumber, false, scVar);
            return convertIdNumber;
        }
        throw new SQLException("Invalid class " + this.f23360t + " for sequence-id " + this);
    }

    /* renamed from: a */
    public <FV> FV m730a(Object obj) throws SQLException {
        Method method = this.f23358r;
        if (method == null) {
            try {
                return (FV) this.f23352l.get(obj);
            } catch (Exception e) {
                throw SqlExceptionUtil.m529a("Could not get field value for " + this, e);
            }
        } else {
            try {
                return (FV) method.invoke(obj, new Object[0]);
            } catch (Exception e2) {
                throw SqlExceptionUtil.m529a("Could not call " + this.f23358r + " for " + this, e2);
            }
        }
    }

    /* renamed from: b */
    public Object m720b(Object obj) throws SQLException {
        Object a = m730a(obj);
        FieldType ssVar = this.f23364x;
        return (ssVar == null || a == null) ? a : ssVar.m730a(a);
    }

    /* renamed from: c */
    public Object m718c(Object obj) throws SQLException {
        return m716d(m720b(obj));
    }

    /* renamed from: d */
    public Object m716d(Object obj) throws SQLException {
        if (obj == null) {
            return null;
        }
        return this.f23363w.javaToSqlArg(this, obj);
    }

    /* renamed from: a */
    public Object m726a(String str, int i) throws SQLException {
        if (str == null) {
            return null;
        }
        return this.f23363w.resultStringToJava(this, str, i);
    }

    /* renamed from: e */
    public Object m714e(Object obj) {
        DataPersister slVar = this.f23360t;
        if (slVar == null) {
            return null;
        }
        return slVar.moveToNextValue(obj);
    }

    /* renamed from: q */
    public FieldType m698q() {
        return this.f23364x;
    }

    /* renamed from: r */
    public boolean m697r() {
        return this.f23360t.isEscapedValue();
    }

    /* renamed from: s */
    public Enum<?> m696s() {
        return this.f23354n.m770n();
    }

    /* renamed from: t */
    public String m695t() {
        return this.f23354n.m761q();
    }

    /* renamed from: u */
    public boolean m694u() {
        return this.f23354n.m758r();
    }

    /* renamed from: v */
    public boolean m693v() {
        return this.f23354n.m755s();
    }

    /* renamed from: w */
    public String m692w() {
        return this.f23354n.m793f(this.f23351k);
    }

    /* renamed from: x */
    public String m691x() {
        return this.f23354n.m787h(this.f23351k);
    }

    /* renamed from: y */
    public boolean m690y() {
        return this.f23360t.isEscapedDefaultValue();
    }

    /* renamed from: z */
    public boolean m689z() throws SQLException {
        if (this.f23354n.m749x()) {
            return false;
        }
        DataPersister slVar = this.f23360t;
        if (slVar != null) {
            return slVar.isComparable();
        }
        throw new SQLException("Internal error.  Data-persister is not configured for field.  Please post _full_ exception with associated data objects to mailing list: " + this);
    }

    /* renamed from: A */
    public boolean m742A() {
        return this.f23360t.isArgumentHolderRequired();
    }

    /* renamed from: B */
    public boolean m741B() {
        return this.f23354n.m749x();
    }

    /* renamed from: a */
    public <FT, FID> BaseForeignCollection<FT, FID> m728a(Object obj, FID fid) throws SQLException {
        if (this.f23366z == null) {
            return null;
        }
        BaseDaoImpl<?, ?> rpVar = this.f23348A;
        if (!this.f23354n.m748y()) {
            return new LazyForeignCollection(rpVar, obj, fid, this.f23366z, this.f23354n.m837B(), this.f23354n.m836C());
        }
        C5563a aVar = f23338C.get();
        if (aVar.f23369c == 0) {
            aVar.f23370d = this.f23354n.m747z();
        }
        if (aVar.f23369c >= aVar.f23370d) {
            return new LazyForeignCollection(rpVar, obj, fid, this.f23366z, this.f23354n.m837B(), this.f23354n.m836C());
        }
        aVar.f23369c++;
        try {
            return new EagerForeignCollection(rpVar, obj, fid, this.f23366z, this.f23354n.m837B(), this.f23354n.m836C());
        } finally {
            aVar.f23369c--;
        }
    }

    /* renamed from: a */
    public <T> T m722a(DatabaseResults woVar, Map<String, Integer> map) throws SQLException {
        Integer num = map.get(this.f23353m);
        if (num == null) {
            num = Integer.valueOf(woVar.mo225a(this.f23353m));
            map.put(this.f23353m, num);
        }
        T t = (T) this.f23363w.resultToJava(this, woVar, num.intValue());
        if (this.f23354n.m779k()) {
            if (woVar.mo202p(num.intValue())) {
                return null;
            }
        } else if (this.f23360t.isPrimitive()) {
            if (this.f23354n.m767o() && woVar.mo202p(num.intValue())) {
                throw new SQLException("Results value for primitive field '" + this.f23352l.getName() + "' was an invalid null value");
            }
        } else if (!this.f23363w.isStreamType() && woVar.mo202p(num.intValue())) {
            return null;
        }
        return t;
    }

    /* renamed from: C */
    public boolean m740C() {
        return this.f23360t.isSelfGeneratedId();
    }

    /* renamed from: D */
    public boolean m739D() {
        return this.f23354n.m833F();
    }

    /* renamed from: E */
    public String m738E() {
        return this.f23354n.m832G();
    }

    /* renamed from: F */
    public boolean m737F() {
        return this.f23354n.m831H();
    }

    /* renamed from: G */
    public boolean m736G() {
        return this.f23354n.m830I();
    }

    /* renamed from: H */
    public Object m735H() {
        return this.f23360t.generateId();
    }

    /* renamed from: I */
    public boolean m734I() {
        return this.f23354n.m828K();
    }

    /* renamed from: f */
    public <FV> FV m712f(Object obj) throws SQLException {
        FV fv = (FV) m720b(obj);
        if (m706i(fv)) {
            return null;
        }
        return fv;
    }

    /* renamed from: g */
    public boolean m710g(Object obj) throws SQLException {
        return m706i(m720b(obj));
    }

    /* renamed from: J */
    public Object m733J() {
        if (this.f23352l.getType() == Boolean.TYPE) {
            return Boolean.valueOf(f23340b);
        }
        if (this.f23352l.getType() == Byte.TYPE || this.f23352l.getType() == Byte.class) {
            return Byte.valueOf(f23341c);
        }
        if (this.f23352l.getType() == Character.TYPE || this.f23352l.getType() == Character.class) {
            return Character.valueOf(f23342d);
        }
        if (this.f23352l.getType() == Short.TYPE || this.f23352l.getType() == Short.class) {
            return Short.valueOf(f23343e);
        }
        if (this.f23352l.getType() == Integer.TYPE || this.f23352l.getType() == Integer.class) {
            return Integer.valueOf(f23344f);
        }
        if (this.f23352l.getType() == Long.TYPE || this.f23352l.getType() == Long.class) {
            return Long.valueOf(f23345g);
        }
        if (this.f23352l.getType() == Float.TYPE || this.f23352l.getType() == Float.class) {
            return Float.valueOf(f23346h);
        }
        if (this.f23352l.getType() == Double.TYPE || this.f23352l.getType() == Double.class) {
            return Double.valueOf(f23347i);
        }
        return null;
    }

    /* renamed from: h */
    public <T> int m708h(T t) throws SQLException {
        return this.f23348A.mo1062e((BaseDaoImpl<?, ?>) t);
    }

    /* renamed from: a */
    public static FieldType m723a(ConnectionSource wmVar, String str, Field field, Class<?> cls) throws SQLException {
        DatabaseFieldConfig a = DatabaseFieldConfig.m819a(wmVar.mo249e(), str, field);
        if (a == null) {
            return null;
        }
        return new FieldType(wmVar, str, field, a, cls);
    }

    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        return this.f23352l.equals(((FieldType) obj).f23352l);
    }

    public int hashCode() {
        return this.f23352l.hashCode();
    }

    public String toString() {
        return getClass().getSimpleName() + ":name=" + this.f23352l.getName() + ",class=" + this.f23352l.getDeclaringClass().getSimpleName();
    }

    /* renamed from: i */
    private boolean m706i(Object obj) {
        if (obj == null) {
            return true;
        }
        return obj.equals(m733J());
    }

    /* renamed from: a */
    private FieldType m731a(Class<?> cls, Class<?> cls2, BaseDaoImpl<?, ?> rpVar) throws SQLException {
        FieldType[] c;
        String D = this.f23354n.m835D();
        for (FieldType ssVar : rpVar.m1114v().m169c()) {
            if (ssVar.m717d() == cls2 && (D == null || ssVar.m732a().getName().equals(D))) {
                if (ssVar.f23354n.m779k() || ssVar.f23354n.m751v()) {
                    return ssVar;
                } else {
                    throw new SQLException("Foreign collection object " + cls + " for field '" + this.f23352l.getName() + "' contains a field of class " + cls2 + " but it's not foreign");
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Foreign collection class ");
        sb.append(cls.getName());
        sb.append(" for field '");
        sb.append(this.f23352l.getName());
        sb.append("' column-name does not contain a foreign field");
        if (D != null) {
            sb.append(" named '");
            sb.append(D);
            sb.append('\'');
        }
        sb.append(" of class ");
        sb.append(cls2.getName());
        throw new SQLException(sb.toString());
    }

    /* renamed from: a */
    private void m725a(DatabaseType siVar, DataPersister slVar) throws SQLException {
        DataType[] values;
        this.f23360t = slVar;
        if (slVar != null) {
            this.f23363w = siVar.mo878a(slVar);
            if (this.f23356p && !slVar.isValidGeneratedType()) {
                StringBuilder sb = new StringBuilder();
                sb.append("Generated-id field '");
                sb.append(this.f23352l.getName());
                sb.append("' in ");
                sb.append(this.f23352l.getDeclaringClass().getSimpleName());
                sb.append(" can't be type ");
                sb.append(this.f23360t.getSqlType());
                sb.append(".  Must be one of: ");
                for (DataType snVar : DataType.values()) {
                    DataPersister dataPersister = snVar.getDataPersister();
                    if (dataPersister != null && dataPersister.isValidGeneratedType()) {
                        sb.append(snVar);
                        sb.append(' ');
                    }
                }
                throw new IllegalArgumentException(sb.toString());
            } else if (this.f23354n.m767o() && !slVar.isPrimitive()) {
                throw new SQLException("Field " + this.f23352l.getName() + " must be a primitive if set with throwIfNull");
            } else if (!this.f23355o || slVar.isAppropriateId()) {
                this.f23362v = slVar.makeConfigObject(this);
                String e = this.f23354n.m798e();
                if (e == null || e.equals("")) {
                    this.f23361u = null;
                } else if (!this.f23356p) {
                    this.f23361u = this.f23363w.parseDefaultString(this, e);
                } else {
                    throw new SQLException("Field '" + this.f23352l.getName() + "' cannot be a generatedId and have a default value '" + e + "'");
                }
            } else {
                throw new SQLException("Field '" + this.f23352l.getName() + "' is of data type " + slVar + " which cannot be the ID field");
            }
        } else if (!this.f23354n.m779k() && !this.f23354n.m749x()) {
            throw new SQLException("Data persister for field " + this + " is null but the field is not a foreign or foreignCollection");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: FieldType.java */
    /* renamed from: z1.ss$a */
    /* loaded from: classes3.dex */
    public static class C5563a {

        /* renamed from: a */
        int f23367a;

        /* renamed from: b */
        int f23368b;

        /* renamed from: c */
        int f23369c;

        /* renamed from: d */
        int f23370d;

        private C5563a() {
        }
    }
}
