package p110z1;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.nrzs.ft.C1990R;
import com.nrzs.ft.ui.base.FtBaseDialog;
import p110z1.FtEvent;

/* renamed from: z1.any */
/* loaded from: classes3.dex */
public class FtNeedGoldDialog extends FtBaseDialog {

    /* renamed from: a */
    private ImageView f16906a;

    /* renamed from: b */
    private TextView f16907b;

    /* renamed from: c */
    private TextView f16908c;

    /* renamed from: d */
    private CheckBox f16909d;

    /* renamed from: e */
    private LinearLayout f16910e;

    @Override // com.nrzs.ft.ui.base.FtBaseDialog
    /* renamed from: d */
    protected void mo3011d() {
    }

    public FtNeedGoldDialog(Context context) {
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
        this.f16906a = (ImageView) findViewById(C1990R.C1992id.nrzs_ft_iv_back);
        this.f16908c = (TextView) findViewById(C1990R.C1992id.nrzs_ft_tv_use_gold_run);
        this.f16907b = (TextView) findViewById(C1990R.C1992id.nrzs_ft_tv_open_more);
        this.f16909d = (CheckBox) findViewById(C1990R.C1992id.cbox_run_to_notice);
        this.f16910e = (LinearLayout) findViewById(C1990R.C1992id.nrzs_ft_ll_root);
    }

    @Override // com.nrzs.ft.ui.base.FtBaseDialog
    /* renamed from: c */
    protected void mo3012c() {
        this.f16906a.setOnClickListener(new View.OnClickListener() { // from class: z1.any.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                FtNeedGoldDialog.this.dismiss();
            }
        });
        this.f16908c.setOnClickListener(new View.OnClickListener() { // from class: z1.any.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                FtNeedGoldDialog.this.dismiss();
                EventBus.m3448a().m3427d(new FtEvent.C3570h(FtNeedGoldDialog.this.f16909d.isChecked()));
            }
        });
        this.f16907b.setOnClickListener(new View.OnClickListener() { // from class: z1.any.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                FtNeedGoldDialog.this.dismiss();
                FloatViewManager.m12346a(FtNeedGoldDialog.this.getContext().getApplicationContext()).m12342a(FtNeedGoldDialog.this.getContext(), false);
            }
        });
    }
}
