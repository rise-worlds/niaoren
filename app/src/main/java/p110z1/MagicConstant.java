package p110z1;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE, ElementType.ANNOTATION_TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.SOURCE)
/* renamed from: z1.dae */
/* loaded from: classes3.dex */
public @interface MagicConstant {
    /* renamed from: a */
    long[] m3356a() default {};

    /* renamed from: b */
    String[] m3355b() default {};

    /* renamed from: c */
    long[] m3354c() default {};

    /* renamed from: d */
    Class m3353d() default void.class;

    /* renamed from: e */
    Class m3352e() default void.class;
}
