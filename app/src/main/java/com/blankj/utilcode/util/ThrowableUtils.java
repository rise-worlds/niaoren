package com.blankj.utilcode.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/* renamed from: com.blankj.utilcode.util.ba */
/* loaded from: classes.dex */
public class ThrowableUtils {

    /* renamed from: a */
    private static final String f6807a = System.getProperty("line.separator");

    private ThrowableUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /* renamed from: a */
    public static String m23131a(Throwable th) {
        ArrayList arrayList = new ArrayList();
        while (th != null && !arrayList.contains(th)) {
            arrayList.add(th);
            th = th.getCause();
        }
        int size = arrayList.size();
        ArrayList<String> arrayList2 = new ArrayList();
        int i = size - 1;
        List<String> b = m23129b((Throwable) arrayList.get(i));
        while (true) {
            size--;
            if (size < 0) {
                break;
            }
            if (size != 0) {
                b = m23129b((Throwable) arrayList.get(size - 1));
                m23130a(b, b);
            } else {
                b = b;
            }
            if (size == i) {
                arrayList2.add(((Throwable) arrayList.get(size)).toString());
            } else {
                arrayList2.add(" Caused by: " + ((Throwable) arrayList.get(size)).toString());
            }
            arrayList2.addAll(b);
        }
        StringBuilder sb = new StringBuilder();
        for (String str : arrayList2) {
            sb.append(str);
            sb.append(f6807a);
        }
        return sb.toString();
    }

    /* renamed from: b */
    private static List<String> m23129b(Throwable th) {
        StringWriter stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter((Writer) stringWriter, true));
        StringTokenizer stringTokenizer = new StringTokenizer(stringWriter.toString(), f6807a);
        ArrayList arrayList = new ArrayList();
        boolean z = false;
        while (stringTokenizer.hasMoreTokens()) {
            String nextToken = stringTokenizer.nextToken();
            int indexOf = nextToken.indexOf("at");
            if (indexOf != -1 && nextToken.substring(0, indexOf).trim().isEmpty()) {
                arrayList.add(nextToken);
                z = true;
            } else if (z) {
                break;
            }
        }
        return arrayList;
    }

    /* renamed from: a */
    private static void m23130a(List<String> list, List<String> list2) {
        int size = list.size() - 1;
        for (int size2 = list2.size() - 1; size >= 0 && size2 >= 0; size2--) {
            if (list.get(size).equals(list2.get(size2))) {
                list.remove(size);
            }
            size--;
        }
    }
}
