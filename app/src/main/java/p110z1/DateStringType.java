package p110z1;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import p110z1.BaseDateType;

/* renamed from: z1.tj */
/* loaded from: classes3.dex */
public class DateStringType extends BaseDateType {

    /* renamed from: b */
    public static int f23400b = 50;

    /* renamed from: c */
    private static final DateStringType f23401c = new DateStringType();

    /* renamed from: a */
    public static DateStringType m656a() {
        return f23401c;
    }

    private DateStringType() {
        super(SqlType.STRING, new Class[0]);
    }

    protected DateStringType(SqlType suVar, Class<?>[] clsArr) {
        super(suVar, clsArr);
    }

    @Override // p110z1.BaseDataType, p110z1.FieldConverter
    public Object parseDefaultString(FieldType ssVar, String str) throws SQLException {
        BaseDateType.C5564a a = m679a(ssVar, f23372a);
        try {
            return m677b(a, str);
        } catch (ParseException e) {
            throw SqlExceptionUtil.m529a("Problems with field " + ssVar + " parsing default date-string '" + str + "' using '" + a + "'", e);
        }
    }

    @Override // p110z1.BaseDataType, p110z1.FieldConverter
    public Object resultToSqlArg(FieldType ssVar, DatabaseResults woVar, int i) throws SQLException {
        return woVar.mo221c(i);
    }

    @Override // p110z1.BaseFieldConverter, p110z1.FieldConverter
    public Object sqlArgToJava(FieldType ssVar, Object obj, int i) throws SQLException {
        String str = (String) obj;
        BaseDateType.C5564a a = m679a(ssVar, f23372a);
        try {
            return m678a(a, str);
        } catch (ParseException e) {
            throw SqlExceptionUtil.m529a("Problems with column " + i + " parsing date-string '" + str + "' using '" + a + "'", e);
        }
    }

    @Override // p110z1.BaseFieldConverter, p110z1.FieldConverter
    public Object javaToSqlArg(FieldType ssVar, Object obj) {
        return m679a(ssVar, f23372a).m676a().format((Date) obj);
    }

    @Override // p110z1.BaseDataType, p110z1.DataPersister
    public Object makeConfigObject(FieldType ssVar) {
        String t = ssVar.m695t();
        if (t == null) {
            return f23372a;
        }
        return new BaseDateType.C5564a(t);
    }

    @Override // p110z1.BaseDataType, p110z1.DataPersister
    public int getDefaultWidth() {
        return f23400b;
    }

    @Override // p110z1.BaseDataType, p110z1.FieldConverter
    public Object resultStringToJava(FieldType ssVar, String str, int i) throws SQLException {
        return sqlArgToJava(ssVar, str, i);
    }

    @Override // p110z1.BaseDataType, p110z1.DataPersister
    public Class<?> getPrimaryClass() {
        return byte[].class;
    }
}
