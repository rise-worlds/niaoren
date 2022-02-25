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
import com.nrzs.data.ddy.bean.request.CheasseGroup;
import com.nrzs.http.ThreadCallback;
import com.nrzs.http.UICallback;
import p110z1.GroupFlushEvent;

/* renamed from: z1.eg */
/* loaded from: classes3.dex */
public class RnameGroupDialog extends Dialog {

    /* renamed from: a */
    private TextView f21443a;

    /* renamed from: b */
    private TextView f21444b;

    /* renamed from: c */
    private EditText f21445c;

    /* renamed from: d */
    private DdyRepository f21446d;

    /* renamed from: e */
    private long f21447e;

    /* renamed from: f */
    private AbstractC5307eh f21448f;

    /* renamed from: g */
    private UICallback<BaseResponse<Object>> f21449g = new UICallback<BaseResponse<Object>>() { // from class: z1.eg.1
        @Override // com.nrzs.http.UICallback
        /* renamed from: a */
        public void mo3021a(Throwable th) {
            aqg.m11586a(RnameGroupDialog.this.getContext(), "修改失败");
        }

        /* renamed from: a  reason: avoid collision after fix types in other method */
        public void mo3022a(BaseResponse<Object> baseResponse) {
            if (baseResponse.code == 1) {
                EventBus.m3448a().m3427d(new GroupFlushEvent.C3557a());
                RnameGroupDialog.this.f21448f.mo3131a();
                RnameGroupDialog.this.dismiss();
                return;
            }
            aqg.m11586a(RnameGroupDialog.this.getContext(), "修改失败");
        }
    };

    /* renamed from: h */
    private ThreadCallback f21450h = new ThreadCallback<Object, String>() { // from class: z1.eg.2
        @Override // com.nrzs.http.ThreadCallback
        public Object onResponse(String str) {
            return (BaseResponse) apa.m11877a(str, new TypeToken<BaseResponse<Object>>() { // from class: z1.eg.2.1
            });
        }
    };

    /* renamed from: e */
    protected void m3133e() {
    }

    public RnameGroupDialog(Context context, long j, AbstractC5307eh ehVar) {
        super(context);
        this.f21447e = j;
        this.f21448f = ehVar;
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().requestFeature(1);
        setContentView(C0692R.layout.nrzs_rname_group);
        setCancelable(true);
        m3136b();
        m3135c();
        m3134d();
    }

    /* renamed from: a */
    public void m3138a() {
        if (TextUtils.isEmpty(this.f21445c.getText().toString().trim())) {
            aqg.m11586a(getContext(), "请输入分组名称");
            return;
        }
        if (this.f21446d == null) {
            this.f21446d = new DdyRepository();
        }
        CheasseGroup cheasseGroup = new CheasseGroup();
        cheasseGroup.UserId = LoginManager.m12620d().m12614j();
        cheasseGroup.OpType = 2;
        cheasseGroup.GroupId = this.f21447e;
        cheasseGroup.GroupName = this.f21445c.getText().toString().trim();
        this.f21446d.m12876a(cheasseGroup, this.f21449g, this.f21450h);
    }

    /* renamed from: b */
    protected void m3136b() {
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        getWindow().setGravity(17);
        getWindow().getDecorView().setPadding(0, 0, 0, 0);
        getWindow().getDecorView().setBackgroundColor(getContext().getResources().getColor(C0692R.color.f1440f2));
        getWindow().setAttributes(attributes);
    }

    /* renamed from: c */
    protected void m3135c() {
        this.f21443a = (TextView) findViewById(C0692R.C0694id.sure_btn);
        this.f21444b = (TextView) findViewById(C0692R.C0694id.cancle_btn);
        this.f21445c = (EditText) findViewById(C0692R.C0694id.editText_btn);
    }

    /* renamed from: d */
    protected void m3134d() {
        this.f21443a.setOnClickListener(new View.OnClickListener() { // from class: z1.eg.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                RnameGroupDialog.this.m3138a();
            }
        });
        this.f21444b.setOnClickListener(new ClickUtils.AbstractView$OnClickListenerC1023b() { // from class: z1.eg.4
            @Override // com.blankj.utilcode.util.ClickUtils.AbstractView$OnClickListenerC1023b
            /* renamed from: a */
            public void mo3004a(View view) {
                RnameGroupDialog.this.dismiss();
            }
        });
    }
}
