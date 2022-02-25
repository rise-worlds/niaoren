package org.apache.tools.ant;

import com.liulishuo.filedownloader.model.ConnectionModel;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.List;
import org.apache.tools.ant.IntrospectionHelper;
import org.apache.tools.ant.attribute.EnableAttribute;
import org.apache.tools.ant.util.CollectionUtils;
import org.xml.sax.AttributeList;
import org.xml.sax.helpers.AttributeListImpl;

/* loaded from: classes2.dex */
public class RuntimeConfigurable implements Serializable {
    private static final Hashtable<String, Object> EMPTY_HASHTABLE = new Hashtable<>(0);
    private static final long serialVersionUID = 1;
    private transient AttributeList attributes;
    private String elementTag = null;
    private List<RuntimeConfigurable> children = null;
    private transient Object wrappedObject = null;
    private transient boolean namespacedAttribute = false;
    private LinkedHashMap<String, Object> attributeMap = null;
    private StringBuffer characters = null;
    private boolean proxyConfigured = false;
    private String polyType = null;

    /* renamed from: id */
    private String f14736id = null;

    public RuntimeConfigurable(Object obj, String str) {
        setProxy(obj);
        setElementTag(str);
        if (obj instanceof Task) {
            ((Task) obj).setRuntimeConfigurableWrapper(this);
        }
    }

    public synchronized void setProxy(Object obj) {
        this.wrappedObject = obj;
        this.proxyConfigured = false;
    }

    /* loaded from: classes2.dex */
    private static class EnableAttributeConsumer {
        public void add(EnableAttribute enableAttribute) {
        }

        private EnableAttributeConsumer() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class AttributeComponentInformation {
        String componentName;
        boolean restricted;

        private AttributeComponentInformation(String str, boolean z) {
            this.componentName = str;
            this.restricted = z;
        }

        public String getComponentName() {
            return this.componentName;
        }

        public boolean isRestricted() {
            return this.restricted;
        }
    }

    private AttributeComponentInformation isRestrictedAttribute(String str, ComponentHelper componentHelper) {
        if (str.indexOf(58) == -1) {
            return new AttributeComponentInformation(null, false);
        }
        String attrToComponent = attrToComponent(str);
        if (componentHelper.getRestrictedDefinitions(ProjectHelper.nsToComponentName(ProjectHelper.extractUriFromComponentName(attrToComponent))) == null) {
            return new AttributeComponentInformation(null, false);
        }
        return new AttributeComponentInformation(attrToComponent, true);
    }

    public boolean isEnabled(UnknownElement unknownElement) {
        if (!this.namespacedAttribute) {
            return true;
        }
        ComponentHelper componentHelper = ComponentHelper.getComponentHelper(unknownElement.getProject());
        IntrospectionHelper helper = IntrospectionHelper.getHelper(unknownElement.getProject(), EnableAttributeConsumer.class);
        for (int i = 0; i < this.attributeMap.keySet().size(); i++) {
            String str = (String) this.attributeMap.keySet().toArray()[i];
            AttributeComponentInformation isRestrictedAttribute = isRestrictedAttribute(str, componentHelper);
            if (isRestrictedAttribute.isRestricted()) {
                String str2 = (String) this.attributeMap.get(str);
                try {
                    EnableAttribute enableAttribute = (EnableAttribute) helper.createElement(unknownElement.getProject(), new EnableAttributeConsumer(), isRestrictedAttribute.getComponentName());
                    if (enableAttribute != null && !enableAttribute.isEnabled(unknownElement, unknownElement.getProject().replaceProperties(str2))) {
                        return false;
                    }
                } catch (BuildException unused) {
                    throw new BuildException("Unsupported attribute " + isRestrictedAttribute.getComponentName());
                }
            }
        }
        return true;
    }

    private String attrToComponent(String str) {
        int lastIndexOf = str.lastIndexOf(58);
        int lastIndexOf2 = str.lastIndexOf(58, lastIndexOf - 1);
        return str.substring(0, lastIndexOf2) + str.substring(lastIndexOf);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void setCreator(IntrospectionHelper.Creator creator) {
    }

    public synchronized Object getProxy() {
        return this.wrappedObject;
    }

    public synchronized String getId() {
        return this.f14736id;
    }

    public synchronized String getPolyType() {
        return this.polyType;
    }

    public synchronized void setPolyType(String str) {
        this.polyType = str;
    }

    public synchronized void setAttributes(AttributeList attributeList) {
        this.attributes = new AttributeListImpl(attributeList);
        for (int i = 0; i < attributeList.getLength(); i++) {
            setAttribute(attributeList.getName(i), attributeList.getValue(i));
        }
    }

    public synchronized void setAttribute(String str, String str2) {
        if (str.indexOf(58) != -1) {
            this.namespacedAttribute = true;
        }
        setAttribute(str, (Object) str2);
    }

    public synchronized void setAttribute(String str, Object obj) {
        String str2 = null;
        if (str.equalsIgnoreCase(ProjectHelper.ANT_TYPE)) {
            if (obj != null) {
                str2 = obj.toString();
            }
            this.polyType = str2;
        } else {
            if (this.attributeMap == null) {
                this.attributeMap = new LinkedHashMap<>();
            }
            if (!str.equalsIgnoreCase("refid") || this.attributeMap.isEmpty()) {
                this.attributeMap.put(str, obj);
            } else {
                LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>();
                linkedHashMap.put(str, obj);
                linkedHashMap.putAll(this.attributeMap);
                this.attributeMap = linkedHashMap;
            }
            if (str.equals(ConnectionModel.f10389a)) {
                if (obj != null) {
                    str2 = obj.toString();
                }
                this.f14736id = str2;
            }
        }
    }

    public synchronized void removeAttribute(String str) {
        this.attributeMap.remove(str);
    }

    public synchronized Hashtable<String, Object> getAttributeMap() {
        return this.attributeMap == null ? EMPTY_HASHTABLE : new Hashtable<>(this.attributeMap);
    }

    public synchronized AttributeList getAttributes() {
        return this.attributes;
    }

    public synchronized void addChild(RuntimeConfigurable runtimeConfigurable) {
        this.children = this.children == null ? new ArrayList<>() : this.children;
        this.children.add(runtimeConfigurable);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized RuntimeConfigurable getChild(int i) {
        return this.children.get(i);
    }

    public synchronized Enumeration<RuntimeConfigurable> getChildren() {
        return this.children == null ? new CollectionUtils.EmptyEnumeration<>() : Collections.enumeration(this.children);
    }

    public synchronized void addText(String str) {
        StringBuffer stringBuffer;
        if (str.length() != 0) {
            if (this.characters == null) {
                stringBuffer = new StringBuffer(str);
            } else {
                stringBuffer = this.characters;
                stringBuffer.append(str);
            }
            this.characters = stringBuffer;
        }
    }

    public synchronized void addText(char[] cArr, int i, int i2) {
        if (i2 != 0) {
            StringBuffer stringBuffer = this.characters == null ? new StringBuffer(i2) : this.characters;
            stringBuffer.append(cArr, i, i2);
            this.characters = stringBuffer;
        }
    }

    public synchronized StringBuffer getText() {
        return this.characters == null ? new StringBuffer(0) : this.characters;
    }

    public synchronized void setElementTag(String str) {
        this.elementTag = str;
    }

    public synchronized String getElementTag() {
        return this.elementTag;
    }

    public void maybeConfigure(Project project) throws BuildException {
        maybeConfigure(project, true);
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:0x0098, code lost:
        if (r7.isDoubleExpanding() == false) goto L_0x009c;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized void maybeConfigure(org.apache.tools.ant.Project r10, boolean r11) throws org.apache.tools.ant.BuildException {
        /*
            Method dump skipped, instructions count: 263
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.tools.ant.RuntimeConfigurable.maybeConfigure(org.apache.tools.ant.Project, boolean):void");
    }

    public void reconfigure(Project project) {
        this.proxyConfigured = false;
        maybeConfigure(project);
    }

    public void applyPreSet(RuntimeConfigurable runtimeConfigurable) {
        LinkedHashMap<String, Object> linkedHashMap = runtimeConfigurable.attributeMap;
        if (linkedHashMap != null) {
            for (String str : linkedHashMap.keySet()) {
                LinkedHashMap<String, Object> linkedHashMap2 = this.attributeMap;
                if (linkedHashMap2 == null || linkedHashMap2.get(str) == null) {
                    setAttribute(str, (String) runtimeConfigurable.attributeMap.get(str));
                }
            }
        }
        String str2 = this.polyType;
        if (str2 == null) {
            str2 = runtimeConfigurable.polyType;
        }
        this.polyType = str2;
        if (runtimeConfigurable.children != null) {
            ArrayList arrayList = new ArrayList();
            arrayList.addAll(runtimeConfigurable.children);
            List<RuntimeConfigurable> list = this.children;
            if (list != null) {
                arrayList.addAll(list);
            }
            this.children = arrayList;
        }
        if (runtimeConfigurable.characters != null) {
            StringBuffer stringBuffer = this.characters;
            if (stringBuffer == null || stringBuffer.toString().trim().length() == 0) {
                this.characters = new StringBuffer(runtimeConfigurable.characters.toString());
            }
        }
    }
}
