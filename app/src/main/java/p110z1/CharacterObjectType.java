package p110z1;

import java.sql.SQLException;

/* renamed from: z1.th */
/* loaded from: classes3.dex */
public class CharacterObjectType extends BaseDataType {

    /* renamed from: a */
    private static final CharacterObjectType f23398a = new CharacterObjectType();

    /* renamed from: b */
    public static CharacterObjectType m658b() {
        return f23398a;
    }

    private CharacterObjectType() {
        super(SqlType.CHAR, new Class[]{Character.class});
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public CharacterObjectType(SqlType suVar, Class<?>[] clsArr) {
        super(suVar, clsArr);
    }

    @Override // p110z1.BaseDataType, p110z1.FieldConverter
    public Object parseDefaultString(FieldType ssVar, String str) throws SQLException {
        if (str.length() == 1) {
            return Character.valueOf(str.charAt(0));
        }
        throw new SQLException("Problems with field " + ssVar + ", default string to long for Character: '" + str + "'");
    }

    @Override // p110z1.BaseDataType, p110z1.FieldConverter
    public Object resultToSqlArg(FieldType ssVar, DatabaseResults woVar, int i) throws SQLException {
        return Character.valueOf(woVar.mo217e(i));
    }
}
