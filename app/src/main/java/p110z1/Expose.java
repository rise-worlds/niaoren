package p110z1;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Documented
@Retention(RetentionPolicy.RUNTIME)
/* renamed from: z1.pr */
/* loaded from: classes3.dex */
public @interface Expose {
    /* renamed from: a */
    boolean m1431a() default true;

    /* renamed from: b */
    boolean m1430b() default true;
}
