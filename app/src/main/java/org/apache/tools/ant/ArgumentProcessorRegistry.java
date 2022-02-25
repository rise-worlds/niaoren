package org.apache.tools.ant;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import org.apache.tools.ant.util.LoaderUtils;

/* loaded from: classes2.dex */
public class ArgumentProcessorRegistry {
    private static final String SERVICE_ID = "META-INF/services/org.apache.tools.ant.ArgumentProcessor";
    private List<ArgumentProcessor> processors = new ArrayList();
    private static final String DEBUG_ARGUMENT_PROCESSOR_REPOSITORY = "ant.argument-processor-repo.debug";
    private static final boolean DEBUG = "true".equals(System.getProperty(DEBUG_ARGUMENT_PROCESSOR_REPOSITORY));
    private static ArgumentProcessorRegistry instance = new ArgumentProcessorRegistry();

    public static ArgumentProcessorRegistry getInstance() {
        return instance;
    }

    private ArgumentProcessorRegistry() {
        collectArgumentProcessors();
    }

    public List<ArgumentProcessor> getProcessors() {
        return this.processors;
    }

    private void collectArgumentProcessors() {
        try {
            ClassLoader contextClassLoader = LoaderUtils.getContextClassLoader();
            if (contextClassLoader != null) {
                Enumeration<URL> resources = contextClassLoader.getResources(SERVICE_ID);
                while (resources.hasMoreElements()) {
                    URLConnection openConnection = resources.nextElement().openConnection();
                    openConnection.setUseCaches(false);
                    registerArgumentProcessor(getProcessorByService(openConnection.getInputStream()));
                }
            }
            InputStream systemResourceAsStream = ClassLoader.getSystemResourceAsStream(SERVICE_ID);
            if (systemResourceAsStream != null) {
                registerArgumentProcessor(getProcessorByService(systemResourceAsStream));
            }
        } catch (Exception e) {
            PrintStream printStream = System.err;
            printStream.println("Unable to load ArgumentProcessor from service META-INF/services/org.apache.tools.ant.ArgumentProcessor (" + e.getClass().getName() + ": " + e.getMessage() + ")");
            if (DEBUG) {
                e.printStackTrace(System.err);
            }
        }
    }

    public void registerArgumentProcessor(String str) throws BuildException {
        registerArgumentProcessor(getProcessor(str));
    }

    public void registerArgumentProcessor(Class<? extends ArgumentProcessor> cls) throws BuildException {
        registerArgumentProcessor(getProcessor(cls));
    }

    /* JADX WARN: Multi-variable type inference failed */
    private ArgumentProcessor getProcessor(String str) {
        try {
            return getProcessor((Class<? extends ArgumentProcessor>) Class.forName(str));
        } catch (ClassNotFoundException e) {
            throw new BuildException("Argument processor class " + str + " was not found", e);
        }
    }

    private ArgumentProcessor getProcessor(Class<? extends ArgumentProcessor> cls) {
        try {
            return (ArgumentProcessor) cls.getConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception e) {
            throw new BuildException("The argument processor class" + cls.getClass().getName() + " could not be instanciated with a default constructor", e);
        }
    }

    public void registerArgumentProcessor(ArgumentProcessor argumentProcessor) {
        if (argumentProcessor != null) {
            this.processors.add(argumentProcessor);
            if (DEBUG) {
                PrintStream printStream = System.out;
                printStream.println("Argument processor " + argumentProcessor.getClass().getName() + " registered.");
            }
        }
    }

    private ArgumentProcessor getProcessorByService(InputStream inputStream) throws IOException {
        Throwable th;
        InputStreamReader inputStreamReader;
        try {
            try {
                inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            } catch (UnsupportedEncodingException unused) {
                inputStreamReader = new InputStreamReader(inputStream);
            }
            try {
                String readLine = new BufferedReader(inputStreamReader).readLine();
                if (readLine == null || "".equals(readLine)) {
                    try {
                        inputStreamReader.close();
                    } catch (IOException unused2) {
                    }
                    return null;
                }
                ArgumentProcessor processor = getProcessor(readLine);
                try {
                    inputStreamReader.close();
                } catch (IOException unused3) {
                }
                return processor;
            } catch (Throwable th2) {
                th = th2;
                try {
                    inputStreamReader.close();
                } catch (IOException unused4) {
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            inputStreamReader = null;
            inputStreamReader.close();
            throw th;
        }
    }
}
