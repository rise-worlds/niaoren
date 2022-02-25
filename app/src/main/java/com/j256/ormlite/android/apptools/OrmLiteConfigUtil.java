package com.j256.ormlite.android.apptools;

import com.lody.virtual.client.ipc.ServiceManagerNative;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import p110z1.Consts;
import p110z1.DatabaseField;
import p110z1.DatabaseFieldConfig;
import p110z1.DatabaseTable;
import p110z1.DatabaseTableConfig;
import p110z1.DatabaseTableConfigLoader;
import p110z1.DatabaseType;
import p110z1.ForeignCollectionField;
import p110z1.SqliteAndroidDatabaseType;

/* loaded from: classes.dex */
public class OrmLiteConfigUtil {

    /* renamed from: a */
    protected static final String f9395a = "res";

    /* renamed from: b */
    protected static final String f9396b = "raw";

    /* renamed from: c */
    protected static int f9397c = 20;

    /* renamed from: d */
    private static final DatabaseType f9398d = new SqliteAndroidDatabaseType();

    public static void main(String[] strArr) throws Exception {
        if (strArr.length == 1) {
            m19852a(strArr[0]);
            return;
        }
        throw new IllegalArgumentException("Main can take a single file-name argument.");
    }

    /* renamed from: a */
    public static void m19852a(String str) throws SQLException, IOException {
        ArrayList arrayList = new ArrayList();
        m19850a(arrayList, new File(Consts.f23430h), 0);
        m19851a(str, (Class[]) arrayList.toArray(new Class[arrayList.size()]));
    }

    /* renamed from: a */
    public static void m19851a(String str, Class<?>[] clsArr) throws SQLException, IOException {
        File b = m19849b(new File(Consts.f23430h));
        if (b == null) {
            System.err.println("Could not find raw directory");
        } else {
            m19856a(new File(b, str), clsArr);
        }
    }

    /* renamed from: a */
    public static void m19858a(File file) throws SQLException, IOException {
        m19857a(file, new File(Consts.f23430h));
    }

    /* renamed from: a */
    public static void m19857a(File file, File file2) throws SQLException, IOException {
        ArrayList arrayList = new ArrayList();
        m19850a(arrayList, file2, 0);
        m19856a(file, (Class[]) arrayList.toArray(new Class[arrayList.size()]));
    }

    /* renamed from: a */
    public static void m19856a(File file, Class<?>[] clsArr) throws SQLException, IOException {
        PrintStream printStream = System.out;
        printStream.println("Writing configurations to " + file.getAbsolutePath());
        m19854a(new FileOutputStream(file), clsArr);
    }

    /* renamed from: a */
    public static void m19855a(OutputStream outputStream, File file) throws SQLException, IOException {
        ArrayList arrayList = new ArrayList();
        m19850a(arrayList, file, 0);
        m19854a(outputStream, (Class[]) arrayList.toArray(new Class[arrayList.size()]));
    }

    /* renamed from: a */
    public static void m19854a(OutputStream outputStream, Class<?>[] clsArr) throws SQLException, IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream), 4096);
        try {
            m19860a(bufferedWriter);
            for (Class<?> cls : clsArr) {
                m19859a(bufferedWriter, cls);
            }
            System.out.println("Done.");
        } finally {
            bufferedWriter.close();
        }
    }

    /* renamed from: b */
    protected static File m19849b(File file) {
        for (int i = 0; file != null && i < 20; i++) {
            File d = m19847d(file);
            if (d != null) {
                return d;
            }
            file = file.getParentFile();
        }
        return null;
    }

    /* renamed from: a */
    private static void m19860a(BufferedWriter bufferedWriter) throws IOException {
        bufferedWriter.append('#');
        bufferedWriter.newLine();
        bufferedWriter.append("# generated on ").append((CharSequence) new SimpleDateFormat("yyyy/MM/dd hh:mm:ss").format(new Date()));
        bufferedWriter.newLine();
        bufferedWriter.append('#');
        bufferedWriter.newLine();
    }

    /* renamed from: a */
    private static void m19850a(List<Class<?>> list, File file, int i) throws SQLException, IOException {
        File[] listFiles;
        String name;
        Class<?>[] declaredClasses;
        for (File file2 : file.listFiles()) {
            if (file2.isDirectory()) {
                if (i < f9397c) {
                    m19850a(list, file2, i + 1);
                }
            } else if (file2.getName().endsWith(".java")) {
                String c = m19848c(file2);
                if (c == null) {
                    System.err.println("Could not find package name for: " + file2);
                } else {
                    try {
                        Class<?> cls = Class.forName(c + Consts.f23430h + file2.getName().substring(0, name.length() - 5));
                        if (m19853a(cls)) {
                            list.add(cls);
                        }
                        try {
                            for (Class<?> cls2 : cls.getDeclaredClasses()) {
                                if (m19853a(cls2)) {
                                    list.add(cls2);
                                }
                            }
                        } catch (Throwable th) {
                            System.err.println("Could not load inner classes for: " + cls);
                            System.err.println("     " + th);
                        }
                    } catch (Throwable th2) {
                        System.err.println("Could not load class file for: " + file2);
                        System.err.println("     " + th2);
                    }
                }
            }
        }
    }

    /* renamed from: a */
    private static void m19859a(BufferedWriter bufferedWriter, Class<?> cls) throws SQLException, IOException {
        String b = DatabaseTableConfig.m187b(cls);
        ArrayList arrayList = new ArrayList();
        for (Class<?> cls2 = cls; cls2 != null; cls2 = cls2.getSuperclass()) {
            try {
                for (Field field : cls2.getDeclaredFields()) {
                    DatabaseFieldConfig a = DatabaseFieldConfig.m819a(f9398d, b, field);
                    if (a != null) {
                        arrayList.add(a);
                    }
                }
            } catch (Error e) {
                System.err.println("Skipping " + cls + " because we got an error finding its definition: " + e.getMessage());
                return;
            }
        }
        if (arrayList.isEmpty()) {
            System.out.println("Skipping " + cls + " because no annotated fields found");
            return;
        }
        DatabaseTableConfigLoader.m180a(bufferedWriter, new DatabaseTableConfig(cls, b, arrayList));
        bufferedWriter.append("#################################");
        bufferedWriter.newLine();
        System.out.println("Wrote config for " + cls);
    }

    /* renamed from: a */
    private static boolean m19853a(Class<?> cls) {
        Field[] declaredFields;
        while (cls != null) {
            if (cls.getAnnotation(DatabaseTable.class) != null) {
                return true;
            }
            try {
                for (Field field : cls.getDeclaredFields()) {
                    if (!(field.getAnnotation(DatabaseField.class) == null && field.getAnnotation(ForeignCollectionField.class) == null)) {
                        return true;
                    }
                }
                try {
                    cls = cls.getSuperclass();
                } catch (Throwable th) {
                    System.err.println("Could not get super class for: " + cls);
                    System.err.println("     " + th);
                    return false;
                }
            } catch (Throwable th2) {
                System.err.println("Could not load get delcared fields from: " + cls);
                System.err.println("     " + th2);
                return false;
            }
        }
        return false;
    }

    /* renamed from: c */
    private static String m19848c(File file) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        while (true) {
            try {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    return null;
                } else if (readLine.contains(ServiceManagerNative.PACKAGE)) {
                    String[] split = readLine.split("[ \t;]");
                    if (split.length > 1 && split[0].equals(ServiceManagerNative.PACKAGE)) {
                        return split[1];
                    }
                }
            } finally {
                bufferedReader.close();
            }
        }
    }

    /* renamed from: d */
    private static File m19847d(File file) {
        File[] listFiles;
        for (File file2 : file.listFiles()) {
            if (file2.getName().equals(f9395a) && file2.isDirectory()) {
                File[] listFiles2 = file2.listFiles(new FileFilter() { // from class: com.j256.ormlite.android.apptools.OrmLiteConfigUtil.1
                    @Override // java.io.FileFilter
                    public boolean accept(File file3) {
                        return file3.getName().equals(OrmLiteConfigUtil.f9396b) && file3.isDirectory();
                    }
                });
                if (listFiles2.length == 1) {
                    return listFiles2[0];
                }
            }
        }
        return null;
    }
}
