package p110z1;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import p110z1.FileDownloadHelper;

/* renamed from: z1.agj */
/* loaded from: classes3.dex */
public class FileDownloadUrlConnection implements FileDownloadConnection {

    /* renamed from: c */
    protected URLConnection f15634c;

    @Override // p110z1.FileDownloadConnection
    /* renamed from: a */
    public boolean mo13497a(String str, long j) {
        return false;
    }

    public FileDownloadUrlConnection(String str, C3441a aVar) throws IOException {
        this(new URL(str), aVar);
    }

    public FileDownloadUrlConnection(URL url, C3441a aVar) throws IOException {
        if (aVar == null || aVar.f15635a == null) {
            this.f15634c = url.openConnection();
        } else {
            this.f15634c = url.openConnection(aVar.f15635a);
        }
        if (aVar != null) {
            if (aVar.f15636b != null) {
                this.f15634c.setReadTimeout(aVar.f15636b.intValue());
            }
            if (aVar.f15637c != null) {
                this.f15634c.setConnectTimeout(aVar.f15637c.intValue());
            }
        }
    }

    public FileDownloadUrlConnection(String str) throws IOException {
        this(str, (C3441a) null);
    }

    @Override // p110z1.FileDownloadConnection
    /* renamed from: a */
    public void mo13496a(String str, String str2) {
        this.f15634c.addRequestProperty(str, str2);
    }

    @Override // p110z1.FileDownloadConnection
    /* renamed from: a */
    public InputStream mo13499a() throws IOException {
        return this.f15634c.getInputStream();
    }

    @Override // p110z1.FileDownloadConnection
    /* renamed from: b */
    public Map<String, List<String>> mo13495b() {
        return this.f15634c.getRequestProperties();
    }

    @Override // p110z1.FileDownloadConnection
    /* renamed from: c */
    public Map<String, List<String>> mo13493c() {
        return this.f15634c.getHeaderFields();
    }

    @Override // p110z1.FileDownloadConnection
    /* renamed from: a */
    public String mo13498a(String str) {
        return this.f15634c.getHeaderField(str);
    }

    @Override // p110z1.FileDownloadConnection
    /* renamed from: b */
    public boolean mo13494b(String str) throws ProtocolException {
        URLConnection uRLConnection = this.f15634c;
        if (!(uRLConnection instanceof HttpURLConnection)) {
            return false;
        }
        ((HttpURLConnection) uRLConnection).setRequestMethod(str);
        return true;
    }

    @Override // p110z1.FileDownloadConnection
    /* renamed from: d */
    public void mo13492d() throws IOException {
        this.f15634c.connect();
    }

    @Override // p110z1.FileDownloadConnection
    /* renamed from: e */
    public int mo13491e() throws IOException {
        URLConnection uRLConnection = this.f15634c;
        if (uRLConnection instanceof HttpURLConnection) {
            return ((HttpURLConnection) uRLConnection).getResponseCode();
        }
        return 0;
    }

    @Override // p110z1.FileDownloadConnection
    /* renamed from: f */
    public void mo13490f() {
        try {
            this.f15634c.getInputStream().close();
        } catch (IOException unused) {
        }
    }

    /* compiled from: FileDownloadUrlConnection.java */
    /* renamed from: z1.agj$b */
    /* loaded from: classes3.dex */
    public static class C3442b implements FileDownloadHelper.AbstractC3478b {

        /* renamed from: a */
        private final C3441a f15638a;

        public C3442b() {
            this(null);
        }

        public C3442b(C3441a aVar) {
            this.f15638a = aVar;
        }

        /* renamed from: a */
        FileDownloadConnection m13483a(URL url) throws IOException {
            return new FileDownloadUrlConnection(url, this.f15638a);
        }

        @Override // p110z1.FileDownloadHelper.AbstractC3478b
        /* renamed from: a */
        public FileDownloadConnection mo13223a(String str) throws IOException {
            return new FileDownloadUrlConnection(str, this.f15638a);
        }
    }

    /* compiled from: FileDownloadUrlConnection.java */
    /* renamed from: z1.agj$a */
    /* loaded from: classes3.dex */
    public static class C3441a {

        /* renamed from: a */
        private Proxy f15635a;

        /* renamed from: b */
        private Integer f15636b;

        /* renamed from: c */
        private Integer f15637c;

        /* renamed from: a */
        public C3441a m13488a(Proxy proxy) {
            this.f15635a = proxy;
            return this;
        }

        /* renamed from: a */
        public C3441a m13489a(int i) {
            this.f15636b = Integer.valueOf(i);
            return this;
        }

        /* renamed from: b */
        public C3441a m13486b(int i) {
            this.f15637c = Integer.valueOf(i);
            return this;
        }
    }
}
