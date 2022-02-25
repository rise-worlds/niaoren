package p110z1;

/* renamed from: z1.po */
/* loaded from: classes3.dex */
public enum LongSerializationPolicy {
    DEFAULT { // from class: z1.po.1
        @Override // p110z1.LongSerializationPolicy
        public JsonElement serialize(Long l) {
            return new JsonPrimitive((Number) l);
        }
    },
    STRING { // from class: z1.po.2
        @Override // p110z1.LongSerializationPolicy
        public JsonElement serialize(Long l) {
            return new JsonPrimitive(String.valueOf(l));
        }
    };

    public abstract JsonElement serialize(Long l);
}
