package p110z1;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/* renamed from: z1.vj */
/* loaded from: classes3.dex */
public abstract class BaseMappedQuery<T, ID> extends BaseMappedStatement<T, ID> implements GenericRowMapper<T> {

    /* renamed from: a */
    protected final FieldType[] f23564a;

    /* renamed from: h */
    private Map<String, Integer> f23565h = null;

    /* renamed from: i */
    private Object f23566i = null;

    /* renamed from: j */
    private Object f23567j = null;

    /* JADX INFO: Access modifiers changed from: protected */
    public BaseMappedQuery(TableInfo<T, ID> wuVar, String str, FieldType[] ssVarArr, FieldType[] ssVarArr2) {
        super(wuVar, str, ssVarArr);
        this.f23564a = ssVarArr2;
    }

    @Override // p110z1.GenericRowMapper
    /* renamed from: a */
    public T mo322a(DatabaseResults woVar) throws SQLException {
        FieldType[] ssVarArr;
        FieldType[] ssVarArr2;
        BaseForeignCollection a;
        Map<String, Integer> map = this.f23565h;
        if (map == null) {
            map = new HashMap<>();
        }
        ObjectCache i = woVar.mo212i();
        if (i != null) {
            T t = (T) i.mo1012a(this.f23570d, this.f23571e.m722a(woVar, map));
            if (t != null) {
                return t;
            }
        }
        T f = this.f23569c.m166f();
        ID id = null;
        boolean z = false;
        for (FieldType ssVar : this.f23564a) {
            if (ssVar.m741B()) {
                z = true;
            } else {
                Object a2 = ssVar.m722a(woVar, map);
                if (a2 == null || this.f23566i == null || ssVar.m732a().getType() != this.f23566i.getClass() || !a2.equals(this.f23567j)) {
                    ssVar.m727a((Object) f, a2, false, i);
                } else {
                    ssVar.m727a((Object) f, this.f23566i, true, i);
                }
                if (ssVar == this.f23571e) {
                    id = a2;
                }
            }
        }
        if (z) {
            for (FieldType ssVar2 : this.f23564a) {
                if (ssVar2.m741B() && (a = ssVar2.m728a((Object) f, (T) id)) != null) {
                    ssVar2.m727a((Object) f, (Object) a, false, i);
                }
            }
        }
        if (!(i == null || id == null)) {
            i.mo1011a(this.f23570d, id, f);
        }
        if (this.f23565h == null) {
            this.f23565h = map;
        }
        return f;
    }

    /* renamed from: a */
    public void m323a(Object obj, Object obj2) {
        this.f23566i = obj;
        this.f23567j = obj2;
    }
}
