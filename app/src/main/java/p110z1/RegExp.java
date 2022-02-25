package p110z1;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE, ElementType.ANNOTATION_TYPE})
@Language(m3359a = "RegExp")
@Retention(RetentionPolicy.CLASS)
/* renamed from: z1.dai */
/* loaded from: classes3.dex */
public @interface RegExp {
    /* renamed from: a */
    String m3350a() default "";

    /* renamed from: b */
    String m3349b() default "";
}
