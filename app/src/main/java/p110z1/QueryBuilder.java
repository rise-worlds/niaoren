package p110z1;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.p105io.FilenameUtils;
import p110z1.StatementBuilder;

/* renamed from: z1.uz */
/* loaded from: classes3.dex */
public class QueryBuilder<T, ID> extends StatementBuilder<T, ID> {

    /* renamed from: h */
    private final FieldType f23476h;

    /* renamed from: i */
    private FieldType[] f23477i;

    /* renamed from: j */
    private boolean f23478j;

    /* renamed from: k */
    private boolean f23479k = true;

    /* renamed from: l */
    private List<String> f23480l;

    /* renamed from: m */
    private List<String> f23481m;

    /* renamed from: n */
    private List<OrderBy> f23482n;

    /* renamed from: o */
    private String f23483o;

    /* renamed from: p */
    private ArgumentHolder[] f23484p;

    /* renamed from: q */
    private List<String> f23485q;

    /* renamed from: r */
    private String f23486r;

    /* renamed from: s */
    private boolean f23487s;

    /* renamed from: t */
    private boolean f23488t;

    /* renamed from: u */
    private String f23489u;

    /* renamed from: v */
    private Long f23490v;

    /* renamed from: w */
    private Long f23491w;

    /* renamed from: x */
    private List<QueryBuilder<T, ID>.C5576b> f23492x;

    public QueryBuilder(DatabaseType siVar, TableInfo<T, ID> wuVar, Dao<T, ID> rvVar) {
        super(siVar, wuVar, rvVar, StatementBuilder.EnumC5579b.SELECT);
        this.f23476h = wuVar.m168d();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m502a() {
        this.f23487s = true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public int m485b() {
        if (this.f23488t) {
            return 1;
        }
        List<String> list = this.f23481m;
        if (list != null && !list.isEmpty()) {
            return this.f23481m.size();
        }
        List<String> list2 = this.f23480l;
        if (list2 == null) {
            return 0;
        }
        return list2.size();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: d */
    public List<String> m475d() {
        if (this.f23488t) {
            return Collections.singletonList("COUNT(*)");
        }
        List<String> list = this.f23481m;
        if (list != null && !list.isEmpty()) {
            return this.f23481m;
        }
        List<String> list2 = this.f23480l;
        return list2 == null ? Collections.emptyList() : list2;
    }

    /* renamed from: e */
    public PreparedQuery<T> m471e() throws SQLException {
        return super.m432c(this.f23490v);
    }

    /* renamed from: a */
    public QueryBuilder<T, ID> m486a(String... strArr) {
        if (this.f23480l == null) {
            this.f23480l = new ArrayList();
        }
        for (String str : strArr) {
            m468f(str);
        }
        return this;
    }

    /* renamed from: a */
    public QueryBuilder<T, ID> m500a(Iterable<String> iterable) {
        if (this.f23480l == null) {
            this.f23480l = new ArrayList();
        }
        for (String str : iterable) {
            m468f(str);
        }
        return this;
    }

    /* renamed from: b */
    public QueryBuilder<T, ID> m478b(String... strArr) {
        if (this.f23481m == null) {
            this.f23481m = new ArrayList();
        }
        for (String str : strArr) {
            this.f23481m.add(str);
        }
        return this;
    }

    /* renamed from: a */
    public QueryBuilder<T, ID> m498a(String str) {
        if (!m430e(str).m741B()) {
            if (this.f23485q == null) {
                this.f23485q = new ArrayList();
            }
            this.f23485q.add(str);
            this.f23479k = false;
            return this;
        }
        throw new IllegalArgumentException("Can't groupBy foreign colletion field: " + str);
    }

    /* renamed from: b */
    public QueryBuilder<T, ID> m482b(String str) {
        this.f23486r = str;
        return this;
    }

    /* renamed from: a */
    public QueryBuilder<T, ID> m496a(String str, boolean z) {
        if (!m430e(str).m741B()) {
            if (this.f23482n == null) {
                this.f23482n = new ArrayList();
            }
            this.f23482n.add(new OrderBy(str, z));
            return this;
        }
        throw new IllegalArgumentException("Can't orderBy foreign colletion field: " + str);
    }

    /* renamed from: c */
    public QueryBuilder<T, ID> m477c(String str) {
        return m495a(str, (ArgumentHolder[]) null);
    }

    /* renamed from: a */
    public QueryBuilder<T, ID> m495a(String str, ArgumentHolder... upVarArr) {
        this.f23483o = str;
        this.f23484p = upVarArr;
        return this;
    }

    /* renamed from: f */
    public QueryBuilder<T, ID> m469f() {
        this.f23478j = true;
        this.f23479k = false;
        return this;
    }

    @Deprecated
    /* renamed from: a */
    public QueryBuilder<T, ID> m501a(int i) {
        return m499a(Long.valueOf(i));
    }

    /* renamed from: a */
    public QueryBuilder<T, ID> m499a(Long l) {
        this.f23490v = l;
        return this;
    }

    @Deprecated
    /* renamed from: b */
    public QueryBuilder<T, ID> m484b(int i) throws SQLException {
        return m483b(Long.valueOf(i));
    }

    /* renamed from: b */
    public QueryBuilder<T, ID> m483b(Long l) throws SQLException {
        if (this.f23525c.mo892h()) {
            this.f23491w = l;
            return this;
        }
        throw new SQLException("Offset is not supported by this database");
    }

    /* renamed from: a */
    public QueryBuilder<T, ID> m487a(boolean z) {
        this.f23488t = z;
        return this;
    }

    /* renamed from: d */
    public QueryBuilder<T, ID> m474d(String str) {
        this.f23489u = str;
        return this;
    }

    /* renamed from: a */
    public QueryBuilder<T, ID> m488a(QueryBuilder<?, ?> uzVar) throws SQLException {
        m497a("INNER", uzVar);
        return this;
    }

    /* renamed from: b */
    public QueryBuilder<T, ID> m480b(QueryBuilder<?, ?> uzVar) throws SQLException {
        m497a("LEFT", uzVar);
        return this;
    }

    /* renamed from: g */
    public List<T> m466g() throws SQLException {
        return this.f23526d.mo1076b((PreparedQuery) m471e());
    }

    /* renamed from: h */
    public GenericRawResults<String[]> m464h() throws SQLException {
        return this.f23526d.mo1098a(m426p(), new String[0]);
    }

    /* renamed from: i */
    public T m463i() throws SQLException {
        return this.f23526d.mo1091a((PreparedQuery) m471e());
    }

    /* renamed from: j */
    public String[] m462j() throws SQLException {
        return this.f23526d.mo1098a(m426p(), new String[0]).mo450d();
    }

    /* renamed from: k */
    public CloseableIterator<T> m461k() throws SQLException {
        return this.f23526d.mo1065d((PreparedQuery) m471e());
    }

    /* renamed from: l */
    public long m460l() throws SQLException {
        m487a(true);
        return this.f23526d.mo1061e((PreparedQuery) m471e());
    }

    @Override // p110z1.StatementBuilder
    /* renamed from: c */
    public void mo373c() {
        super.mo373c();
        this.f23478j = false;
        this.f23479k = true;
        this.f23480l = null;
        this.f23481m = null;
        this.f23482n = null;
        this.f23483o = null;
        this.f23485q = null;
        this.f23486r = null;
        this.f23487s = false;
        this.f23488t = false;
        this.f23489u = null;
        this.f23490v = null;
        this.f23491w = null;
        List<QueryBuilder<T, ID>.C5576b> list = this.f23492x;
        if (list != null) {
            list.clear();
            this.f23492x = null;
        }
        this.f23528f = false;
    }

    @Override // p110z1.StatementBuilder
    /* renamed from: a */
    protected void mo378a(StringBuilder sb, List<ArgumentHolder> list) {
        if (this.f23492x == null) {
            m479b(false);
        } else {
            m479b(true);
        }
        sb.append("SELECT ");
        if (this.f23525c.mo890j()) {
            m473d(sb);
        }
        if (this.f23478j) {
            sb.append("DISTINCT ");
        }
        if (this.f23488t) {
            this.f23527e = StatementBuilder.EnumC5579b.SELECT_LONG;
            sb.append("COUNT(*) ");
        } else {
            List<String> list2 = this.f23481m;
            if (list2 == null || list2.isEmpty()) {
                this.f23527e = StatementBuilder.EnumC5579b.SELECT;
                m476c(sb);
            } else {
                this.f23527e = StatementBuilder.EnumC5579b.SELECT_RAW;
                m481b(sb);
            }
        }
        sb.append("FROM ");
        this.f23525c.mo899b(sb, this.f23524b);
        sb.append(' ');
        if (this.f23492x != null) {
            m494a(sb);
        }
    }

    @Override // p110z1.StatementBuilder
    /* renamed from: m */
    protected FieldType[] mo429m() {
        return this.f23477i;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // p110z1.StatementBuilder
    /* renamed from: a */
    public void mo435a(StringBuilder sb, List<ArgumentHolder> list, boolean z) throws SQLException {
        if (this.f23529g != null) {
            super.mo435a(sb, list, z);
            z = false;
        }
        List<QueryBuilder<T, ID>.C5576b> list2 = this.f23492x;
        if (list2 != null) {
            for (QueryBuilder<T, ID>.C5576b bVar : list2) {
                bVar.f23495b.mo435a(sb, list, z);
                z = false;
            }
        }
    }

    @Override // p110z1.StatementBuilder
    /* renamed from: b */
    protected void mo374b(StringBuilder sb, List<ArgumentHolder> list) throws SQLException {
        m467f(sb);
        m465g(sb);
        m472d(sb, list);
        if (!this.f23525c.mo890j()) {
            m473d(sb);
        }
        m470e(sb);
        m479b(false);
    }

    @Override // p110z1.StatementBuilder
    /* renamed from: n */
    protected boolean mo428n() {
        return this.f23492x != null;
    }

    /* renamed from: b */
    private void m479b(boolean z) {
        this.f23528f = z;
        List<QueryBuilder<T, ID>.C5576b> list = this.f23492x;
        if (list != null) {
            for (QueryBuilder<T, ID>.C5576b bVar : list) {
                bVar.f23495b.m479b(z);
            }
        }
    }

    /* renamed from: a */
    private void m497a(String str, QueryBuilder<?, ?> uzVar) throws SQLException {
        QueryBuilder<T, ID>.C5576b bVar = new C5576b(str, uzVar);
        m489a(bVar, uzVar);
        if (this.f23492x == null) {
            this.f23492x = new ArrayList();
        }
        this.f23492x.add(bVar);
    }

    /* renamed from: a */
    private void m489a(QueryBuilder<T, ID>.C5576b bVar, QueryBuilder<?, ?> uzVar) throws SQLException {
        FieldType[] c;
        FieldType[] c2;
        for (FieldType ssVar : this.f23523a.m169c()) {
            FieldType q = ssVar.m698q();
            if (ssVar.m699p() && q.equals(uzVar.f23523a.m168d())) {
                bVar.f23496c = ssVar;
                bVar.f23497d = q;
                return;
            }
        }
        for (FieldType ssVar2 : uzVar.f23523a.m169c()) {
            if (ssVar2.m699p() && ssVar2.m698q().equals(this.f23476h)) {
                bVar.f23496c = this.f23476h;
                bVar.f23497d = ssVar2;
                return;
            }
        }
        throw new SQLException("Could not find a foreign " + this.f23523a.m175a() + " field in " + uzVar.f23523a.m175a() + " or vice versa");
    }

    /* renamed from: f */
    private void m468f(String str) {
        m430e(str);
        this.f23480l.add(str);
    }

    /* renamed from: a */
    private void m494a(StringBuilder sb) {
        for (QueryBuilder<T, ID>.C5576b bVar : this.f23492x) {
            sb.append(bVar.f23494a);
            sb.append(" JOIN ");
            this.f23525c.mo899b(sb, bVar.f23495b.f23524b);
            sb.append(" ON ");
            this.f23525c.mo899b(sb, this.f23524b);
            sb.append(FilenameUtils.EXTENSION_SEPARATOR);
            this.f23525c.mo899b(sb, bVar.f23496c.m715e());
            sb.append(" = ");
            this.f23525c.mo899b(sb, bVar.f23495b.f23524b);
            sb.append(FilenameUtils.EXTENSION_SEPARATOR);
            this.f23525c.mo899b(sb, bVar.f23497d.m715e());
            sb.append(' ');
            if (bVar.f23495b.f23492x != null) {
                bVar.f23495b.m494a(sb);
            }
        }
    }

    /* renamed from: b */
    private void m481b(StringBuilder sb) {
        boolean z = true;
        for (String str : this.f23481m) {
            if (z) {
                z = false;
            } else {
                sb.append(", ");
            }
            sb.append(str);
        }
        sb.append(' ');
    }

    /* renamed from: c */
    private void m476c(StringBuilder sb) {
        List<String> list = this.f23480l;
        if (list == null) {
            if (this.f23528f) {
                this.f23525c.mo899b(sb, this.f23524b);
                sb.append(FilenameUtils.EXTENSION_SEPARATOR);
            }
            sb.append("* ");
            this.f23477i = this.f23523a.m169c();
            return;
        }
        boolean z = this.f23487s;
        List<FieldType> arrayList = new ArrayList<>(list.size() + 1);
        boolean z2 = true;
        for (String str : this.f23480l) {
            FieldType a = this.f23523a.m173a(str);
            if (a.m741B()) {
                arrayList.add(a);
            } else {
                if (z2) {
                    z2 = false;
                } else {
                    sb.append(',');
                }
                m492a(sb, a, arrayList);
                if (a == this.f23476h) {
                    z = true;
                }
            }
        }
        if (!z && this.f23479k) {
            if (!z2) {
                sb.append(',');
            }
            m492a(sb, this.f23476h, arrayList);
        }
        sb.append(' ');
        this.f23477i = (FieldType[]) arrayList.toArray(new FieldType[arrayList.size()]);
    }

    /* renamed from: a */
    private void m492a(StringBuilder sb, FieldType ssVar, List<FieldType> list) {
        m493a(sb, ssVar.m715e());
        if (list != null) {
            list.add(ssVar);
        }
    }

    /* renamed from: d */
    private void m473d(StringBuilder sb) {
        if (this.f23490v != null && this.f23525c.mo893g()) {
            this.f23525c.mo904a(sb, this.f23490v.longValue(), this.f23491w);
        }
    }

    /* renamed from: e */
    private void m470e(StringBuilder sb) throws SQLException {
        if (this.f23491w != null) {
            if (!this.f23525c.mo891i()) {
                this.f23525c.mo905a(sb, this.f23491w.longValue());
            } else if (this.f23490v == null) {
                throw new SQLException("If the offset is specified, limit must also be specified with this database");
            }
        }
    }

    /* renamed from: f */
    private void m467f(StringBuilder sb) {
        boolean z = true;
        if (m459s()) {
            m491a(sb, true);
            z = false;
        }
        List<QueryBuilder<T, ID>.C5576b> list = this.f23492x;
        if (list != null) {
            for (QueryBuilder<T, ID>.C5576b bVar : list) {
                if (bVar.f23495b != null && bVar.f23495b.m459s()) {
                    bVar.f23495b.m491a(sb, z);
                }
            }
        }
    }

    /* renamed from: s */
    private boolean m459s() {
        List<String> list = this.f23485q;
        return (list != null && !list.isEmpty()) || this.f23486r != null;
    }

    /* renamed from: a */
    private void m491a(StringBuilder sb, boolean z) {
        if (z) {
            sb.append("GROUP BY ");
        }
        if (this.f23486r != null) {
            if (!z) {
                sb.append(',');
            }
            sb.append(this.f23486r);
        } else {
            for (String str : this.f23485q) {
                if (z) {
                    z = false;
                } else {
                    sb.append(',');
                }
                m493a(sb, str);
            }
        }
        sb.append(' ');
    }

    /* renamed from: d */
    private void m472d(StringBuilder sb, List<ArgumentHolder> list) {
        boolean z = true;
        if (m458t()) {
            m490a(sb, true, list);
            z = false;
        }
        List<QueryBuilder<T, ID>.C5576b> list2 = this.f23492x;
        if (list2 != null) {
            for (QueryBuilder<T, ID>.C5576b bVar : list2) {
                if (bVar.f23495b != null && bVar.f23495b.m458t()) {
                    bVar.f23495b.m490a(sb, z, list);
                }
            }
        }
    }

    /* renamed from: t */
    private boolean m458t() {
        List<OrderBy> list = this.f23482n;
        return (list != null && !list.isEmpty()) || this.f23483o != null;
    }

    /* renamed from: a */
    private void m490a(StringBuilder sb, boolean z, List<ArgumentHolder> list) {
        if (z) {
            sb.append("ORDER BY ");
        }
        if (this.f23483o != null) {
            if (!z) {
                sb.append(',');
            }
            sb.append(this.f23483o);
            ArgumentHolder[] upVarArr = this.f23484p;
            if (upVarArr != null) {
                for (ArgumentHolder upVar : upVarArr) {
                    list.add(upVar);
                }
            }
        } else {
            for (OrderBy wfVar : this.f23482n) {
                if (z) {
                    z = false;
                } else {
                    sb.append(',');
                }
                m493a(sb, wfVar.m279a());
                if (!wfVar.m278b()) {
                    sb.append(" DESC");
                }
            }
        }
        sb.append(' ');
    }

    /* renamed from: a */
    private void m493a(StringBuilder sb, String str) {
        if (this.f23528f) {
            this.f23525c.mo899b(sb, this.f23524b);
            sb.append(FilenameUtils.EXTENSION_SEPARATOR);
        }
        this.f23525c.mo899b(sb, str);
    }

    /* renamed from: g */
    private void m465g(StringBuilder sb) {
        if (this.f23489u != null) {
            sb.append("HAVING ");
            sb.append(this.f23489u);
            sb.append(' ');
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: QueryBuilder.java */
    /* renamed from: z1.uz$b */
    /* loaded from: classes3.dex */
    public class C5576b {

        /* renamed from: a */
        final String f23494a;

        /* renamed from: b */
        final QueryBuilder<?, ?> f23495b;

        /* renamed from: c */
        FieldType f23496c;

        /* renamed from: d */
        FieldType f23497d;

        public C5576b(String str, QueryBuilder<?, ?> uzVar) {
            this.f23494a = str;
            this.f23495b = uzVar;
        }
    }

    /* compiled from: QueryBuilder.java */
    /* renamed from: z1.uz$a */
    /* loaded from: classes3.dex */
    public static class C5575a {

        /* renamed from: a */
        private final QueryBuilder<?, ?> f23493a;

        /* JADX INFO: Access modifiers changed from: package-private */
        public C5575a(QueryBuilder<?, ?> uzVar) {
            this.f23493a = uzVar;
        }

        /* renamed from: a */
        public void m456a(StringBuilder sb, List<ArgumentHolder> list) throws SQLException {
            this.f23493a.m431c(sb, list);
        }

        /* renamed from: a */
        public FieldType[] m457a() {
            return this.f23493a.mo429m();
        }
    }
}
