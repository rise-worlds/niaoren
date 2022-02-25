package p110z1;

import java.lang.reflect.Field;
import java.sql.Timestamp;

/* renamed from: z1.ud */
/* loaded from: classes3.dex */
public class TimeStampType extends DateType {

    /* renamed from: b */
    private static final TimeStampType f23442b = new TimeStampType();

    @Override // p110z1.DateType, p110z1.BaseFieldConverter, p110z1.FieldConverter
    public Object javaToSqlArg(FieldType ssVar, Object obj) {
        return obj;
    }

    @Override // p110z1.DateType, p110z1.BaseFieldConverter, p110z1.FieldConverter
    public Object sqlArgToJava(FieldType ssVar, Object obj, int i) {
        return obj;
    }

    /* renamed from: c */
    public static TimeStampType m631c() {
        return f23442b;
    }

    private TimeStampType() {
        super(SqlType.DATE, new Class[]{Timestamp.class});
    }

    protected TimeStampType(SqlType suVar, Class<?>[] clsArr) {
        super(suVar, clsArr);
    }

    @Override // p110z1.BaseDateType, p110z1.BaseDataType, p110z1.DataPersister
    public boolean isValidForField(Field field) {
        return field.getType() == Timestamp.class;
    }

    @Override // p110z1.BaseDateType, p110z1.BaseDataType, p110z1.DataPersister
    public Object moveToNextValue(Object obj) {
        long currentTimeMillis = System.currentTimeMillis();
        if (obj == null) {
            return new Timestamp(currentTimeMillis);
        }
        if (currentTimeMillis == ((Timestamp) obj).getTime()) {
            return new Timestamp(currentTimeMillis + 1);
        }
        return new Timestamp(currentTimeMillis);
    }
}
