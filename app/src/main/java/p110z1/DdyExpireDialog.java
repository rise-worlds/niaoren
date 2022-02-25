package p110z1;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import com.angel.nrzs.C0692R;
import com.angel.nrzs.p017ui.activity.NRZSWebviewActivity;
import com.blankj.utilcode.util.ClickUtils;
import com.nrzs.base.router.provider.ProviderFactory;
import com.nrzs.data.ddy.bean.YsjResultInfo;
import com.nrzs.data.ddy.bean.respond.OrderDaileInfo;
import p110z1.PopShowManager;

/* renamed from: z1.ev */
/* loaded from: classes3.dex */
public class DdyExpireDialog extends Dialog {

    /* renamed from: a */
    private TextView f21568a;

    /* renamed from: b */
    private TextView f21569b;

    /* renamed from: c */
    private ImageView f21570c;

    /* renamed from: d */
    private PopShowManager.AbstractC5313a f21571d;

    /* renamed from: e */
    private int f21572e;

    /* renamed from: f */
    private OrderDaileInfo f21573f;

    /* renamed from: g */
    private Context f21574g;

    public DdyExpireDialog(Context context, int i, OrderDaileInfo orderDaileInfo, PopShowManager.AbstractC5313a aVar) {
        super(context);
        this.f21571d = aVar;
        this.f21572e = i;
        this.f21573f = orderDaileInfo;
        this.f21574g = context;
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().requestFeature(1);
        setContentView(C0692R.layout.ddy_exprire_dialog);
        setCancelable(true);
        m3020a();
        m3017b();
        m3015d();
        m3016c();
    }

    /* renamed from: a */
    protected void m3020a() {
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        getWindow().setGravity(17);
        getWindow().getDecorView().setPadding(0, 0, 0, 0);
        getWindow().getDecorView().setBackgroundColor(getContext().getResources().getColor(C0692R.color.f1440f2));
        getWindow().setAttributes(attributes);
    }

    /* renamed from: b */
    protected void m3017b() {
        this.f21568a = (TextView) findViewById(C0692R.C0694id.time_conten);
        this.f21569b = (TextView) findViewById(C0692R.C0694id.buy_tv);
        this.f21570c = (ImageView) findViewById(C0692R.C0694id.clse_img);
    }

    /* renamed from: c */
    protected void m3016c() {
        this.f21569b.setOnClickListener(new View.OnClickListener() { // from class: z1.ev.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DdyExpireDialog.this.m3019a(1);
                DdyExpireDialog.this.dismiss();
            }
        });
        this.f21570c.setOnClickListener(new ClickUtils.AbstractView$OnClickListenerC1023b() { // from class: z1.ev.2
            @Override // com.blankj.utilcode.util.ClickUtils.AbstractView$OnClickListenerC1023b
            /* renamed from: a */
            public void mo3004a(View view) {
                DdyExpireDialog.this.dismiss();
            }
        });
    }

    /* renamed from: d */
    protected void m3015d() {
        TextView textView = this.f21568a;
        textView.setText("您的【" + this.f21573f.DeviceName + "】将于" + this.f21573f.ExpireTime + "到期,为保证正常挂机,请您及时续费");
    }

    @Override // android.app.Dialog
    public void show() {
        super.show();
        PopShowManager.m3117a(this.f21574g).f21463a = true;
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        super.dismiss();
        PopShowManager.m3117a(this.f21574g).f21463a = false;
        this.f21571d.mo3109a(this.f21572e + 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m3019a(int i) {
        if (ChannelDataManager.m12655a().m12650d()) {
            ProviderFactory.createCarkey().showdialog(getContext());
            return;
        }
        YsjResultInfo ysjResultInfo = new YsjResultInfo();
        ysjResultInfo.Title = "续费云手机";
        ysjResultInfo.ExecArgs = HttpVal.f16516c;
        NRZSWebviewActivity.m25005a(getContext(), 0, 1, 5, ysjResultInfo, this.f21573f.f10629Id);
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }
}
