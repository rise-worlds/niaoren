package p110z1;

/* renamed from: z1.tz */
/* loaded from: classes3.dex */
public class ShortType extends ShortObjectType {

    /* renamed from: a */
    private static final ShortType f23422a = new ShortType();

    @Override // p110z1.BaseDataType, p110z1.DataPersister
    public boolean isPrimitive() {
        return true;
    }

    /* renamed from: b */
    public static ShortType m637b() {
        return f23422a;
    }

    private ShortType() {
        super(SqlType.SHORT, new Class[]{Short.TYPE});
    }

    protected ShortType(SqlType suVar, Class<?>[] clsArr) {
        super(suVar, clsArr);
    }
}
