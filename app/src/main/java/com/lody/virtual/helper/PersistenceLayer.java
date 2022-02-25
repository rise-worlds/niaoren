package com.lody.virtual.helper;

import android.os.Parcel;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/* loaded from: classes.dex */
public abstract class PersistenceLayer {
    private File mPersistenceFile;

    public abstract int getCurrentVersion();

    public void onPersistenceFileDamage() {
    }

    public abstract void readPersistenceData(Parcel parcel, int i);

    public boolean verifyMagic(Parcel parcel) {
        return true;
    }

    public void writeMagic(Parcel parcel) {
    }

    public abstract void writePersistenceData(Parcel parcel);

    public PersistenceLayer(File file) {
        this.mPersistenceFile = file;
    }

    public final File getPersistenceFile() {
        return this.mPersistenceFile;
    }

    public void save() {
        Parcel obtain = Parcel.obtain();
        try {
            try {
                writeMagic(obtain);
                obtain.writeInt(getCurrentVersion());
                writePersistenceData(obtain);
                FileOutputStream fileOutputStream = new FileOutputStream(this.mPersistenceFile);
                fileOutputStream.write(obtain.marshall());
                fileOutputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } finally {
            obtain.recycle();
        }
    }

    public void read() {
        byte[] bArr;
        int read;
        File file = this.mPersistenceFile;
        Parcel obtain = Parcel.obtain();
        try {
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                bArr = new byte[(int) file.length()];
                read = fileInputStream.read(bArr);
                fileInputStream.close();
            } catch (Exception e) {
                if (!(e instanceof FileNotFoundException)) {
                    e.printStackTrace();
                }
            }
            if (read == bArr.length) {
                obtain.unmarshall(bArr, 0, bArr.length);
                obtain.setDataPosition(0);
                if (verifyMagic(obtain)) {
                    readPersistenceData(obtain, obtain.readInt());
                } else {
                    onPersistenceFileDamage();
                    throw new IOException("Invalid persistence file.");
                }
            } else {
                throw new IOException("Unable to read Persistence file.");
            }
        } finally {
            obtain.recycle();
        }
    }
}
