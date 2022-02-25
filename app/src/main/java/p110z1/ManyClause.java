package p110z1;

import java.sql.SQLException;
import java.util.List;

/* renamed from: z1.wc */
/* loaded from: classes3.dex */
public class ManyClause implements Clause, NeedsFutureClause {

    /* renamed from: a */
    public static final String f23595a = "AND";

    /* renamed from: b */
    public static final String f23596b = "OR";

    /* renamed from: c */
    private final Clause f23597c;

    /* renamed from: d */
    private Clause f23598d;

    /* renamed from: e */
    private final Clause[] f23599e;

    /* renamed from: f */
    private final int f23600f;

    /* renamed from: g */
    private final String f23601g;

    public ManyClause(Clause vvVar, String str) {
        this.f23597c = vvVar;
        this.f23598d = null;
        this.f23599e = null;
        this.f23600f = 0;
        this.f23601g = str;
    }

    public ManyClause(Clause vvVar, Clause vvVar2, Clause[] vvVarArr, String str) {
        this.f23597c = vvVar;
        this.f23598d = vvVar2;
        this.f23599e = vvVarArr;
        this.f23600f = 0;
        this.f23601g = str;
    }

    public ManyClause(Clause[] vvVarArr, String str) {
        this.f23597c = vvVarArr[0];
        if (vvVarArr.length < 2) {
            this.f23598d = null;
            this.f23600f = vvVarArr.length;
        } else {
            this.f23598d = vvVarArr[1];
            this.f23600f = 2;
        }
        this.f23599e = vvVarArr;
        this.f23601g = str;
    }

    @Override // p110z1.Clause
    /* renamed from: a */
    public void mo274a(DatabaseType siVar, String str, StringBuilder sb, List<ArgumentHolder> list) throws SQLException {
        sb.append("(");
        this.f23597c.mo274a(siVar, str, sb, list);
        if (this.f23598d != null) {
            sb.append(this.f23601g);
            sb.append(' ');
            this.f23598d.mo274a(siVar, str, sb, list);
        }
        if (this.f23599e != null) {
            for (int i = this.f23600f; i < this.f23599e.length; i++) {
                sb.append(this.f23601g);
                sb.append(' ');
                this.f23599e[i].mo274a(siVar, str, sb, list);
            }
        }
        sb.append(") ");
    }

    @Override // p110z1.NeedsFutureClause
    /* renamed from: a */
    public void mo280a(Clause vvVar) {
        this.f23598d = vvVar;
    }
}
