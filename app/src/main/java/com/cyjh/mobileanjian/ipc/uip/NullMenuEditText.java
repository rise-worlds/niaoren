package com.cyjh.mobileanjian.ipc.uip;

import android.content.Context;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

/* renamed from: com.cyjh.mobileanjian.ipc.uip.d */
/* loaded from: classes.dex */
public final class NullMenuEditText extends EditText {
    @Override // android.widget.TextView
    public final boolean onTextContextMenuItem(int i) {
        return true;
    }

    private NullMenuEditText(Context context) {
        super(context);
        setLongClickable(false);
        setTextIsSelectable(false);
        setCustomSelectionActionModeCallback(new ActionMode.Callback() { // from class: com.cyjh.mobileanjian.ipc.uip.d.1
            @Override // android.view.ActionMode.Callback
            public final boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
                return false;
            }

            @Override // android.view.ActionMode.Callback
            public final boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                return false;
            }

            @Override // android.view.ActionMode.Callback
            public final void onDestroyActionMode(ActionMode actionMode) {
            }

            @Override // android.view.ActionMode.Callback
            public final boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                return false;
            }
        });
    }
}
