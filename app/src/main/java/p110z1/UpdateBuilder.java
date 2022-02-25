package p110z1;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import p110z1.StatementBuilder;

/* renamed from: z1.vh */
/* loaded from: classes3.dex */
public class UpdateBuilder<T, ID> extends StatementBuilder<T, ID> {

    /* renamed from: h */
    private List<Clause> f23554h = null;

    @Override // p110z1.StatementBuilder
    /* renamed from: b */
    protected void mo374b(StringBuilder sb, List<ArgumentHolder> list) {
    }

    public UpdateBuilder(DatabaseType siVar, TableInfo<T, ID> wuVar, Dao<T, ID> rvVar) {
        super(siVar, wuVar, rvVar, StatementBuilder.EnumC5579b.UPDATE);
    }

    /* renamed from: a */
    public PreparedUpdate<T> m384a() throws SQLException {
        return super.m432c(null);
    }

    /* renamed from: a */
    public StatementBuilder<T, ID> m382a(String str, Object obj) throws SQLException {
        FieldType e = m430e(str);
        if (!e.m741B()) {
            m380a(str, (Clause) new SetValue(str, e, obj));
            return this;
        }
        throw new SQLException("Can't update foreign colletion field: " + str);
    }

    /* renamed from: a */
    public StatementBuilder<T, ID> m381a(String str, String str2) throws SQLException {
        FieldType e = m430e(str);
        if (!e.m741B()) {
            m380a(str, (Clause) new SetExpression(str, e, str2));
            return this;
        }
        throw new SQLException("Can't update foreign colletion field: " + str);
    }

    /* renamed from: a */
    public void m379a(StringBuilder sb, String str) {
        this.f23525c.mo899b(sb, str);
    }

    /* renamed from: a */
    public String m383a(String str) {
        StringBuilder sb = new StringBuilder(str.length() + 4);
        this.f23525c.mo899b(sb, str);
        return sb.toString();
    }

    /* renamed from: b */
    public void m375b(StringBuilder sb, String str) {
        this.f23525c.mo903a(sb, str);
    }

    /* renamed from: b */
    public String m376b(String str) {
        StringBuilder sb = new StringBuilder(str.length() + 4);
        this.f23525c.mo903a(sb, str);
        return sb.toString();
    }

    /* renamed from: b */
    public int m377b() throws SQLException {
        return this.f23526d.mo1089a((PreparedUpdate) m384a());
    }

    @Override // p110z1.StatementBuilder
    /* renamed from: c */
    public void mo373c() {
        super.mo373c();
        this.f23554h = null;
    }

    @Override // p110z1.StatementBuilder
    /* renamed from: a */
    protected void mo378a(StringBuilder sb, List<ArgumentHolder> list) throws SQLException {
        List<Clause> list2 = this.f23554h;
        if (list2 == null || list2.isEmpty()) {
            throw new IllegalArgumentException("UPDATE statements must have at least one SET column");
        }
        sb.append("UPDATE ");
        this.f23525c.mo899b(sb, this.f23523a.m171b());
        sb.append(" SET ");
        boolean z = true;
        for (Clause vvVar : this.f23554h) {
            if (z) {
                z = false;
            } else {
                sb.append(',');
            }
            vvVar.mo274a(this.f23525c, null, sb, list);
        }
    }

    /* renamed from: a */
    private void m380a(String str, Clause vvVar) {
        if (this.f23554h == null) {
            this.f23554h = new ArrayList();
        }
        this.f23554h.add(vvVar);
    }
}
