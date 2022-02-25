package p110z1;

import java.sql.SQLException;

/* renamed from: z1.vd */
/* loaded from: classes3.dex */
public class SelectIterator<T, ID> implements CloseableIterator<T> {

    /* renamed from: a */
    private static final C5570ui f23508a = LoggerFactory.m545a(SelectIterator.class);

    /* renamed from: b */
    private final Class<?> f23509b;

    /* renamed from: c */
    private final Dao<T, ID> f23510c;

    /* renamed from: d */
    private final ConnectionSource f23511d;

    /* renamed from: e */
    private final DatabaseConnection f23512e;

    /* renamed from: f */
    private final CompiledStatement f23513f;

    /* renamed from: g */
    private final DatabaseResults f23514g;

    /* renamed from: h */
    private final GenericRowMapper<T> f23515h;

    /* renamed from: i */
    private final String f23516i;

    /* renamed from: j */
    private boolean f23517j = true;

    /* renamed from: k */
    private boolean f23518k = false;

    /* renamed from: l */
    private boolean f23519l = false;

    /* renamed from: m */
    private T f23520m = null;

    /* renamed from: n */
    private int f23521n = 0;

    public SelectIterator(Class<?> cls, Dao<T, ID> rvVar, GenericRowMapper<T> utVar, ConnectionSource wmVar, DatabaseConnection wnVar, CompiledStatement wlVar, String str, ObjectCache scVar) throws SQLException {
        this.f23509b = cls;
        this.f23510c = rvVar;
        this.f23515h = utVar;
        this.f23511d = wmVar;
        this.f23512e = wnVar;
        this.f23513f = wlVar;
        this.f23514g = wlVar.mo262a(scVar);
        this.f23516i = str;
        if (str != null) {
            f23508a.m594b("starting iterator @{} for '{}'", Integer.valueOf(hashCode()), str);
        }
    }

    /* renamed from: i */
    public boolean m438i() throws SQLException {
        boolean z;
        if (this.f23518k) {
            return false;
        }
        if (this.f23519l) {
            return true;
        }
        if (this.f23517j) {
            this.f23517j = false;
            z = this.f23514g.mo222c();
        } else {
            z = this.f23514g.mo220d();
        }
        if (!z) {
            mo447a();
        }
        this.f23519l = true;
        return z;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        try {
            return m438i();
        } catch (SQLException e) {
            this.f23520m = null;
            mo445b();
            throw new IllegalStateException("Errors getting more results of " + this.f23509b, e);
        }
    }

    @Override // p110z1.CloseableIterator
    /* renamed from: e */
    public T mo442e() throws SQLException {
        if (this.f23518k) {
            return null;
        }
        this.f23517j = false;
        if (this.f23514g.mo222c()) {
            return m436k();
        }
        return null;
    }

    @Override // p110z1.CloseableIterator
    /* renamed from: f */
    public T mo441f() throws SQLException {
        if (this.f23518k) {
            return null;
        }
        this.f23517j = false;
        if (this.f23514g.mo216f()) {
            return m436k();
        }
        return null;
    }

    @Override // p110z1.CloseableIterator
    /* renamed from: g */
    public T mo440g() throws SQLException {
        if (this.f23518k) {
            return null;
        }
        if (this.f23517j) {
            return mo442e();
        }
        return m436k();
    }

    @Override // p110z1.CloseableIterator
    /* renamed from: h */
    public T mo439h() throws SQLException {
        boolean z;
        if (this.f23518k) {
            return null;
        }
        if (!this.f23519l) {
            if (this.f23517j) {
                this.f23517j = false;
                z = this.f23514g.mo222c();
            } else {
                z = this.f23514g.mo220d();
            }
            if (!z) {
                this.f23517j = false;
                return null;
            }
        }
        this.f23517j = false;
        return m436k();
    }

    @Override // java.util.Iterator
    public T next() {
        SQLException e;
        T h;
        try {
            h = mo439h();
        } catch (SQLException e2) {
            e = e2;
        }
        if (h != null) {
            return h;
        }
        e = null;
        this.f23520m = null;
        mo445b();
        throw new IllegalStateException("Could not get next result for " + this.f23509b, e);
    }

    @Override // p110z1.CloseableIterator
    /* renamed from: a */
    public T mo446a(int i) throws SQLException {
        if (this.f23518k) {
            return null;
        }
        this.f23517j = false;
        if (this.f23514g.mo226a(i)) {
            return m436k();
        }
        return null;
    }

    /* renamed from: j */
    public void m437j() throws SQLException {
        T t = this.f23520m;
        if (t != null) {
            Dao<T, ID> rvVar = this.f23510c;
            if (rvVar != null) {
                try {
                    rvVar.mo1051j(t);
                } finally {
                    this.f23520m = null;
                }
            } else {
                throw new IllegalStateException("Cannot remove " + this.f23509b + " object because classDao not initialized");
            }
        } else {
            throw new IllegalStateException("No last " + this.f23509b + " object to remove. Must be called after a call to next.");
        }
    }

    @Override // java.util.Iterator
    public void remove() {
        try {
            m437j();
        } catch (SQLException e) {
            mo445b();
            throw new IllegalStateException("Could not delete " + this.f23509b + " object " + this.f23520m, e);
        }
    }

    @Override // p110z1.CloseableIterator
    /* renamed from: a */
    public void mo447a() throws SQLException {
        if (!this.f23518k) {
            this.f23513f.mo258d();
            this.f23518k = true;
            this.f23520m = null;
            if (this.f23516i != null) {
                f23508a.m594b("closed iterator @{} after {} rows", Integer.valueOf(hashCode()), Integer.valueOf(this.f23521n));
            }
            this.f23511d.mo255a(this.f23512e);
        }
    }

    @Override // p110z1.CloseableIterator
    /* renamed from: b */
    public void mo445b() {
        try {
            mo447a();
        } catch (SQLException unused) {
        }
    }

    @Override // p110z1.CloseableIterator
    /* renamed from: c */
    public DatabaseResults mo444c() {
        return this.f23514g;
    }

    @Override // p110z1.CloseableIterator
    /* renamed from: d */
    public void mo443d() {
        this.f23520m = null;
        this.f23517j = false;
        this.f23519l = false;
    }

    /* renamed from: k */
    private T m436k() throws SQLException {
        this.f23520m = this.f23515h.mo322a(this.f23514g);
        this.f23519l = false;
        this.f23521n++;
        return this.f23520m;
    }
}
