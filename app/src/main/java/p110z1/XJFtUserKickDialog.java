package p110z1;

import android.content.Context;
import android.support.p006v7.widget.LinearLayoutManager;
import android.support.p006v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.blankj.utilcode.util.ToastUtils;
import com.cyjh.mobileanjian.ipc.utils.Util;
import com.nrzs.data.DataApp;
import com.nrzs.data.base.BaseResponse;
import com.nrzs.data.p066ft.bean.EnginInteraRequestInfo;
import com.nrzs.data.p066ft.bean.SessionInfo;
import com.nrzs.data.p066ft.bean.request.SessionRequestInfo;
import com.nrzs.data.p066ft.bean.response.SessionInfoResponse;
import com.nrzs.data.user.bean.respond.LoginResultV1Info;
import com.nrzs.http.UICallback;
import com.nrzs.p067ft.C1990R;
import com.nrzs.p067ft.FloatApp;
import com.nrzs.p067ft.adapter.UserKickAdapter;
import com.nrzs.p067ft.p068ui.base.FtBaseDialog;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.apache.tools.ant.taskdefs.optional.vss.MSVSSConstants;
import p110z1.XNKJEvent;

/* renamed from: z1.ads */
/* loaded from: classes3.dex */
public class XJFtUserKickDialog extends FtBaseDialog implements IUserKickCallback {

    /* renamed from: a */
    private ImageView f15346a;

    /* renamed from: b */
    private TextView f15347b;

    /* renamed from: c */
    private RecyclerView f15348c;

    /* renamed from: d */
    private UserKickAdapter f15349d;

    /* renamed from: e */
    private UserKickModel f15350e;

    /* renamed from: f */
    private TextView f15351f;

    /* renamed from: g */
    private SessionInfoResponse f15352g;

    /* renamed from: h */
    private TextView f15353h;

    /* renamed from: i */
    private TextView f15354i;

    /* renamed from: j */
    private TextView f15355j;

    /* renamed from: k */
    private TextView f15356k;

    /* renamed from: l */
    private LinearLayout f15357l;

    /* renamed from: m */
    private TextView f15358m;

    /* renamed from: n */
    private LinearLayout f15359n;

    /* renamed from: o */
    private LinearLayout f15360o;

    /* renamed from: p */
    private List<SessionInfo> f15361p = new ArrayList();

    /* renamed from: q */
    private List<SessionInfo> f15362q = new ArrayList();

    /* renamed from: r */
    private UICallback<BaseResponse<LoginResultV1Info>> f15363r = new UICallback<BaseResponse<LoginResultV1Info>>() { // from class: z1.ads.1
        /* renamed from: a  reason: avoid collision after fix types in other method */
        public void mo3022a(BaseResponse<LoginResultV1Info> baseResponse) {
        }

        @Override // com.nrzs.http.UICallback
        /* renamed from: a */
        public void mo3021a(Throwable th) {
            ToastUtils.m23030a("登录失败");
        }
    };

    /* renamed from: s */
    private UICallback f15364s = new UICallback<SessionInfoResponse>() { // from class: z1.ads.2
        @Override // com.nrzs.http.UICallback
        /* renamed from: a */
        public void mo3021a(Throwable th) {
        }

        /* renamed from: a  reason: avoid collision after fix types in other method */
        public void mo3022a(SessionInfoResponse sessionInfoResponse) {
            if (sessionInfoResponse != null) {
                XJFtUserKickDialog.this.mo12177a(sessionInfoResponse);
            }
        }
    };

    @Override // com.nrzs.p067ft.p068ui.base.FtBaseDialog
    /* renamed from: d */
    protected void mo3011d() {
    }

    public XJFtUserKickDialog(Context context) {
        super(context, C1990R.style.nrzs_assist_dialog_theme);
    }

    @Override // android.app.Dialog
    public void show() {
        FwManager.getInstance().removeXJAssisInfoView();
        super.show();
        FloatViewManager.m12346a(FloatApp.m18899b().m18901a()).m12319j();
        m14271e();
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        FwManager.getInstance().initXJFloatView(true);
    }

    @Override // com.nrzs.p067ft.p068ui.base.FtBaseDialog
    /* renamed from: a */
    protected void mo3014a() {
        setCancelable(false);
    }

    @Override // com.nrzs.p067ft.p068ui.base.FtBaseDialog
    /* renamed from: b */
    protected void mo3013b() {
        setContentView(C1990R.layout.nrzs_ft_dialog_user_kick);
        this.f15346a = (ImageView) findViewById(C1990R.C1992id.nrzs_ft_iv_close);
        this.f15348c = (RecyclerView) findViewById(C1990R.C1992id.rv_ft_user_kick);
        this.f15347b = (TextView) findViewById(C1990R.C1992id.bird_pop_kick_title);
        this.f15353h = (TextView) findViewById(C1990R.C1992id.tv_kick_title_explain);
        this.f15354i = (TextView) findViewById(C1990R.C1992id.bird_pop_multi_open_title_current);
        this.f15355j = (TextView) findViewById(C1990R.C1992id.tv_user_kick_session);
        this.f15356k = (TextView) findViewById(C1990R.C1992id.tv_gold_kick_session);
        this.f15357l = (LinearLayout) findViewById(C1990R.C1992id.ll_gold_kick_session);
        this.f15351f = (TextView) findViewById(C1990R.C1992id.tv_gold_kick_session_tip);
        this.f15358m = (TextView) findViewById(C1990R.C1992id.tv_tips);
        this.f15359n = (LinearLayout) findViewById(C1990R.C1992id.not_jb_layout);
        this.f15360o = (LinearLayout) findViewById(C1990R.C1992id.data_lay);
    }

    @Override // com.nrzs.p067ft.p068ui.base.FtBaseDialog
    /* renamed from: c */
    protected void mo3012c() {
        this.f15347b.setOnClickListener(new View.OnClickListener() { // from class: z1.ads.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                XJFtUserKickDialog.this.dismiss();
                IntentToAssistService.m12813a(FloatApp.m18899b().m18901a());
            }
        });
        this.f15346a.setOnClickListener(new View.OnClickListener() { // from class: z1.ads.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                XJFtUserKickDialog.this.dismiss();
            }
        });
        this.f15357l.setOnClickListener(new View.OnClickListener() { // from class: z1.ads.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                XJFtUserKickDialog.this.f15357l.setEnabled(false);
                XJFtUserKickDialog.this.f15355j.setEnabled(true);
                XJFtUserKickDialog.this.f15355j.setTextColor(XJFtUserKickDialog.this.getContext().getResources().getColor(C1990R.color.black));
                XJFtUserKickDialog.this.f15356k.setTextColor(XJFtUserKickDialog.this.getContext().getResources().getColor(C1990R.color.white));
                XJFtUserKickDialog.this.f15351f.setTextColor(XJFtUserKickDialog.this.getContext().getResources().getColor(C1990R.color.white));
                if (XJFtUserKickDialog.this.f15352g != null) {
                    XJFtUserKickDialog.this.f15359n.setVisibility(8);
                    XJFtUserKickDialog.this.f15360o.setVisibility(0);
                    if (XJFtUserKickDialog.this.f15362q.size() == 0) {
                        XJFtUserKickDialog.this.f15359n.setVisibility(0);
                        XJFtUserKickDialog.this.f15360o.setVisibility(8);
                    }
                    XJFtUserKickDialog adsVar = XJFtUserKickDialog.this;
                    adsVar.m14278a(adsVar.f15362q);
                    return;
                }
                XJFtUserKickDialog.this.f15359n.setVisibility(0);
                XJFtUserKickDialog.this.f15360o.setVisibility(8);
            }
        });
        this.f15355j.setOnClickListener(new View.OnClickListener() { // from class: z1.ads.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                XJFtUserKickDialog.this.f15355j.setEnabled(false);
                XJFtUserKickDialog.this.f15357l.setEnabled(true);
                XJFtUserKickDialog.this.f15355j.setTextColor(XJFtUserKickDialog.this.getContext().getResources().getColor(C1990R.color.white));
                XJFtUserKickDialog.this.f15356k.setTextColor(XJFtUserKickDialog.this.getContext().getResources().getColor(C1990R.color.black));
                XJFtUserKickDialog.this.f15351f.setTextColor(XJFtUserKickDialog.this.getContext().getResources().getColor(C1990R.color.black));
                if (XJFtUserKickDialog.this.f15352g != null) {
                    XJFtUserKickDialog.this.f15359n.setVisibility(8);
                    XJFtUserKickDialog.this.f15360o.setVisibility(0);
                    if (XJFtUserKickDialog.this.f15361p.size() == 0) {
                        XJFtUserKickDialog.this.f15359n.setVisibility(0);
                        XJFtUserKickDialog.this.f15360o.setVisibility(8);
                    }
                    XJFtUserKickDialog adsVar = XJFtUserKickDialog.this;
                    adsVar.m14278a(adsVar.f15361p);
                    return;
                }
                XJFtUserKickDialog.this.f15359n.setVisibility(0);
                XJFtUserKickDialog.this.f15360o.setVisibility(8);
            }
        });
    }

    @Override // p110z1.IUserKickCallback
    /* renamed from: a */
    public void mo12178a(BaseResponse<LoginResultV1Info> baseResponse) {
        if (baseResponse == null) {
            return;
        }
        if (baseResponse.code == 0) {
            ToastUtils.m23030a(baseResponse.msg);
            dismiss();
            IntentToAssistService.m12813a(FloatApp.m18899b().m18901a());
        } else if (baseResponse.code == 1) {
            apf.m11842a(DataApp.m18939d().m18947a(), ShareVal.f16591a, ShareVal.f16597g, baseResponse.data.AutoLoginToken);
            FtUserManager.m12790g().m12796a(baseResponse.data.UserInfo);
            LoginManager.m12620d().m12628a(baseResponse.data.UserInfo);
            EnginInteraRequestInfo enginInteraRequestInfo = new EnginInteraRequestInfo();
            enginInteraRequestInfo.AppSign = Util.getAppSinature(FloatApp.m18899b().m18901a());
            enginInteraRequestInfo.Command = 1;
            enginInteraRequestInfo.SessionId = LoginManager.m12620d().m12616h();
            enginInteraRequestInfo.UserId = LoginManager.m12620d().m12614j();
            enginInteraRequestInfo.DesKey = LoginManager.m12620d().m12612l();
            enginInteraRequestInfo.ScriptCacheRPath = ShareVal.f16591a + File.separatorChar + MSVSSConstants.SS_EXE;
            enginInteraRequestInfo.dycIp = LocalHttp.f16542c;
            FloatAssistManager.m12397i().m12420a(enginInteraRequestInfo, "钥匙激活成功");
            EventBus.m3448a().m3427d(new XNKJEvent.C3574d(baseResponse.data.UserInfo));
            FloatViewManager.m12346a(getContext().getApplicationContext()).m12318k();
        } else if (baseResponse.code == -1) {
            show();
            FloatViewManager.m12346a(getContext().getApplicationContext()).m12318k();
        }
    }

    @Override // p110z1.IUserKickCallback
    /* renamed from: a */
    public void mo12177a(SessionInfoResponse sessionInfoResponse) {
        if (sessionInfoResponse != null) {
            this.f15352g = sessionInfoResponse;
            int size = sessionInfoResponse.getUserSessions() == null ? 0 : sessionInfoResponse.getUserSessions().size();
            int size2 = sessionInfoResponse.getUserGoldSessions() == null ? 0 : sessionInfoResponse.getUserGoldSessions().size();
            TextView textView = this.f15354i;
            textView.setText("当前账号VIP登录开数上限：" + sessionInfoResponse.getOpenNum());
            TextView textView2 = this.f15353h;
            textView2.setText(" 已登录设备数:" + size);
            TextView textView3 = this.f15355j;
            textView3.setText("VIP设备（" + size + "）");
            TextView textView4 = this.f15356k;
            textView4.setText("金币设备（" + size2 + "）");
            if (size > sessionInfoResponse.getOpenNum()) {
                this.f15358m.setVisibility(0);
            }
        }
        this.f15355j.setEnabled(false);
        this.f15357l.setEnabled(true);
        this.f15355j.setTextColor(getContext().getResources().getColor(C1990R.color.white));
        this.f15356k.setTextColor(getContext().getResources().getColor(C1990R.color.black));
        this.f15351f.setTextColor(getContext().getResources().getColor(C1990R.color.black));
        m14278a(sessionInfoResponse.getUserSessions());
        this.f15361p.clear();
        this.f15362q.clear();
        if (sessionInfoResponse.getUserSessions() != null) {
            this.f15361p.addAll(sessionInfoResponse.getUserSessions());
        }
        if (sessionInfoResponse.getUserGoldSessions() != null) {
            this.f15362q.addAll(sessionInfoResponse.getUserGoldSessions());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m14278a(List<SessionInfo> list) {
        if (list == null) {
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
        UserKickAdapter userKickAdapter = this.f15349d;
        if (userKickAdapter == null) {
            this.f15349d = new UserKickAdapter(new UserKickAdapter.AbstractC2004a() { // from class: z1.ads.7
                @Override // com.nrzs.p067ft.adapter.UserKickAdapter.AbstractC2004a
                /* renamed from: a */
                public void mo12161a(long j, String str, int i) {
                    XJFtUserKickDialog.this.m14280a(j, str, i);
                }

                @Override // com.nrzs.p067ft.adapter.UserKickAdapter.AbstractC2004a
                /* renamed from: a */
                public void mo12160a(String str, long j, String str2, int i) {
                    XJFtUserKickDialog.this.m14279a(str, j, str2, i);
                }
            }, list);
            this.f15348c.setAdapter(this.f15349d);
            this.f15348c.setLayoutManager(new LinearLayoutManager(getContext()));
            return;
        }
        userKickAdapter.f11255c.clear();
        this.f15349d.f11255c.addAll(list);
        this.f15349d.notifyDataSetChanged();
    }

    @Override // p110z1.IUserKickCallback
    /* renamed from: a */
    public void mo12181a(int i, String str) {
        UserKickAdapter userKickAdapter = this.f15349d;
        if (userKickAdapter != null) {
            userKickAdapter.m18912a(i, str);
        }
    }

    @Override // p110z1.IUserKickCallback
    /* renamed from: a */
    public void mo12180a(int i, boolean z) {
        UserKickAdapter userKickAdapter = this.f15349d;
        if (userKickAdapter != null) {
            userKickAdapter.m18904b(i);
        }
        if (z) {
            IntentToAssistService.m12813a(FloatApp.m18899b().m18901a());
            dismiss();
            return;
        }
        m14269f();
    }

    /* renamed from: e */
    public void m14271e() {
        try {
            SessionRequestInfo sessionRequestInfo = new SessionRequestInfo();
            sessionRequestInfo.UserID = LoginManager.m12620d().m12614j();
            if (this.f15350e != null) {
                this.f15350e.m12556a(sessionRequestInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public void m14279a(String str, long j, String str2, int i) {
        UserKickModel alsVar = this.f15350e;
        if (alsVar != null) {
            alsVar.m12555a(str, j, str2, i);
        }
    }

    /* renamed from: a */
    public void m14280a(long j, String str, int i) {
        UserKickModel alsVar = this.f15350e;
        if (alsVar != null) {
            alsVar.m12557a(j, str, i, false);
        }
    }

    /* renamed from: f */
    public void m14269f() {
        UserKickModel alsVar = this.f15350e;
        if (alsVar != null) {
            alsVar.m12558a();
        }
    }

    @Override // android.app.Dialog
    protected void onStart() {
        super.onStart();
        if (this.f15350e == null) {
            this.f15350e = new UserKickModel(this);
        }
    }

    @Override // android.app.Dialog
    protected void onStop() {
        super.onStop();
        UserKickModel alsVar = this.f15350e;
        if (alsVar != null) {
            alsVar.m12553b();
            this.f15350e = null;
        }
    }

    /* renamed from: a */
    public void m14275a(boolean z) {
        if (z) {
            this.f15346a.setVisibility(8);
            this.f15347b.setVisibility(0);
            return;
        }
        this.f15346a.setVisibility(0);
        this.f15347b.setVisibility(8);
    }
}
