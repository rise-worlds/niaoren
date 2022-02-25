package p110z1;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Documented
@Retention(RetentionPolicy.CLASS)
/* renamed from: z1.dbo */
/* loaded from: classes3.dex */
public @interface Contract {
    /* renamed from: a */
    String m3250a() default "";

    /* renamed from: b */
    boolean m3249b() default false;
}
