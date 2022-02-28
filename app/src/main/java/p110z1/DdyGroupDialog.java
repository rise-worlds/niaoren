package p110z1;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.angel.nrzs.C0692R;
import com.angel.nrzs.adapter.GrouptAdapter;
import com.angel.nrzs.ddy.activity.GroupManagerActivity;
import com.nrzs.data.ddy.bean.respond.GroupInfo;
import com.nrzs.ft.ui.base.FtBaseDialog;
import java.util.ArrayList;
import java.util.List;

/* renamed from: z1.eb */
/* loaded from: classes3.dex */
public class DdyGroupDialog extends FtBaseDialog {

    /* renamed from: a */
    private TextView f21391a;

    /* renamed from: b */
    private RecyclerView f21392b;

    /* renamed from: c */
    private GrouptAdapter f21393c;

    /* renamed from: d */
    private List<GroupInfo> f21394d = new ArrayList();

    @Override // com.nrzs.ft.ui.base.FtBaseDialog
    /* renamed from: d */
    protected void mo3011d() {
    }

    public DdyGroupDialog(Context context) {
        super(context, false, C0692R.style.a4p);
    }

    @Override // com.nrzs.ft.ui.base.FtBaseDialog
    /* renamed from: a */
    protected void mo3014a() {
        setCancelable(true);
        getWindow().setGravity(48);
    }

    @Override // com.nrzs.ft.ui.base.FtBaseDialog
    /* renamed from: b */
    protected void mo3013b() {
        setContentView(C0692R.layout.nrzs_group_view);
        this.f21391a = (TextView) findViewById(C0692R.C0694id.manage_btn);
        this.f21392b = (RecyclerView) findViewById(C0692R.C0694id.recycler_group_list);
    }

    @Override // com.nrzs.ft.ui.base.FtBaseDialog
    /* renamed from: c */
    protected void mo3012c() {
        this.f21391a.setOnClickListener(new View.OnClickListener() { // from class: z1.eb.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                GroupManagerActivity.m25149a(DdyGroupDialog.this.getContext());
                DdyGroupDialog.this.dismiss();
            }
        });
    }

    @Override // android.app.Dialog
    public void show() {
        super.show();
        m3178e();
    }

    /* renamed from: e */
    private void m3178e() {
        this.f21392b.setLayoutManager(new LinearLayoutManager(getContext()));
        this.f21393c = new GrouptAdapter(new GrouptAdapter.AbstractC0704a() { // from class: z1.eb.2
            @Override // com.angel.nrzs.adapter.GrouptAdapter.AbstractC0704a
            /* renamed from: a */
            public void mo3177a() {
                DdyGroupDialog.this.dismiss();
            }
        });
        this.f21393c.m25192a(this.f21394d);
        this.f21392b.setAdapter(this.f21393c);
    }

    /* renamed from: a */
    public void m3179a(List<GroupInfo> list) {
        this.f21394d = list;
    }
}
