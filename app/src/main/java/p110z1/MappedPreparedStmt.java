package p110z1;

import java.sql.SQLException;
import p110z1.Log;
import p110z1.StatementBuilder;

/* renamed from: z1.vo */
/* loaded from: classes3.dex */
public class MappedPreparedStmt<T, ID> extends BaseMappedQuery<T, ID> implements PreparedDelete<T>, PreparedQuery<T>, PreparedUpdate<T> {

    /* renamed from: h */
    private final ArgumentHolder[] f23578h;

    /* renamed from: i */
    private final Long f23579i;

    /* renamed from: j */
    private final StatementBuilder.EnumC5579b f23580j;

    public MappedPreparedStmt(TableInfo<T, ID> wuVar, String str, FieldType[] ssVarArr, FieldType[] ssVarArr2, ArgumentHolder[] upVarArr, Long l, StatementBuilder.EnumC5579b bVar) {
        super(wuVar, str, ssVarArr, ssVarArr2);
        this.f23578h = upVarArr;
        this.f23579i = l;
        this.f23580j = bVar;
    }

    @Override // p110z1.PreparedStmt
    /* renamed from: a */
    public CompiledStatement mo297a(DatabaseConnection wnVar, StatementBuilder.EnumC5579b bVar) throws SQLException {
        return mo296a(wnVar, bVar, -1);
    }

    @Override // p110z1.PreparedStmt
    /* renamed from: a */
    public CompiledStatement mo296a(DatabaseConnection wnVar, StatementBuilder.EnumC5579b bVar, int i) throws SQLException {
        if (this.f23580j == bVar) {
            return m298a(wnVar.mo242a(this.f23572f, bVar, this.f23573g, i));
        }
        throw new SQLException("Could not compile this " + this.f23580j + " statement since the caller is expecting a " + bVar + " statement.  Check your QueryBuilder methods.");
    }

    @Override // p110z1.PreparedStmt
    /* renamed from: a */
    public String mo300a() {
        return this.f23572f;
    }

    @Override // p110z1.PreparedStmt
    /* renamed from: b */
    public StatementBuilder.EnumC5579b mo295b() {
        return this.f23580j;
    }

    @Override // p110z1.PreparedStmt
    /* renamed from: a */
    public void mo299a(int i, Object obj) throws SQLException {
        if (i >= 0) {
            ArgumentHolder[] upVarArr = this.f23578h;
            if (upVarArr.length > i) {
                upVarArr[i].mo387a(obj);
                return;
            }
            throw new SQLException("argument holder index " + i + " not valid, only " + this.f23578h.length + " in statement");
        }
        throw new SQLException("argument holder index " + i + " must be >= 0");
    }

    /* renamed from: a */
    private CompiledStatement m298a(CompiledStatement wlVar) throws SQLException {
        SqlType suVar;
        try {
            if (this.f23579i != null) {
                wlVar.mo260b(this.f23579i.intValue());
            }
            Object[] objArr = null;
            if (f23568b.m608a(Log.EnumC5569a.TRACE) && this.f23578h.length > 0) {
                objArr = new Object[this.f23578h.length];
            }
            for (int i = 0; i < this.f23578h.length; i++) {
                Object b = this.f23578h[i].mo505b();
                FieldType ssVar = this.f23573g[i];
                if (ssVar == null) {
                    suVar = this.f23578h[i].mo504c();
                } else {
                    suVar = ssVar.m709h();
                }
                wlVar.mo264a(i, b, suVar);
                if (objArr != null) {
                    objArr[i] = b;
                }
            }
            f23568b.m594b("prepared statement '{}' with {} args", this.f23572f, Integer.valueOf(this.f23578h.length));
            if (objArr != null) {
                f23568b.m619a("prepared statement arguments: {}", (Object) objArr);
            }
            return wlVar;
        } catch (Throwable th) {
            wlVar.mo258d();
            throw th;
        }
    }
}
