package p110z1;

import java.sql.SQLException;
import java.util.List;

/* renamed from: z1.vu */
/* loaded from: classes3.dex */
public class Between extends BaseComparison {

    /* renamed from: c */
    private Object f23588c;

    /* renamed from: d */
    private Object f23589d;

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

    public Between(String str, FieldType ssVar, Object obj, Object obj2) throws SQLException {
        super(str, ssVar, null, true);
        this.f23588c = obj;
        this.f23589d = obj2;
    }

    @Override // p110z1.BaseComparison, p110z1.Comparison
    /* renamed from: a */
    public void mo275a(StringBuilder sb) {
        sb.append("BETWEEN ");
    }

    @Override // p110z1.BaseComparison, p110z1.Comparison
    /* renamed from: a */
    public void mo273a(DatabaseType siVar, StringBuilder sb, List<ArgumentHolder> list) throws SQLException {
        if (this.f23588c == null) {
            throw new IllegalArgumentException("BETWEEN low value for '" + this.f23585a + "' is null");
        } else if (this.f23589d != null) {
            mo277a(siVar, this.f23586b, sb, list, this.f23588c);
            sb.append("AND ");
            mo277a(siVar, this.f23586b, sb, list, this.f23589d);
        } else {
            throw new IllegalArgumentException("BETWEEN high value for '" + this.f23585a + "' is null");
        }
    }
}
