package p110z1;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

/* renamed from: z1.vy */
/* loaded from: classes3.dex */
public class In extends BaseComparison {

    /* renamed from: c */
    private Iterable<?> f23591c;

    /* renamed from: d */
    private final boolean f23592d;

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

    public In(String str, FieldType ssVar, Iterable<?> iterable, boolean z) throws SQLException {
        super(str, ssVar, null, true);
        this.f23591c = iterable;
        this.f23592d = z;
    }

    public In(String str, FieldType ssVar, Object[] objArr, boolean z) throws SQLException {
        super(str, ssVar, null, true);
        this.f23591c = Arrays.asList(objArr);
        this.f23592d = z;
    }

    @Override // p110z1.BaseComparison, p110z1.Comparison
    /* renamed from: a */
    public void mo275a(StringBuilder sb) {
        if (this.f23592d) {
            sb.append("IN ");
        } else {
            sb.append("NOT IN ");
        }
    }

    @Override // p110z1.BaseComparison, p110z1.Comparison
    /* renamed from: a */
    public void mo273a(DatabaseType siVar, StringBuilder sb, List<ArgumentHolder> list) throws SQLException {
        sb.append('(');
        boolean z = true;
        for (Object obj : this.f23591c) {
            if (obj != null) {
                if (z) {
                    z = false;
                } else {
                    sb.append(',');
                }
                super.mo277a(siVar, this.f23586b, sb, list, obj);
            } else {
                throw new IllegalArgumentException("one of the IN values for '" + this.f23585a + "' is null");
            }
        }
        sb.append(") ");
    }
}
