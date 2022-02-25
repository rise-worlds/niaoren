package p110z1;

import android.text.InputFilter;
import android.text.Spanned;
import android.widget.EditText;
import com.ctetin.expandabletextviewlibrary.ExpandableTextView;
import java.util.regex.Pattern;

/* renamed from: z1.aov */
/* loaded from: classes3.dex */
public class EditextUtils {
    /* renamed from: a */
    public static void m11915a(EditText editText) {
        editText.setFilters(new InputFilter[]{new InputFilter() { // from class: z1.aov.1
            @Override // android.text.InputFilter
            public CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
                if (charSequence.equals(ExpandableTextView.f6958c)) {
                    return "";
                }
                return null;
            }
        }, new InputFilter() { // from class: z1.aov.2
            @Override // android.text.InputFilter
            public CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
                if (Pattern.compile("[`~!@#_$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）— +|{}【】‘；：”“’。，、？]").matcher(charSequence.toString()).find()) {
                    return "";
                }
                return null;
            }
        }});
    }
}
