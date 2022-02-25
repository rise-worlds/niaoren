package p110z1;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/* renamed from: z1.tp */
/* loaded from: classes3.dex */
public class EnumStringType extends BaseEnumType {

    /* renamed from: a */
    public static int f23411a = 100;

    /* renamed from: b */
    private static final EnumStringType f23412b = new EnumStringType();

    @Override // p110z1.BaseDataType, p110z1.FieldConverter
    public Object parseDefaultString(FieldType ssVar, String str) {
        return str;
    }

    /* renamed from: a */
    public static EnumStringType m647a() {
        return f23412b;
    }

    private EnumStringType() {
        super(SqlType.STRING, new Class[]{Enum.class});
    }

    protected EnumStringType(SqlType suVar, Class<?>[] clsArr) {
        super(suVar, clsArr);
    }

    @Override // p110z1.BaseDataType, p110z1.FieldConverter
    public Object resultToSqlArg(FieldType ssVar, DatabaseResults woVar, int i) throws SQLException {
        return woVar.mo221c(i);
    }

    @Override // p110z1.BaseFieldConverter, p110z1.FieldConverter
    public Object sqlArgToJava(FieldType ssVar, Object obj, int i) throws SQLException {
        if (ssVar == null) {
            return obj;
        }
        String str = (String) obj;
        Map map = (Map) ssVar.m711g();
        if (map == null) {
            return m674a(ssVar, str, null, ssVar.m696s());
        }
        return m674a(ssVar, str, (Enum) map.get(str), ssVar.m696s());
    }

    @Override // p110z1.BaseFieldConverter, p110z1.FieldConverter
    public Object javaToSqlArg(FieldType ssVar, Object obj) {
        return ((Enum) obj).name();
    }

    @Override // p110z1.BaseDataType, p110z1.DataPersister
    public Object makeConfigObject(FieldType ssVar) throws SQLException {
        HashMap hashMap = new HashMap();
        Enum[] enumArr = (Enum[]) ssVar.m717d().getEnumConstants();
        if (enumArr != null) {
            for (Enum r3 : enumArr) {
                hashMap.put(r3.name(), r3);
            }
            return hashMap;
        }
        throw new SQLException("Field " + ssVar + " improperly configured as type " + this);
    }

    @Override // p110z1.BaseDataType, p110z1.DataPersister
    public int getDefaultWidth() {
        return f23411a;
    }

    @Override // p110z1.BaseDataType, p110z1.FieldConverter
    public Object resultStringToJava(FieldType ssVar, String str, int i) throws SQLException {
        return sqlArgToJava(ssVar, str, i);
    }
}
