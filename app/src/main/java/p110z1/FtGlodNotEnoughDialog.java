package p110z1;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.blankj.utilcode.util.Utils;
import com.nrzs.data.other.bean.AdResultInfoItem;
import com.nrzs.p067ft.C1990R;
import com.nrzs.p067ft.FloatApp;
import com.nrzs.p067ft.p068ui.base.FtBaseDialog;

/* renamed from: z1.anw */
/* loaded from: classes3.dex */
public class FtGlodNotEnoughDialog extends FtBaseDialog {

    /* renamed from: a */
    private ImageView f16883a;

    /* renamed from: b */
    private TextView f16884b;

    /* renamed from: c */
    private TextView f16885c;

    /* renamed from: d */
    private AdResultInfoItem f16886d;

    public FtGlodNotEnoughDialog(Context context) {
        super(context, C1990R.style.nrzs_assist_dialog_theme);
    }

    @Override // com.nrzs.p067ft.p068ui.base.FtBaseDialog
    /* renamed from: a */
    protected void mo3014a() {
        setCancelable(false);
    }

    @Override // com.nrzs.p067ft.p068ui.base.FtBaseDialog
    /* renamed from: b */
    protected void mo3013b() {
        setContentView(C1990R.layout.nrzs_ft_dialog_glodnotenough);
        this.f16885c = (TextView) findViewById(C1990R.C1992id.more_open_manager);
        this.f16883a = (ImageView) findViewById(C1990R.C1992id.nrzs_ft_iv_close);
        this.f16884b = (TextView) findViewById(C1990R.C1992id.nrzs_ft_tv_renew);
    }

    @Override // com.nrzs.p067ft.p068ui.base.FtBaseDialog
    /* renamed from: c */
    protected void mo3012c() {
        this.f16885c.setOnClickListener(new View.OnClickListener() { // from class: z1.anw.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                FtGlodNotEnoughDialog.this.dismiss();
                FloatViewManager.m12346a(FtGlodNotEnoughDialog.this.getContext().getApplicationContext()).m12342a(FtGlodNotEnoughDialog.this.getContext(), false);
            }
        });
        this.f16883a.setOnClickListener(new View.OnClickListener() { // from class: z1.anw.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                FtGlodNotEnoughDialog.this.dismiss();
            }
        });
        this.f16884b.setOnClickListener(new View.OnClickListener() { // from class: z1.anw.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                FtGlodNotEnoughDialog.this.dismiss();
                EventCollectManager.m12642a().m12640a(FtGlodNotEnoughDialog.this.getContext(), "脚本到期弹窗充值金币", "脚本到期弹窗充值金币", EventConstants.f16424aa);
                if (ChannelDataManager.m12655a().m12650d()) {
                    new CdKeyDialog(FtGlodNotEnoughDialog.this.getContext()).show();
                } else if (apf.m11836b((Context) Utils.m24103a(), ShareVal.f16591a, ShareVal.f16614x, false)) {
                    new CdKeyDialog(FtGlodNotEnoughDialog.this.getContext()).show();
                } else {
                    IntentToAssistService.m12800b(view.getContext(), 0L, 2);
                }
            }
        });
    }

    @Override // com.nrzs.p067ft.p068ui.base.FtBaseDialog
    /* renamed from: d */
    protected void mo3011d() {
        FloatViewManager.m12346a(FloatApp.m18899b().m18901a()).m12327e();
    }
}
