package androidx.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD, ElementType.LOCAL_VARIABLE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.CLASS)
/* renamed from: androidx.annotation.q */
/* loaded from: classes.dex */
public @interface FloatRange {
    /* renamed from: a */
    double m25663a() default Double.NEGATIVE_INFINITY;

    /* renamed from: b */
    double m25662b() default Double.POSITIVE_INFINITY;

    /* renamed from: c */
    boolean m25661c() default true;

    /* renamed from: d */
    boolean m25660d() default true;
}
