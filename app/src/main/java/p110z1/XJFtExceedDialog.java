package p110z1;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.blankj.utilcode.util.Utils;
import com.nrzs.data.other.bean.AdResultInfoItem;
import com.nrzs.ft.C1990R;
import com.nrzs.ft.ui.base.FtBaseDialog;

/* renamed from: z1.ado */
/* loaded from: classes3.dex */
public class XJFtExceedDialog extends FtBaseDialog {

    /* renamed from: a */
    private ImageView f15304a;

    /* renamed from: b */
    private TextView f15305b;

    /* renamed from: c */
    private TextView f15306c;

    /* renamed from: d */
    private LinearLayout f15307d;

    /* renamed from: e */
    private ImageView f15308e;

    /* renamed from: f */
    private AdResultInfoItem f15309f;

    public XJFtExceedDialog(Context context) {
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
        this.f15308e = (ImageView) findViewById(C1990R.C1992id.nrzs_ft_iv_banner);
        this.f15306c = (TextView) findViewById(C1990R.C1992id.nrzs_ft_iv_back);
        this.f15304a = (ImageView) findViewById(C1990R.C1992id.nrzs_ft_iv_close);
        this.f15305b = (TextView) findViewById(C1990R.C1992id.nrzs_ft_tv_renew);
        this.f15307d = (LinearLayout) findViewById(C1990R.C1992id.nrzs_ft_ll_root);
    }

    @Override // com.nrzs.ft.ui.base.FtBaseDialog
    /* renamed from: c */
    protected void mo3012c() {
        this.f15306c.setOnClickListener(new View.OnClickListener() { // from class: z1.ado.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                XJFtExceedDialog.this.dismiss();
                XJFtExceedDialog.this.m14293e();
            }
        });
        this.f15304a.setOnClickListener(new View.OnClickListener() { // from class: z1.ado.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                XJFtExceedDialog.this.dismiss();
                FwManager.getInstance().initXJFloatView(true);
            }
        });
        this.f15305b.setOnClickListener(new View.OnClickListener() { // from class: z1.ado.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                EventCollectManager.m12642a().m12640a(XJFtExceedDialog.this.getContext(), "悬浮窗-VIP到期", "悬浮窗-VIP到期", EventConstants.f16399C);
                boolean b = apf.m11836b((Context) Utils.m24103a(), ShareVal.f16591a, ShareVal.f16614x, false);
                XJFtExceedDialog.this.dismiss();
                if (b) {
                    new CdKeyDialog(XJFtExceedDialog.this.getContext()).show();
                } else {
                    IntentToAssistService.m12800b(view.getContext(), 0L, 2);
                }
            }
        });
        this.f15308e.setOnClickListener(new View.OnClickListener() { // from class: z1.ado.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                XJFtExceedDialog.this.m14293e();
                IntentToAssistService.m12810a(view.getContext(), 0L);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: e */
    public void m14293e() {
        FwManager.getInstance().initXJAssistListView();
    }

    @Override // com.nrzs.ft.ui.base.FtBaseDialog
    /* renamed from: d */
    protected void mo3011d() {
        FloatViewManager.m12346a(getContext().getApplicationContext()).m12319j();
        FwManager.getInstance().removeXJAssisInfoView();
        BannerManager.m12679a().m12669b(4, new DoneCallback<AdResultInfoItem>() { // from class: z1.ado.5
            /* renamed from: a */
            public void onDone(AdResultInfoItem adResultInfoItem) {
                XJFtExceedDialog.this.f15309f = adResultInfoItem;
                if (XJFtExceedDialog.this.f15309f != null) {
                    GlideImageUtils.m11880a(XJFtExceedDialog.this.f15308e, XJFtExceedDialog.this.getContext(), C1990R.C1991drawable.bird_bg_common_img, XJFtExceedDialog.this.f15309f.ImgUrl);
                }
            }
        });
    }
}
