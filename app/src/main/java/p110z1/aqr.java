package p110z1;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.arch.persistence.room.RoomMasterTable;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.os.Process;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.Html;
import android.text.Selection;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import com.ctetin.expandabletextviewlibrary.ExpandableTextView;
import com.cyjh.ddy.media.p035a.ResultTypeConstant;
import com.cyjh.ddysdk.device.base.constants.DdyConstants;
import com.cyjh.p045mq.p049d.C1363e;
import com.cyjh.p045mq.sdk.inf.IRunner;
import com.lody.virtual.client.ipc.ServiceManagerNative;
import com.tencent.smtt.sdk.TbsConfig;
import com.tencent.smtt.sdk.TbsListener;
import com.tencent.smtt.sdk.WebView;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Pattern;
import org.apache.tools.ant.util.DateUtils;

/* compiled from: GeneralUtil.java */
@SuppressLint({"SimpleDateFormat"})
/* renamed from: z1.aqr */
/* loaded from: classes3.dex */
public class aqr {

    /* renamed from: a */
    public static final int f17382a = -1;

    /* renamed from: b */
    public static final int f17383b = 0;

    /* renamed from: c */
    public static final int f17384c = 1;

    /* renamed from: d */
    private static final char[] f17385d = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /* renamed from: a */
    public static boolean m11515a(String str) {
        return Pattern.compile("[🀀-🏿]|[🐀-\u1f7ff]|[☀-⟿]", 66).matcher(str).find();
    }

    /* renamed from: a */
    public static int m11522a(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            String str = packageInfo.versionName;
            return packageInfo.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /* renamed from: b */
    public static String m11502b(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (Exception e) {
            e.printStackTrace();
            return "1.0";
        }
    }

    /* renamed from: b */
    public static boolean m11496b(String str) {
        return "男".equals(str) || "女".equals(str);
    }

    /* renamed from: c */
    public static boolean m11487c(String str) {
        return str.matches("^0[0-9]{3}-?[0-9]{7,8}$");
    }

    /* renamed from: d */
    public static boolean m11481d(String str) {
        return str.matches("^(\\+86-|\\+86|86-|86){0,1}1[3|4|5|7|8]{1}\\d{9}$");
    }

    /* renamed from: a */
    public static boolean m11517a(EditText editText) {
        return editText.getText().toString().trim().matches("^1[34568]\\d{9}$");
    }

    /* renamed from: e */
    public static boolean m11477e(String str) {
        return str.matches("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
    }

    /* renamed from: b */
    public static boolean m11498b(EditText editText) {
        return editText.getText().toString().trim().matches("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
    }

    /* renamed from: a */
    public static int m11521a(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    /* renamed from: c */
    public static void m11491c(Context context) {
        Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
        launchIntentForPackage.addFlags(67108864);
        context.startActivity(launchIntentForPackage);
        Process.killProcess(Process.myPid());
        System.exit(0);
    }

    /* renamed from: b */
    public static int m11501b(Context context, float f) {
        return (int) ((f / context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    /* renamed from: a */
    public static void m11513a(String str, String str2) throws Exception {
        File file = new File(str2);
        if (file.exists()) {
            file.delete();
        }
        file.createNewFile();
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(str);
        bufferedWriter.flush();
        bufferedWriter.close();
        fileWriter.close();
    }

    @SuppressLint({"NewApi"})
    /* renamed from: b */
    public static void m11495b(String str, String str2) throws Exception {
        byte[] decode = Base64.decode(str, 0);
        FileOutputStream fileOutputStream = new FileOutputStream(str2);
        fileOutputStream.write(decode);
        fileOutputStream.close();
    }

    /* renamed from: f */
    public static String m11474f(String str) throws Exception {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(IRunner.START_ENIGINE_TIME_OUT_MILLIS);
            if (httpURLConnection.getResponseCode() != 200) {
                return null;
            }
            InputStream inputStream = httpURLConnection.getInputStream();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[1024];
            while (true) {
                int read = inputStream.read(bArr);
                if (read != -1) {
                    byteArrayOutputStream.write(bArr, 0, read);
                } else {
                    byteArrayOutputStream.close();
                    inputStream.close();
                    return new String(byteArrayOutputStream.toByteArray(), "UTF-8");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public static void m11516a(File file) {
        if (file.exists()) {
            if (file.isDirectory()) {
                for (File file2 : file.listFiles()) {
                    m11516a(file2);
                }
                return;
            }
            file.delete();
        }
    }

    /* renamed from: g */
    public static void m11472g(String str) {
        m11516a(new File(str));
    }

    /* renamed from: a */
    public static Animation m11526a(int i) {
        TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, 10.0f, 0.0f, 0.0f);
        translateAnimation.setInterpolator(new CycleInterpolator(i));
        translateAnimation.setDuration(500L);
        return translateAnimation;
    }

    /* renamed from: h */
    public static String m11470h(String str) {
        return str.substring(str.length() - 4, str.length());
    }

    /* renamed from: a */
    public static String m11514a(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        if (str.length() <= i) {
            return str;
        }
        return str.substring(0, i) + "...";
    }

    /* renamed from: d */
    public static void m11483d(Context context) {
        Intent intent = new Intent("com.lbd.xj.system_exit");
        intent.putExtra("data", "lalala");
        context.sendBroadcast(intent);
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [z1.aqr$1] */
    /* JADX WARN: Type inference failed for: r4v5, types: [z1.aqr$2] */
    /* renamed from: a */
    public static boolean m11510a(String str, String str2, StringBuilder sb) {
        Process process = null;
        try {
            try {
                process = Runtime.getRuntime().exec(C1363e.f8871b);
                final InputStream inputStream = process.getInputStream();
                final InputStream errorStream = process.getErrorStream();
                new Thread() { // from class: z1.aqr.1
                    @Override // java.lang.Thread, java.lang.Runnable
                    public void run() {
                        DataInputStream dataInputStream = new DataInputStream(inputStream);
                        while (dataInputStream.readLine() == null) {
                            try {
                                inputStream.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                                try {
                                    inputStream.close();
                                    return;
                                } catch (IOException e2) {
                                    e2.printStackTrace();
                                    return;
                                }
                            }
                        }
                    }
                }.start();
                new Thread() { // from class: z1.aqr.2
                    @Override // java.lang.Thread, java.lang.Runnable
                    public void run() {
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(errorStream));
                        while (bufferedReader.readLine() == null) {
                            try {
                                errorStream.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                                try {
                                    errorStream.close();
                                    return;
                                } catch (IOException e2) {
                                    e2.printStackTrace();
                                    return;
                                }
                            }
                        }
                    }
                }.start();
                process.waitFor();
                process.destroy();
                return true;
            } catch (Exception unused) {
                process.getErrorStream().close();
                process.getInputStream().close();
                process.getOutputStream().close();
                return true;
            }
        } catch (Exception unused2) {
            return true;
        }
    }

    /* renamed from: a */
    public static View m11520a(Context context, int i, ViewGroup viewGroup) {
        return View.inflate(context, i, viewGroup);
    }

    /* renamed from: a */
    public static char m11527a() {
        String str = "";
        Random random = new Random();
        try {
            str = new String(new byte[]{Integer.valueOf(Math.abs(random.nextInt(39)) + TbsListener.ErrorCode.NEEDDOWNLOAD_FALSE_6).byteValue(), Integer.valueOf(Math.abs(random.nextInt(93)) + TbsListener.ErrorCode.STARTDOWNLOAD_2).byteValue()}, "GBK");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str.charAt(0);
    }

    /* renamed from: a */
    public static void m11504a(View... viewArr) {
        for (View view : viewArr) {
            view.setEnabled(false);
        }
    }

    /* renamed from: b */
    public static void m11493b(View... viewArr) {
        for (View view : viewArr) {
            view.setVisibility(4);
        }
    }

    /* renamed from: c */
    public static void m11485c(View... viewArr) {
        for (View view : viewArr) {
            view.setVisibility(0);
        }
    }

    /* renamed from: d */
    public static void m11480d(View... viewArr) {
        for (View view : viewArr) {
            view.setVisibility(8);
        }
    }

    /* renamed from: a */
    public static void m11525a(int i, View... viewArr) {
        for (View view : viewArr) {
            view.setVisibility(i);
        }
    }

    /* renamed from: e */
    public static void m11476e(View... viewArr) {
        for (View view : viewArr) {
            view.setEnabled(true);
        }
    }

    /* renamed from: a */
    public static void m11524a(Activity activity) {
        try {
            ((InputMethodManager) activity.getSystemService("input_method")).hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public static void m11523a(Activity activity, View view) {
        try {
            ((InputMethodManager) activity.getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public static void m11518a(View view) {
        view.setFocusable(true);
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        ((InputMethodManager) view.getContext().getSystemService("input_method")).showSoftInput(view, 2);
    }

    /* renamed from: b */
    public static void m11499b(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) view.getContext().getSystemService("input_method");
        if (inputMethodManager.isActive()) {
            inputMethodManager.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
        }
    }

    /* renamed from: e */
    public static boolean m11479e(Context context) {
        ComponentName componentName;
        List<ActivityManager.RunningTaskInfo> runningTasks = ((ActivityManager) context.getSystemService(ServiceManagerNative.ACTIVITY)).getRunningTasks(1);
        return runningTasks != null && !runningTasks.isEmpty() && (componentName = runningTasks.get(0).topActivity) != null && !componentName.getPackageName().equals(context.getPackageName());
    }

    /* renamed from: f */
    public static String m11475f(Context context) {
        List<ActivityManager.RunningTaskInfo> runningTasks = ((ActivityManager) context.getSystemService(ServiceManagerNative.ACTIVITY)).getRunningTasks(1);
        if (runningTasks != null) {
            return runningTasks.get(0).topActivity.getClassName();
        }
        return null;
    }

    /* renamed from: a */
    public static <T> ArrayList<T> m11507a(ArrayList<T> arrayList, ArrayList<T> arrayList2) {
        arrayList.addAll(arrayList2);
        return arrayList;
    }

    /* renamed from: a */
    public static boolean m11508a(ArrayList<?> arrayList) {
        return arrayList != null && arrayList.size() > 0;
    }

    /* renamed from: i */
    public static int m11468i(String str) {
        if (str.length() <= 6) {
            return 1;
        }
        return (str.length() < 7 || str.length() > 10) ? 3 : 2;
    }

    /* renamed from: a */
    public static boolean m11519a(Context context, String str) {
        for (ActivityManager.RunningServiceInfo runningServiceInfo : ((ActivityManager) context.getSystemService(ServiceManagerNative.ACTIVITY)).getRunningServices(100)) {
            if (str.equals(runningServiceInfo.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: g */
    public static int m11473g(Context context) {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            packageInfo = null;
        }
        if (packageInfo != null) {
            return packageInfo.versionCode;
        }
        return 0;
    }

    /* renamed from: a */
    public static ArrayList<String> m11506a(HashMap<String, String> hashMap) {
        ArrayList<String> arrayList = new ArrayList<>();
        for (Map.Entry<String, String> entry : hashMap.entrySet()) {
            arrayList.add(entry.getValue());
        }
        return arrayList;
    }

    /* renamed from: b */
    public static boolean m11497b(File file) {
        if (!file.exists()) {
            return file.mkdir();
        }
        return false;
    }

    /* renamed from: j */
    public static boolean m11466j(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return Pattern.compile("[0-9]*").matcher(str).matches();
    }

    /* renamed from: c */
    public static void m11489c(View view) {
        int i;
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new ViewGroup.LayoutParams(-1, -2);
        }
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(0, 0, layoutParams.width);
        int i2 = layoutParams.height;
        if (i2 > 0) {
            i = View.MeasureSpec.makeMeasureSpec(i2, MemoryConstants.f21646d);
        } else {
            i = View.MeasureSpec.makeMeasureSpec(0, 0);
        }
        view.measure(childMeasureSpec, i);
    }

    /* renamed from: k */
    public static String m11464k(String str) {
        String[] strArr = {"1", ResultTypeConstant.f7213z, "x", "9", "8", DdyConstants.APP_INSTALL_INSTALLED, DdyConstants.APP_INSTALL_UNINSTALL, DdyConstants.APP_INSTALL_INSTALLING, DdyConstants.APP_INSTALL_DOWNLOADING, DdyConstants.APP_INSTALL_ERROR, "2"};
        String[] strArr2 = {DdyConstants.APP_INSTALL_INSTALLED, "9", "10", DdyConstants.APP_INSTALL_INSTALLING, "8", DdyConstants.APP_INSTALL_DOWNLOADING, "2", "1", DdyConstants.APP_INSTALL_UNINSTALL, DdyConstants.APP_INSTALL_ERROR, DdyConstants.APP_INSTALL_INSTALLED, "9", "10", DdyConstants.APP_INSTALL_INSTALLING, "8", DdyConstants.APP_INSTALL_DOWNLOADING, "2"};
        String str2 = "";
        if (!(str.length() == 15 || str.length() == 18)) {
            return "身份证号码长度应该为15位或18位。";
        }
        if (str.length() == 18) {
            str2 = str.substring(0, 17);
        } else if (str.length() == 15) {
            str2 = str.substring(0, 6) + "19" + str.substring(6, 15);
        }
        if (!m11466j(str2)) {
            return "身份证15位号码都应为数字 ; 18位号码除最后一位外，都应为数字。";
        }
        String substring = str2.substring(6, 10);
        String substring2 = str2.substring(10, 12);
        String substring3 = str2.substring(12, 14);
        if (!m11460m(substring + "-" + substring2 + "-" + substring3)) {
            return "身份证生日无效。";
        }
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateUtils.ISO8601_DATE_PATTERN);
        try {
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (ParseException e2) {
            e2.printStackTrace();
        }
        if (gregorianCalendar.get(1) - Integer.parseInt(substring) > 150) {
            return "身份证生日不在有效范围。";
        }
        if (gregorianCalendar.getTime().getTime() - simpleDateFormat.parse(substring + "-" + substring2 + "-" + substring3).getTime() < 0) {
            return "身份证生日不在有效范围。";
        }
        if (Integer.parseInt(substring2) > 12 || Integer.parseInt(substring2) == 0) {
            return "身份证月份无效";
        }
        if (Integer.parseInt(substring3) > 31 || Integer.parseInt(substring3) == 0) {
            return "身份证日期无效";
        }
        if (m11484d().get(str2.substring(0, 2)) == null) {
            return "身份证地区编码错误。";
        }
        int i = 0;
        for (int i2 = 0; i2 < 17; i2++) {
            i += Integer.parseInt(String.valueOf(str2.charAt(i2))) * Integer.parseInt(strArr2[i2]);
        }
        String str3 = strArr[i % 11];
        StringBuilder sb = new StringBuilder();
        sb.append(str2);
        sb.append(str3);
        return (str.length() != 18 || sb.toString().equals(str)) ? "" : "身份证无效，不是合法的身份证号码";
    }

    /* renamed from: d */
    private static Hashtable<String, String> m11484d() {
        Hashtable<String, String> hashtable = new Hashtable<>();
        hashtable.put("11", "北京");
        hashtable.put("12", "天津");
        hashtable.put("13", "河北");
        hashtable.put("14", "山西");
        hashtable.put("15", "内蒙古");
        hashtable.put("21", "辽宁");
        hashtable.put("22", "吉林");
        hashtable.put("23", "黑龙江");
        hashtable.put("31", "上海");
        hashtable.put(acf.f15191p, "江苏");
        hashtable.put("33", "浙江");
        hashtable.put("34", "安徽");
        hashtable.put("35", "福建");
        hashtable.put("36", "江西");
        hashtable.put("37", "山东");
        hashtable.put("41", "河南");
        hashtable.put(RoomMasterTable.DEFAULT_ID, "湖北");
        hashtable.put("43", "湖南");
        hashtable.put("44", "广东");
        hashtable.put("45", "广西");
        hashtable.put("46", "海南");
        hashtable.put("50", "重庆");
        hashtable.put("51", "四川");
        hashtable.put("52", "贵州");
        hashtable.put("53", "云南");
        hashtable.put("54", "西藏");
        hashtable.put("61", "陕西");
        hashtable.put("62", "甘肃");
        hashtable.put("63", "青海");
        hashtable.put(acf.f15192q, "宁夏");
        hashtable.put("65", "新疆");
        hashtable.put("71", "台湾");
        hashtable.put("81", "香港");
        hashtable.put("82", "澳门");
        hashtable.put("91", "国外");
        return hashtable;
    }

    /* renamed from: l */
    public static final String m11462l(String str) {
        String str2 = "";
        str.trim();
        if (str != null && !"".equals(str)) {
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) >= '0' && str.charAt(i) <= '9') {
                    str2 = str2 + str.charAt(i);
                }
            }
        }
        return str2;
    }

    /* renamed from: m */
    public static boolean m11460m(String str) {
        return Pattern.compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$").matcher(str).matches();
    }

    /* renamed from: n */
    public static String m11459n(String str) {
        int i;
        if (str == null || str.length() <= 0) {
            return null;
        }
        int length = str.length();
        StringBuilder sb = new StringBuilder();
        sb.append(str.substring(0, length / 2).hashCode());
        sb.append("");
        return sb.toString() + (str.substring(i).hashCode() + "");
    }

    /* renamed from: a */
    public static String m11505a(byte[] bArr) {
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        return Base64.encodeToString(bArr, 2);
    }

    /* renamed from: o */
    public static byte[] m11458o(String str) {
        if (str == null || str.length() <= 0) {
            return null;
        }
        return Base64.decode(str, 2);
    }

    /* renamed from: b */
    public static String m11494b(byte[] bArr) {
        try {
            return m11505a(MessageDigest.getInstance("md5").digest(bArr));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: b */
    public static void m11500b(Context context, String str) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.setData(Uri.parse(WebView.SCHEME_TEL + str));
        context.startActivity(intent);
    }

    /* renamed from: c */
    public static void m11490c(Context context, String str) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.setData(Uri.parse(WebView.SCHEME_TEL + str));
        context.startActivity(intent);
    }

    /* renamed from: c */
    public static void m11488c(EditText editText) {
        Editable text = editText.getText();
        Selection.setSelection(text, text.length());
    }

    /* renamed from: p */
    public static boolean m11457p(String str) {
        if (TextUtils.isEmpty(str)) {
            return true;
        }
        return str.matches("^((https|http|ftp|rtsp|mms)?://)+(([0-9a-z_!~*'().&=+$%-]+: )?[0-9a-z_!~*'().&=+$%-]+@)?(([0-9]{1,3}\\.){3}[0-9]{1,3}|([0-9a-z_!~*'()-]+\\.)*([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]\\.[a-z]{2,6})(:[0-9]{1,4})?((/?)|(/[0-9a-z_!~*'().;?:@&=+$,%#-]+)+/?)$");
    }

    /* renamed from: a */
    public static void m11512a(String str, String str2, Context context) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(str);
            InputStream open = context.getAssets().open(str2);
            byte[] bArr = new byte[1024];
            while (true) {
                int read = open.read(bArr);
                if (read <= 0) {
                    fileOutputStream.flush();
                    open.close();
                    fileOutputStream.close();
                    return;
                }
                fileOutputStream.write(bArr, 0, read);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public static void m11511a(String str, String str2, AssetManager assetManager, Context context) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(str);
            InputStream open = context.getAssets().open(str2);
            byte[] bArr = new byte[1024];
            while (true) {
                int read = open.read(bArr);
                if (read <= 0) {
                    fileOutputStream.flush();
                    open.close();
                    fileOutputStream.close();
                    return;
                }
                fileOutputStream.write(bArr, 0, read);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: q */
    public static boolean m11456q(String str) {
        return str.matches("^[-|+]?\\d*([.]\\d{0,2})?$");
    }

    /* renamed from: d */
    public static void m11482d(final EditText editText) {
        editText.addTextChangedListener(new TextWatcher() { // from class: z1.aqr.3
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if (!(charSequence == null || charSequence.length() == 0)) {
                    StringBuilder sb = new StringBuilder();
                    for (int i4 = 0; i4 < charSequence.length(); i4++) {
                        if (i4 == 3 || i4 == 8 || charSequence.charAt(i4) != ' ') {
                            sb.append(charSequence.charAt(i4));
                            if ((sb.length() == 4 || sb.length() == 9) && sb.charAt(sb.length() - 1) != ' ') {
                                sb.insert(sb.length() - 1, ' ');
                            }
                        }
                    }
                    if (!sb.toString().equals(charSequence.toString())) {
                        int i5 = i + 1;
                        if (sb.charAt(i) == ' ') {
                            i5 = i2 == 0 ? i5 + 1 : i5 - 1;
                        } else if (i2 == 1) {
                            i5--;
                        }
                        editText.setText(sb.toString());
                        editText.setSelection(i5);
                    }
                }
            }
        });
    }

    /* renamed from: e */
    public static void m11478e(final EditText editText) {
        editText.addTextChangedListener(new TextWatcher() { // from class: z1.aqr.4

            /* renamed from: g */
            private char[] f17395g;

            /* renamed from: a */
            int f17389a = 0;

            /* renamed from: b */
            int f17390b = 0;

            /* renamed from: c */
            boolean f17391c = false;

            /* renamed from: d */
            int f17392d = 0;

            /* renamed from: h */
            private StringBuffer f17396h = new StringBuffer();

            /* renamed from: e */
            int f17393e = 0;

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                this.f17389a = charSequence.length();
                if (this.f17396h.length() > 0) {
                    StringBuffer stringBuffer = this.f17396h;
                    stringBuffer.delete(0, stringBuffer.length());
                }
                this.f17393e = 0;
                for (int i4 = 0; i4 < charSequence.length(); i4++) {
                    if (charSequence.charAt(i4) == ' ') {
                        this.f17393e++;
                    }
                }
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                this.f17390b = charSequence.length();
                this.f17396h.append(charSequence.toString());
                int i4 = this.f17390b;
                if (i4 == this.f17389a || i4 <= 3 || this.f17391c) {
                    this.f17391c = false;
                } else {
                    this.f17391c = true;
                }
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                if (this.f17391c) {
                    this.f17392d = editText.getSelectionEnd();
                    int i = 0;
                    while (i < this.f17396h.length()) {
                        if (this.f17396h.charAt(i) == ' ') {
                            this.f17396h.deleteCharAt(i);
                        } else {
                            i++;
                        }
                    }
                    int i2 = 0;
                    for (int i3 = 0; i3 < this.f17396h.length(); i3++) {
                        if (i3 == 4 || i3 == 9 || i3 == 14 || i3 == 19) {
                            this.f17396h.insert(i3, ' ');
                            i2++;
                        }
                    }
                    int i4 = this.f17393e;
                    if (i2 > i4) {
                        this.f17392d += i2 - i4;
                    }
                    this.f17395g = new char[this.f17396h.length()];
                    StringBuffer stringBuffer = this.f17396h;
                    stringBuffer.getChars(0, stringBuffer.length(), this.f17395g, 0);
                    String stringBuffer2 = this.f17396h.toString();
                    if (this.f17392d > stringBuffer2.length()) {
                        this.f17392d = stringBuffer2.length();
                    } else if (this.f17392d < 0) {
                        this.f17392d = 0;
                    }
                    editText.setText(stringBuffer2);
                    Selection.setSelection(editText.getText(), this.f17392d);
                    this.f17391c = false;
                }
            }
        });
    }

    /* renamed from: r */
    public static String m11455r(String str) {
        return str.replace(ExpandableTextView.f6958c, "");
    }

    /* renamed from: a */
    public static <T> T m11509a(String str, Type type) {
        return (T) new Gson().m1588a(str, type);
    }

    /* renamed from: s */
    public static String m11454s(String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(str.trim().getBytes());
            return m11486c(instance.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return str;
        }
    }

    /* renamed from: c */
    public static String m11486c(byte[] bArr) {
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (byte b : bArr) {
            sb.append(f17385d[(b & 240) >>> 4]);
            sb.append(f17385d[b & 15]);
        }
        return sb.toString();
    }

    /* renamed from: h */
    public static boolean m11471h(Context context) {
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) context.getSystemService(ServiceManagerNative.ACTIVITY)).getRunningAppProcesses()) {
            if (runningAppProcessInfo.processName.equals(context.getPackageName())) {
                return runningAppProcessInfo.importance != 100;
            }
        }
        return false;
    }

    /* renamed from: i */
    public static boolean m11469i(Context context) {
        List<PackageInfo> installedPackages = context.getPackageManager().getInstalledPackages(0);
        if (installedPackages != null) {
            for (int i = 0; i < installedPackages.size(); i++) {
                String str = installedPackages.get(i).packageName;
                if (str.equalsIgnoreCase("com.tencent.qqlite") || str.equalsIgnoreCase(TbsConfig.APP_QQ)) {
                    return true;
                }
            }
        }
        return false;
    }

    /* renamed from: j */
    public static boolean m11467j(Context context) {
        List<PackageInfo> installedPackages = context.getPackageManager().getInstalledPackages(0);
        if (installedPackages != null) {
            for (int i = 0; i < installedPackages.size(); i++) {
                if (installedPackages.get(i).packageName.equalsIgnoreCase("com.sina.weibo")) {
                    return true;
                }
            }
        }
        return false;
    }

    /* renamed from: k */
    public static boolean m11465k(Context context) {
        List<PackageInfo> installedPackages = context.getPackageManager().getInstalledPackages(0);
        if (installedPackages != null) {
            for (int i = 0; i < installedPackages.size(); i++) {
                if (installedPackages.get(i).packageName.equals(TbsConfig.APP_WX)) {
                    return true;
                }
            }
        }
        return false;
    }

    /* renamed from: t */
    public static CharSequence m11453t(String str) {
        return Html.fromHtml(str);
    }

    /* renamed from: b */
    public static String m11503b() {
        return Build.BRAND;
    }

    /* renamed from: c */
    public static String m11492c() {
        return Build.MODEL;
    }

    /* renamed from: l */
    public static boolean m11463l(@NonNull Context context) {
        Resources resources = context.getResources();
        return (resources == null || resources.getConfiguration() == null || resources.getConfiguration().orientation != 2) ? false : true;
    }

    /* renamed from: m */
    public static void m11461m(Context context) {
        if (Build.VERSION.SDK_INT >= 19) {
            ((Activity) context).getWindow().getDecorView().setSystemUiVisibility(3334);
        }
    }
}
