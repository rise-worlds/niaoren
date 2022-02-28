package p110z1;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import com.lody.virtual.client.hook.delegate.TaskDescriptionDelegate;
import com.lody.virtual.os.VUserManager;

@TargetApi(21)
/* renamed from: z1.aja */
/* loaded from: classes3.dex */
public class MyTaskDescDelegate implements TaskDescriptionDelegate {
    @Override // com.lody.virtual.client.hook.delegate.TaskDescriptionDelegate
    public ActivityManager.TaskDescription getTaskDescription(ActivityManager.TaskDescription taskDescription) {
        if (taskDescription == null) {
            return null;
        }
        int userHandle = VUserManager.get().getUserHandle();
        String str = " (" + (userHandle + 1) + ")";
        if ((taskDescription.getLabel() != null ? taskDescription.getLabel() : "").endsWith(str)) {
            return taskDescription;
        }
        return new ActivityManager.TaskDescription(taskDescription.getLabel() + str, taskDescription.getIcon(), taskDescription.getPrimaryColor());
    }
}
