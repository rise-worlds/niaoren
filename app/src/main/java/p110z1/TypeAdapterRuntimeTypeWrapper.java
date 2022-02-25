package p110z1;

import java.io.IOException;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import p110z1.ReflectiveTypeAdapterFactory;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: z1.qx */
/* loaded from: classes3.dex */
public final class TypeAdapterRuntimeTypeWrapper<T> extends TypeAdapter<T> {

    /* renamed from: a */
    private final Gson f22969a;

    /* renamed from: b */
    private final TypeAdapter<T> f22970b;

    /* renamed from: c */
    private final Type f22971c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public TypeAdapterRuntimeTypeWrapper(Gson oxVar, TypeAdapter<T> ppVar, Type type) {
        this.f22969a = oxVar;
        this.f22970b = ppVar;
        this.f22971c = type;
    }

    @Override // p110z1.TypeAdapter
    /* renamed from: b */
    public T mo1234b(JsonReader reVar) throws IOException {
        return this.f22970b.mo1234b(reVar);
    }

    @Override // p110z1.TypeAdapter
    /* renamed from: a */
    public void mo1235a(JsonWriter rhVar, T t) throws IOException {
        TypeAdapter<T> ppVar = this.f22970b;
        Type a = m1302a(this.f22971c, t);
        if (a != this.f22971c) {
            ppVar = this.f22969a.m1579a((TypeToken) TypeToken.get(a));
            if (ppVar instanceof ReflectiveTypeAdapterFactory.C5492a) {
                TypeAdapter<T> ppVar2 = this.f22970b;
                if (!(ppVar2 instanceof ReflectiveTypeAdapterFactory.C5492a)) {
                    ppVar = ppVar2;
                }
            }
        }
        ppVar.mo1235a(rhVar, (JsonWriter) t);
    }

    /* renamed from: a */
    private Type m1302a(Type type, Object obj) {
        return obj != null ? (type == Object.class || (type instanceof TypeVariable) || (type instanceof Class)) ? obj.getClass() : type : type;
    }
}
