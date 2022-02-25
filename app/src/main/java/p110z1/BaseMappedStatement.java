package p110z1;

import java.sql.SQLException;
import java.util.List;

/* renamed from: z1.vk */
/* loaded from: classes3.dex */
public abstract class BaseMappedStatement<T, ID> {

    /* renamed from: b */
    protected static C5570ui f23568b = LoggerFactory.m545a(BaseMappedStatement.class);

    /* renamed from: c */
    protected final TableInfo<T, ID> f23569c;

    /* renamed from: d */
    protected final Class<T> f23570d;

    /* renamed from: e */
    protected final FieldType f23571e;

    /* renamed from: f */
    protected final String f23572f;

    /* renamed from: g */
    protected final FieldType[] f23573g;

    /* JADX INFO: Access modifiers changed from: protected */
    public BaseMappedStatement(TableInfo<T, ID> wuVar, String str, FieldType[] ssVarArr) {
        this.f23569c = wuVar;
        this.f23570d = wuVar.m175a();
        this.f23571e = wuVar.m168d();
        this.f23572f = str;
        this.f23573g = ssVarArr;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public Object[] m321a(Object obj) throws SQLException {
        Object[] objArr = new Object[this.f23573g.length];
        int i = 0;
        while (true) {
            FieldType[] ssVarArr = this.f23573g;
            if (i >= ssVarArr.length) {
                return objArr;
            }
            FieldType ssVar = ssVarArr[i];
            if (ssVar.m739D()) {
                objArr[i] = ssVar.m712f(obj);
            } else {
                objArr[i] = ssVar.m718c(obj);
            }
            if (objArr[i] == null && ssVar.m707i() != null) {
                objArr[i] = ssVar.m707i();
            }
            i++;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: b */
    public Object m317b(ID id) throws SQLException {
        return this.f23571e.m716d(id);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static void m318a(DatabaseType siVar, FieldType ssVar, StringBuilder sb, List<FieldType> list) {
        sb.append("WHERE ");
        m319a(siVar, sb, ssVar, list);
        sb.append("= ?");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static void m320a(DatabaseType siVar, StringBuilder sb, String str, String str2) {
        if (str != null) {
            sb.append(str);
        }
        siVar.mo899b(sb, str2);
        sb.append(' ');
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static void m319a(DatabaseType siVar, StringBuilder sb, FieldType ssVar, List<FieldType> list) {
        siVar.mo899b(sb, ssVar.m715e());
        if (list != null) {
            list.add(ssVar);
        }
        sb.append(' ');
    }

    public String toString() {
        return "MappedStatement: " + this.f23572f;
    }
}
