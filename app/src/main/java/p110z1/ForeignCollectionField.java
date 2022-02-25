package p110z1;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
/* renamed from: z1.st */
/* loaded from: classes3.dex */
public @interface ForeignCollectionField {

    /* renamed from: a */
    public static final int f23371a = 1;

    /* renamed from: a */
    boolean m687a() default false;

    @Deprecated
    /* renamed from: b */
    int m686b() default 1;

    /* renamed from: c */
    int m685c() default 1;

    /* renamed from: d */
    String m684d() default "";

    /* renamed from: e */
    String m683e() default "";

    /* renamed from: f */
    boolean m682f() default true;

    @Deprecated
    /* renamed from: g */
    String m681g() default "";

    /* renamed from: h */
    String m680h() default "";
}
