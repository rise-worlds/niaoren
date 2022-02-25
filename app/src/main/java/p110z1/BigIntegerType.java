package p110z1;

import java.math.BigInteger;
import java.sql.SQLException;

/* renamed from: z1.ta */
/* loaded from: classes3.dex */
public class BigIntegerType extends BaseDataType {

    /* renamed from: a */
    public static int f23390a = 255;

    /* renamed from: b */
    private static final BigIntegerType f23391b = new BigIntegerType();

    @Override // p110z1.BaseDataType, p110z1.DataPersister
    public boolean isAppropriateId() {
        return false;
    }

    /* renamed from: a */
    public static BigIntegerType m665a() {
        return f23391b;
    }

    protected BigIntegerType() {
        super(SqlType.STRING, new Class[]{BigInteger.class});
    }

    protected BigIntegerType(SqlType suVar, Class<?>[] clsArr) {
        super(suVar, clsArr);
    }

    @Override // p110z1.BaseDataType, p110z1.FieldConverter
    public Object parseDefaultString(FieldType ssVar, String str) throws SQLException {
        try {
            return new BigInteger(str);
        } catch (IllegalArgumentException e) {
            throw SqlExceptionUtil.m529a("Problems with field " + ssVar + " parsing default BigInteger string '" + str + "'", e);
        }
    }

    @Override // p110z1.BaseDataType, p110z1.FieldConverter
    public Object resultToSqlArg(FieldType ssVar, DatabaseResults woVar, int i) throws SQLException {
        return woVar.mo221c(i);
    }

    @Override // p110z1.BaseFieldConverter, p110z1.FieldConverter
    public Object sqlArgToJava(FieldType ssVar, Object obj, int i) throws SQLException {
        try {
            return new BigInteger((String) obj);
        } catch (IllegalArgumentException e) {
            throw SqlExceptionUtil.m529a("Problems with column " + i + " parsing BigInteger string '" + obj + "'", e);
        }
    }

    @Override // p110z1.BaseFieldConverter, p110z1.FieldConverter
    public Object javaToSqlArg(FieldType ssVar, Object obj) {
        return ((BigInteger) obj).toString();
    }

    @Override // p110z1.BaseDataType, p110z1.DataPersister
    public int getDefaultWidth() {
        return f23390a;
    }
}
