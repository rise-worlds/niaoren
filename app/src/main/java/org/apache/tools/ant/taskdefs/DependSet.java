package org.apache.tools.ant.taskdefs;

import com.ctetin.expandabletextviewlibrary.ExpandableTextView;
import com.stripe.android.RequestOptions;
import java.io.File;
import java.util.Iterator;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.types.FileList;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.types.Path;
import org.apache.tools.ant.types.Resource;
import org.apache.tools.ant.types.ResourceCollection;
import org.apache.tools.ant.types.TimeComparison;
import org.apache.tools.ant.types.resources.Resources;
import org.apache.tools.ant.types.resources.Restrict;
import org.apache.tools.ant.types.resources.Union;
import org.apache.tools.ant.types.resources.comparators.Date;
import org.apache.tools.ant.types.resources.comparators.ResourceComparator;
import org.apache.tools.ant.types.resources.comparators.Reverse;
import org.apache.tools.ant.types.resources.selectors.Exists;
import org.apache.tools.ant.types.resources.selectors.Not;
import org.apache.tools.ant.types.resources.selectors.ResourceSelector;

/* loaded from: classes2.dex */
public class DependSet extends MatchingTask {
    private Union sources = null;
    private Path targets = null;
    private boolean verbose;
    private static final ResourceSelector NOT_EXISTS = new Not(new Exists());
    private static final ResourceComparator DATE = new Date();
    private static final ResourceComparator REVERSE_DATE = new Reverse(DATE);

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static final class NonExistent extends Restrict {
        private NonExistent(ResourceCollection resourceCollection) {
            super.add(resourceCollection);
            super.add(DependSet.NOT_EXISTS);
        }
    }

    /* loaded from: classes2.dex */
    private static final class HideMissingBasedir implements ResourceCollection {

        /* renamed from: fs */
        private FileSet f14747fs;

        @Override // org.apache.tools.ant.types.ResourceCollection
        public boolean isFilesystemOnly() {
            return true;
        }

        private HideMissingBasedir(FileSet fileSet) {
            this.f14747fs = fileSet;
        }

        @Override // org.apache.tools.ant.types.ResourceCollection, java.lang.Iterable
        public Iterator<Resource> iterator() {
            return basedirExists() ? this.f14747fs.iterator() : Resources.EMPTY_ITERATOR;
        }

        @Override // org.apache.tools.ant.types.ResourceCollection
        public int size() {
            if (basedirExists()) {
                return this.f14747fs.size();
            }
            return 0;
        }

        private boolean basedirExists() {
            File dir = this.f14747fs.getDir();
            return dir == null || dir.exists();
        }
    }

    public synchronized Union createSources() {
        this.sources = this.sources == null ? new Union() : this.sources;
        return this.sources;
    }

    public void addSrcfileset(FileSet fileSet) {
        createSources().add(fileSet);
    }

    public void addSrcfilelist(FileList fileList) {
        createSources().add(fileList);
    }

    public synchronized Path createTargets() {
        this.targets = this.targets == null ? new Path(getProject()) : this.targets;
        return this.targets;
    }

    public void addTargetfileset(FileSet fileSet) {
        createTargets().add(new HideMissingBasedir(fileSet));
    }

    public void addTargetfilelist(FileList fileList) {
        createTargets().add(fileList);
    }

    public void setVerbose(boolean z) {
        this.verbose = z;
    }

    @Override // org.apache.tools.ant.Task
    public void execute() throws BuildException {
        Union union = this.sources;
        if (union == null) {
            throw new BuildException("At least one set of source resources must be specified");
        } else if (this.targets == null) {
            throw new BuildException("At least one set of target files must be specified");
        } else if (union.size() > 0 && this.targets.size() > 0 && !uptodate(this.sources, this.targets)) {
            log("Deleting all target files.", 3);
            if (this.verbose) {
                String[] list = this.targets.list();
                for (int i = 0; i < list.length; i++) {
                    log("Deleting " + list[i]);
                }
            }
            Delete delete = new Delete();
            delete.bindToOwner(this);
            delete.add(this.targets);
            delete.perform();
        }
    }

    private boolean uptodate(ResourceCollection resourceCollection, ResourceCollection resourceCollection2) {
        org.apache.tools.ant.types.resources.selectors.Date date = new org.apache.tools.ant.types.resources.selectors.Date();
        date.setMillis(System.currentTimeMillis());
        date.setWhen(TimeComparison.AFTER);
        date.setGranularity(0L);
        logFuture(this.targets, date);
        ResourceCollection nonExistent = new NonExistent(this.targets);
        int size = nonExistent.size();
        if (size > 0) {
            log(size + " nonexistent targets", 3);
            logMissing(nonExistent, "target");
            return false;
        }
        Resource oldest = getOldest(this.targets);
        logWithModificationTime(oldest, "oldest target file");
        logFuture(this.sources, date);
        ResourceCollection nonExistent2 = new NonExistent(this.sources);
        int size2 = nonExistent2.size();
        if (size2 > 0) {
            log(size2 + " nonexistent sources", 3);
            logMissing(nonExistent2, RequestOptions.f12301a);
            return false;
        }
        Resource newest = getNewest(this.sources);
        logWithModificationTime(newest, "newest source");
        return oldest.getLastModified() >= newest.getLastModified();
    }

    private void logFuture(ResourceCollection resourceCollection, ResourceSelector resourceSelector) {
        Restrict restrict = new Restrict();
        restrict.add(resourceSelector);
        restrict.add(resourceCollection);
        Iterator<Resource> it = restrict.iterator();
        while (it.hasNext()) {
            log("Warning: " + it.next() + " modified in the future.", 1);
        }
    }

    private Resource getXest(ResourceCollection resourceCollection, ResourceComparator resourceComparator) {
        Iterator<Resource> it = resourceCollection.iterator();
        if (!it.hasNext()) {
            return null;
        }
        Resource next = it.next();
        while (it.hasNext()) {
            Resource next2 = it.next();
            if (resourceComparator.compare(next, next2) < 0) {
                next = next2;
            }
        }
        return next;
    }

    private Resource getOldest(ResourceCollection resourceCollection) {
        return getXest(resourceCollection, REVERSE_DATE);
    }

    private Resource getNewest(ResourceCollection resourceCollection) {
        return getXest(resourceCollection, DATE);
    }

    private void logWithModificationTime(Resource resource, String str) {
        log(resource.toLongString() + " is " + str + ", modified at " + new java.util.Date(resource.getLastModified()), this.verbose ? 2 : 3);
    }

    private void logMissing(ResourceCollection resourceCollection, String str) {
        if (this.verbose) {
            Iterator<Resource> it = resourceCollection.iterator();
            while (it.hasNext()) {
                log("Expected " + str + ExpandableTextView.f6958c + it.next().toLongString() + " is missing.");
            }
        }
    }
}
