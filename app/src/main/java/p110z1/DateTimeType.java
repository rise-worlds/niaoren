package p110z1;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.sql.SQLException;

/* renamed from: z1.tk */
/* loaded from: classes3.dex */
public class DateTimeType extends BaseDataType {

    /* renamed from: a */
    private static final DateTimeType f23402a = new DateTimeType();

    /* renamed from: b */
    private static Class<?> f23403b = null;

    /* renamed from: c */
    private static Method f23404c = null;

    /* renamed from: d */
    private static Constructor<?> f23405d = null;

    /* renamed from: e */
    private static final String[] f23406e = {"org.joda.time.DateTime"};

    @Override // p110z1.BaseDataType, p110z1.DataPersister
    public boolean isAppropriateId() {
        return false;
    }

    @Override // p110z1.BaseDataType, p110z1.DataPersister
    public boolean isEscapedValue() {
        return false;
    }

    private DateTimeType() {
        super(SqlType.LONG, new Class[0]);
    }

    protected DateTimeType(SqlType suVar, Class<?>[] clsArr) {
        super(suVar, clsArr);
    }

    /* renamed from: a */
    public static DateTimeType m655a() {
        return f23402a;
    }

    @Override // p110z1.BaseDataType, p110z1.DataPersister
    public String[] getAssociatedClassNames() {
        return f23406e;
    }

    @Override // p110z1.BaseFieldConverter, p110z1.FieldConverter
    public Object javaToSqlArg(FieldType ssVar, Object obj) throws SQLException {
        try {
            Method b = m654b();
            if (obj == null) {
                return null;
            }
            return b.invoke(obj, new Object[0]);
        } catch (Exception e) {
            throw SqlExceptionUtil.m529a("Could not use reflection to get millis from Joda DateTime: " + obj, e);
        }
    }

    @Override // p110z1.BaseDataType, p110z1.FieldConverter
    public Object parseDefaultString(FieldType ssVar, String str) {
        return Long.valueOf(Long.parseLong(str));
    }

    @Override // p110z1.BaseDataType, p110z1.FieldConverter
    public Object resultToSqlArg(FieldType ssVar, DatabaseResults woVar, int i) throws SQLException {
        return Long.valueOf(woVar.mo209j(i));
    }

    @Override // p110z1.BaseFieldConverter, p110z1.FieldConverter
    public Object sqlArgToJava(FieldType ssVar, Object obj, int i) throws SQLException {
        try {
            return m653c().newInstance((Long) obj);
        } catch (Exception e) {
            throw SqlExceptionUtil.m529a("Could not use reflection to construct a Joda DateTime", e);
        }
    }

    @Override // p110z1.BaseDataType, p110z1.DataPersister
    public Class<?> getPrimaryClass() {
        try {
            return m652d();
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }

    /* renamed from: b */
    private Method m654b() throws Exception {
        if (f23404c == null) {
            f23404c = m652d().getMethod("getMillis", new Class[0]);
        }
        return f23404c;
    }

    /* renamed from: c */
    private Constructor<?> m653c() throws Exception {
        if (f23405d == null) {
            f23405d = m652d().getConstructor(Long.TYPE);
        }
        return f23405d;
    }

    /* renamed from: d */
    private Class<?> m652d() throws ClassNotFoundException {
        if (f23403b == null) {
            f23403b = Class.forName("org.joda.time.DateTime");
        }
        return f23403b;
    }
}
