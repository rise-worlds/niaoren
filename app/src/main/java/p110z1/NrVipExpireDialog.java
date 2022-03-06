package p110z1;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import com.angel.nrzs.C0692R;
import com.angel.nrzs.ui.activity.NRZSWebviewActivity;
import com.blankj.utilcode.util.ClickUtils;
import com.nrzs.base.router.provider.ProviderFactory;
import com.nrzs.data.other.bean.AdResultInfoItem;

/* renamed from: z1.ex */
/* loaded from: classes3.dex */
public class NrVipExpireDialog extends Dialog {

    /* renamed from: a */
    private TextView f21581a;

    /* renamed from: b */
    private TextView f21582b;

    /* renamed from: c */
    private ImageView f21583c;

    /* renamed from: d */
    private PopShowManager.AbstractC5313a f21584d;

    /* renamed from: e */
    private Context f21585e;

    public NrVipExpireDialog(Context context, PopShowManager.AbstractC5313a aVar) {
        super(context);
        this.f21584d = aVar;
        this.f21585e = context;
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().requestFeature(1);
        setContentView(C0692R.layout.nrzs_vip_exprire_dialog);
        setCancelable(true);
        m3010a();
        m3007b();
        m3005d();
        m3006c();
    }

    /* renamed from: a */
    protected void m3010a() {
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        getWindow().setGravity(17);
        getWindow().getDecorView().setPadding(0, 0, 0, 0);
        getWindow().getDecorView().setBackgroundColor(getContext().getResources().getColor(C0692R.color.f1440f2));
        getWindow().setAttributes(attributes);
    }

    /* renamed from: b */
    protected void m3007b() {
        this.f21581a = (TextView) findViewById(C0692R.C0694id.time_conten);
        this.f21582b = (TextView) findViewById(C0692R.C0694id.buy_tv);
        this.f21583c = (ImageView) findViewById(C0692R.C0694id.clse_img);
    }

    /* renamed from: c */
    protected void m3006c() {
        this.f21582b.setOnClickListener(new View.OnClickListener() { // from class: z1.ex.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NrVipExpireDialog.this.m3009a(1);
                NrVipExpireDialog.this.dismiss();
            }
        });
        this.f21583c.setOnClickListener(new ClickUtils.AbstractView$OnClickListenerC1023b() { // from class: z1.ex.2
            @Override // com.blankj.utilcode.util.ClickUtils.AbstractView$OnClickListenerC1023b
            /* renamed from: a */
            public void mo3004a(View view) {
                NrVipExpireDialog.this.dismiss();
            }
        });
    }

    /* renamed from: d */
    protected void m3005d() {
        TextView textView = this.f21581a;
        textView.setText("您的鸟人VIP将于" + LoginManager.m12620d().m12609o() + "到期,为保证正常挂机,请您及时续费");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m3009a(int i) {
        if (ChannelDataManager.m12655a().m12650d()) {
            ProviderFactory.createCarkey().showdialog(getContext());
            return;
        }
        AdResultInfoItem adResultInfoItem = new AdResultInfoItem();
        if (i == 2) {
            adResultInfoItem.Title = "支持鸟人送金币";
        } else {
            adResultInfoItem.Title = "购买续费";
        }
        adResultInfoItem.ExecArgs = HttpVal.f16516c;
        NRZSWebviewActivity.m25006a(getContext(), 0, 1, i, adResultInfoItem);
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    @Override // android.app.Dialog
    public void show() {
        super.show();
        PopShowManager.m3117a(this.f21585e).f21463a = true;
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        super.dismiss();
        PopShowManager.m3117a(this.f21585e).f21463a = false;
        this.f21584d.mo3109a(1);
    }
}
