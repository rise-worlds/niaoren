package p110z1;

import java.lang.reflect.Field;
import java.sql.SQLException;

/* renamed from: z1.sx */
/* loaded from: classes3.dex */
public abstract class BaseEnumType extends BaseDataType {
    /* JADX INFO: Access modifiers changed from: protected */
    public BaseEnumType(SqlType suVar, Class<?>[] clsArr) {
        super(suVar, clsArr);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public static Enum<?> m674a(FieldType ssVar, Object obj, Enum<?> r3, Enum<?> r4) throws SQLException {
        if (r3 != null) {
            return r3;
        }
        if (r4 != null) {
            return r4;
        }
        throw new SQLException("Cannot get enum value of '" + obj + "' for field " + ssVar);
    }

    @Override // p110z1.BaseDataType, p110z1.DataPersister
    public boolean isValidForField(Field field) {
        return field.getType().isEnum();
    }
}
