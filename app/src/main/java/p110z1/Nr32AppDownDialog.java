package p110z1;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.graphics.drawable.PathInterpolatorCompat;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.SPUtils;
import com.nrzs.game.C2029R;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* renamed from: z1.aor */
/* loaded from: classes3.dex */
public class Nr32AppDownDialog extends Dialog {

    /* renamed from: a */
    private static int f17015a = 801;

    /* renamed from: b */
    private static int f17016b = 802;

    /* renamed from: c */
    private static int f17017c = 803;

    /* renamed from: d */
    private static int f17018d = 804;

    /* renamed from: e */
    private static String f17019e = "com.angel.nrzs";

    /* renamed from: f */
    private Activity f17020f;

    /* renamed from: g */
    private RelativeLayout f17021g;

    /* renamed from: h */
    private ProgressBar f17022h;

    /* renamed from: i */
    private TextView f17023i;

    /* renamed from: j */
    private amx f17024j;

    /* renamed from: k */
    private DownLoadTask f17025k;

    /* renamed from: l */
    private ThreadPoolExecutor f17026l;

    /* renamed from: m */
    private Map<String, amx> f17027m;

    /* renamed from: n */
    private Gson f17028n;

    /* renamed from: o */
    private String f17029o;

    /* renamed from: p */
    private ImageView f17030p;

    /* renamed from: q */
    private TextView f17031q;

    /* renamed from: r */
    private Handler f17032r = new Handler() { // from class: z1.aor.6
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what == Nr32AppDownDialog.f17015a) {
                Nr32AppDownDialog.this.f17024j = (amx) message.obj;
                Toast.makeText(Nr32AppDownDialog.this.f17020f, "下载完成", 1).show();
                File file = new File(GlobalConstants.f16479b, Nr32AppDownDialog.this.f17024j.getFileName());
                if (file.exists()) {
                    AppUtils.m22950a(file.getAbsolutePath());
                }
                try {
                    Nr32AppDownDialog.this.f17027m.remove(Nr32AppDownDialog.this.f17024j.getUrl());
                    SPUtils.m23341a().m23332a(Nr32AppDownDialog.class.getSimpleName(), Nr32AppDownDialog.this.f17028n.m1575b(Nr32AppDownDialog.this.f17027m));
                } catch (Exception unused) {
                }
                Nr32AppDownDialog.this.dismiss();
            }
            if (message.what == Nr32AppDownDialog.f17016b) {
                Nr32AppDownDialog.this.f17024j = (amx) message.obj;
                Toast.makeText(Nr32AppDownDialog.this.f17020f, "更新失败，请稍后重试", 1).show();
                Nr32AppDownDialog.this.f17021g.setVisibility(8);
                Nr32AppDownDialog.this.f17031q.setText("重试");
                Nr32AppDownDialog.this.f17031q.setVisibility(0);
            }
            if (message.what == Nr32AppDownDialog.f17017c) {
                Nr32AppDownDialog.this.f17024j = (amx) message.obj;
                if (!Nr32AppDownDialog.this.f17024j.isStop()) {
                    Nr32AppDownDialog.this.f17023i.setText("更新中");
                }
                Nr32AppDownDialog.this.f17022h.setMax(message.arg1);
                Nr32AppDownDialog.this.f17022h.setProgress(message.arg2);
                Nr32AppDownDialog.this.f17024j = (amx) message.obj;
            }
            if (message.what == Nr32AppDownDialog.f17018d) {
                Nr32AppDownDialog.this.f17024j = (amx) message.obj;
                try {
                    Nr32AppDownDialog.this.f17024j.setMD5(FileUtils.m22182t(new File(Nr32AppDownDialog.this.f17024j.getSaveUri())));
                    Nr32AppDownDialog.this.f17027m.put(Nr32AppDownDialog.this.f17024j.getUrl(), Nr32AppDownDialog.this.f17024j);
                    SPUtils.m23341a().m23332a(Nr32AppDownDialog.class.getSimpleName(), Nr32AppDownDialog.this.f17028n.m1575b(Nr32AppDownDialog.this.f17027m));
                } catch (Exception unused2) {
                }
            }
        }
    };

    /* renamed from: f */
    private void m11994f() {
    }

    public Nr32AppDownDialog(Activity activity, String str) {
        super(activity, C2029R.style.NRZS_Game_my_dialog);
        this.f17020f = activity;
        this.f17029o = str;
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f17026l = new ThreadPoolExecutor(50, 50, 50L, TimeUnit.SECONDS, new ArrayBlockingQueue(PathInterpolatorCompat.MAX_NUM_POINTS));
        this.f17028n = new Gson();
        String b = SPUtils.m23341a().m23324b(Nr32AppDownDialog.class.getSimpleName());
        if (!b.isEmpty()) {
            this.f17027m = (Map) this.f17028n.m1588a(b, new TypeToken<HashMap<String, amx>>() { // from class: z1.aor.1
            }.getType());
        } else {
            this.f17027m = new HashMap();
        }
        setContentView(C2029R.layout.nrzs_game_va_32app_install);
        m11988i();
        m11996e();
        m11992g();
        m11994f();
        setCancelable(true);
        setCanceledOnTouchOutside(true);
    }

    /* renamed from: e */
    private void m11996e() {
        if (AppUtils.m22927e("com.angel.nrzs")) {
            this.f17031q.setText("打开32位鸟人助手");
        }
    }

    /* renamed from: g */
    private void m11992g() {
        setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: z1.aor.2
            @Override // android.content.DialogInterface.OnKeyListener
            public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                return i == 4 && keyEvent.getAction() == 1;
            }
        });
        this.f17030p.setOnClickListener(new View.OnClickListener() { // from class: z1.aor.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Nr32AppDownDialog.this.dismiss();
            }
        });
        this.f17021g.setOnClickListener(new View.OnClickListener() { // from class: z1.aor.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (Nr32AppDownDialog.this.f17024j.isStop()) {
                    Nr32AppDownDialog.this.f17023i.setText("连接中");
                    Nr32AppDownDialog.this.m11986j();
                    return;
                }
                Nr32AppDownDialog.this.f17023i.setText("暂停更新");
                Nr32AppDownDialog.this.m11984k();
            }
        });
        this.f17031q.setOnClickListener(new View.OnClickListener() { // from class: z1.aor.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (AppUtils.m22927e("com.angel.nrzs")) {
                    AppUtils.m22917j("com.angel.nrzs");
                    Nr32AppDownDialog.this.dismiss();
                    return;
                }
                Nr32AppDownDialog.this.f17021g.setVisibility(0);
                Nr32AppDownDialog.this.f17031q.setVisibility(8);
                Nr32AppDownDialog.this.f17022h.setMax(10000);
                Nr32AppDownDialog.this.f17022h.setProgress(1);
                view.post(new Runnable() { // from class: z1.aor.5.1
                    @Override // java.lang.Runnable
                    public void run() {
                        File file = new File(GlobalConstants.f16479b, Nr32AppDownDialog.this.m11990h());
                        if (file.exists()) {
                            AppUtils.m22950a(file.getAbsolutePath());
                            Nr32AppDownDialog.this.dismiss();
                            return;
                        }
                        if (Nr32AppDownDialog.this.f17027m != null && Nr32AppDownDialog.this.f17027m.size() > 0) {
                            Nr32AppDownDialog.this.f17024j = (amx) Nr32AppDownDialog.this.f17027m.get(Nr32AppDownDialog.this.f17029o);
                            if (Nr32AppDownDialog.this.f17024j != null) {
                                if (Nr32AppDownDialog.this.f17024j.getMD5() == null || Nr32AppDownDialog.this.f17024j.getMD5().isEmpty()) {
                                    String str = GlobalConstants.f16479b;
                                    File file2 = new File(str, Nr32AppDownDialog.this.f17024j.getFileName() + "-temp");
                                    if (file2.exists()) {
                                        file2.delete();
                                        Nr32AppDownDialog.this.f17027m.remove(Nr32AppDownDialog.this.f17024j);
                                        Nr32AppDownDialog.this.f17024j = null;
                                    }
                                } else {
                                    String str2 = GlobalConstants.f16479b;
                                    File file3 = new File(str2, Nr32AppDownDialog.this.f17024j.getFileName() + "-temp");
                                    if (file3.exists() && !FileUtils.m22182t(file3).equals(Nr32AppDownDialog.this.f17024j.getMD5())) {
                                        file3.delete();
                                        Nr32AppDownDialog.this.f17027m.remove(Nr32AppDownDialog.this.f17024j);
                                        Nr32AppDownDialog.this.f17024j = null;
                                    }
                                }
                            }
                        }
                        if (Nr32AppDownDialog.this.f17024j == null) {
                            Nr32AppDownDialog.this.f17024j = new amx();
                            Nr32AppDownDialog.this.f17024j.setSaveDir(GlobalConstants.f16479b);
                            Nr32AppDownDialog.this.f17024j.setFileName(Nr32AppDownDialog.this.m11990h());
                            Nr32AppDownDialog.this.f17024j.setUrl(Nr32AppDownDialog.this.f17029o);
                            Nr32AppDownDialog.this.f17027m.put(Nr32AppDownDialog.this.f17024j.getUrl(), Nr32AppDownDialog.this.f17024j);
                        }
                        Nr32AppDownDialog.this.m11986j();
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: h */
    public String m11990h() {
        String str = f17019e;
        if (TextUtils.isEmpty(this.f17029o)) {
            return str;
        }
        try {
            return this.f17029o.substring(this.f17029o.lastIndexOf("/") + 1);
        } catch (Exception e) {
            e.printStackTrace();
            return str;
        }
    }

    /* renamed from: i */
    private void m11988i() {
        this.f17031q = (TextView) findViewById(C2029R.C2031id.nrzs_game_tv_install_64);
        this.f17021g = (RelativeLayout) findViewById(C2029R.C2031id.rl_progressbar);
        this.f17022h = (ProgressBar) findViewById(C2029R.C2031id.progress);
        this.f17023i = (TextView) findViewById(C2029R.C2031id.tv_progressbar);
        this.f17030p = (ImageView) findViewById(C2029R.C2031id.nrzs_game_iv_install_64_close);
    }

    @Override // android.app.Dialog
    protected void onStop() {
        amx amxVar = this.f17024j;
        if (amxVar != null && !amxVar.isStop()) {
            this.f17024j.setStop(true);
        }
        DownLoadTask amwVar = this.f17025k;
        if (amwVar != null) {
            this.f17026l.remove(amwVar);
            this.f17025k = null;
        }
        SPUtils.m23341a().m23332a(Nr32AppDownDialog.class.getSimpleName(), this.f17028n.m1575b(this.f17027m));
        super.onStop();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: j */
    public void m11986j() {
        this.f17024j.setStop(false);
        if (this.f17025k == null) {
            this.f17025k = new DownLoadTask(getContext(), this.f17024j, new OnDownLoaderListener() { // from class: z1.aor.7
                @Override // p110z1.OnDownLoaderListener
                /* renamed from: a */
                public void mo3049a(int i, int i2, double d, amx amxVar) {
                    Message message = new Message();
                    message.what = Nr32AppDownDialog.f17017c;
                    message.arg1 = i;
                    message.arg2 = i2;
                    message.obj = amxVar;
                    Nr32AppDownDialog.this.f17032r.sendMessage(message);
                }

                @Override // p110z1.OnDownLoaderListener
                /* renamed from: a */
                public void mo3048a(amx amxVar) {
                    Message message = new Message();
                    message.what = Nr32AppDownDialog.f17018d;
                    message.obj = amxVar;
                    Nr32AppDownDialog.this.f17032r.sendMessage(message);
                }

                @Override // p110z1.OnDownLoaderListener
                /* renamed from: a */
                public void mo3047a(amx amxVar, String str) {
                    Message message = new Message();
                    message.what = Nr32AppDownDialog.f17016b;
                    message.obj = amxVar;
                    Nr32AppDownDialog.this.f17032r.sendMessage(message);
                }

                @Override // p110z1.OnDownLoaderListener
                /* renamed from: b */
                public void mo3046b(amx amxVar) {
                    Message message = new Message();
                    message.what = Nr32AppDownDialog.f17015a;
                    message.obj = amxVar;
                    Nr32AppDownDialog.this.f17032r.sendMessage(message);
                }
            });
            this.f17026l.execute(this.f17025k);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: k */
    public void m11984k() {
        this.f17024j.setStop(true);
    }
}
