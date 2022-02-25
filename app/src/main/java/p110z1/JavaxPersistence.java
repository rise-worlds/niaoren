package p110z1;

import com.stripe.android.model.SourceCardData;
import com.tendcloud.tenddata.AbstractC2790d;
import com.tendcloud.tenddata.C2970ih;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.Collection;
import org.apache.tools.ant.types.selectors.SizeSelector;

/* renamed from: z1.ul */
/* loaded from: classes3.dex */
public class JavaxPersistence {
    /* renamed from: a */
    public static DatabaseFieldConfig m530a(DatabaseType siVar, Field field) throws SQLException {
        Annotation[] annotations;
        boolean z;
        Annotation annotation = null;
        Annotation annotation2 = null;
        Annotation annotation3 = null;
        Annotation annotation4 = null;
        Annotation annotation5 = null;
        Annotation annotation6 = null;
        Annotation annotation7 = null;
        Annotation annotation8 = null;
        Annotation annotation9 = null;
        for (Annotation annotation10 : field.getAnnotations()) {
            Class<? extends Annotation> annotationType = annotation10.annotationType();
            if (annotationType.getName().equals("javax.persistence.Column")) {
                annotation = annotation10;
            }
            if (annotationType.getName().equals("javax.persistence.Basic")) {
                annotation2 = annotation10;
            }
            if (annotationType.getName().equals("javax.persistence.Id")) {
                annotation3 = annotation10;
            }
            if (annotationType.getName().equals("javax.persistence.GeneratedValue")) {
                annotation8 = annotation10;
            }
            if (annotationType.getName().equals("javax.persistence.OneToOne")) {
                annotation4 = annotation10;
            }
            if (annotationType.getName().equals("javax.persistence.ManyToOne")) {
                annotation5 = annotation10;
            }
            if (annotationType.getName().equals("javax.persistence.JoinColumn")) {
                annotation9 = annotation10;
            }
            if (annotationType.getName().equals("javax.persistence.Enumerated")) {
                annotation6 = annotation10;
            }
            if (annotationType.getName().equals("javax.persistence.Version")) {
                annotation7 = annotation10;
            }
        }
        if (annotation == null && annotation2 == null && annotation3 == null && annotation4 == null && annotation5 == null && annotation6 == null && annotation7 == null) {
            return null;
        }
        DatabaseFieldConfig spVar = new DatabaseFieldConfig();
        String name = field.getName();
        if (siVar.mo887m()) {
            name = name.toUpperCase();
        }
        spVar.m822a(name);
        if (annotation != null) {
            try {
                String str = (String) annotation.getClass().getMethod("name", new Class[0]).invoke(annotation, new Object[0]);
                if (str != null && str.length() > 0) {
                    spVar.m810b(str);
                }
                String str2 = (String) annotation.getClass().getMethod("columnDefinition", new Class[0]).invoke(annotation, new Object[0]);
                if (str2 != null && str2.length() > 0) {
                    spVar.m766o(str2);
                }
                spVar.m825a(((Integer) annotation.getClass().getMethod(C2970ih.C2971a.LENGTH, new Class[0]).invoke(annotation, new Object[0])).intValue());
                Boolean bool = (Boolean) annotation.getClass().getMethod("nullable", new Class[0]).invoke(annotation, new Object[0]);
                if (bool != null) {
                    spVar.m813a(bool.booleanValue());
                }
                Boolean bool2 = (Boolean) annotation.getClass().getMethod("unique", new Class[0]).invoke(annotation, new Object[0]);
                if (bool2 != null) {
                    spVar.m786h(bool2.booleanValue());
                }
            } catch (Exception e) {
                throw SqlExceptionUtil.m529a("Problem accessing fields from the @Column annotation for field " + field, e);
            }
        }
        if (annotation2 != null) {
            try {
                Boolean bool3 = (Boolean) annotation2.getClass().getMethod(SourceCardData.f12152b, new Class[0]).invoke(annotation2, new Object[0]);
                if (bool3 == null) {
                    spVar.m813a(true);
                } else {
                    spVar.m813a(bool3.booleanValue());
                }
            } catch (Exception e2) {
                throw SqlExceptionUtil.m529a("Problem accessing fields from the @Basic annotation for field " + field, e2);
            }
        }
        if (annotation3 != null) {
            if (annotation8 == null) {
                spVar.m807b(true);
            } else {
                spVar.m803c(true);
            }
        }
        if (!(annotation4 == null && annotation5 == null)) {
            if (Collection.class.isAssignableFrom(field.getType()) || ForeignCollection.class.isAssignableFrom(field.getType())) {
                spVar.m771m(true);
                if (annotation9 != null) {
                    try {
                        String str3 = (String) annotation9.getClass().getMethod("name", new Class[0]).invoke(annotation9, new Object[0]);
                        if (str3 != null && str3.length() > 0) {
                            spVar.m781j(str3);
                        }
                        Object invoke = annotation9.getClass().getMethod("fetch", new Class[0]).invoke(annotation9, new Object[0]);
                        if (invoke != null && invoke.toString().equals("EAGER")) {
                            spVar.m768n(true);
                        }
                    } catch (Exception e3) {
                        throw SqlExceptionUtil.m529a("Problem accessing fields from the @JoinColumn annotation for field " + field, e3);
                    }
                }
            } else {
                spVar.m799d(true);
                if (annotation9 != null) {
                    try {
                        String str4 = (String) annotation9.getClass().getMethod("name", new Class[0]).invoke(annotation9, new Object[0]);
                        if (str4 != null && str4.length() > 0) {
                            spVar.m810b(str4);
                        }
                        Boolean bool4 = (Boolean) annotation9.getClass().getMethod("nullable", new Class[0]).invoke(annotation9, new Object[0]);
                        if (bool4 != null) {
                            spVar.m813a(bool4.booleanValue());
                        }
                        Boolean bool5 = (Boolean) annotation9.getClass().getMethod("unique", new Class[0]).invoke(annotation9, new Object[0]);
                        if (bool5 != null) {
                            spVar.m786h(bool5.booleanValue());
                        }
                    } catch (Exception e4) {
                        throw SqlExceptionUtil.m529a("Problem accessing fields from the @JoinColumn annotation for field " + field, e4);
                    }
                }
            }
        }
        if (annotation6 != null) {
            try {
                Object invoke2 = annotation6.getClass().getMethod(SizeSelector.SIZE_KEY, new Class[0]).invoke(annotation6, new Object[0]);
                if (invoke2 == null || !invoke2.toString().equals(AbstractC2790d.MF_STRING)) {
                    spVar.m815a(DataType.ENUM_INTEGER);
                } else {
                    spVar.m815a(DataType.ENUM_STRING);
                }
            } catch (Exception e5) {
                throw SqlExceptionUtil.m529a("Problem accessing fields from the @Enumerated annotation for field " + field, e5);
            }
        }
        if (annotation7 != null) {
            spVar.m756r(true);
        }
        if (spVar.m802d() == null) {
            spVar.m816a(DataPersisterManager.m869a(field));
            z = false;
        } else {
            z = false;
        }
        if (!(DatabaseFieldConfig.m820a(field, z) == null || DatabaseFieldConfig.m808b(field, z) == null)) {
            z = true;
        }
        spVar.m795e(z);
        return spVar;
    }

    /* renamed from: a */
    public static String m531a(Class<?> cls) {
        Annotation[] annotations;
        Annotation annotation = null;
        for (Annotation annotation2 : cls.getAnnotations()) {
            if (annotation2.annotationType().getName().equals("javax.persistence.Entity")) {
                annotation = annotation2;
            }
        }
        if (annotation == null) {
            return null;
        }
        try {
            String str = (String) annotation.getClass().getMethod("name", new Class[0]).invoke(annotation, new Object[0]);
            if (str != null) {
                if (str.length() > 0) {
                    return str;
                }
            }
            return null;
        } catch (Exception e) {
            throw new IllegalStateException("Could not get entity name from class " + cls, e);
        }
    }
}
