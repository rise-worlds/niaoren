package p110z1;

import java.sql.SQLException;

/* renamed from: z1.tt */
/* loaded from: classes3.dex */
public class IntegerObjectType extends BaseDataType {

    /* renamed from: a */
    private static final IntegerObjectType f23416a = new IntegerObjectType();

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

    /* renamed from: b */
    public static IntegerObjectType m643b() {
        return f23416a;
    }

    private IntegerObjectType() {
        super(SqlType.INTEGER, new Class[]{Integer.class});
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public IntegerObjectType(SqlType suVar, Class<?>[] clsArr) {
        super(suVar, clsArr);
    }

    @Override // p110z1.BaseDataType, p110z1.FieldConverter
    public Object parseDefaultString(FieldType ssVar, String str) {
        return Integer.valueOf(Integer.parseInt(str));
    }

    @Override // p110z1.BaseDataType, p110z1.FieldConverter
    public Object resultToSqlArg(FieldType ssVar, DatabaseResults woVar, int i) throws SQLException {
        return Integer.valueOf(woVar.mo211i(i));
    }

    @Override // p110z1.BaseDataType, p110z1.DataPersister
    public Object convertIdNumber(Number number) {
        return Integer.valueOf(number.intValue());
    }

    @Override // p110z1.BaseDataType, p110z1.DataPersister
    public Object moveToNextValue(Object obj) {
        if (obj == null) {
            return 1;
        }
        return Integer.valueOf(((Integer) obj).intValue() + 1);
    }
}
