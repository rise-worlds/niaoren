package org.apache.tools.ant.types;

import java.util.ArrayList;
import java.util.List;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectHelper;
import org.apache.tools.ant.Target;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.UnknownElement;
import org.apache.tools.ant.helper.ProjectHelper2;
import org.apache.tools.ant.helper.ProjectHelperImpl;

/* loaded from: classes2.dex */
public class Description extends DataType {
    public void addText(String str) {
        if (((ProjectHelper) getProject().getReference("ant.projectHelper")) instanceof ProjectHelperImpl) {
            String description = getProject().getDescription();
            if (description == null) {
                getProject().setDescription(str);
                return;
            }
            Project project = getProject();
            project.setDescription(description + str);
        }
    }

    public static String getDescription(Project project) {
        List<Target> list = (List) project.getReference(ProjectHelper2.REFID_TARGETS);
        if (list == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (Target target : list) {
            concatDescriptions(project, target, sb);
        }
        return sb.toString();
    }

    private static void concatDescriptions(Project project, Target target, StringBuilder sb) {
        String stringBuffer;
        if (target != null) {
            for (Task task : findElementInTarget(project, target, "description")) {
                if ((task instanceof UnknownElement) && (stringBuffer = ((UnknownElement) task).getWrapper().getText().toString()) != null) {
                    sb.append(project.replaceProperties(stringBuffer));
                }
            }
        }
    }

    private static List<Task> findElementInTarget(Project project, Target target, String str) {
        Task[] tasks;
        ArrayList arrayList = new ArrayList();
        for (Task task : target.getTasks()) {
            if (str.equals(task.getTaskName())) {
                arrayList.add(task);
            }
        }
        return arrayList;
    }
}
