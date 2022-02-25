package p110z1;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/* renamed from: z1.aic */
/* loaded from: classes3.dex */
public class VisualTouchHelp {

    /* renamed from: a */
    private long f15908a = 0;

    /* renamed from: b */
    private long f15909b = 0;

    /* renamed from: c */
    private float f15910c = 0.0f;

    /* renamed from: d */
    private float f15911d = 0.0f;

    /* renamed from: e */
    private int f15912e = 0;

    /* renamed from: f */
    private int f15913f = 0;

    /* renamed from: g */
    private float f15914g;

    /* renamed from: h */
    private float f15915h;

    /* renamed from: i */
    private int f15916i;

    /* renamed from: j */
    private int f15917j;

    /* renamed from: k */
    private float f15918k;

    /* renamed from: l */
    private float f15919l;

    /* renamed from: m */
    private boolean f15920m;

    /* renamed from: n */
    private ITouchHelp f15921n;

    /* renamed from: o */
    private RelativeLayout f15922o;

    /* renamed from: p */
    private RelativeLayout.LayoutParams f15923p;

    public VisualTouchHelp(ITouchHelp aidVar, RelativeLayout relativeLayout, RelativeLayout.LayoutParams layoutParams) {
        this.f15921n = aidVar;
        this.f15922o = relativeLayout;
        this.f15923p = layoutParams;
    }

    /* renamed from: a */
    public void m13118a(int i, int i2) {
        this.f15912e = i;
        this.f15913f = i2;
    }

    /* renamed from: a */
    public void m13116a(RelativeLayout.LayoutParams layoutParams) {
        this.f15923p = layoutParams;
    }

    /* renamed from: a */
    public void m13117a(ImageView imageView) {
        imageView.setOnTouchListener(new View.OnTouchListener() { // from class: z1.aic.1
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int i;
                int i2 = 0;
                switch (motionEvent.getAction()) {
                    case 0:
                        VisualTouchHelp.this.f15910c = 0.0f;
                        VisualTouchHelp.this.f15911d = 0.0f;
                        VisualTouchHelp.this.f15920m = false;
                        VisualTouchHelp.this.f15908a = System.currentTimeMillis();
                        VisualTouchHelp.this.f15921n.mo13090a();
                        VisualTouchHelp.this.f15914g = motionEvent.getRawX();
                        VisualTouchHelp.this.f15915h = motionEvent.getRawY();
                        VisualTouchHelp.this.f15918k = motionEvent.getRawX();
                        VisualTouchHelp.this.f15919l = motionEvent.getRawY();
                        break;
                    case 1:
                        VisualTouchHelp.this.f15909b = System.currentTimeMillis();
                        if (VisualTouchHelp.this.f15920m || VisualTouchHelp.this.f15909b - VisualTouchHelp.this.f15908a >= 200.0d) {
                            int i3 = VisualTouchHelp.this.f15923p.leftMargin;
                            int i4 = VisualTouchHelp.this.f15923p.rightMargin;
                            int i5 = VisualTouchHelp.this.f15923p.topMargin;
                            int i6 = VisualTouchHelp.this.f15923p.bottomMargin;
                            Log.i("DDYLBSD", "up rootView.getWidth():" + VisualTouchHelp.this.f15922o.getWidth());
                            Log.i("DDYLBSD", "up rootView.getHeight():" + VisualTouchHelp.this.f15922o.getHeight());
                            Log.i("DDYLBSD", "up mScreenWidth:" + VisualTouchHelp.this.f15912e);
                            Log.i("DDYLBSD", "up mScreenHeight:" + VisualTouchHelp.this.f15913f);
                            Log.i("DDYLBSD", "up mainLayoutParam.leftMargin:" + VisualTouchHelp.this.f15923p.leftMargin);
                            Log.i("DDYLBSD", "up mainLayoutParam.rightMargin:" + VisualTouchHelp.this.f15923p.rightMargin);
                            Log.i("DDYLBSD", "up mainLayoutParam.bottomMargin:" + VisualTouchHelp.this.f15923p.bottomMargin);
                            Log.i("DDYLBSD", "up mainLayoutParam.topMargin:" + VisualTouchHelp.this.f15923p.topMargin);
                            if (i3 < 0) {
                                i4 = VisualTouchHelp.this.f15912e - VisualTouchHelp.this.f15922o.getWidth();
                                i = 0;
                            } else {
                                i = i3;
                            }
                            if (i4 < 0) {
                                i = VisualTouchHelp.this.f15912e - VisualTouchHelp.this.f15922o.getWidth();
                                i4 = 0;
                            }
                            if (i6 < 0) {
                                i5 = VisualTouchHelp.this.f15913f - VisualTouchHelp.this.f15922o.getHeight();
                                i6 = 0;
                            }
                            if (i5 < 0) {
                                i6 = VisualTouchHelp.this.f15913f - VisualTouchHelp.this.f15922o.getHeight();
                            } else {
                                i2 = i5;
                            }
                            VisualTouchHelp.this.f15923p.leftMargin = i;
                            VisualTouchHelp.this.f15923p.rightMargin = i4;
                            VisualTouchHelp.this.f15923p.bottomMargin = i6;
                            VisualTouchHelp.this.f15923p.topMargin = i2;
                            Log.i("DDYLBSD", "mainLayoutParam.leftMargin:" + VisualTouchHelp.this.f15923p.leftMargin);
                            Log.i("DDYLBSD", "mainLayoutParam.rightMargin:" + VisualTouchHelp.this.f15923p.rightMargin);
                            Log.i("DDYLBSD", "mainLayoutParam.bottomMargin:" + VisualTouchHelp.this.f15923p.bottomMargin);
                            Log.i("DDYLBSD", "mainLayoutParam.topMargin:" + VisualTouchHelp.this.f15923p.topMargin);
                            VisualTouchHelp.this.f15921n.setLayoutParams(VisualTouchHelp.this.f15923p);
                            break;
                        } else {
                            VisualTouchHelp.this.f15921n.mo13089b();
                            return true;
                        }
                        break;
                    case 2:
                        motionEvent.getRawX();
                        motionEvent.getRawY();
                        motionEvent.getX();
                        motionEvent.getY();
                        int rawX = (int) (motionEvent.getRawX() - VisualTouchHelp.this.f15914g);
                        int rawY = (int) (motionEvent.getRawY() - VisualTouchHelp.this.f15915h);
                        VisualTouchHelp.this.f15910c += Math.abs(rawX);
                        VisualTouchHelp.this.f15911d += Math.abs(rawY);
                        if (VisualTouchHelp.this.f15910c <= 25.0f && VisualTouchHelp.this.f15911d <= 25.0f) {
                            VisualTouchHelp.this.f15920m = false;
                            break;
                        } else {
                            VisualTouchHelp.this.f15920m = true;
                            VisualTouchHelp.this.f15914g = motionEvent.getRawX();
                            VisualTouchHelp.this.f15915h = motionEvent.getRawY();
                            VisualTouchHelp.this.f15916i += rawX;
                            VisualTouchHelp.this.f15917j += rawY;
                            VisualTouchHelp.this.f15923p.leftMargin = VisualTouchHelp.this.f15922o.getLeft() + rawX;
                            VisualTouchHelp.this.f15923p.topMargin = VisualTouchHelp.this.f15922o.getTop() + rawY;
                            VisualTouchHelp.this.f15923p.rightMargin = (VisualTouchHelp.this.f15912e - VisualTouchHelp.this.f15923p.leftMargin) - VisualTouchHelp.this.f15922o.getWidth();
                            VisualTouchHelp.this.f15923p.bottomMargin = (VisualTouchHelp.this.f15913f - VisualTouchHelp.this.f15923p.topMargin) - VisualTouchHelp.this.f15922o.getHeight();
                            Log.i("DDYLBSD", "move mainLayoutParam.leftMargin:" + VisualTouchHelp.this.f15923p.leftMargin);
                            Log.i("DDYLBSD", "move mainLayoutParam.rightMargin:" + VisualTouchHelp.this.f15923p.rightMargin);
                            Log.i("DDYLBSD", "move mainLayoutParam.bottomMargin:" + VisualTouchHelp.this.f15923p.bottomMargin);
                            Log.i("DDYLBSD", "move mainLayoutParam.topMargin:" + VisualTouchHelp.this.f15923p.topMargin);
                            VisualTouchHelp.this.f15921n.setLayoutParams(VisualTouchHelp.this.f15923p);
                            VisualTouchHelp.this.f15914g = (float) ((int) motionEvent.getRawX());
                            VisualTouchHelp.this.f15915h = (float) ((int) motionEvent.getRawY());
                            break;
                        }
                        break;
                }
                return true;
            }
        });
    }
}
