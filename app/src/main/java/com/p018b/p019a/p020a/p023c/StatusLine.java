package com.p018b.p019a.p020a.p023c;

import com.p018b.p019a.Protocol;
import java.net.ProtocolException;

/* renamed from: com.b.a.a.c.l */
/* loaded from: classes.dex */
public final class StatusLine {

    /* renamed from: a */
    public final Protocol f5813a;

    /* renamed from: b */
    public final int f5814b;

    /* renamed from: c */
    public final String f5815c;

    private StatusLine(Protocol alVar, int i, String str) {
        this.f5813a = alVar;
        this.f5814b = i;
        this.f5815c = str;
    }

    /* renamed from: a */
    public static StatusLine m24724a(String str) {
        Protocol alVar;
        int i = 9;
        if (str.startsWith("HTTP/1.")) {
            if (str.length() < 9 || str.charAt(8) != ' ') {
                throw new ProtocolException("Unexpected status line: " + str);
            }
            int charAt = str.charAt(7) - '0';
            if (charAt == 0) {
                alVar = Protocol.HTTP_1_0;
            } else if (charAt == 1) {
                alVar = Protocol.HTTP_1_1;
            } else {
                throw new ProtocolException("Unexpected status line: " + str);
            }
        } else if (str.startsWith("ICY ")) {
            alVar = Protocol.HTTP_1_0;
            i = 4;
        } else {
            throw new ProtocolException("Unexpected status line: " + str);
        }
        int i2 = i + 3;
        if (str.length() >= i2) {
            try {
                int parseInt = Integer.parseInt(str.substring(i, i2));
                String str2 = "";
                if (str.length() > i2) {
                    if (str.charAt(i2) == ' ') {
                        str2 = str.substring(i + 4);
                    } else {
                        throw new ProtocolException("Unexpected status line: " + str);
                    }
                }
                return new StatusLine(alVar, parseInt, str2);
            } catch (NumberFormatException unused) {
                throw new ProtocolException("Unexpected status line: " + str);
            }
        } else {
            throw new ProtocolException("Unexpected status line: " + str);
        }
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.f5813a == Protocol.HTTP_1_0 ? "HTTP/1.0" : "HTTP/1.1");
        sb.append(' ');
        sb.append(this.f5814b);
        if (this.f5815c != null) {
            sb.append(' ');
            sb.append(this.f5815c);
        }
        return sb.toString();
    }
}
