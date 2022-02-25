package p110z1;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.TYPE})
@Documented
@Retention(RetentionPolicy.RUNTIME)
/* renamed from: z1.ato */
/* loaded from: classes3.dex */
public @interface SchedulerSupport {

    /* renamed from: a */
    public static final String f17505a = "none";

    /* renamed from: b */
    public static final String f17506b = "custom";

    /* renamed from: c */
    public static final String f17507c = "io.reactivex:computation";

    /* renamed from: d */
    public static final String f17508d = "io.reactivex:io";

    /* renamed from: e */
    public static final String f17509e = "io.reactivex:new-thread";

    /* renamed from: f */
    public static final String f17510f = "io.reactivex:trampoline";

    /* renamed from: g */
    public static final String f17511g = "io.reactivex:single";

    /* renamed from: a */
    String m10000a();
}
