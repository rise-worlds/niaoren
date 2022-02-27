package com.nrzs.moudleui.pinnedheader;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import p110z1.MemoryConstants;

/* loaded from: classes2.dex */
public class PinnedHeaderItemDecoration extends RecyclerView.ItemDecoration implements IPinnedHeaderDecoration {

    /* renamed from: a */
    private Rect f11292a = null;

    /* renamed from: b */
    private int f11293b = -1;

    @Override // android.support.p006v7.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
    }

    /* JADX WARN: Type inference failed for: r2v2, types: [com.nrzs.moudleui.adapter.BaseViewHolder] */
    /* JADX WARN: Unknown variable types count: 1 */
    @Override // android.support.p006v7.widget.RecyclerView.ItemDecoration
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onDrawOver(android.graphics.Canvas r7, android.support.p006v7.widget.RecyclerView r8, android.support.p006v7.widget.RecyclerView.State r9) {
        /*
            r6 = this;
            super.onDrawOver(r7, r8, r9)
            android.support.v7.widget.RecyclerView$Adapter r9 = r8.getAdapter()
            boolean r9 = r9 instanceof com.nrzs.moudleui.pinnedheader.PinnedHeaderAdapter
            if (r9 == 0) goto L_0x00ad
            int r9 = r8.getChildCount()
            if (r9 <= 0) goto L_0x00ad
            android.support.v7.widget.RecyclerView$Adapter r9 = r8.getAdapter()
            com.nrzs.moudleui.pinnedheader.PinnedHeaderAdapter r9 = (com.nrzs.moudleui.pinnedheader.PinnedHeaderAdapter) r9
            r0 = 0
            android.view.View r1 = r8.getChildAt(r0)
            int r1 = r8.getChildAdapterPosition(r1)
            int r1 = r6.m18431a(r1, r9)
            r6.f11293b = r1
            r2 = -1
            if (r1 == r2) goto L_0x00aa
            int r2 = r9.getItemViewType(r1)
            com.nrzs.moudleui.adapter.BaseViewHolder r2 = r9.onCreateViewHolder(r8, r2)
            r9.m18484b(r2, r1)
            android.view.View r1 = r2.itemView
            r6.m18430a(r1, r8)
            r2 = 0
            r3 = 0
        L_0x003b:
            int r4 = r8.getChildCount()
            if (r2 >= r4) goto L_0x0064
            android.view.View r4 = r8.getChildAt(r2)
            int r4 = r8.getChildAdapterPosition(r4)
            boolean r4 = r9.mo18296b(r4)
            if (r4 == 0) goto L_0x0061
            android.view.View r4 = r8.getChildAt(r2)
            int r4 = r4.getTop()
            int r5 = r1.getHeight()
            if (r4 >= r5) goto L_0x0061
            if (r4 <= 0) goto L_0x0061
            int r4 = r4 - r5
            r3 = r4
        L_0x0061:
            int r2 = r2 + 1
            goto L_0x003b
        L_0x0064:
            int r9 = r7.save()
            android.view.ViewGroup$LayoutParams r2 = r1.getLayoutParams()
            android.support.v7.widget.RecyclerView$LayoutParams r2 = (android.support.p006v7.widget.RecyclerView.LayoutParams) r2
            if (r2 == 0) goto L_0x00a2
            int r2 = r2.leftMargin
            float r2 = (float) r2
            float r4 = (float) r3
            r7.translate(r2, r4)
            int r2 = r8.getWidth()
            int r4 = r1.getMeasuredHeight()
            r7.clipRect(r0, r0, r2, r4)
            r1.draw(r7)
            r7.restoreToCount(r9)
            android.graphics.Rect r7 = r6.f11292a
            if (r7 != 0) goto L_0x0093
            android.graphics.Rect r7 = new android.graphics.Rect
            r7.<init>()
            r6.f11292a = r7
        L_0x0093:
            android.graphics.Rect r7 = r6.f11292a
            int r8 = r8.getWidth()
            int r9 = r1.getMeasuredHeight()
            int r9 = r9 + r3
            r7.set(r0, r0, r8, r9)
            goto L_0x00ad
        L_0x00a2:
            java.lang.NullPointerException r7 = new java.lang.NullPointerException
            java.lang.String r8 = "PinnedHeaderItemDecoration"
            r7.<init>(r8)
            throw r7
        L_0x00aa:
            r7 = 0
            r6.f11292a = r7
        L_0x00ad:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.nrzs.moudleui.pinnedheader.PinnedHeaderItemDecoration.onDrawOver(android.graphics.Canvas, android.support.v7.widget.RecyclerView, android.support.v7.widget.RecyclerView$State):void");
    }

    /* renamed from: a */
    private int m18431a(int i, PinnedHeaderAdapter pinnedHeaderAdapter) {
        while (i >= 0) {
            if (pinnedHeaderAdapter.mo18296b(i)) {
                return i;
            }
            i--;
        }
        return -1;
    }

    /* renamed from: a */
    private void m18430a(View view, RecyclerView recyclerView) {
        int i;
        if (view.isLayoutRequested()) {
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
            if (layoutParams != null) {
                int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec((recyclerView.getMeasuredWidth() - layoutParams.leftMargin) - layoutParams.rightMargin, MemoryConstants.f21646d);
                if (layoutParams.height > 0) {
                    i = View.MeasureSpec.makeMeasureSpec(layoutParams.height, MemoryConstants.f21646d);
                } else {
                    i = View.MeasureSpec.makeMeasureSpec(0, 0);
                }
                view.measure(makeMeasureSpec, i);
                view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
                return;
            }
            throw new NullPointerException("PinnedHeaderItemDecoration");
        }
    }

    @Override // com.nrzs.moudleui.pinnedheader.IPinnedHeaderDecoration
    /* renamed from: a */
    public Rect mo18428a() {
        return this.f11292a;
    }

    @Override // com.nrzs.moudleui.pinnedheader.IPinnedHeaderDecoration
    /* renamed from: b */
    public int mo18427b() {
        return this.f11293b;
    }
}
