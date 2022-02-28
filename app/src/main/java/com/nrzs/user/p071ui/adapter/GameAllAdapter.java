package com.nrzs.user.p071ui.adapter;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.nrzs.base.router.RouterUtils;
import com.nrzs.data.game.bean.TopicInfo;
import com.nrzs.moudleui.adapter.BaseViewHolder;
import com.nrzs.moudleui.pinnedheader.PinnedHeaderAdapter;
import com.nrzs.user.C2222R;
import java.util.List;
import org.slf4j.Marker;
import p110z1.GlideImageUtils;
import p110z1.IChooseCallback;
import p110z1.XnkjUtils;
import p110z1.aly;

/* renamed from: com.nrzs.user.ui.adapter.GameAllAdapter */
/* loaded from: classes2.dex */
public class GameAllAdapter extends PinnedHeaderAdapter {

    /* renamed from: a */
    private static final int f11487a = 77;

    /* renamed from: b */
    private int f11488b;

    /* renamed from: d */
    private IChooseCallback f11489d;

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

    public GameAllAdapter(List<TopicInfo> list, int i, IChooseCallback alpVar) {
        super(list);
        this.f11488b = i;
        this.f11489d = alpVar;
    }

    @Override // com.nrzs.moudleui.adapter.BaseListAdapter
    /* renamed from: b */
    public BaseViewHolder mo18181b(LayoutInflater layoutInflater, ViewGroup viewGroup, int i, int i2) {
        if (i2 == 77) {
            return new C2279b(layoutInflater, viewGroup, i);
        }
        return new C2278a(layoutInflater, viewGroup, i);
    }

    @Override // com.nrzs.moudleui.adapter.BaseListAdapter
    /* renamed from: a */
    public void mo18183a(@NonNull BaseViewHolder baseViewHolder, int i) {
        if (baseViewHolder.getItemViewType() == 77) {
            C2279b bVar = (C2279b) baseViewHolder;
            TopicInfo topicInfo = (TopicInfo) this.f11255c.get(i);
            if (Marker.ANY_MARKER.equals(topicInfo.sortLetters)) {
                bVar.f11501a.setText("猜你喜欢");
            } else {
                bVar.f11501a.setText(topicInfo.sortLetters);
            }
        } else {
            C2278a aVar = (C2278a) baseViewHolder;
            final TopicInfo topicInfo2 = (TopicInfo) this.f11255c.get(i);
            if (topicInfo2.isVip == 1) {
                aVar.f11494a.setVisibility(8);
                aVar.f11496c.setVisibility(0);
            } else {
                aVar.f11494a.setVisibility(0);
                aVar.f11496c.setVisibility(8);
            }
            if (topicInfo2.localAppIcon != null) {
                GlideImageUtils.m11881a(aVar.f11495b, m18480d(), C2222R.C2223drawable.bird_bg_common_img, topicInfo2.localAppIcon);
            } else {
                GlideImageUtils.m11880a(aVar.f11495b, m18480d(), C2222R.C2223drawable.bird_bg_common_img, topicInfo2.ImgPath);
            }
            if (topicInfo2.SportBackGround != 1 || XnkjUtils.m12528a()) {
                aVar.f11497d.setVisibility(4);
            } else {
                aVar.f11497d.setVisibility(0);
            }
            aVar.f11498e.setText(topicInfo2.TopicName);
            if (aly.m12531a(1) == 1) {
                TextView textView = aVar.f11499f;
                textView.setText("辅助：" + topicInfo2.ScriptCount);
            } else {
                TextView textView2 = aVar.f11499f;
                textView2.setText("辅助：" + topicInfo2.VaScriptCount);
            }
            aVar.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.nrzs.user.ui.adapter.GameAllAdapter.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (topicInfo2 != null && GameAllAdapter.this.f11488b != 2) {
                        RouterUtils.toGameTopic(topicInfo2.TopicName, (int) topicInfo2.TopicID);
                    }
                }
            });
            aVar.f11500g.setText("选择");
            aVar.f11500g.setOnClickListener(new View.OnClickListener() { // from class: com.nrzs.user.ui.adapter.GameAllAdapter.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    GameAllAdapter.this.f11489d.mo12560a(topicInfo2);
                }
            });
        }
    }

    @Override // com.nrzs.moudleui.adapter.BaseListAdapter
    /* renamed from: a */
    protected int mo18186a(int i) {
        if (i == 77) {
            return C2222R.layout.nrzs_item_game_title_question;
        }
        return C2222R.layout.nrzs_item_game_layout_question;
    }

    @Override // com.nrzs.moudleui.adapter.BaseListAdapter, android.support.v7.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        if (((TopicInfo) this.f11255c.get(i)).isFirst) {
            return 77;
        }
        return super.getItemViewType(i);
    }

    /* renamed from: c */
    public int m18294c(int i) {
        for (int i2 = 0; i2 < m18298a(); i2++) {
            if (((TopicInfo) this.f11255c.get(i2)).sortLetters.toUpperCase().charAt(0) == i) {
                return i2;
            }
        }
        return -1;
    }

    /* renamed from: a */
    public int m18298a() {
        if (this.f11255c == null || this.f11255c.isEmpty()) {
            return 0;
        }
        return this.f11255c.size();
    }

    /* renamed from: com.nrzs.user.ui.adapter.GameAllAdapter$b */
    /* loaded from: classes2.dex */
    static class C2279b extends BaseViewHolder {

        /* renamed from: a */
        TextView f11501a = (TextView) this.itemView.findViewById(C2222R.C2224id.nrzs_game_tv_title);

        C2279b(LayoutInflater layoutInflater, ViewGroup viewGroup, int i) {
            super(layoutInflater, viewGroup, i);
        }
    }

    /* renamed from: com.nrzs.user.ui.adapter.GameAllAdapter$a */
    /* loaded from: classes2.dex */
    static class C2278a extends BaseViewHolder {

        /* renamed from: a */
        ImageView f11494a = (ImageView) this.itemView.findViewById(C2222R.C2224id.nrzs_app__home_item_img_free);

        /* renamed from: e */
        TextView f11498e = (TextView) this.itemView.findViewById(C2222R.C2224id.nrzs_app__home_ietm_name);

        /* renamed from: b */
        ImageView f11495b = (ImageView) this.itemView.findViewById(C2222R.C2224id.nrzs_app__home_item_himg);

        /* renamed from: f */
        TextView f11499f = (TextView) this.itemView.findViewById(C2222R.C2224id.nrzs_app__home_item_num);

        /* renamed from: g */
        TextView f11500g = (TextView) this.itemView.findViewById(C2222R.C2224id.nrzs_app__home_ietm_run);

        /* renamed from: c */
        ImageView f11496c = (ImageView) this.itemView.findViewById(C2222R.C2224id.nrzs_app__home_item_img_vip);

        /* renamed from: d */
        ImageView f11497d = (ImageView) this.itemView.findViewById(C2222R.C2224id.nrzs_app__home_item_onhook);

        C2278a(LayoutInflater layoutInflater, ViewGroup viewGroup, int i) {
            super(layoutInflater, viewGroup, i);
        }
    }
}
