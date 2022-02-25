package androidx.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PARAMETER, ElementType.LOCAL_VARIABLE, ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.CLASS)
/* renamed from: androidx.annotation.ao */
/* loaded from: classes.dex */
public @interface Size {
    /* renamed from: a */
    long m25672a() default -1;

    /* renamed from: b */
    long m25671b() default Long.MIN_VALUE;

    /* renamed from: c */
    long m25670c() default Long.MAX_VALUE;

    /* renamed from: d */
    long m25669d() default 1;
}
