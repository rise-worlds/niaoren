package p110z1;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.arch.persistence.room.RoomMasterTable;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Process;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.telephony.TelephonyManager;
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
import com.common.utils.log.LogUtils;
import com.ctetin.expandabletextviewlibrary.ExpandableTextView;
import com.cyjh.ddy.media.p035a.ResultTypeConstant;
import com.cyjh.ddysdk.device.base.constants.DdyConstants;
import com.cyjh.mq.p049d.C1363e;
import com.cyjh.mq.sdk.inf.IRunner;
import com.lbd.p054xj.app.XJApp;
import com.lbd.p054xj.keeplive.PlayerMusicService;
import com.lbd.p054xj.service.XJFloatService;
import com.lody.virtual.client.ipc.ServiceManagerNative;
import com.stripe.android.view.ShippingInfoWidget;
import com.tencent.smtt.sdk.TbsConfig;
import com.tencent.smtt.sdk.TbsListener;
import com.tencent.smtt.sdk.WebView;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
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

@SuppressLint({"SimpleDateFormat"})
/* renamed from: z1.aeh */
/* loaded from: classes3.dex */
public class GeneralUtil {

    /* renamed from: a */
    public static final int f15436a = -1;

    /* renamed from: b */
    public static final int f15437b = 0;

    /* renamed from: c */
    public static final int f15438c = 1;

    /* renamed from: d */
    private static final char[] f15439d = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /* renamed from: a */
    public static boolean m14049a(String str) {
        return Pattern.compile("[🀀-🏿]|[🐀-\u1f7ff]|[☀-⟿]", 66).matcher(str).find();
    }

    /* renamed from: a */
    public static int m14058a(Context context) {
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
    public static String m14036b(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (Exception e) {
            e.printStackTrace();
            return "1.0";
        }
    }

    /* renamed from: a */
    public static String m14047a(String str, String str2) {
        String str3 = "";
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            str3 = (String) cls.getMethod("get", String.class, String.class).invoke(cls, str, "");
        } catch (Exception e) {
            LogUtils.m22036e("getSystemProperty", "key = " + str + ", error = " + e.getMessage());
        }
        LogUtils.m22036e("getSystemProperty", str + " = " + str3);
        return str3;
    }

    /* renamed from: a */
    public static String m14063a() {
        if (m14047a(acf.f15190o, "").length() > 0) {
            LogUtils.m22038d("getArchType", "CPU arch is 64bit");
            return acf.f15192q;
        }
        LogUtils.m22038d("getArchType", "cpu DEFAULT 32bit!");
        return acf.f15191p;
    }

    /* renamed from: b */
    public static boolean m14030b(String str) {
        return "男".equals(str) || "女".equals(str);
    }

    /* renamed from: c */
    public static boolean m14020c(String str) {
        return str.matches("^0[0-9]{3}-?[0-9]{7,8}$");
    }

    /* renamed from: d */
    public static boolean m14012d(String str) {
        return str.matches("^(\\+86-|\\+86|86-|86){0,1}1[3|4|5|7|8]{1}\\d{9}$");
    }

    /* renamed from: a */
    public static boolean m14051a(EditText editText) {
        return editText.getText().toString().trim().matches("^1[34568]\\d{9}$");
    }

    /* renamed from: e */
    public static boolean m14006e(String str) {
        return str.matches("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
    }

    /* renamed from: b */
    public static boolean m14032b(EditText editText) {
        return editText.getText().toString().trim().matches("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
    }

    /* renamed from: a */
    public static int m14057a(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    /* renamed from: c */
    public static void m14024c(Context context) {
        if (context != null) {
            context.stopService(new Intent(context, PlayerMusicService.class));
            context.stopService(new Intent(context, XJFloatService.class));
        }
        Process.killProcess(Process.myPid());
        System.exit(0);
    }

    /* renamed from: b */
    public static void m14037b() {
        m14024c(XJApp.getInstance().getApplicationContext());
    }

    /* renamed from: d */
    public static void m14014d(Context context) {
        m14055a(context, 500L);
    }

    /* renamed from: c */
    public static void m14025c() {
        m14055a(XJApp.getInstance().getApplicationContext(), 500L);
    }

    /* renamed from: a */
    public static void m14055a(Context context, long j) {
        if (context != null) {
            context.stopService(new Intent(context, PlayerMusicService.class));
            context.stopService(new Intent(context, XJFloatService.class));
        }
        m14008e(context);
    }

    /* renamed from: e */
    public static void m14008e(Context context) {
        Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
        launchIntentForPackage.addFlags(67108864);
        context.startActivity(launchIntentForPackage);
        Process.killProcess(Process.myPid());
        System.exit(0);
    }

    /* renamed from: b */
    public static int m14035b(Context context, float f) {
        return (int) ((f / context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    /* renamed from: b */
    public static void m14029b(String str, String str2) throws Exception {
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
    /* renamed from: c */
    public static void m14019c(String str, String str2) throws Exception {
        byte[] decode = Base64.decode(str, 0);
        FileOutputStream fileOutputStream = new FileOutputStream(str2);
        fileOutputStream.write(decode);
        fileOutputStream.close();
    }

    /* renamed from: f */
    public static String m14001f(String str) throws Exception {
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
    public static void m14050a(File file) {
        if (file.exists()) {
            if (file.isDirectory()) {
                for (File file2 : file.listFiles()) {
                    m14050a(file2);
                }
                return;
            }
            file.delete();
        }
    }

    /* renamed from: g */
    public static void m13998g(String str) {
        m14050a(new File(str));
    }

    /* renamed from: a */
    public static Animation m14062a(int i) {
        TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, 10.0f, 0.0f, 0.0f);
        translateAnimation.setInterpolator(new CycleInterpolator(i));
        translateAnimation.setDuration(500L);
        return translateAnimation;
    }

    /* renamed from: h */
    public static String m13995h(String str) {
        return str.substring(str.length() - 4, str.length());
    }

    /* renamed from: a */
    public static String m14048a(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        if (str.length() <= i) {
            return str;
        }
        return str.substring(0, i) + "...";
    }

    /* renamed from: f */
    public static void m14002f(Context context) {
        Intent intent = new Intent("com.lbd.xj.system_exit");
        intent.putExtra("data", "lalala");
        context.sendBroadcast(intent);
    }

    /* renamed from: d */
    public static void m14015d() {
        m14002f(XJApp.getInstance().getApplicationContext());
    }

    /* renamed from: a */
    public static boolean m14044a(String str, String str2, StringBuilder sb) {
        try {
            LogUtils.m22036e("RunLocalUserCommand", str2 + ":xx\n");
            Process exec = Runtime.getRuntime().exec(C1363e.f8871b);
            DataInputStream dataInputStream = new DataInputStream(exec.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(exec.getOutputStream());
            dataOutputStream.writeBytes("cd " + str + "\n");
            StringBuilder sb2 = new StringBuilder();
            sb2.append(str2);
            sb2.append(" &\n");
            dataOutputStream.writeBytes(sb2.toString());
            dataOutputStream.writeBytes(C1363e.f8872c);
            dataOutputStream.flush();
            int waitFor = exec.waitFor();
            if (waitFor != 0) {
                LogUtils.m22036e("commend", "RunLocalUserCommand: " + str2 + " result=" + waitFor);
            }
            byte[] bArr = new byte[dataInputStream.available()];
            dataInputStream.read(bArr);
            String str3 = new String(bArr);
            if (sb == null) {
                return true;
            }
            sb.append("CMD Result:\n" + str3);
            return true;
        } catch (Exception e) {
            if (sb == null) {
                return false;
            }
            sb.append("Exception:" + e.getMessage());
            return false;
        }
    }

    /* renamed from: b */
    public static boolean m14028b(String str, String str2, StringBuilder sb) {
        try {
            LogUtils.m22036e("RunLocalUserCommand", str2 + ":xx\n");
            Process exec = Runtime.getRuntime().exec(C1363e.f8871b);
            DataInputStream dataInputStream = new DataInputStream(exec.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(exec.getOutputStream());
            dataOutputStream.writeBytes("cd " + str + "\n");
            StringBuilder sb2 = new StringBuilder();
            sb2.append(str2);
            sb2.append("\n");
            dataOutputStream.writeBytes(sb2.toString());
            dataOutputStream.writeBytes(C1363e.f8872c);
            dataOutputStream.flush();
            exec.waitFor();
            byte[] bArr = new byte[dataInputStream.available()];
            dataInputStream.read(bArr);
            String str3 = new String(bArr);
            if (sb == null) {
                return true;
            }
            sb.append("CMD Result:\n" + str3);
            return true;
        } catch (Exception e) {
            if (sb == null) {
                return false;
            }
            sb.append("Exception:" + e.getMessage());
            return false;
        }
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [z1.aeh$1] */
    /* JADX WARN: Type inference failed for: r4v5, types: [z1.aeh$2] */
    /* renamed from: c */
    public static boolean m14018c(String str, String str2, StringBuilder sb) {
        Process process = null;
        try {
            try {
                process = Runtime.getRuntime().exec(C1363e.f8871b);
                final InputStream inputStream = process.getInputStream();
                final InputStream errorStream = process.getErrorStream();
                new Thread() { // from class: z1.aeh.1
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
                new Thread() { // from class: z1.aeh.2
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
    public static View m14056a(Context context, int i, ViewGroup viewGroup) {
        return View.inflate(context, i, viewGroup);
    }

    /* renamed from: e */
    public static char m14009e() {
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
    public static void m14038a(View... viewArr) {
        for (View view : viewArr) {
            view.setEnabled(false);
        }
    }

    /* renamed from: b */
    public static void m14026b(View... viewArr) {
        for (View view : viewArr) {
            view.setVisibility(4);
        }
    }

    /* renamed from: c */
    public static void m14016c(View... viewArr) {
        for (View view : viewArr) {
            view.setVisibility(0);
        }
    }

    /* renamed from: d */
    public static void m14010d(View... viewArr) {
        for (View view : viewArr) {
            view.setVisibility(8);
        }
    }

    /* renamed from: a */
    public static void m14061a(int i, View... viewArr) {
        for (View view : viewArr) {
            view.setVisibility(i);
        }
    }

    /* renamed from: e */
    public static void m14004e(View... viewArr) {
        for (View view : viewArr) {
            view.setEnabled(true);
        }
    }

    /* renamed from: a */
    public static void m14060a(Activity activity) {
        try {
            ((InputMethodManager) activity.getSystemService("input_method")).hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public static void m14059a(Activity activity, View view) {
        try {
            ((InputMethodManager) activity.getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public static void m14052a(View view) {
        view.setFocusable(true);
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        ((InputMethodManager) view.getContext().getSystemService("input_method")).showSoftInput(view, 2);
    }

    /* renamed from: b */
    public static void m14033b(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) view.getContext().getSystemService("input_method");
        if (inputMethodManager.isActive()) {
            inputMethodManager.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
        }
    }

    /* renamed from: g */
    public static boolean m13999g(Context context) {
        ComponentName componentName;
        List<ActivityManager.RunningTaskInfo> runningTasks = ((ActivityManager) context.getSystemService(ServiceManagerNative.ACTIVITY)).getRunningTasks(1);
        return runningTasks != null && !runningTasks.isEmpty() && (componentName = runningTasks.get(0).topActivity) != null && !componentName.getPackageName().equals(context.getPackageName());
    }

    /* renamed from: h */
    public static String m13996h(Context context) {
        List<ActivityManager.RunningTaskInfo> runningTasks = ((ActivityManager) context.getSystemService(ServiceManagerNative.ACTIVITY)).getRunningTasks(1);
        if (runningTasks != null) {
            return runningTasks.get(0).topActivity.getClassName();
        }
        return null;
    }

    /* renamed from: a */
    public static <T> ArrayList<T> m14041a(ArrayList<T> arrayList, ArrayList<T> arrayList2) {
        arrayList.addAll(arrayList2);
        return arrayList;
    }

    /* renamed from: a */
    public static boolean m14042a(ArrayList<?> arrayList) {
        return arrayList != null && arrayList.size() > 0;
    }

    /* renamed from: i */
    public static int m13992i(String str) {
        if (str.length() <= 6) {
            return 1;
        }
        return (str.length() < 7 || str.length() > 10) ? 3 : 2;
    }

    /* renamed from: a */
    public static boolean m14053a(Context context, String str) {
        for (ActivityManager.RunningServiceInfo runningServiceInfo : ((ActivityManager) context.getSystemService(ServiceManagerNative.ACTIVITY)).getRunningServices(100)) {
            if (str.equals(runningServiceInfo.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: i */
    public static int m13993i(Context context) {
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
    public static ArrayList<String> m14040a(HashMap<String, String> hashMap) {
        ArrayList<String> arrayList = new ArrayList<>();
        for (Map.Entry<String, String> entry : hashMap.entrySet()) {
            arrayList.add(entry.getValue());
        }
        return arrayList;
    }

    /* renamed from: b */
    public static boolean m14031b(File file) {
        if (!file.exists()) {
            return file.mkdir();
        }
        return false;
    }

    /* renamed from: j */
    public static boolean m13990j(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return Pattern.compile("[0-9]*").matcher(str).matches();
    }

    /* renamed from: c */
    public static void m14022c(View view) {
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
    public static String m13988k(String str) {
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
        if (!m13990j(str2)) {
            return "身份证15位号码都应为数字 ; 18位号码除最后一位外，都应为数字。";
        }
        String substring = str2.substring(6, 10);
        String substring2 = str2.substring(10, 12);
        String substring3 = str2.substring(12, 14);
        if (!m13984m(substring + "-" + substring2 + "-" + substring3)) {
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
        if (m13994i().get(str2.substring(0, 2)) == null) {
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

    /* renamed from: i */
    private static Hashtable<String, String> m13994i() {
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
    public static final String m13986l(String str) {
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
    public static boolean m13984m(String str) {
        return Pattern.compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$").matcher(str).matches();
    }

    /* renamed from: n */
    public static String m13982n(String str) {
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

    /* renamed from: j */
    public static final String m13991j(Context context) {
        try {
            String deviceId = ((TelephonyManager) context.getSystemService(ShippingInfoWidget.f12563f)).getDeviceId();
            return TextUtils.isEmpty(deviceId) ? Settings.Secure.getString(context.getContentResolver(), "android_id") : deviceId;
        } catch (Exception unused) {
            return Settings.Secure.getString(context.getContentResolver(), "android_id");
        }
    }

    /* renamed from: a */
    public static ArrayList m14054a(Context context, ApplicationInfo applicationInfo) {
        PackageManager packageManager = context.getPackageManager();
        ArrayList arrayList = new ArrayList();
        List<PackageInfo> installedPackages = packageManager.getInstalledPackages(8192);
        File file = new File(applicationInfo.dataDir, "osimg");
        StringBuilder sb = new StringBuilder();
        sb.append(file);
        sb.append("/r/ot01/data/system/packages.list");
        boolean exists = new File(sb.toString()).exists();
        try {
            for (PackageInfo packageInfo : installedPackages) {
                if (packageInfo.applicationInfo.publicSourceDir != null && (packageInfo.applicationInfo.flags & 1) <= 0 && !packageInfo.applicationInfo.publicSourceDir.contains(applicationInfo.packageName)) {
                    HashMap hashMap = new HashMap();
                    hashMap.put("appimage", packageInfo.applicationInfo.loadIcon(packageManager));
                    hashMap.put("packageName", packageInfo.packageName);
                    hashMap.put("versionCode", Integer.valueOf(packageInfo.versionCode));
                    hashMap.put("versionName", packageInfo.versionName);
                    hashMap.put("appName", packageInfo.applicationInfo.loadLabel(packageManager).toString());
                    hashMap.put("publicSourceDir", packageInfo.applicationInfo.publicSourceDir);
                    if (!exists || adz.m14221b(sb.toString(), packageInfo.packageName) != 0) {
                        hashMap.put("strHave", "");
                        arrayList.add(hashMap);
                    } else {
                        hashMap.put("strHave", " (已拷贝)");
                        arrayList.add(hashMap);
                    }
                }
            }
            return arrayList;
        } catch (Exception e) {
            e.printStackTrace();
            return arrayList;
        }
    }

    /* renamed from: a */
    public static String m14039a(byte[] bArr) {
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        return Base64.encodeToString(bArr, 2);
    }

    /* renamed from: o */
    public static byte[] m13980o(String str) {
        if (str == null || str.length() <= 0) {
            return null;
        }
        return Base64.decode(str, 2);
    }

    /* renamed from: b */
    public static String m14027b(byte[] bArr) {
        try {
            return m14039a(MessageDigest.getInstance("md5").digest(bArr));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: b */
    public static void m14034b(Context context, String str) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.setData(Uri.parse(WebView.SCHEME_TEL + str));
        context.startActivity(intent);
    }

    /* renamed from: c */
    public static void m14023c(Context context, String str) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.setData(Uri.parse(WebView.SCHEME_TEL + str));
        context.startActivity(intent);
    }

    /* renamed from: c */
    public static void m14021c(EditText editText) {
        Editable text = editText.getText();
        Selection.setSelection(text, text.length());
    }

    /* renamed from: p */
    public static boolean m13978p(String str) {
        if (TextUtils.isEmpty(str)) {
            return true;
        }
        return str.matches("^((https|http|ftp|rtsp|mms)?://)+(([0-9a-z_!~*'().&=+$%-]+: )?[0-9a-z_!~*'().&=+$%-]+@)?(([0-9]{1,3}\\.){3}[0-9]{1,3}|([0-9a-z_!~*'()-]+\\.)*([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]\\.[a-z]{2,6})(:[0-9]{1,4})?((/?)|(/[0-9a-z_!~*'().;?:@&=+$,%#-]+)+/?)$");
    }

    /* renamed from: k */
    public static void m13989k(Context context) {
        context.stopService(new Intent(context, PlayerMusicService.class));
        context.stopService(new Intent(context, XJFloatService.class));
    }

    /* renamed from: f */
    public static void m14003f() {
        m13989k(XJApp.getInstance().getApplicationContext());
    }

    /* renamed from: a */
    public static void m14046a(String str, String str2, Context context) {
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

    /* renamed from: d */
    public static void m14011d(String str, String str2) {
        m14046a(str, str2, XJApp.getInstance().getApplicationContext());
    }

    /* renamed from: a */
    public static void m14045a(String str, String str2, AssetManager assetManager, Context context) {
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

    /* renamed from: e */
    public static void m14005e(String str, String str2) {
        m14045a(str, str2, XJApp.getInstance().getAssets(), XJApp.getInstance().getApplicationContext());
    }

    /* renamed from: q */
    public static boolean m13976q(String str) {
        return str.matches("^[-|+]?\\d*([.]\\d{0,2})?$");
    }

    /* renamed from: d */
    public static void m14013d(final EditText editText) {
        editText.addTextChangedListener(new TextWatcher() { // from class: z1.aeh.3
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
    public static void m14007e(final EditText editText) {
        editText.addTextChangedListener(new TextWatcher() { // from class: z1.aeh.4

            /* renamed from: g */
            private char[] f15449g;

            /* renamed from: a */
            int f15443a = 0;

            /* renamed from: b */
            int f15444b = 0;

            /* renamed from: c */
            boolean f15445c = false;

            /* renamed from: d */
            int f15446d = 0;

            /* renamed from: h */
            private StringBuffer f15450h = new StringBuffer();

            /* renamed from: e */
            int f15447e = 0;

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                this.f15443a = charSequence.length();
                if (this.f15450h.length() > 0) {
                    StringBuffer stringBuffer = this.f15450h;
                    stringBuffer.delete(0, stringBuffer.length());
                }
                this.f15447e = 0;
                for (int i4 = 0; i4 < charSequence.length(); i4++) {
                    if (charSequence.charAt(i4) == ' ') {
                        this.f15447e++;
                    }
                }
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                this.f15444b = charSequence.length();
                this.f15450h.append(charSequence.toString());
                int i4 = this.f15444b;
                if (i4 == this.f15443a || i4 <= 3 || this.f15445c) {
                    this.f15445c = false;
                } else {
                    this.f15445c = true;
                }
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
                if (this.f15445c) {
                    this.f15446d = editText.getSelectionEnd();
                    int i = 0;
                    while (i < this.f15450h.length()) {
                        if (this.f15450h.charAt(i) == ' ') {
                            this.f15450h.deleteCharAt(i);
                        } else {
                            i++;
                        }
                    }
                    int i2 = 0;
                    for (int i3 = 0; i3 < this.f15450h.length(); i3++) {
                        if (i3 == 4 || i3 == 9 || i3 == 14 || i3 == 19) {
                            this.f15450h.insert(i3, ' ');
                            i2++;
                        }
                    }
                    int i4 = this.f15447e;
                    if (i2 > i4) {
                        this.f15446d += i2 - i4;
                    }
                    this.f15449g = new char[this.f15450h.length()];
                    StringBuffer stringBuffer = this.f15450h;
                    stringBuffer.getChars(0, stringBuffer.length(), this.f15449g, 0);
                    String stringBuffer2 = this.f15450h.toString();
                    if (this.f15446d > stringBuffer2.length()) {
                        this.f15446d = stringBuffer2.length();
                    } else if (this.f15446d < 0) {
                        this.f15446d = 0;
                    }
                    editText.setText(stringBuffer2);
                    Selection.setSelection(editText.getText(), this.f15446d);
                    this.f15445c = false;
                }
            }
        });
    }

    /* renamed from: r */
    public static String m13974r(String str) {
        return str.replace(ExpandableTextView.f6958c, "");
    }

    /* renamed from: a */
    public static <T> T m14043a(String str, Type type) {
        return (T) new Gson().m1588a(str, type);
    }

    /* renamed from: s */
    public static String m13972s(String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(str.trim().getBytes());
            return m14017c(instance.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return str;
        }
    }

    /* renamed from: c */
    public static String m14017c(byte[] bArr) {
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (byte b : bArr) {
            sb.append(f15439d[(b & 240) >>> 4]);
            sb.append(f15439d[b & 15]);
        }
        return sb.toString();
    }

    /* renamed from: l */
    public static int m13987l(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            return -1;
        }
        if (activeNetworkInfo.getType() == 1) {
            return 1;
        }
        return activeNetworkInfo.getType() == 0 ? 0 : -1;
    }

    /* renamed from: m */
    public static boolean m13985m(Context context) {
        return -1 != m13987l(context);
    }

    /* renamed from: n */
    public static boolean m13983n(Context context) {
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) context.getSystemService(ServiceManagerNative.ACTIVITY)).getRunningAppProcesses()) {
            if (runningAppProcessInfo.processName.equals(context.getPackageName())) {
                return runningAppProcessInfo.importance != 100;
            }
        }
        return false;
    }

    /* renamed from: o */
    public static String m13981o(Context context) {
        return ((TelephonyManager) context.getSystemService(ShippingInfoWidget.f12563f)).getLine1Number();
    }

    /* renamed from: p */
    public static boolean m13979p(Context context) {
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

    /* renamed from: q */
    public static boolean m13977q(Context context) {
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

    /* renamed from: r */
    public static boolean m13975r(Context context) {
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
    public static CharSequence m13970t(String str) {
        return Html.fromHtml(str);
    }

    /* renamed from: g */
    public static String m14000g() {
        return Build.BRAND;
    }

    /* renamed from: h */
    public static String m13997h() {
        return Build.MODEL;
    }

    /* renamed from: s */
    public static boolean m13973s(@NonNull Context context) {
        Resources resources = context.getResources();
        return (resources == null || resources.getConfiguration() == null || resources.getConfiguration().orientation != 2) ? false : true;
    }

    /* renamed from: t */
    public static void m13971t(Context context) {
        if (Build.VERSION.SDK_INT >= 19) {
            ((Activity) context).getWindow().getDecorView().setSystemUiVisibility(3334);
        }
    }
}
