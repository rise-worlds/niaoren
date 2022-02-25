package org.apache.http.impl.auth;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import org.apache.http.Header;
import org.apache.http.HttpRequest;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.MalformedChallengeException;
import org.apache.http.auth.params.AuthParams;
import org.apache.http.message.BasicHeaderValueFormatter;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.message.BufferedHeader;
import org.apache.http.util.CharArrayBuffer;
import org.apache.http.util.EncodingUtils;
import p110z1.C3857aq;

@Deprecated
/* loaded from: assets/org.apache.http.legacy.boot */
public class DigestScheme extends RFC2617Scheme {
    private static final char[] HEXADECIMAL = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /* renamed from: NC */
    private static final String f14725NC = "00000001";
    private static final int QOP_AUTH = 2;
    private static final int QOP_AUTH_INT = 1;
    private static final int QOP_MISSING = 0;
    private String cnonce;
    private int qopVariant = 0;
    private boolean complete = false;

    @Override // org.apache.http.impl.auth.AuthSchemeBase, org.apache.http.auth.AuthScheme
    public void processChallenge(Header header) throws MalformedChallengeException {
        super.processChallenge(header);
        if (getParameter("realm") == null) {
            throw new MalformedChallengeException("missing realm in challange");
        } else if (getParameter("nonce") != null) {
            boolean unsupportedQop = false;
            String qop = getParameter("qop");
            if (qop != null) {
                StringTokenizer tok = new StringTokenizer(qop, ",");
                while (true) {
                    if (!tok.hasMoreTokens()) {
                        break;
                    }
                    String variant = tok.nextToken().trim();
                    if (variant.equals(C3857aq.f17253d)) {
                        this.qopVariant = 2;
                        break;
                    } else if (variant.equals("auth-int")) {
                        this.qopVariant = 1;
                    } else {
                        unsupportedQop = true;
                    }
                }
            }
            if (!unsupportedQop || this.qopVariant != 0) {
                this.cnonce = null;
                this.complete = true;
                return;
            }
            throw new MalformedChallengeException("None of the qop methods is supported");
        } else {
            throw new MalformedChallengeException("missing nonce in challange");
        }
    }

    @Override // org.apache.http.auth.AuthScheme
    public boolean isComplete() {
        String s = getParameter("stale");
        if ("true".equalsIgnoreCase(s)) {
            return false;
        }
        return this.complete;
    }

    @Override // org.apache.http.auth.AuthScheme
    public String getSchemeName() {
        return "digest";
    }

    @Override // org.apache.http.auth.AuthScheme
    public boolean isConnectionBased() {
        return false;
    }

    public void overrideParamter(String name, String value) {
        getParameters().put(name, value);
    }

    private String getCnonce() {
        if (this.cnonce == null) {
            this.cnonce = createCnonce();
        }
        return this.cnonce;
    }

    @Override // org.apache.http.auth.AuthScheme
    public Header authenticate(Credentials credentials, HttpRequest request) throws AuthenticationException {
        if (credentials == null) {
            throw new IllegalArgumentException("Credentials may not be null");
        } else if (request != null) {
            getParameters().put("methodname", request.getRequestLine().getMethod());
            getParameters().put("uri", request.getRequestLine().getUri());
            String charset = getParameter(com.github.kevinsawicki.http.HttpRequest.PARAM_CHARSET);
            if (charset == null) {
                String charset2 = AuthParams.getCredentialCharset(request.getParams());
                getParameters().put(com.github.kevinsawicki.http.HttpRequest.PARAM_CHARSET, charset2);
            }
            String digest = createDigest(credentials);
            return createDigestHeader(credentials, digest);
        } else {
            throw new IllegalArgumentException("HTTP request may not be null");
        }
    }

    private static MessageDigest createMessageDigest(String digAlg) throws UnsupportedDigestAlgorithmException {
        try {
            return MessageDigest.getInstance(digAlg);
        } catch (Exception e) {
            throw new UnsupportedDigestAlgorithmException("Unsupported algorithm in HTTP Digest authentication: " + digAlg);
        }
    }

    private String createDigest(Credentials credentials) throws AuthenticationException {
        String qopOption;
        String uri = getParameter("uri");
        String realm = getParameter("realm");
        String nonce = getParameter("nonce");
        String method = getParameter("methodname");
        String algorithm = getParameter("algorithm");
        if (uri == null) {
            throw new IllegalStateException("URI may not be null");
        } else if (realm == null) {
            throw new IllegalStateException("Realm may not be null");
        } else if (nonce != null) {
            if (algorithm == null) {
                algorithm = "MD5";
            }
            String charset = getParameter(com.github.kevinsawicki.http.HttpRequest.PARAM_CHARSET);
            if (charset == null) {
                charset = "ISO-8859-1";
            }
            if (this.qopVariant != 1) {
                MessageDigest md5Helper = createMessageDigest("MD5");
                String uname = credentials.getUserPrincipal().getName();
                String pwd = credentials.getPassword();
                StringBuilder tmp = new StringBuilder(uname.length() + realm.length() + pwd.length() + 2);
                tmp.append(uname);
                tmp.append(':');
                tmp.append(realm);
                tmp.append(':');
                tmp.append(pwd);
                String a1 = tmp.toString();
                if (algorithm.equalsIgnoreCase("MD5-sess")) {
                    String cnonce = getCnonce();
                    String tmp2 = encode(md5Helper.digest(EncodingUtils.getBytes(a1, charset)));
                    StringBuilder tmp3 = new StringBuilder(tmp2.length() + nonce.length() + cnonce.length() + 2);
                    tmp3.append(tmp2);
                    tmp3.append(':');
                    tmp3.append(nonce);
                    tmp3.append(':');
                    tmp3.append(cnonce);
                    a1 = tmp3.toString();
                } else if (!algorithm.equalsIgnoreCase("MD5")) {
                    throw new AuthenticationException("Unhandled algorithm " + algorithm + " requested");
                }
                String md5a1 = encode(md5Helper.digest(EncodingUtils.getBytes(a1, charset)));
                String a2 = null;
                if (this.qopVariant != 1) {
                    a2 = method + ':' + uri;
                }
                String md5a2 = encode(md5Helper.digest(EncodingUtils.getAsciiBytes(a2)));
                if (this.qopVariant == 0) {
                    StringBuilder tmp22 = new StringBuilder(md5a1.length() + nonce.length() + md5a2.length());
                    tmp22.append(md5a1);
                    tmp22.append(':');
                    tmp22.append(nonce);
                    tmp22.append(':');
                    tmp22.append(md5a2);
                    qopOption = tmp22.toString();
                } else {
                    String qopOption2 = getQopVariantString();
                    String cnonce2 = getCnonce();
                    StringBuilder tmp23 = new StringBuilder(md5a1.length() + nonce.length() + f14725NC.length() + cnonce2.length() + qopOption2.length() + md5a2.length() + 5);
                    tmp23.append(md5a1);
                    tmp23.append(':');
                    tmp23.append(nonce);
                    tmp23.append(':');
                    tmp23.append(f14725NC);
                    tmp23.append(':');
                    tmp23.append(cnonce2);
                    tmp23.append(':');
                    tmp23.append(qopOption2);
                    tmp23.append(':');
                    tmp23.append(md5a2);
                    qopOption = tmp23.toString();
                }
                String serverDigest = encode(md5Helper.digest(EncodingUtils.getAsciiBytes(qopOption)));
                return serverDigest;
            }
            throw new AuthenticationException("Unsupported qop in HTTP Digest authentication");
        } else {
            throw new IllegalStateException("Nonce may not be null");
        }
    }

    private Header createDigestHeader(Credentials credentials, String digest) throws AuthenticationException {
        CharArrayBuffer buffer = new CharArrayBuffer(128);
        if (isProxy()) {
            buffer.append("Proxy-Authorization");
        } else {
            buffer.append("Authorization");
        }
        buffer.append(": Digest ");
        String uri = getParameter("uri");
        String realm = getParameter("realm");
        String nonce = getParameter("nonce");
        String opaque = getParameter("opaque");
        String algorithm = getParameter("algorithm");
        String uname = credentials.getUserPrincipal().getName();
        List<BasicNameValuePair> params = new ArrayList<>(20);
        params.add(new BasicNameValuePair("username", uname));
        params.add(new BasicNameValuePair("realm", realm));
        params.add(new BasicNameValuePair("nonce", nonce));
        params.add(new BasicNameValuePair("uri", uri));
        params.add(new BasicNameValuePair("response", digest));
        if (this.qopVariant != 0) {
            params.add(new BasicNameValuePair("qop", getQopVariantString()));
            params.add(new BasicNameValuePair("nc", f14725NC));
            params.add(new BasicNameValuePair("cnonce", getCnonce()));
        }
        if (algorithm != null) {
            params.add(new BasicNameValuePair("algorithm", algorithm));
        }
        if (opaque != null) {
            params.add(new BasicNameValuePair("opaque", opaque));
        }
        for (int i = 0; i < params.size(); i++) {
            BasicNameValuePair param = params.get(i);
            if (i > 0) {
                buffer.append(", ");
            }
            boolean z = true;
            boolean noQuotes = "nc".equals(param.getName()) || "qop".equals(param.getName());
            BasicHeaderValueFormatter basicHeaderValueFormatter = BasicHeaderValueFormatter.DEFAULT;
            if (noQuotes) {
                z = false;
            }
            basicHeaderValueFormatter.formatNameValuePair(buffer, param, z);
        }
        return new BufferedHeader(buffer);
    }

    private String getQopVariantString() {
        if (this.qopVariant == 1) {
            return "auth-int";
        }
        return C3857aq.f17253d;
    }

    private static String encode(byte[] binaryData) {
        if (binaryData.length != 16) {
            return null;
        }
        char[] buffer = new char[32];
        for (int i = 0; i < 16; i++) {
            int low = binaryData[i] & 15;
            int high = (binaryData[i] & 240) >> 4;
            buffer[i * 2] = HEXADECIMAL[high];
            buffer[(i * 2) + 1] = HEXADECIMAL[low];
        }
        return new String(buffer);
    }

    public static String createCnonce() {
        MessageDigest md5Helper = createMessageDigest("MD5");
        String cnonce = Long.toString(System.currentTimeMillis());
        return encode(md5Helper.digest(EncodingUtils.getAsciiBytes(cnonce)));
    }
}
