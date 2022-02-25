package p110z1;

import java.math.BigDecimal;
import java.sql.SQLException;

/* renamed from: z1.sz */
/* loaded from: classes3.dex */
public class BigDecimalStringType extends BaseDataType {

    /* renamed from: a */
    public static int f23377a = 255;

    /* renamed from: b */
    private static final BigDecimalStringType f23378b = new BigDecimalStringType();

    @Override // p110z1.BaseDataType, p110z1.DataPersister
    public boolean isAppropriateId() {
        return false;
    }

    /* renamed from: a */
    public static BigDecimalStringType m672a() {
        return f23378b;
    }

    private BigDecimalStringType() {
        super(SqlType.STRING, new Class[]{BigDecimal.class});
    }

    protected BigDecimalStringType(SqlType suVar, Class<?>[] clsArr) {
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
        return woVar.mo221c(i);
    }

    @Override // p110z1.BaseFieldConverter, p110z1.FieldConverter
    public Object sqlArgToJava(FieldType ssVar, Object obj, int i) throws SQLException {
        try {
            return new BigDecimal((String) obj);
        } catch (IllegalArgumentException e) {
            throw SqlExceptionUtil.m529a("Problems with column " + i + " parsing BigDecimal string '" + obj + "'", e);
        }
    }

    @Override // p110z1.BaseFieldConverter, p110z1.FieldConverter
    public Object javaToSqlArg(FieldType ssVar, Object obj) {
        return ((BigDecimal) obj).toString();
    }

    @Override // p110z1.BaseDataType, p110z1.DataPersister
    public int getDefaultWidth() {
        return f23377a;
    }
}
