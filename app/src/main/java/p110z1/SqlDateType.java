package p110z1;

import java.lang.reflect.Field;
import java.sql.Date;
import java.sql.Timestamp;
import org.apache.tools.ant.util.DateUtils;
import p110z1.BaseDateType;

/* renamed from: z1.ua */
/* loaded from: classes3.dex */
public class SqlDateType extends DateType {

    /* renamed from: b */
    private static final SqlDateType f23436b = new SqlDateType();

    /* renamed from: c */
    private static final BaseDateType.C5564a f23437c = new BaseDateType.C5564a(DateUtils.ISO8601_DATE_PATTERN);

    /* renamed from: c */
    public static SqlDateType m635c() {
        return f23436b;
    }

    private SqlDateType() {
        super(SqlType.DATE, new Class[]{Date.class});
    }

    protected SqlDateType(SqlType suVar, Class<?>[] clsArr) {
        super(suVar, clsArr);
    }

    @Override // p110z1.DateType, p110z1.BaseFieldConverter, p110z1.FieldConverter
    public Object sqlArgToJava(FieldType ssVar, Object obj, int i) {
        return new Date(((Timestamp) obj).getTime());
    }

    @Override // p110z1.DateType, p110z1.BaseFieldConverter, p110z1.FieldConverter
    public Object javaToSqlArg(FieldType ssVar, Object obj) {
        return new Timestamp(((Date) obj).getTime());
    }

    @Override // p110z1.DateType
    /* renamed from: b */
    protected BaseDateType.C5564a mo636b() {
        return f23437c;
    }

    @Override // p110z1.BaseDataType, p110z1.FieldConverter
    public Object resultStringToJava(FieldType ssVar, String str, int i) {
        return sqlArgToJava(ssVar, Timestamp.valueOf(str), i);
    }

    @Override // p110z1.BaseDateType, p110z1.BaseDataType, p110z1.DataPersister
    public boolean isValidForField(Field field) {
        return field.getType() == Date.class;
    }
}
