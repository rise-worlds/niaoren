package com.lidroid.xutils.p058db.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
/* renamed from: com.lidroid.xutils.db.annotation.Table */
/* loaded from: classes.dex */
public @interface Table {
    String execAfterTableCreated() default "";

    String name() default "";
}
