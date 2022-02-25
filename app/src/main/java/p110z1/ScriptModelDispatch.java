package p110z1;

import android.content.Context;
import android.util.Log;
import com.ctetin.expandabletextviewlibrary.ExpandableTextView;
import java.io.File;

/* renamed from: z1.aby */
/* loaded from: classes3.dex */
public class ScriptModelDispatch {
    /* renamed from: a */
    public static IScriptUiModel m14412a(Context context, String str, String str2, String str3) {
        Log.e("a111111", "getScriptModel " + str2 + ExpandableTextView.f6958c + str + ExpandableTextView.f6958c + str3);
        if (str2 == null || !new File(str2).exists()) {
            return new ScriptUiModel(context, str, str3);
        }
        return new ScriptUipModel(context, str2, str3);
    }
}
