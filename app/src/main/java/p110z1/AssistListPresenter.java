package p110z1;

import com.nrzs.data.DataApp;
import com.nrzs.data.p066ft.bean.AssistInfo;
import com.nrzs.data.p066ft.bean.request.AssistRequestInfo;
import com.nrzs.http.UICallback;
import java.io.PrintStream;
import java.util.List;
import p110z1.AssistListContract;

/* renamed from: z1.anq */
/* loaded from: classes3.dex */
public class AssistListPresenter implements AssistListContract.AbstractC3681a {

    /* renamed from: a */
    private AssistRepository f16821a;

    /* renamed from: b */
    private AssistListContract.AbstractC3682b f16822b;

    /* renamed from: c */
    private UICallback<List<AssistInfo>> f16823c = new UICallback<List<AssistInfo>>() { // from class: z1.anq.1
        @Override // com.nrzs.http.UICallback
        /* renamed from: a */
        public void mo3021a(Throwable th) {
            AssistListPresenter.this.f16822b.mo12492f();
        }

        /* renamed from: a  reason: avoid collision after fix types in other method */
        public void mo3022a(List<AssistInfo> list) {
            PrintStream printStream = System.out;
            printStream.println("DataApp appChannelType:" + DataApp.m18939d().f10604g);
            PrintStream printStream2 = System.out;
            printStream2.println("DataApp channelId:" + DataApp.m18939d().f10605h);
            AssistListPresenter.this.f16822b.getAdapter().m18487a(list);
            AssistListPresenter.this.f16822b.mo12493e();
        }
    };

    @Override // p110z1.IBasePresenter
    /* renamed from: b */
    public void mo12241b() {
    }

    public AssistListPresenter(AssistListContract.AbstractC3682b bVar) {
        this.f16822b = bVar;
        this.f16822b.setPresenter(this);
    }

    @Override // p110z1.IBasePresenter
    /* renamed from: a */
    public void mo12243a() {
        this.f16821a = new AssistRepository();
    }

    @Override // p110z1.AssistListContract.AbstractC3681a
    /* renamed from: c */
    public void mo12240c() {
        AssistRequestInfo assistRequestInfo = new AssistRequestInfo();
        assistRequestInfo.TopicId = FloatDataManager.m12352j().m12354h();
        assistRequestInfo.CurrentPage = 0;
        assistRequestInfo.PageSize = 999;
        assistRequestInfo.UserId = FtUserManager.m12790g().m12797a();
        assistRequestInfo.isVa = FloatDataManager.m12352j().f16748d;
        assistRequestInfo.AuthorId = DataApp.m18939d().f10605h;
        this.f16821a.m12787a(assistRequestInfo, this.f16823c);
    }
}
