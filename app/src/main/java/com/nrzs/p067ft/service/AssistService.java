package com.nrzs.p067ft.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.os.Build;
import android.os.IBinder;
import android.support.p003v4.internal.view.SupportMenu;
import android.util.Log;
import android.widget.RemoteViews;
import com.blankj.utilcode.util.LogUtils;
import com.lody.virtual.client.ipc.ServiceManagerNative;
import com.lody.virtual.client.stub.ChooseTypeAndAccountActivity;
import com.nrzs.data.DataApp;
import com.nrzs.data.game.bean.TopicInfo;
import com.nrzs.data.p066ft.bean.AssistInfo;
import com.nrzs.data.p066ft.bean.EnginInteraRequestInfo;
import com.nrzs.data.user.bean.UserInfo;
import com.nrzs.p067ft.C1990R;
import com.nrzs.p067ft.p068ui.view.AssistInfoView;
import java.util.Date;
import p110z1.AssistRunTJRepository;
import p110z1.ChannelDataManager;
import p110z1.FloatAssistManager;
import p110z1.FloatDataManager;
import p110z1.FloatViewManager;
import p110z1.FtUserManager;
import p110z1.HttpVal;
import p110z1.IntentToAssistService;
import p110z1.LocalHttp;
import p110z1.LoginManager;

/* renamed from: com.nrzs.ft.service.AssistService */
/* loaded from: classes2.dex */
public class AssistService extends Service {

    /* renamed from: a */
    public static final String f10723a = "com.nrzs.fg.AssistService";

    /* renamed from: b */
    public static final int f10724b = 1;

    /* renamed from: c */
    public static final int f10725c = 2;

    /* renamed from: g */
    private static final int f10726g = 65533;

    /* renamed from: h */
    private static final String f10727h = "click_notification";

    /* renamed from: d */
    private boolean f10728d = false;

    /* renamed from: e */
    private Notification f10729e = null;

    /* renamed from: f */
    private RemoteViews f10730f = null;

    /* renamed from: i */
    private BroadcastReceiver f10731i;

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        m18888d();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        UserInfo userInfo;
        int intExtra = intent.getIntExtra(IntentToAssistService.f16142e, 0);
        Log.d("MyComponentDelegate", "flag:" + i + ",startId:" + i2 + ",key:" + intExtra);
        if (intExtra == 1) {
            EnginInteraRequestInfo enginInteraRequestInfo = (EnginInteraRequestInfo) intent.getParcelableExtra(IntentToAssistService.f16143f);
            FloatAssistManager.m12397i().m12420a(enginInteraRequestInfo, "登录成功初始化");
            LocalHttp.f16542c = enginInteraRequestInfo.dycIp;
            HttpVal.m12518b();
            UserInfo userInfo2 = (UserInfo) intent.getParcelableExtra(UserInfo.class.getName());
            FtUserManager.m12790g().m12796a(userInfo2);
            LoginManager.m12620d().m12628a(userInfo2);
            if (DataApp.m18939d().f10604g == 2) {
                ChannelDataManager.m12655a().m12652b();
            }
        } else if (intExtra == 2) {
            m18891a(intent, 1);
        } else if (intExtra == 3) {
            m18891a(intent, 2);
        } else if (intExtra == 4) {
            if (FloatDataManager.m12352j().f16748d == 2) {
                m18893a();
            } else {
                m18889c();
            }
        } else if (intExtra == 7) {
            m18892a(intent);
        } else if (intExtra != 6) {
            if (intExtra == 8) {
                String stringExtra = intent.getStringExtra("localGamePackage");
                int intExtra2 = intent.getIntExtra(ChooseTypeAndAccountActivity.KEY_USER_ID, -1);
                FloatDataManager.m12352j().m12364a(stringExtra);
                FloatDataManager.m12352j().m12361b(intExtra2);
            } else if (intExtra == 9) {
                m18889c();
            } else if (intExtra == 10) {
                FloatAssistManager.m12397i().m12426a(0);
            } else if (intExtra == 11 && (userInfo = (UserInfo) intent.getParcelableExtra(UserInfo.class.getName())) != null) {
                FtUserManager.m12790g().m12796a(userInfo);
            }
        }
        return 2;
    }

    /* renamed from: a */
    public void m18893a() {
        if (!FloatAssistManager.m12397i().m12427a()) {
            this.f10728d = false;
            m18889c();
        }
    }

    /* renamed from: b */
    public void m18890b() {
        Log.d("MyComponentDelegate", "go toScriptServiceForKey stopScript:");
        FloatViewManager.m12346a(getApplicationContext()).m12319j();
        FloatViewManager.m12346a(getApplicationContext()).m12335b(AssistInfoView.class.getName());
        FloatDataManager.m12352j().m12370a(1);
        FloatAssistManager.m12397i().m12414b();
    }

    /* renamed from: c */
    public void m18889c() {
        if (!FloatAssistManager.m12397i().m12427a()) {
            Log.d("MyComponentDelegate", "go toScriptServiceForKey stopScript:");
            try {
                FloatDataManager.m12352j().m12353i();
                FloatAssistManager.m12397i().m12398h();
                FloatViewManager.m12346a(getApplicationContext()).m12321h();
                AssistRunTJRepository.m12779c().m12781b();
                Intent intent = new Intent("com.nrzs.ft.NRZSAidl.action");
                intent.setPackage("com.angel.nrzs");
                stopService(intent);
            } catch (Exception e) {
                e.printStackTrace();
            }
            stopSelf();
        }
    }

    /* renamed from: a */
    private void m18891a(Intent intent, int i) {
        LogUtils.m23734c(f10723a, "initFloat type 1:" + i);
        FloatAssistManager.m12397i().m12424a(getApplication());
        if (i == 1) {
            LogUtils.m23734c(f10723a, "initFloat type 2:" + i);
            Class cls = (Class) intent.getSerializableExtra(IntentToAssistService.f16141d);
            int intExtra = intent.getIntExtra(IntentToAssistService.f16144g, 0);
            AssistInfo assistInfo = (AssistInfo) intent.getParcelableExtra("assistInfo");
            FloatAssistManager.m12397i().m12413b(0);
            FloatDataManager.m12352j().m12368a(this, (TopicInfo) intent.getParcelableExtra("topicInfo"), assistInfo, cls, intExtra);
        } else if (i == 2) {
            LogUtils.m23734c(f10723a, "initFloat type 3:" + i);
            FloatAssistManager.m12397i().m12413b(1);
            FloatDataManager.m12352j().m12369a(this);
        }
    }

    /* renamed from: a */
    private void m18892a(Intent intent) {
        int intExtra = intent.getIntExtra(IntentToAssistService.f16144g, 0);
        TopicInfo topicInfo = (TopicInfo) intent.getParcelableExtra("topicInfo");
        AssistInfo assistInfo = (AssistInfo) intent.getParcelableExtra("assistInfo");
        FloatAssistManager.m12397i().m12413b(1);
        FloatDataManager.m12352j().m12365a(topicInfo, assistInfo, (Class) intent.getSerializableExtra(IntentToAssistService.f16141d), intExtra);
    }

    /* renamed from: d */
    private void m18888d() {
        Notification.Builder builder;
        try {
            this.f10731i = new BroadcastReceiver() { // from class: com.nrzs.ft.service.AssistService.1
                @Override // android.content.BroadcastReceiver
                public void onReceive(Context context, Intent intent) {
                    if (intent.getAction().equals(AssistService.f10727h)) {
                        AssistService.this.m18889c();
                    }
                }
            };
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(f10727h);
            registerReceiver(this.f10731i, intentFilter);
            NotificationManager notificationManager = (NotificationManager) getSystemService(ServiceManagerNative.NOTIFICATION);
            Resources resources = getResources();
            long currentTimeMillis = System.currentTimeMillis();
            if (Build.VERSION.SDK_INT >= 26) {
                NotificationChannel notificationChannel = new NotificationChannel("createRecordingNotification", "Channel1", 3);
                notificationChannel.enableLights(true);
                notificationChannel.setLightColor(SupportMenu.CATEGORY_MASK);
                notificationChannel.setShowBadge(true);
                notificationChannel.enableVibration(false);
                notificationChannel.setVibrationPattern(new long[]{0});
                if (notificationManager != null) {
                    notificationManager.createNotificationChannel(notificationChannel);
                    builder = new Notification.Builder(this, "createRecordingNotification");
                } else {
                    builder = new Notification.Builder(this);
                }
            } else {
                builder = new Notification.Builder(this);
            }
            builder.setTicker(resources.getString(C1990R.string.app_name));
            builder.setSmallIcon(C1990R.C1991drawable.bird_logo_pop);
            builder.setWhen(currentTimeMillis);
            this.f10729e = builder.build();
            this.f10729e.icon = C1990R.C1991drawable.bird_logo_pop;
            this.f10729e.flags = 2;
            this.f10729e.flags |= 32;
            this.f10729e.flags |= 64;
            this.f10730f = new RemoteViews(getPackageName(), C1990R.layout.nrzs_ft_notification_view);
            this.f10730f.setImageViewResource(C1990R.C1992id.notification_large_icon, C1990R.C1991drawable.bird_logo_pop);
            this.f10730f.setImageViewResource(C1990R.C1992id.notification_small_icon, C1990R.C1991drawable.bird_ic_head_search_clear);
            this.f10730f.setTextViewText(C1990R.C1992id.notification_title, getResources().getString(C1990R.string.app_name));
            this.f10730f.setTextViewText(C1990R.C1992id.notification_text, "正在运行");
            Date date = new Date();
            this.f10730f.setTextViewText(C1990R.C1992id.notification_time, date.getHours() + ":" + date.getMinutes());
            this.f10729e.contentView = this.f10730f;
            this.f10730f.setOnClickPendingIntent(C1990R.C1992id.notification_small_icon, PendingIntent.getBroadcast(this, 0, new Intent(f10727h), 0));
            Intent intent = new Intent();
            intent.setClassName("com.angel.nrzs", "com.angel.nrzs.ui.activity.MainActivity");
            intent.setFlags(335544320);
            this.f10729e.contentIntent = PendingIntent.getActivity(this, C1990R.string.app_name, intent, 0);
            startForeground(f10726g, this.f10729e);
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override // android.app.Service
    public void onDestroy() {
        unregisterReceiver(this.f10731i);
        super.onDestroy();
    }
}
