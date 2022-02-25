package com.cyjh.ddy.base.utils;

import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.ShellUtils;
import com.ctetin.expandabletextviewlibrary.ExpandableTextView;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/* loaded from: classes.dex */
public class FileUtilsEx {

    /* loaded from: classes.dex */
    public interface Callback {
        void addFile(String str);

        boolean isInterrupt();
    }

    /* loaded from: classes.dex */
    public static class FileInfo {
        public boolean isDir;
        public String name;
    }

    /* loaded from: classes.dex */
    public interface Filter {
        boolean accept(FileInfo fileInfo);
    }

    /* loaded from: classes.dex */
    public static class Filters implements Filter {

        /* renamed from: a */
        private List<Filter> f7107a = new ArrayList();

        @Override // com.cyjh.ddy.base.utils.FileUtilsEx.Filter
        public boolean accept(FileInfo fileInfo) {
            for (Filter filter : this.f7107a) {
                if (!filter.accept(fileInfo)) {
                    return false;
                }
            }
            return true;
        }

        public Filters add(Filter filter) {
            this.f7107a.add(filter);
            return this;
        }
    }

    /* loaded from: classes.dex */
    public static class ApkFilter implements Filter {
        @Override // com.cyjh.ddy.base.utils.FileUtilsEx.Filter
        public boolean accept(FileInfo fileInfo) {
            if (!fileInfo.isDir) {
                return fileInfo.name.endsWith(".apk");
            }
            return true;
        }
    }

    /* loaded from: classes.dex */
    public static class NoChinieseNameFilter implements Filter {
        /* renamed from: a */
        private boolean m21876a(String str) {
            return Pattern.compile("[一-龥]").matcher(str).find();
        }

        @Override // com.cyjh.ddy.base.utils.FileUtilsEx.Filter
        public boolean accept(FileInfo fileInfo) {
            return !m21876a(fileInfo.name);
        }
    }

    /* renamed from: a */
    public static List<FileInfo> m21878a(String str) {
        ShellUtils.C0985a a = ShellUtils.m23276a("ls -l " + str, false);
        if (a.f6748a != 0 || a.f6749b.contains("No such file or directory")) {
            return null;
        }
        String[] split = a.f6749b.split("\n");
        if (split.length < 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < split.length; i++) {
            String substring = split[i].substring(split[i].lastIndexOf(ExpandableTextView.f6958c) + 1, split[i].length());
            substring.trim();
            FileInfo fileInfo = new FileInfo();
            fileInfo.name = new String(str + "/" + substring);
            fileInfo.isDir = split[i].startsWith("d");
            arrayList.add(fileInfo);
        }
        return arrayList;
    }

    /* renamed from: a */
    public static void m21877a(String str, Filter filter, Callback callback, boolean z) {
        List<FileInfo> a;
        if (!(!FileUtils.m22222c(str) || (a = m21878a(str)) == null || a.size() == 0)) {
            for (FileInfo fileInfo : a) {
                if (callback.isInterrupt()) {
                    return;
                }
                if (filter.accept(fileInfo)) {
                    if (!fileInfo.isDir) {
                        callback.addFile(fileInfo.name);
                    }
                    if (z && fileInfo.isDir) {
                        m21877a(fileInfo.name, filter, callback, true);
                    }
                }
            }
        }
    }
}
