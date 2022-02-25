package com.sun.mail.smtp;

import com.sun.mail.util.ASCIIUtility;
import com.sun.mail.util.BASE64EncoderStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Hashtable;
import java.util.StringTokenizer;
import p110z1.C3857aq;

/* loaded from: classes2.dex */
public class DigestMD5 {
    private static char[] digits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private String clientResponse;
    private PrintStream debugout;
    private MessageDigest md5;
    private String uri;

    public DigestMD5(PrintStream printStream) {
        this.debugout = printStream;
        if (printStream != null) {
            printStream.println("DEBUG DIGEST-MD5: Loaded");
        }
    }

    public byte[] authClient(String str, String str2, String str3, String str4, String str5) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        BASE64EncoderStream bASE64EncoderStream = new BASE64EncoderStream(byteArrayOutputStream, Integer.MAX_VALUE);
        try {
            SecureRandom secureRandom = new SecureRandom();
            this.md5 = MessageDigest.getInstance("MD5");
            StringBuffer stringBuffer = new StringBuffer();
            this.uri = "smtp/" + str;
            byte[] bArr = new byte[32];
            PrintStream printStream = this.debugout;
            if (printStream != null) {
                printStream.println("DEBUG DIGEST-MD5: Begin authentication ...");
            }
            Hashtable hashtable = tokenize(str5);
            if (str4 == null) {
                String str6 = (String) hashtable.get("realm");
                str4 = str6 != null ? new StringTokenizer(str6, ",").nextToken() : str;
            }
            String str7 = (String) hashtable.get("nonce");
            secureRandom.nextBytes(bArr);
            bASE64EncoderStream.write(bArr);
            bASE64EncoderStream.flush();
            String byteArrayOutputStream2 = byteArrayOutputStream.toString();
            byteArrayOutputStream.reset();
            MessageDigest messageDigest = this.md5;
            messageDigest.update(messageDigest.digest(ASCIIUtility.getBytes(String.valueOf(str2) + ":" + str4 + ":" + str3)));
            MessageDigest messageDigest2 = this.md5;
            messageDigest2.update(ASCIIUtility.getBytes(":" + str7 + ":" + byteArrayOutputStream2));
            this.clientResponse = String.valueOf(toHex(this.md5.digest())) + ":" + str7 + ":00000001:" + byteArrayOutputStream2 + ":" + C3857aq.f17253d + ":";
            MessageDigest messageDigest3 = this.md5;
            StringBuilder sb = new StringBuilder("AUTHENTICATE:");
            sb.append(this.uri);
            messageDigest3.update(ASCIIUtility.getBytes(sb.toString()));
            MessageDigest messageDigest4 = this.md5;
            StringBuilder sb2 = new StringBuilder(String.valueOf(this.clientResponse));
            sb2.append(toHex(this.md5.digest()));
            messageDigest4.update(ASCIIUtility.getBytes(sb2.toString()));
            stringBuffer.append("username=\"" + str2 + "\"");
            stringBuffer.append(",realm=\"" + str4 + "\"");
            StringBuilder sb3 = new StringBuilder(",qop=");
            sb3.append(C3857aq.f17253d);
            stringBuffer.append(sb3.toString());
            stringBuffer.append(",nc=00000001");
            stringBuffer.append(",nonce=\"" + str7 + "\"");
            stringBuffer.append(",cnonce=\"" + byteArrayOutputStream2 + "\"");
            stringBuffer.append(",digest-uri=\"" + this.uri + "\"");
            StringBuilder sb4 = new StringBuilder(",response=");
            sb4.append(toHex(this.md5.digest()));
            stringBuffer.append(sb4.toString());
            PrintStream printStream2 = this.debugout;
            if (printStream2 != null) {
                printStream2.println("DEBUG DIGEST-MD5: Response => " + stringBuffer.toString());
            }
            bASE64EncoderStream.write(ASCIIUtility.getBytes(stringBuffer.toString()));
            bASE64EncoderStream.flush();
            return byteArrayOutputStream.toByteArray();
        } catch (NoSuchAlgorithmException e) {
            PrintStream printStream3 = this.debugout;
            if (printStream3 != null) {
                printStream3.println("DEBUG DIGEST-MD5: " + e);
            }
            throw new IOException(e.toString());
        }
    }

    public boolean authServer(String str) throws IOException {
        Hashtable hashtable = tokenize(str);
        MessageDigest messageDigest = this.md5;
        messageDigest.update(ASCIIUtility.getBytes(":" + this.uri));
        MessageDigest messageDigest2 = this.md5;
        messageDigest2.update(ASCIIUtility.getBytes(String.valueOf(this.clientResponse) + toHex(this.md5.digest())));
        String hex = toHex(this.md5.digest());
        if (hex.equals((String) hashtable.get("rspauth"))) {
            return true;
        }
        PrintStream printStream = this.debugout;
        if (printStream == null) {
            return false;
        }
        printStream.println("DEBUG DIGEST-MD5: Expected => rspauth=" + hex);
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0089  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.util.Hashtable tokenize(java.lang.String r8) throws java.io.IOException {
        /*
            r7 = this;
            java.util.Hashtable r0 = new java.util.Hashtable
            r0.<init>()
            byte[] r8 = r8.getBytes()
            java.io.StreamTokenizer r1 = new java.io.StreamTokenizer
            java.io.InputStreamReader r2 = new java.io.InputStreamReader
            com.sun.mail.util.BASE64DecoderStream r3 = new com.sun.mail.util.BASE64DecoderStream
            java.io.ByteArrayInputStream r4 = new java.io.ByteArrayInputStream
            int r5 = r8.length
            r6 = 4
            int r5 = r5 - r6
            r4.<init>(r8, r6, r5)
            r3.<init>(r4)
            r2.<init>(r3)
            r1.<init>(r2)
            r8 = 57
            r2 = 48
            r1.ordinaryChars(r2, r8)
            r1.wordChars(r2, r8)
            r8 = 0
            r2 = r8
        L_0x002c:
            int r3 = r1.nextToken()
            r4 = -1
            if (r3 != r4) goto L_0x0034
            return r0
        L_0x0034:
            r4 = -3
            if (r3 == r4) goto L_0x003c
            r4 = 34
            if (r3 == r4) goto L_0x0041
            goto L_0x002c
        L_0x003c:
            if (r2 != 0) goto L_0x0041
            java.lang.String r2 = r1.sval
            goto L_0x002c
        L_0x0041:
            java.io.PrintStream r3 = r7.debugout
            if (r3 == 0) goto L_0x0065
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "DEBUG DIGEST-MD5: Received => "
            r4.<init>(r5)
            r4.append(r2)
            java.lang.String r5 = "='"
            r4.append(r5)
            java.lang.String r5 = r1.sval
            r4.append(r5)
            java.lang.String r5 = "'"
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            r3.println(r4)
        L_0x0065:
            boolean r3 = r0.containsKey(r2)
            if (r3 == 0) goto L_0x0089
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.Object r4 = r0.get(r2)
            r3.append(r4)
            java.lang.String r4 = ","
            r3.append(r4)
            java.lang.String r4 = r1.sval
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            r0.put(r2, r3)
            goto L_0x008e
        L_0x0089:
            java.lang.String r3 = r1.sval
            r0.put(r2, r3)
        L_0x008e:
            r2 = r8
            goto L_0x002c
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sun.mail.smtp.DigestMD5.tokenize(java.lang.String):java.util.Hashtable");
    }

    private static String toHex(byte[] bArr) {
        char[] cArr = new char[bArr.length * 2];
        int i = 0;
        for (byte b : bArr) {
            int i2 = b & 255;
            int i3 = i + 1;
            char[] cArr2 = digits;
            cArr[i] = cArr2[i2 >> 4];
            i = i3 + 1;
            cArr[i3] = cArr2[i2 & 15];
        }
        return new String(cArr);
    }
}
