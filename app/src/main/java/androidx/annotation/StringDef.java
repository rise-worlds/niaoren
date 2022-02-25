package androidx.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.SOURCE)
/* renamed from: androidx.annotation.ap */
/* loaded from: classes.dex */
public @interface StringDef {
    /* renamed from: a */
    String[] m25668a() default {};

    /* renamed from: b */
    boolean m25667b() default false;
}
