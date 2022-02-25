package org.apache.tools.ant;

import com.ctetin.expandabletextviewlibrary.ExpandableTextView;
import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.apache.tools.ant.taskdefs.PreSetDef;
import org.apache.tools.ant.types.EnumeratedAttribute;
import org.apache.tools.ant.types.Resource;
import org.apache.tools.ant.types.resources.FileProvider;
import org.apache.tools.ant.types.resources.FileResource;
import org.apache.tools.ant.util.StringUtils;

/* loaded from: classes2.dex */
public final class IntrospectionHelper {
    private static final String ELLIPSIS = "...";
    private static final int MAX_REPORT_NESTED_TEXT = 20;
    protected static final String NOT_SUPPORTED_CHILD_POSTFIX = "\" element.";
    protected static final String NOT_SUPPORTED_CHILD_PREFIX = " doesn't support the nested \"";
    private final Method addText;
    private final Class<?> bean;
    private static final Map<String, IntrospectionHelper> HELPERS = new Hashtable();
    private static final Map<Class<?>, Class<?>> PRIMITIVE_TYPE_MAP = new HashMap(8);
    private final Hashtable<String, Class<?>> attributeTypes = new Hashtable<>();
    private final Hashtable<String, AttributeSetter> attributeSetters = new Hashtable<>();
    private final Hashtable<String, Class<?>> nestedTypes = new Hashtable<>();
    private final Hashtable<String, NestedCreator> nestedCreators = new Hashtable<>();
    private final List<Method> addTypeMethods = new ArrayList();

    static {
        Class<?>[] clsArr = {Boolean.TYPE, Byte.TYPE, Character.TYPE, Short.TYPE, Integer.TYPE, Long.TYPE, Float.TYPE, Double.TYPE};
        Class<?>[] clsArr2 = {Boolean.class, Byte.class, Character.class, Short.class, Integer.class, Long.class, Float.class, Double.class};
        for (int i = 0; i < clsArr.length; i++) {
            PRIMITIVE_TYPE_MAP.put(clsArr[i], clsArr2[i]);
        }
    }

    private IntrospectionHelper(Class<?> cls) {
        Constructor<?> constructor;
        Constructor<?> constructor2;
        AttributeSetter createAttributeSetter;
        this.bean = cls;
        Method[] methods = cls.getMethods();
        Method method = null;
        for (int i = 0; i < methods.length; i++) {
            Method method2 = methods[i];
            String name = method2.getName();
            Class<?> returnType = method2.getReturnType();
            Class<?>[] parameterTypes = method2.getParameterTypes();
            if (parameterTypes.length == 1 && Void.TYPE.equals(returnType) && ("add".equals(name) || "addConfigured".equals(name))) {
                insertAddTypeMethod(method2);
            } else if ((!ProjectComponent.class.isAssignableFrom(cls) || parameterTypes.length != 1 || !isHiddenSetMethod(name, parameterTypes[0])) && (!isContainer() || parameterTypes.length != 1 || !"addTask".equals(name) || !Task.class.equals(parameterTypes[0]))) {
                if ("addText".equals(name) && Void.TYPE.equals(returnType) && parameterTypes.length == 1 && String.class.equals(parameterTypes[0])) {
                    method = methods[i];
                } else if (name.startsWith("set") && Void.TYPE.equals(returnType) && parameterTypes.length == 1 && !parameterTypes[0].isArray()) {
                    String propertyName = getPropertyName(name, "set");
                    AttributeSetter attributeSetter = this.attributeSetters.get(propertyName);
                    if ((attributeSetter == null || (!String.class.equals(parameterTypes[0]) && (!File.class.equals(parameterTypes[0]) || (!Resource.class.equals(attributeSetter.type) && !FileProvider.class.equals(attributeSetter.type))))) && (createAttributeSetter = createAttributeSetter(method2, parameterTypes[0], propertyName)) != null) {
                        this.attributeTypes.put(propertyName, parameterTypes[0]);
                        this.attributeSetters.put(propertyName, createAttributeSetter);
                    }
                } else if (name.startsWith("create") && !returnType.isArray() && !returnType.isPrimitive() && parameterTypes.length == 0) {
                    String propertyName2 = getPropertyName(name, "create");
                    if (this.nestedCreators.get(propertyName2) == null) {
                        this.nestedTypes.put(propertyName2, returnType);
                        this.nestedCreators.put(propertyName2, new CreateNestedCreator(method2));
                    }
                } else if (name.startsWith("addConfigured") && Void.TYPE.equals(returnType) && parameterTypes.length == 1 && !String.class.equals(parameterTypes[0]) && !parameterTypes[0].isArray() && !parameterTypes[0].isPrimitive()) {
                    try {
                        try {
                            constructor2 = parameterTypes[0].getConstructor(new Class[0]);
                        } catch (NoSuchMethodException unused) {
                            constructor2 = parameterTypes[0].getConstructor(Project.class);
                        }
                        String propertyName3 = getPropertyName(name, "addConfigured");
                        this.nestedTypes.put(propertyName3, parameterTypes[0]);
                        this.nestedCreators.put(propertyName3, new AddNestedCreator(method2, constructor2, 2));
                    } catch (NoSuchMethodException unused2) {
                    }
                } else if (name.startsWith("add") && Void.TYPE.equals(returnType) && parameterTypes.length == 1 && !String.class.equals(parameterTypes[0]) && !parameterTypes[0].isArray() && !parameterTypes[0].isPrimitive()) {
                    try {
                        constructor = parameterTypes[0].getConstructor(new Class[0]);
                    } catch (NoSuchMethodException unused3) {
                        constructor = parameterTypes[0].getConstructor(Project.class);
                    }
                    String propertyName4 = getPropertyName(name, "add");
                    if (this.nestedTypes.get(propertyName4) == null) {
                        this.nestedTypes.put(propertyName4, parameterTypes[0]);
                        this.nestedCreators.put(propertyName4, new AddNestedCreator(method2, constructor, 1));
                    }
                }
            }
        }
        this.addText = method;
    }

    private boolean isHiddenSetMethod(String str, Class<?> cls) {
        if (!"setLocation".equals(str) || !Location.class.equals(cls)) {
            return "setTaskType".equals(str) && String.class.equals(cls);
        }
        return true;
    }

    public static synchronized IntrospectionHelper getHelper(Class<?> cls) {
        IntrospectionHelper helper;
        synchronized (IntrospectionHelper.class) {
            helper = getHelper(null, cls);
        }
        return helper;
    }

    public static synchronized IntrospectionHelper getHelper(Project project, Class<?> cls) {
        IntrospectionHelper introspectionHelper;
        synchronized (IntrospectionHelper.class) {
            introspectionHelper = HELPERS.get(cls.getName());
            if (introspectionHelper == null || introspectionHelper.bean != cls) {
                introspectionHelper = new IntrospectionHelper(cls);
                if (project != null) {
                    HELPERS.put(cls.getName(), introspectionHelper);
                }
            }
        }
        return introspectionHelper;
    }

    public void setAttribute(Project project, Object obj, String str, Object obj2) throws BuildException {
        String str2;
        AttributeSetter attributeSetter = this.attributeSetters.get(str.toLowerCase(Locale.ENGLISH));
        if (attributeSetter != null || obj2 == null) {
            try {
                attributeSetter.setObject(project, obj, obj2);
            } catch (IllegalAccessException e) {
                throw new BuildException(e);
            } catch (InvocationTargetException e2) {
                throw extractBuildException(e2);
            }
        } else if (obj instanceof DynamicAttributeNS) {
            DynamicAttributeNS dynamicAttributeNS = (DynamicAttributeNS) obj;
            String extractUriFromComponentName = ProjectHelper.extractUriFromComponentName(ProjectHelper.extractUriFromComponentName(str));
            String extractNameFromComponentName = ProjectHelper.extractNameFromComponentName(str);
            if ("".equals(extractUriFromComponentName)) {
                str2 = extractNameFromComponentName;
            } else {
                str2 = extractUriFromComponentName + ":" + extractNameFromComponentName;
            }
            dynamicAttributeNS.setDynamicAttribute(extractUriFromComponentName, extractNameFromComponentName, str2, obj2.toString());
        } else if (obj instanceof DynamicObjectAttribute) {
            ((DynamicObjectAttribute) obj).setDynamicAttribute(str.toLowerCase(Locale.ENGLISH), obj2);
        } else if (obj instanceof DynamicAttribute) {
            ((DynamicAttribute) obj).setDynamicAttribute(str.toLowerCase(Locale.ENGLISH), obj2.toString());
        } else if (str.indexOf(58) < 0) {
            throw new UnsupportedAttributeException(getElementName(project, obj) + " doesn't support the \"" + str + "\" attribute.", str);
        }
    }

    public void setAttribute(Project project, Object obj, String str, String str2) throws BuildException {
        setAttribute(project, obj, str, (Object) str2);
    }

    public void addText(Project project, Object obj, String str) throws BuildException {
        Method method = this.addText;
        if (method == null) {
            String trim = str.trim();
            if (trim.length() != 0) {
                throw new BuildException(project.getElementName(obj) + " doesn't support nested text data (\"" + condenseText(trim) + "\").");
            }
            return;
        }
        try {
            method.invoke(obj, str);
        } catch (IllegalAccessException e) {
            throw new BuildException(e);
        } catch (InvocationTargetException e2) {
            throw extractBuildException(e2);
        }
    }

    public void throwNotSupported(Project project, Object obj, String str) {
        throw new UnsupportedElementException(project.getElementName(obj) + NOT_SUPPORTED_CHILD_PREFIX + str + NOT_SUPPORTED_CHILD_POSTFIX, str);
    }

    private NestedCreator getNestedCreator(Project project, String str, Object obj, String str2, UnknownElement unknownElement) throws BuildException {
        String extractUriFromComponentName = ProjectHelper.extractUriFromComponentName(str2);
        String extractNameFromComponentName = ProjectHelper.extractNameFromComponentName(str2);
        if (extractUriFromComponentName.equals(ProjectHelper.ANT_CORE_URI)) {
            extractUriFromComponentName = "";
        }
        if (str.equals(ProjectHelper.ANT_CORE_URI)) {
            str = "";
        }
        NestedCreator nestedCreator = (extractUriFromComponentName.equals(str) || extractUriFromComponentName.length() == 0) ? this.nestedCreators.get(extractNameFromComponentName.toLowerCase(Locale.ENGLISH)) : null;
        if (nestedCreator == null) {
            nestedCreator = createAddTypeCreator(project, obj, str2);
        }
        if (nestedCreator == null && ((obj instanceof DynamicElementNS) || (obj instanceof DynamicElement))) {
            final Object createDynamicElement = createDynamicElement(obj, unknownElement == null ? "" : unknownElement.getNamespace(), extractNameFromComponentName, unknownElement == null ? extractNameFromComponentName : unknownElement.getQName());
            if (createDynamicElement != null) {
                nestedCreator = new NestedCreator(null) { // from class: org.apache.tools.ant.IntrospectionHelper.1
                    @Override // org.apache.tools.ant.IntrospectionHelper.NestedCreator
                    Object create(Project project2, Object obj2, Object obj3) {
                        return createDynamicElement;
                    }
                };
            }
        }
        if (nestedCreator == null) {
            throwNotSupported(project, obj, str2);
        }
        return nestedCreator;
    }

    private Object createDynamicElement(Object obj, String str, String str2, String str3) {
        Object createDynamicElement = obj instanceof DynamicElementNS ? ((DynamicElementNS) obj).createDynamicElement(str, str2, str3) : null;
        return (createDynamicElement != null || !(obj instanceof DynamicElement)) ? createDynamicElement : ((DynamicElement) obj).createDynamicElement(str2.toLowerCase(Locale.ENGLISH));
    }

    @Deprecated
    public Object createElement(Project project, Object obj, String str) throws BuildException {
        try {
            Object create = getNestedCreator(project, "", obj, str, null).create(project, obj, null);
            if (project != null) {
                project.setProjectReference(create);
            }
            return create;
        } catch (IllegalAccessException e) {
            throw new BuildException(e);
        } catch (InstantiationException e2) {
            throw new BuildException(e2);
        } catch (InvocationTargetException e3) {
            throw extractBuildException(e3);
        }
    }

    public Creator getElementCreator(Project project, String str, Object obj, String str2, UnknownElement unknownElement) {
        return new Creator(project, obj, getNestedCreator(project, str, obj, str2, unknownElement));
    }

    public boolean isDynamic() {
        return DynamicElement.class.isAssignableFrom(this.bean) || DynamicElementNS.class.isAssignableFrom(this.bean);
    }

    public boolean isContainer() {
        return TaskContainer.class.isAssignableFrom(this.bean);
    }

    public boolean supportsNestedElement(String str) {
        return supportsNestedElement("", str);
    }

    public boolean supportsNestedElement(String str, String str2) {
        if (isDynamic() || this.addTypeMethods.size() > 0) {
            return true;
        }
        return supportsReflectElement(str, str2);
    }

    public boolean supportsNestedElement(String str, String str2, Project project, Object obj) {
        return (this.addTypeMethods.size() > 0 && createAddTypeCreator(project, obj, str2) != null) || isDynamic() || supportsReflectElement(str, str2);
    }

    public boolean supportsReflectElement(String str, String str2) {
        if (!this.nestedCreators.containsKey(ProjectHelper.extractNameFromComponentName(str2).toLowerCase(Locale.ENGLISH))) {
            return false;
        }
        String extractUriFromComponentName = ProjectHelper.extractUriFromComponentName(str2);
        if (extractUriFromComponentName.equals(ProjectHelper.ANT_CORE_URI)) {
            extractUriFromComponentName = "";
        }
        if ("".equals(extractUriFromComponentName)) {
            return true;
        }
        if (str.equals(ProjectHelper.ANT_CORE_URI)) {
            str = "";
        }
        return extractUriFromComponentName.equals(str);
    }

    public void storeElement(Project project, Object obj, Object obj2, String str) throws BuildException {
        NestedCreator nestedCreator;
        if (str != null && (nestedCreator = this.nestedCreators.get(str.toLowerCase(Locale.ENGLISH))) != null) {
            try {
                nestedCreator.store(obj, obj2);
            } catch (IllegalAccessException e) {
                throw new BuildException(e);
            } catch (InstantiationException e2) {
                throw new BuildException(e2);
            } catch (InvocationTargetException e3) {
                throw extractBuildException(e3);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static BuildException extractBuildException(InvocationTargetException invocationTargetException) {
        Throwable targetException = invocationTargetException.getTargetException();
        if (targetException instanceof BuildException) {
            return (BuildException) targetException;
        }
        return new BuildException(targetException);
    }

    public Class<?> getElementType(String str) throws BuildException {
        Class<?> cls = this.nestedTypes.get(str);
        if (cls != null) {
            return cls;
        }
        throw new UnsupportedElementException("Class " + this.bean.getName() + NOT_SUPPORTED_CHILD_PREFIX + str + NOT_SUPPORTED_CHILD_POSTFIX, str);
    }

    public Class<?> getAttributeType(String str) throws BuildException {
        Class<?> cls = this.attributeTypes.get(str);
        if (cls != null) {
            return cls;
        }
        throw new UnsupportedAttributeException("Class " + this.bean.getName() + " doesn't support the \"" + str + "\" attribute.", str);
    }

    public Method getAddTextMethod() throws BuildException {
        if (supportsCharacters()) {
            return this.addText;
        }
        throw new BuildException("Class " + this.bean.getName() + " doesn't support nested text data.");
    }

    public Method getElementMethod(String str) throws BuildException {
        NestedCreator nestedCreator = this.nestedCreators.get(str);
        if (nestedCreator != null) {
            return nestedCreator.method;
        }
        throw new UnsupportedElementException("Class " + this.bean.getName() + NOT_SUPPORTED_CHILD_PREFIX + str + NOT_SUPPORTED_CHILD_POSTFIX, str);
    }

    public Method getAttributeMethod(String str) throws BuildException {
        AttributeSetter attributeSetter = this.attributeSetters.get(str);
        if (attributeSetter != null) {
            return attributeSetter.method;
        }
        throw new UnsupportedAttributeException("Class " + this.bean.getName() + " doesn't support the \"" + str + "\" attribute.", str);
    }

    public boolean supportsCharacters() {
        return this.addText != null;
    }

    public Enumeration<String> getAttributes() {
        return this.attributeSetters.keys();
    }

    public Map<String, Class<?>> getAttributeMap() {
        return this.attributeTypes.isEmpty() ? Collections.emptyMap() : Collections.unmodifiableMap(this.attributeTypes);
    }

    public Enumeration<String> getNestedElements() {
        return this.nestedTypes.keys();
    }

    public Map<String, Class<?>> getNestedElementMap() {
        return this.nestedTypes.isEmpty() ? Collections.emptyMap() : Collections.unmodifiableMap(this.nestedTypes);
    }

    public List<Method> getExtensionPoints() {
        return this.addTypeMethods.isEmpty() ? Collections.emptyList() : Collections.unmodifiableList(this.addTypeMethods);
    }

    private AttributeSetter createAttributeSetter(final Method method, Class<?> cls, final String str) {
        final Constructor<?> constructor;
        final boolean z;
        final Class<?> cls2 = PRIMITIVE_TYPE_MAP.containsKey(cls) ? PRIMITIVE_TYPE_MAP.get(cls) : cls;
        if (Object.class == cls2) {
            return new AttributeSetter(method, cls) { // from class: org.apache.tools.ant.IntrospectionHelper.2
                @Override // org.apache.tools.ant.IntrospectionHelper.AttributeSetter
                public void set(Project project, Object obj, String str2) throws InvocationTargetException, IllegalAccessException {
                    throw new BuildException("Internal ant problem - this should not get called");
                }
            };
        }
        if (String.class.equals(cls2)) {
            return new AttributeSetter(method, cls) { // from class: org.apache.tools.ant.IntrospectionHelper.3
                @Override // org.apache.tools.ant.IntrospectionHelper.AttributeSetter
                public void set(Project project, Object obj, String str2) throws InvocationTargetException, IllegalAccessException {
                    method.invoke(obj, str2);
                }
            };
        }
        if (Character.class.equals(cls2)) {
            return new AttributeSetter(method, cls) { // from class: org.apache.tools.ant.IntrospectionHelper.4
                @Override // org.apache.tools.ant.IntrospectionHelper.AttributeSetter
                public void set(Project project, Object obj, String str2) throws InvocationTargetException, IllegalAccessException {
                    if (str2.length() != 0) {
                        method.invoke(obj, new Character(str2.charAt(0)));
                        return;
                    }
                    throw new BuildException("The value \"\" is not a legal value for attribute \"" + str + "\"");
                }
            };
        }
        if (Boolean.class.equals(cls2)) {
            return new AttributeSetter(method, cls) { // from class: org.apache.tools.ant.IntrospectionHelper.5
                @Override // org.apache.tools.ant.IntrospectionHelper.AttributeSetter
                public void set(Project project, Object obj, String str2) throws InvocationTargetException, IllegalAccessException {
                    Method method2 = method;
                    Boolean[] boolArr = new Boolean[1];
                    boolArr[0] = Project.toBoolean(str2) ? Boolean.TRUE : Boolean.FALSE;
                    method2.invoke(obj, boolArr);
                }
            };
        }
        if (Class.class.equals(cls2)) {
            return new AttributeSetter(method, cls) { // from class: org.apache.tools.ant.IntrospectionHelper.6
                @Override // org.apache.tools.ant.IntrospectionHelper.AttributeSetter
                public void set(Project project, Object obj, String str2) throws InvocationTargetException, IllegalAccessException, BuildException {
                    try {
                        method.invoke(obj, Class.forName(str2));
                    } catch (ClassNotFoundException e) {
                        throw new BuildException(e);
                    }
                }
            };
        }
        if (File.class.equals(cls2)) {
            return new AttributeSetter(method, cls) { // from class: org.apache.tools.ant.IntrospectionHelper.7
                @Override // org.apache.tools.ant.IntrospectionHelper.AttributeSetter
                public void set(Project project, Object obj, String str2) throws InvocationTargetException, IllegalAccessException {
                    method.invoke(obj, project.resolveFile(str2));
                }
            };
        }
        if (Resource.class.equals(cls2) || FileProvider.class.equals(cls2)) {
            return new AttributeSetter(method, cls) { // from class: org.apache.tools.ant.IntrospectionHelper.8
                @Override // org.apache.tools.ant.IntrospectionHelper.AttributeSetter
                void set(Project project, Object obj, String str2) throws InvocationTargetException, IllegalAccessException, BuildException {
                    method.invoke(obj, new FileResource(project, project.resolveFile(str2)));
                }
            };
        }
        if (EnumeratedAttribute.class.isAssignableFrom(cls2)) {
            return new AttributeSetter(method, cls) { // from class: org.apache.tools.ant.IntrospectionHelper.9
                @Override // org.apache.tools.ant.IntrospectionHelper.AttributeSetter
                public void set(Project project, Object obj, String str2) throws InvocationTargetException, IllegalAccessException, BuildException {
                    try {
                        EnumeratedAttribute enumeratedAttribute = (EnumeratedAttribute) cls2.newInstance();
                        enumeratedAttribute.setValue(str2);
                        method.invoke(obj, enumeratedAttribute);
                    } catch (InstantiationException e) {
                        throw new BuildException(e);
                    }
                }
            };
        }
        AttributeSetter enumSetter = getEnumSetter(cls2, method, cls);
        if (enumSetter != null) {
            return enumSetter;
        }
        if (Long.class.equals(cls2)) {
            return new AttributeSetter(method, cls) { // from class: org.apache.tools.ant.IntrospectionHelper.10
                @Override // org.apache.tools.ant.IntrospectionHelper.AttributeSetter
                public void set(Project project, Object obj, String str2) throws InvocationTargetException, IllegalAccessException, BuildException {
                    try {
                        method.invoke(obj, new Long(StringUtils.parseHumanSizes(str2)));
                    } catch (IllegalAccessException e) {
                        throw e;
                    } catch (NumberFormatException unused) {
                        throw new BuildException("Can't assign non-numeric value '" + str2 + "' to attribute " + str);
                    } catch (InvocationTargetException e2) {
                        throw e2;
                    } catch (Exception e3) {
                        throw new BuildException(e3);
                    }
                }
            };
        }
        try {
            try {
                constructor = cls2.getConstructor(Project.class, String.class);
                z = true;
            } catch (NoSuchMethodException unused) {
                constructor = cls2.getConstructor(String.class);
                z = false;
            }
            return new AttributeSetter(method, cls) { // from class: org.apache.tools.ant.IntrospectionHelper.11
                @Override // org.apache.tools.ant.IntrospectionHelper.AttributeSetter
                public void set(Project project, Object obj, String str2) throws InvocationTargetException, IllegalAccessException, BuildException {
                    try {
                        Object newInstance = constructor.newInstance(z ? new Object[]{project, str2} : new Object[]{str2});
                        if (project != null) {
                            project.setProjectReference(newInstance);
                        }
                        method.invoke(obj, newInstance);
                    } catch (InstantiationException e) {
                        throw new BuildException(e);
                    } catch (InvocationTargetException e2) {
                        Throwable cause = e2.getCause();
                        if (cause instanceof IllegalArgumentException) {
                            throw new BuildException("Can't assign value '" + str2 + "' to attribute " + str + ", reason: " + cause.getClass() + " with message '" + cause.getMessage() + "'");
                        }
                        throw e2;
                    }
                }
            };
        } catch (NoSuchMethodException unused2) {
            return null;
        }
    }

    private AttributeSetter getEnumSetter(final Class<?> cls, final Method method, Class<?> cls2) {
        if (cls.isEnum()) {
            return new AttributeSetter(method, cls2) { // from class: org.apache.tools.ant.IntrospectionHelper.12
                @Override // org.apache.tools.ant.IntrospectionHelper.AttributeSetter
                public void set(Project project, Object obj, String str) throws InvocationTargetException, IllegalAccessException, BuildException {
                    try {
                        method.invoke(obj, Enum.valueOf(cls, str));
                    } catch (IllegalArgumentException unused) {
                        throw new BuildException("'" + str + "' is not a permitted value for " + cls.getName());
                    }
                }
            };
        }
        return null;
    }

    private String getElementName(Project project, Object obj) {
        return project.getElementName(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String getPropertyName(String str, String str2) {
        return str.substring(str2.length()).toLowerCase(Locale.ENGLISH);
    }

    /* loaded from: classes2.dex */
    public static final class Creator {
        private final NestedCreator nestedCreator;
        private Object nestedObject;
        private final Object parent;
        private String polyType;
        private final Project project;

        private Creator(Project project, Object obj, NestedCreator nestedCreator) {
            this.project = project;
            this.parent = obj;
            this.nestedCreator = nestedCreator;
        }

        public void setPolyType(String str) {
            this.polyType = str;
        }

        public Object create() {
            if (this.polyType != null) {
                if (this.nestedCreator.isPolyMorphic()) {
                    this.nestedObject = ComponentHelper.getComponentHelper(this.project).createComponent(this.polyType);
                    if (this.nestedObject == null) {
                        throw new BuildException("Unable to create object of type " + this.polyType);
                    }
                } else {
                    throw new BuildException("Not allowed to use the polymorphic form for this element");
                }
            }
            try {
                this.nestedObject = this.nestedCreator.create(this.project, this.parent, this.nestedObject);
                if (this.project != null) {
                    this.project.setProjectReference(this.nestedObject);
                }
                return this.nestedObject;
            } catch (IllegalAccessException e) {
                throw new BuildException(e);
            } catch (IllegalArgumentException e2) {
                if (this.polyType == null) {
                    throw e2;
                }
                throw new BuildException("Invalid type used " + this.polyType);
            } catch (InstantiationException e3) {
                throw new BuildException(e3);
            } catch (InvocationTargetException e4) {
                throw IntrospectionHelper.extractBuildException(e4);
            }
        }

        public Object getRealObject() {
            return this.nestedCreator.getRealObject();
        }

        public void store() {
            try {
                this.nestedCreator.store(this.parent, this.nestedObject);
            } catch (IllegalAccessException e) {
                throw new BuildException(e);
            } catch (IllegalArgumentException e2) {
                if (this.polyType == null) {
                    throw e2;
                }
                throw new BuildException("Invalid type used " + this.polyType);
            } catch (InstantiationException e3) {
                throw new BuildException(e3);
            } catch (InvocationTargetException e4) {
                throw IntrospectionHelper.extractBuildException(e4);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static abstract class NestedCreator {
        private final Method method;

        abstract Object create(Project project, Object obj, Object obj2) throws InvocationTargetException, IllegalAccessException, InstantiationException;

        Object getRealObject() {
            return null;
        }

        boolean isPolyMorphic() {
            return false;
        }

        void store(Object obj, Object obj2) throws InvocationTargetException, IllegalAccessException, InstantiationException {
        }

        protected NestedCreator(Method method) {
            this.method = method;
        }

        Method getMethod() {
            return this.method;
        }
    }

    /* loaded from: classes2.dex */
    private static class CreateNestedCreator extends NestedCreator {
        CreateNestedCreator(Method method) {
            super(method);
        }

        @Override // org.apache.tools.ant.IntrospectionHelper.NestedCreator
        Object create(Project project, Object obj, Object obj2) throws InvocationTargetException, IllegalAccessException {
            return getMethod().invoke(obj, new Object[0]);
        }
    }

    /* loaded from: classes2.dex */
    private static class AddNestedCreator extends NestedCreator {
        static final int ADD = 1;
        static final int ADD_CONFIGURED = 2;
        private final int behavior;
        private final Constructor<?> constructor;

        @Override // org.apache.tools.ant.IntrospectionHelper.NestedCreator
        boolean isPolyMorphic() {
            return true;
        }

        AddNestedCreator(Method method, Constructor<?> constructor, int i) {
            super(method);
            this.constructor = constructor;
            this.behavior = i;
        }

        @Override // org.apache.tools.ant.IntrospectionHelper.NestedCreator
        Object create(Project project, Object obj, Object obj2) throws InvocationTargetException, IllegalAccessException, InstantiationException {
            if (obj2 == null) {
                Constructor<?> constructor = this.constructor;
                obj2 = constructor.newInstance(constructor.getParameterTypes().length == 0 ? new Object[0] : new Object[]{project});
            }
            if (obj2 instanceof PreSetDef.PreSetDefinition) {
                obj2 = ((PreSetDef.PreSetDefinition) obj2).createObject(project);
            }
            if (this.behavior == 1) {
                istore(obj, obj2);
            }
            return obj2;
        }

        @Override // org.apache.tools.ant.IntrospectionHelper.NestedCreator
        void store(Object obj, Object obj2) throws InvocationTargetException, IllegalAccessException, InstantiationException {
            if (this.behavior == 2) {
                istore(obj, obj2);
            }
        }

        private void istore(Object obj, Object obj2) throws InvocationTargetException, IllegalAccessException, InstantiationException {
            getMethod().invoke(obj, obj2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static abstract class AttributeSetter {
        private final Method method;
        private final Class<?> type;

        abstract void set(Project project, Object obj, String str) throws InvocationTargetException, IllegalAccessException, BuildException;

        protected AttributeSetter(Method method, Class<?> cls) {
            this.method = method;
            this.type = cls;
        }

        void setObject(Project project, Object obj, Object obj2) throws InvocationTargetException, IllegalAccessException, BuildException {
            Class<?> cls = this.type;
            if (cls != null) {
                if (cls.isPrimitive()) {
                    if (obj2 != null) {
                        cls = (Class) IntrospectionHelper.PRIMITIVE_TYPE_MAP.get(this.type);
                    } else {
                        throw new BuildException("Attempt to set primitive " + IntrospectionHelper.getPropertyName(this.method.getName(), "set") + " to null on " + obj);
                    }
                }
                if (obj2 == null || cls.isInstance(obj2)) {
                    this.method.invoke(obj, obj2);
                    return;
                }
            }
            set(project, obj, obj2.toString());
        }
    }

    public static synchronized void clearCache() {
        synchronized (IntrospectionHelper.class) {
            HELPERS.clear();
        }
    }

    private NestedCreator createAddTypeCreator(Project project, Object obj, String str) throws BuildException {
        if (this.addTypeMethods.size() == 0) {
            return null;
        }
        ComponentHelper componentHelper = ComponentHelper.getComponentHelper(project);
        MethodAndObject createRestricted = createRestricted(componentHelper, str, this.addTypeMethods);
        MethodAndObject createTopLevel = createTopLevel(componentHelper, str, this.addTypeMethods);
        if (createRestricted == null && createTopLevel == null) {
            return null;
        }
        if (createRestricted == null || createTopLevel == null) {
            if (createRestricted != null) {
                createTopLevel = createRestricted;
            }
            final Object obj2 = createTopLevel.object;
            if (createTopLevel.object instanceof PreSetDef.PreSetDefinition) {
                obj2 = ((PreSetDef.PreSetDefinition) createTopLevel.object).createObject(project);
            }
            final Object obj3 = createTopLevel.object;
            return new NestedCreator(createTopLevel.method) { // from class: org.apache.tools.ant.IntrospectionHelper.13
                @Override // org.apache.tools.ant.IntrospectionHelper.NestedCreator
                Object create(Project project2, Object obj4, Object obj5) throws InvocationTargetException, IllegalAccessException {
                    if (!getMethod().getName().endsWith("Configured")) {
                        getMethod().invoke(obj4, obj2);
                    }
                    return obj3;
                }

                @Override // org.apache.tools.ant.IntrospectionHelper.NestedCreator
                Object getRealObject() {
                    return obj2;
                }

                @Override // org.apache.tools.ant.IntrospectionHelper.NestedCreator
                void store(Object obj4, Object obj5) throws InvocationTargetException, IllegalAccessException, InstantiationException {
                    if (getMethod().getName().endsWith("Configured")) {
                        getMethod().invoke(obj4, obj2);
                    }
                }
            };
        }
        throw new BuildException("ambiguous: type and component definitions for " + str);
    }

    private void insertAddTypeMethod(Method method) {
        Class<?> cls = method.getParameterTypes()[0];
        int size = this.addTypeMethods.size();
        for (int i = 0; i < size; i++) {
            Method method2 = this.addTypeMethods.get(i);
            if (method2.getParameterTypes()[0].equals(cls)) {
                if (method.getName().equals("addConfigured")) {
                    this.addTypeMethods.set(i, method);
                    return;
                }
                return;
            } else if (method2.getParameterTypes()[0].isAssignableFrom(cls)) {
                this.addTypeMethods.add(i, method);
                return;
            }
        }
        this.addTypeMethods.add(method);
    }

    private Method findMatchingMethod(Class<?> cls, List<Method> list) {
        if (cls == null) {
            return null;
        }
        int size = list.size();
        Method method = null;
        Class<?> cls2 = null;
        for (int i = 0; i < size; i++) {
            Method method2 = list.get(i);
            Class<?> cls3 = method2.getParameterTypes()[0];
            if (cls3.isAssignableFrom(cls)) {
                if (cls2 == null) {
                    method = method2;
                    cls2 = cls3;
                } else if (!cls3.isAssignableFrom(cls2)) {
                    throw new BuildException("ambiguous: types " + cls2.getName() + " and " + cls3.getName() + " match " + cls.getName());
                }
            }
        }
        return method;
    }

    private String condenseText(String str) {
        return str.length() <= 20 ? str : new StringBuffer(str).replace(8, str.length() - 8, ELLIPSIS).toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class MethodAndObject {
        private final Method method;
        private final Object object;

        public MethodAndObject(Method method, Object obj) {
            this.method = method;
            this.object = obj;
        }
    }

    private AntTypeDefinition findRestrictedDefinition(ComponentHelper componentHelper, String str, List<Method> list) {
        List<AntTypeDefinition> restrictedDefinitions = componentHelper.getRestrictedDefinitions(str);
        AntTypeDefinition antTypeDefinition = null;
        if (restrictedDefinitions == null) {
            return null;
        }
        synchronized (restrictedDefinitions) {
            int size = restrictedDefinitions.size();
            Class<?> cls = null;
            for (int i = 0; i < size; i++) {
                AntTypeDefinition antTypeDefinition2 = restrictedDefinitions.get(i);
                Class<?> exposedClass = antTypeDefinition2.getExposedClass(componentHelper.getProject());
                if (!(exposedClass == null || findMatchingMethod(exposedClass, list) == null)) {
                    if (cls == null) {
                        antTypeDefinition = antTypeDefinition2;
                        cls = exposedClass;
                    } else {
                        throw new BuildException("ambiguous: restricted definitions for " + str + ExpandableTextView.f6958c + cls + " and " + exposedClass);
                    }
                }
            }
        }
        return antTypeDefinition;
    }

    private MethodAndObject createRestricted(ComponentHelper componentHelper, String str, List<Method> list) {
        Project project = componentHelper.getProject();
        AntTypeDefinition findRestrictedDefinition = findRestrictedDefinition(componentHelper, str, list);
        if (findRestrictedDefinition == null) {
            return null;
        }
        Method findMatchingMethod = findMatchingMethod(findRestrictedDefinition.getExposedClass(project), list);
        if (findMatchingMethod != null) {
            Object create = findRestrictedDefinition.create(project);
            if (create != null) {
                return new MethodAndObject(findMatchingMethod, create);
            }
            throw new BuildException("Failed to create object " + str + " of type " + findRestrictedDefinition.getTypeClass(project));
        }
        throw new BuildException("Ant Internal Error - contract mismatch for " + str);
    }

    private MethodAndObject createTopLevel(ComponentHelper componentHelper, String str, List<Method> list) {
        Method findMatchingMethod;
        Class<?> componentClass = componentHelper.getComponentClass(str);
        if (componentClass == null || (findMatchingMethod = findMatchingMethod(componentClass, this.addTypeMethods)) == null) {
            return null;
        }
        return new MethodAndObject(findMatchingMethod, componentHelper.createComponent(str));
    }
}
