package p110z1;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;
import p110z1.BaseDateType;

/* renamed from: z1.tl */
/* loaded from: classes3.dex */
public class DateType extends BaseDateType {

    /* renamed from: b */
    private static final DateType f23407b = new DateType();

    @Override // p110z1.BaseDataType, p110z1.DataPersister
    public boolean isArgumentHolderRequired() {
        return true;
    }

    /* renamed from: a */
    public static DateType m651a() {
        return f23407b;
    }

    private DateType() {
        super(SqlType.DATE, new Class[]{Date.class});
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public DateType(SqlType suVar, Class<?>[] clsArr) {
        super(suVar, clsArr);
    }

    @Override // p110z1.BaseDataType, p110z1.FieldConverter
    public Object parseDefaultString(FieldType ssVar, String str) throws SQLException {
        BaseDateType.C5564a a = m679a(ssVar, mo636b());
        try {
            return new Timestamp(m678a(a, str).getTime());
        } catch (ParseException e) {
            throw SqlExceptionUtil.m529a("Problems parsing default date string '" + str + "' using '" + a + '\'', e);
        }
    }

    @Override // p110z1.BaseDataType, p110z1.FieldConverter
    public Object resultToSqlArg(FieldType ssVar, DatabaseResults woVar, int i) throws SQLException {
        return woVar.mo205m(i);
    }

    @Override // p110z1.BaseFieldConverter, p110z1.FieldConverter
    public Object sqlArgToJava(FieldType ssVar, Object obj, int i) {
        return new Date(((Timestamp) obj).getTime());
    }

    @Override // p110z1.BaseFieldConverter, p110z1.FieldConverter
    public Object javaToSqlArg(FieldType ssVar, Object obj) {
        return new Timestamp(((Date) obj).getTime());
    }

    /* renamed from: b */
    protected BaseDateType.C5564a mo636b() {
        return f23372a;
    }
}
