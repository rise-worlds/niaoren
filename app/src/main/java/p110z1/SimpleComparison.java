package p110z1;

import java.sql.SQLException;
import java.util.List;

/* renamed from: z1.wj */
/* loaded from: classes3.dex */
public class SimpleComparison extends BaseComparison {

    /* renamed from: c */
    public static final String f23609c = "=";

    /* renamed from: d */
    public static final String f23610d = ">";

    /* renamed from: e */
    public static final String f23611e = ">=";

    /* renamed from: f */
    public static final String f23612f = "<";

    /* renamed from: g */
    public static final String f23613g = "<=";

    /* renamed from: h */
    public static final String f23614h = "LIKE";

    /* renamed from: i */
    public static final String f23615i = "<>";

    /* renamed from: j */
    private final String f23616j;

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

    public SimpleComparison(String str, FieldType ssVar, Object obj, String str2) throws SQLException {
        super(str, ssVar, obj, true);
        this.f23616j = str2;
    }

    @Override // p110z1.BaseComparison, p110z1.Comparison
    /* renamed from: a */
    public void mo275a(StringBuilder sb) {
        sb.append(this.f23616j);
        sb.append(' ');
    }
}
