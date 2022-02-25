package p110z1;

/* renamed from: z1.tg */
/* loaded from: classes3.dex */
public class CharType extends CharacterObjectType {

    /* renamed from: a */
    private static final CharType f23397a = new CharType();

    @Override // p110z1.BaseDataType, p110z1.DataPersister
    public boolean isPrimitive() {
        return true;
    }

    /* renamed from: a */
    public static CharType m659a() {
        return f23397a;
    }

    private CharType() {
        super(SqlType.CHAR, new Class[]{Character.TYPE});
    }

    protected CharType(SqlType suVar, Class<?>[] clsArr) {
        super(suVar, clsArr);
    }

    @Override // p110z1.BaseFieldConverter, p110z1.FieldConverter
    public Object javaToSqlArg(FieldType ssVar, Object obj) {
        Character ch = (Character) obj;
        if (ch == null || ch.charValue() == 0) {
            return null;
        }
        return ch;
    }
}
