package org.apache.tools.ant.taskdefs.optional.script;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import org.apache.tools.ant.AntTypeDefinition;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.ComponentHelper;
import org.apache.tools.ant.MagicNames;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectHelper;
import org.apache.tools.ant.taskdefs.DefBase;
import org.apache.tools.ant.types.ResourceCollection;
import org.apache.tools.ant.util.ClasspathUtils;
import org.apache.tools.ant.util.ScriptRunnerBase;
import org.apache.tools.ant.util.ScriptRunnerHelper;
import p110z1.SimpleComparison;

/* loaded from: classes2.dex */
public class ScriptDef extends DefBase {
    private Set attributeSet;
    private String name;
    private Map nestedElementMap;
    private ScriptRunnerHelper helper = new ScriptRunnerHelper();
    private List attributes = new ArrayList();
    private List nestedElements = new ArrayList();

    @Override // org.apache.tools.ant.ProjectComponent
    public void setProject(Project project) {
        super.setProject(project);
        this.helper.setProjectComponent(this);
        this.helper.setSetBeans(false);
    }

    public void setName(String str) {
        this.name = str;
    }

    public boolean isAttributeSupported(String str) {
        return this.attributeSet.contains(str);
    }

    /* loaded from: classes2.dex */
    public static class Attribute {
        private String name;

        public void setName(String str) {
            this.name = str.toLowerCase(Locale.ENGLISH);
        }
    }

    public void addAttribute(Attribute attribute) {
        this.attributes.add(attribute);
    }

    /* loaded from: classes2.dex */
    public static class NestedElement {
        private String className;
        private String name;
        private String type;

        public void setName(String str) {
            this.name = str.toLowerCase(Locale.ENGLISH);
        }

        public void setType(String str) {
            this.type = str;
        }

        public void setClassName(String str) {
            this.className = str;
        }
    }

    public void addElement(NestedElement nestedElement) {
        this.nestedElements.add(nestedElement);
    }

    @Override // org.apache.tools.ant.Task
    public void execute() {
        if (this.name == null) {
            throw new BuildException("scriptdef requires a name attribute to name the script");
        } else if (this.helper.getLanguage() != null) {
            if (getAntlibClassLoader() != null || hasCpDelegate()) {
                this.helper.setClassLoader(createLoader());
            }
            this.attributeSet = new HashSet();
            for (Attribute attribute : this.attributes) {
                if (attribute.name == null) {
                    throw new BuildException("scriptdef <attribute> elements must specify an attribute name");
                } else if (!this.attributeSet.contains(attribute.name)) {
                    this.attributeSet.add(attribute.name);
                } else {
                    throw new BuildException("scriptdef <" + this.name + "> declares the " + attribute.name + " attribute more than once");
                }
            }
            this.nestedElementMap = new HashMap();
            for (NestedElement nestedElement : this.nestedElements) {
                if (nestedElement.name == null) {
                    throw new BuildException("scriptdef <element> elements must specify an element name");
                } else if (this.nestedElementMap.containsKey(nestedElement.name)) {
                    throw new BuildException("scriptdef <" + this.name + "> declares the " + nestedElement.name + " nested element more than once");
                } else if (nestedElement.className == null && nestedElement.type == null) {
                    throw new BuildException("scriptdef <element> elements must specify either a classname or type attribute");
                } else if (nestedElement.className == null || nestedElement.type == null) {
                    this.nestedElementMap.put(nestedElement.name, nestedElement);
                } else {
                    throw new BuildException("scriptdef <element> elements must specify only one of the classname and type attributes");
                }
            }
            Map lookupScriptRepository = lookupScriptRepository();
            this.name = ProjectHelper.genComponentName(getURI(), this.name);
            lookupScriptRepository.put(this.name, this);
            AntTypeDefinition antTypeDefinition = new AntTypeDefinition();
            antTypeDefinition.setName(this.name);
            antTypeDefinition.setClass(ScriptDefBase.class);
            ComponentHelper.getComponentHelper(getProject()).addDataTypeDefinition(antTypeDefinition);
        } else {
            throw new BuildException("<scriptdef> requires a language attribute to specify the script language");
        }
    }

    private Map lookupScriptRepository() {
        Map map;
        Project project = getProject();
        synchronized (project) {
            map = (Map) project.getReference(MagicNames.SCRIPT_REPOSITORY);
            if (map == null) {
                map = new HashMap();
                project.addReference(MagicNames.SCRIPT_REPOSITORY, map);
            }
        }
        return map;
    }

    public Object createNestedElement(String str) {
        Object obj;
        NestedElement nestedElement = (NestedElement) this.nestedElementMap.get(str);
        if (nestedElement != null) {
            String str2 = nestedElement.className;
            if (str2 == null) {
                obj = getProject().createTask(nestedElement.type);
                if (obj == null) {
                    obj = getProject().createDataType(nestedElement.type);
                }
            } else {
                try {
                    obj = ClasspathUtils.newInstance(str2, createLoader());
                } catch (BuildException unused) {
                    obj = ClasspathUtils.newInstance(str2, ScriptDef.class.getClassLoader());
                }
                getProject().setProjectReference(obj);
            }
            if (obj != null) {
                return obj;
            }
            throw new BuildException(SimpleComparison.f23612f + this.name + "> is unable to create the <" + str + "> nested element");
        }
        throw new BuildException(SimpleComparison.f23612f + this.name + "> does not support the <" + str + "> nested element");
    }

    public void executeScript(Map map, Map map2) {
        executeScript(map, map2, null);
    }

    public void executeScript(Map map, Map map2, ScriptDefBase scriptDefBase) {
        ScriptRunnerBase scriptRunner = this.helper.getScriptRunner();
        scriptRunner.addBean("attributes", map);
        scriptRunner.addBean("elements", map2);
        scriptRunner.addBean("project", getProject());
        if (scriptDefBase != null) {
            scriptRunner.addBean("self", scriptDefBase);
        }
        scriptRunner.executeScript("scriptdef_" + this.name);
    }

    public void setManager(String str) {
        this.helper.setManager(str);
    }

    public void setLanguage(String str) {
        this.helper.setLanguage(str);
    }

    public void setSrc(File file) {
        this.helper.setSrc(file);
    }

    public void addText(String str) {
        this.helper.addText(str);
    }

    public void add(ResourceCollection resourceCollection) {
        this.helper.add(resourceCollection);
    }
}
