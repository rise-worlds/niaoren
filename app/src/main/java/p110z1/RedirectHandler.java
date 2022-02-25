package p110z1;

import com.github.kevinsawicki.http.HttpRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* renamed from: z1.agk */
/* loaded from: classes3.dex */
public class RedirectHandler {

    /* renamed from: a */
    private static final int f15639a = 10;

    /* renamed from: b */
    private static final int f15640b = 307;

    /* renamed from: c */
    private static final int f15641c = 308;

    /* renamed from: a */
    private static boolean m13482a(int i) {
        return i == 301 || i == 302 || i == 303 || i == 300 || i == 307 || i == 308;
    }

    /* renamed from: a */
    public static FileDownloadConnection m13480a(Map<String, List<String>> map, FileDownloadConnection agiVar, List<String> list) throws IOException, IllegalAccessException {
        int e = agiVar.mo13491e();
        String a = agiVar.mo13498a(HttpRequest.HEADER_LOCATION);
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (m13482a(e)) {
            if (a != null) {
                if (FileDownloadLog.f15845a) {
                    FileDownloadLog.m13211c(RedirectHandler.class, "redirect to %s with %d, %s", a, Integer.valueOf(e), arrayList);
                }
                agiVar.mo13490f();
                agiVar = m13481a(map, a);
                arrayList.add(a);
                agiVar.mo13492d();
                e = agiVar.mo13491e();
                a = agiVar.mo13498a(HttpRequest.HEADER_LOCATION);
                i++;
                if (i >= 10) {
                    throw new IllegalAccessException(FileDownloadUtils.m13182a("redirect too many times! %s", arrayList));
                }
            } else {
                throw new IllegalAccessException(FileDownloadUtils.m13182a("receive %d (redirect) but the location is null with response [%s]", Integer.valueOf(e), agiVar.mo13493c()));
            }
        }
        if (list != null) {
            list.addAll(arrayList);
        }
        return agiVar;
    }

    /* renamed from: a */
    private static FileDownloadConnection m13481a(Map<String, List<String>> map, String str) throws IOException {
        FileDownloadConnection a = CustomComponentHolder.m13415a().m13411a(str);
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            String key = entry.getKey();
            List<String> value = entry.getValue();
            if (value != null) {
                for (String str2 : value) {
                    a.mo13496a(key, str2);
                }
            }
        }
        return a;
    }
}
