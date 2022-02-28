package p110z1;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import com.lody.virtual.client.hook.delegate.TaskDescriptionDelegate;
import com.lody.virtual.os.VUserManager;

@TargetApi(21)
/* renamed from: z1.ajb */
/* loaded from: classes3.dex */
public class MyTaskDescriptionDelegate implements TaskDescriptionDelegate {
    @Override // com.lody.virtual.client.hook.delegate.TaskDescriptionDelegate
    public ActivityManager.TaskDescription getTaskDescription(ActivityManager.TaskDescription taskDescription) {
        if (taskDescription == null) {
            return null;
        }
        String str = "[" + VUserManager.get().getUserName() + "] ";
        if ((taskDescription.getLabel() != null ? taskDescription.getLabel() : "").startsWith(str)) {
            return taskDescription;
        }
        return new ActivityManager.TaskDescription(str + taskDescription.getLabel(), taskDescription.getIcon(), taskDescription.getPrimaryColor());
    }
}
