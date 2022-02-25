package org.apache.tools.ant;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import org.apache.tools.ant.IntrospectionHelper;
import org.apache.tools.ant.taskdefs.PreSetDef;

/* loaded from: classes2.dex */
public class UnknownElement extends Task {
    private final String elementName;
    private String qname;
    private Object realThing;
    private String namespace = "";
    private List<UnknownElement> children = null;
    private boolean presetDefed = false;

    public UnknownElement(String str) {
        this.elementName = str;
    }

    public List<UnknownElement> getChildren() {
        return this.children;
    }

    public String getTag() {
        return this.elementName;
    }

    public String getNamespace() {
        return this.namespace;
    }

    public void setNamespace(String str) {
        if (str.equals(ProjectHelper.ANT_CURRENT_URI)) {
            str = ComponentHelper.getComponentHelper(getProject()).getCurrentAntlibUri();
        }
        if (str == null) {
            str = "";
        }
        this.namespace = str;
    }

    public String getQName() {
        return this.qname;
    }

    public void setQName(String str) {
        this.qname = str;
    }

    @Override // org.apache.tools.ant.Task
    public RuntimeConfigurable getWrapper() {
        return super.getWrapper();
    }

    @Override // org.apache.tools.ant.Task
    public void maybeConfigure() throws BuildException {
        if (this.realThing == null) {
            configure(makeObject(this, getWrapper()));
        }
    }

    public void configure(Object obj) {
        if (obj != null) {
            this.realThing = obj;
            getWrapper().setProxy(this.realThing);
            Task task = null;
            Object obj2 = this.realThing;
            if (obj2 instanceof Task) {
                task = (Task) obj2;
                task.setRuntimeConfigurableWrapper(getWrapper());
                if (getWrapper().getId() != null) {
                    getOwningTarget().replaceChild(this, (Task) this.realThing);
                }
            }
            if (task != null) {
                task.maybeConfigure();
            } else {
                getWrapper().maybeConfigure(getProject());
            }
            handleChildren(this.realThing, getWrapper());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.tools.ant.Task
    public void handleOutput(String str) {
        Object obj = this.realThing;
        if (obj instanceof Task) {
            ((Task) obj).handleOutput(str);
        } else {
            super.handleOutput(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.tools.ant.Task
    public int handleInput(byte[] bArr, int i, int i2) throws IOException {
        Object obj = this.realThing;
        if (obj instanceof Task) {
            return ((Task) obj).handleInput(bArr, i, i2);
        }
        return super.handleInput(bArr, i, i2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.tools.ant.Task
    public void handleFlush(String str) {
        Object obj = this.realThing;
        if (obj instanceof Task) {
            ((Task) obj).handleFlush(str);
        } else {
            super.handleFlush(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.tools.ant.Task
    public void handleErrorOutput(String str) {
        Object obj = this.realThing;
        if (obj instanceof Task) {
            ((Task) obj).handleErrorOutput(str);
        } else {
            super.handleErrorOutput(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.tools.ant.Task
    public void handleErrorFlush(String str) {
        Object obj = this.realThing;
        if (obj instanceof Task) {
            ((Task) obj).handleErrorFlush(str);
        } else {
            super.handleErrorFlush(str);
        }
    }

    @Override // org.apache.tools.ant.Task
    public void execute() {
        Object obj = this.realThing;
        if (obj != null) {
            try {
                if (obj instanceof Task) {
                    ((Task) obj).execute();
                }
            } finally {
                if (getWrapper().getId() == null) {
                    this.realThing = null;
                    getWrapper().setProxy(null);
                }
            }
        }
    }

    public void addChild(UnknownElement unknownElement) {
        if (this.children == null) {
            this.children = new ArrayList();
        }
        this.children.add(unknownElement);
    }

    protected void handleChildren(Object obj, RuntimeConfigurable runtimeConfigurable) throws BuildException {
        if (obj instanceof TypeAdapter) {
            obj = ((TypeAdapter) obj).getProxy();
        }
        String namespace = getNamespace();
        IntrospectionHelper helper = IntrospectionHelper.getHelper(getProject(), obj.getClass());
        List<UnknownElement> list = this.children;
        if (list != null) {
            int i = 0;
            for (UnknownElement unknownElement : list) {
                RuntimeConfigurable child = runtimeConfigurable.getChild(i);
                try {
                    if ((child.isEnabled(unknownElement) || !helper.supportsNestedElement(namespace, ProjectHelper.genComponentName(unknownElement.getNamespace(), unknownElement.getTag()))) && !handleChild(namespace, helper, obj, unknownElement, child)) {
                        if (!(obj instanceof TaskContainer)) {
                            helper.throwNotSupported(getProject(), obj, unknownElement.getTag());
                        } else {
                            ((TaskContainer) obj).addTask(unknownElement);
                        }
                    }
                    i++;
                } catch (UnsupportedElementException e) {
                    throw new BuildException(runtimeConfigurable.getElementTag() + " doesn't support the nested \"" + e.getElement() + "\" element.", e);
                }
            }
        }
    }

    protected String getComponentName() {
        return ProjectHelper.genComponentName(getNamespace(), getTag());
    }

    public void applyPreSet(UnknownElement unknownElement) {
        if (!this.presetDefed) {
            getWrapper().applyPreSet(unknownElement.getWrapper());
            if (unknownElement.children != null) {
                ArrayList arrayList = new ArrayList();
                arrayList.addAll(unknownElement.children);
                List<UnknownElement> list = this.children;
                if (list != null) {
                    arrayList.addAll(list);
                }
                this.children = arrayList;
            }
            this.presetDefed = true;
        }
    }

    protected Object makeObject(UnknownElement unknownElement, RuntimeConfigurable runtimeConfigurable) {
        if (!runtimeConfigurable.isEnabled(unknownElement)) {
            return null;
        }
        ComponentHelper componentHelper = ComponentHelper.getComponentHelper(getProject());
        String componentName = unknownElement.getComponentName();
        Object createComponent = componentHelper.createComponent(unknownElement, unknownElement.getNamespace(), componentName);
        if (createComponent != null) {
            if (createComponent instanceof PreSetDef.PreSetDefinition) {
                PreSetDef.PreSetDefinition preSetDefinition = (PreSetDef.PreSetDefinition) createComponent;
                Object createObject = preSetDefinition.createObject(unknownElement.getProject());
                if (createObject != null) {
                    unknownElement.applyPreSet(preSetDefinition.getPreSets());
                    if (createObject instanceof Task) {
                        Task task = (Task) createObject;
                        task.setTaskType(unknownElement.getTaskType());
                        task.setTaskName(unknownElement.getTaskName());
                        task.init();
                    }
                    createComponent = createObject;
                } else {
                    throw getNotFoundException("preset " + componentName, preSetDefinition.getPreSets().getComponentName());
                }
            }
            if (createComponent instanceof UnknownElement) {
                UnknownElement unknownElement2 = (UnknownElement) createComponent;
                createComponent = unknownElement2.makeObject(unknownElement2, runtimeConfigurable);
            }
            if (createComponent instanceof Task) {
                ((Task) createComponent).setOwningTarget(getOwningTarget());
            }
            if (createComponent instanceof ProjectComponent) {
                ((ProjectComponent) createComponent).setLocation(getLocation());
            }
            return createComponent;
        }
        throw getNotFoundException("task or type", componentName);
    }

    protected Task makeTask(UnknownElement unknownElement, RuntimeConfigurable runtimeConfigurable) {
        Task createTask = getProject().createTask(unknownElement.getTag());
        if (createTask != null) {
            createTask.setLocation(getLocation());
            createTask.setOwningTarget(getOwningTarget());
            createTask.init();
        }
        return createTask;
    }

    protected BuildException getNotFoundException(String str, String str2) {
        return new BuildException(ComponentHelper.getComponentHelper(getProject()).diagnoseCreationFailure(str2, str), getLocation());
    }

    @Override // org.apache.tools.ant.Task
    public String getTaskName() {
        Object obj = this.realThing;
        return (obj == null || !(obj instanceof Task)) ? super.getTaskName() : ((Task) obj).getTaskName();
    }

    public Task getTask() {
        Object obj = this.realThing;
        if (obj instanceof Task) {
            return (Task) obj;
        }
        return null;
    }

    public Object getRealThing() {
        return this.realThing;
    }

    public void setRealThing(Object obj) {
        this.realThing = obj;
    }

    private boolean handleChild(String str, IntrospectionHelper introspectionHelper, Object obj, UnknownElement unknownElement, RuntimeConfigurable runtimeConfigurable) {
        String genComponentName = ProjectHelper.genComponentName(unknownElement.getNamespace(), unknownElement.getTag());
        if (!introspectionHelper.supportsNestedElement(str, genComponentName, getProject(), obj)) {
            return false;
        }
        try {
            IntrospectionHelper.Creator elementCreator = introspectionHelper.getElementCreator(getProject(), str, obj, genComponentName, unknownElement);
            elementCreator.setPolyType(runtimeConfigurable.getPolyType());
            Object create = elementCreator.create();
            if (create instanceof PreSetDef.PreSetDefinition) {
                Object realObject = elementCreator.getRealObject();
                unknownElement.applyPreSet(((PreSetDef.PreSetDefinition) create).getPreSets());
                create = realObject;
            }
            runtimeConfigurable.setCreator(elementCreator);
            runtimeConfigurable.setProxy(create);
            if (create instanceof Task) {
                Task task = (Task) create;
                task.setRuntimeConfigurableWrapper(runtimeConfigurable);
                task.setTaskName(genComponentName);
                task.setTaskType(genComponentName);
            }
            if (create instanceof ProjectComponent) {
                ((ProjectComponent) create).setLocation(unknownElement.getLocation());
            }
            runtimeConfigurable.maybeConfigure(getProject());
            unknownElement.handleChildren(create, runtimeConfigurable);
            elementCreator.store();
            return true;
        } catch (UnsupportedElementException e) {
            if (introspectionHelper.isDynamic()) {
                return false;
            }
            throw e;
        }
    }

    public boolean similar(Object obj) {
        if (obj == null || !getClass().getName().equals(obj.getClass().getName())) {
            return false;
        }
        UnknownElement unknownElement = (UnknownElement) obj;
        if (!(equalsString(this.elementName, unknownElement.elementName) && this.namespace.equals(unknownElement.namespace) && this.qname.equals(unknownElement.qname) && getWrapper().getAttributeMap().equals(unknownElement.getWrapper().getAttributeMap()) && getWrapper().getText().toString().equals(unknownElement.getWrapper().getText().toString()))) {
            return false;
        }
        List<UnknownElement> list = this.children;
        int size = list == null ? 0 : list.size();
        if (size == 0) {
            List<UnknownElement> list2 = unknownElement.children;
            return list2 == null || list2.size() == 0;
        }
        List<UnknownElement> list3 = unknownElement.children;
        if (list3 == null || size != list3.size()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!this.children.get(i).similar(unknownElement.children.get(i))) {
                return false;
            }
        }
        return true;
    }

    private static boolean equalsString(String str, String str2) {
        if (str == null) {
            return str2 == null;
        }
        return str.equals(str2);
    }

    public UnknownElement copy(Project project) {
        UnknownElement unknownElement = new UnknownElement(getTag());
        unknownElement.setNamespace(getNamespace());
        unknownElement.setProject(project);
        unknownElement.setQName(getQName());
        unknownElement.setTaskType(getTaskType());
        unknownElement.setTaskName(getTaskName());
        unknownElement.setLocation(getLocation());
        if (getOwningTarget() == null) {
            Target target = new Target();
            target.setProject(getProject());
            unknownElement.setOwningTarget(target);
        } else {
            unknownElement.setOwningTarget(getOwningTarget());
        }
        RuntimeConfigurable runtimeConfigurable = new RuntimeConfigurable(unknownElement, getTaskName());
        runtimeConfigurable.setPolyType(getWrapper().getPolyType());
        for (Map.Entry<String, Object> entry : getWrapper().getAttributeMap().entrySet()) {
            runtimeConfigurable.setAttribute(entry.getKey(), (String) entry.getValue());
        }
        runtimeConfigurable.addText(getWrapper().getText().toString());
        Enumeration<RuntimeConfigurable> children = getWrapper().getChildren();
        while (children.hasMoreElements()) {
            UnknownElement copy = ((UnknownElement) children.nextElement().getProxy()).copy(project);
            runtimeConfigurable.addChild(copy.getWrapper());
            unknownElement.addChild(copy);
        }
        return unknownElement;
    }
}
