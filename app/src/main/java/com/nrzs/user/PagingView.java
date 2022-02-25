package com.nrzs.user;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.arch.paging.DataSource;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PageKeyedDataSource;
import android.arch.paging.PagedList;
import android.arch.paging.PagedListAdapter;
import android.arch.paging.PositionalDataSource;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.nrzs.user.c */
/* loaded from: classes2.dex */
public class PagingView {

    /* renamed from: a */
    private int f11330a;

    /* renamed from: b */
    private Pageinfo f11331b;

    /* renamed from: c */
    private PagedListAdapter f11332c;

    /* renamed from: d */
    private List<Pageinfo> f11333d = new ArrayList();

    /* renamed from: e */
    private LifecycleOwner f11334e;

    public PagingView(LifecycleOwner lifecycleOwner, PagedListAdapter pagedListAdapter, int i, Pageinfo bVar) {
        this.f11330a = i;
        this.f11331b = bVar;
        this.f11332c = pagedListAdapter;
        this.f11334e = lifecycleOwner;
        m18402a();
    }

    /* renamed from: a */
    private void m18402a() {
        new LivePagedListBuilder(new C2230b(), new PagedList.Config.Builder().setPageSize(this.f11330a).setEnablePlaceholders(false).setPrefetchDistance(5).setInitialLoadSizeHint(this.f11330a).build()).setBoundaryCallback(new PagedList.BoundaryCallback<Pageinfo>() { // from class: com.nrzs.user.c.1
            @Override // android.arch.paging.PagedList.BoundaryCallback
            public void onZeroItemsLoaded() {
                super.onZeroItemsLoaded();
            }

            /* renamed from: a */
            public void onItemAtFrontLoaded(@NonNull Pageinfo bVar) {
                super.onItemAtFrontLoaded(bVar);
            }

            /* renamed from: b */
            public void onItemAtEndLoaded(@NonNull Pageinfo bVar) {
                super.onItemAtEndLoaded(bVar);
            }
        }).build().observe(this.f11334e, new Observer<PagedList<Pageinfo>>() { // from class: com.nrzs.user.c.2
            /* renamed from: a */
            public void onChanged(@Nullable PagedList<Pageinfo> pagedList) {
                PagingView.this.f11332c.submitList(pagedList);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: PagingView.java */
    /* renamed from: com.nrzs.user.c$b */
    /* loaded from: classes2.dex */
    public class C2230b extends DataSource.Factory<Integer, Pageinfo> {
        private C2230b() {
        }

        @Override // android.arch.paging.DataSource.Factory
        public DataSource<Integer, Pageinfo> create() {
            return new C2229a();
        }
    }

    /* compiled from: PagingView.java */
    /* renamed from: com.nrzs.user.c$c */
    /* loaded from: classes2.dex */
    private class C2231c extends PageKeyedDataSource<Integer, Pageinfo> {
        @Override // android.arch.paging.PageKeyedDataSource
        public void loadAfter(@NonNull PageKeyedDataSource.LoadParams<Integer> loadParams, @NonNull PageKeyedDataSource.LoadCallback<Integer, Pageinfo> loadCallback) {
        }

        @Override // android.arch.paging.PageKeyedDataSource
        public void loadBefore(@NonNull PageKeyedDataSource.LoadParams<Integer> loadParams, @NonNull PageKeyedDataSource.LoadCallback<Integer, Pageinfo> loadCallback) {
        }

        private C2231c() {
        }

        @Override // android.arch.paging.PageKeyedDataSource
        public void loadInitial(@NonNull PageKeyedDataSource.LoadInitialParams<Integer> loadInitialParams, @NonNull PageKeyedDataSource.LoadInitialCallback<Integer, Pageinfo> loadInitialCallback) {
            loadInitialCallback.onResult(PagingView.this.m18399b(), 0, Integer.valueOf(PagingView.this.f11330a));
        }
    }

    /* compiled from: PagingView.java */
    /* renamed from: com.nrzs.user.c$a */
    /* loaded from: classes2.dex */
    private class C2229a extends PositionalDataSource<Pageinfo> {
        private C2229a() {
        }

        @Override // android.arch.paging.PositionalDataSource
        public void loadInitial(@NonNull PositionalDataSource.LoadInitialParams loadInitialParams, @NonNull PositionalDataSource.LoadInitialCallback<Pageinfo> loadInitialCallback) {
            loadInitialCallback.onResult(new ArrayList(), 0);
        }

        @Override // android.arch.paging.PositionalDataSource
        public void loadRange(@NonNull PositionalDataSource.LoadRangeParams loadRangeParams, @NonNull PositionalDataSource.LoadRangeCallback<Pageinfo> loadRangeCallback) {
            if (loadRangeParams.startPosition != 80) {
                loadRangeCallback.onResult(PagingView.this.m18397c());
            } else {
                invalidate();
            }
        }

        @Override // android.arch.paging.DataSource
        public void invalidate() {
            super.invalidate();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public List<Pageinfo> m18399b() {
        return this.f11333d;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public List<Pageinfo> m18397c() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < 5; i++) {
            Pageinfo bVar = new Pageinfo();
            bVar.f11328a = "你好啊";
            bVar.f11329b = i;
            arrayList.add(bVar);
        }
        return arrayList;
    }

    /* renamed from: a */
    public void m18400a(List<Pageinfo> list) {
        this.f11333d = list;
    }
}
