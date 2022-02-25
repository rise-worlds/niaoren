package p110z1;

import java.io.IOException;
import java.util.ArrayList;

/* renamed from: z1.qs */
/* loaded from: classes3.dex */
public final class ObjectTypeAdapter extends TypeAdapter<Object> {

    /* renamed from: a */
    public static final TypeAdapterFactory f22932a = new TypeAdapterFactory() { // from class: z1.qs.1
        @Override // p110z1.TypeAdapterFactory
        /* renamed from: a */
        public <T> TypeAdapter<T> mo1264a(Gson oxVar, TypeToken<T> rdVar) {
            if (rdVar.getRawType() == Object.class) {
                return new ObjectTypeAdapter(oxVar);
            }
            return null;
        }
    };

    /* renamed from: b */
    private final Gson f22933b;

    ObjectTypeAdapter(Gson oxVar) {
        this.f22933b = oxVar;
    }

    @Override // p110z1.TypeAdapter
    /* renamed from: b */
    public Object mo1234b(JsonReader reVar) throws IOException {
        switch (reVar.mo1205f()) {
            case BEGIN_ARRAY:
                ArrayList arrayList = new ArrayList();
                reVar.mo1219a();
                while (reVar.mo1206e()) {
                    arrayList.add(mo1234b(reVar));
                }
                reVar.mo1214b();
                return arrayList;
            case BEGIN_OBJECT:
                LinkedTreeMap qfVar = new LinkedTreeMap();
                reVar.mo1209c();
                while (reVar.mo1206e()) {
                    qfVar.put(reVar.mo1204g(), mo1234b(reVar));
                }
                reVar.mo1207d();
                return qfVar;
            case STRING:
                return reVar.mo1203h();
            case NUMBER:
                return Double.valueOf(reVar.mo1200k());
            case BOOLEAN:
                return Boolean.valueOf(reVar.mo1202i());
            case NULL:
                reVar.mo1201j();
                return null;
            default:
                throw new IllegalStateException();
        }
    }

    @Override // p110z1.TypeAdapter
    /* renamed from: a */
    public void mo1235a(JsonWriter rhVar, Object obj) throws IOException {
        if (obj == null) {
            rhVar.mo1161f();
            return;
        }
        TypeAdapter a = this.f22933b.m1596a((Class) obj.getClass());
        if (a instanceof ObjectTypeAdapter) {
            rhVar.mo1166d();
            rhVar.mo1163e();
            return;
        }
        a.mo1235a(rhVar, (JsonWriter) obj);
    }
}
