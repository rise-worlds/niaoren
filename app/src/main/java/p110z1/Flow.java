package p110z1;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PARAMETER, ElementType.METHOD})
@Documented
@Retention(RetentionPolicy.CLASS)
/* renamed from: z1.daa */
/* loaded from: classes3.dex */
public @interface Flow {
    @NonNls

    /* renamed from: a */
    public static final String f21212a = "The method argument (if parameter was annotated) or this container (if instance method was annotated)";
    @NonNls

    /* renamed from: b */
    public static final String f21213b = "this";
    @NonNls

    /* renamed from: c */
    public static final String f21214c = "This container (if the parameter was annotated) or the return value (if instance method was annotated)";
    @NonNls

    /* renamed from: d */
    public static final String f21215d = "The return value of this method";
    @NonNls

    /* renamed from: e */
    public static final String f21216e = "this";

    /* renamed from: a */
    String m3363a() default "The method argument (if parameter was annotated) or this container (if instance method was annotated)";

    /* renamed from: b */
    boolean m3362b() default false;

    /* renamed from: c */
    String m3361c() default "This container (if the parameter was annotated) or the return value (if instance method was annotated)";

    /* renamed from: d */
    boolean m3360d() default false;
}
