package p110z1;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.p006v7.widget.LinearLayoutManager;
import android.support.p006v7.widget.RecyclerView;
import android.view.WindowManager;
import com.angel.nrzs.C0692R;
import com.angel.nrzs.adapter.MovePopGrouptAdapter;
import com.nrzs.data.ddy.bean.respond.GroupInfo;
import java.util.List;

/* renamed from: z1.ef */
/* loaded from: classes3.dex */
public class MoveGroupDialog extends Dialog {

    /* renamed from: a */
    private RecyclerView f21439a;

    /* renamed from: b */
    private MovePopGrouptAdapter f21440b;

    /* renamed from: c */
    private List<GroupInfo> f21441c;

    /* renamed from: d */
    private ChooseCallback f21442d;

    /* renamed from: c */
    protected void m3140c() {
    }

    public MoveGroupDialog(Context context, List<GroupInfo> list, ChooseCallback eiVar) {
        super(context);
        this.f21441c = list;
        this.f21442d = eiVar;
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().requestFeature(1);
        setContentView(C0692R.layout.nrzs_move_group_view);
        setCancelable(true);
        m3142a();
        m3141b();
        m3140c();
        m3139d();
    }

    /* renamed from: a */
    protected void m3142a() {
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        getWindow().setGravity(53);
        getWindow().getDecorView().setPadding(0, 0, 0, 0);
        getWindow().getDecorView().setBackgroundColor(getContext().getResources().getColor(C0692R.color.f1440f2));
        getWindow().setAttributes(attributes);
    }

    /* renamed from: b */
    protected void m3141b() {
        this.f21439a = (RecyclerView) findViewById(C0692R.C0694id.recycler_group_list);
    }

    /* renamed from: d */
    protected void m3139d() {
        this.f21439a.setLayoutManager(new LinearLayoutManager(getContext()));
        this.f21440b = new MovePopGrouptAdapter();
        this.f21440b.m25167a(this.f21441c, this.f21442d);
        this.f21439a.setAdapter(this.f21440b);
    }
}
