package com.nrzs.moudleui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
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
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public abstract class BaseListAdapter<T, VH extends BaseViewHolder> extends RecyclerView.Adapter<VH> {

    /* renamed from: b */
    private static final int f11250b = 99;

    /* renamed from: d */
    private static final int f11251d = 100;

    /* renamed from: e */
    private static final int f11252e = 101;

    /* renamed from: f */
    private static final int f11253f = 102;

    /* renamed from: a */
    private Context f11254a;

    /* renamed from: c */
    public List<T> f11255c;

    /* renamed from: g */
    private FrameLayout f11256g;

    /* renamed from: h */
    private LinearLayout f11257h;

    /* renamed from: i */
    private LinearLayout f11258i;

    /* renamed from: a */
    protected abstract int mo18186a(int i);

    /* renamed from: a */
    public abstract void mo18184a(View view, int i);

    /* renamed from: a */
    public abstract void mo18183a(@NonNull VH vh, int i);

    /* renamed from: b */
    public abstract VH mo18181b(LayoutInflater layoutInflater, ViewGroup viewGroup, int i, int i2);

    /* renamed from: b */
    public abstract void mo18180b(View view, int i);

    /* renamed from: d */
    protected boolean m18479d(int i) {
        return i == 99 || i == 100;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.support.v7.widget.RecyclerView.Adapter
    public /* synthetic */ void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        m18484b((BaseListAdapter<T, VH>) ((BaseViewHolder) viewHolder), i);
    }

    public BaseListAdapter() {
        this.f11255c = new ArrayList();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public BaseListAdapter(List<T> list) {
        this.f11255c = new ArrayList();
        this.f11255c = list;
    }

    @NonNull
    /* renamed from: a */
    public VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        this.f11254a = viewGroup.getContext();
        switch (i) {
            case 99:
                return m18482c(this.f11257h);
            case 100:
                return m18482c(this.f11258i);
            default:
                return mo18181b(LayoutInflater.from(this.f11254a), viewGroup, mo18186a(i), i);
        }
    }

    /* renamed from: b */
    public void m18484b(@NonNull VH vh, int i) {
        int itemViewType = vh.getItemViewType();
        if (itemViewType != 0) {
            switch (itemViewType) {
                case 99:
                case 100:
                    return;
                default:
                    mo18183a((BaseListAdapter<T, VH>) vh, i - m18486b());
                    return;
            }
        } else {
            mo18183a((BaseListAdapter<T, VH>) vh, i - m18486b());
        }
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() { // from class: com.nrzs.moudleui.adapter.BaseListAdapter.1
                @Override // android.support.v7.widget.GridLayoutManager.SpanSizeLookup
                public int getSpanSize(int i) {
                    if (BaseListAdapter.this.m18479d(BaseListAdapter.this.getItemViewType(i))) {
                        return gridLayoutManager.getSpanCount();
                    }
                    return 1;
                }
            });
        }
    }

    /* renamed from: c */
    private VH m18482c(View view) {
        VH vh;
        Class cls = null;
        for (Class<?> cls2 = getClass(); cls == null && cls2 != null; cls2 = cls2.getSuperclass()) {
            cls = m18489a(cls2);
        }
        if (cls == null) {
            vh = (VH) new BaseViewHolder(view);
        } else {
            vh = m18488a(cls, view);
        }
        return vh != null ? vh : (VH) new BaseViewHolder(view);
    }

    /* renamed from: a */
    private VH m18488a(Class cls, View view) {
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
    private Class m18489a(Class cls) {
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
    public int m18491a(View view, int i, boolean z) {
        return m18492a(view, i, 1, z);
    }

    /* renamed from: a */
    public int m18492a(View view, int i, int i2, boolean z) {
        if (this.f11257h == null) {
            this.f11257h = new LinearLayout(view.getContext());
            if (i2 == 1) {
                this.f11257h.setOrientation(1);
                if (z) {
                    this.f11257h.setLayoutParams(new RecyclerView.LayoutParams(-1, -1));
                } else {
                    this.f11257h.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
                }
            } else {
                this.f11257h.setOrientation(0);
                this.f11257h.setLayoutParams(new RecyclerView.LayoutParams(-2, -1));
            }
        }
        int childCount = this.f11257h.getChildCount();
        if (i < 0 || i > childCount) {
            i = childCount;
        }
        this.f11257h.addView(view, i);
        if (this.f11257h.getChildCount() == 1) {
            notifyItemInserted(0);
        }
        return i;
    }

    /* renamed from: c */
    public int m18481c(View view, int i) {
        return m18493a(view, i, 1);
    }

    /* renamed from: a */
    public int m18493a(View view, int i, int i2) {
        int b;
        if (this.f11258i == null) {
            this.f11258i = new LinearLayout(view.getContext());
            if (i2 == 1) {
                this.f11258i.setOrientation(1);
                this.f11258i.setLayoutParams(new RecyclerView.LayoutParams(-1, -1));
            } else {
                this.f11258i.setOrientation(0);
                this.f11258i.setLayoutParams(new RecyclerView.LayoutParams(-2, -1));
            }
        }
        int childCount = this.f11258i.getChildCount();
        if (i < 0 || i > childCount) {
            i = childCount;
        }
        this.f11258i.addView(view, i);
        if (this.f11258i.getChildCount() == 1 && (b = m18486b() + this.f11255c.size()) != -1) {
            notifyItemInserted(b);
        }
        return i;
    }

    /* renamed from: a */
    public void m18494a(View view) {
        if (m18486b() != 0) {
            this.f11257h.removeView(view);
            if (this.f11257h.getChildCount() == 0) {
                notifyItemRemoved(0);
            }
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) this.f11257h.getLayoutParams();
            layoutParams.width = -1;
            layoutParams.height = -2;
            this.f11257h.setLayoutParams(layoutParams);
        }
    }

    /* renamed from: b */
    public void m18485b(View view) {
        int a;
        if (m18483c() != 0) {
            this.f11258i.removeView(view);
            if (this.f11258i.getChildCount() == 0 && (a = m18495a()) != -1) {
                notifyItemRemoved(a);
            }
        }
    }

    /* renamed from: a */
    private int m18495a() {
        return m18486b() + getItemCount();
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public int getItemCount() {
        return m18486b() + this.f11255c.size() + m18483c();
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        int b = m18486b();
        if (i < b) {
            return 99;
        }
        int i2 = i - b;
        int size = this.f11255c.size();
        if (i2 < size) {
            return super.getItemViewType(i);
        }
        if (i2 - size < m18483c()) {
            return 100;
        }
        return super.getItemViewType(i);
    }

    /* renamed from: b */
    public int m18486b() {
        LinearLayout linearLayout = this.f11257h;
        return (linearLayout == null || linearLayout.getChildCount() == 0) ? 0 : 1;
    }

    /* renamed from: c */
    public int m18483c() {
        LinearLayout linearLayout = this.f11258i;
        return (linearLayout == null || linearLayout.getChildCount() == 0) ? 0 : 1;
    }

    /* renamed from: a */
    public void m18487a(List<T> list) {
        this.f11255c.clear();
        this.f11255c.addAll(list);
        notifyDataSetChanged();
    }

    /* renamed from: d */
    public Context m18480d() {
        return this.f11254a;
    }
}
