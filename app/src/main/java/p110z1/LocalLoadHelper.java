package p110z1;

import android.content.Context;
import android.view.View;
import p110z1.LoadViewHelper;

/* renamed from: z1.app */
/* loaded from: classes3.dex */
public class LocalLoadHelper implements LoadViewHelper.AbstractC3846a {

    /* renamed from: e */
    protected View f17177e;

    /* renamed from: f */
    protected Context f17178f;

    /* renamed from: g */
    protected View.OnClickListener f17179g;

    /* renamed from: h */
    private LoadViewHelper.AbstractC3846a f17180h;

    /* renamed from: i */
    private View f17181i;

    /* renamed from: j */
    private View f17182j;

    /* renamed from: k */
    private View f17183k;

    public LocalLoadHelper(Context context, View view, View.OnClickListener onClickListener) {
        this.f17177e = view;
        this.f17178f = context;
        this.f17179g = OnClickListenerWrapper.m11696a(context, onClickListener);
    }

    public LocalLoadHelper(Context context, View view, View.OnClickListener onClickListener, boolean z) {
        this.f17177e = view;
        this.f17178f = context;
        if (z) {
            this.f17179g = OnClickListenerWrapper.m11696a(context, onClickListener);
        } else {
            this.f17179g = onClickListener;
        }
    }

    public LocalLoadHelper(Context context, LoadViewHelper.AbstractC3846a aVar) {
        this.f17178f = context;
        this.f17180h = aVar;
    }

    public LocalLoadHelper(Context context, View view, View view2, View view3, View view4, View.OnClickListener onClickListener) {
        this.f17177e = view;
        this.f17178f = context;
        this.f17179g = OnClickListenerWrapper.m11696a(context, onClickListener);
        this.f17181i = view2;
        this.f17182j = view3;
        this.f17183k = view4;
    }

    public LocalLoadHelper(Context context, View view, View view2, View view3, View view4, View.OnClickListener onClickListener, boolean z) {
        this.f17177e = view;
        this.f17178f = context;
        if (z) {
            this.f17179g = OnClickListenerWrapper.m11696a(context, onClickListener);
        } else {
            this.f17179g = onClickListener;
        }
        this.f17181i = view2;
        this.f17182j = view3;
        this.f17183k = view4;
    }

    @Override // p110z1.LoadViewHelper.AbstractC3846a
    /* renamed from: a */
    public View mo11690a() {
        LoadViewHelper.AbstractC3846a aVar = this.f17180h;
        if (aVar != null) {
            this.f17177e = aVar.mo11690a();
        }
        return this.f17177e;
    }

    @Override // p110z1.LoadViewHelper.AbstractC3846a
    /* renamed from: a */
    public View mo11689a(View view) {
        LoadViewHelper.AbstractC3846a aVar = this.f17180h;
        if (aVar != null) {
            return aVar.mo11689a(view);
        }
        View view2 = this.f17181i;
        if (view2 != null) {
            return view2;
        }
        if (this.f17177e == null) {
            return null;
        }
        return LoadstatueViewFactory.m11678a(this.f17178f, view);
    }

    @Override // p110z1.LoadViewHelper.AbstractC3846a
    /* renamed from: b */
    public View mo11688b(View view) {
        LoadViewHelper.AbstractC3846a aVar = this.f17180h;
        if (aVar != null) {
            return aVar.mo11688b(view);
        }
        View view2 = this.f17183k;
        if (view2 != null) {
            return view2;
        }
        if (this.f17177e == null) {
            return null;
        }
        return LoadstatueViewFactory.m11674b(this.f17178f, view, this.f17179g);
    }

    @Override // p110z1.LoadViewHelper.AbstractC3846a
    /* renamed from: c */
    public View mo11687c(View view) {
        LoadViewHelper.AbstractC3846a aVar = this.f17180h;
        if (aVar != null) {
            return aVar.mo11687c(view);
        }
        View view2 = this.f17182j;
        if (view2 != null) {
            return view2;
        }
        if (this.f17177e == null) {
            return null;
        }
        return LoadstatueViewFactory.m11677a(this.f17178f, view, this.f17179g);
    }
}
