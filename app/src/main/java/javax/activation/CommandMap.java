package javax.activation;

/* loaded from: classes2.dex */
public abstract class CommandMap {
    private static CommandMap defaultCommandMap;

    public abstract DataContentHandler createDataContentHandler(String str);

    public abstract CommandInfo[] getAllCommands(String str);

    public abstract CommandInfo getCommand(String str, String str2);

    public String[] getMimeTypes() {
        return null;
    }

    public abstract CommandInfo[] getPreferredCommands(String str);

    public static CommandMap getDefaultCommandMap() {
        if (defaultCommandMap == null) {
            defaultCommandMap = new MailcapCommandMap();
        }
        return defaultCommandMap;
    }

    public static void setDefaultCommandMap(CommandMap commandMap) {
        SecurityManager securityManager = System.getSecurityManager();
        if (securityManager != null) {
            try {
                securityManager.checkSetFactory();
            } catch (SecurityException e) {
                if (CommandMap.class.getClassLoader() != commandMap.getClass().getClassLoader()) {
                    throw e;
                }
            }
        }
        defaultCommandMap = commandMap;
    }

    public CommandInfo[] getPreferredCommands(String str, DataSource dataSource) {
        return getPreferredCommands(str);
    }

    public CommandInfo[] getAllCommands(String str, DataSource dataSource) {
        return getAllCommands(str);
    }

    public CommandInfo getCommand(String str, String str2, DataSource dataSource) {
        return getCommand(str, str2);
    }

    public DataContentHandler createDataContentHandler(String str, DataSource dataSource) {
        return createDataContentHandler(str);
    }
}
