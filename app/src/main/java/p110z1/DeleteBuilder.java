package p110z1;

import java.sql.SQLException;
import java.util.List;
import p110z1.StatementBuilder;

/* renamed from: z1.us */
/* loaded from: classes3.dex */
public class DeleteBuilder<T, ID> extends StatementBuilder<T, ID> {
    @Override // p110z1.StatementBuilder
    /* renamed from: b */
    protected void mo374b(StringBuilder sb, List<ArgumentHolder> list) {
    }

    public DeleteBuilder(DatabaseType siVar, TableInfo<T, ID> wuVar, Dao<T, ID> rvVar) {
        super(siVar, wuVar, rvVar, StatementBuilder.EnumC5579b.DELETE);
    }

    /* renamed from: a */
    public PreparedDelete<T> m511a() throws SQLException {
        return super.m432c(null);
    }

    /* renamed from: b */
    public int m510b() throws SQLException {
        return this.f23526d.mo1092a((PreparedDelete) m511a());
    }

    @Override // p110z1.StatementBuilder
    /* renamed from: c */
    public void mo373c() {
        super.mo373c();
    }

    @Override // p110z1.StatementBuilder
    /* renamed from: a */
    protected void mo378a(StringBuilder sb, List<ArgumentHolder> list) {
        sb.append("DELETE FROM ");
        this.f23525c.mo899b(sb, this.f23523a.m171b());
        sb.append(' ');
    }
}
