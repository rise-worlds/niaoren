package com.nrzs.game.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.blankj.utilcode.util.Utils;
import com.nrzs.base.router.RouterUtils;
import com.nrzs.base.router.provider.ProviderFactory;
import com.nrzs.base.router.provider.XNKJRunProvider;
import com.nrzs.core.models.GameInfo;
import com.nrzs.data.game.bean.TopicInfo;
import com.nrzs.game.C2029R;
import com.nrzs.game.p069ui.activity.GameLocalActivity;
import com.nrzs.moudleui.adapter.BaseViewHolder;
import com.nrzs.moudleui.pinnedheader.PinnedHeaderAdapter;
import java.util.List;
import p110z1.CKU;
import p110z1.GlideImageUtils;
import p110z1.ShareVal;
import p110z1.XnkjUtils;
import p110z1.apf;

/* loaded from: classes2.dex */
public class GameLocalAdapter extends PinnedHeaderAdapter {

    /* renamed from: a */
    private static final int f10825a = 77;

    /* renamed from: b */
    private TopicInfo f10826b;

    @Override // com.nrzs.moudleui.adapter.BaseListAdapter
    /* renamed from: a */
    public void mo18184a(View view, int i) {
    }

    @Override // com.nrzs.moudleui.adapter.BaseListAdapter
    /* renamed from: b */
    public void mo18180b(View view, int i) {
    }

    @Override // com.nrzs.moudleui.pinnedheader.PinnedHeaderAdapter
    /* renamed from: b */
    public boolean mo18296b(int i) {
        return false;
    }

    public GameLocalAdapter(List<GameInfo> list, TopicInfo topicInfo) {
        super(list);
        this.f10826b = topicInfo;
    }

    @Override // com.nrzs.moudleui.adapter.BaseListAdapter
    /* renamed from: b */
    public BaseViewHolder mo18181b(LayoutInflater layoutInflater, ViewGroup viewGroup, int i, int i2) {
        if (i2 == 77) {
            return new C2041b(layoutInflater, viewGroup, i);
        }
        return new C2040a(layoutInflater, viewGroup, i);
    }

    @Override // com.nrzs.moudleui.adapter.BaseListAdapter
    /* renamed from: a */
    public void mo18183a(@NonNull BaseViewHolder baseViewHolder, int i) {
        if (baseViewHolder.getItemViewType() == 77) {
            ((C2041b) baseViewHolder).f10837a.setText(((GameInfo) this.f11255c.get(i)).f10545i);
            return;
        }
        C2040a aVar = (C2040a) baseViewHolder;
        final GameInfo gameInfo = (GameInfo) this.f11255c.get(i);
        GlideImageUtils.m11881a(aVar.f10833c, m18480d(), C2029R.C2030drawable.ic_launcher, gameInfo.f10548l);
        aVar.f10834d.setText(gameInfo.f10543g);
        aVar.f10835e.setText("");
        aVar.f10831a.setVisibility(8);
        aVar.f10832b.setVisibility(8);
        aVar.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.nrzs.game.adapter.GameLocalAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(final View view) {
                GameLocalAdapter.this.f10826b.localAppPackage = gameInfo.f10544h;
                GameLocalAdapter.this.f10826b.localAppName = gameInfo.f10543g;
                boolean b = apf.m11836b((Context) Utils.m24103a(), ShareVal.f16591a, ShareVal.f16615y, false);
                if (XnkjUtils.m12528a() || GameLocalAdapter.this.f10826b.SportBackGround != 1 || b) {
                    new CKU().m13031a(view.getContext(), GameLocalActivity.class, GameLocalAdapter.this.f10826b, true);
                    return;
                }
                XNKJRunProvider createXNKJRun = ProviderFactory.createXNKJRun();
                if (createXNKJRun != null) {
                    createXNKJRun.showdialog(GameLocalAdapter.this.m18480d(), new DialogInterface.OnClickListener() { // from class: com.nrzs.game.adapter.GameLocalAdapter.1.1
                        @Override // android.content.DialogInterface.OnClickListener
                        public void onClick(DialogInterface dialogInterface, int i2) {
                            dialogInterface.dismiss();
                            if (i2 == 0) {
                                new CKU().m13031a(view.getContext(), GameLocalActivity.class, GameLocalAdapter.this.f10826b, true);
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
        if (i == 77) {
            return C2029R.layout.nrzs_item_game_title;
        }
        return C2029R.layout.nrzs_item_game_layout;
    }

    @Override // com.nrzs.moudleui.adapter.BaseListAdapter, android.support.p006v7.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        if (((GameInfo) this.f11255c.get(i)).f10549m) {
            return 77;
        }
        return super.getItemViewType(i);
    }

    /* renamed from: com.nrzs.game.adapter.GameLocalAdapter$b */
    /* loaded from: classes2.dex */
    static class C2041b extends BaseViewHolder {

        /* renamed from: a */
        TextView f10837a = (TextView) this.itemView.findViewById(C2029R.C2031id.nrzs_game_tv_title);

        C2041b(LayoutInflater layoutInflater, ViewGroup viewGroup, int i) {
            super(layoutInflater, viewGroup, i);
        }
    }

    /* renamed from: com.nrzs.game.adapter.GameLocalAdapter$a */
    /* loaded from: classes2.dex */
    static class C2040a extends BaseViewHolder {

        /* renamed from: a */
        ImageView f10831a = (ImageView) this.itemView.findViewById(C2029R.C2031id.nrzs_app__home_item_img_free);

        /* renamed from: b */
        ImageView f10832b = (ImageView) this.itemView.findViewById(C2029R.C2031id.nrzs_app__home_item_img_vip);

        /* renamed from: d */
        TextView f10834d = (TextView) this.itemView.findViewById(C2029R.C2031id.nrzs_app__home_ietm_name);

        /* renamed from: c */
        ImageView f10833c = (ImageView) this.itemView.findViewById(C2029R.C2031id.nrzs_app__home_item_himg);

        /* renamed from: e */
        TextView f10835e = (TextView) this.itemView.findViewById(C2029R.C2031id.nrzs_app__home_item_num);

        /* renamed from: f */
        TextView f10836f = (TextView) this.itemView.findViewById(C2029R.C2031id.nrzs_app__home_ietm_run);

        C2040a(LayoutInflater layoutInflater, ViewGroup viewGroup, int i) {
            super(layoutInflater, viewGroup, i);
        }
    }
}
