package com.lbd.p054xj.socket;

import android.net.LocalServerSocket;
import android.net.LocalSocket;
import android.support.annotation.Nullable;
import com.common.utils.log.LogUtils;
import com.lbd.p054xj.socket.C1545f;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* renamed from: com.lbd.xj.socket.d */
/* loaded from: classes.dex */
public class SocketManager {

    /* renamed from: f */
    private static final String f9552f = "SocketManager";

    /* renamed from: c */
    String f9555c;

    /* renamed from: g */
    private SocketLongCallBack f9558g;

    /* renamed from: a */
    int f9553a = 0;

    /* renamed from: b */
    Map<Integer, SocketChild> f9554b = new HashMap();

    /* renamed from: d */
    C1545f.AbstractRunnableC1552d f9556d = new C1545f.AbstractRunnableC1552d() { // from class: com.lbd.xj.socket.d.1
        @Override // com.lbd.p054xj.socket.C1545f.AbstractRunnableC1552d
        public void onSuccess(@Nullable Object obj) {
        }

        @Override // com.lbd.p054xj.socket.C1545f.AbstractRunnableC1552d
        @Nullable
        public Object doInBackground() throws Throwable {
            while (true) {
                SocketManager.this.m19630e();
            }
        }

        @Override // com.lbd.p054xj.socket.C1545f.AbstractRunnableC1552d
        public void onCancel() {
            LogUtils.m22036e("readInputListenr", "onCancel: ");
            SocketManager.this.f9560i = false;
        }

        @Override // com.lbd.p054xj.socket.C1545f.AbstractRunnableC1552d
        public void onFail(Throwable th) {
            LogUtils.m22036e("readInputListenr", "onFail: " + th.getMessage());
            SocketManager.this.f9560i = false;
        }
    };

    /* renamed from: h */
    private volatile Map<Integer, InputStream> f9559h = new ConcurrentHashMap();

    /* renamed from: i */
    private boolean f9560i = false;

    /* renamed from: e */
    C1545f.AbstractRunnableC1552d f9557e = new C1545f.AbstractRunnableC1552d() { // from class: com.lbd.xj.socket.d.2
        @Override // com.lbd.p054xj.socket.C1545f.AbstractRunnableC1552d
        public void onCancel() {
        }

        @Override // com.lbd.p054xj.socket.C1545f.AbstractRunnableC1552d
        public void onSuccess(@Nullable Object obj) {
        }

        @Override // com.lbd.p054xj.socket.C1545f.AbstractRunnableC1552d
        @Nullable
        public Object doInBackground() throws Throwable {
            LogUtils.m22036e(SocketManager.f9552f, "doInBackground  SocketName:" + SocketManager.this.f9555c + "    SocketID:" + SocketManager.this.f9553a);
            SocketManager dVar = SocketManager.this;
            while (true) {
                LocalSocket accept = new LocalServerSocket(dVar.f9555c).accept();
                SocketManagerServer.m19692b().m19705a();
                int i = SocketManager.this.f9553a + 1;
                SocketManager dVar2 = SocketManager.this;
                dVar2.f9553a = i;
                dVar2.m19644a(accept, i, dVar2.m19633c());
                SocketManager dVar3 = SocketManager.this;
                dVar3.m19635b(dVar3.f9553a, accept.getInputStream());
                SocketManager.this.m19649a(dVar.f9553a);
            }
        }

        @Override // com.lbd.p054xj.socket.C1545f.AbstractRunnableC1552d
        public void onFail(Throwable th) {
            LogUtils.m22036e("onFail", "onFail: " + th.getMessage());
            C1545f.m19586c(SocketManager.this.f9557e);
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public void m19635b(int i, InputStream inputStream) {
        this.f9559h.put(Integer.valueOf(i), inputStream);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: e */
    public void m19630e() {
        ArrayList<Integer> arrayList = new ArrayList();
        for (Map.Entry<Integer, InputStream> entry : this.f9559h.entrySet()) {
            try {
                m19648a(entry.getKey().intValue(), entry.getValue());
            } catch (IOException e) {
                LogUtils.m22036e("readInputListenr", "readInputListenr: ." + e.getMessage());
                arrayList.add(entry.getKey());
                e.printStackTrace();
            }
        }
        for (Integer num : arrayList) {
            this.f9559h.remove(num);
        }
        try {
            Thread.sleep(100L);
        } catch (InterruptedException e2) {
            e2.printStackTrace();
        }
    }

    /* renamed from: f */
    private C1545f.AbstractRunnableC1552d m19629f() {
        return new C1545f.AbstractRunnableC1552d() { // from class: com.lbd.xj.socket.d.3
            @Override // com.lbd.p054xj.socket.C1545f.AbstractRunnableC1552d
            public void onSuccess(@Nullable Object obj) {
            }

            @Override // com.lbd.p054xj.socket.C1545f.AbstractRunnableC1552d
            @Nullable
            public Object doInBackground() throws Throwable {
                while (true) {
                    SocketManager.this.m19630e();
                }
            }

            @Override // com.lbd.p054xj.socket.C1545f.AbstractRunnableC1552d
            public void onCancel() {
                LogUtils.m22036e("readInputListenr", "onCancel: ");
                SocketManager.this.f9560i = false;
            }

            @Override // com.lbd.p054xj.socket.C1545f.AbstractRunnableC1552d
            public void onFail(Throwable th) {
                LogUtils.m22036e("readInputListenr", "onFail: " + th.getMessage());
                SocketManager.this.m19637b();
                SocketManager.this.f9560i = false;
            }
        };
    }

    /* renamed from: a */
    public boolean m19650a() {
        return this.f9560i;
    }

    /* renamed from: b */
    public void m19637b() {
        LogUtils.m22036e("readInputListenr", "startInputListener");
        C1545f.m19586c(m19629f());
        this.f9560i = true;
    }

    /* renamed from: a */
    public void m19648a(int i, InputStream inputStream) throws IOException {
        int available = (m19636b(i) == null || !m19636b(i).f9540d || !m19636b(i).m19657b()) ? 0 : inputStream.available();
        if (available != 0) {
            byte[] bArr = new byte[available];
            inputStream.read(bArr);
            String str = new String(bArr);
            if (this.f9558g != null && str.length() > 5) {
                this.f9558g.mo19652a(str, i);
            }
        }
    }

    public SocketManager(String str) {
        this.f9555c = str;
        C1545f.m19586c(this.f9557e);
        m19637b();
    }

    /* renamed from: a */
    public void m19649a(int i) {
        Map<Integer, SocketChild> map = this.f9554b;
        if (map != null && map.containsKey(Integer.valueOf(i))) {
            m19636b(i).m19662a(this.f9558g);
        }
    }

    /* renamed from: a */
    public void m19643a(SocketLongCallBack cVar) {
        this.f9558g = cVar;
    }

    /* renamed from: c */
    public SocketLongCallBack m19633c() {
        return this.f9558g;
    }

    /* renamed from: a */
    public void m19639a(String str) {
        ArrayList<Integer> arrayList = new ArrayList();
        synchronized (this.f9554b) {
            for (Map.Entry<Integer, SocketChild> entry : this.f9554b.entrySet()) {
                if (entry.getValue().m19657b()) {
                    entry.getValue().m19660a(str);
                } else {
                    arrayList.add(entry.getKey());
                }
            }
            for (Integer num : arrayList) {
                if (this.f9554b.containsKey(num)) {
                    m19632c(num.intValue());
                    this.f9559h.containsKey(num);
                }
            }
        }
    }

    /* renamed from: a */
    public void m19638a(String str, String str2) {
        ArrayList arrayList = new ArrayList();
        synchronized (this.f9554b) {
            for (Map.Entry<Integer, SocketChild> entry : this.f9554b.entrySet()) {
                if (entry.getValue().m19657b()) {
                    entry.getValue().m19659a(str, str2);
                } else {
                    arrayList.add(entry.getKey());
                }
            }
        }
    }

    /* renamed from: a */
    public void m19647a(int i, String str) {
        Map<Integer, SocketChild> map = this.f9554b;
        if (map != null && map.containsKey(Integer.valueOf(i))) {
            this.f9554b.get(Integer.valueOf(i)).m19660a(str);
        }
    }

    /* renamed from: a */
    public void m19646a(int i, String str, String str2) {
        Map<Integer, SocketChild> map = this.f9554b;
        if (map != null && map.containsKey(Integer.valueOf(i))) {
            this.f9554b.get(Integer.valueOf(i)).m19659a(str, str2);
        }
    }

    /* renamed from: b */
    public void m19634b(int i, String str) {
        Map<Integer, SocketChild> map = this.f9554b;
        if (map != null && map.containsKey(Integer.valueOf(i))) {
            this.f9554b.get(Integer.valueOf(i)).m19655b(str);
        }
    }

    /* renamed from: b */
    public SocketChild m19636b(int i) {
        Map<Integer, SocketChild> map = this.f9554b;
        if (map == null || !map.containsKey(Integer.valueOf(i))) {
            return null;
        }
        return this.f9554b.get(Integer.valueOf(i));
    }

    /* renamed from: a */
    public void m19645a(LocalSocket localSocket, int i) {
        this.f9554b.put(Integer.valueOf(i), new SocketChild(localSocket));
    }

    /* renamed from: a */
    public void m19644a(LocalSocket localSocket, int i, SocketLongCallBack cVar) {
        this.f9554b.put(Integer.valueOf(i), new SocketChild(localSocket, cVar, i));
    }

    /* renamed from: c */
    public void m19632c(int i) {
        synchronized (this.f9554b) {
            LogUtils.m22036e("removeSocket", "localSocketid: " + i);
            Iterator<Map.Entry<Integer, SocketChild>> it = this.f9554b.entrySet().iterator();
            while (it.hasNext()) {
                LogUtils.m22036e("removeSocket", "getKey: " + it.next().getKey());
            }
            if (this.f9554b.containsKey(Integer.valueOf(i))) {
                this.f9554b.get(Integer.valueOf(i)).m19663a();
                this.f9554b.remove(Integer.valueOf(i));
            }
            LogUtils.m22036e("removeSocket", "size:" + this.f9554b.size());
        }
    }

    /* renamed from: d */
    public void m19631d() {
        for (Map.Entry<Integer, SocketChild> entry : this.f9554b.entrySet()) {
            entry.getValue().m19663a();
        }
        this.f9554b.clear();
        this.f9554b = null;
    }
}
