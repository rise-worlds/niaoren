package p110z1;

import java.sql.SQLException;
import java.util.List;

/* renamed from: z1.vm */
/* loaded from: classes3.dex */
public class MappedDelete<T, ID> extends BaseMappedStatement<T, ID> {
    private MappedDelete(TableInfo<T, ID> wuVar, String str, FieldType[] ssVarArr) {
        super(wuVar, str, ssVarArr);
    }

    /* renamed from: a */
    public static <T, ID> MappedDelete<T, ID> m308a(DatabaseType siVar, TableInfo<T, ID> wuVar) throws SQLException {
        FieldType d = wuVar.m168d();
        if (d != null) {
            StringBuilder sb = new StringBuilder(64);
            m320a(siVar, sb, "DELETE FROM ", wuVar.m171b());
            m318a(siVar, d, sb, (List<FieldType>) null);
            return new MappedDelete<>(wuVar, sb.toString(), new FieldType[]{d});
        }
        throw new SQLException("Cannot delete from " + wuVar.m175a() + " because it doesn't have an id field");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: a */
    public int m307a(DatabaseConnection wnVar, T t, ObjectCache scVar) throws SQLException {
        try {
            Object[] a = m321a(t);
            int b = wnVar.mo234b(this.f23572f, a, this.f23573g);
            f23568b.m593b("delete data with statement '{}' and {} args, changed {} rows", this.f23572f, Integer.valueOf(a.length), Integer.valueOf(b));
            if (a.length > 0) {
                f23568b.m619a("delete arguments: {}", (Object) a);
            }
            if (b > 0 && scVar != 0) {
                scVar.mo1007b(this.f23570d, this.f23571e.m718c(t));
            }
            return b;
        } catch (SQLException e) {
            throw SqlExceptionUtil.m529a("Unable to run delete stmt on object " + t + ": " + this.f23572f, e);
        }
    }

    /* renamed from: b */
    public int m306b(DatabaseConnection wnVar, ID id, ObjectCache scVar) throws SQLException {
        try {
            Object[] objArr = {m317b(id)};
            int b = wnVar.mo234b(this.f23572f, objArr, this.f23573g);
            f23568b.m593b("delete data with statement '{}' and {} args, changed {} rows", this.f23572f, Integer.valueOf(objArr.length), Integer.valueOf(b));
            if (objArr.length > 0) {
                f23568b.m619a("delete arguments: {}", (Object) objArr);
            }
            if (b > 0 && scVar != null) {
                scVar.mo1007b(this.f23570d, id);
            }
            return b;
        } catch (SQLException e) {
            throw SqlExceptionUtil.m529a("Unable to run deleteById stmt on id " + id + ": " + this.f23572f, e);
        }
    }
}
