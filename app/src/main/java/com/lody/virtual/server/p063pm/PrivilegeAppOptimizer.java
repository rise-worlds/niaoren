package com.lody.virtual.server.p063pm;

import android.content.Intent;
import android.net.Uri;
import com.lody.virtual.client.stub.StubManifest;
import com.lody.virtual.p061os.VUserHandle;
import com.lody.virtual.server.p062am.VActivityManagerService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* renamed from: com.lody.virtual.server.pm.PrivilegeAppOptimizer */
/* loaded from: classes.dex */
public class PrivilegeAppOptimizer {
    private static final PrivilegeAppOptimizer sInstance = new PrivilegeAppOptimizer();
    private final List<String> privilegeApps = new ArrayList();

    private PrivilegeAppOptimizer() {
        Collections.addAll(this.privilegeApps, StubManifest.PRIVILEGE_APPS);
    }

    public static PrivilegeAppOptimizer get() {
        return sInstance;
    }

    public List<String> getPrivilegeApps() {
        return Collections.unmodifiableList(this.privilegeApps);
    }

    public void addPrivilegeApp(String str) {
        this.privilegeApps.add(str);
    }

    public void removePrivilegeApp(String str) {
        this.privilegeApps.remove(str);
    }

    public boolean isPrivilegeApp(String str) {
        return this.privilegeApps.contains(str);
    }

    public void performOptimizeAllApps() {
        for (String str : this.privilegeApps) {
            performOptimize(str, -1);
        }
    }

    public boolean performOptimize(String str, int i) {
        if (!isPrivilegeApp(str)) {
            return false;
        }
        VActivityManagerService.get().sendBroadcastAsUser(specifyApp(new Intent("android.intent.action.BOOT_COMPLETED", (Uri) null), str, i), new VUserHandle(i));
        return true;
    }

    private Intent specifyApp(Intent intent, String str, int i) {
        intent.putExtra("_VA_|_privilege_pkg_", str);
        intent.putExtra("_VA_|_user_id_", i);
        return intent;
    }
}
