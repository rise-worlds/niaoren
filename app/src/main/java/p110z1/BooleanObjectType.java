package p110z1;

import java.sql.SQLException;

/* renamed from: z1.tb */
/* loaded from: classes3.dex */
public class BooleanObjectType extends BaseDataType {

    /* renamed from: a */
    private static final BooleanObjectType f23392a = new BooleanObjectType();

    @Override // p110z1.BaseDataType, p110z1.DataPersister
    public boolean isAppropriateId() {
        return false;
    }

    @Override // p110z1.BaseDataType, p110z1.DataPersister
    public boolean isEscapedValue() {
        return false;
    }

    /* renamed from: a */
    public static BooleanObjectType m664a() {
        return f23392a;
    }

    private BooleanObjectType() {
        super(SqlType.BOOLEAN, new Class[]{Boolean.class});
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public BooleanObjectType(SqlType suVar, Class<?>[] clsArr) {
        super(suVar, clsArr);
    }

    @Override // p110z1.BaseDataType, p110z1.FieldConverter
    public Object parseDefaultString(FieldType ssVar, String str) {
        return Boolean.valueOf(Boolean.parseBoolean(str));
    }

    @Override // p110z1.BaseDataType, p110z1.FieldConverter
    public Object resultToSqlArg(FieldType ssVar, DatabaseResults woVar, int i) throws SQLException {
        return Boolean.valueOf(woVar.mo219d(i));
    }
}
