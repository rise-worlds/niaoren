package p110z1;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Documented
@Retention(RetentionPolicy.RUNTIME)
/* renamed from: z1.czp */
/* loaded from: classes3.dex */
public @interface Subscribe {
    /* renamed from: a */
    ThreadMode m3389a() default ThreadMode.POSTING;

    /* renamed from: b */
    boolean m3388b() default false;

    /* renamed from: c */
    int m3387c() default 0;
}
