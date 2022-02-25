package p110z1;

import java.sql.SQLException;

/* renamed from: z1.ty */
/* loaded from: classes3.dex */
public class ShortObjectType extends BaseDataType {

    /* renamed from: a */
    private static final ShortObjectType f23421a = new ShortObjectType();

    @Override // p110z1.BaseDataType, p110z1.DataPersister
    public boolean isEscapedValue() {
        return false;
    }

    @Override // p110z1.BaseDataType, p110z1.DataPersister
    public boolean isValidForVersion() {
        return true;
    }

    /* renamed from: a */
    public static ShortObjectType m638a() {
        return f23421a;
    }

    private ShortObjectType() {
        super(SqlType.SHORT, new Class[]{Short.class});
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ShortObjectType(SqlType suVar, Class<?>[] clsArr) {
        super(suVar, clsArr);
    }

    @Override // p110z1.BaseDataType, p110z1.FieldConverter
    public Object parseDefaultString(FieldType ssVar, String str) {
        return Short.valueOf(Short.parseShort(str));
    }

    @Override // p110z1.BaseDataType, p110z1.FieldConverter
    public Object resultToSqlArg(FieldType ssVar, DatabaseResults woVar, int i) throws SQLException {
        return Short.valueOf(woVar.mo213h(i));
    }

    @Override // p110z1.BaseDataType, p110z1.DataPersister
    public Object moveToNextValue(Object obj) {
        if (obj == null) {
            return (short) 1;
        }
        return Short.valueOf((short) (((Short) obj).shortValue() + 1));
    }
}
