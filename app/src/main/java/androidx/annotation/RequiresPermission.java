package androidx.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD, ElementType.CONSTRUCTOR, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.CLASS)
/* renamed from: androidx.annotation.am */
/* loaded from: classes.dex */
public @interface RequiresPermission {

    /* compiled from: RequiresPermission.java */
    @Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
    /* renamed from: androidx.annotation.am$a */
    /* loaded from: classes.dex */
    public @interface AbstractC0571a {
        /* renamed from: a */
        RequiresPermission m25675a() default @RequiresPermission;
    }

    /* compiled from: RequiresPermission.java */
    @Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
    /* renamed from: androidx.annotation.am$b */
    /* loaded from: classes.dex */
    public @interface AbstractC0572b {
        /* renamed from: a */
        RequiresPermission m25674a() default @RequiresPermission;
    }

    /* renamed from: a */
    String m25679a() default "";

    /* renamed from: b */
    String[] m25678b() default {};

    /* renamed from: c */
    String[] m25677c() default {};

    /* renamed from: d */
    boolean m25676d() default false;
}
