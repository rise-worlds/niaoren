package p110z1;

import java.sql.SQLException;
import java.util.List;

/* renamed from: z1.vp */
/* loaded from: classes3.dex */
public class MappedQueryForId<T, ID> extends BaseMappedQuery<T, ID> {

    /* renamed from: h */
    private final String f23581h;

    /* JADX INFO: Access modifiers changed from: protected */
    public MappedQueryForId(TableInfo<T, ID> wuVar, String str, FieldType[] ssVarArr, FieldType[] ssVarArr2, String str2) {
        super(wuVar, str, ssVarArr, ssVarArr2);
        this.f23581h = str2;
    }

    /* renamed from: a */
    public T m293a(DatabaseConnection wnVar, ID id, ObjectCache scVar) throws SQLException {
        T t;
        if (scVar != null && (t = (T) scVar.mo1012a(this.f23570d, id)) != null) {
            return t;
        }
        Object[] objArr = {m317b(id)};
        T t2 = (T) wnVar.mo240a(this.f23572f, objArr, this.f23573g, this, scVar);
        if (t2 == null) {
            f23568b.m593b("{} using '{}' and {} args, got no results", this.f23581h, this.f23572f, Integer.valueOf(objArr.length));
        } else if (t2 != DatabaseConnection.f23620a) {
            f23568b.m593b("{} using '{}' and {} args, got 1 result", this.f23581h, this.f23572f, Integer.valueOf(objArr.length));
        } else {
            f23568b.m563e("{} using '{}' and {} args, got >1 results", this.f23581h, this.f23572f, Integer.valueOf(objArr.length));
            m292a(objArr);
            throw new SQLException(this.f23581h + " got more than 1 result: " + this.f23572f);
        }
        m292a(objArr);
        return t2;
    }

    /* renamed from: a */
    public static <T, ID> MappedQueryForId<T, ID> m294a(DatabaseType siVar, TableInfo<T, ID> wuVar, FieldType ssVar) throws SQLException {
        if (ssVar != null || (ssVar = wuVar.m168d()) != null) {
            return new MappedQueryForId<>(wuVar, m291b(siVar, wuVar, ssVar), new FieldType[]{ssVar}, wuVar.m169c(), "query-for-id");
        }
        throw new SQLException("Cannot query-for-id with " + wuVar.m175a() + " because it doesn't have an id field");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: b */
    public static <T, ID> String m291b(DatabaseType siVar, TableInfo<T, ID> wuVar, FieldType ssVar) {
        StringBuilder sb = new StringBuilder(64);
        m320a(siVar, sb, "SELECT * FROM ", wuVar.m171b());
        m318a(siVar, ssVar, sb, (List<FieldType>) null);
        return sb.toString();
    }

    /* renamed from: a */
    private void m292a(Object[] objArr) {
        if (objArr.length > 0) {
            f23568b.m618a("{} arguments: {}", this.f23581h, objArr);
        }
    }
}
