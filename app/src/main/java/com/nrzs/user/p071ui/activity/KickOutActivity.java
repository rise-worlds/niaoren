package com.nrzs.user.p071ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.nrzs.base.router.RouterConstants;
import com.nrzs.data.DataApp;
import com.nrzs.data.base.BaseResponse;
import com.nrzs.data.p066ft.bean.SessionInfo;
import com.nrzs.data.p066ft.bean.request.SessionRequestInfo;
import com.nrzs.data.p066ft.bean.response.SessionInfoResponse;
import com.nrzs.data.user.bean.respond.LoginResultV1Info;
import com.nrzs.p067ft.adapter.UserKickAdapter;
import com.nrzs.user.C2222R;
import com.nrzs.user.p071ui.base.UserBaseActivity;
import java.util.ArrayList;
import java.util.List;
import p110z1.AllExticDialog;
import p110z1.Autologinmanager;
import p110z1.Config;
import p110z1.IUserKickCallback;
import p110z1.IntentToAssistService;
import p110z1.LoginDialog;
import p110z1.LoginManager;
import p110z1.ShareVal;
import p110z1.UserKickModel;
import p110z1.apf;

@Route(path = RouterConstants.ModuleUser.KICK_OUT)
/* renamed from: com.nrzs.user.ui.activity.KickOutActivity */
/* loaded from: classes2.dex */
public class KickOutActivity extends UserBaseActivity implements IUserKickCallback {

    /* renamed from: a */
    private ImageView f11393a;

    /* renamed from: b */
    private TextView f11394b;

    /* renamed from: c */
    private RecyclerView f11395c;

    /* renamed from: d */
    private UserKickAdapter f11396d;

    /* renamed from: e */
    private UserKickModel f11397e;

    /* renamed from: f */
    private SessionInfoResponse f11398f;

    /* renamed from: g */
    private TextView f11399g;

    /* renamed from: h */
    private TextView f11400h;

    /* renamed from: i */
    private TextView f11401i;

    /* renamed from: j */
    private TextView f11402j;

    /* renamed from: k */
    private TextView f11403k;

    /* renamed from: l */
    private LinearLayout f11404l;

    /* renamed from: m */
    private TextView f11405m;

    /* renamed from: n */
    private LinearLayout f11406n;

    /* renamed from: o */
    private LinearLayout f11407o;

    /* renamed from: p */
    private ImageView f11408p;

    /* renamed from: q */
    private TextView f11409q;

    /* renamed from: t */
    private LoginDialog f11412t;

    /* renamed from: v */
    private long f11414v;

    /* renamed from: r */
    private List<SessionInfo> f11410r = new ArrayList();

    /* renamed from: s */
    private List<SessionInfo> f11411s = new ArrayList();

    /* renamed from: u */
    private boolean f11413u = true;

    @Override // p110z1.IUserKickCallback
    /* renamed from: a */
    public void mo12178a(BaseResponse<LoginResultV1Info> baseResponse) {
    }

    @Override // com.nrzs.libcommon.BaseActivity
    /* renamed from: b */
    public void mo18303b() {
    }

    /* renamed from: a */
    public static void m18357a(Context context) {
        Intent intent = new Intent(context, KickOutActivity.class);
        intent.setFlags(337641472);
        context.startActivity(intent);
    }

    @Override // com.nrzs.libcommon.BaseActivity
    /* renamed from: a */
    protected int mo18307a() {
        return C2222R.layout.activity_kickout_layout;
    }

    @Override // com.nrzs.libcommon.BaseActivity
    /* renamed from: a */
    public void mo18305a(Bundle bundle) {
        this.f11393a = (ImageView) findViewById(C2222R.C2224id.nrzs_ft_iv_close);
        this.f11394b = (TextView) findViewById(C2222R.C2224id.bird_pop_kick_title);
        this.f11395c = (RecyclerView) findViewById(C2222R.C2224id.rv_ft_user_kick);
        this.f11406n = (LinearLayout) findViewById(C2222R.C2224id.not_jb_layout);
        this.f11407o = (LinearLayout) findViewById(C2222R.C2224id.data_lay);
        this.f11408p = (ImageView) findViewById(C2222R.C2224id.freash);
        this.f11409q = (TextView) findViewById(C2222R.C2224id.all_out);
        this.f11399g = (TextView) findViewById(C2222R.C2224id.tv_kick_title_explain);
        this.f11400h = (TextView) findViewById(C2222R.C2224id.bird_pop_multi_open_title_current);
        this.f11401i = (TextView) findViewById(C2222R.C2224id.tv_user_kick_session);
        this.f11405m = (TextView) findViewById(C2222R.C2224id.tv_tips);
        this.f11402j = (TextView) findViewById(C2222R.C2224id.tv_gold_kick_session);
        this.f11404l = (LinearLayout) findViewById(C2222R.C2224id.ll_gold_kick_session);
        this.f11403k = (TextView) findViewById(C2222R.C2224id.tv_gold_kick_session_tip);
        this.f11401i.setEnabled(false);
        this.f11404l.setEnabled(true);
    }

    @Override // com.nrzs.libcommon.BaseActivity
    /* renamed from: b */
    public void mo18302b(Bundle bundle) {
        this.f11412t = new LoginDialog(this);
        this.f11397e = new UserKickModel(this);
        this.f11414v = getIntent().getLongExtra("UserId", -1L);
        this.f11413u = getIntent().getBooleanExtra("isKick", true);
        m18359a(this.f11414v);
        if (this.f11413u) {
            this.f11393a.setVisibility(8);
            this.f11394b.setVisibility(0);
            return;
        }
        this.f11393a.setVisibility(0);
        this.f11394b.setVisibility(8);
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
        if (this.f11413u) {
            IntentToAssistService.m12813a(this);
        }
    }

    @Override // com.nrzs.libcommon.BaseActivity
    /* renamed from: d */
    public void mo18300d() {
        this.f11409q.setOnClickListener(new View.OnClickListener() { // from class: com.nrzs.user.ui.activity.KickOutActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (apf.m11836b(DataApp.m18939d().m18947a(), ShareVal.f16591a, ShareVal.f16588S, false)) {
                    KickOutActivity kickOutActivity = KickOutActivity.this;
                    kickOutActivity.m18351b(kickOutActivity.f11414v, "", -1);
                    return;
                }
                new AllExticDialog(KickOutActivity.this, new AllExticDialog.AbstractC3853a() { // from class: com.nrzs.user.ui.activity.KickOutActivity.1.1
                    @Override // p110z1.AllExticDialog.AbstractC3853a
                    /* renamed from: a */
                    public void mo11659a() {
                        KickOutActivity.this.m18351b(KickOutActivity.this.f11414v, "", -1);
                    }
                }).show();
            }
        });
        this.f11408p.setOnClickListener(new View.OnClickListener() { // from class: com.nrzs.user.ui.activity.KickOutActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (KickOutActivity.this.f11412t == null) {
                    KickOutActivity kickOutActivity = KickOutActivity.this;
                    kickOutActivity.f11412t = new LoginDialog(kickOutActivity);
                }
                KickOutActivity.this.f11412t.show();
                KickOutActivity kickOutActivity2 = KickOutActivity.this;
                kickOutActivity2.m18359a(kickOutActivity2.f11414v);
            }
        });
        this.f11394b.setOnClickListener(new View.OnClickListener() { // from class: com.nrzs.user.ui.activity.KickOutActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                KickOutActivity.this.finish();
                IntentToAssistService.m12813a(KickOutActivity.this);
            }
        });
        this.f11393a.setOnClickListener(new View.OnClickListener() { // from class: com.nrzs.user.ui.activity.KickOutActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                KickOutActivity.this.finish();
            }
        });
        this.f11404l.setOnClickListener(new View.OnClickListener() { // from class: com.nrzs.user.ui.activity.KickOutActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                KickOutActivity.this.f11404l.setEnabled(false);
                KickOutActivity.this.f11401i.setEnabled(true);
                KickOutActivity.this.f11401i.setTextColor(KickOutActivity.this.getResources().getColor(C2222R.color.black));
                KickOutActivity.this.f11402j.setTextColor(KickOutActivity.this.getResources().getColor(C2222R.color.white));
                KickOutActivity.this.f11403k.setTextColor(KickOutActivity.this.getResources().getColor(C2222R.color.white));
                KickOutActivity.this.f11409q.setVisibility(4);
                if (KickOutActivity.this.f11398f != null) {
                    KickOutActivity.this.f11406n.setVisibility(8);
                    KickOutActivity.this.f11407o.setVisibility(0);
                    if (KickOutActivity.this.f11411s.size() == 0) {
                        KickOutActivity.this.f11406n.setVisibility(0);
                        KickOutActivity.this.f11407o.setVisibility(8);
                    }
                    KickOutActivity kickOutActivity = KickOutActivity.this;
                    kickOutActivity.m18352a(kickOutActivity.f11411s);
                    return;
                }
                KickOutActivity.this.f11406n.setVisibility(0);
                KickOutActivity.this.f11407o.setVisibility(8);
            }
        });
        this.f11401i.setOnClickListener(new View.OnClickListener() { // from class: com.nrzs.user.ui.activity.KickOutActivity.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                KickOutActivity.this.f11401i.setEnabled(false);
                KickOutActivity.this.f11404l.setEnabled(true);
                KickOutActivity.this.f11401i.setTextColor(KickOutActivity.this.getResources().getColor(C2222R.color.white));
                KickOutActivity.this.f11402j.setTextColor(KickOutActivity.this.getResources().getColor(C2222R.color.black));
                KickOutActivity.this.f11403k.setTextColor(KickOutActivity.this.getResources().getColor(C2222R.color.black));
                KickOutActivity.this.f11409q.setVisibility(0);
                if (KickOutActivity.this.f11398f != null) {
                    KickOutActivity.this.f11406n.setVisibility(8);
                    KickOutActivity.this.f11407o.setVisibility(0);
                    if (KickOutActivity.this.f11410r.size() == 0) {
                        KickOutActivity.this.f11406n.setVisibility(0);
                        KickOutActivity.this.f11407o.setVisibility(8);
                    }
                    KickOutActivity kickOutActivity = KickOutActivity.this;
                    kickOutActivity.m18352a(kickOutActivity.f11410r);
                    return;
                }
                KickOutActivity.this.f11406n.setVisibility(0);
                KickOutActivity.this.f11407o.setVisibility(8);
            }
        });
    }

    @Override // p110z1.IUserKickCallback
    /* renamed from: a */
    public void mo12177a(SessionInfoResponse sessionInfoResponse) {
        LoginDialog apyVar = this.f11412t;
        if (apyVar != null) {
            apyVar.dismiss();
        }
        if (sessionInfoResponse != null) {
            this.f11398f = sessionInfoResponse;
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
            this.f11400h.setText("当前账号VIP运行开数上限：" + sessionInfoResponse.getOpenNum());
            this.f11399g.setText(" 已运行设备数:" + size);
            this.f11401i.setText("VIP设备（" + size + "）");
            this.f11402j.setText("金币设备（" + size2 + "）");
            if (size > sessionInfoResponse.getOpenNum()) {
                this.f11405m.setVisibility(0);
            }
        }
        this.f11401i.setEnabled(false);
        this.f11404l.setEnabled(true);
        this.f11401i.setTextColor(getResources().getColor(C2222R.color.white));
        this.f11402j.setTextColor(getResources().getColor(C2222R.color.black));
        this.f11403k.setTextColor(getResources().getColor(C2222R.color.black));
        m18352a(sessionInfoResponse.getUserSessions());
        this.f11410r.clear();
        this.f11411s.clear();
        if (sessionInfoResponse.getUserSessions() != null) {
            this.f11410r.addAll(sessionInfoResponse.getUserSessions());
        }
        if (sessionInfoResponse.getUserGoldSessions() != null) {
            this.f11411s.addAll(sessionInfoResponse.getUserGoldSessions());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m18352a(List<SessionInfo> list) {
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
        UserKickAdapter userKickAdapter = this.f11396d;
        if (userKickAdapter == null) {
            this.f11396d = new UserKickAdapter(new UserKickAdapter.AbstractC2004a() { // from class: com.nrzs.user.ui.activity.KickOutActivity.7
                @Override // com.nrzs.p067ft.adapter.UserKickAdapter.AbstractC2004a
                /* renamed from: a */
                public void mo12161a(long j, String str, int i) {
                    KickOutActivity.this.m18358a(j, str, i);
                }

                @Override // com.nrzs.p067ft.adapter.UserKickAdapter.AbstractC2004a
                /* renamed from: a */
                public void mo12160a(String str, long j, String str2, int i) {
                    KickOutActivity.this.m18353a(str, j, str2, i);
                }
            }, list);
            this.f11396d.m18905a(false);
            this.f11395c.setAdapter(this.f11396d);
            this.f11395c.setLayoutManager(new LinearLayoutManager(this));
            return;
        }
        userKickAdapter.f11255c.clear();
        this.f11396d.f11255c.addAll(list);
        this.f11396d.notifyDataSetChanged();
    }

    @Override // p110z1.IUserKickCallback
    /* renamed from: a */
    public void mo12181a(int i, String str) {
        UserKickAdapter userKickAdapter = this.f11396d;
        if (userKickAdapter != null) {
            userKickAdapter.m18912a(i, str);
        }
    }

    @Override // p110z1.IUserKickCallback
    /* renamed from: a */
    public void mo12180a(int i, boolean z) {
        UserKickAdapter userKickAdapter = this.f11396d;
        if (userKickAdapter != null) {
            userKickAdapter.m18904b(i);
        }
        if (z) {
            IntentToAssistService.m12813a(this);
            finish();
        } else if (this.f11413u) {
            Autologinmanager.m11654a(DataApp.m18939d().m18947a()).m11655a();
            finish();
        } else {
            m18359a(this.f11414v);
        }
    }

    /* renamed from: a */
    public void m18359a(long j) {
        try {
            SessionRequestInfo sessionRequestInfo = new SessionRequestInfo();
            if (j == -1) {
                j = LoginManager.m12620d().m12614j();
            }
            sessionRequestInfo.UserID = j;
            if (this.f11397e != null) {
                this.f11397e.m12556a(sessionRequestInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public void m18353a(String str, long j, String str2, int i) {
        UserKickModel alsVar = this.f11397e;
        if (alsVar != null) {
            alsVar.m12555a(str, j, str2, i);
        }
    }

    /* renamed from: a */
    public void m18358a(long j, String str, int i) {
        UserKickModel alsVar = this.f11397e;
        if (alsVar != null) {
            alsVar.m12557a(j, str, i, false);
        }
    }

    /* renamed from: b */
    public void m18351b(long j, String str, int i) {
        UserKickModel alsVar = this.f11397e;
        if (alsVar != null) {
            alsVar.m12557a(j, str, i, true);
        }
    }

    /* renamed from: com.nrzs.user.ui.activity.KickOutActivity$MyPagerAdapter */
    /* loaded from: classes2.dex */
    public class MyPagerAdapter extends PagerAdapter {

        /* renamed from: b */
        private List<View> f11424b;

        @Override // android.support.v4.view.PagerAdapter
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        public MyPagerAdapter(List<View> list) {
            this.f11424b = list;
        }

        @Override // android.support.v4.view.PagerAdapter
        public int getCount() {
            return this.f11424b.size();
        }

        @Override // android.support.v4.view.PagerAdapter
        public Object instantiateItem(ViewGroup viewGroup, int i) {
            viewGroup.addView(this.f11424b.get(i));
            return this.f11424b.get(i);
        }

        @Override // android.support.v4.view.PagerAdapter
        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
            viewGroup.removeView((View) obj);
        }
    }
}
