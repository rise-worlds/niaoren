package p110z1;

import android.content.Context;
import com.nrzs.core.models.AppData;
import com.nrzs.core.models.AppInfo;
import com.nrzs.core.models.AppInfoLite;
import com.nrzs.p072va.AppInstallResult;
import java.io.File;
import java.util.List;

/* renamed from: z1.aje */
/* loaded from: classes3.dex */
public interface AppDataSource {
    /* renamed from: a */
    AppInfo mo12955a(Context context, String str, boolean z);

    /* renamed from: a */
    AppInstallResult mo12952a(AppInfoLite appInfoLite);

    /* renamed from: a */
    Promise<List<AppData>, Throwable, Void> mo12961a();

    /* renamed from: a */
    Promise<List<AppInfo>, Throwable, Void> mo12960a(Context context);

    /* renamed from: a */
    Promise<List<AppInfo>, Throwable, Void> mo12958a(Context context, File file);

    /* renamed from: a */
    Promise<AppInfo, Throwable, Void> mo12956a(Context context, String str);

    /* renamed from: a */
    Promise<AppData, Throwable, Void> mo12951a(String str);

    /* renamed from: a */
    boolean mo12950a(String str, int i);

    /* renamed from: b */
    Promise<AppInfo, Throwable, Void> mo12946b(Context context, String str);
}
