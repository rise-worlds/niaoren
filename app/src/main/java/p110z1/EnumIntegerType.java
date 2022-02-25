package p110z1;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/* renamed from: z1.to */
/* loaded from: classes3.dex */
public class EnumIntegerType extends BaseEnumType {

    /* renamed from: a */
    private static final EnumIntegerType f23410a = new EnumIntegerType();

    @Override // p110z1.BaseDataType, p110z1.DataPersister
    public boolean isEscapedValue() {
        return false;
    }

    /* renamed from: a */
    public static EnumIntegerType m648a() {
        return f23410a;
    }

    private EnumIntegerType() {
        super(SqlType.INTEGER, new Class[0]);
    }

    protected EnumIntegerType(SqlType suVar, Class<?>[] clsArr) {
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

    @Override // p110z1.BaseFieldConverter, p110z1.FieldConverter
    public Object sqlArgToJava(FieldType ssVar, Object obj, int i) throws SQLException {
        if (ssVar == null) {
            return obj;
        }
        Integer num = (Integer) obj;
        Map map = (Map) ssVar.m711g();
        if (map == null) {
            return m674a(ssVar, num, null, ssVar.m696s());
        }
        return m674a(ssVar, num, (Enum) map.get(num), ssVar.m696s());
    }

    @Override // p110z1.BaseFieldConverter, p110z1.FieldConverter
    public Object javaToSqlArg(FieldType ssVar, Object obj) {
        return Integer.valueOf(((Enum) obj).ordinal());
    }

    @Override // p110z1.BaseDataType, p110z1.DataPersister
    public Object makeConfigObject(FieldType ssVar) throws SQLException {
        HashMap hashMap = new HashMap();
        Enum[] enumArr = (Enum[]) ssVar.m717d().getEnumConstants();
        if (enumArr != null) {
            for (Enum r3 : enumArr) {
                hashMap.put(Integer.valueOf(r3.ordinal()), r3);
            }
            return hashMap;
        }
        throw new SQLException("Field " + ssVar + " improperly configured as type " + this);
    }

    @Override // p110z1.BaseDataType, p110z1.FieldConverter
    public Object resultStringToJava(FieldType ssVar, String str, int i) throws SQLException {
        return sqlArgToJava(ssVar, Integer.valueOf(Integer.parseInt(str)), i);
    }

    @Override // p110z1.BaseDataType, p110z1.DataPersister
    public Class<?> getPrimaryClass() {
        return Integer.TYPE;
    }
}
