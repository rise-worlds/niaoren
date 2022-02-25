package p110z1;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import com.angel.nrzs.C0692R;
import com.nrzs.data.other.bean.response.RewarResponseinfo;

/* renamed from: z1.ey */
/* loaded from: classes3.dex */
public class RewardSuccessDialog extends Dialog {

    /* renamed from: a */
    private static RewardSuccessDialog f21588a;

    /* renamed from: b */
    private TextView f21589b;

    /* renamed from: c */
    private ImageView f21590c;

    /* renamed from: d */
    private TextView f21591d;

    /* renamed from: e */
    private TextView f21592e;

    /* renamed from: f */
    private RewarResponseinfo f21593f;

    public RewardSuccessDialog(Context context, RewarResponseinfo rewarResponseinfo) {
        super(context);
        this.f21593f = rewarResponseinfo;
        m2999c();
        m3001b();
    }

    public RewardSuccessDialog(Context context, int i) {
        super(context, i);
    }

    /* renamed from: a */
    public static void m3003a() {
        RewardSuccessDialog eyVar = f21588a;
        if (eyVar != null) {
            eyVar.dismiss();
        }
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        super.dismiss();
        f21588a = null;
    }

    /* renamed from: b */
    public void m3001b() {
        setContentView(C0692R.layout.reward_success);
        this.f21589b = (TextView) findViewById(C0692R.C0694id.f3130xm);
        this.f21590c = (ImageView) findViewById(C0692R.C0694id.iv_author_head);
        this.f21591d = (TextView) findViewById(C0692R.C0694id.nrzs_closimg);
        this.f21591d.setOnClickListener(new View.OnClickListener() { // from class: z1.-$$Lambda$ey$qDLSPhLtggV8u6wv452IdmGaZeY
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RewardSuccessDialog.this.m3000b(view);
            }
        });
        this.f21592e = (TextView) findViewById(C0692R.C0694id.tv_close);
        this.f21592e.setOnClickListener(new View.OnClickListener() { // from class: z1.-$$Lambda$ey$M2v_m9UoDX_pxPPZxtbGzfqz2Ao
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RewardSuccessDialog.this.m3002a(view);
            }
        });
        RewarResponseinfo rewarResponseinfo = this.f21593f;
        if (rewarResponseinfo != null) {
            this.f21589b.setText(rewarResponseinfo.AuthorNickName);
            GlideImageUtils.m11880a(this.f21590c, getContext(), (int) C0692R.C0693drawable.f2238m0, this.f21593f.AuthorHeadImgPath);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public /* synthetic */ void m3000b(View view) {
        dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public /* synthetic */ void m3002a(View view) {
        dismiss();
    }

    /* renamed from: c */
    public void m2999c() {
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        getWindow().setGravity(17);
        getWindow().getDecorView().setPadding(0, 0, 0, 0);
        getWindow().getDecorView().setBackgroundColor(getContext().getResources().getColor(C0692R.color.f1440f2));
        getWindow().setAttributes(attributes);
    }
}
