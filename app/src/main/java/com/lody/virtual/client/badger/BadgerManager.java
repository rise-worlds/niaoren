package com.lody.virtual.client.badger;

import android.content.Intent;
import com.lody.virtual.client.badger.BroadcastBadger1;
import com.lody.virtual.client.badger.BroadcastBadger2;
import com.lody.virtual.client.ipc.VActivityManager;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class BadgerManager {
    private static final Map<String, IBadger> BADGERS = new HashMap(10);

    static {
        addBadger(new BroadcastBadger1.AdwHomeBadger());
        addBadger(new BroadcastBadger1.AospHomeBadger());
        addBadger(new BroadcastBadger1.LGHomeBadger());
        addBadger(new BroadcastBadger1.NewHtcHomeBadger2());
        addBadger(new BroadcastBadger1.OPPOHomeBader());
        addBadger(new BroadcastBadger2.NewHtcHomeBadger1());
    }

    private static void addBadger(IBadger iBadger) {
        BADGERS.put(iBadger.getAction(), iBadger);
    }

    public static boolean handleBadger(Intent intent) {
        IBadger iBadger = BADGERS.get(intent.getAction());
        if (iBadger == null) {
            return false;
        }
        VActivityManager.get().notifyBadgerChange(iBadger.handleBadger(intent));
        return true;
    }
}
