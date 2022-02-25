package p110z1;

import android.util.Xml;
import com.lbd.p054xj.app.AppConfig;
import com.lbd.p054xj.manager.launch.BoxLaunchManager;
import com.lody.virtual.client.ipc.ServiceManagerNative;
import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import org.apache.commons.mail.EmailConstants;
import org.xmlpull.v1.XmlPullParser;

/* renamed from: z1.aet */
/* loaded from: classes3.dex */
public class XposeUtil {
    /* renamed from: a */
    public static void m13839a() {
        String d = m13836d();
        if (d != null && d.startsWith("/data/app")) {
            aef.m14113i(BoxLaunchManager.rootFile + "/r/ot01" + d);
        }
        new File(BoxLaunchManager.rootFile + "/r/ot01/data/app/xposed_update_Sign").deleteOnExit();
    }

    /* renamed from: b */
    public static void m13838b() {
        if (m13837c()) {
            try {
                aef.m14139b(BoxLaunchManager.rootFile.getAbsolutePath() + File.separator + "xp", BoxLaunchManager.rootFile.getAbsolutePath() + File.separator + "r/ot01");
            } catch (Exception e) {
                e.printStackTrace();
            }
            m13839a();
        }
    }

    /* renamed from: c */
    public static boolean m13837c() {
        String d = m13836d();
        if (d == null) {
            return false;
        }
        return d.startsWith("/data/app") || d.startsWith("/system/app");
    }

    /* renamed from: d */
    public static String m13836d() {
        String str;
        Exception e;
        new HashMap();
        try {
            FileInputStream fileInputStream = new FileInputStream(new File("/data/data/" + AppConfig.f9441h + "/osimg/r/ot01/data/system/packages.xml"));
            XmlPullParser newPullParser = Xml.newPullParser();
            newPullParser.setInput(fileInputStream, EmailConstants.UTF_8);
            str = null;
            for (int eventType = newPullParser.getEventType(); eventType != 1; eventType = newPullParser.next()) {
                try {
                    String name = newPullParser.getName();
                    if (eventType == 2 && !"packages".equals(name) && ServiceManagerNative.PACKAGE.equals(name) && "de.robv.android.xposed.installer".equals(newPullParser.getAttributeValue(null, "name"))) {
                        str = newPullParser.getAttributeValue(null, "codePath");
                    }
                } catch (Exception e2) {
                    e = e2;
                    e.printStackTrace();
                    return str;
                }
            }
        } catch (Exception e3) {
            e = e3;
            str = null;
        }
        return str;
    }
}
