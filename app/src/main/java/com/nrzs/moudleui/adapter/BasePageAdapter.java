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
public abstract class BasePageAdapter<T, VH extends BaseViewHolder> extends PagedListAdapter<T, VH> {

    /* renamed from: b */
    private static final int f11261b = 99;

    /* renamed from: c */
    private static final int f11262c = 100;

    /* renamed from: d */
    private static final int f11263d = 101;

    /* renamed from: e */
    private static final int f11264e = 102;

    /* renamed from: a */
    private Context f11265a;

    /* renamed from: f */
    private FrameLayout f11266f;

    /* renamed from: g */
    private LinearLayout f11267g;

    /* renamed from: h */
    private LinearLayout f11268h;

    /* renamed from: a */
    protected abstract int mo18478a();

    /* renamed from: a */
    public abstract void mo18476a(View view);

    /* renamed from: a */
    public abstract void mo18470a(@NonNull VH vh, int i);

    /* renamed from: a */
    protected boolean m18477a(int i) {
        return i == 99 || i == 100;
    }

    /* renamed from: b */
    public abstract void mo18466b(View view);

    /* JADX INFO: Access modifiers changed from: protected */
    public BasePageAdapter(@NonNull DiffUtil.ItemCallback<T> itemCallback) {
        super(itemCallback);
    }

    @NonNull
    /* renamed from: a */
    public VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        this.f11265a = viewGroup.getContext();
        switch (i) {
            case 99:
                return m18459e(this.f11267g);
            case 100:
                return m18459e(this.f11268h);
            default:
                VH vh = (VH) new BaseViewHolder(LayoutInflater.from(this.f11265a), viewGroup, mo18478a());
                mo18476a(vh.itemView);
                mo18466b(vh.itemView);
                return vh;
        }
    }

    /* renamed from: b */
    public void onBindViewHolder(@NonNull VH vh, int i) {
        int itemViewType = vh.getItemViewType();
        if (itemViewType != 0) {
            switch (itemViewType) {
                case 99:
                case 100:
                    return;
                default:
                    mo18470a((BasePageAdapter<T, VH>) vh, i - m18467b());
                    return;
            }
        } else {
            mo18470a((BasePageAdapter<T, VH>) vh, i - m18467b());
        }
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() { // from class: com.nrzs.moudleui.adapter.BasePageAdapter.1
                @Override // android.support.v7.widget.GridLayoutManager.SpanSizeLookup
                public int getSpanSize(int i) {
                    if (BasePageAdapter.this.m18477a(BasePageAdapter.this.getItemViewType(i))) {
                        return gridLayoutManager.getSpanCount();
                    }
                    return 1;
                }
            });
        }
    }

    /* renamed from: e */
    private VH m18459e(View view) {
        VH vh;
        Class cls = null;
        for (Class<?> cls2 = getClass(); cls == null && cls2 != null; cls2 = cls2.getSuperclass()) {
            cls = m18469a(cls2);
        }
        if (cls == null) {
            vh = (VH) new BaseViewHolder(view);
        } else {
            vh = m18468a(cls, view);
        }
        return vh != null ? vh : (VH) new BaseViewHolder(view);
    }

    /* renamed from: a */
    private VH m18468a(Class cls, View view) {
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
    private Class m18469a(Class cls) {
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
    public int m18472a(View view, int i, boolean z) {
        return m18473a(view, i, 1, z);
    }

    /* renamed from: a */
    public int m18473a(View view, int i, int i2, boolean z) {
        if (this.f11267g == null) {
            this.f11267g = new LinearLayout(view.getContext());
            if (i2 == 1) {
                this.f11267g.setOrientation(1);
                this.f11267g.setLayoutParams(new RecyclerView.LayoutParams(-1, -1));
            } else {
                this.f11267g.setOrientation(0);
                this.f11267g.setLayoutParams(new RecyclerView.LayoutParams(-2, -1));
            }
        }
        int childCount = this.f11267g.getChildCount();
        if (i < 0 || i > childCount) {
            i = childCount;
        }
        this.f11267g.addView(view, i);
        if (this.f11267g.getChildCount() == 1) {
            notifyItemInserted(0);
        }
        return i;
    }

    /* renamed from: a */
    public int m18475a(View view, int i) {
        return m18474a(view, i, 1);
    }

    /* renamed from: a */
    public int m18474a(View view, int i, int i2) {
        int b;
        if (this.f11268h == null) {
            this.f11268h = new LinearLayout(view.getContext());
            if (i2 == 1) {
                this.f11268h.setOrientation(1);
                this.f11268h.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
            } else {
                this.f11268h.setOrientation(0);
                this.f11268h.setLayoutParams(new RecyclerView.LayoutParams(-2, -1));
            }
        }
        int childCount = this.f11268h.getChildCount();
        if (i < 0 || i > childCount) {
            i = childCount;
        }
        this.f11268h.addView(view, i);
        if (this.f11268h.getChildCount() == 1 && (b = m18467b() + super.getItemCount()) != -1) {
            notifyItemInserted(b);
        }
        return i;
    }

    /* renamed from: c */
    public void m18463c(View view) {
        if (m18467b() != 0) {
            this.f11267g.removeView(view);
            if (this.f11267g.getChildCount() == 0) {
                notifyItemRemoved(0);
            }
        }
    }

    /* renamed from: d */
    public void m18461d(View view) {
        int e;
        if (m18464c() != 0) {
            this.f11268h.removeView(view);
            if (this.f11268h.getChildCount() == 0 && (e = m18460e()) != -1) {
                notifyItemRemoved(e);
            }
        }
    }

    /* renamed from: e */
    private int m18460e() {
        return m18467b() + getItemCount();
    }

    @Override // android.arch.paging.PagedListAdapter, android.support.v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        return m18467b() + super.getItemCount() + m18464c();
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        int b = m18467b();
        if (i < b) {
            return 99;
        }
        int i2 = i - b;
        int itemCount = super.getItemCount();
        if (i2 < itemCount) {
            return super.getItemViewType(i);
        }
        if (i2 - itemCount < m18464c()) {
            return 100;
        }
        return super.getItemViewType(i);
    }

    /* renamed from: b */
    public int m18467b() {
        LinearLayout linearLayout = this.f11267g;
        return (linearLayout == null || linearLayout.getChildCount() == 0) ? 0 : 1;
    }

    /* renamed from: c */
    public int m18464c() {
        LinearLayout linearLayout = this.f11268h;
        return (linearLayout == null || linearLayout.getChildCount() == 0) ? 0 : 1;
    }

    /* renamed from: d */
    public Context m18462d() {
        return this.f11265a;
    }
}
