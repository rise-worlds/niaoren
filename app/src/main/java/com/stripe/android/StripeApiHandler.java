package com.stripe.android;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.text.TextUtils;
import com.github.kevinsawicki.http.HttpRequest;
import com.stripe.android.ErrorParser;
import com.stripe.android.StripeNetworkUtils;
import com.stripe.android.model.C2395g;
import com.stripe.android.model.Customer;
import com.stripe.android.model.ShippingInformation;
import com.stripe.android.model.SourceParams;
import com.stripe.android.model.Token;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;
import org.apache.http.cookie.AbstractC3144SM;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import p110z1.APIConnectionException;
import p110z1.APIException;
import p110z1.AuthenticationException;
import p110z1.C4745bt;
import p110z1.CardException;
import p110z1.InvalidRequestException;
import p110z1.PermissionException;
import p110z1.RateLimitException;
import p110z1.StripeException;

/* renamed from: com.stripe.android.s */
/* loaded from: classes2.dex */
class StripeApiHandler {

    /* renamed from: a */
    static final String f12338a = "GET";

    /* renamed from: b */
    static final String f12339b = "POST";

    /* renamed from: c */
    static final String f12340c = "DELETE";

    /* renamed from: d */
    static final String f12341d = "2017-06-05";

    /* renamed from: e */
    private static final String f12342e = "https://api.stripe.com";

    /* renamed from: f */
    private static final String f12343f = "https://q.stripe.com";

    /* renamed from: g */
    private static final String f12344g = "https://m.stripe.com/4";

    /* renamed from: h */
    private static final String f12345h = "UTF-8";

    /* renamed from: i */
    private static final String f12346i = "customers";

    /* renamed from: j */
    private static final String f12347j = "tokens";

    /* renamed from: k */
    private static final String f12348k = "sources";

    /* renamed from: l */
    private static final String f12349l = "networkaddress.cache.ttl";

    /* renamed from: m */
    private static final SSLSocketFactory f12350m = new StripeSSLSocketFactory();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: StripeApiHandler.java */
    /* renamed from: com.stripe.android.s$a */
    /* loaded from: classes2.dex */
    public interface AbstractC2412a {
        /* renamed from: a */
        void m17479a(StripeResponse uVar);

        /* renamed from: a */
        void m17478a(StripeException arfVar);

        /* renamed from: a */
        boolean m17480a();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: StripeApiHandler.java */
    /* renamed from: com.stripe.android.s$c */
    /* loaded from: classes2.dex */
    public interface AbstractC2414c {
        /* renamed from: a */
        void m17477a(StripeResponse uVar);
    }

    StripeApiHandler() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static void m17501a(@NonNull Map<String, Object> map, @Nullable RequestOptions oVar, @Nullable AbstractC2412a aVar) {
        String d;
        if (oVar != null) {
            if ((aVar == null || aVar.m17480a()) && (d = oVar.m17575d()) != null && !d.trim().isEmpty()) {
                m17499a(map, f12343f, "GET", oVar, aVar);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    /* renamed from: a */
    public static C2395g m17519a(@Nullable StripeNetworkUtils.AbstractC2415a aVar, @NonNull Context context, @NonNull SourceParams kVar, @NonNull String str, @Nullable String str2, @Nullable AbstractC2412a aVar2) throws AuthenticationException, InvalidRequestException, APIConnectionException, APIException {
        return m17518a(aVar, context, kVar, str, str2, aVar2, (AbstractC2414c) null);
    }

    @VisibleForTesting
    @Nullable
    /* renamed from: a */
    static C2395g m17518a(@Nullable StripeNetworkUtils.AbstractC2415a aVar, @NonNull Context context, @NonNull SourceParams kVar, @NonNull String str, @Nullable String str2, @Nullable AbstractC2412a aVar2, @Nullable AbstractC2414c cVar) throws AuthenticationException, InvalidRequestException, APIConnectionException, APIException {
        Map<String, Object> k = kVar.m17664k();
        StripeNetworkUtils.m17471a(aVar, context, k);
        RequestOptions a = RequestOptions.m17578a(str, str2, RequestOptions.f12301a).m17572a();
        try {
            String d = a.m17575d();
            if (StripeTextUtils.m17202b(d)) {
                return null;
            }
            m17525a(context, aVar2);
            m17501a(LoggingUtils.m18000b(context, null, d, kVar.m17670g()), RequestOptions.m17580a(str).m17572a(), aVar2);
            StripeResponse b = m17492b("POST", m17498b(), k, a);
            if (cVar != null) {
                cVar.m17477a(b);
            }
            return C2395g.m17769i(b.m17466b());
        } catch (CardException e) {
            throw new APIException(e.getMessage(), e.getRequestId(), e.getStatusCode(), e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    /* renamed from: a */
    public static C2395g m17509a(@NonNull String str, @NonNull String str2, @NonNull String str3) throws AuthenticationException, InvalidRequestException, APIConnectionException, APIException {
        try {
            return C2395g.m17769i(m17492b("GET", m17489c(str), SourceParams.m17686b(str2), RequestOptions.m17580a(str3).m17572a()).m17466b());
        } catch (CardException e) {
            throw new APIException(e.getMessage(), e.getRequestId(), e.getStatusCode(), e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    /* renamed from: a */
    public static Token m17521a(@NonNull Context context, @NonNull Map<String, Object> map, @NonNull RequestOptions oVar, @NonNull String str, @Nullable AbstractC2412a aVar) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
        String d;
        try {
            d = oVar.m17575d();
        } catch (ClassCastException unused) {
            map.remove("product_usage");
        }
        if (StripeTextUtils.m17202b(d)) {
            return null;
        }
        map.remove("product_usage");
        m17525a(context, aVar);
        m17501a(LoggingUtils.m18005a(context, (List) map.get("product_usage"), d, str), oVar, aVar);
        return m17487c("POST", m17526a(), map, oVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    /* renamed from: a */
    public static C2395g m17522a(@Nullable Context context, @NonNull String str, @NonNull String str2, @NonNull List<String> list, @NonNull String str3, @NonNull String str4, @NonNull String str5, @Nullable AbstractC2412a aVar) throws InvalidRequestException, APIConnectionException, APIException, AuthenticationException, CardException {
        HashMap hashMap = new HashMap();
        hashMap.put(RequestOptions.f12301a, str3);
        if (context != null) {
            m17501a(LoggingUtils.m17999c(context, list, str2, str4), RequestOptions.m17580a(str2).m17568d(f12341d).m17572a(), aVar);
        }
        StripeResponse a = m17508a("POST", m17514a(str), hashMap, RequestOptions.m17580a(str5).m17568d(f12341d).m17572a());
        m17517a(a);
        return C2395g.m17769i(a.m17466b());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    /* renamed from: a */
    public static C2395g m17523a(@Nullable Context context, @NonNull String str, @NonNull String str2, @NonNull List<String> list, @NonNull String str3, @NonNull String str4, @Nullable AbstractC2412a aVar) throws InvalidRequestException, APIConnectionException, APIException, AuthenticationException, CardException {
        HashMap hashMap = new HashMap();
        if (context != null) {
            m17501a(LoggingUtils.m18006a(context, list, str2), RequestOptions.m17580a(str2).m17568d(f12341d).m17572a(), aVar);
        }
        StripeResponse a = m17508a("DELETE", m17493b(str, str3), hashMap, RequestOptions.m17580a(str4).m17568d(f12341d).m17572a());
        m17517a(a);
        return C2395g.m17769i(a.m17466b());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    /* renamed from: b */
    public static Customer m17497b(@Nullable Context context, @NonNull String str, @NonNull String str2, @NonNull List<String> list, @NonNull String str3, @NonNull String str4, @NonNull String str5, @Nullable AbstractC2412a aVar) throws InvalidRequestException, APIConnectionException, APIException, AuthenticationException, CardException {
        HashMap hashMap = new HashMap();
        hashMap.put("default_source", str3);
        if (context != null) {
            m17501a(LoggingUtils.m18004a(context, list, str4, null, str2, "default_source"), RequestOptions.m17580a(str2).m17568d(f12341d).m17572a(), aVar);
        }
        StripeResponse a = m17508a("POST", m17495b(str), hashMap, RequestOptions.m17580a(str5).m17568d(f12341d).m17572a());
        m17517a(a);
        return Customer.m17814b(a.m17466b());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    /* renamed from: a */
    public static Customer m17524a(@Nullable Context context, @NonNull String str, @NonNull String str2, @NonNull List<String> list, @NonNull ShippingInformation shippingInformation, @NonNull String str3, @Nullable AbstractC2412a aVar) throws InvalidRequestException, APIConnectionException, APIException, AuthenticationException, CardException {
        HashMap hashMap = new HashMap();
        hashMap.put("shipping", shippingInformation.mo17623b());
        if (context != null) {
            m17501a(LoggingUtils.m18004a(context, list, null, null, str2, "set_shipping_info"), RequestOptions.m17580a(str2).m17568d(f12341d).m17572a(), aVar);
        }
        StripeResponse a = m17508a("POST", m17495b(str), hashMap, RequestOptions.m17580a(str3).m17568d(f12341d).m17572a());
        m17517a(a);
        return Customer.m17814b(a.m17466b());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    /* renamed from: a */
    public static Customer m17511a(@NonNull String str, @NonNull String str2) throws InvalidRequestException, APIConnectionException, APIException, AuthenticationException, CardException {
        StripeResponse a = m17508a("GET", m17495b(str), null, RequestOptions.m17580a(str2).m17568d(f12341d).m17572a());
        m17517a(a);
        return Customer.m17814b(a.m17466b());
    }

    /* renamed from: a */
    static String m17503a(Map<String, Object> map) throws UnsupportedEncodingException, InvalidRequestException {
        StringBuilder sb = new StringBuilder();
        for (C2413b bVar : m17486c(map)) {
            if (sb.length() > 0) {
                sb.append(C4745bt.f20071b);
            }
            sb.append(m17484d(bVar.f12351a, bVar.f12352b));
        }
        return sb.toString();
    }

    /* renamed from: a */
    static Map<String, String> m17520a(RequestOptions oVar) {
        HashMap hashMap = new HashMap();
        hashMap.put(HttpRequest.HEADER_ACCEPT_CHARSET, "UTF-8");
        hashMap.put(HttpRequest.HEADER_ACCEPT, "application/json");
        hashMap.put("User-Agent", String.format("Stripe/v1 AndroidBindings/%s", C2368a.f11783f));
        if (oVar != null) {
            hashMap.put("Authorization", String.format(Locale.ENGLISH, "Bearer %s", oVar.m17575d()));
        }
        HashMap hashMap2 = new HashMap();
        hashMap2.put("java.version", System.getProperty("java.version"));
        hashMap2.put("os.name", "android");
        hashMap2.put("os.version", String.valueOf(Build.VERSION.SDK_INT));
        hashMap2.put("bindings.version", C2368a.f11783f);
        hashMap2.put("lang", "Java");
        hashMap2.put("publisher", "Stripe");
        hashMap.put("X-Stripe-Client-User-Agent", new JSONObject(hashMap2).toString());
        if (!(oVar == null || oVar.m17581a() == null)) {
            hashMap.put("Stripe-Version", oVar.m17581a());
        }
        if (!(oVar == null || oVar.m17573f() == null)) {
            hashMap.put("Stripe-Account", oVar.m17573f());
        }
        if (!(oVar == null || oVar.m17576c() == null)) {
            hashMap.put("Idempotency-Key", oVar.m17576c());
        }
        return hashMap;
    }

    @VisibleForTesting
    /* renamed from: a */
    static String m17526a() {
        return String.format(Locale.ENGLISH, "%s/v1/%s", f12342e, f12347j);
    }

    @VisibleForTesting
    /* renamed from: b */
    static String m17498b() {
        return String.format(Locale.ENGLISH, "%s/v1/%s", f12342e, f12348k);
    }

    @VisibleForTesting
    /* renamed from: c */
    static String m17490c() {
        return String.format(Locale.ENGLISH, "%s/v1/%s", f12342e, f12346i);
    }

    @VisibleForTesting
    /* renamed from: a */
    static String m17514a(@NonNull String str) {
        return String.format(Locale.ENGLISH, "%s/%s", m17495b(str), f12348k);
    }

    @VisibleForTesting
    /* renamed from: b */
    static String m17493b(@NonNull String str, @NonNull String str2) {
        return String.format(Locale.ENGLISH, "%s/%s", m17514a(str), str2);
    }

    @VisibleForTesting
    /* renamed from: b */
    static String m17495b(@NonNull String str) {
        return String.format(Locale.ENGLISH, "%s/%s", m17490c(), str);
    }

    @VisibleForTesting
    /* renamed from: c */
    static String m17489c(@NonNull String str) {
        return String.format(Locale.ENGLISH, "%s/%s", m17498b(), str);
    }

    @VisibleForTesting
    /* renamed from: d */
    static String m17485d(@NonNull String str) {
        return String.format("%s/%s", m17526a(), str);
    }

    /* renamed from: a */
    static void m17517a(StripeResponse uVar) throws InvalidRequestException, APIConnectionException, APIException, AuthenticationException, CardException {
        int a = uVar.m17467a();
        String b = uVar.m17466b();
        Map<String, List<String>> c = uVar.m17465c();
        String str = null;
        List<String> list = c == null ? null : c.get("Request-Id");
        if (list != null && list.size() > 0) {
            str = list.get(0);
        }
        if (a < 200 || a >= 300) {
            m17513a(b, a, str);
        }
    }

    @Nullable
    /* renamed from: b */
    private static JSONObject m17491b(@Nullable Map<String, ? extends Object> map) {
        if (map == null) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        for (String str : map.keySet()) {
            Object obj = map.get(str);
            if (obj != null) {
                try {
                    if (obj instanceof Map) {
                        try {
                            jSONObject.put(str, m17491b((Map) obj));
                        } catch (ClassCastException unused) {
                        }
                    } else if (obj instanceof List) {
                        jSONObject.put(str, m17505a((List) obj));
                    } else {
                        if (!(obj instanceof Number) && !(obj instanceof Boolean)) {
                            jSONObject.put(str, obj.toString());
                        }
                        jSONObject.put(str, obj);
                    }
                } catch (JSONException unused2) {
                }
            }
        }
        return jSONObject;
    }

    @Nullable
    /* renamed from: a */
    private static JSONArray m17505a(@Nullable List list) {
        if (list == null) {
            return null;
        }
        JSONArray jSONArray = new JSONArray();
        for (Object obj : list) {
            if (obj instanceof Map) {
                jSONArray.put(m17491b((Map) obj));
            } else if (obj instanceof List) {
                jSONArray.put(m17505a((List) obj));
            } else if ((obj instanceof Number) || (obj instanceof Boolean)) {
                jSONArray.put(obj);
            } else {
                jSONArray.put(obj.toString());
            }
        }
        return jSONArray;
    }

    /* renamed from: a */
    private static void m17506a(@NonNull HttpURLConnection httpURLConnection, @NonNull RequestOptions oVar) {
        if (oVar.m17577b() != null && !TextUtils.isEmpty(oVar.m17577b())) {
            httpURLConnection.setRequestProperty(AbstractC3144SM.COOKIE, "m=" + oVar.m17577b());
        }
    }

    /* renamed from: a */
    private static HttpURLConnection m17512a(@NonNull String str, @NonNull RequestOptions oVar) throws IOException {
        HttpURLConnection b = m17494b(str, oVar);
        b.setRequestMethod("DELETE");
        return b;
    }

    /* renamed from: a */
    private static HttpURLConnection m17510a(String str, String str2, RequestOptions oVar) throws IOException {
        HttpURLConnection b = m17494b(m17488c(str, str2), oVar);
        b.setRequestMethod("GET");
        return b;
    }

    /* renamed from: a */
    private static HttpURLConnection m17507a(@NonNull String str, @NonNull Map<String, Object> map, @NonNull RequestOptions oVar) throws IOException, InvalidRequestException {
        Throwable th;
        OutputStream outputStream;
        HttpURLConnection b = m17494b(str, oVar);
        b.setDoOutput(true);
        b.setRequestMethod("POST");
        b.setRequestProperty("Content-Type", m17496b(oVar));
        try {
            outputStream = b.getOutputStream();
            try {
                outputStream.write(m17502a(map, oVar));
                if (outputStream != null) {
                    outputStream.close();
                }
                return b;
            } catch (Throwable th2) {
                th = th2;
                if (outputStream != null) {
                    outputStream.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            outputStream = null;
        }
    }

    /* renamed from: b */
    private static HttpURLConnection m17494b(String str, RequestOptions oVar) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
        httpURLConnection.setConnectTimeout(30000);
        httpURLConnection.setReadTimeout(80000);
        httpURLConnection.setUseCaches(false);
        if (m17483e(str)) {
            for (Map.Entry<String, String> entry : m17520a(oVar).entrySet()) {
                httpURLConnection.setRequestProperty(entry.getKey(), entry.getValue());
            }
        }
        if (m17482f(str)) {
            m17506a(httpURLConnection, oVar);
        }
        if (httpURLConnection instanceof HttpsURLConnection) {
            ((HttpsURLConnection) httpURLConnection).setSSLSocketFactory(f12350m);
        }
        return httpURLConnection;
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x005d, code lost:
        if (r1 == null) goto L_0x002a;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:?, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:?, code lost:
        return;
     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void m17499a(@android.support.annotation.NonNull java.util.Map<java.lang.String, java.lang.Object> r4, @android.support.annotation.NonNull java.lang.String r5, @android.support.annotation.NonNull java.lang.String r6, @android.support.annotation.Nullable com.stripe.android.RequestOptions r7, @android.support.annotation.Nullable com.stripe.android.StripeApiHandler.AbstractC2412a r8) {
        /*
            r0 = 1
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
            r1 = 0
            java.lang.String r2 = "networkaddress.cache.ttl"
            java.lang.String r1 = java.security.Security.getProperty(r2)     // Catch: SecurityException -> 0x0014
            java.lang.String r2 = "networkaddress.cache.ttl"
            java.lang.String r3 = "0"
            java.security.Security.setProperty(r2, r3)     // Catch: SecurityException -> 0x0014
            goto L_0x0019
        L_0x0014:
            r0 = 0
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
        L_0x0019:
            com.stripe.android.u r4 = m17508a(r6, r5, r4, r7)     // Catch: all -> 0x0038, arf -> 0x003a
            if (r8 == 0) goto L_0x0022
            r8.m17479a(r4)     // Catch: all -> 0x0038, arf -> 0x003a
        L_0x0022:
            boolean r4 = r0.booleanValue()
            if (r4 == 0) goto L_0x0060
            if (r1 != 0) goto L_0x0032
        L_0x002a:
            java.lang.String r4 = "networkaddress.cache.ttl"
            java.lang.String r5 = "-1"
            java.security.Security.setProperty(r4, r5)
            goto L_0x0060
        L_0x0032:
            java.lang.String r4 = "networkaddress.cache.ttl"
            java.security.Security.setProperty(r4, r1)
            goto L_0x0060
        L_0x0038:
            r4 = move-exception
            goto L_0x0041
        L_0x003a:
            r4 = move-exception
            if (r8 == 0) goto L_0x0057
            r8.m17478a(r4)     // Catch: all -> 0x0038
            goto L_0x0057
        L_0x0041:
            boolean r5 = r0.booleanValue()
            if (r5 == 0) goto L_0x0056
            if (r1 != 0) goto L_0x0051
            java.lang.String r5 = "networkaddress.cache.ttl"
            java.lang.String r6 = "-1"
            java.security.Security.setProperty(r5, r6)
            goto L_0x0056
        L_0x0051:
            java.lang.String r5 = "networkaddress.cache.ttl"
            java.security.Security.setProperty(r5, r1)
        L_0x0056:
            throw r4
        L_0x0057:
            boolean r4 = r0.booleanValue()
            if (r4 == 0) goto L_0x0060
            if (r1 != 0) goto L_0x0032
            goto L_0x002a
        L_0x0060:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.stripe.android.StripeApiHandler.m17499a(java.util.Map, java.lang.String, java.lang.String, com.stripe.android.o, com.stripe.android.s$a):void");
    }

    /* renamed from: c */
    private static List<C2413b> m17486c(Map<String, Object> map) throws InvalidRequestException {
        return m17500a(map, (String) null);
    }

    /* renamed from: a */
    private static List<C2413b> m17504a(List<Object> list, String str) throws InvalidRequestException {
        LinkedList linkedList = new LinkedList();
        String format = String.format("%s[]", str);
        if (list.isEmpty()) {
            linkedList.add(new C2413b(str, ""));
        } else {
            for (Object obj : list) {
                linkedList.addAll(m17515a(obj, format));
            }
        }
        return linkedList;
    }

    /* renamed from: a */
    private static List<C2413b> m17500a(Map<String, Object> map, String str) throws InvalidRequestException {
        LinkedList linkedList = new LinkedList();
        if (map == null) {
            return linkedList;
        }
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (str != null) {
                key = String.format("%s[%s]", str, key);
            }
            linkedList.addAll(m17515a(value, key));
        }
        return linkedList;
    }

    /* renamed from: a */
    private static List<C2413b> m17515a(Object obj, String str) throws InvalidRequestException {
        if (obj instanceof Map) {
            return m17500a((Map<String, Object>) obj, str);
        }
        if (obj instanceof List) {
            return m17504a((List<Object>) obj, str);
        }
        if ("".equals(obj)) {
            throw new InvalidRequestException("You cannot set '" + str + "' to an empty string. We interpret empty strings as null in requests. You may set '" + str + "' to null to delete the property.", str, null, 0, null);
        } else if (obj == null) {
            LinkedList linkedList = new LinkedList();
            linkedList.add(new C2413b(str, ""));
            return linkedList;
        } else {
            LinkedList linkedList2 = new LinkedList();
            linkedList2.add(new C2413b(str, obj.toString()));
            return linkedList2;
        }
    }

    /* renamed from: c */
    private static String m17488c(String str, String str2) {
        if (str2 == null || str2.isEmpty()) {
            return str;
        }
        return String.format("%s%s%s", str, str.contains("?") ? C4745bt.f20071b : "?", str2);
    }

    /* renamed from: b */
    private static String m17496b(@NonNull RequestOptions oVar) {
        return RequestOptions.f12302b.equals(oVar.m17574e()) ? String.format("application/json; charset=%s", "UTF-8") : String.format("application/x-www-form-urlencoded;charset=%s", "UTF-8");
    }

    /* renamed from: a */
    private static byte[] m17502a(@NonNull Map<String, Object> map, @NonNull RequestOptions oVar) throws InvalidRequestException {
        try {
            if (!RequestOptions.f12302b.equals(oVar.m17574e())) {
                return m17503a(map).getBytes("UTF-8");
            }
            JSONObject b = m17491b(map);
            if (b != null) {
                return b.toString().getBytes("UTF-8");
            }
            throw new InvalidRequestException("Unable to create JSON data from parameters. Please contact support@stripe.com for assistance.", null, null, 0, null);
        } catch (UnsupportedEncodingException e) {
            throw new InvalidRequestException("Unable to encode parameters to UTF-8. Please contact support@stripe.com for assistance.", null, null, 0, e);
        }
    }

    /* renamed from: a */
    private static String m17516a(InputStream inputStream) throws IOException {
        Scanner useDelimiter = new Scanner(inputStream, "UTF-8").useDelimiter("\\A");
        String next = useDelimiter.hasNext() ? useDelimiter.next() : null;
        inputStream.close();
        return next;
    }

    /* renamed from: a */
    private static StripeResponse m17508a(String str, String str2, Map<String, Object> map, RequestOptions oVar) throws InvalidRequestException, APIConnectionException, APIException {
        String str3;
        HttpURLConnection a;
        char c = 65535;
        HttpURLConnection httpURLConnection = null;
        try {
            try {
                int hashCode = str.hashCode();
                if (hashCode != 70454) {
                    if (hashCode != 2461856) {
                        if (hashCode == 2012838315 && str.equals("DELETE")) {
                            c = 2;
                        }
                    } else if (str.equals("POST")) {
                        c = 1;
                    }
                } else if (str.equals("GET")) {
                    c = 0;
                }
                switch (c) {
                    case 0:
                        a = m17510a(str2, m17503a(map), oVar);
                        break;
                    case 1:
                        a = m17507a(str2, map, oVar);
                        break;
                    case 2:
                        a = m17512a(str2, oVar);
                        break;
                    default:
                        throw new APIConnectionException(String.format("Unrecognized HTTP method %s. This indicates a bug in the Stripe bindings. Please contact support@stripe.com for assistance.", str));
                }
                int responseCode = httpURLConnection.getResponseCode();
                if (responseCode < 200 || responseCode >= 300) {
                    str3 = m17516a(httpURLConnection.getErrorStream());
                } else {
                    str3 = m17516a(httpURLConnection.getInputStream());
                }
                return new StripeResponse(responseCode, str3, httpURLConnection.getHeaderFields());
            } catch (IOException e) {
                throw new APIConnectionException(String.format("IOException during API request to Stripe (%s): %s Please check your internet connection and try again. If this problem persists, you should check Stripe's service status at https://twitter.com/stripestatus, or let us know at support@stripe.com.", m17526a(), e.getMessage()), e);
            }
        } finally {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        }
    }

    /* renamed from: a */
    private static void m17513a(String str, int i, String str2) throws InvalidRequestException, AuthenticationException, CardException, APIException {
        ErrorParser.C2380a a = ErrorParser.m18008a(str);
        if (i != 429) {
            switch (i) {
                case 400:
                    throw new InvalidRequestException(a.f11865b, a.f11867d, str2, Integer.valueOf(i), null);
                case 401:
                    throw new AuthenticationException(a.f11865b, str2, Integer.valueOf(i));
                case 402:
                    throw new CardException(a.f11865b, str2, a.f11866c, a.f11867d, a.f11868e, a.f11869f, Integer.valueOf(i), null);
                case 403:
                    throw new PermissionException(a.f11865b, str2, Integer.valueOf(i));
                case 404:
                    throw new InvalidRequestException(a.f11865b, a.f11867d, str2, Integer.valueOf(i), null);
                default:
                    throw new APIException(a.f11865b, str2, Integer.valueOf(i), null);
            }
        } else {
            throw new RateLimitException(a.f11865b, a.f11867d, str2, Integer.valueOf(i), null);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x002b  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0077  */
    /* renamed from: b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static com.stripe.android.StripeResponse m17492b(java.lang.String r6, java.lang.String r7, java.util.Map<java.lang.String, java.lang.Object> r8, com.stripe.android.RequestOptions r9) throws p110z1.AuthenticationException, p110z1.InvalidRequestException, p110z1.APIConnectionException, p110z1.CardException, p110z1.APIException {
        /*
            r0 = 0
            if (r9 != 0) goto L_0x0004
            return r0
        L_0x0004:
            r1 = 1
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)
            r2 = 0
            java.lang.String r3 = "networkaddress.cache.ttl"
            java.lang.String r3 = java.security.Security.getProperty(r3)     // Catch: SecurityException -> 0x0018
            java.lang.String r4 = "networkaddress.cache.ttl"
            java.lang.String r5 = "0"
            java.security.Security.setProperty(r4, r5)     // Catch: SecurityException -> 0x0019
            goto L_0x001d
        L_0x0018:
            r3 = r0
        L_0x0019:
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r2)
        L_0x001d:
            java.lang.String r4 = r9.m17575d()
            java.lang.String r4 = r4.trim()
            boolean r4 = r4.isEmpty()
            if (r4 != 0) goto L_0x0077
            com.stripe.android.u r6 = m17508a(r6, r7, r8, r9)
            int r7 = r6.m17467a()
            java.lang.String r8 = r6.m17466b()
            java.util.Map r9 = r6.m17465c()
            if (r9 != 0) goto L_0x003f
            r9 = r0
            goto L_0x0047
        L_0x003f:
            java.lang.String r4 = "Request-Id"
            java.lang.Object r9 = r9.get(r4)
            java.util.List r9 = (java.util.List) r9
        L_0x0047:
            if (r9 == 0) goto L_0x0056
            int r4 = r9.size()
            if (r4 <= 0) goto L_0x0056
            java.lang.Object r9 = r9.get(r2)
            r0 = r9
            java.lang.String r0 = (java.lang.String) r0
        L_0x0056:
            r9 = 200(0xc8, float:2.8E-43)
            if (r7 < r9) goto L_0x005e
            r9 = 300(0x12c, float:4.2E-43)
            if (r7 < r9) goto L_0x0061
        L_0x005e:
            m17513a(r8, r7, r0)
        L_0x0061:
            boolean r7 = r1.booleanValue()
            if (r7 == 0) goto L_0x0076
            if (r3 != 0) goto L_0x0071
            java.lang.String r7 = "networkaddress.cache.ttl"
            java.lang.String r8 = "-1"
            java.security.Security.setProperty(r7, r8)
            goto L_0x0076
        L_0x0071:
            java.lang.String r7 = "networkaddress.cache.ttl"
            java.security.Security.setProperty(r7, r3)
        L_0x0076:
            return r6
        L_0x0077:
            z1.ara r6 = new z1.ara
            java.lang.Integer r7 = java.lang.Integer.valueOf(r2)
            java.lang.String r8 = "No API key provided. (HINT: set your API key using 'Stripe.apiKey = <API-KEY>'. You can generate API keys from the Stripe web interface. See https://stripe.com/api for details or email support@stripe.com if you have questions."
            r6.<init>(r8, r0, r7)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.stripe.android.StripeApiHandler.m17492b(java.lang.String, java.lang.String, java.util.Map, com.stripe.android.o):com.stripe.android.u");
    }

    @Nullable
    /* renamed from: c */
    private static Token m17487c(String str, String str2, Map<String, Object> map, RequestOptions oVar) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
        return Token.m17590a(m17492b(str, str2, map, oVar).m17466b());
    }

    /* renamed from: a */
    private static void m17525a(@NonNull Context context, @Nullable AbstractC2412a aVar) {
        Map<String, Object> a = TelemetryClientUtil.m17198a(context);
        StripeNetworkUtils.m17470a(a);
        if (aVar == null || aVar.m17480a()) {
            m17499a(a, f12344g, "POST", RequestOptions.m17579a(null, RequestOptions.f12302b).m17569c(TelemetryClientUtil.m17195b(context)).m17572a(), aVar);
        }
    }

    /* renamed from: e */
    private static boolean m17483e(@NonNull String str) {
        return str.startsWith(f12342e) || str.startsWith(f12343f);
    }

    /* renamed from: f */
    private static boolean m17482f(@NonNull String str) {
        return str.startsWith(f12344g);
    }

    /* renamed from: d */
    private static String m17484d(String str, String str2) throws UnsupportedEncodingException {
        return String.format("%s=%s", m17481g(str), m17481g(str2));
    }

    /* renamed from: g */
    private static String m17481g(String str) throws UnsupportedEncodingException {
        if (str == null) {
            return null;
        }
        return URLEncoder.encode(str, "UTF-8");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: StripeApiHandler.java */
    /* renamed from: com.stripe.android.s$b */
    /* loaded from: classes2.dex */
    public static final class C2413b {

        /* renamed from: a */
        public final String f12351a;

        /* renamed from: b */
        public final String f12352b;

        C2413b(String str, String str2) {
            this.f12351a = str;
            this.f12352b = str2;
        }
    }
}
