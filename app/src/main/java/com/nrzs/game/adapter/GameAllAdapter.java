package com.nrzs.game.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.nrzs.base.router.RouterUtils;
import com.nrzs.base.router.provider.ProviderFactory;
import com.nrzs.data.game.bean.TopicInfo;
import com.nrzs.game.C2029R;
import com.nrzs.game.ui.activity.GameAllActivity;
import com.nrzs.moudleui.adapter.BaseViewHolder;
import com.nrzs.moudleui.pinnedheader.PinnedHeaderAdapter;
import java.util.List;
import org.slf4j.Marker;
import p110z1.CKU;
import p110z1.EventCollectManager;
import p110z1.EventConstants;
import p110z1.GlideImageUtils;
import p110z1.XnkjUtils;
import p110z1.aly;

/* loaded from: classes2.dex */
public class GameAllAdapter extends PinnedHeaderAdapter {

    /* renamed from: a */
    private static final int f10811a = 77;

    /* renamed from: b */
    private int f10812b;

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

    public GameAllAdapter(List<TopicInfo> list, int i) {
        super(list);
        this.f10812b = i;
    }

    @Override // com.nrzs.moudleui.adapter.BaseListAdapter
    /* renamed from: b */
    public BaseViewHolder mo18181b(LayoutInflater layoutInflater, ViewGroup viewGroup, int i, int i2) {
        if (i2 == 77) {
            return new C2037b(layoutInflater, viewGroup, i);
        }
        return new C2036a(layoutInflater, viewGroup, i);
    }

    @Override // com.nrzs.moudleui.adapter.BaseListAdapter
    /* renamed from: a */
    public void mo18183a(@NonNull BaseViewHolder baseViewHolder, int i) {
        if (baseViewHolder.getItemViewType() == 77) {
            C2037b bVar = (C2037b) baseViewHolder;
            TopicInfo topicInfo = (TopicInfo) this.f11255c.get(i);
            if (Marker.ANY_MARKER.equals(topicInfo.sortLetters)) {
                bVar.f10824a.setText("猜你喜欢");
            } else {
                bVar.f10824a.setText(topicInfo.sortLetters);
            }
        } else {
            C2036a aVar = (C2036a) baseViewHolder;
            final TopicInfo topicInfo2 = (TopicInfo) this.f11255c.get(i);
            if (topicInfo2.isVip == 1) {
                aVar.f10817a.setVisibility(8);
                aVar.f10819c.setVisibility(0);
            } else {
                aVar.f10817a.setVisibility(0);
                aVar.f10819c.setVisibility(8);
            }
            if (topicInfo2.localAppIcon != null) {
                GlideImageUtils.m11881a(aVar.f10818b, m18480d(), C2029R.C2030drawable.bird_bg_common_img, topicInfo2.localAppIcon);
            } else {
                GlideImageUtils.m11880a(aVar.f10818b, m18480d(), C2029R.C2030drawable.bird_bg_common_img, topicInfo2.ImgPath);
            }
            if (topicInfo2.SportBackGround != 1 || XnkjUtils.m12528a()) {
                aVar.f10820d.setVisibility(4);
            } else {
                aVar.f10820d.setVisibility(0);
            }
            aVar.f10821e.setText(topicInfo2.TopicName);
            if (aly.m12531a(1) == 1) {
                TextView textView = aVar.f10822f;
                textView.setText("辅助：" + topicInfo2.ScriptCount);
            } else {
                TextView textView2 = aVar.f10822f;
                textView2.setText("辅助：" + topicInfo2.VaScriptCount);
            }
            aVar.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.nrzs.game.adapter.GameAllAdapter.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (topicInfo2 != null && GameAllAdapter.this.f10812b != 2) {
                        RouterUtils.toGameTopic(topicInfo2.TopicName, (int) topicInfo2.TopicID);
                    }
                }
            });
            aVar.f10823g.setOnClickListener(new View.OnClickListener() { // from class: com.nrzs.game.adapter.GameAllAdapter.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    EventCollectManager.m12642a().m12640a(GameAllAdapter.this.m18480d(), "全部游戏运行", "全部游戏运行", EventConstants.f16440h);
                    if (GameAllAdapter.this.f10812b == 2) {
                        ProviderFactory.createXNKJRun().startRom(GameAllAdapter.this.m18480d(), topicInfo2);
                        if (GameAllAdapter.this.m18480d() instanceof GameAllActivity) {
                            ((GameAllActivity) GameAllAdapter.this.m18480d()).finish();
                            return;
                        }
                        return;
                    }
                    CKU aiqVar = new CKU();
                    Context context = view.getContext();
                    Class<?> cls = GameAllAdapter.this.m18480d().getClass();
                    TopicInfo topicInfo3 = topicInfo2;
                    aiqVar.m13031a(context, cls, topicInfo3, !TextUtils.isEmpty(topicInfo3.localAppPackage));
                }
            });
        }
    }

    @Override // com.nrzs.moudleui.adapter.BaseListAdapter
    /* renamed from: a */
    protected int mo18186a(int i) {
        if (i == 77) {
            return C2029R.layout.nrzs_item_game_title;
        }
        return C2029R.layout.nrzs_item_game_layout;
    }

    @Override // com.nrzs.moudleui.adapter.BaseListAdapter, android.support.v7.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        if (((TopicInfo) this.f11255c.get(i)).isFirst) {
            return 77;
        }
        return super.getItemViewType(i);
    }

    /* renamed from: c */
    public int m18829c(int i) {
        for (int i2 = 0; i2 < m18831a(); i2++) {
            if (((TopicInfo) this.f11255c.get(i2)).sortLetters.toUpperCase().charAt(0) == i) {
                return i2;
            }
        }
        return -1;
    }

    /* renamed from: a */
    public int m18831a() {
        if (this.f11255c == null || this.f11255c.isEmpty()) {
            return 0;
        }
        return this.f11255c.size();
    }

    /* renamed from: com.nrzs.game.adapter.GameAllAdapter$b */
    /* loaded from: classes2.dex */
    static class C2037b extends BaseViewHolder {

        /* renamed from: a */
        TextView f10824a = (TextView) this.itemView.findViewById(C2029R.C2031id.nrzs_game_tv_title);

        C2037b(LayoutInflater layoutInflater, ViewGroup viewGroup, int i) {
            super(layoutInflater, viewGroup, i);
        }
    }

    /* renamed from: com.nrzs.game.adapter.GameAllAdapter$a */
    /* loaded from: classes2.dex */
    static class C2036a extends BaseViewHolder {

        /* renamed from: a */
        ImageView f10817a = (ImageView) this.itemView.findViewById(C2029R.C2031id.nrzs_app__home_item_img_free);

        /* renamed from: e */
        TextView f10821e = (TextView) this.itemView.findViewById(C2029R.C2031id.nrzs_app__home_ietm_name);

        /* renamed from: b */
        ImageView f10818b = (ImageView) this.itemView.findViewById(C2029R.C2031id.nrzs_app__home_item_himg);

        /* renamed from: f */
        TextView f10822f = (TextView) this.itemView.findViewById(C2029R.C2031id.nrzs_app__home_item_num);

        /* renamed from: g */
        TextView f10823g = (TextView) this.itemView.findViewById(C2029R.C2031id.nrzs_app__home_ietm_run);

        /* renamed from: c */
        ImageView f10819c = (ImageView) this.itemView.findViewById(C2029R.C2031id.nrzs_app__home_item_img_vip);

        /* renamed from: d */
        ImageView f10820d = (ImageView) this.itemView.findViewById(C2029R.C2031id.nrzs_app__home_item_onhook);

        C2036a(LayoutInflater layoutInflater, ViewGroup viewGroup, int i) {
            super(layoutInflater, viewGroup, i);
        }
    }
}
