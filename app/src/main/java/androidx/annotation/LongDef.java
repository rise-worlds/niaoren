package androidx.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.SOURCE)
/* renamed from: androidx.annotation.ab */
/* loaded from: classes.dex */
public @interface LongDef {
    /* renamed from: a */
    long[] m25686a() default {};

    /* renamed from: b */
    boolean m25685b() default false;

    /* renamed from: c */
    boolean m25684c() default false;
}
