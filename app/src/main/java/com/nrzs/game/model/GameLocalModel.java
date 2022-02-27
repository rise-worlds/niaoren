package com.nrzs.game.model;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import com.blankj.utilcode.util.LogUtils;
import com.nrzs.core.models.GameInfo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import p110z1.CharacterParser;
import p110z1.GameApp;
import p110z1.PackageUtil;
import p110z1.VUiKit;

/* loaded from: classes2.dex */
public class GameLocalModel extends AndroidViewModel {

    /* renamed from: a */
    private MutableLiveData<List<GameInfo>> f10880a;

    /* renamed from: b */
    private PackageManager f10881b = GameApp.getInstance().m13006b().getPackageManager();

    public GameLocalModel(@NonNull Application application) {
        super(application);
    }

    /* renamed from: a */
    public MutableLiveData<List<GameInfo>> m18812a() {
        if (this.f10880a == null) {
            this.f10880a = new MutableLiveData<>();
        }
        return this.f10880a;
    }

    /* renamed from: b */
    public void m18809b() {
        VUiKit.m11713a().mo3333a(new Runnable() { // from class: com.nrzs.game.model.GameLocalModel.1
            @Override // java.lang.Runnable
            public void run() {
                ArrayList<GameInfo> arrayList = new ArrayList();
                List<PackageInfo> a = PackageUtil.m11851a(GameApp.getInstance().m13006b());
                if (a != null) {
                    for (PackageInfo packageInfo : a) {
                        GameInfo gameInfo = new GameInfo();
                        gameInfo.f10543g = packageInfo.applicationInfo.loadLabel(GameLocalModel.this.f10881b).toString();
                        gameInfo.f10544h = packageInfo.packageName;
                        gameInfo.f10548l = packageInfo.applicationInfo.loadIcon(GameLocalModel.this.f10881b);
                        String upperCase = CharacterParser.m12894a().m12889c(gameInfo.f10543g).substring(0, 1).toUpperCase();
                        if (upperCase.matches("[A-Z]")) {
                            gameInfo.f10545i = upperCase.toUpperCase();
                        } else {
                            gameInfo.f10545i = "#";
                        }
                        arrayList.add(gameInfo);
                    }
                }
                Collections.sort(arrayList, new C2058a());
                ArrayList arrayList2 = new ArrayList();
                int i = 0;
                for (GameInfo gameInfo2 : arrayList) {
                    LogUtils.m23734c("GameLocalModel", "letter:" + gameInfo2.f10545i);
                    if (i == GameLocalModel.this.m18811a(gameInfo2.f10545i.charAt(0), arrayList, arrayList.size())) {
                        GameInfo gameInfo3 = new GameInfo();
                        gameInfo3.f10549m = true;
                        gameInfo3.f10545i = gameInfo2.f10545i;
                        arrayList2.add(gameInfo3);
                        arrayList2.add(gameInfo2);
                    } else {
                        arrayList2.add(gameInfo2);
                    }
                    i++;
                }
                GameLocalModel.this.m18812a().postValue(arrayList2);
            }
        });
    }

    /* renamed from: a */
    public int m18811a(int i, List<GameInfo> list, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            if (list.get(i3).f10545i.toUpperCase().charAt(0) == i) {
                return i3;
            }
        }
        return -1;
    }

    /* renamed from: com.nrzs.game.model.GameLocalModel$a */
    /* loaded from: classes2.dex */
    public class C2058a implements Comparator<GameInfo> {
        public C2058a() {
        }

        /* renamed from: a */
        public int compare(GameInfo gameInfo, GameInfo gameInfo2) {
            if ("@".equals(gameInfo.f10545i) || "#".equals(gameInfo2.f10545i)) {
                return -1;
            }
            if ("#".equals(gameInfo.f10545i) || "@".equals(gameInfo2.f10545i)) {
                return 1;
            }
            return gameInfo.f10545i.compareTo(gameInfo2.f10545i);
        }
    }
}
