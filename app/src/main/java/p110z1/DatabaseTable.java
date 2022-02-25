package p110z1;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
/* renamed from: z1.wq */
/* loaded from: classes3.dex */
public @interface DatabaseTable {
    /* renamed from: a */
    String m200a() default "";

    /* renamed from: b */
    Class<?> m199b() default Void.class;
}
