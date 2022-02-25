package p110z1;

import java.sql.SQLException;
import java.util.List;

/* renamed from: z1.vr */
/* loaded from: classes3.dex */
public class MappedUpdate<T, ID> extends BaseMappedStatement<T, ID> {

    /* renamed from: a */
    private final FieldType f23582a;

    /* renamed from: h */
    private final int f23583h;

    private MappedUpdate(TableInfo<T, ID> wuVar, String str, FieldType[] ssVarArr, FieldType ssVar, int i) {
        super(wuVar, str, ssVarArr);
        this.f23582a = ssVar;
        this.f23583h = i;
    }

    /* renamed from: a */
    public static <T, ID> MappedUpdate<T, ID> m288a(DatabaseType siVar, TableInfo<T, ID> wuVar) throws SQLException {
        FieldType[] c;
        FieldType[] c2;
        FieldType d = wuVar.m168d();
        if (d != null) {
            StringBuilder sb = new StringBuilder(64);
            m320a(siVar, sb, "UPDATE ", wuVar.m171b());
            r12 = null;
            int i = 0;
            int i2 = -1;
            for (FieldType ssVar : wuVar.m169c()) {
                if (m287a(ssVar, d)) {
                    if (ssVar.m736G()) {
                        i2 = i;
                    } else {
                        ssVar = ssVar;
                    }
                    i++;
                }
            }
            int i3 = i + 1;
            if (ssVar != null) {
                i3++;
            }
            FieldType[] ssVarArr = new FieldType[i3];
            int i4 = 0;
            boolean z = true;
            for (FieldType ssVar2 : wuVar.m169c()) {
                if (m287a(ssVar2, d)) {
                    if (z) {
                        sb.append("SET ");
                        z = false;
                    } else {
                        sb.append(", ");
                    }
                    m319a(siVar, sb, ssVar2, (List<FieldType>) null);
                    i4++;
                    ssVarArr[i4] = ssVar2;
                    sb.append("= ?");
                }
            }
            sb.append(' ');
            m318a(siVar, d, sb, (List<FieldType>) null);
            int i5 = i4 + 1;
            ssVarArr[i4] = d;
            if (ssVar != null) {
                sb.append(" AND ");
                m319a(siVar, sb, ssVar, (List<FieldType>) null);
                sb.append("= ?");
                ssVarArr[i5] = ssVar;
            }
            return new MappedUpdate<>(wuVar, sb.toString(), ssVarArr, ssVar, i2);
        }
        throw new SQLException("Cannot update " + wuVar.m175a() + " because it doesn't have an id field");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: a */
    public int m286a(DatabaseConnection wnVar, T t, ObjectCache scVar) throws SQLException {
        Object obj;
        FieldType[] c;
        try {
            if (this.f23573g.length <= 1) {
                return 0;
            }
            Object[] a = m321a(t);
            if (this.f23582a != null) {
                obj = this.f23582a.m714e(this.f23582a.m720b(t));
                a[this.f23583h] = this.f23582a.m716d(obj);
            } else {
                obj = null;
            }
            int a2 = wnVar.mo241a(this.f23572f, a, this.f23573g);
            if (a2 > 0) {
                if (obj != null) {
                    this.f23582a.m727a((Object) t, obj, false, (ObjectCache) null);
                }
                if (scVar != 0) {
                    Object a3 = scVar.mo1012a(this.f23570d, this.f23571e.m720b(t));
                    if (!(a3 == null || a3 == t)) {
                        for (FieldType ssVar : this.f23569c.m169c()) {
                            if (ssVar != this.f23571e) {
                                ssVar.m727a(a3, ssVar.m720b(t), false, scVar);
                            }
                        }
                    }
                }
            }
            f23568b.m593b("update data with statement '{}' and {} args, changed {} rows", this.f23572f, Integer.valueOf(a.length), Integer.valueOf(a2));
            if (a.length > 0) {
                f23568b.m619a("update arguments: {}", (Object) a);
            }
            return a2;
        } catch (SQLException e) {
            throw SqlExceptionUtil.m529a("Unable to run update stmt on object " + t + ": " + this.f23572f, e);
        }
    }

    /* renamed from: a */
    private static boolean m287a(FieldType ssVar, FieldType ssVar2) {
        if (ssVar == ssVar2) {
            return false;
        }
        return !(ssVar.m734I() | ssVar.m741B());
    }
}
