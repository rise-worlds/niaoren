package com.nrzs.game.ui.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import com.nrzs.data.game.bean.TopicListBean;
import com.nrzs.game.C2029R;
import com.nrzs.game.adapter.SearchGameResultAdapter;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.nrzs.game.ui.view.SearchResultGameGridView */
/* loaded from: classes2.dex */
public class SearchResultGameGridView extends LinearLayout {

    /* renamed from: a */
    private NRZSGridView f11127a;

    /* renamed from: b */
    private SearchGameResultAdapter f11128b;

    /* renamed from: c */
    private List<TopicListBean> f11129c;

    /* renamed from: d */
    private Context f11130d;

    /* renamed from: a */
    private void m18589a() {
    }

    public SearchResultGameGridView(Context context) {
        super(context);
        m18587b(context);
        m18588a(context);
        m18589a();
    }

    /* renamed from: a */
    private void m18588a(Context context) {
        if (this.f11129c == null) {
            this.f11129c = new ArrayList();
        }
        this.f11128b = new SearchGameResultAdapter(context, this.f11129c);
        this.f11127a.setAdapter((ListAdapter) this.f11128b);
    }

    /* renamed from: b */
    private void m18587b(Context context) {
        this.f11130d = context;
        LayoutInflater.from(context).inflate(C2029R.layout.nrzs_game_search_result_head_view, this);
        this.f11127a = (NRZSGridView) findViewById(C2029R.C2031id.gv_search_game_result);
    }

    public void setData(List<TopicListBean> list) {
        this.f11129c = list;
        this.f11128b = new SearchGameResultAdapter(this.f11130d, this.f11129c);
        this.f11127a.setAdapter((ListAdapter) this.f11128b);
    }
}
