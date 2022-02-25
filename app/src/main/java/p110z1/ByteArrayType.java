package p110z1;

import java.sql.SQLException;
import java.util.Arrays;

/* renamed from: z1.td */
/* loaded from: classes3.dex */
public class ByteArrayType extends BaseDataType {

    /* renamed from: a */
    private static final ByteArrayType f23394a = new ByteArrayType();

    @Override // p110z1.BaseDataType, p110z1.DataPersister
    public boolean isAppropriateId() {
        return false;
    }

    @Override // p110z1.BaseDataType, p110z1.DataPersister
    public boolean isArgumentHolderRequired() {
        return true;
    }

    /* renamed from: a */
    public static ByteArrayType m662a() {
        return f23394a;
    }

    private ByteArrayType() {
        super(SqlType.BYTE_ARRAY, new Class[0]);
    }

    protected ByteArrayType(SqlType suVar, Class<?>[] clsArr) {
        super(suVar, clsArr);
    }

    @Override // p110z1.BaseDataType, p110z1.FieldConverter
    public Object parseDefaultString(FieldType ssVar, String str) throws SQLException {
        throw new SQLException("byte[] type cannot have default values");
    }

    @Override // p110z1.BaseDataType, p110z1.FieldConverter
    public Object resultToSqlArg(FieldType ssVar, DatabaseResults woVar, int i) throws SQLException {
        return woVar.mo214g(i);
    }

    @Override // p110z1.BaseDataType, p110z1.DataPersister
    public boolean dataIsEqual(Object obj, Object obj2) {
        if (obj == null) {
            return obj2 == null;
        }
        if (obj2 == null) {
            return false;
        }
        return Arrays.equals((byte[]) obj, (byte[]) obj2);
    }

    @Override // p110z1.BaseDataType, p110z1.FieldConverter
    public Object resultStringToJava(FieldType ssVar, String str, int i) throws SQLException {
        throw new SQLException("byte[] type cannot be converted from string to Java");
    }

    @Override // p110z1.BaseDataType, p110z1.DataPersister
    public Class<?> getPrimaryClass() {
        return byte[].class;
    }
}
