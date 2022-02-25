package p110z1;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PARAMETER, ElementType.LOCAL_VARIABLE, ElementType.FIELD})
@Documented
@Retention(RetentionPolicy.CLASS)
/* renamed from: z1.dbt */
/* loaded from: classes3.dex */
public @interface PropertyKey {
    /* renamed from: a */
    String m3246a();
}
