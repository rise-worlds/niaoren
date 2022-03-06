package com.lbd.xj.ui.view.gvp.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.lbd.xj.C1467R;
import p110z1.GVPAdapter;

/* renamed from: com.lbd.xj.ui.view.gvp.widget.GridViewPager */
/* loaded from: classes.dex */
public class GridViewPager extends ViewPager {

    /* renamed from: a */
    private int f9960a;

    /* renamed from: b */
    private int f9961b;

    /* renamed from: c */
    private int f9962c;

    /* renamed from: d */
    private int f9963d;

    /* renamed from: e */
    private C1656b f9964e;

    public GridViewPager(Context context) {
        this(context, null);
    }

    public GridViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f9961b = 4;
        this.f9962c = 4;
        m19291a(context, attributeSet);
    }

    /* renamed from: a */
    private void m19291a(Context context, AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C1467R.styleable.GridViewPager);
            this.f9961b = obtainStyledAttributes.getInteger(C1467R.styleable.GridViewPager_page_size, 4);
            this.f9962c = obtainStyledAttributes.getInteger(C1467R.styleable.GridViewPager_num_columns, 4);
            obtainStyledAttributes.recycle();
        }
    }

    public void setGVPAdapter(GVPAdapter adwVar) {
        if (adwVar != null) {
            this.f9960a = adwVar.m14232b();
            if (this.f9960a > 0) {
                this.f9964e = new C1656b(adwVar);
                setAdapter(this.f9964e);
                return;
            }
            throw new RuntimeException("条目总数必须大于0");
        }
        throw new IllegalArgumentException("适配器不能为空");
    }

    /* renamed from: a */
    public void m19292a() {
        C1656b bVar = this.f9964e;
        if (bVar != null) {
            bVar.notifyDataSetChanged();
        }
    }

    public void setPageSize(int i) {
        this.f9961b = i;
    }

    public int getPageSize() {
        return this.f9961b;
    }

    public void setNumColumns(int i) {
        this.f9962c = i;
    }

    public int getNumColumns() {
        return this.f9962c;
    }

    public int getPageCount() {
        return this.f9963d;
    }

    /* renamed from: com.lbd.xj.ui.view.gvp.widget.GridViewPager$b */
    /* loaded from: classes.dex */
    private class C1656b extends PagerAdapter {

        /* renamed from: b */
        private GVPAdapter f9970b;

        @Override // android.support.v4.view.PagerAdapter
        public int getItemPosition(Object obj) {
            return -2;
        }

        @Override // android.support.v4.view.PagerAdapter
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        public C1656b(GVPAdapter adwVar) {
            this.f9970b = adwVar;
        }

        @Override // android.support.v4.view.PagerAdapter
        public int getCount() {
            GridViewPager gridViewPager = GridViewPager.this;
            gridViewPager.f9963d = gridViewPager.f9960a % GridViewPager.this.f9961b == 0 ? GridViewPager.this.f9960a / GridViewPager.this.f9961b : (GridViewPager.this.f9960a / GridViewPager.this.f9961b) + 1;
            return GridViewPager.this.f9963d;
        }

        /* renamed from: a */
        public View instantiateItem(ViewGroup viewGroup, int i) {
            View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(C1467R.layout.widget_recycler_view_pager, (ViewGroup) null);
            RecyclerView recyclerView = (RecyclerView) inflate.findViewById(C1467R.C1469id.rv_rvg_pager);
            recyclerView.setLayoutManager(new GridLayoutManager(viewGroup.getContext(), GridViewPager.this.f9962c));
            m19284a(recyclerView, i);
            viewGroup.addView(inflate);
            return inflate;
        }

        @Override // android.support.v4.view.PagerAdapter
        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
            viewGroup.removeView((View) obj);
        }

        /* renamed from: a */
        private void m19284a(RecyclerView recyclerView, int i) {
            RecyclerView.Adapter adapter = recyclerView.getAdapter();
            if (adapter != null) {
                adapter.notifyDataSetChanged();
                return;
            }
            C1654a aVar = new C1654a(this.f9970b, i);
            recyclerView.setAdapter(aVar);
            RecyclerView.ItemDecoration a = aVar.m19285a();
            if (a != null) {
                recyclerView.addItemDecoration(a);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.lbd.xj.ui.view.gvp.widget.GridViewPager$a */
    /* loaded from: classes.dex */
    public class C1654a<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        /* renamed from: b */
        private GVPAdapter<T> f9966b;

        /* renamed from: c */
        private int f9967c;

        public C1654a(GVPAdapter<T> adwVar, int i) {
            this.f9966b = adwVar;
            this.f9967c = i;
        }

        @Override // android.support.v7.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new C1655a(LayoutInflater.from(viewGroup.getContext()).inflate(this.f9966b.m14240a(), (ViewGroup) null));
        }

        @Override // android.support.v7.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
            int i2 = (this.f9967c * GridViewPager.this.f9961b) + i;
            viewHolder.itemView.setTag(Integer.valueOf(i2));
            this.f9966b.m14231b(viewHolder.itemView, i2, this.f9966b.m14239a(i2));
        }

        @Override // android.support.v7.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.f9967c < GridViewPager.this.f9963d + (-1) ? GridViewPager.this.f9961b : GridViewPager.this.f9960a - (GridViewPager.this.f9961b * (GridViewPager.this.f9963d - 1));
        }

        /* renamed from: a */
        public RecyclerView.ItemDecoration m19285a() {
            return this.f9966b.m14229c();
        }

        /* renamed from: com.lbd.xj.ui.view.gvp.widget.GridViewPager$a$a */
        /* loaded from: classes.dex */
        private class C1655a extends RecyclerView.ViewHolder {
            public C1655a(View view) {
                super(view);
            }
        }
    }
}
