package p110z1;

import com.nrzs.data.DataApp;
import com.nrzs.data.base.BaseResponse;
import com.nrzs.data.game.bean.AuthorPaySetBean;
import com.nrzs.http.UICallback;
import java.io.PrintStream;

/* renamed from: z1.ala */
/* loaded from: classes3.dex */
public class ChannelDataManager {

    /* renamed from: c */
    private static volatile ChannelDataManager f16305c;

    /* renamed from: a */
    private boolean f16306a = false;

    /* renamed from: b */
    private String f16307b;

    /* renamed from: a */
    public static ChannelDataManager m12655a() {
        if (f16305c == null) {
            synchronized (ChannelDataManager.class) {
                if (f16305c == null) {
                    f16305c = new ChannelDataManager();
                }
            }
        }
        return f16305c;
    }

    /* renamed from: b */
    public void m12652b() {
        System.out.println("loadChannelPayParams");
        long j = DataApp.m18939d().f10605h;
        if (j != 0 && DataApp.m18939d().f10604g == 2) {
            new AuthorRepository().m12718a((int) j, new UICallback<BaseResponse<AuthorPaySetBean>>() { // from class: z1.ala.1
                @Override // com.nrzs.http.UICallback
                /* renamed from: a */
                public void mo3021a(Throwable th) {
                }

                /* renamed from: a  reason: avoid collision after fix types in other method */
                public void mo3022a(BaseResponse<AuthorPaySetBean> baseResponse) {
                    if (baseResponse.data != null) {
                        ChannelDataManager.this.f16306a = baseResponse.data.Enabled;
                        ChannelDataManager.this.f16307b = baseResponse.data.Url;
                        PrintStream printStream = System.out;
                        printStream.println("response:" + baseResponse.data.toString());
                    }
                }
            });
        }
    }

    /* renamed from: c */
    public String m12651c() {
        return this.f16307b;
    }

    /* renamed from: d */
    public boolean m12650d() {
        return this.f16306a;
    }
}
