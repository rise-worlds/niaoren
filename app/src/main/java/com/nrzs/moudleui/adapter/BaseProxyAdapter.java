package com.nrzs.moudleui.adapter;

import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.nrzs.moudleui.adapter.BaseViewHolder;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/* loaded from: classes2.dex */
public abstract class BaseProxyAdapter<T, VH extends BaseViewHolder> extends PagedListAdapter<T, VH> {

    /* renamed from: b */
    private static final int f11271b = 99;

    /* renamed from: c */
    private static final int f11272c = 100;

    /* renamed from: d */
    private static final int f11273d = 101;

    /* renamed from: e */
    private static final int f11274e = 102;

    /* renamed from: a */
    private Context f11275a;

    /* renamed from: f */
    private FrameLayout f11276f;

    /* renamed from: g */
    private LinearLayout f11277g;

    /* renamed from: h */
    private LinearLayout f11278h;

    /* renamed from: a */
    public abstract void m18456a(View view);

    /* renamed from: a */
    protected boolean m18457a(int i) {
        return i == 99 || i == 100;
    }

    /* renamed from: b */
    public abstract void m18446b(View view);

    /* renamed from: b */
    public abstract void m18445b(@NonNull VH vh, int i);

    /* renamed from: d */
    protected abstract int m18442d();

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.support.p006v7.widget.RecyclerView.Adapter
    public /* synthetic */ void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        m18450a((BaseProxyAdapter<T, VH>) ((BaseViewHolder) viewHolder), i);
    }

    protected BaseProxyAdapter(@NonNull DiffUtil.ItemCallback<T> itemCallback) {
        super(itemCallback);
    }

    @NonNull
    /* renamed from: a */
    public VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        this.f11275a = viewGroup.getContext();
        switch (i) {
            case 99:
                return m18443c(this.f11277g);
            case 100:
                return m18443c(this.f11278h);
            default:
                VH vh = (VH) new BaseViewHolder(LayoutInflater.from(this.f11275a), viewGroup, m18442d());
                m18456a(vh.itemView);
                m18446b(vh.itemView);
                return vh;
        }
    }

    /* renamed from: a */
    public void m18450a(@NonNull VH vh, int i) {
        int itemViewType = vh.getItemViewType();
        if (itemViewType != 0) {
            switch (itemViewType) {
                case 99:
                case 100:
                    return;
                default:
                    m18445b(vh, i - m18458a());
                    return;
            }
        } else {
            m18445b(vh, i - m18458a());
        }
    }

    @Override // android.support.p006v7.widget.RecyclerView.Adapter
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() { // from class: com.nrzs.moudleui.adapter.BaseProxyAdapter.1
                @Override // android.support.p006v7.widget.GridLayoutManager.SpanSizeLookup
                public int getSpanSize(int i) {
                    if (BaseProxyAdapter.this.m18457a(BaseProxyAdapter.this.getItemViewType(i))) {
                        return gridLayoutManager.getSpanCount();
                    }
                    return 1;
                }
            });
        }
    }

    /* renamed from: c */
    private VH m18443c(View view) {
        VH vh;
        Class cls = null;
        for (Class<?> cls2 = getClass(); cls == null && cls2 != null; cls2 = cls2.getSuperclass()) {
            cls = m18449a(cls2);
        }
        if (cls == null) {
            vh = (VH) new BaseViewHolder(view);
        } else {
            vh = m18448a(cls, view);
        }
        return vh != null ? vh : (VH) new BaseViewHolder(view);
    }

    /* renamed from: a */
    private VH m18448a(Class cls, View view) {
        try {
            if (!cls.isMemberClass() || Modifier.isStatic(cls.getModifiers())) {
                Constructor<T> declaredConstructor = cls.getDeclaredConstructor(View.class);
                declaredConstructor.setAccessible(true);
                return (VH) ((BaseViewHolder) declaredConstructor.newInstance(view));
            }
            Constructor<T> declaredConstructor2 = cls.getDeclaredConstructor(getClass(), View.class);
            declaredConstructor2.setAccessible(true);
            return (VH) ((BaseViewHolder) declaredConstructor2.newInstance(this, view));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        } catch (InstantiationException e2) {
            e2.printStackTrace();
            return null;
        } catch (NoSuchMethodException e3) {
            e3.printStackTrace();
            return null;
        } catch (InvocationTargetException e4) {
            e4.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    private Class m18449a(Class cls) {
        Type[] actualTypeArguments;
        Type genericSuperclass = cls.getGenericSuperclass();
        if (!(genericSuperclass instanceof ParameterizedType)) {
            return null;
        }
        for (Type type : ((ParameterizedType) genericSuperclass).getActualTypeArguments()) {
            if (type instanceof Class) {
                Class cls2 = (Class) type;
                if (BaseViewHolder.class.isAssignableFrom(cls2)) {
                    return cls2;
                }
            } else if (type instanceof ParameterizedType) {
                Type rawType = ((ParameterizedType) type).getRawType();
                if (rawType instanceof Class) {
                    Class cls3 = (Class) rawType;
                    if (BaseViewHolder.class.isAssignableFrom(cls3)) {
                        return cls3;
                    }
                } else {
                    continue;
                }
            } else {
                continue;
            }
        }
        return null;
    }

    /* renamed from: a */
    public int m18452a(View view, int i, boolean z) {
        return m18453a(view, i, 1, z);
    }

    /* renamed from: a */
    public int m18453a(View view, int i, int i2, boolean z) {
        if (this.f11277g == null) {
            this.f11277g = new LinearLayout(view.getContext());
            if (i2 == 1) {
                this.f11277g.setOrientation(1);
                this.f11277g.setLayoutParams(new RecyclerView.LayoutParams(-1, -1));
            } else {
                this.f11277g.setOrientation(0);
                this.f11277g.setLayoutParams(new RecyclerView.LayoutParams(-2, -1));
            }
        }
        int childCount = this.f11277g.getChildCount();
        if (i < 0 || i > childCount) {
            i = childCount;
        }
        this.f11277g.addView(view, i);
        if (this.f11277g.getChildCount() == 1) {
            notifyItemInserted(0);
        }
        return i;
    }

    /* renamed from: a */
    public int m18455a(View view, int i) {
        return m18454a(view, i, 1);
    }

    /* renamed from: a */
    public int m18454a(View view, int i, int i2) {
        int a;
        if (this.f11278h == null) {
            this.f11278h = new LinearLayout(view.getContext());
            if (i2 == 1) {
                this.f11278h.setOrientation(1);
                this.f11278h.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
            } else {
                this.f11278h.setOrientation(0);
                this.f11278h.setLayoutParams(new RecyclerView.LayoutParams(-2, -1));
            }
        }
        int childCount = this.f11278h.getChildCount();
        if (i < 0 || i > childCount) {
            i = childCount;
        }
        this.f11278h.addView(view, i);
        if (this.f11278h.getChildCount() == 1 && (a = m18458a() + super.getItemCount()) != -1) {
            notifyItemInserted(a);
        }
        return i;
    }

    @Override // android.arch.paging.PagedListAdapter, android.support.p006v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        return m18458a() + super.getItemCount() + m18447b();
    }

    @Override // android.support.p006v7.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        int a = m18458a();
        if (i < a) {
            return 99;
        }
        int i2 = i - a;
        int itemCount = super.getItemCount();
        if (i2 < itemCount) {
            return super.getItemViewType(i);
        }
        if (i2 - itemCount < m18447b()) {
            return 100;
        }
        return super.getItemViewType(i);
    }

    /* renamed from: a */
    public int m18458a() {
        LinearLayout linearLayout = this.f11277g;
        return (linearLayout == null || linearLayout.getChildCount() == 0) ? 0 : 1;
    }

    /* renamed from: b */
    public int m18447b() {
        LinearLayout linearLayout = this.f11278h;
        return (linearLayout == null || linearLayout.getChildCount() == 0) ? 0 : 1;
    }

    /* renamed from: c */
    public Context m18444c() {
        return this.f11275a;
    }
}
