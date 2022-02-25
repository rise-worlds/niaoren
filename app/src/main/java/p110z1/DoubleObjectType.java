package p110z1;

import java.sql.SQLException;

/* renamed from: z1.tm */
/* loaded from: classes3.dex */
public class DoubleObjectType extends BaseDataType {

    /* renamed from: a */
    private static final DoubleObjectType f23408a = new DoubleObjectType();

    @Override // p110z1.BaseDataType, p110z1.DataPersister
    public boolean isEscapedValue() {
        return false;
    }

    /* renamed from: a */
    public static DoubleObjectType m650a() {
        return f23408a;
    }

    private DoubleObjectType() {
        super(SqlType.DOUBLE, new Class[]{Double.class});
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public DoubleObjectType(SqlType suVar, Class<?>[] clsArr) {
        super(suVar, clsArr);
    }

    @Override // p110z1.BaseDataType, p110z1.FieldConverter
    public Object parseDefaultString(FieldType ssVar, String str) {
        return Double.valueOf(Double.parseDouble(str));
    }

    @Override // p110z1.BaseDataType, p110z1.FieldConverter
    public Object resultToSqlArg(FieldType ssVar, DatabaseResults woVar, int i) throws SQLException {
        return Double.valueOf(woVar.mo206l(i));
    }
}
