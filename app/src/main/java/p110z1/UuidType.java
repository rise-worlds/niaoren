package p110z1;

import java.sql.SQLException;
import java.util.UUID;

/* renamed from: z1.ue */
/* loaded from: classes3.dex */
public class UuidType extends BaseDataType {

    /* renamed from: a */
    public static int f23443a = 48;

    /* renamed from: b */
    private static final UuidType f23444b = new UuidType();

    @Override // p110z1.BaseDataType, p110z1.DataPersister
    public boolean isSelfGeneratedId() {
        return true;
    }

    @Override // p110z1.BaseDataType, p110z1.DataPersister
    public boolean isValidGeneratedType() {
        return true;
    }

    /* renamed from: a */
    public static UuidType m630a() {
        return f23444b;
    }

    private UuidType() {
        super(SqlType.STRING, new Class[]{UUID.class});
    }

    protected UuidType(SqlType suVar, Class<?>[] clsArr) {
        super(suVar, clsArr);
    }

    @Override // p110z1.BaseDataType, p110z1.FieldConverter
    public Object parseDefaultString(FieldType ssVar, String str) throws SQLException {
        try {
            return UUID.fromString(str);
        } catch (IllegalArgumentException e) {
            throw SqlExceptionUtil.m529a("Problems with field " + ssVar + " parsing default UUID-string '" + str + "'", e);
        }
    }

    @Override // p110z1.BaseDataType, p110z1.FieldConverter
    public Object resultToSqlArg(FieldType ssVar, DatabaseResults woVar, int i) throws SQLException {
        return woVar.mo221c(i);
    }

    @Override // p110z1.BaseFieldConverter, p110z1.FieldConverter
    public Object sqlArgToJava(FieldType ssVar, Object obj, int i) throws SQLException {
        String str = (String) obj;
        try {
            return UUID.fromString(str);
        } catch (IllegalArgumentException e) {
            throw SqlExceptionUtil.m529a("Problems with column " + i + " parsing UUID-string '" + str + "'", e);
        }
    }

    @Override // p110z1.BaseFieldConverter, p110z1.FieldConverter
    public Object javaToSqlArg(FieldType ssVar, Object obj) {
        return ((UUID) obj).toString();
    }

    @Override // p110z1.BaseDataType, p110z1.DataPersister
    public Object generateId() {
        return UUID.randomUUID();
    }

    @Override // p110z1.BaseDataType, p110z1.DataPersister
    public int getDefaultWidth() {
        return f23443a;
    }
}
