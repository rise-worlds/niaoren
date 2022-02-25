package p110z1;

import java.sql.SQLException;
import java.util.List;
import org.apache.commons.p105io.FilenameUtils;

/* renamed from: z1.we */
/* loaded from: classes3.dex */
public class Not implements Clause, NeedsFutureClause {

    /* renamed from: a */
    private Comparison f23602a = null;

    /* renamed from: b */
    private Exists f23603b = null;

    public Not() {
    }

    public Not(Clause vvVar) {
        mo280a(vvVar);
    }

    @Override // p110z1.NeedsFutureClause
    /* renamed from: a */
    public void mo280a(Clause vvVar) {
        if (this.f23602a != null) {
            throw new IllegalArgumentException("NOT operation already has a comparison set");
        } else if (vvVar instanceof Comparison) {
            this.f23602a = (Comparison) vvVar;
        } else if (vvVar instanceof Exists) {
            this.f23603b = (Exists) vvVar;
        } else {
            throw new IllegalArgumentException("NOT operation can only work with comparison SQL clauses, not " + vvVar);
        }
    }

    @Override // p110z1.Clause
    /* renamed from: a */
    public void mo274a(DatabaseType siVar, String str, StringBuilder sb, List<ArgumentHolder> list) throws SQLException {
        if (this.f23602a == null && this.f23603b == null) {
            throw new IllegalStateException("Clause has not been set in NOT operation");
        }
        if (this.f23602a == null) {
            sb.append("(NOT ");
            this.f23603b.mo274a(siVar, str, sb, list);
        } else {
            sb.append("(NOT ");
            if (str != null) {
                siVar.mo899b(sb, str);
                sb.append(FilenameUtils.EXTENSION_SEPARATOR);
            }
            siVar.mo899b(sb, this.f23602a.mo276a());
            sb.append(' ');
            this.f23602a.mo275a(sb);
            this.f23602a.mo273a(siVar, sb, list);
        }
        sb.append(") ");
    }

    public String toString() {
        if (this.f23602a == null) {
            return "NOT without comparison";
        }
        return "NOT comparison " + this.f23602a;
    }
}
