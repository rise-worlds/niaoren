package org.apache.tools.ant.util.regexp;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.MagicNames;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.util.ClasspathUtils;

/* loaded from: classes2.dex */
public class RegexpMatcherFactory {
    public RegexpMatcher newRegexpMatcher() throws BuildException {
        return newRegexpMatcher(null);
    }

    public RegexpMatcher newRegexpMatcher(Project project) throws BuildException {
        String str;
        if (project == null) {
            str = System.getProperty(MagicNames.REGEXP_IMPL);
        } else {
            str = project.getProperty(MagicNames.REGEXP_IMPL);
        }
        if (str != null) {
            return createInstance(str);
        }
        return new Jdk14RegexpMatcher();
    }

    protected RegexpMatcher createInstance(String str) throws BuildException {
        return (RegexpMatcher) ClasspathUtils.newInstance(str, RegexpMatcherFactory.class.getClassLoader(), RegexpMatcher.class);
    }

    protected void testAvailability(String str) throws BuildException {
        try {
            Class.forName(str);
        } catch (Throwable th) {
            throw new BuildException(th);
        }
    }

    public static boolean regexpMatcherPresent(Project project) {
        try {
            new RegexpMatcherFactory().newRegexpMatcher(project);
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }
}
