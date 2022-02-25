package androidx.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD, ElementType.CONSTRUCTOR, ElementType.FIELD, ElementType.PACKAGE})
@Retention(RetentionPolicy.CLASS)
/* renamed from: androidx.annotation.ak */
/* loaded from: classes.dex */
public @interface RequiresApi {
    @IntRange(m25655a = 1)
    /* renamed from: a */
    int m25683a() default 1;

    @IntRange(m25655a = 1)
    /* renamed from: b */
    int m25682b() default 1;
}
