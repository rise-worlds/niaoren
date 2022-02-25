package com.cyjh.mobileanjian.rpc;

import com.cyjh.mobileanjian.ipc.rpc.Invocator;
import com.cyjh.mobileanjian.ipc.utils.ArrayUtils;
import com.cyjh.mobileanjian.ipc.utils.C1340p;
import com.cyjh.mobileanjian.ipc.utils.ReturnTypeMap;
import java.util.ArrayList;
import java.util.List;
import p110z1.Consts;

/* loaded from: classes.dex */
public class Rpc {
    private static int checkArgs(String[] strArr, String[] strArr2) {
        if (!ArrayUtils.m20671a(strArr) || !ArrayUtils.m20671a(strArr2)) {
            return (ArrayUtils.m20671a(strArr) || ArrayUtils.m20671a(strArr2) || ArrayUtils.m20670b(strArr) || ArrayUtils.m20670b(strArr2) || strArr.length != strArr2.length) ? -1 : 0;
        }
        return 1;
    }

    private static List<String> arrayToList(String[] strArr) {
        ArrayList arrayList = new ArrayList();
        for (String str : strArr) {
            arrayList.add(str);
        }
        return arrayList;
    }

    public static boolean SimpleCallStringParam(boolean z, String str, String str2, String str3, String str4) {
        if (z) {
            return SynCall(str, str2, str3, new String[]{"String"}, new String[]{str4});
        }
        return AsynCall(str, str2, str3, new String[]{"String"}, new String[]{str4});
    }

    public static boolean SimpleCallStringStringParam(boolean z, String str, String str2, String str3, String str4, String str5) {
        if (z) {
            return SynCall(str, str2, str3, new String[]{"String", "String"}, new String[]{str4, str5});
        }
        return AsynCall(str, str2, str3, new String[]{"String", "String"}, new String[]{str4, str5});
    }

    public static String SimpleCallReturnString(String str, String str2, String str3) {
        return (String) SynCall(str, str2, str3, null, null, "String");
    }

    public static String SimpleCallStringStringParamReturnString(String str, String str2, String str3, String str4, String str5) {
        String[] strArr;
        String[] strArr2;
        if (str4 == null) {
            return "Use SimpleCallReturnString instead.";
        }
        if (str5 == null) {
            strArr2 = new String[]{String.class.getSimpleName()};
            strArr = new String[]{str4};
        } else {
            strArr = new String[]{str4, str5};
            strArr2 = new String[]{String.class.getSimpleName(), String.class.getSimpleName()};
        }
        return (String) SynCall(str, str2, str3, strArr2, strArr, "String");
    }

    public static int SimpleCallReturnInt(String str, String str2, String str3) {
        return ((Integer) SynCall(str, str2, str3, null, null, "int")).intValue();
    }

    public static boolean AsynCall(String str, String str2, String str3, String[] strArr, String[] strArr2) {
        if (C1340p.m20620a(str) || C1340p.m20620a(str2) || C1340p.m20620a(str3) || checkArgs(strArr, strArr2) == -1) {
            return false;
        }
        new StringBuilder("retObj = ").append(Invocator.invoke(str, str2, str3, arrayToList(strArr), arrayToList(strArr2), 0));
        StringBuilder sb = new StringBuilder("已经调用了AsynCall ");
        sb.append(str);
        sb.append(Consts.f23430h);
        sb.append(str3);
        return true;
    }

    public static boolean SynCall(String str, String str2, String str3, String[] strArr, String[] strArr2) {
        SynCall(str, str2, str3, strArr, strArr2, "void");
        return true;
    }

    public static Object SynCall(String str, String str2, String str3, String[] strArr, String[] strArr2, String str4) {
        List<String> list;
        if (C1340p.m20620a(str) || C1340p.m20620a(str2) || C1340p.m20620a(str3)) {
            return false;
        }
        int checkArgs = checkArgs(strArr, strArr2);
        if (!(checkArgs == -1 || ReturnTypeMap.m20624a(str4) == null)) {
            List<String> list2 = null;
            if (checkArgs == 0) {
                List<String> arrayToList = arrayToList(strArr);
                list = arrayToList(strArr2);
                list2 = arrayToList;
            } else {
                list = null;
            }
            Object invoke = Invocator.invoke(str, str2, str3, list2, list, 0);
            new StringBuilder("retObj = ").append(invoke);
            try {
            } catch (Throwable th) {
                th.printStackTrace();
            }
            switch (ReturnTypeMap.m20624a(str4)) {
                case BOOLEAN:
                    return (Boolean) invoke;
                case INT:
                    return (Integer) invoke;
                case LONG:
                    return (Long) invoke;
                case FLOAT:
                    return (Float) invoke;
                case DOUBLE:
                    return (Double) invoke;
                case STRING:
                    return invoke == null ? "" : (String) invoke;
                default:
                    return true;
            }
        }
        return false;
    }
}
