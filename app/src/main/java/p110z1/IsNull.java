package p110z1;

import java.sql.SQLException;
import java.util.List;

/* renamed from: z1.wb */
/* loaded from: classes3.dex */
public class IsNull extends BaseComparison {
    @Override // p110z1.BaseComparison, p110z1.Comparison
    /* renamed from: a */
    public void mo273a(DatabaseType siVar, StringBuilder sb, List<ArgumentHolder> list) {
    }

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

    public IsNull(String str, FieldType ssVar) throws SQLException {
        super(str, ssVar, null, true);
    }

    @Override // p110z1.BaseComparison, p110z1.Comparison
    /* renamed from: a */
    public void mo275a(StringBuilder sb) {
        sb.append("IS NULL ");
    }
}
