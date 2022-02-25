package p110z1;

import com.ctetin.expandabletextviewlibrary.ExpandableTextView;
import java.lang.reflect.Field;
import java.util.Locale;

/* renamed from: z1.ov */
/* loaded from: classes3.dex */
public enum FieldNamingPolicy implements FieldNamingStrategy {
    IDENTITY { // from class: z1.ov.1
        @Override // p110z1.FieldNamingStrategy
        public String translateName(Field field) {
            return field.getName();
        }
    },
    UPPER_CAMEL_CASE { // from class: z1.ov.2
        @Override // p110z1.FieldNamingStrategy
        public String translateName(Field field) {
            return upperCaseFirstLetter(field.getName());
        }
    },
    UPPER_CAMEL_CASE_WITH_SPACES { // from class: z1.ov.3
        @Override // p110z1.FieldNamingStrategy
        public String translateName(Field field) {
            return upperCaseFirstLetter(separateCamelCase(field.getName(), ExpandableTextView.f6958c));
        }
    },
    LOWER_CASE_WITH_UNDERSCORES { // from class: z1.ov.4
        @Override // p110z1.FieldNamingStrategy
        public String translateName(Field field) {
            return separateCamelCase(field.getName(), "_").toLowerCase(Locale.ENGLISH);
        }
    },
    LOWER_CASE_WITH_DASHES { // from class: z1.ov.5
        @Override // p110z1.FieldNamingStrategy
        public String translateName(Field field) {
            return separateCamelCase(field.getName(), "-").toLowerCase(Locale.ENGLISH);
        }
    },
    LOWER_CASE_WITH_DOTS { // from class: z1.ov.6
        @Override // p110z1.FieldNamingStrategy
        public String translateName(Field field) {
            return separateCamelCase(field.getName(), Consts.f23430h).toLowerCase(Locale.ENGLISH);
        }
    };

    static String separateCamelCase(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if (Character.isUpperCase(charAt) && sb.length() != 0) {
                sb.append(str2);
            }
            sb.append(charAt);
        }
        return sb.toString();
    }

    static String upperCaseFirstLetter(String str) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        char charAt = str.charAt(0);
        int length = str.length();
        while (i < length - 1 && !Character.isLetter(charAt)) {
            sb.append(charAt);
            i++;
            charAt = str.charAt(i);
        }
        if (Character.isUpperCase(charAt)) {
            return str;
        }
        sb.append(modifyString(Character.toUpperCase(charAt), str, i + 1));
        return sb.toString();
    }

    private static String modifyString(char c, String str, int i) {
        if (i >= str.length()) {
            return String.valueOf(c);
        }
        return c + str.substring(i);
    }
}
