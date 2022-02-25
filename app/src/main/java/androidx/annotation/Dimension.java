package androidx.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD, ElementType.LOCAL_VARIABLE, ElementType.ANNOTATION_TYPE})
@Documented
@Retention(RetentionPolicy.CLASS)
/* renamed from: androidx.annotation.o */
/* loaded from: classes.dex */
public @interface Dimension {

    /* renamed from: a */
    public static final int f53a = 0;

    /* renamed from: b */
    public static final int f54b = 1;

    /* renamed from: c */
    public static final int f55c = 2;

    /* renamed from: a */
    int m25664a() default 1;
}
