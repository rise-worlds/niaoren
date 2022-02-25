package p110z1;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* renamed from: z1.di */
/* loaded from: classes3.dex */
public final class C5263di {

    /* renamed from: a */
    private static String f21304a = "";

    /* renamed from: b */
    private static String f21305b = "";

    /* renamed from: c */
    private static String f21306c = "";

    /* renamed from: a */
    public static synchronized void m3221a(String str) {
        synchronized (C5263di.class) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(str);
            m3218a(arrayList);
        }
    }

    /* renamed from: a */
    public static synchronized void m3220a(String str, String str2, String str3) {
        synchronized (C5263di.class) {
            f21304a = str;
            f21305b = str2;
            f21306c = str3;
        }
    }

    /* renamed from: a */
    public static synchronized void m3219a(Throwable th) {
        String str;
        synchronized (C5263di.class) {
            ArrayList arrayList = new ArrayList();
            if (th != null) {
                StringWriter stringWriter = new StringWriter();
                th.printStackTrace(new PrintWriter(stringWriter));
                str = stringWriter.toString();
            } else {
                str = "";
            }
            arrayList.add(str);
            m3218a(arrayList);
        }
    }

    /* renamed from: a */
    private static synchronized void m3218a(List<String> list) {
        synchronized (C5263di.class) {
            if (!C5097cq.m3699a(f21305b) && !C5097cq.m3699a(f21306c)) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(f21306c);
                Iterator<String> it = list.iterator();
                while (it.hasNext()) {
                    stringBuffer.append(", " + it.next());
                }
                stringBuffer.append("\n");
                try {
                    File file = new File(f21304a);
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                    File file2 = new File(f21304a, f21305b);
                    if (!file2.exists()) {
                        file2.createNewFile();
                    }
                    FileWriter fileWriter = ((long) stringBuffer.length()) + file2.length() <= 51200 ? new FileWriter(file2, true) : new FileWriter(file2);
                    fileWriter.write(stringBuffer.toString());
                    fileWriter.flush();
                    fileWriter.close();
                } catch (Exception unused) {
                }
            }
        }
    }
}
