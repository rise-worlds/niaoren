package p110z1;

import android.text.TextUtils;
import android.util.Xml;
import com.common.utils.log.LogUtils;
import com.lbd.xj.app.XJApp;
import com.lbd.xj.manager.launch.BoxLaunchManager;
import com.lody.virtual.client.ipc.ServiceManagerNative;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.mail.EmailConstants;
import org.apache.http.cookie.ClientCookie;
import org.xmlpull.v1.XmlPullParser;

/* renamed from: z1.aev */
/* loaded from: classes3.dex */
public class VersionHelper {

    /* compiled from: VersionHelper.java */
    /* renamed from: z1.aev$a */
    /* loaded from: classes3.dex */
    public static class C3400a {

        /* renamed from: b */
        public int f15480b = 100119;

        /* renamed from: a */
        public String f15479a = "";
    }

    /* renamed from: a */
    public static String m13829a(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        File file = new File(str);
        if (file.isDirectory()) {
            LogUtils.m22038d("TestFile", "The File doesn't not exist.");
            return stringBuffer.toString();
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                stringBuffer.append(readLine);
                stringBuffer.append("\n");
            }
            fileInputStream.close();
        } catch (IOException e) {
            LogUtils.m22038d("TestFile", e.getMessage());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return stringBuffer.toString();
    }

    /* renamed from: a */
    public static int m13832a() {
        File file = new File(BoxLaunchManager.rootFile + "/r/ot01/data/system/packages.xml");
        if (file.exists()) {
            m13829a(file.getAbsolutePath());
            Map a = m13830a(file);
            if (a.containsKey("ch.deletescape.lawnchair.plah")) {
                return ((Integer) a.get("ch.deletescape.lawnchair.plah")).intValue();
            }
        }
        return 0;
    }

    /* renamed from: b */
    public static int m13828b() {
        File file = new File(BoxLaunchManager.rootFile + "/r/ot01/data/system/romversion");
        if (file.exists()) {
            String a = m13829a(file.getAbsolutePath());
            if (TextUtils.isEmpty(a)) {
                try {
                    return Integer.valueOf(a).intValue();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return 100119;
    }

    /* renamed from: c */
    public static C3400a m13827c() {
        C3400a aVar = new C3400a();
        File file = new File(XJApp.getInstance().getApplicationInfo().dataDir + "/Update");
        if (!file.exists()) {
            return aVar;
        }
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
            aVar.f15480b = randomAccessFile.readInt();
            randomAccessFile.close();
            return aVar;
        } catch (Exception unused) {
            return aVar;
        }
    }

    /* renamed from: a */
    public static Map m13830a(File file) {
        HashMap hashMap = new HashMap();
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            XmlPullParser newPullParser = Xml.newPullParser();
            newPullParser.setInput(fileInputStream, EmailConstants.UTF_8);
            for (int eventType = newPullParser.getEventType(); eventType != 1; eventType = newPullParser.next()) {
                String name = newPullParser.getName();
                if (eventType == 2 && !"packages".equals(name) && ServiceManagerNative.PACKAGE.equals(name)) {
                    hashMap.put(newPullParser.getAttributeValue(null, "name"), Integer.valueOf(Integer.parseInt(newPullParser.getAttributeValue(null, ClientCookie.VERSION_ATTR))));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hashMap;
    }

    /* renamed from: a */
    public static void m13831a(int i) {
        File file = new File(XJApp.getInstance().getApplicationInfo().dataDir + "/Update");
        if (!file.exists()) {
            try {
                if (!file.createNewFile()) {
                    LogUtils.m22036e("SHENG UPDATE", "create Update error");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
            randomAccessFile.writeInt(i);
            randomAccessFile.close();
        } catch (IOException e2) {
            e2.printStackTrace();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }
}
