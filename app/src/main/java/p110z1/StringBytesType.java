package p110z1;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

/* renamed from: z1.ub */
/* loaded from: classes3.dex */
public class StringBytesType extends BaseDataType {

    /* renamed from: a */
    private static final String f23438a = "Unicode";

    /* renamed from: b */
    private static final StringBytesType f23439b = new StringBytesType();

    @Override // p110z1.BaseDataType, p110z1.DataPersister
    public boolean isAppropriateId() {
        return false;
    }

    @Override // p110z1.BaseDataType, p110z1.DataPersister
    public boolean isArgumentHolderRequired() {
        return true;
    }

    /* renamed from: a */
    public static StringBytesType m634a() {
        return f23439b;
    }

    private StringBytesType() {
        super(SqlType.BYTE_ARRAY, new Class[0]);
    }

    protected StringBytesType(SqlType suVar, Class<?>[] clsArr) {
        super(suVar, clsArr);
    }

    @Override // p110z1.BaseDataType, p110z1.FieldConverter
    public Object parseDefaultString(FieldType ssVar, String str) throws SQLException {
        throw new SQLException("String-bytes type cannot have default values");
    }

    @Override // p110z1.BaseDataType, p110z1.FieldConverter
    public Object resultToSqlArg(FieldType ssVar, DatabaseResults woVar, int i) throws SQLException {
        return woVar.mo214g(i);
    }

    @Override // p110z1.BaseFieldConverter, p110z1.FieldConverter
    public Object sqlArgToJava(FieldType ssVar, Object obj, int i) throws SQLException {
        byte[] bArr = (byte[]) obj;
        String a = m633a(ssVar);
        try {
            return new String(bArr, a);
        } catch (UnsupportedEncodingException e) {
            throw SqlExceptionUtil.m529a("Could not convert string with charset name: " + a, e);
        }
    }

    @Override // p110z1.BaseFieldConverter, p110z1.FieldConverter
    public Object javaToSqlArg(FieldType ssVar, Object obj) throws SQLException {
        String str = (String) obj;
        String a = m633a(ssVar);
        try {
            return str.getBytes(a);
        } catch (UnsupportedEncodingException e) {
            throw SqlExceptionUtil.m529a("Could not convert string with charset name: " + a, e);
        }
    }

    @Override // p110z1.BaseDataType, p110z1.FieldConverter
    public Object resultStringToJava(FieldType ssVar, String str, int i) throws SQLException {
        throw new SQLException("String-bytes type cannot be converted from string to Java");
    }

    @Override // p110z1.BaseDataType, p110z1.DataPersister
    public Class<?> getPrimaryClass() {
        return String.class;
    }

    /* renamed from: a */
    private String m633a(FieldType ssVar) {
        return (ssVar == null || ssVar.m695t() == null) ? f23438a : ssVar.m695t();
    }
}
