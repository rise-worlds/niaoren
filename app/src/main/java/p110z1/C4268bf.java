package p110z1;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.CookieManager;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.List;
import java.util.Map;
import org.apache.tools.ant.util.ProxySetup;

/* renamed from: z1.bf */
/* loaded from: classes3.dex */
public final class C4268bf {

    /* renamed from: a */
    private static final String f18553a = "msp";

    /* renamed from: b */
    private static final String f18554b = "application/octet-stream;binary/octet-stream";

    /* renamed from: c */
    private static final CookieManager f18555c = new CookieManager();

    /* renamed from: z1.bf$a */
    /* loaded from: classes3.dex */
    public static final class C4269a {

        /* renamed from: a */
        public final String f18556a;

        /* renamed from: b */
        public final byte[] f18557b;

        /* renamed from: c */
        public final Map<String, String> f18558c;

        public C4269a(String str, Map<String, String> map, byte[] bArr) {
            this.f18556a = str;
            this.f18557b = bArr;
            this.f18558c = map;
        }

        public String toString() {
            return String.format("<UrlConnectionConfigure url=%s headers=%s>", this.f18556a, this.f18558c);
        }
    }

    /* renamed from: z1.bf$b */
    /* loaded from: classes3.dex */
    public static final class C4270b {

        /* renamed from: a */
        public final Map<String, List<String>> f18559a;

        /* renamed from: b */
        public final String f18560b;

        /* renamed from: c */
        public final byte[] f18561c;

        public C4270b(Map<String, List<String>> map, String str, byte[] bArr) {
            this.f18559a = map;
            this.f18560b = str;
            this.f18561c = bArr;
        }
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Unreachable block: B:63:0x019a
        	at jadx.core.dex.visitors.blocks.BlockProcessor.checkForUnreachableBlocks(BlockProcessor.java:90)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:52)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:44)
        */
    /* renamed from: a */
    public static p110z1.C4268bf.C4270b m9708a(android.content.Context r12, p110z1.C4268bf.C4269a r13) {
        /*
            Method dump skipped, instructions count: 486
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p110z1.C4268bf.m9708a(android.content.Context, z1.bf$a):z1.bf$b");
    }

    /* renamed from: a */
    private static Proxy m9709a(Context context) {
        String c = m9705c(context);
        if (c != null && !c.contains("wap")) {
            return null;
        }
        try {
            String property = System.getProperty(ProxySetup.HTTPS_PROXY_HOST);
            String property2 = System.getProperty(ProxySetup.HTTPS_PROXY_PORT);
            if (!TextUtils.isEmpty(property)) {
                return new Proxy(Proxy.Type.HTTP, new InetSocketAddress(property, Integer.parseInt(property2)));
            }
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }

    /* renamed from: b */
    private static NetworkInfo m9706b(Context context) {
        if (context == null) {
            return null;
        }
        try {
            return ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        } catch (Exception unused) {
            return null;
        }
    }

    /* renamed from: c */
    private static String m9705c(Context context) {
        try {
            NetworkInfo b = m9706b(context);
            return (b == null || !b.isAvailable()) ? "none" : b.getType() == 1 ? "wifi" : b.getExtraInfo().toLowerCase();
        } catch (Exception unused) {
            return "none";
        }
    }

    /* renamed from: a */
    private static byte[] m9707a(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        while (true) {
            int read = inputStream.read(bArr, 0, bArr.length);
            if (read != -1) {
                byteArrayOutputStream.write(bArr, 0, read);
            } else {
                byteArrayOutputStream.flush();
                return byteArrayOutputStream.toByteArray();
            }
        }
    }
}
