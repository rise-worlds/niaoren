package p110z1;

import java.sql.SQLException;
import java.util.List;

/* renamed from: z1.vs */
/* loaded from: classes3.dex */
public class MappedUpdateId<T, ID> extends BaseMappedStatement<T, ID> {
    private MappedUpdateId(TableInfo<T, ID> wuVar, String str, FieldType[] ssVarArr) {
        super(wuVar, str, ssVarArr);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: a */
    public int m284a(DatabaseConnection wnVar, T t, ID id, ObjectCache scVar) throws SQLException {
        Object b;
        try {
            Object[] objArr = {m317b(id), m283c(t)};
            int a = wnVar.mo241a(this.f23572f, objArr, this.f23573g);
            if (a > 0) {
                if (!(scVar == 0 || (b = scVar.mo1006b(this.f23570d, this.f23571e.m720b(t), id)) == null || b == t)) {
                    this.f23571e.m727a(b, (Object) id, false, scVar);
                }
                this.f23571e.m727a((Object) t, (Object) id, false, scVar);
            }
            f23568b.m593b("updating-id with statement '{}' and {} args, changed {} rows", this.f23572f, Integer.valueOf(objArr.length), Integer.valueOf(a));
            if (objArr.length > 0) {
                f23568b.m619a("updating-id arguments: {}", (Object) objArr);
            }
            return a;
        } catch (SQLException e) {
            throw SqlExceptionUtil.m529a("Unable to run update-id stmt on object " + t + ": " + this.f23572f, e);
        }
    }

    /* renamed from: a */
    public static <T, ID> MappedUpdateId<T, ID> m285a(DatabaseType siVar, TableInfo<T, ID> wuVar) throws SQLException {
        FieldType d = wuVar.m168d();
        if (d != null) {
            StringBuilder sb = new StringBuilder(64);
            m320a(siVar, sb, "UPDATE ", wuVar.m171b());
            sb.append("SET ");
            m319a(siVar, sb, d, (List<FieldType>) null);
            sb.append("= ? ");
            m318a(siVar, d, sb, (List<FieldType>) null);
            return new MappedUpdateId<>(wuVar, sb.toString(), new FieldType[]{d, d});
        }
        throw new SQLException("Cannot update-id in " + wuVar.m175a() + " because it doesn't have an id field");
    }

    /* renamed from: c */
    private Object m283c(T t) throws SQLException {
        return this.f23571e.m718c(t);
    }
}
