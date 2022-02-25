package com.nrzs.moudleui.pinnedheader;

import com.nrzs.moudleui.adapter.BaseListAdapter;
import com.nrzs.moudleui.adapter.BaseViewHolder;
import java.util.List;

/* loaded from: classes2.dex */
public abstract class PinnedHeaderAdapter<T, VH extends BaseViewHolder> extends BaseListAdapter<T, VH> {
    /* renamed from: b */
    public abstract boolean mo18296b(int i);

    /* JADX INFO: Access modifiers changed from: protected */
    public PinnedHeaderAdapter(List list) {
        super(list);
    }
}
