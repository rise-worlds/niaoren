package p110z1;

import java.util.List;

/* renamed from: z1.wg */
/* loaded from: classes3.dex */
public class Raw implements Clause {

    /* renamed from: a */
    private final String f23606a;

    /* renamed from: b */
    private final ArgumentHolder[] f23607b;

    public Raw(String str, ArgumentHolder[] upVarArr) {
        this.f23606a = str;
        this.f23607b = upVarArr;
    }

    @Override // p110z1.Clause
    /* renamed from: a */
    public void mo274a(DatabaseType siVar, String str, StringBuilder sb, List<ArgumentHolder> list) {
        sb.append(this.f23606a);
        sb.append(' ');
        for (ArgumentHolder upVar : this.f23607b) {
            list.add(upVar);
        }
    }
}
