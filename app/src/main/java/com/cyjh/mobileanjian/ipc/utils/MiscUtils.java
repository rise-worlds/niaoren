package com.cyjh.mobileanjian.ipc.utils;

import android.net.http.Headers;
import com.liulishuo.filedownloader.model.ConnectionModel;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import org.apache.http.protocol.HTTP;
import org.apache.tools.ant.taskdefs.Manifest;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class MiscUtils {
    /* JADX WARN: Multi-variable type inference failed */
    public static String sendGet(String str, String str2) {
        Exception e;
        String str3 = "";
        BufferedReader bufferedReader = null;
        BufferedReader bufferedReader2 = null;
        bufferedReader = null;
        bufferedReader = null;
        try {
            try {
                try {
                    StringBuilder sb = new StringBuilder();
                    sb.append(str);
                    sb.append("?");
                    if (str2 == null) {
                        str2 = "";
                    }
                    sb.append(str2);
                    URLConnection openConnection = new URL(sb.toString()).openConnection();
                    openConnection.setRequestProperty("accept", "*/*");
                    openConnection.setRequestProperty(Headers.CONN_DIRECTIVE, HTTP.CONN_KEEP_ALIVE);
                    openConnection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
                    openConnection.connect();
                    openConnection.getHeaderFields();
                    BufferedReader bufferedReader3 = new BufferedReader(new InputStreamReader(openConnection.getInputStream()));
                    while (true) {
                        try {
                            String readLine = bufferedReader3.readLine();
                            if (readLine == null) {
                                break;
                            }
                            StringBuilder sb2 = new StringBuilder();
                            sb2.append(str3);
                            sb2.append(readLine);
                            str3 = sb2.toString();
                            bufferedReader2 = sb2;
                        } catch (Exception e2) {
                            e = e2;
                            bufferedReader = bufferedReader3;
                            e.printStackTrace();
                            if (bufferedReader != null) {
                                bufferedReader.close();
                                bufferedReader = bufferedReader;
                            }
                            return str3;
                        } catch (Throwable th) {
                            th = th;
                            bufferedReader = bufferedReader3;
                            if (bufferedReader != null) {
                                try {
                                    bufferedReader.close();
                                } catch (Exception e3) {
                                    e3.printStackTrace();
                                }
                            }
                            throw th;
                        }
                    }
                    bufferedReader3.close();
                    bufferedReader = bufferedReader2;
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Exception e4) {
                e = e4;
            }
        } catch (Exception e5) {
            e5.printStackTrace();
        }
        return str3;
    }

    public static String parseId(String str) {
        try {
            return new JSONObject(FileUtils.file2Text(str, "GBK")).getString(ConnectionModel.f10389a);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String parseScriptName(String str) {
        try {
            return new JSONObject(FileUtils.file2Text(str, "GBK")).getString(Manifest.ATTRIBUTE_NAME);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean hasClass(String str) {
        try {
            Class.forName(str);
            return true;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
}
