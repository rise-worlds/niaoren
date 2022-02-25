package p110z1;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.nrzs.data.DataApp;
import com.nrzs.data.base.BaseResponse;
import com.nrzs.data.database.AppDatabase;
import com.nrzs.data.game.bean.TopicInfo;
import com.nrzs.data.game.bean.request.TopicRequestInfo;
import com.nrzs.data.game.bean.response.TopicResponseInfo;
import com.nrzs.http.Api;
import com.nrzs.http.BaseRepository;
import com.nrzs.http.ThreadCallback;
import com.nrzs.http.UICallback;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* renamed from: z1.akw */
/* loaded from: classes3.dex */
public class TopicRepository {

    /* renamed from: a */
    private BaseRepository<List<TopicInfo>> f16267a;

    /* renamed from: b */
    private ThreadCallback f16268b = new ThreadCallback<List<TopicInfo>, String>() { // from class: z1.akw.1
        /* renamed from: a */
        public List<TopicInfo> onResponse(String str) {
            BaseResponse baseResponse;
            List<TopicInfo> arrayList = new ArrayList<>();
            new ArrayList();
            List<TopicInfo> e = TopicInfoManager.m12726c().m12723e();
            if (e == null || e.size() <= 0) {
                String a = TopicRepository.m12700a(DataApp.m18939d().m18947a(), "xs.txt");
                if (!(TextUtils.isEmpty(a) || (baseResponse = (BaseResponse) apa.m11877a(a, new TypeToken<BaseResponse<TopicResponseInfo>>() { // from class: z1.akw.1.1
                })) == null || baseResponse.data == 0)) {
                    arrayList.addAll(((TopicResponseInfo) baseResponse.data).rdata);
                    Log.e("数据库", "走本地json" + arrayList.size());
                    AppDatabase.m18933e().mo18932a().mo12749a(arrayList);
                }
            } else {
                arrayList.addAll(e);
            }
            Log.i("NRZS_TOPIC", "assestDatas.size():" + arrayList.size());
            BaseResponse baseResponse2 = (BaseResponse) apa.m11877a(str, new TypeToken<BaseResponse<TopicResponseInfo>>() { // from class: z1.akw.1.2
            });
            ArrayList arrayList2 = new ArrayList();
            if (!(baseResponse2 == null || baseResponse2.data == 0)) {
                List<TopicInfo> list = ((TopicResponseInfo) baseResponse2.data).rdata;
                for (int i = 0; i < list.size(); i++) {
                    int i2 = 0;
                    while (true) {
                        if (i2 >= arrayList.size()) {
                            break;
                        } else if (list.get(i).TopicID == arrayList.get(i2).TopicID) {
                            arrayList2.add(arrayList.get(i2));
                            AppDatabase.m18933e().mo18932a().mo12744c(arrayList.get(i2).TopicID);
                            break;
                        } else {
                            i2++;
                        }
                    }
                }
                Log.e("数据库", "移除了几项 " + arrayList2.size());
                Log.e("数据库", "重新添加了几项 " + list.size());
                arrayList.removeAll(arrayList2);
                arrayList.addAll(list);
                AppDatabase.m18933e().mo18932a().mo12749a(list);
                String b = apf.m11837b(DataApp.m18939d().m18947a(), ShareVal.f16591a, ShareVal.f16584O, "");
                if (!TextUtils.isEmpty(b)) {
                    ArrayList arrayList3 = new ArrayList();
                    for (String str2 : b.split(",")) {
                        int i3 = 0;
                        while (true) {
                            if (i3 >= arrayList.size()) {
                                break;
                            } else if (Long.parseLong(str2) == arrayList.get(i3).TopicID) {
                                arrayList3.add(arrayList.get(i3));
                                AppDatabase.m18933e().mo18932a().mo12744c(arrayList.get(i3).TopicID);
                                break;
                            } else {
                                i3++;
                            }
                        }
                    }
                    arrayList.removeAll(arrayList3);
                }
            }
            if (arrayList.size() <= 0) {
                return null;
            }
            TopicInfoManager.m12726c().m12727b(arrayList);
            List<TopicInfo> a2 = TopicInfoManager.m12726c().m12734a(DataApp.m18939d().m18947a());
            if (a2 == null || a2.size() <= 0) {
                ArrayList arrayList4 = new ArrayList();
                for (TopicInfo topicInfo : arrayList) {
                    String upperCase = CharacterParser.m12894a().m12889c(topicInfo.TopicName).substring(0, 1).toUpperCase();
                    if (upperCase.matches("[A-Z]")) {
                        topicInfo.sortLetters = upperCase.toUpperCase();
                    } else {
                        topicInfo.sortLetters = "#";
                    }
                }
                Collections.sort(arrayList, new C3624a());
                for (TopicInfo topicInfo2 : arrayList) {
                    topicInfo2.sortLetters.charAt(0);
                    arrayList4.add(topicInfo2);
                }
                return arrayList4;
            }
            for (TopicInfo topicInfo3 : a2) {
                List<TopicInfo> a3 = AppDatabase.m18933e().mo18932a().mo12751a(topicInfo3.TopicID);
                if (a3 == null) {
                    AppDatabase.m18933e().mo18932a().mo12572a((TopicInfoDao) topicInfo3);
                } else {
                    topicInfo3.tid = a3.get(0).tid;
                    AppDatabase.m18933e().mo18932a().mo12568c((TopicInfoDao) topicInfo3);
                }
            }
            a2.addAll(TopicInfoManager.m12726c().m12738a());
            return a2;
        }
    };

    /* renamed from: a */
    public void m12699a(TopicRequestInfo topicRequestInfo, UICallback<List<TopicInfo>> oVar) {
        try {
            if (this.f16267a == null) {
                this.f16267a = new BaseRepository<>();
            }
            this.f16267a.m18568a(Api.m18586a(topicRequestInfo.toGetUrl(HttpVal.f16527n))).m18572a(this.f16268b).m18571a(oVar).m18574a(17);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public void m12702a() {
        if (this.f16267a != null) {
            this.f16267a = null;
        }
    }

    /* renamed from: a */
    public int m12701a(int i, List<TopicInfo> list, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            if (list.get(i3).sortLetters.toUpperCase().charAt(0) == i) {
                return i3;
            }
        }
        return -1;
    }

    /* compiled from: TopicRepository.java */
    /* renamed from: z1.akw$a */
    /* loaded from: classes3.dex */
    public class C3624a implements Comparator<TopicInfo> {
        public C3624a() {
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

    /* JADX WARN: Removed duplicated region for block: B:27:0x003d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* renamed from: b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.String m12698b(android.content.Context r5, java.lang.String r6) {
        /*
            r4 = this;
            r0 = 0
            android.content.res.AssetManager r5 = r5.getAssets()     // Catch: all -> 0x002c, Exception -> 0x002f
            java.io.DataInputStream r1 = new java.io.DataInputStream     // Catch: all -> 0x002c, Exception -> 0x002f
            java.io.InputStream r5 = r5.open(r6)     // Catch: all -> 0x002c, Exception -> 0x002f
            r1.<init>(r5)     // Catch: all -> 0x002c, Exception -> 0x002f
            java.lang.StringBuffer r5 = new java.lang.StringBuffer     // Catch: Exception -> 0x002a, all -> 0x003a
            r5.<init>()     // Catch: Exception -> 0x002a, all -> 0x003a
            r6 = 1024(0x400, float:1.435E-42)
            byte[] r6 = new byte[r6]     // Catch: Exception -> 0x002a, all -> 0x003a
        L_0x0017:
            int r2 = r1.read(r6)     // Catch: Exception -> 0x002a, all -> 0x003a
            r3 = -1
            if (r2 == r3) goto L_0x0022
            r5.append(r6)     // Catch: Exception -> 0x002a, all -> 0x003a
            goto L_0x0017
        L_0x0022:
            java.lang.String r5 = r5.toString()     // Catch: Exception -> 0x002a, all -> 0x003a
            r1.close()     // Catch: Exception -> 0x0029
        L_0x0029:
            return r5
        L_0x002a:
            r5 = move-exception
            goto L_0x0031
        L_0x002c:
            r5 = move-exception
            r1 = r0
            goto L_0x003b
        L_0x002f:
            r5 = move-exception
            r1 = r0
        L_0x0031:
            r5.printStackTrace()     // Catch: all -> 0x003a
            if (r1 == 0) goto L_0x0039
            r1.close()     // Catch: Exception -> 0x0039
        L_0x0039:
            return r0
        L_0x003a:
            r5 = move-exception
        L_0x003b:
            if (r1 == 0) goto L_0x0040
            r1.close()     // Catch: Exception -> 0x0040
        L_0x0040:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: p110z1.TopicRepository.m12698b(android.content.Context, java.lang.String):java.lang.String");
    }

    /* renamed from: a */
    public static String m12700a(Context context, String str) {
        Exception e;
        IOException e2;
        InputStream open;
        String str2;
        String str3 = "";
        try {
            try {
                open = context.getAssets().open(str);
                byte[] bArr = new byte[open.available()];
                open.read(bArr);
                str2 = new String(bArr, StandardCharsets.UTF_8);
            } catch (IOException e3) {
                e2 = e3;
            }
        } catch (Exception e4) {
            e = e4;
        }
        try {
            open.close();
            str3 = str2;
        } catch (IOException e5) {
            e2 = e5;
            str3 = str2;
            e2.printStackTrace();
            System.out.println(str3);
            return str3;
        } catch (Exception e6) {
            e = e6;
            str3 = str2;
            e.printStackTrace();
            System.out.println(str3);
            return str3;
        }
        System.out.println(str3);
        return str3;
    }
}
