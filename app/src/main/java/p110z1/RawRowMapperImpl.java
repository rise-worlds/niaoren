package p110z1;

import java.sql.SQLException;

/* renamed from: z1.vb */
/* loaded from: classes3.dex */
public class RawRowMapperImpl<T, ID> implements RawRowMapper<T> {

    /* renamed from: a */
    private final TableInfo<T, ID> f23505a;

    public RawRowMapperImpl(TableInfo<T, ID> wuVar) {
        this.f23505a = wuVar;
    }

    @Override // p110z1.RawRowMapper
    /* renamed from: a */
    public T mo448a(String[] strArr, String[] strArr2) throws SQLException {
        T f = this.f23505a.m166f();
        for (int i = 0; i < strArr.length; i++) {
            if (i < strArr2.length) {
                FieldType a = this.f23505a.m173a(strArr[i]);
                a.m727a((Object) f, a.m726a(strArr2[i], i), false, (ObjectCache) null);
            }
        }
        return f;
    }
}
