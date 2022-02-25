package com.common.utils.log;

import android.text.TextUtils;
import android.util.Log;
import com.ctetin.expandabletextviewlibrary.ExpandableTextView;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.apache.tools.ant.util.DateUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import p110z1.Consts;
import p110z1.NRZSFileConfig;
import p110z1.SimpleComparison;

/* loaded from: classes.dex */
public final class LogUtilPrinter implements LogUtilPrinterInterface {
    private static final String BOTTOM_BORDER = "╚════════════════════════════════════════════════════════════════════";
    private static final char BOTTOM_LEFT_CORNER = 9562;
    private static final String DOUBLE_DIVIDER = "══════════════════════════════════";
    private static final char HORIZONTAL_DOUBLE_LINE = 9553;
    private static final int JSON_INDENT = 4;
    private static final String MIDDLE_BORDER = "╟────────────────────────────────────────────────────────────────────";
    private static final char MIDDLE_CORNER = 9567;
    private static final String SINGLE_DIVIDER = "──────────────────────────────────";
    public static String TAG = "PRETTYLOGGER";
    private static final String TOP_BORDER = "╔════════════════════════════════════════════════════════════════════";
    private static final char TOP_LEFT_CORNER = 9556;
    private static final Settings settings = new Settings();
    private static Boolean MYLOG_WRITE_TO_FILE = true;
    private static char MYLOG_TYPE = 'v';
    private static String MYLOG_PATH_SDCARD_DIR = NRZSFileConfig.f16555m;
    private static String MYLOGFILEName = "Log.txt";
    private static SimpleDateFormat myLogSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static SimpleDateFormat logfile = new SimpleDateFormat(DateUtils.ISO8601_DATE_PATTERN);

    @Override // com.common.utils.log.LogUtilPrinterInterface
    public Settings getSettings() {
        return settings;
    }

    @Override // com.common.utils.log.LogUtilPrinterInterface
    /* renamed from: d */
    public void mo22044d(Object obj, String str) {
        log(3, str, obj);
    }

    @Override // com.common.utils.log.LogUtilPrinterInterface
    /* renamed from: e */
    public void mo22043e(Object obj, String str) {
        log(6, str, obj);
    }

    @Override // com.common.utils.log.LogUtilPrinterInterface
    /* renamed from: w */
    public void mo22040w(Object obj, String str) {
        log(5, str, obj);
    }

    @Override // com.common.utils.log.LogUtilPrinterInterface
    /* renamed from: i */
    public void mo22042i(Object obj, String str) {
        log(4, str, obj);
    }

    @Override // com.common.utils.log.LogUtilPrinterInterface
    /* renamed from: v */
    public void mo22041v(Object obj, String str) {
        log(2, str, obj);
    }

    @Override // com.common.utils.log.LogUtilPrinterInterface
    public void wtf(Object obj, String str) {
        log(7, str, obj);
    }

    @Override // com.common.utils.log.LogUtilPrinterInterface
    public void json(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            mo22043e("Empty/Null json content", "");
            return;
        }
        try {
            int indexOf = str.indexOf("{");
            if (indexOf == -1) {
                indexOf = str.indexOf("[");
            }
            String str3 = "";
            if (indexOf > 0) {
                str3 = str.substring(0, indexOf);
                str = str.substring(indexOf, str.length());
            }
            if (!TextUtils.isEmpty(str3)) {
                str3 = str3 + System.getProperty("line.separator");
                str = str.replace("[]:", "");
            }
            if (str.startsWith("{")) {
                mo22044d(str3 + new JSONObject(str).toString(4), str2);
            } else if (str.startsWith("[")) {
                mo22044d(str3 + new JSONArray(str).toString(4), str2);
            }
        } catch (JSONException e) {
            mo22043e(str, "invalid json content");
            e.printStackTrace();
        }
    }

    @Override // com.common.utils.log.LogUtilPrinterInterface
    public synchronized void xml(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            mo22044d("Empty/Null xml content", str2);
            return;
        }
        try {
            StreamSource streamSource = new StreamSource(new StringReader(str));
            StreamResult streamResult = new StreamResult(new StringWriter());
            Transformer newTransformer = TransformerFactory.newInstance().newTransformer();
            newTransformer.setOutputProperty("indent", "yes");
            newTransformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            newTransformer.transform(streamSource, streamResult);
            mo22044d(streamResult.getWriter().toString().replaceFirst(SimpleComparison.f23610d, ">\n"), "");
        } catch (TransformerException e) {
            mo22043e(e.getCause().getMessage() + "\n" + str, str2);
        }
    }

    @Override // com.common.utils.log.LogUtilPrinterInterface
    public synchronized void printD(Object obj, String str) {
        Log.d(str, String.valueOf(obj));
    }

    @Override // com.common.utils.log.LogUtilPrinterInterface
    public synchronized void printE(Object obj, String str) {
        Log.e(str, String.valueOf(obj));
    }

    private synchronized void logContentPart(int i, String str, String str2) {
        logContent(i, str, str2);
        logBottomBorder(i, str);
    }

    private synchronized void logBottomBorder(int i, String str) {
        logChunk(i, str, BOTTOM_BORDER);
    }

    private synchronized void logContent(int i, String str, String str2) {
        String[] split;
        for (String str3 : str2.split(System.getProperty("line.separator"))) {
            logChunk(i, str, "║ " + str3);
        }
    }

    public synchronized void log(int i, String str, Object obj) {
        if (MYLOG_WRITE_TO_FILE.booleanValue()) {
            writeLogtoFile(String.valueOf(i), str, String.valueOf(obj));
        }
        logThreadPart(i, str);
        logMiddenInfoPart(i, str);
        logContentPart(i, str, String.valueOf(obj));
    }

    private synchronized void logMiddenInfoPart(int i, String str) {
        logChunk(i, str, generateFileTag());
        logDivider(i, str);
    }

    private synchronized String generateFileTag() {
        StackTraceElement callerStackTraceElement;
        String substring;
        callerStackTraceElement = getCallerStackTraceElement();
        String className = callerStackTraceElement.getClassName();
        substring = className.substring(className.lastIndexOf(Consts.f23430h) + 1);
        return "║ " + substring + Consts.f23430h + callerStackTraceElement.getMethodName() + ExpandableTextView.f6958c + " (" + callerStackTraceElement.getFileName() + ":" + callerStackTraceElement.getLineNumber() + ")";
    }

    private synchronized void logThreadPart(int i, String str) {
        logTopBorder(i, str);
        logChunk(i, str, "║ Thread: " + Thread.currentThread().getName());
        logDivider(i, str);
    }

    private synchronized void logTopBorder(int i, String str) {
        logChunk(i, str, TOP_BORDER);
    }

    private synchronized void logDivider(int i, String str) {
        logChunk(i, str, MIDDLE_BORDER);
    }

    private synchronized void logChunk(int i, String str, String str2) {
        String formatTag = formatTag(str);
        if (i != 2) {
            switch (i) {
                case 4:
                    Log.d(formatTag, str2);
                    break;
                case 5:
                    Log.w(formatTag, str2);
                    break;
                case 6:
                    Log.e(formatTag, str2);
                    break;
                case 7:
                    Log.wtf(formatTag, str2);
                    break;
                default:
                    Log.d(formatTag, str2);
                    break;
            }
        } else {
            Log.v(formatTag, str2);
        }
    }

    private static String formatTag(String str) {
        return (TextUtils.isEmpty(str) || TextUtils.equals(TAG, str)) ? TAG : str;
    }

    private StackTraceElement getCallerStackTraceElement() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        return stackTrace[getStackOffset(stackTrace)];
    }

    private int getStackOffset(StackTraceElement[] stackTraceElementArr) {
        for (int i = 0; i < stackTraceElementArr.length; i++) {
            if (stackTraceElementArr[i].getClassName().equals(LogUtils.class.getName())) {
                return i + 1;
            }
        }
        return -1;
    }

    private static void writeLogtoFile(String str, String str2, String str3) {
        Date date;
        String format = logfile.format(new Date());
        String str4 = myLogSdf.format(date) + "    " + str + "    " + str2 + "    " + str3;
        File file = new File(MYLOG_PATH_SDCARD_DIR);
        if (!file.exists()) {
            file.mkdirs();
        }
        File file2 = new File(file.toString(), format + MYLOGFILEName);
        if (!file2.exists()) {
            try {
                file2.createNewFile();
            } catch (Exception unused) {
            }
        }
        try {
            FileWriter fileWriter = new FileWriter(file2, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(str4);
            bufferedWriter.newLine();
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
