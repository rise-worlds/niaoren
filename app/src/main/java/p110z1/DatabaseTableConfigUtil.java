package p110z1;

import com.liulishuo.filedownloader.model.ConnectionModel;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.http.cookie.ClientCookie;
import org.apache.tools.ant.types.selectors.SizeSelector;

/* renamed from: z1.ro */
/* loaded from: classes3.dex */
public class DatabaseTableConfigUtil {

    /* renamed from: A */
    private static final int f23157A = 20;

    /* renamed from: B */
    private static final int f23158B = 21;

    /* renamed from: C */
    private static final int f23159C = 22;

    /* renamed from: D */
    private static final int f23160D = 23;

    /* renamed from: E */
    private static final int f23161E = 24;

    /* renamed from: F */
    private static final int f23162F = 25;

    /* renamed from: G */
    private static final int f23163G = 26;

    /* renamed from: H */
    private static final int f23164H = 27;

    /* renamed from: I */
    private static final int f23165I = 28;

    /* renamed from: J */
    private static final int f23166J = 29;

    /* renamed from: a */
    private static Class<?> f23167a = null;

    /* renamed from: b */
    private static Field f23168b = null;

    /* renamed from: c */
    private static Class<?> f23169c = null;

    /* renamed from: d */
    private static Field f23170d = null;

    /* renamed from: e */
    private static Field f23171e = null;

    /* renamed from: f */
    private static int f23172f = 0;

    /* renamed from: g */
    private static final int[] f23173g = m1131b();

    /* renamed from: h */
    private static final int f23174h = 1;

    /* renamed from: i */
    private static final int f23175i = 2;

    /* renamed from: j */
    private static final int f23176j = 3;

    /* renamed from: k */
    private static final int f23177k = 4;

    /* renamed from: l */
    private static final int f23178l = 5;

    /* renamed from: m */
    private static final int f23179m = 6;

    /* renamed from: n */
    private static final int f23180n = 7;

    /* renamed from: o */
    private static final int f23181o = 8;

    /* renamed from: p */
    private static final int f23182p = 9;

    /* renamed from: q */
    private static final int f23183q = 10;

    /* renamed from: r */
    private static final int f23184r = 11;

    /* renamed from: s */
    private static final int f23185s = 12;

    /* renamed from: t */
    private static final int f23186t = 13;

    /* renamed from: u */
    private static final int f23187u = 14;

    /* renamed from: v */
    private static final int f23188v = 15;

    /* renamed from: w */
    private static final int f23189w = 16;

    /* renamed from: x */
    private static final int f23190x = 17;

    /* renamed from: y */
    private static final int f23191y = 18;

    /* renamed from: z */
    private static final int f23192z = 19;

    /* renamed from: a */
    public static <T> DatabaseTableConfig<T> m1132a(ConnectionSource wmVar, Class<T> cls) throws SQLException {
        DatabaseType e = wmVar.mo249e();
        String b = DatabaseTableConfig.m187b(cls);
        ArrayList arrayList = new ArrayList();
        for (Class<T> cls2 = cls; cls2 != null; cls2 = cls2.getSuperclass()) {
            for (Field field : cls2.getDeclaredFields()) {
                DatabaseFieldConfig a = m1134a(e, b, field);
                if (a != null && a.m764p()) {
                    arrayList.add(a);
                }
            }
        }
        if (arrayList.size() == 0) {
            return null;
        }
        return new DatabaseTableConfig<>(cls, b, arrayList);
    }

    /* renamed from: a */
    public static int m1137a() {
        return f23172f;
    }

    /* renamed from: b */
    private static int[] m1131b() {
        try {
            f23167a = Class.forName("org.apache.harmony.lang.annotation.AnnotationFactory");
            f23169c = Class.forName("org.apache.harmony.lang.annotation.AnnotationMember");
            Class<?> cls = Class.forName("[Lorg.apache.harmony.lang.annotation.AnnotationMember;");
            f23169c = Class.forName("org.apache.harmony.lang.annotation.AnnotationMember");
            try {
                f23168b = f23167a.getDeclaredField("elements");
                f23168b.setAccessible(true);
                f23170d = f23169c.getDeclaredField("name");
                f23170d.setAccessible(true);
                f23171e = f23169c.getDeclaredField(SizeSelector.SIZE_KEY);
                f23171e.setAccessible(true);
                InvocationHandler invocationHandler = Proxy.getInvocationHandler((DatabaseField) C5544a.class.getDeclaredField("a").getAnnotation(DatabaseField.class));
                if (invocationHandler.getClass() != f23167a) {
                    return null;
                }
                try {
                    Object obj = f23168b.get(invocationHandler);
                    if (obj != null && obj.getClass() == cls) {
                        Object[] objArr = (Object[]) obj;
                        int[] iArr = new int[objArr.length];
                        for (int i = 0; i < objArr.length; i++) {
                            iArr[i] = m1135a((String) f23170d.get(objArr[i]));
                        }
                        return iArr;
                    }
                    return null;
                } catch (IllegalAccessException unused) {
                    return null;
                }
            } catch (NoSuchFieldException unused2) {
                return null;
            } catch (SecurityException unused3) {
                return null;
            }
        } catch (ClassNotFoundException unused4) {
            return null;
        }
    }

    /* renamed from: a */
    private static int m1135a(String str) {
        if (str.equals("columnName")) {
            return 1;
        }
        if (str.equals("dataType")) {
            return 2;
        }
        if (str.equals("defaultValue")) {
            return 3;
        }
        if (str.equals("width")) {
            return 4;
        }
        if (str.equals("canBeNull")) {
            return 5;
        }
        if (str.equals(ConnectionModel.f10389a)) {
            return 6;
        }
        if (str.equals("generatedId")) {
            return 7;
        }
        if (str.equals("generatedIdSequence")) {
            return 8;
        }
        if (str.equals("foreign")) {
            return 9;
        }
        if (str.equals("useGetSet")) {
            return 10;
        }
        if (str.equals("unknownEnumName")) {
            return 11;
        }
        if (str.equals("throwIfNull")) {
            return 12;
        }
        if (str.equals("persisted")) {
            return 13;
        }
        if (str.equals("format")) {
            return 14;
        }
        if (str.equals("unique")) {
            return 15;
        }
        if (str.equals("uniqueCombo")) {
            return 16;
        }
        if (str.equals("index")) {
            return 17;
        }
        if (str.equals("uniqueIndex")) {
            return 18;
        }
        if (str.equals("indexName")) {
            return 19;
        }
        if (str.equals("uniqueIndexName")) {
            return 20;
        }
        if (str.equals("foreignAutoRefresh")) {
            return 21;
        }
        if (str.equals("maxForeignAutoRefreshLevel")) {
            return 22;
        }
        if (str.equals("persisterClass")) {
            return 23;
        }
        if (str.equals("allowGeneratedIdInsert")) {
            return 24;
        }
        if (str.equals("columnDefinition")) {
            return 25;
        }
        if (str.equals("foreignAutoCreate")) {
            return 26;
        }
        if (str.equals(ClientCookie.VERSION_ATTR)) {
            return 27;
        }
        if (str.equals("foreignColumnName")) {
            return 28;
        }
        if (str.equals("readOnly")) {
            return 29;
        }
        throw new IllegalStateException("Could not find support for DatabaseField " + str);
    }

    /* renamed from: a */
    private static DatabaseFieldConfig m1134a(DatabaseType siVar, String str, Field field) throws SQLException {
        if (f23173g == null) {
            return DatabaseFieldConfig.m819a(siVar, str, field);
        }
        DatabaseField soVar = (DatabaseField) field.getAnnotation(DatabaseField.class);
        DatabaseFieldConfig spVar = null;
        if (soVar != null) {
            try {
                spVar = m1133a(soVar, str, field);
            } catch (Exception unused) {
            }
        }
        if (spVar == null) {
            return DatabaseFieldConfig.m819a(siVar, str, field);
        }
        f23172f++;
        return spVar;
    }

    /* renamed from: a */
    private static DatabaseFieldConfig m1133a(DatabaseField soVar, String str, Field field) throws Exception {
        Object obj;
        InvocationHandler invocationHandler = Proxy.getInvocationHandler(soVar);
        if (invocationHandler.getClass() != f23167a || (obj = f23168b.get(invocationHandler)) == null) {
            return null;
        }
        DatabaseFieldConfig spVar = new DatabaseFieldConfig(field.getName());
        Object[] objArr = (Object[]) obj;
        for (int i = 0; i < f23173g.length; i++) {
            Object obj2 = f23171e.get(objArr[i]);
            if (obj2 != null) {
                m1136a(f23173g[i], spVar, field, obj2);
            }
        }
        return spVar;
    }

    /* renamed from: a */
    private static void m1136a(int i, DatabaseFieldConfig spVar, Field field, Object obj) {
        switch (i) {
            case 1:
                spVar.m810b(m1130b((String) obj));
                return;
            case 2:
                spVar.m815a((DataType) obj);
                return;
            case 3:
                String str = (String) obj;
                if (str != null && !str.equals(DatabaseField.f23248a)) {
                    spVar.m804c(str);
                    return;
                }
                return;
            case 4:
                spVar.m825a(((Integer) obj).intValue());
                return;
            case 5:
                spVar.m813a(((Boolean) obj).booleanValue());
                return;
            case 6:
                spVar.m807b(((Boolean) obj).booleanValue());
                return;
            case 7:
                spVar.m803c(((Boolean) obj).booleanValue());
                return;
            case 8:
                spVar.m800d(m1130b((String) obj));
                return;
            case 9:
                spVar.m799d(((Boolean) obj).booleanValue());
                return;
            case 10:
                spVar.m795e(((Boolean) obj).booleanValue());
                return;
            case 11:
                spVar.m823a(DatabaseFieldConfig.m821a(field, (String) obj));
                return;
            case 12:
                spVar.m792f(((Boolean) obj).booleanValue());
                return;
            case 13:
                spVar.m789g(((Boolean) obj).booleanValue());
                return;
            case 14:
                spVar.m796e(m1130b((String) obj));
                return;
            case 15:
                spVar.m786h(((Boolean) obj).booleanValue());
                return;
            case 16:
                spVar.m783i(((Boolean) obj).booleanValue());
                return;
            case 17:
                spVar.m780j(((Boolean) obj).booleanValue());
                return;
            case 18:
                spVar.m777k(((Boolean) obj).booleanValue());
                return;
            case 19:
                spVar.m790g(m1130b((String) obj));
                return;
            case 20:
                spVar.m784i(m1130b((String) obj));
                return;
            case 21:
                spVar.m774l(((Boolean) obj).booleanValue());
                return;
            case 22:
                spVar.m811b(((Integer) obj).intValue());
                return;
            case 23:
                spVar.m824a((Class) obj);
                return;
            case 24:
                spVar.m762p(((Boolean) obj).booleanValue());
                return;
            case 25:
                spVar.m766o(m1130b((String) obj));
                return;
            case 26:
                spVar.m759q(((Boolean) obj).booleanValue());
                return;
            case 27:
                spVar.m756r(((Boolean) obj).booleanValue());
                return;
            case 28:
                spVar.m763p(m1130b((String) obj));
                return;
            case 29:
                spVar.m754s(((Boolean) obj).booleanValue());
                return;
            default:
                throw new IllegalStateException("Could not find support for DatabaseField number " + i);
        }
    }

    /* renamed from: b */
    private static String m1130b(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        return str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: DatabaseTableConfigUtil.java */
    /* renamed from: z1.ro$a */
    /* loaded from: classes3.dex */
    public static class C5544a {
        @DatabaseField

        /* renamed from: a */
        String f23193a;

        private C5544a() {
        }
    }
}
