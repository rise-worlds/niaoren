package com.nrzs.game.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.Utils;
import com.nrzs.base.router.RouterUtils;
import com.nrzs.base.router.provider.ProviderFactory;
import com.nrzs.data.game.bean.RdataBean;
import com.nrzs.data.game.bean.TopicInfo;
import com.nrzs.game.C2029R;
import com.nrzs.moudleui.adapter.BaseListAdapter;
import com.nrzs.moudleui.adapter.BaseViewHolder;
import java.util.List;
import p110z1.CKU;
import p110z1.DoneCallback;
import p110z1.EventCollectManager;
import p110z1.EventConstants;
import p110z1.FailCallback;
import p110z1.GlideImageUtils;
import p110z1.OrcDownManager;
import p110z1.OrcDownlDialog;
import p110z1.ShareVal;
import p110z1.TopicInfoManager;
import p110z1.XnkjUtils;
import p110z1.apf;

/* loaded from: classes2.dex */
public class GameTopicListViewAdapter extends BaseListAdapter<RdataBean, BaseViewHolder> {

    /* renamed from: a */
    private long f10838a = -1;

    /* renamed from: b */
    private String f10839b = "";

    @Override // com.nrzs.moudleui.adapter.BaseListAdapter
    /* renamed from: a */
    public void mo18184a(View view, int i) {
    }

    @Override // com.nrzs.moudleui.adapter.BaseListAdapter
    /* renamed from: b */
    public void mo18180b(View view, int i) {
    }

    public GameTopicListViewAdapter(List<RdataBean> list) {
        super(list);
    }

    /* renamed from: a */
    public void m18823a(String str) {
        this.f10839b = str;
    }

    /* renamed from: a */
    public void m18827a(long j) {
        this.f10838a = j;
    }

    @Override // com.nrzs.moudleui.adapter.BaseListAdapter
    /* renamed from: b */
    public BaseViewHolder mo18181b(LayoutInflater layoutInflater, ViewGroup viewGroup, int i, int i2) {
        return new BaseViewHolder(layoutInflater, viewGroup, i);
    }

    @Override // com.nrzs.moudleui.adapter.BaseListAdapter
    /* renamed from: a */
    public void mo18183a(@NonNull BaseViewHolder baseViewHolder, int i) {
        final RdataBean rdataBean = (RdataBean) this.f11255c.get(i);
        GlideImageUtils.m11880a((ImageView) baseViewHolder.itemView.findViewById(C2029R.C2031id.iv_scrpit_icon), m18480d(), C2029R.C2030drawable.bird_bg_common_img, rdataBean.getScriptIco());
        ((TextView) baseViewHolder.itemView.findViewById(C2029R.C2031id.text_script_release_date)).setText(rdataBean.getReleaseDateStr());
        ((TextView) baseViewHolder.itemView.findViewById(C2029R.C2031id.text_script_title)).setText(rdataBean.getScriptName());
        ((TextView) baseViewHolder.itemView.findViewById(C2029R.C2031id.text_script_score)).setText(Html.fromHtml("<font color=\"#FF9100\">" + rdataBean.getAuthorRewardSGBTotalNumStr() + "</font>次打赏"));
        ((TextView) baseViewHolder.itemView.findViewById(C2029R.C2031id.tv_author_name)).setText(TextUtils.isEmpty(rdataBean.getNickName()) ? "辅助大神" : rdataBean.getNickName());
        TextView textView = (TextView) baseViewHolder.itemView.findViewById(C2029R.C2031id.text_script_gold);
        if (rdataBean.isToolVip()) {
            textView.setText(rdataBean.getGold() + "金币/天");
            textView.setVisibility(0);
        } else {
            textView.setVisibility(4);
        }
        ((RelativeLayout) baseViewHolder.itemView.findViewById(C2029R.C2031id.rl_topic_item)).setOnClickListener(new View.OnClickListener() { // from class: com.nrzs.game.adapter.GameTopicListViewAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                RouterUtils.toScripInfo(GameTopicListViewAdapter.this.f10838a == -1 ? rdataBean.getTopicID() : GameTopicListViewAdapter.this.f10838a, rdataBean.getOnlyID(), GameTopicListViewAdapter.this.f10839b);
            }
        });
        ((TextView) baseViewHolder.itemView.findViewById(C2029R.C2031id.btn_run)).setOnClickListener(new View.OnClickListener() { // from class: com.nrzs.game.adapter.GameTopicListViewAdapter.2
            @Override // android.view.View.OnClickListener
            public void onClick(final View view) {
                if (!OrcDownManager.m12866a().m12864b() || rdataBean.getUseOcrText() != 1) {
                    GameTopicListViewAdapter.this.m18826a(rdataBean, view);
                } else {
                    new OrcDownlDialog(GameTopicListViewAdapter.this.m18480d(), new OrcDownlDialog.AbstractC3549a() { // from class: com.nrzs.game.adapter.GameTopicListViewAdapter.2.1
                        @Override // p110z1.OrcDownlDialog.AbstractC3549a
                        /* renamed from: a */
                        public void mo12817a() {
                            GameTopicListViewAdapter.this.m18826a(rdataBean, view);
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
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m18826a(final RdataBean rdataBean, final View view) {
        EventCollectManager.m12642a().m12640a(m18480d(), "游戏详情页运行按钮", String.valueOf(this.f10838a), EventConstants.f16441i);
        TopicInfoManager c = TopicInfoManager.m12726c();
        long j = this.f10838a;
        if (j == -1) {
            j = rdataBean.getTopicID();
        }
        c.m12735a(j, new FailCallback<Throwable>() { // from class: com.nrzs.game.adapter.GameTopicListViewAdapter.3
            /* renamed from: a */
            public void onFail(Throwable th) {
                LogUtils.m23734c("TopicList", "result:" + th.getMessage());
            }
        }, new DoneCallback<TopicInfo>() { // from class: com.nrzs.game.adapter.GameTopicListViewAdapter.4
            /* renamed from: a */
            public void onDone(final TopicInfo topicInfo) {
                boolean b = apf.m11836b((Context) Utils.m24103a(), ShareVal.f16591a, ShareVal.f16615y, false);
                if (topicInfo.SportBackGround != 1 || b || XnkjUtils.m12528a()) {
                    new CKU().m13032a(view.getContext(), view.getContext().getClass(), topicInfo, CKU.m13035a(rdataBean.getScriptID(), rdataBean.getOnlyID(), rdataBean.getScriptName(), rdataBean.getScriptAuthorID()));
                } else {
                    ProviderFactory.createXNKJRun().showdialog(GameTopicListViewAdapter.this.m18480d(), new DialogInterface.OnClickListener() { // from class: com.nrzs.game.adapter.GameTopicListViewAdapter.4.1
                        @Override // android.content.DialogInterface.OnClickListener
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            if (i == 0) {
                                new CKU().m13032a(view.getContext(), view.getContext().getClass(), topicInfo, CKU.m13035a(rdataBean.getScriptID(), rdataBean.getOnlyID(), rdataBean.getScriptName(), rdataBean.getScriptAuthorID()));
                            } else {
                                RouterUtils.toMain(1);
                            }
                        }
                    });
                }
            }
        });
    }

    @Override // com.nrzs.moudleui.adapter.BaseListAdapter
    /* renamed from: a */
    protected int mo18186a(int i) {
        return C2029R.layout.nrzs_game_topic_item_view;
    }
}
