package com.nrzs.moudleui.pinnedheader;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.p006v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;

/* loaded from: classes2.dex */
public class PinnedHeaderRecyclerView extends RecyclerView {

    /* renamed from: a */
    private AbstractC2212a f11294a;

    /* renamed from: b */
    private boolean f11295b;

    /* renamed from: com.nrzs.moudleui.pinnedheader.PinnedHeaderRecyclerView$a */
    /* loaded from: classes2.dex */
    public interface AbstractC2212a {
        /* renamed from: a */
        void m18429a(int i);
    }

    public PinnedHeaderRecyclerView(Context context) {
        super(context);
    }

    public PinnedHeaderRecyclerView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public PinnedHeaderRecyclerView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setOnPinnedHeaderClickListener(AbstractC2212a aVar) {
        this.f11294a = aVar;
    }

    @Override // android.support.p006v7.widget.RecyclerView, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.f11294a == null) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        IPinnedHeaderDecoration pinnedHeaderDecoration = getPinnedHeaderDecoration();
        if (pinnedHeaderDecoration == null) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        Rect a = pinnedHeaderDecoration.mo18428a();
        int b = pinnedHeaderDecoration.mo18427b();
        if (a == null || b == -1) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        if (motionEvent.getAction() == 0 && a.contains((int) motionEvent.getX(), (int) motionEvent.getY())) {
            return true;
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    @Override // android.support.p006v7.widget.RecyclerView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.f11294a == null) {
            return super.onTouchEvent(motionEvent);
        }
        IPinnedHeaderDecoration pinnedHeaderDecoration = getPinnedHeaderDecoration();
        if (pinnedHeaderDecoration == null) {
            return super.onTouchEvent(motionEvent);
        }
        Rect a = pinnedHeaderDecoration.mo18428a();
        int b = pinnedHeaderDecoration.mo18427b();
        if (a == null || b == -1) {
            return super.onTouchEvent(motionEvent);
        }
        switch (motionEvent.getAction()) {
            case 0:
                this.f11295b = false;
                if (a.contains((int) motionEvent.getX(), (int) motionEvent.getY())) {
                    this.f11295b = true;
                    return true;
                }
                break;
            case 1:
            case 3:
                float x = motionEvent.getX();
                float y = motionEvent.getY();
                if (!this.f11295b || !a.contains((int) x, (int) y)) {
                    this.f11295b = false;
                    break;
                } else {
                    this.f11294a.m18429a(b);
                    this.f11295b = false;
                    return true;
                }
                break;
            case 2:
                if (this.f11295b) {
                    if (a.contains((int) motionEvent.getX(), (int) motionEvent.getY())) {
                        return true;
                    }
                    MotionEvent obtain = MotionEvent.obtain(motionEvent);
                    obtain.setAction(3);
                    super.dispatchTouchEvent(obtain);
                    MotionEvent obtain2 = MotionEvent.obtain(motionEvent);
                    obtain2.setAction(0);
                    return super.dispatchTouchEvent(obtain2);
                }
                break;
        }
        return super.onTouchEvent(motionEvent);
    }

    public IPinnedHeaderDecoration getPinnedHeaderDecoration() {
        RecyclerView.ItemDecoration itemDecorationAt;
        int i = 0;
        do {
            itemDecorationAt = getItemDecorationAt(i);
            if (itemDecorationAt instanceof IPinnedHeaderDecoration) {
                return (IPinnedHeaderDecoration) itemDecorationAt;
            }
            i++;
        } while (itemDecorationAt != null);
        return null;
    }
}
