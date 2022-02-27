package com.nrzs.user.p071ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.cyjh.mobileanjian.ipc.utils.RootUtil;
import com.gyf.barlibrary.ImmersionBar;
import com.lody.virtual.client.ipc.ServiceManagerNative;
import com.nrzs.base.router.RouterConstants;
import com.nrzs.base.router.RouterUtils;
import com.nrzs.data.DataApp;
import com.nrzs.data.game.bean.RdataBean;
import com.nrzs.data.game.bean.TopicInfo;
import com.nrzs.data.other.bean.AdResultInfoItem;
import com.nrzs.data.other.bean.request.UploadLog;
import com.nrzs.user.C2222R;
import com.nrzs.user.p071ui.base.UserBaseActivity;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.apache.tools.ant.taskdefs.WaitFor;
import p110z1.ARouter;
import p110z1.ChooseEvent;
import p110z1.EventBus;
import p110z1.HttpVal;
import p110z1.IQuesttionCallback;
import p110z1.LoginManager;
import p110z1.NRZSFileConfig;
import p110z1.NRZSFileUtils;
import p110z1.ShareVal;
import p110z1.Subscribe;
import p110z1.ThreadMode;
import p110z1.acf;
import p110z1.aoy;
import p110z1.apf;
import p110z1.aqg;
import p110z1.aqh;

@Route(path = RouterConstants.ModuleUser.QUESTION)
/* renamed from: com.nrzs.user.ui.activity.QuestionActivity */
/* loaded from: classes2.dex */
public class QuestionActivity extends UserBaseActivity implements View.OnClickListener, IQuesttionCallback {

    /* renamed from: A */
    private TextView f11446A;

    /* renamed from: B */
    private TextView f11447B;

    /* renamed from: C */
    private TextView f11448C;

    /* renamed from: a */
    private TopicInfo f11449a;

    /* renamed from: b */
    private RdataBean f11450b;

    /* renamed from: c */
    private boolean f11451c;

    /* renamed from: d */
    private boolean f11452d;

    /* renamed from: e */
    private int f11453e = 5;

    /* renamed from: f */
    private boolean f11454f = false;

    /* renamed from: g */
    private LinearLayout f11455g;

    /* renamed from: h */
    private RelativeLayout f11456h;

    /* renamed from: i */
    private RelativeLayout f11457i;

    /* renamed from: j */
    private RelativeLayout f11458j;

    /* renamed from: k */
    private ImageView f11459k;

    /* renamed from: l */
    private TextView f11460l;

    /* renamed from: m */
    private TextView f11461m;

    /* renamed from: n */
    private TextView f11462n;

    /* renamed from: o */
    private TextView f11463o;

    /* renamed from: p */
    private TextView f11464p;

    /* renamed from: q */
    private TextView f11465q;

    /* renamed from: r */
    private TextView f11466r;

    /* renamed from: s */
    private TextView f11467s;

    /* renamed from: t */
    private EditText f11468t;

    /* renamed from: u */
    private TextView f11469u;

    /* renamed from: v */
    private CheckBox f11470v;

    /* renamed from: w */
    private ImageView f11471w;

    /* renamed from: x */
    private TextView f11472x;

    /* renamed from: y */
    private LinearLayout f11473y;

    /* renamed from: z */
    private LinearLayout f11474z;

    /* renamed from: a */
    public void m18327a(long j) {
    }

    @Override // p110z1.IQuesttionCallback
    /* renamed from: c */
    public void mo12559c() {
    }

    /* renamed from: a */
    public static void m18326a(Context context) {
        Intent intent = new Intent(context, QuestionActivity.class);
        intent.setFlags(337641472);
        context.startActivity(intent);
    }

    @Override // com.nrzs.libcommon.BaseActivity
    /* renamed from: a */
    protected int mo18307a() {
        return C2222R.layout.activity_question_layout;
    }

    @Override // com.nrzs.libcommon.BaseActivity
    /* renamed from: b */
    public void mo18303b() {
        ImmersionBar.m20080a(this).m20087a(C2222R.color.colorPrimary).m19974h(true).m19994f();
    }

    @Override // com.nrzs.libcommon.BaseActivity
    /* renamed from: a */
    public void mo18305a(Bundle bundle) {
        EventBus.m3448a().m3446a(this);
        this.f11455g = (LinearLayout) findViewById(C2222R.C2224id.questtion_type);
        this.f11456h = (RelativeLayout) findViewById(C2222R.C2224id.choose_quest_type_lay);
        this.f11458j = (RelativeLayout) findViewById(C2222R.C2224id.choose_sctit_lay);
        this.f11457i = (RelativeLayout) findViewById(C2222R.C2224id.choose_game_lay);
        this.f11459k = (ImageView) findViewById(C2222R.C2224id.img_type_question);
        this.f11471w = (ImageView) findViewById(C2222R.C2224id.back_img);
        this.f11472x = (TextView) findViewById(C2222R.C2224id.fqa_tv);
        this.f11465q = (TextView) findViewById(C2222R.C2224id.mqustion_type_tv);
        this.f11466r = (TextView) findViewById(C2222R.C2224id.choose_game_tv);
        this.f11467s = (TextView) findViewById(C2222R.C2224id.sctipt_tv);
        this.f11470v = (CheckBox) findViewById(C2222R.C2224id.mcheck);
        this.f11473y = (LinearLayout) findViewById(C2222R.C2224id.data_lay);
        this.f11474z = (LinearLayout) findViewById(C2222R.C2224id.submit_loading);
        this.f11446A = (TextView) findViewById(C2222R.C2224id.subumit_wait);
        this.f11447B = (TextView) findViewById(C2222R.C2224id.submit_succtss);
        this.f11448C = (TextView) findViewById(C2222R.C2224id.subit_success_text_btn);
        this.f11468t = (EditText) findViewById(C2222R.C2224id.exit_content);
        this.f11469u = (TextView) findViewById(C2222R.C2224id.submit_btn);
        this.f11460l = (TextView) findViewById(C2222R.C2224id.cz_question);
        this.f11461m = (TextView) findViewById(C2222R.C2224id.script_question);
        this.f11462n = (TextView) findViewById(C2222R.C2224id.rj_question);
        this.f11463o = (TextView) findViewById(C2222R.C2224id.yj_question);
        this.f11464p = (TextView) findViewById(C2222R.C2224id.other_question);
    }

    @Override // com.nrzs.libcommon.BaseActivity
    /* renamed from: b */
    public void mo18302b(Bundle bundle) {
        try {
            ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override // com.nrzs.user.p071ui.base.UserBaseActivity, android.support.p006v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        EventBus.m3448a().m3430c(this);
    }

    @Subscribe(m3389a = ThreadMode.MAIN)
    /* renamed from: a */
    public void m18318a(ChooseEvent.C3858a aVar) {
        this.f11449a = aVar.f17286a;
        this.f11466r.setText(this.f11449a.TopicName);
        m18313e();
    }

    @Subscribe(m3389a = ThreadMode.MAIN)
    /* renamed from: a */
    public void m18317a(ChooseEvent.C3859b bVar) {
        this.f11450b = bVar.f17287a;
        this.f11467s.setText(this.f11450b.getScriptName());
        m18313e();
    }

    @Override // com.nrzs.libcommon.BaseActivity
    /* renamed from: d */
    public void mo18300d() {
        this.f11472x.setOnClickListener(this);
        this.f11460l.setOnClickListener(this);
        this.f11461m.setOnClickListener(this);
        this.f11462n.setOnClickListener(this);
        this.f11463o.setOnClickListener(this);
        this.f11464p.setOnClickListener(this);
        this.f11471w.setOnClickListener(this);
        this.f11456h.setOnClickListener(this);
        this.f11448C.setOnClickListener(this);
        this.f11457i.setOnClickListener(this);
        this.f11458j.setOnClickListener(this);
        this.f11469u.setOnClickListener(this);
        this.f11470v.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.nrzs.user.ui.activity.QuestionActivity.1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (z) {
                    QuestionActivity.this.f11452d = true;
                } else {
                    QuestionActivity.this.f11452d = false;
                }
            }
        });
    }

    /* renamed from: e */
    private void m18313e() {
        this.f11457i.setVisibility(8);
        this.f11458j.setVisibility(8);
        int i = this.f11453e;
        if (i == 1) {
            if (this.f11449a == null || this.f11450b == null) {
                this.f11451c = false;
                this.f11469u.setBackground(getResources().getDrawable(C2222R.C2223drawable.bird_btn_tool_no_data_gray));
            } else {
                this.f11451c = true;
                this.f11469u.setBackground(getResources().getDrawable(C2222R.C2223drawable.bird_btn_tool_no_data));
            }
        } else if (i == 5) {
            this.f11451c = false;
            this.f11469u.setBackground(getResources().getDrawable(C2222R.C2223drawable.bird_btn_tool_no_data_gray));
        } else {
            this.f11451c = true;
            this.f11469u.setBackground(getResources().getDrawable(C2222R.C2223drawable.bird_btn_tool_no_data));
        }
        int i2 = this.f11453e;
        if (i2 == 0) {
            this.f11465q.setText("充值问题");
        } else if (i2 == 1) {
            this.f11465q.setText("脚本问题");
            this.f11457i.setVisibility(0);
            this.f11458j.setVisibility(0);
        } else if (i2 == 2) {
            this.f11465q.setText("软件问题");
        } else if (i2 == 3) {
            this.f11465q.setText("意见建议");
        } else if (i2 == 4) {
            this.f11465q.setText("其他");
        }
        if (this.f11454f) {
            this.f11455g.setVisibility(0);
        } else {
            this.f11455g.setVisibility(8);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == this.f11460l.getId()) {
            this.f11453e = 0;
            this.f11454f = false;
            m18313e();
        } else if (id == this.f11461m.getId()) {
            this.f11453e = 1;
            this.f11454f = false;
            m18313e();
        } else if (id == this.f11462n.getId()) {
            this.f11453e = 2;
            this.f11454f = false;
            m18313e();
        } else if (id == this.f11463o.getId()) {
            this.f11453e = 3;
            this.f11454f = false;
            m18313e();
        } else if (id == this.f11464p.getId()) {
            this.f11453e = 4;
            this.f11454f = false;
            m18313e();
        } else if (id == this.f11456h.getId()) {
            if (this.f11454f) {
                this.f11454f = false;
                m18313e();
                return;
            }
            this.f11454f = true;
            m18313e();
        } else if (id == this.f11457i.getId()) {
            ARouter.m1714a().m1707a(RouterConstants.ModuleUser.CHOOSE_GAME).navigation();
        } else if (id == this.f11458j.getId()) {
            TopicInfo topicInfo = this.f11449a;
            if (topicInfo == null) {
                aqg.m11586a(this, "请先选择游戏");
            } else {
                RouterUtils.toChooseScript((int) topicInfo.TopicID);
            }
        } else if (id == this.f11471w.getId()) {
            finish();
        } else if (id == this.f11472x.getId()) {
            String b = apf.m11837b(DataApp.m18939d().m18947a(), ShareVal.f16591a, ShareVal.f16583N, "");
            AdResultInfoItem adResultInfoItem = new AdResultInfoItem();
            adResultInfoItem.Title = "FAQ";
            adResultInfoItem.ExecArgs = b;
            RouterUtils.toMainWeb(0, 1, adResultInfoItem);
        } else if (id == this.f11469u.getId()) {
            if (!this.f11451c) {
                return;
            }
            if (TextUtils.isEmpty(this.f11468t.getText())) {
                aqg.m11586a(this, "请您先填写问题详情");
                return;
            }
            this.f11473y.setVisibility(8);
            this.f11474z.setVisibility(0);
            if (this.f11452d) {
                try {
                    m18322a(NRZSFileConfig.f16554l);
                    m18322a(NRZSFileConfig.f16555m);
                    m18322a(NRZSFileConfig.f16548f);
                    aqh.m11578a(NRZSFileConfig.f16556n, NRZSFileConfig.f16557o);
                    m18320a(NRZSFileConfig.f16557o + "/allupload.zip", this.f11452d);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                m18320a(NRZSFileConfig.f16557o, this.f11452d);
            }
        } else if (id == this.f11448C.getId()) {
            finish();
        }
    }

    /* renamed from: a */
    private void m18322a(String str) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        String format = simpleDateFormat.format(new Date(System.currentTimeMillis()));
        if (!TextUtils.isEmpty(str)) {
            try {
                File[] listFiles = new File(str).listFiles();
                if (listFiles != null && listFiles.length > 0) {
                    for (int i = 0; i < listFiles.length; i++) {
                        if (m18319a(simpleDateFormat.parse(NRZSFileUtils.m11628a(listFiles[i])), simpleDateFormat.parse(format)) <= 3) {
                            Log.e("上传", listFiles[i].getName() + "原路径" + listFiles[i].getAbsolutePath() + "新路径" + NRZSFileConfig.f16556n);
                            NRZSFileUtils.m11618a(listFiles[i].getName(), listFiles[i].getPath(), NRZSFileConfig.f16556n);
                        }
                    }
                }
            } catch (Exception unused) {
            }
        }
    }

    /* renamed from: a */
    public long m18319a(Date date, Date date2) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        Calendar instance2 = Calendar.getInstance();
        instance2.setTime(date2);
        return (instance2.getTimeInMillis() - instance.getTimeInMillis()) / WaitFor.ONE_DAY;
    }

    /* renamed from: a */
    public void m18320a(final String str, final boolean z) {
        Log.e("datalal", "调用");
        new Thread(new Runnable() { // from class: com.nrzs.user.ui.activity.QuestionActivity.2
            @Override // java.lang.Runnable
            public void run() {
                Log.e("datalal", "走run");
                if (LoginManager.m12620d().m12606r()) {
                    Log.e("datalal", "开始上传");
                    try {
                        UploadLog uploadLog = new UploadLog();
                        uploadLog.UserID = LoginManager.m12620d().m12614j();
                        uploadLog.ProblemType = QuestionActivity.this.f11465q.getText().toString().trim();
                        uploadLog.ProblemDetails = QuestionActivity.this.f11468t.getText().toString().trim();
                        uploadLog.MobileType = Build.MODEL;
                        uploadLog.MobileBrand = Build.MANUFACTURER.toLowerCase();
                        uploadLog.IsROOT = RootUtil.isRoot() ? 1 : 2;
                        uploadLog.SysVersion = Build.VERSION.SDK + "," + Build.VERSION.RELEASE;
                        if (QuestionActivity.this.f11450b != null) {
                            uploadLog.GameName = QuestionActivity.this.f11466r.getText().toString().trim();
                            uploadLog.OnlyID = QuestionActivity.this.f11450b.getOnlyID();
                            uploadLog.ScriptName = QuestionActivity.this.f11450b.getScriptName();
                        } else {
                            uploadLog.GameName = "";
                            uploadLog.OnlyID = "";
                            uploadLog.ScriptName = "";
                        }
                        QuestionActivity.this.m18321a(HttpVal.f16510Z, str, uploadLog, z);
                    } catch (Exception e) {
                        aqg.m11586a(QuestionActivity.this, "提交失败");
                        QuestionActivity.this.f11473y.setVisibility(0);
                        QuestionActivity.this.f11474z.setVisibility(8);
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m18321a(String str, String str2, UploadLog uploadLog, boolean z) throws Exception {
        File file;
        String str3;
        MultipartBody multipartBody;
        new HashMap();
        List<String> sigin = uploadLog.getSigin(str, uploadLog.getNoencodeMapProperty());
        if (sigin != null && sigin.size() >= 2) {
            String str4 = sigin.get(0);
            String str5 = sigin.get(1);
            if (str2.equals("")) {
                file = null;
                str3 = "没有找到安装包";
            } else {
                File file2 = new File(str2);
                str3 = file2.getName();
                file = file2;
            }
            Log.e("datalal", "进来了");
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            if (!z || file == null) {
                MultipartBody.Builder addFormDataPart = new MultipartBody.Builder().setType(MultipartBody.FORM).addFormDataPart("UserId", String.valueOf(LoginManager.m12620d().m12614j()));
                MultipartBody.Builder addFormDataPart2 = addFormDataPart.addFormDataPart("GameName", uploadLog.GameName + "").addFormDataPart("ProblemType", uploadLog.ProblemType).addFormDataPart("ProblemDetails", uploadLog.ProblemDetails).addFormDataPart("MobileType", uploadLog.MobileType).addFormDataPart("MobileBrand", uploadLog.MobileBrand).addFormDataPart("IsROOT", String.valueOf(uploadLog.IsROOT)).addFormDataPart("SysVersion", uploadLog.SysVersion).addFormDataPart("OnlyID", uploadLog.OnlyID).addFormDataPart("ScriptName", uploadLog.ScriptName).addFormDataPart("a", uploadLog.f10610a).addFormDataPart("b", uploadLog.f10614b).addFormDataPart("ab", uploadLog.f10612ab).addFormDataPart("bc", uploadLog.f10615bc);
                MultipartBody.Builder addFormDataPart3 = addFormDataPart2.addFormDataPart("d", uploadLog.f10616d + "");
                MultipartBody.Builder addFormDataPart4 = addFormDataPart3.addFormDataPart("de", uploadLog.f10617de + "").addFormDataPart(ServiceManagerNative.f10497VS, uploadLog.f10621vs);
                MultipartBody.Builder addFormDataPart5 = addFormDataPart4.addFormDataPart("vc", uploadLog.f10620vc + "");
                MultipartBody.Builder addFormDataPart6 = addFormDataPart5.addFormDataPart("pg", uploadLog.f10618pg + "").addFormDataPart("pv", uploadLog.f10619pv).addFormDataPart("ad", uploadLog.f10613ad);
                MultipartBody.Builder addFormDataPart7 = addFormDataPart6.addFormDataPart("aa", uploadLog.f10611aa + "");
                multipartBody = addFormDataPart7.addFormDataPart("isVa", uploadLog.isVa + "").addFormDataPart("R", str4).addFormDataPart("Sign", str5).build();
            } else {
                MultipartBody.Builder addFormDataPart8 = new MultipartBody.Builder().setType(MultipartBody.FORM).addFormDataPart("UserId", String.valueOf(LoginManager.m12620d().m12614j()));
                MultipartBody.Builder addFormDataPart9 = addFormDataPart8.addFormDataPart("GameName", uploadLog.GameName + "").addFormDataPart("ProblemType", uploadLog.ProblemType).addFormDataPart("ProblemDetails", uploadLog.ProblemDetails).addFormDataPart("MobileType", uploadLog.MobileType).addFormDataPart("MobileBrand", uploadLog.MobileBrand).addFormDataPart("IsROOT", String.valueOf(uploadLog.IsROOT)).addFormDataPart("SysVersion", uploadLog.SysVersion).addFormDataPart("OnlyID", uploadLog.OnlyID).addFormDataPart("ScriptName", uploadLog.ScriptName).addFormDataPart("a", uploadLog.f10610a).addFormDataPart("b", uploadLog.f10614b).addFormDataPart("ab", uploadLog.f10612ab).addFormDataPart("bc", uploadLog.f10615bc);
                MultipartBody.Builder addFormDataPart10 = addFormDataPart9.addFormDataPart("d", uploadLog.f10616d + "");
                MultipartBody.Builder addFormDataPart11 = addFormDataPart10.addFormDataPart("de", uploadLog.f10617de + "").addFormDataPart(ServiceManagerNative.f10497VS, uploadLog.f10621vs);
                MultipartBody.Builder addFormDataPart12 = addFormDataPart11.addFormDataPart("vc", uploadLog.f10620vc + "");
                MultipartBody.Builder addFormDataPart13 = addFormDataPart12.addFormDataPart("pg", uploadLog.f10618pg + "").addFormDataPart("pv", uploadLog.f10619pv).addFormDataPart("ad", uploadLog.f10613ad);
                MultipartBody.Builder addFormDataPart14 = addFormDataPart13.addFormDataPart("aa", uploadLog.f10611aa + "");
                multipartBody = addFormDataPart14.addFormDataPart("isVa", uploadLog.isVa + "").addFormDataPart("R", str4).addFormDataPart("Sign", str5).addFormDataPart("file", str3, RequestBody.create(MediaType.parse("multipart/form-data"), file)).build();
            }
            Request.Builder builder2 = new Request.Builder();
            Request build = builder2.header("Authorization", "Client-ID " + UUID.randomUUID()).url(str).post(multipartBody).build();
            OkHttpClient build2 = builder.connectTimeout(acf.f15175N, TimeUnit.SECONDS).writeTimeout(acf.f15175N, TimeUnit.SECONDS).readTimeout(acf.f15175N, TimeUnit.SECONDS).build();
            Log.e("上传", "开始enqueue");
            build2.newCall(build).enqueue(new Callback() { // from class: com.nrzs.user.ui.activity.QuestionActivity.3
                @Override // okhttp3.Callback
                public void onFailure(Call call, IOException iOException) {
                    Log.e("上传", "失败");
                    aqg.m11586a(QuestionActivity.this, "提交失败");
                    QuestionActivity.this.runOnUiThread(new Runnable() { // from class: com.nrzs.user.ui.activity.QuestionActivity.3.1
                        @Override // java.lang.Runnable
                        public void run() {
                            QuestionActivity.this.f11473y.setVisibility(0);
                            QuestionActivity.this.f11474z.setVisibility(8);
                        }
                    });
                }

                @Override // okhttp3.Callback
                public void onResponse(Call call, Response response) throws IOException {
                    Log.e("上传", "成功");
                    QuestionActivity.this.runOnUiThread(new Runnable() { // from class: com.nrzs.user.ui.activity.QuestionActivity.3.2
                        @Override // java.lang.Runnable
                        public void run() {
                            aoy.m11886a(NRZSFileConfig.f16556n);
                            QuestionActivity.this.f11473y.setVisibility(8);
                            QuestionActivity.this.f11474z.setVisibility(0);
                            QuestionActivity.this.f11446A.setVisibility(8);
                            QuestionActivity.this.f11447B.setVisibility(0);
                            QuestionActivity.this.f11448C.setVisibility(0);
                        }
                    });
                }
            });
        }
    }
}
