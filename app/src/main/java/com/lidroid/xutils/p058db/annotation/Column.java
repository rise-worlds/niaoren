package com.lidroid.xutils.p058db.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
/* renamed from: com.lidroid.xutils.db.annotation.Column */
/* loaded from: classes.dex */
public @interface Column {
    String column() default "";

    String defaultValue() default "";
}
