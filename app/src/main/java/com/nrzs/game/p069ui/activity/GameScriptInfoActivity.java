package com.nrzs.game.p069ui.activity;

import android.arch.lifecycle.Observer;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.RelativeSizeSpan;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alipay.sdk.widget.C0675j;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.Utils;
import com.gyf.barlibrary.ImmersionBar;
import com.nrzs.base.router.RouterConstants;
import com.nrzs.base.router.RouterUtils;
import com.nrzs.base.router.provider.CarkeyProvider;
import com.nrzs.base.router.provider.PayProvider;
import com.nrzs.base.router.provider.ProviderFactory;
import com.nrzs.data.game.bean.TopicInfo;
import com.nrzs.data.game.bean.TwitterInfo;
import com.nrzs.data.game.bean.response.ScriptInfoResponse;
import com.nrzs.data.other.bean.AdResultInfoItem;
import com.nrzs.game.C2029R;
import com.nrzs.game.model.GameTopicModel;
import com.nrzs.game.p069ui.base.GameBaseActivity;
import p110z1.ActionVal;
import p110z1.BirMoneyEvent;
import p110z1.CKU;
import p110z1.ChannelDataManager;
import p110z1.Consts;
import p110z1.DoneCallback;
import p110z1.EventBus;
import p110z1.EventCollectManager;
import p110z1.EventConstants;
import p110z1.FailCallback;
import p110z1.GlideImageUtils;
import p110z1.GlobalConstants;
import p110z1.HttpVal;
import p110z1.ILoadData;
import p110z1.IntentToAssistService;
import p110z1.LocalLoadHelper;
import p110z1.LoginManager;
import p110z1.OrcDownManager;
import p110z1.OrcDownlDialog;
import p110z1.PreSetListManager;
import p110z1.ShareVal;
import p110z1.Subscribe;
import p110z1.ThreadMode;
import p110z1.TopicInfoManager;
import p110z1.XnkjUtils;
import p110z1.apf;
import p110z1.apn;

@Route(path = RouterConstants.ModuleGame.SCRIPT_INFO)
/* renamed from: com.nrzs.game.ui.activity.GameScriptInfoActivity */
/* loaded from: classes2.dex */
public class GameScriptInfoActivity extends GameBaseActivity {

    /* renamed from: a */
    public static String f10966a = "script_id_key";

    /* renamed from: b */
    private ImageView f10967b;

    /* renamed from: c */
    private TextView f10968c;

    /* renamed from: d */
    private TextView f10969d;

    /* renamed from: e */
    private TextView f10970e;

    /* renamed from: f */
    private TextView f10971f;

    /* renamed from: g */
    private TextView f10972g;

    /* renamed from: h */
    private ImageView f10973h;

    /* renamed from: i */
    private TextView f10974i;

    /* renamed from: j */
    private TextView f10975j;

    /* renamed from: k */
    private TextView f10976k;

    /* renamed from: l */
    private TextView f10977l;

    /* renamed from: m */
    private TextView f10978m;

    /* renamed from: n */
    private TextView f10979n;

    /* renamed from: o */
    private apn f10980o;

    /* renamed from: p */
    private TextView f10981p;

    /* renamed from: q */
    private LinearLayout f10982q;

    /* renamed from: s */
    private TwitterInfo f10984s;

    /* renamed from: r */
    private long f10983r = -1;

    /* renamed from: t */
    private Observer<ScriptInfoResponse> f10985t = new Observer<ScriptInfoResponse>() { // from class: com.nrzs.game.ui.activity.GameScriptInfoActivity.1
        /* renamed from: a */
        public void onChanged(@Nullable final ScriptInfoResponse scriptInfoResponse) {
            if (scriptInfoResponse == null) {
                GameScriptInfoActivity.this.f10980o.mo11681l_();
                return;
            }
            GameScriptInfoActivity.this.f10984s = scriptInfoResponse.getTwitterInfo();
            if (GameScriptInfoActivity.this.f10984s == null) {
                GameScriptInfoActivity.this.f10980o.mo11681l_();
                return;
            }
            GameScriptInfoActivity.this.f10980o.mo11679n_();
            GameScriptInfoActivity.this.f10974i.setText(GameScriptInfoActivity.this.f10984s.getScriptName());
            GameScriptInfoActivity.this.f10977l.setText(GameScriptInfoActivity.this.f10984s.getScriptDesc());
            if (GameScriptInfoActivity.this.f10984s.isToolVip()) {
                TextView textView = GameScriptInfoActivity.this.f10971f;
                textView.setText(GameScriptInfoActivity.this.f10984s.getGold() + "金币/天");
                GameScriptInfoActivity.this.f10971f.setVisibility(0);
            } else {
                GameScriptInfoActivity.this.f10971f.setVisibility(4);
            }
            GameScriptInfoActivity.this.f10978m.setText(TextUtils.isEmpty(GameScriptInfoActivity.this.f10984s.getNickName()) ? "辅助大神" : GameScriptInfoActivity.this.f10984s.getNickName());
            GameScriptInfoActivity.this.f10975j.setText(GameScriptInfoActivity.this.f10984s.getReleaseDateStr());
            TextView textView2 = GameScriptInfoActivity.this.f10976k;
            textView2.setText(Html.fromHtml("<font color=\"#FF9100\">" + GameScriptInfoActivity.this.f10984s.getAuthorRewardSGBTotalNumStr() + "</font>次打赏"));
            GameScriptInfoActivity.this.f10970e.setText(TextUtils.isEmpty(GameScriptInfoActivity.this.f10984s.getAuthorPersonalInfo()) ? GameScriptInfoActivity.this.getResources().getString(C2029R.string.bird_tool_reward_word_none) : GameScriptInfoActivity.this.f10984s.getAuthorPersonalInfo());
            GlideImageUtils.m11880a(GameScriptInfoActivity.this.f10973h, GameScriptInfoActivity.this.getBaseContext(), C2029R.C2030drawable.bird_bg_common_img, GameScriptInfoActivity.this.f10984s.getScriptIco());
            if (scriptInfoResponse.getPlatformVIPInfo() == null) {
                GameScriptInfoActivity.this.f10981p.setText(Html.fromHtml("畅享全平台辅助，约<font color='#FFF700'>0</font>元/月"));
            } else {
                GameScriptInfoActivity.this.f10981p.setText(Html.fromHtml("畅享全平台辅助，约<font color='#FFF700'>" + scriptInfoResponse.getPlatformVIPInfo().getPrice() + "</font>元/月"));
            }
            GameScriptInfoActivity.this.f10982q.setOnClickListener(new View.OnClickListener() { // from class: com.nrzs.game.ui.activity.GameScriptInfoActivity.1.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    try {
                        if (!LoginManager.m12620d().m12606r()) {
                            IntentToAssistService.m12813a(GameScriptInfoActivity.this.getBaseContext());
                        } else if (scriptInfoResponse.getPlatformVIPInfo() != null) {
                            if (ChannelDataManager.m12655a().m12650d()) {
                                ProviderFactory.createCarkey().showdialog(GameScriptInfoActivity.this);
                                return;
                            }
                            EventCollectManager.m12642a().m12640a(GameScriptInfoActivity.this, "脚本详情全平台", "脚本详情全平台", EventConstants.f16452t);
                            CarkeyProvider createCarkey = ProviderFactory.createCarkey();
                            if (!PreSetListManager.m12116a().m12110f() || createCarkey == null) {
                                Intent intent = new Intent(ActionVal.f16466c);
                                intent.addFlags(268435456);
                                AdResultInfoItem adResultInfoItem = new AdResultInfoItem();
                                adResultInfoItem.Title = "购买VIP";
                                adResultInfoItem.ExecArgs = HttpVal.f16516c;
                                intent.putExtra(GlobalConstants.f16482e, 0);
                                intent.putExtra(GlobalConstants.f16481d, adResultInfoItem);
                                GameScriptInfoActivity.this.getBaseContext().startActivity(intent);
                                return;
                            }
                            createCarkey.showdialog(GameScriptInfoActivity.this);
                        }
                    } catch (ActivityNotFoundException unused) {
                        Log.d("FirstActivity.this", "找不到该活动");
                    }
                }
            });
        }
    };

    /* renamed from: a */
    public static void m18697a(Context context, long j, String str) {
        Intent intent = new Intent(context, GameScriptInfoActivity.class);
        intent.setFlags(268435456);
        intent.putExtra("topicId", j);
        intent.putExtra(f10966a, str);
        context.startActivity(intent);
    }

    @Override // com.nrzs.libcommon.BaseActivity
    /* renamed from: a */
    protected int mo18307a() {
        return C2029R.layout.nrzs_game_script_detail;
    }

    @Override // com.nrzs.libcommon.BaseActivity
    /* renamed from: b */
    public void mo18303b() {
        ImmersionBar.m20080a(this).m20087a(C2029R.color.colorPrimary).m19974h(true).m19994f();
    }

    @Override // com.nrzs.libcommon.BaseActivity
    /* renamed from: a */
    public void mo18305a(Bundle bundle) {
        this.f10967b = (ImageView) findViewById(C2029R.C2031id.btn_back);
        this.f10968c = (TextView) findViewById(C2029R.C2031id.text_title);
        this.f10973h = (ImageView) findViewById(C2029R.C2031id.iv_scrpit_icon);
        this.f10973h.setImageResource(C2029R.C2030drawable.ic_launcher);
        this.f10974i = (TextView) findViewById(C2029R.C2031id.text_script_title);
        this.f10975j = (TextView) findViewById(C2029R.C2031id.text_script_release_date);
        this.f10976k = (TextView) findViewById(C2029R.C2031id.text_script_score);
        this.f10976k.setText(m18691a("0.0分"));
        this.f10977l = (TextView) findViewById(C2029R.C2031id.text_script_content);
        this.f10981p = (TextView) findViewById(C2029R.C2031id.text_buy_vip_content);
        this.f10978m = (TextView) findViewById(C2029R.C2031id.tv_author_name);
        this.f10982q = (LinearLayout) findViewById(C2029R.C2031id.btn_buy_vip);
        this.f10969d = (TextView) findViewById(C2029R.C2031id.tv_tool_reward);
        this.f10970e = (TextView) findViewById(C2029R.C2031id.tv_tool_info_content);
        this.f10971f = (TextView) findViewById(C2029R.C2031id.tv_script_gold);
        this.f10972g = (TextView) findViewById(C2029R.C2031id.tv_recharge_gold);
        this.f10979n = (TextView) findViewById(C2029R.C2031id.btn_run);
        this.f10980o = new apn(new LocalLoadHelper(getApplicationContext(), (LinearLayout) findViewById(C2029R.C2031id.ll_script_detail), new View.OnClickListener() { // from class: com.nrzs.game.ui.activity.GameScriptInfoActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                GameScriptInfoActivity gameScriptInfoActivity = GameScriptInfoActivity.this;
                gameScriptInfoActivity.m18688b(gameScriptInfoActivity.getIntent().getStringExtra(GameScriptInfoActivity.f10966a));
            }
        }), new ILoadData() { // from class: com.nrzs.game.ui.activity.GameScriptInfoActivity.3
            @Override // p110z1.ILoadData
            /* renamed from: a */
            public void mo11683a(int i) {
            }
        });
    }

    @Override // com.nrzs.libcommon.BaseActivity
    /* renamed from: b */
    public void mo18302b(Bundle bundle) {
        EventBus.m3448a().m3446a(this);
        this.f10983r = getIntent().getLongExtra("topicId", -1L);
        m18688b(getIntent().getStringExtra(f10966a));
        String stringExtra = getIntent().getStringExtra(C0675j.f373k);
        TextView textView = this.f10968c;
        if (TextUtils.isEmpty(stringExtra)) {
            stringExtra = "";
        }
        textView.setText(stringExtra);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m18696a(final View view) {
        EventCollectManager.m12642a().m12640a(this, "脚本详情运行按钮", String.valueOf(this.f10983r), EventConstants.f16442j);
        TopicInfoManager.m12726c().m12735a(this.f10983r, new FailCallback<Throwable>() { // from class: com.nrzs.game.ui.activity.GameScriptInfoActivity.4
            /* renamed from: a */
            public void onFail(Throwable th) {
                LogUtils.m23734c("TopicList", "result:" + th.getMessage());
            }
        }, new DoneCallback<TopicInfo>() { // from class: com.nrzs.game.ui.activity.GameScriptInfoActivity.5
            /* renamed from: a */
            public void onDone(final TopicInfo topicInfo) {
                if (GameScriptInfoActivity.this.f10984s != null) {
                    boolean b = apf.m11836b((Context) Utils.m24103a(), ShareVal.f16591a, ShareVal.f16615y, false);
                    if (topicInfo.SportBackGround != 1 || b || XnkjUtils.m12528a()) {
                        new CKU().m13032a(view.getContext(), view.getContext().getClass(), topicInfo, CKU.m13035a(GameScriptInfoActivity.this.f10984s.getScriptID(), GameScriptInfoActivity.this.f10984s.getOnlyID(), GameScriptInfoActivity.this.f10984s.getScriptName(), GameScriptInfoActivity.this.f10984s.getScriptAuthor()));
                    } else {
                        ProviderFactory.createXNKJRun().showdialog(GameScriptInfoActivity.this, new DialogInterface.OnClickListener() { // from class: com.nrzs.game.ui.activity.GameScriptInfoActivity.5.1
                            @Override // android.content.DialogInterface.OnClickListener
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                                if (i == 0) {
                                    new CKU().m13032a(view.getContext(), view.getContext().getClass(), topicInfo, CKU.m13035a(GameScriptInfoActivity.this.f10984s.getScriptID(), GameScriptInfoActivity.this.f10984s.getOnlyID(), GameScriptInfoActivity.this.f10984s.getScriptName(), GameScriptInfoActivity.this.f10984s.getScriptAuthor()));
                                } else {
                                    RouterUtils.toMain(1);
                                }
                            }
                        });
                    }
                }
            }
        });
    }

    @Override // com.nrzs.libcommon.BaseActivity
    /* renamed from: d */
    public void mo18300d() {
        this.f10967b.setOnClickListener(new View.OnClickListener() { // from class: com.nrzs.game.ui.activity.GameScriptInfoActivity.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                GameScriptInfoActivity.this.finish();
            }
        });
        this.f10979n.setOnClickListener(new View.OnClickListener() { // from class: com.nrzs.game.ui.activity.GameScriptInfoActivity.7
            @Override // android.view.View.OnClickListener
            public void onClick(final View view) {
                if (!OrcDownManager.m12866a().m12864b() || GameScriptInfoActivity.this.f10984s.getUseOcrText() != 1) {
                    GameScriptInfoActivity.this.m18696a(view);
                } else {
                    new OrcDownlDialog(GameScriptInfoActivity.this, new OrcDownlDialog.AbstractC3549a() { // from class: com.nrzs.game.ui.activity.GameScriptInfoActivity.7.1
                        @Override // p110z1.OrcDownlDialog.AbstractC3549a
                        /* renamed from: a */
                        public void mo12817a() {
                            GameScriptInfoActivity.this.m18696a(view);
                        }

                        @Override // p110z1.OrcDownlDialog.AbstractC3549a
                        /* renamed from: b */
                        public void mo12816b() {
                            Toast.makeText(view.getContext(), "下载文字识别资源失败，请重试", 1).show();
                        }
                    }).show();
                }
            }
        });
        this.f10969d.setOnClickListener(new View.OnClickListener() { // from class: com.nrzs.game.ui.activity.GameScriptInfoActivity.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (!LoginManager.m12620d().m12606r()) {
                    RouterUtils.toLogin(1, 0);
                } else if (GameScriptInfoActivity.this.f10984s != null) {
                    RouterUtils.toReward(GameScriptInfoActivity.this.f10984s.getScriptAuthor());
                }
            }
        });
        this.f10972g.setOnClickListener(new View.OnClickListener() { // from class: com.nrzs.game.ui.activity.GameScriptInfoActivity.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (ChannelDataManager.m12655a().m12650d()) {
                    ProviderFactory.createCarkey().showdialog(GameScriptInfoActivity.this);
                    return;
                }
                PayProvider createPay = ProviderFactory.createPay();
                EventCollectManager.m12642a().m12640a(GameScriptInfoActivity.this, "脚本详情页充值金币", "脚本详情页充值金币", EventConstants.f16420X);
                if (createPay != null) {
                    createPay.openPay(GameScriptInfoActivity.this, 0, 1, 2);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public void m18688b(String str) {
        this.f10980o.mo11682k_();
        GameTopicModel gameTopicModel = new GameTopicModel(getApplication());
        gameTopicModel.m18804b().observe(this, this.f10985t);
        gameTopicModel.m18805a(str);
    }

    /* renamed from: a */
    public SpannableString m18691a(String str) {
        SpannableString spannableString = new SpannableString(str);
        if (str.contains(Consts.f23430h)) {
            spannableString.setSpan(new RelativeSizeSpan(1.3f), 0, str.indexOf(Consts.f23430h), 33);
        }
        return spannableString;
    }

    @Subscribe(m3389a = ThreadMode.MAIN)
    /* renamed from: a */
    public void m18690a(BirMoneyEvent.C3551a aVar) {
        m18688b(getIntent().getStringExtra(f10966a));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.nrzs.game.p069ui.base.GameBaseActivity, android.support.p006v7.app.AppCompatActivity, android.support.p003v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        EventBus.m3448a().m3430c(this);
    }
}
