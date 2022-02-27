package com.nrzs.game.ui.view;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.nrzs.base.router.provider.PayProvider;
import com.nrzs.base.router.provider.ProviderFactory;
import com.nrzs.data.game.bean.response.TopicDetailResponseInfo;
import com.nrzs.data.other.bean.AdResultInfoItem;
import com.nrzs.game.C2029R;
import java.util.List;
import p110z1.ActionVal;
import p110z1.BannerManager;
import p110z1.ChannelDataManager;
import p110z1.DoneCallback;
import p110z1.EventCollectManager;
import p110z1.EventConstants;
import p110z1.GlideImageUtils;
import p110z1.GlobalConstants;
import p110z1.HttpVal;
import p110z1.IntentToAssistService;
import p110z1.LoginManager;

/* renamed from: com.nrzs.game.ui.view.GameTopicHeadView */
/* loaded from: classes2.dex */
public class GameTopicHeadView extends LinearLayout {

    /* renamed from: a */
    private ImageView f11102a;

    /* renamed from: b */
    private TextView f11103b;

    /* renamed from: c */
    private TextView f11104c;

    /* renamed from: d */
    private ImageView f11105d;

    /* renamed from: e */
    private RelativeLayout f11106e;

    /* renamed from: f */
    private RelativeLayout f11107f;

    /* renamed from: g */
    private TextView f11108g;

    /* renamed from: h */
    private TextView f11109h;

    /* renamed from: i */
    private TextView f11110i;

    /* renamed from: j */
    private TextView f11111j;

    /* renamed from: a */
    private void m18597a(Context context) {
    }

    public GameTopicHeadView(Context context) {
        super(context);
        m18594c(context);
        m18595b(context);
        m18597a(context);
    }

    /* renamed from: b */
    private void m18595b(final Context context) {
        BannerManager.m12679a().m12665c(new DoneCallback<List<AdResultInfoItem>>() { // from class: com.nrzs.game.ui.view.GameTopicHeadView.1
            /* renamed from: a */
            public void onDone(final List<AdResultInfoItem> list) {
                if (list == null || list.size() <= 0) {
                    GameTopicHeadView.this.f11105d.setVisibility(8);
                    return;
                }
                GameTopicHeadView.this.f11105d.setVisibility(0);
                Glide.with(GameTopicHeadView.this.getContext()).load(list.get(0).ImgUrl).apply((BaseRequestOptions<?>) new RequestOptions().placeholder(C2029R.C2030drawable.nrzs_bird_bg_common)).into(GameTopicHeadView.this.f11105d);
                GameTopicHeadView.this.f11105d.setOnClickListener(new View.OnClickListener() { // from class: com.nrzs.game.ui.view.GameTopicHeadView.1.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        try {
                            if (!TextUtils.isEmpty(((AdResultInfoItem) list.get(0)).ExecArgs)) {
                                Intent intent = new Intent(ActionVal.f16466c);
                                intent.addFlags(268435456);
                                intent.putExtra(GlobalConstants.f16482e, 99);
                                intent.putExtra(GlobalConstants.f16481d, (Parcelable) list.get(0));
                                context.startActivity(intent);
                            }
                        } catch (ActivityNotFoundException unused) {
                            Log.d("FirstActivity.this", "找不到该活动");
                        }
                    }
                });
            }
        });
        this.f11111j.setOnClickListener(new View.OnClickListener() { // from class: com.nrzs.game.ui.view.GameTopicHeadView.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (ChannelDataManager.m12655a().m12650d()) {
                    ProviderFactory.createCarkey().showdialog(GameTopicHeadView.this.getContext());
                    return;
                }
                PayProvider createPay = ProviderFactory.createPay();
                EventCollectManager.m12642a().m12640a(GameTopicHeadView.this.getContext(), "游戏详情页充值金币", "游戏详情页充值金币", EventConstants.f16419W);
                if (createPay != null) {
                    createPay.openPay(GameTopicHeadView.this.getContext(), 0, 1, 2);
                }
            }
        });
    }

    /* renamed from: c */
    private void m18594c(Context context) {
        LayoutInflater.from(getContext()).inflate(C2029R.layout.nrzs_game_topic_head_view, this);
        this.f11102a = (ImageView) findViewById(C2029R.C2031id.iv_topic_icon);
        this.f11103b = (TextView) findViewById(C2029R.C2031id.text_topic_title);
        this.f11104c = (TextView) findViewById(C2029R.C2031id.text_topic_desc);
        this.f11105d = (ImageView) findViewById(C2029R.C2031id.iv_topic_ad);
        this.f11106e = (RelativeLayout) findViewById(C2029R.C2031id.btn_buy_script);
        this.f11107f = (RelativeLayout) findViewById(C2029R.C2031id.btn_buy_vip);
        this.f11108g = (TextView) findViewById(C2029R.C2031id.text_buy_script_title);
        this.f11109h = (TextView) findViewById(C2029R.C2031id.text_buy_script_content);
        this.f11110i = (TextView) findViewById(C2029R.C2031id.text_buy_vip_content);
        this.f11111j = (TextView) findViewById(C2029R.C2031id.tv_recharge_gold);
    }

    public void setdata(final TopicDetailResponseInfo topicDetailResponseInfo) {
        if (topicDetailResponseInfo.getGameVIPInfo() == null) {
            this.f11109h.setText(Html.fromHtml("畅享<font color='#FFF700'>0</font>辅助，约<font color='#FFF700'>0</font>元/月"));
        } else {
            this.f11109h.setText(Html.fromHtml("畅享<font color='#FFF700'>" + topicDetailResponseInfo.getGameVIPInfo().getScriptNum() + "</font>辅助，约<font color='#FFF700'>" + topicDetailResponseInfo.getGameVIPInfo().getPrice() + "</font>元/月"));
        }
        if (topicDetailResponseInfo.getPlatformVIPInfo() == null) {
            this.f11110i.setText(Html.fromHtml("畅享<font color='#FFF700'>0</font>辅助，约<font color='#FFF700'>0</font>元/月"));
        } else {
            this.f11110i.setText(Html.fromHtml("畅享<font color='#FFF700'>" + topicDetailResponseInfo.getPlatformVIPInfo().getScriptNum() + "</font>辅助，约<font color='#FFF700'>" + topicDetailResponseInfo.getPlatformVIPInfo().getPrice() + "</font>元/月"));
        }
        if (topicDetailResponseInfo.getTopicInfo() != null) {
            GlideImageUtils.m11880a(this.f11102a, getContext(), C2029R.C2030drawable.bird_bg_common_img, topicDetailResponseInfo.getTopicInfo().getImgPath());
            TextView textView = this.f11103b;
            textView.setText("辅助:" + topicDetailResponseInfo.getGameVIPInfo().getScriptNum());
            this.f11104c.setText(topicDetailResponseInfo.getTopicInfo().getTopicDesc());
        }
        this.f11106e.setOnClickListener(new View.OnClickListener() { // from class: com.nrzs.game.ui.view.GameTopicHeadView.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                try {
                    if (!LoginManager.m12620d().m12606r()) {
                        IntentToAssistService.m12813a(GameTopicHeadView.this.getContext());
                    } else if (topicDetailResponseInfo.getGameVIPInfo() == null) {
                        ToastUtils.m23021b("该游戏辅助还未上架，请耐心等待");
                    } else if (topicDetailResponseInfo.getGameVIPInfo().getPrice() != 0) {
                        EventCollectManager.m12642a().m12640a(view.getContext(), "游戏详情辅助", "游戏详情辅助", EventConstants.f16451s);
                        Intent intent = new Intent(ActionVal.f16466c);
                        intent.addFlags(268435456);
                        intent.putExtra(GlobalConstants.f16482e, 0);
                        AdResultInfoItem adResultInfoItem = new AdResultInfoItem();
                        adResultInfoItem.Title = "购买续费";
                        adResultInfoItem.ExecArgs = HttpVal.f16516c + "?topicId=" + topicDetailResponseInfo.getTopicInfo().getTopicID();
                        intent.putExtra(GlobalConstants.f16481d, adResultInfoItem);
                        GameTopicHeadView.this.getContext().startActivity(intent);
                    } else if (topicDetailResponseInfo.getGameVIPInfo().getScriptNum() == 0) {
                        ToastUtils.m23021b("该游戏辅助还未上架，请耐心等待");
                    } else {
                        ToastUtils.m23021b("该游戏辅助无需购买，点击【运行】即可使用");
                    }
                } catch (ActivityNotFoundException unused) {
                    Log.d("FirstActivity.this", "找不到该活动");
                }
            }
        });
        this.f11107f.setOnClickListener(new View.OnClickListener() { // from class: com.nrzs.game.ui.view.GameTopicHeadView.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                try {
                    if (!LoginManager.m12620d().m12606r()) {
                        IntentToAssistService.m12813a(GameTopicHeadView.this.getContext());
                    } else if (topicDetailResponseInfo.getPlatformVIPInfo() != null) {
                        EventCollectManager.m12642a().m12640a(GameTopicHeadView.this.getContext(), "游戏详情全平台", "游戏详情全平台", EventConstants.f16450r);
                        Intent intent = new Intent(ActionVal.f16466c);
                        intent.addFlags(268435456);
                        AdResultInfoItem adResultInfoItem = new AdResultInfoItem();
                        adResultInfoItem.Title = "购买续费";
                        adResultInfoItem.ExecArgs = HttpVal.f16516c;
                        intent.putExtra(GlobalConstants.f16482e, 0);
                        intent.putExtra(GlobalConstants.f16481d, adResultInfoItem);
                        GameTopicHeadView.this.getContext().startActivity(intent);
                    }
                } catch (ActivityNotFoundException unused) {
                    Log.d("FirstActivity.this", "找不到该活动");
                }
            }
        });
    }
}
