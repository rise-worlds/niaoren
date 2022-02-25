package p110z1;

import java.sql.SQLException;

/* renamed from: z1.tu */
/* loaded from: classes3.dex */
public class LongObjectType extends BaseDataType {

    /* renamed from: a */
    private static final LongObjectType f23417a = new LongObjectType();

    @Override // p110z1.BaseDataType, p110z1.DataPersister
    public boolean isEscapedValue() {
        return false;
    }

    @Override // p110z1.BaseDataType, p110z1.DataPersister
    public boolean isValidForVersion() {
        return true;
    }

    @Override // p110z1.BaseDataType, p110z1.DataPersister
    public boolean isValidGeneratedType() {
        return true;
    }

    /* renamed from: a */
    public static LongObjectType m642a() {
        return f23417a;
    }

    private LongObjectType() {
        super(SqlType.LONG, new Class[]{Long.class});
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public LongObjectType(SqlType suVar, Class<?>[] clsArr) {
        super(suVar, clsArr);
    }

    @Override // p110z1.BaseDataType, p110z1.FieldConverter
    public Object parseDefaultString(FieldType ssVar, String str) {
        return Long.valueOf(Long.parseLong(str));
    }

    @Override // p110z1.BaseDataType, p110z1.FieldConverter
    public Object resultToSqlArg(FieldType ssVar, DatabaseResults woVar, int i) throws SQLException {
        return Long.valueOf(woVar.mo209j(i));
    }

    @Override // p110z1.BaseDataType, p110z1.DataPersister
    public Object convertIdNumber(Number number) {
        return Long.valueOf(number.longValue());
    }

    @Override // p110z1.BaseDataType, p110z1.DataPersister
    public Object moveToNextValue(Object obj) {
        if (obj == null) {
            return 1L;
        }
        return Long.valueOf(((Long) obj).longValue() + 1);
    }
}
