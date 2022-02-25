package com.tendcloud.tenddata;

import android.content.Context;
import com.tendcloud.tenddata.C2813ds;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import p110z1.C4745bt;
import p110z1.SimpleComparison;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.hq */
/* loaded from: classes2.dex */
class C2949hq {
    C2949hq() {
    }

    /* renamed from: a */
    private static String m15498a(String str) {
        URL url;
        String str2;
        try {
            if (new URL(C2663aa.f13478u).getPort() == -1) {
                str2 = "";
            } else {
                str2 = ":" + url.getPort();
            }
            return (url.getProtocol() + C2663aa.f13457a + url.getHost() + str2) + str;
        } catch (Throwable unused) {
            return "";
        }
    }

    /* renamed from: a */
    static byte[] m15497a(List list) {
        if (list == null) {
            return null;
        }
        try {
            StringBuffer stringBuffer = new StringBuffer();
            Iterator it = list.iterator();
            while (it.hasNext()) {
                C2952hs hsVar = (C2952hs) it.next();
                stringBuffer.append(hsVar.m15489a() + SimpleComparison.f23609c + hsVar.m15488b() + C4745bt.f20071b);
            }
            if (stringBuffer.length() > 0) {
                stringBuffer.deleteCharAt(stringBuffer.length() - 1);
            }
            return stringBuffer.toString().getBytes();
        } catch (Throwable unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static C2950a m15499a(Context context) {
        C2950a aVar = new C2950a();
        try {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new C2952hs("app", C2951hr.m15493b(context)));
            C2813ds.C2818e a = C2813ds.m15994a(context, AbstractC2790d.PUSH.getHost(), AbstractC2790d.PUSH.getIP(), m15498a(String.format("/api/q/a/%s", C2951hr.m15496a(context))), AbstractC2790d.PUSH.getCert(), m15497a(arrayList), "application/x-www-form-urlencoded");
            if (a.statusCode == 200) {
                String[] split = a.responseMsg.trim().split(":");
                if (split.length == 2) {
                    aVar.f14187ip = split[0];
                    aVar.port = Integer.parseInt(split[1]);
                }
            } else {
                C2811dq.eForInternal("[push] get connector address failed." + a.statusCode);
            }
            return aVar;
        } catch (Throwable unused) {
            return aVar;
        }
    }

    /* compiled from: td */
    /* renamed from: com.tendcloud.tenddata.hq$a */
    /* loaded from: classes2.dex */
    static class C2950a {

        /* renamed from: ip */
        public String f14187ip;
        public int port;

        C2950a() {
        }

        public boolean valid() {
            return !C2948hp.m15502a(this.f14187ip) && this.port != 0;
        }

        public String toString() {
            return String.format("%s:%d", this.f14187ip, Integer.valueOf(this.port));
        }
    }
}
