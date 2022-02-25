package p110z1;

import java.sql.SQLException;

/* renamed from: z1.te */
/* loaded from: classes3.dex */
public class ByteObjectType extends BaseDataType {

    /* renamed from: a */
    private static final ByteObjectType f23395a = new ByteObjectType();

    @Override // p110z1.BaseDataType, p110z1.DataPersister
    public boolean isEscapedValue() {
        return false;
    }

    /* renamed from: a */
    public static ByteObjectType m661a() {
        return f23395a;
    }

    private ByteObjectType() {
        super(SqlType.BYTE, new Class[]{Byte.class});
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ByteObjectType(SqlType suVar, Class<?>[] clsArr) {
        super(suVar, clsArr);
    }

    @Override // p110z1.BaseDataType, p110z1.FieldConverter
    public Object parseDefaultString(FieldType ssVar, String str) {
        return Byte.valueOf(Byte.parseByte(str));
    }

    @Override // p110z1.BaseDataType, p110z1.FieldConverter
    public Object resultToSqlArg(FieldType ssVar, DatabaseResults woVar, int i) throws SQLException {
        return Byte.valueOf(woVar.mo215f(i));
    }
}
