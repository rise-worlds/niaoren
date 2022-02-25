package p110z1;

import java.io.IOException;
import java.io.InputStream;
import java.net.ProtocolException;
import java.util.List;
import java.util.Map;

/* renamed from: z1.agi */
/* loaded from: classes3.dex */
public interface FileDownloadConnection {

    /* renamed from: a */
    public static final int f15632a = 0;

    /* renamed from: b */
    public static final int f15633b = 1;

    /* renamed from: a */
    InputStream mo13499a() throws IOException;

    /* renamed from: a */
    String mo13498a(String str);

    /* renamed from: a */
    void mo13496a(String str, String str2);

    /* renamed from: a */
    boolean mo13497a(String str, long j);

    /* renamed from: b */
    Map<String, List<String>> mo13495b();

    /* renamed from: b */
    boolean mo13494b(String str) throws ProtocolException;

    /* renamed from: c */
    Map<String, List<String>> mo13493c();

    /* renamed from: d */
    void mo13492d() throws IOException;

    /* renamed from: e */
    int mo13491e() throws IOException;

    /* renamed from: f */
    void mo13490f();
}
