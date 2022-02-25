package com.nrzs.game.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.Utils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.nrzs.base.router.RouterUtils;
import com.nrzs.base.router.provider.ProviderFactory;
import com.nrzs.data.game.bean.TopicInfo;
import com.nrzs.data.game.bean.TopicListBean;
import com.nrzs.game.C2029R;
import java.util.ArrayList;
import java.util.List;
import p110z1.CKU;
import p110z1.DoneCallback;
import p110z1.FailCallback;
import p110z1.ShareVal;
import p110z1.TopicInfoManager;
import p110z1.XnkjUtils;
import p110z1.apf;

/* renamed from: com.nrzs.game.adapter.b */
/* loaded from: classes2.dex */
public class SearchGameResultAdapter extends ArrayAdapter<TopicListBean> {

    /* renamed from: a */
    private Context f10856a;

    /* renamed from: b */
    private List<TopicListBean> f10857b;

    public SearchGameResultAdapter(Context context, List<TopicListBean> list) {
        super(context, C2029R.layout.nrzs_game_search_key_item, list);
        this.f10857b = new ArrayList();
        this.f10856a = context;
        this.f10857b = list;
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public View getView(final int i, View view, ViewGroup viewGroup) {
        View view2;
        C2054a aVar;
        if (view == null) {
            aVar = new C2054a();
            view2 = LayoutInflater.from(this.f10856a).inflate(C2029R.layout.nrzs_game_search_result_game_item, (ViewGroup) null);
            aVar.f10868a = (TextView) view2.findViewById(C2029R.C2031id.tv_game_name);
            aVar.f10874g = (TextView) view2.findViewById(C2029R.C2031id.btn_run);
            aVar.f10872e = (TextView) view2.findViewById(C2029R.C2031id.tv_script_num);
            aVar.f10869b = (ImageView) view2.findViewById(C2029R.C2031id.iv_is_free);
            aVar.f10871d = (ImageView) view2.findViewById(C2029R.C2031id.iv_no_free);
            aVar.f10873f = (ImageView) view2.findViewById(C2029R.C2031id.iv_game_icon);
            aVar.f10875h = (RelativeLayout) view2.findViewById(C2029R.C2031id.rl_topic_item);
            aVar.f10870c = (ImageView) view2.findViewById(C2029R.C2031id.iv_game_onhook);
            view2.setTag(aVar);
        } else {
            aVar = (C2054a) view.getTag();
            view2 = view;
        }
        TopicListBean topicListBean = this.f10857b.get(i);
        aVar.f10868a.setText(topicListBean.getTopicName());
        aVar.f10868a.setTag(Integer.valueOf(i));
        TextView textView = aVar.f10872e;
        textView.setText("辅助：" + topicListBean.getScriptCount());
        TopicInfoManager.m12726c().m12735a((long) topicListBean.getTopicID(), new FailCallback<Throwable>() { // from class: com.nrzs.game.adapter.b.1
            /* renamed from: a */
            public void onFail(Throwable th) {
                LogUtils.m23734c("TopicList", "result:" + th.getMessage());
            }
        }, new C20502(aVar, topicListBean));
        Glide.with(getContext()).load(topicListBean.getImgPath()).apply((BaseRequestOptions<?>) new RequestOptions().placeholder(C2029R.C2030drawable.bird_bg_common_img)).into(aVar.f10873f);
        aVar.f10875h.setOnClickListener(new View.OnClickListener() { // from class: com.nrzs.game.adapter.b.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view3) {
                TopicListBean topicListBean2 = (TopicListBean) SearchGameResultAdapter.this.f10857b.get(i);
                if (topicListBean2 != null) {
                    RouterUtils.toGameTopic(topicListBean2.getTopicName(), topicListBean2.getTopicID());
                }
            }
        });
        return view2;
    }

    /* compiled from: SearchGameResultAdapter.java */
    /* renamed from: com.nrzs.game.adapter.b$2 */
    /* loaded from: classes2.dex */
    class C20502 implements DoneCallback<TopicInfo> {

        /* renamed from: a */
        final /* synthetic */ C2054a f10859a;

        /* renamed from: b */
        final /* synthetic */ TopicListBean f10860b;

        C20502(C2054a aVar, TopicListBean topicListBean) {
            this.f10859a = aVar;
            this.f10860b = topicListBean;
        }

        /* renamed from: a */
        public void onDone(final TopicInfo topicInfo) {
            if (topicInfo.SportBackGround != 1 || XnkjUtils.m12528a()) {
                this.f10859a.f10870c.setVisibility(8);
            } else {
                this.f10859a.f10870c.setVisibility(0);
            }
            if (topicInfo.isVip == 1) {
                this.f10859a.f10871d.setVisibility(0);
                this.f10859a.f10869b.setVisibility(8);
            } else {
                this.f10859a.f10869b.setVisibility(0);
                this.f10859a.f10871d.setVisibility(8);
            }
            this.f10859a.f10874g.setOnClickListener(new View.OnClickListener() { // from class: com.nrzs.game.adapter.b.2.1
                @Override // android.view.View.OnClickListener
                public void onClick(final View view) {
                    boolean b = apf.m11836b((Context) Utils.m24103a(), ShareVal.f16591a, ShareVal.f16615y, false);
                    if (C20502.this.f10860b.getSportBackGround() != 1 || b || XnkjUtils.m12528a()) {
                        CKU aiqVar = new CKU();
                        Context context = view.getContext();
                        Class<?> cls = SearchGameResultAdapter.this.getContext().getClass();
                        TopicInfo topicInfo2 = topicInfo;
                        aiqVar.m13031a(context, cls, topicInfo2, true ^ TextUtils.isEmpty(topicInfo2.localAppPackage));
                        return;
                    }
                    ProviderFactory.createXNKJRun().showdialog(SearchGameResultAdapter.this.getContext(), new DialogInterface.OnClickListener() { // from class: com.nrzs.game.adapter.b.2.1.1
                        @Override // android.content.DialogInterface.OnClickListener
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            if (i == 0) {
                                new CKU().m13031a(view.getContext(), SearchGameResultAdapter.this.getContext().getClass(), topicInfo, true ^ TextUtils.isEmpty(topicInfo.localAppPackage));
                            } else {
                                RouterUtils.toMain(1);
                            }
                        }
                    });
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: SearchGameResultAdapter.java */
    /* renamed from: com.nrzs.game.adapter.b$a */
    /* loaded from: classes2.dex */
    public class C2054a {

        /* renamed from: a */
        TextView f10868a;

        /* renamed from: b */
        ImageView f10869b;

        /* renamed from: c */
        ImageView f10870c;

        /* renamed from: d */
        ImageView f10871d;

        /* renamed from: e */
        TextView f10872e;

        /* renamed from: f */
        ImageView f10873f;

        /* renamed from: g */
        TextView f10874g;

        /* renamed from: h */
        RelativeLayout f10875h;

        C2054a() {
        }
    }
}
