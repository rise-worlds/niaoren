package com.redwas.service;

import android.accessibilityservice.AccessibilityService;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.PowerManager;
import android.os.SystemClock;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import com.cyjh.ddy.media.p035a.ResultTypeConstant;
import p110z1.AppExecutors;
import p110z1.RedUtil;
import p110z1.ScreenUtil;
import p110z1.SharedPreferencesUtil;

/* loaded from: classes2.dex */
public class LuckMoneyService extends BaseAccessbilityService {

    /* renamed from: B */
    private static long f11713B = 0;

    /* renamed from: a */
    public static final String f11715a = "LuckMoneyService";

    /* renamed from: b */
    public static boolean f11716b = false;

    /* renamed from: c */
    public static boolean f11717c = true;

    /* renamed from: d */
    public static boolean f11718d = false;

    /* renamed from: o */
    private static final String f11729o = "com.tencent.mm";

    /* renamed from: p */
    private static final String f11730p = "com.tencent.mm.plugin.luckymoney.ui.LuckyMoneyNotHookReceiveUI";

    /* renamed from: w */
    private static final float f11737w = 0.641f;

    /* renamed from: x */
    private static final float f11738x = 0.81f;

    /* renamed from: y */
    private static final float f11739y = 0.705f;

    /* renamed from: e */
    public static final String f11719e = RedUtil.m11452a().f17399c;

    /* renamed from: q */
    private static final String f11731q = RedUtil.m11452a().f17400d;

    /* renamed from: r */
    private static final String f11732r = RedUtil.m11452a().f17401e;

    /* renamed from: s */
    private static final String f11733s = RedUtil.m11452a().f17402f;

    /* renamed from: t */
    private static final String f11734t = RedUtil.m11452a().f17403g;

    /* renamed from: u */
    private static final String f11735u = RedUtil.m11452a().f17404h;

    /* renamed from: f */
    public static final String f11720f = RedUtil.m11452a().f17405i;

    /* renamed from: g */
    public static final String f11721g = RedUtil.m11452a().f17406j;

    /* renamed from: h */
    public static final String f11722h = RedUtil.m11452a().f17407k;

    /* renamed from: i */
    public static final String f11723i = RedUtil.m11452a().f17408l;

    /* renamed from: j */
    public static final String f11724j = RedUtil.m11452a().f17409m;

    /* renamed from: v */
    private static String f11736v = "com.tencent.mm.plugin.luckymoney.ui.LuckyMoneyDetailUI";

    /* renamed from: k */
    public static int f11725k = 600;

    /* renamed from: l */
    public static int f11726l = 500;

    /* renamed from: m */
    public static int f11727m = 1500;

    /* renamed from: n */
    public static int f11728n = -1;

    /* renamed from: C */
    private static boolean f11714C = false;

    /* renamed from: z */
    private int f11741z = ScreenUtil.f17410a;

    /* renamed from: A */
    private int f11740A = ScreenUtil.f17411b;

    @Override // android.accessibilityservice.AccessibilityService
    public void onInterrupt() {
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        Log.e("红包", "服务挂了");
    }

    @Override // android.accessibilityservice.AccessibilityService
    @RequiresApi(api = 24)
    public void onAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        String str;
        if (f11717c) {
            Log.e("红包", "设置暂停了。直接拦截");
            f11718d = false;
        } else if (accessibilityEvent != null) {
            Log.e("红包", accessibilityEvent.toString());
            String str2 = null;
            String charSequence = accessibilityEvent.getPackageName() != null ? accessibilityEvent.getPackageName().toString() : null;
            if (charSequence == null || charSequence.contains("com.tencent.mm")) {
                Log.e("红包", "当前页面 之前");
                if (accessibilityEvent.getClassName() != null) {
                    str2 = accessibilityEvent.getClassName().toString();
                }
                if (str2 == null || !str2.equals(f11730p)) {
                    AccessibilityNodeInfo b = m18136b(f11720f);
                    if (b != null) {
                        Log.e("详情页面", "是在详情页面——list不为空");
                        f11713B = System.currentTimeMillis();
                        m18130c(b);
                        f11718d = true;
                    } else if (accessibilityEvent.getParcelableData() == null || !(accessibilityEvent.getParcelableData() instanceof Notification)) {
                        Log.e("详情页面", "classname" + str2);
                        if (str2 == null || !str2.equals(f11736v)) {
                            AccessibilityNodeInfo b2 = m18136b(f11734t);
                            if (b2 != null) {
                                Log.e("红包", "在聊天列表");
                                m18128d(b2);
                                return;
                            }
                            return;
                        }
                        f11714C = true;
                        Log.e(f11715a, "ç" + (System.currentTimeMillis() - f11713B) + "ms");
                        AccessibilityNodeInfo b3 = m18136b(f11721g);
                        String str3 = "";
                        if (b3 != null) {
                            str3 = b3.getText().toString();
                            Log.e("详情页面", "时间" + b3.getText().toString());
                        }
                        AccessibilityNodeInfo b4 = m18136b(f11723i);
                        String charSequence2 = b4 != null ? b4.getText().toString() : ResultTypeConstant.f7213z;
                        Log.e("详情页面", "金额" + b4.getText().toString());
                        AccessibilityNodeInfo b5 = m18136b(f11722h);
                        if (b5 != null) {
                            Log.e("详情页面", "金额" + b5.getText().toString());
                            str = b5.getText().toString();
                        } else {
                            str = "";
                        }
                        Intent intent = new Intent();
                        intent.setAction("redbroadcase_action");
                        intent.putExtra("times", str3);
                        intent.putExtra("moenys", charSequence2);
                        intent.putExtra("users", str);
                        sendBroadcast(intent);
                        SystemClock.sleep(2000L);
                        performGlobalAction(1);
                        performGlobalAction(2);
                        if (!f11716b) {
                            SystemClock.sleep(10L);
                            performGlobalAction(1);
                            performGlobalAction(2);
                        }
                    } else {
                        Log.e("红包", "接收到通知栏红包消息");
                        if (!f11718d) {
                            Log.e("红包", "不在聊天详情页面");
                            Notification notification = (Notification) accessibilityEvent.getParcelableData();
                            if (notification.tickerText != null && notification.tickerText.toString().split(":")[1].trim().contains(f11719e)) {
                                Log.e(f11715a, "接收到通知栏红包消息");
                                try {
                                    notification.contentIntent.send();
                                } catch (PendingIntent.CanceledException e) {
                                    Log.e("红包", "接收到通知栏红包消息--跳转出错");
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                } else {
                    Log.e("红包", "红包弹窗");
                    f11714C = false;
                    m18127e();
                }
            } else {
                f11718d = false;
            }
        }
    }

    /* renamed from: e */
    private void m18127e() {
        if (Build.VERSION.SDK_INT > 24) {
            AccessibilityNodeInfo b = m18136b(f11724j);
            if (b != null) {
                Log.e("红包", "红包弹窗--取到开按钮 ——den");
                f11728n = 0;
                m18143a(b);
                Log.e(f11715a, "获取到了开按钮");
                return;
            }
            f11728n = 1;
            Log.e("红包", "红包弹窗--没有取到开按钮 ——den，走模拟点击");
            AppExecutors.m11422e().m11425b().execute(new Runnable() { // from class: com.redwas.service.-$$Lambda$LuckMoneyService$DkrrLAzsOtwWveNAHrcfU1S5xeI
                @Override // java.lang.Runnable
                public final void run() {
                    LuckMoneyService.this.m18126f();
                }
            });
            return;
        }
        m18131b(m18136b(f11724j));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: f */
    public /* synthetic */ void m18126f() {
        long currentTimeMillis = System.currentTimeMillis();
        while (System.currentTimeMillis() - currentTimeMillis < f11725k && !f11714C) {
            Log.e("红包", "红包弹窗--没有取到开按钮 ——den，走模拟点击进来");
            m18145a(this.f11741z / 2, this.f11740A * f11737w, 1, (AccessibilityService.GestureResultCallback) null);
            SystemClock.sleep(30L);
        }
        if (f11714C) {
            Log.e(f11715a, "按钮点击完成，已到领取详情页");
            return;
        }
        SystemClock.sleep(f11726l);
        if (f11714C) {
            Log.e(f11715a, "等待时间后，已到领取详情页");
            return;
        }
        if (f11716b) {
            m18145a(this.f11741z / 2, this.f11740A * f11738x, 1, (AccessibilityService.GestureResultCallback) null);
        } else {
            performGlobalAction(2);
        }
        SystemClock.sleep(f11727m);
        performGlobalAction(2);
    }

    /* renamed from: b */
    private void m18131b(AccessibilityNodeInfo accessibilityNodeInfo) {
        if (accessibilityNodeInfo != null) {
            if (accessibilityNodeInfo.isClickable() && accessibilityNodeInfo.getClassName() != null && accessibilityNodeInfo.getClassName().toString().contains("android.widget.Button")) {
                accessibilityNodeInfo.performAction(16);
                accessibilityNodeInfo.recycle();
            }
            int childCount = accessibilityNodeInfo.getChildCount();
            if (childCount > 0) {
                for (int i = 0; i < childCount; i++) {
                    m18131b(accessibilityNodeInfo.getChild(i));
                }
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:9:0x0055, code lost:
        if (r2.left > (r6.f11741z / 2)) goto L_0x0059;
     */
    /* renamed from: c */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void m18130c(android.view.accessibility.AccessibilityNodeInfo r7) {
        /*
            r6 = this;
            int r0 = r7.getChildCount()
            r1 = 1
            int r0 = r0 - r1
            android.view.accessibility.AccessibilityNodeInfo r7 = r7.getChild(r0)
            java.lang.String r0 = com.redwas.service.LuckMoneyService.f11735u
            android.view.accessibility.AccessibilityNodeInfo r0 = r6.m18142a(r7, r0)
            java.lang.String r2 = com.redwas.service.LuckMoneyService.f11732r
            android.view.accessibility.AccessibilityNodeInfo r7 = r6.m18142a(r7, r2)
            java.lang.String r2 = "详情页面"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "未领取红包。selfLuckMoney 前"
            r3.append(r4)
            r4 = 0
            r3.append(r4)
            java.lang.String r5 = "--screenWidth"
            r3.append(r5)
            int r5 = r6.f11741z
            r3.append(r5)
            java.lang.String r3 = r3.toString()
            android.util.Log.e(r2, r3)
            int r2 = r6.f11741z
            if (r2 == 0) goto L_0x003f
            int r2 = r6.f11740A
            if (r2 != 0) goto L_0x0045
        L_0x003f:
            p110z1.ScreenUtil.m11450a(r6)
            r6.m18129d()
        L_0x0045:
            if (r7 == 0) goto L_0x0058
            android.graphics.Rect r2 = new android.graphics.Rect
            r2.<init>()
            r7.getBoundsInScreen(r2)
            int r7 = r2.left
            int r2 = r6.f11741z
            int r2 = r2 / 2
            if (r7 <= r2) goto L_0x0058
            goto L_0x0059
        L_0x0058:
            r1 = 0
        L_0x0059:
            if (r0 == 0) goto L_0x008e
            if (r1 != 0) goto L_0x008e
            java.lang.String r7 = com.redwas.service.LuckMoneyService.f11733s
            android.view.accessibility.AccessibilityNodeInfo r7 = r6.m18142a(r0, r7)
            java.lang.String r1 = "详情页面"
            java.lang.String r2 = "是红包的记录"
            android.util.Log.e(r1, r2)
            if (r7 != 0) goto L_0x0070
            r6.m18143a(r0)
            return
        L_0x0070:
            java.lang.String r0 = "详情页面"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "已领取过了"
            r1.append(r2)
            java.lang.CharSequence r7 = r7.getText()
            java.lang.String r7 = r7.toString()
            r1.append(r7)
            java.lang.String r7 = r1.toString()
            android.util.Log.e(r0, r7)
        L_0x008e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.redwas.service.LuckMoneyService.m18130c(android.view.accessibility.AccessibilityNodeInfo):void");
    }

    /* renamed from: d */
    private void m18128d(AccessibilityNodeInfo accessibilityNodeInfo) {
        for (int i = 0; i < accessibilityNodeInfo.getChildCount(); i++) {
            AccessibilityNodeInfo a = m18142a(accessibilityNodeInfo.getChild(i), f11731q);
            if (a != null && a.getText() != null && a.getText().toString().contains(f11719e)) {
                Log.e("红包", "在聊天列表，有发现微信红包");
                m18143a(a);
                return;
            }
        }
    }

    /* renamed from: d */
    protected void m18129d() {
        SharedPreferencesUtil.m11446a(getApplicationContext());
        this.f11741z = SharedPreferencesUtil.m11439a("SCREEN_WIDTH", (Integer) 0);
        this.f11740A = SharedPreferencesUtil.m11439a("SCREEN_HEIGHT", (Integer) 0);
    }

    @Override // android.accessibilityservice.AccessibilityService
    protected void onServiceConnected() {
        super.onServiceConnected();
        Log.e("红包", "onServiceConnected");
        m18129d();
        if (this.f11741z == 0 || this.f11740A == 0) {
            ScreenUtil.m11450a(this);
            m18129d();
        }
        Log.e("红包", "onServiceConnected--之后");
        ((PowerManager) getSystemService("power")).newWakeLock(6, "WxService:wakeLock").acquire();
        performGlobalAction(1);
        performGlobalAction(1);
        performGlobalAction(1);
        Log.e("红包", "onServiceConnected--最后");
    }
}
