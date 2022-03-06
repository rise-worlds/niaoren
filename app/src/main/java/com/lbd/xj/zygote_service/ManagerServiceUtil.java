package com.lbd.xj.zygote_service;

import android.content.Intent;
import android.net.LocalServerSocket;
import android.os.Build;
import com.common.utils.log.LogUtils;
import com.lbd.xj.app.XJApp;
import java.io.DataInputStream;
import java.io.IOException;
import p110z1.SocketConstants;

/* renamed from: com.lbd.xj.zygote_service.a */
/* loaded from: classes.dex */
public class ManagerServiceUtil {

    /* renamed from: a */
    public static String f10230a = "SHENG";

    /* JADX WARN: Type inference failed for: r0v0, types: [com.lbd.xj.zygote_service.a$1] */
    /* renamed from: a */
    public static void m19232a() {
        new Thread() { // from class: com.lbd.xj.zygote_service.a.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                try {
                    LocalServerSocket localServerSocket = new LocalServerSocket(SocketConstants.f15241d);
                    boolean z = true;
                    int i = 1;
                    while (z) {
                        new DataInputStream(localServerSocket.accept().getInputStream()).readInt();
                        switch (i) {
                            case 1:
                                ManagerServiceUtil.m19231b();
                                break;
                            case 2:
                                Intent intent = new Intent(XJApp.getInstance().getApplicationContext(), MyService5.class);
                                intent.putExtra("name", "m1");
                                if (Build.VERSION.SDK_INT < 26) {
                                    XJApp.getInstance().getApplicationContext().startService(intent);
                                    break;
                                } else {
                                    XJApp.getInstance().getApplicationContext().startForegroundService(intent);
                                    break;
                                }
                            case 3:
                                Intent intent2 = new Intent(XJApp.getInstance().getApplicationContext(), MyService6.class);
                                intent2.putExtra("name", "m1");
                                if (Build.VERSION.SDK_INT < 26) {
                                    XJApp.getInstance().getApplicationContext().startService(intent2);
                                    break;
                                } else {
                                    XJApp.getInstance().getApplicationContext().startForegroundService(intent2);
                                    break;
                                }
                            case 4:
                                Intent intent3 = new Intent(XJApp.getInstance().getApplicationContext(), MyService7.class);
                                intent3.putExtra("name", "m1");
                                if (Build.VERSION.SDK_INT < 26) {
                                    XJApp.getInstance().getApplicationContext().startService(intent3);
                                    break;
                                } else {
                                    XJApp.getInstance().getApplicationContext().startForegroundService(intent3);
                                    break;
                                }
                            case 5:
                                Intent intent4 = new Intent(XJApp.getInstance().getApplicationContext(), MyService8.class);
                                intent4.putExtra("name", "m1");
                                if (Build.VERSION.SDK_INT < 26) {
                                    XJApp.getInstance().getApplicationContext().startService(intent4);
                                    break;
                                } else {
                                    XJApp.getInstance().getApplicationContext().startForegroundService(intent4);
                                    break;
                                }
                            case 6:
                                Intent intent5 = new Intent(XJApp.getInstance().getApplicationContext(), MyService9.class);
                                intent5.putExtra("name", "m1");
                                if (Build.VERSION.SDK_INT < 26) {
                                    XJApp.getInstance().getApplicationContext().startService(intent5);
                                    break;
                                } else {
                                    XJApp.getInstance().getApplicationContext().startForegroundService(intent5);
                                    break;
                                }
                            case 7:
                                Intent intent6 = new Intent(XJApp.getInstance().getApplicationContext(), MyService10.class);
                                intent6.putExtra("name", "m1");
                                if (Build.VERSION.SDK_INT < 26) {
                                    XJApp.getInstance().getApplicationContext().startService(intent6);
                                    break;
                                } else {
                                    XJApp.getInstance().getApplicationContext().startForegroundService(intent6);
                                    break;
                                }
                            case 8:
                                Intent intent7 = new Intent(XJApp.getInstance().getApplicationContext(), MyService11.class);
                                intent7.putExtra("name", "m1");
                                if (Build.VERSION.SDK_INT < 26) {
                                    XJApp.getInstance().getApplicationContext().startService(intent7);
                                    break;
                                } else {
                                    XJApp.getInstance().getApplicationContext().startForegroundService(intent7);
                                    break;
                                }
                            case 9:
                                Intent intent8 = new Intent(XJApp.getInstance().getApplicationContext(), MyService12.class);
                                intent8.putExtra("name", "m1");
                                if (Build.VERSION.SDK_INT < 26) {
                                    XJApp.getInstance().getApplicationContext().startService(intent8);
                                    break;
                                } else {
                                    XJApp.getInstance().getApplicationContext().startForegroundService(intent8);
                                    break;
                                }
                            case 10:
                                Intent intent9 = new Intent(XJApp.getInstance().getApplicationContext(), MyService13.class);
                                intent9.putExtra("name", "m1");
                                if (Build.VERSION.SDK_INT < 26) {
                                    XJApp.getInstance().getApplicationContext().startService(intent9);
                                    break;
                                } else {
                                    XJApp.getInstance().getApplicationContext().startForegroundService(intent9);
                                    break;
                                }
                            case 11:
                                Intent intent10 = new Intent(XJApp.getInstance().getApplicationContext(), MyService14.class);
                                intent10.putExtra("name", "m1");
                                if (Build.VERSION.SDK_INT < 26) {
                                    XJApp.getInstance().getApplicationContext().startService(intent10);
                                    break;
                                } else {
                                    XJApp.getInstance().getApplicationContext().startForegroundService(intent10);
                                    break;
                                }
                            case 12:
                                Intent intent11 = new Intent(XJApp.getInstance().getApplicationContext(), MyService15.class);
                                intent11.putExtra("name", "m1");
                                if (Build.VERSION.SDK_INT < 26) {
                                    XJApp.getInstance().getApplicationContext().startService(intent11);
                                    break;
                                } else {
                                    XJApp.getInstance().getApplicationContext().startForegroundService(intent11);
                                    break;
                                }
                            case 13:
                                Intent intent12 = new Intent(XJApp.getInstance().getApplicationContext(), MyService16.class);
                                intent12.putExtra("name", "m1");
                                if (Build.VERSION.SDK_INT < 26) {
                                    XJApp.getInstance().getApplicationContext().startService(intent12);
                                    break;
                                } else {
                                    XJApp.getInstance().getApplicationContext().startForegroundService(intent12);
                                    break;
                                }
                            case 14:
                                Intent intent13 = new Intent(XJApp.getInstance().getApplicationContext(), MyService17.class);
                                intent13.putExtra("name", "m1");
                                if (Build.VERSION.SDK_INT < 26) {
                                    XJApp.getInstance().getApplicationContext().startService(intent13);
                                    break;
                                } else {
                                    XJApp.getInstance().getApplicationContext().startForegroundService(intent13);
                                    break;
                                }
                            case 15:
                                Intent intent14 = new Intent(XJApp.getInstance().getApplicationContext(), MyService18.class);
                                intent14.putExtra("name", "m1");
                                if (Build.VERSION.SDK_INT < 26) {
                                    XJApp.getInstance().getApplicationContext().startService(intent14);
                                    break;
                                } else {
                                    XJApp.getInstance().getApplicationContext().startForegroundService(intent14);
                                    break;
                                }
                            case 16:
                                Intent intent15 = new Intent(XJApp.getInstance().getApplicationContext(), MyService19.class);
                                intent15.putExtra("name", "m1");
                                if (Build.VERSION.SDK_INT < 26) {
                                    XJApp.getInstance().getApplicationContext().startService(intent15);
                                    break;
                                } else {
                                    XJApp.getInstance().getApplicationContext().startForegroundService(intent15);
                                    break;
                                }
                            case 17:
                                Intent intent16 = new Intent(XJApp.getInstance().getApplicationContext(), MyService20.class);
                                intent16.putExtra("name", "m1");
                                if (Build.VERSION.SDK_INT < 26) {
                                    XJApp.getInstance().getApplicationContext().startService(intent16);
                                    break;
                                } else {
                                    XJApp.getInstance().getApplicationContext().startForegroundService(intent16);
                                    break;
                                }
                            case 18:
                                Intent intent17 = new Intent(XJApp.getInstance().getApplicationContext(), MyService21.class);
                                intent17.putExtra("name", "m1");
                                if (Build.VERSION.SDK_INT < 26) {
                                    XJApp.getInstance().getApplicationContext().startService(intent17);
                                    break;
                                } else {
                                    XJApp.getInstance().getApplicationContext().startForegroundService(intent17);
                                    break;
                                }
                            case 19:
                                Intent intent18 = new Intent(XJApp.getInstance().getApplicationContext(), MyService22.class);
                                intent18.putExtra("name", "m1");
                                if (Build.VERSION.SDK_INT < 26) {
                                    XJApp.getInstance().getApplicationContext().startService(intent18);
                                    break;
                                } else {
                                    XJApp.getInstance().getApplicationContext().startForegroundService(intent18);
                                    break;
                                }
                            case 20:
                                Intent intent19 = new Intent(XJApp.getInstance().getApplicationContext(), MyService23.class);
                                intent19.putExtra("name", "m1");
                                if (Build.VERSION.SDK_INT < 26) {
                                    XJApp.getInstance().getApplicationContext().startService(intent19);
                                    break;
                                } else {
                                    XJApp.getInstance().getApplicationContext().startForegroundService(intent19);
                                    break;
                                }
                            case 21:
                                Intent intent20 = new Intent(XJApp.getInstance().getApplicationContext(), MyService24.class);
                                intent20.putExtra("name", "m1");
                                if (Build.VERSION.SDK_INT < 26) {
                                    XJApp.getInstance().getApplicationContext().startService(intent20);
                                    break;
                                } else {
                                    XJApp.getInstance().getApplicationContext().startForegroundService(intent20);
                                    break;
                                }
                            case 22:
                                Intent intent21 = new Intent(XJApp.getInstance().getApplicationContext(), MyService25.class);
                                intent21.putExtra("name", "m1");
                                if (Build.VERSION.SDK_INT < 26) {
                                    XJApp.getInstance().getApplicationContext().startService(intent21);
                                    break;
                                } else {
                                    XJApp.getInstance().getApplicationContext().startForegroundService(intent21);
                                    break;
                                }
                            case 23:
                                Intent intent22 = new Intent(XJApp.getInstance().getApplicationContext(), MyService26.class);
                                intent22.putExtra("name", "m1");
                                if (Build.VERSION.SDK_INT < 26) {
                                    XJApp.getInstance().getApplicationContext().startService(intent22);
                                    break;
                                } else {
                                    XJApp.getInstance().getApplicationContext().startForegroundService(intent22);
                                    break;
                                }
                            case 24:
                                Intent intent23 = new Intent(XJApp.getInstance().getApplicationContext(), MyService27.class);
                                intent23.putExtra("name", "m1");
                                if (Build.VERSION.SDK_INT < 26) {
                                    XJApp.getInstance().getApplicationContext().startService(intent23);
                                    break;
                                } else {
                                    XJApp.getInstance().getApplicationContext().startForegroundService(intent23);
                                    break;
                                }
                            case 25:
                                Intent intent24 = new Intent(XJApp.getInstance().getApplicationContext(), MyService28.class);
                                intent24.putExtra("name", "m1");
                                if (Build.VERSION.SDK_INT < 26) {
                                    XJApp.getInstance().getApplicationContext().startService(intent24);
                                    break;
                                } else {
                                    XJApp.getInstance().getApplicationContext().startForegroundService(intent24);
                                    break;
                                }
                            case 26:
                                Intent intent25 = new Intent(XJApp.getInstance().getApplicationContext(), MyService29.class);
                                intent25.putExtra("name", "m1");
                                if (Build.VERSION.SDK_INT < 26) {
                                    XJApp.getInstance().getApplicationContext().startService(intent25);
                                    break;
                                } else {
                                    XJApp.getInstance().getApplicationContext().startForegroundService(intent25);
                                    break;
                                }
                            case 27:
                                Intent intent26 = new Intent(XJApp.getInstance().getApplicationContext(), MyService30.class);
                                intent26.putExtra("name", "m1");
                                if (Build.VERSION.SDK_INT < 26) {
                                    XJApp.getInstance().getApplicationContext().startService(intent26);
                                    break;
                                } else {
                                    XJApp.getInstance().getApplicationContext().startForegroundService(intent26);
                                    break;
                                }
                            case 28:
                                Intent intent27 = new Intent(XJApp.getInstance().getApplicationContext(), MyService31.class);
                                intent27.putExtra("name", "m1");
                                if (Build.VERSION.SDK_INT < 26) {
                                    XJApp.getInstance().getApplicationContext().startService(intent27);
                                    break;
                                } else {
                                    XJApp.getInstance().getApplicationContext().startForegroundService(intent27);
                                    break;
                                }
                            case 29:
                                Intent intent28 = new Intent(XJApp.getInstance().getApplicationContext(), MyService32.class);
                                intent28.putExtra("name", "m1");
                                if (Build.VERSION.SDK_INT < 26) {
                                    XJApp.getInstance().getApplicationContext().startService(intent28);
                                    break;
                                } else {
                                    XJApp.getInstance().getApplicationContext().startForegroundService(intent28);
                                    break;
                                }
                            case 30:
                                Intent intent29 = new Intent(XJApp.getInstance().getApplicationContext(), MyService33.class);
                                intent29.putExtra("name", "m1");
                                if (Build.VERSION.SDK_INT < 26) {
                                    XJApp.getInstance().getApplicationContext().startService(intent29);
                                    break;
                                } else {
                                    XJApp.getInstance().getApplicationContext().startForegroundService(intent29);
                                    break;
                                }
                            case 31:
                                Intent intent30 = new Intent(XJApp.getInstance().getApplicationContext(), MyService34.class);
                                intent30.putExtra("name", "m1");
                                if (Build.VERSION.SDK_INT < 26) {
                                    XJApp.getInstance().getApplicationContext().startService(intent30);
                                    break;
                                } else {
                                    XJApp.getInstance().getApplicationContext().startForegroundService(intent30);
                                    break;
                                }
                            case 32:
                                Intent intent31 = new Intent(XJApp.getInstance().getApplicationContext(), MyService35.class);
                                intent31.putExtra("name", "m1");
                                if (Build.VERSION.SDK_INT < 26) {
                                    XJApp.getInstance().getApplicationContext().startService(intent31);
                                    break;
                                } else {
                                    XJApp.getInstance().getApplicationContext().startForegroundService(intent31);
                                    break;
                                }
                            case 33:
                                Intent intent32 = new Intent(XJApp.getInstance().getApplicationContext(), MyService36.class);
                                intent32.putExtra("name", "m1");
                                if (Build.VERSION.SDK_INT < 26) {
                                    XJApp.getInstance().getApplicationContext().startService(intent32);
                                    break;
                                } else {
                                    XJApp.getInstance().getApplicationContext().startForegroundService(intent32);
                                    break;
                                }
                            case 34:
                                Intent intent33 = new Intent(XJApp.getInstance().getApplicationContext(), MyService37.class);
                                intent33.putExtra("name", "m1");
                                if (Build.VERSION.SDK_INT < 26) {
                                    XJApp.getInstance().getApplicationContext().startService(intent33);
                                    break;
                                } else {
                                    XJApp.getInstance().getApplicationContext().startForegroundService(intent33);
                                    break;
                                }
                            case 35:
                                Intent intent34 = new Intent(XJApp.getInstance().getApplicationContext(), MyService38.class);
                                intent34.putExtra("name", "m1");
                                if (Build.VERSION.SDK_INT < 26) {
                                    XJApp.getInstance().getApplicationContext().startService(intent34);
                                    break;
                                } else {
                                    XJApp.getInstance().getApplicationContext().startForegroundService(intent34);
                                    break;
                                }
                            case 36:
                                Intent intent35 = new Intent(XJApp.getInstance().getApplicationContext(), MyService39.class);
                                intent35.putExtra("name", "m1");
                                if (Build.VERSION.SDK_INT < 26) {
                                    XJApp.getInstance().getApplicationContext().startService(intent35);
                                    break;
                                } else {
                                    XJApp.getInstance().getApplicationContext().startForegroundService(intent35);
                                    break;
                                }
                            case 37:
                                Intent intent36 = new Intent(XJApp.getInstance().getApplicationContext(), MyService40.class);
                                intent36.putExtra("name", "m1");
                                if (Build.VERSION.SDK_INT < 26) {
                                    XJApp.getInstance().getApplicationContext().startService(intent36);
                                    break;
                                } else {
                                    XJApp.getInstance().getApplicationContext().startForegroundService(intent36);
                                    break;
                                }
                            default:
                                z = false;
                                break;
                        }
                        i++;
                    }
                } catch (IOException e) {
                    LogUtils.m22038d(ManagerServiceUtil.f10230a, "MyService error");
                    e.printStackTrace();
                }
            }
        }.start();
    }

    /* renamed from: b */
    public static void m19231b() {
        Intent intent = new Intent(XJApp.getInstance().getApplicationContext(), MyService1.class);
        intent.putExtra("name", "m1");
        if (Build.VERSION.SDK_INT >= 26) {
            XJApp.getInstance().getApplicationContext().startForegroundService(intent);
        } else {
            XJApp.getInstance().getApplicationContext().startService(intent);
        }
        Intent intent2 = new Intent(XJApp.getInstance().getApplicationContext(), MyService2.class);
        intent2.putExtra("name", "m1");
        if (Build.VERSION.SDK_INT >= 26) {
            XJApp.getInstance().getApplicationContext().startForegroundService(intent2);
        } else {
            XJApp.getInstance().getApplicationContext().startService(intent2);
        }
        Intent intent3 = new Intent(XJApp.getInstance().getApplicationContext(), MyService3.class);
        intent3.putExtra("name", "m1");
        if (Build.VERSION.SDK_INT >= 26) {
            XJApp.getInstance().getApplicationContext().startForegroundService(intent3);
        } else {
            XJApp.getInstance().getApplicationContext().startService(intent3);
        }
        Intent intent4 = new Intent(XJApp.getInstance().getApplicationContext(), MyService4.class);
        intent4.putExtra("name", "m1");
        if (Build.VERSION.SDK_INT >= 26) {
            XJApp.getInstance().getApplicationContext().startForegroundService(intent4);
        } else {
            XJApp.getInstance().getApplicationContext().startService(intent4);
        }
    }
}
