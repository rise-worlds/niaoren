package p110z1;

import java.sql.SQLException;

/* renamed from: z1.uk */
/* loaded from: classes3.dex */
public abstract class BaseDaoEnabled<T, ID> {

    /* renamed from: a */
    protected transient Dao<T, ID> f23462a;

    /* renamed from: a */
    public int m542a() throws SQLException {
        m532h();
        return this.f23462a.mo1062e((Dao<T, ID>) this);
    }

    /* renamed from: b */
    public int m539b() throws SQLException {
        m532h();
        return this.f23462a.mo1053i(this);
    }

    /* renamed from: c */
    public int m537c() throws SQLException {
        m532h();
        return this.f23462a.mo1055h(this);
    }

    /* renamed from: a */
    public int m541a(ID id) throws SQLException {
        m532h();
        return this.f23462a.mo1103a((Dao<T, ID>) this, (BaseDaoEnabled<T, ID>) id);
    }

    /* renamed from: d */
    public int m536d() throws SQLException {
        m532h();
        return this.f23462a.mo1051j(this);
    }

    /* renamed from: e */
    public String m535e() {
        try {
            m532h();
            return this.f23462a.mo1047l(this);
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /* renamed from: f */
    public ID m534f() throws SQLException {
        m532h();
        return this.f23462a.mo1045m(this);
    }

    /* renamed from: b */
    public boolean m538b(T t) throws SQLException {
        m532h();
        return this.f23462a.mo1081b(this, (BaseDaoEnabled<T, ID>) t);
    }

    /* renamed from: a */
    public void m540a(Dao<T, ID> rvVar) {
        this.f23462a = rvVar;
    }

    /* renamed from: g */
    public Dao<T, ID> m533g() {
        return this.f23462a;
    }

    /* renamed from: h */
    private void m532h() throws SQLException {
        if (this.f23462a == null) {
            throw new SQLException("Dao has not been set on " + getClass() + " object: " + this);
        }
    }
}
