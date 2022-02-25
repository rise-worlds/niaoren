package androidx.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.CLASS)
/* renamed from: androidx.annotation.t */
/* loaded from: classes.dex */
public @interface GuardedBy {
    /* renamed from: a */
    String m25659a();
}
