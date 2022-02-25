package p110z1;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.CLASS)
/* renamed from: z1.dad */
/* loaded from: classes3.dex */
public @interface Language {
    @NonNls
    /* renamed from: a */
    String m3359a();

    @NonNls
    /* renamed from: b */
    String m3358b() default "";

    @NonNls
    /* renamed from: c */
    String m3357c() default "";
}
