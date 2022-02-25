package org.apache.tools.ant.taskdefs;

import com.ctetin.expandabletextviewlibrary.ExpandableTextView;
import com.liulishuo.filedownloader.model.ConnectionModel;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DynamicAttribute;
import org.apache.tools.ant.ProjectHelper;
import org.apache.tools.ant.RuntimeConfigurable;
import org.apache.tools.ant.Target;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.TaskContainer;
import org.apache.tools.ant.UnknownElement;
import org.apache.tools.ant.property.LocalProperties;
import org.apache.tools.ant.taskdefs.MacroDef;
import p110z1.C4963cj;

/* loaded from: classes2.dex */
public class MacroInstance extends Task implements DynamicAttribute, TaskContainer {
    private static final int STATE_EXPECT_BRACKET = 1;
    private static final int STATE_EXPECT_NAME = 2;
    private static final int STATE_NORMAL = 0;
    private Hashtable<String, String> localAttributes;
    private MacroDef macroDef;
    private Map<String, UnknownElement> presentElements;
    private Map<String, String> map = new HashMap();
    private Map<String, MacroDef.TemplateElement> nsElements = null;
    private String text = null;
    private String implicitTag = null;
    private List<Task> unknownElements = new ArrayList();

    public void setMacroDef(MacroDef macroDef) {
        this.macroDef = macroDef;
    }

    public MacroDef getMacroDef() {
        return this.macroDef;
    }

    @Override // org.apache.tools.ant.DynamicAttribute
    public void setDynamicAttribute(String str, String str2) {
        this.map.put(str, str2);
    }

    public Object createDynamicElement(String str) throws BuildException {
        throw new BuildException("Not implemented any more");
    }

    private Map<String, MacroDef.TemplateElement> getNsElements() {
        if (this.nsElements == null) {
            this.nsElements = new HashMap();
            for (Map.Entry<String, MacroDef.TemplateElement> entry : this.macroDef.getElements().entrySet()) {
                this.nsElements.put(entry.getKey(), entry.getValue());
                MacroDef.TemplateElement value = entry.getValue();
                if (value.isImplicit()) {
                    this.implicitTag = value.getName();
                }
            }
        }
        return this.nsElements;
    }

    @Override // org.apache.tools.ant.TaskContainer
    public void addTask(Task task) {
        this.unknownElements.add(task);
    }

    private void processTasks() {
        if (this.implicitTag == null) {
            Iterator<Task> it = this.unknownElements.iterator();
            while (it.hasNext()) {
                UnknownElement unknownElement = (UnknownElement) it.next();
                String lowerCase = ProjectHelper.extractNameFromComponentName(unknownElement.getTag()).toLowerCase(Locale.ENGLISH);
                if (getNsElements().get(lowerCase) == null) {
                    throw new BuildException("unsupported element " + lowerCase);
                } else if (this.presentElements.get(lowerCase) == null) {
                    this.presentElements.put(lowerCase, unknownElement);
                } else {
                    throw new BuildException("Element " + lowerCase + " already present");
                }
            }
        }
    }

    /* loaded from: classes2.dex */
    public static class Element implements TaskContainer {
        private List<Task> unknownElements = new ArrayList();

        @Override // org.apache.tools.ant.TaskContainer
        public void addTask(Task task) {
            this.unknownElements.add(task);
        }

        public List<Task> getUnknownElements() {
            return this.unknownElements;
        }
    }

    private String macroSubs(String str, Map<String, String> map) {
        if (str == null) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        StringBuffer stringBuffer2 = null;
        char c = 0;
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            switch (c) {
                case 0:
                    if (charAt == '@') {
                        c = 1;
                        break;
                    } else {
                        stringBuffer.append(charAt);
                        break;
                    }
                case 1:
                    if (charAt != '{') {
                        if (charAt == '@') {
                            stringBuffer.append('@');
                            c = 0;
                            break;
                        } else {
                            stringBuffer.append('@');
                            stringBuffer.append(charAt);
                            c = 0;
                            break;
                        }
                    } else {
                        c = 2;
                        stringBuffer2 = new StringBuffer();
                        break;
                    }
                case 2:
                    if (charAt == '}') {
                        String lowerCase = stringBuffer2.toString().toLowerCase(Locale.ENGLISH);
                        String str2 = map.get(lowerCase);
                        if (str2 == null) {
                            stringBuffer.append("@{");
                            stringBuffer.append(lowerCase);
                            stringBuffer.append(C4963cj.f20747d);
                        } else {
                            stringBuffer.append(str2);
                        }
                        stringBuffer2 = null;
                        c = 0;
                        break;
                    } else {
                        stringBuffer2.append(charAt);
                        break;
                    }
            }
        }
        switch (c) {
            case 1:
                stringBuffer.append('@');
                break;
            case 2:
                stringBuffer.append("@{");
                stringBuffer.append(stringBuffer2.toString());
                break;
        }
        return stringBuffer.toString();
    }

    public void addText(String str) {
        this.text = str;
    }

    private UnknownElement copy(UnknownElement unknownElement, boolean z) {
        UnknownElement unknownElement2 = new UnknownElement(unknownElement.getTag());
        unknownElement2.setNamespace(unknownElement.getNamespace());
        unknownElement2.setProject(getProject());
        unknownElement2.setQName(unknownElement.getQName());
        unknownElement2.setTaskType(unknownElement.getTaskType());
        unknownElement2.setTaskName(unknownElement.getTaskName());
        unknownElement2.setLocation(this.macroDef.getBackTrace() ? unknownElement.getLocation() : getLocation());
        if (getOwningTarget() == null) {
            Target target = new Target();
            target.setProject(getProject());
            unknownElement2.setOwningTarget(target);
        } else {
            unknownElement2.setOwningTarget(getOwningTarget());
        }
        RuntimeConfigurable runtimeConfigurable = new RuntimeConfigurable(unknownElement2, unknownElement.getTaskName());
        runtimeConfigurable.setPolyType(unknownElement.getWrapper().getPolyType());
        for (Map.Entry<String, Object> entry : unknownElement.getWrapper().getAttributeMap().entrySet()) {
            runtimeConfigurable.setAttribute(entry.getKey(), macroSubs((String) entry.getValue(), this.localAttributes));
        }
        runtimeConfigurable.addText(macroSubs(unknownElement.getWrapper().getText().toString(), this.localAttributes));
        Enumeration<RuntimeConfigurable> children = unknownElement.getWrapper().getChildren();
        while (children.hasMoreElements()) {
            UnknownElement unknownElement3 = (UnknownElement) children.nextElement().getProxy();
            String taskType = unknownElement3.getTaskType();
            if (taskType != null) {
                taskType = taskType.toLowerCase(Locale.ENGLISH);
            }
            MacroDef.TemplateElement templateElement = getNsElements().get(taskType);
            if (templateElement == null || z) {
                UnknownElement copy = copy(unknownElement3, z);
                runtimeConfigurable.addChild(copy.getWrapper());
                unknownElement2.addChild(copy);
            } else if (!templateElement.isImplicit()) {
                UnknownElement unknownElement4 = this.presentElements.get(taskType);
                if (unknownElement4 != null) {
                    String stringBuffer = unknownElement4.getWrapper().getText().toString();
                    if (!"".equals(stringBuffer)) {
                        runtimeConfigurable.addText(macroSubs(stringBuffer, this.localAttributes));
                    }
                    List<UnknownElement> children2 = unknownElement4.getChildren();
                    if (children2 != null) {
                        for (UnknownElement unknownElement5 : children2) {
                            UnknownElement copy2 = copy(unknownElement5, true);
                            runtimeConfigurable.addChild(copy2.getWrapper());
                            unknownElement2.addChild(copy2);
                        }
                    }
                } else if (!templateElement.isOptional()) {
                    throw new BuildException("Required nested element " + templateElement.getName() + " missing");
                }
            } else if (this.unknownElements.size() != 0 || templateElement.isOptional()) {
                Iterator<Task> it = this.unknownElements.iterator();
                while (it.hasNext()) {
                    UnknownElement copy3 = copy((UnknownElement) it.next(), true);
                    runtimeConfigurable.addChild(copy3.getWrapper());
                    unknownElement2.addChild(copy3);
                }
            } else {
                throw new BuildException("Missing nested elements for implicit element " + templateElement.getName());
            }
        }
        return unknownElement2;
    }

    @Override // org.apache.tools.ant.Task
    public void execute() {
        this.presentElements = new HashMap();
        getNsElements();
        processTasks();
        this.localAttributes = new Hashtable<>();
        HashSet hashSet = new HashSet(this.map.keySet());
        for (MacroDef.Attribute attribute : this.macroDef.getAttributes()) {
            String str = this.map.get(attribute.getName());
            if (str == null && "description".equals(attribute.getName())) {
                str = getDescription();
            }
            if (str == null) {
                str = macroSubs(attribute.getDefault(), this.localAttributes);
            }
            if (str != null) {
                this.localAttributes.put(attribute.getName(), str);
                hashSet.remove(attribute.getName());
            } else {
                throw new BuildException("required attribute " + attribute.getName() + " not set");
            }
        }
        if (hashSet.contains(ConnectionModel.f10389a)) {
            hashSet.remove(ConnectionModel.f10389a);
        }
        if (this.macroDef.getText() != null) {
            if (this.text == null) {
                String str2 = this.macroDef.getText().getDefault();
                if (this.macroDef.getText().getOptional() || str2 != null) {
                    if (str2 == null) {
                        str2 = "";
                    }
                    this.text = str2;
                } else {
                    throw new BuildException("required text missing");
                }
            }
            if (this.macroDef.getText().getTrim()) {
                this.text = this.text.trim();
            }
            this.localAttributes.put(this.macroDef.getText().getName(), this.text);
        } else {
            String str3 = this.text;
            if (str3 != null && !str3.trim().equals("")) {
                throw new BuildException("The \"" + getTaskName() + "\" macro does not support nested text data.");
            }
        }
        if (hashSet.size() != 0) {
            StringBuilder sb = new StringBuilder();
            sb.append("Unknown attribute");
            sb.append(hashSet.size() > 1 ? "s " : ExpandableTextView.f6958c);
            sb.append(hashSet);
            throw new BuildException(sb.toString());
        }
        UnknownElement copy = copy(this.macroDef.getNestedTask(), false);
        copy.init();
        LocalProperties localProperties = LocalProperties.get(getProject());
        localProperties.enterScope();
        try {
            try {
                copy.perform();
            } catch (BuildException e) {
                if (this.macroDef.getBackTrace()) {
                    throw ProjectHelper.addLocationToBuildException(e, getLocation());
                }
                e.setLocation(getLocation());
                throw e;
            }
        } finally {
            this.presentElements = null;
            this.localAttributes = null;
            localProperties.exitScope();
        }
    }
}
