package androidx.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.SOURCE)
/* renamed from: androidx.annotation.w */
/* loaded from: classes.dex */
public @interface IntDef {
    /* renamed from: a */
    int[] m25658a() default {};

    /* renamed from: b */
    boolean m25657b() default false;

    /* renamed from: c */
    boolean m25656c() default false;
}
