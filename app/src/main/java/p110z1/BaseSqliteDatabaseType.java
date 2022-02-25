package p110z1;

import java.util.List;
import p110z1.BaseDatabaseType;

/* renamed from: z1.sh */
/* loaded from: classes3.dex */
public abstract class BaseSqliteDatabaseType extends BaseDatabaseType implements DatabaseType {

    /* renamed from: c */
    private static final FieldConverter f23242c = new BaseDatabaseType.C5559a();

    @Override // p110z1.BaseDatabaseType
    /* renamed from: c */
    protected boolean mo910c() {
        return false;
    }

    @Override // p110z1.BaseDatabaseType, p110z1.DatabaseType
    /* renamed from: f */
    public boolean mo894f() {
        return false;
    }

    @Override // p110z1.BaseDatabaseType, p110z1.DatabaseType
    /* renamed from: k */
    public boolean mo889k() {
        return false;
    }

    @Override // p110z1.BaseDatabaseType, p110z1.DatabaseType
    /* renamed from: r */
    public boolean mo884r() {
        return true;
    }

    @Override // p110z1.BaseDatabaseType
    /* renamed from: h */
    protected void mo909h(StringBuilder sb, FieldType ssVar, int i) {
        if (ssVar.m709h() != SqlType.LONG || !ssVar.m702m()) {
            sb.append("BIGINT");
        } else {
            sb.append("INTEGER");
        }
    }

    @Override // p110z1.BaseDatabaseType
    /* renamed from: b */
    protected void mo911b(String str, StringBuilder sb, FieldType ssVar, List<String> list, List<String> list2, List<String> list3, List<String> list4) {
        if (ssVar.m709h() == SqlType.INTEGER || ssVar.m709h() == SqlType.LONG) {
            sb.append("PRIMARY KEY AUTOINCREMENT ");
            return;
        }
        throw new IllegalArgumentException("Sqlite requires that auto-increment generated-id be integer or long type");
    }

    @Override // p110z1.BaseDatabaseType, p110z1.DatabaseType
    /* renamed from: a */
    public FieldConverter mo878a(DataPersister slVar) {
        switch (slVar.getSqlType()) {
            case BOOLEAN:
                return f23242c;
            case BIG_DECIMAL:
                return BigDecimalStringType.m672a();
            default:
                return super.mo878a(slVar);
        }
    }
}
