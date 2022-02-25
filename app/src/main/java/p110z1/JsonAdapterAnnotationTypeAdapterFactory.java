package p110z1;

/* renamed from: z1.qo */
/* loaded from: classes3.dex */
public final class JsonAdapterAnnotationTypeAdapterFactory implements TypeAdapterFactory {

    /* renamed from: a */
    private final ConstructorConstructor f22914a;

    public JsonAdapterAnnotationTypeAdapterFactory(ConstructorConstructor pyVar) {
        this.f22914a = pyVar;
    }

    @Override // p110z1.TypeAdapterFactory
    /* renamed from: a */
    public <T> TypeAdapter<T> mo1264a(Gson oxVar, TypeToken<T> rdVar) {
        JsonAdapter psVar = (JsonAdapter) rdVar.getRawType().getAnnotation(JsonAdapter.class);
        if (psVar == null) {
            return null;
        }
        return (TypeAdapter<T>) m1335a(this.f22914a, oxVar, rdVar, psVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public TypeAdapter<?> m1335a(ConstructorConstructor pyVar, Gson oxVar, TypeToken<?> rdVar, JsonAdapter psVar) {
        TypeAdapter<?> ppVar;
        Object a = pyVar.m1398a(TypeToken.get((Class) psVar.m1429a())).mo1357a();
        if (a instanceof TypeAdapter) {
            ppVar = (TypeAdapter) a;
        } else if (a instanceof TypeAdapterFactory) {
            ppVar = ((TypeAdapterFactory) a).mo1264a(oxVar, rdVar);
        } else {
            boolean z = a instanceof JsonSerializer;
            if (z || (a instanceof JsonDeserializer)) {
                JsonDeserializer pcVar = null;
                JsonSerializer plVar = z ? (JsonSerializer) a : null;
                if (a instanceof JsonDeserializer) {
                    pcVar = (JsonDeserializer) a;
                }
                ppVar = new TreeTypeAdapter<>(plVar, pcVar, oxVar, rdVar, null);
            } else {
                throw new IllegalArgumentException("Invalid attempt to bind an instance of " + a.getClass().getName() + " as a @JsonAdapter for " + rdVar.toString() + ". @JsonAdapter value must be a TypeAdapter, TypeAdapterFactory, JsonSerializer or JsonDeserializer.");
            }
        }
        return (ppVar == null || !psVar.m1428b()) ? ppVar : ppVar.m1438a();
    }
}
