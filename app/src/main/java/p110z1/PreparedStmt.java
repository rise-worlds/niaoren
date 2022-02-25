package p110z1;

import java.sql.SQLException;
import p110z1.StatementBuilder;

/* renamed from: z1.ux */
/* loaded from: classes3.dex */
public interface PreparedStmt<T> extends GenericRowMapper<T> {
    /* renamed from: a */
    String mo300a() throws SQLException;

    /* renamed from: a */
    CompiledStatement mo297a(DatabaseConnection wnVar, StatementBuilder.EnumC5579b bVar) throws SQLException;

    /* renamed from: a */
    CompiledStatement mo296a(DatabaseConnection wnVar, StatementBuilder.EnumC5579b bVar, int i) throws SQLException;

    /* renamed from: a */
    void mo299a(int i, Object obj) throws SQLException;

    /* renamed from: b */
    StatementBuilder.EnumC5579b mo295b();
}
