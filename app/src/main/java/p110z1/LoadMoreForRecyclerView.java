package p110z1;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;

/* renamed from: z1.aow */
/* loaded from: classes3.dex */
public class LoadMoreForRecyclerView {

    /* renamed from: a */
    private int f17097a;

    /* renamed from: b */
    private float f17098b;

    /* renamed from: c */
    private int f17099c;

    /* renamed from: d */
    private int f17100d = 0;

    /* renamed from: e */
    private float f17101e;

    /* renamed from: f */
    private AbstractC3827a f17102f;

    /* compiled from: LoadMoreForRecyclerView.java */
    /* renamed from: z1.aow$a */
    /* loaded from: classes3.dex */
    public interface AbstractC3827a {
        /* renamed from: a */
        void mo11902a();
    }

    public LoadMoreForRecyclerView(RecyclerView recyclerView, AbstractC3827a aVar) {
        m11914a(recyclerView, aVar);
    }

    /* renamed from: a */
    public void m11914a(final RecyclerView recyclerView, AbstractC3827a aVar) {
        this.f17102f = aVar;
        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: z1.aow.1
            @Override // android.support.v7.widget.RecyclerView.OnScrollListener
            public void onScrollStateChanged(RecyclerView recyclerView2, int i) {
                super.onScrollStateChanged(recyclerView2, i);
                LoadMoreForRecyclerView.this.f17099c = i;
                LoadMoreForRecyclerView.this.f17097a = linearLayoutManager.findLastVisibleItemPosition();
            }

            @Override // android.support.v7.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView recyclerView2, int i, int i2) {
                super.onScrolled(recyclerView2, i, i2);
                LoadMoreForRecyclerView.this.f17100d = i2;
            }
        });
        recyclerView.setOnTouchListener(new View.OnTouchListener() { // from class: z1.aow.2
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case 1:
                        if ((LoadMoreForRecyclerView.this.f17099c != 1 && LoadMoreForRecyclerView.this.f17099c != 2) || LoadMoreForRecyclerView.this.f17097a != recyclerView.getAdapter().getItemCount() - 1) {
                            return false;
                        }
                        if (LoadMoreForRecyclerView.this.f17100d > 0) {
                            if (LoadMoreForRecyclerView.this.f17102f == null) {
                                return false;
                            }
                            LoadMoreForRecyclerView.this.f17102f.mo11902a();
                            return false;
                        } else if (LoadMoreForRecyclerView.this.f17100d != 0 || LoadMoreForRecyclerView.this.f17101e >= 0.0f || LoadMoreForRecyclerView.this.f17102f == null) {
                            return false;
                        } else {
                            LoadMoreForRecyclerView.this.f17102f.mo11902a();
                            return false;
                        }
                    case 2:
                        LoadMoreForRecyclerView.this.f17101e = motionEvent.getY() - LoadMoreForRecyclerView.this.f17098b;
                        LoadMoreForRecyclerView.this.f17098b = motionEvent.getY();
                        return false;
                    default:
                        return false;
                }
            }
        });
    }
}
