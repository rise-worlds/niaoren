package com.nrzs.p067ft.adapter.manager;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/* renamed from: com.nrzs.ft.adapter.manager.FtVerLayoutManager */
/* loaded from: classes2.dex */
public class FtVerLayoutManager extends LinearLayoutManager {
    public FtVerLayoutManager(Context context) {
        super(context);
        setOrientation(1);
    }

    public FtVerLayoutManager(Context context, int i, boolean z) {
        super(context, i, z);
    }

    public FtVerLayoutManager(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    @Override // android.support.p006v7.widget.LinearLayoutManager, android.support.p006v7.widget.RecyclerView.LayoutManager
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(-1, -2);
    }
}
