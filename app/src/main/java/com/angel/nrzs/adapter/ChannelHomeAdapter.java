package com.angel.nrzs.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.angel.nrzs.C0692R;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.Utils;
import com.nrzs.base.router.RouterUtils;
import com.nrzs.base.router.provider.ProviderFactory;
import com.nrzs.data.game.bean.AuthorScriptBean;
import com.nrzs.data.game.bean.TopicInfo;
import com.nrzs.moudleui.adapter.BaseListAdapter;
import com.nrzs.moudleui.adapter.BaseViewHolder;
import java.util.List;
import p110z1.CKU;
import p110z1.DoneCallback;
import p110z1.FailCallback;
import p110z1.GlideImageUtils;
import p110z1.ShareVal;
import p110z1.TopicInfoManager;
import p110z1.XnkjUtils;
import p110z1.apf;

/* loaded from: classes.dex */
public class ChannelHomeAdapter extends BaseListAdapter<AuthorScriptBean, C0702a> {

    /* renamed from: a */
    private long f5114a;

    @Override // com.nrzs.moudleui.adapter.BaseListAdapter
    /* renamed from: a */
    protected int mo18186a(int i) {
        return C0692R.layout.nrzs_item_channel;
    }

    @Override // com.nrzs.moudleui.adapter.BaseListAdapter
    /* renamed from: a */
    public void mo18184a(View view, int i) {
    }

    @Override // com.nrzs.moudleui.adapter.BaseListAdapter
    /* renamed from: b */
    public void mo18180b(View view, int i) {
    }

    public ChannelHomeAdapter(List<AuthorScriptBean> list) {
        super(list);
    }

    /* renamed from: a */
    public C0702a mo18181b(LayoutInflater layoutInflater, ViewGroup viewGroup, int i, int i2) {
        return new C0702a(layoutInflater, viewGroup, i);
    }

    /* renamed from: a */
    public void m25201a(long j) {
        this.f5114a = j;
    }

    /* renamed from: a  reason: avoid collision after fix types in other method */
    public void mo18183a(@NonNull C0702a aVar, int i) {
        final AuthorScriptBean authorScriptBean = (AuthorScriptBean) this.f11255c.get(i);
        if (authorScriptBean.getScriptName() != null) {
            aVar.f5126c.setText(authorScriptBean.getScriptName());
        }
        if (authorScriptBean.getTopicName() != null) {
            aVar.f5130g.setText(authorScriptBean.getTopicName());
        }
        if (authorScriptBean.getReleaseDate() != null) {
            aVar.f5128e.setText(authorScriptBean.getReleaseDate());
        }
        GlideImageUtils.m11880a(aVar.f5125b, m18480d(), (int) C0692R.C0693drawable.f1994b5, authorScriptBean.getScriptIcon());
        TextView textView = aVar.f5127d;
        textView.setText(authorScriptBean.getGold() + "/天");
        aVar.f5124a.setOnClickListener(new View.OnClickListener() { // from class: com.angel.nrzs.adapter.ChannelHomeAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                RouterUtils.toScripInfo(authorScriptBean.getTopicId(), authorScriptBean.getOnlyID(), authorScriptBean.getTopicName());
            }
        });
        aVar.f5129f.setOnClickListener(new View$OnClickListenerC06982(authorScriptBean));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.angel.nrzs.adapter.ChannelHomeAdapter$2 */
    /* loaded from: classes.dex */
    public class View$OnClickListenerC06982 implements View.OnClickListener {

        /* renamed from: a */
        final /* synthetic */ AuthorScriptBean f5117a;

        View$OnClickListenerC06982(AuthorScriptBean authorScriptBean) {
            this.f5117a = authorScriptBean;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(final View view) {
            TopicInfoManager.m12726c().m12735a(this.f5117a.getTopicId(), new FailCallback<Throwable>() { // from class: com.angel.nrzs.adapter.ChannelHomeAdapter.2.1
                /* renamed from: a */
                public void onFail(Throwable th) {
                    LogUtils.m23734c("TopicList", "result:" + th.getMessage());
                }
            }, new DoneCallback<TopicInfo>() { // from class: com.angel.nrzs.adapter.ChannelHomeAdapter.2.2
                /* renamed from: a */
                public void onDone(final TopicInfo topicInfo) {
                    boolean b = apf.m11836b((Context) Utils.m24103a(), ShareVal.f16591a, ShareVal.f16615y, false);
                    if (topicInfo.SportBackGround != 1 || b || XnkjUtils.m12528a()) {
                        new CKU().m13032a(view.getContext(), view.getContext().getClass(), topicInfo, CKU.m13035a(View$OnClickListenerC06982.this.f5117a.getID(), View$OnClickListenerC06982.this.f5117a.getOnlyID(), View$OnClickListenerC06982.this.f5117a.getScriptName(), ChannelHomeAdapter.this.f5114a));
                    } else {
                        ProviderFactory.createXNKJRun().showdialog(ChannelHomeAdapter.this.m18480d(), new DialogInterface.OnClickListener() { // from class: com.angel.nrzs.adapter.ChannelHomeAdapter.2.2.1
                            @Override // android.content.DialogInterface.OnClickListener
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                                if (i == 0) {
                                    new CKU().m13032a(view.getContext(), view.getContext().getClass(), topicInfo, CKU.m13035a(View$OnClickListenerC06982.this.f5117a.getID(), View$OnClickListenerC06982.this.f5117a.getOnlyID(), View$OnClickListenerC06982.this.f5117a.getScriptName(), ChannelHomeAdapter.this.f5114a));
                                } else {
                                    RouterUtils.toMain(1);
                                }
                            }
                        });
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.angel.nrzs.adapter.ChannelHomeAdapter$a */
    /* loaded from: classes.dex */
    public static class C0702a extends BaseViewHolder {

        /* renamed from: b */
        ImageView f5125b = (ImageView) this.itemView.findViewById(C0692R.C0694id.nrzs_channel_item_iv_icon);

        /* renamed from: c */
        TextView f5126c = (TextView) this.itemView.findViewById(C0692R.C0694id.nrzs_channel_item_tv_name);

        /* renamed from: d */
        TextView f5127d = (TextView) this.itemView.findViewById(C0692R.C0694id.nrzs_channel_item_tv_price);

        /* renamed from: e */
        TextView f5128e = (TextView) this.itemView.findViewById(C0692R.C0694id.nrzs_channel_item_tv_time);

        /* renamed from: f */
        TextView f5129f = (TextView) this.itemView.findViewById(C0692R.C0694id.nrzs_channel_item_tv_run);

        /* renamed from: g */
        TextView f5130g = (TextView) this.itemView.findViewById(C0692R.C0694id.nrzs_channel_item_tv_gamename);

        /* renamed from: a */
        RelativeLayout f5124a = (RelativeLayout) this.itemView.findViewById(C0692R.C0694id.nrzs_item_view);

        C0702a(LayoutInflater layoutInflater, ViewGroup viewGroup, int i) {
            super(layoutInflater, viewGroup, i);
        }
    }
}
