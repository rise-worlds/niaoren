package p110z1;

/* renamed from: z1.ni */
/* loaded from: classes3.dex */
enum DataMask {
    DATA_MASK_000 { // from class: z1.ni.1
        @Override // p110z1.DataMask
        /* renamed from: a */
        final boolean mo1838a(int i, int i2) {
            return ((i + i2) & 1) == 0;
        }
    },
    DATA_MASK_001 { // from class: z1.ni.2
        @Override // p110z1.DataMask
        /* renamed from: a */
        final boolean mo1838a(int i, int i2) {
            return (i & 1) == 0;
        }
    },
    DATA_MASK_010 { // from class: z1.ni.3
        @Override // p110z1.DataMask
        /* renamed from: a */
        final boolean mo1838a(int i, int i2) {
            return i2 % 3 == 0;
        }
    },
    DATA_MASK_011 { // from class: z1.ni.4
        @Override // p110z1.DataMask
        /* renamed from: a */
        final boolean mo1838a(int i, int i2) {
            return (i + i2) % 3 == 0;
        }
    },
    DATA_MASK_100 { // from class: z1.ni.5
        @Override // p110z1.DataMask
        /* renamed from: a */
        final boolean mo1838a(int i, int i2) {
            return (((i / 2) + (i2 / 3)) & 1) == 0;
        }
    },
    DATA_MASK_101 { // from class: z1.ni.6
        @Override // p110z1.DataMask
        /* renamed from: a */
        final boolean mo1838a(int i, int i2) {
            return (i * i2) % 6 == 0;
        }
    },
    DATA_MASK_110 { // from class: z1.ni.7
        @Override // p110z1.DataMask
        /* renamed from: a */
        final boolean mo1838a(int i, int i2) {
            return (i * i2) % 6 < 3;
        }
    },
    DATA_MASK_111 { // from class: z1.ni.8
        @Override // p110z1.DataMask
        /* renamed from: a */
        final boolean mo1838a(int i, int i2) {
            return (((i + i2) + ((i * i2) % 3)) & 1) == 0;
        }
    };

    /* renamed from: a */
    abstract boolean mo1838a(int i, int i2);

    /* synthetic */ DataMask(byte b) {
        this();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final void m1839a(BitMatrix hyVar, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            for (int i3 = 0; i3 < i; i3++) {
                if (mo1838a(i2, i3)) {
                    hyVar.m2507c(i3, i2);
                }
            }
        }
    }
}
