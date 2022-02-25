package com.lody.virtual.client.core;

import android.os.Build;
import com.lody.virtual.client.hook.base.MethodInvocationProxy;
import com.lody.virtual.client.hook.base.MethodInvocationStub;
import com.lody.virtual.client.hook.delegate.AppInstrumentation;
import com.lody.virtual.client.hook.proxies.accessibility.AccessibilityManagerStub;
import com.lody.virtual.client.hook.proxies.account.AccountManagerStub;
import com.lody.virtual.client.hook.proxies.alarm.AlarmManagerStub;
import com.lody.virtual.client.hook.proxies.appops.AppOpsManagerStub;
import com.lody.virtual.client.hook.proxies.appops.FlymePermissionServiceStub;
import com.lody.virtual.client.hook.proxies.appops.SmtOpsManagerStub;
import com.lody.virtual.client.hook.proxies.appwidget.AppWidgetManagerStub;
import com.lody.virtual.client.hook.proxies.atm.ActivityTaskManagerStub;
import com.lody.virtual.client.hook.proxies.audio.AudioManagerStub;
import com.lody.virtual.client.hook.proxies.backup.BackupManagerStub;
import com.lody.virtual.client.hook.proxies.battery_stats.BatteryStatsHub;
import com.lody.virtual.client.hook.proxies.bluetooth.BluetoothStub;
import com.lody.virtual.client.hook.proxies.clipboard.ClipBoardStub;
import com.lody.virtual.client.hook.proxies.connectivity.ConnectivityStub;
import com.lody.virtual.client.hook.proxies.content.ContentServiceStub;
import com.lody.virtual.client.hook.proxies.context_hub.ContextHubServiceStub;
import com.lody.virtual.client.hook.proxies.dev_identifiers_policy.DeviceIdentifiersPolicyServiceHub;
import com.lody.virtual.client.hook.proxies.devicepolicy.DevicePolicyManagerStub;
import com.lody.virtual.client.hook.proxies.display.DisplayStub;
import com.lody.virtual.client.hook.proxies.dropbox.DropBoxManagerStub;
import com.lody.virtual.client.hook.proxies.fingerprint.FingerprintManagerStub;
import com.lody.virtual.client.hook.proxies.graphics.GraphicsStatsStub;
import com.lody.virtual.client.hook.proxies.imms.MmsStub;
import com.lody.virtual.client.hook.proxies.input.InputMethodManagerStub;
import com.lody.virtual.client.hook.proxies.isms.ISmsStub;
import com.lody.virtual.client.hook.proxies.isub.ISubStub;
import com.lody.virtual.client.hook.proxies.job.JobServiceStub;
import com.lody.virtual.client.hook.proxies.libcore.LibCoreStub;
import com.lody.virtual.client.hook.proxies.location.LocationManagerStub;
import com.lody.virtual.client.hook.proxies.media.router.MediaRouterServiceStub;
import com.lody.virtual.client.hook.proxies.media.session.SessionManagerStub;
import com.lody.virtual.client.hook.proxies.mount.MountServiceStub;
import com.lody.virtual.client.hook.proxies.network.NetworkManagementStub;
import com.lody.virtual.client.hook.proxies.notification.NotificationManagerStub;
import com.lody.virtual.client.hook.proxies.p059am.ActivityManagerStub;
import com.lody.virtual.client.hook.proxies.p059am.HCallbackStub;
import com.lody.virtual.client.hook.proxies.p060pm.PackageManagerStub;
import com.lody.virtual.client.hook.proxies.persistent_data_block.PersistentDataBlockServiceStub;
import com.lody.virtual.client.hook.proxies.phonesubinfo.PhoneSubInfoStub;
import com.lody.virtual.client.hook.proxies.power.PowerManagerStub;
import com.lody.virtual.client.hook.proxies.restriction.RestrictionStub;
import com.lody.virtual.client.hook.proxies.search.SearchManagerStub;
import com.lody.virtual.client.hook.proxies.shortcut.ShortcutServiceStub;
import com.lody.virtual.client.hook.proxies.system.LockSettingsStub;
import com.lody.virtual.client.hook.proxies.system.SystemUpdateStub;
import com.lody.virtual.client.hook.proxies.system.WifiScannerStub;
import com.lody.virtual.client.hook.proxies.telecom.TelecomManagerStub;
import com.lody.virtual.client.hook.proxies.telephony.HwTelephonyStub;
import com.lody.virtual.client.hook.proxies.telephony.TelephonyRegistryStub;
import com.lody.virtual.client.hook.proxies.telephony.TelephonyStub;
import com.lody.virtual.client.hook.proxies.usage.UsageStatsManagerStub;
import com.lody.virtual.client.hook.proxies.user.UserManagerStub;
import com.lody.virtual.client.hook.proxies.vibrator.VibratorStub;
import com.lody.virtual.client.hook.proxies.view.AutoFillManagerStub;
import com.lody.virtual.client.hook.proxies.wifi.WifiManagerStub;
import com.lody.virtual.client.hook.proxies.window.WindowManagerStub;
import com.lody.virtual.client.interfaces.IInjector;
import com.lody.virtual.helper.compat.BuildCompat;
import java.util.HashMap;
import java.util.Map;
import p110z1.IFlymePermissionService;
import p110z1.IHwTelephony;
import p110z1.ISmtOpsService;

/* loaded from: classes.dex */
public final class InvocationStubManager {
    private static boolean sInit;
    private static InvocationStubManager sInstance = new InvocationStubManager();
    private Map<Class<?>, IInjector> mInjectors = new HashMap(13);

    private InvocationStubManager() {
    }

    public static InvocationStubManager getInstance() {
        return sInstance;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void injectAll() throws Throwable {
        for (IInjector iInjector : this.mInjectors.values()) {
            iInjector.inject();
        }
        if (VirtualCore.get().isVAppProcess()) {
            addInjector(AppInstrumentation.getDefault());
        }
    }

    public boolean isInit() {
        return sInit;
    }

    public void init() throws Throwable {
        if (!isInit()) {
            injectInternal();
            sInit = true;
            return;
        }
        throw new IllegalStateException("InvocationStubManager Has been initialized.");
    }

    private void injectInternal() throws Throwable {
        if (!VirtualCore.get().isMainProcess()) {
            if (VirtualCore.get().isServerProcess()) {
                addInjector(new ActivityManagerStub());
                addInjector(new PackageManagerStub());
            } else if (VirtualCore.get().isVAppProcess()) {
                addInjector(new LibCoreStub());
                addInjector(new ActivityManagerStub());
                addInjector(new PackageManagerStub());
                addInjector(HCallbackStub.getDefault());
                addInjector(new ISmsStub());
                addInjector(new ISubStub());
                addInjector(new DropBoxManagerStub());
                addInjector(new NotificationManagerStub());
                addInjector(new LocationManagerStub());
                addInjector(new WindowManagerStub());
                addInjector(new ClipBoardStub());
                addInjector(new MountServiceStub());
                addInjector(new BackupManagerStub());
                addInjector(new TelephonyStub());
                addInjector(new AccessibilityManagerStub());
                if (BuildCompat.isOreo() && IHwTelephony.TYPE != null) {
                    addInjector(new HwTelephonyStub());
                }
                addInjector(new TelephonyRegistryStub());
                addInjector(new PhoneSubInfoStub());
                addInjector(new PowerManagerStub());
                addInjector(new AppWidgetManagerStub());
                addInjector(new AccountManagerStub());
                addInjector(new AudioManagerStub());
                addInjector(new SearchManagerStub());
                addInjector(new ContentServiceStub());
                addInjector(new ConnectivityStub());
                addInjector(new BluetoothStub());
                if (Build.VERSION.SDK_INT >= 18) {
                    addInjector(new VibratorStub());
                    addInjector(new WifiManagerStub());
                    addInjector(new ContextHubServiceStub());
                }
                if (Build.VERSION.SDK_INT >= 17) {
                    addInjector(new UserManagerStub());
                }
                if (Build.VERSION.SDK_INT >= 17) {
                    addInjector(new DisplayStub());
                }
                if (Build.VERSION.SDK_INT >= 21) {
                    addInjector(new PersistentDataBlockServiceStub());
                    addInjector(new InputMethodManagerStub());
                    addInjector(new MmsStub());
                    addInjector(new SessionManagerStub());
                    addInjector(new JobServiceStub());
                    addInjector(new RestrictionStub());
                    addInjector(new TelecomManagerStub());
                }
                if (Build.VERSION.SDK_INT >= 19) {
                    addInjector(new AlarmManagerStub());
                    addInjector(new AppOpsManagerStub());
                    addInjector(new MediaRouterServiceStub());
                    if (ISmtOpsService.TYPE != null) {
                        addInjector(new SmtOpsManagerStub());
                    }
                }
                if (Build.VERSION.SDK_INT >= 22) {
                    addInjector(new GraphicsStatsStub());
                    addInjector(new UsageStatsManagerStub());
                }
                if (Build.VERSION.SDK_INT >= 23) {
                    addInjector(new FingerprintManagerStub());
                    addInjector(new NetworkManagementStub());
                }
                if (Build.VERSION.SDK_INT >= 24) {
                    addInjector(new WifiScannerStub());
                    addInjector(new ShortcutServiceStub());
                    addInjector(new DevicePolicyManagerStub());
                    addInjector(new BatteryStatsHub());
                }
                if (BuildCompat.isOreo()) {
                    addInjector(new AutoFillManagerStub());
                }
                if (BuildCompat.isPie()) {
                    addInjector(new SystemUpdateStub());
                    addInjector(new LockSettingsStub());
                }
                if (IFlymePermissionService.TYPE != null) {
                    addInjector(new FlymePermissionServiceStub());
                }
                if (BuildCompat.isQ()) {
                    addInjector(new ActivityTaskManagerStub());
                    addInjector(new DeviceIdentifiersPolicyServiceHub());
                }
            }
        }
    }

    private void addInjector(IInjector iInjector) {
        this.mInjectors.put(iInjector.getClass(), iInjector);
    }

    public <T extends IInjector> T findInjector(Class<T> cls) {
        return (T) this.mInjectors.get(cls);
    }

    public <T extends IInjector> void checkEnv(Class<T> cls) {
        IInjector findInjector = findInjector(cls);
        if (findInjector != null && findInjector.isEnvBad()) {
            try {
                findInjector.inject();
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public void checkAllEnv() {
        for (IInjector iInjector : this.mInjectors.values()) {
            if (iInjector.isEnvBad()) {
                try {
                    iInjector.inject();
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        }
    }

    public <T extends IInjector, H extends MethodInvocationStub> H getInvocationStub(Class<T> cls) {
        IInjector findInjector = findInjector(cls);
        if (findInjector instanceof MethodInvocationProxy) {
            return (H) ((MethodInvocationProxy) findInjector).getInvocationStub();
        }
        return null;
    }
}
