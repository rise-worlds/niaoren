package p110z1;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
/* renamed from: z1.so */
/* loaded from: classes3.dex */
public @interface DatabaseField {

    /* renamed from: a */
    public static final String f23248a = "__ormlite__ no default value string was specified";

    /* renamed from: b */
    public static final int f23249b = -1;

    /* renamed from: c */
    public static final int f23250c = 2;

    /* renamed from: A */
    boolean m867A() default false;

    /* renamed from: B */
    String m866B() default "";

    /* renamed from: C */
    boolean m865C() default false;

    /* renamed from: a */
    String m864a() default "";

    /* renamed from: b */
    DataType m863b() default DataType.UNKNOWN;

    /* renamed from: c */
    String m862c() default "__ormlite__ no default value string was specified";

    /* renamed from: d */
    int m861d() default 0;

    /* renamed from: e */
    boolean m860e() default true;

    /* renamed from: f */
    boolean m859f() default false;

    /* renamed from: g */
    boolean m858g() default false;

    /* renamed from: h */
    String m857h() default "";

    /* renamed from: i */
    boolean m856i() default false;

    /* renamed from: j */
    boolean m855j() default false;

    /* renamed from: k */
    String m854k() default "";

    /* renamed from: l */
    boolean m853l() default false;

    /* renamed from: m */
    boolean m852m() default true;

    /* renamed from: n */
    String m851n() default "";

    /* renamed from: o */
    boolean m850o() default false;

    /* renamed from: p */
    boolean m849p() default false;

    /* renamed from: q */
    boolean m848q() default false;

    /* renamed from: r */
    boolean m847r() default false;

    /* renamed from: s */
    String m846s() default "";

    /* renamed from: t */
    String m845t() default "";

    /* renamed from: u */
    boolean m844u() default false;

    /* renamed from: v */
    int m843v() default -1;

    /* renamed from: w */
    Class<? extends DataPersister> m842w() default VoidType.class;

    /* renamed from: x */
    boolean m841x() default false;

    /* renamed from: y */
    String m840y() default "";

    /* renamed from: z */
    boolean m839z() default false;
}
