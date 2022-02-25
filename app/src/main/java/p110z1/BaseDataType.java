package p110z1;

import java.lang.reflect.Field;
import java.sql.SQLException;

/* renamed from: z1.sv */
/* loaded from: classes3.dex */
public abstract class BaseDataType extends BaseFieldConverter implements DataPersister {
    private final Class<?>[] classes;
    private final SqlType sqlType;

    @Override // p110z1.DataPersister
    public Object convertIdNumber(Number number) {
        return null;
    }

    @Override // p110z1.DataPersister
    public String[] getAssociatedClassNames() {
        return null;
    }

    @Override // p110z1.DataPersister
    public int getDefaultWidth() {
        return 0;
    }

    @Override // p110z1.DataPersister
    public boolean isAppropriateId() {
        return true;
    }

    @Override // p110z1.DataPersister
    public boolean isArgumentHolderRequired() {
        return false;
    }

    @Override // p110z1.DataPersister
    public boolean isComparable() {
        return true;
    }

    @Override // p110z1.DataPersister
    public boolean isEscapedValue() {
        return true;
    }

    @Override // p110z1.DataPersister
    public boolean isPrimitive() {
        return false;
    }

    @Override // p110z1.DataPersister
    public boolean isSelfGeneratedId() {
        return false;
    }

    @Override // p110z1.DataPersister
    public boolean isValidForVersion() {
        return false;
    }

    @Override // p110z1.DataPersister
    public boolean isValidGeneratedType() {
        return false;
    }

    @Override // p110z1.DataPersister
    public Object makeConfigObject(FieldType ssVar) throws SQLException {
        return null;
    }

    @Override // p110z1.DataPersister
    public Object moveToNextValue(Object obj) {
        return null;
    }

    @Override // p110z1.FieldConverter
    public abstract Object parseDefaultString(FieldType ssVar, String str) throws SQLException;

    @Override // p110z1.FieldConverter
    public abstract Object resultToSqlArg(FieldType ssVar, DatabaseResults woVar, int i) throws SQLException;

    public BaseDataType(SqlType suVar, Class<?>[] clsArr) {
        this.sqlType = suVar;
        this.classes = clsArr;
    }

    @Override // p110z1.DataPersister
    public boolean isValidForField(Field field) {
        Class<?>[] clsArr = this.classes;
        if (clsArr.length == 0) {
            return true;
        }
        for (Class<?> cls : clsArr) {
            if (cls.isAssignableFrom(field.getType())) {
                return true;
            }
        }
        return false;
    }

    @Override // p110z1.DataPersister
    public Class<?> getPrimaryClass() {
        Class<?>[] clsArr = this.classes;
        if (clsArr.length == 0) {
            return null;
        }
        return clsArr[0];
    }

    @Override // p110z1.FieldConverter
    public SqlType getSqlType() {
        return this.sqlType;
    }

    @Override // p110z1.DataPersister
    public Class<?>[] getAssociatedClasses() {
        return this.classes;
    }

    @Override // p110z1.DataPersister
    public boolean isEscapedDefaultValue() {
        return isEscapedValue();
    }

    @Override // p110z1.DataPersister
    public Object generateId() {
        throw new IllegalStateException("Should not have tried to generate this type");
    }

    @Override // p110z1.DataPersister
    public boolean dataIsEqual(Object obj, Object obj2) {
        if (obj == null) {
            return obj2 == null;
        }
        if (obj2 == null) {
            return false;
        }
        return obj.equals(obj2);
    }

    @Override // p110z1.FieldConverter
    public Object resultStringToJava(FieldType ssVar, String str, int i) throws SQLException {
        return parseDefaultString(ssVar, str);
    }
}
