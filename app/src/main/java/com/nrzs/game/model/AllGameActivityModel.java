package com.nrzs.game.model;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import com.nrzs.data.game.bean.TopicInfo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.slf4j.Marker;
import p110z1.CharacterParser;
import p110z1.TopicInfoManager;
import p110z1.VUiKit;

/* loaded from: classes2.dex */
public class AllGameActivityModel extends AndroidViewModel {

    /* renamed from: a */
    private MutableLiveData<List<TopicInfo>> f10877a;

    public AllGameActivityModel(@NonNull Application application) {
        super(application);
    }

    /* renamed from: a */
    public MutableLiveData<List<TopicInfo>> m18816a() {
        if (this.f10877a == null) {
            this.f10877a = new MutableLiveData<>();
        }
        return this.f10877a;
    }

    /* renamed from: b */
    public void m18814b() {
        VUiKit.m11713a().mo3333a(new Runnable() { // from class: com.nrzs.game.model.AllGameActivityModel.1
            @Override // java.lang.Runnable
            public void run() {
                ArrayList arrayList = new ArrayList();
                List<TopicInfo> b = TopicInfoManager.m12726c().m12729b();
                if (b != null && b.size() > 0) {
                    TopicInfo topicInfo = new TopicInfo();
                    topicInfo.isFirst = true;
                    topicInfo.sortLetters = Marker.ANY_MARKER;
                    arrayList.add(topicInfo);
                    for (TopicInfo topicInfo2 : b) {
                        topicInfo2.sortLetters = Marker.ANY_MARKER;
                        arrayList.add(topicInfo2);
                    }
                }
                List<TopicInfo> a = TopicInfoManager.m12726c().m12738a();
                for (TopicInfo topicInfo3 : a) {
                    String upperCase = CharacterParser.m12894a().m12889c(topicInfo3.TopicName).substring(0, 1).toUpperCase();
                    if (upperCase.matches("[A-Z]")) {
                        topicInfo3.sortLetters = upperCase.toUpperCase();
                    } else {
                        topicInfo3.sortLetters = "#";
                    }
                }
                Collections.sort(a, new C2056a());
                int i = 0;
                for (TopicInfo topicInfo4 : a) {
                    if (i == AllGameActivityModel.this.m18815a(topicInfo4.sortLetters.charAt(0), a, a.size())) {
                        TopicInfo topicInfo5 = new TopicInfo();
                        topicInfo5.isFirst = true;
                        topicInfo5.sortLetters = topicInfo4.sortLetters;
                        arrayList.add(topicInfo5);
                        arrayList.add(topicInfo4);
                    } else {
                        arrayList.add(topicInfo4);
                    }
                    i++;
                }
                AllGameActivityModel.this.m18816a().postValue(arrayList);
            }
        });
    }

    /* renamed from: a */
    public int m18815a(int i, List<TopicInfo> list, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            if (list.get(i3).sortLetters.toUpperCase().charAt(0) == i) {
                return i3;
            }
        }
        return -1;
    }

    /* renamed from: com.nrzs.game.model.AllGameActivityModel$a */
    /* loaded from: classes2.dex */
    public class C2056a implements Comparator<TopicInfo> {
        public C2056a() {
        }

        /* renamed from: a */
        public int compare(TopicInfo topicInfo, TopicInfo topicInfo2) {
            if ("@".equals(topicInfo.sortLetters) || "#".equals(topicInfo2.sortLetters)) {
                return -1;
            }
            if ("#".equals(topicInfo.sortLetters) || "@".equals(topicInfo2.sortLetters)) {
                return 1;
            }
            return topicInfo.sortLetters.compareTo(topicInfo2.sortLetters);
        }
    }
}
