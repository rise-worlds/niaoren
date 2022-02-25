package com.tendcloud.tenddata;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.liulishuo.filedownloader.model.ConnectionModel;
import com.tendcloud.tenddata.AbstractC2732bp;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import org.apache.tools.ant.types.selectors.ContainsSelector;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.be */
/* loaded from: classes2.dex */
public class C2702be implements AbstractC2732bp.AbstractC2740f {

    /* renamed from: d */
    private static final int f13566d = 128;

    /* renamed from: e */
    private static final int f13567e = 1000;

    /* renamed from: a */
    private final Handler f13568a;

    /* renamed from: c */
    private final Map f13570c = new HashMap();

    /* renamed from: b */
    private final Runnable f13569b = new RunnableC2703a();

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2702be(Handler handler) {
        this.f13568a = handler;
    }

    @Override // com.tendcloud.tenddata.AbstractC2732bp.AbstractC2740f
    public void OnEvent(View view, String str, boolean z) {
        try {
            long currentTimeMillis = System.currentTimeMillis();
            Hashtable hashtable = new Hashtable();
            String a = m16267a(view);
            if (a == null) {
                a = "";
            }
            hashtable.put(ContainsSelector.CONTAINS_KEY, a);
            hashtable.put("dynamic_event", true);
            hashtable.put(ConnectionModel.f10389a, str);
            hashtable.put("time", Long.valueOf(currentTimeMillis));
            if (z) {
                C2704b bVar = new C2704b(view, str);
                C2705c cVar = new C2705c(str, hashtable, currentTimeMillis);
                synchronized (this.f13570c) {
                    boolean isEmpty = this.f13570c.isEmpty();
                    this.f13570c.put(bVar, cVar);
                    if (isEmpty) {
                        this.f13568a.postDelayed(this.f13569b, 1000L);
                    }
                }
                return;
            }
            Message obtainMessage = this.f13568a.obtainMessage(13);
            obtainMessage.obj = hashtable;
            this.f13568a.sendMessage(obtainMessage);
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }

    /* compiled from: td */
    /* renamed from: com.tendcloud.tenddata.be$a */
    /* loaded from: classes2.dex */
    final class RunnableC2703a implements Runnable {
        private RunnableC2703a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            long currentTimeMillis = System.currentTimeMillis();
            synchronized (C2702be.this.f13570c) {
                Iterator it = C2702be.this.f13570c.entrySet().iterator();
                while (it.hasNext()) {
                    C2705c cVar = (C2705c) ((Map.Entry) it.next()).getValue();
                    if (currentTimeMillis - cVar.timeSentMillis > 1000) {
                        TCAgent.onEvent(C2664ab.f13513g, cVar.eventName, "", cVar.properties);
                        it.remove();
                    }
                }
                if (!C2702be.this.f13570c.isEmpty()) {
                    C2702be.this.f13568a.postDelayed(this, 500L);
                }
            }
        }
    }

    /* renamed from: a */
    private static String m16267a(View view) {
        if (view == null) {
            return "";
        }
        try {
            if (view instanceof TextView) {
                CharSequence text = ((TextView) view).getText();
                if (text != null) {
                    return text.toString();
                }
                return null;
            } else if (!(view instanceof ViewGroup)) {
                return null;
            } else {
                StringBuilder sb = new StringBuilder();
                ViewGroup viewGroup = (ViewGroup) view;
                int childCount = viewGroup.getChildCount();
                boolean z = false;
                for (int i = 0; i < childCount && sb.length() < 128; i++) {
                    String a = m16267a(viewGroup.getChildAt(i));
                    if (a != null && a.length() > 0) {
                        if (z) {
                            sb.append(",");
                        }
                        sb.append(a);
                        z = true;
                    }
                }
                if (sb.length() > 128) {
                    return sb.substring(0, 128);
                }
                if (z) {
                    return sb.toString();
                }
                return null;
            }
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
            return null;
        }
    }

    /* compiled from: td */
    /* renamed from: com.tendcloud.tenddata.be$b */
    /* loaded from: classes2.dex */
    static class C2704b {
        private final int mHashCode;

        public C2704b(View view, String str) {
            this.mHashCode = view.hashCode() ^ str.hashCode();
        }

        public boolean equals(Object obj) {
            try {
                if (obj instanceof C2704b) {
                    return this.mHashCode == obj.hashCode();
                }
            } catch (Throwable unused) {
            }
            return false;
        }

        public int hashCode() {
            return this.mHashCode;
        }
    }

    /* compiled from: td */
    /* renamed from: com.tendcloud.tenddata.be$c */
    /* loaded from: classes2.dex */
    static class C2705c {
        final String eventName;
        final Hashtable properties;
        final long timeSentMillis;

        C2705c(String str, Hashtable hashtable, long j) {
            this.eventName = str;
            this.properties = hashtable;
            this.timeSentMillis = j;
        }
    }
}
