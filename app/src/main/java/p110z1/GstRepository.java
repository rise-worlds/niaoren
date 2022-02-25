package p110z1;

import com.nrzs.data.p066ft.bean.request.GstInfo;
import com.nrzs.http.Api;
import com.nrzs.http.BaseRepository;
import com.nrzs.http.ThreadCallback;
import com.nrzs.http.UICallback;
import java.io.PrintStream;

/* renamed from: z1.akk */
/* loaded from: classes3.dex */
public class GstRepository {
    /* renamed from: a */
    public static void m12775a(long j) {
        try {
            System.out.println("requestGoldSetTodayNoChoose");
            new BaseRepository().m18568a(Api.m18586a(new GstInfo(j).toGetUrl(HttpVal.f16500P))).m18572a(new ThreadCallback() { // from class: z1.akk.1
                @Override // com.nrzs.http.ThreadCallback
                public Object onResponse(String str) {
                    PrintStream printStream = System.out;
                    printStream.println("requestGoldSetTodayNoChoose s:" + str);
                    return str;
                }
            }).m18571a((UICallback) null).m18574a(4);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
