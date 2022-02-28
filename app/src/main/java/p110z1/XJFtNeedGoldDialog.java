package p110z1;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.lbd.p054xj.app.XJApp;
import com.nrzs.ft.C1990R;
import com.nrzs.ft.ui.base.FtBaseDialog;
import p110z1.FtEvent;

/* renamed from: z1.adr */
/* loaded from: classes3.dex */
public class XJFtNeedGoldDialog extends FtBaseDialog {

    /* renamed from: a */
    private ImageView f15338a;

    /* renamed from: b */
    private TextView f15339b;

    /* renamed from: c */
    private TextView f15340c;

    /* renamed from: d */
    private CheckBox f15341d;

    /* renamed from: e */
    private LinearLayout f15342e;

    @Override // com.nrzs.ft.ui.base.FtBaseDialog
    /* renamed from: d */
    protected void mo3011d() {
    }

    public XJFtNeedGoldDialog(Context context) {
        super(context, C1990R.style.nrzs_assist_dialog_theme);
    }

    @Override // com.nrzs.ft.ui.base.FtBaseDialog
    /* renamed from: a */
    protected void mo3014a() {
        setCancelable(false);
    }

    @Override // com.nrzs.ft.ui.base.FtBaseDialog
    /* renamed from: b */
    protected void mo3013b() {
        setContentView(C1990R.layout.nrzs_ft_dialog_needgold);
        this.f15338a = (ImageView) findViewById(C1990R.C1992id.nrzs_ft_iv_back);
        this.f15340c = (TextView) findViewById(C1990R.C1992id.nrzs_ft_tv_use_gold_run);
        this.f15339b = (TextView) findViewById(C1990R.C1992id.nrzs_ft_tv_open_more);
        this.f15341d = (CheckBox) findViewById(C1990R.C1992id.cbox_run_to_notice);
        this.f15342e = (LinearLayout) findViewById(C1990R.C1992id.nrzs_ft_ll_root);
    }

    @Override // com.nrzs.ft.ui.base.FtBaseDialog
    /* renamed from: c */
    protected void mo3012c() {
        this.f15338a.setOnClickListener(new View.OnClickListener() { // from class: z1.adr.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                XJFtNeedGoldDialog.this.dismiss();
            }
        });
        this.f15340c.setOnClickListener(new View.OnClickListener() { // from class: z1.adr.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                XJFtNeedGoldDialog.this.dismiss();
                EventBus.m3448a().m3427d(new FtEvent.C3570h(XJFtNeedGoldDialog.this.f15341d.isChecked()));
            }
        });
        this.f15339b.setOnClickListener(new View.OnClickListener() { // from class: z1.adr.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                XJFtNeedGoldDialog.this.dismiss();
                FwManager.getInstance().showXJUserKickDialog(XJApp.getInstance().getApplicationContext(), false);
            }
        });
    }
}
