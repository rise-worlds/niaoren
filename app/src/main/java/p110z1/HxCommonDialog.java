package p110z1;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import java.util.HashMap;
import java.util.Map;

/* renamed from: z1.aiu */
/* loaded from: classes3.dex */
public class HxCommonDialog extends AlertDialog {

    /* renamed from: a */
    private C3516c f16015a;

    /* renamed from: b */
    private AbstractC3514a f16016b;

    /* compiled from: HxCommonDialog.java */
    /* renamed from: z1.aiu$a */
    /* loaded from: classes3.dex */
    public interface AbstractC3514a {
        /* renamed from: a */
        void mo12989a(View view);
    }

    /* compiled from: HxCommonDialog.java */
    /* renamed from: z1.aiu$b */
    /* loaded from: classes3.dex */
    public interface AbstractC3515b {
        /* renamed from: a */
        void mo12988a(View view, HxCommonDialog aiuVar);
    }

    public HxCommonDialog(Context context, C3516c cVar) {
        super(context, cVar.f16025f);
        this.f16015a = cVar;
        this.f16016b = cVar.f16030k;
    }

    @Override // android.app.AlertDialog, android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setCancelable(this.f16015a.f16031l);
        m12990c();
        super.setContentView(this.f16015a.f16021b);
        m12994a();
        m12991b();
    }

    /* renamed from: a */
    private void m12994a() {
        if (this.f16016b != null && getWindow() != null) {
            this.f16016b.mo12989a(getWindow().getDecorView());
        }
    }

    /* renamed from: b */
    private void m12991b() {
        Map<Integer, AbstractC3515b> map = this.f16015a.f16029j;
        if (map.size() > 0) {
            for (final Map.Entry<Integer, AbstractC3515b> entry : map.entrySet()) {
                findViewById(entry.getKey().intValue()).setOnClickListener(new View.OnClickListener() { // from class: z1.aiu.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        ((AbstractC3515b) entry.getValue()).mo12988a(view, HxCommonDialog.this);
                    }
                });
            }
        }
        if (this.f16015a.f16027h != null) {
            setOnDismissListener(this.f16015a.f16027h);
        } else {
            setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: z1.aiu.2
                @Override // android.content.DialogInterface.OnDismissListener
                public void onDismiss(DialogInterface dialogInterface) {
                    if (HxCommonDialog.this.f16015a.f16026g != null) {
                        HxCommonDialog.this.f16015a.f16026g.m12987a();
                    }
                }
            });
        }
    }

    /* renamed from: c */
    private void m12990c() {
        Window window = getWindow();
        if (window != null) {
            WindowManager.LayoutParams attributes = getWindow().getAttributes();
            if (-1 != this.f16015a.f16022c) {
                window.setGravity(17);
            }
            if (-2 != this.f16015a.f16023d) {
                attributes.width = this.f16015a.f16023d;
            }
            if (-2 != this.f16015a.f16024e) {
                attributes.height = this.f16015a.f16024e;
            }
            getWindow().setAttributes(attributes);
        }
    }

    /* renamed from: a */
    public static C3516c m12993a(Context context) {
        return new C3516c(context);
    }

    /* compiled from: HxCommonDialog.java */
    /* renamed from: z1.aiu$c */
    /* loaded from: classes3.dex */
    public static class C3516c {

        /* renamed from: a */
        View f16020a;

        /* renamed from: b */
        int f16021b;

        /* renamed from: g */
        C3516c f16026g;

        /* renamed from: h */
        DialogInterface.OnDismissListener f16027h;

        /* renamed from: i */
        Context f16028i;

        /* renamed from: k */
        AbstractC3514a f16030k;

        /* renamed from: c */
        int f16022c = -1;

        /* renamed from: d */
        int f16023d = -2;

        /* renamed from: e */
        int f16024e = -2;

        /* renamed from: f */
        int f16025f = 0;

        /* renamed from: l */
        boolean f16031l = true;

        /* renamed from: j */
        Map<Integer, AbstractC3515b> f16029j = new HashMap();

        public C3516c(Context context) {
            this.f16028i = context;
        }

        /* renamed from: a */
        public C3516c m12986a(int i) {
            this.f16021b = i;
            return this;
        }

        /* renamed from: b */
        public C3516c m12979b(int i) {
            this.f16022c = i;
            return this;
        }

        /* renamed from: c */
        public C3516c m12978c(int i) {
            this.f16023d = i;
            return this;
        }

        /* renamed from: d */
        public C3516c m12977d(int i) {
            this.f16024e = i;
            return this;
        }

        /* renamed from: e */
        public C3516c m12976e(int i) {
            this.f16025f = i;
            return this;
        }

        /* renamed from: a */
        public C3516c m12981a(C3516c cVar) {
            this.f16026g = cVar;
            return this;
        }

        /* renamed from: a */
        public C3516c m12983a(DialogInterface.OnDismissListener onDismissListener) {
            this.f16027h = onDismissListener;
            return this;
        }

        /* renamed from: a */
        public C3516c m12980a(boolean z) {
            this.f16031l = z;
            return this;
        }

        /* renamed from: a */
        public C3516c m12985a(int i, AbstractC3515b bVar) {
            this.f16029j.put(Integer.valueOf(i), bVar);
            return this;
        }

        /* renamed from: a */
        public C3516c m12982a(AbstractC3514a aVar) {
            this.f16030k = aVar;
            return this;
        }

        /* renamed from: a */
        public HxCommonDialog m12984a(Context context) {
            return new HxCommonDialog(context, this);
        }

        /* renamed from: a */
        public void m12987a() {
            m12984a(this.f16028i).show();
        }
    }
}
