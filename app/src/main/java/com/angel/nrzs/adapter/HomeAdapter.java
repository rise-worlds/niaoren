package com.angel.nrzs.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.angel.nrzs.ui;
import com.angel.nrzs.C0692R;
import com.nrzs.base.router.RouterUtils;
import com.nrzs.base.router.provider.ProviderFactory;
import com.nrzs.base.router.provider.XNKJRunProvider;
import com.nrzs.data.game.bean.TopicInfo;
import com.nrzs.moudleui.adapter.BaseListAdapter;
import com.nrzs.moudleui.adapter.BaseViewHolder;
import java.util.List;
import p110z1.CKU;
import p110z1.EventCollectManager;
import p110z1.EventConstants;
import p110z1.GlideImageUtils;
import p110z1.ScriptModeManager;
import p110z1.ShareVal;
import p110z1.XnkjUtils;
import p110z1.apf;

/* loaded from: classes.dex */
public class HomeAdapter extends BaseListAdapter<TopicInfo, C0710a> {
    @Override // com.nrzs.moudleui.adapter.BaseListAdapter
    /* renamed from: a */
    protected int mo18186a(int i) {
        return C0692R.layout.f3474gk;
    }

    @Override // com.nrzs.moudleui.adapter.BaseListAdapter
    /* renamed from: a */
    public void mo18184a(View view, int i) {
    }

    @Override // com.nrzs.moudleui.adapter.BaseListAdapter
    /* renamed from: b */
    public void mo18180b(View view, int i) {
    }

    public HomeAdapter(List<TopicInfo> list) {
        super(list);
    }

    /* renamed from: a */
    public C0710a mo18181b(LayoutInflater layoutInflater, ViewGroup viewGroup, int i, int i2) {
        return new C0710a(layoutInflater, viewGroup, i);
    }

    /* renamed from: a  reason: avoid collision after fix types in other method */
    public void mo18183a(@NonNull C0710a aVar, int i) {
        final TopicInfo topicInfo = (TopicInfo) this.f11255c.get(i);
        if (topicInfo != null) {
            if (topicInfo.isVip == 1) {
                aVar.f5155a.setVisibility(8);
                aVar.f5157c.setVisibility(0);
            } else {
                aVar.f5155a.setVisibility(0);
                aVar.f5157c.setVisibility(8);
            }
            if (topicInfo.localAppIcon != null) {
                GlideImageUtils.m11881a(aVar.f5156b, m18480d(), (int) C0692R.C0693drawable.f1994b5, topicInfo.localAppIcon);
            } else {
                GlideImageUtils.m11880a(aVar.f5156b, m18480d(), (int) C0692R.C0693drawable.f1994b5, topicInfo.ImgPath);
            }
            if (topicInfo.SportBackGround != 1 || XnkjUtils.m12528a()) {
                aVar.f5158d.setVisibility(8);
            } else {
                aVar.f5158d.setVisibility(0);
            }
            final String str = TextUtils.isEmpty(topicInfo.localAppPackage) ? topicInfo.TopicName : topicInfo.localAppName;
            aVar.f5159e.setText(str);
            if (ScriptModeManager.m3097f().m3103a() == 1) {
                TextView textView = aVar.f5160f;
                textView.setText("辅助：" + topicInfo.ScriptCount);
            } else {
                TextView textView2 = aVar.f5160f;
                textView2.setText("辅助：" + topicInfo.VaScriptCount);
            }
            aVar.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.angel.nrzs.adapter.HomeAdapter.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    RouterUtils.toGameTopic(str, (int) topicInfo.TopicID);
                }
            });
            aVar.f5161g.setOnClickListener(new View.OnClickListener() { // from class: com.angel.nrzs.adapter.HomeAdapter.2
                @Override // android.view.View.OnClickListener
                public void onClick(final View view) {
                    EventCollectManager.m12642a().m12640a(HomeAdapter.this.m18480d(), "首页猜你喜欢运行", "首页猜你喜欢运行", EventConstants.f16439g);
                    boolean b = apf.m11836b((Context) App.getInstance(), ShareVal.f16591a, ShareVal.f16615y, false);
                    if (topicInfo.SportBackGround != 1 || b || XnkjUtils.m12528a()) {
                        CKU aiqVar = new CKU();
                        Context context = view.getContext();
                        Class<?> cls = HomeAdapter.this.m18480d().getClass();
                        TopicInfo topicInfo2 = topicInfo;
                        aiqVar.m13031a(context, cls, topicInfo2, true ^ TextUtils.isEmpty(topicInfo2.localAppPackage));
                        return;
                    }
                    final XNKJRunProvider createXNKJRun = ProviderFactory.createXNKJRun();
                    createXNKJRun.showdialog(HomeAdapter.this.m18480d(), new DialogInterface.OnClickListener() { // from class: com.angel.nrzs.adapter.HomeAdapter.2.1
                        @Override // android.content.DialogInterface.OnClickListener
                        public void onClick(DialogInterface dialogInterface, int i2) {
                            dialogInterface.dismiss();
                            if (i2 == 0) {
                                new CKU().m13031a(view.getContext(), HomeAdapter.this.m18480d().getClass(), topicInfo, !TextUtils.isEmpty(topicInfo.localAppPackage));
                            } else {
                                createXNKJRun.startGame(topicInfo.localAppPackage);
                            }
                        }
                    });
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.angel.nrzs.adapter.HomeAdapter$a */
    /* loaded from: classes.dex */
    public static class C0710a extends BaseViewHolder {

        /* renamed from: a */
        ImageView f5155a = (ImageView) this.itemView.findViewById(C0692R.C0694id.f2801li);

        /* renamed from: e */
        TextView f5159e = (TextView) this.itemView.findViewById(C0692R.C0694id.f2798lf);

        /* renamed from: b */
        ImageView f5156b = (ImageView) this.itemView.findViewById(C0692R.C0694id.f2800lh);

        /* renamed from: f */
        TextView f5160f = (TextView) this.itemView.findViewById(C0692R.C0694id.f2803lk);

        /* renamed from: g */
        TextView f5161g = (TextView) this.itemView.findViewById(C0692R.C0694id.f2799lg);

        /* renamed from: c */
        ImageView f5157c = (ImageView) this.itemView.findViewById(C0692R.C0694id.f2802lj);

        /* renamed from: d */
        ImageView f5158d = (ImageView) this.itemView.findViewById(C0692R.C0694id.f2804ll);

        C0710a(LayoutInflater layoutInflater, ViewGroup viewGroup, int i) {
            super(layoutInflater, viewGroup, i);
        }
    }
}
