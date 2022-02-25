package androidx.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD, ElementType.LOCAL_VARIABLE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.CLASS)
/* renamed from: androidx.annotation.x */
/* loaded from: classes.dex */
public @interface IntRange {
    /* renamed from: a */
    long m25655a() default Long.MIN_VALUE;

    /* renamed from: b */
    long m25654b() default Long.MAX_VALUE;
}
