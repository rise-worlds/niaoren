package p110z1;

import android.content.Context;
import android.util.Log;
import android.widget.LinearLayout;
import com.cyjh.mobileanjian.ipc.uip.UipHelper;
import com.cyjh.mqm.MiscUtilities;
import java.io.File;
import java.io.IOException;
import org.apache.commons.p105io.FileUtils;
import org.json.JSONException;

/* renamed from: z1.aca */
/* loaded from: classes3.dex */
public class ScriptUipModel implements IScriptUiModel {

    /* renamed from: a */
    private Context f15149a;

    /* renamed from: b */
    private File f15150b;

    /* renamed from: c */
    private File f15151c;

    /* renamed from: d */
    private UipHelper f15152d;

    @Override // p110z1.IScriptUiModel
    /* renamed from: a */
    public void mo14398a(String str) {
    }

    public ScriptUipModel(Context context, String str, String str2) {
        this.f15150b = new File(str);
        this.f15151c = new File(str2);
        this.f15149a = context;
        this.f15152d = new UipHelper(context);
        Log.e("a111111", "ScriptUipModel " + str);
    }

    @Override // p110z1.IScriptUiModel
    /* renamed from: b */
    public void mo14397b() throws JSONException {
        try {
            m14401a(this.f15151c);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // p110z1.IScriptUiModel
    /* renamed from: c */
    public String mo14396c() throws Exception {
        if (this.f15150b.exists()) {
            return m14400b(this.f15151c);
        }
        return null;
    }

    /* renamed from: a */
    private void m14401a(File file) throws JSONException {
        if (file != null && file.exists() && !file.isDirectory() && file.length() != 0) {
            try {
                this.f15152d.configViewFromJson(FileUtils.readFileToString(file, "UTF-8"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /* renamed from: b */
    private String m14400b(File file) throws Exception {
        if (file == null || file.isDirectory()) {
            return null;
        }
        this.f15152d.saveToConfigFile(file.getAbsolutePath());
        return FileUtils.readFileToString(file, "UTF-8");
    }

    @Override // p110z1.IScriptUiModel
    /* renamed from: a */
    public LinearLayout mo14399a() throws Exception {
        if (!this.f15150b.exists()) {
            return new LinearLayout(this.f15149a);
        }
        return this.f15152d.parseLayoutFromJson(new MiscUtilities().LoadUIFile(this.f15150b.getAbsolutePath(), true), null);
    }
}
