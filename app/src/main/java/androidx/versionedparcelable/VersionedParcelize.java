package androidx.versionedparcelable;

import android.support.annotation.RestrictTo;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.SOURCE)
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* renamed from: androidx.versionedparcelable.i */
/* loaded from: classes.dex */
public @interface VersionedParcelize {
    /* renamed from: a */
    boolean m25531a() default false;

    /* renamed from: b */
    boolean m25530b() default false;

    /* renamed from: c */
    boolean m25529c() default false;

    /* renamed from: d */
    int[] m25528d() default {};

    /* renamed from: e */
    String m25527e() default "";
}
