package p110z1;

import com.liulishuo.filedownloader.model.ConnectionModel;
import com.stripe.android.PaymentResultListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import org.json.JSONObject;

/* renamed from: z1.dg */
/* loaded from: classes3.dex */
public final class C5261dg {

    /* renamed from: a */
    private File f21301a;

    /* renamed from: b */
    private AbstractC5273ds f21302b;

    public C5261dg(String str, AbstractC5273ds dsVar) {
        this.f21301a = null;
        this.f21302b = null;
        this.f21301a = new File(str);
        this.f21302b = dsVar;
    }

    /* renamed from: a */
    private static String m3224a(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("type", ConnectionModel.f10389a);
            jSONObject.put(PaymentResultListener.f11903c, str);
            return jSONObject.toString();
        } catch (Exception unused) {
            return "";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public synchronized void m3222b() {
        if (this.f21301a != null) {
            if (this.f21301a.exists() && this.f21301a.isDirectory() && this.f21301a.list().length != 0) {
                ArrayList arrayList = new ArrayList();
                for (String str : this.f21301a.list()) {
                    arrayList.add(str);
                }
                Collections.sort(arrayList);
                String str2 = (String) arrayList.get(arrayList.size() - 1);
                int size = arrayList.size();
                if (str2.equals(new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime()) + ".log")) {
                    if (arrayList.size() >= 2) {
                        str2 = (String) arrayList.get(arrayList.size() - 2);
                        size--;
                    } else {
                        return;
                    }
                }
                if (!this.f21302b.mo3202a(m3224a(C5137cu.m3515a(this.f21301a.getAbsolutePath(), str2)))) {
                    size--;
                }
                for (int i = 0; i < size; i++) {
                    new File(this.f21301a, (String) arrayList.get(i)).delete();
                }
            }
        }
    }

    /* renamed from: a */
    public final void m3225a() {
        new Thread(new RunnableC5262dh(this)).start();
    }
}
