package com.tendcloud.tenddata;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CodingErrorAction;
import org.apache.http.protocol.HTTP;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.dl */
/* loaded from: classes2.dex */
public class C2806dl {

    /* renamed from: a */
    public static CodingErrorAction f13758a = CodingErrorAction.REPORT;

    /* renamed from: a */
    public static byte[] m16062a(String str) {
        try {
            return str.getBytes("UTF8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    /* renamed from: b */
    public static byte[] m16058b(String str) {
        try {
            return str.getBytes(HTTP.ASCII);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    /* renamed from: a */
    public static String m16060a(byte[] bArr) {
        return m16059a(bArr, 0, bArr.length);
    }

    /* renamed from: a */
    public static String m16059a(byte[] bArr, int i, int i2) {
        try {
            return new String(bArr, i, i2, HTTP.ASCII);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    /* renamed from: b */
    public static String m16057b(byte[] bArr) {
        return m16061a(ByteBuffer.wrap(bArr));
    }

    /* renamed from: a */
    public static String m16061a(ByteBuffer byteBuffer) {
        CharsetDecoder newDecoder = Charset.forName("UTF8").newDecoder();
        newDecoder.onMalformedInput(f13758a);
        newDecoder.onUnmappableCharacter(f13758a);
        try {
            byteBuffer.mark();
            String charBuffer = newDecoder.decode(byteBuffer).toString();
            byteBuffer.reset();
            return charBuffer;
        } catch (CharacterCodingException e) {
            throw new C2774cl(1007, e);
        }
    }

    public static void main(String[] strArr) {
        m16057b(m16062a("\u0000"));
        m16060a(m16058b("\u0000"));
    }
}
