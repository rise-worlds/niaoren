package com.redwas.redwars;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.WindowManager;
import android.widget.TextView;
import com.nrzs.data.redbag.bean.MoneyInfo;
import com.redwas.adapter.MoneyAdapter;
import java.util.List;

/* renamed from: com.redwas.redwars.b */
/* loaded from: classes2.dex */
public class HistoryDialog extends Dialog {

    /* renamed from: a */
    private static HistoryDialog f11706a;

    /* renamed from: b */
    private TextView f11707b;

    /* renamed from: c */
    private TextView f11708c;

    /* renamed from: d */
    private TextView f11709d;

    /* renamed from: e */
    private List<MoneyInfo> f11710e;

    /* renamed from: f */
    private RecyclerView f11711f;

    /* renamed from: g */
    private MoneyAdapter f11712g;

    public HistoryDialog(Context context, List<MoneyInfo> list) {
        super(context, C2342R.style.NoTitleDialog);
        this.f11710e = list;
    }

    public HistoryDialog(Context context, int i) {
        super(context, i);
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        m18150b();
        m18148d();
        m18149c();
    }

    /* renamed from: a */
    public static void m18151a() {
        HistoryDialog bVar = f11706a;
        if (bVar != null) {
            bVar.dismiss();
        }
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        super.dismiss();
        f11706a = null;
    }

    /* renamed from: b */
    public void m18150b() {
        setContentView(C2342R.layout.histroy_list_view);
        this.f11711f = (RecyclerView) findViewById(C2342R.C2344id.list_history);
    }

    /* renamed from: c */
    public void m18149c() {
        this.f11712g = new MoneyAdapter(this.f11710e);
        this.f11711f.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(1);
        this.f11711f.setLayoutManager(linearLayoutManager);
        this.f11711f.setAdapter(this.f11712g);
        this.f11712g.notifyDataSetChanged();
    }

    /* renamed from: d */
    public void m18148d() {
        setCancelable(true);
        setCanceledOnTouchOutside(true);
    }

    /* renamed from: e */
    public void m18147e() {
        getWindow().setGravity(80);
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.width = -1;
        getWindow().setAttributes(attributes);
    }
}
