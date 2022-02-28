package p110z1;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.cyjh.mobileanjian.ipc.utils.Util;
import com.nrzs.data.DataApp;
import com.nrzs.data.base.BaseResponse;
import com.nrzs.data.p066ft.bean.EnginInteraRequestInfo;
import com.nrzs.data.p066ft.bean.SessionInfo;
import com.nrzs.data.p066ft.bean.request.SessionRequestInfo;
import com.nrzs.data.p066ft.bean.response.SessionInfoResponse;
import com.nrzs.data.user.bean.respond.LoginResultV1Info;
import com.nrzs.ft.C1990R;
import com.nrzs.ft.FloatApp;
import com.nrzs.ft.adapter.UserKickAdapter;
import com.nrzs.ft.ui.base.FtBaseDialog;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.apache.tools.ant.taskdefs.optional.vss.MSVSSConstants;
import p110z1.FtEvent;

/* renamed from: z1.anz */
/* loaded from: classes3.dex */
public class FtUserKickDialog extends FtBaseDialog implements IUserKickCallback {

    /* renamed from: a */
    private ImageView f16914a;

    /* renamed from: b */
    private TextView f16915b;

    /* renamed from: c */
    private RecyclerView f16916c;

    /* renamed from: d */
    private UserKickAdapter f16917d;

    /* renamed from: e */
    private UserKickModel f16918e;

    /* renamed from: f */
    private SessionInfoResponse f16919f;

    /* renamed from: g */
    private TextView f16920g;

    /* renamed from: h */
    private TextView f16921h;

    /* renamed from: i */
    private TextView f16922i;

    /* renamed from: j */
    private TextView f16923j;

    /* renamed from: k */
    private LinearLayout f16924k;

    /* renamed from: l */
    private TextView f16925l;

    /* renamed from: m */
    private TextView f16926m;

    /* renamed from: n */
    private LinearLayout f16927n;

    /* renamed from: o */
    private LinearLayout f16928o;

    /* renamed from: p */
    private List<SessionInfo> f16929p = new ArrayList();

    /* renamed from: q */
    private List<SessionInfo> f16930q = new ArrayList();

    @Override // com.nrzs.ft.ui.base.FtBaseDialog
    /* renamed from: d */
    protected void mo3011d() {
    }

    public FtUserKickDialog(Context context) {
        super(context, C1990R.style.nrzs_assist_dialog_theme);
    }

    @Override // android.app.Dialog
    public void show() {
        super.show();
        FloatViewManager.m12346a(FloatApp.m18899b().m18901a()).m12327e();
        FloatViewManager.m12346a(FloatApp.m18899b().m18901a()).m12319j();
        m12168e();
    }

    @Override // com.nrzs.ft.ui.base.FtBaseDialog
    /* renamed from: a */
    protected void mo3014a() {
        setCancelable(false);
    }

    @Override // com.nrzs.ft.ui.base.FtBaseDialog
    /* renamed from: b */
    protected void mo3013b() {
        setContentView(C1990R.layout.nrzs_ft_dialog_user_kick);
        this.f16914a = (ImageView) findViewById(C1990R.C1992id.nrzs_ft_iv_close);
        this.f16916c = (RecyclerView) findViewById(C1990R.C1992id.rv_ft_user_kick);
        this.f16915b = (TextView) findViewById(C1990R.C1992id.bird_pop_kick_title);
        this.f16927n = (LinearLayout) findViewById(C1990R.C1992id.not_jb_layout);
        this.f16928o = (LinearLayout) findViewById(C1990R.C1992id.data_lay);
        this.f16920g = (TextView) findViewById(C1990R.C1992id.tv_kick_title_explain);
        this.f16921h = (TextView) findViewById(C1990R.C1992id.bird_pop_multi_open_title_current);
        this.f16922i = (TextView) findViewById(C1990R.C1992id.tv_user_kick_session);
        this.f16923j = (TextView) findViewById(C1990R.C1992id.tv_gold_kick_session);
        this.f16924k = (LinearLayout) findViewById(C1990R.C1992id.ll_gold_kick_session);
        this.f16926m = (TextView) findViewById(C1990R.C1992id.tv_tips);
        this.f16925l = (TextView) findViewById(C1990R.C1992id.tv_gold_kick_session_tip);
        this.f16922i.setEnabled(false);
        this.f16924k.setEnabled(true);
    }

    @Override // com.nrzs.ft.ui.base.FtBaseDialog
    /* renamed from: c */
    protected void mo3012c() {
        this.f16915b.setOnClickListener(new View.OnClickListener() { // from class: z1.anz.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                FtUserKickDialog.this.dismiss();
                IntentToAssistService.m12813a(FloatApp.m18899b().m18901a());
            }
        });
        this.f16914a.setOnClickListener(new View.OnClickListener() { // from class: z1.anz.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                FtUserKickDialog.this.dismiss();
            }
        });
        this.f16924k.setOnClickListener(new View.OnClickListener() { // from class: z1.anz.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                FtUserKickDialog.this.f16924k.setEnabled(false);
                FtUserKickDialog.this.f16922i.setEnabled(true);
                FtUserKickDialog.this.f16922i.setTextColor(FtUserKickDialog.this.getContext().getResources().getColor(C1990R.color.black));
                FtUserKickDialog.this.f16923j.setTextColor(FtUserKickDialog.this.getContext().getResources().getColor(C1990R.color.white));
                FtUserKickDialog.this.f16925l.setTextColor(FtUserKickDialog.this.getContext().getResources().getColor(C1990R.color.white));
                if (FtUserKickDialog.this.f16919f != null) {
                    FtUserKickDialog.this.f16927n.setVisibility(8);
                    FtUserKickDialog.this.f16928o.setVisibility(0);
                    if (FtUserKickDialog.this.f16930q.size() == 0) {
                        FtUserKickDialog.this.f16927n.setVisibility(0);
                        FtUserKickDialog.this.f16928o.setVisibility(8);
                    }
                    FtUserKickDialog anzVar = FtUserKickDialog.this;
                    anzVar.m12175a(anzVar.f16930q);
                    return;
                }
                FtUserKickDialog.this.f16927n.setVisibility(0);
                FtUserKickDialog.this.f16928o.setVisibility(8);
            }
        });
        this.f16922i.setOnClickListener(new View.OnClickListener() { // from class: z1.anz.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                FtUserKickDialog.this.f16922i.setEnabled(false);
                FtUserKickDialog.this.f16924k.setEnabled(true);
                FtUserKickDialog.this.f16922i.setTextColor(FtUserKickDialog.this.getContext().getResources().getColor(C1990R.color.white));
                FtUserKickDialog.this.f16923j.setTextColor(FtUserKickDialog.this.getContext().getResources().getColor(C1990R.color.black));
                FtUserKickDialog.this.f16925l.setTextColor(FtUserKickDialog.this.getContext().getResources().getColor(C1990R.color.black));
                if (FtUserKickDialog.this.f16919f != null) {
                    FtUserKickDialog.this.f16927n.setVisibility(8);
                    FtUserKickDialog.this.f16928o.setVisibility(0);
                    if (FtUserKickDialog.this.f16929p.size() == 0) {
                        FtUserKickDialog.this.f16927n.setVisibility(0);
                        FtUserKickDialog.this.f16928o.setVisibility(8);
                    }
                    FtUserKickDialog anzVar = FtUserKickDialog.this;
                    anzVar.m12175a(anzVar.f16929p);
                    return;
                }
                FtUserKickDialog.this.f16927n.setVisibility(0);
                FtUserKickDialog.this.f16928o.setVisibility(8);
            }
        });
    }

    @Override // p110z1.IUserKickCallback
    /* renamed from: a */
    public void mo12178a(BaseResponse<LoginResultV1Info> baseResponse) {
        if (baseResponse.code == 0) {
            ToastUtils.m23030a(baseResponse.msg);
            dismiss();
            IntentToAssistService.m12813a(FloatApp.m18899b().m18901a());
        } else if (baseResponse.code == 1) {
            LogUtils.m23734c("token111", "悬浮窗自动登录成功：name:" + baseResponse.data.UserInfo.UserName + ",token:" + baseResponse.data.AutoLoginToken);
            apf.m11842a(DataApp.m18939d().m18947a(), ShareVal.f16591a, ShareVal.f16597g, baseResponse.data.AutoLoginToken);
            Log.e("token", "ftlog自动登录成功 账号" + baseResponse.data.UserInfo.UserName + baseResponse.data.AutoLoginToken);
            FtUserManager.m12790g().m12796a(baseResponse.data.UserInfo);
            LoginManager.m12620d().m12628a(baseResponse.data.UserInfo);
            EnginInteraRequestInfo enginInteraRequestInfo = new EnginInteraRequestInfo();
            enginInteraRequestInfo.AppSign = Util.getAppSinature(FloatApp.m18899b().m18901a());
            enginInteraRequestInfo.Command = 1;
            enginInteraRequestInfo.SessionId = FtUserManager.m12790g().m12794c();
            enginInteraRequestInfo.UserId = FtUserManager.m12790g().m12797a();
            enginInteraRequestInfo.DesKey = FtUserManager.m12790g().m12793d();
            enginInteraRequestInfo.ScriptCacheRPath = ShareVal.f16591a + File.separatorChar + MSVSSConstants.SS_EXE;
            enginInteraRequestInfo.dycIp = LocalHttp.f16542c;
            FloatAssistManager.m12397i().m12420a(enginInteraRequestInfo, "钥匙激活成功");
            EventBus.m3448a().m3427d(new FtEvent.C3567e(baseResponse.data.UserInfo));
            FloatViewManager.m12346a(getContext().getApplicationContext()).m12318k();
        }
    }

    @Override // p110z1.IUserKickCallback
    /* renamed from: a */
    public void mo12177a(SessionInfoResponse sessionInfoResponse) {
        if (sessionInfoResponse != null) {
            this.f16919f = sessionInfoResponse;
            int size = sessionInfoResponse.getUserSessions() == null ? 0 : sessionInfoResponse.getUserSessions().size();
            if (size != 0) {
                List<SessionInfo> userSessions = sessionInfoResponse.getUserSessions();
                int i = size;
                for (int i2 = 0; i2 < userSessions.size(); i2++) {
                    if (!userSessions.get(i2).isTwentyFourHour() || TextUtils.isEmpty(userSessions.get(i2).getLastRunTime())) {
                        i--;
                    }
                }
                size = i;
            }
            int size2 = sessionInfoResponse.getUserGoldSessions() == null ? 0 : sessionInfoResponse.getUserGoldSessions().size();
            this.f16921h.setText("当前账号VIP登录开数上限：" + sessionInfoResponse.getOpenNum());
            this.f16920g.setText(" 已登陆设备数:" + size);
            this.f16922i.setText("VIP设备（" + size + "）");
            this.f16923j.setText("金币设备（" + size2 + "）");
            if (size > sessionInfoResponse.getOpenNum()) {
                this.f16926m.setVisibility(0);
            }
        }
        this.f16922i.setEnabled(false);
        m12175a(sessionInfoResponse.getUserSessions());
        this.f16929p.clear();
        this.f16930q.clear();
        if (sessionInfoResponse.getUserSessions() != null) {
            this.f16929p.addAll(sessionInfoResponse.getUserSessions());
        }
        if (sessionInfoResponse.getUserGoldSessions() != null) {
            this.f16930q.addAll(sessionInfoResponse.getUserGoldSessions());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0, types: [java.util.List, java.util.Collection, java.util.List<com.nrzs.data.ft.bean.SessionInfo>] */
    /* JADX WARN: Type inference failed for: r5v1, types: [java.util.List, java.util.List<T>] */
    /* JADX WARN: Type inference failed for: r5v4, types: [java.util.ArrayList] */
    /* renamed from: a */
    public void m12175a(List<SessionInfo> list) {
        if (list == 0) {
            list = new ArrayList<>();
        } else {
            ArrayList<SessionInfo> arrayList = new ArrayList();
            arrayList.addAll(list);
            for (SessionInfo sessionInfo : arrayList) {
                if (sessionInfo.getDeviceCode().equals(Config.m12527a())) {
                    list.remove(sessionInfo);
                    list.add(0, sessionInfo);
                }
            }
        }
        UserKickAdapter userKickAdapter = this.f16917d;
        if (userKickAdapter == null) {
            this.f16917d = new UserKickAdapter(new UserKickAdapter.AbstractC2004a() { // from class: z1.anz.5
                @Override // com.nrzs.ft.adapter.UserKickAdapter.AbstractC2004a
                /* renamed from: a */
                public void mo12161a(long j, String str, int i) {
                    FtUserKickDialog.this.m12179a(j, str, i);
                }

                @Override // com.nrzs.ft.adapter.UserKickAdapter.AbstractC2004a
                /* renamed from: a */
                public void mo12160a(String str, long j, String str2, int i) {
                    FtUserKickDialog.this.m12176a(str, j, str2, i);
                }
            }, list);
            this.f16916c.setAdapter(this.f16917d);
            this.f16916c.setLayoutManager(new LinearLayoutManager(getContext()));
            return;
        }
        userKickAdapter.f11255c = list;
        userKickAdapter.notifyDataSetChanged();
    }

    @Override // p110z1.IUserKickCallback
    /* renamed from: a */
    public void mo12181a(int i, String str) {
        UserKickAdapter userKickAdapter = this.f16917d;
        if (userKickAdapter != null) {
            userKickAdapter.m18912a(i, str);
        }
    }

    @Override // p110z1.IUserKickCallback
    /* renamed from: a */
    public void mo12180a(int i, boolean z) {
        UserKickAdapter userKickAdapter = this.f16917d;
        if (userKickAdapter != null) {
            userKickAdapter.m18904b(i);
        }
        if (z) {
            IntentToAssistService.m12813a(FloatApp.m18899b().m18901a());
            dismiss();
            return;
        }
        m12166f();
        m12168e();
    }

    /* renamed from: e */
    public void m12168e() {
        try {
            SessionRequestInfo sessionRequestInfo = new SessionRequestInfo();
            sessionRequestInfo.UserID = FtUserManager.m12790g().m12797a();
            if (this.f16918e != null) {
                this.f16918e.m12556a(sessionRequestInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public void m12176a(String str, long j, String str2, int i) {
        UserKickModel alsVar = this.f16918e;
        if (alsVar != null) {
            alsVar.m12555a(str, j, str2, i);
        }
    }

    /* renamed from: a */
    public void m12179a(long j, String str, int i) {
        UserKickModel alsVar = this.f16918e;
        if (alsVar != null) {
            alsVar.m12557a(j, str, i, false);
        }
    }

    /* renamed from: f */
    public void m12166f() {
        UserKickModel alsVar = this.f16918e;
        if (alsVar != null) {
            alsVar.m12558a();
        }
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override // android.app.Dialog
    protected void onStart() {
        super.onStart();
        if (this.f16918e == null) {
            this.f16918e = new UserKickModel(this);
        }
    }

    @Override // android.app.Dialog
    protected void onStop() {
        super.onStop();
        UserKickModel alsVar = this.f16918e;
        if (alsVar != null) {
            alsVar.m12553b();
            this.f16918e = null;
        }
    }

    /* renamed from: a */
    public void m12172a(boolean z) {
        if (z) {
            this.f16914a.setVisibility(8);
            this.f16915b.setVisibility(0);
            return;
        }
        this.f16914a.setVisibility(0);
        this.f16915b.setVisibility(8);
    }
}
