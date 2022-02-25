package p110z1;

import java.sql.SQLException;

/* renamed from: z1.uc */
/* loaded from: classes3.dex */
public class StringType extends BaseDataType {

    /* renamed from: a */
    public static int f23440a = 255;

    /* renamed from: b */
    private static final StringType f23441b = new StringType();

    @Override // p110z1.BaseDataType, p110z1.FieldConverter
    public Object parseDefaultString(FieldType ssVar, String str) {
        return str;
    }

    /* renamed from: b */
    public static StringType m632b() {
        return f23441b;
    }

    private StringType() {
        super(SqlType.STRING, new Class[]{String.class});
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public StringType(SqlType suVar, Class<?>[] clsArr) {
        super(suVar, clsArr);
    }

    @Override // p110z1.BaseDataType, p110z1.FieldConverter
    public Object resultToSqlArg(FieldType ssVar, DatabaseResults woVar, int i) throws SQLException {
        return woVar.mo221c(i);
    }

    @Override // p110z1.BaseDataType, p110z1.DataPersister
    public int getDefaultWidth() {
        return f23440a;
    }
}
