package com.angel.nrzs.adapter;

import android.support.annotation.NonNull;
import android.support.p006v7.util.DiffUtil;
import android.view.View;
import com.angel.nrzs.C0692R;
import com.nrzs.moudleui.adapter.BasePageAdapter;
import com.nrzs.moudleui.adapter.BaseViewHolder;
import java.util.List;

/* loaded from: classes.dex */
public class MyAdapter extends BasePageAdapter<Demo, BaseViewHolder> {
    @Override // com.nrzs.moudleui.adapter.BasePageAdapter
    /* renamed from: a */
    protected int mo18478a() {
        return C0692R.layout.f3474gk;
    }

    @Override // com.nrzs.moudleui.adapter.BasePageAdapter
    /* renamed from: a */
    public void mo18476a(View view) {
    }

    @Override // com.nrzs.moudleui.adapter.BasePageAdapter
    /* renamed from: a */
    public void mo18470a(@NonNull BaseViewHolder baseViewHolder, int i) {
    }

    @Override // com.nrzs.moudleui.adapter.BasePageAdapter
    /* renamed from: b */
    public void mo18466b(View view) {
    }

    public MyAdapter(@NonNull DiffUtil.ItemCallback<Demo> itemCallback, List<Demo> list) {
        super(itemCallback);
    }
}
