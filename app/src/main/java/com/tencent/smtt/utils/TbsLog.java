package com.tencent.smtt.utils;

import android.content.Context;
import android.os.Process;
import android.util.Log;
import android.widget.TextView;
import com.tencent.smtt.sdk.TbsConfig;
import java.util.LinkedList;
import java.util.List;

/* loaded from: classes2.dex */
public class TbsLog {
    public static final int TBSLOG_CODE_SDK_BASE = 1000;
    public static final int TBSLOG_CODE_SDK_CONFLICT_X5CORE = 993;
    public static final int TBSLOG_CODE_SDK_INIT = 999;
    public static final int TBSLOG_CODE_SDK_INVOKE_ERROR = 997;
    public static final int TBSLOG_CODE_SDK_LOAD_ERROR = 998;
    public static final int TBSLOG_CODE_SDK_NO_SHARE_X5CORE = 994;
    public static final int TBSLOG_CODE_SDK_SELF_MODE = 996;
    public static final int TBSLOG_CODE_SDK_THIRD_MODE = 995;
    public static final int TBSLOG_CODE_SDK_UNAVAIL_X5CORE = 992;
    public static final String X5LOGTAG = "x5logtag";

    /* renamed from: a */
    private static boolean f13262a = false;

    /* renamed from: b */
    private static boolean f13263b = true;

    /* renamed from: c */
    private static TbsLogClient f13264c;
    public static List<String> sTbsLogList = new LinkedList();
    public static int sLogMaxCount = 10;

    public static void setWriteLogJIT(boolean z) {
        f13263b = z;
        if (f13264c != null) {
            TbsLogClient.setWriteLogJIT(z);
        }
    }

    public static void app_extra(String str, Context context) {
        try {
            Context applicationContext = context.getApplicationContext();
            String[] strArr = {TbsConfig.APP_DEMO, TbsConfig.APP_QB, TbsConfig.APP_WX, TbsConfig.APP_QQ, TbsConfig.APP_DEMO_TEST, TbsConfig.APP_QZONE};
            String[] strArr2 = {"DEMO", "QB", "WX", "QQ", "TEST", "QZ"};
            int i = 0;
            while (true) {
                if (i >= strArr.length) {
                    break;
                } else if (applicationContext.getPackageName().contains(strArr[i])) {
                    m16531i(str, "app_extra pid:" + Process.myPid() + "; APP_TAG:" + strArr2[i] + "!");
                    break;
                } else {
                    i++;
                }
            }
            if (i == strArr.length) {
                m16531i(str, "app_extra pid:" + Process.myPid() + "; APP_TAG:OTHER!");
            }
        } catch (Throwable th) {
            m16527w(str, "app_extra exception:" + Log.getStackTraceString(th));
        }
    }

    /* renamed from: i */
    public static void m16531i(String str, String str2) {
        TbsLogClient tbsLogClient = f13264c;
        if (tbsLogClient != null) {
            tbsLogClient.m16522i(str, "TBS:" + str2);
            TbsLogClient tbsLogClient2 = f13264c;
            tbsLogClient2.writeLog("(I)-" + str + "-TBS:" + str2);
        }
    }

    /* renamed from: i */
    public static void m16530i(String str, String str2, boolean z) {
        m16531i(str, str2);
        TbsLogClient tbsLogClient = f13264c;
        if (tbsLogClient != null && f13262a && z) {
            tbsLogClient.showLog(str + ": " + str2);
        }
    }

    /* renamed from: e */
    public static void m16533e(String str, String str2) {
        TbsLogClient tbsLogClient = f13264c;
        if (tbsLogClient != null) {
            tbsLogClient.m16523e(str, "TBS:" + str2);
            TbsLogClient tbsLogClient2 = f13264c;
            tbsLogClient2.writeLog("(E)-" + str + "-TBS:" + str2);
        }
    }

    /* renamed from: e */
    public static void m16532e(String str, String str2, boolean z) {
        m16533e(str, str2);
        TbsLogClient tbsLogClient = f13264c;
        if (tbsLogClient != null && f13262a && z) {
            tbsLogClient.showLog(str + ": " + str2);
        }
    }

    /* renamed from: w */
    public static void m16527w(String str, String str2) {
        TbsLogClient tbsLogClient = f13264c;
        if (tbsLogClient != null) {
            tbsLogClient.m16520w(str, "TBS:" + str2);
            TbsLogClient tbsLogClient2 = f13264c;
            tbsLogClient2.writeLog("(W)-" + str + "-TBS:" + str2);
        }
    }

    /* renamed from: w */
    public static void m16526w(String str, String str2, boolean z) {
        m16527w(str, str2);
        TbsLogClient tbsLogClient = f13264c;
        if (tbsLogClient != null && f13262a && z) {
            tbsLogClient.showLog(str + ": " + str2);
        }
    }

    /* renamed from: d */
    public static void m16535d(String str, String str2) {
        TbsLogClient tbsLogClient = f13264c;
        if (tbsLogClient != null) {
            tbsLogClient.m16524d(str, "TBS:" + str2);
        }
    }

    /* renamed from: d */
    public static void m16534d(String str, String str2, boolean z) {
        m16535d(str, str2);
        TbsLogClient tbsLogClient = f13264c;
        if (tbsLogClient != null && f13262a && z) {
            tbsLogClient.showLog(str + ": " + str2);
        }
    }

    /* renamed from: v */
    public static void m16529v(String str, String str2) {
        TbsLogClient tbsLogClient = f13264c;
        if (tbsLogClient != null) {
            tbsLogClient.m16521v(str, "TBS:" + str2);
        }
    }

    /* renamed from: v */
    public static void m16528v(String str, String str2, boolean z) {
        m16529v(str, str2);
        TbsLogClient tbsLogClient = f13264c;
        if (tbsLogClient != null && f13262a && z) {
            tbsLogClient.showLog(str + ": " + str2);
        }
    }

    public static void setLogView(TextView textView) {
        TbsLogClient tbsLogClient;
        if (textView != null && (tbsLogClient = f13264c) != null) {
            tbsLogClient.setLogView(textView);
        }
    }

    public static boolean setTbsLogClient(TbsLogClient tbsLogClient) {
        if (tbsLogClient == null) {
            return false;
        }
        f13264c = tbsLogClient;
        TbsLogClient tbsLogClient2 = f13264c;
        TbsLogClient.setWriteLogJIT(f13263b);
        return true;
    }

    public static String getTbsLogFilePath() {
        if (TbsLogClient.f13266c != null) {
            return TbsLogClient.f13266c.getAbsolutePath();
        }
        return null;
    }

    public static synchronized void initIfNeed(Context context) {
        synchronized (TbsLog.class) {
            if (f13264c == null) {
                setTbsLogClient(new TbsLogClient(context));
            }
        }
    }

    public static synchronized void writeLogToDisk() {
        synchronized (TbsLog.class) {
            if (f13264c != null) {
                f13264c.writeLogToDisk();
            }
        }
    }

    public static void addLog(int i, String str, Object... objArr) {
        synchronized (sTbsLogList) {
            try {
                if (sTbsLogList.size() > sLogMaxCount) {
                    int size = sTbsLogList.size() - sLogMaxCount;
                    while (true) {
                        size--;
                        if (size <= 0 || sTbsLogList.size() <= 0) {
                            break;
                        }
                        sTbsLogList.remove(0);
                    }
                }
                String str2 = null;
                if (str != null) {
                    try {
                        str2 = String.format(str, objArr);
                    } catch (Exception unused) {
                    }
                }
                if (str2 == null) {
                    str2 = "";
                }
                sTbsLogList.add(String.format("[%d][%d][%c][%d]%s", Long.valueOf(System.currentTimeMillis()), 1, '0', Integer.valueOf(i), str2));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
