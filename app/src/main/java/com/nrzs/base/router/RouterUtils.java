package com.nrzs.base.router;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.p003v4.app.Fragment;
import com.alipay.sdk.widget.C0675j;
import com.nrzs.base.router.RouterConstants;
import com.nrzs.base.router.provider.InvokeProvider;
import p110z1.ARouter;
import p110z1.GlobalConstants;

/* loaded from: classes.dex */
public class RouterUtils {
    public static void toLogin(int i, int i2) {
        ARouter.m1714a().m1707a(RouterConstants.ModuleUser.LOGIN).withInt("type", i).withInt("closetype", i2).withFlags(337641472).navigation();
    }

    public static void toKickOut(long j, boolean z) {
        ARouter.m1714a().m1707a(RouterConstants.ModuleUser.KICK_OUT).withLong("UserId", j).withBoolean("isKick", z).withFlags(337641472).navigation();
    }

    public static void toChooseScript(int i) {
        ARouter.m1714a().m1707a(RouterConstants.ModuleUser.CHOOSE_SCTIPT).withInt("topic_id_key", i).withFlags(268435456).navigation();
    }

    public static void toGameTopic(String str, int i) {
        ARouter.m1714a().m1707a(RouterConstants.ModuleGame.TOPIC).withString(C0675j.f373k, str).withInt("topic_id_key", i).withFlags(268435456).navigation();
    }

    public static void toGameTopic(String str, int i, int i2) {
        ARouter.m1714a().m1707a(RouterConstants.ModuleGame.TOPIC).withInt("type", i2).withString(C0675j.f373k, str).withInt("topic_id_key", i).withFlags(268435456).navigation();
    }

    public static void toReward(long j) {
        ARouter.m1714a().m1707a(RouterConstants.ModuleUser.REWARD).withLong("authorid", j).navigation();
    }

    public static void toReward(long j, Bundle bundle) {
        ARouter.m1714a().m1707a(RouterConstants.ModuleUser.REWARD).withLong("authorid", j).withBundle("bundle", bundle).navigation();
    }

    public static void toScripInfo(long j, String str, String str2) {
        ARouter.m1714a().m1707a(RouterConstants.ModuleGame.SCRIPT_INFO).withLong("topicId", j).withString("script_id_key", str).withString(C0675j.f373k, str2).withFlags(268435456).navigation();
    }

    public static void toSearch() {
        ARouter.m1714a().m1707a(RouterConstants.ModuleGame.SEARCH).withFlags(268435456).navigation();
    }

    public static void invokeProvider(String str, Object... objArr) {
        InvokeProvider invokeProvider = (InvokeProvider) ARouter.m1714a().m1707a(str).navigation();
        if (invokeProvider != null) {
            invokeProvider.invoke(objArr);
        }
    }

    public static void toXnkj() {
        ARouter.m1714a().m1707a(RouterConstants.ModuleXNKJ.START).withFlags(268435456).navigation();
    }

    public static Fragment getXnkjFragment() {
        return (Fragment) ARouter.m1714a().m1707a(RouterConstants.ModuleXNKJ.XNKJ).navigation();
    }

    public static void toMain(int i) {
        ARouter.m1714a().m1707a(RouterConstants.MAIN).withInt("position", i).withFlags(268435456).navigation();
    }

    public static void toQuesttion() {
        ARouter.m1714a().m1707a(RouterConstants.ModuleUser.QUESTION).navigation();
    }

    public static void toAllGame(int i) {
        ARouter.m1714a().m1707a(RouterConstants.ModuleGame.ALL).withInt("type", i).navigation();
    }

    public static void toMainWebWithClose(int i, int i2, Parcelable parcelable) {
        ARouter.m1714a().m1707a(RouterConstants.WEB).withInt(GlobalConstants.f16483f, i).withInt(GlobalConstants.f16484g, i2).withParcelable(GlobalConstants.f16481d, parcelable).withFlags(268435456).navigation();
    }

    public static void toMainWeb(int i, int i2, Parcelable parcelable) {
        ARouter.m1714a().m1707a(RouterConstants.WEB).withInt(GlobalConstants.f16482e, i).withInt(GlobalConstants.f16483f, i2).withParcelable(GlobalConstants.f16481d, parcelable).withFlags(268435456).navigation();
    }

    public static void toMainWeb(int i, int i2, int i3, Parcelable parcelable) {
        ARouter.m1714a().m1707a(RouterConstants.WEB).withInt(GlobalConstants.f16482e, i).withInt(GlobalConstants.f16483f, i2).withInt(GlobalConstants.f16484g, i3).withParcelable(GlobalConstants.f16481d, parcelable).withFlags(268435456).navigation();
    }

    public static void toMainWebWithWhere(int i, int i2, Parcelable parcelable) {
        ARouter.m1714a().m1707a(RouterConstants.WEB).withInt(GlobalConstants.f16482e, i).withInt(GlobalConstants.f16484g, i2).withParcelable(GlobalConstants.f16481d, parcelable).withFlags(268435456).navigation();
    }
}
