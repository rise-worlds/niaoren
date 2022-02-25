package p110z1;

import java.sql.SQLException;
import java.util.List;
import p110z1.Log;

/* renamed from: z1.vl */
/* loaded from: classes3.dex */
public class MappedCreate<T, ID> extends BaseMappedStatement<T, ID> {

    /* renamed from: a */
    private final String f23574a;

    /* renamed from: h */
    private String f23575h;

    /* renamed from: i */
    private int f23576i;

    private MappedCreate(TableInfo<T, ID> wuVar, String str, FieldType[] ssVarArr, String str2, int i) {
        super(wuVar, str, ssVarArr);
        this.f23575h = wuVar.m175a().getSimpleName();
        this.f23574a = str2;
        this.f23576i = i;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0064 A[Catch: SQLException -> 0x013c, TryCatch #0 {SQLException -> 0x013c, blocks: (B:27:0x005c, B:29:0x0064, B:31:0x006e, B:34:0x0077, B:36:0x007d, B:38:0x0087, B:39:0x008a, B:40:0x008d, B:42:0x0095, B:44:0x009b, B:47:0x00b7, B:49:0x00cc, B:52:0x00d7, B:54:0x00e2, B:56:0x00e8, B:58:0x00f2, B:59:0x00f8, B:60:0x00ff, B:61:0x0100, B:62:0x0107, B:64:0x010a, B:66:0x0116, B:69:0x0123, B:71:0x0134, B:72:0x013b, B:46:0x00af), top: B:76:0x005c, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00cc A[Catch: SQLException -> 0x013c, TryCatch #0 {SQLException -> 0x013c, blocks: (B:27:0x005c, B:29:0x0064, B:31:0x006e, B:34:0x0077, B:36:0x007d, B:38:0x0087, B:39:0x008a, B:40:0x008d, B:42:0x0095, B:44:0x009b, B:47:0x00b7, B:49:0x00cc, B:52:0x00d7, B:54:0x00e2, B:56:0x00e8, B:58:0x00f2, B:59:0x00f8, B:60:0x00ff, B:61:0x0100, B:62:0x0107, B:64:0x010a, B:66:0x0116, B:69:0x0123, B:71:0x0134, B:72:0x013b, B:46:0x00af), top: B:76:0x005c, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00d5  */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int m314a(p110z1.DatabaseType r10, p110z1.DatabaseConnection r11, T r12, p110z1.ObjectCache r13) throws java.sql.SQLException {
        /*
            Method dump skipped, instructions count: 349
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p110z1.MappedCreate.m314a(z1.si, z1.wn, java.lang.Object, z1.sc):int");
    }

    /* renamed from: a */
    public static <T, ID> MappedCreate<T, ID> m313a(DatabaseType siVar, TableInfo<T, ID> wuVar) {
        FieldType[] c;
        FieldType[] c2;
        StringBuilder sb = new StringBuilder(128);
        m320a(siVar, sb, "INSERT INTO ", wuVar.m171b());
        sb.append('(');
        int i = 0;
        int i2 = -1;
        for (FieldType ssVar : wuVar.m169c()) {
            if (m315a(siVar, ssVar)) {
                if (ssVar.m736G()) {
                    i2 = i;
                }
                i++;
            }
        }
        FieldType[] ssVarArr = new FieldType[i];
        boolean z = true;
        int i3 = 0;
        for (FieldType ssVar2 : wuVar.m169c()) {
            if (m315a(siVar, ssVar2)) {
                if (z) {
                    z = false;
                } else {
                    sb.append(",");
                }
                m319a(siVar, sb, ssVar2, (List<FieldType>) null);
                i3++;
                ssVarArr[i3] = ssVar2;
            }
        }
        sb.append(") VALUES (");
        boolean z2 = true;
        for (FieldType ssVar3 : wuVar.m169c()) {
            if (m315a(siVar, ssVar3)) {
                if (z2) {
                    z2 = false;
                } else {
                    sb.append(",");
                }
                sb.append("?");
            }
        }
        sb.append(")");
        return new MappedCreate<>(wuVar, sb.toString(), ssVarArr, m310b(siVar, wuVar.m168d()), i2);
    }

    /* renamed from: a */
    private boolean m311a(FieldType[] ssVarArr, Object obj) throws SQLException {
        for (FieldType ssVar : ssVarArr) {
            if (ssVar.m720b(obj) == null) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: a */
    private static boolean m315a(DatabaseType siVar, FieldType ssVar) {
        if (!ssVar.m741B() && !ssVar.m734I()) {
            return (siVar.mo895e() && siVar.mo882t()) || !ssVar.m702m() || ssVar.m740C() || ssVar.m739D();
        }
        return false;
    }

    /* renamed from: b */
    private static String m310b(DatabaseType siVar, FieldType ssVar) {
        String o;
        if (ssVar == null || (o = ssVar.m700o()) == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder(64);
        siVar.mo897c(sb, o);
        return sb.toString();
    }

    /* renamed from: a */
    private void m312a(DatabaseConnection wnVar, T t, ObjectCache scVar) throws SQLException {
        long b = wnVar.mo235b(this.f23574a);
        f23568b.m594b("queried for sequence {} using stmt: {}", Long.valueOf(b), this.f23574a);
        if (b != 0) {
            m316a((MappedCreate<T, ID>) t, Long.valueOf(b), "sequence", scVar);
            return;
        }
        throw new SQLException("Should not have returned 0 for stmt: " + this.f23574a);
    }

    /* renamed from: a */
    private void m316a(T t, Number number, String str, ObjectCache scVar) throws SQLException {
        this.f23571e.m729a(t, number, scVar);
        if (f23568b.m608a(Log.EnumC5569a.DEBUG)) {
            f23568b.m592b("assigned id '{}' from {} to '{}' in {} object", new Object[]{number, str, this.f23571e.m719c(), this.f23575h});
        }
    }

    /* compiled from: MappedCreate.java */
    /* renamed from: z1.vl$a */
    /* loaded from: classes3.dex */
    private static class C5584a implements GeneratedKeyHolder {

        /* renamed from: a */
        Number f23577a;

        private C5584a() {
        }

        /* renamed from: a */
        public Number m309a() {
            return this.f23577a;
        }

        @Override // p110z1.GeneratedKeyHolder
        /* renamed from: a */
        public void mo201a(Number number) throws SQLException {
            if (this.f23577a == null) {
                this.f23577a = number;
                return;
            }
            throw new SQLException("generated key has already been set to " + this.f23577a + ", now set to " + number);
        }
    }
}
