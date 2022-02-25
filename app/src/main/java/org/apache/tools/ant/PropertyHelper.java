package org.apache.tools.ant;

import com.tencent.smtt.sdk.TbsListener;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import org.apache.tools.ant.property.GetProperty;
import org.apache.tools.ant.property.NullReturn;
import org.apache.tools.ant.property.ParseNextProperty;
import org.apache.tools.ant.property.ParseProperties;
import org.apache.tools.ant.property.PropertyExpander;

/* loaded from: classes2.dex */
public class PropertyHelper implements GetProperty {
    private PropertyHelper next;
    private Project project;
    private static final PropertyEvaluator TO_STRING = new PropertyEvaluator() { // from class: org.apache.tools.ant.PropertyHelper.1
        private final String PREFIX = "toString:";
        private final int PREFIX_LEN = 9;

        @Override // org.apache.tools.ant.PropertyHelper.PropertyEvaluator
        public Object evaluate(String str, PropertyHelper propertyHelper) {
            Object reference = (!str.startsWith("toString:") || propertyHelper.getProject() == null) ? null : propertyHelper.getProject().getReference(str.substring(this.PREFIX_LEN));
            if (reference == null) {
                return null;
            }
            return reference.toString();
        }
    };
    private static final PropertyExpander DEFAULT_EXPANDER = new PropertyExpander() { // from class: org.apache.tools.ant.PropertyHelper.2
        @Override // org.apache.tools.ant.property.PropertyExpander
        public String parsePropertyName(String str, ParsePosition parsePosition, ParseNextProperty parseNextProperty) {
            int index = parsePosition.getIndex();
            if (str.length() - index < 3 || '$' != str.charAt(index) || '{' != str.charAt(index + 1)) {
                return null;
            }
            int i = index + 2;
            int indexOf = str.indexOf(TbsListener.ErrorCode.DOWNLOAD_THROWABLE, i);
            if (indexOf >= 0) {
                parsePosition.setIndex(indexOf + 1);
                return i == indexOf ? "" : str.substring(i, indexOf);
            }
            throw new BuildException("Syntax error in property: " + str.substring(index));
        }
    };
    private static final PropertyExpander SKIP_DOUBLE_DOLLAR = new PropertyExpander() { // from class: org.apache.tools.ant.PropertyHelper.3
        @Override // org.apache.tools.ant.property.PropertyExpander
        public String parsePropertyName(String str, ParsePosition parsePosition, ParseNextProperty parseNextProperty) {
            int index = parsePosition.getIndex();
            if (str.length() - index < 2 || '$' != str.charAt(index)) {
                return null;
            }
            int i = index + 1;
            if ('$' != str.charAt(i)) {
                return null;
            }
            parsePosition.setIndex(i);
            return null;
        }
    };
    private static final PropertyEvaluator FROM_REF = new PropertyEvaluator() { // from class: org.apache.tools.ant.PropertyHelper.4
        private final String PREFIX = "ant.refid:";
        private final int PREFIX_LEN = 10;

        @Override // org.apache.tools.ant.PropertyHelper.PropertyEvaluator
        public Object evaluate(String str, PropertyHelper propertyHelper) {
            if (!str.startsWith("ant.refid:") || propertyHelper.getProject() == null) {
                return null;
            }
            return propertyHelper.getProject().getReference(str.substring(this.PREFIX_LEN));
        }
    };
    private final Hashtable<Class<? extends Delegate>, List<Delegate>> delegates = new Hashtable<>();
    private Hashtable<String, Object> properties = new Hashtable<>();
    private Hashtable<String, Object> userProperties = new Hashtable<>();
    private Hashtable<String, Object> inheritedProperties = new Hashtable<>();

    /* loaded from: classes2.dex */
    public interface Delegate {
    }

    /* loaded from: classes2.dex */
    public interface PropertyEvaluator extends Delegate {
        Object evaluate(String str, PropertyHelper propertyHelper);
    }

    /* loaded from: classes2.dex */
    public interface PropertySetter extends Delegate {
        boolean set(String str, Object obj, PropertyHelper propertyHelper);

        boolean setNew(String str, Object obj, PropertyHelper propertyHelper);
    }

    protected PropertyHelper() {
        add(FROM_REF);
        add(TO_STRING);
        add(SKIP_DOUBLE_DOLLAR);
        add(DEFAULT_EXPANDER);
    }

    public static Object getProperty(Project project, String str) {
        return getPropertyHelper(project).getProperty(str);
    }

    public static void setProperty(Project project, String str, Object obj) {
        getPropertyHelper(project).setProperty(str, obj, true);
    }

    public static void setNewProperty(Project project, String str, Object obj) {
        getPropertyHelper(project).setNewProperty(str, obj);
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Project getProject() {
        return this.project;
    }

    public void setNext(PropertyHelper propertyHelper) {
        this.next = propertyHelper;
    }

    public PropertyHelper getNext() {
        return this.next;
    }

    public static synchronized PropertyHelper getPropertyHelper(Project project) {
        synchronized (PropertyHelper.class) {
            PropertyHelper propertyHelper = null;
            if (project != null) {
                propertyHelper = (PropertyHelper) project.getReference(MagicNames.REFID_PROPERTY_HELPER);
            }
            if (propertyHelper != null) {
                return propertyHelper;
            }
            PropertyHelper propertyHelper2 = new PropertyHelper();
            propertyHelper2.setProject(project);
            if (project != null) {
                project.addReference(MagicNames.REFID_PROPERTY_HELPER, propertyHelper2);
            }
            return propertyHelper2;
        }
    }

    public Collection<PropertyExpander> getExpanders() {
        return getDelegates(PropertyExpander.class);
    }

    public boolean setPropertyHook(String str, String str2, Object obj, boolean z, boolean z2, boolean z3) {
        return getNext() != null && getNext().setPropertyHook(str, str2, obj, z, z2, z3);
    }

    public Object getPropertyHook(String str, String str2, boolean z) {
        Object propertyHook;
        if (getNext() != null && (propertyHook = getNext().getPropertyHook(str, str2, z)) != null) {
            return propertyHook;
        }
        if (this.project == null || !str2.startsWith("toString:")) {
            return null;
        }
        Object reference = this.project.getReference(str2.substring(9));
        if (reference == null) {
            return null;
        }
        return reference.toString();
    }

    public void parsePropertyString(String str, Vector<String> vector, Vector<String> vector2) throws BuildException {
        parsePropertyStringDefault(str, vector, vector2);
    }

    public String replaceProperties(String str, String str2, Hashtable<String, Object> hashtable) throws BuildException {
        return replaceProperties(str2);
    }

    public String replaceProperties(String str) throws BuildException {
        Object parseProperties = parseProperties(str);
        return (parseProperties == null || (parseProperties instanceof String)) ? (String) parseProperties : parseProperties.toString();
    }

    public Object parseProperties(String str) throws BuildException {
        return new ParseProperties(getProject(), getExpanders(), this).parseProperties(str);
    }

    public boolean containsProperties(String str) {
        return new ParseProperties(getProject(), getExpanders(), this).containsProperties(str);
    }

    public boolean setProperty(String str, String str2, Object obj, boolean z) {
        return setProperty(str2, obj, z);
    }

    public boolean setProperty(String str, Object obj, boolean z) {
        for (PropertySetter propertySetter : getDelegates(PropertySetter.class)) {
            if (propertySetter.set(str, obj, this)) {
                return true;
            }
        }
        synchronized (this) {
            if (this.userProperties.containsKey(str)) {
                if (this.project != null && z) {
                    Project project = this.project;
                    project.log("Override ignored for user property \"" + str + "\"", 3);
                }
                return false;
            }
            if (this.project != null && z) {
                if (this.properties.containsKey(str)) {
                    Project project2 = this.project;
                    project2.log("Overriding previous definition of property \"" + str + "\"", 3);
                }
                Project project3 = this.project;
                project3.log("Setting project property: " + str + " -> " + obj, 4);
            }
            if (!(str == null || obj == null)) {
                this.properties.put(str, obj);
            }
            return true;
        }
    }

    public void setNewProperty(String str, String str2, Object obj) {
        setNewProperty(str2, obj);
    }

    public void setNewProperty(String str, Object obj) {
        for (PropertySetter propertySetter : getDelegates(PropertySetter.class)) {
            if (propertySetter.setNew(str, obj, this)) {
                return;
            }
        }
        synchronized (this) {
            if (this.project == null || !this.properties.containsKey(str)) {
                if (this.project != null) {
                    Project project = this.project;
                    project.log("Setting project property: " + str + " -> " + obj, 4);
                }
                if (!(str == null || obj == null)) {
                    this.properties.put(str, obj);
                }
                return;
            }
            Project project2 = this.project;
            project2.log("Override ignored for property \"" + str + "\"", 3);
        }
    }

    public void setUserProperty(String str, String str2, Object obj) {
        setUserProperty(str2, obj);
    }

    public void setUserProperty(String str, Object obj) {
        Project project = this.project;
        if (project != null) {
            project.log("Setting ro project property: " + str + " -> " + obj, 4);
        }
        synchronized (this) {
            this.userProperties.put(str, obj);
            this.properties.put(str, obj);
        }
    }

    public void setInheritedProperty(String str, String str2, Object obj) {
        setInheritedProperty(str2, obj);
    }

    public void setInheritedProperty(String str, Object obj) {
        Project project = this.project;
        if (project != null) {
            project.log("Setting ro project property: " + str + " -> " + obj, 4);
        }
        synchronized (this) {
            this.inheritedProperties.put(str, obj);
            this.userProperties.put(str, obj);
            this.properties.put(str, obj);
        }
    }

    public Object getProperty(String str, String str2) {
        return getProperty(str2);
    }

    @Override // org.apache.tools.ant.property.GetProperty
    public Object getProperty(String str) {
        if (str == null) {
            return null;
        }
        for (PropertyEvaluator propertyEvaluator : getDelegates(PropertyEvaluator.class)) {
            Object evaluate = propertyEvaluator.evaluate(str, this);
            if (evaluate != null) {
                if (evaluate instanceof NullReturn) {
                    return null;
                }
                return evaluate;
            }
        }
        return this.properties.get(str);
    }

    public Object getUserProperty(String str, String str2) {
        return getUserProperty(str2);
    }

    public Object getUserProperty(String str) {
        if (str == null) {
            return null;
        }
        return this.userProperties.get(str);
    }

    public Hashtable<String, Object> getProperties() {
        Hashtable<String, Object> hashtable;
        synchronized (this.properties) {
            hashtable = new Hashtable<>(this.properties);
        }
        return hashtable;
    }

    public Hashtable<String, Object> getUserProperties() {
        Hashtable<String, Object> hashtable;
        synchronized (this.userProperties) {
            hashtable = new Hashtable<>(this.userProperties);
        }
        return hashtable;
    }

    public Hashtable<String, Object> getInheritedProperties() {
        Hashtable<String, Object> hashtable;
        synchronized (this.inheritedProperties) {
            hashtable = new Hashtable<>(this.inheritedProperties);
        }
        return hashtable;
    }

    protected Hashtable<String, Object> getInternalProperties() {
        return this.properties;
    }

    protected Hashtable<String, Object> getInternalUserProperties() {
        return this.userProperties;
    }

    protected Hashtable<String, Object> getInternalInheritedProperties() {
        return this.inheritedProperties;
    }

    public void copyInheritedProperties(Project project) {
        synchronized (this.inheritedProperties) {
            Enumeration<String> keys = this.inheritedProperties.keys();
            while (keys.hasMoreElements()) {
                String str = keys.nextElement().toString();
                if (project.getUserProperty(str) == null) {
                    project.setInheritedProperty(str, this.inheritedProperties.get(str).toString());
                }
            }
        }
    }

    public void copyUserProperties(Project project) {
        synchronized (this.userProperties) {
            Enumeration<String> keys = this.userProperties.keys();
            while (keys.hasMoreElements()) {
                String nextElement = keys.nextElement();
                if (!this.inheritedProperties.containsKey(nextElement)) {
                    project.setUserProperty(nextElement.toString(), this.userProperties.get(nextElement).toString());
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void parsePropertyStringDefault(String str, Vector<String> vector, Vector<String> vector2) throws BuildException {
        int i = 0;
        while (true) {
            int indexOf = str.indexOf("$", i);
            if (indexOf >= 0) {
                if (indexOf > 0) {
                    vector.addElement(str.substring(i, indexOf));
                }
                if (indexOf == str.length() - 1) {
                    vector.addElement("$");
                    i = indexOf + 1;
                } else {
                    int i2 = indexOf + 1;
                    if (str.charAt(i2) == '{') {
                        int indexOf2 = str.indexOf(TbsListener.ErrorCode.DOWNLOAD_THROWABLE, indexOf);
                        if (indexOf2 >= 0) {
                            String substring = str.substring(indexOf + 2, indexOf2);
                            vector.addElement(null);
                            vector2.addElement(substring);
                            i = indexOf2 + 1;
                        } else {
                            throw new BuildException("Syntax error in property: " + str);
                        }
                    } else if (str.charAt(i2) == '$') {
                        vector.addElement("$");
                        i = indexOf + 2;
                    } else {
                        i = indexOf + 2;
                        vector.addElement(str.substring(indexOf, i));
                    }
                }
            } else if (i < str.length()) {
                vector.addElement(str.substring(i));
                return;
            } else {
                return;
            }
        }
    }

    public void add(Delegate delegate) {
        ArrayList arrayList;
        synchronized (this.delegates) {
            for (Class<? extends Delegate> cls : getDelegateInterfaces(delegate)) {
                List<Delegate> list = this.delegates.get(cls);
                if (list == null) {
                    arrayList = new ArrayList();
                } else {
                    ArrayList arrayList2 = new ArrayList(list);
                    arrayList2.remove(delegate);
                    arrayList = arrayList2;
                }
                arrayList.add(0, delegate);
                this.delegates.put(cls, Collections.unmodifiableList(arrayList));
            }
        }
    }

    protected <D extends Delegate> List<D> getDelegates(Class<D> cls) {
        List<D> list = (List<D>) this.delegates.get(cls);
        return list == null ? Collections.emptyList() : list;
    }

    protected static Set<Class<? extends Delegate>> getDelegateInterfaces(Delegate delegate) {
        HashSet hashSet = new HashSet();
        for (Class<?> cls = delegate.getClass(); cls != null; cls = cls.getSuperclass()) {
            Class<?>[] interfaces = cls.getInterfaces();
            for (int i = 0; i < interfaces.length; i++) {
                if (Delegate.class.isAssignableFrom(interfaces[i])) {
                    hashSet.add(interfaces[i]);
                }
            }
        }
        hashSet.remove(Delegate.class);
        return hashSet;
    }

    public static Boolean toBoolean(Object obj) {
        if (obj instanceof Boolean) {
            return (Boolean) obj;
        }
        if (!(obj instanceof String)) {
            return null;
        }
        String str = (String) obj;
        if (Project.toBoolean(str)) {
            return Boolean.TRUE;
        }
        if ("off".equalsIgnoreCase(str) || "false".equalsIgnoreCase(str) || "no".equalsIgnoreCase(str)) {
            return Boolean.FALSE;
        }
        return null;
    }

    private static boolean nullOrEmpty(Object obj) {
        return obj == null || "".equals(obj);
    }

    private boolean evalAsBooleanOrPropertyName(Object obj) {
        Boolean bool = toBoolean(obj);
        if (bool != null) {
            return bool.booleanValue();
        }
        return getProperty(String.valueOf(obj)) != null;
    }

    public boolean testIfCondition(Object obj) {
        return nullOrEmpty(obj) || evalAsBooleanOrPropertyName(obj);
    }

    public boolean testUnlessCondition(Object obj) {
        return nullOrEmpty(obj) || !evalAsBooleanOrPropertyName(obj);
    }
}
