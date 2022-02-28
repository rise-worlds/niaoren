package p110z1;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import com.blankj.utilcode.util.LogUtils;
import com.nrzs.data.p066ft.bean.AssistInfo;
import com.nrzs.ft.FloatApp;
import com.nrzs.ft.ui.base.FtBaseView;
import com.nrzs.ft.ui.view.AssistInfoView;
import com.nrzs.ft.ui.view.AssistListView;
import com.nrzs.ft.ui.view.FtDView;
import java.util.Iterator;
import java.util.Stack;

/* renamed from: z1.ank */
/* loaded from: classes3.dex */
public class FloatViewManager {

    /* renamed from: a */
    private static String f16763a = "FloatViewManager";

    /* renamed from: b */
    private static FloatViewManager f16764b;

    /* renamed from: c */
    private Context f16765c;

    /* renamed from: d */
    private WindowManager f16766d;

    /* renamed from: g */
    private FtDView f16769g;

    /* renamed from: h */
    private AssistSetDialog f16770h;

    /* renamed from: i */
    private FtUserKickDialog f16771i;

    /* renamed from: j */
    private FtLoginKickDialog f16772j;

    /* renamed from: k */
    private FtExceedDialog f16773k;

    /* renamed from: l */
    private FtGlodNotEnoughDialog f16774l;

    /* renamed from: m */
    private FtNeedGoldDialog f16775m;

    /* renamed from: f */
    private boolean f16768f = true;

    /* renamed from: e */
    private Stack<C3711a> f16767e = new Stack<>();

    /* renamed from: a */
    public boolean m12347a() {
        return this.f16768f;
    }

    /* renamed from: a */
    public void m12338a(boolean z) {
        this.f16768f = z;
    }

    /* compiled from: FloatViewManager.java */
    /* renamed from: z1.ank$a */
    /* loaded from: classes3.dex */
    public class C3711a {

        /* renamed from: b */
        private FtBaseView f16777b;

        /* renamed from: c */
        private ViewGroup.LayoutParams f16778c;

        /* renamed from: d */
        private String f16779d;

        C3711a(FtBaseView ftBaseView, ViewGroup.LayoutParams layoutParams, String str) {
            this.f16777b = ftBaseView;
            this.f16778c = layoutParams;
            this.f16779d = str;
        }

        /* renamed from: a */
        public View m12313a() {
            return this.f16777b;
        }

        /* renamed from: a */
        public void m12311a(FtBaseView ftBaseView) {
            this.f16777b = ftBaseView;
        }

        /* renamed from: b */
        public ViewGroup.LayoutParams m12308b() {
            return this.f16778c;
        }

        /* renamed from: a */
        public void m12312a(ViewGroup.LayoutParams layoutParams) {
            this.f16778c = layoutParams;
        }

        /* renamed from: c */
        public String m12307c() {
            return this.f16779d;
        }

        /* renamed from: a */
        public void m12310a(String str) {
            this.f16779d = str;
        }
    }

    /* renamed from: a */
    public static FloatViewManager m12346a(Context context) {
        if (f16764b == null) {
            synchronized (FloatViewManager.class) {
                if (f16764b == null) {
                    f16764b = new FloatViewManager(context);
                }
            }
        }
        return f16764b;
    }

    private FloatViewManager(Context context) {
        this.f16765c = context;
        this.f16766d = (WindowManager) context.getSystemService("window");
    }

    /* renamed from: a */
    public void m12340a(FtBaseView ftBaseView, ViewGroup.LayoutParams layoutParams, String str) {
        this.f16766d.addView(ftBaseView, layoutParams);
        this.f16767e.push(new C3711a(ftBaseView, layoutParams, str));
    }

    /* renamed from: a */
    public void m12339a(String str) {
        C3711a c = m12331c(str);
        if (c != null && c.m12313a() != null) {
            c.m12313a().setVisibility(8);
        }
    }

    /* renamed from: b */
    public void m12335b(String str) {
        C3711a c = m12331c(str);
        if (!(c == null || c.m12313a() == null)) {
            this.f16766d.removeView(c.m12313a());
        }
        try {
            this.f16767e.remove(c);
        } catch (Exception e) {
            String str2 = f16763a;
            Log.e(str2, "removeView exception=" + e);
        }
    }

    /* renamed from: c */
    public C3711a m12331c(String str) {
        for (int i = 0; i < this.f16767e.size(); i++) {
            if (this.f16767e.get(i).m12307c().equals(str)) {
                return this.f16767e.get(i);
            }
        }
        return null;
    }

    /* renamed from: a */
    public void m12341a(ViewGroup.LayoutParams layoutParams, String str) {
        C3711a c = m12331c(str);
        if (c != null && c.m12313a() != null) {
            this.f16766d.updateViewLayout(c.m12313a(), layoutParams);
            c.m12312a(layoutParams);
        }
    }

    /* renamed from: d */
    public boolean m12328d(String str) {
        C3711a c = m12331c(str);
        if (c == null) {
            return false;
        }
        c.m12313a().setVisibility(0);
        return true;
    }

    /* renamed from: b */
    public void m12337b() {
        if (this.f16767e.size() > 0) {
            Stack<C3711a> stack = this.f16767e;
            stack.get(stack.size() - 1).m12313a().setVisibility(0);
        } else {
            m12336b(FloatApp.m18899b().m18901a());
        }
        this.f16768f = true;
    }

    /* renamed from: c */
    public C3711a m12333c() {
        C3711a peek;
        if (this.f16767e.size() <= 0 || (peek = this.f16767e.peek()) == null) {
            return null;
        }
        return peek;
    }

    /* renamed from: d */
    public void m12330d() {
        C3711a peek;
        if (this.f16767e.size() > 0 && (peek = this.f16767e.peek()) != null) {
            if (peek.m12307c().equals("ScriptListFwinView")) {
                peek.m12313a().setVisibility(8);
                return;
            }
            this.f16766d.removeView(peek.m12313a());
            this.f16767e.pop();
        }
    }

    /* renamed from: e */
    public void m12327e() {
        Iterator<C3711a> it = this.f16767e.iterator();
        while (it.hasNext()) {
            this.f16766d.removeView(it.next().m12313a());
        }
        this.f16767e.clear();
    }

    /* renamed from: f */
    public void m12325f() {
        if (this.f16767e.size() > 0) {
            Iterator<C3711a> it = this.f16767e.iterator();
            while (it.hasNext()) {
                it.next().m12313a().setVisibility(8);
            }
        }
        this.f16768f = false;
    }

    /* renamed from: g */
    public int m12323g() {
        Stack<C3711a> stack = this.f16767e;
        if (stack != null) {
            return stack.size();
        }
        return 0;
    }

    /* renamed from: h */
    public void m12321h() {
        m12327e();
        m12319j();
        m12316m();
        m12317l();
        m12320i();
    }

    /* renamed from: b */
    public void m12336b(Context context) {
        C3711a c = m12331c(AssistListView.class.getName());
        if (c != null) {
            c.f16777b.setVisibility(0);
        } else {
            new AssistListView(context).m18885a(0, 0, true, AssistListView.class.getName(), false);
        }
    }

    /* renamed from: a */
    public void m12343a(Context context, AssistInfo assistInfo) {
        C3711a c = m12331c(AssistInfoView.class.getName());
        if (c != null) {
            c.f16777b.setVisibility(0);
        } else {
            new AssistInfoView(context, assistInfo).m18885a(0, 0, true, AssistInfoView.class.getName(), false);
        }
    }

    /* renamed from: c */
    public void m12332c(Context context) {
        if (this.f16769g == null) {
            this.f16769g = new FtDView(context);
        }
        this.f16769g.m18854c();
    }

    /* renamed from: i */
    public void m12320i() {
        FtDView ftDView = this.f16769g;
        if (ftDView != null) {
            ftDView.m18851d();
            this.f16769g.m18843g();
            this.f16769g = null;
        }
    }

    /* renamed from: a */
    public void m12344a(Context context, LinearLayout linearLayout) {
        if (this.f16770h == null) {
            this.f16770h = new AssistSetDialog(context, linearLayout);
        }
        if (!this.f16770h.isShowing()) {
            this.f16770h.show();
        }
    }

    /* renamed from: j */
    public void m12319j() {
        AssistSetDialog ansVar = this.f16770h;
        if (ansVar != null) {
            ansVar.m12213b();
            this.f16770h = null;
        }
    }

    /* renamed from: d */
    public void m12329d(Context context) {
        m12342a(context, true);
    }

    /* renamed from: a */
    public void m12342a(Context context, boolean z) {
        LogUtils.m23734c("newEngin", "onEventMainThread - showUserKickDialog");
        if (this.f16771i == null) {
            this.f16771i = new FtUserKickDialog(context);
        }
        if (!this.f16771i.isShowing()) {
            this.f16771i.show();
        }
        this.f16771i.m12172a(z);
    }

    /* renamed from: k */
    public void m12318k() {
        FtUserKickDialog anzVar = this.f16771i;
        if (anzVar != null) {
            anzVar.dismiss();
            this.f16771i = null;
        }
    }

    /* renamed from: b */
    public void m12334b(boolean z) {
        AssistSetDialog ansVar = this.f16770h;
        if (ansVar != null) {
            ansVar.m12214a(z);
        }
    }

    /* renamed from: a */
    public void m12345a(Context context, int i) {
        m12335b(AssistListView.class.getName());
        m12335b(AssistInfoView.class.getName());
        if (this.f16772j == null) {
            this.f16772j = new FtLoginKickDialog(context, i);
        }
        if (!this.f16772j.isShowing()) {
            this.f16772j.m12187a(i);
            this.f16772j.show();
        }
        String str = f16763a;
        Log.i(str, "showLoginKickDialog: ftLoginKickDialog：" + this.f16772j);
    }

    /* renamed from: l */
    public void m12317l() {
        String str = f16763a;
        Log.i(str, "dismissFtLoginKickDialog: ftLoginKickDialog：" + this.f16772j);
        FtLoginKickDialog anxVar = this.f16772j;
        if (anxVar != null) {
            anxVar.dismiss();
            this.f16772j = null;
        }
    }

    /* renamed from: e */
    public void m12326e(Context context) {
        m12335b(AssistListView.class.getName());
        m12335b(AssistInfoView.class.getName());
        if (this.f16773k == null) {
            this.f16773k = new FtExceedDialog(context);
        }
        if (!this.f16773k.isShowing()) {
            this.f16773k.show();
        }
    }

    /* renamed from: m */
    public void m12316m() {
        FtExceedDialog anvVar = this.f16773k;
        if (anvVar != null) {
            anvVar.dismiss();
            this.f16773k = null;
        }
    }

    /* renamed from: f */
    public void m12324f(Context context) {
        m12335b(AssistListView.class.getName());
        m12335b(AssistInfoView.class.getName());
        if (this.f16774l == null) {
            this.f16774l = new FtGlodNotEnoughDialog(context);
        }
        if (!this.f16774l.isShowing()) {
            this.f16774l.show();
        }
    }

    /* renamed from: n */
    public void m12315n() {
        FtGlodNotEnoughDialog anwVar = this.f16774l;
        if (anwVar != null) {
            anwVar.dismiss();
            this.f16774l = null;
        }
    }

    /* renamed from: g */
    public void m12322g(Context context) {
        if (this.f16775m == null) {
            this.f16775m = new FtNeedGoldDialog(context);
        }
        if (!this.f16775m.isShowing()) {
            this.f16775m.show();
        }
    }

    /* renamed from: o */
    public void m12314o() {
        FtNeedGoldDialog anyVar = this.f16775m;
        if (anyVar != null) {
            anyVar.dismiss();
            this.f16775m = null;
        }
    }
}
