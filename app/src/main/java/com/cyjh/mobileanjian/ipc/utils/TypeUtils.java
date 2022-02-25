package com.cyjh.mobileanjian.ipc.utils;

import java.util.HashMap;
import java.util.List;

/* renamed from: com.cyjh.mobileanjian.ipc.utils.q */
/* loaded from: classes.dex */
public final class TypeUtils {

    /* renamed from: a */
    public static final HashMap<String, Class> f8711a = new HashMap<String, Class>() { // from class: com.cyjh.mobileanjian.ipc.utils.q.1
        {
            put("boolean", Boolean.TYPE);
            put("int", Integer.TYPE);
            put("long", Long.TYPE);
            put("String", String.class);
        }
    };

    /* renamed from: a */
    private static Object m20618a(String str, String str2) {
        switch (RpcType.f8709j.f8687a.get(str).intValue()) {
            case 1:
                return Boolean.valueOf(Boolean.parseBoolean(str2));
            case 2:
                if (str2.length() > 0) {
                    return Character.valueOf(str2.charAt(0));
                }
                return null;
            case 3:
                Byte.parseByte(str2);
                break;
            case 4:
                return Integer.valueOf(Integer.parseInt(str2));
            case 5:
                return Long.valueOf(Long.parseLong(str2));
            case 6:
                return Float.valueOf(Float.parseFloat(str2));
            case 7:
                Double.parseDouble(str2);
                break;
            case 9:
                return str2;
        }
        return null;
    }

    /* renamed from: a */
    private static Object m20619a(Class cls, String str) {
        try {
            switch (RpcType.f8709j.f8688b.get(cls).intValue()) {
                case 1:
                    return Boolean.valueOf(Boolean.parseBoolean(str));
                case 2:
                    return Character.valueOf(str.length() > 0 ? str.charAt(0) : ' ');
                case 3:
                    Byte.parseByte(str);
                    return null;
                case 4:
                    return Integer.valueOf(Integer.parseInt(str));
                case 5:
                    return Long.valueOf(Long.parseLong(str));
                case 6:
                    return Float.valueOf(Float.parseFloat(str));
                case 7:
                    Double.parseDouble(str);
                    return null;
                case 8:
                default:
                    return null;
                case 9:
                    if (str == null) {
                        str = "";
                    }
                    return str;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    private static Object[] m20615a(String[] strArr, String[] strArr2) {
        if (strArr == null || strArr.length == 0) {
            return null;
        }
        Object[] objArr = new Object[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            Object a = m20618a(strArr[i], strArr2[i]);
            objArr[i] = a;
            if (a == null) {
                return null;
            }
        }
        return objArr;
    }

    /* renamed from: a */
    private static Object[] m20616a(Class[] clsArr, String[] strArr) {
        if (clsArr == null || clsArr.length == 0) {
            return null;
        }
        Object[] objArr = new Object[clsArr.length];
        for (int i = 0; i < clsArr.length; i++) {
            Object a = m20619a(clsArr[i], strArr[i]);
            objArr[i] = a;
            if (a == null) {
                return null;
            }
        }
        return objArr;
    }

    /* renamed from: a */
    public static Object[] m20617a(List<String> list, List<String> list2) {
        if (list == null || list.size() == 0) {
            return null;
        }
        int size = list.size();
        Object[] objArr = new Object[size];
        for (int i = 0; i < size; i++) {
            Object a = m20618a(list.get(i), list2.get(i));
            objArr[i] = a;
            if (a == null) {
                return null;
            }
        }
        return objArr;
    }
}
