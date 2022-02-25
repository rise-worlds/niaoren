package jonathanfinerty.once;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import java.util.concurrent.ExecutionException;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class AsyncSharedPreferenceLoader {

    /* renamed from: a */
    private final Context f14672a;

    /* renamed from: b */
    private final AsyncTask<String, Void, SharedPreferences> f14673b = new AsyncTask<String, Void, SharedPreferences>() { // from class: jonathanfinerty.once.AsyncSharedPreferenceLoader.1
        /* JADX INFO: Access modifiers changed from: protected */
        /* renamed from: a */
        public SharedPreferences doInBackground(String... strArr) {
            return AsyncSharedPreferenceLoader.this.f14672a.getSharedPreferences(strArr[0], 0);
        }
    };

    public AsyncSharedPreferenceLoader(Context context, String str) {
        this.f14672a = context;
        this.f14673b.execute(str);
    }

    public SharedPreferences get() {
        try {
            return this.f14673b.get();
        } catch (InterruptedException | ExecutionException unused) {
            return null;
        }
    }
}
