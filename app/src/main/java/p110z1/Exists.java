package p110z1;

import java.sql.SQLException;
import java.util.List;
import p110z1.QueryBuilder;

/* renamed from: z1.vx */
/* loaded from: classes3.dex */
public class Exists implements Clause {

    /* renamed from: a */
    private final QueryBuilder.C5575a f23590a;

    public Exists(QueryBuilder.C5575a aVar) {
        this.f23590a = aVar;
    }

    @Override // p110z1.Clause
    /* renamed from: a */
    public void mo274a(DatabaseType siVar, String str, StringBuilder sb, List<ArgumentHolder> list) throws SQLException {
        sb.append("EXISTS (");
        this.f23590a.m456a(sb, list);
        sb.append(") ");
    }
}
