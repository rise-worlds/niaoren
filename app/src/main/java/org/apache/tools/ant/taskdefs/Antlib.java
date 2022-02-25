package org.apache.tools.ant.taskdefs;

import com.ctetin.expandabletextviewlibrary.ExpandableTextView;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.ComponentHelper;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectHelper;
import org.apache.tools.ant.ProjectHelperRepository;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.TaskContainer;
import org.apache.tools.ant.UnknownElement;
import org.apache.tools.ant.types.resources.URLResource;

/* loaded from: classes2.dex */
public class Antlib extends Task implements TaskContainer {
    public static final String TAG = "antlib";
    private ClassLoader classLoader;
    private String uri = "";
    private List<Object> tasks = new ArrayList();

    public static Antlib createAntlib(Project project, URL url, String str) {
        try {
            URLConnection openConnection = url.openConnection();
            openConnection.setUseCaches(false);
            openConnection.connect();
            ComponentHelper componentHelper = ComponentHelper.getComponentHelper(project);
            componentHelper.enterAntLib(str);
            URLResource uRLResource = new URLResource(url);
            try {
                Object reference = project.getReference("ant.projectHelper");
                ProjectHelper projectHelper = null;
                if (reference instanceof ProjectHelper) {
                    ProjectHelper projectHelper2 = (ProjectHelper) reference;
                    if (projectHelper2.canParseAntlibDescriptor(uRLResource)) {
                        projectHelper = projectHelper2;
                    }
                }
                if (projectHelper == null) {
                    projectHelper = ProjectHelperRepository.getInstance().getProjectHelperForAntlib(uRLResource);
                }
                UnknownElement parseAntlibDescriptor = projectHelper.parseAntlibDescriptor(project, uRLResource);
                if (parseAntlibDescriptor.getTag().equals(TAG)) {
                    Antlib antlib = new Antlib();
                    antlib.setProject(project);
                    antlib.setLocation(parseAntlibDescriptor.getLocation());
                    antlib.setTaskName(TAG);
                    antlib.init();
                    parseAntlibDescriptor.configure(antlib);
                    return antlib;
                }
                throw new BuildException("Unexpected tag " + parseAntlibDescriptor.getTag() + " expecting " + TAG, parseAntlibDescriptor.getLocation());
            } finally {
                componentHelper.exitAntLib();
            }
        } catch (IOException e) {
            throw new BuildException("Unable to find " + url, e);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setURI(String str) {
        this.uri = str;
    }

    private ClassLoader getClassLoader() {
        if (this.classLoader == null) {
            this.classLoader = Antlib.class.getClassLoader();
        }
        return this.classLoader;
    }

    @Override // org.apache.tools.ant.TaskContainer
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    @Override // org.apache.tools.ant.Task
    public void execute() {
        Iterator<Object> it = this.tasks.iterator();
        while (it.hasNext()) {
            UnknownElement unknownElement = (UnknownElement) it.next();
            setLocation(unknownElement.getLocation());
            unknownElement.maybeConfigure();
            Object realThing = unknownElement.getRealThing();
            if (realThing != null) {
                if (realThing instanceof AntlibDefinition) {
                    AntlibDefinition antlibDefinition = (AntlibDefinition) realThing;
                    antlibDefinition.setURI(this.uri);
                    antlibDefinition.setAntlibClassLoader(getClassLoader());
                    antlibDefinition.init();
                    antlibDefinition.execute();
                } else {
                    throw new BuildException("Invalid task in antlib " + unknownElement.getTag() + ExpandableTextView.f6958c + realThing.getClass() + " does not extend org.apache.tools.ant.taskdefs.AntlibDefinition");
                }
            }
        }
    }
}
