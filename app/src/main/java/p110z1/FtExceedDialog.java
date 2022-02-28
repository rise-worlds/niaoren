package p110z1;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.blankj.utilcode.util.Utils;
import com.nrzs.data.other.bean.AdResultInfoItem;
import com.nrzs.ft.C1990R;
import com.nrzs.ft.FloatApp;
import com.nrzs.ft.ui.base.FtBaseDialog;

/* renamed from: z1.anv */
/* loaded from: classes3.dex */
public class FtExceedDialog extends FtBaseDialog {

    /* renamed from: a */
    private ImageView f16872a;

    /* renamed from: b */
    private TextView f16873b;

    /* renamed from: c */
    private TextView f16874c;

    /* renamed from: d */
    private LinearLayout f16875d;

    /* renamed from: e */
    private ImageView f16876e;

    /* renamed from: f */
    private AdResultInfoItem f16877f;

    public FtExceedDialog(Context context) {
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
        setContentView(C1990R.layout.nrzs_ft_dialog_exceed);
        this.f16876e = (ImageView) findViewById(C1990R.C1992id.nrzs_ft_iv_banner);
        this.f16874c = (TextView) findViewById(C1990R.C1992id.nrzs_ft_iv_back);
        this.f16872a = (ImageView) findViewById(C1990R.C1992id.nrzs_ft_iv_close);
        this.f16873b = (TextView) findViewById(C1990R.C1992id.nrzs_ft_tv_renew);
        this.f16875d = (LinearLayout) findViewById(C1990R.C1992id.nrzs_ft_ll_root);
    }

    @Override // com.nrzs.ft.ui.base.FtBaseDialog
    /* renamed from: c */
    protected void mo3012c() {
        this.f16874c.setOnClickListener(new View.OnClickListener() { // from class: z1.anv.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                FloatViewManager.m12346a(view.getContext().getApplicationContext()).m12336b(view.getContext().getApplicationContext());
                FtExceedDialog.this.dismiss();
            }
        });
        this.f16872a.setOnClickListener(new View.OnClickListener() { // from class: z1.anv.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                FtExceedDialog.this.dismiss();
            }
        });
        this.f16873b.setOnClickListener(new View.OnClickListener() { // from class: z1.anv.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                EventCollectManager.m12642a().m12640a(FtExceedDialog.this.getContext(), "悬浮窗-VIP到期", "悬浮窗-VIP到期", EventConstants.f16399C);
                if (apf.m11836b((Context) Utils.m24103a(), ShareVal.f16591a, ShareVal.f16614x, false)) {
                    new CdKeyDialog(FtExceedDialog.this.getContext()).show();
                } else {
                    IntentToAssistService.m12810a(view.getContext(), 0L);
                }
            }
        });
        this.f16876e.setOnClickListener(new View.OnClickListener() { // from class: z1.anv.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                FloatViewManager.m12346a(view.getContext().getApplicationContext()).m12336b(view.getContext().getApplicationContext());
                IntentToAssistService.m12810a(view.getContext(), 0L);
            }
        });
    }

    @Override // com.nrzs.ft.ui.base.FtBaseDialog
    /* renamed from: d */
    protected void mo3011d() {
        FloatViewManager.m12346a(FloatApp.m18899b().m18901a()).m12327e();
        BannerManager.m12679a().m12669b(4, new DoneCallback<AdResultInfoItem>() { // from class: z1.anv.5
            /* renamed from: a */
            public void onDone(AdResultInfoItem adResultInfoItem) {
                FtExceedDialog.this.f16877f = adResultInfoItem;
                if (FtExceedDialog.this.f16877f != null) {
                    GlideImageUtils.m11880a(FtExceedDialog.this.f16876e, FtExceedDialog.this.getContext(), C1990R.C1991drawable.bird_bg_common_img, FtExceedDialog.this.f16877f.ImgUrl);
                }
            }
        });
    }
}
