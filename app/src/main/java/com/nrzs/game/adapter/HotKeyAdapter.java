package com.nrzs.game.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.nrzs.data.game.bean.HotKeyBean;
import com.nrzs.game.C2029R;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.nrzs.game.adapter.a */
/* loaded from: classes2.dex */
public class HotKeyAdapter extends ArrayAdapter<HotKeyBean> {

    /* renamed from: a */
    private Context f10852a;

    /* renamed from: b */
    private List<HotKeyBean> f10853b;

    public HotKeyAdapter(Context context, List<HotKeyBean> list) {
        super(context, C2029R.layout.nrzs_game_search_key_item, list);
        this.f10853b = new ArrayList();
        this.f10852a = context;
        this.f10853b = list;
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        View view2;
        C2048a aVar;
        if (view == null) {
            aVar = new C2048a();
            view2 = LayoutInflater.from(this.f10852a).inflate(C2029R.layout.nrzs_game_search_key_item, (ViewGroup) null);
            aVar.f10854a = (TextView) view2.findViewById(C2029R.C2031id.tv_hot_key);
            view2.setTag(aVar);
        } else {
            aVar = (C2048a) view.getTag();
            view2 = view;
        }
        HotKeyBean hotKeyBean = this.f10853b.get(i);
        aVar.f10854a.setText(hotKeyBean.getKeyTitle());
        aVar.f10854a.setTag(Integer.valueOf(i));
        if (hotKeyBean.getMark().equals("H")) {
            aVar.f10854a.setTextColor(this.f10852a.getResources().getColor(C2029R.color.red_FF7474));
            aVar.f10854a.setBackgroundResource(C2029R.C2030drawable.bird_bg_search_hot_item_red);
        }
        return view2;
    }

    /* compiled from: HotKeyAdapter.java */
    /* renamed from: com.nrzs.game.adapter.a$a */
    /* loaded from: classes2.dex */
    class C2048a {

        /* renamed from: a */
        TextView f10854a;

        C2048a() {
        }
    }
}
