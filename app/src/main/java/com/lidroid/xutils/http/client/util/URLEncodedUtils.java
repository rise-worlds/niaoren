package com.lidroid.xutils.http.client.util;

import android.text.TextUtils;
import com.tencent.smtt.sdk.TbsListener;
import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicHeaderValueParser;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.message.ParserCursor;
import org.apache.http.util.CharArrayBuffer;
import p110z1.Typography;

/* loaded from: classes.dex */
public class URLEncodedUtils {
    public static final String CONTENT_TYPE = "application/x-www-form-urlencoded";
    private static final String NAME_VALUE_SEPARATOR = "=";
    private static final String PARAMETER_SEPARATOR = "&";
    private static final int RADIX = 16;
    private static final char[] DELIM = {Typography.f21051c};
    private static final BitSet UNRESERVED = new BitSet(256);
    private static final BitSet PUNCT = new BitSet(256);
    private static final BitSet USERINFO = new BitSet(256);
    private static final BitSet PATHSAFE = new BitSet(256);
    private static final BitSet FRAGMENT = new BitSet(256);
    private static final BitSet RESERVED = new BitSet(256);
    private static final BitSet URLENCODER = new BitSet(256);

    public static boolean isEncoded(HttpEntity httpEntity) {
        Header contentType = httpEntity.getContentType();
        if (contentType == null) {
            return false;
        }
        HeaderElement[] elements = contentType.getElements();
        if (elements.length > 0) {
            return elements[0].getName().equalsIgnoreCase("application/x-www-form-urlencoded");
        }
        return false;
    }

    public static List<NameValuePair> parse(URI uri) {
        String rawQuery = uri.getRawQuery();
        if (TextUtils.isEmpty(rawQuery)) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        parse(arrayList, new Scanner(rawQuery));
        return arrayList;
    }

    public static void parse(List<NameValuePair> list, Scanner scanner) {
        String str;
        scanner.useDelimiter("&");
        while (scanner.hasNext()) {
            String str2 = null;
            String next = scanner.next();
            int indexOf = next.indexOf("=");
            if (indexOf != -1) {
                str = next.substring(0, indexOf).trim();
                str2 = next.substring(indexOf + 1).trim();
            } else {
                str = next.trim();
            }
            list.add(new BasicNameValuePair(str, str2));
        }
    }

    static {
        for (int i = 97; i <= 122; i++) {
            UNRESERVED.set(i);
        }
        for (int i2 = 65; i2 <= 90; i2++) {
            UNRESERVED.set(i2);
        }
        for (int i3 = 48; i3 <= 57; i3++) {
            UNRESERVED.set(i3);
        }
        UNRESERVED.set(95);
        UNRESERVED.set(45);
        UNRESERVED.set(46);
        UNRESERVED.set(42);
        URLENCODER.or(UNRESERVED);
        UNRESERVED.set(33);
        UNRESERVED.set(TbsListener.ErrorCode.PV_UPLOAD_ERROR);
        UNRESERVED.set(39);
        UNRESERVED.set(40);
        UNRESERVED.set(41);
        PUNCT.set(44);
        PUNCT.set(59);
        PUNCT.set(58);
        PUNCT.set(36);
        PUNCT.set(38);
        PUNCT.set(43);
        PUNCT.set(61);
        USERINFO.or(UNRESERVED);
        USERINFO.or(PUNCT);
        PATHSAFE.or(UNRESERVED);
        PATHSAFE.set(47);
        PATHSAFE.set(59);
        PATHSAFE.set(58);
        PATHSAFE.set(64);
        PATHSAFE.set(38);
        PATHSAFE.set(61);
        PATHSAFE.set(43);
        PATHSAFE.set(36);
        PATHSAFE.set(44);
        RESERVED.set(59);
        RESERVED.set(47);
        RESERVED.set(63);
        RESERVED.set(58);
        RESERVED.set(64);
        RESERVED.set(38);
        RESERVED.set(61);
        RESERVED.set(43);
        RESERVED.set(36);
        RESERVED.set(44);
        RESERVED.set(91);
        RESERVED.set(93);
        FRAGMENT.or(RESERVED);
        FRAGMENT.or(UNRESERVED);
    }

    public static List<NameValuePair> parse(String str) {
        if (str == null) {
            return Collections.emptyList();
        }
        BasicHeaderValueParser basicHeaderValueParser = BasicHeaderValueParser.DEFAULT;
        CharArrayBuffer charArrayBuffer = new CharArrayBuffer(str.length());
        charArrayBuffer.append(str);
        ParserCursor parserCursor = new ParserCursor(0, charArrayBuffer.length());
        ArrayList arrayList = new ArrayList();
        while (!parserCursor.atEnd()) {
            NameValuePair parseNameValuePair = basicHeaderValueParser.parseNameValuePair(charArrayBuffer, parserCursor, DELIM);
            if (parseNameValuePair.getName().length() > 0) {
                arrayList.add(new BasicNameValuePair(parseNameValuePair.getName(), parseNameValuePair.getValue()));
            }
        }
        return arrayList;
    }

    public static String format(List<? extends NameValuePair> list, String str) {
        StringBuilder sb = new StringBuilder();
        for (NameValuePair nameValuePair : list) {
            String encodeFormFields = encodeFormFields(nameValuePair.getName(), str);
            String encodeFormFields2 = encodeFormFields(nameValuePair.getValue(), str);
            if (sb.length() > 0) {
                sb.append("&");
            }
            sb.append(encodeFormFields);
            if (encodeFormFields2 != null) {
                sb.append("=");
                sb.append(encodeFormFields2);
            }
        }
        return sb.toString();
    }

    public static String format(Iterable<? extends NameValuePair> iterable, Charset charset) {
        StringBuilder sb = new StringBuilder();
        for (NameValuePair nameValuePair : iterable) {
            String encodeFormFields = encodeFormFields(nameValuePair.getName(), charset);
            String encodeFormFields2 = encodeFormFields(nameValuePair.getValue(), charset);
            if (sb.length() > 0) {
                sb.append("&");
            }
            sb.append(encodeFormFields);
            if (encodeFormFields2 != null) {
                sb.append("=");
                sb.append(encodeFormFields2);
            }
        }
        return sb.toString();
    }

    private static String urlencode(String str, Charset charset, BitSet bitSet, boolean z) {
        if (str == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        ByteBuffer encode = charset.encode(str);
        while (encode.hasRemaining()) {
            int i = encode.get() & 255;
            if (bitSet.get(i)) {
                sb.append((char) i);
            } else if (!z || i != 32) {
                sb.append("%");
                char upperCase = Character.toUpperCase(Character.forDigit((i >> 4) & 15, 16));
                char upperCase2 = Character.toUpperCase(Character.forDigit(i & 15, 16));
                sb.append(upperCase);
                sb.append(upperCase2);
            } else {
                sb.append('+');
            }
        }
        return sb.toString();
    }

    private static String urldecode(String str, Charset charset, boolean z) {
        if (str == null) {
            return null;
        }
        ByteBuffer allocate = ByteBuffer.allocate(str.length());
        CharBuffer wrap = CharBuffer.wrap(str);
        while (wrap.hasRemaining()) {
            char c = wrap.get();
            if (c == '%' && wrap.remaining() >= 2) {
                char c2 = wrap.get();
                char c3 = wrap.get();
                int digit = Character.digit(c2, 16);
                int digit2 = Character.digit(c3, 16);
                if (digit == -1 || digit2 == -1) {
                    allocate.put((byte) 37);
                    allocate.put((byte) c2);
                    allocate.put((byte) c3);
                } else {
                    allocate.put((byte) ((digit << 4) + digit2));
                }
            } else if (!z || c != '+') {
                allocate.put((byte) c);
            } else {
                allocate.put((byte) 32);
            }
        }
        allocate.flip();
        return charset.decode(allocate).toString();
    }

    private static String decodeFormFields(String str, String str2) {
        if (str == null) {
            return null;
        }
        if (str2 == null) {
            str2 = "UTF-8";
        }
        return urldecode(str, Charset.forName(str2), true);
    }

    private static String decodeFormFields(String str, Charset charset) {
        if (str == null) {
            return null;
        }
        if (charset == null) {
            charset = Charset.forName("UTF-8");
        }
        return urldecode(str, charset, true);
    }

    private static String encodeFormFields(String str, String str2) {
        Charset charset;
        if (str == null) {
            return null;
        }
        if (str2 != null) {
            charset = Charset.forName(str2);
        } else {
            charset = Charset.forName("UTF-8");
        }
        return urlencode(str, charset, URLENCODER, true);
    }

    private static String encodeFormFields(String str, Charset charset) {
        if (str == null) {
            return null;
        }
        if (charset == null) {
            charset = Charset.forName("UTF-8");
        }
        return urlencode(str, charset, URLENCODER, true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String encUserInfo(String str, Charset charset) {
        return urlencode(str, charset, USERINFO, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String encFragment(String str, Charset charset) {
        return urlencode(str, charset, FRAGMENT, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String encPath(String str, Charset charset) {
        return urlencode(str, charset, PATHSAFE, false);
    }
}
