package org.apache.tools.ant.util.facade;

import java.util.ArrayList;
import java.util.List;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.types.Path;

/* loaded from: classes2.dex */
public class FacadeTaskHelper {
    private List<ImplementationSpecificArgument> args;
    private String defaultValue;
    private Path implementationClasspath;
    private String magicValue;
    private String userChoice;

    public FacadeTaskHelper(String str) {
        this(str, null);
    }

    public FacadeTaskHelper(String str, String str2) {
        this.args = new ArrayList();
        this.defaultValue = str;
        this.magicValue = str2;
    }

    public void setMagicValue(String str) {
        this.magicValue = str;
    }

    public void setImplementation(String str) {
        this.userChoice = str;
    }

    public String getImplementation() {
        String str = this.userChoice;
        if (str != null) {
            return str;
        }
        String str2 = this.magicValue;
        return str2 != null ? str2 : this.defaultValue;
    }

    public String getExplicitChoice() {
        return this.userChoice;
    }

    public void addImplementationArgument(ImplementationSpecificArgument implementationSpecificArgument) {
        this.args.add(implementationSpecificArgument);
    }

    public String[] getArgs() {
        ArrayList arrayList = new ArrayList(this.args.size());
        for (ImplementationSpecificArgument implementationSpecificArgument : this.args) {
            String[] parts = implementationSpecificArgument.getParts(getImplementation());
            if (parts != null) {
                for (String str : parts) {
                    arrayList.add(str);
                }
            }
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    public boolean hasBeenSet() {
        return (this.userChoice == null && this.magicValue == null) ? false : true;
    }

    public Path getImplementationClasspath(Project project) {
        if (this.implementationClasspath == null) {
            this.implementationClasspath = new Path(project);
        }
        return this.implementationClasspath;
    }
}
