package p110z1;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.SQLException;

/* renamed from: z1.sp */
/* loaded from: classes3.dex */
public class DatabaseFieldConfig {

    /* renamed from: a */
    public static final Class<? extends DataPersister> f23251a = VoidType.class;

    /* renamed from: b */
    public static final DataType f23252b = DataType.UNKNOWN;

    /* renamed from: c */
    public static final boolean f23253c = true;

    /* renamed from: d */
    public static final boolean f23254d = true;

    /* renamed from: e */
    private static final int f23255e = 1;

    /* renamed from: A */
    private boolean f23256A;

    /* renamed from: B */
    private String f23257B;

    /* renamed from: C */
    private boolean f23258C;

    /* renamed from: D */
    private int f23259D;

    /* renamed from: E */
    private Class<? extends DataPersister> f23260E;

    /* renamed from: F */
    private boolean f23261F;

    /* renamed from: G */
    private String f23262G;

    /* renamed from: H */
    private boolean f23263H;

    /* renamed from: I */
    private boolean f23264I;

    /* renamed from: J */
    private String f23265J;

    /* renamed from: K */
    private boolean f23266K;

    /* renamed from: L */
    private boolean f23267L;

    /* renamed from: M */
    private boolean f23268M;

    /* renamed from: N */
    private int f23269N;

    /* renamed from: O */
    private String f23270O;

    /* renamed from: P */
    private String f23271P;

    /* renamed from: Q */
    private boolean f23272Q;

    /* renamed from: R */
    private String f23273R;

    /* renamed from: f */
    private String f23274f;

    /* renamed from: g */
    private String f23275g;

    /* renamed from: h */
    private DataType f23276h;

    /* renamed from: i */
    private DataPersister f23277i;

    /* renamed from: j */
    private String f23278j;

    /* renamed from: k */
    private int f23279k;

    /* renamed from: l */
    private boolean f23280l;

    /* renamed from: m */
    private boolean f23281m;

    /* renamed from: n */
    private boolean f23282n;

    /* renamed from: o */
    private String f23283o;

    /* renamed from: p */
    private boolean f23284p;

    /* renamed from: q */
    private DatabaseTableConfig<?> f23285q;

    /* renamed from: r */
    private boolean f23286r;

    /* renamed from: s */
    private Enum<?> f23287s;

    /* renamed from: t */
    private boolean f23288t;

    /* renamed from: u */
    private boolean f23289u;

    /* renamed from: v */
    private String f23290v;

    /* renamed from: w */
    private boolean f23291w;

    /* renamed from: x */
    private boolean f23292x;

    /* renamed from: y */
    private boolean f23293y;

    /* renamed from: z */
    private String f23294z;

    public DatabaseFieldConfig() {
        this.f23276h = f23252b;
        this.f23280l = true;
        this.f23289u = true;
        this.f23259D = -1;
        this.f23260E = f23251a;
        this.f23269N = 1;
        this.f23272Q = true;
    }

    public DatabaseFieldConfig(String str) {
        this.f23276h = f23252b;
        this.f23280l = true;
        this.f23289u = true;
        this.f23259D = -1;
        this.f23260E = f23251a;
        this.f23269N = 1;
        this.f23272Q = true;
        this.f23274f = str;
    }

    public DatabaseFieldConfig(String str, String str2, DataType snVar, String str3, int i, boolean z, boolean z2, boolean z3, String str4, boolean z4, DatabaseTableConfig<?> wrVar, boolean z5, Enum<?> r16, boolean z6, String str5, boolean z7, String str6, String str7, boolean z8, int i2, int i3) {
        this.f23276h = f23252b;
        this.f23280l = true;
        this.f23289u = true;
        this.f23259D = -1;
        this.f23260E = f23251a;
        this.f23269N = 1;
        this.f23272Q = true;
        this.f23274f = str;
        this.f23275g = str2;
        this.f23276h = DataType.UNKNOWN;
        this.f23278j = str3;
        this.f23279k = i;
        this.f23280l = z;
        this.f23281m = z2;
        this.f23282n = z3;
        this.f23283o = str4;
        this.f23284p = z4;
        this.f23285q = wrVar;
        this.f23286r = z5;
        this.f23287s = r16;
        this.f23288t = z6;
        this.f23290v = str5;
        this.f23291w = z7;
        this.f23294z = str6;
        this.f23257B = str7;
        this.f23258C = z8;
        this.f23259D = i2;
        this.f23269N = i3;
    }

    /* renamed from: a */
    public String m826a() {
        return this.f23274f;
    }

    /* renamed from: a */
    public void m822a(String str) {
        this.f23274f = str;
    }

    /* renamed from: b */
    public String m812b() {
        return this.f23275g;
    }

    /* renamed from: b */
    public void m810b(String str) {
        this.f23275g = str;
    }

    /* renamed from: c */
    public DataType m806c() {
        return this.f23276h;
    }

    /* renamed from: a */
    public void m815a(DataType snVar) {
        this.f23276h = snVar;
    }

    /* renamed from: d */
    public DataPersister m802d() {
        DataPersister slVar = this.f23277i;
        return slVar == null ? this.f23276h.getDataPersister() : slVar;
    }

    /* renamed from: a */
    public void m816a(DataPersister slVar) {
        this.f23277i = slVar;
    }

    /* renamed from: e */
    public String m798e() {
        return this.f23278j;
    }

    /* renamed from: c */
    public void m804c(String str) {
        this.f23278j = str;
    }

    /* renamed from: f */
    public int m794f() {
        return this.f23279k;
    }

    /* renamed from: a */
    public void m825a(int i) {
        this.f23279k = i;
    }

    /* renamed from: g */
    public boolean m791g() {
        return this.f23280l;
    }

    /* renamed from: a */
    public void m813a(boolean z) {
        this.f23280l = z;
    }

    /* renamed from: h */
    public boolean m788h() {
        return this.f23281m;
    }

    /* renamed from: b */
    public void m807b(boolean z) {
        this.f23281m = z;
    }

    /* renamed from: i */
    public boolean m785i() {
        return this.f23282n;
    }

    /* renamed from: c */
    public void m803c(boolean z) {
        this.f23282n = z;
    }

    /* renamed from: j */
    public String m782j() {
        return this.f23283o;
    }

    /* renamed from: d */
    public void m800d(String str) {
        this.f23283o = str;
    }

    /* renamed from: k */
    public boolean m779k() {
        return this.f23284p;
    }

    /* renamed from: d */
    public void m799d(boolean z) {
        this.f23284p = z;
    }

    /* renamed from: l */
    public DatabaseTableConfig<?> m776l() {
        return this.f23285q;
    }

    /* renamed from: a */
    public void m814a(DatabaseTableConfig<?> wrVar) {
        this.f23285q = wrVar;
    }

    /* renamed from: m */
    public boolean m773m() {
        return this.f23286r;
    }

    /* renamed from: e */
    public void m795e(boolean z) {
        this.f23286r = z;
    }

    /* renamed from: n */
    public Enum<?> m770n() {
        return this.f23287s;
    }

    /* renamed from: a */
    public void m823a(Enum<?> r1) {
        this.f23287s = r1;
    }

    /* renamed from: o */
    public boolean m767o() {
        return this.f23288t;
    }

    /* renamed from: f */
    public void m792f(boolean z) {
        this.f23288t = z;
    }

    /* renamed from: p */
    public boolean m764p() {
        return this.f23289u;
    }

    /* renamed from: g */
    public void m789g(boolean z) {
        this.f23289u = z;
    }

    /* renamed from: q */
    public String m761q() {
        return this.f23290v;
    }

    /* renamed from: e */
    public void m796e(String str) {
        this.f23290v = str;
    }

    /* renamed from: r */
    public boolean m758r() {
        return this.f23291w;
    }

    /* renamed from: h */
    public void m786h(boolean z) {
        this.f23291w = z;
    }

    /* renamed from: s */
    public boolean m755s() {
        return this.f23292x;
    }

    /* renamed from: i */
    public void m783i(boolean z) {
        this.f23292x = z;
    }

    /* renamed from: t */
    public boolean m753t() {
        return this.f23293y;
    }

    /* renamed from: j */
    public void m780j(boolean z) {
        this.f23293y = z;
    }

    /* renamed from: f */
    public String m793f(String str) {
        if (this.f23293y && this.f23294z == null) {
            this.f23294z = m760q(str);
        }
        return this.f23294z;
    }

    /* renamed from: g */
    public void m790g(String str) {
        this.f23294z = str;
    }

    /* renamed from: u */
    public boolean m752u() {
        return this.f23256A;
    }

    /* renamed from: k */
    public void m777k(boolean z) {
        this.f23256A = z;
    }

    /* renamed from: h */
    public String m787h(String str) {
        if (this.f23256A && this.f23257B == null) {
            this.f23257B = m760q(str);
        }
        return this.f23257B;
    }

    /* renamed from: i */
    public void m784i(String str) {
        this.f23257B = str;
    }

    /* renamed from: l */
    public void m774l(boolean z) {
        this.f23258C = z;
    }

    /* renamed from: v */
    public boolean m751v() {
        return this.f23258C;
    }

    /* renamed from: w */
    public int m750w() {
        return this.f23259D;
    }

    /* renamed from: b */
    public void m811b(int i) {
        this.f23259D = i;
    }

    /* renamed from: x */
    public boolean m749x() {
        return this.f23267L;
    }

    /* renamed from: m */
    public void m771m(boolean z) {
        this.f23267L = z;
    }

    /* renamed from: y */
    public boolean m748y() {
        return this.f23268M;
    }

    /* renamed from: n */
    public void m768n(boolean z) {
        this.f23268M = z;
    }

    /* renamed from: z */
    public int m747z() {
        return this.f23269N;
    }

    /* renamed from: c */
    public void m805c(int i) {
        this.f23269N = i;
    }

    @Deprecated
    /* renamed from: d */
    public void m801d(int i) {
        this.f23269N = i;
    }

    @Deprecated
    /* renamed from: e */
    public void m797e(int i) {
        this.f23269N = i;
    }

    /* renamed from: A */
    public String m838A() {
        return this.f23270O;
    }

    /* renamed from: j */
    public void m781j(String str) {
        this.f23270O = str;
    }

    /* renamed from: B */
    public String m837B() {
        return this.f23271P;
    }

    @Deprecated
    /* renamed from: k */
    public void m778k(String str) {
        this.f23271P = str;
    }

    /* renamed from: l */
    public void m775l(String str) {
        this.f23271P = str;
    }

    /* renamed from: C */
    public boolean m836C() {
        return this.f23272Q;
    }

    /* renamed from: o */
    public void m765o(boolean z) {
        this.f23272Q = z;
    }

    /* renamed from: D */
    public String m835D() {
        return this.f23273R;
    }

    @Deprecated
    /* renamed from: m */
    public void m772m(String str) {
        this.f23273R = str;
    }

    /* renamed from: n */
    public void m769n(String str) {
        this.f23273R = str;
    }

    /* renamed from: E */
    public Class<? extends DataPersister> m834E() {
        return this.f23260E;
    }

    /* renamed from: a */
    public void m824a(Class<? extends DataPersister> cls) {
        this.f23260E = cls;
    }

    /* renamed from: F */
    public boolean m833F() {
        return this.f23261F;
    }

    /* renamed from: p */
    public void m762p(boolean z) {
        this.f23261F = z;
    }

    /* renamed from: G */
    public String m832G() {
        return this.f23262G;
    }

    /* renamed from: o */
    public void m766o(String str) {
        this.f23262G = str;
    }

    /* renamed from: H */
    public boolean m831H() {
        return this.f23263H;
    }

    /* renamed from: q */
    public void m759q(boolean z) {
        this.f23263H = z;
    }

    /* renamed from: I */
    public boolean m830I() {
        return this.f23264I;
    }

    /* renamed from: r */
    public void m756r(boolean z) {
        this.f23264I = z;
    }

    /* renamed from: J */
    public String m829J() {
        return this.f23265J;
    }

    /* renamed from: p */
    public void m763p(String str) {
        this.f23265J = str;
    }

    /* renamed from: K */
    public boolean m828K() {
        return this.f23266K;
    }

    /* renamed from: s */
    public void m754s(boolean z) {
        this.f23266K = z;
    }

    /* renamed from: a */
    public static DatabaseFieldConfig m819a(DatabaseType siVar, String str, Field field) throws SQLException {
        DatabaseField soVar = (DatabaseField) field.getAnnotation(DatabaseField.class);
        if (soVar == null) {
            ForeignCollectionField stVar = (ForeignCollectionField) field.getAnnotation(ForeignCollectionField.class);
            if (stVar != null) {
                return m817a(siVar, field, stVar);
            }
            return JavaxPersistence.m530a(siVar, field);
        } else if (soVar.m852m()) {
            return m818a(siVar, str, field, soVar);
        } else {
            return null;
        }
    }

    /* renamed from: a */
    public static Method m820a(Field field, boolean z) {
        String b = m809b(field, "get");
        try {
            Method method = field.getDeclaringClass().getMethod(b, new Class[0]);
            if (method.getReturnType() == field.getType()) {
                return method;
            }
            if (!z) {
                return null;
            }
            throw new IllegalArgumentException("Return type of get method " + b + " does not return " + field.getType());
        } catch (Exception unused) {
            if (!z) {
                return null;
            }
            throw new IllegalArgumentException("Could not find appropriate get method for " + field);
        }
    }

    /* renamed from: b */
    public static Method m808b(Field field, boolean z) {
        String b = m809b(field, "set");
        try {
            Method method = field.getDeclaringClass().getMethod(b, field.getType());
            if (method.getReturnType() == Void.TYPE) {
                return method;
            }
            if (!z) {
                return null;
            }
            throw new IllegalArgumentException("Return type of set method " + b + " returns " + method.getReturnType() + " instead of void");
        } catch (Exception unused) {
            if (!z) {
                return null;
            }
            throw new IllegalArgumentException("Could not find appropriate set method for " + field);
        }
    }

    /* renamed from: a */
    public static DatabaseFieldConfig m818a(DatabaseType siVar, String str, Field field, DatabaseField soVar) {
        DatabaseFieldConfig spVar = new DatabaseFieldConfig();
        spVar.f23274f = field.getName();
        if (siVar.mo887m()) {
            spVar.f23274f = spVar.f23274f.toUpperCase();
        }
        spVar.f23275g = m757r(soVar.m864a());
        spVar.f23276h = soVar.m863b();
        String c = soVar.m862c();
        if (!c.equals(DatabaseField.f23248a)) {
            spVar.f23278j = c;
        }
        spVar.f23279k = soVar.m861d();
        spVar.f23280l = soVar.m860e();
        spVar.f23281m = soVar.m859f();
        spVar.f23282n = soVar.m858g();
        spVar.f23283o = m757r(soVar.m857h());
        spVar.f23284p = soVar.m856i();
        spVar.f23286r = soVar.m855j();
        spVar.f23287s = m821a(field, soVar.m854k());
        spVar.f23288t = soVar.m853l();
        spVar.f23290v = m757r(soVar.m851n());
        spVar.f23291w = soVar.m850o();
        spVar.f23292x = soVar.m849p();
        spVar.f23293y = soVar.m848q();
        spVar.f23294z = m757r(soVar.m846s());
        spVar.f23256A = soVar.m847r();
        spVar.f23257B = m757r(soVar.m845t());
        spVar.f23258C = soVar.m844u();
        spVar.f23259D = soVar.m843v();
        spVar.f23260E = soVar.m842w();
        spVar.f23261F = soVar.m841x();
        spVar.f23262G = m757r(soVar.m840y());
        spVar.f23263H = soVar.m839z();
        spVar.f23264I = soVar.m867A();
        spVar.f23265J = m757r(soVar.m866B());
        spVar.f23266K = soVar.m865C();
        return spVar;
    }

    /* renamed from: L */
    public void m827L() {
        if (this.f23265J != null) {
            this.f23258C = true;
        }
        if (this.f23258C && this.f23259D == -1) {
            this.f23259D = 2;
        }
    }

    /* renamed from: a */
    public static Enum<?> m821a(Field field, String str) {
        Enum<?>[] enumArr;
        if (str == null || str.length() == 0) {
            return null;
        }
        for (Enum<?> r3 : (Enum[]) field.getType().getEnumConstants()) {
            if (r3.name().equals(str)) {
                return r3;
            }
        }
        throw new IllegalArgumentException("Unknwown enum unknown name " + str + " for field " + field);
    }

    /* renamed from: a */
    private static DatabaseFieldConfig m817a(DatabaseType siVar, Field field, ForeignCollectionField stVar) {
        DatabaseFieldConfig spVar = new DatabaseFieldConfig();
        spVar.f23274f = field.getName();
        if (stVar.m684d().length() > 0) {
            spVar.f23275g = stVar.m684d();
        }
        spVar.f23267L = true;
        spVar.f23268M = stVar.m687a();
        int b = stVar.m686b();
        if (b != 1) {
            spVar.f23269N = b;
        } else {
            spVar.f23269N = stVar.m685c();
        }
        spVar.f23271P = m757r(stVar.m683e());
        spVar.f23272Q = stVar.m682f();
        spVar.f23270O = m757r(stVar.m684d());
        String r = m757r(stVar.m680h());
        if (r == null) {
            spVar.f23273R = m757r(m757r(stVar.m681g()));
        } else {
            spVar.f23273R = r;
        }
        return spVar;
    }

    /* renamed from: q */
    private String m760q(String str) {
        if (this.f23275g == null) {
            return str + "_" + this.f23274f + "_idx";
        }
        return str + "_" + this.f23275g + "_idx";
    }

    /* renamed from: r */
    private static String m757r(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        return str;
    }

    /* renamed from: b */
    private static String m809b(Field field, String str) {
        return str + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
    }
}
