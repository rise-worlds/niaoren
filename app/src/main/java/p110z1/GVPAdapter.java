package p110z1;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import java.util.List;

/* renamed from: z1.adw */
/* loaded from: classes3.dex */
public abstract class GVPAdapter<T> {

    /* renamed from: a */
    private int f15402a;

    /* renamed from: b */
    private List<T> f15403b;

    /* renamed from: c */
    private RecyclerView.ItemDecoration f15404c;

    /* renamed from: d */
    private OnItemClickListener<T> f15405d;

    /* renamed from: e */
    private OnItemLongClickListener<T> f15406e;

    /* renamed from: a */
    public abstract void mo14237a(View view, int i, T t);

    public GVPAdapter(int i, List<T> list) {
        this.f15402a = i;
        this.f15403b = list;
    }

    /* renamed from: a */
    public void m14236a(List<T> list) {
        this.f15403b = list;
    }

    /* renamed from: a */
    public final int m14240a() {
        return this.f15402a;
    }

    /* renamed from: b */
    public int m14232b() {
        List<T> list = this.f15403b;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    /* renamed from: a */
    public final T m14239a(int i) {
        return this.f15403b.get(i);
    }

    /* renamed from: b */
    public final void m14231b(final View view, final int i, final T t) {
        view.setOnClickListener(new View.OnClickListener() { // from class: z1.adw.1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (GVPAdapter.this.f15405d != null) {
                    GVPAdapter.this.f15405d.mo14228a(view, i, t);
                }
            }
        });
        view.setOnLongClickListener(new View.OnLongClickListener() { // from class: z1.adw.2
            /* JADX WARN: Multi-variable type inference failed */
            @Override // android.view.View.OnLongClickListener
            public boolean onLongClick(View view2) {
                if (GVPAdapter.this.f15406e != null) {
                    return GVPAdapter.this.f15406e.m14227a(view, i, t);
                }
                return false;
            }
        });
        mo14237a(view, i, t);
    }

    /* renamed from: a */
    public void m14238a(RecyclerView.ItemDecoration itemDecoration) {
        this.f15404c = itemDecoration;
    }

    /* renamed from: c */
    public RecyclerView.ItemDecoration m14229c() {
        return this.f15404c;
    }

    /* renamed from: a */
    public void m14234a(OnItemClickListener<T> adxVar) {
        this.f15405d = adxVar;
    }

    /* renamed from: a */
    public void m14233a(OnItemLongClickListener<T> adyVar) {
        this.f15406e = adyVar;
    }
}
