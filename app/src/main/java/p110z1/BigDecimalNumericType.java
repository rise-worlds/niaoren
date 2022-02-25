package p110z1;

import java.math.BigDecimal;
import java.sql.SQLException;

/* renamed from: z1.sy */
/* loaded from: classes3.dex */
public class BigDecimalNumericType extends BaseDataType {

    /* renamed from: a */
    private static final BigDecimalNumericType f23376a = new BigDecimalNumericType();

    @Override // p110z1.BaseDataType, p110z1.DataPersister
    public boolean isAppropriateId() {
        return false;
    }

    @Override // p110z1.BaseDataType, p110z1.DataPersister
    public boolean isEscapedValue() {
        return false;
    }

    /* renamed from: a */
    public static BigDecimalNumericType m673a() {
        return f23376a;
    }

    private BigDecimalNumericType() {
        super(SqlType.BIG_DECIMAL, new Class[0]);
    }

    protected BigDecimalNumericType(SqlType suVar, Class<?>[] clsArr) {
        super(suVar, clsArr);
    }

    @Override // p110z1.BaseDataType, p110z1.FieldConverter
    public Object parseDefaultString(FieldType ssVar, String str) throws SQLException {
        try {
            return new BigDecimal(str);
        } catch (IllegalArgumentException e) {
            throw SqlExceptionUtil.m529a("Problems with field " + ssVar + " parsing default BigDecimal string '" + str + "'", e);
        }
    }

    @Override // p110z1.BaseDataType, p110z1.FieldConverter
    public Object resultToSqlArg(FieldType ssVar, DatabaseResults woVar, int i) throws SQLException {
        return woVar.mo203o(i);
    }

    @Override // p110z1.BaseDataType, p110z1.DataPersister
    public Class<?> getPrimaryClass() {
        return BigDecimal.class;
    }
}
