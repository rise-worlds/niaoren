package p110z1;

import com.ctetin.expandabletextviewlibrary.ExpandableTextView;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import p110z1.C4218be;

/* renamed from: z1.cd */
/* loaded from: classes3.dex */
public class C4921cd {

    /* renamed from: a */
    private static C4218be.AbstractC4219a f20563a = null;

    /* renamed from: b */
    private static final String f20564b = "alipaysdk";

    /* renamed from: a */
    public static void m5617a(C4218be.AbstractC4219a aVar) {
        f20563a = aVar;
    }

    /* renamed from: a */
    private static void m5621a(String str) {
        try {
            C4218be.AbstractC4219a aVar = f20563a;
            if (aVar != null) {
                aVar.m9728a(String.format("[AlipaySDK] %s %s", new SimpleDateFormat("hh:mm:ss.SSS", Locale.getDefault()).format(new Date()), str));
            }
        } catch (Throwable unused) {
        }
    }

    /* renamed from: a */
    public static void m5620a(String str, String str2) {
        m5621a(m5612e(str, str2));
    }

    /* renamed from: b */
    public static void m5616b(String str, String str2) {
        m5621a(m5612e(str, str2));
    }

    /* renamed from: c */
    public static void m5614c(String str, String str2) {
        m5621a(m5612e(str, str2));
    }

    /* renamed from: d */
    public static void m5613d(String str, String str2) {
        m5621a(m5612e(str, str2));
    }

    /* renamed from: a */
    public static void m5619a(String str, String str2, Throwable th) {
        String e = m5612e(str, str2);
        m5621a(e + ExpandableTextView.f6958c + m5615b(th));
    }

    /* renamed from: a */
    public static void m5618a(Throwable th) {
        if (th != null) {
            try {
                m5621a(m5615b(th));
            } catch (Throwable unused) {
            }
        }
    }

    /* renamed from: e */
    private static String m5612e(String str, String str2) {
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = "";
        }
        return String.format("[%s][%s]", str, str2);
    }

    /* renamed from: b */
    private static String m5615b(Throwable th) {
        StringWriter stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }
}
