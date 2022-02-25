package android.net.http;

import android.os.SystemClock;
import android.util.Log;
import com.ctetin.expandabletextviewlibrary.ExpandableTextView;

/* loaded from: assets/org.apache.http.legacy.boot */
class HttpLog {
    private static final boolean DEBUG = false;
    private static final String LOGTAG = "http";
    static final boolean LOGV = false;

    HttpLog() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: v */
    public static void m25699v(String logMe) {
        Log.v("http", SystemClock.uptimeMillis() + ExpandableTextView.f6958c + Thread.currentThread().getName() + ExpandableTextView.f6958c + logMe);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: e */
    public static void m25700e(String logMe) {
        Log.e("http", logMe);
    }
}
