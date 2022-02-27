package p110z1;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.graphics.drawable.PathInterpolatorCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.angel.nrzs.C0692R;
import com.angel.nrzs.adapter.UpdateContentAdapter;
import com.angel.nrzs.app.activity.MainActivity;
import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.SPUtils;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* compiled from: AppUpdateDialog.java */
/* renamed from: z1.es */
/* loaded from: classes3.dex */
public class DialogC5320es extends Dialog {

    /* renamed from: a */
    private static int f21497a = 801;

    /* renamed from: b */
    private static int f21498b = 802;

    /* renamed from: c */
    private static int f21499c = 803;

    /* renamed from: d */
    private static int f21500d = 804;

    /* renamed from: x */
    private static final long f21501x = 3000;

    /* renamed from: e */
    private UpdateBean f21502e;

    /* renamed from: f */
    private Activity f21503f;

    /* renamed from: g */
    private TextView f21504g;

    /* renamed from: h */
    private ImageView f21505h;

    /* renamed from: i */
    private TextView f21506i;

    /* renamed from: j */
    private TextView f21507j;

    /* renamed from: k */
    private TextView f21508k;

    /* renamed from: l */
    private RecyclerView f21509l;

    /* renamed from: m */
    private UpdateContentAdapter f21510m;

    /* renamed from: n */
    private RelativeLayout f21511n;

    /* renamed from: o */
    private ProgressBar f21512o;

    /* renamed from: p */
    private TextView f21513p;

    /* renamed from: q */
    private amx f21514q;

    /* renamed from: r */
    private InstallUtil f21515r;

    /* renamed from: s */
    private DownLoadTask f21516s;

    /* renamed from: t */
    private ThreadPoolExecutor f21517t;

    /* renamed from: u */
    private Map<String, amx> f21518u;

    /* renamed from: v */
    private Gson f21519v;

    /* renamed from: w */
    private boolean f21520w;

    /* renamed from: y */
    private long f21521y = 0;

    /* renamed from: z */
    private Handler f21522z = new Handler() { // from class: z1.es.6
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what == DialogC5320es.f21497a) {
                DialogC5320es.this.f21514q = (amx) message.obj;
                Toast.makeText(DialogC5320es.this.f21503f, "下载完成", 1).show();
                File file = new File(GlobalConstants.f16479b, DialogC5320es.this.f21502e.getAppName());
                if (file.exists()) {
                    DialogC5320es esVar = DialogC5320es.this;
                    esVar.f21515r = new InstallUtil(esVar.f21503f, file.getAbsolutePath());
                    DialogC5320es.this.f21515r.m2900a();
                }
                try {
                    DialogC5320es.this.f21518u.remove(DialogC5320es.this.f21514q.getUrl());
                    SPUtils.m23341a().m23332a(DialogC5320es.class.getSimpleName(), DialogC5320es.this.f21519v.m1575b(DialogC5320es.this.f21518u));
                } catch (Exception unused) {
                }
                DialogC5320es.this.dismiss();
            }
            if (message.what == DialogC5320es.f21498b) {
                DialogC5320es.this.f21514q = (amx) message.obj;
                Toast.makeText(DialogC5320es.this.f21503f, "更新失败，请稍后重试", 1).show();
                DialogC5320es.this.f21511n.setVisibility(8);
                DialogC5320es.this.f21508k.setText(DialogC5320es.this.getContext().getResources().getString(C0692R.string.f3942ko));
                DialogC5320es.this.f21504g.setText("重试");
                DialogC5320es.this.f21504g.setVisibility(0);
            }
            if (message.what == DialogC5320es.f21499c) {
                DialogC5320es.this.f21514q = (amx) message.obj;
                if (!DialogC5320es.this.f21514q.isStop()) {
                    DialogC5320es.this.f21513p.setText("更新中");
                }
                DialogC5320es.this.f21512o.setMax(message.arg1);
                DialogC5320es.this.f21512o.setProgress(message.arg2);
                DialogC5320es.this.f21514q = (amx) message.obj;
            }
            if (message.what == DialogC5320es.f21500d) {
                DialogC5320es.this.f21514q = (amx) message.obj;
                try {
                    DialogC5320es.this.f21514q.setMD5(FileUtils.m22182t(new File(DialogC5320es.this.f21514q.getSaveUri())));
                    DialogC5320es.this.f21518u.put(DialogC5320es.this.f21514q.getUrl(), DialogC5320es.this.f21514q);
                    SPUtils.m23341a().m23332a(DialogC5320es.class.getSimpleName(), DialogC5320es.this.f21519v.m1575b(DialogC5320es.this.f21518u));
                } catch (Exception unused2) {
                }
            }
        }
    };

    /* renamed from: e */
    private void m3065e() {
    }

    public DialogC5320es(Activity activity, UpdateBean ajkVar, boolean z) {
        super(activity, C0692R.style.f4318es);
        this.f21502e = ajkVar;
        this.f21503f = activity;
        this.f21520w = z;
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f21517t = new ThreadPoolExecutor(50, 50, 50L, TimeUnit.SECONDS, new ArrayBlockingQueue(PathInterpolatorCompat.MAX_NUM_POINTS));
        this.f21519v = new Gson();
        String b = SPUtils.m23341a().m23324b(DialogC5320es.class.getSimpleName());
        if (!b.isEmpty()) {
            this.f21518u = (Map) this.f21519v.m1588a(b, new TypeToken<HashMap<String, amx>>() { // from class: z1.es.1
            }.getType());
        } else {
            this.f21518u = new HashMap();
        }
        setContentView(C0692R.layout.nrzs_dialog_appupdate);
        m3061g();
        m3063f();
        m3065e();
        int i = 0;
        if (this.f21520w) {
            setCancelable(false);
            setCanceledOnTouchOutside(false);
        } else {
            setCancelable(true);
            setCanceledOnTouchOutside(true);
        }
        ImageView imageView = this.f21505h;
        if (this.f21520w) {
            i = 8;
        }
        imageView.setVisibility(i);
    }

    /* renamed from: f */
    private void m3063f() {
        setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: z1.es.2
            @Override // android.content.DialogInterface.OnKeyListener
            public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                if (i != 4 || keyEvent.getAction() != 1) {
                    return false;
                }
                Log.e("", "");
                if (DialogC5320es.this.f21503f instanceof MainActivity) {
                    if (DialogC5320es.this.f21521y == 0) {
                        DialogC5320es.this.f21521y = System.currentTimeMillis();
                        aqg.m11586a(DialogC5320es.this.f21503f, DialogC5320es.this.f21503f.getResources().getString(C0692R.string.common_message_exit_app));
                    } else {
                        long currentTimeMillis = System.currentTimeMillis();
                        if (currentTimeMillis - DialogC5320es.this.f21521y > DialogC5320es.f21501x) {
                            DialogC5320es.this.f21521y = currentTimeMillis;
                            aqg.m11586a(DialogC5320es.this.f21503f, DialogC5320es.this.f21503f.getResources().getString(C0692R.string.common_message_exit_app));
                        } else {
                            DialogC5320es.this.f21503f.finish();
                        }
                    }
                }
                return true;
            }
        });
        this.f21511n.setOnClickListener(new View.OnClickListener() { // from class: z1.es.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (DialogC5320es.this.f21514q.isStop()) {
                    DialogC5320es.this.f21513p.setText("连接中");
                    DialogC5320es.this.m3059h();
                    return;
                }
                DialogC5320es.this.f21513p.setText("暂停更新");
                DialogC5320es.this.m3057i();
            }
        });
        this.f21505h.setOnClickListener(new View.OnClickListener() { // from class: z1.es.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DialogC5320es.this.dismiss();
            }
        });
        this.f21504g.setOnClickListener(new View.OnClickListener() { // from class: z1.es.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DialogC5320es.this.f21508k.setText("");
                DialogC5320es.this.f21504g.setVisibility(8);
                DialogC5320es.this.f21511n.setVisibility(0);
                DialogC5320es.this.f21512o.setMax(10000);
                DialogC5320es.this.f21512o.setProgress(1);
                view.post(new Runnable() { // from class: z1.es.5.1
                    @Override // java.lang.Runnable
                    public void run() {
                        File file = new File(GlobalConstants.f16479b, DialogC5320es.this.f21502e.getAppName());
                        if (file.exists()) {
                            DialogC5320es.this.f21515r = new InstallUtil(DialogC5320es.this.f21503f, file.getAbsolutePath());
                            DialogC5320es.this.f21515r.m2900a();
                            DialogC5320es.this.dismiss();
                            return;
                        }
                        if (DialogC5320es.this.f21518u != null && DialogC5320es.this.f21518u.size() > 0) {
                            DialogC5320es.this.f21514q = (amx) DialogC5320es.this.f21518u.get(DialogC5320es.this.f21502e.getUrl());
                            if (DialogC5320es.this.f21514q != null) {
                                if (DialogC5320es.this.f21514q.getMD5() == null || DialogC5320es.this.f21514q.getMD5().isEmpty()) {
                                    String str = GlobalConstants.f16479b;
                                    File file2 = new File(str, DialogC5320es.this.f21502e.getAppName() + "-temp");
                                    if (file2.exists()) {
                                        file2.delete();
                                        DialogC5320es.this.f21518u.remove(DialogC5320es.this.f21514q);
                                        DialogC5320es.this.f21514q = null;
                                    }
                                } else {
                                    String str2 = GlobalConstants.f16479b;
                                    File file3 = new File(str2, DialogC5320es.this.f21502e.getAppName() + "-temp");
                                    if (file3.exists() && !FileUtils.m22182t(file3).equals(DialogC5320es.this.f21514q.getMD5())) {
                                        file3.delete();
                                        DialogC5320es.this.f21518u.remove(DialogC5320es.this.f21514q);
                                        DialogC5320es.this.f21514q = null;
                                    }
                                }
                            }
                        }
                        if (DialogC5320es.this.f21514q == null) {
                            DialogC5320es.this.f21514q = new amx();
                            DialogC5320es.this.f21514q.setSaveDir(GlobalConstants.f16479b);
                            DialogC5320es.this.f21514q.setFileName(DialogC5320es.this.f21502e.getAppName());
                            DialogC5320es.this.f21514q.setUrl(DialogC5320es.this.f21502e.getUrl());
                            DialogC5320es.this.f21518u.put(DialogC5320es.this.f21514q.getUrl(), DialogC5320es.this.f21514q);
                        }
                        DialogC5320es.this.m3059h();
                    }
                });
            }
        });
    }

    /* renamed from: g */
    private void m3061g() {
        this.f21504g = (TextView) findViewById(C0692R.C0694id.btn_update_immediately);
        this.f21505h = (ImageView) findViewById(C0692R.C0694id.btn_close);
        this.f21506i = (TextView) findViewById(C0692R.C0694id.text_app_size);
        this.f21507j = (TextView) findViewById(C0692R.C0694id.text_version);
        this.f21511n = (RelativeLayout) findViewById(C0692R.C0694id.f2986t6);
        this.f21512o = (ProgressBar) findViewById(C0692R.C0694id.pb_progressbar);
        this.f21513p = (TextView) findViewById(C0692R.C0694id.f3160ym);
        this.f21508k = (TextView) findViewById(C0692R.C0694id.tv_error_show);
        this.f21509l = (RecyclerView) findViewById(C0692R.C0694id.recycler_update_content_list);
        this.f21509l.setLayoutManager(new LinearLayoutManager(getContext()));
        UpdateBean ajkVar = this.f21502e;
        if (ajkVar != null) {
            if (ajkVar.getUpdateContent() != null) {
                this.f21510m = new UpdateContentAdapter(this.f21502e.getUpdateContent());
                if (this.f21510m.getItemCount() <= 0) {
                    this.f21509l.setVisibility(8);
                } else {
                    this.f21509l.setAdapter(this.f21510m);
                }
            }
            this.f21507j.setText(Html.fromHtml("版本号：<font color='#444444'>v" + this.f21502e.getVersion() + "</font>"));
            this.f21506i.setText(Html.fromHtml("大小：<font color='#444444'>" + this.f21502e.getSize() + "M</font>"));
        }
    }

    @Override // android.app.Dialog
    protected void onStop() {
        amx amxVar = this.f21514q;
        if (amxVar != null && !amxVar.isStop()) {
            this.f21514q.setStop(true);
        }
        DownLoadTask amwVar = this.f21516s;
        if (amwVar != null) {
            this.f21517t.remove(amwVar);
            this.f21516s = null;
        }
        SPUtils.m23341a().m23332a(DialogC5320es.class.getSimpleName(), this.f21519v.m1575b(this.f21518u));
        super.onStop();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: h */
    public void m3059h() {
        this.f21514q.setStop(false);
        if (this.f21516s == null) {
            this.f21516s = new DownLoadTask(getContext(), this.f21514q, new OnDownLoaderListener() { // from class: z1.es.7
                @Override // p110z1.OnDownLoaderListener
                /* renamed from: a */
                public void mo3049a(int i, int i2, double d, amx amxVar) {
                    Message message = new Message();
                    message.what = DialogC5320es.f21499c;
                    message.arg1 = i;
                    message.arg2 = i2;
                    message.obj = amxVar;
                    DialogC5320es.this.f21522z.sendMessage(message);
                }

                @Override // p110z1.OnDownLoaderListener
                /* renamed from: a */
                public void mo3048a(amx amxVar) {
                    Message message = new Message();
                    message.what = DialogC5320es.f21500d;
                    message.obj = amxVar;
                    DialogC5320es.this.f21522z.sendMessage(message);
                }

                @Override // p110z1.OnDownLoaderListener
                /* renamed from: a */
                public void mo3047a(amx amxVar, String str) {
                    Message message = new Message();
                    message.what = DialogC5320es.f21498b;
                    message.obj = amxVar;
                    DialogC5320es.this.f21522z.sendMessage(message);
                }

                @Override // p110z1.OnDownLoaderListener
                /* renamed from: b */
                public void mo3046b(amx amxVar) {
                    Message message = new Message();
                    message.what = DialogC5320es.f21497a;
                    message.obj = amxVar;
                    DialogC5320es.this.f21522z.sendMessage(message);
                }
            });
            this.f21517t.execute(this.f21516s);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: i */
    public void m3057i() {
        this.f21514q.setStop(true);
    }
}
