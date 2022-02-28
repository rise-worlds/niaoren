package com.nrzs.user;

import android.arch.paging.PagedListAdapter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.internal.view.SupportMenu;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class MainActivity extends AppCompatActivity {

    /* renamed from: a */
    private PagingView f11311a;

    /* renamed from: b */
    private DiffUtil.ItemCallback<Pageinfo> f11312b = new DiffUtil.ItemCallback<Pageinfo>() { // from class: com.nrzs.user.MainActivity.1
        /* renamed from: b */
        public boolean areContentsTheSame(@NonNull Pageinfo bVar, @NonNull Pageinfo bVar2) {
            return bVar == bVar2;
        }

        /* renamed from: a */
        public boolean areItemsTheSame(@NonNull Pageinfo bVar, @NonNull Pageinfo bVar2) {
            return bVar.f11329b == bVar2.f11329b;
        }
    };

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C2222R.layout.activity_main);
        C2220a aVar = new C2220a();
        this.f11311a = new PagingView(this, aVar, 10, new Pageinfo());
        RecyclerView recyclerView = (RecyclerView) findViewById(C2222R.C2224id.mRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(aVar);
        this.f11311a.m18400a(m18409a());
    }

    /* renamed from: a */
    private List<Pageinfo> m18409a() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < 10; i++) {
            Pageinfo bVar = new Pageinfo();
            bVar.f11329b = 0 + i;
            bVar.f11328a = "pageinfo测试=" + bVar.f11329b;
            arrayList.add(bVar);
        }
        return arrayList;
    }

    /* renamed from: com.nrzs.user.MainActivity$a */
    /* loaded from: classes2.dex */
    private class C2220a extends PagedListAdapter<Pageinfo, C2221b> {
        protected C2220a() {
            super(MainActivity.this.f11312b);
        }

        @NonNull
        /* renamed from: a */
        public C2221b onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new C2221b(LayoutInflater.from(MainActivity.this.getApplicationContext()).inflate(17367044, (ViewGroup) null));
        }

        /* renamed from: a */
        public void onBindViewHolder(@NonNull C2221b bVar, int i) {
            bVar.f11315a.setText(String.valueOf(i));
            bVar.f11316b.setText(String.valueOf(getItem(i).f11328a));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.nrzs.user.MainActivity$b */
    /* loaded from: classes2.dex */
    public class C2221b extends RecyclerView.ViewHolder {

        /* renamed from: a */
        public TextView f11315a;

        /* renamed from: b */
        public TextView f11316b;

        public C2221b(View view) {
            super(view);
            this.f11315a = (TextView) view.findViewById(16908308);
            this.f11315a.setTextColor(SupportMenu.CATEGORY_MASK);
            this.f11316b = (TextView) view.findViewById(16908309);
            this.f11316b.setTextColor(-16776961);
        }
    }
}
