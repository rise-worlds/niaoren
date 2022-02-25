package p110z1;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.TextView;
import com.lbd.p054xj.C1467R;
import java.util.List;

/* renamed from: z1.adm */
/* loaded from: classes3.dex */
public class GridApdater extends GVPAdapter<GridItem> {

    /* renamed from: a */
    private Context f15300a;

    public GridApdater(Context context, int i, List<GridItem> list) {
        super(i, list);
        this.f15300a = context;
    }

    /* renamed from: a  reason: avoid collision after fix types in other method */
    public void mo14237a(View view, int i, GridItem adnVar) {
        TextView textView = (TextView) view.findViewById(C1467R.C1469id.xj_grid_item);
        textView.setText(adnVar.f15303c);
        Drawable drawable = this.f15300a.getResources().getDrawable(adnVar.f15302b);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        textView.setCompoundDrawables(null, drawable, null, null);
    }
}
