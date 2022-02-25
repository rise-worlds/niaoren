package p110z1;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/* renamed from: z1.ve */
/* loaded from: classes3.dex */
public abstract class StatementBuilder<T, ID> {

    /* renamed from: h */
    private static C5570ui f23522h = LoggerFactory.m545a(StatementBuilder.class);

    /* renamed from: a */
    protected final TableInfo<T, ID> f23523a;

    /* renamed from: b */
    protected final String f23524b;

    /* renamed from: c */
    protected final DatabaseType f23525c;

    /* renamed from: d */
    protected final Dao<T, ID> f23526d;

    /* renamed from: e */
    protected EnumC5579b f23527e;

    /* renamed from: f */
    protected boolean f23528f;

    /* renamed from: g */
    protected Where<T, ID> f23529g = null;

    /* renamed from: a */
    protected abstract void mo378a(StringBuilder sb, List<ArgumentHolder> list) throws SQLException;

    /* renamed from: b */
    protected abstract void mo374b(StringBuilder sb, List<ArgumentHolder> list) throws SQLException;

    /* renamed from: m */
    protected FieldType[] mo429m() {
        return null;
    }

    /* renamed from: n */
    protected boolean mo428n() {
        return false;
    }

    public StatementBuilder(DatabaseType siVar, TableInfo<T, ID> wuVar, Dao<T, ID> rvVar, EnumC5579b bVar) {
        this.f23525c = siVar;
        this.f23523a = wuVar;
        this.f23524b = wuVar.m171b();
        this.f23526d = rvVar;
        this.f23527e = bVar;
        if (!bVar.isOkForStatementBuilder()) {
            throw new IllegalStateException("Building a statement from a " + bVar + " statement is not allowed");
        }
    }

    /* renamed from: o */
    public Where<T, ID> m427o() {
        this.f23529g = new Where<>(this.f23523a, this, this.f23525c);
        return this.f23529g;
    }

    /* renamed from: a */
    public void m433a(Where<T, ID> viVar) {
        this.f23529g = viVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: c */
    public MappedPreparedStmt<T, ID> m432c(Long l) throws SQLException {
        List<ArgumentHolder> arrayList = new ArrayList<>();
        String a = m434a(arrayList);
        ArgumentHolder[] upVarArr = (ArgumentHolder[]) arrayList.toArray(new ArgumentHolder[arrayList.size()]);
        FieldType[] m = mo429m();
        FieldType[] ssVarArr = new FieldType[arrayList.size()];
        for (int i = 0; i < upVarArr.length; i++) {
            ssVarArr[i] = upVarArr[i].mo503d();
        }
        if (this.f23527e.isOkForStatementBuilder()) {
            TableInfo<T, ID> wuVar = this.f23523a;
            if (this.f23525c.mo893g()) {
                l = null;
            }
            return new MappedPreparedStmt<>(wuVar, a, ssVarArr, m, upVarArr, l, this.f23527e);
        }
        throw new IllegalStateException("Building a statement from a " + this.f23527e + " statement is not allowed");
    }

    /* renamed from: p */
    public String m426p() throws SQLException {
        return m434a(new ArrayList());
    }

    /* renamed from: q */
    public C5578a m425q() throws SQLException {
        ArrayList arrayList = new ArrayList();
        return new C5578a(m434a(arrayList), arrayList);
    }

    /* renamed from: c */
    public void mo373c() {
        this.f23529g = null;
    }

    /* renamed from: a */
    protected String m434a(List<ArgumentHolder> list) throws SQLException {
        StringBuilder sb = new StringBuilder(128);
        m431c(sb, list);
        String sb2 = sb.toString();
        f23522h.m595b("built statement {}", sb2);
        return sb2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: c */
    public void m431c(StringBuilder sb, List<ArgumentHolder> list) throws SQLException {
        mo378a(sb, list);
        mo435a(sb, list, true);
        mo374b(sb, list);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public void mo435a(StringBuilder sb, List<ArgumentHolder> list, boolean z) throws SQLException {
        if (this.f23529g != null) {
            if (z) {
                sb.append("WHERE ");
            } else {
                sb.append("AND (");
            }
            this.f23529g.m364a(this.f23528f ? this.f23524b : null, sb, list);
            if (!z) {
                sb.append(") ");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: e */
    public FieldType m430e(String str) {
        return this.f23523a.m173a(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: r */
    public EnumC5579b m424r() {
        return this.f23527e;
    }

    /* compiled from: StatementBuilder.java */
    /* renamed from: z1.ve$b */
    /* loaded from: classes3.dex */
    public enum EnumC5579b {
        SELECT(true, true, false, false),
        SELECT_LONG(true, true, false, false),
        SELECT_RAW(true, true, false, false),
        UPDATE(true, false, true, false),
        DELETE(true, false, true, false),
        EXECUTE(false, false, false, true);
        
        private final boolean okForExecute;
        private final boolean okForQuery;
        private final boolean okForStatementBuilder;
        private final boolean okForUpdate;

        EnumC5579b(boolean z, boolean z2, boolean z3, boolean z4) {
            this.okForStatementBuilder = z;
            this.okForQuery = z2;
            this.okForUpdate = z3;
            this.okForExecute = z4;
        }

        public boolean isOkForStatementBuilder() {
            return this.okForStatementBuilder;
        }

        public boolean isOkForQuery() {
            return this.okForQuery;
        }

        public boolean isOkForUpdate() {
            return this.okForUpdate;
        }

        public boolean isOkForExecute() {
            return this.okForExecute;
        }
    }

    /* compiled from: StatementBuilder.java */
    /* renamed from: z1.ve$a */
    /* loaded from: classes3.dex */
    public static class C5578a {

        /* renamed from: a */
        private final String f23530a;

        /* renamed from: b */
        private final List<ArgumentHolder> f23531b;

        private C5578a(String str, List<ArgumentHolder> list) {
            this.f23531b = list;
            this.f23530a = str;
        }

        /* renamed from: a */
        public String m423a() {
            return this.f23530a;
        }

        /* renamed from: b */
        public List<ArgumentHolder> m422b() {
            return this.f23531b;
        }
    }
}
