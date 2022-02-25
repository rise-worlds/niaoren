package p110z1;

import java.sql.SQLException;

/* renamed from: z1.vq */
/* loaded from: classes3.dex */
public class MappedRefresh<T, ID> extends MappedQueryForId<T, ID> {
    private MappedRefresh(TableInfo<T, ID> wuVar, String str, FieldType[] ssVarArr, FieldType[] ssVarArr2) {
        super(wuVar, str, ssVarArr, ssVarArr2, "refresh");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: b */
    public int m289b(DatabaseConnection wnVar, T t, ObjectCache scVar) throws SQLException {
        FieldType[] ssVarArr;
        Object a = super.m293a(wnVar, (DatabaseConnection) this.f23571e.m720b(t), (ObjectCache) null);
        if (a == null) {
            return 0;
        }
        for (FieldType ssVar : this.f23564a) {
            if (ssVar != this.f23571e) {
                ssVar.m727a((Object) t, ssVar.m720b(a), false, scVar);
            }
        }
        return 1;
    }

    /* renamed from: a */
    public static <T, ID> MappedRefresh<T, ID> m290a(DatabaseType siVar, TableInfo<T, ID> wuVar) throws SQLException {
        FieldType d = wuVar.m168d();
        if (d != null) {
            return new MappedRefresh<>(wuVar, m291b(siVar, wuVar, d), new FieldType[]{wuVar.m168d()}, wuVar.m169c());
        }
        throw new SQLException("Cannot refresh " + wuVar.m175a() + " because it doesn't have an id field");
    }
}
