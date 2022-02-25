package p110z1;

import java.sql.Driver;
import java.sql.SQLException;
import java.util.List;

/* renamed from: z1.si */
/* loaded from: classes3.dex */
public interface DatabaseType {
    /* renamed from: a */
    String mo907a(String str, FieldType ssVar);

    /* renamed from: a */
    FieldConverter mo878a(DataPersister slVar);

    /* renamed from: a */
    <T> DatabaseTableConfig<T> mo877a(ConnectionSource wmVar, Class<T> cls) throws SQLException;

    /* renamed from: a */
    void mo908a(String str, StringBuilder sb, FieldType ssVar, List<String> list, List<String> list2, List<String> list3, List<String> list4) throws SQLException;

    /* renamed from: a */
    void mo906a(StringBuilder sb);

    /* renamed from: a */
    void mo905a(StringBuilder sb, long j);

    /* renamed from: a */
    void mo904a(StringBuilder sb, long j, Long l);

    /* renamed from: a */
    void mo903a(StringBuilder sb, String str);

    /* renamed from: a */
    void mo902a(Driver driver);

    /* renamed from: a */
    void mo901a(FieldType ssVar, List<String> list, List<String> list2);

    /* renamed from: a */
    void mo900a(FieldType[] ssVarArr, List<String> list, List<String> list2, List<String> list3, List<String> list4) throws SQLException;

    /* renamed from: a */
    boolean mo879a(String str, String str2);

    /* renamed from: b */
    void mo876b() throws SQLException;

    /* renamed from: b */
    void mo899b(StringBuilder sb, String str);

    /* renamed from: b */
    void mo898b(FieldType[] ssVarArr, List<String> list, List<String> list2, List<String> list3, List<String> list4) throws SQLException;

    /* renamed from: c */
    void mo897c(StringBuilder sb, String str);

    /* renamed from: d */
    String mo896d();

    /* renamed from: e */
    boolean mo895e();

    /* renamed from: f */
    boolean mo894f();

    /* renamed from: g */
    boolean mo893g();

    /* renamed from: h */
    boolean mo892h();

    /* renamed from: i */
    boolean mo891i();

    /* renamed from: j */
    boolean mo890j();

    /* renamed from: k */
    boolean mo889k();

    /* renamed from: l */
    boolean mo888l();

    /* renamed from: m */
    boolean mo887m();

    /* renamed from: n */
    boolean mo873n();

    /* renamed from: o */
    String mo886o();

    /* renamed from: p */
    boolean mo872p();

    /* renamed from: q */
    boolean mo885q();

    /* renamed from: r */
    boolean mo884r();

    /* renamed from: s */
    boolean mo883s();

    /* renamed from: t */
    boolean mo882t();

    /* renamed from: u */
    boolean mo881u();

    /* renamed from: v */
    String mo871v();
}
