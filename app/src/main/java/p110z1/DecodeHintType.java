package p110z1;

import java.util.List;

/* renamed from: z1.jl */
/* loaded from: classes3.dex */
public enum DecodeHintType {
    OTHER(Object.class),
    PURE_BARCODE(Void.class),
    POSSIBLE_FORMATS(List.class),
    TRY_HARDER(Void.class),
    CHARACTER_SET(String.class),
    ALLOWED_LENGTHS(int[].class),
    ASSUME_CODE_39_CHECK_DIGIT(Void.class),
    ASSUME_GS1(Void.class),
    RETURN_CODABAR_START_END(Void.class),
    NEED_RESULT_POINT_CALLBACK(ResultPointCallback.class),
    ALLOWED_EAN_EXTENSIONS(int[].class);
    

    /* renamed from: l */
    private final Class<?> f22138l;

    DecodeHintType(Class cls) {
        this.f22138l = cls;
    }

    /* renamed from: a */
    private Class<?> m2271a() {
        return this.f22138l;
    }
}
