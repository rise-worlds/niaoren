package p110z1;

import java.sql.SQLException;

/* renamed from: z1.tq */
/* loaded from: classes3.dex */
public class FloatObjectType extends BaseDataType {

    /* renamed from: a */
    private static final FloatObjectType f23413a = new FloatObjectType();

    @Override // p110z1.BaseDataType, p110z1.DataPersister
    public boolean isEscapedValue() {
        return false;
    }

    /* renamed from: a */
    public static FloatObjectType m646a() {
        return f23413a;
    }

    private FloatObjectType() {
        super(SqlType.FLOAT, new Class[]{Float.class});
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public FloatObjectType(SqlType suVar, Class<?>[] clsArr) {
        super(suVar, clsArr);
    }

    @Override // p110z1.BaseDataType, p110z1.FieldConverter
    public Object resultToSqlArg(FieldType ssVar, DatabaseResults woVar, int i) throws SQLException {
        return Float.valueOf(woVar.mo207k(i));
    }

    @Override // p110z1.BaseDataType, p110z1.FieldConverter
    public Object parseDefaultString(FieldType ssVar, String str) {
        return Float.valueOf(Float.parseFloat(str));
    }
}
