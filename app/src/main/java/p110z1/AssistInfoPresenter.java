package p110z1;

import android.view.ContextThemeWrapper;
import android.widget.LinearLayout;
import com.blankj.utilcode.util.LogUtils;
import com.cyjh.mobileanjian.ipc.utils.RootUtil;
import com.nrzs.data.p066ft.bean.AInfo;
import com.nrzs.data.p066ft.bean.VInfo;
import com.nrzs.data.user.bean.UserInfo;
import com.nrzs.ft.C1990R;
import com.nrzs.ft.FloatApp;
import p110z1.FtEvent;

/* renamed from: z1.anp */
/* loaded from: classes3.dex */
public class AssistInfoPresenter {

    /* renamed from: b */
    private long f16811b;

    /* renamed from: c */
    private long f16812c;

    /* renamed from: d */
    private String f16813d;

    /* renamed from: e */
    private IAssistInfoView f16814e;

    /* renamed from: f */
    private AInfo f16815f;

    /* renamed from: g */
    private LinearLayout f16816g;

    /* renamed from: h */
    private boolean f16817h = false;

    /* renamed from: i */
    private boolean f16818i = false;

    /* renamed from: j */
    private boolean f16819j = false;

    /* renamed from: a */
    private AssistInfoModel f16810a = new AssistInfoModel();

    public AssistInfoPresenter(long j, long j2, String str, IAssistInfoView aocVar) {
        this.f16811b = j;
        this.f16812c = j2;
        this.f16813d = str;
        this.f16814e = aocVar;
    }

    /* renamed from: a */
    public void m12256a() {
        this.f16810a.m12258a(this.f16811b, this.f16812c, this.f16813d);
    }

    /* renamed from: b */
    public void m12249b() {
        if (this.f16818i) {
            this.f16814e.mo12125h();
            return;
        }
        if (!this.f16819j) {
            this.f16819j = true;
            if (FloatDataManager.m12352j().f16748d == 1) {
                EventCollectManager.m12642a().m12640a(this.f16814e.getContext(), "脚本设置页启动按钮点击ROOT模式", "脚本设置页启动按钮点击ROOT模式", EventConstants.f16445m);
                if (RootUtil.isRoot()) {
                    FloatAssistManager.m12397i().m12401f();
                } else {
                    MyToast.m11705a(FloatApp.m18899b().m18901a(), "无ROOT权限，请到【我的】页面-【更换模式】为免ROOT模式");
                }
            } else {
                EventCollectManager.m12642a().m12640a(this.f16814e.getContext(), "脚本设置页启动按钮点击免ROOT模式", "脚本设置页启动按钮点击免ROOT模式", EventConstants.f16446n);
                FloatAssistManager.m12397i().m12401f();
            }
        }
        this.f16819j = false;
    }

    @Subscribe(m3389a = ThreadMode.MAIN)
    /* renamed from: a */
    public void m12252a(FtEvent.C3569g gVar) {
        LogUtils.m23734c("newEngin", "onEventMainThread - EventRunPermission");
        this.f16815f = gVar.f16164a;
        AInfo aInfo = this.f16815f;
        if (aInfo == null) {
            this.f16814e.mo12128e();
            return;
        }
        VInfo vInfo = aInfo.RunPerm;
        if (FtUserManager.m12790g().m12795b() == 1 || FloatDataManager.m12352j().f16748d == 2) {
            if (vInfo != null) {
                if (vInfo.KickedOut) {
                    FloatAssistManager.m12397i().m12414b();
                    FloatViewManager.m12346a(FloatApp.m18899b().m18901a()).m12345a(this.f16814e.getContext(), 2);
                } else if (vInfo.BanRun || vInfo.TryExpired || vInfo.GoldNotEnough) {
                    FloatAssistManager.m12397i().m12414b();
                    FloatViewManager.m12346a(FloatApp.m18899b().m18901a()).m12324f(this.f16814e.getContext());
                } else {
                    if (this.f16815f.ScriptInfo != null) {
                        this.f16814e.mo12129a(this.f16815f);
                    }
                    if (vInfo.OutOpenNum) {
                        LogUtils.m23734c("newEngin", "onEventMainThread - OutOpenNum");
                        FloatViewManager.m12346a(FloatApp.m18899b().m18901a()).m12329d(this.f16814e.getContext());
                        return;
                    }
                    if (vInfo.IsNeedChooseGold) {
                        this.f16818i = true;
                    } else {
                        this.f16818i = false;
                    }
                    this.f16810a.m12257b(this.f16811b, this.f16812c, this.f16813d);
                }
            }
        } else if (vInfo.BanRun || vInfo.TryExpired || vInfo.GoldNotEnough) {
            FloatAssistManager.m12397i().m12414b();
            FloatViewManager.m12346a(FloatApp.m18899b().m18901a()).m12324f(this.f16814e.getContext());
        } else if (vInfo.OutOpenNum) {
            FloatAssistManager.m12397i().m12414b();
            LogUtils.m23734c("newEngin", "onEventMainThread - OutOpenNum");
            FloatViewManager.m12346a(FloatApp.m18899b().m18901a()).m12329d(this.f16814e.getContext());
        } else {
            LogUtils.m23734c("newEngin", "runPermInfo - IsNeedChooseGold" + vInfo.IsNeedChooseGold);
            if (vInfo.IsNeedChooseGold) {
                this.f16818i = true;
            } else {
                this.f16818i = false;
            }
            if (this.f16815f.ScriptInfo != null) {
                this.f16814e.mo12129a(this.f16815f);
            }
            this.f16810a.m12257b(this.f16811b, this.f16812c, this.f16813d);
        }
    }

    @Subscribe(m3389a = ThreadMode.MAIN)
    /* renamed from: a */
    public void m12254a(FtEvent.C3565c cVar) {
        try {
            this.f16816g = FloatAssistManager.m12397i().m12412b(new ContextThemeWrapper(FloatApp.m18899b().m18901a(), C1990R.style.nrzs_assist_set_theme));
            this.f16817h = true;
            if (this.f16816g != null) {
                this.f16814e.mo12126g();
                FloatViewManager.m12346a(this.f16814e.getContext().getApplicationContext()).m12344a(this.f16814e.getContext(), this.f16816g);
            } else {
                this.f16814e.mo12127f();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Subscribe(m3389a = ThreadMode.MAIN)
    /* renamed from: a */
    public void m12251a(FtEvent.C3570h hVar) {
        if (hVar.f16165a) {
            GstRepository.m12775a(FtUserManager.m12790g().m12797a());
        }
        this.f16818i = false;
        m12249b();
    }

    @Subscribe(m3389a = ThreadMode.MAIN)
    /* renamed from: a */
    public void m12253a(FtEvent.C3568f fVar) {
        int i = fVar.f16163a;
        LogUtils.m23734c("newEngin", "收到引擎消息：" + i);
        if (i == 25) {
            this.f16814e.mo12128e();
        }
    }

    /* renamed from: a */
    public void m12255a(int i) {
        if (i == 0 && this.f16816g != null) {
            FloatViewManager.m12346a(this.f16814e.getContext().getApplicationContext()).m12344a(this.f16814e.getContext(), this.f16816g);
        }
    }

    /* renamed from: c */
    public void m12248c() {
        LogUtils.m23734c("newEngin", "脚本设置界面register");
        EventBus.m3448a().m3446a(this);
    }

    /* renamed from: d */
    public void m12247d() {
        LogUtils.m23734c("newEngin", "脚本设置界面unRegister");
        EventBus.m3448a().m3430c(this);
    }

    /* renamed from: e */
    public boolean m12246e() {
        return this.f16818i;
    }

    /* renamed from: f */
    public void m12245f() {
        UserInfoManager.m12594a().m12588c(new DoneCallback<UserInfo>() { // from class: z1.anp.1
            /* renamed from: a */
            public void onDone(UserInfo userInfo) {
                if (userInfo != null && AssistInfoPresenter.this.f16814e != null) {
                    AssistInfoPresenter.this.f16814e.mo12130a(userInfo.GoldCoinNum);
                }
            }
        });
    }
}
