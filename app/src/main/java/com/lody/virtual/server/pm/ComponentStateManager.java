package com.lody.virtual.server.pm;

import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.SparseArray;
import com.lody.virtual.client.core.VirtualCore;
import java.util.Map;

/* renamed from: com.lody.virtual.server.pm.ComponentStateManager */
/* loaded from: classes.dex */
public class ComponentStateManager {
    private static SparseArray<UserComponentState> helpers = new SparseArray<>();

    public static synchronized UserComponentState user(int i) {
        UserComponentState userComponentState;
        synchronized (ComponentStateManager.class) {
            userComponentState = helpers.get(i);
            if (userComponentState == null) {
                userComponentState = new UserComponentState(i);
                helpers.put(i, userComponentState);
            }
        }
        return userComponentState;
    }

    /* renamed from: com.lody.virtual.server.pm.ComponentStateManager$UserComponentState */
    /* loaded from: classes.dex */
    public static class UserComponentState {
        private SharedPreferences sharedPreferences;

        private UserComponentState(int i) {
            Context context = VirtualCore.get().getContext();
            this.sharedPreferences = context.getSharedPreferences("va_components_state_u" + i, 0);
        }

        public int get(ComponentName componentName) {
            return this.sharedPreferences.getInt(componentKey(componentName), 0);
        }

        public void set(ComponentName componentName, int i) {
            this.sharedPreferences.edit().putInt(componentKey(componentName), i).apply();
        }

        public void clear(String str) {
            Map<String, ?> all = this.sharedPreferences.getAll();
            if (all != null) {
                for (String str2 : all.keySet()) {
                    if (str2.startsWith(str + "@")) {
                        this.sharedPreferences.edit().remove(str2).apply();
                    }
                }
            }
        }

        public void clearAll() {
            this.sharedPreferences.edit().clear().apply();
        }

        private String componentKey(ComponentName componentName) {
            return componentName.getPackageName() + "@" + componentName.getClassName();
        }
    }
}
