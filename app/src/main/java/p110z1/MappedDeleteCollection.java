package p110z1;

import java.sql.SQLException;
import java.util.Collection;

/* renamed from: z1.vn */
/* loaded from: classes3.dex */
public class MappedDeleteCollection<T, ID> extends BaseMappedStatement<T, ID> {
    private MappedDeleteCollection(TableInfo<T, ID> wuVar, String str, FieldType[] ssVarArr) {
        super(wuVar, str, ssVarArr);
    }

    /* renamed from: a */
    public static <T, ID> int m303a(DatabaseType siVar, TableInfo<T, ID> wuVar, DatabaseConnection wnVar, Collection<T> collection, ObjectCache scVar) throws SQLException {
        MappedDeleteCollection a = m304a(siVar, wuVar, collection.size());
        Object[] objArr = new Object[collection.size()];
        FieldType d = wuVar.m168d();
        int i = 0;
        for (T t : collection) {
            objArr[i] = d.m718c(t);
            i++;
        }
        return m302a(wnVar, wuVar.m175a(), a, objArr, scVar);
    }

    /* renamed from: b */
    public static <T, ID> int m301b(DatabaseType siVar, TableInfo<T, ID> wuVar, DatabaseConnection wnVar, Collection<ID> collection, ObjectCache scVar) throws SQLException {
        MappedDeleteCollection a = m304a(siVar, wuVar, collection.size());
        Object[] objArr = new Object[collection.size()];
        FieldType d = wuVar.m168d();
        int i = 0;
        for (ID id : collection) {
            objArr[i] = d.m716d(id);
            i++;
        }
        return m302a(wnVar, wuVar.m175a(), a, objArr, scVar);
    }

    /* renamed from: a */
    private static <T, ID> MappedDeleteCollection<T, ID> m304a(DatabaseType siVar, TableInfo<T, ID> wuVar, int i) throws SQLException {
        FieldType d = wuVar.m168d();
        if (d != null) {
            StringBuilder sb = new StringBuilder(128);
            m320a(siVar, sb, "DELETE FROM ", wuVar.m171b());
            FieldType[] ssVarArr = new FieldType[i];
            m305a(siVar, d, sb, i, ssVarArr);
            return new MappedDeleteCollection<>(wuVar, sb.toString(), ssVarArr);
        }
        throw new SQLException("Cannot delete " + wuVar.m175a() + " because it doesn't have an id field defined");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: a */
    private static <T, ID> int m302a(DatabaseConnection wnVar, Class<T> cls, MappedDeleteCollection<T, ID> vnVar, Object[] objArr, ObjectCache scVar) throws SQLException {
        try {
            int b = wnVar.mo234b(vnVar.f23572f, objArr, vnVar.f23573g);
            if (b > 0 && scVar != 0) {
                for (Object obj : objArr) {
                    scVar.mo1007b(cls, obj);
                }
            }
            f23568b.m593b("delete-collection with statement '{}' and {} args, changed {} rows", vnVar.f23572f, Integer.valueOf(objArr.length), Integer.valueOf(b));
            if (objArr.length > 0) {
                f23568b.m619a("delete-collection arguments: {}", (Object) objArr);
            }
            return b;
        } catch (SQLException e) {
            throw SqlExceptionUtil.m529a("Unable to run delete collection stmt: " + vnVar.f23572f, e);
        }
    }

    /* renamed from: a */
    private static void m305a(DatabaseType siVar, FieldType ssVar, StringBuilder sb, int i, FieldType[] ssVarArr) {
        sb.append("WHERE ");
        siVar.mo899b(sb, ssVar.m715e());
        sb.append(" IN (");
        boolean z = true;
        for (int i2 = 0; i2 < i; i2++) {
            if (z) {
                z = false;
            } else {
                sb.append(',');
            }
            sb.append('?');
            if (ssVarArr != null) {
                ssVarArr[i2] = ssVar;
            }
        }
        sb.append(") ");
    }
}
