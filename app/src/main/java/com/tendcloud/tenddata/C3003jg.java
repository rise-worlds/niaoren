package com.tendcloud.tenddata;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.jg */
/* loaded from: classes2.dex */
public class C3003jg {
    public static final int TYPE_BOOL = 8;
    public static final int TYPE_BYTES = 12;
    public static final int TYPE_DOUBLE = 1;
    public static final int TYPE_ENUM = 14;
    public static final int TYPE_FIXED32 = 7;
    public static final int TYPE_FIXED64 = 6;
    public static final int TYPE_FLOAT = 2;
    public static final int TYPE_GROUP = 10;
    public static final int TYPE_INT32 = 5;
    public static final int TYPE_INT64 = 3;
    public static final int TYPE_MESSAGE = 11;
    public static final int TYPE_SFIXED32 = 15;
    public static final int TYPE_SFIXED64 = 16;
    public static final int TYPE_SINT32 = 17;
    public static final int TYPE_SINT64 = 18;
    public static final int TYPE_STRING = 9;
    public static final int TYPE_UINT32 = 13;
    public static final int TYPE_UINT64 = 4;
    protected final Class clazz;
    protected final boolean repeated;
    public final int tag;
    protected final int type;

    @Deprecated
    public static C3003jg createMessageTyped(int i, Class cls, int i2) {
        return new C3003jg(i, cls, i2, false);
    }

    public static C3003jg createMessageTyped(int i, Class cls, long j) {
        return new C3003jg(i, cls, (int) j, false);
    }

    public static C3003jg createRepeatedMessageTyped(int i, Class cls, long j) {
        return new C3003jg(i, cls, (int) j, true);
    }

    public static C3003jg createPrimitiveTyped(int i, Class cls, long j) {
        return new C3004a(i, cls, (int) j, false, 0, 0);
    }

    public static C3003jg createRepeatedPrimitiveTyped(int i, Class cls, long j, long j2, long j3) {
        return new C3004a(i, cls, (int) j, true, (int) j2, (int) j3);
    }

    private C3003jg(int i, Class cls, int i2, boolean z) {
        this.type = i;
        this.clazz = cls;
        this.tag = i2;
        this.repeated = z;
    }

    final Object getValueFrom(List list) {
        if (list == null) {
            return null;
        }
        return this.repeated ? getRepeatedValueFrom(list) : getSingularValueFrom(list);
    }

    private Object getRepeatedValueFrom(List list) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            C3012jo joVar = (C3012jo) list.get(i);
            if (joVar.f14334b.length != 0) {
                readDataInto(joVar, arrayList);
            }
        }
        int size = arrayList.size();
        if (size == 0) {
            return null;
        }
        Class cls = this.clazz;
        Object cast = cls.cast(Array.newInstance(cls.getComponentType(), size));
        for (int i2 = 0; i2 < size; i2++) {
            Array.set(cast, i2, arrayList.get(i2));
        }
        return cast;
    }

    private Object getSingularValueFrom(List list) {
        if (list.isEmpty()) {
            return null;
        }
        return this.clazz.cast(readData(C2999jd.m15374a(((C3012jo) list.get(list.size() - 1)).f14334b)));
    }

    protected Object readData(C2999jd jdVar) {
        Class<?> componentType = this.repeated ? this.clazz.getComponentType() : this.clazz;
        try {
            switch (this.type) {
                case 10:
                    AbstractC3010jm jmVar = (AbstractC3010jm) componentType.newInstance();
                    jdVar.m15375a(jmVar, C3013jp.m15240b(this.tag));
                    return jmVar;
                case 11:
                    AbstractC3010jm jmVar2 = (AbstractC3010jm) componentType.newInstance();
                    jdVar.readMessage(jmVar2);
                    return jmVar2;
                default:
                    throw new IllegalArgumentException("Unknown type " + this.type);
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("Error reading extension field", e);
        } catch (IllegalAccessException e2) {
            throw new IllegalArgumentException("Error creating instance of class " + componentType, e2);
        } catch (InstantiationException e3) {
            throw new IllegalArgumentException("Error creating instance of class " + componentType, e3);
        }
    }

    protected void readDataInto(C3012jo joVar, List list) {
        list.add(readData(C2999jd.m15374a(joVar.f14334b)));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void writeTo(Object obj, C3000je jeVar) {
        if (this.repeated) {
            writeRepeatedData(obj, jeVar);
        } else {
            writeSingularData(obj, jeVar);
        }
    }

    protected void writeSingularData(Object obj, C3000je jeVar) {
        try {
            jeVar.writeRawVarint32(this.tag);
            switch (this.type) {
                case 10:
                    int b = C3013jp.m15240b(this.tag);
                    jeVar.writeGroupNoTag((AbstractC3010jm) obj);
                    jeVar.m15291h(b, 4);
                    return;
                case 11:
                    jeVar.writeMessageNoTag((AbstractC3010jm) obj);
                    return;
                default:
                    throw new IllegalArgumentException("Unknown type " + this.type);
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    protected void writeRepeatedData(Object obj, C3000je jeVar) {
        int length = Array.getLength(obj);
        for (int i = 0; i < length; i++) {
            Object obj2 = Array.get(obj, i);
            if (obj2 != null) {
                writeSingularData(obj2, jeVar);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int computeSerializedSize(Object obj) {
        if (this.repeated) {
            return computeRepeatedSerializedSize(obj);
        }
        return computeSingularSerializedSize(obj);
    }

    protected int computeRepeatedSerializedSize(Object obj) {
        int length = Array.getLength(obj);
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            if (Array.get(obj, i2) != null) {
                i += computeSingularSerializedSize(Array.get(obj, i2));
            }
        }
        return i;
    }

    protected int computeSingularSerializedSize(Object obj) {
        int b = C3013jp.m15240b(this.tag);
        switch (this.type) {
            case 10:
                return C3000je.m15337a(b, (AbstractC3010jm) obj);
            case 11:
                return C3000je.m15319b(b, (AbstractC3010jm) obj);
            default:
                throw new IllegalArgumentException("Unknown type " + this.type);
        }
    }

    /* compiled from: td */
    /* renamed from: com.tendcloud.tenddata.jg$a */
    /* loaded from: classes2.dex */
    static class C3004a extends C3003jg {
        private final int nonPackedTag;
        private final int packedTag;

        public C3004a(int i, Class cls, int i2, boolean z, int i3, int i4) {
            super(i, cls, i2, z);
            this.nonPackedTag = i3;
            this.packedTag = i4;
        }

        @Override // com.tendcloud.tenddata.C3003jg
        protected Object readData(C2999jd jdVar) {
            try {
                switch (this.type) {
                    case 1:
                        return Double.valueOf(jdVar.m15370c());
                    case 2:
                        return Float.valueOf(jdVar.m15368d());
                    case 3:
                        return Long.valueOf(jdVar.m15365f());
                    case 4:
                        return Long.valueOf(jdVar.m15366e());
                    case 5:
                        return Integer.valueOf(jdVar.m15364g());
                    case 6:
                        return Long.valueOf(jdVar.m15363h());
                    case 7:
                        return Integer.valueOf(jdVar.m15362i());
                    case 8:
                        return Boolean.valueOf(jdVar.m15361j());
                    case 9:
                        return jdVar.m15360k();
                    case 10:
                    case 11:
                    default:
                        throw new IllegalArgumentException("Unknown type " + this.type);
                    case 12:
                        return jdVar.m15359l();
                    case 13:
                        return Integer.valueOf(jdVar.m15358m());
                    case 14:
                        return Integer.valueOf(jdVar.m15357n());
                    case 15:
                        return Integer.valueOf(jdVar.m15356o());
                    case 16:
                        return Long.valueOf(jdVar.m15355p());
                    case 17:
                        return Integer.valueOf(jdVar.m15354q());
                    case 18:
                        return Long.valueOf(jdVar.m15353r());
                }
            } catch (IOException e) {
                throw new IllegalArgumentException("Error reading extension field", e);
            }
        }

        @Override // com.tendcloud.tenddata.C3003jg
        protected void readDataInto(C3012jo joVar, List list) {
            if (joVar.f14333a == this.nonPackedTag) {
                list.add(readData(C2999jd.m15374a(joVar.f14334b)));
                return;
            }
            C2999jd a = C2999jd.m15374a(joVar.f14334b);
            try {
                a.m15369c(a.m15352s());
                while (!a.m15348w()) {
                    list.add(readData(a));
                }
            } catch (IOException e) {
                throw new IllegalArgumentException("Error reading extension field", e);
            }
        }

        @Override // com.tendcloud.tenddata.C3003jg
        protected final void writeSingularData(Object obj, C3000je jeVar) {
            try {
                jeVar.writeRawVarint32(this.tag);
                switch (this.type) {
                    case 1:
                        jeVar.writeDoubleNoTag(((Double) obj).doubleValue());
                        return;
                    case 2:
                        jeVar.writeFloatNoTag(((Float) obj).floatValue());
                        return;
                    case 3:
                        jeVar.writeInt64NoTag(((Long) obj).longValue());
                        return;
                    case 4:
                        jeVar.writeUInt64NoTag(((Long) obj).longValue());
                        return;
                    case 5:
                        jeVar.writeInt32NoTag(((Integer) obj).intValue());
                        return;
                    case 6:
                        jeVar.writeFixed64NoTag(((Long) obj).longValue());
                        return;
                    case 7:
                        jeVar.writeFixed32NoTag(((Integer) obj).intValue());
                        return;
                    case 8:
                        jeVar.writeBoolNoTag(((Boolean) obj).booleanValue());
                        return;
                    case 9:
                        jeVar.writeStringNoTag((String) obj);
                        return;
                    case 10:
                    case 11:
                    default:
                        throw new IllegalArgumentException("Unknown type " + this.type);
                    case 12:
                        jeVar.writeBytesNoTag((byte[]) obj);
                        return;
                    case 13:
                        jeVar.writeUInt32NoTag(((Integer) obj).intValue());
                        return;
                    case 14:
                        jeVar.writeEnumNoTag(((Integer) obj).intValue());
                        return;
                    case 15:
                        jeVar.writeSFixed32NoTag(((Integer) obj).intValue());
                        return;
                    case 16:
                        jeVar.writeSFixed64NoTag(((Long) obj).longValue());
                        return;
                    case 17:
                        jeVar.writeSInt32NoTag(((Integer) obj).intValue());
                        return;
                    case 18:
                        jeVar.writeSInt64NoTag(((Long) obj).longValue());
                        return;
                }
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }

        @Override // com.tendcloud.tenddata.C3003jg
        protected void writeRepeatedData(Object obj, C3000je jeVar) {
            if (this.tag == this.nonPackedTag) {
                C3003jg.super.writeRepeatedData(obj, jeVar);
            } else if (this.tag == this.packedTag) {
                int length = Array.getLength(obj);
                int computePackedDataSize = computePackedDataSize(obj);
                try {
                    jeVar.writeRawVarint32(this.tag);
                    jeVar.writeRawVarint32(computePackedDataSize);
                    int i = this.type;
                    int i2 = 0;
                    switch (i) {
                        case 1:
                            while (i2 < length) {
                                jeVar.writeDoubleNoTag(Array.getDouble(obj, i2));
                                i2++;
                            }
                            return;
                        case 2:
                            while (i2 < length) {
                                jeVar.writeFloatNoTag(Array.getFloat(obj, i2));
                                i2++;
                            }
                            return;
                        case 3:
                            while (i2 < length) {
                                jeVar.writeInt64NoTag(Array.getLong(obj, i2));
                                i2++;
                            }
                            return;
                        case 4:
                            while (i2 < length) {
                                jeVar.writeUInt64NoTag(Array.getLong(obj, i2));
                                i2++;
                            }
                            return;
                        case 5:
                            while (i2 < length) {
                                jeVar.writeInt32NoTag(Array.getInt(obj, i2));
                                i2++;
                            }
                            return;
                        case 6:
                            while (i2 < length) {
                                jeVar.writeFixed64NoTag(Array.getLong(obj, i2));
                                i2++;
                            }
                            return;
                        case 7:
                            while (i2 < length) {
                                jeVar.writeFixed32NoTag(Array.getInt(obj, i2));
                                i2++;
                            }
                            return;
                        case 8:
                            while (i2 < length) {
                                jeVar.writeBoolNoTag(Array.getBoolean(obj, i2));
                                i2++;
                            }
                            return;
                        default:
                            switch (i) {
                                case 13:
                                    while (i2 < length) {
                                        jeVar.writeUInt32NoTag(Array.getInt(obj, i2));
                                        i2++;
                                    }
                                    return;
                                case 14:
                                    while (i2 < length) {
                                        jeVar.writeEnumNoTag(Array.getInt(obj, i2));
                                        i2++;
                                    }
                                    return;
                                case 15:
                                    while (i2 < length) {
                                        jeVar.writeSFixed32NoTag(Array.getInt(obj, i2));
                                        i2++;
                                    }
                                    return;
                                case 16:
                                    while (i2 < length) {
                                        jeVar.writeSFixed64NoTag(Array.getLong(obj, i2));
                                        i2++;
                                    }
                                    return;
                                case 17:
                                    while (i2 < length) {
                                        jeVar.writeSInt32NoTag(Array.getInt(obj, i2));
                                        i2++;
                                    }
                                    return;
                                case 18:
                                    while (i2 < length) {
                                        jeVar.writeSInt64NoTag(Array.getLong(obj, i2));
                                        i2++;
                                    }
                                    return;
                                default:
                                    throw new IllegalArgumentException("Unpackable type " + this.type);
                            }
                    }
                } catch (IOException e) {
                    throw new IllegalStateException(e);
                }
            } else {
                throw new IllegalArgumentException("Unexpected repeated extension tag " + this.tag + ", unequal to both non-packed variant " + this.nonPackedTag + " and packed variant " + this.packedTag);
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:29:0x0091  */
        /* JADX WARN: Removed duplicated region for block: B:30:0x0095  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private int computePackedDataSize(java.lang.Object r6) {
            /*
                r5 = this;
                int r0 = java.lang.reflect.Array.getLength(r6)
                int r1 = r5.type
                r2 = 0
                switch(r1) {
                    case 1: goto L_0x0095;
                    case 2: goto L_0x0091;
                    case 3: goto L_0x0082;
                    case 4: goto L_0x0073;
                    case 5: goto L_0x0064;
                    case 6: goto L_0x0095;
                    case 7: goto L_0x0091;
                    case 8: goto L_0x0062;
                    default: goto L_0x000a;
                }
            L_0x000a:
                switch(r1) {
                    case 13: goto L_0x0053;
                    case 14: goto L_0x0044;
                    case 15: goto L_0x0091;
                    case 16: goto L_0x0095;
                    case 17: goto L_0x0035;
                    case 18: goto L_0x0026;
                    default: goto L_0x000d;
                }
            L_0x000d:
                java.lang.IllegalArgumentException r6 = new java.lang.IllegalArgumentException
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                r0.<init>()
                java.lang.String r1 = "Unexpected non-packable type "
                r0.append(r1)
                int r1 = r5.type
                r0.append(r1)
                java.lang.String r0 = r0.toString()
                r6.<init>(r0)
                throw r6
            L_0x0026:
                r1 = 0
            L_0x0027:
                if (r2 >= r0) goto L_0x0098
                long r3 = java.lang.reflect.Array.getLong(r6, r2)
                int r3 = com.tendcloud.tenddata.C3000je.m15300e(r3)
                int r1 = r1 + r3
                int r2 = r2 + 1
                goto L_0x0027
            L_0x0035:
                r1 = 0
            L_0x0036:
                if (r2 >= r0) goto L_0x0098
                int r3 = java.lang.reflect.Array.getInt(r6, r2)
                int r3 = com.tendcloud.tenddata.C3000je.m15299f(r3)
                int r1 = r1 + r3
                int r2 = r2 + 1
                goto L_0x0036
            L_0x0044:
                r1 = 0
            L_0x0045:
                if (r2 >= r0) goto L_0x0098
                int r3 = java.lang.reflect.Array.getInt(r6, r2)
                int r3 = com.tendcloud.tenddata.C3000je.m15307d(r3)
                int r1 = r1 + r3
                int r2 = r2 + 1
                goto L_0x0045
            L_0x0053:
                r1 = 0
            L_0x0054:
                if (r2 >= r0) goto L_0x0098
                int r3 = java.lang.reflect.Array.getInt(r6, r2)
                int r3 = com.tendcloud.tenddata.C3000je.m15311c(r3)
                int r1 = r1 + r3
                int r2 = r2 + 1
                goto L_0x0054
            L_0x0062:
                r1 = r0
                goto L_0x0098
            L_0x0064:
                r1 = 0
            L_0x0065:
                if (r2 >= r0) goto L_0x0098
                int r3 = java.lang.reflect.Array.getInt(r6, r2)
                int r3 = com.tendcloud.tenddata.C3000je.m15342a(r3)
                int r1 = r1 + r3
                int r2 = r2 + 1
                goto L_0x0065
            L_0x0073:
                r1 = 0
            L_0x0074:
                if (r2 >= r0) goto L_0x0098
                long r3 = java.lang.reflect.Array.getLong(r6, r2)
                int r3 = com.tendcloud.tenddata.C3000je.m15333a(r3)
                int r1 = r1 + r3
                int r2 = r2 + 1
                goto L_0x0074
            L_0x0082:
                r1 = 0
            L_0x0083:
                if (r2 >= r0) goto L_0x0098
                long r3 = java.lang.reflect.Array.getLong(r6, r2)
                int r3 = com.tendcloud.tenddata.C3000je.m15316b(r3)
                int r1 = r1 + r3
                int r2 = r2 + 1
                goto L_0x0083
            L_0x0091:
                int r0 = r0 * 4
                r1 = r0
                goto L_0x0098
            L_0x0095:
                int r0 = r0 * 8
                r1 = r0
            L_0x0098:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tendcloud.tenddata.C3003jg.C3004a.computePackedDataSize(java.lang.Object):int");
        }

        @Override // com.tendcloud.tenddata.C3003jg
        protected int computeRepeatedSerializedSize(Object obj) {
            if (this.tag == this.nonPackedTag) {
                return C3003jg.super.computeRepeatedSerializedSize(obj);
            }
            if (this.tag == this.packedTag) {
                int computePackedDataSize = computePackedDataSize(obj);
                return computePackedDataSize + C3000je.m15292h(computePackedDataSize) + C3000je.m15292h(this.tag);
            }
            throw new IllegalArgumentException("Unexpected repeated extension tag " + this.tag + ", unequal to both non-packed variant " + this.nonPackedTag + " and packed variant " + this.packedTag);
        }

        @Override // com.tendcloud.tenddata.C3003jg
        protected final int computeSingularSerializedSize(Object obj) {
            int b = C3013jp.m15240b(this.tag);
            switch (this.type) {
                case 1:
                    return C3000je.m15341a(b, ((Double) obj).doubleValue());
                case 2:
                    return C3000je.m15340a(b, ((Float) obj).floatValue());
                case 3:
                    return C3000je.m15309c(b, ((Long) obj).longValue());
                case 4:
                    return C3000je.m15320b(b, ((Long) obj).longValue());
                case 5:
                    return C3000je.m15321b(b, ((Integer) obj).intValue());
                case 6:
                    return C3000je.m15305d(b, ((Long) obj).longValue());
                case 7:
                    return C3000je.m15310c(b, ((Integer) obj).intValue());
                case 8:
                    return C3000je.m15335a(b, ((Boolean) obj).booleanValue());
                case 9:
                    return C3000je.m15318b(b, (String) obj);
                case 10:
                case 11:
                default:
                    throw new IllegalArgumentException("Unknown type " + this.type);
                case 12:
                    return C3000je.m15317b(b, (byte[]) obj);
                case 13:
                    return C3000je.m15306d(b, ((Integer) obj).intValue());
                case 14:
                    return C3000je.m15302e(b, ((Integer) obj).intValue());
                case 15:
                    return C3000je.m15298f(b, ((Integer) obj).intValue());
                case 16:
                    return C3000je.m15301e(b, ((Long) obj).longValue());
                case 17:
                    return C3000je.m15294g(b, ((Integer) obj).intValue());
                case 18:
                    return C3000je.m15297f(b, ((Long) obj).longValue());
            }
        }
    }
}
