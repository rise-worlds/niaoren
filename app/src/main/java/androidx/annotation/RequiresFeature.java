package androidx.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.CONSTRUCTOR})
@Retention(RetentionPolicy.SOURCE)
/* renamed from: androidx.annotation.al */
/* loaded from: classes.dex */
public @interface RequiresFeature {
    /* renamed from: a */
    String m25681a();

    /* renamed from: b */
    String m25680b();
}
