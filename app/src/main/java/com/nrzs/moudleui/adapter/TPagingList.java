package com.nrzs.moudleui.adapter;

import android.arch.lifecycle.LiveData;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import com.nrzs.moudleui.adapter.TPageCallback;

/* renamed from: com.nrzs.moudleui.adapter.d */
/* loaded from: classes2.dex */
public class TPagingList<T> {

    /* renamed from: a */
    private int f11283a;

    /* renamed from: b */
    private int f11284b;

    /* renamed from: c */
    private PagedList.Config f11285c;

    /* renamed from: d */
    private TPageCallback.AbstractC2210a<T> f11286d;

    /* renamed from: e */
    private LiveData<PagedList<T>> f11287e;

    /* renamed from: f */
    private TDataSourceFactory<T> f11288f;

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public TPagingList<T> m18437a(int i, int i2, TPageCallback.AbstractC2210a<T> aVar) {
        this.f11283a = i;
        this.f11284b = i2;
        this.f11286d = aVar;
        this.f11285c = new PagedList.Config.Builder().setPageSize(this.f11284b).setEnablePlaceholders(false).setInitialLoadSizeHint(this.f11283a).build();
        this.f11288f = new TDataSourceFactory<>(this.f11286d);
        this.f11287e = new LivePagedListBuilder(this.f11288f, this.f11285c).build();
        return this;
    }

    /* renamed from: a */
    public LiveData<PagedList<T>> m18438a() {
        return this.f11287e;
    }

    /* compiled from: TPagingList.java */
    /* renamed from: com.nrzs.moudleui.adapter.d$a */
    /* loaded from: classes2.dex */
    public static final class C2211a<T> {

        /* renamed from: a */
        private int f11289a = 10;

        /* renamed from: b */
        private int f11290b = 10;

        /* renamed from: c */
        private TPageCallback.AbstractC2210a<T> f11291c;

        /* renamed from: a */
        public C2211a<T> m18434a(int i) {
            this.f11289a = i;
            return this;
        }

        /* renamed from: b */
        public C2211a<T> m18432b(int i) {
            this.f11290b = i;
            return this;
        }

        /* renamed from: a */
        public C2211a<T> m18433a(TPageCallback.AbstractC2210a<T> aVar) {
            this.f11291c = aVar;
            return this;
        }

        /* renamed from: a */
        public TPagingList<T> m18435a() {
            return new TPagingList().m18437a(this.f11289a, this.f11290b, this.f11291c);
        }
    }
}
