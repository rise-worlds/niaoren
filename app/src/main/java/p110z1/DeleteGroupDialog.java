package p110z1;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import com.angel.nrzs.C0692R;
import com.blankj.utilcode.util.ClickUtils;
import com.nrzs.data.base.BaseResponse;
import com.nrzs.data.ddy.bean.request.CheasseGroup;
import com.nrzs.http.ThreadCallback;
import com.nrzs.http.UICallback;
import p110z1.GroupFlushEvent;

/* renamed from: z1.ec */
/* loaded from: classes3.dex */
public class DeleteGroupDialog extends Dialog {

    /* renamed from: a */
    private TextView f21397a;

    /* renamed from: b */
    private TextView f21398b;

    /* renamed from: c */
    private DdyRepository f21399c;

    /* renamed from: d */
    private AbstractC5307eh f21400d;

    /* renamed from: e */
    private long f21401e;

    /* renamed from: f */
    private UICallback<BaseResponse<Object>> f21402f = new UICallback<BaseResponse<Object>>() { // from class: z1.ec.1
        @Override // com.nrzs.http.UICallback
        /* renamed from: a */
        public void mo3021a(Throwable th) {
            aqg.m11586a(DeleteGroupDialog.this.getContext(), "删除失败");
        }

        /* renamed from: a  reason: avoid collision after fix types in other method */
        public void mo3022a(BaseResponse<Object> baseResponse) {
            if (baseResponse.code == 1) {
                EventBus.m3448a().m3427d(new GroupFlushEvent.C3557a());
                DeleteGroupDialog.this.f21400d.mo3131a();
                DeleteGroupDialog.this.dismiss();
                return;
            }
            aqg.m11586a(DeleteGroupDialog.this.getContext(), "删除失败");
        }
    };

    /* renamed from: g */
    private ThreadCallback f21403g = new ThreadCallback<Object, String>() { // from class: z1.ec.2
        @Override // com.nrzs.http.ThreadCallback
        public Object onResponse(String str) {
            return (BaseResponse) apa.m11877a(str, new TypeToken<BaseResponse<Object>>() { // from class: z1.ec.2.1
            });
        }
    };

    /* renamed from: e */
    protected void m3171e() {
    }

    public DeleteGroupDialog(Context context, long j, AbstractC5307eh ehVar) {
        super(context);
        this.f21401e = j;
        this.f21400d = ehVar;
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().requestFeature(1);
        setContentView(C0692R.layout.nrzs_delete_group);
        setCancelable(true);
        m3174b();
        m3173c();
        m3172d();
    }

    /* renamed from: a */
    public void m3176a() {
        if (this.f21399c == null) {
            this.f21399c = new DdyRepository();
        }
        CheasseGroup cheasseGroup = new CheasseGroup();
        cheasseGroup.UserId = LoginManager.m12620d().m12614j();
        cheasseGroup.OpType = 3;
        cheasseGroup.GroupId = this.f21401e;
        this.f21399c.m12876a(cheasseGroup, this.f21402f, this.f21403g);
    }

    /* renamed from: b */
    protected void m3174b() {
        setCancelable(true);
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        getWindow().setGravity(17);
        getWindow().getDecorView().setPadding(0, 0, 0, 0);
        getWindow().getDecorView().setBackgroundColor(getContext().getResources().getColor(C0692R.color.f1440f2));
        getWindow().setAttributes(attributes);
    }

    /* renamed from: c */
    protected void m3173c() {
        this.f21397a = (TextView) findViewById(C0692R.C0694id.sure_btn);
        this.f21398b = (TextView) findViewById(C0692R.C0694id.cancel_btn);
    }

    /* renamed from: d */
    protected void m3172d() {
        this.f21397a.setOnClickListener(new View.OnClickListener() { // from class: z1.ec.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DeleteGroupDialog.this.m3176a();
            }
        });
        this.f21398b.setOnClickListener(new ClickUtils.AbstractView$OnClickListenerC1023b() { // from class: z1.ec.4
            @Override // com.blankj.utilcode.util.ClickUtils.AbstractView$OnClickListenerC1023b
            /* renamed from: a */
            public void mo3004a(View view) {
                DeleteGroupDialog.this.dismiss();
            }
        });
    }
}
