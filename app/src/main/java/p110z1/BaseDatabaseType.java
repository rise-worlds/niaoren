package p110z1;

import java.sql.Driver;
import java.sql.SQLException;
import java.util.List;

/* renamed from: z1.sg */
/* loaded from: classes3.dex */
public abstract class BaseDatabaseType implements DatabaseType {

    /* renamed from: a */
    protected static String f23239a = "_id_seq";

    /* renamed from: b */
    protected Driver f23240b;

    /* renamed from: a */
    private void m927a(StringBuilder sb, FieldType ssVar) {
    }

    /* renamed from: a */
    protected abstract String mo880a();

    @Override // p110z1.DatabaseType
    /* renamed from: a */
    public FieldConverter mo878a(DataPersister slVar) {
        return slVar;
    }

    @Override // p110z1.DatabaseType
    /* renamed from: a */
    public <T> DatabaseTableConfig<T> mo877a(ConnectionSource wmVar, Class<T> cls) throws SQLException {
        return null;
    }

    @Override // p110z1.DatabaseType
    /* renamed from: a */
    public void mo906a(StringBuilder sb) {
    }

    @Override // p110z1.DatabaseType
    /* renamed from: a */
    public void mo901a(FieldType ssVar, List<String> list, List<String> list2) {
    }

    /* renamed from: b */
    protected void m921b(StringBuilder sb, FieldType ssVar, List<String> list, List<String> list2, List<String> list3) {
    }

    @Override // p110z1.DatabaseType
    /* renamed from: c */
    public void mo897c(StringBuilder sb, String str) {
    }

    /* renamed from: c */
    protected boolean mo910c() {
        return true;
    }

    @Override // p110z1.DatabaseType
    /* renamed from: d */
    public String mo896d() {
        return "-- ";
    }

    @Override // p110z1.DatabaseType
    /* renamed from: e */
    public boolean mo895e() {
        return false;
    }

    @Override // p110z1.DatabaseType
    /* renamed from: f */
    public boolean mo894f() {
        return true;
    }

    @Override // p110z1.DatabaseType
    /* renamed from: g */
    public boolean mo893g() {
        return true;
    }

    @Override // p110z1.DatabaseType
    /* renamed from: h */
    public boolean mo892h() {
        return true;
    }

    @Override // p110z1.DatabaseType
    /* renamed from: i */
    public boolean mo891i() {
        return false;
    }

    @Override // p110z1.DatabaseType
    /* renamed from: j */
    public boolean mo890j() {
        return false;
    }

    @Override // p110z1.DatabaseType
    /* renamed from: k */
    public boolean mo889k() {
        return true;
    }

    @Override // p110z1.DatabaseType
    /* renamed from: l */
    public boolean mo888l() {
        return false;
    }

    @Override // p110z1.DatabaseType
    /* renamed from: m */
    public boolean mo887m() {
        return false;
    }

    @Override // p110z1.DatabaseType
    /* renamed from: n */
    public boolean mo873n() {
        return true;
    }

    @Override // p110z1.DatabaseType
    /* renamed from: o */
    public String mo886o() {
        return "SELECT 1";
    }

    @Override // p110z1.DatabaseType
    /* renamed from: p */
    public boolean mo872p() {
        return false;
    }

    @Override // p110z1.DatabaseType
    /* renamed from: q */
    public boolean mo885q() {
        return false;
    }

    @Override // p110z1.DatabaseType
    /* renamed from: r */
    public boolean mo884r() {
        return false;
    }

    @Override // p110z1.DatabaseType
    /* renamed from: t */
    public boolean mo882t() {
        return false;
    }

    @Override // p110z1.DatabaseType
    /* renamed from: u */
    public boolean mo881u() {
        return true;
    }

    @Override // p110z1.DatabaseType
    /* renamed from: b */
    public void mo876b() throws SQLException {
        String a = mo880a();
        if (a != null) {
            try {
                Class.forName(a);
            } catch (ClassNotFoundException e) {
                throw SqlExceptionUtil.m529a("Driver class was not found for " + mo871v() + " database.  Missing jar with class " + a + Consts.f23430h, e);
            }
        }
    }

    @Override // p110z1.DatabaseType
    /* renamed from: a */
    public void mo902a(Driver driver) {
        this.f23240b = driver;
    }

    @Override // p110z1.DatabaseType
    /* renamed from: a */
    public void mo908a(String str, StringBuilder sb, FieldType ssVar, List<String> list, List<String> list2, List<String> list3, List<String> list4) throws SQLException {
        mo899b(sb, ssVar.m715e());
        sb.append(' ');
        DataPersister f = ssVar.m713f();
        int j = ssVar.m705j();
        if (j == 0) {
            j = f.getDefaultWidth();
        }
        switch (f.getSqlType()) {
            case STRING:
                m926a(sb, ssVar, j);
                break;
            case LONG_STRING:
                m922b(sb, ssVar, j);
                break;
            case BOOLEAN:
                mo874d(sb, ssVar, j);
                break;
            case DATE:
                mo875c(sb, ssVar, j);
                break;
            case CHAR:
                m920e(sb, ssVar, j);
                break;
            case BYTE:
                m919f(sb, ssVar, j);
                break;
            case BYTE_ARRAY:
                m917i(sb, ssVar, j);
                break;
            case SHORT:
                m918g(sb, ssVar, j);
                break;
            case INTEGER:
                m914l(sb, ssVar, j);
                break;
            case LONG:
                mo909h(sb, ssVar, j);
                break;
            case FLOAT:
                m913m(sb, ssVar, j);
                break;
            case DOUBLE:
                m912n(sb, ssVar, j);
                break;
            case SERIALIZABLE:
                m916j(sb, ssVar, j);
                break;
            case BIG_DECIMAL:
                m915k(sb, ssVar, j);
                break;
            default:
                throw new IllegalArgumentException("Unknown SQL-type " + f.getSqlType());
        }
        sb.append(' ');
        if (ssVar.m701n() && !ssVar.m740C()) {
            m923a(sb, ssVar, list2, list, list4);
        } else if (ssVar.m702m() && !ssVar.m740C()) {
            mo911b(str, sb, ssVar, list2, list3, list, list4);
        } else if (ssVar.m703l()) {
            m921b(sb, ssVar, list2, list, list4);
        }
        if (!ssVar.m702m()) {
            Object i = ssVar.m707i();
            if (i != null) {
                sb.append("DEFAULT ");
                m925a(sb, ssVar, i);
                sb.append(' ');
            }
            if (ssVar.m704k()) {
                m927a(sb, ssVar);
            } else {
                sb.append("NOT NULL ");
            }
            if (ssVar.m694u()) {
                m924a(sb, ssVar, list, list3);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public void m926a(StringBuilder sb, FieldType ssVar, int i) {
        if (mo894f()) {
            sb.append("VARCHAR(");
            sb.append(i);
            sb.append(")");
            return;
        }
        sb.append("VARCHAR");
    }

    /* renamed from: b */
    protected void m922b(StringBuilder sb, FieldType ssVar, int i) {
        sb.append("TEXT");
    }

    /* renamed from: c */
    protected void mo875c(StringBuilder sb, FieldType ssVar, int i) {
        sb.append("TIMESTAMP");
    }

    /* renamed from: d */
    protected void mo874d(StringBuilder sb, FieldType ssVar, int i) {
        sb.append("BOOLEAN");
    }

    /* renamed from: e */
    protected void m920e(StringBuilder sb, FieldType ssVar, int i) {
        sb.append("CHAR");
    }

    /* renamed from: f */
    protected void m919f(StringBuilder sb, FieldType ssVar, int i) {
        sb.append("TINYINT");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: g */
    public void m918g(StringBuilder sb, FieldType ssVar, int i) {
        sb.append("SMALLINT");
    }

    /* renamed from: l */
    private void m914l(StringBuilder sb, FieldType ssVar, int i) {
        sb.append("INTEGER");
    }

    /* renamed from: h */
    protected void mo909h(StringBuilder sb, FieldType ssVar, int i) {
        sb.append("BIGINT");
    }

    /* renamed from: m */
    private void m913m(StringBuilder sb, FieldType ssVar, int i) {
        sb.append("FLOAT");
    }

    /* renamed from: n */
    private void m912n(StringBuilder sb, FieldType ssVar, int i) {
        sb.append("DOUBLE PRECISION");
    }

    /* renamed from: i */
    protected void m917i(StringBuilder sb, FieldType ssVar, int i) {
        sb.append("BLOB");
    }

    /* renamed from: j */
    protected void m916j(StringBuilder sb, FieldType ssVar, int i) {
        sb.append("BLOB");
    }

    /* renamed from: k */
    protected void m915k(StringBuilder sb, FieldType ssVar, int i) {
        sb.append("NUMERIC");
    }

    /* renamed from: a */
    private void m925a(StringBuilder sb, FieldType ssVar, Object obj) {
        if (ssVar.m690y()) {
            mo903a(sb, obj.toString());
        } else {
            sb.append(obj);
        }
    }

    /* renamed from: a */
    protected void m923a(StringBuilder sb, FieldType ssVar, List<String> list, List<String> list2, List<String> list3) throws SQLException {
        throw new SQLException("GeneratedIdSequence is not supported by database " + mo871v() + " for field " + ssVar);
    }

    /* renamed from: b */
    protected void mo911b(String str, StringBuilder sb, FieldType ssVar, List<String> list, List<String> list2, List<String> list3, List<String> list4) {
        throw new IllegalStateException("GeneratedId is not supported by database " + mo871v() + " for field " + ssVar);
    }

    @Override // p110z1.DatabaseType
    /* renamed from: a */
    public void mo900a(FieldType[] ssVarArr, List<String> list, List<String> list2, List<String> list3, List<String> list4) {
        StringBuilder sb = null;
        for (FieldType ssVar : ssVarArr) {
            if ((!ssVar.m702m() || mo910c() || ssVar.m740C()) && ssVar.m703l()) {
                if (sb == null) {
                    sb = new StringBuilder(48);
                    sb.append("PRIMARY KEY (");
                } else {
                    sb.append(',');
                }
                mo899b(sb, ssVar.m715e());
            }
        }
        if (sb != null) {
            sb.append(") ");
            list.add(sb.toString());
        }
    }

    @Override // p110z1.DatabaseType
    /* renamed from: b */
    public void mo898b(FieldType[] ssVarArr, List<String> list, List<String> list2, List<String> list3, List<String> list4) {
        StringBuilder sb = null;
        for (FieldType ssVar : ssVarArr) {
            if (ssVar.m693v()) {
                if (sb == null) {
                    sb = new StringBuilder(48);
                    sb.append("UNIQUE (");
                } else {
                    sb.append(',');
                }
                mo899b(sb, ssVar.m715e());
            }
        }
        if (sb != null) {
            sb.append(") ");
            list.add(sb.toString());
        }
    }

    @Override // p110z1.DatabaseType
    /* renamed from: a */
    public void mo903a(StringBuilder sb, String str) {
        sb.append('\'');
        sb.append(str);
        sb.append('\'');
    }

    @Override // p110z1.DatabaseType
    /* renamed from: b */
    public void mo899b(StringBuilder sb, String str) {
        sb.append('`');
        sb.append(str);
        sb.append('`');
    }

    @Override // p110z1.DatabaseType
    /* renamed from: a */
    public String mo907a(String str, FieldType ssVar) {
        String str2 = str + f23239a;
        return mo887m() ? str2.toUpperCase() : str2;
    }

    @Override // p110z1.DatabaseType
    /* renamed from: a */
    public void mo904a(StringBuilder sb, long j, Long l) {
        sb.append("LIMIT ");
        sb.append(j);
        sb.append(' ');
    }

    @Override // p110z1.DatabaseType
    /* renamed from: a */
    public void mo905a(StringBuilder sb, long j) {
        sb.append("OFFSET ");
        sb.append(j);
        sb.append(' ');
    }

    @Override // p110z1.DatabaseType
    /* renamed from: s */
    public boolean mo883s() {
        return mo884r();
    }

    /* renamed from: a */
    private void m924a(StringBuilder sb, FieldType ssVar, List<String> list, List<String> list2) {
        StringBuilder sb2 = new StringBuilder();
        sb2.append(" UNIQUE (");
        mo899b(sb2, ssVar.m715e());
        sb2.append(")");
        list.add(sb2.toString());
    }

    /* compiled from: BaseDatabaseType.java */
    /* renamed from: z1.sg$a */
    /* loaded from: classes3.dex */
    protected static class C5559a extends BaseFieldConverter {
        @Override // p110z1.FieldConverter
        public SqlType getSqlType() {
            return SqlType.BOOLEAN;
        }

        @Override // p110z1.FieldConverter
        public Object parseDefaultString(FieldType ssVar, String str) {
            return Byte.valueOf(Boolean.parseBoolean(str) ? (byte) 1 : (byte) 0);
        }

        @Override // p110z1.BaseFieldConverter, p110z1.FieldConverter
        public Object javaToSqlArg(FieldType ssVar, Object obj) {
            return Byte.valueOf(((Boolean) obj).booleanValue() ? (byte) 1 : (byte) 0);
        }

        @Override // p110z1.FieldConverter
        public Object resultToSqlArg(FieldType ssVar, DatabaseResults woVar, int i) throws SQLException {
            return Byte.valueOf(woVar.mo215f(i));
        }

        @Override // p110z1.BaseFieldConverter, p110z1.FieldConverter
        public Object sqlArgToJava(FieldType ssVar, Object obj, int i) {
            return ((Byte) obj).byteValue() == 1;
        }

        @Override // p110z1.FieldConverter
        public Object resultStringToJava(FieldType ssVar, String str, int i) {
            return sqlArgToJava(ssVar, Byte.valueOf(Byte.parseByte(str)), i);
        }
    }
}
