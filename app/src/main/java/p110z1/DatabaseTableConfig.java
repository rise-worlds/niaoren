package p110z1;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/* renamed from: z1.wr */
/* loaded from: classes3.dex */
public class DatabaseTableConfig<T> {

    /* renamed from: a */
    private Class<T> f23622a;

    /* renamed from: b */
    private String f23623b;

    /* renamed from: c */
    private List<DatabaseFieldConfig> f23624c;

    /* renamed from: d */
    private FieldType[] f23625d;

    /* renamed from: e */
    private Constructor<T> f23626e;

    public DatabaseTableConfig() {
    }

    public DatabaseTableConfig(Class<T> cls, List<DatabaseFieldConfig> list) {
        this(cls, m187b(cls), list);
    }

    public DatabaseTableConfig(Class<T> cls, String str, List<DatabaseFieldConfig> list) {
        this.f23622a = cls;
        this.f23623b = str;
        this.f23624c = list;
    }

    private DatabaseTableConfig(Class<T> cls, String str, FieldType[] ssVarArr) {
        this.f23622a = cls;
        this.f23623b = str;
        this.f23625d = ssVarArr;
    }

    /* renamed from: a */
    public void m198a() {
        Class<T> cls = this.f23622a;
        if (cls == null) {
            throw new IllegalStateException("dataClass was never set on " + getClass().getSimpleName());
        } else if (this.f23623b == null) {
            this.f23623b = m187b(cls);
        }
    }

    /* renamed from: b */
    public Class<T> m188b() {
        return this.f23622a;
    }

    /* renamed from: a */
    public void m197a(Class<T> cls) {
        this.f23622a = cls;
    }

    /* renamed from: c */
    public String m186c() {
        return this.f23623b;
    }

    /* renamed from: a */
    public void m196a(String str) {
        this.f23623b = str;
    }

    /* renamed from: a */
    public void m194a(List<DatabaseFieldConfig> list) {
        this.f23624c = list;
    }

    /* renamed from: a */
    public void m192a(ConnectionSource wmVar) throws SQLException {
        if (this.f23625d == null) {
            List<DatabaseFieldConfig> list = this.f23624c;
            if (list == null) {
                this.f23625d = m190a(wmVar, this.f23622a, this.f23623b);
            } else {
                this.f23625d = m189a(wmVar, this.f23623b, list);
            }
        }
    }

    /* renamed from: a */
    public FieldType[] m193a(DatabaseType siVar) throws SQLException {
        FieldType[] ssVarArr = this.f23625d;
        if (ssVarArr != null) {
            return ssVarArr;
        }
        throw new SQLException("Field types have not been extracted in table config");
    }

    /* renamed from: d */
    public List<DatabaseFieldConfig> m184d() {
        return this.f23624c;
    }

    /* renamed from: e */
    public Constructor<T> m183e() {
        if (this.f23626e == null) {
            this.f23626e = m185c(this.f23622a);
        }
        return this.f23626e;
    }

    /* renamed from: a */
    public void m195a(Constructor<T> constructor) {
        this.f23626e = constructor;
    }

    /* renamed from: a */
    public static <T> DatabaseTableConfig<T> m191a(ConnectionSource wmVar, Class<T> cls) throws SQLException {
        String b = m187b(cls);
        if (wmVar.mo249e().mo887m()) {
            b = b.toUpperCase();
        }
        return new DatabaseTableConfig<>(cls, b, m190a(wmVar, cls, b));
    }

    /* renamed from: b */
    public static <T> String m187b(Class<T> cls) {
        DatabaseTable wqVar = (DatabaseTable) cls.getAnnotation(DatabaseTable.class);
        if (wqVar != null && wqVar.m200a() != null && wqVar.m200a().length() > 0) {
            return wqVar.m200a();
        }
        String a = JavaxPersistence.m531a(cls);
        return a == null ? cls.getSimpleName().toLowerCase() : a;
    }

    /* renamed from: c */
    public static <T> Constructor<T> m185c(Class<T> cls) {
        try {
            for (Constructor<?> constructor : cls.getDeclaredConstructors()) {
                Constructor<T> constructor2 = (Constructor<T>) constructor;
                if (constructor2.getParameterTypes().length == 0) {
                    if (!constructor2.isAccessible()) {
                        try {
                            constructor2.setAccessible(true);
                        } catch (SecurityException unused) {
                            throw new IllegalArgumentException("Could not open access to constructor for " + cls);
                        }
                    }
                    return constructor2;
                }
            }
            if (cls.getEnclosingClass() == null) {
                throw new IllegalArgumentException("Can't find a no-arg constructor for " + cls);
            }
            throw new IllegalArgumentException("Can't find a no-arg constructor for " + cls + ".  Missing static on inner class?");
        } catch (Exception e) {
            throw new IllegalArgumentException("Can't lookup declared constructors for " + cls, e);
        }
    }

    /* renamed from: a */
    private static <T> FieldType[] m190a(ConnectionSource wmVar, Class<T> cls, String str) throws SQLException {
        ArrayList arrayList = new ArrayList();
        for (Class<T> cls2 = cls; cls2 != null; cls2 = cls2.getSuperclass()) {
            for (Field field : cls2.getDeclaredFields()) {
                FieldType a = FieldType.m723a(wmVar, str, field, (Class<?>) cls);
                if (a != null) {
                    arrayList.add(a);
                }
            }
        }
        if (!arrayList.isEmpty()) {
            return (FieldType[]) arrayList.toArray(new FieldType[arrayList.size()]);
        }
        throw new IllegalArgumentException("No fields have a " + DatabaseField.class.getSimpleName() + " annotation in " + cls);
    }

    /* renamed from: a */
    private FieldType[] m189a(ConnectionSource wmVar, String str, List<DatabaseFieldConfig> list) throws SQLException {
        Field declaredField;
        ArrayList arrayList = new ArrayList();
        for (DatabaseFieldConfig spVar : list) {
            FieldType ssVar = null;
            Class<T> cls = this.f23622a;
            while (true) {
                if (cls == null) {
                    break;
                }
                try {
                    declaredField = cls.getDeclaredField(spVar.m826a());
                } catch (NoSuchFieldException unused) {
                }
                if (declaredField != null) {
                    ssVar = new FieldType(wmVar, str, declaredField, spVar, this.f23622a);
                    break;
                }
                cls = cls.getSuperclass();
            }
            if (ssVar != null) {
                arrayList.add(ssVar);
            } else {
                throw new SQLException("Could not find declared field with name '" + spVar.m826a() + "' for " + this.f23622a);
            }
        }
        if (!arrayList.isEmpty()) {
            return (FieldType[]) arrayList.toArray(new FieldType[arrayList.size()]);
        }
        throw new SQLException("No fields were configured for class " + this.f23622a);
    }
}
