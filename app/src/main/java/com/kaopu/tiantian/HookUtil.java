package com.kaopu.tiantian;

import com.ctetin.expandabletextviewlibrary.ExpandableTextView;
import com.googlecode.tesseract.android.TessBaseAPI;
import com.lody.virtual.helper.utils.VLog;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import p110z1.C4963cj;
import p110z1.Consts;

/* loaded from: classes.dex */
public class HookUtil {
    private static final HashMap<String, String> abbreviationMap = new HashMap<>();
    private static final HashMap<String, String> reverseAbbreviationMap = new HashMap<>();

    static {
        addAbbreviation("int", "I");
        addAbbreviation("boolean", "Z");
        addAbbreviation("float", TessBaseAPI.f9205f);
        addAbbreviation("long", "J");
        addAbbreviation("short", "S");
        addAbbreviation("byte", "B");
        addAbbreviation("double", "D");
        addAbbreviation("char", "C");
        addAbbreviation("void", "V");
    }

    private static void addAbbreviation(String str, String str2) {
        abbreviationMap.put(str, str2);
        reverseAbbreviationMap.put(str2, str);
    }

    private static String toCanonicalName(String str) {
        if (str == null) {
            throw new NullPointerException("className must not be null.");
        } else if (str.endsWith("[]")) {
            StringBuilder sb = new StringBuilder();
            while (str.endsWith("[]")) {
                str = str.substring(0, str.length() - 2);
                sb.append("[");
            }
            sb.append(toCanonicalName(str));
            return sb.toString();
        } else if (abbreviationMap.containsKey(str)) {
            return abbreviationMap.get(str);
        } else {
            StringBuilder sb2 = new StringBuilder();
            String replace = str.replace(Consts.f23430h, "/");
            sb2.append("L");
            sb2.append(replace);
            sb2.append(C4963cj.f20745b);
            return sb2.toString();
        }
    }

    public static String getMethodSig(Method method) {
        Class<?>[] parameterTypes;
        String str = "(";
        for (int i = 0; i < method.getParameterTypes().length; i++) {
            str = str + toCanonicalName(parameterTypes[i].getName());
        }
        return (str + ")") + toCanonicalName(method.getReturnType().getName());
    }

    public static String getMethodString(Method method) {
        Class<?>[] parameterTypes = method.getParameterTypes();
        String str = "(";
        for (int i = 0; i < parameterTypes.length; i++) {
            str = i >= 1 ? str + "," + parameterTypes[i].getName() : str + parameterTypes[i].getName();
        }
        return (str + ")") + method.getReturnType().getName();
    }

    public static boolean checkSig(Class cls, int i, String str) {
        Method[] declaredMethods;
        for (Method method : cls.getDeclaredMethods()) {
            if (method.getName().equals(str)) {
                VLog.m18993d("sunya-yafa", "findmethod String->" + str + getMethodString(method), new Object[0]);
                StringBuilder sb = new StringBuilder();
                sb.append("findmethod Sig->");
                sb.append(getMethodSig(method));
                VLog.m18993d("sunya-yafa", sb.toString(), new Object[0]);
                boolean isStatic = Modifier.isStatic(method.getModifiers());
                int length = method.getParameterTypes().length;
                if (!isStatic) {
                    length++;
                }
                if (length == i) {
                    VLog.m18993d("sunya", "找到了一个长度匹配的", new Object[0]);
                    return true;
                }
            }
        }
        VLog.m18993d("sunya", "没有长度匹配的", new Object[0]);
        return false;
    }

    public static String autoFillSig(Class cls, String str, int i) {
        Method[] declaredMethods;
        String str2 = null;
        int i2 = 0;
        for (Method method : cls.getDeclaredMethods()) {
            if (method.getName().equals(str)) {
                VLog.m18993d("sunya-yafa", "findmethod String->" + str + getMethodString(method), new Object[0]);
                StringBuilder sb = new StringBuilder();
                sb.append("findmethod Sig->");
                sb.append(getMethodSig(method));
                VLog.m18993d("sunya-yafa", sb.toString(), new Object[0]);
                if (str2 == null) {
                    boolean isStatic = Modifier.isStatic(method.getModifiers());
                    int length = method.getParameterTypes().length;
                    if (!isStatic) {
                        length++;
                    }
                    if (length == i) {
                        str2 = getMethodSig(method);
                    }
                }
                i2++;
            }
        }
        if (i2 <= 0) {
            return "";
        }
        VLog.m18993d("sunya", "未设置sig 自动填入1/" + i2 + ExpandableTextView.f6958c + str2, new Object[0]);
        return str2;
    }
}
