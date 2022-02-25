package p110z1;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.angel.nrzs.C0692R;
import com.angel.nrzs.app.activity.NRZSWebviewActivity;
import com.nrzs.data.other.bean.AdResultInfoItem;
import com.nrzs.p067ft.p068ui.base.FtBaseDialog;

/* renamed from: z1.ew */
/* loaded from: classes3.dex */
public class GoldExpireDialog extends FtBaseDialog {

    /* renamed from: a */
    private ImageView f21577a;

    /* renamed from: b */
    private TextView f21578b;

    @Override // com.nrzs.p067ft.p068ui.base.FtBaseDialog
    /* renamed from: d */
    protected void mo3011d() {
    }

    public GoldExpireDialog(Context context) {
        super(context, false, C0692R.style.a4p);
    }

    @Override // com.nrzs.p067ft.p068ui.base.FtBaseDialog
    /* renamed from: a */
    protected void mo3014a() {
        setCancelable(false);
    }

    @Override // com.nrzs.p067ft.p068ui.base.FtBaseDialog
    /* renamed from: b */
    protected void mo3013b() {
        setContentView(C0692R.layout.f3435fe);
        this.f21577a = (ImageView) findViewById(C0692R.C0694id.f2818o_);
        this.f21578b = (TextView) findViewById(C0692R.C0694id.f3168yu);
    }

    @Override // com.nrzs.p067ft.p068ui.base.FtBaseDialog
    /* renamed from: c */
    protected void mo3012c() {
        this.f21577a.setOnClickListener(new View.OnClickListener() { // from class: z1.ew.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                GoldExpireDialog.this.dismiss();
            }
        });
        this.f21578b.setOnClickListener(new View.OnClickListener() { // from class: z1.ew.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (ChannelDataManager.m12655a().m12650d()) {
                    new CdKeyDialog(GoldExpireDialog.this.getContext()).show();
                    return;
                }
                AdResultInfoItem adResultInfoItem = new AdResultInfoItem();
                adResultInfoItem.Title = "支持鸟人送金币";
                adResultInfoItem.ExecArgs = HttpVal.f16516c;
                NRZSWebviewActivity.m25006a(GoldExpireDialog.this.getContext(), 0, 1, 2, adResultInfoItem);
                GoldExpireDialog.this.dismiss();
            }
        });
    }
}
