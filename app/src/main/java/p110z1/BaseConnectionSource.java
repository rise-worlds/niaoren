package p110z1;

import java.sql.SQLException;

/* renamed from: z1.wk */
/* loaded from: classes3.dex */
public abstract class BaseConnectionSource implements ConnectionSource {

    /* renamed from: a */
    private ThreadLocal<C5585a> f23617a = new ThreadLocal<>();

    @Override // p110z1.ConnectionSource
    /* renamed from: g */
    public DatabaseConnection mo247g() {
        C5585a aVar = this.f23617a.get();
        if (aVar == null) {
            return null;
        }
        return aVar.f23618a;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: h */
    public DatabaseConnection m269h() {
        C5585a aVar = this.f23617a.get();
        if (aVar == null) {
            return null;
        }
        return aVar.f23618a;
    }

    /* renamed from: d */
    protected boolean m271d(DatabaseConnection wnVar) {
        C5585a aVar = this.f23617a.get();
        return aVar != null && aVar.f23618a == wnVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: e */
    public boolean m270e(DatabaseConnection wnVar) throws SQLException {
        C5585a aVar = this.f23617a.get();
        if (aVar == null) {
            this.f23617a.set(new C5585a(wnVar));
            return true;
        } else if (aVar.f23618a == wnVar) {
            aVar.m268a();
            return false;
        } else {
            throw new SQLException("trying to save connection " + wnVar + " but already have saved connection " + aVar.f23618a);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public boolean m272a(DatabaseConnection wnVar, C5570ui uiVar) {
        C5585a aVar = this.f23617a.get();
        if (wnVar != null) {
            if (aVar == null) {
                uiVar.m566e("no connection has been saved when clear() called");
            } else if (aVar.f23618a == wnVar) {
                if (aVar.m267b() == 0) {
                    this.f23617a.set(null);
                }
                return true;
            } else {
                uiVar.m564e("connection saved {} is not the one being cleared {}", aVar.f23618a, wnVar);
            }
        }
        return false;
    }

    /* compiled from: BaseConnectionSource.java */
    /* renamed from: z1.wk$a */
    /* loaded from: classes3.dex */
    private static class C5585a {

        /* renamed from: a */
        public final DatabaseConnection f23618a;

        /* renamed from: b */
        private int f23619b = 1;

        public C5585a(DatabaseConnection wnVar) {
            this.f23618a = wnVar;
        }

        /* renamed from: a */
        public void m268a() {
            this.f23619b++;
        }

        /* renamed from: b */
        public int m267b() {
            this.f23619b--;
            return this.f23619b;
        }
    }
}
