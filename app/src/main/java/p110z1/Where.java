package p110z1;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import p110z1.QueryBuilder;

/* renamed from: z1.vi */
/* loaded from: classes3.dex */
public class Where<T, ID> {

    /* renamed from: a */
    private static final int f23555a = 4;

    /* renamed from: b */
    private final TableInfo<T, ID> f23556b;

    /* renamed from: c */
    private final StatementBuilder<T, ID> f23557c;

    /* renamed from: d */
    private final FieldType f23558d;

    /* renamed from: e */
    private final String f23559e;

    /* renamed from: f */
    private final DatabaseType f23560f;

    /* renamed from: g */
    private Clause[] f23561g = new Clause[4];

    /* renamed from: h */
    private int f23562h = 0;

    /* renamed from: i */
    private NeedsFutureClause f23563i = null;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Where(TableInfo<T, ID> wuVar, StatementBuilder<T, ID> veVar, DatabaseType siVar) {
        this.f23556b = wuVar;
        this.f23557c = veVar;
        this.f23558d = wuVar.m168d();
        FieldType ssVar = this.f23558d;
        if (ssVar == null) {
            this.f23559e = null;
        } else {
            this.f23559e = ssVar.m715e();
        }
        this.f23560f = siVar;
    }

    /* renamed from: a */
    public Where<T, ID> m372a() {
        m355a((NeedsFutureClause) new ManyClause(m335e(ManyClause.f23595a), ManyClause.f23595a));
        return this;
    }

    /* renamed from: a */
    public Where<T, ID> m357a(Where<T, ID> viVar, Where<T, ID> viVar2, Where<T, ID>... viVarArr) {
        Clause[] a = m352a(viVarArr, ManyClause.f23595a);
        m356a((Clause) new ManyClause(m335e(ManyClause.f23595a), m335e(ManyClause.f23595a), a, ManyClause.f23595a));
        return this;
    }

    /* renamed from: a */
    public Where<T, ID> m371a(int i) {
        if (i != 0) {
            Clause[] vvVarArr = new Clause[i];
            for (int i2 = i - 1; i2 >= 0; i2--) {
                vvVarArr[i2] = m335e(ManyClause.f23595a);
            }
            m356a((Clause) new ManyClause(vvVarArr, ManyClause.f23595a));
            return this;
        }
        throw new IllegalArgumentException("Must have at least one clause in and(numClauses)");
    }

    /* renamed from: a */
    public Where<T, ID> m366a(String str, Object obj, Object obj2) throws SQLException {
        m356a((Clause) new Between(str, m338d(str), obj, obj2));
        return this;
    }

    /* renamed from: a */
    public Where<T, ID> m367a(String str, Object obj) throws SQLException {
        m356a((Clause) new SimpleComparison(str, m338d(str), obj, SimpleComparison.f23609c));
        return this;
    }

    /* renamed from: b */
    public Where<T, ID> m347b(String str, Object obj) throws SQLException {
        m356a((Clause) new SimpleComparison(str, m338d(str), obj, SimpleComparison.f23611e));
        return this;
    }

    /* renamed from: c */
    public Where<T, ID> m340c(String str, Object obj) throws SQLException {
        m356a((Clause) new SimpleComparison(str, m338d(str), obj, SimpleComparison.f23610d));
        return this;
    }

    /* renamed from: a */
    public Where<T, ID> m368a(String str, Iterable<?> iterable) throws SQLException {
        m356a((Clause) new In(str, m338d(str), iterable, true));
        return this;
    }

    /* renamed from: b */
    public Where<T, ID> m348b(String str, Iterable<?> iterable) throws SQLException {
        m356a((Clause) new In(str, m338d(str), iterable, false));
        return this;
    }

    /* renamed from: a */
    public Where<T, ID> m362a(String str, Object... objArr) throws SQLException {
        return m353a(true, str, objArr);
    }

    /* renamed from: b */
    public Where<T, ID> m345b(String str, Object... objArr) throws SQLException {
        return m353a(false, str, objArr);
    }

    /* renamed from: a */
    public Where<T, ID> m363a(String str, QueryBuilder<?, ?> uzVar) throws SQLException {
        return m354a(true, str, uzVar);
    }

    /* renamed from: b */
    public Where<T, ID> m346b(String str, QueryBuilder<?, ?> uzVar) throws SQLException {
        return m354a(false, str, uzVar);
    }

    /* renamed from: a */
    public Where<T, ID> m359a(QueryBuilder<?, ?> uzVar) {
        uzVar.m502a();
        m356a((Clause) new Exists(new QueryBuilder.C5575a(uzVar)));
        return this;
    }

    /* renamed from: a */
    public Where<T, ID> m369a(String str) throws SQLException {
        m356a((Clause) new IsNull(str, m338d(str)));
        return this;
    }

    /* renamed from: b */
    public Where<T, ID> m349b(String str) throws SQLException {
        m356a((Clause) new IsNotNull(str, m338d(str)));
        return this;
    }

    /* renamed from: d */
    public Where<T, ID> m337d(String str, Object obj) throws SQLException {
        m356a((Clause) new SimpleComparison(str, m338d(str), obj, SimpleComparison.f23613g));
        return this;
    }

    /* renamed from: e */
    public Where<T, ID> m334e(String str, Object obj) throws SQLException {
        m356a((Clause) new SimpleComparison(str, m338d(str), obj, SimpleComparison.f23612f));
        return this;
    }

    /* renamed from: f */
    public Where<T, ID> m332f(String str, Object obj) throws SQLException {
        m356a((Clause) new SimpleComparison(str, m338d(str), obj, SimpleComparison.f23614h));
        return this;
    }

    /* renamed from: g */
    public Where<T, ID> m330g(String str, Object obj) throws SQLException {
        m356a((Clause) new SimpleComparison(str, m338d(str), obj, SimpleComparison.f23615i));
        return this;
    }

    /* renamed from: b */
    public Where<T, ID> m351b() {
        m355a((NeedsFutureClause) new Not());
        return this;
    }

    /* renamed from: a */
    public Where<T, ID> m358a(Where<T, ID> viVar) {
        m356a((Clause) new Not(m335e("NOT")));
        return this;
    }

    /* renamed from: c */
    public Where<T, ID> m342c() {
        m355a((NeedsFutureClause) new ManyClause(m335e(ManyClause.f23596b), ManyClause.f23596b));
        return this;
    }

    /* renamed from: b */
    public Where<T, ID> m344b(Where<T, ID> viVar, Where<T, ID> viVar2, Where<T, ID>... viVarArr) {
        Clause[] a = m352a(viVarArr, ManyClause.f23596b);
        m356a((Clause) new ManyClause(m335e(ManyClause.f23596b), m335e(ManyClause.f23596b), a, ManyClause.f23596b));
        return this;
    }

    /* renamed from: b */
    public Where<T, ID> m350b(int i) {
        if (i != 0) {
            Clause[] vvVarArr = new Clause[i];
            for (int i2 = i - 1; i2 >= 0; i2--) {
                vvVarArr[i2] = m335e(ManyClause.f23596b);
            }
            m356a((Clause) new ManyClause(vvVarArr, ManyClause.f23596b));
            return this;
        }
        throw new IllegalArgumentException("Must have at least one clause in or(numClauses)");
    }

    /* renamed from: a */
    public Where<T, ID> m370a(ID id) throws SQLException {
        String str = this.f23559e;
        if (str != null) {
            m356a((Clause) new SimpleComparison(str, this.f23558d, id, SimpleComparison.f23609c));
            return this;
        }
        throw new SQLException("Object has no id column specified");
    }

    /* renamed from: a */
    public <OD> Where<T, ID> m360a(Dao<OD, ?> rvVar, OD od) throws SQLException {
        String str = this.f23559e;
        if (str != null) {
            m356a((Clause) new SimpleComparison(str, this.f23558d, rvVar.mo1045m(od), SimpleComparison.f23609c));
            return this;
        }
        throw new SQLException("Object has no id column specified");
    }

    /* renamed from: a */
    public Where<T, ID> m361a(String str, ArgumentHolder... upVarArr) {
        for (ArgumentHolder upVar : upVarArr) {
            String a = upVar.mo509a();
            if (a != null) {
                upVar.mo506a(m338d(a));
            } else if (upVar.mo504c() == null) {
                throw new IllegalArgumentException("Either the column name or SqlType must be set on each argument");
            }
        }
        m356a((Clause) new Raw(str, upVarArr));
        return this;
    }

    /* renamed from: a */
    public Where<T, ID> m365a(String str, String str2, Object obj) throws SQLException {
        m356a((Clause) new SimpleComparison(str, m338d(str), obj, str2));
        return this;
    }

    /* renamed from: d */
    public PreparedQuery<T> m339d() throws SQLException {
        return this.f23557c.m432c(null);
    }

    /* renamed from: e */
    public List<T> m336e() throws SQLException {
        return m341c("query()").m466g();
    }

    /* renamed from: f */
    public GenericRawResults<String[]> m333f() throws SQLException {
        return m341c("queryRaw()").m464h();
    }

    /* renamed from: g */
    public T m331g() throws SQLException {
        return m341c("queryForFirst()").m463i();
    }

    /* renamed from: h */
    public String[] m329h() throws SQLException {
        return m341c("queryRawFirst()").m462j();
    }

    /* renamed from: i */
    public long m328i() throws SQLException {
        return m341c("countOf()").m460l();
    }

    /* renamed from: j */
    public CloseableIterator<T> m327j() throws SQLException {
        return m341c("iterator()").m461k();
    }

    /* renamed from: k */
    public Where<T, ID> m326k() {
        for (int i = 0; i < this.f23562h; i++) {
            this.f23561g[i] = null;
        }
        this.f23562h = 0;
        return this;
    }

    /* renamed from: l */
    public String m325l() throws SQLException {
        StringBuilder sb = new StringBuilder();
        m364a((String) null, sb, (List<ArgumentHolder>) new ArrayList());
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m364a(String str, StringBuilder sb, List<ArgumentHolder> list) throws SQLException {
        int i = this.f23562h;
        if (i == 0) {
            throw new IllegalStateException("No where clauses defined.  Did you miss a where operation?");
        } else if (i == 1) {
            m324m().mo274a(this.f23560f, str, sb, list);
        } else {
            throw new IllegalStateException("Both the \"left-hand\" and \"right-hand\" clauses have been defined.  Did you miss an AND or OR?");
        }
    }

    public String toString() {
        if (this.f23562h == 0) {
            return "empty where clause";
        }
        Clause m = m324m();
        return "where clause: " + m;
    }

    /* renamed from: c */
    private QueryBuilder<T, ID> m341c(String str) throws SQLException {
        StatementBuilder<T, ID> veVar = this.f23557c;
        if (veVar instanceof QueryBuilder) {
            return (QueryBuilder) veVar;
        }
        throw new SQLException("Cannot call " + str + " on a statement of type " + this.f23557c.m424r());
    }

    /* renamed from: a */
    private Where<T, ID> m353a(boolean z, String str, Object... objArr) throws SQLException {
        if (objArr.length == 1) {
            if (objArr[0].getClass().isArray()) {
                StringBuilder sb = new StringBuilder();
                sb.append("Object argument to ");
                sb.append(z ? "IN" : "notId");
                sb.append(" seems to be an array within an array");
                throw new IllegalArgumentException(sb.toString());
            } else if (objArr[0] instanceof Where) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Object argument to ");
                sb2.append(z ? "IN" : "notId");
                sb2.append(" seems to be a Where object, did you mean the QueryBuilder?");
                throw new IllegalArgumentException(sb2.toString());
            } else if (objArr[0] instanceof PreparedStmt) {
                StringBuilder sb3 = new StringBuilder();
                sb3.append("Object argument to ");
                sb3.append(z ? "IN" : "notId");
                sb3.append(" seems to be a prepared statement, did you mean the QueryBuilder?");
                throw new IllegalArgumentException(sb3.toString());
            }
        }
        m356a((Clause) new In(str, m338d(str), objArr, z));
        return this;
    }

    /* renamed from: a */
    private Where<T, ID> m354a(boolean z, String str, QueryBuilder<?, ?> uzVar) throws SQLException {
        if (uzVar.m485b() == 1) {
            uzVar.m502a();
            m356a((Clause) new InSubQuery(str, m338d(str), new QueryBuilder.C5575a(uzVar), z));
            return this;
        } else if (uzVar.m485b() == 0) {
            throw new SQLException("Inner query must have only 1 select column specified instead of *");
        } else {
            throw new SQLException("Inner query must have only 1 select column specified instead of " + uzVar.m485b() + ": " + Arrays.toString(uzVar.m475d().toArray(new String[0])));
        }
    }

    /* renamed from: a */
    private Clause[] m352a(Where<T, ID>[] viVarArr, String str) {
        if (viVarArr.length == 0) {
            return null;
        }
        Clause[] vvVarArr = new Clause[viVarArr.length];
        for (int length = viVarArr.length - 1; length >= 0; length--) {
            vvVarArr[length] = m335e(str);
        }
        return vvVarArr;
    }

    /* renamed from: a */
    private void m355a(NeedsFutureClause wdVar) {
        if (this.f23563i == null) {
            this.f23563i = wdVar;
            m343b(wdVar);
            return;
        }
        throw new IllegalStateException(this.f23563i + " is already waiting for a future clause, can't add: " + wdVar);
    }

    /* renamed from: a */
    private void m356a(Clause vvVar) {
        NeedsFutureClause wdVar = this.f23563i;
        if (wdVar == null) {
            m343b(vvVar);
            return;
        }
        wdVar.mo280a(vvVar);
        this.f23563i = null;
    }

    /* renamed from: d */
    private FieldType m338d(String str) {
        return this.f23556b.m173a(str);
    }

    /* renamed from: b */
    private void m343b(Clause vvVar) {
        int i = this.f23562h;
        if (i == this.f23561g.length) {
            Clause[] vvVarArr = new Clause[i * 2];
            for (int i2 = 0; i2 < this.f23562h; i2++) {
                Clause[] vvVarArr2 = this.f23561g;
                vvVarArr[i2] = vvVarArr2[i2];
                vvVarArr2[i2] = null;
            }
            this.f23561g = vvVarArr;
        }
        Clause[] vvVarArr3 = this.f23561g;
        int i3 = this.f23562h;
        this.f23562h = i3 + 1;
        vvVarArr3[i3] = vvVar;
    }

    /* renamed from: e */
    private Clause m335e(String str) {
        int i = this.f23562h;
        if (i != 0) {
            Clause[] vvVarArr = this.f23561g;
            int i2 = i - 1;
            this.f23562h = i2;
            Clause vvVar = vvVarArr[i2];
            vvVarArr[this.f23562h] = null;
            return vvVar;
        }
        throw new IllegalStateException("Expecting there to be a clause already defined for '" + str + "' operation");
    }

    /* renamed from: m */
    private Clause m324m() {
        return this.f23561g[this.f23562h - 1];
    }
}
