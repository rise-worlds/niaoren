package p110z1;

import android.text.TextUtils;
import android.view.ContextThemeWrapper;
import android.widget.LinearLayout;
import com.blankj.utilcode.util.LogUtils;
import com.cyjh.mobileanjian.ipc.utils.Util;
import com.lbd.p054xj.app.XJApp;
import com.nrzs.data.p066ft.bean.AInfo;
import com.nrzs.data.p066ft.bean.AssistInfo;
import com.nrzs.data.p066ft.bean.EnginInteraRequestInfo;
import com.nrzs.data.user.bean.UserInfo;
import com.nrzs.ft.C1990R;
import com.nrzs.ft.FloatApp;
import java.io.File;
import org.apache.tools.ant.taskdefs.optional.vss.MSVSSConstants;
import p110z1.FloatXnkjManager;
import p110z1.FtEvent;
import p110z1.UserInfoEvent;
import p110z1.XNKJEvent;
import p110z1.aph;

/* renamed from: z1.adv */
/* loaded from: classes3.dex */
public class XJAssistInfoPresenter {

    /* renamed from: b */
    private AssistInfo f15389b;

    /* renamed from: c */
    private long f15390c;

    /* renamed from: d */
    private long f15391d;

    /* renamed from: e */
    private String f15392e;

    /* renamed from: f */
    private IAssistInfoView f15393f;

    /* renamed from: g */
    private AInfo f15394g;

    /* renamed from: h */
    private LinearLayout f15395h;

    /* renamed from: i */
    private boolean f15396i = false;

    /* renamed from: j */
    private boolean f15397j = false;

    /* renamed from: k */
    private boolean f15398k = false;

    /* renamed from: a */
    private AssistInfoModel f15388a = new AssistInfoModel();

    public XJAssistInfoPresenter(AssistInfo assistInfo, IAssistInfoView aocVar) {
        this.f15390c = assistInfo.TopicId;
        this.f15391d = assistInfo.ScriptID;
        this.f15392e = assistInfo.OnlyID;
        this.f15389b = assistInfo;
        this.f15393f = aocVar;
    }

    /* renamed from: a */
    public void m14257a() {
        aph.m11745c(new aph.AbstractRunnableC3836c<Object>() { // from class: z1.adv.1
            @Override // p110z1.aph.AbstractRunnableC3836c
            /* renamed from: a */
            public void mo11548a(Throwable th) {
            }

            @Override // p110z1.aph.AbstractRunnableC3836c
            /* renamed from: b */
            public void mo11547b() {
            }

            @Override // p110z1.aph.AbstractRunnableC3836c
            /* renamed from: a */
            public Object mo11551a() throws Throwable {
                EnginInteraRequestInfo enginInteraRequestInfo = new EnginInteraRequestInfo();
                enginInteraRequestInfo.AppSign = Util.getAppSinature(FloatApp.m18899b().m18901a());
                enginInteraRequestInfo.Command = 1;
                enginInteraRequestInfo.SessionId = LoginManager.m12620d().m12616h();
                enginInteraRequestInfo.UserId = LoginManager.m12620d().m12614j();
                enginInteraRequestInfo.DesKey = LoginManager.m12620d().m12612l();
                enginInteraRequestInfo.ScriptCacheRPath = ShareVal.f16591a + File.separatorChar + MSVSSConstants.SS_EXE;
                enginInteraRequestInfo.isVip = LoginManager.m12620d().m12613k();
                EnginInteraRequestInfo.EnginInteraParams enginInteraParams = new EnginInteraRequestInfo.EnginInteraParams();
                enginInteraParams.TopicId = XJAssistInfoPresenter.this.f15390c;
                enginInteraParams.ToolId = XJAssistInfoPresenter.this.f15391d;
                enginInteraParams.OnlyId = XJAssistInfoPresenter.this.f15392e;
                enginInteraRequestInfo.Param = enginInteraParams;
                FileWriteUtils.m14066g(apa.m11879a(enginInteraRequestInfo));
                return null;
            }

            @Override // p110z1.aph.AbstractRunnableC3836c
            /* renamed from: a */
            public void mo11549a(Object obj) {
                FtXnkjSdk.INSTANCE.executeXNKJRunPerm();
            }
        });
    }

    @Subscribe(m3389a = ThreadMode.MAIN)
    /* renamed from: a */
    public void m14250a(XNKJEvent.C3576f fVar) {
        LogUtils.m23734c("newEngin", "onEventMainThread - EventRunPermission" + fVar.f16172a);
        switch (fVar.f16172a) {
            case 17:
                File file = new File(m14242f(), "/storage/sdcard/nrzs/ss/script");
                if (file.exists()) {
                    m14255a(file.getAbsolutePath());
                }
                AInfo aInfo = this.f15394g;
                if (aInfo != null && aInfo.ScriptInfo != null) {
                    this.f15393f.mo12129a(this.f15394g);
                    return;
                }
                return;
            case 18:
                this.f15393f.mo12128e();
                return;
            case 19:
            case 20:
            case 21:
            case 22:
            default:
                return;
            case 23:
                System.out.println("ENGIN_PERMISSION_RESULT");
                String h = FileWriteUtils.m14065h("run_perm_result");
                if (!TextUtils.isEmpty(h)) {
                    try {
                        this.f15394g = (AInfo) apa.m11878a(h, AInfo.class);
                        return;
                    } catch (Exception e) {
                        e.printStackTrace();
                        return;
                    }
                } else {
                    return;
                }
            case 24:
                System.out.println("ENGIN_PERMISSION_NEED_GOLD");
                this.f15398k = true;
                return;
        }
    }

    /* renamed from: f */
    private String m14242f() {
        File file = new File(XJApp.getInstance().getApplicationInfo().dataDir, "osimg/r/ot01/");
        if (file.exists()) {
            return file.toString();
        }
        return null;
    }

    /* renamed from: a */
    private void m14255a(String str) {
        try {
            this.f15395h = FloatAssistManager.m12397i().m12423a(new ContextThemeWrapper(FloatApp.m18899b().m18901a(), C1990R.style.nrzs_assist_set_theme), str, this.f15392e);
            this.f15396i = true;
            if (this.f15395h != null) {
                this.f15393f.mo12126g();
                FloatViewManager.m12346a(this.f15393f.getContext().getApplicationContext()).m12344a(this.f15393f.getContext(), this.f15395h);
            } else {
                this.f15393f.mo12127f();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Subscribe(m3389a = ThreadMode.MAIN)
    /* renamed from: a */
    public void m14253a(FtEvent.C3568f fVar) {
        int i = fVar.f16163a;
        LogUtils.m23734c("newEngin", "收到引擎消息：" + i);
        if (i == 25) {
            this.f15393f.mo12128e();
        }
    }

    @Subscribe(m3389a = ThreadMode.MAIN)
    /* renamed from: a */
    public void m14251a(XNKJEvent.C3574d dVar) {
        m14257a();
    }

    /* renamed from: a */
    public void m14256a(int i) {
        if (i == 0 && this.f15395h != null) {
            FloatViewManager.m12346a(this.f15393f.getContext().getApplicationContext()).m12344a(this.f15393f.getContext(), this.f15395h);
        }
    }

    /* renamed from: b */
    public void m14249b() {
        LogUtils.m23734c("newEngin", "脚本设置界面register");
        EventBus.m3448a().m3446a(this);
    }

    /* renamed from: c */
    public void m14247c() {
        LogUtils.m23734c("newEngin", "脚本设置界面unRegister");
        EventBus.m3448a().m3430c(this);
    }

    /* renamed from: d */
    public void m14245d() {
        UserInfoManager.m12594a().m12588c(new DoneCallback<UserInfo>() { // from class: z1.adv.2
            /* renamed from: a */
            public void onDone(UserInfo userInfo) {
                if (userInfo != null && XJAssistInfoPresenter.this.f15393f != null) {
                    XJAssistInfoPresenter.this.f15393f.mo12130a(userInfo.GoldCoinNum);
                }
            }
        });
    }

    /* renamed from: e */
    public void m14243e() {
        if (this.f15398k) {
            this.f15393f.mo12125h();
        } else {
            FloatXnkjManager.INSTANCE.runEngin(new FloatXnkjManager.AbstractC3864a() { // from class: z1.adv.3
                @Override // p110z1.FloatXnkjManager.AbstractC3864a
                /* renamed from: a */
                public void mo11569a(int i, String str) {
                }

                @Override // p110z1.FloatXnkjManager.AbstractC3864a
                /* renamed from: a */
                public void mo11570a() {
                    FwManager.getInstance().removeXJAssisInfoView();
                    FloatViewManager.m12346a(XJAssistInfoPresenter.this.f15393f.getContext()).m12319j();
                    FwManager.getInstance().setRunScript(true);
                    EventBus.m3448a().m3427d(new UserInfoEvent.C3561a());
                }
            });
        }
    }

    @Subscribe(m3389a = ThreadMode.MAIN)
    /* renamed from: a */
    public void m14252a(FtEvent.C3570h hVar) {
        if (hVar.f16165a) {
            GstRepository.m12775a(FtUserManager.m12790g().m12797a());
        }
        this.f15398k = false;
        m14243e();
    }
}
