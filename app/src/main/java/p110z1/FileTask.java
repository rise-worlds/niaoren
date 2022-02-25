package p110z1;

import android.os.AsyncTask;
import com.common.utils.log.LogUtils;
import com.lbd.p054xj.socket.SocketManagerServer;
import com.lbd.p054xj.socket.model.FileBean;
import com.lbd.p054xj.socket.model.MessgeBean;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* renamed from: z1.adh */
/* loaded from: classes3.dex */
public class FileTask {

    /* renamed from: d */
    private static FileTask f15287d;

    /* renamed from: c */
    private Map f15290c = new HashMap();

    /* renamed from: a */
    private int f15288a = 0;

    /* renamed from: b */
    private ArrayList<FileBean> f15289b = new ArrayList<>();

    /* compiled from: FileTask.java */
    /* renamed from: z1.adh$a */
    /* loaded from: classes3.dex */
    class AsyncTaskC3337a extends AsyncTask {

        /* renamed from: a */
        File f15291a;

        /* renamed from: b */
        String f15292b;

        /* renamed from: c */
        FileTask f15293c;

        AsyncTaskC3337a(FileTask adhVar, String str) {
            this.f15293c = adhVar;
            this.f15292b = str;
            this.f15291a = new File(str + File.separator);
        }

        @Override // android.os.AsyncTask
        protected Object doInBackground(Object[] objArr) {
            File[] listFiles;
            ArrayList arrayList = new ArrayList();
            if (this.f15291a.isDirectory() && (listFiles = this.f15291a.listFiles()) != null) {
                for (File file : listFiles) {
                    if (!file.isHidden()) {
                        FileBean fileBean = new FileBean();
                        fileBean.setName(file.getName());
                        fileBean.setPath(file.getAbsolutePath());
                        fileBean.setFileType(aee.m14171e(file));
                        fileBean.setSize(file.length());
                        arrayList.add(fileBean);
                    }
                }
            }
            FileTask.m14309a(FileTask.this, arrayList);
            return arrayList;
        }

        @Override // android.os.AsyncTask
        protected void onPostExecute(Object obj) {
            MessgeBean messgeBean = new MessgeBean();
            if (FileTask.m14310a(FileTask.this) == null || FileTask.m14310a(FileTask.this).size() <= 0) {
                messgeBean.setCode(1);
                messgeBean.setMsg("Error");
                messgeBean.setData(FileTask.m14310a(FileTask.this));
            } else {
                messgeBean.setCode(0);
                messgeBean.setMsg("OK");
                messgeBean.setData(FileTask.m14310a(FileTask.this));
            }
            LogUtils.m22036e("onPostExecute", "sendAll");
            if (FileTask.m14307b(FileTask.this) == 0) {
                SocketManagerServer b = SocketManagerServer.m19692b();
                b.m19699a(SocketConstants.f15227Z + GsonUtil.m13969a(messgeBean));
                return;
            }
            SocketManagerServer b2 = SocketManagerServer.m19692b();
            b2.m19698a(SocketConstants.f15227Z + GsonUtil.m13969a(messgeBean), FileTask.m14307b(FileTask.this));
        }
    }

    /* renamed from: a */
    private void m14313a(File file) {
        if (file.isDirectory()) {
            this.f15290c.put(file.getAbsolutePath() + File.separator, file);
            for (File file2 : file.listFiles()) {
                m14313a(file2);
            }
        }
    }

    /* renamed from: b */
    private void m14308b(File file) {
        Map map = this.f15290c;
        if (map != null) {
            map.clear();
            Map map2 = this.f15290c;
            map2.put(file.getAbsolutePath() + File.separator, file);
            m14313a(file);
        }
    }

    /* renamed from: a */
    static ArrayList<FileBean> m14310a(FileTask adhVar) {
        return adhVar.f15289b;
    }

    /* renamed from: a */
    static ArrayList<FileBean> m14309a(FileTask adhVar, ArrayList<FileBean> arrayList) {
        adhVar.f15289b = arrayList;
        return arrayList;
    }

    /* renamed from: b */
    static int m14307b(FileTask adhVar) {
        return adhVar.f15288a;
    }

    /* renamed from: a */
    public void m14311a(String str, int i) {
        LogUtils.m22036e("getFile: ", str);
        this.f15288a = i;
        new AsyncTaskC3337a(this, str).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "");
    }

    /* renamed from: a */
    public void m14312a(String str) {
        LogUtils.m22036e("getFile: ", str);
        new AsyncTaskC3337a(this, str).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "");
    }

    /* renamed from: a */
    public static synchronized FileTask m14314a() {
        FileTask adhVar;
        synchronized (FileTask.class) {
            if (f15287d == null) {
                f15287d = new FileTask();
            }
            adhVar = f15287d;
        }
        return adhVar;
    }
}
