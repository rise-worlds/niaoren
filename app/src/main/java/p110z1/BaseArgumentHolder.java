package p110z1;

import java.sql.SQLException;

/* renamed from: z1.uq */
/* loaded from: classes3.dex */
public abstract class BaseArgumentHolder implements ArgumentHolder {

    /* renamed from: a */
    private String f23471a;

    /* renamed from: b */
    private FieldType f23472b;

    /* renamed from: c */
    private SqlType f23473c;

    @Override // p110z1.ArgumentHolder
    /* renamed from: a */
    public abstract void mo387a(Object obj);

    /* renamed from: e */
    protected abstract Object mo386e();

    /* renamed from: f */
    protected abstract boolean mo385f();

    public BaseArgumentHolder() {
        this.f23471a = null;
        this.f23472b = null;
        this.f23473c = null;
    }

    public BaseArgumentHolder(String str) {
        this.f23471a = null;
        this.f23472b = null;
        this.f23473c = null;
        this.f23471a = str;
    }

    public BaseArgumentHolder(SqlType suVar) {
        this.f23471a = null;
        this.f23472b = null;
        this.f23473c = null;
        this.f23473c = suVar;
    }

    @Override // p110z1.ArgumentHolder
    /* renamed from: a */
    public String mo509a() {
        return this.f23471a;
    }

    @Override // p110z1.ArgumentHolder
    /* renamed from: a */
    public void mo508a(String str) {
        String str2 = this.f23471a;
        if (str2 != null && !str2.equals(str)) {
            throw new IllegalArgumentException("Column name cannot be set twice from " + this.f23471a + " to " + str + ".  Using a SelectArg twice in query with different columns?");
        }
        this.f23471a = str;
    }

    @Override // p110z1.ArgumentHolder
    /* renamed from: a */
    public void mo506a(FieldType ssVar) {
        FieldType ssVar2 = this.f23472b;
        if (ssVar2 == null || ssVar2 == ssVar) {
            this.f23472b = ssVar;
            return;
        }
        throw new IllegalArgumentException("FieldType name cannot be set twice from " + this.f23472b + " to " + ssVar + ".  Using a SelectArg twice in query with different columns?");
    }

    @Override // p110z1.ArgumentHolder
    /* renamed from: a */
    public void mo507a(String str, FieldType ssVar) {
        mo508a(str);
        mo506a(ssVar);
    }

    @Override // p110z1.ArgumentHolder
    /* renamed from: b */
    public Object mo505b() throws SQLException {
        if (mo385f()) {
            Object e = mo386e();
            if (e == null) {
                return null;
            }
            FieldType ssVar = this.f23472b;
            if (ssVar == null) {
                return e;
            }
            if (!ssVar.m699p() || this.f23472b.m717d() != e.getClass()) {
                return this.f23472b.m716d(e);
            }
            return this.f23472b.m698q().m720b(e);
        }
        throw new SQLException("Column value has not been set for " + this.f23471a);
    }

    @Override // p110z1.ArgumentHolder
    /* renamed from: d */
    public FieldType mo503d() {
        return this.f23472b;
    }

    @Override // p110z1.ArgumentHolder
    /* renamed from: c */
    public SqlType mo504c() {
        return this.f23473c;
    }

    public String toString() {
        if (!mo385f()) {
            return "[unset]";
        }
        try {
            Object b = mo505b();
            return b == null ? "[null]" : b.toString();
        } catch (SQLException e) {
            return "[could not get value: " + e + "]";
        }
    }
}
