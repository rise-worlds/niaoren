package p110z1;

import java.sql.SQLException;
import java.sql.Savepoint;
import p110z1.StatementBuilder;

/* renamed from: z1.wn */
/* loaded from: classes3.dex */
public interface DatabaseConnection {

    /* renamed from: a */
    public static final Object f23620a = new Object();

    /* renamed from: b */
    public static final int f23621b = -1;

    /* renamed from: a */
    int mo244a(String str, int i) throws SQLException;

    /* renamed from: a */
    int mo241a(String str, Object[] objArr, FieldType[] ssVarArr) throws SQLException;

    /* renamed from: a */
    int mo239a(String str, Object[] objArr, FieldType[] ssVarArr, GeneratedKeyHolder wpVar) throws SQLException;

    /* renamed from: a */
    <T> Object mo240a(String str, Object[] objArr, FieldType[] ssVarArr, GenericRowMapper<T> utVar, ObjectCache scVar) throws SQLException;

    /* renamed from: a */
    Savepoint mo245a(String str) throws SQLException;

    /* renamed from: a */
    CompiledStatement mo243a(String str, StatementBuilder.EnumC5579b bVar, FieldType[] ssVarArr) throws SQLException;

    /* renamed from: a */
    CompiledStatement mo242a(String str, StatementBuilder.EnumC5579b bVar, FieldType[] ssVarArr, int i) throws SQLException;

    /* renamed from: a */
    void mo238a(Savepoint savepoint) throws SQLException;

    /* renamed from: a */
    void mo237a(boolean z) throws SQLException;

    /* renamed from: a */
    boolean mo246a() throws SQLException;

    /* renamed from: b */
    int mo234b(String str, Object[] objArr, FieldType[] ssVarArr) throws SQLException;

    /* renamed from: b */
    long mo235b(String str) throws SQLException;

    /* renamed from: b */
    void mo233b(Savepoint savepoint) throws SQLException;

    /* renamed from: b */
    boolean mo236b() throws SQLException;

    /* renamed from: c */
    long mo231c(String str, Object[] objArr, FieldType[] ssVarArr) throws SQLException;

    /* renamed from: c */
    boolean mo232c(String str) throws SQLException;

    /* renamed from: d */
    void mo230d() throws SQLException;

    /* renamed from: e */
    void mo229e();

    /* renamed from: f */
    boolean mo228f() throws SQLException;
}
