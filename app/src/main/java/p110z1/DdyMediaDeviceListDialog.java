package p110z1;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.gyf.barlibrary.ImmersionBar;
import com.live.ddy.C1701R;
import com.live.ddy.visual.adapter.OrderListAdapter;

/* renamed from: z1.aib */
/* loaded from: classes3.dex */
public class DdyMediaDeviceListDialog extends Dialog {

    /* renamed from: a */
    private IDdyClickInterface f15897a;

    /* renamed from: b */
    private TextView f15898b;

    /* renamed from: c */
    private TextView f15899c;

    /* renamed from: d */
    private TextView f15900d;

    /* renamed from: e */
    private OrderListAdapter f15901e;

    /* renamed from: f */
    private RecyclerView f15902f;

    /* renamed from: g */
    private Activity f15903g;

    /* renamed from: h */
    private LinearLayout f15904h;

    public DdyMediaDeviceListDialog(@NonNull Context context) {
        super(context, C1701R.style.nrzs_ddy_dialog_theme);
        this.f15903g = (Activity) context;
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.setContentView(C1701R.layout.ddy_dialog_media_device_list_layout);
        m13119d();
        m13121c();
        m13127a();
        m13123b();
    }

    /* renamed from: b */
    private void m13123b() {
        this.f15902f.setLayoutManager(new LinearLayoutManager(getContext()));
        this.f15901e = new OrderListAdapter(this.f15897a);
        this.f15901e.m19013a(GroupManager.m12887a().m12880d());
        this.f15902f.setAdapter(this.f15901e);
    }

    /* renamed from: c */
    private void m13121c() {
        this.f15898b.setOnClickListener(new View.OnClickListener() { // from class: z1.aib.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (DdyMediaDeviceListDialog.this.f15897a != null) {
                    DdyMediaDeviceListDialog.this.f15897a.mo13081a(view, 0, 1, "");
                }
            }
        });
        this.f15899c.setOnClickListener(new View.OnClickListener() { // from class: z1.aib.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (DdyMediaDeviceListDialog.this.f15897a != null) {
                    DdyMediaDeviceListDialog.this.f15897a.mo13081a(view, 1, 1, "");
                }
            }
        });
        this.f15900d.setOnClickListener(new View.OnClickListener() { // from class: z1.aib.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (DdyMediaDeviceListDialog.this.f15897a != null) {
                    DdyMediaDeviceListDialog.this.f15897a.mo13081a(view, 2, 1, "");
                }
            }
        });
    }

    /* renamed from: d */
    private void m13119d() {
        this.f15904h = (LinearLayout) findViewById(C1701R.C1703id.nrzs_ddy_id_ll_opera);
        boolean z = true;
        ImmersionBar h = ImmersionBar.m20079a(this.f15903g, this).m20009d(this.f15904h).m19974h(true);
        if (getContext().getResources().getConfiguration().orientation != 1) {
            z = false;
        }
        h.m19965j(z).m19994f();
        this.f15898b = (TextView) findViewById(C1701R.C1703id.nrzs_ddy_tv_definition);
        this.f15899c = (TextView) findViewById(C1701R.C1703id.nrzs_ddy_tv_nav);
        this.f15900d = (TextView) findViewById(C1701R.C1703id.nrzs_ddy_tv_exit);
        this.f15902f = (RecyclerView) findViewById(C1701R.C1703id.recycler_order_list);
    }

    /* renamed from: a */
    public void m13124a(IDdyClickInterface aieVar) {
        this.f15897a = aieVar;
    }

    @Override // android.app.Dialog
    protected void onStart() {
        super.onStart();
    }

    /* renamed from: a */
    public void m13127a() {
        Window window = getWindow();
        if (window != null) {
            window.setGravity(8388659);
            window.setWindowAnimations(C1701R.style.LeftAnimation);
            window.setLayout(DdyScreenUtil.m13144a(getContext().getApplicationContext(), 230.0f), -1);
        }
    }

    /* renamed from: a */
    public void m13126a(int i) {
        if (i == 1) {
            this.f15897a = null;
        }
        dismiss();
    }

    /* renamed from: b */
    public void m13122b(int i) {
        Log.d("DDY_Change", i + "");
    }

    /* renamed from: c */
    public void m13120c(int i) {
        Log.d("DdyMediaModel", "1 切换清晰度成功 ,切换图片" + i + "," + Thread.currentThread().getName());
        if (i == 3) {
            Toast.makeText(getContext(), "切换失败", 1).show();
        } else if (i == 0) {
            Drawable drawable = getContext().getResources().getDrawable(C1701R.C1702drawable.bird_ic_phone_menu_quality_smooth);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            this.f15898b.setCompoundDrawables(null, drawable, null, null);
        } else {
            Drawable drawable2 = getContext().getResources().getDrawable(C1701R.C1702drawable.bird_ic_phone_menu_quality_sclear);
            drawable2.setBounds(0, 0, drawable2.getMinimumWidth(), drawable2.getMinimumHeight());
            this.f15898b.setCompoundDrawables(null, drawable2, null, null);
        }
    }
}
