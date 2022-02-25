package p110z1;

import java.sql.SQLException;
import java.util.Date;

/* renamed from: z1.ti */
/* loaded from: classes3.dex */
public class DateLongType extends BaseDateType {

    /* renamed from: b */
    private static final DateLongType f23399b = new DateLongType();

    @Override // p110z1.BaseDataType, p110z1.DataPersister
    public boolean isEscapedValue() {
        return false;
    }

    /* renamed from: a */
    public static DateLongType m657a() {
        return f23399b;
    }

    private DateLongType() {
        super(SqlType.LONG, new Class[0]);
    }

    protected DateLongType(SqlType suVar, Class<?>[] clsArr) {
        super(suVar, clsArr);
    }

    @Override // p110z1.BaseDataType, p110z1.FieldConverter
    public Object parseDefaultString(FieldType ssVar, String str) throws SQLException {
        try {
            return Long.valueOf(Long.parseLong(str));
        } catch (NumberFormatException e) {
            throw SqlExceptionUtil.m529a("Problems with field " + ssVar + " parsing default date-long value: " + str, e);
        }
    }

    @Override // p110z1.BaseDataType, p110z1.FieldConverter
    public Object resultToSqlArg(FieldType ssVar, DatabaseResults woVar, int i) throws SQLException {
        return Long.valueOf(woVar.mo209j(i));
    }

    @Override // p110z1.BaseFieldConverter, p110z1.FieldConverter
    public Object sqlArgToJava(FieldType ssVar, Object obj, int i) {
        return new Date(((Long) obj).longValue());
    }

    @Override // p110z1.BaseFieldConverter, p110z1.FieldConverter
    public Object javaToSqlArg(FieldType ssVar, Object obj) {
        return Long.valueOf(((Date) obj).getTime());
    }

    @Override // p110z1.BaseDataType, p110z1.FieldConverter
    public Object resultStringToJava(FieldType ssVar, String str, int i) {
        return sqlArgToJava(ssVar, Long.valueOf(Long.parseLong(str)), i);
    }

    @Override // p110z1.BaseDataType, p110z1.DataPersister
    public Class<?> getPrimaryClass() {
        return Date.class;
    }
}
