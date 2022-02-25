package p110z1;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.p006v7.widget.LinearLayoutManager;
import android.support.p006v7.widget.RecyclerView;
import android.view.WindowManager;
import com.angel.nrzs.C0692R;
import com.angel.nrzs.adapter.MoveGrouptAdapter;
import com.nrzs.data.ddy.bean.respond.GroupInfo;
import java.util.List;

/* renamed from: z1.ed */
/* loaded from: classes3.dex */
public class DoMoveGroupDialog extends Dialog {

    /* renamed from: a */
    private RecyclerView f21409a;

    /* renamed from: b */
    private MoveGrouptAdapter f21410b;

    /* renamed from: c */
    private List<GroupInfo> f21411c;

    /* renamed from: d */
    private ChooseCallback f21412d;

    /* renamed from: c */
    protected void m3167c() {
    }

    public DoMoveGroupDialog(Context context, List<GroupInfo> list, ChooseCallback eiVar) {
        super(context);
        this.f21411c = list;
        this.f21412d = eiVar;
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().requestFeature(1);
        setContentView(C0692R.layout.nrzs_move_group_view);
        setCancelable(true);
        m3169a();
        m3168b();
        m3167c();
        m3166d();
    }

    /* renamed from: a */
    protected void m3169a() {
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        getWindow().setGravity(17);
        getWindow().getDecorView().setPadding(0, 0, 0, 0);
        getWindow().getDecorView().setBackgroundColor(getContext().getResources().getColor(C0692R.color.f1440f2));
        getWindow().setAttributes(attributes);
    }

    /* renamed from: b */
    protected void m3168b() {
        this.f21409a = (RecyclerView) findViewById(C0692R.C0694id.recycler_group_list);
    }

    /* renamed from: d */
    protected void m3166d() {
        this.f21409a.setLayoutManager(new LinearLayoutManager(getContext()));
        this.f21410b = new MoveGrouptAdapter(this.f21412d);
        this.f21410b.m25172a(this.f21411c);
        this.f21409a.setAdapter(this.f21410b);
    }
}
