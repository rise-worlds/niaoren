package p110z1;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.nrzs.data.game.bean.TopicInfo;
import com.nrzs.game.C2029R;
import java.util.concurrent.Callable;

/* renamed from: z1.aop */
/* loaded from: classes3.dex */
public class GameDialogVARunScriptCheck extends Dialog {

    /* renamed from: a */
    private AbstractC3792a f17010a;

    /* renamed from: b */
    private long f17011b;

    /* compiled from: GameDialogVARunScriptCheck.java */
    /* renamed from: z1.aop$a */
    /* loaded from: classes3.dex */
    public interface AbstractC3792a {
        /* renamed from: a */
        void mo12008a();

        /* renamed from: a */
        void mo12007a(TopicInfo topicInfo);
    }

    public GameDialogVARunScriptCheck(Context context, AbstractC3792a aVar, long j) {
        super(context);
        this.f17010a = aVar;
        this.f17011b = j;
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        setCancelable(false);
        m12015a();
    }

    /* renamed from: a */
    private void m12015a() {
        setContentView(C2029R.layout.nrzs_game_dialog_va_loading);
        m12013b();
    }

    /* renamed from: b */
    private void m12013b() {
        VUiKit.m11713a().mo3332a(new Callable<TopicInfo>() { // from class: z1.aop.3
            /* renamed from: a */
            public TopicInfo call() throws Exception {
                TopicInfo a = TopicInfoManager.m12726c().m12736a(GameDialogVARunScriptCheck.this.f17011b);
                if (a != null && !TextUtils.isEmpty(a.localAppPackage)) {
                    return a;
                }
                throw new IllegalAccessException("无法找到类");
            }
        }).mo3285a(new FailCallback<Throwable>() { // from class: z1.aop.2
            /* renamed from: a */
            public void onFail(Throwable th) {
                GameDialogVARunScriptCheck.this.f17010a.mo12008a();
                VAManager.m12070i().m12073f();
            }
        }).mo3282b(new DoneCallback<TopicInfo>() { // from class: z1.aop.1
            /* renamed from: a */
            public void onDone(TopicInfo topicInfo) {
                GameDialogVARunScriptCheck.this.f17010a.mo12007a(topicInfo);
                VAManager.m12070i().m12073f();
            }
        });
    }
}
