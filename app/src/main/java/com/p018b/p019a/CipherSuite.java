package com.p018b.p019a;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* renamed from: com.b.a.l */
/* loaded from: classes.dex */
public final class CipherSuite {

    /* renamed from: bi */
    final String f6354bi;

    /* renamed from: bj */
    private static final ConcurrentMap<String, CipherSuite> f6329bj = new ConcurrentHashMap();

    /* renamed from: a */
    public static final CipherSuite f6267a = m24400a("SSL_RSA_WITH_NULL_MD5");

    /* renamed from: b */
    public static final CipherSuite f6320b = m24400a("SSL_RSA_WITH_NULL_SHA");

    /* renamed from: c */
    public static final CipherSuite f6330c = m24400a("SSL_RSA_EXPORT_WITH_RC4_40_MD5");

    /* renamed from: d */
    public static final CipherSuite f6331d = m24400a("SSL_RSA_WITH_RC4_128_MD5");

    /* renamed from: e */
    public static final CipherSuite f6332e = m24400a("SSL_RSA_WITH_RC4_128_SHA");

    /* renamed from: f */
    public static final CipherSuite f6333f = m24400a("SSL_RSA_EXPORT_WITH_DES40_CBC_SHA");

    /* renamed from: g */
    public static final CipherSuite f6334g = m24400a("SSL_RSA_WITH_DES_CBC_SHA");

    /* renamed from: h */
    public static final CipherSuite f6335h = m24400a("SSL_RSA_WITH_3DES_EDE_CBC_SHA");

    /* renamed from: i */
    public static final CipherSuite f6336i = m24400a("SSL_DHE_DSS_EXPORT_WITH_DES40_CBC_SHA");

    /* renamed from: j */
    public static final CipherSuite f6337j = m24400a("SSL_DHE_DSS_WITH_DES_CBC_SHA");

    /* renamed from: k */
    public static final CipherSuite f6338k = m24400a("SSL_DHE_DSS_WITH_3DES_EDE_CBC_SHA");

    /* renamed from: l */
    public static final CipherSuite f6339l = m24400a("SSL_DHE_RSA_EXPORT_WITH_DES40_CBC_SHA");

    /* renamed from: m */
    public static final CipherSuite f6340m = m24400a("SSL_DHE_RSA_WITH_DES_CBC_SHA");

    /* renamed from: n */
    public static final CipherSuite f6341n = m24400a("SSL_DHE_RSA_WITH_3DES_EDE_CBC_SHA");

    /* renamed from: o */
    public static final CipherSuite f6342o = m24400a("SSL_DH_anon_EXPORT_WITH_RC4_40_MD5");

    /* renamed from: p */
    public static final CipherSuite f6343p = m24400a("SSL_DH_anon_WITH_RC4_128_MD5");

    /* renamed from: q */
    public static final CipherSuite f6344q = m24400a("SSL_DH_anon_EXPORT_WITH_DES40_CBC_SHA");

    /* renamed from: r */
    public static final CipherSuite f6345r = m24400a("SSL_DH_anon_WITH_DES_CBC_SHA");

    /* renamed from: s */
    public static final CipherSuite f6346s = m24400a("SSL_DH_anon_WITH_3DES_EDE_CBC_SHA");

    /* renamed from: t */
    public static final CipherSuite f6347t = m24400a("TLS_KRB5_WITH_DES_CBC_SHA");

    /* renamed from: u */
    public static final CipherSuite f6348u = m24400a("TLS_KRB5_WITH_3DES_EDE_CBC_SHA");

    /* renamed from: v */
    public static final CipherSuite f6349v = m24400a("TLS_KRB5_WITH_RC4_128_SHA");

    /* renamed from: w */
    public static final CipherSuite f6350w = m24400a("TLS_KRB5_WITH_DES_CBC_MD5");

    /* renamed from: x */
    public static final CipherSuite f6351x = m24400a("TLS_KRB5_WITH_3DES_EDE_CBC_MD5");

    /* renamed from: y */
    public static final CipherSuite f6352y = m24400a("TLS_KRB5_WITH_RC4_128_MD5");

    /* renamed from: z */
    public static final CipherSuite f6353z = m24400a("TLS_KRB5_EXPORT_WITH_DES_CBC_40_SHA");

    /* renamed from: A */
    public static final CipherSuite f6241A = m24400a("TLS_KRB5_EXPORT_WITH_RC4_40_SHA");

    /* renamed from: B */
    public static final CipherSuite f6242B = m24400a("TLS_KRB5_EXPORT_WITH_DES_CBC_40_MD5");

    /* renamed from: C */
    public static final CipherSuite f6243C = m24400a("TLS_KRB5_EXPORT_WITH_RC4_40_MD5");

    /* renamed from: D */
    public static final CipherSuite f6244D = m24400a("TLS_RSA_WITH_AES_128_CBC_SHA");

    /* renamed from: E */
    public static final CipherSuite f6245E = m24400a("TLS_DHE_DSS_WITH_AES_128_CBC_SHA");

    /* renamed from: F */
    public static final CipherSuite f6246F = m24400a("TLS_DHE_RSA_WITH_AES_128_CBC_SHA");

    /* renamed from: G */
    public static final CipherSuite f6247G = m24400a("TLS_DH_anon_WITH_AES_128_CBC_SHA");

    /* renamed from: H */
    public static final CipherSuite f6248H = m24400a("TLS_RSA_WITH_AES_256_CBC_SHA");

    /* renamed from: I */
    public static final CipherSuite f6249I = m24400a("TLS_DHE_DSS_WITH_AES_256_CBC_SHA");

    /* renamed from: J */
    public static final CipherSuite f6250J = m24400a("TLS_DHE_RSA_WITH_AES_256_CBC_SHA");

    /* renamed from: K */
    public static final CipherSuite f6251K = m24400a("TLS_DH_anon_WITH_AES_256_CBC_SHA");

    /* renamed from: L */
    public static final CipherSuite f6252L = m24400a("TLS_RSA_WITH_NULL_SHA256");

    /* renamed from: M */
    public static final CipherSuite f6253M = m24400a("TLS_RSA_WITH_AES_128_CBC_SHA256");

    /* renamed from: N */
    public static final CipherSuite f6254N = m24400a("TLS_RSA_WITH_AES_256_CBC_SHA256");

    /* renamed from: O */
    public static final CipherSuite f6255O = m24400a("TLS_DHE_DSS_WITH_AES_128_CBC_SHA256");

    /* renamed from: P */
    public static final CipherSuite f6256P = m24400a("TLS_RSA_WITH_CAMELLIA_128_CBC_SHA");

    /* renamed from: Q */
    public static final CipherSuite f6257Q = m24400a("TLS_DHE_DSS_WITH_CAMELLIA_128_CBC_SHA");

    /* renamed from: R */
    public static final CipherSuite f6258R = m24400a("TLS_DHE_RSA_WITH_CAMELLIA_128_CBC_SHA");

    /* renamed from: S */
    public static final CipherSuite f6259S = m24400a("TLS_DHE_RSA_WITH_AES_128_CBC_SHA256");

    /* renamed from: T */
    public static final CipherSuite f6260T = m24400a("TLS_DHE_DSS_WITH_AES_256_CBC_SHA256");

    /* renamed from: U */
    public static final CipherSuite f6261U = m24400a("TLS_DHE_RSA_WITH_AES_256_CBC_SHA256");

    /* renamed from: V */
    public static final CipherSuite f6262V = m24400a("TLS_DH_anon_WITH_AES_128_CBC_SHA256");

    /* renamed from: W */
    public static final CipherSuite f6263W = m24400a("TLS_DH_anon_WITH_AES_256_CBC_SHA256");

    /* renamed from: X */
    public static final CipherSuite f6264X = m24400a("TLS_RSA_WITH_CAMELLIA_256_CBC_SHA");

    /* renamed from: Y */
    public static final CipherSuite f6265Y = m24400a("TLS_DHE_DSS_WITH_CAMELLIA_256_CBC_SHA");

    /* renamed from: Z */
    public static final CipherSuite f6266Z = m24400a("TLS_DHE_RSA_WITH_CAMELLIA_256_CBC_SHA");

    /* renamed from: aa */
    public static final CipherSuite f6294aa = m24400a("TLS_PSK_WITH_RC4_128_SHA");

    /* renamed from: ab */
    public static final CipherSuite f6295ab = m24400a("TLS_PSK_WITH_3DES_EDE_CBC_SHA");

    /* renamed from: ac */
    public static final CipherSuite f6296ac = m24400a("TLS_PSK_WITH_AES_128_CBC_SHA");

    /* renamed from: ad */
    public static final CipherSuite f6297ad = m24400a("TLS_PSK_WITH_AES_256_CBC_SHA");

    /* renamed from: ae */
    public static final CipherSuite f6298ae = m24400a("TLS_RSA_WITH_SEED_CBC_SHA");

    /* renamed from: af */
    public static final CipherSuite f6299af = m24400a("TLS_RSA_WITH_AES_128_GCM_SHA256");

    /* renamed from: ag */
    public static final CipherSuite f6300ag = m24400a("TLS_RSA_WITH_AES_256_GCM_SHA384");

    /* renamed from: ah */
    public static final CipherSuite f6301ah = m24400a("TLS_DHE_RSA_WITH_AES_128_GCM_SHA256");

    /* renamed from: ai */
    public static final CipherSuite f6302ai = m24400a("TLS_DHE_RSA_WITH_AES_256_GCM_SHA384");

    /* renamed from: aj */
    public static final CipherSuite f6303aj = m24400a("TLS_DHE_DSS_WITH_AES_128_GCM_SHA256");

    /* renamed from: ak */
    public static final CipherSuite f6304ak = m24400a("TLS_DHE_DSS_WITH_AES_256_GCM_SHA384");

    /* renamed from: al */
    public static final CipherSuite f6305al = m24400a("TLS_DH_anon_WITH_AES_128_GCM_SHA256");

    /* renamed from: am */
    public static final CipherSuite f6306am = m24400a("TLS_DH_anon_WITH_AES_256_GCM_SHA384");

    /* renamed from: an */
    public static final CipherSuite f6307an = m24400a("TLS_EMPTY_RENEGOTIATION_INFO_SCSV");

    /* renamed from: ao */
    public static final CipherSuite f6308ao = m24400a("TLS_FALLBACK_SCSV");

    /* renamed from: ap */
    public static final CipherSuite f6309ap = m24400a("TLS_ECDH_ECDSA_WITH_NULL_SHA");

    /* renamed from: aq */
    public static final CipherSuite f6310aq = m24400a("TLS_ECDH_ECDSA_WITH_RC4_128_SHA");

    /* renamed from: ar */
    public static final CipherSuite f6311ar = m24400a("TLS_ECDH_ECDSA_WITH_3DES_EDE_CBC_SHA");

    /* renamed from: as */
    public static final CipherSuite f6312as = m24400a("TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA");

    /* renamed from: at */
    public static final CipherSuite f6313at = m24400a("TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA");

    /* renamed from: au */
    public static final CipherSuite f6314au = m24400a("TLS_ECDHE_ECDSA_WITH_NULL_SHA");

    /* renamed from: av */
    public static final CipherSuite f6315av = m24400a("TLS_ECDHE_ECDSA_WITH_RC4_128_SHA");

    /* renamed from: aw */
    public static final CipherSuite f6316aw = m24400a("TLS_ECDHE_ECDSA_WITH_3DES_EDE_CBC_SHA");

    /* renamed from: ax */
    public static final CipherSuite f6317ax = m24400a("TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA");

    /* renamed from: ay */
    public static final CipherSuite f6318ay = m24400a("TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA");

    /* renamed from: az */
    public static final CipherSuite f6319az = m24400a("TLS_ECDH_RSA_WITH_NULL_SHA");

    /* renamed from: aA */
    public static final CipherSuite f6268aA = m24400a("TLS_ECDH_RSA_WITH_RC4_128_SHA");

    /* renamed from: aB */
    public static final CipherSuite f6269aB = m24400a("TLS_ECDH_RSA_WITH_3DES_EDE_CBC_SHA");

    /* renamed from: aC */
    public static final CipherSuite f6270aC = m24400a("TLS_ECDH_RSA_WITH_AES_128_CBC_SHA");

    /* renamed from: aD */
    public static final CipherSuite f6271aD = m24400a("TLS_ECDH_RSA_WITH_AES_256_CBC_SHA");

    /* renamed from: aE */
    public static final CipherSuite f6272aE = m24400a("TLS_ECDHE_RSA_WITH_NULL_SHA");

    /* renamed from: aF */
    public static final CipherSuite f6273aF = m24400a("TLS_ECDHE_RSA_WITH_RC4_128_SHA");

    /* renamed from: aG */
    public static final CipherSuite f6274aG = m24400a("TLS_ECDHE_RSA_WITH_3DES_EDE_CBC_SHA");

    /* renamed from: aH */
    public static final CipherSuite f6275aH = m24400a("TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA");

    /* renamed from: aI */
    public static final CipherSuite f6276aI = m24400a("TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA");

    /* renamed from: aJ */
    public static final CipherSuite f6277aJ = m24400a("TLS_ECDH_anon_WITH_NULL_SHA");

    /* renamed from: aK */
    public static final CipherSuite f6278aK = m24400a("TLS_ECDH_anon_WITH_RC4_128_SHA");

    /* renamed from: aL */
    public static final CipherSuite f6279aL = m24400a("TLS_ECDH_anon_WITH_3DES_EDE_CBC_SHA");

    /* renamed from: aM */
    public static final CipherSuite f6280aM = m24400a("TLS_ECDH_anon_WITH_AES_128_CBC_SHA");

    /* renamed from: aN */
    public static final CipherSuite f6281aN = m24400a("TLS_ECDH_anon_WITH_AES_256_CBC_SHA");

    /* renamed from: aO */
    public static final CipherSuite f6282aO = m24400a("TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA256");

    /* renamed from: aP */
    public static final CipherSuite f6283aP = m24400a("TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA384");

    /* renamed from: aQ */
    public static final CipherSuite f6284aQ = m24400a("TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA256");

    /* renamed from: aR */
    public static final CipherSuite f6285aR = m24400a("TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA384");

    /* renamed from: aS */
    public static final CipherSuite f6286aS = m24400a("TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256");

    /* renamed from: aT */
    public static final CipherSuite f6287aT = m24400a("TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA384");

    /* renamed from: aU */
    public static final CipherSuite f6288aU = m24400a("TLS_ECDH_RSA_WITH_AES_128_CBC_SHA256");

    /* renamed from: aV */
    public static final CipherSuite f6289aV = m24400a("TLS_ECDH_RSA_WITH_AES_256_CBC_SHA384");

    /* renamed from: aW */
    public static final CipherSuite f6290aW = m24400a("TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256");

    /* renamed from: aX */
    public static final CipherSuite f6291aX = m24400a("TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384");

    /* renamed from: aY */
    public static final CipherSuite f6292aY = m24400a("TLS_ECDH_ECDSA_WITH_AES_128_GCM_SHA256");

    /* renamed from: aZ */
    public static final CipherSuite f6293aZ = m24400a("TLS_ECDH_ECDSA_WITH_AES_256_GCM_SHA384");

    /* renamed from: ba */
    public static final CipherSuite f6321ba = m24400a("TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256");

    /* renamed from: bb */
    public static final CipherSuite f6322bb = m24400a("TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384");

    /* renamed from: bc */
    public static final CipherSuite f6323bc = m24400a("TLS_ECDH_RSA_WITH_AES_128_GCM_SHA256");

    /* renamed from: bd */
    public static final CipherSuite f6324bd = m24400a("TLS_ECDH_RSA_WITH_AES_256_GCM_SHA384");

    /* renamed from: be */
    public static final CipherSuite f6325be = m24400a("TLS_ECDHE_PSK_WITH_AES_128_CBC_SHA");

    /* renamed from: bf */
    public static final CipherSuite f6326bf = m24400a("TLS_ECDHE_PSK_WITH_AES_256_CBC_SHA");

    /* renamed from: bg */
    public static final CipherSuite f6327bg = m24400a("TLS_ECDHE_RSA_WITH_CHACHA20_POLY1305_SHA256");

    /* renamed from: bh */
    public static final CipherSuite f6328bh = m24400a("TLS_ECDHE_ECDSA_WITH_CHACHA20_POLY1305_SHA256");

    /* renamed from: a */
    public static CipherSuite m24400a(String str) {
        CipherSuite lVar = f6329bj.get(str);
        if (lVar != null) {
            return lVar;
        }
        CipherSuite lVar2 = new CipherSuite(str);
        CipherSuite putIfAbsent = f6329bj.putIfAbsent(str, lVar2);
        return putIfAbsent == null ? lVar2 : putIfAbsent;
    }

    private CipherSuite(String str) {
        if (str != null) {
            this.f6354bi = str;
            return;
        }
        throw new NullPointerException();
    }

    public final String toString() {
        return this.f6354bi;
    }
}
