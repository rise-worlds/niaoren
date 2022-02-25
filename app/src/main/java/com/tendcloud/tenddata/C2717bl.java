package com.tendcloud.tenddata;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.JsonWriter;
import android.util.Pair;
import com.common.utils.VMProperUtil;
import com.liulishuo.filedownloader.model.ConnectionModel;
import com.tendcloud.tenddata.AbstractC2713bj;
import com.tendcloud.tenddata.AbstractC2732bp;
import com.tendcloud.tenddata.C2707bg;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.OutputStreamWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javax.net.ssl.SSLSocketFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import p110z1.C4963cj;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: td */
@TargetApi(16)
/* renamed from: com.tendcloud.tenddata.bl */
/* loaded from: classes2.dex */
public class C2717bl implements AbstractC2732bp.AbstractC2741g {

    /* renamed from: a */
    static final int f13581a = 1;

    /* renamed from: b */
    static final int f13582b = 13;

    /* renamed from: c */
    static final String f13583c = "TDDynamicEvent";

    /* renamed from: d */
    static final String f13584d = "TDExceptionLog";

    /* renamed from: e */
    private static volatile C2717bl f13585e = null;

    /* renamed from: l */
    private static final String f13586l = "talkingdata.viewcrawler.changes";

    /* renamed from: m */
    private static final String f13587m = "config.events";

    /* renamed from: n */
    private static final int f13588n = 0;

    /* renamed from: o */
    private static final int f13589o = 2;

    /* renamed from: p */
    private static final int f13590p = 3;

    /* renamed from: q */
    private static final int f13591q = 4;

    /* renamed from: r */
    private static final int f13592r = 5;

    /* renamed from: s */
    private static final int f13593s = 6;

    /* renamed from: t */
    private static final int f13594t = 7;

    /* renamed from: u */
    private static final int f13595u = 8;

    /* renamed from: v */
    private static final int f13596v = 9;

    /* renamed from: w */
    private static final int f13597w = 10;

    /* renamed from: x */
    private static final int f13598x = 11;

    /* renamed from: y */
    private static final int f13599y = 12;

    /* renamed from: f */
    private final Context f13600f;

    /* renamed from: g */
    private final C2702be f13601g;

    /* renamed from: j */
    private final HandlerC2721d f13604j;

    /* renamed from: h */
    private final C2696bb f13602h = new C2696bb();

    /* renamed from: i */
    private final Map f13603i = m16238e();

    /* renamed from: k */
    private final float f13605k = Resources.getSystem().getDisplayMetrics().scaledDensity;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static C2717bl m16251a(Context context, String str, C2961i iVar) {
        if (f13585e == null) {
            synchronized (C2717bl.class) {
                if (f13585e == null) {
                    f13585e = new C2717bl(context, str, iVar);
                }
            }
        }
        return f13585e;
    }

    /* renamed from: a */
    static C2717bl m16252a() {
        return f13585e;
    }

    private C2717bl(Context context, String str, C2961i iVar) {
        this.f13600f = context;
        iVar.setEditState(this.f13602h);
        HandlerThread handlerThread = new HandlerThread("CodelessViewCrawler");
        handlerThread.setPriority(10);
        handlerThread.start();
        this.f13604j = new HandlerC2721d(context, str, handlerThread.getLooper(), this);
        C2744bq.m16223a().m16221a(this.f13604j);
        this.f13601g = new C2702be(this.f13604j);
        m16240d();
    }

    /* renamed from: b */
    HandlerC2721d m16246b() {
        return this.f13604j;
    }

    /* renamed from: d */
    private void m16240d() {
        this.f13604j.start();
        HandlerC2721d dVar = this.f13604j;
        dVar.sendMessage(dVar.obtainMessage(0));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m16248a(JSONArray jSONArray, int i) {
        Message obtainMessage = this.f13604j.obtainMessage(5);
        obtainMessage.obj = jSONArray;
        obtainMessage.arg1 = i;
        this.f13604j.sendMessage(obtainMessage);
    }

    public void setVariants(JSONArray jSONArray) {
        Message obtainMessage = this.f13604j.obtainMessage(9);
        obtainMessage.obj = jSONArray;
        this.f13604j.sendMessage(obtainMessage);
    }

    @Override // com.tendcloud.tenddata.AbstractC2732bp.AbstractC2741g
    public void onLayoutError(AbstractC2732bp.C2739e eVar) {
        Message obtainMessage = this.f13604j.obtainMessage();
        obtainMessage.what = 12;
        obtainMessage.obj = eVar;
        this.f13604j.sendMessage(obtainMessage);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: td */
    /* renamed from: com.tendcloud.tenddata.bl$d */
    /* loaded from: classes2.dex */
    public class HandlerC2721d extends Handler {
        private final Context mContext;
        private C2707bg mEditorConnection;
        private final C2699bd mProtocol;
        private final String mToken;
        private C2727bo mSnapshot = null;
        private final Map mEditorChanges = new HashMap();
        private final List mEditorTweaks = new ArrayList();
        private final List mEditorEventBindings = new ArrayList();
        private final List mPersistentChanges = new ArrayList();
        private final List mPersistentTweaks = new ArrayList();
        private final List mPersistentEventBindings = new ArrayList();
        private final Set mSeenExperiments = new HashSet();
        private final Lock mStartLock = new ReentrantLock();

        public HandlerC2721d(Context context, String str, Looper looper, AbstractC2732bp.AbstractC2741g gVar) {
            super(looper);
            this.mContext = context;
            this.mToken = str;
            this.mProtocol = new C2699bd(new AbstractC2713bj.C2715b(context.getPackageName(), context), gVar);
            this.mStartLock.lock();
        }

        public void start() {
            this.mStartLock.unlock();
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            this.mStartLock.lock();
            try {
                switch (message.what) {
                    case 0:
                        loadKnownChanges();
                        initializeChanges();
                        break;
                    case 1:
                        connectToEditor();
                        break;
                    case 2:
                        sendSnapshot((JSONObject) message.obj);
                        break;
                    case 3:
                        handleEditorChangeReceived((JSONObject) message.obj);
                        break;
                    case 4:
                        sendDeviceInfo();
                        break;
                    case 5:
                        handleEventBindingsReceived(message.obj, message.arg1);
                        break;
                    case 6:
                        handleEditorBindingsReceived((JSONObject) message.obj);
                        break;
                    case 7:
                        sendReportTrackToEditor((String) message.obj);
                        break;
                    case 8:
                        handleEditorClosed();
                        break;
                    case 9:
                        handleVariantsReceived((JSONArray) message.obj);
                        break;
                    case 10:
                        handleEditorBindingsCleared((JSONObject) message.obj);
                        break;
                    case 12:
                        sendLayoutError((AbstractC2732bp.C2739e) message.obj);
                        break;
                    case 13:
                        sendEvent((Hashtable) message.obj);
                        break;
                }
            } catch (Throwable th) {
                try {
                    C2933hb.postSDKError(th);
                } finally {
                    this.mStartLock.unlock();
                }
            }
        }

        private void loadKnownChanges() {
            try {
                SharedPreferences sharedPreferences = getSharedPreferences();
                String string = sharedPreferences.getString(C2717bl.f13586l, null);
                if (string != null) {
                    try {
                        JSONArray jSONArray = new JSONArray(string);
                        int length = jSONArray.length();
                        for (int i = 0; i < length; i++) {
                            JSONObject jSONObject = jSONArray.getJSONObject(i);
                            this.mSeenExperiments.add(new Pair(Integer.valueOf(jSONObject.getInt("experiment_id")), Integer.valueOf(jSONObject.getInt(ConnectionModel.f10389a))));
                        }
                    } catch (JSONException e) {
                        SharedPreferences.Editor edit = sharedPreferences.edit();
                        edit.remove(C2717bl.f13586l);
                        edit.remove(C2717bl.f13587m);
                        edit.apply();
                        C2933hb.postSDKError(e);
                    }
                }
            } catch (Throwable th) {
                C2933hb.postSDKError(th);
            }
        }

        private void initializeChanges() {
            try {
                SharedPreferences sharedPreferences = getSharedPreferences();
                String string = sharedPreferences.getString(C2717bl.f13586l, null);
                String string2 = sharedPreferences.getString(C2717bl.f13587m, null);
                if (string != null) {
                    try {
                        this.mPersistentChanges.clear();
                        this.mPersistentTweaks.clear();
                        JSONArray jSONArray = new JSONArray(string);
                        int length = jSONArray.length();
                        for (int i = 0; i < length; i++) {
                            JSONObject jSONObject = jSONArray.getJSONObject(i);
                            Pair pair = new Pair(Integer.valueOf(jSONObject.getInt("experiment_id")), Integer.valueOf(jSONObject.getInt(ConnectionModel.f10389a)));
                            JSONArray jSONArray2 = jSONObject.getJSONArray("actions");
                            for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                                JSONObject jSONObject2 = jSONArray2.getJSONObject(i2);
                                this.mPersistentChanges.add(new C2719b(C2717bl.m16243b(jSONObject2, "target_activity"), jSONObject2, pair));
                            }
                            JSONArray jSONArray3 = jSONObject.getJSONArray("tweaks");
                            int length2 = jSONArray3.length();
                            for (int i3 = 0; i3 < length2; i3++) {
                                this.mPersistentTweaks.add(new C2720c(jSONArray3.getJSONObject(i3), pair));
                            }
                        }
                    } catch (JSONException e) {
                        SharedPreferences.Editor edit = sharedPreferences.edit();
                        edit.remove(C2717bl.f13586l);
                        edit.remove(C2717bl.f13587m);
                        edit.apply();
                        C2933hb.postSDKError(e);
                    }
                }
                this.mPersistentEventBindings.clear();
                if (string2 != null) {
                    nativeEventBinding(new JSONArray(string2), this.mPersistentEventBindings);
                }
                applyVariantsAndEventBindings();
            } catch (Throwable th) {
                C2933hb.postSDKError(th);
            }
        }

        private void connectToEditor() {
            C2707bg bgVar = this.mEditorConnection;
            if (bgVar == null || !bgVar.m16264a()) {
                String str = C2663aa.f13432B + "/" + C2664ab.m16358a(C2664ab.f13513g, AbstractC2790d.APP);
                try {
                    if (str.startsWith(C2663aa.f13480w)) {
                        SSLSocketFactory a = !C2855es.m15791b("") ? C2813ds.m15976a(true, C2717bl.m16244b("")) : C2813ds.m15976a(false, C2717bl.m16244b(""));
                        if (a != null) {
                            this.mEditorConnection = new C2707bg(new URI(str), new C2718a(), a.createSocket());
                            return;
                        }
                        return;
                    }
                    this.mEditorConnection = new C2707bg(new URI(str), new C2718a(), null);
                } catch (URISyntaxException e) {
                    C2933hb.postSDKError(e);
                } catch (Throwable th) {
                    C2933hb.postSDKError(th);
                }
            }
        }

        private void sendError(String str) {
            if (this.mEditorConnection != null) {
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("error_message", str);
                } catch (JSONException e) {
                    C2933hb.postSDKError(e);
                }
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(this.mEditorConnection.m16261b());
                try {
                    outputStreamWriter.write("{\"type\": \"error\", ");
                    outputStreamWriter.write("\"payload\": ");
                    outputStreamWriter.write(jSONObject.toString());
                    outputStreamWriter.write(C4963cj.f20747d);
                } catch (Throwable th) {
                    try {
                        C2933hb.postSDKError(th);
                    } catch (Throwable th2) {
                        try {
                            outputStreamWriter.close();
                        } catch (Throwable unused) {
                        }
                        throw th2;
                    }
                }
                try {
                    outputStreamWriter.close();
                } catch (Throwable unused2) {
                }
            }
        }

        private void sendDeviceInfo() {
            C2707bg bgVar = this.mEditorConnection;
            if (bgVar != null) {
                JsonWriter jsonWriter = new JsonWriter(new OutputStreamWriter(bgVar.m16261b()));
                try {
                    jsonWriter.beginObject();
                    jsonWriter.name("type").value("device_info_response");
                    jsonWriter.name("payload").beginObject();
                    jsonWriter.name("scaled_density").value(C2717bl.f13585e.f13605k);
                    jsonWriter.name("ssid").value(C2836ec.m15847y(this.mContext));
                    for (Map.Entry entry : C2717bl.m16252a().f13603i.entrySet()) {
                        jsonWriter.name((String) entry.getKey()).value((String) entry.getValue());
                    }
                    jsonWriter.endObject();
                    jsonWriter.endObject();
                } catch (Throwable unused) {
                }
                try {
                    jsonWriter.close();
                } catch (Throwable unused2) {
                }
            }
        }

        private void sendSnapshot(JSONObject jSONObject) {
            long currentTimeMillis = System.currentTimeMillis();
            try {
                JSONObject jSONObject2 = jSONObject.getJSONObject("payload");
                if (jSONObject2.has("config")) {
                    this.mSnapshot = this.mProtocol.m16270a(jSONObject2);
                }
                if (this.mSnapshot != null) {
                    BufferedOutputStream b = this.mEditorConnection.m16261b();
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(b);
                    try {
                        outputStreamWriter.write("{");
                        outputStreamWriter.write("\"type\": \"snapshot_response\",");
                        outputStreamWriter.write("\"payload\": {");
                        outputStreamWriter.write("\"activities\":");
                        outputStreamWriter.flush();
                        this.mSnapshot.m16228a(C2717bl.f13585e.f13602h, b);
                        outputStreamWriter.write(",\"snapshot_time_millis\": ");
                        outputStreamWriter.write(Long.toString(System.currentTimeMillis() - currentTimeMillis));
                        outputStreamWriter.write(C4963cj.f20747d);
                        outputStreamWriter.write(C4963cj.f20747d);
                    } catch (Throwable th) {
                        try {
                            C2933hb.postSDKError(th);
                        } catch (Throwable th2) {
                            try {
                                outputStreamWriter.close();
                            } catch (Throwable unused) {
                            }
                            throw th2;
                        }
                    }
                    try {
                        outputStreamWriter.close();
                    } catch (Throwable unused2) {
                    }
                }
            } catch (Throwable th3) {
                C2933hb.postSDKError(th3);
            }
        }

        private void sendReportTrackToEditor(String str) {
            C2707bg bgVar = this.mEditorConnection;
            if (bgVar != null) {
                JsonWriter jsonWriter = new JsonWriter(new OutputStreamWriter(bgVar.m16261b()));
                try {
                    jsonWriter.beginObject();
                    jsonWriter.name("type").value("track_message");
                    jsonWriter.name("payload");
                    jsonWriter.beginObject();
                    jsonWriter.name("event_name").value(str);
                    jsonWriter.endObject();
                    jsonWriter.endObject();
                    jsonWriter.flush();
                } catch (Throwable th) {
                    try {
                        C2933hb.postSDKError(th);
                    } catch (Throwable th2) {
                        try {
                            jsonWriter.close();
                        } catch (Throwable unused) {
                        }
                        throw th2;
                    }
                }
                try {
                    jsonWriter.close();
                } catch (Throwable unused2) {
                }
            }
        }

        private void sendLayoutError(AbstractC2732bp.C2739e eVar) {
            C2707bg bgVar = this.mEditorConnection;
            if (bgVar != null) {
                JsonWriter jsonWriter = new JsonWriter(new OutputStreamWriter(bgVar.m16261b()));
                try {
                    jsonWriter.beginObject();
                    jsonWriter.name("type").value("layout_error");
                    jsonWriter.name("exception_type").value(eVar.getErrorType());
                    jsonWriter.name("cid").value(eVar.getName());
                    jsonWriter.endObject();
                } catch (Throwable th) {
                    try {
                        C2933hb.postSDKError(th);
                    } catch (Throwable th2) {
                        try {
                            jsonWriter.close();
                        } catch (Throwable unused) {
                        }
                        throw th2;
                    }
                }
                try {
                    jsonWriter.close();
                } catch (Throwable unused2) {
                }
            }
        }

        void sendEvent(Hashtable hashtable) {
            C2707bg bgVar = this.mEditorConnection;
            if (bgVar == null || !bgVar.m16264a()) {
                try {
                    String obj = hashtable.get(ConnectionModel.f10389a).toString();
                    hashtable.remove(ConnectionModel.f10389a);
                    TCAgent.onEvent(C2664ab.f13513g, obj, "", hashtable);
                } catch (Throwable unused) {
                }
            } else {
                JsonWriter jsonWriter = new JsonWriter(new OutputStreamWriter(this.mEditorConnection.m16261b()));
                try {
                    jsonWriter.beginObject();
                    jsonWriter.name("type").value("event_triggered_response");
                    for (Map.Entry entry : hashtable.entrySet()) {
                        JsonWriter name = jsonWriter.name((String) entry.getKey());
                        name.value(entry.getValue() + "");
                    }
                    jsonWriter.endObject();
                } catch (Throwable th) {
                    try {
                        C2933hb.postSDKError(th);
                    } catch (Throwable th2) {
                        try {
                            jsonWriter.close();
                        } catch (Throwable unused2) {
                        }
                        throw th2;
                    }
                }
                try {
                    jsonWriter.close();
                } catch (Throwable unused3) {
                }
            }
        }

        private void handleEditorChangeReceived(JSONObject jSONObject) {
            try {
                JSONArray jSONArray = jSONObject.getJSONObject("payload").getJSONArray("actions");
                for (int i = 0; i < jSONArray.length(); i++) {
                    JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                    String b = C2717bl.m16243b(jSONObject2, "target_activity");
                    this.mEditorChanges.put(jSONObject2.getString(ConnectionModel.f10389a), new Pair(b, jSONObject2));
                }
                applyVariantsAndEventBindings();
            } catch (Throwable th) {
                C2933hb.postSDKError(th);
            }
        }

        private void handleEditorBindingsCleared(JSONObject jSONObject) {
            try {
                JSONArray jSONArray = jSONObject.getJSONObject("payload").getJSONArray("actions");
                for (int i = 0; i < jSONArray.length(); i++) {
                    this.mEditorChanges.remove(jSONArray.getString(i));
                }
            } catch (Throwable th) {
                C2933hb.postSDKError(th);
            }
            applyVariantsAndEventBindings();
        }

        private void handleVariantsReceived(JSONArray jSONArray) {
            try {
                SharedPreferences.Editor edit = getSharedPreferences().edit();
                edit.putString(C2717bl.f13586l, jSONArray.toString());
                edit.apply();
                initializeChanges();
            } catch (Throwable th) {
                C2933hb.postSDKError(th);
            }
        }

        private void handleEventBindingsReceived(Object obj, int i) {
            try {
                SharedPreferences sharedPreferences = getSharedPreferences();
                String str = "";
                if (i == 0) {
                    str = C2717bl.f13587m;
                }
                if (obj != null && (obj instanceof JSONArray)) {
                    SharedPreferences.Editor edit = sharedPreferences.edit();
                    edit.putString(str, ((JSONArray) obj).toString());
                    edit.apply();
                }
                initializeChanges();
            } catch (Throwable th) {
                C2933hb.postSDKError(th);
            }
        }

        private void handleEditorBindingsReceived(JSONObject jSONObject) {
            try {
                JSONArray jSONArray = jSONObject.getJSONObject("payload").getJSONArray("events");
                this.mEditorEventBindings.clear();
                nativeEventBinding(jSONArray, this.mEditorEventBindings);
                applyVariantsAndEventBindings();
            } catch (Throwable th) {
                C2933hb.postSDKError(th);
            }
        }

        private void nativeEventBinding(JSONArray jSONArray, List list) {
            if (jSONArray != null && jSONArray.length() != 0) {
                try {
                    int length = jSONArray.length();
                    for (int i = 0; i < length; i++) {
                        try {
                            JSONObject jSONObject = jSONArray.getJSONObject(i);
                            list.add(new Pair(C2717bl.m16243b(jSONObject, "target_activity"), jSONObject));
                        } catch (JSONException e) {
                            C2933hb.postSDKError(e);
                        }
                    }
                } catch (Throwable unused) {
                }
            }
        }

        private void handleEditorClosed() {
            try {
                this.mEditorChanges.clear();
                this.mEditorEventBindings.clear();
                this.mSnapshot = null;
                C2811dq.dForDeveloper("Closed.");
                applyVariantsAndEventBindings();
            } catch (Throwable th) {
                C2933hb.postSDKError(th);
            }
        }

        private void applyVariantsAndEventBindings() {
            List list;
            try {
                ArrayList arrayList = new ArrayList();
                HashSet hashSet = new HashSet();
                int size = this.mPersistentEventBindings.size();
                for (int i = 0; i < size; i++) {
                    Pair pair = (Pair) this.mPersistentEventBindings.get(i);
                    AbstractC2732bp a = this.mProtocol.m16269a((JSONObject) pair.second, C2717bl.m16252a().f13601g);
                    if (a != null) {
                        arrayList.add(new Pair(pair.first, a));
                    }
                }
                int size2 = this.mEditorEventBindings.size();
                for (int i2 = 0; i2 < size2; i2++) {
                    Pair pair2 = (Pair) this.mEditorEventBindings.get(i2);
                    arrayList.add(new Pair(pair2.first, this.mProtocol.m16269a((JSONObject) pair2.second, C2717bl.f13585e.f13601g)));
                }
                HashMap hashMap = new HashMap();
                int size3 = arrayList.size();
                for (int i3 = 0; i3 < size3; i3++) {
                    Pair pair3 = (Pair) arrayList.get(i3);
                    if (hashMap.containsKey(pair3.first)) {
                        list = (List) hashMap.get(pair3.first);
                    } else {
                        list = new ArrayList();
                        hashMap.put(pair3.first, list);
                    }
                    list.add(pair3.second);
                }
                C2717bl.f13585e.f13602h.m16277a(hashMap);
                this.mSeenExperiments.addAll(hashSet);
            } catch (Throwable th) {
                C2933hb.postSDKError(th);
            }
        }

        private SharedPreferences getSharedPreferences() {
            return this.mContext.getSharedPreferences("TalingDataConfig" + this.mToken, 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: td */
    /* renamed from: com.tendcloud.tenddata.bl$a */
    /* loaded from: classes2.dex */
    public class C2718a implements C2707bg.AbstractC2708a {
        private C2718a() {
        }

        @Override // com.tendcloud.tenddata.C2707bg.AbstractC2708a
        public void sendSnapshot(JSONObject jSONObject) {
            C2717bl.m16252a().f13604j.removeMessages(2);
            internalSendMessage(2, jSONObject);
        }

        @Override // com.tendcloud.tenddata.C2707bg.AbstractC2708a
        public void performEdit(JSONObject jSONObject) {
            internalSendMessage(3, jSONObject);
        }

        @Override // com.tendcloud.tenddata.C2707bg.AbstractC2708a
        public void clearEdits(JSONObject jSONObject) {
            internalSendMessage(10, jSONObject);
        }

        @Override // com.tendcloud.tenddata.C2707bg.AbstractC2708a
        public void setTweaks(JSONObject jSONObject) {
            internalSendMessage(11, jSONObject);
        }

        @Override // com.tendcloud.tenddata.C2707bg.AbstractC2708a
        public void bindEvents(JSONObject jSONObject) {
            internalSendMessage(6, jSONObject);
        }

        @Override // com.tendcloud.tenddata.C2707bg.AbstractC2708a
        public void sendDeviceInfo() {
            internalSendMessage(4, null);
        }

        @Override // com.tendcloud.tenddata.C2707bg.AbstractC2708a
        public void cleanup() {
            C2717bl.this.f13604j.sendMessage(C2717bl.this.f13604j.obtainMessage(8));
        }

        private void internalSendMessage(int i, JSONObject jSONObject) {
            Message obtainMessage = C2717bl.m16252a().f13604j.obtainMessage(i);
            if (jSONObject != null) {
                obtainMessage.obj = jSONObject;
            }
            C2717bl.m16252a().f13604j.sendMessage(obtainMessage);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: td */
    /* renamed from: com.tendcloud.tenddata.bl$b */
    /* loaded from: classes2.dex */
    public static class C2719b {
        final String activityName;
        public final JSONObject change;
        final Pair variantId;

        C2719b(String str, JSONObject jSONObject, Pair pair) {
            this.activityName = str;
            this.change = jSONObject;
            this.variantId = pair;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: td */
    /* renamed from: com.tendcloud.tenddata.bl$c */
    /* loaded from: classes2.dex */
    public static class C2720c {
        final JSONObject tweak;
        final Pair variantId;

        C2720c(JSONObject jSONObject, Pair pair) {
            this.tweak = jSONObject;
            this.variantId = pair;
        }
    }

    /* renamed from: e */
    private Map m16238e() {
        HashMap hashMap = new HashMap();
        try {
            hashMap.put("sdk_version", C2684as.f13538d);
            hashMap.put("system_name", "Android");
            hashMap.put("system_version", Build.VERSION.RELEASE == null ? "UNKNOWN" : Build.VERSION.RELEASE);
            hashMap.put("device_manufacturer", Build.MANUFACTURER == null ? "UNKNOWN" : Build.MANUFACTURER);
            hashMap.put("device_brand", Build.BRAND == null ? "UNKNOWN" : Build.BRAND);
            hashMap.put("device_model", Build.MODEL == null ? "UNKNOWN" : Build.MODEL);
            try {
                PackageInfo packageInfo = this.f13600f.getPackageManager().getPackageInfo(this.f13600f.getPackageName(), 0);
                hashMap.put("app_version", packageInfo.versionName);
                hashMap.put(VMProperUtil.APPVERSIONCODE, Integer.toString(packageInfo.versionCode));
            } catch (PackageManager.NameNotFoundException e) {
                C2933hb.postSDKError(e);
            }
        } catch (Throwable unused) {
        }
        return hashMap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public static X509Certificate m16244b(String str) {
        if (C2855es.m15791b(str)) {
            return null;
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(str.getBytes());
        try {
            X509Certificate x509Certificate = (X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(byteArrayInputStream);
            try {
                byteArrayInputStream.close();
                return x509Certificate;
            } catch (Throwable unused) {
                return x509Certificate;
            }
        } catch (Throwable th) {
            try {
                C2933hb.postSDKError(th);
                return null;
            } finally {
                try {
                    byteArrayInputStream.close();
                } catch (Throwable unused2) {
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public static String m16243b(JSONObject jSONObject, String str) {
        if (!jSONObject.has(str) || jSONObject.isNull(str)) {
            return null;
        }
        return jSONObject.getString(str);
    }
}
