package com.tendcloud.tenddata;

import android.app.Activity;
import android.content.Context;
import com.tendcloud.tenddata.TDAccount;
import java.util.Map;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.ao */
/* loaded from: classes2.dex */
public interface AbstractC2680ao {
    /* renamed from: a */
    void mo15236a(Activity activity, AbstractC2790d dVar);

    /* renamed from: a */
    void mo15235a(Activity activity, String str, String str2, AbstractC2790d dVar);

    /* renamed from: a */
    void mo15233a(Context context, AbstractC2790d dVar);

    /* renamed from: a */
    void mo15231a(Context context, String str, AbstractC2790d dVar);

    /* renamed from: a */
    void mo15230a(Context context, String str, String str2, AbstractC2790d dVar);

    /* renamed from: a */
    void mo15229a(Context context, String str, String str2, Map map, AbstractC2790d dVar);

    /* renamed from: a */
    void mo15228a(Context context, Throwable th, AbstractC2790d dVar);

    /* renamed from: a */
    void mo15227a(ShoppingCart shoppingCart, AbstractC2790d dVar);

    /* renamed from: a */
    void mo15226a(String str, Order order, AbstractC2790d dVar);

    /* renamed from: a */
    void mo15225a(String str, TDAccount.AccountType accountType, AbstractC2790d dVar);

    /* renamed from: a */
    void mo15224a(String str, TDAccount.AccountType accountType, String str2, AbstractC2790d dVar);

    /* renamed from: a */
    void mo15223a(String str, AbstractC2790d dVar);

    /* renamed from: a */
    void mo15222a(String str, Object obj);

    /* renamed from: a */
    void mo15221a(String str, String str2, int i, String str3, String str4, Order order, AbstractC2790d dVar);

    /* renamed from: a */
    void mo15220a(String str, String str2, int i, String str3, String str4, AbstractC2790d dVar);

    /* renamed from: a */
    void mo15219a(String str, String str2, int i, String str3, String str4, String str5, int i2, AbstractC2790d dVar);

    /* renamed from: a */
    void mo15218a(String str, String str2, Order order, AbstractC2790d dVar);

    /* renamed from: a */
    void mo15217a(String str, String str2, String str3, int i, int i2, AbstractC2790d dVar);

    /* renamed from: a */
    void mo15216a(String str, String str2, String str3, int i, AbstractC2790d dVar);

    /* renamed from: b */
    String mo15213b(Context context);

    /* renamed from: b */
    String mo15212b(Context context, AbstractC2790d dVar);

    /* renamed from: b */
    void mo15214b(Activity activity, AbstractC2790d dVar);

    /* renamed from: b */
    void mo15211b(Context context, String str, AbstractC2790d dVar);

    /* renamed from: b */
    void mo15210b(String str, TDAccount.AccountType accountType, AbstractC2790d dVar);

    /* renamed from: b */
    void mo15209b(String str, TDAccount.AccountType accountType, String str2, AbstractC2790d dVar);

    /* renamed from: b */
    void mo15208b(String str, AbstractC2790d dVar);

    /* renamed from: b */
    void mo15207b(String str, String str2, int i, String str3, String str4, AbstractC2790d dVar);

    /* renamed from: c */
    String mo15205c(Context context, AbstractC2790d dVar);

    /* renamed from: c */
    void mo15204c(String str, TDAccount.AccountType accountType, AbstractC2790d dVar);

    /* renamed from: c */
    void mo15203c(String str, AbstractC2790d dVar);

    /* renamed from: c */
    void mo15202c(boolean z);

    /* renamed from: d */
    Context mo15201d();

    /* renamed from: d */
    String mo15200d(Context context, AbstractC2790d dVar);

    /* renamed from: d */
    void mo15199d(String str, AbstractC2790d dVar);

    /* renamed from: e */
    void mo15198e();

    /* renamed from: e */
    void mo15197e(String str, AbstractC2790d dVar);

    void onLogout(AbstractC2790d dVar);

    void removeGlobalKV(String str);

    void setAntiCheatingDisabled(boolean z);
}
