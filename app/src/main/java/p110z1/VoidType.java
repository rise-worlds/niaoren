package p110z1;

import java.lang.reflect.Field;

/* renamed from: z1.uf */
/* loaded from: classes3.dex */
public class VoidType extends BaseDataType {
    @Override // p110z1.BaseDataType, p110z1.DataPersister
    public boolean isValidForField(Field field) {
        return false;
    }

    @Override // p110z1.BaseDataType, p110z1.FieldConverter
    public Object parseDefaultString(FieldType ssVar, String str) {
        return null;
    }

    @Override // p110z1.BaseDataType, p110z1.FieldConverter
    public Object resultToSqlArg(FieldType ssVar, DatabaseResults woVar, int i) {
        return null;
    }

    VoidType() {
        super(null, new Class[0]);
    }
}
