package p110z1;

import android.text.TextUtils;
import com.liulishuo.filedownloader.model.FileDownloadHeader;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import p110z1.ConnectionProfile;

/* renamed from: z1.agq */
/* loaded from: classes3.dex */
public class ConnectTask {

    /* renamed from: a */
    final int f15669a;

    /* renamed from: b */
    final String f15670b;

    /* renamed from: c */
    final FileDownloadHeader f15671c;

    /* renamed from: d */
    private ConnectionProfile f15672d;

    /* renamed from: e */
    private String f15673e;

    /* renamed from: f */
    private Map<String, List<String>> f15674f;

    /* renamed from: g */
    private List<String> f15675g;

    private ConnectTask(ConnectionProfile agrVar, int i, String str, String str2, FileDownloadHeader fileDownloadHeader) {
        this.f15669a = i;
        this.f15670b = str;
        this.f15673e = str2;
        this.f15671c = fileDownloadHeader;
        this.f15672d = agrVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m13436a(long j) {
        if (j == this.f15672d.f15683c) {
            FileDownloadLog.m13210d(this, "no data download, no need to update", new Object[0]);
            return;
        }
        this.f15672d = ConnectionProfile.C3456a.m13417a(this.f15672d.f15682b, j, this.f15672d.f15684d, this.f15672d.f15685e - (j - this.f15672d.f15683c));
        if (FileDownloadLog.f15845a) {
            FileDownloadLog.m13212b(this, "after update profile:%s", this.f15672d);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public FileDownloadConnection m13437a() throws IOException, IllegalAccessException {
        FileDownloadConnection a = CustomComponentHolder.m13415a().m13411a(this.f15670b);
        m13435a(a);
        m13432b(a);
        m13430c(a);
        this.f15674f = a.mo13495b();
        if (FileDownloadLog.f15845a) {
            FileDownloadLog.m13211c(this, "<---- %s request header %s", Integer.valueOf(this.f15669a), this.f15674f);
        }
        a.mo13492d();
        this.f15675g = new ArrayList();
        FileDownloadConnection a2 = RedirectHandler.m13480a(this.f15674f, a, this.f15675g);
        if (FileDownloadLog.f15845a) {
            FileDownloadLog.m13211c(this, "----> %s response header %s", Integer.valueOf(this.f15669a), a2.mo13493c());
        }
        return a2;
    }

    /* renamed from: a */
    private void m13435a(FileDownloadConnection agiVar) {
        HashMap<String, List<String>> a;
        FileDownloadHeader fileDownloadHeader = this.f15671c;
        if (!(fileDownloadHeader == null || (a = fileDownloadHeader.m19141a()) == null)) {
            if (FileDownloadLog.f15845a) {
                FileDownloadLog.m13209e(this, "%d add outside header: %s", Integer.valueOf(this.f15669a), a);
            }
            for (Map.Entry<String, List<String>> entry : a.entrySet()) {
                String key = entry.getKey();
                List<String> value = entry.getValue();
                if (value != null) {
                    for (String str : value) {
                        agiVar.mo13496a(key, str);
                    }
                }
            }
        }
    }

    /* renamed from: b */
    private void m13432b(FileDownloadConnection agiVar) throws ProtocolException {
        if (!agiVar.mo13497a(this.f15673e, this.f15672d.f15682b)) {
            if (!TextUtils.isEmpty(this.f15673e)) {
                agiVar.mo13496a("If-Match", this.f15673e);
            }
            this.f15672d.m13421a(agiVar);
        }
    }

    /* renamed from: c */
    private void m13430c(FileDownloadConnection agiVar) {
        FileDownloadHeader fileDownloadHeader = this.f15671c;
        if (fileDownloadHeader == null || fileDownloadHeader.m19141a().get("User-Agent") == null) {
            agiVar.mo13496a("User-Agent", FileDownloadUtils.m13159f());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public boolean m13433b() {
        return this.f15672d.f15683c > 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c */
    public String m13431c() {
        List<String> list = this.f15675g;
        if (list == null || list.isEmpty()) {
            return null;
        }
        List<String> list2 = this.f15675g;
        return list2.get(list2.size() - 1);
    }

    /* renamed from: d */
    public Map<String, List<String>> m13429d() {
        return this.f15674f;
    }

    /* renamed from: e */
    public ConnectionProfile m13428e() {
        return this.f15672d;
    }

    /* renamed from: a */
    public void m13434a(ConnectionProfile agrVar, String str) throws C3454b {
        if (agrVar == null) {
            throw new IllegalArgumentException();
        }
        this.f15672d = agrVar;
        this.f15673e = str;
        throw new C3454b();
    }

    /* compiled from: ConnectTask.java */
    /* renamed from: z1.agq$b */
    /* loaded from: classes3.dex */
    class C3454b extends Throwable {
        C3454b() {
        }
    }

    /* compiled from: ConnectTask.java */
    /* renamed from: z1.agq$a */
    /* loaded from: classes3.dex */
    static class C3453a {

        /* renamed from: a */
        private Integer f15676a;

        /* renamed from: b */
        private String f15677b;

        /* renamed from: c */
        private String f15678c;

        /* renamed from: d */
        private FileDownloadHeader f15679d;

        /* renamed from: e */
        private ConnectionProfile f15680e;

        /* renamed from: a */
        public C3453a m13426a(int i) {
            this.f15676a = Integer.valueOf(i);
            return this;
        }

        /* renamed from: a */
        public C3453a m13424a(String str) {
            this.f15677b = str;
            return this;
        }

        /* renamed from: b */
        public C3453a m13422b(String str) {
            this.f15678c = str;
            return this;
        }

        /* renamed from: a */
        public C3453a m13425a(FileDownloadHeader fileDownloadHeader) {
            this.f15679d = fileDownloadHeader;
            return this;
        }

        /* renamed from: a */
        public C3453a m13423a(ConnectionProfile agrVar) {
            this.f15680e = agrVar;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: a */
        public ConnectTask m13427a() {
            ConnectionProfile agrVar;
            Integer num = this.f15676a;
            if (num != null && (agrVar = this.f15680e) != null && this.f15677b != null) {
                return new ConnectTask(agrVar, num.intValue(), this.f15677b, this.f15678c, this.f15679d);
            }
            throw new IllegalArgumentException();
        }
    }
}
