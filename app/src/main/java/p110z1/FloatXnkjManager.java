package p110z1;

import com.blankj.utilcode.util.LogUtils;
import com.nrzs.base.router.provider.ProviderFactory;
import com.nrzs.base.router.provider.XNKJRunProvider;
import com.nrzs.http.UICallback;
import p110z1.FtXnkjSocketTask;
import p110z1.XNKJEvent;

/* renamed from: z1.aqj */
/* loaded from: classes3.dex */
public enum FloatXnkjManager {
    INSTANCE;
    
    private FtXnkjSocketTask.AbstractC3865a callback = new FtXnkjSocketTask.AbstractC3865a() { // from class: z1.aqj.1
        @Override // p110z1.FtXnkjSocketTask.AbstractC3865a
        /* renamed from: a */
        public void mo11564a() {
        }

        @Override // p110z1.FtXnkjSocketTask.AbstractC3865a
        /* renamed from: b */
        public void mo11563b() {
            FloatXnkjManager.this.destory();
        }
    };
    public FtXnkjSocketTask ftXnkjSocketTask;

    /* compiled from: FloatXnkjManager.java */
    /* renamed from: z1.aqj$a */
    /* loaded from: classes3.dex */
    public interface AbstractC3864a {
        /* renamed from: a */
        void mo11570a();

        /* renamed from: a */
        void mo11569a(int i, String str);
    }

    FloatXnkjManager() {
    }

    public void createSocket(String str) {
        LogUtils.m23734c("newEngin", "localServerName:" + str);
        if (this.ftXnkjSocketTask == null) {
            this.ftXnkjSocketTask = new FtXnkjSocketTask(str, this.callback);
            aph.m11745c(this.ftXnkjSocketTask);
        }
    }

    public void enginRunPerm() {
        FtXnkjSocketTask aqkVar = this.ftXnkjSocketTask;
        if (aqkVar != null && !aqkVar.m11568a(16)) {
            EventBus.m3448a().m3427d(new XNKJEvent.C3576f(18));
        }
    }

    public void userEnginInit() {
        FtXnkjSocketTask aqkVar = this.ftXnkjSocketTask;
        if (aqkVar != null) {
            aqkVar.m11568a(7);
        }
    }

    public void runEngin(AbstractC3864a aVar) {
        FtXnkjSocketTask aqkVar = this.ftXnkjSocketTask;
        if (aqkVar == null) {
            return;
        }
        if (!aqkVar.m11568a(1)) {
            aVar.mo11569a(1, "连接出现异常");
            EventBus.m3448a().m3427d(new XNKJEvent.C3576f(18));
            return;
        }
        aVar.mo11570a();
    }

    public void stopEngin(AbstractC3864a aVar) {
        FtXnkjSocketTask aqkVar = this.ftXnkjSocketTask;
        if (aqkVar == null) {
            return;
        }
        if (!aqkVar.m11568a(2)) {
            aVar.mo11569a(1, "连接出现异常");
        } else {
            aVar.mo11570a();
        }
    }

    public void destory() {
        if (this.ftXnkjSocketTask != null) {
            this.ftXnkjSocketTask = null;
        }
    }

    public void enginRunKick() {
        EventBus.m3448a().m3427d(new XNKJEvent.C3576f(4));
    }

    public void handlerInfo(int i) {
        LogUtils.m23734c("newEngin", "infoInt:" + i);
        switch (i) {
            case 1:
                runHeart();
                return;
            case 2:
                stopHeart();
                EventBus.m3448a().m3427d(new XNKJEvent.C3577g());
                return;
            default:
                EventBus.m3448a().m3427d(new XNKJEvent.C3576f(i));
                return;
        }
    }

    private void runHeart() {
        AssistRunTJRepository.m12779c().m12784a(FloatDataManager.m12352j().m12357e(), 3);
        AssistRunTJRepository.m12779c().m12783a(new UICallback<Long>() { // from class: z1.aqj.2
            @Override // com.nrzs.http.UICallback
            /* renamed from: a */
            public void mo3021a(Throwable th) {
            }

            /* renamed from: a  reason: avoid collision after fix types in other method */
            public void mo3022a(Long l) {
                XNKJRunProvider createXNKJRun = ProviderFactory.createXNKJRun();
                if (createXNKJRun != null) {
                    createXNKJRun.writeHeartSID(String.valueOf(l));
                }
                AssistRunTJRepository.m12779c().m12783a((UICallback) null);
            }
        });
    }

    private void stopHeart() {
        AssistRunTJRepository.m12779c().m12785a();
    }

    public void handerHeart() {
        FtXnkjSocketTask aqkVar = this.ftXnkjSocketTask;
        if (aqkVar != null) {
            aqkVar.m11568a(21);
        }
    }
}
