package org.apache.tools.ant;

import com.ctetin.expandabletextviewlibrary.ExpandableTextView;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.Stack;
import org.apache.tools.ant.launch.Launcher;
import org.apache.tools.ant.taskdefs.Definer;
import org.apache.tools.ant.taskdefs.Property;
import org.apache.tools.ant.taskdefs.Typedef;
import org.apache.tools.ant.util.FileUtils;
import p110z1.Consts;

/* loaded from: classes2.dex */
public class ComponentHelper {
    private static final String ANT_PROPERTY_TASK = "property";
    private static final String BUILD_SYSCLASSPATH_ONLY = "only";
    public static final String COMPONENT_HELPER_REFERENCE = "ant.ComponentHelper";
    private static final String ERROR_NO_TASK_LIST_LOAD = "Can't load default task list";
    private static final String ERROR_NO_TYPE_LIST_LOAD = "Can't load default type list";
    private static Properties[] defaultDefinitions = new Properties[2];
    private ComponentHelper next;
    private Project project;
    private Map<String, List<AntTypeDefinition>> restrictedDefinitions = new HashMap();
    private final Hashtable<String, AntTypeDefinition> antTypeTable = new Hashtable<>();
    private final Hashtable<String, Class<?>> taskClassDefinitions = new Hashtable<>();
    private boolean rebuildTaskClassDefinitions = true;
    private final Hashtable<String, Class<?>> typeClassDefinitions = new Hashtable<>();
    private boolean rebuildTypeClassDefinitions = true;
    private final HashSet<String> checkedNamespaces = new HashSet<>();
    private Stack<String> antLibStack = new Stack<>();
    private String antLibCurrentUri = null;

    public Project getProject() {
        return this.project;
    }

    public static ComponentHelper getComponentHelper(Project project) {
        if (project == null) {
            return null;
        }
        ComponentHelper componentHelper = (ComponentHelper) project.getReference(COMPONENT_HELPER_REFERENCE);
        if (componentHelper != null) {
            return componentHelper;
        }
        ComponentHelper componentHelper2 = new ComponentHelper();
        componentHelper2.setProject(project);
        project.addReference(COMPONENT_HELPER_REFERENCE, componentHelper2);
        return componentHelper2;
    }

    protected ComponentHelper() {
    }

    public void setNext(ComponentHelper componentHelper) {
        this.next = componentHelper;
    }

    public ComponentHelper getNext() {
        return this.next;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    private synchronized Set<String> getCheckedNamespace() {
        return (Set) this.checkedNamespaces.clone();
    }

    private Map<String, List<AntTypeDefinition>> getRestrictedDefinition() {
        ArrayList arrayList;
        HashMap hashMap = new HashMap();
        synchronized (this.restrictedDefinitions) {
            for (Map.Entry<String, List<AntTypeDefinition>> entry : this.restrictedDefinitions.entrySet()) {
                List<AntTypeDefinition> value = entry.getValue();
                synchronized (value) {
                    arrayList = new ArrayList(value);
                }
                hashMap.put(entry.getKey(), arrayList);
            }
        }
        return hashMap;
    }

    public void initSubProject(ComponentHelper componentHelper) {
        Hashtable hashtable = (Hashtable) componentHelper.antTypeTable.clone();
        synchronized (this.antTypeTable) {
            for (AntTypeDefinition antTypeDefinition : hashtable.values()) {
                this.antTypeTable.put(antTypeDefinition.getName(), antTypeDefinition);
            }
        }
        Set<String> checkedNamespace = componentHelper.getCheckedNamespace();
        synchronized (this) {
            this.checkedNamespaces.addAll(checkedNamespace);
        }
        Map<String, List<AntTypeDefinition>> restrictedDefinition = componentHelper.getRestrictedDefinition();
        synchronized (this.restrictedDefinitions) {
            this.restrictedDefinitions.putAll(restrictedDefinition);
        }
    }

    public Object createComponent(UnknownElement unknownElement, String str, String str2) throws BuildException {
        Object createComponent = createComponent(str2);
        if (createComponent instanceof Task) {
            Task task = (Task) createComponent;
            task.setLocation(unknownElement.getLocation());
            task.setTaskType(str2);
            task.setTaskName(unknownElement.getTaskName());
            task.setOwningTarget(unknownElement.getOwningTarget());
            task.init();
        }
        return createComponent;
    }

    public Object createComponent(String str) {
        AntTypeDefinition definition = getDefinition(str);
        if (definition == null) {
            return null;
        }
        return definition.create(this.project);
    }

    public Class<?> getComponentClass(String str) {
        AntTypeDefinition definition = getDefinition(str);
        if (definition == null) {
            return null;
        }
        return definition.getExposedClass(this.project);
    }

    public AntTypeDefinition getDefinition(String str) {
        checkNamespace(str);
        return this.antTypeTable.get(str);
    }

    public void initDefaultDefinitions() {
        initTasks();
        initTypes();
        new DefaultDefinitions(this).execute();
    }

    public void addTaskDefinition(String str, Class<?> cls) {
        checkTaskClass(cls);
        AntTypeDefinition antTypeDefinition = new AntTypeDefinition();
        antTypeDefinition.setName(str);
        antTypeDefinition.setClassLoader(cls.getClassLoader());
        antTypeDefinition.setClass(cls);
        antTypeDefinition.setAdapterClass(TaskAdapter.class);
        antTypeDefinition.setClassName(cls.getName());
        antTypeDefinition.setAdaptToClass(Task.class);
        updateDataTypeDefinition(antTypeDefinition);
    }

    public void checkTaskClass(Class<?> cls) throws BuildException {
        if (!Modifier.isPublic(cls.getModifiers())) {
            String str = cls + " is not public";
            this.project.log(str, 0);
            throw new BuildException(str);
        } else if (!Modifier.isAbstract(cls.getModifiers())) {
            try {
                cls.getConstructor(null);
                if (!Task.class.isAssignableFrom(cls)) {
                    TaskAdapter.checkTaskClass(cls, this.project);
                }
            } catch (NoSuchMethodException unused) {
                String str2 = "No public no-arg constructor in " + cls;
                this.project.log(str2, 0);
                throw new BuildException(str2);
            }
        } else {
            String str3 = cls + " is abstract";
            this.project.log(str3, 0);
            throw new BuildException(str3);
        }
    }

    public Hashtable<String, Class<?>> getTaskDefinitions() {
        synchronized (this.taskClassDefinitions) {
            synchronized (this.antTypeTable) {
                if (this.rebuildTaskClassDefinitions) {
                    this.taskClassDefinitions.clear();
                    for (Map.Entry<String, AntTypeDefinition> entry : this.antTypeTable.entrySet()) {
                        Class<?> exposedClass = entry.getValue().getExposedClass(this.project);
                        if (exposedClass != null && Task.class.isAssignableFrom(exposedClass)) {
                            this.taskClassDefinitions.put(entry.getKey(), entry.getValue().getTypeClass(this.project));
                        }
                    }
                    this.rebuildTaskClassDefinitions = false;
                }
            }
        }
        return this.taskClassDefinitions;
    }

    public Hashtable<String, Class<?>> getDataTypeDefinitions() {
        synchronized (this.typeClassDefinitions) {
            synchronized (this.antTypeTable) {
                if (this.rebuildTypeClassDefinitions) {
                    this.typeClassDefinitions.clear();
                    for (Map.Entry<String, AntTypeDefinition> entry : this.antTypeTable.entrySet()) {
                        Class<?> exposedClass = entry.getValue().getExposedClass(this.project);
                        if (exposedClass != null && !Task.class.isAssignableFrom(exposedClass)) {
                            this.typeClassDefinitions.put(entry.getKey(), entry.getValue().getTypeClass(this.project));
                        }
                    }
                    this.rebuildTypeClassDefinitions = false;
                }
            }
        }
        return this.typeClassDefinitions;
    }

    public List<AntTypeDefinition> getRestrictedDefinitions(String str) {
        List<AntTypeDefinition> list;
        synchronized (this.restrictedDefinitions) {
            list = this.restrictedDefinitions.get(str);
        }
        return list;
    }

    public void addDataTypeDefinition(String str, Class<?> cls) {
        AntTypeDefinition antTypeDefinition = new AntTypeDefinition();
        antTypeDefinition.setName(str);
        antTypeDefinition.setClass(cls);
        updateDataTypeDefinition(antTypeDefinition);
        Project project = this.project;
        project.log(" +User datatype: " + str + "     " + cls.getName(), 4);
    }

    public void addDataTypeDefinition(AntTypeDefinition antTypeDefinition) {
        if (!antTypeDefinition.isRestrict()) {
            updateDataTypeDefinition(antTypeDefinition);
        } else {
            updateRestrictedDefinition(antTypeDefinition);
        }
    }

    public Hashtable<String, AntTypeDefinition> getAntTypeTable() {
        return this.antTypeTable;
    }

    public Task createTask(String str) throws BuildException {
        Task createNewTask = createNewTask(str);
        if (createNewTask != null || !str.equals("property")) {
            return createNewTask;
        }
        addTaskDefinition("property", Property.class);
        return createNewTask(str);
    }

    private Task createNewTask(String str) throws BuildException {
        Object createComponent;
        Class<?> componentClass = getComponentClass(str);
        if (componentClass == null || !Task.class.isAssignableFrom(componentClass) || (createComponent = createComponent(str)) == null) {
            return null;
        }
        if (createComponent instanceof Task) {
            Task task = (Task) createComponent;
            task.setTaskType(str);
            task.setTaskName(str);
            Project project = this.project;
            project.log("   +Task: " + str, 4);
            return task;
        }
        throw new BuildException("Expected a Task from '" + str + "' but got an instance of " + createComponent.getClass().getName() + " instead");
    }

    public Object createDataType(String str) throws BuildException {
        return createComponent(str);
    }

    public String getElementName(Object obj) {
        return getElementName(obj, false);
    }

    public String getElementName(Object obj, boolean z) {
        Class<?> cls = obj.getClass();
        String name = cls.getName();
        synchronized (this.antTypeTable) {
            for (AntTypeDefinition antTypeDefinition : this.antTypeTable.values()) {
                if (name.equals(antTypeDefinition.getClassName()) && cls == antTypeDefinition.getExposedClass(this.project)) {
                    String name2 = antTypeDefinition.getName();
                    if (!z) {
                        name2 = "The <" + name2 + "> type";
                    }
                    return name2;
                }
            }
            return getUnmappedElementName(obj.getClass(), z);
        }
    }

    public static String getElementName(Project project, Object obj, boolean z) {
        if (project == null) {
            project = Project.getProject(obj);
        }
        return project == null ? getUnmappedElementName(obj.getClass(), z) : getComponentHelper(project).getElementName(obj, z);
    }

    private static String getUnmappedElementName(Class<?> cls, boolean z) {
        if (!z) {
            return cls.toString();
        }
        String name = cls.getName();
        return name.substring(name.lastIndexOf(46) + 1);
    }

    private boolean validDefinition(AntTypeDefinition antTypeDefinition) {
        return (antTypeDefinition.getTypeClass(this.project) == null || antTypeDefinition.getExposedClass(this.project) == null) ? false : true;
    }

    private boolean sameDefinition(AntTypeDefinition antTypeDefinition, AntTypeDefinition antTypeDefinition2) {
        boolean validDefinition = validDefinition(antTypeDefinition);
        return (validDefinition == validDefinition(antTypeDefinition2)) && (!validDefinition || antTypeDefinition.sameDefinition(antTypeDefinition2, this.project));
    }

    private void updateRestrictedDefinition(AntTypeDefinition antTypeDefinition) {
        List<AntTypeDefinition> list;
        String name = antTypeDefinition.getName();
        synchronized (this.restrictedDefinitions) {
            list = this.restrictedDefinitions.get(name);
            if (list == null) {
                list = new ArrayList<>();
                this.restrictedDefinitions.put(name, list);
            }
        }
        synchronized (list) {
            Iterator<AntTypeDefinition> it = list.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (it.next().getClassName().equals(antTypeDefinition.getClassName())) {
                        it.remove();
                        break;
                    }
                } else {
                    break;
                }
            }
            list.add(antTypeDefinition);
        }
    }

    private void updateDataTypeDefinition(AntTypeDefinition antTypeDefinition) {
        String name = antTypeDefinition.getName();
        synchronized (this.antTypeTable) {
            int i = 1;
            this.rebuildTaskClassDefinitions = true;
            this.rebuildTypeClassDefinitions = true;
            AntTypeDefinition antTypeDefinition2 = this.antTypeTable.get(name);
            if (antTypeDefinition2 != null) {
                if (!sameDefinition(antTypeDefinition, antTypeDefinition2)) {
                    Class<?> exposedClass = antTypeDefinition2.getExposedClass(this.project);
                    boolean z = exposedClass != null && Task.class.isAssignableFrom(exposedClass);
                    Project project = this.project;
                    StringBuilder sb = new StringBuilder();
                    sb.append("Trying to override old definition of ");
                    sb.append(z ? "task " : "datatype ");
                    sb.append(name);
                    String sb2 = sb.toString();
                    if (antTypeDefinition.similarDefinition(antTypeDefinition2, this.project)) {
                        i = 3;
                    }
                    project.log(sb2, i);
                } else {
                    return;
                }
            }
            Project project2 = this.project;
            project2.log(" +Datatype " + name + ExpandableTextView.f6958c + antTypeDefinition.getClassName(), 4);
            this.antTypeTable.put(name, antTypeDefinition);
        }
    }

    public void enterAntLib(String str) {
        this.antLibCurrentUri = str;
        this.antLibStack.push(str);
    }

    public String getCurrentAntlibUri() {
        return this.antLibCurrentUri;
    }

    public void exitAntLib() {
        this.antLibStack.pop();
        this.antLibCurrentUri = this.antLibStack.size() == 0 ? null : this.antLibStack.peek();
    }

    private void initTasks() {
        ClassLoader classLoader = getClassLoader(null);
        Properties defaultDefinitions2 = getDefaultDefinitions(false);
        Enumeration<?> propertyNames = defaultDefinitions2.propertyNames();
        while (propertyNames.hasMoreElements()) {
            String str = (String) propertyNames.nextElement();
            String property = defaultDefinitions2.getProperty(str);
            AntTypeDefinition antTypeDefinition = new AntTypeDefinition();
            antTypeDefinition.setName(str);
            antTypeDefinition.setClassName(property);
            antTypeDefinition.setClassLoader(classLoader);
            antTypeDefinition.setAdaptToClass(Task.class);
            antTypeDefinition.setAdapterClass(TaskAdapter.class);
            this.antTypeTable.put(str, antTypeDefinition);
        }
    }

    private ClassLoader getClassLoader(ClassLoader classLoader) {
        return (this.project.getCoreLoader() == null || BUILD_SYSCLASSPATH_ONLY.equals(this.project.getProperty(MagicNames.BUILD_SYSCLASSPATH))) ? classLoader : this.project.getCoreLoader();
    }

    private static synchronized Properties getDefaultDefinitions(boolean z) throws BuildException {
        Properties properties;
        synchronized (ComponentHelper.class) {
            char c = z ? (char) 1 : (char) 0;
            if (defaultDefinitions[c] == null) {
                String str = z ? MagicNames.TYPEDEFS_PROPERTIES_RESOURCE : MagicNames.TASKDEF_PROPERTIES_RESOURCE;
                String str2 = z ? ERROR_NO_TYPE_LIST_LOAD : ERROR_NO_TASK_LIST_LOAD;
                try {
                    InputStream resourceAsStream = ComponentHelper.class.getResourceAsStream(str);
                    if (resourceAsStream != null) {
                        Properties properties2 = new Properties();
                        properties2.load(resourceAsStream);
                        defaultDefinitions[c] = properties2;
                        FileUtils.close(resourceAsStream);
                    } else {
                        throw new BuildException(str2);
                    }
                } catch (IOException e) {
                    throw new BuildException(str2, e);
                }
            }
            properties = defaultDefinitions[c];
        }
        return properties;
    }

    private void initTypes() {
        ClassLoader classLoader = getClassLoader(null);
        Properties defaultDefinitions2 = getDefaultDefinitions(true);
        Enumeration<?> propertyNames = defaultDefinitions2.propertyNames();
        while (propertyNames.hasMoreElements()) {
            String str = (String) propertyNames.nextElement();
            String property = defaultDefinitions2.getProperty(str);
            AntTypeDefinition antTypeDefinition = new AntTypeDefinition();
            antTypeDefinition.setName(str);
            antTypeDefinition.setClassName(property);
            antTypeDefinition.setClassLoader(classLoader);
            this.antTypeTable.put(str, antTypeDefinition);
        }
    }

    private synchronized void checkNamespace(String str) {
        String extractUriFromComponentName = ProjectHelper.extractUriFromComponentName(str);
        if ("".equals(extractUriFromComponentName)) {
            extractUriFromComponentName = ProjectHelper.ANT_CORE_URI;
        }
        if (extractUriFromComponentName.startsWith("antlib:")) {
            if (!this.checkedNamespaces.contains(extractUriFromComponentName)) {
                this.checkedNamespaces.add(extractUriFromComponentName);
                if (this.antTypeTable.size() == 0) {
                    initDefaultDefinitions();
                }
                Typedef typedef = new Typedef();
                typedef.setProject(this.project);
                typedef.init();
                typedef.setURI(extractUriFromComponentName);
                typedef.setTaskName(extractUriFromComponentName);
                typedef.setResource(Definer.makeResourceFromURI(extractUriFromComponentName));
                typedef.setOnError(new Definer.OnError(Definer.OnError.POLICY_IGNORE));
                typedef.execute();
            }
        }
    }

    public String diagnoseCreationFailure(String str, String str2) {
        boolean z;
        String str3;
        boolean z2;
        boolean z3;
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        printWriter.println("Problem: failed to create " + str2 + ExpandableTextView.f6958c + str);
        File file = new File(System.getProperty("user.home"), Launcher.USER_LIBDIR);
        String property = System.getProperty(MagicNames.ANT_HOME);
        boolean z4 = true;
        if (property != null) {
            str3 = new File(property, "lib").getAbsolutePath();
            z = false;
        } else {
            str3 = "ANT_HOME" + File.separatorChar + "lib";
            z = true;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("        -");
        stringBuffer.append(str3);
        stringBuffer.append('\n');
        if (z) {
            stringBuffer.append("        -");
            stringBuffer.append("the IDE Ant configuration dialogs");
        } else {
            stringBuffer.append("        -");
            stringBuffer.append(file);
            stringBuffer.append('\n');
            stringBuffer.append("        -");
            stringBuffer.append("a directory added on the command line with the -lib argument");
        }
        String stringBuffer2 = stringBuffer.toString();
        AntTypeDefinition definition = getDefinition(str);
        if (definition == null) {
            printUnknownDefinition(printWriter, str, stringBuffer2);
        } else {
            String className = definition.getClassName();
            boolean startsWith = className.startsWith("org.apache.tools.ant.");
            boolean startsWith2 = className.startsWith("org.apache.tools.ant.taskdefs.optional") | className.startsWith("org.apache.tools.ant.types.optional");
            Class<?> cls = null;
            try {
                cls = definition.innerGetTypeClass();
                z3 = false;
                z2 = false;
            } catch (ClassNotFoundException unused) {
                z2 = !startsWith2;
                printClassNotFound(printWriter, className, startsWith2, stringBuffer2);
                z3 = true;
            } catch (NoClassDefFoundError e) {
                printNotLoadDependentClass(printWriter, startsWith2, e, stringBuffer2);
                z3 = true;
                z2 = false;
            }
            if (cls != null) {
                try {
                    definition.innerCreateAndSet(cls, this.project);
                    printWriter.println("The component could be instantiated.");
                } catch (IllegalAccessException unused2) {
                    printWriter.println("Cause: The constructor for " + className + " is private and cannot be invoked.");
                } catch (InstantiationException unused3) {
                    printWriter.println("Cause: The class " + className + " is abstract and cannot be instantiated.");
                } catch (NoClassDefFoundError e2) {
                    printWriter.println("Cause:  A class needed by class " + className + " cannot be found: ");
                    StringBuilder sb = new StringBuilder();
                    sb.append("       ");
                    sb.append(e2.getMessage());
                    printWriter.println(sb.toString());
                    printWriter.println("Action: Determine what extra JAR files are needed, and place them in:");
                    printWriter.println(stringBuffer2);
                    z4 = false;
                    z3 = true;
                } catch (NoSuchMethodException unused4) {
                    printWriter.println("Cause: The class " + className + " has no compatible constructor.");
                } catch (InvocationTargetException e3) {
                    Throwable targetException = e3.getTargetException();
                    printWriter.println("Cause: The constructor threw the exception");
                    printWriter.println(targetException.toString());
                    targetException.printStackTrace(printWriter);
                }
            }
            z4 = false;
            printWriter.println();
            printWriter.println("Do not panic, this is a common problem.");
            if (z2) {
                printWriter.println("It may just be a typographical error in the build file or the task/type declaration.");
            }
            if (z3) {
                printWriter.println("The commonest cause is a missing JAR.");
            }
            if (z4) {
                printWriter.println("This is quite a low level problem, which may need consultation with the author of the task.");
                if (startsWith) {
                    printWriter.println("This may be the Ant team. Please file a defect or contact the developer team.");
                } else {
                    printWriter.println("This does not appear to be a task bundled with Ant.");
                    printWriter.println("Please take it up with the supplier of the third-party " + str2 + Consts.f23430h);
                    printWriter.println("If you have written it yourself, you probably have a bug to fix.");
                }
            } else {
                printWriter.println();
                printWriter.println("This is not a bug; it is a configuration problem");
            }
        }
        printWriter.flush();
        printWriter.close();
        return stringWriter.toString();
    }

    private void printUnknownDefinition(PrintWriter printWriter, String str, String str2) {
        boolean startsWith = str.startsWith("antlib:");
        String extractUriFromComponentName = ProjectHelper.extractUriFromComponentName(str);
        printWriter.println("Cause: The name is undefined.");
        printWriter.println("Action: Check the spelling.");
        printWriter.println("Action: Check that any custom tasks/types have been declared.");
        printWriter.println("Action: Check that any <presetdef>/<macrodef> declarations have taken place.");
        if (extractUriFromComponentName.length() > 0) {
            List<AntTypeDefinition> findTypeMatches = findTypeMatches(extractUriFromComponentName);
            if (findTypeMatches.size() > 0) {
                printWriter.println();
                printWriter.println("The definitions in the namespace " + extractUriFromComponentName + " are:");
                for (AntTypeDefinition antTypeDefinition : findTypeMatches) {
                    String extractNameFromComponentName = ProjectHelper.extractNameFromComponentName(antTypeDefinition.getName());
                    printWriter.println("    " + extractNameFromComponentName);
                }
                return;
            }
            printWriter.println("No types or tasks have been defined in this namespace yet");
            if (startsWith) {
                printWriter.println();
                printWriter.println("This appears to be an antlib declaration. ");
                printWriter.println("Action: Check that the implementing library exists in one of:");
                printWriter.println(str2);
            }
        }
    }

    private void printClassNotFound(PrintWriter printWriter, String str, boolean z, String str2) {
        printWriter.println("Cause: the class " + str + " was not found.");
        if (z) {
            printWriter.println("        This looks like one of Ant's optional components.");
            printWriter.println("Action: Check that the appropriate optional JAR exists in");
            printWriter.println(str2);
            return;
        }
        printWriter.println("Action: Check that the component has been correctly declared");
        printWriter.println("        and that the implementing JAR is in one of:");
        printWriter.println(str2);
    }

    private void printNotLoadDependentClass(PrintWriter printWriter, boolean z, NoClassDefFoundError noClassDefFoundError, String str) {
        printWriter.println("Cause: Could not load a dependent class " + noClassDefFoundError.getMessage());
        if (z) {
            printWriter.println("       It is not enough to have Ant's optional JARs");
            printWriter.println("       you need the JAR files that the optional tasks depend upon.");
            printWriter.println("       Ant's optional task dependencies are listed in the manual.");
        } else {
            printWriter.println("       This class may be in a separate JAR that is not installed.");
        }
        printWriter.println("Action: Determine what extra JAR files are needed, and place them in one of:");
        printWriter.println(str);
    }

    private List<AntTypeDefinition> findTypeMatches(String str) {
        ArrayList arrayList = new ArrayList();
        synchronized (this.antTypeTable) {
            for (AntTypeDefinition antTypeDefinition : this.antTypeTable.values()) {
                if (antTypeDefinition.getName().startsWith(str)) {
                    arrayList.add(antTypeDefinition);
                }
            }
        }
        return arrayList;
    }
}
