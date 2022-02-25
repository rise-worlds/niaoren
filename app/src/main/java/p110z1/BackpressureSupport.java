package p110z1;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})
@Documented
@Retention(RetentionPolicy.RUNTIME)
/* renamed from: z1.ati */
/* loaded from: classes3.dex */
public @interface BackpressureSupport {
    /* renamed from: a */
    BackpressureKind m10001a();
}
