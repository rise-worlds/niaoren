package p110z1;

/* renamed from: z1.tr */
/* loaded from: classes3.dex */
public class FloatType extends FloatObjectType {

    /* renamed from: a */
    private static final FloatType f23414a = new FloatType();

    @Override // p110z1.BaseDataType, p110z1.DataPersister
    public boolean isPrimitive() {
        return true;
    }

    /* renamed from: b */
    public static FloatType m645b() {
        return f23414a;
    }

    private FloatType() {
        super(SqlType.FLOAT, new Class[]{Float.TYPE});
    }

    protected FloatType(SqlType suVar, Class<?>[] clsArr) {
        super(suVar, clsArr);
    }
}
