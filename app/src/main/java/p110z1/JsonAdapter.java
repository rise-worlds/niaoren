package p110z1;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
/* renamed from: z1.ps */
/* loaded from: classes3.dex */
public @interface JsonAdapter {
    /* renamed from: a */
    Class<?> m1429a();

    /* renamed from: b */
    boolean m1428b() default true;
}
