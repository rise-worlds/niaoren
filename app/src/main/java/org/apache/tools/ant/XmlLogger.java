package org.apache.tools.ant;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Stack;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.tools.ant.util.DOMElementWriter;
import org.apache.tools.ant.util.FileUtils;
import org.apache.tools.ant.util.StringUtils;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/* loaded from: classes2.dex */
public class XmlLogger implements BuildLogger {
    private static final String BUILD_TAG = "build";
    private static final String ERROR_ATTR = "error";
    private static final String LOCATION_ATTR = "location";
    private static final String MESSAGE_TAG = "message";
    private static final String NAME_ATTR = "name";
    private static final String PRIORITY_ATTR = "priority";
    private static final String STACKTRACE_TAG = "stacktrace";
    private static final String TARGET_TAG = "target";
    private static final String TASK_TAG = "task";
    private static final String TIME_ATTR = "time";
    private static DocumentBuilder builder = getDocumentBuilder();
    private PrintStream outStream;
    private int msgOutputLevel = 4;
    private Document doc = builder.newDocument();
    private Hashtable<Task, TimedElement> tasks = new Hashtable<>();
    private Hashtable<Target, TimedElement> targets = new Hashtable<>();
    private Hashtable<Thread, Stack<TimedElement>> threadStacks = new Hashtable<>();
    private TimedElement buildElement = null;

    @Override // org.apache.tools.ant.BuildLogger
    public void setEmacsMode(boolean z) {
    }

    @Override // org.apache.tools.ant.BuildLogger
    public void setErrorPrintStream(PrintStream printStream) {
    }

    private static DocumentBuilder getDocumentBuilder() {
        try {
            return DocumentBuilderFactory.newInstance().newDocumentBuilder();
        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class TimedElement {
        private Element element;
        private long startTime;

        private TimedElement() {
        }

        public String toString() {
            return this.element.getTagName() + ":" + this.element.getAttribute("name");
        }
    }

    @Override // org.apache.tools.ant.BuildListener
    public void buildStarted(BuildEvent buildEvent) {
        this.buildElement = new TimedElement();
        this.buildElement.startTime = System.currentTimeMillis();
        this.buildElement.element = this.doc.createElement(BUILD_TAG);
    }

    @Override // org.apache.tools.ant.BuildListener
    public void buildFinished(BuildEvent buildEvent) {
        Throwable th;
        OutputStreamWriter outputStreamWriter;
        IOException e;
        this.buildElement.element.setAttribute(TIME_ATTR, DefaultLogger.formatTime(System.currentTimeMillis() - this.buildElement.startTime));
        if (buildEvent.getException() != null) {
            this.buildElement.element.setAttribute("error", buildEvent.getException().toString());
            CDATASection createCDATASection = this.doc.createCDATASection(StringUtils.getStackTrace(buildEvent.getException()));
            Element createElement = this.doc.createElement(STACKTRACE_TAG);
            createElement.appendChild(createCDATASection);
            synchronizedAppend(this.buildElement.element, createElement);
        }
        String property = getProperty(buildEvent, "XmlLogger.file", "log.xml");
        String property2 = getProperty(buildEvent, "ant.XmlLogger.stylesheet.uri", "log.xsl");
        try {
            try {
                OutputStream outputStream = this.outStream;
                if (outputStream == null) {
                    outputStream = new FileOutputStream(property);
                }
                outputStreamWriter = new OutputStreamWriter(outputStream, "UTF8");
            } catch (Throwable th2) {
                th = th2;
                outputStreamWriter = null;
            }
        } catch (IOException e2) {
            e = e2;
        }
        try {
            outputStreamWriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            if (property2.length() > 0) {
                outputStreamWriter.write("<?xml-stylesheet type=\"text/xsl\" href=\"" + property2 + "\"?>\n\n");
            }
            new DOMElementWriter().write(this.buildElement.element, outputStreamWriter, 0, "\t");
            outputStreamWriter.flush();
            FileUtils.close(outputStreamWriter);
            this.buildElement = null;
        } catch (IOException e3) {
            e = e3;
            throw new BuildException("Unable to write log file", e);
        } catch (Throwable th3) {
            th = th3;
            FileUtils.close(outputStreamWriter);
            throw th;
        }
    }

    private String getProperty(BuildEvent buildEvent, String str, String str2) {
        return (buildEvent == null || buildEvent.getProject() == null || buildEvent.getProject().getProperty(str) == null) ? str2 : buildEvent.getProject().getProperty(str);
    }

    private Stack<TimedElement> getStack() {
        Stack<TimedElement> stack = this.threadStacks.get(Thread.currentThread());
        if (stack != null) {
            return stack;
        }
        Stack<TimedElement> stack2 = new Stack<>();
        this.threadStacks.put(Thread.currentThread(), stack2);
        return stack2;
    }

    @Override // org.apache.tools.ant.BuildListener
    public void targetStarted(BuildEvent buildEvent) {
        Target target = buildEvent.getTarget();
        TimedElement timedElement = new TimedElement();
        timedElement.startTime = System.currentTimeMillis();
        timedElement.element = this.doc.createElement(TARGET_TAG);
        timedElement.element.setAttribute("name", target.getName());
        this.targets.put(target, timedElement);
        getStack().push(timedElement);
    }

    @Override // org.apache.tools.ant.BuildListener
    public void targetFinished(BuildEvent buildEvent) {
        Target target = buildEvent.getTarget();
        TimedElement timedElement = this.targets.get(target);
        if (timedElement != null) {
            timedElement.element.setAttribute(TIME_ATTR, DefaultLogger.formatTime(System.currentTimeMillis() - timedElement.startTime));
            TimedElement timedElement2 = null;
            Stack<TimedElement> stack = getStack();
            if (!stack.empty()) {
                TimedElement pop = stack.pop();
                if (pop != timedElement) {
                    throw new RuntimeException("Mismatch - popped element = " + pop + " finished target element = " + timedElement);
                } else if (!stack.empty()) {
                    timedElement2 = stack.peek();
                }
            }
            if (timedElement2 == null) {
                synchronizedAppend(this.buildElement.element, timedElement.element);
            } else {
                synchronizedAppend(timedElement2.element, timedElement.element);
            }
        }
        this.targets.remove(target);
    }

    @Override // org.apache.tools.ant.BuildListener
    public void taskStarted(BuildEvent buildEvent) {
        TimedElement timedElement = new TimedElement();
        timedElement.startTime = System.currentTimeMillis();
        timedElement.element = this.doc.createElement(TASK_TAG);
        Task task = buildEvent.getTask();
        String taskName = buildEvent.getTask().getTaskName();
        if (taskName == null) {
            taskName = "";
        }
        timedElement.element.setAttribute("name", taskName);
        timedElement.element.setAttribute("location", buildEvent.getTask().getLocation().toString());
        this.tasks.put(task, timedElement);
        getStack().push(timedElement);
    }

    @Override // org.apache.tools.ant.BuildListener
    public void taskFinished(BuildEvent buildEvent) {
        TimedElement pop;
        Task task = buildEvent.getTask();
        TimedElement timedElement = this.tasks.get(task);
        if (timedElement != null) {
            timedElement.element.setAttribute(TIME_ATTR, DefaultLogger.formatTime(System.currentTimeMillis() - timedElement.startTime));
            Target owningTarget = task.getOwningTarget();
            TimedElement timedElement2 = null;
            if (owningTarget != null) {
                timedElement2 = this.targets.get(owningTarget);
            }
            if (timedElement2 == null) {
                synchronizedAppend(this.buildElement.element, timedElement.element);
            } else {
                synchronizedAppend(timedElement2.element, timedElement.element);
            }
            Stack<TimedElement> stack = getStack();
            if (stack.empty() || (pop = stack.pop()) == timedElement) {
                this.tasks.remove(task);
                return;
            }
            throw new RuntimeException("Mismatch - popped element = " + pop + " finished task element = " + timedElement);
        }
        throw new RuntimeException("Unknown task " + task + " not in " + this.tasks);
    }

    private TimedElement getTaskElement(Task task) {
        TimedElement timedElement = this.tasks.get(task);
        if (timedElement != null) {
            return timedElement;
        }
        Enumeration<Task> keys = this.tasks.keys();
        while (keys.hasMoreElements()) {
            Task nextElement = keys.nextElement();
            if ((nextElement instanceof UnknownElement) && ((UnknownElement) nextElement).getTask() == task) {
                return this.tasks.get(nextElement);
            }
        }
        return null;
    }

    @Override // org.apache.tools.ant.BuildListener
    public void messageLogged(BuildEvent buildEvent) {
        String str;
        int priority = buildEvent.getPriority();
        if (priority <= this.msgOutputLevel) {
            Element createElement = this.doc.createElement(MESSAGE_TAG);
            switch (priority) {
                case 0:
                    str = "error";
                    break;
                case 1:
                    str = "warn";
                    break;
                case 2:
                    str = "info";
                    break;
                default:
                    str = "debug";
                    break;
            }
            createElement.setAttribute("priority", str);
            Throwable exception = buildEvent.getException();
            if (4 <= this.msgOutputLevel && exception != null) {
                CDATASection createCDATASection = this.doc.createCDATASection(StringUtils.getStackTrace(exception));
                Element createElement2 = this.doc.createElement(STACKTRACE_TAG);
                createElement2.appendChild(createCDATASection);
                synchronizedAppend(this.buildElement.element, createElement2);
            }
            createElement.appendChild(this.doc.createCDATASection(buildEvent.getMessage()));
            TimedElement timedElement = null;
            Task task = buildEvent.getTask();
            Target target = buildEvent.getTarget();
            if (task != null) {
                timedElement = getTaskElement(task);
            }
            if (timedElement == null && target != null) {
                timedElement = this.targets.get(target);
            }
            if (timedElement != null) {
                synchronizedAppend(timedElement.element, createElement);
            } else {
                synchronizedAppend(this.buildElement.element, createElement);
            }
        }
    }

    @Override // org.apache.tools.ant.BuildLogger
    public void setMessageOutputLevel(int i) {
        this.msgOutputLevel = i;
    }

    @Override // org.apache.tools.ant.BuildLogger
    public void setOutputPrintStream(PrintStream printStream) {
        this.outStream = new PrintStream((OutputStream) printStream, true);
    }

    private void synchronizedAppend(Node node, Node node2) {
        synchronized (node) {
            node.appendChild(node2);
        }
    }
}
