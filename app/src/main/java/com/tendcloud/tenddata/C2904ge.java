package com.tendcloud.tenddata;

import android.content.Context;
import android.hardware.SensorManager;
import android.opengl.Matrix;
import android.os.Environment;
import android.util.Base64;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;

/* compiled from: td */
/* renamed from: com.tendcloud.tenddata.ge */
/* loaded from: classes2.dex */
public class C2904ge {

    /* renamed from: a */
    public static final String f14048a = "Myna";

    /* renamed from: a */
    public static String m15632a(Context context, String str) {
        if (!(context == null || str == null || str.isEmpty())) {
            try {
                InputStream open = context.getAssets().open(str);
                byte[] bArr = new byte[open.available()];
                open.read(bArr);
                open.close();
                return new String(bArr);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return null;
    }

    /* renamed from: a */
    public static Object m15629a(String str) {
        ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(Base64.decode(str, 0)));
        Object readObject = objectInputStream.readObject();
        objectInputStream.close();
        return readObject;
    }

    /* renamed from: a */
    public static String m15630a(Serializable serializable) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(serializable);
        objectOutputStream.close();
        return Base64.encodeToString(byteArrayOutputStream.toByteArray(), 0);
    }

    /* renamed from: a */
    public static void m15631a(Context context, String str, String str2) {
        if (context != null && !str.isEmpty() && str2 != null && !str2.isEmpty()) {
            File file = new File(context.getFilesDir(), str2);
            if ("mounted".equals(Environment.getExternalStorageState())) {
                File file2 = new File(Environment.getExternalStorageDirectory() + "/rHAR/");
                if (!file2.exists()) {
                    file2.mkdir();
                }
                file = new File(file2, str2);
            }
            if (file.exists()) {
                file.delete();
            }
            try {
                FileWriter fileWriter = new FileWriter(file);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write(str);
                bufferedWriter.close();
                fileWriter.close();
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    /* renamed from: b */
    public static byte[] m15628b(String str) {
        Throwable th;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Deflater deflater = new Deflater(9, true);
        DeflaterOutputStream deflaterOutputStream = null;
        try {
            try {
                DeflaterOutputStream deflaterOutputStream2 = new DeflaterOutputStream(byteArrayOutputStream, deflater);
                try {
                    deflaterOutputStream2.write(str.getBytes("UTF-8"));
                    deflaterOutputStream2.close();
                } catch (Throwable th2) {
                    th = th2;
                    deflaterOutputStream = deflaterOutputStream2;
                    try {
                        th.printStackTrace();
                        if (deflaterOutputStream != null) {
                            deflaterOutputStream.close();
                        }
                        deflater.end();
                        return byteArrayOutputStream.toByteArray();
                    } catch (Throwable th3) {
                        if (deflaterOutputStream != null) {
                            try {
                                deflaterOutputStream.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        throw th3;
                    }
                }
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        } catch (Throwable th4) {
            th = th4;
        }
        deflater.end();
        return byteArrayOutputStream.toByteArray();
    }

    public static void calculateWorldAcce(C2897fy fyVar) {
        try {
            float[] fArr = new float[16];
            float[] fArr2 = new float[16];
            float[] fArr3 = new float[3];
            if (((int) fyVar.f14019f[0]) == 0 && ((int) fyVar.f14019f[1]) == 0 && ((int) fyVar.f14019f[2]) == 0) {
                SensorManager.getRotationMatrix(fArr, fArr2, fyVar.f14016c, fyVar.f14017d);
            } else {
                SensorManager.getRotationMatrixFromVector(fArr, fyVar.f14019f);
            }
            SensorManager.getOrientation(fArr, fArr3);
            System.arraycopy(fArr3, 0, fyVar.f14018e, 0, 3);
            float[] fArr4 = new float[4];
            float[] fArr5 = new float[4];
            float[] fArr6 = new float[16];
            System.arraycopy(fyVar.f14014a, 0, fArr4, 0, 3);
            fArr4[3] = 0.0f;
            Matrix.invertM(fArr6, 0, fArr, 0);
            Matrix.multiplyMV(fArr5, 0, fArr6, 0, fArr4, 0);
            System.arraycopy(fArr5, 0, fyVar.f14020g, 0, 3);
        } catch (Throwable th) {
            C2933hb.postSDKError(th);
        }
    }
}
