package p110z1;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import com.angel.nrzs.C0692R;
import com.blankj.utilcode.util.ClickUtils;
import com.nrzs.data.base.BaseResponse;
import com.nrzs.data.ddy.bean.request.AlterNameInfo;
import com.nrzs.http.UICallback;

/* renamed from: z1.dz */
/* loaded from: classes3.dex */
public class AlterNameDialog extends Dialog {

    /* renamed from: a */
    private TextView f21369a;

    /* renamed from: b */
    private TextView f21370b;

    /* renamed from: c */
    private EditText f21371c;

    /* renamed from: d */
    private DdyRepository f21372d;

    /* renamed from: e */
    private long f21373e;

    /* renamed from: f */
    private AbstractC5281a f21374f;

    /* renamed from: g */
    private UICallback<BaseResponse<Object>> f21375g = new UICallback<BaseResponse<Object>>() { // from class: z1.dz.1
        @Override // com.nrzs.http.UICallback
        /* renamed from: a */
        public void mo3021a(Throwable th) {
            aqg.m11586a(AlterNameDialog.this.getContext(), "修改名称失败");
        }

        /* renamed from: a  reason: avoid collision after fix types in other method */
        public void mo3022a(BaseResponse<Object> baseResponse) {
            if (baseResponse.code == 1) {
                aqg.m11586a(AlterNameDialog.this.getContext(), "修改名称成功");
                AlterNameDialog.this.f21374f.mo3187a(AlterNameDialog.this.f21371c.getText().toString().trim());
                AlterNameDialog.this.dismiss();
                return;
            }
            aqg.m11586a(AlterNameDialog.this.getContext(), "修改名称失败");
        }
    };

    /* compiled from: AlterNameDialog.java */
    /* renamed from: z1.dz$a */
    /* loaded from: classes3.dex */
    public interface AbstractC5281a {
        /* renamed from: a */
        void mo3187a(String str);
    }

    /* renamed from: e */
    protected void m3189e() {
    }

    public AlterNameDialog(Context context, AbstractC5281a aVar, long j) {
        super(context);
        this.f21374f = aVar;
        this.f21373e = j;
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().requestFeature(1);
        setContentView(C0692R.layout.nrzs_alter_orname);
        setCancelable(true);
        m3193b();
        m3191c();
        m3190d();
    }

    /* renamed from: a */
    public void m3195a() {
        if (this.f21372d == null) {
            this.f21372d = new DdyRepository();
        }
        AlterNameInfo alterNameInfo = new AlterNameInfo();
        alterNameInfo.UserId = LoginManager.m12620d().m12614j();
        alterNameInfo.OrderId = this.f21373e;
        alterNameInfo.DeviceName = this.f21371c.getText().toString().trim();
        this.f21372d.m12877a(alterNameInfo, this.f21375g);
    }

    /* renamed from: b */
    protected void m3193b() {
        setCancelable(true);
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        getWindow().setGravity(17);
        getWindow().getDecorView().setPadding(0, 0, 0, 0);
        getWindow().getDecorView().setBackgroundColor(getContext().getResources().getColor(C0692R.color.f1440f2));
        getWindow().setAttributes(attributes);
    }

    /* renamed from: c */
    protected void m3191c() {
        this.f21369a = (TextView) findViewById(C0692R.C0694id.sure_btn);
        this.f21370b = (TextView) findViewById(C0692R.C0694id.cancle_btn);
        this.f21371c = (EditText) findViewById(C0692R.C0694id.editText_btn);
    }

    /* renamed from: d */
    protected void m3190d() {
        this.f21369a.setOnClickListener(new View.OnClickListener() { // from class: z1.dz.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (!TextUtils.isEmpty(AlterNameDialog.this.f21371c.getText().toString().trim())) {
                    AlterNameDialog.this.m3195a();
                } else {
                    aqg.m11586a(AlterNameDialog.this.getContext(), "名称不能为空");
                }
            }
        });
        this.f21370b.setOnClickListener(new ClickUtils.AbstractView$OnClickListenerC1023b() { // from class: z1.dz.3
            @Override // com.blankj.utilcode.util.ClickUtils.AbstractView$OnClickListenerC1023b
            /* renamed from: a */
            public void mo3004a(View view) {
                AlterNameDialog.this.dismiss();
            }
        });
    }
}
