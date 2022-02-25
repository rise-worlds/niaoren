package p110z1;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.CONSTRUCTOR, ElementType.TYPE})
@Documented
@Retention(RetentionPolicy.CLASS)
/* renamed from: z1.bti */
/* loaded from: classes3.dex */
public @interface SuppressAnimalSniffer {
}
