package p110z1;

import java.lang.reflect.Field;
import java.sql.SQLException;

/* renamed from: z1.sl */
/* loaded from: classes3.dex */
public interface DataPersister extends FieldConverter {
    Object convertIdNumber(Number number);

    boolean dataIsEqual(Object obj, Object obj2);

    Object generateId();

    String[] getAssociatedClassNames();

    Class<?>[] getAssociatedClasses();

    int getDefaultWidth();

    Class<?> getPrimaryClass();

    boolean isAppropriateId();

    boolean isArgumentHolderRequired();

    boolean isComparable();

    boolean isEscapedDefaultValue();

    boolean isEscapedValue();

    boolean isPrimitive();

    boolean isSelfGeneratedId();

    boolean isValidForField(Field field);

    boolean isValidForVersion();

    boolean isValidGeneratedType();

    Object makeConfigObject(FieldType ssVar) throws SQLException;

    Object moveToNextValue(Object obj);
}
