package com.tencent.smtt.sdk;

import android.content.Context;
import com.tencent.smtt.utils.TbsLog;
import java.util.Arrays;

/* loaded from: classes2.dex */
public class TbsCoreLoadStat {

    /* renamed from: d */
    private static TbsCoreLoadStat f12880d = null;
    public static volatile int mLoadErrorCode = -1;

    /* renamed from: a */
    private TbsSequenceQueue f12881a = null;

    /* renamed from: b */
    private boolean f12882b = false;

    /* renamed from: c */
    private final int f12883c = 3;

    private TbsCoreLoadStat() {
    }

    public static TbsCoreLoadStat getInstance() {
        if (f12880d == null) {
            f12880d = new TbsCoreLoadStat();
        }
        return f12880d;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m17037a() {
        TbsSequenceQueue tbsSequenceQueue = this.f12881a;
        if (tbsSequenceQueue != null) {
            tbsSequenceQueue.clear();
        }
        this.f12882b = false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m17036a(Context context, int i) {
        m17035a(context, i, null);
        TbsLog.m16533e(TbsListener.tag_load_error, "" + i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public synchronized void m17035a(Context context, int i, Throwable th) {
        if (mLoadErrorCode == -1) {
            mLoadErrorCode = i;
            TbsLog.addLog(TbsLog.TBSLOG_CODE_SDK_LOAD_ERROR, "code=%d,desc=%s", Integer.valueOf(i), String.valueOf(th));
            if (th != null) {
                TbsLogReport.getInstance(context).setLoadErrorCode(i, th);
            } else {
                TbsLog.m16533e("TbsCoreLoadStat", "setLoadErrorCode :: error is null with errorCode: " + i + "; Check & correct it!");
            }
            return;
        }
        TbsLog.m16527w("TbsCoreLoadStat", "setLoadErrorCode :: error(" + mLoadErrorCode + ") was already reported; " + i + " is duplicated. Try to remove it!");
    }

    /* loaded from: classes2.dex */
    public class TbsSequenceQueue {

        /* renamed from: b */
        private int f12885b;

        /* renamed from: c */
        private int f12886c;

        /* renamed from: d */
        private int[] f12887d;

        /* renamed from: e */
        private int f12888e;

        /* renamed from: f */
        private int f12889f;

        public TbsSequenceQueue() {
            this.f12885b = 10;
            this.f12888e = 0;
            this.f12889f = 0;
            this.f12886c = this.f12885b;
            this.f12887d = new int[this.f12886c];
        }

        public TbsSequenceQueue(int i, int i2) {
            this.f12885b = 10;
            this.f12888e = 0;
            this.f12889f = 0;
            this.f12886c = i2;
            this.f12887d = new int[this.f12886c];
            this.f12887d[0] = i;
            this.f12889f++;
        }

        public int length() {
            return this.f12889f - this.f12888e;
        }

        public void add(int i) {
            int i2 = this.f12889f;
            if (i2 <= this.f12886c - 1) {
                int[] iArr = this.f12887d;
                this.f12889f = i2 + 1;
                iArr[i2] = i;
                return;
            }
            throw new IndexOutOfBoundsException("sequeue is full");
        }

        public int remove() {
            if (!empty()) {
                int[] iArr = this.f12887d;
                int i = this.f12888e;
                int i2 = iArr[i];
                this.f12888e = i + 1;
                iArr[i] = 0;
                return i2;
            }
            throw new IndexOutOfBoundsException("sequeue is null");
        }

        public int element() {
            if (!empty()) {
                return this.f12887d[this.f12888e];
            }
            throw new IndexOutOfBoundsException("sequeue is null");
        }

        public boolean empty() {
            return this.f12889f == this.f12888e;
        }

        public void clear() {
            Arrays.fill(this.f12887d, 0);
            this.f12888e = 0;
            this.f12889f = 0;
        }

        public String toString() {
            if (empty()) {
                return "";
            }
            StringBuilder sb = new StringBuilder("[");
            for (int i = this.f12888e; i < this.f12889f; i++) {
                sb.append(String.valueOf(this.f12887d[i]) + ",");
            }
            int length = sb.length();
            StringBuilder delete = sb.delete(length - 1, length);
            delete.append("]");
            return delete.toString();
        }
    }
}
