package com.tencent.smtt.sdk;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.tencent.smtt.sdk.TbsReaderView;
import java.util.LinkedList;

/* loaded from: classes2.dex */
public class TbsReaderPredownload {
    public static final int READER_SO_SUCCESS = 2;
    public static final int READER_WAIT_IN_QUEUE = 3;

    /* renamed from: b */
    static final String[] f12953b = {"docx", "pptx", "xlsx", "pdf", "epub", "txt"};

    /* renamed from: i */
    ReaderPreDownloadCallback f12961i;

    /* renamed from: a */
    Handler f12954a = null;

    /* renamed from: c */
    LinkedList<String> f12955c = new LinkedList<>();

    /* renamed from: d */
    boolean f12956d = false;

    /* renamed from: e */
    ReaderWizard f12957e = null;

    /* renamed from: f */
    TbsReaderView.ReaderCallback f12958f = null;

    /* renamed from: g */
    Object f12959g = null;

    /* renamed from: h */
    Context f12960h = null;

    /* renamed from: j */
    String f12962j = "";

    /* loaded from: classes2.dex */
    public interface ReaderPreDownloadCallback {
        public static final int NOTIFY_PLUGIN_FAILED = -1;
        public static final int NOTIFY_PLUGIN_SUCCESS = 0;

        void onEvent(String str, int i, boolean z);
    }

    public TbsReaderPredownload(ReaderPreDownloadCallback readerPreDownloadCallback) {
        this.f12961i = null;
        this.f12961i = readerPreDownloadCallback;
        for (String str : f12953b) {
            this.f12955c.add(str);
        }
        m16974a();
    }

    public boolean init(Context context) {
        if (context == null) {
            return false;
        }
        this.f12960h = context.getApplicationContext();
        boolean a = TbsReaderView.m16966a(context.getApplicationContext());
        this.f12958f = new TbsReaderView.ReaderCallback() { // from class: com.tencent.smtt.sdk.TbsReaderPredownload.1
            @Override // com.tencent.smtt.sdk.TbsReaderView.ReaderCallback
            public void onCallBackAction(Integer num, Object obj, Object obj2) {
                int intValue;
                if (num.intValue() == 5012 && 5014 != (intValue = ((Integer) obj).intValue())) {
                    if (5013 == intValue) {
                        TbsReaderPredownload.this.m16973a(0);
                    } else if (intValue == 0) {
                        TbsReaderPredownload.this.m16973a(0);
                    } else {
                        TbsReaderPredownload.this.m16973a(-1);
                    }
                    TbsReaderPredownload tbsReaderPredownload = TbsReaderPredownload.this;
                    tbsReaderPredownload.f12962j = "";
                    tbsReaderPredownload.m16972a(3, 100);
                }
            }
        };
        try {
            if (this.f12957e == null) {
                this.f12957e = new ReaderWizard(this.f12958f);
            }
            if (this.f12959g == null) {
                this.f12959g = this.f12957e.getTbsReader();
            }
            return this.f12959g != null ? this.f12957e.initTbsReader(this.f12959g, context.getApplicationContext()) : a;
        } catch (NullPointerException unused) {
            Log.e("TbsReaderPredownload", "Unexpect null object!");
            return false;
        }
    }

    public void startAll() {
        this.f12956d = false;
        if (!false && !m16968c(3)) {
            m16972a(3, 100);
        }
    }

    public void start(String str) {
        this.f12956d = false;
        m16969b(3);
        this.f12955c.add(str);
        m16972a(3, 100);
    }

    public void pause() {
        this.f12956d = true;
    }

    public void shutdown() {
        this.f12961i = null;
        this.f12956d = false;
        this.f12955c.clear();
        m16970b();
        ReaderWizard readerWizard = this.f12957e;
        if (readerWizard != null) {
            readerWizard.destroy(this.f12959g);
            this.f12959g = null;
        }
        this.f12960h = null;
    }

    /* renamed from: b */
    private void m16970b() {
        m16969b(3);
    }

    /* renamed from: a */
    boolean m16971a(String str) {
        if (this.f12959g == null || this.f12957e == null || !ReaderWizard.isSupportExt(str)) {
            return false;
        }
        return this.f12957e.checkPlugin(this.f12959g, this.f12960h, str, true);
    }

    /* renamed from: a */
    void m16973a(int i) {
        if (this.f12961i != null) {
            this.f12961i.onEvent(this.f12962j, i, this.f12955c.isEmpty());
        }
    }

    /* renamed from: a */
    void m16974a() {
        this.f12954a = new Handler(Looper.getMainLooper()) { // from class: com.tencent.smtt.sdk.TbsReaderPredownload.2
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (message.what == 3 && !TbsReaderPredownload.this.f12955c.isEmpty() && !TbsReaderPredownload.this.f12956d) {
                    String removeFirst = TbsReaderPredownload.this.f12955c.removeFirst();
                    TbsReaderPredownload tbsReaderPredownload = TbsReaderPredownload.this;
                    tbsReaderPredownload.f12962j = removeFirst;
                    if (!tbsReaderPredownload.m16971a(removeFirst)) {
                        TbsReaderPredownload.this.m16973a(-1);
                    }
                }
            }
        };
    }

    /* renamed from: b */
    void m16969b(int i) {
        this.f12954a.removeMessages(i);
    }

    /* renamed from: c */
    boolean m16968c(int i) {
        return this.f12954a.hasMessages(i);
    }

    /* renamed from: a */
    void m16972a(int i, int i2) {
        this.f12954a.sendMessageDelayed(this.f12954a.obtainMessage(i), i2);
    }
}
