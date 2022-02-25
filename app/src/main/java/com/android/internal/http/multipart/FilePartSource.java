package com.android.internal.http.multipart;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: assets/org.apache.http.legacy.boot */
public class FilePartSource implements PartSource {
    private File file;
    private String fileName;

    public FilePartSource(File file) throws FileNotFoundException {
        this.file = null;
        this.fileName = null;
        this.file = file;
        if (file == null) {
            return;
        }
        if (!file.isFile()) {
            throw new FileNotFoundException("File is not a normal file.");
        } else if (file.canRead()) {
            this.fileName = file.getName();
        } else {
            throw new FileNotFoundException("File is not readable.");
        }
    }

    public FilePartSource(String fileName, File file) throws FileNotFoundException {
        this(file);
        if (fileName != null) {
            this.fileName = fileName;
        }
    }

    @Override // com.android.internal.http.multipart.PartSource
    public long getLength() {
        if (this.file != null) {
            return this.file.length();
        }
        return 0L;
    }

    @Override // com.android.internal.http.multipart.PartSource
    public String getFileName() {
        return this.fileName == null ? "noname" : this.fileName;
    }

    @Override // com.android.internal.http.multipart.PartSource
    public InputStream createInputStream() throws IOException {
        if (this.file != null) {
            return new FileInputStream(this.file);
        }
        return new ByteArrayInputStream(new byte[0]);
    }
}
