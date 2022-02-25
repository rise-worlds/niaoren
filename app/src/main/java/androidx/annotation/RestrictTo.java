package androidx.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.ANNOTATION_TYPE, ElementType.TYPE, ElementType.METHOD, ElementType.CONSTRUCTOR, ElementType.FIELD, ElementType.PACKAGE})
@Retention(RetentionPolicy.CLASS)
/* renamed from: androidx.annotation.an */
/* loaded from: classes.dex */
public @interface RestrictTo {

    /* compiled from: RestrictTo.java */
    /* renamed from: androidx.annotation.an$a */
    /* loaded from: classes.dex */
    public enum EnumC0573a {
        LIBRARY,
        LIBRARY_GROUP,
        GROUP_ID,
        TESTS,
        SUBCLASSES
    }

    /* renamed from: a */
    EnumC0573a[] m25673a();
}
