package org.apache.tools.ant.taskdefs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.apache.tools.ant.AntTypeDefinition;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.ComponentHelper;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectHelper;
import org.apache.tools.ant.RuntimeConfigurable;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.TaskContainer;
import org.apache.tools.ant.UnknownElement;

/* loaded from: classes2.dex */
public class MacroDef extends AntlibDefinition {
    private String name;
    private NestedSequential nestedSequential;
    private boolean backTrace = true;
    private List<Attribute> attributes = new ArrayList();
    private Map<String, TemplateElement> elements = new HashMap();
    private String textName = null;
    private Text text = null;
    private boolean hasImplicitElement = false;

    public void setName(String str) {
        this.name = str;
    }

    public void addConfiguredText(Text text) {
        if (this.text != null) {
            throw new BuildException("Only one nested text element allowed");
        } else if (text.getName() != null) {
            for (Attribute attribute : this.attributes) {
                if (text.getName().equals(attribute.getName())) {
                    throw new BuildException("the name \"" + text.getName() + "\" is already used as an attribute");
                }
            }
            this.text = text;
            this.textName = text.getName();
        } else {
            throw new BuildException("the text nested element needed a \"name\" attribute");
        }
    }

    public Text getText() {
        return this.text;
    }

    public void setBackTrace(boolean z) {
        this.backTrace = z;
    }

    public boolean getBackTrace() {
        return this.backTrace;
    }

    public NestedSequential createSequential() {
        if (this.nestedSequential == null) {
            this.nestedSequential = new NestedSequential();
            return this.nestedSequential;
        }
        throw new BuildException("Only one sequential allowed");
    }

    /* loaded from: classes2.dex */
    public static class NestedSequential implements TaskContainer {
        private List<Task> nested = new ArrayList();

        @Override // org.apache.tools.ant.TaskContainer
        public void addTask(Task task) {
            this.nested.add(task);
        }

        public List<Task> getNested() {
            return this.nested;
        }

        public boolean similar(NestedSequential nestedSequential) {
            int size = this.nested.size();
            if (size != nestedSequential.nested.size()) {
                return false;
            }
            for (int i = 0; i < size; i++) {
                if (!((UnknownElement) this.nested.get(i)).similar((UnknownElement) nestedSequential.nested.get(i))) {
                    return false;
                }
            }
            return true;
        }
    }

    public UnknownElement getNestedTask() {
        UnknownElement unknownElement = new UnknownElement("sequential");
        unknownElement.setTaskName("sequential");
        unknownElement.setNamespace("");
        unknownElement.setQName("sequential");
        new RuntimeConfigurable(unknownElement, "sequential");
        int size = this.nestedSequential.getNested().size();
        for (int i = 0; i < size; i++) {
            UnknownElement unknownElement2 = (UnknownElement) this.nestedSequential.getNested().get(i);
            unknownElement.addChild(unknownElement2);
            unknownElement.getWrapper().addChild(unknownElement2.getWrapper());
        }
        return unknownElement;
    }

    public List<Attribute> getAttributes() {
        return this.attributes;
    }

    public Map<String, TemplateElement> getElements() {
        return this.elements;
    }

    public static boolean isValidNameCharacter(char c) {
        return Character.isLetterOrDigit(c) || c == '.' || c == '-';
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isValidName(String str) {
        if (str.length() == 0) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            if (!isValidNameCharacter(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public void addConfiguredAttribute(Attribute attribute) {
        if (attribute.getName() == null) {
            throw new BuildException("the attribute nested element needed a \"name\" attribute");
        } else if (!attribute.getName().equals(this.textName)) {
            int size = this.attributes.size();
            for (int i = 0; i < size; i++) {
                if (this.attributes.get(i).getName().equals(attribute.getName())) {
                    throw new BuildException("the name \"" + attribute.getName() + "\" has already been used in another attribute element");
                }
            }
            this.attributes.add(attribute);
        } else {
            throw new BuildException("the name \"" + attribute.getName() + "\" has already been used by the text element");
        }
    }

    public void addConfiguredElement(TemplateElement templateElement) {
        if (templateElement.getName() == null) {
            throw new BuildException("the element nested element needed a \"name\" attribute");
        } else if (this.elements.get(templateElement.getName()) != null) {
            throw new BuildException("the element " + templateElement.getName() + " has already been specified");
        } else if (this.hasImplicitElement || (templateElement.isImplicit() && this.elements.size() != 0)) {
            throw new BuildException("Only one element allowed when using implicit elements");
        } else {
            this.hasImplicitElement = templateElement.isImplicit();
            this.elements.put(templateElement.getName(), templateElement);
        }
    }

    @Override // org.apache.tools.ant.Task
    public void execute() {
        if (this.nestedSequential == null) {
            throw new BuildException("Missing sequential element");
        } else if (this.name != null) {
            this.name = ProjectHelper.genComponentName(getURI(), this.name);
            MyAntTypeDefinition myAntTypeDefinition = new MyAntTypeDefinition(this);
            myAntTypeDefinition.setName(this.name);
            myAntTypeDefinition.setClass(MacroInstance.class);
            ComponentHelper.getComponentHelper(getProject()).addDataTypeDefinition(myAntTypeDefinition);
            log("creating macro  " + this.name, 3);
        } else {
            throw new BuildException("Name not specified");
        }
    }

    /* loaded from: classes2.dex */
    public static class Attribute {
        private String defaultValue;
        private String description;
        private boolean doubleExpanding = true;
        private String name;

        public void setName(String str) {
            if (MacroDef.isValidName(str)) {
                this.name = str.toLowerCase(Locale.ENGLISH);
                return;
            }
            throw new BuildException("Illegal name [" + str + "] for attribute");
        }

        public String getName() {
            return this.name;
        }

        public void setDefault(String str) {
            this.defaultValue = str;
        }

        public String getDefault() {
            return this.defaultValue;
        }

        public void setDescription(String str) {
            this.description = str;
        }

        public String getDescription() {
            return this.description;
        }

        public void setDoubleExpanding(boolean z) {
            this.doubleExpanding = z;
        }

        public boolean isDoubleExpanding() {
            return this.doubleExpanding;
        }

        public boolean equals(Object obj) {
            if (obj == null || obj.getClass() != getClass()) {
                return false;
            }
            Attribute attribute = (Attribute) obj;
            String str = this.name;
            if (str == null) {
                if (attribute.name != null) {
                    return false;
                }
            } else if (!str.equals(attribute.name)) {
                return false;
            }
            String str2 = this.defaultValue;
            return str2 == null ? attribute.defaultValue == null : str2.equals(attribute.defaultValue);
        }

        public int hashCode() {
            return MacroDef.objectHashCode(this.defaultValue) + MacroDef.objectHashCode(this.name);
        }
    }

    /* loaded from: classes2.dex */
    public static class Text {
        private String defaultString;
        private String description;
        private String name;
        private boolean optional;
        private boolean trim;

        public void setName(String str) {
            if (MacroDef.isValidName(str)) {
                this.name = str.toLowerCase(Locale.ENGLISH);
                return;
            }
            throw new BuildException("Illegal name [" + str + "] for attribute");
        }

        public String getName() {
            return this.name;
        }

        public void setOptional(boolean z) {
            this.optional = z;
        }

        public boolean getOptional() {
            return this.optional;
        }

        public void setTrim(boolean z) {
            this.trim = z;
        }

        public boolean getTrim() {
            return this.trim;
        }

        public void setDescription(String str) {
            this.description = str;
        }

        public String getDescription() {
            return this.description;
        }

        public void setDefault(String str) {
            this.defaultString = str;
        }

        public String getDefault() {
            return this.defaultString;
        }

        public boolean equals(Object obj) {
            if (obj == null || obj.getClass() != getClass()) {
                return false;
            }
            Text text = (Text) obj;
            return MacroDef.safeCompare(this.name, text.name) && this.optional == text.optional && this.trim == text.trim && MacroDef.safeCompare(this.defaultString, text.defaultString);
        }

        public int hashCode() {
            return MacroDef.objectHashCode(this.name);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean safeCompare(Object obj, Object obj2) {
        if (obj == null) {
            return obj2 == null;
        }
        return obj.equals(obj2);
    }

    /* loaded from: classes2.dex */
    public static class TemplateElement {
        private String description;
        private String name;
        private boolean optional = false;
        private boolean implicit = false;

        public void setName(String str) {
            if (MacroDef.isValidName(str)) {
                this.name = str.toLowerCase(Locale.ENGLISH);
                return;
            }
            throw new BuildException("Illegal name [" + str + "] for macro element");
        }

        public String getName() {
            return this.name;
        }

        public void setDescription(String str) {
            this.description = str;
        }

        public String getDescription() {
            return this.description;
        }

        public void setOptional(boolean z) {
            this.optional = z;
        }

        public boolean isOptional() {
            return this.optional;
        }

        public void setImplicit(boolean z) {
            this.implicit = z;
        }

        public boolean isImplicit() {
            return this.implicit;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj == null || !obj.getClass().equals(getClass())) {
                return false;
            }
            TemplateElement templateElement = (TemplateElement) obj;
            String str = this.name;
            if (str != null ? str.equals(templateElement.name) : templateElement.name == null) {
                if (this.optional == templateElement.optional && this.implicit == templateElement.implicit) {
                    return true;
                }
            }
            return false;
        }

        public int hashCode() {
            return MacroDef.objectHashCode(this.name) + (this.optional ? 1 : 0) + (this.implicit ? 1 : 0);
        }
    }

    private boolean sameOrSimilar(Object obj, boolean z) {
        if (obj == this) {
            return true;
        }
        if (obj == null || !obj.getClass().equals(getClass())) {
            return false;
        }
        MacroDef macroDef = (MacroDef) obj;
        String str = this.name;
        if (str == null) {
            return macroDef.name == null;
        }
        if (!str.equals(macroDef.name)) {
            return false;
        }
        if (macroDef.getLocation() != null && macroDef.getLocation().equals(getLocation()) && !z) {
            return true;
        }
        Text text = this.text;
        if (text == null) {
            if (macroDef.text != null) {
                return false;
            }
        } else if (!text.equals(macroDef.text)) {
            return false;
        }
        if (getURI() == null || getURI().equals("") || getURI().equals(ProjectHelper.ANT_CORE_URI)) {
            if (macroDef.getURI() != null && !macroDef.getURI().equals("") && !macroDef.getURI().equals(ProjectHelper.ANT_CORE_URI)) {
                return false;
            }
        } else if (!getURI().equals(macroDef.getURI())) {
            return false;
        }
        return this.nestedSequential.similar(macroDef.nestedSequential) && this.attributes.equals(macroDef.attributes) && this.elements.equals(macroDef.elements);
    }

    public boolean similar(Object obj) {
        return sameOrSimilar(obj, false);
    }

    public boolean sameDefinition(Object obj) {
        return sameOrSimilar(obj, true);
    }

    /* loaded from: classes2.dex */
    private static class MyAntTypeDefinition extends AntTypeDefinition {
        private MacroDef macroDef;

        public MyAntTypeDefinition(MacroDef macroDef) {
            this.macroDef = macroDef;
        }

        @Override // org.apache.tools.ant.AntTypeDefinition
        public Object create(Project project) {
            Object create = super.create(project);
            if (create == null) {
                return null;
            }
            ((MacroInstance) create).setMacroDef(this.macroDef);
            return create;
        }

        @Override // org.apache.tools.ant.AntTypeDefinition
        public boolean sameDefinition(AntTypeDefinition antTypeDefinition, Project project) {
            if (!super.sameDefinition(antTypeDefinition, project)) {
                return false;
            }
            return this.macroDef.sameDefinition(((MyAntTypeDefinition) antTypeDefinition).macroDef);
        }

        @Override // org.apache.tools.ant.AntTypeDefinition
        public boolean similarDefinition(AntTypeDefinition antTypeDefinition, Project project) {
            if (!super.similarDefinition(antTypeDefinition, project)) {
                return false;
            }
            return this.macroDef.similar(((MyAntTypeDefinition) antTypeDefinition).macroDef);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int objectHashCode(Object obj) {
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }
}
