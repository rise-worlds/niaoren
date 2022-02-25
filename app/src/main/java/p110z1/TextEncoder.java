package p110z1;

import com.tencent.smtt.sdk.TbsListener;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: z1.jj */
/* loaded from: classes3.dex */
public final class TextEncoder extends C40Encoder {
    @Override // p110z1.C40Encoder, p110z1.AbstractC5379jd
    /* renamed from: a */
    public final int mo2275a() {
        return 2;
    }

    @Override // p110z1.C40Encoder
    /* renamed from: a */
    final int mo2274a(char c, StringBuilder sb) {
        if (c == ' ') {
            sb.append((char) 3);
            return 1;
        } else if (c >= '0' && c <= '9') {
            sb.append((char) ((c - '0') + 4));
            return 1;
        } else if (c >= 'a' && c <= 'z') {
            sb.append((char) ((c - 'a') + 14));
            return 1;
        } else if (c < ' ') {
            sb.append((char) 0);
            sb.append(c);
            return 2;
        } else if (c >= '!' && c <= '/') {
            sb.append((char) 1);
            sb.append((char) (c - '!'));
            return 2;
        } else if (c >= ':' && c <= '@') {
            sb.append((char) 1);
            sb.append((char) ((c - ':') + 15));
            return 2;
        } else if (c >= '[' && c <= '_') {
            sb.append((char) 1);
            sb.append((char) ((c - '[') + 22));
            return 2;
        } else if (c == '`') {
            sb.append((char) 2);
            sb.append((char) (c - '`'));
            return 2;
        } else if (c >= 'A' && c <= 'Z') {
            sb.append((char) 2);
            sb.append((char) ((c - 'A') + 1));
            return 2;
        } else if (c < '{' || c > 127) {
            sb.append("\u0001\u001e");
            return mo2274a((char) (c - 128), sb) + 2;
        } else {
            sb.append((char) 2);
            sb.append((char) ((c - TbsListener.ErrorCode.DOWNLOAD_RETRYTIMES302_EXCEED) + 27));
            return 2;
        }
    }
}
