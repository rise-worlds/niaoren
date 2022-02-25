package androidx.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Documented
@Retention(RetentionPolicy.CLASS)
/* renamed from: androidx.annotation.j */
/* loaded from: classes.dex */
public @interface CheckResult {
    /* renamed from: a */
    String m25665a() default "";
}
