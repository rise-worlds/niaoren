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

/* renamed from: z1.adp */
/* loaded from: classes3.dex */
public class XJFtGlodNotEnoughDialog extends FtBaseDialog {

    /* renamed from: a */
    private ImageView f15315a;

    /* renamed from: b */
    private TextView f15316b;

    /* renamed from: c */
    private ImageView f15317c;

    /* renamed from: d */
    private LinearLayout f15318d;

    /* renamed from: e */
    private ImageView f15319e;

    /* renamed from: f */
    private AdResultInfoItem f15320f;

    public XJFtGlodNotEnoughDialog(Context context) {
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
        setContentView(C1990R.layout.nrzs_ft_dialog_glodnotenough);
        this.f15319e = (ImageView) findViewById(C1990R.C1992id.nrzs_ft_iv_banner);
        this.f15317c = (ImageView) findViewById(C1990R.C1992id.nrzs_ft_iv_back);
        this.f15315a = (ImageView) findViewById(C1990R.C1992id.nrzs_ft_iv_close);
        this.f15316b = (TextView) findViewById(C1990R.C1992id.nrzs_ft_tv_renew);
        this.f15318d = (LinearLayout) findViewById(C1990R.C1992id.nrzs_ft_ll_root);
    }

    @Override // com.nrzs.ft.ui.base.FtBaseDialog
    /* renamed from: c */
    protected void mo3012c() {
        this.f15317c.setOnClickListener(new View.OnClickListener() { // from class: z1.adp.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                XJFtGlodNotEnoughDialog.this.dismiss();
                XJFtGlodNotEnoughDialog.this.m14287e();
            }
        });
        this.f15315a.setOnClickListener(new View.OnClickListener() { // from class: z1.adp.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                XJFtGlodNotEnoughDialog.this.dismiss();
                FwManager.getInstance().initXJFloatView(true);
            }
        });
        this.f15316b.setOnClickListener(new View.OnClickListener() { // from class: z1.adp.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                boolean b = apf.m11836b((Context) Utils.m24103a(), ShareVal.f16591a, ShareVal.f16614x, false);
                XJFtGlodNotEnoughDialog.this.dismiss();
                if (b) {
                    new CdKeyDialog(XJFtGlodNotEnoughDialog.this.getContext()).show();
                } else {
                    IntentToAssistService.m12800b(view.getContext(), 0L, 2);
                }
            }
        });
        this.f15319e.setOnClickListener(new View.OnClickListener() { // from class: z1.adp.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                XJFtGlodNotEnoughDialog.this.dismiss();
                IntentToAssistService.m12810a(view.getContext(), 0L);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: e */
    public void m14287e() {
        FwManager.getInstance().initXJAssistListView();
    }

    @Override // com.nrzs.ft.ui.base.FtBaseDialog
    /* renamed from: d */
    protected void mo3011d() {
        FloatViewManager.m12346a(getContext().getApplicationContext()).m12319j();
        FwManager.getInstance().removeXJAssisInfoView();
        BannerManager.m12679a().m12669b(4, new DoneCallback<AdResultInfoItem>() { // from class: z1.adp.5
            /* renamed from: a */
            public void onDone(AdResultInfoItem adResultInfoItem) {
                XJFtGlodNotEnoughDialog.this.f15320f = adResultInfoItem;
                if (XJFtGlodNotEnoughDialog.this.f15320f != null) {
                    GlideImageUtils.m11880a(XJFtGlodNotEnoughDialog.this.f15319e, XJFtGlodNotEnoughDialog.this.getContext(), C1990R.C1991drawable.bird_bg_common_img, XJFtGlodNotEnoughDialog.this.f15320f.ImgUrl);
                }
            }
        });
    }
}
