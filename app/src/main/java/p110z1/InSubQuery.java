package p110z1;

import java.sql.SQLException;
import java.util.List;
import p110z1.QueryBuilder;

/* renamed from: z1.vz */
/* loaded from: classes3.dex */
public class InSubQuery extends BaseComparison {

    /* renamed from: c */
    private final QueryBuilder.C5575a f23593c;

    /* renamed from: d */
    private final boolean f23594d;

    @Override // p110z1.BaseComparison, p110z1.Comparison
    /* renamed from: a */
    public /* bridge */ /* synthetic */ String mo276a() {
        return super.mo276a();
    }

    @Override // p110z1.BaseComparison, p110z1.Clause
    /* renamed from: a */
    public /* bridge */ /* synthetic */ void mo274a(DatabaseType siVar, String str, StringBuilder sb, List list) throws SQLException {
        super.mo274a(siVar, str, sb, list);
    }

    @Override // p110z1.BaseComparison
    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    public InSubQuery(String str, FieldType ssVar, QueryBuilder.C5575a aVar, boolean z) throws SQLException {
        super(str, ssVar, null, true);
        this.f23593c = aVar;
        this.f23594d = z;
    }

    @Override // p110z1.BaseComparison, p110z1.Comparison
    /* renamed from: a */
    public void mo275a(StringBuilder sb) {
        if (this.f23594d) {
            sb.append("IN ");
        } else {
            sb.append("NOT IN ");
        }
    }

    @Override // p110z1.BaseComparison, p110z1.Comparison
    /* renamed from: a */
    public void mo273a(DatabaseType siVar, StringBuilder sb, List<ArgumentHolder> list) throws SQLException {
        sb.append('(');
        this.f23593c.m456a(sb, list);
        FieldType[] a = this.f23593c.m457a();
        if (a != null) {
            if (a.length != 1) {
                throw new SQLException("There must be only 1 result column in sub-query but we found " + a.length);
            } else if (this.f23586b.m709h() != a[0].m709h()) {
                throw new SQLException("Outer column " + this.f23586b + " is not the same type as inner column " + a[0]);
            }
        }
        sb.append(") ");
    }
}
