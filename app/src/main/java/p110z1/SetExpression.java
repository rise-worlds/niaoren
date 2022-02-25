package p110z1;

import java.sql.SQLException;
import java.util.List;

/* renamed from: z1.wh */
/* loaded from: classes3.dex */
public class SetExpression extends BaseComparison {
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

    @Override // p110z1.BaseComparison, p110z1.Comparison
    /* renamed from: a */
    public /* bridge */ /* synthetic */ void mo273a(DatabaseType siVar, StringBuilder sb, List list) throws SQLException {
        super.mo273a(siVar, sb, list);
    }

    @Override // p110z1.BaseComparison
    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    public SetExpression(String str, FieldType ssVar, String str2) throws SQLException {
        super(str, ssVar, str2, true);
    }

    @Override // p110z1.BaseComparison, p110z1.Comparison
    /* renamed from: a */
    public void mo275a(StringBuilder sb) {
        sb.append("= ");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // p110z1.BaseComparison
    /* renamed from: a */
    public void mo277a(DatabaseType siVar, FieldType ssVar, StringBuilder sb, List<ArgumentHolder> list, Object obj) {
        sb.append(obj);
        sb.append(' ');
    }
}
