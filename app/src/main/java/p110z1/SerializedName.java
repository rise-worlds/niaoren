package p110z1;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD})
@Documented
@Retention(RetentionPolicy.RUNTIME)
/* renamed from: z1.pt */
/* loaded from: classes3.dex */
public @interface SerializedName {
    /* renamed from: a */
    String m1427a();

    /* renamed from: b */
    String[] m1426b() default {};
}
