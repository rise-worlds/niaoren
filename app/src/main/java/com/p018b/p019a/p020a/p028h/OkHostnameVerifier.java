package com.p018b.p019a.p020a.p028h;

import com.p018b.p019a.p020a.Util;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import org.apache.commons.p105io.FilenameUtils;
import org.slf4j.Marker;
import p110z1.Consts;

/* renamed from: com.b.a.a.h.d */
/* loaded from: classes.dex */
public final class OkHostnameVerifier implements HostnameVerifier {

    /* renamed from: a */
    public static final OkHostnameVerifier f6053a = new OkHostnameVerifier();

    private OkHostnameVerifier() {
    }

    @Override // javax.net.ssl.HostnameVerifier
    public final boolean verify(String str, SSLSession sSLSession) {
        String a;
        try {
            X509Certificate x509Certificate = (X509Certificate) sSLSession.getPeerCertificates()[0];
            if (Util.m24747c(str)) {
                List<String> a2 = m24562a(x509Certificate, 7);
                int size = a2.size();
                for (int i = 0; i < size; i++) {
                    if (str.equalsIgnoreCase(a2.get(i))) {
                        return true;
                    }
                }
                return false;
            }
            String lowerCase = str.toLowerCase(Locale.US);
            List<String> a3 = m24562a(x509Certificate, 2);
            int size2 = a3.size();
            int i2 = 0;
            boolean z = false;
            while (i2 < size2) {
                if (m24564a(lowerCase, a3.get(i2))) {
                    return true;
                }
                i2++;
                z = true;
            }
            if (z || (a = new DistinguishedNameParser(x509Certificate.getSubjectX500Principal()).m24569a("cn")) == null) {
                return false;
            }
            return m24564a(lowerCase, a);
        } catch (SSLException unused) {
            return false;
        }
    }

    /* renamed from: a */
    public static List<String> m24563a(X509Certificate x509Certificate) {
        List<String> a = m24562a(x509Certificate, 7);
        List<String> a2 = m24562a(x509Certificate, 2);
        ArrayList arrayList = new ArrayList(a.size() + a2.size());
        arrayList.addAll(a);
        arrayList.addAll(a2);
        return arrayList;
    }

    /* renamed from: a */
    private static List<String> m24562a(X509Certificate x509Certificate, int i) {
        Integer num;
        String str;
        ArrayList arrayList = new ArrayList();
        try {
            Collection<List<?>> subjectAlternativeNames = x509Certificate.getSubjectAlternativeNames();
            if (subjectAlternativeNames == null) {
                return Collections.emptyList();
            }
            for (List<?> list : subjectAlternativeNames) {
                if (!(list == null || list.size() < 2 || (num = (Integer) list.get(0)) == null || num.intValue() != i || (str = (String) list.get(1)) == null)) {
                    arrayList.add(str);
                }
            }
            return arrayList;
        } catch (CertificateParsingException unused) {
            return Collections.emptyList();
        }
    }

    /* renamed from: a */
    private static boolean m24564a(String str, String str2) {
        if (str == null || str.length() == 0 || str.startsWith(Consts.f23430h) || str.endsWith("..") || str2 == null || str2.length() == 0 || str2.startsWith(Consts.f23430h) || str2.endsWith("..")) {
            return false;
        }
        if (!str.endsWith(Consts.f23430h)) {
            str = str + FilenameUtils.EXTENSION_SEPARATOR;
        }
        if (!str2.endsWith(Consts.f23430h)) {
            str2 = str2 + FilenameUtils.EXTENSION_SEPARATOR;
        }
        String lowerCase = str2.toLowerCase(Locale.US);
        if (!lowerCase.contains(Marker.ANY_MARKER)) {
            return str.equals(lowerCase);
        }
        if (!lowerCase.startsWith("*.") || lowerCase.indexOf(42, 1) != -1 || str.length() < lowerCase.length() || "*.".equals(lowerCase)) {
            return false;
        }
        String substring = lowerCase.substring(1);
        if (!str.endsWith(substring)) {
            return false;
        }
        int length = str.length() - substring.length();
        return length <= 0 || str.lastIndexOf(46, length - 1) == -1;
    }
}
