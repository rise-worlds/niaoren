package p110z1;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import com.angel.nrzs.C0692R;
import com.blankj.utilcode.util.ClickUtils;
import com.nrzs.data.base.BaseResponse;
import com.nrzs.data.ddy.bean.request.CheasseGroup;
import com.nrzs.http.ThreadCallback;
import com.nrzs.http.UICallback;
import p110z1.GroupFlushEvent;

/* renamed from: z1.ea */
/* loaded from: classes3.dex */
public class CreateGroupDialog extends Dialog {

    /* renamed from: a */
    private TextView f21379a;

    /* renamed from: b */
    private TextView f21380b;

    /* renamed from: c */
    private EditText f21381c;

    /* renamed from: d */
    private DdyRepository f21382d;

    /* renamed from: e */
    private AbstractC5307eh f21383e;

    /* renamed from: f */
    private UICallback<BaseResponse<Object>> f21384f = new UICallback<BaseResponse<Object>>() { // from class: z1.ea.1
        @Override // com.nrzs.http.UICallback
        /* renamed from: a */
        public void mo3021a(Throwable th) {
            Log.e("DDy", "sb");
            aqg.m11586a(CreateGroupDialog.this.getContext(), "添加失败");
        }

        /* renamed from: a  reason: avoid collision after fix types in other method */
        public void mo3022a(BaseResponse<Object> baseResponse) {
            if (baseResponse.code == 1) {
                EventBus.m3448a().m3427d(new GroupFlushEvent.C3557a());
                CreateGroupDialog.this.f21383e.mo3131a();
                CreateGroupDialog.this.dismiss();
                return;
            }
            aqg.m11586a(CreateGroupDialog.this.getContext(), "添加失败");
        }
    };

    /* renamed from: g */
    private ThreadCallback f21385g = new ThreadCallback<Object, String>() { // from class: z1.ea.2
        @Override // com.nrzs.http.ThreadCallback
        public Object onResponse(String str) {
            return (BaseResponse) apa.m11877a(str, new TypeToken<BaseResponse<Object>>() { // from class: z1.ea.2.1
            });
        }
    };

    /* renamed from: e */
    protected void m3181e() {
    }

    public CreateGroupDialog(Context context, AbstractC5307eh ehVar) {
        super(context);
        this.f21383e = ehVar;
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().requestFeature(1);
        setContentView(C0692R.layout.nrzs_create_group);
        setCancelable(true);
        m3184b();
        m3183c();
        m3182d();
    }

    /* renamed from: a */
    public void m3186a() {
        if (TextUtils.isEmpty(this.f21381c.getText().toString().trim())) {
            aqg.m11586a(getContext(), "请输入分组名称");
            return;
        }
        if (this.f21382d == null) {
            this.f21382d = new DdyRepository();
        }
        CheasseGroup cheasseGroup = new CheasseGroup();
        cheasseGroup.UserId = LoginManager.m12620d().m12614j();
        cheasseGroup.OpType = 1;
        cheasseGroup.GroupName = this.f21381c.getText().toString().trim();
        this.f21382d.m12876a(cheasseGroup, this.f21384f, this.f21385g);
    }

    /* renamed from: b */
    protected void m3184b() {
        setCancelable(true);
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        getWindow().setGravity(17);
        getWindow().getDecorView().setPadding(0, 0, 0, 0);
        getWindow().getDecorView().setBackgroundColor(getContext().getResources().getColor(C0692R.color.f1440f2));
        getWindow().setAttributes(attributes);
    }

    /* renamed from: c */
    protected void m3183c() {
        this.f21379a = (TextView) findViewById(C0692R.C0694id.sure_btn);
        this.f21380b = (TextView) findViewById(C0692R.C0694id.cancle_btn);
        this.f21381c = (EditText) findViewById(C0692R.C0694id.editText_btn);
    }

    /* renamed from: d */
    protected void m3182d() {
        this.f21379a.setOnClickListener(new View.OnClickListener() { // from class: z1.ea.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CreateGroupDialog.this.m3186a();
            }
        });
        this.f21380b.setOnClickListener(new ClickUtils.AbstractView$OnClickListenerC1023b() { // from class: z1.ea.4
            @Override // com.blankj.utilcode.util.ClickUtils.AbstractView$OnClickListenerC1023b
            /* renamed from: a */
            public void mo3004a(View view) {
                CreateGroupDialog.this.dismiss();
            }
        });
    }
}
